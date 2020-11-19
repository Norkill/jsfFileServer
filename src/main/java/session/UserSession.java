package session;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import model.Directory;
import model.User;

@Named("userSessionBean") @SessionScoped
public class UserSession implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private User user;
	private Directory currentDir;


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Directory getCurrentDir() {
		return currentDir;
	}

	public void setCurrentDir(Directory currentDir) {
		this.currentDir = currentDir;
	}
	
	
}
