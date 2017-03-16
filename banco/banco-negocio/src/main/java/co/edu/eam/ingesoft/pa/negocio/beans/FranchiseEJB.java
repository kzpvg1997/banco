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

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Franchise;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.IFranchiseRemote;

@LocalBean
@Stateless
@Remote(IFranchiseRemote.class)
public class FranchiseEJB {
	
	@PersistenceContext
	private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Franchise> listarFranquicias(){
		
		Query q = em.createNamedQuery(Franchise.FRANQUICIAS);
		List<Franchise> franquicias = q.getResultList();
		return franquicias;
		
	}
	
	

}
