package controladores;

import java.io.Serializable;

import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

@Named("asociadosAjaxController")
@ViewScoped
public class AsociacionCuentaAjaxController implements Serializable{
	
	private String nombreTitular;
	
	private String cbDocumentoTitular;
	
	private String cbBanco;
	
	private String numero;
	
	private String nombre;
	

}
