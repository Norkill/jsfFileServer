package service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
			em.detach(result);
			return (User) result;
		}
		System.err.println("Something went wrong with credentials check");
		return null;
	}
	
	
	
	public void updateUser(User user) {
		
	}
	
}
