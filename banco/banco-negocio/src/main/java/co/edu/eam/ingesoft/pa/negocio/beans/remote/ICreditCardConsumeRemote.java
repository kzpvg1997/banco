package co.edu.eam.ingesoft.pa.negocio.beans.remote;

import java.util.List;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCardConsume;

public interface ICreditCardConsumeRemote {

	public void crearConsumo(CreditCardConsume consumo);
	
	public List<CreditCardConsume> consumosTarjeta(CreditCard tarjeta);
	
	public double sumaConsumosTarjetaCuotas(CreditCard tarjeta);
	
	public List<CreditCardConsume> consumosTarjetaNoPagos(CreditCard c);
}
