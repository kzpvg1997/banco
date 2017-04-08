package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SegundaClave;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ISavingAccountRemote;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ISegundaClaveRemote;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@LocalBean
@Stateless
@Remote(ISegundaClaveRemote.class)
public class SegundaClaveEJB {
	
	@PersistenceContext
	private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearSegundaClave(SegundaClave s){
		SegundaClave busc = buscarSegundaClave(s.getClave());
		//no existe, se puede crear...
		if(busc==null){
			em.persist(s);
		}else{
			throw new ExcepcionNegocio("Ya existe la clave");
		}
		
	}
	

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public SegundaClave buscarSegundaClave(int num){
		return em.find(SegundaClave.class, num);
	}
	 
	
	public static int numeroAleatorio6(){
		Long numero = ThreadLocalRandom.current().nextLong(100000L, 999999L );
		System.out.println(numero);
		int numero1 = numero.intValue();
		return numero1;
		
		}
	


	public Date fechaExpedicionClave() {
		Calendar calendar = Calendar.getInstance();
		Date fecha = calendar.getTime();
		return fecha;
	}
	
	
	public Date fechaExpiracionClave() {
		Calendar calendar = Calendar.getInstance();
		Date fecha = calendar.getTime();
		fecha.setMinutes(fecha.getMinutes()+5);
		return fecha;
	}

	
	

}
