package co.edu.eam.ingesoft.pa.negocio.beans.remote;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import java.util.Date;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SavingAccount;
import java.util.List;

/**
 * Interface remota para acceder a las operaciones del EJB.
 * @author GART
 *
 */

public interface ISavingAccountRemote {

	public void crearCuentaAhorro(SavingAccount cuenta);
	public SavingAccount buscarCuentaAhorro(String num);
        public List<SavingAccount> listarCuentasAhorroCliente(Customer cliente);
        public int contarProductosCliente(Customer cliente);
        public void consignar(Double monto,SavingAccount s);
        public void retirar(Double monto,SavingAccount s);
        /**
         * transferir
         */
        public void transferir(Double monto,SavingAccount s1,SavingAccount s2);
}
