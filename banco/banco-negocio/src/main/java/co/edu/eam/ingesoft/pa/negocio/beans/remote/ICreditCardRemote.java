package co.edu.eam.ingesoft.pa.negocio.beans.remote;

import java.util.Date;
import java.util.List;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;

/**
 * Interface remota para acceder a las operaciones del EJB.
 * @author GART
 *
 */
public interface ICreditCardRemote {

	public void crearCreditCard(CreditCard creditCard);
	public CreditCard buscarCreditCard(String num);
	public String numeroAleatorio16();
	public String numeroAleatorio3();
	public Date fechaExpedicion();
	public Date fechaExpiracion();
	public List<CreditCard> listaTarjetasCliente (Customer cliente);
	
}
