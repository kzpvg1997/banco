/**
 * 
 */
package co.edu.eam.ingesoft.pa.negocio.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.ws.BindingProvider;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Bank;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CuentaAsociados;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SavingAccount;
import co.edu.eam.pa.interbancariows.Banco;
import co.edu.eam.pa.interbancariows.InterbancarioWS;
import co.edu.eam.pa.interbancariows.InterbancarioWS_Service;
import co.edu.eam.pa.interbancariows.ListarBancos;
import co.edu.eam.pa.interbancariows.ListarBancosResponse;
import co.edu.eam.pa.interbancariows.RegistrarCuentaAsociada;
import co.edu.eam.pa.interbancariows.RespuestaServicio;
import co.edu.eam.pa.interbancariows.TipoDocumentoEnum;
import co.edu.eam.pa.interbancariows.TransferirMonto;

/**
 * @author TOSHIBAP55W Clase encargada de los servicios del servidor
 *
 */
@LocalBean
@Stateless
public class ServiciosServidor implements Serializable {

	@PersistenceContext
	private EntityManager em;
	
	@EJB
	private SavingAccountEJB savEJB;

	@EJB
	private CuentaAsociadosEJB asoEJB;

	private TipoDocumentoEnum tipo;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void transferirMonto(String numeroCuentaAhorros,String idBanco, String numeroCuentaAso, double monto) {

		InterbancarioWS_Service cliente = new InterbancarioWS_Service();
		InterbancarioWS servicio = cliente.getInterbancarioWSPort();

		String endpoint = "http://104.155.128.249:8080/interbancario/InterbancarioWS/InterbancarioWS";

		BindingProvider bp = (BindingProvider) servicio;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);

		TransferirMonto tran = new TransferirMonto();
		tran.setIdbanco(idBanco);
		tran.setNumerocuenta(numeroCuentaAso);
		tran.setMonto(monto);

		RespuestaServicio resp = servicio.transferirMonto(idBanco, numeroCuentaAso, monto);
		if (resp.getCodigo().equals("0000")) {// Si la trasnferencia es exitosa
			System.out.println("Transferencia Exitosa");
			SavingAccount cuentaAhorros = savEJB.buscarCuentaAhorro(numeroCuentaAhorros);
			CuentaAsociados cuentaAsociada =asoEJB.buscarCuentaAsociado(numeroCuentaAso);
			savEJB.transferirCuentaAsociados(monto, cuentaAhorros, cuentaAsociada);
			resp.getMensaje();
		} else {
			if (resp.getCodigo().equals("0002")) {// si la cuenta aun no esta
													// asociada
				System.out.println("la cuenta aun no esta asociada");
				resp.getMensaje();
			} else {
				if (resp.getCodigo().equals("0002")) {// si la Cuenta no existe
														// en el banco de
					System.out.println("la Cuenta no existe en el banco de destino");
					resp.getMensaje();
				}
			}
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void verificarCuenta(CuentaAsociados cuenta) {
		InterbancarioWS_Service cliente = new InterbancarioWS_Service();
		InterbancarioWS servicio = cliente.getInterbancarioWSPort();

		String endpointURL = "http://104.155.128.249:8080/interbancario/InterbancarioWS/InterbancarioWS";
		BindingProvider bp = (BindingProvider) servicio;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);

		RegistrarCuentaAsociada ca = new RegistrarCuentaAsociada();
		// Id banco
		ca.setIdbanco(cuenta.getBanco().getId());
		// Tipo de documento
		TipoDocumentoEnum tipoDoc = null;
		tipoDoc = tipoDoc.fromValue(cuenta.getTipoID());
		ca.setTipodoc(tipoDoc);
		// numero documento
		ca.setNumerodoc(cuenta.getIdAsociado());
		// nombre
		ca.setNombre(cuenta.getNombreAsociacion());
		// numero cuenta
		ca.setNumerocuenta(cuenta.getNumeroCuenta());

		RespuestaServicio resp = servicio.registrarCuentaAsociada(ca.getIdbanco(), ca.getTipodoc(), ca.getNumerodoc(),
				ca.getNombre(), ca.getNumerocuenta());

		System.out.println(resp.getMensaje());

		if (resp.getMensaje().equalsIgnoreCase("NO_VALIDA")) {
			cuenta.setEstado(resp.getMensaje());
			em.merge(cuenta);
			System.out.println(cuenta.getNumeroCuenta());
			asoEJB.borrarCuentaAsociado(cuenta);
		} else {
			cuenta.setEstado(resp.getMensaje());

			em.merge(cuenta);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void asociarCuenta(String idbanco, String tipodoc, String numerodoc, String nombreAsociado,
			String numerocuenta) {

		InterbancarioWS_Service cliente = new InterbancarioWS_Service();
		InterbancarioWS servicio = cliente.getInterbancarioWSPort();

		String endpoint = "http://104.155.128.249:8080/interbancario/InterbancarioWS/InterbancarioWS";

		BindingProvider bp = (BindingProvider) servicio;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);

		RegistrarCuentaAsociada cai = new RegistrarCuentaAsociada();
		cai.setIdbanco(idbanco);
		cai.setNombre(nombreAsociado);
		cai.setNumerocuenta(numerocuenta);
		cai.setNumerodoc(numerodoc);
		if (tipodoc.equals("CC")) {
			tipo = TipoDocumentoEnum.CC;
		} else if (tipodoc.equals("TI")) {
			tipo = TipoDocumentoEnum.TI;
		} else if (tipodoc.equals("PAS")) {
			tipo = TipoDocumentoEnum.PAS;
		} else if (tipodoc.equals("CE")) {
			tipo = TipoDocumentoEnum.CE;
		}
		cai.setTipodoc(tipo);

		RespuestaServicio resp = servicio.registrarCuentaAsociada(idbanco, tipo, numerodoc, nombreAsociado,
				numerocuenta);

		if (resp.getCodigo().equals("0003")) { // si la cuenta es NO_VALIDA
			CuentaAsociados cu = asoEJB.buscarCuentaAsociado(numerocuenta);
			if (cu != null) {
				asoEJB.borrarCuentaAsociado(cu);

				resp.getMensaje();
				System.out.println("la cuenta es NO_VALIDA");
			} else {
				if (resp.getCodigo().equals("0010")) {// si el banco no existe
					asoEJB.borrarCuentaAsociado(cu);

					resp.getMensaje();
					System.out.println("el banco no existe");
				} else {
					if (resp.getCodigo().equals("0001")) {// si la cuenta esta
															// pendiente
						resp.getMensaje();
						CuentaAsociados cuen = asoEJB.buscarCuentaAsociado(numerocuenta);
						cuen.setEstado(resp.getMensaje());
						System.out.println("la cuenta esta pendiente");
					} else {
						if (resp.getCodigo().equals("0000")) {// si la cuenta
																// esta asociada
							CuentaAsociados cuen = asoEJB.buscarCuentaAsociado(numerocuenta);
							cuen.setEstado(resp.getMensaje());
							em.merge(cuen);

							resp.getMensaje();
							System.out.println("la cuenta esta asociada");
						}
					}
				}
			}
		}

	}

}
