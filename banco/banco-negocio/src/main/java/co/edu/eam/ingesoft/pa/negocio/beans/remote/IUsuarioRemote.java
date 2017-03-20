package co.edu.eam.ingesoft.pa.negocio.beans.remote;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Usuario;

public interface IUsuarioRemote {
	
	public Usuario buscarUsuario(String pass);
}
