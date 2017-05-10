/**
 * 
 */
package servicios;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CuentaAsociados;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SavingAccount;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SegundaClave;
import co.edu.eam.ingesoft.pa.negocio.DTO.CuentaAsociadaDTO;
import co.edu.eam.ingesoft.pa.negocio.DTO.SavingAccountDTO;
import co.edu.eam.ingesoft.pa.negocio.DTO.TransferirDTO;
import co.edu.eam.ingesoft.pa.negocio.beans.BancoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CuentaAsociadosEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CustomerEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.MensajeEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.SavingAccountEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.SegundaClaveEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.ServiciosServidor;

/**
 * @author TOSHIBAP55W
 *
 */
@Path("/transferir")
public class TransferirRest {

	
	@EJB
	private CuentaAsociadosEJB cuentaAsociadaEJB;

	@EJB
	private SavingAccountEJB savAccountEJB;
	
	@EJB
	private MensajeEJB mensajeEJB;
	
	@EJB
	private ServiciosServidor serviciosEJB;

	/**
	 * EJB de la segunda clave
	 */
	@EJB
	private SegundaClaveEJB segundaClaveEJB;

	/**
	 * EJB de la segunda clave
	 */
	@EJB
	private CustomerEJB customerEJB;


	/**
	 * EJB del banco
	 */
	@EJB
	private BancoEJB bankEJB;
	
	@GET
	@Path("/listarCuentasAsociadasVerificadas")
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO listarCuentasVerificadas(@QueryParam("id") String cedula, @QueryParam("tipoId") String tipoId) {

		Customer c = customerEJB.buscarCustomer(tipoId, cedula);

		List<CuentaAsociados> lista = cuentaAsociadaEJB.listarCuentasVerificadas(c);

		if (lista.isEmpty()) {

			return new RespuestaDTO("No hay registros", 1, null);
		} else {
			List<CuentaAsociadaDTO> listaDto = new ArrayList<CuentaAsociadaDTO>();
			for (CuentaAsociados cuentas : lista) {
				CuentaAsociadaDTO dto = new CuentaAsociadaDTO();

				dto.setNombreAsociado(cuentas.getNombreAsociado());
				dto.setTipoIdAsociado(cuentas.getTipoID());
				dto.setNumeroIdAsociado(cuentas.getIdAsociado());
				dto.setBank(cuentas.getBanco().getId());
				dto.setTipoIdCliente(cuentas.getCustomer().getIdType());
				dto.setNumeroIdAsociado(cuentas.getCustomer().getIdNum());
				dto.setNumeroCuenta(cuentas.getNumeroCuenta());
				dto.setNombreCuenta(cuentas.getNombreAsociacion());
				dto.setEstado(cuentas.getEstado());
				listaDto.add(dto);
			}
			return new RespuestaDTO("Se encontraron registros", 0, listaDto);
		}
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/generarEnviarCodigo")
	@GET
	public RespuestaDTO generarEnviarCodigo(@QueryParam("id") String cedula, @QueryParam("tipoId") String tipoId) {

		Customer cus = customerEJB.buscarCustomer(tipoId, cedula);
		String claveGenerada = segundaClaveEJB.numeroAleatorio6();
		SegundaClave sc = new SegundaClave();
		sc.setClave(claveGenerada);
		segundaClaveEJB.crearSegundaClave(sc);;
		String msj = "Su codigo de verificacion es: " + claveGenerada + "\n \nSu codigo expirara en 90 minutos";
		mensajeEJB.Sms(msj, cus.getTelefono());

		return new RespuestaDTO("Se encontraron registros",0, claveGenerada);
	}
	
	@GET
	@Path("/listarCuentasAhorroCliente")
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO listarCuentasCliente(@QueryParam("id") String cedula, @QueryParam("tipoId") String tipoId) {

		Customer c = customerEJB.buscarCustomer(tipoId, cedula);

		List<SavingAccount> lista = savAccountEJB.listarCuentasAhorroCliente(c);

		if (lista.isEmpty()) {
			return new RespuestaDTO("No hay registros", 1, null);
		} else {
			List<SavingAccountDTO> listaDto = new ArrayList<SavingAccountDTO>();
			for (SavingAccount cuentas : lista) {
				SavingAccountDTO dto = new SavingAccountDTO();

				dto.setNumber(cuentas.getNumber());
				dto.setAmmount(cuentas.getAmmount());
				dto.setSavingInterest(cuentas.getSavingInterest());
				listaDto.add(dto);
			}

			return new RespuestaDTO("Se encontraron registros", 0, listaDto);
		}
	}
	
	@POST
	@Path("/transferir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO transferir(TransferirDTO dto) {
		
		CuentaAsociados asociada = cuentaAsociadaEJB.buscarCuentaAsociado(dto.getCuentaAsociada());
		System.out.println(dto.getCuentaAsociada() + "XXXXXXXXXXXXXXX");
		System.out.println(asociada.getBanco().getId() + "NMNMNMNMNMNMNMNMN");

		String msj ="((((((Error al transferir))))))";
		
		if (asociada != null) {

			 serviciosEJB.transferirMonto(dto.getCuentaAhorros(), asociada.getBanco().getId(), 
					dto.getCuentaAsociada(), dto.getCantidad());
			 msj="((((((TRANSFERENCIA EXITOSA)))))))";
			 
			System.out.println(msj + "{}{}{}{}{}{}{}{}{}{}{}{||||||||{}{}{}{}{}{}{}");
		
			return new RespuestaDTO("Se completo la transferencia exitosamente!", 0, true);
		}

		return new RespuestaDTO("Ocurrio un error al completar la transferencia", 1, false);
	
	}
	
}
