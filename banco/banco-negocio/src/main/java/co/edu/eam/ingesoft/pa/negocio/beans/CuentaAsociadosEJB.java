package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.ws.BindingProvider;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Banco;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CuentaAsociados;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ICuentaAsociadosRemote;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.eam.pa.clientews.Mail;
import co.edu.eam.pa.clientews.Notificaciones;
import co.edu.eam.pa.clientews.NotificacionesService;
import co.edu.eam.pa.clientews.RespuestaNotificacion;

@LocalBean
@Stateless
@Remote(ICuentaAsociadosRemote.class)
public class CuentaAsociadosEJB {
	

	@PersistenceContext
	private EntityManager em;

	@EJB
	private CreditCardEJB cardEJB;

	/**
	 * Metodo para obtener la fecha actual
	 * 
	 * @return la fecha actual
	 */
	public Date fechaActual() {
		return cardEJB.fechaExpedicion();
	}

	public void agregarCuentaAsociados(CuentaAsociados cuenta){
		CuentaAsociados cu = buscarCuentaAsociado(cuenta.getNumeroCuenta());
		if(cu!=null){

			throw new ExcepcionNegocio("Este numero no de cuenta esta disponible,\n Digite otro numero");	
		}else{
			if(BuscarIdAsociado(cu)!=null){
				em.persist(cuenta);
			}else{
				throw new ExcepcionNegocio("El asociado con numero de documento: "+cuenta.getIdAsociado()+
						" ya se encuentra registrado");	
			}
				
		}
		
	}

	public CuentaAsociados buscarCuentaAsociado(String numero) {
		return em.find(CuentaAsociados.class, numero);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Banco> listaBancos() {

		Query q = em.createNamedQuery(Banco.LISTA_BANCOS);
		List<Banco> bancos = q.getResultList();
		return bancos;

	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<CuentaAsociados> listaCuentasCliente(Customer customer) {

		Query q = em.createNamedQuery(CuentaAsociados.ASOCIACIONES_CLIENTE);
		q.setParameter(1, customer);
		List<CuentaAsociados> cuentas = q.getResultList();
		return cuentas;

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<CuentaAsociados> listarCuentasVerificadas(Customer customer) {

		List<CuentaAsociados> lista = listaCuentasCliente(customer);
		List<CuentaAsociados> verificadas = new ArrayList<CuentaAsociados>();
		for (CuentaAsociados c : lista) {
			if (c.isVerificado()) { // preguntar
				verificadas.add(c);
			}
		}
		return verificadas;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Banco buscarBanco(String id) {
		return em.find(Banco.class, id);
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void borrarCuentaAsociado(CuentaAsociados cu){
		cu=buscarCuentaAsociado(cu.getNumeroCuenta());
		em.remove(cu);
	}

	public String BuscarIdAsociado(CuentaAsociados cuen) {
		String valor = null;
		Query q = em.createNamedQuery(CuentaAsociados.BUSCAR_ID_ASOCIADO);
		q.setParameter(1, cuen);
		valor = String.valueOf(q.getFirstResult());
		System.out.println(valor);
		return valor;
		
	}
	
	
	public void verificarCuenta(CuentaAsociados cu) {
		
		CuentaAsociados c = buscarCuentaAsociado(cu.getNumeroCuenta());
		if(c!=null){
			if(c.isVerificado()==false){
				
				c.setVerificado(true);
				em.merge(c);
			}else{
				throw new ExcepcionNegocio("Esta cuenta ya se encuentra verificada");
			}
		}
		
		NotificacionesService cliente = new NotificacionesService();
        Notificaciones servicio = cliente.getNotificacionesPort();
        
        String endpointURL = "http://104.197.238.134:8080/notificaciones/notificacionesService";
       BindingProvider bp = (BindingProvider)servicio;
       bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);
        
        Mail correo = new Mail();
        correo.setBody("Hello World");
        correo.setFrom("BancoEAM@bank.com");
        correo.setTo("kzpvg1997@hotmail.com");
        correo.setSubject("Prueba de la clase");
        servicio.enviarMail(correo);
        
        RespuestaNotificacion resp = servicio.enviarMail(correo);
        System.out.println(resp.getMensaje()); 
		
	}
	
	
}
