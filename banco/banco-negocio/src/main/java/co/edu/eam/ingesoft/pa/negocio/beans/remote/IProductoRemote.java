package co.edu.eam.ingesoft.pa.negocio.beans.remote;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;

public interface IProductoRemote {
	
	/*
	 * Cuenta los productos de un determinado cliente
	 */
	public int contarProductosCliente(Customer cliente);
}
