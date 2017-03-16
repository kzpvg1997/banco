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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_TRANSACTION")
public class Transaction implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@JoinColumn(name = "saving_account_number")
	@ManyToOne(cascade = {})
	private SavingAccount savingAccNumber;

	@Column(name = "ammount", nullable = false)
	private double ammount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "transaction_date", nullable = false)
	private Date transactionDate;

	@Column(name = "source_transaction",length=15)
	private String sourceTransact;
	
	@Column(name = "tipo_transaccion")
	private String tipoTransaccion;

	public Transaction(){
		
	}

	/**
	 * @param id
	 * @param savingAccNumber
	 * @param ammount
	 * @param transactionDate
	 * @param sourceTransact
	 */
	public Transaction(SavingAccount savingAccNumber, double ammount, Date transactionDate,
			String sourceTransact,String tipoTransaccion) {
		super();
		this.savingAccNumber = savingAccNumber;
		this.ammount = ammount;
		this.transactionDate = transactionDate;
		this.sourceTransact = sourceTransact;
		this.tipoTransaccion = tipoTransaccion;
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
	 * @return the savingAccNumber
	 */
	public SavingAccount getSavingAccNumber() {
		return savingAccNumber;
	}

	/**
	 * @param savingAccNumber the savingAccNumber to set
	 */
	public void setSavingAccNumber(SavingAccount savingAccNumber) {
		this.savingAccNumber = savingAccNumber;
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
	 * @return the transactionDate
	 */
	public Date getTransactionDate() {
		return transactionDate;
	}

	/**
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	/**
	 * @return the sourceTransact
	 */
	public String getSourceTransact() {
		return sourceTransact;
	}

	/**
	 * @param sourceTransact the sourceTransact to set
	 */
	public void setSourceTransact(String sourceTransact) {
		this.sourceTransact = sourceTransact;
	}

	/**
	 * @return the tipoTransaccion
	 */
	public String getTipoTransaccion() {
		return tipoTransaccion;
	}

	/**
	 * @param tipoTransaccion the tipoTransaccion to set
	 */
	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}
	
	
}
