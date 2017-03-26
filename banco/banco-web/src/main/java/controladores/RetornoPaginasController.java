package controladores;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Usuario;

@Named("retornoController")
@SessionScoped
public class RetornoPaginasController implements Serializable {

	private int numero = 1;
	
	public String otraPagina() {
		if(numero == 1){
			return"/paginas/seguro/resumenproducto.xhtml?faces-redirect=true";
		}
		return"/paginas/seguro/resumenproducto.xhtml?faces-redirect=true";

	}
	
	
}
