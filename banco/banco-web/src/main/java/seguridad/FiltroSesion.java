package seguridad;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omnifaces.util.Faces;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import controladores.SessionController;

@WebFilter(urlPatterns="/paginas/seguro/*")
public class FiltroSesion implements Filter {

	@Inject
	private SessionController sesion;
	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("Filtrando Peticion...");
		
		HttpServletRequest req=(HttpServletRequest) arg0;
		HttpServletResponse  res = (HttpServletResponse) arg1;
		
		Customer cliente = sesion.getCliente();
		System.out.println("Usuario: "+cliente);
		
		if(cliente != null){ 
			chain.doFilter(arg0, arg1);
		}else{
			res.sendRedirect(req.getContextPath()+"/paginas/publico/login.xhtml");
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
