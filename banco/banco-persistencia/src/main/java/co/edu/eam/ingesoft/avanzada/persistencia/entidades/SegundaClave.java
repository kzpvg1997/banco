package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="T_Segunda_Clave")
public class SegundaClave implements Serializable{
	
	
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="num_clave")
	private String numeroClave;
	
	@JoinColumns({
		@JoinColumn(name="holder_idtype", referencedColumnName="identification_type"),
		@JoinColumn(name="holder_idnumber", referencedColumnName="identification_number")
	})
	@ManyToOne(fetch=FetchType.EAGER)
	private Customer customer;
	
	@Column(name="fecha_genecion")
	@Temporal(TemporalType.DATE)
	private Date fechaGeneracion;
		

	public SegundaClave() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SegundaClave(String id, String numeroClave, Customer customer, Date fechaGeneracion) {
		super();
		this.id = id;
		this.numeroClave = numeroClave;
		this.customer = customer;
		this.fechaGeneracion = fechaGeneracion;
	}


	/**
	 * @return the clave
	 */
	public String getClave() {
		return id;
	}


	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.id = clave;
	}


	/**
	 * @return the numeroClave
	 */
	public String getNumeroClave() {
		return numeroClave;
	}


	/**
	 * @param numeroClave the numeroClave to set
	 */
	public void setNumeroClave(String numeroClave) {
		this.numeroClave = numeroClave;
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
	 * @return the fechaGeneracion
	 */
	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}


	/**
	 * @param fechaGeneracion the fechaGeneracion to set
	 */
	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	
	
}
