package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
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

@Entity
@Table(name="T_CREDITCARD")
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQueries({
	@NamedQuery(name=CreditCard.TarjetasCliente,query="SELECT c FROM CreditCard c WHERE c.customer=?1")
})
public class CreditCard extends Product implements Serializable{

	public static final String TarjetasCliente = "CreditCard.ContarTarjetasCliente";
	
	
	@Column(name="cvc")
	private String cvc;
	
	@Temporal(TemporalType.DATE)
	@Column(name="expiration_date")
	private Date expirationDate;
	
	@JoinColumn(name="franchise")
	@ManyToOne(cascade={})
	private Franchise franchise;
	
	@Column(name="monto")
	private Double monto;
	

	public CreditCard() {
		super();
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




	public CreditCard(String cvc, Date expirationDate, Franchise franchise, Double monto) {
		super();
		this.cvc = cvc;
		this.expirationDate = expirationDate;
		this.franchise = franchise;
		this.monto = monto;
	}




	public String getCvc() {
		return cvc;
	}


	public void setCvc(String cvc) {
		this.cvc = cvc;
	}


	public Date getExpirationDate() {
		return expirationDate;
	}


	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}


	public Franchise getFranchise() {
		return franchise;
	}


	public void setFranchise(Franchise franchise) {
		this.franchise = franchise;
	}




	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getNumber();
	}
	
	
	
}
