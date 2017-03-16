package co.edu.eam.ingesoft.pa.negocio.beans.remote;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;

/**
 * Interface remota para acceder a las operaciones del EJB.
 * @author GART
 *
 */
public interface ICustomerRemote {

	public void crearCustomer(Customer cus);
	
	public Customer buscarCustomer(String idType,String idNum);
	
	
}
