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

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CustomerPK;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Product;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SavingAccount;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.IProductoRemote;

@LocalBean
@Stateless
@Remote(IProductoRemote.class)
public class ProductEJB {
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Cuenta los productos de un determinado cliente
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int contarProductosCliente(Customer cliente){
		Query q = em.createNamedQuery(Product.ContarProductosCliente);
		q.setParameter(1, cliente);
		List<Product> lista = q.getResultList();
		return lista.size();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Product> listarProductosCliente(Customer cliente){
		Query q = em.createNamedQuery(Product.ContarProductosCliente);
		q.setParameter(1, cliente);
		List<Product> lista = q.getResultList();
		return lista;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Product buscarProducto(String num){
		return em.find(Product.class, num);
	}

		
}
