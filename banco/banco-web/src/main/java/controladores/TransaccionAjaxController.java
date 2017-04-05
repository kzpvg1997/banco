package controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CuentaAsociados;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SavingAccount;
import co.edu.eam.ingesoft.pa.negocio.beans.CreditCardEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CuentaAsociadosEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.SavingAccountEJB;

@Named("transaccionAjaxController")
@ViewScoped
public class TransaccionAjaxController implements Serializable {

	@Inject
	private SessionController sesionCotroller;

	@EJB
	private SavingAccountEJB savigEJB;

	@EJB
	private CuentaAsociadosEJB asocEJB;

	private List<SavingAccount> cuentasCliente;

	private String cuentasClienteSeleccionada;

	private List<CuentaAsociados> cuentasAsociadas;

	private String cuentaAsociadoSeleccionada;

	private Double monto;
	
	private int clave;

	@PostConstruct
	public void inicializar() {
		listarCombos();

	}

	public void listarCombos() {
		Customer cli = sesionCotroller.getCliente();
		if (cli != null) {
			cuentasCliente = savigEJB.listarCuentasAhorroCliente(cli);
			cuentasAsociadas = asocEJB.listarCuentasVerificadas(cli);
		}

	}

	public void tranferirCuentaAsociados() {

		SavingAccount sa = savigEJB.buscarCuentaAhorro(cuentaAsociadoSeleccionada);
		if (sa != null) {
			CuentaAsociados cu = asocEJB.buscarCuentaAsociado(cuentaAsociadoSeleccionada);
			if(cu!=null){
				
				savigEJB.transferirCuentaAsociados(monto, sa, cu);
				Messages.addFlashGlobalWarn("Transferencia Exitosa !");
				System.out.println("Transferencia Exitosa !");
			}
				
			}

	}
	
	public void verificarClave(){
		System.out.println("Verificando...");
	}

	/**
	 * @return the sesionCotroller
	 */
	public SessionController getSesionCotroller() {
		return sesionCotroller;
	}

	/**
	 * @param sesionCotroller
	 *            the sesionCotroller to set
	 */
	public void setSesionCotroller(SessionController sesionCotroller) {
		this.sesionCotroller = sesionCotroller;
	}

	/**
	 * @return the savigEJB
	 */
	public SavingAccountEJB getSavigEJB() {
		return savigEJB;
	}

	/**
	 * @param savigEJB
	 *            the savigEJB to set
	 */
	public void setSavigEJB(SavingAccountEJB savigEJB) {
		this.savigEJB = savigEJB;
	}

	/**
	 * @return the asocEJB
	 */
	public CuentaAsociadosEJB getAsocEJB() {
		return asocEJB;
	}

	/**
	 * @param asocEJB
	 *            the asocEJB to set
	 */
	public void setAsocEJB(CuentaAsociadosEJB asocEJB) {
		this.asocEJB = asocEJB;
	}

	/**
	 * @return the cuentasCliente
	 */
	public List<SavingAccount> getCuentasCliente() {
		return cuentasCliente;
	}

	/**
	 * @param cuentasCliente
	 *            the cuentasCliente to set
	 */
	public void setCuentasCliente(List<SavingAccount> cuentasCliente) {
		this.cuentasCliente = cuentasCliente;
	}

	/**
	 * @return the cuentasClienteSeleccionada
	 */
	public String getCuentasClienteSeleccionada() {
		return cuentasClienteSeleccionada;
	}

	/**
	 * @param cuentasClienteSeleccionada
	 *            the cuentasClienteSeleccionada to set
	 */
	public void setCuentasClienteSeleccionada(String cuentasClienteSeleccionada) {
		this.cuentasClienteSeleccionada = cuentasClienteSeleccionada;
	}

	/**
	 * @return the cuentasAsociadas
	 */
	public List<CuentaAsociados> getCuentasAsociadas() {
		return cuentasAsociadas;
	}

	/**
	 * @param cuentasAsociadas
	 *            the cuentasAsociadas to set
	 */
	public void setCuentasAsociadas(List<CuentaAsociados> cuentasAsociadas) {
		this.cuentasAsociadas = cuentasAsociadas;
	}

	/**
	 * @return the cuentaAsociadoSeleccionada
	 */
	public String getCuentaAsociadoSeleccionada() {
		return cuentaAsociadoSeleccionada;
	}

	/**
	 * @param cuentaAsociadoSeleccionada
	 *            the cuentaAsociadoSeleccionada to set
	 */
	public void setCuentaAsociadoSeleccionada(String cuentaAsociadoSeleccionada) {
		this.cuentaAsociadoSeleccionada = cuentaAsociadoSeleccionada;
	}

	/**
	 * @return the monto
	 */
	public Double getMonto() {
		return monto;
	}

	/**
	 * @param monto
	 *            the monto to set
	 */
	public void setMonto(Double monto) {
		this.monto = monto;
	}

	/**
	 * @return the clave
	 */
	public int getClave() {
		return clave;
	}

	/**
	 * @param clave the clave to set
	 */
	public void setClave(int clave) {
		this.clave = clave;
	}

	
}
