package co.edu.eam.ingesoft.standalone.gui.controlador;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Franchise;
import java.util.Date;

import javax.naming.NamingException;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SavingAccount;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ISavingAccountRemote;
import co.edu.eam.ingesoft.standalone.gui.util.ServiceLocator;
import java.util.List;

public class ControladorVentanaGestionSavingAccount {
	
	/**
	 * Interface remota de EJB
	 */
	private ISavingAccountRemote savingAccountRemote;
	
	/**
	 * Constructor
	 * @throws NamingException
	 */
	public ControladorVentanaGestionSavingAccount() throws NamingException {
		savingAccountRemote = (ISavingAccountRemote) ServiceLocator.buscarEJB("SavingAccountEJB",
				ISavingAccountRemote.class.getCanonicalName());
	}
	
	/**
	 * Metodo para crear una cuenta de ahorros
	 * @param cuenta que recibe como parametro
	 */
	public void crearCuentaAhorro(SavingAccount cuenta){
		savingAccountRemote.crearCuentaAhorro(cuenta);
	}
	
	public SavingAccount buscarCuentaAhorro(String num){
		return savingAccountRemote.buscarCuentaAhorro(num);
	}
	
	/**
	 * Metodo que captura la fecha del sistema
	 * @return la fecha actual
	 */
	public Date fechaExpedicion() {
		return null;//savingAccountRemote.fechaExpedicion();
	}
        
        /**
         * Lista todas las cuentas de ahorro de un determinado cliente
         */
	public List<SavingAccount> listarCuentasAhorroCliente(Customer cliente){
		return savingAccountRemote.listarCuentasAhorroCliente(cliente);
		
	}
	
	/**
	 * Consignar
	 */
	public void consignar(Double monto,SavingAccount s){
		savingAccountRemote.consignar(monto,s);
	}
	
	/*
	 * Retirar 
	 */
	public void retirar(Double monto,SavingAccount s){
		savingAccountRemote.retirar(monto,s);
	}
	
	/**
	 * Transferir
	 */
	public void transferir(Double monto,SavingAccount s1,SavingAccount s2){
		savingAccountRemote.transferir(monto,s1,s2);
	}
}
