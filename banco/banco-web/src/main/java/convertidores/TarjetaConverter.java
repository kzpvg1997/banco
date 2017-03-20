package convertidores;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCard;
import co.edu.eam.ingesoft.pa.negocio.beans.CreditCardEJB;

@FacesConverter(value="tarjConverter",forClass=CreditCard.class)
@Named("tarjConverter")
public class TarjetaConverter implements Converter{

	@EJB
	private CreditCardEJB creditCartdEJB;
	
	/**
	 * Metodo para convertit una cadenaa un objeto
	 * @param arg0
	 * @param arg1
	 * @param string
	 * @return
	 */
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string){
		if(string == null || string.trim().length() == 0 || string.equals("Seleccione...")){
			return null;
		}
		return creditCartdEJB.buscarCreditCard(string);
	}
	
	/**
	 * Metodo para convertir un objeto en una cadena
	 * @param arg0
	 * @param arg1
	 * @param obj
	 * @return
	 */
	
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj){
		if(obj instanceof CreditCard){
			CreditCard tarjeta = (CreditCard) obj;
			return tarjeta.getNumber();
		}
		return null;
	}
}
