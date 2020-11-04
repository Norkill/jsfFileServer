package jsfFileserver.model;

import java.io.Serializable;


public class User implements Serializable {


	private static final long serialVersionUID = 1L;

	private String username;

	private String pwd;
	private int id;
	
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
