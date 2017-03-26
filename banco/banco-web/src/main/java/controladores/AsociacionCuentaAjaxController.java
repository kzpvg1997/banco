package controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Banco;
import co.edu.eam.ingesoft.pa.negocio.beans.CuentaAsociadosEJB;

@Named("asociadosAjaxController")
@ViewScoped
public class AsociacionCuentaAjaxController implements Serializable{
	
	@EJB
	private CuentaAsociadosEJB asociadosEJB;
	
	private String nombreTitular;
	
	private String cbDocumentoTitular;
	
	private List<Banco> bancos;
	
	private String numero;
	
	private String nombre;
	
	private String bancoSeleccionado;
	
	
	@PostConstruct
	public void inicializar(){
		bancos = asociadosEJB.listaBancos();
	}


	/**
	 * @return the bancoSeleccionado
	 */
	public String getBancoSeleccionado() {
		return bancoSeleccionado;
	}


	/**
	 * @param bancoSeleccionado the bancoSeleccionado to set
	 */
	public void setBancoSeleccionado(String bancoSeleccionado) {
		this.bancoSeleccionado = bancoSeleccionado;
	}


	/**
	 * @return the asociadosEJB
	 */
	public CuentaAsociadosEJB getAsociadosEJB() {
		return asociadosEJB;
	}


	/**
	 * @param asociadosEJB the asociadosEJB to set
	 */
	public void setAsociadosEJB(CuentaAsociadosEJB asociadosEJB) {
		this.asociadosEJB = asociadosEJB;
	}


	/**
	 * @return the nombreTitular
	 */
	public String getNombreTitular() {
		return nombreTitular;
	}


	/**
	 * @param nombreTitular the nombreTitular to set
	 */
	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}


	/**
	 * @return the cbDocumentoTitular
	 */
	public String getCbDocumentoTitular() {
		return cbDocumentoTitular;
	}


	/**
	 * @param cbDocumentoTitular the cbDocumentoTitular to set
	 */
	public void setCbDocumentoTitular(String cbDocumentoTitular) {
		this.cbDocumentoTitular = cbDocumentoTitular;
	}


	/**
	 * @return the bancos
	 */
	public List<Banco> getBancos() {
		return bancos;
	}


	/**
	 * @param bancos the bancos to set
	 */
	public void setBancos(List<Banco> bancos) {
		this.bancos = bancos;
	}


	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}


	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}


	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
