package controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.JOptionPane;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Product;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SavingAccount;
import co.edu.eam.ingesoft.pa.negocio.beans.CreditCardEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.ProductEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.SavingAccountEJB;

@Named("avanceAjax")
@ViewScoped
public class AvanceAjaxController implements Serializable {

	@Inject
	private SessionController sesionCotroller;

	@EJB
	private CreditCardEJB creditCardEJB;

	@EJB
	private SavingAccountEJB savingEJB;
	
	@EJB
	private ProductEJB productoEJB;

	private Double cantidad;

	private List<CreditCard> tarjetas;

	private String tarjetaSeleccionada;

	private List<SavingAccount> cuentas;

	private String cuentaSeleccionada;
	
	private Double total;

	@PostConstruct
	public void inicializar() {

		tarjetas = creditCardEJB.listaTarjetasCliente(sesionCotroller.getCliente());
		cuentas = savingEJB.listarCuentasAhorroCliente(sesionCotroller.getCliente());

	}

	public void avance() {
		
		Product protar = productoEJB.buscarProducto(tarjetaSeleccionada);
		CreditCard ta = (CreditCard) protar;
		if(ta != null){
		
			total = ta.getMonto()*0.30;
			if(cantidad <= total){
				ta.setMonto(ta.getMonto()-cantidad);			
				ta.setDeuda(ta.getDeuda()+cantidad);
				creditCardEJB.actualizar(ta);
				
				Product procuen = productoEJB.buscarProducto(cuentaSeleccionada);
				SavingAccount cue = (SavingAccount) procuen;
				if(cue != null){
					cue.setAmmount(cue.getAmmount()+cantidad);
					savingEJB.actualizarCuenta(cue);
					Messages.addFlashGlobalWarn("Avance Exitoso !");
					System.out.println("Avance Exitoso !");
				}
			}else{
				Messages.addFlashGlobalWarn("Imposible hacer el avance, El cupo maximo es de: "+total);
				System.out.println("Imposible hacer el avance, El cupo maximo es de: "+total);
			}
				
		
		}

		cantidad = 0.0;
	}
	

	/**
	 * @return the cantidad
	 */
	public Double getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
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

	

}
