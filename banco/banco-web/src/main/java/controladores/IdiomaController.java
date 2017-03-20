package controladores;

import java.util.Locale;

import javax.inject.Named;

import org.omnifaces.util.Faces;

@Named("idiomaController")
public class IdiomaController {
	
	public void cambiarIngles(){
		Faces.getViewRoot().setLocale(Locale.ENGLISH);
	}
	
	public void cambiarEspañol(){
		Faces.getViewRoot().setLocale(Locale.ROOT);
	}

}
