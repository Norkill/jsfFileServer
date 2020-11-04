package jsfFileserver.service;

import javax.ejb.Stateless;

import jsfFileserver.model.User;

@Stateless 
public class UserService {

	public boolean checkCredentials(String username, String pwd) {
		if(username == null || pwd == null)
			return false;
		
		if(username.equals("admin") && pwd.equals("pass"))
			return true;
		else
			return false;
	}
	
	public User retriveUserFromDB() {
		return null;
	}
	
	public void updateUser(User user) {
		
	}
	
}
