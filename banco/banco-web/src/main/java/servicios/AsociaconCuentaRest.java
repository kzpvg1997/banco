/**
 * 
 */
package servicios;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CuentaAsociados;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SavingAccount;
import co.edu.eam.ingesoft.pa.negocio.beans.CuentaAsociadosEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CustomerEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.SavingAccountEJB;

/**
 * @author TOSHIBAP55W
 *
 *Para invocar este servicio se nesecita:
 *
 *1.La URL del servicio ===> http://ip:puerto/<root>/<raizRest>/<pathClase>/<pathMetodo>
 */

/*<pathClase>*/
@Path("/asociacionCuenta")
public class AsociaconCuentaRest {

	@EJB
	private CustomerEJB clienteEJB;;
	
	@EJB 
	private SavingAccountEJB cueEJB;
	
	@Path("/verificar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@POST
	public String verificar(@FormParam("numeroCuenta")String numeroCuenta,@FormParam("id")String cedula,
			@FormParam("tipoId")String tipoId){
		
		return cueEJB.verificarCuentaAhorros(numeroCuenta, cedula,tipoId);
	}
}
