/**
 * 
 */
package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Usuario;

/**
 * @author TOSHIBAP55W
 *
 */
public class SeguridadEJB {

	@PersistenceContext
	private EntityManager em;

//	/**
//	 * 
//	 * M�todo que lista los privilegios de un usuario<br>
//	 * 
//	 * Camilo Andres Ferrer Bustos<br>
//	 * 
//	 * Fecha: 4/08/2016
//	 * 
//	 * @version 1.0
//	 * @param usuario
//	 * @return
//	 *
//	 */
//	public List<Privilegio> listarPrivilegios(Usuario usuario) {
//		return em.createNamedQuery("Privilegio.listarXRol").
//				setParameter(1, usuario.getRol()).
//				getResultList();
//	}

	/**
	 * 
	 * M�todo que busca un usuario por nombre de usuario <br>
	 * 
	 * @author Camilo Andres Ferrer Bustos<br>
	 * 
	 *         Fecha: 4/08/2016
	 * @version 1.0
	 * @param rol
	 * @return
	 *
	 */
	public Usuario buscarUsuario(String user) {
		List<Usuario>us= em.createNamedQuery("Usuario.buscarXUser").setParameter(1, user).getResultList();
		if(us.isEmpty()){
			return null;
		}else{
			return us.get(0);
		}
	}

}
