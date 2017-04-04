/**
 * 
 */
package co.edu.eam.ingesoft.pa.negocio.beans;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCardConsume;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Product;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ICreditCardConsumeRemote;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import java.util.ArrayList;

/**
 * @author TOSHIBAP55W
 *
 */
@LocalBean
@Stateless
@Remote(ICreditCardConsumeRemote.class)
public class CreditCardConsumeEJB {

	
	@PersistenceContext
	private EntityManager em;
	
	@EJB
	private CreditCardEJB cardEJB;
	
	/**
	 * Metodo para obtener la fecha actual
	 * @return la fecha actual
	 */
	public Date fechaActual(){
		return cardEJB.fechaExpedicion();
	}
	/**
	 * 
	 * @param consumo
	 * @throws Exception
	 */
	public void crearConsumo(CreditCardConsume consumo)throws Exception{
		
		consumo.setDateConsume(fechaActual());
		if(consumo.getAmmount()>consumo.getCreditCard().getMonto()){
			throw new ExcepcionNegocio("Esta compra excede el monto de la terjeta de credito");
		}else{
			double valorRestante = consumo.getAmmount();
			
//			DecimalFormat formateador = new DecimalFormat("###0.##"); 
			double interes = consumo.getAmmount()*0.036;
//			interes = formateador.parse(formateador.format(interes)).doubleValue();
			
//			DecimalFormat formateador2 = new DecimalFormat("###0");
			double valorCuota = valorRestante/consumo.getNumberShares();
//			valorCuota = formateador2.parse(formateador2.format(valorCuota)).doubleValue();
//			Math.round(valorCuota);
			
			consumo.setInterest(interes);
			consumo.setRemainingAmmount(valorRestante);
			consumo.setRemaningShares(consumo.getNumberShares());
			consumo.setValorCuota(valorCuota);
			
			em.persist(consumo);
			consumo.getCreditCard().setMonto(consumo.getCreditCard().getMonto()-consumo.getAmmount());
			consumo.getCreditCard().setDeuda(consumo.getCreditCard().getDeuda()+consumo.getAmmount());
			em.merge(consumo.getCreditCard());
		}
	}
	/**
	 * Metodo para listar los conumos de una tarjeta
	 * @param tarjeta la tarjeta que se le deseasacar la lista
	 * @return la lista de consumos
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<CreditCardConsume> consumosTarjeta(CreditCard tarjeta){
		Query q = em.createNamedQuery(CreditCardConsume.CONSUMOS_TARJETAS);
		q.setParameter(1, tarjeta);
		List<CreditCardConsume> lista = q.getResultList();
		return lista;
	}
        
        	/**
	 * Lista todos los consumos de una determinada tarjeta en estado no pagado
	 * @param c
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<CreditCardConsume> consumosTarjetaNoPagos(CreditCard c){
		List<CreditCardConsume> lista = consumosTarjeta(c);
		List<CreditCardConsume> pagos = new ArrayList<CreditCardConsume>();
		for (CreditCardConsume e : lista) {
			if(!e.isPayed()){ // preguntar
				pagos.add(e);
			}
		}
		return pagos;
	}
	
	/**
	 * Calcular la suma de todos los consumos que debe el cliente con respecto a una tarjeta de credito
	 */
	public double consumosTotalTarjetaCredito(CreditCard c){
		double total = 0.0;
		List<CreditCardConsume> lista = consumosTarjetaNoPagos(c);
		for (CreditCardConsume e : lista) {
			total = total + e.getRemainingAmmount();
		}
		return total;
	}
	/**
	 * Actualiza un consumo
	 */
	public void actualizarConsumo(CreditCardConsume consumo){
		em.merge(consumo); // Actualizar
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public double sumaConsumosTarjetaCuotas(CreditCard tarjeta){
		double valor = 0.0;
		List<CreditCardConsume> lista= consumosTarjetaNoPagos(tarjeta);
		for (CreditCardConsume cc : lista) {
			
				valor += cc.getValorCuota();
			}
		/**
		 * Metodo para reducir la cantidad de decimales de un double
		 */
		//DecimalFormat formateador = new DecimalFormat("###0.##"); 
		double interes = valor*0.036;
			//interes = formateador.parse(formateador.format(interes)).doubleValue();
		
		
		return valor+interes;
	}
	
	
}
