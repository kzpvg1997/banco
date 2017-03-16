package co.edu.eam.ingesoft.standalone.gui.controlador;

import java.util.List;

import javax.naming.NamingException;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Franchise;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ICreditCardRemote;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.IFranchiseRemote;
import co.edu.eam.ingesoft.standalone.gui.util.ServiceLocator;

/**
 * Controlador de la ventana gestion customer
 * 
 * @author 
 *
 */
public class ControladorVentanaGestionTarjetas {

	/**
	 * Inteface remota del EJB
	 */
	private IFranchiseRemote franquiciaRemote;
	private ICreditCardRemote creditCardRemoto;

	/**
	 * constructor
	 * 
	 * @throws NamingException
	 */
	public ControladorVentanaGestionTarjetas() throws NamingException {
		creditCardRemoto = (ICreditCardRemote) ServiceLocator.buscarEJB("CreditCardEJB",
				ICreditCardRemote.class.getCanonicalName());
		franquiciaRemote = (IFranchiseRemote) ServiceLocator.buscarEJB("FranchiseEJB",
				IFranchiseRemote.class.getCanonicalName());
	}
	

	/**
	 * metodo para buscar customer.
	 * 
	 * @param identificacion
	 *            y tipo de identificacion
	 * @return el customer.
	 */
	public CreditCard buscarCreditCard(String number) {
		return creditCardRemoto.buscarCreditCard(number);
	}

	/**
	 * metodo para crear customer.
	 * 
	 * @param cus,
	 *            customer
	 */
	public void crearCreditCard(CreditCard credit) {
		creditCardRemoto.crearCreditCard(credit);
	}
	
	public  String numeroAleatorio16(){
		return creditCardRemoto.numeroAleatorio16();
		
	}
	
	public List<Franchise> listarFranquicias(){
		return franquiciaRemote.listarFranquicias();
		
	}
	
	



	
	

}
