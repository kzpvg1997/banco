package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="T_PRODUCT")
@Entity
@NamedQueries({
	@NamedQuery(name=Product.ContarProductosCliente,query="SELECT p FROM Product p WHERE p.customer=?1")
})
public class Product implements Serializable{
	
	public static final String ContarProductosCliente = "Product.ContarProductosCliente";
	
	@Id
	@Column(name="number")
	private String number;
	
	@JoinColumns({
		@JoinColumn(name="holder_idtype", referencedColumnName="identification_type"),
		@JoinColumn(name="holder_idnumber", referencedColumnName="identification_number")
	}
	)
	@ManyToOne
	private Customer customer;
	
	@Temporal(TemporalType.DATE)
	@Column(name="expedition_date")
	private Date expeditionDate;

	public Product() {
		super();
	}

	public Product(String number, Customer customer, Date expeditionDate) {
		super();
		this.number = number;
		this.customer = customer;
		this.expeditionDate = expeditionDate;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getExpeditionDate() {
		return expeditionDate;
	}

	public void setExpeditionDate(Date expeditionDate) {
		this.expeditionDate = expeditionDate;
	}
	
	
	
}
