package controladores;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.pa.negocio.beans.CustomerEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.UsuarioEJB;

@Named("sessionControl")
@SessionScoped
public class SessionController implements Serializable {

	private String tipo;
	private String num;
	private String nombre;

	private Customer cliente;

	@EJB
	private CustomerEJB customerEJB;

	@EJB
	private UsuarioEJB usuarioEJB;


	public String loginU() {

		Usuario usu = usuarioEJB.buscarUsuario(num);
		if (usu != null) {
			if (usu.getUsuario().equals(nombre)) {
				if (usu.getContraseña().equals(num)) {
					Customer clienteTemp = customerEJB.buscarCustomer(usu.getCustomer().getIdType(),
							usu.getCustomer().getIdNum());
					if (clienteTemp != null) {
						cliente = clienteTemp;
						Faces.setSessionAttribute("user", cliente);
						System.out.println("Inicio sesion");
						return "/paginas/seguro/inicio.xhtml?faces-redirect=true";
						
					}
				} else {
					Messages.addFlashGlobalError("Datos incorrectos");
					System.out.println("Datos incorrectos");
				}
			} else {
				Messages.addFlashGlobalError("Datos incorrectos");
				System.out.println("Datos incorrectos");
			}
		} else {
			Messages.addFlashGlobalError("Datos incorrectos");
			System.out.println("Datos incorrectos");
		}
		return null;
	}
	

	public String cerrarSesion() {
		cliente = null;
		HttpSession sesion;
		sesion = (HttpSession) Faces.getSession();
		sesion.invalidate();
		return "/paginas/publico/login.xhtml?faces-redirect=true";
	}

	public boolean isSesion() {
		return cliente != null;
	}



	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the num
	 */
	public String getNum() {
		return num;
	}

	/**
	 * @param num
	 *            the num to set
	 */
	public void setNum(String num) {
		this.num = num;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the cliente
	 */
	public Customer getCliente() {
		return cliente;
	}

	/**
	 * @param cliente
	 *            the cliente to set
	 */
	public void setCliente(Customer cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the customerEJB
	 */
	public CustomerEJB getCustomerEJB() {
		return customerEJB;
	}

	/**
	 * @param customerEJB
	 *            the customerEJB to set
	 */
	public void setCustomerEJB(CustomerEJB customerEJB) {
		this.customerEJB = customerEJB;
	}

}
