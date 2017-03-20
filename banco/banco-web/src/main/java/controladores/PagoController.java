/**
 * 
 */
package controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCardConsume;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Product;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SavingAccount;
import co.edu.eam.ingesoft.pa.negocio.beans.CreditCardConsumeEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CreditCardEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CustomerEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.PaymentEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.ProductEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.SavingAccountEJB;

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
	private CreditCardConsumeEJB consumoEJB;
	
	@EJB
	private ProductEJB productoEJB;
	
	@EJB
	private SavingAccountEJB savinEJB;
	
	@Inject
	private SessionController sesionCotroller;
	
	@NotNull(message="Seleccione una opcion")
	private String combTipoDocumento;
	
	@Pattern(regexp="[0-9]*",message="Ingrese solo numeros")
	@Length(min=4,max=10,message="Lonitud entre 4 y 10")
	private String numeroDocumento;
	
	private String tarjetaSeleccionada;
	
	private String cuentaSeleccionada;
	
	private List<SavingAccount> cuentas;
	
	private List<CreditCard> tarjetas;
	
	private List<CreditCardConsume> consumos;
	
	private List<Product> productos;
	
	private CreditCardConsume Consume;
	
	private CreditCard tarSeleccionada;
	
	
	
	
	@PostConstruct
	public void inicializar(){
		buscarConsumos();
		
	}
	
	public void buscarConsumos(){
		
		//Customer cli = customerEJB.buscarCustomer(combTipoDocumento,numeroDocumento);
		Customer cli = sesionCotroller.getCliente();
		if(cli != null){
			tarjetas = creditCardEJB.listaTarjetasCliente(cli);
			cuentas = savinEJB.listarCuentasAhorroCliente(cli);
		}

	}	
	
	public void cargarTablaCombo(){
		Product prot = productoEJB.buscarProducto(tarjetaSeleccionada);
		System.out.println(prot.getNumber());
		CreditCard tar = (CreditCard) prot;
		consumos = consumoEJB.consumosTarjetaNoPagos(tar);
		
	}

	
	public void pagar(){
		System.out.println("holaaaaa");
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
	 * @return the productoEJB
	 */
	public ProductEJB getProductoEJB() {
		return productoEJB;
	}

	/**
	 * @param productoEJB the productoEJB to set
	 */
	public void setProductoEJB(ProductEJB productoEJB) {
		this.productoEJB = productoEJB;
	}

	/**
	 * @return the savinEJB
	 */
	public SavingAccountEJB getSavinEJB() {
		return savinEJB;
	}

	/**
	 * @param savinEJB the savinEJB to set
	 */
	public void setSavinEJB(SavingAccountEJB savinEJB) {
		this.savinEJB = savinEJB;
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
	 * @return the tarjetaSeleccionada
	 */
	public String getTarjetaSeleccionada() {
		return tarjetaSeleccionada;
	}

	/**
	 * @param tarjetaSeleccionada the tarjetaSeleccionada to set
	 */
	public void setTarjetaSeleccionada(String tarjetaSeleccionada) {
		this.tarjetaSeleccionada = tarjetaSeleccionada;
	}

	/**
	 * @return the cuentaSeleccionada
	 */
	public String getCuentaSeleccionada() {
		return cuentaSeleccionada;
	}

	/**
	 * @param cuentaSeleccionada the cuentaSeleccionada to set
	 */
	public void setCuentaSeleccionada(String cuentaSeleccionada) {
		this.cuentaSeleccionada = cuentaSeleccionada;
	}

	/**
	 * @return the cuentas
	 */
	public List<SavingAccount> getCuentas() {
		return cuentas;
	}

	/**
	 * @param cuentas the cuentas to set
	 */
	public void setCuentas(List<SavingAccount> cuentas) {
		this.cuentas = cuentas;
	}

	/**
	 * @return the tarjetas
	 */
	public List<CreditCard> getTarjetas() {
		return tarjetas;
	}

	/**
	 * @param tarjetas the tarjetas to set
	 */
	public void setTarjetas(List<CreditCard> tarjetas) {
		this.tarjetas = tarjetas;
	}

	/**
	 * @return the consumos
	 */
	public List<CreditCardConsume> getConsumos() {
		return consumos;
	}

	/**
	 * @param consumos the consumos to set
	 */
	public void setConsumos(List<CreditCardConsume> consumos) {
		this.consumos = consumos;
	}

	/**
	 * @return the productos
	 */
	public List<Product> getProductos() {
		return productos;
	}

	/**
	 * @param productos the productos to set
	 */
	public void setProductos(List<Product> productos) {
		this.productos = productos;
	}

	/**
	 * @return the consume
	 */
	public CreditCardConsume getConsume() {
		return Consume;
	}

	/**
	 * @param consume the consume to set
	 */
	public void setConsume(CreditCardConsume consume) {
		Consume = consume;
	}

	/**
	 * @return the tarSeleccionada
	 */
	public CreditCard getTarSeleccionada() {
		return tarSeleccionada;
	}

	/**
	 * @param tarSeleccionada the tarSeleccionada to set
	 */
	public void setTarSeleccionada(CreditCard tarSeleccionada) {
		this.tarSeleccionada = tarSeleccionada;
	}


}
