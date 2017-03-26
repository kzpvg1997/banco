/**
 * 
 */
package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



/**
 * @author TOSHIBAP55W
 *
 */

@Entity
@Table(name="T_ASSOCIATED_ACOUNTS")
public class CuentaAsociados  implements Serializable{

	@Id
	@Column(name="numero_cuenta")
	private String numeroCuenta;
	
	@Column(name="numero_documento")
	private String idAsociado;
	
	@Column(name="nombre_titular")
	private String nombreAsociado;
	
	@Column(name="tipo_documento")
	private String tipoID;


	@JoinColumns({
		@JoinColumn(name="numero_cliente", referencedColumnName="identification_type"),
		@JoinColumn(name="tipo_id_cliente", referencedColumnName="identification_number")
	})
	@ManyToOne
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="bank_id")
	private Banco banco;
	
	@Column(name="verificated")
	private boolean verificado;
	
	@Column(name="name_association")
	private String nombreAsociacion;
	
	@Column(name="ammount")
	private double monto;
	
	
	
	
	public CuentaAsociados() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param numeroCuenta
	 * @param idAsociado
	 * @param nombreAsociado
	 * @param tipoID
	 * @param customer
	 * @param banco
	 * @param verificado
	 * @param nombreAsociacion
	 * @param monto
	 */
	public CuentaAsociados(String numeroCuenta, String idAsociado, String nombreAsociado, String tipoID,
			Customer customer, Banco banco, boolean verificado, String nombreAsociacion, double monto) {
		
		this.numeroCuenta = numeroCuenta;
		this.idAsociado = idAsociado;
		this.nombreAsociado = nombreAsociado;
		this.tipoID = tipoID;
		this.customer = customer;
		this.banco = banco;
		this.verificado = verificado;
		this.nombreAsociacion = nombreAsociacion;
		this.monto = monto;
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
	 * @return the idAsociado
	 */
	public String getIdAsociado() {
		return idAsociado;
	}


	/**
	 * @param idAsociado the idAsociado to set
	 */
	public void setIdAsociado(String idAsociado) {
		this.idAsociado = idAsociado;
	}


	/**
	 * @return the nombreAsociado
	 */
	public String getNombreAsociado() {
		return nombreAsociado;
	}


	/**
	 * @param nombreAsociado the nombreAsociado to set
	 */
	public void setNombreAsociado(String nombreAsociado) {
		this.nombreAsociado = nombreAsociado;
	}


	/**
	 * @return the tipoID
	 */
	public String getTipoID() {
		return tipoID;
	}


	/**
	 * @param tipoID the tipoID to set
	 */
	public void setTipoID(String tipoID) {
		this.tipoID = tipoID;
	}


	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}


	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	/**
	 * @return the banco
	 */
	public Banco getBanco() {
		return banco;
	}


	/**
	 * @param banco the banco to set
	 */
	public void setBanco(Banco banco) {
		this.banco = banco;
	}


	/**
	 * @return the verificado
	 */
	public boolean isVerificado() {
		return verificado;
	}


	/**
	 * @param verificado the verificado to set
	 */
	public void setVerificado(boolean verificado) {
		this.verificado = verificado;
	}


	/**
	 * @return the nombreAsociacion
	 */
	public String getNombreAsociacion() {
		return nombreAsociacion;
	}


	/**
	 * @param nombreAsociacion the nombreAsociacion to set
	 */
	public void setNombreAsociacion(String nombreAsociacion) {
		this.nombreAsociacion = nombreAsociacion;
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



	
}
