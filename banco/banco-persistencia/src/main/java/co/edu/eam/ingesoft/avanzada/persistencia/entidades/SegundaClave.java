package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;
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
	@Column(name="clave")
	private int clave;
	
	@JoinColumns({
		@JoinColumn(name="holder_idtype", referencedColumnName="identification_type"),
		@JoinColumn(name="holder_idnumber", referencedColumnName="identification_number")
	})
	@ManyToOne(fetch=FetchType.EAGER)
	private Customer customer;
	
	@Column(name="fecha_genecion")
	@Temporal(TemporalType.DATE)
	private Date fechaGeneracion;
	
	@Column(name="fecha_vencimiento")
	@Temporal(TemporalType.DATE)
	private Date fechaVencimiento;
	

	public SegundaClave() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public SegundaClave(int clave, Customer customer, Date fechaGeneracion, Date fechaVencimiento) {
		super();
		this.clave = clave;
		this.customer = customer;
		this.fechaGeneracion = fechaGeneracion;
		this.fechaVencimiento = fechaVencimiento;
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


	/**
	 * @return the fechaVencimiento
	 */
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}


	/**
	 * @param fechaVencimiento the fechaVencimiento to set
	 */
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}


	

	
}
