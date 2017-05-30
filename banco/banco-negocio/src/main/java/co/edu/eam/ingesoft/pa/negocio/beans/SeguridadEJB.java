package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Usuario;


@Stateless
@LocalBean
public class SeguridadEJB {
	
	@PersistenceContext
	private EntityManager em;
	
	public Usuario buscar(String us){
		
		List<Usuario> use = em.createNamedQuery(Usuario.USUARIO).setParameter(1, us).getResultList();
		
		if(use.isEmpty()){
			return null;
		}else{
			return use.get(0);
		}
	}
	
	
	public Usuario buscarUs (String nombreUs){
		return em.find(Usuario.class, nombreUs);		
	}
	

}
