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

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Bank;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CuentaAsociados;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SavingAccount;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ICuentaAsociadosRemote;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.eam.pa.clientews.Mail;
import co.edu.eam.pa.clientews.Notificaciones;
import co.edu.eam.pa.clientews.NotificacionesService;
import co.edu.eam.pa.clientews.RespuestaNotificacion;
import co.edu.eam.pa.interbancariows.InterbancarioWS;
import co.edu.eam.pa.interbancariows.InterbancarioWS_Service;
import co.edu.eam.pa.interbancariows.RegistrarCuentaAsociada;

@LocalBean
@Stateless
@Remote(ICuentaAsociadosRemote.class)
public class CuentaAsociadosEJB {
	

	@PersistenceContext
	private EntityManager em;

	@EJB
	private CreditCardEJB cardEJB;
	
	@EJB
	private CustomerEJB customerEJB;
	
	@EJB
	private ServiciosServidor servicioEJB;

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
						
				em.persist(cuenta);
				
		}
		
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
			if (c.getEstado().equalsIgnoreCase("Asociada")) { // preguntar
				verificadas.add(c);
			}
		}
		return verificadas;
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void borrarCuentaAsociado(CuentaAsociados cu){
		cu=buscarCuentaAsociado(cu.getNumeroCuenta());
		em.remove(cu);
	}

	public CuentaAsociados buscarCuentaAsociado(String numeroCuenta) {
		
		Query q = em.createNamedQuery(CuentaAsociados.BUSCAR_CUENTA_ASOCIADOS);
		q.setParameter(1, numeroCuenta);
		List<CuentaAsociados> cuentas = q.getResultList();
		if(cuentas.isEmpty()){
			return null;
		}else{
		return cuentas.get(0);
		}
	}
	
	
}
