package controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Banco;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CuentaAsociados;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.pa.negocio.beans.CreditCardEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CuentaAsociadosEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CustomerEJB;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@ViewScoped
@Named("asociadosAjaxController")
public class AsociacionCuentaAjaxController implements Serializable{
	
	@EJB
	private CuentaAsociadosEJB cuAsEJB; //EJB de CuentaAsociados
	
	@EJB
	private CustomerEJB customerEJB;
	
	@Inject
	private SessionController sesionCotroller;
	
	private String nombreTitular;
	
	private String cbDocumentoTitular;
	
	private String numeroDocumento;
	
	private List<Banco> bancos;
	
	private String numeroCuenta;
	
	private String nombreCuenta;
	
	@NotNull(message="Seleccione una opcion")
	private String bancoSeleccionado;
	
	private double monto;
	
	private List<CuentaAsociados> cuentasCliente;
	
	
	@PostConstruct
	public void inicializar(){
		bancos = cuAsEJB.listaBancos();
		cuentasCliente = cuAsEJB.listaCuentasCliente(sesionCotroller.getCliente());
	}

	
	public void agregarCuentaAsociada(){
		try{
		Customer cus = customerEJB.buscarCustomer(sesionCotroller.getCliente().getIdType(), sesionCotroller.getCliente().getIdNum());
		
		if(cus != null){
			Banco b = cuAsEJB.buscarBanco(bancoSeleccionado);
				if(b != null){
					CuentaAsociados cu = new CuentaAsociados(numeroCuenta,numeroDocumento,nombreTitular,cbDocumentoTitular,
							cus,b,true,nombreCuenta,monto);
					cuAsEJB.agregarCuentaAsociados(cu);
					cuentasCliente = actualizarCuentasCliente();
					Messages.addFlashGlobalInfo("Cuenta Asociada Registrada Con Exito!");
					
				}
		}
		
		}catch (ExcepcionNegocio e) {
			Messages.addFlashGlobalError(e.getMessage());
		}

	}
	

	public List<CuentaAsociados> actualizarCuentasCliente(){
	
		Customer cus = customerEJB.buscarCustomer(sesionCotroller.getCliente().getIdType(), sesionCotroller.getCliente().getIdNum());
		return cuAsEJB.listaCuentasCliente(cus);
	}
	
	public void borrar(CuentaAsociados cu) {

		cuAsEJB.borrarCuentaAsociado(cu);
		Messages.addFlashGlobalInfo("Docente borrado exitosamente");
		cuentasCliente = cuAsEJB.listaCuentasCliente(sesionCotroller.getCliente());
	}
	
	
	
	
	/**
	 * @return the cuentasCliente
	 */
	public List<CuentaAsociados> getCuentasCliente() {
		return cuentasCliente;
	}


	/**
	 * @param cuentasCliente the cuentasCliente to set
	 */
	public void setCuentasCliente(List<CuentaAsociados> cuentasCliente) {
		this.cuentasCliente = cuentasCliente;
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
	 * @return the monto
	 */
	public double getMonto() {
		return monto;
	}


	/**
	 * @param monto the monto to set
	 */
	public void setMonto(double monto) {
		this.monto = monto;
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
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}


	/**
	 * @param numeroCuenta the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}


	/**
	 * @return the nombreCuenta
	 */
	public String getNombreCuenta() {
		return nombreCuenta;
	}


	/**
	 * @param nombreCuenta the nombreCuenta to set
	 */
	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}


	/**
	 * @return the cuAsEJB
	 */
	public CuentaAsociadosEJB getCuAsEJB() {
		return cuAsEJB;
	}


	/**
	 * @param cuAsEJB the cuAsEJB to set
	 */
	public void setCuAsEJB(CuentaAsociadosEJB cuAsEJB) {
		this.cuAsEJB = cuAsEJB;
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


	
	
	
}
