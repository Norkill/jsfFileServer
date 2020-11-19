package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Table(name = "Upload") @Entity(name = "Upload") @Inheritance(strategy = InheritanceType.JOINED)
public abstract class Upload {

	@Column @Id @GeneratedValue
	protected int uploadId;
	@ManyToOne(optional = false)
	protected User user;
	@Column(nullable = false) @Size(min=1, max=30)
	protected String name;
	@ManyToOne
	protected Directory directory;
	
	
	
	public int getUploadId() {
		return uploadId;
	}
	public void setUploadId(int uploadId) {
		this.uploadId = uploadId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		//TODO check if valid filename not */?...
		this.name = name;
	}
	public Directory getDirectory() {
		return directory;
	}
	public void setDirectory(Directory parent) {
		this.directory = parent;
	}
	
}
