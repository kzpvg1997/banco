package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import co.edu.eam.ingesoft.avanzada.persistencia.enumeraciones.TipoDocumentEnum;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_CUSTOMER")
@IdClass(CustomerPK.class)
@NamedQueries({
	@NamedQuery(name=Customer.ListaClientes,query="SELECT cl FROM Customer cl")
})
public class Customer implements Serializable{

	public static final String ListaClientes = "Customer.listarClientes";
	
	@Id
	@Column(name="identification_type")
//        @Enumerated(EnumType.STRING)
	private String idType;
	
	@Id
	@Column(name="identification_number")
	private String idNum;

	@Column(name = "name", nullable = false,length=50)
	private String name;

	@Column(name = "lastname", nullable = false,length=50)
	private String lastName;
	
	@Column(name="correo")
	private String correo;
	
	@Column(name="telefono")
	private String telefono;

	@OneToMany(mappedBy="customer",cascade={})
	private List<Product> productos;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}

    public Customer(String idType, String idNum, String name, String lastName,String correo,String telefono) {
        this.idType = idType;
        this.idNum = idNum;
        this.name = name;
        this.lastName = lastName;
        this.correo = correo;
        this.telefono = telefono;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }


	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Product> getProductos() {
		return productos;
	}

	public void setProductos(List<Product> productos) {
		this.productos = productos;
	}

	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
	
	
}
