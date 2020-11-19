package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity(name = "Directory")
public class Directory extends Upload {

	@OneToMany
	private List<Upload> children = new ArrayList<Upload>();

	public List<Upload> getChildren() {
		return children;
	}

	public void setChildren(List<Upload> children) {
		this.children = children;
	}
	
	@Override
	public void setName(String name) {	
		if(!name.endsWith("/")) {
			this.name = name + "/";
		}
	}

	public void addChild(Upload child) {
		children.add(child);
	}
	
//	public Directory(String name, User user, Directory directory, List<Upload> children) {
//		this.name = name;
//		this.user = user;
//		this.directory = directory;
//	}
	
}
