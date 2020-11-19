package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "User") @Table(name = "User")
public class User {


	@Column @Id @GeneratedValue
	private int userId;
	
	@Column(unique=true) @NotNull @Size(min=7, max=20)
	private String username;
	
	@Column @NotNull @Size(min=7, max=20) 
	private String pwd;
	
	@OneToOne 
	@JoinTable(name = "userRootDirectory", 
				joinColumns = {
						@JoinColumn(name = "userId", referencedColumnName = "userId")
				},
				inverseJoinColumns = {
						@JoinColumn(name = "directoryId", referencedColumnName = "uploadId")
				})
	private Directory rootDirectory;

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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Directory getUserRootDirectory() {
		return rootDirectory;
	}
	public void setRootDirectory(Directory rootDirectory) {
		this.rootDirectory = rootDirectory;
	}
	
	
}
