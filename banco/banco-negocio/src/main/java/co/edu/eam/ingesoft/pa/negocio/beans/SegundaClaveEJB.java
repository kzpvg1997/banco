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
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCardConsume;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CuentaAsociados;
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
	public SegundaClave buscarSegundaClave(String num){
		return em.find(SegundaClave.class, num);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void borrarSegundaClave(SegundaClave cu){
		SegundaClave c =buscarSegundaClave(cu.getClave());
		em.remove(c);
	}
	
	public static String numeroAleatorio6(){
		Long numero = ThreadLocalRandom.current().nextLong(100000L, 999999L );
		System.out.println(numero);
		String numero1 = numero.toString();
		return numero1;
		
		}
	
	
	public Date fechaExpedicionClave() {
		Calendar calendar = Calendar.getInstance();
		Date fecha = calendar.getTime();
	    fecha.setMinutes(fecha.getMinutes()+2);
		return fecha;
	}
	
	public Date fechaExpiracion() {
		Calendar calendar = Calendar.getInstance();
		Date fecha = calendar.getTime();
		fecha.setYear(fecha.getYear()+4);
		return fecha;
	}


	
	

}
