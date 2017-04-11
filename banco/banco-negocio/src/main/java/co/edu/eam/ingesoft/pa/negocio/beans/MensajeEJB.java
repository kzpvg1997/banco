package co.edu.eam.ingesoft.pa.negocio.beans;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.xml.ws.BindingProvider;

import co.edu.eam.ingesoft.pa.negocio.beans.remote.IMensajeRemote;
import co.edu.eam.pa.clientews.Notificaciones;
import co.edu.eam.pa.clientews.NotificacionesService;
import co.edu.eam.pa.clientews.RespuestaNotificacion;
import co.edu.eam.pa.clientews.Sms;
import co.edu.eam.pa.clientews.Mail;


@LocalBean
@Stateless
@Remote(IMensajeRemote.class)
public class MensajeEJB {
	
	public void Sms(String txt, String tel){
		NotificacionesService cliente=new NotificacionesService();
		Notificaciones servicio=cliente.getNotificacionesPort();
		
		String endpointURL = "http://104.197.238.134:8080/notificaciones/notificacionesService?";
		BindingProvider bp = (BindingProvider)servicio;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);
		
		Sms mensaje = new Sms();
		mensaje.setTexto(txt);
		mensaje.setTo(tel);
		
		RespuestaNotificacion resp = servicio.enviarSMS(mensaje);
		System.out.println(resp.getMensaje());
	}
	
	public void Mail(String msj, String asunto, String email){
		NotificacionesService cliente=new NotificacionesService();
		Notificaciones servicio=cliente.getNotificacionesPort();
		
		String endpointURL = "http://104.197.238.134:8080/notificaciones/notificacionesService?";
		BindingProvider bp = (BindingProvider)servicio;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);
		
		Mail correo=new Mail();
		correo.setBody(msj);
		correo.setFrom("a");
		correo.setTo(email);
		correo.setSubject(asunto);
		
		RespuestaNotificacion resp = servicio.enviarMail(correo);
		System.out.println(resp.getMensaje());
	}

}
