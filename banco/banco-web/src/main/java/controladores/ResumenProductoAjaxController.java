package controladores;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SavingAccount;

@Named("resumenProductoAjaxController")
@ViewScoped
public class ResumenProductoAjaxController implements Serializable {
	
	private List<SavingAccount> cuentas;
	
	private List<CreditCard> tarjetas;

}
