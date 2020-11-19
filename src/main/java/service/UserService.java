package service;

import java.io.File;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import model.Directory;
import model.User;

@Stateless 
public class UserService {

	@PersistenceContext
	private EntityManager em;
	
	
	public User checkCredentials(String username, String pwd) {
		if(username == null || pwd == null)
			return null;
		
		String query = "FROM User WHERE username = :username AND pwd = :pwd";
		Query q = em.createQuery(query).setParameter("username", username).setParameter("pwd", pwd);
		Object result = null;
		try {
			result = q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
			
		if(result != null) {
			return (User) result;
		}
		System.err.println("Something went wrong with credentials check");
		return null;
	}
	
	
	public void updateUser(User user) {
		
	}
	@Transactional
	public int registerUser(User user) {
		
		em.persist(user);
		Directory dir = new Directory();
		dir.setName(String.valueOf(user.getUserId()));
		dir.setUser(user);
		dir.setDirectory(null);
		//dir.addChild(dir);
		em.persist(dir);
		
		//Add base dir to user
		user.setRootDirectory(dir);
		em.flush();
		
		//Create a new Directory for files for that user
		String userDir = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("upload.location")
				+ dir.getName();
		File dirFS = new File(userDir);
		boolean success = dirFS.mkdir();
		if (success)
			System.out.println("Directory created");
		else {
			System.out.println("Directory can not be created");
			throw new RuntimeException("File creation error");
		}

		em.detach(user);
		em.detach(dir);
		
		return user.getUserId();
	}
	
}
