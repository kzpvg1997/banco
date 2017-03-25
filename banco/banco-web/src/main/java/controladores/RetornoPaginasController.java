package controladores;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("retornoController")
@SessionScoped
public class RetornoPaginasController implements Serializable {

	
	public String otraPagina(){
		return "/paginas/seguro/resumenproducto.xhtml?faces-redirect=true";
		
	}
}
