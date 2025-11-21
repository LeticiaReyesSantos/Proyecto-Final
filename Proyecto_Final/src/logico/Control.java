package logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Control implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static Control control;
	private ArrayList<Persona> personas;
	private static Persona personaLogueada;

	
	public Control() {
		super();
		personas = new ArrayList<>();
	}
	
	public static Control getInstance() {
		if(control == null)
			control = new Control();
		return control;
	}

	public ArrayList<Persona> getMisUsers() {
		return personas;
	}

	public void regUser(Persona aux) {
		personas.add(aux);
	}

	public static Control getControl() {
		return control;
	}

	public static void setControl(Control control) {
		Control.control = control;
	}

	public static Persona getLoginUser() {
		return personaLogueada;
	}

	public static void setLoginUser(Persona personaLogueada) {
		Control.personaLogueada = personaLogueada;
	}
	
	public boolean confirmarLogin(String usuario, String pass) {
		boolean valido = false;
		int i = 0;
		
		while(i<personas.size() && !valido) {
			User user = personas.get(i).getUser();
			if(user.getUserName().equals(usuario) && user.getPass().equals(pass)) {
				personaLogueada = personas.get(i);
				valido = true;
			}
			
			i++;
		}
		
	return valido;
	}
	
	public String genAutoPassword() {
		
	    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	    StringBuilder pass = new StringBuilder();

	    for(int i = 0; i < 8; i++) {
	        int rnd = (int)(Math.random() * chars.length());
	        pass.append(chars.charAt(rnd));
	    }

	    return pass.toString();

	}
}
