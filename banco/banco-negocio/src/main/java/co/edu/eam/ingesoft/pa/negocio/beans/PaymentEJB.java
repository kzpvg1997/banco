package co.edu.eam.ingesoft.pa.negocio.beans;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.crypto.CipherOutputStream;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCardConsume;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCardPaymentConsume;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CustomerPK;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.IPaymentRemote;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@LocalBean
@Stateless
@Remote(IPaymentRemote.class)
public class PaymentEJB {

	/* Guardo el iva en una constante, para luego modificar mas facil */
	static final double iva = 0.036;

	public static double getIva() {

		return iva;
	}

	@PersistenceContext
	private EntityManager em;

	@EJB
	private CreditCardConsumeEJB consumoEJB;

	@EJB
	private CreditCardEJB cardEJB;

	/**
	 * Lista los pagos de un determinado consumo
	 * 
	 * @param c
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<CreditCardPaymentConsume> listarPagosConsumo(CreditCardConsume c) {
		Query q = em.createNamedQuery(CreditCardPaymentConsume.listarPagosConsumo);
		q.setParameter(1, c);
		List<CreditCardPaymentConsume> lista = q.getResultList();
		return lista;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public CreditCardPaymentConsume buscarPago(int id) {

		CreditCardPaymentConsume pag = em.find(CreditCardPaymentConsume.class, id);
		return pag;
	}

	/**
	 * Calcula la cuota con respecto a un consumo
	 */
	public int numeroCuotaRestante(CreditCardConsume consumo) {
		int cuota = consumo.getNumberShares() - listarPagosConsumo(consumo).size();
		return cuota;
	}

	/**
	 * Calcula el capital de una cuota
	 */
	public double capitalCuota(CreditCardConsume consumo) {
		double valor = consumo.getAmmount();
		return valor;
	}

	/**
	 * Calcula el interes de una cuota con respecto a un consumo
	 */
	public double interesCuota(CreditCardConsume consumo) {
		double capital = capitalCuota(consumo);
		double interes = capital * iva;
		return interes;
	}

