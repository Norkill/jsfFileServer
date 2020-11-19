package view;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.User;
import service.UserService;

@Named("userRegisterBean") @RequestScoped
public class UserRegisterBean  {

	@Inject
	UserService userService;
	
	private User user = new User();
	
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}


	public String register() {
		int id = userService.registerUser(user);
		if(id != 0) {
			FacesMessage msg = new FacesMessage("Account creation successful, you can now log in from the main page");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "success";
		} else {
			FacesMessage msg = new FacesMessage("Something went wrong in the register process");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "fail";
		}
	}
	
}
