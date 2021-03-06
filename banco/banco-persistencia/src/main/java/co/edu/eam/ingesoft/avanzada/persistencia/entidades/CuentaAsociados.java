/**
 * 
 */
package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@NamedQueries({
	@NamedQuery(name=CuentaAsociados.ASOCIACIONES_CLIENTE,query="SELECT c FROM CuentaAsociados c WHERE c.customer=?1"),
	@NamedQuery(name=CuentaAsociados.BUSCAR_CUENTA_ASOCIADOS,query="SELECT c FROM CuentaAsociados c WHERE c.numeroCuenta=?1")
})
public class CuentaAsociados  implements Serializable{

	public static final String ASOCIACIONES_CLIENTE = "CuentasAsciados.Cliente";
	
	public static final String BUSCAR_CUENTA_ASOCIADOS = "CuentasAsociados.numeroCuenta";

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo_cuenta")
	private int codigoCuenta;
	
	@Column(name="numero_cuenta")
	private String numeroCuenta;
	
	@Column(name="numero_documento",unique=true)
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
	private Bank bank;
	
	@Column(name="estado")
	private String estado;
	
	@Column(name="name_association")
	private String nombreAsociacion;
	
	
	
	
	
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
	 * @param bank
	 * @param verificado
	 * @param nombreAsociacion
	 * @param monto
	 */
	public CuentaAsociados(String numeroCuenta, String idAsociado, String nombreAsociado, String tipoID,
			Customer customer, Bank bank, String estado, String nombreAsociacion) {
		
		this.numeroCuenta = numeroCuenta;
		this.idAsociado = idAsociado;
		this.nombreAsociado = nombreAsociado;
		this.tipoID = tipoID;
		this.customer = customer;
		this.bank = bank;
		this.estado = estado;
		this.nombreAsociacion = nombreAsociacion;
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
	 * @return the bank
	 */
	public Bank getBanco() {
		return bank;
	}


	/**
	 * @param bank the bank to set
	 */
	public void setBanco(Bank bank) {
		this.bank = bank;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}


	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
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
	
}
