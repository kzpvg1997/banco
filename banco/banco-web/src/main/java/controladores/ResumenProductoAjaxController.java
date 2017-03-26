package controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SavingAccount;
import co.edu.eam.ingesoft.pa.negocio.beans.CreditCardEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.SavingAccountEJB;

@Named("resumenProductoAjaxController")
@ViewScoped
public class ResumenProductoAjaxController implements Serializable {
	
	@EJB
	private CreditCardEJB creditCardEJB;
	
	@EJB
	private SavingAccountEJB savinEJB;
	
	@Inject
	private SessionController sesionCotroller;
	
	private List<SavingAccount> cuentas;
	
	private List<CreditCard> tarjetas;
	

	
	@PostConstruct
	public void inicializar(){
		
		cargarTablaTarjetas();
		
	}
	
	public void cargarTablaTarjetas(){
		
		Customer cli = sesionCotroller.getCliente();
		if(cli != null){
			cuentas = savinEJB.listarCuentasAhorroCliente(cli);
			tarjetas = creditCardEJB.listaTarjetasCliente(cli);
	
		}
	}
	

	/**
	 * @return the disponible
	 */

	/**
	 * @param disponible the disponible to set
	 */


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
	 * @return the sesionCotroller
	 */
	public SessionController getSesionCotroller() {
		return sesionCotroller;
	}

	/**
	 * @param sesionCotroller the sesionCotroller to set
	 */
	public void setSesionCotroller(SessionController sesionCotroller) {
		this.sesionCotroller = sesionCotroller;
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

	
	

}
