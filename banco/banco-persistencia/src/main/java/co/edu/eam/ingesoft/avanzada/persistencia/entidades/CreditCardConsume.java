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


@Table(name="T_CREDITCARD_CONSUME")
@Entity
@NamedQueries({
	@NamedQuery(name=CreditCardConsume.CONSUMOS_TARJETAS,query="SELECT c FROM CreditCardConsume c WHERE c.creditCard=?1")
	
})
public class CreditCardConsume implements Serializable{

	public static final String CONSUMOS_TARJETAS="Consumos.Tarjetas";
	
	//atributos de la clase
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@JoinColumn(name="creditcard_number")
	@ManyToOne(cascade={})
	private CreditCard creditCard;
	
	@Column(name="date_consume")
	@Temporal(TemporalType.DATE)
	private Date dateConsume;
	
	@Column(name="number_shares")
	private int numberShares;
	
	@Column(name="remanning_shares")
	private int remaningShares;
	
	@Column(name="ammount")
	private double ammount;
	
	@Column(name="interest")
	private double interest;
	
	@Column(name="remaining_ammount")
	private double remainingAmmount;
	
	@Column(name="is_payed")
	private boolean isPayed;
	
	@Column(name="valor_cuota")
	private double valorCuota;
	
	public CreditCardConsume (){
		
	}

	/**
	 * @param id
	 * @param creditCard
	 * @param dateConsume
	 * @param numberShares
	 * @param ammount
	 * @param interest
	 * @param remainingAmmount
	 * @param isPayed
	 */
	public CreditCardConsume( CreditCard creditCard, Date dateConsume, int numberShares, int remaningShares, double ammount,
			double interest, double remainingAmmount, boolean isPayed,double valorCuota) {
		super();
		
		this.creditCard = creditCard;
		this.dateConsume = dateConsume;
		this.numberShares = numberShares;
		this.remaningShares = remaningShares;
		this.ammount = ammount;
		this.interest = interest;
		this.remainingAmmount = remainingAmmount;
		this.isPayed = isPayed;
		this.valorCuota = valorCuota;
	}
	
	/**
	 * @return the remaningShares
	 */
	public int getRemaningShares() {
		return remaningShares;
	}

	/**
	 * @param remaningShares the remaningShares to set
	 */
	public void setRemaningShares(int remaningShares) {
		this.remaningShares = remaningShares;
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
	 * @return the creditCard
	 */
	public CreditCard getCreditCard() {
		return creditCard;
	}

	/**
	 * @param creditCard the creditCard to set
	 */
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	/**
	 * @return the dateConsume
	 */
	public Date getDateConsume() {
		return dateConsume;
	}

	/**
	 * @param dateConsume the dateConsume to set
	 */
	public void setDateConsume(Date dateConsume) {
		this.dateConsume = dateConsume;
	}

	/**
	 * @return the numberShares
	 */
	public int getNumberShares() {
		return numberShares;
	}

	/**
	 * @param numberShares the numberShares to set
	 */
	public void setNumberShares(int numberShares) {
		this.numberShares = numberShares;
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
	 * @return the interest
	 */
	public double getInterest() {
		return interest;
	}

	/**
	 * @param interest the interest to set
	 */
	public void setInterest(double interest) {
		this.interest = interest;
	}

	/**
	 * @return the remainingAmmount
	 */
	public double getRemainingAmmount() {
		return remainingAmmount;
	}

	/**
	 * @param remainingAmmount the remainingAmmount to set
	 */
	public void setRemainingAmmount(double remainingAmmount) {
		this.remainingAmmount = remainingAmmount;
	}

	/**
	 * @return the isPayed
	 */
	public boolean isPayed() {
		return isPayed;
	}

	/**
	 * @param isPayed the isPayed to set
	 */
	public void setPayed(boolean isPayed) {
		this.isPayed = isPayed;
	}
	
	/**
	 * @return the valorCuota
	 */
	public double getValorCuota() {
		return valorCuota;
	}

	/**
	 * @param valorCuota the valorCuota to set
	 */
	public void setValorCuota(double valorCuota) {
		this.valorCuota = valorCuota;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return id+"";
	}
	
	
	
}
