package controladores;

import java.io.Serializable;

import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

@Named("transaccionAjaxController")
@ViewScoped
public class TransaccionAjaxController implements Serializable{
	
	private String cbCuentaOrigen;
	
	private String cbCuentaDestino;
	
	private Double monto;

}
