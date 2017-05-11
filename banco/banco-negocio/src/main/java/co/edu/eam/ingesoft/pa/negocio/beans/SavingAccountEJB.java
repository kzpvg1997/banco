package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CuentaAsociados;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Franchise;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Product;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SavingAccount;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Transaction;
import co.edu.eam.ingesoft.avanzada.persistencia.enumeraciones.TipoTransaccionEnum;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ISavingAccountRemote;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;


@LocalBean
@Stateless
@Remote(ISavingAccountRemote.class)
public class SavingAccountEJB {
	
	@PersistenceContext
	private EntityManager em;
	
	@EJB
	private CustomerEJB customerEJB;
	
	@EJB
	private MensajeEJB msjEJB;
		

	
	/**
	 * Metodo para crear una cuenta de ahorro
	 * @param cuenta que recibe como parametro
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearCuentaAhorro(SavingAccount cuenta){
		
		cuenta.setNumber(numeroAleatorio10());
		SavingAccount cu = buscarCuentaAhorro(cuenta.getNumber());
		if(cu==null){
			if(contarProductosCliente(cuenta.getCustomer()) < 5){
				Date fechaExpedicion = fechaExpedicion();
				cuenta.setExpeditionDate(fechaExpedicion);
				em.persist(cuenta);
			}else{
				throw new ExcepcionNegocio("El cliente con cedula=''"+cuenta.getCustomer().getIdNum()+"'' Ya tiene 5 prodctos registrados");
			}
		}else{
			throw new ExcepcionNegocio("Ya existe esta cuenta de ahorros");
		}
		
	}
	
	/**
	 * MEtodo para buscar una cuenta de ahorros
	 * @param num parametro que recibe para buscar
	 * @return una cuenta de ahorros
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public SavingAccount buscarCuentaAhorro(String num){
		return em.find(SavingAccount.class, num);
	}
	
	/**
	 * Metodo que captura la fecha del sistema
	 * @return la fecha actual
	 */
	public Date fechaExpedicion() {
		Calendar calendar = Calendar.getInstance();
		Date fecha = calendar.getTime();
		return fecha;
	}
	
	/**
	 * Metodo que me obtiene un numero aleatorio
	 * @return una caneda de 10 digitos
	 */
	
	public  String numeroAleatorio10(){
		int contador = 0;
		String numtotal = "";
		
	     while ( contador < 10 ) {
	    	 contador++;
	    	 int numero1 = ThreadLocalRandom.current().nextInt(0, 9 + 1);
	    	 String num = String.valueOf(numero1);
	    	 
	    	 numtotal += num ;
	    	
	     }
	    
		return numtotal;
		
	}
    /**
     * Lista todas las cuentas de ahorro de un determinado cliente
     */
	public List<SavingAccount> listarCuentasAhorroCliente(Customer cliente){
		Query q = em.createNamedQuery(SavingAccount.CUENTASAHORROCLIENTE);
		q.setParameter(1, cliente);
		List<SavingAccount> lista = q.getResultList();
		return lista;
	}
	
