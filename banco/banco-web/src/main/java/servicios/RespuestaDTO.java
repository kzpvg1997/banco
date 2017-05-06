package servicios;

public class RespuestaDTO {

	
	private String mensaje;
	private int codigo;
	private Object obj;
	
	
	/**
	 * @param mensaje
	 * @param codigo
	 * @param objeto
	 */
	public RespuestaDTO(String mensaje, int codigo, Object obj) {
		super();
		this.mensaje = mensaje;
		this.codigo = codigo;
		this.obj = obj;
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
	public Object getObj() {
		return obj;
	}
	/**
	 * @param objeto the objeto to set
	 */
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	
}
