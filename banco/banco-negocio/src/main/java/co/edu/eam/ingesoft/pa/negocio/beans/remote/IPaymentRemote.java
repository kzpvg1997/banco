package co.edu.eam.ingesoft.pa.negocio.beans.remote;

import java.util.List;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCardConsume;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCardPaymentConsume;

public interface IPaymentRemote {
	/**
	 * Busca un pago por su id
	 * @param id
	 * @return
	 */
	public CreditCardPaymentConsume buscarPago(int id);
	
	/**
	 * Lista todos los pagos de un determinado consumo
	 * @param c
	 * @return
	 */
	public List<CreditCardPaymentConsume> listarPagosConsumo(CreditCardConsume c);
	/**
	 * pagar 
	 * @param tarjeta
	 * @param consumo
	 * @param avance
	 */
	public void pagar(CreditCard tarjeta, CreditCardConsume consumo,double avance);
	/**
	 * Accede a la constante iva
	 * @return
	 */
	public double getIva();
	
	/**
	 *  Calcula la cuota con respecto a un consumo 
	 */
	public int numeroCuota(CreditCardConsume consumo);
	
	/**
	 *  Calcula el capital de una cuota
	 */
	public double capitalCuota(CreditCardConsume consumo);
	
	/**
	 *  Calcula el interes de una cuota con respecto a un consumo 
	 */
	public double interesCuota(CreditCardConsume consumo);
	
	/** 
	 * Calcula el valor de la cuota a pagar 
	 */
	public double calcularValorCuota(CreditCardConsume consumo);
	/**
	 * Paga la cuota total de una lista de consumos
	 * @param consumo
	 */
	public void pagarTarjeta(CreditCard tarjeta);
	/**
	 * Paga un avance de los consumos de una tarjeta
	 * @param consumo
	 * @param restante
	 */
	public void avances( CreditCardConsume consumo, double restante);
	
}
