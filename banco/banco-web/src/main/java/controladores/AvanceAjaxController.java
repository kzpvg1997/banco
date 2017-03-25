package controladores;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;


import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SavingAccount;

@Named("avanceAjax")
@ViewScoped
public class AvanceAjaxController implements Serializable  {
	

	private String cbTarjeta;
	
	@Length(min=1,max=30,message="Lonitud entre 4 y 30")
	private String cantidad;
	
	private String cbCuenta;
	
	private List<CreditCard> tarjeta;
	
	private List<SavingAccount> cuenta;

}
