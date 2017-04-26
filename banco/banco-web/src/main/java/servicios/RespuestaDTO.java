package servicios;

public class RespuestaDTO {

	
	private String mensaje;
	private int codigo;
	private Object objeto;
	
	
	/**
	 * @param mensaje
	 * @param codigo
	 * @param objeto
	 */
	public RespuestaDTO(String mensaje, int codigo, Object objeto) {
		super();
		this.mensaje = mensaje;
		this.codigo = codigo;
		this.objeto = objeto;
	}
	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}
	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the objeto
	 */
	public Object getObjeto() {
		return objeto;
	}
	/**
	 * @param objeto the objeto to set
	 */
	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}
	
	
}
