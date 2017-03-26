package co.edu.eam.ingesoft.pa.negocio.beans;

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

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Banco;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CuentaAsociados;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Franchise;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ICuentaAsociadosRemote;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;


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
	 * @return la fecha actual
	 */
	public Date fechaActual(){
		return cardEJB.fechaExpedicion();
	}
	
	public void agregarCuentaAsociados(CuentaAsociados cuenta){
		CuentaAsociados cu = buscarCuentaAsociado(cuenta.getNumeroCuenta());
		if(cu!=null){
			
			cu.setVerificado(true);
			em.persist(cu);
			
		}else{
			throw new ExcepcionNegocio("Este numero no esta disponible,\n Digite otro numero");
		}
		
	}
	
	public CuentaAsociados buscarCuentaAsociado(String numero){
		return em.find(CuentaAsociados.class, numero);
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Banco> listaBancos(){
		
		Query q = em.createNamedQuery(Banco.LISTA_BANCOS);
		List<Banco> bancos = q.getResultList();
		return bancos;
		
	}
	
}
