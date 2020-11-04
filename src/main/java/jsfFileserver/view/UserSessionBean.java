package jsfFileserver.view;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import jsfFileserver.model.User;
import jsfFileserver.service.UserService;

@Named("userSessionBean") @SessionScoped
public class UserSessionBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private User user;
	
	@Inject
	UserService userService;
	
	
	@PostConstruct
	public void init() {
		user = new User();
	}

	public String login() {
		
		boolean valid = userService.checkCredentials(user.getUsername(), user.getPwd());
		if(!valid) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect username or password", 
							"Please enter correct username and password"));
			return "fail";
		}
		return "loggedIn.xhtml";		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
