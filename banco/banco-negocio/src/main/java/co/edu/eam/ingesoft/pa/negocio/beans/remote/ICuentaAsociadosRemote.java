/**
 * 
 */
package co.edu.eam.ingesoft.pa.negocio.beans.remote;

import java.util.List;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Banco;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CuentaAsociados;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;

/**
 * @author TOSHIBAP55W
 *
 */
public interface ICuentaAsociadosRemote {

	public List<Banco> listaBancos();
	
	public List<CuentaAsociados> listaCuentasCliente(Customer customer);
	
	public void agregarCuentaAsociados(CuentaAsociados cuenta);
	
	public CuentaAsociados buscarCuentaAsociado(String numero);
	
	public void borrarCuentaAsociado(CuentaAsociados cu);
}
