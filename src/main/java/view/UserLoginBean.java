package view;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.User;
import service.UserService;
import session.UserSession;

@Named("userLoginBean") @RequestScoped
public class UserLoginBean {

	private String username;
	private String pwd;
	
	@Inject
	private UserService userService;
	//@Inject
	//private FileService fileService;

	@Inject
	private UserSession session;
	
	

	public String login() {
		
		//Retrieve User from DB
		User user = userService.checkCredentials(username, pwd);
		//User not found
		if(user == null) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect username or password", 
							"Please enter correct username and password"));
			return "fail";
		}
		//User found, set in session
		session.setUser(user);
		//Set current dir to base dir
		session.setCurrentDir(session.getUser().getUserRootDirectory());
		return "success";		
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	
	
	
}
