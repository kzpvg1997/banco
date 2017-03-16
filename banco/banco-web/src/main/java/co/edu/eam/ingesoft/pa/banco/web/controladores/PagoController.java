/**
 * 
 */
package co.edu.eam.ingesoft.pa.banco.web.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCardConsume;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.pa.negocio.beans.CreditCardConsumeEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CreditCardEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CustomerEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.PaymentEJB;

/**
 * @author GAR-T
 *
 */
@Named("pagoController")
@ViewScoped
public class PagoController implements Serializable{
	
	@EJB
	private CreditCardEJB creditCardEJB;
	
	@EJB
	private CustomerEJB customerEJB;
	
	@EJB
	private PaymentEJB paymentEJB;
	
	@EJB
	private CreditCardConsumeEJB consumoEJB;
	
	
	private String combTipoDocumento;
	
	private String numeroDocumento;
	
	private CreditCard combTarjeta;
	
	private String combConsumo;
	
	private String numeroTarjeta;
	
	private Double cuota;
	
	private Double monto;
	
	private Double capital;
	
	private Double interes;
	
	private Double avance;
	
	private CreditCard creditCardSeleccionada;
	
	private List<CreditCard> creditCard;
	
	private List<CreditCardConsume> creditConsumo;
	
	
	@PostConstruct
	public void inicializar(){
		
		
	}
	
	public void buscarConsumos(){
		
		CreditCard tarjeta = creditCardEJB.buscarCreditCard(numeroTarjeta);
		creditConsumo = consumoEJB.consumosTarjeta(tarjeta);

	}
	
	public void pagar(){
		System.out.println("holaaaaa");
	}
	
	/**
	 * @return the consumoEJB
	 */
	public CreditCardConsumeEJB getConsumoEJB() {
		return consumoEJB;
	}

	/**
	 * @param consumoEJB the consumoEJB to set
	 */
	public void setConsumoEJB(CreditCardConsumeEJB consumoEJB) {
		this.consumoEJB = consumoEJB;
	}

	/**
	 * @return the numeroTarjeta
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}




	/**
	 * @param numeroTarjeta the numeroTarjeta to set
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}




	/**
	 * @return the creditConsumo
	 */
	public List<CreditCardConsume> getCreditConsumo() {
		return creditConsumo;
	}




	/**
	 * @param creditConsumo the creditConsumo to set
	 */
	public void setCreditConsumo(List<CreditCardConsume> creditConsumo) {
		this.creditConsumo = creditConsumo;
	}




	/**
	 * @return the creditCard
	 */
	public List<CreditCard> getCreditCard() {
		return creditCard;
	}

	/**
	 * @param creditCard the creditCard to set
	 */
	public void setCreditCard(List<CreditCard> creditCard) {
		this.creditCard = creditCard;
	}

	/**
	 * @return the creditCardEJB
	 */
	public CreditCardEJB getCreditCardEJB() {
		return creditCardEJB;
	}

	/**
	 * @param creditCardEJB the creditCardEJB to set
	 */
	public void setCreditCardEJB(CreditCardEJB creditCardEJB) {
		this.creditCardEJB = creditCardEJB;
	}

	/**
	 * @return the customerEJB
	 */
	public CustomerEJB getCustomerEJB() {
		return customerEJB;
	}

	/**
	 * @param customerEJB the customerEJB to set
	 */
	public void setCustomerEJB(CustomerEJB customerEJB) {
		this.customerEJB = customerEJB;
	}

	/**
	 * @return the paymentEJB
	 */
	public PaymentEJB getPaymentEJB() {
		return paymentEJB;
	}

	/**
	 * @param paymentEJB the paymentEJB to set
	 */
	public void setPaymentEJB(PaymentEJB paymentEJB) {
		this.paymentEJB = paymentEJB;
	}

	/**
	 * @return the combTipoDocumento
	 */
	public String getCombTipoDocumento() {
		return combTipoDocumento;
	}

	/**
	 * @param combTipoDocumento the combTipoDocumento to set
	 */
	public void setCombTipoDocumento(String combTipoDocumento) {
		this.combTipoDocumento = combTipoDocumento;
	}

	/**
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param numeroDocumento the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * @return the combTarjeta
	 */
	public CreditCard getCombTarjeta() {
		return combTarjeta;
	}

	/**
	 * @param combTarjeta the combTarjeta to set
	 */
	public void setCombTarjeta(CreditCard combTarjeta) {
		this.combTarjeta = combTarjeta;
	}

	/**
	 * @return the combConsumo
	 */
	public String getCombConsumo() {
		return combConsumo;
	}

	/**
	 * @param combConsumo the combConsumo to set
	 */
	public void setCombConsumo(String combConsumo) {
		this.combConsumo = combConsumo;
	}

	/**
	 * @return the cuota
	 */
	public Double getCuota() {
		return cuota;
	}

	/**
	 * @param cuota the cuota to set
	 */
	public void setCuota(Double cuota) {
		this.cuota = cuota;
	}

	/**
	 * @return the monto
	 */
	public Double getMonto() {
		return monto;
	}

	/**
	 * @param monto the monto to set
	 */
	public void setMonto(Double monto) {
		this.monto = monto;
	}

	/**
	 * @return the capital
	 */
	public Double getCapital() {
		return capital;
	}

	/**
	 * @param capital the capital to set
	 */
	public void setCapital(Double capital) {
		this.capital = capital;
	}

	/**
	 * @return the interes
	 */
	public Double getInteres() {
		return interes;
	}

	/**
	 * @param interes the interes to set
	 */
	public void setInteres(Double interes) {
		this.interes = interes;
	}

	/**
	 * @return the avance
	 */
	public Double getAvance() {
		return avance;
	}

	/**
	 * @param avance the avance to set
	 */
	public void setAvance(Double avance) {
		this.avance = avance;
	}

	/**
	 * @return the creditCardSeleccionada
	 */
	public CreditCard getCreditCardSeleccionada() {
		return creditCardSeleccionada;
	}

	/**
	 * @param creditCardSeleccionada the creditCardSeleccionada to set
	 */
	public void setCreditCardSeleccionada(CreditCard creditCardSeleccionada) {
		this.creditCardSeleccionada = creditCardSeleccionada;
	}
	


}
