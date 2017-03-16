package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_CREDITCARD_PAYMENT_CONSUME")
@NamedQueries({
	@NamedQuery(name=CreditCardPaymentConsume.listarPagosConsumo,query="SELECT c FROM CreditCardPaymentConsume c WHERE c.idConsume=?1")
})
public class CreditCardPaymentConsume implements Serializable{

	public static final String listarPagosConsumo = "CreditCardPaymentConsume.listarPagosConsumo";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne(cascade = {})
	@JoinColumn(name = "id_consume")
	private CreditCardConsume idConsume;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "payment_date", nullable = false)
	private Date paymentDate;

	@Column(name = "share", nullable = false)
	private int share;

	@Column(name = "ammount", nullable = false)
	private double ammount;

	@Column(name = "capital_ammount", nullable = false)
	private double capitalAmmount;

	@Column(name = "interest_ammount", nullable = false)
	private double interestAmmount;

	public CreditCardPaymentConsume(){
		
	}

	/**
	 * @param id
	 * @param idConsume
	 * @param paymentDate
	 * @param share
	 * @param ammount
	 * @param capitalAmmount
	 * @param interestAmmount
	 */
	public CreditCardPaymentConsume(CreditCardConsume idConsume, Date paymentDate, int share, double ammount,
			double capitalAmmount, double interestAmmount) {
		super();
		this.idConsume = idConsume;
		this.paymentDate = paymentDate;
		this.share = share;
		this.ammount = ammount;
		this.capitalAmmount = capitalAmmount;
		this.interestAmmount = interestAmmount;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the idConsume
	 */
	public CreditCardConsume getIdConsume() {
		return idConsume;
	}

	/**
	 * @param idConsume the idConsume to set
	 */
	public void setIdConsume(CreditCardConsume idConsume) {
		this.idConsume = idConsume;
	}

	/**
	 * @return the paymentDate
	 */
	public Date getPaymentDate() {
		return paymentDate;
	}

	/**
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * @return the share
	 */
	public int getShare() {
		return share;
	}

	/**
	 * @param share the share to set
	 */
	public void setShare(int share) {
		this.share = share;
	}

	/**
	 * @return the ammount
	 */
	public double getAmmount() {
		return ammount;
	}

	/**
	 * @param ammount the ammount to set
	 */
	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}

	/**
	 * @return the capitalAmmount
	 */
	public double getCapitalAmmount() {
		return capitalAmmount;
	}

	/**
	 * @param capitalAmmount the capitalAmmount to set
	 */
	public void setCapitalAmmount(double capitalAmmount) {
		this.capitalAmmount = capitalAmmount;
	}

	/**
	 * @return the interestAmmount
	 */
	public double getInterestAmmount() {
		return interestAmmount;
	}

	/**
	 * @param interestAmmount the interestAmmount to set
	 */
	public void setInterestAmmount(double interestAmmount) {
		this.interestAmmount = interestAmmount;
	}
        
        
	
}