	/**
	 * Cuenta los productos de un determinado cliente
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int contarProductosCliente(Customer cliente){
		Query q = em.createNamedQuery(Product.ContarProductosCliente);
		q.setParameter(1, cliente);
		List<Product> lista = q.getResultList();
		return lista.size();
	}
	/**
	 *  Consignar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void consignar(Double monto,SavingAccount s){
		if(monto > 0.0){
			s.setAmmount(s.getAmmount()+monto);
			em.merge(s);
			Transaction tr = new Transaction();
			tr.setAmmount(monto);
			tr.setSavingAccNumber(s);
			tr.setTransactionDate(fechaExpedicion());
			tr.setSourceTransact(s.getNumber());
			tr.setTipoTransaccion(TipoTransaccionEnum.CONSIGANCION.toString());
			em.persist(tr);
		
			msjEJB.Sms("Se ha tranferido un monto de: "+monto,s.getCustomer().getTelefono());
			
		}else{
			throw new ExcepcionNegocio("Monto invalido");
		}
		
	}
	/**
	 * Retirar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void retirar(Double monto,SavingAccount s){
		if(monto > 0.0){
			if(s.getAmmount() >= monto){
				s.setAmmount(s.getAmmount()-monto);
				em.merge(s);
				Transaction tr = new Transaction();
				tr.setAmmount(monto);
				tr.setSavingAccNumber(s);
				tr.setTransactionDate(fechaExpedicion());
				tr.setSourceTransact(s.getNumber());
				tr.setTipoTransaccion(TipoTransaccionEnum.RETIRO.toString());
				em.persist(tr);
			}else{
				throw new ExcepcionNegocio("Saldo insuficiente para retirar");
			}
		}else{
			throw new ExcepcionNegocio("Monto invalido");
		}
		
	}
	/**
	 * Transfiere de una cuenta de ahorros de un cliente a una cuenta asociadas de este
	 * @param monto monto que se desea trasferir
	 * @param s ala cuenta de ahorros
	 * @param c la cuenta asociada
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void transferirCuentaAsociados(Double monto, SavingAccount s, CuentaAsociados c){
		if(monto > 0.0){
			if(s.getAmmount() >= monto){
				s.setAmmount(s.getAmmount()-monto);
				em.merge(s);//actualizamos la cuenta de ahorros
				em.merge(c);//actualizamos cuenta asociados
				Transaction tr = new Transaction();
				tr.setAmmount(monto);
				tr.setSavingAccNumber(s);
				tr.setTransactionDate(fechaExpedicion());
				tr.setSourceTransact(c.getNumeroCuenta());
				tr.setTipoTransaccion(TipoTransaccionEnum.TRANAFERENCIA_INTERBANCARIA.toString());
				em.persist(tr);
				
				msjEJB.Sms("Se ha tranferido un monto de: "+monto, s.getCustomer().getTelefono());
				
			}else{
				throw new ExcepcionNegocio("Saldo insuficiente para transferir \n Su saldo es de: "+s.getAmmount());
			}
		}else{
			throw new ExcepcionNegocio("Monto invalido");
		}
	}
	/**
	 * Transferir
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void transferir(Double monto,SavingAccount s1,SavingAccount s2){
		if(s1.getNumber() != s2.getNumber()){
			if(monto > 0.0){
				if(s1.getAmmount() >= monto){
					
					s1.setAmmount(s1.getAmmount()-monto);
					s2.setAmmount(s2.getAmmount()+monto);
					em.merge(s1);
					em.merge(s2);
					Transaction tr = new Transaction();
					tr.setAmmount(monto);
					tr.setSavingAccNumber(s2);
					tr.setTransactionDate(fechaExpedicion());
					tr.setSourceTransact(s1.getNumber());
					tr.setTipoTransaccion(TipoTransaccionEnum.TRANSFERENCIA.toString());
					em.persist(tr);
					
					
				}else{
					throw new ExcepcionNegocio("Saldo insuficiente para transferir");
				}
			}else{
				throw new ExcepcionNegocio("Monto invalido");
			}
		}else{
			throw new ExcepcionNegocio("No se puede transferir a la misma cuenta");
		}
	}
	
	public void actualizarCuenta(SavingAccount c){
		em.merge(c);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String verificarCuentaAhorros(String numeroCuenta, String cedula,String tipoId) {
		String tipo="";
		String estado = "ERROR";
		if(tipoId.equals("1")){
			tipo="C.C";
		}else if(tipoId.equals("2")){
			tipo="T.I";
		}
		SavingAccount c = buscarCuentaAhorro(numeroCuenta);
		Customer cus = customerEJB.buscarCustomer(tipo, cedula);
		if(cus!=null){
			if(c!=null){

				estado= "EXITO";
			}
			}
		return estado;
	}
}
