package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Product;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.IUsuarioRemote;

@LocalBean
@Stateless
@Remote(IUsuarioRemote.class)
public class UsuarioEJB {
	
	@PersistenceContext
	private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Usuario buscarUsuario(String pass){
		return em.find(Usuario.class, pass);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Usuario> listarUsuarios(Usuario u){
		Query q = em.createNamedQuery(Usuario.USUARIO);
		q.setParameter(1, u);
		List<Usuario> lista = q.getResultList();
		return lista;
	}
	

}
