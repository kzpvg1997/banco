/**
 * 
 */
package servicios;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CuentaAsociados;
import co.edu.eam.ingesoft.pa.negocio.beans.CuentaAsociadosEJB;

/**
 * @author TOSHIBAP55W
 *
 *Para invocar este servicio se nesecita:
 *
 *1.La URL del servicio ===> http://ip:puerto/<root>/<raizRest>/<pathClase>/<pathMetodo>
 */

/*<pathClase>*/
@Path("/verificacion")
public class VerificacionRest {

	@EJB
	private CuentaAsociadosEJB asoEJB;
	
	@GET
	@Path("/buscar")
	@Produces(MediaType.APPLICATION_JSON)
	public CuentaAsociados buscarCuenta (@QueryParam("numero")String numero){
		CuentaAsociados cu = asoEJB.buscarCuentaAsociado(numero);
		cu.getCustomer().setProductos(null);
		
		return cu;
	}
	
	 
}
