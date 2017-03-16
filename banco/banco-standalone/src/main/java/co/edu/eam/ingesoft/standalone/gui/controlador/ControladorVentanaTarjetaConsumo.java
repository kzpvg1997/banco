/**
 * 
 */
package co.edu.eam.ingesoft.standalone.gui.controlador;

import java.util.List;

import javax.naming.NamingException;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCardConsume;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ICreditCardConsumeRemote;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ICreditCardRemote;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ICustomerRemote;
import co.edu.eam.ingesoft.standalone.gui.util.ServiceLocator;

/**
 * @author TOSHIBAP55W
 *
 */

public class ControladorVentanaTarjetaConsumo {

	/**
	 * Inteface remota del EJB
	 */
	private ICreditCardRemote creditCardRemote;
	
	private ICreditCardConsumeRemote cardConsumeRemote;
	
	/**
	 * constructor
	 * @throws NamingException
	 */
	public ControladorVentanaTarjetaConsumo() throws NamingException {
		creditCardRemote=(ICreditCardRemote) ServiceLocator.buscarEJB("CreditCardEJB",
				ICreditCardRemote.class.getCanonicalName());
		
		cardConsumeRemote=(ICreditCardConsumeRemote) ServiceLocator.buscarEJB("CreditCardConsumeEJB",
				ICreditCardConsumeRemote.class.getCanonicalName());		
	}
	
	public List<CreditCard> listaTarjetasCliente (Customer cliente){
		return creditCardRemote.listaTarjetasCliente(cliente);
	}
	
	public void crearConsumo(CreditCardConsume consumo){
		cardConsumeRemote.crearConsumo(consumo);
		}
	
	public List<CreditCardConsume> consumosTarjeta(CreditCard tarjeta){
		return cardConsumeRemote.consumosTarjeta(tarjeta);
	}
	public double sumaConsumosTarjetaCuotas(CreditCard tarjeta){
		return cardConsumeRemote.sumaConsumosTarjetaCuotas(tarjeta);
	}
	public List<CreditCardConsume> consumosTarjetaNoPagos(CreditCard tarjeta){
		return cardConsumeRemote.consumosTarjetaNoPagos(tarjeta);
	}
}
