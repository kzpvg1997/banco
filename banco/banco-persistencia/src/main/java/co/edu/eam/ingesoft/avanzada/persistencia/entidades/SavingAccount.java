package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="T_SAVING_ACCOUNT")
@NamedQueries({
	@NamedQuery(name=SavingAccount.CUENTASAHORROCLIENTE,query="SELECT s FROM SavingAccount s WHERE s.customer=?1")
})
public class SavingAccount extends Product implements Serializable{

	public static final String CUENTASAHORROCLIENTE = "SavingAccount.CUENTASAHORROCLIENTE";
	
	@Column(name = "saving_interest", nullable = false)
	private double savingInterest;

	@Column(name = "ammount", nullable = false)
	private double ammount;
	

	public SavingAccount() {

	}


	/**
	 * @return the savingInterest
	 */
	public double getSavingInterest() {
		return savingInterest;
	}


	/**
	 * @param savingInterest the savingInterest to set
	 */
	public void setSavingInterest(double savingInterest) {
		this.savingInterest = savingInterest;
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


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(ammount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(savingInterest);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SavingAccount other = (SavingAccount) obj;
		if (Double.doubleToLongBits(ammount) != Double.doubleToLongBits(other.ammount))
			return false;
		if (Double.doubleToLongBits(savingInterest) != Double.doubleToLongBits(other.savingInterest))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return getNumber();
	}
	
	



}
