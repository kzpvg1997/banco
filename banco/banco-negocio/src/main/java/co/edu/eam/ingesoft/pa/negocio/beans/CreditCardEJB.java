package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Product;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SavingAccount;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ICreditCardRemote;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@LocalBean
@Stateless
@Remote(ICreditCardRemote.class)
public class CreditCardEJB {
	
	@EJB
	private CreditCardEJB creditCardEJB;
	
	@EJB
	private SavingAccountEJB savingEJB;
	
	@EJB
	private ProductEJB productoEJB;
	
	@EJB
	private MensajeEJB msjEJB;

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * MEtodo para crear un creditcard...
	 * @param creditCard
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearCreditCard(CreditCard creditCard){			
		
		creditCard.setNumber(numeroAleatorio16());
		CreditCard busc=buscarCreditCard(creditCard.getNumber());
		
		//no existe, se puede crear...
		if(busc==null){
			if(contarTarjetasCliente(creditCard.getCustomer()) < 2){
				if(contarProductosCliente(creditCard.getCustomer()) < 5){
					Date fechaExpedicion = fechaExpedicion();
					creditCard.setExpeditionDate(fechaExpedicion);
					Date fechaExpiracion = fechaExpedicion();
					fechaExpedicion.setYear(fechaExpedicion.getYear()+4);
					creditCard.setExpirationDate(fechaExpedicion);
					creditCard.setCvc(numeroAleatorio3());
					creditCard.setDeuda(0.0);;
					em.persist(creditCard);
					
				}else{
					throw new ExcepcionNegocio("El cliente con cedula=''"+creditCard.getCustomer().getIdNum()+"'' Ya tiene 5 prodctos registrados");
				}
			}else{
				throw new ExcepcionNegocio("El cliente con cedula=''"+creditCard.getCustomer().getIdNum()+"'' ya tiene 2 Tarjetas de credito. no puede tener mas.");
			}
		}else{
			throw new ExcepcionNegocio("Ya existe la credit card");
		} 
		
	}
	
	
	
	
	
	/**
	 * Cuenta los productos de un determinado cliente
	 * @param cliente el cliente que recibe para contar los productos
	 * @return el tama�o de la lista
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int contarProductosCliente(Customer cliente){
		Query q = em.createNamedQuery(Product.ContarProductosCliente);
		q.setParameter(1, cliente);
		List<Product> lista = q.getResultList();
		return lista.size();
	}
	/**
	 * metodo para contar las terjetas de un cliente
	 * @param cliente que recibe para contar las terjetas de un cliente
	 * @return el tama�o de la lista
	 */
	public int contarTarjetasCliente(Customer cliente){
		Query q = em.createNamedQuery(CreditCard.TarjetasCliente);
		q.setParameter(1, cliente);
		List<CreditCard> lista = q.getResultList();
		return lista.size();
	}
	/**
	 * Metodo encargado de listar las tarjetas de un cliente
	 * @param cliente  lista de tarjetas del cliente solicitado
	 * @return lista de tarjetas
	 */
	public List<CreditCard> listaTarjetasCliente (Customer cliente){
		Query q = em.createNamedQuery(CreditCard.TarjetasCliente);
		q.setParameter(1, cliente);
		List<CreditCard> lista = q.getResultList();
		return lista;
	}
	
	/**
	 * Metodo para buscar una credit card.
	 * @param numero de la credit card
	 * @return la creditcard.
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public CreditCard buscarCreditCard(String num){
		return em.find(CreditCard.class, num);
	}
	

	
	public static String numeroAleatorio3(){
		int contador = 0;
		String numtotal = "";
		
	     while ( contador < 3 ) {
	    	 contador++;
	    	 int numero1 = ThreadLocalRandom.current().nextInt(0, 9 + 1);
	    	 String num = String.valueOf(numero1);
	    	 
	    	 numtotal += num ;
	    	
	     }
	    
		return numtotal;
		
	}
	
	public String numeroAleatorio16(){
		Long numero = ThreadLocalRandom.current().nextLong(100000000000000L, 999999999999999L );
		System.out.println(numero);
		String cadena = numero.toString();
		int ultimo = 1;
		int digito = 0;
		
		for(int i=0;i<cadena.length();i++){
			
			char letra = cadena.charAt(i);
			if(Character.isDigit(letra)){
				digito = Integer.parseInt(String.valueOf(cadena.charAt(i)));
				
				ultimo *= digito;
			}
		}
		
		String fin = ultimo + "";
		cadena = cadena + fin.charAt(fin.length()-1);
		
		return cadena;
		
		}
	
	public Date fechaExpedicion() {
		Calendar calendar = Calendar.getInstance();
		Date fecha = calendar.getTime();
		return fecha;
	}
	
	
	public Date fechaExpiracion() {
		Calendar calendar = Calendar.getInstance();
		Date fecha = calendar.getTime();
		fecha.setYear(fecha.getYear()+4);
		return fecha;
	}
	 
        public List<CreditCard> TarjetasCliente(Customer cliente){
		Query q = em.createNamedQuery(CreditCard.TarjetasCliente);
		q.setParameter(1, cliente);
		List<CreditCard> lista = q.getResultList();
		return lista;
	}
	
	public void actualizar(CreditCard c){
		em.merge(c);
	}
	
	public void avanceTarjetaCuenta(String tarjetaSeleccionada, String cuentaSeleccionada, Double cantidad, Double total) {
		
		Product protar = productoEJB.buscarProducto(tarjetaSeleccionada);
		CreditCard ta = (CreditCard) protar;
		if(ta != null){
		
			total = ta.getMonto()*0.30;
			if(cantidad <= total){
				ta.setMonto(ta.getMonto()-cantidad);			
				ta.setDeuda(ta.getDeuda()+cantidad);
				creditCardEJB.actualizar(ta);
				
				Product procuen = productoEJB.buscarProducto(cuentaSeleccionada);
				SavingAccount cue = (SavingAccount) procuen;
				if(cue != null){
					cue.setAmmount(cue.getAmmount()+cantidad);
					savingEJB.actualizarCuenta(cue);		
					
				}
			}else{
				throw new ExcepcionNegocio("Imposible hacer el avance, El cupo maximo es de: $"+total);
				
			}
				
		
		}

		
	}

}
