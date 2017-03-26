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

@Named("transaccionAjaxController")
@ViewScoped
public class TransaccionAjaxController implements Serializable{
	
	@Inject
	private SessionController sesionCotroller;
	
	@EJB
	private CreditCardEJB creditCardEJB;
	
	@EJB
	private SavingAccountEJB savinEJB;
	
	private List<CreditCard> tarjetas;
	
	private List<SavingAccount> cuentas;
	
	private Double monto;
	
	
	@PostConstruct
	public void inicializar(){
		listarCombos();
		
	}
	
	public void listarCombos(){
		Customer cli = sesionCotroller.getCliente();
		if(cli != null){
			tarjetas = creditCardEJB.listaTarjetasCliente(cli);
			cuentas = savinEJB.listarCuentasAhorroCliente(cli);
		}
		
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
	
	

}
