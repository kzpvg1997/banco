package controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.swing.JOptionPane;

import org.omnifaces.cdi.ViewScoped;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.pa.negocio.beans.CustomerEJB;

@Named("customerController")
@ViewScoped
public class ClienteController implements Serializable {
	
	@EJB
	private CustomerEJB customerEJB;
	
	private String tipoDocumento;
	
	private String numeroDocumento;
	
	private String nombre;
	
	private String apellido;
	
	private String correo;
	
	private String telefono;
	
	private List<Customer> cliente; 
	

	
	@PostConstruct
	public void inicializar(){
		cliente=customerEJB.listarClientes();
		
	}


	public void registrar(){
		
		Customer cli = new Customer(tipoDocumento,numeroDocumento,nombre,apellido,correo,telefono);
		customerEJB.crearCustomer(cli);
		tipoDocumento = "";
		numeroDocumento = "";
		nombre = "";
		apellido = "";
		cliente=customerEJB.listarClientes();
		
	}
	
	public void buscar(){
		
		Customer cli = customerEJB.buscarCustomer(tipoDocumento, numeroDocumento);
		if(cli != null){
			tipoDocumento = cli.getIdType();
			numeroDocumento = cli.getIdNum();
			nombre = cli.getName();
			apellido = cli.getLastName();
		}
	}


	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}


	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}


	/**
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}


	/**
	 * @param numeroDocumento the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
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


	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}


	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	/**
	 * @return the cliente
	 */
	public List<Customer> getCliente() {
		return cliente;
	}


	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(List<Customer> cliente) {
		this.cliente = cliente;
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
