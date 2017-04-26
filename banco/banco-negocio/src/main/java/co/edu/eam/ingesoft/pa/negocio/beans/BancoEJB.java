/**
 * 
 */
package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.ws.BindingProvider;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Bank;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.IBancoRemote;
import co.edu.eam.pa.interbancariows.Banco;
import co.edu.eam.pa.interbancariows.InterbancarioWS;
import co.edu.eam.pa.interbancariows.InterbancarioWS_Service;

/**
 * @author TOSHIBAP55W
 *
 */
@LocalBean
@Stateless
@Remote(IBancoRemote.class)
public class BancoEJB {

	@PersistenceContext
	private EntityManager em;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Bank buscarBanco(String id) {
		return em.find(Bank.class, id);
	}

	public void crearBanco(Bank bank) {

		em.persist(bank);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Bank> listaBancos() {

		Query q = em.createNamedQuery(Bank.LISTA_BANCOS);
		List<Bank> banks = q.getResultList();
		return banks;

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Bank> listarBancosRemotos() {

		InterbancarioWS_Service cliente = new InterbancarioWS_Service();
		InterbancarioWS servicio = cliente.getInterbancarioWSPort();

		String endpoint = "http://104.155.128.249:8080/interbancario/InterbancarioWS/InterbancarioWS";

		BindingProvider bp = (BindingProvider) servicio;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);

		ArrayList<Banco> resp = (ArrayList<Banco>) servicio.listarBancos();

		List<Bank> bancos = new ArrayList<Bank>();

		for (Banco banco : resp) {

			Bank b = new Bank(banco.getCodigo(), banco.getNombre());
			bancos.add(b);
			Bank buscado = em.find(Bank.class, b.getId());

			if (buscado == null) {
				
				crearBanco(b);

			}

		}
		//Listar banco y vverificar si no esta
//		for (Bank bank : bancos) {
//			
//		}
		return bancos;
	}
}
