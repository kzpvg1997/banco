/**
 * 
 */
package co.edu.eam.ingesoft.pa.negocio.DTO;

/**
 * @author TOSHIBAP55W
 *
 */
public class LoginDTO {

	
	private String user;
	
	private String password;

	
	public LoginDTO() {
		super();
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
