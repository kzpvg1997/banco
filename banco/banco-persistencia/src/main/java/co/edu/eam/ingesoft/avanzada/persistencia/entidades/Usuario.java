package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="T_USUARIO")
@NamedQuery(name=Usuario.USUARIO,query="SELECT u FROM Usuario u WHERE u.usuario=?1")
public class Usuario implements Serializable  {
	
	public static final String USUARIO = "Usuario.listUs";

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name="NAME_US")
	private String usuario;
	
	@Id
	@Column(name="CONTRASE�A")
	private String contrase�a;
	
	@OneToOne
	@JoinColumns({
		@JoinColumn(name="holder_idtype",referencedColumnName="identification_type"),
		@JoinColumn(name="holder_idnumber",referencedColumnName="identification_number")
	})
	private Customer customer;

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usuario(int id, String usuario, String contrase�a, Customer customer) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.contrase�a = contrase�a;
		this.customer = customer;
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
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the contrase�a
	 */
	public String getContrase�a() {
		return contrase�a;
	}

	/**
	 * @param contrase�a the contrase�a to set
	 */
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
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

	
}
