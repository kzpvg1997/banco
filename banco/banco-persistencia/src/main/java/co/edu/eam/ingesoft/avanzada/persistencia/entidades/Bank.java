/**
 * 
 */
package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author TOSHIBAP55W
 *
 */

@Entity
@Table(name="T_BANK")
@NamedQueries({
	@NamedQuery(name=Bank.LISTA_BANCOS,query="SELECT b FROM Bank b")
})
public class Bank implements Serializable{

	public static final String LISTA_BANCOS= "Lista.Bancos";
	
	@Id
	@Column(name="id_bank")
	private String id;
	
	
	@Column(name="name_bank",length=40)
	private String nombre;
	
	
	public Bank() {
		
	}
	
	/**
	 * @param id
	 * @param nombre
	 */
	public Bank(String id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}



	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
