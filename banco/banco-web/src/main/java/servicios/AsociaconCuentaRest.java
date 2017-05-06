/**
 * 
 */
package servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Bank;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CuentaAsociados;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SavingAccount;
import co.edu.eam.ingesoft.pa.negocio.DTO.CuentaAsociadaDTO;
import co.edu.eam.ingesoft.pa.negocio.beans.BancoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CuentaAsociadosEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CustomerEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.MensajeEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.SavingAccountEJB;
import controladores.SessionController;

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
	private CustomerEJB clienteEJB;
	
	@EJB 
	private SavingAccountEJB savaccEJB;
	
	@EJB
	private CuentaAsociadosEJB asocEJB;
	
	@EJB
	private BancoEJB bankEJB;

	@Path("/listarBancos")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public RespuestaDTO listarBancos() {

		List<Bank> lista = bankEJB.listaBancos();

		if (lista.isEmpty()) {
			return new RespuestaDTO("No hay registros", 1, null);
		} else {
			return new RespuestaDTO("Se encontraron registros", 0, lista);
		}

	}
	
	@Path("/verificar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@POST
	public String verificar(@FormParam("numeroCuenta")String numeroCuenta,@FormParam("id")String cedula,
			@FormParam("tipoId")String tipoId){
		
		return savaccEJB.verificarCuentaAhorros(numeroCuenta, cedula,tipoId);
	}
	
	@POST
    @Path("/transferir")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
    public String transferir(@QueryParam("numeroCuenta") String numeroCuenta,
    		@QueryParam("cuentaAsociada") String cuentaAsociada,@QueryParam("cantidad") double cantidad) {
       
		SavingAccount cuenta = savaccEJB.buscarCuentaAhorro(numeroCuenta);
        CuentaAsociados asociada = asocEJB.buscarCuentaAsociado(cuentaAsociada);
        if (cuenta != null) {
        	if(asociada!=null){

        	savaccEJB.transferirCuentaAsociados(cantidad, cuenta, asociada);
            return "Exito";
            
        	}
        }
        return "Error";
        
	}

    @POST
    @Path("/recibirdinero")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String recibirDinero(@FormParam("numerocuenta") String numeroCuenta,
            @FormParam("cantidad") double cantidad) {
        SavingAccount cuenta = savaccEJB.buscarCuentaAhorro(numeroCuenta);
        if (cuenta != null) {
        	savaccEJB.consignar(cantidad,cuenta);
            return "EXITO";
        }
        return "ERROR";

    }
    
    @POST
	@Path("/asociarCuenta")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO asociarCuenta(CuentaAsociadaDTO cuentaAsociada) {

		if (cuentaAsociada != null) {

			CuentaAsociados ca = new CuentaAsociados();
			ca.setNombreAsociado(cuentaAsociada.getNombreAsociado());
			ca.setTipoID(cuentaAsociada.getTipoIdAsociado());
			ca.setIdAsociado(cuentaAsociada.getNumeroIdAsociado());
			Bank b = bankEJB.buscarBanco(cuentaAsociada.getBank());
			ca.setBanco(b);
			Customer cus = clienteEJB.buscarCustomer(cuentaAsociada.getTipoIdCliente(),cuentaAsociada.getIdCliente() );
			ca.setCustomer(cus);
			ca.setNumeroCuenta(cuentaAsociada.getNumeroCuenta());
			ca.setNombreAsociacion(cuentaAsociada.getNombreCuenta());

			asocEJB.agregarCuentaAsociados(ca);
			return new RespuestaDTO("Se hizo la peticion de asociacion correctamente", 0, null);
		}

		return new RespuestaDTO("Hubo un error al hacer la peticion de asociacion", 1, null);
	}
    
    @GET
	@Path("/listarCuentasAsociadas")
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO listarCuentasAsociadas(@QueryParam("id") String cedula, @QueryParam("tipoId") String tipoId) {

		Customer c = clienteEJB.buscarCustomer(tipoId, cedula);

		List<CuentaAsociados> lista = asocEJB.listaCuentasCliente(c);

		if (lista.isEmpty()) {
			return new RespuestaDTO("No hay registros", 1, null);
		} else {
			return new RespuestaDTO("Se encontraron registros", 0, lista);
		}
	}
}
