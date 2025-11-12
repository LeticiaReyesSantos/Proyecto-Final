package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Control implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<User> misUsers;
	private static Control control;
	private static User loginUser;
	
	public Control() {
		super();
		misUsers = new ArrayList<>();
	}
	
	public static Control getInstance() {
		if(control == null)
			control = new Control();
		return control;
	}

	public ArrayList<User> getMisUsers() {
		return misUsers;
	}

	public void regUser(User aux) {
		misUsers.add(aux);
	}

	public static Control getControl() {
		return control;
	}

	public static void setControl(Control control) {
		Control.control = control;
	}

	public static User getLoginUser() {
		return loginUser;
	}

	public static void setLoginUser(User loginUser) {
		Control.loginUser = loginUser;
	}
	
	public boolean confirmarLogin(String usuario, String pass) {
		boolean valido = false;
		int i = 0;
		
		while(i<misUsers.size() && !valido) {
			if(misUsers.get(i).getUserName().equals(usuario) && misUsers.get(i).getPass().equals(pass)) {
				loginUser = misUsers.get(i);
				valido = true;
			}
			
			i++;
		}
		
	return valido;
	}
}
