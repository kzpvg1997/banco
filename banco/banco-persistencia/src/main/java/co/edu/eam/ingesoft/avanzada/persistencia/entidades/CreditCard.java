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
	
	@Column(name="monto_original")
	private Double montoOriginal;
	
	@Column(name="deuda")
	private Double deuda;
	
	@Column(name="disponible")
	private boolean disponible;
	//HOla
	

	public CreditCard() {
		super();
	}

	
	

	public CreditCard(String cvc, Date expirationDate, Franchise franchise, Double monto, Double montoOriginal,
			Double deuda, boolean disponible) {
		super();
		this.cvc = cvc;
		this.expirationDate = expirationDate;
		this.franchise = franchise;
		this.monto = monto;
		this.montoOriginal = montoOriginal;
		this.deuda = deuda;
		this.disponible = disponible;
	}




	/**
	 * @return the cvc
	 */
	public String getCvc() {
		return cvc;
	}


	/**
	 * @param cvc the cvc to set
	 */
	public void setCvc(String cvc) {
		this.cvc = cvc;
	}


	/**
	 * @return the expirationDate
	 */
	public Date getExpirationDate() {
		return expirationDate;
	}


	/**
	 * @param expirationDate the expirationDate to set
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}


	/**
	 * @return the franchise
	 */
	public Franchise getFranchise() {
		return franchise;
	}


	/**
	 * @param franchise the franchise to set
	 */
	public void setFranchise(Franchise franchise) {
		this.franchise = franchise;
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


	/**
	 * @return the montoOriginal
	 */
	public Double getMontoOriginal() {
		return montoOriginal;
	}


	/**
	 * @param montoOriginal the montoOriginal to set
	 */
	public void setMontoOriginal(Double montoOriginal) {
		this.montoOriginal = montoOriginal;
	}


	/**
	 * @return the deuda
	 */
	public Double getDeuda() {
		return deuda;
	}


	/**
	 * @param deuda the deuda to set
	 */
	public void setDeuda(Double deuda) {
		this.deuda = deuda;
	}


	/**
	 * @return the disponible
	 */
	public boolean getDisponible() {
		return disponible;
	}


	/**
	 * @param disponible the disponible to set
	 */
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}


	/**
	 * @return the tarjetascliente
	 */
	public static String getTarjetascliente() {
		return TarjetasCliente;
	}




	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getNumber();
	}

	


	
}
