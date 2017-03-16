package co.edu.eam.ingesoft.standalone.gui.controlador;

import java.util.List;

import javax.naming.NamingException;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCardConsume;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCardPaymentConsume;
import co.edu.eam.ingesoft.pa.negocio.beans.PaymentEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ICustomerRemote;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.IPaymentRemote;
import co.edu.eam.ingesoft.standalone.gui.util.ServiceLocator;

public class ControladorPayment {
	
	private IPaymentRemote paymentRemote;

	public ControladorPayment() throws NamingException {
		paymentRemote=(IPaymentRemote) ServiceLocator.buscarEJB("PaymentEJB",
				IPaymentRemote.class.getCanonicalName());
	}
	
	public List<CreditCardPaymentConsume> listarPagosConsumo(CreditCardConsume c){
		return paymentRemote.listarPagosConsumo(c);
	}
	/**
	 * Con este metodo vamos a pagar 
	 */
	public void pagar(CreditCard tarjeta, CreditCardConsume consumo,double avance){
		paymentRemote.pagar(tarjeta,consumo,avance);
	}
	
	/**
	 * Accede a la constante iva
	 * @return
	 */
	public double getIva(){
		return paymentRemote.getIva();
	}
	
	/**
	 *  Calcula la cuota con respecto a un consumo 
	 */
	public int numeroCuota(CreditCardConsume consumo){
		return paymentRemote.numeroCuota(consumo);
	}
	
	/**
	 *  Calcula el capital de una cuota
	 */
	public double capitalCuota(CreditCardConsume consumo){
		return paymentRemote.capitalCuota(consumo);
	}
	
	/**
	 *  Calcula el interes de una cuota con respecto a un consumo 
	 */
	public double interesCuota(CreditCardConsume consumo){
		return paymentRemote.interesCuota(consumo);
	}
	
	/** 
	 * Calcula el valor de la cuota a pagar 
	 */
	public double calcularValorCuota(CreditCardConsume consumo){
		return paymentRemote.calcularValorCuota(consumo);
	}
	/**
	 * Paga la cuota de consumos totales de una tarjeta
	 * @param tarjeta a pagar
	 * @param valorTotal
	 */
	public void pagarTarjeta(CreditCard tarjeta)  {
		
		paymentRemote.pagarTarjeta(tarjeta);
	}

	public CreditCardPaymentConsume buscarPago(int id){
		return paymentRemote.buscarPago(id);
	}
	
	public void avances( CreditCardConsume consumo, double restante){
		paymentRemote.avances(consumo, restante);
	}
}
