/**
 * 
 */
package servicios;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

/**
 * @author TOSHIBAP55W
 *
 */
@Provider
public class ManejadorExcepciones implements ExceptionMapper<Throwable>{

	public Response toResponse(Throwable exc) {
		
		if(exc instanceof ExcepcionNegocio){
			RespuestaDTO res  = new RespuestaDTO(exc.getMessage(), -200, null);
			return Response.status(500).entity(res).build();
		}else{
			exc.printStackTrace();
			RespuestaDTO res = new RespuestaDTO("Error General", -100, null);
			return Response.status(500).entity(res).build();
		}
		
	}

	
}
