/**
 * 
 */
package co.edu.eam.ingesoft.pa.negocio.DTO;

/**
 * @author TOSHIBAP55W
 *
 */
public class CuentaAsociadaDTO {

	private String nombreAsociado;

	private String tipoIdAsociado;

	private String numeroIdAsociado;

	private String bank;

	private String tipoIdCliente;

	private String idCliente;

	private String numeroCuenta;

	private String nombreCuenta;

	private String estado;

	public CuentaAsociadaDTO() {
		this.estado = "No verificada";
	}

	/**
	 * @return the nombreAsociado
	 */
	public String getNombreAsociado() {
		return nombreAsociado;
	}

	/**
	 * @param nombreAsociado the nombreAsociado to set
	 */
	public void setNombreAsociado(String nombreAsociado) {
		this.nombreAsociado = nombreAsociado;
	}

	/**
	 * @return the tipoIdAsociado
	 */
	public String getTipoIdAsociado() {
		return tipoIdAsociado;
	}

	/**
	 * @param tipoIdAsociado the tipoIdAsociado to set
	 */
	public void setTipoIdAsociado(String tipoIdAsociado) {
		this.tipoIdAsociado = tipoIdAsociado;
	}

	/**
	 * @return the numeroIdAsociado
	 */
	public String getNumeroIdAsociado() {
		return numeroIdAsociado;
	}

	/**
	 * @param numeroIdAsociado the numeroIdAsociado to set
	 */
	public void setNumeroIdAsociado(String numeroIdAsociado) {
		this.numeroIdAsociado = numeroIdAsociado;
	}

	/**
	 * @return the bank
	 */
	public String getBank() {
		return bank;
	}

	/**
	 * @param bank the bank to set
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}

	/**
	 * @return the tipoIdCliente
	 */
	public String getTipoIdCliente() {
		return tipoIdCliente;
	}

	/**
	 * @param tipoIdCliente the tipoIdCliente to set
	 */
	public void setTipoIdCliente(String tipoIdCliente) {
		this.tipoIdCliente = tipoIdCliente;
	}

	/**
	 * @return the idCliente
	 */
	public String getIdCliente() {
		return idCliente;
	}

	/**
	 * @param idCliente the idCliente to set
	 */
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * @param numeroCuenta the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * @return the nombreCuenta
	 */
	public String getNombreCuenta() {
		return nombreCuenta;
	}

	/**
	 * @param nombreCuenta the nombreCuenta to set
	 */
	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	

}