	/**
	 * Calcula el valor de la cuota a pagar
	 */
	public double calcularValorCuota(CreditCardConsume consumo) {
		double monto = capitalCuota(consumo);
		double interes = interesCuota(consumo);
		double operacion = monto + interes / consumo.getNumberShares();
		return operacion;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void pagarTarjeta(CreditCard tarjeta) {

		double valorTotal = consumoEJB.sumaConsumosTarjetaCuotas(tarjeta);
		double vdescontar = 0.0;
		double vSumarTarjeta = calcularValorTotalCuotas(tarjeta);

		if (valorTotal == 0) {
			throw new ExcepcionNegocio("Usted ya tiene todos los consumos de esta tarjeta pagos.");
		} else {

			List<CreditCardConsume> conNoPago = consumoEJB.consumosTarjetaNoPagos(tarjeta);
			for (CreditCardConsume conNoPag : conNoPago) {

				if (!conNoPag.isPayed()) {

					vdescontar = conNoPag.getValorCuota();
					conNoPag.setRemaningShares(conNoPag.getRemaningShares() - 1);
					conNoPag.setRemainingAmmount(conNoPag.getRemainingAmmount() - vdescontar);
					em.merge(conNoPag);

					CreditCard tarj = cardEJB.buscarCreditCard(conNoPag.getCreditCard().getNumber());
					tarj.setMonto(tarj.getMonto() + vSumarTarjeta);
					em.merge(tarj);

					CreditCardPaymentConsume pay = new CreditCardPaymentConsume();
					pay.setAmmount(valorTotal);
					pay.setCapitalAmmount(vdescontar);
					pay.setInterestAmmount(valorTotal * 0.036 + valorTotal);
					pay.setPaymentDate(consumoEJB.fechaActual());
					pay.setIdConsume(conNoPag);
					pay.setShare(conNoPag.getRemaningShares());

					em.persist(pay);

					if (conNoPag.getRemainingAmmount() <= 0.0 || conNoPag.getRemaningShares() == 0) {
						conNoPag.setPayed(true);
						conNoPag.setRemainingAmmount(0);
						conNoPag.setValorCuota(0);
						em.merge(conNoPag);
					}
				}

			}
		}
	}

	/**
	 * determina si pagar consumo o consumos
	 */
	public void pagar(CreditCardConsume consumo, double avance) {
		double vcuota = calcularValorCuota(consumo);
		if (avance == 0 || avance == vcuota) {
			pagarConsumo(consumo);
		} else {
			if (avance < 0 || avance < vcuota) {
				throw new ExcepcionNegocio(
						"El avance debe ser mayor al monto del consumo\nNo puede ser negativo. su cuota es de "
								+ vcuota);
			} else if (avance > vcuota) {
				double consumosTotal = consumoEJB.consumosTotalTarjetaCredito(consumo.getCreditCard());
				double deuda = consumosTotal + interesCuota(consumo);
				if (avance > deuda) {
					throw new ExcepcionNegocio("Su avance supera el total de todos sus consumos, que es " + deuda);
				} else {
					// aqui es donde se debe verificar cuantos consumos tiene y
					// como se va a repartir
					pagarConsumo(consumo);
					double restante = avance - vcuota; // es lo que queda del
														// avance luego de pagar
														// la cuota
					avances(consumo, restante);

					/* Actualizo el saldo de la tarjeta */
					double capital = capitalCuota(consumo);
					double saldo = consumo.getCreditCard().getMonto() + restante + capital;
					CreditCard tarjeta = cardEJB.buscarCreditCard(consumo.getCreditCard().getNumber());
					tarjeta.setMonto(saldo);
					em.merge(tarjeta);
				}
			}
		}
	}

	/* paga un consumo */
	public void pagarConsumo(CreditCardConsume consumo) {
		if (consumo.getRemainingAmmount() == 0) {
			throw new ExcepcionNegocio("En horabuena usted ya pago este consumo");
		} else {
			int cuota = numeroCuotaRestante(consumo);
			double valor = capitalCuota(consumo);
			double interes = interesCuota(consumo);
			CreditCardPaymentConsume pago = new CreditCardPaymentConsume();
			pago.setShare(cuota);
			pago.setPaymentDate(cardEJB.fechaExpedicion());
			pago.setAmmount(valor + interes);
			pago.setCapitalAmmount(valor);
			pago.setIdConsume(consumo);
			pago.setInterestAmmount(interes);
			em.persist(pago); // Guardar

			/* Actualizamos la informacion del consumo */
			if (cuota - 1 == 0) {
				consumo.setPayed(true);
			}
			consumo.setRemainingAmmount(consumo.getRemainingAmmount() - valor);
			consumoEJB.actualizarConsumo(consumo);

			/* actualizar el monto de la tarjeta de credito */
			consumo.getCreditCard().setMonto(consumo.getCreditCard().getMonto() + valor);
			cardEJB.actualizar(consumo.getCreditCard());
		}
	}

	/* Hacer avances a consumos */
	public void avances(CreditCardConsume consumo, double restante) {
		List<CreditCardConsume> consumos = consumoEJB.consumosTarjetaNoPagos(consumo.getCreditCard());
		double avancePago = restante / consumos.size();
		double residuo = 0; // es lo que quedara despues de abonar a todos los
							// consumos

		for (CreditCardConsume c : consumos) {
			double monto = 0;
			/* verifico si el avance es mayor o igual a lo que se debe */
			if (avancePago >= c.getRemainingAmmount()) {
				residuo = avancePago - c.getRemainingAmmount(); // va asignando
																// el restande a
																// la variable
																// $residuo
				c.setRemainingAmmount(c.getRemainingAmmount() - c.getRemainingAmmount()); // descuento
																							// el
																							// monto
																							// restante
				monto = c.getRemainingAmmount();
			} else {
				c.setRemainingAmmount(avancePago);
				monto = avancePago;
			}
			/*
			 * Si el monto restante es 0 entonces cambio el estado a true =
			 * pagado
			 */
			if (c.getRemainingAmmount() == 0) {
				c.setPayed(true);
			}
			consumoEJB.actualizarConsumo(c); // actualizo el consumo

			CreditCard tarj = cardEJB.buscarCreditCard(c.getCreditCard().getNumber());
			tarj.setMonto(tarj.getMonto() + monto);
			em.merge(tarj);
		}
		if (residuo != 0) {
			avances(consumo, residuo);
		}
	}

	public double calcularValorTotalCuotas(CreditCard tarjeta) {
		double total = 0.0;
		List<CreditCardConsume> lista = consumoEJB.consumosTarjetaNoPagos(tarjeta);
		for (CreditCardConsume cmo : lista) {
			total += cmo.getValorCuota();
		}
		return total;
	}

	public void pagarAvanceTarjeta(CreditCardConsume consumo, double valorAvance) {

		List<CreditCardConsume> consumos = consumoEJB.consumosTarjetaNoPagos(consumo.getCreditCard());
		double avancePago = valorAvance / consumos.size();
		double residuo = 0; // es lo que quedara despues de abonar a todos los
							// consumos

		for (CreditCardConsume c : consumos) {
			double monto = 0;
			/* verifico si el avance es mayor o igual a lo que se debe */
			if (avancePago >= c.getRemainingAmmount()) {
				residuo = avancePago - c.getRemainingAmmount(); // va asignando
																// el restande a
																// la variable
																// $residuo
				c.setRemainingAmmount(c.getRemainingAmmount() - c.getRemainingAmmount()); // descuento
																							// el
																							// monto
																							// restante
				monto = c.getRemainingAmmount();
			} else {

				throw new ExcepcionNegocio("El monto asignado");
				// c.setRemainingAmmount(avancePago);
				// monto = avancePago;
			}
			/*
			 * Si el monto restante es 0 entonces cambio el estado a true =
			 * pagado
			 */
			if (c.getRemainingAmmount() == 0) {
				c.setPayed(true);
			}
			consumoEJB.actualizarConsumo(c); // actualizo el consumo

			CreditCard tarj = cardEJB.buscarCreditCard(c.getCreditCard().getNumber());
			tarj.setMonto(tarj.getMonto() + monto);
			em.merge(tarj);
		}
		if (residuo != 0) {
			avances(consumo, residuo);
		}
	}

}
