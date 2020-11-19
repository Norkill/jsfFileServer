package service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import model.Directory;
import model.UploadFile;
import model.User;
import session.UserSession;


@Stateless
public class FileService {

	@Inject 
	private UserSession session;
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Save a user input file on disk and in db
	 * @param part user input file
	 */
	@Transactional
	public void upload(Part part) {
		
		String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
		Directory currentDirectory = session.getCurrentDir();
		//check if file with that name exists
		
		int i = 1;
		 while(fileExists(filename, currentDirectory)) {
			 i++;
		 } 
		filename = filename + " (" + i + ")";
		
		User user = session.getUser();
		UploadFile uploadFile = new UploadFile();
		uploadFile.setName(filename);
		uploadFile.setSize(part.getSize());
		uploadFile.setUser(user);
		uploadFile.setDirectory(currentDirectory);
		em.persist(uploadFile);
		
		// locationInFS/BaseDirForUser/someDirsTillCurrentDir/CurrentDir/fileToUpload
		String location = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("upload.location"); // /home/norkill/FileServerFiles/
		
		
		// TODO check
		String pathToWrite = location + user.getUserRootDirectory().getName() + uploadFile.getUploadId();
		
		System.out.println("saving a file as " + pathToWrite);
		
			
		
		
		File file = new File(pathToWrite);
		
		try {
			InputStream is = part.getInputStream();
			Files.copy(is, file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("File Upload Failed");
		}
		

	}
	

	private boolean fileExists(String filename, Directory dir) {
		String query = "FROM UploadFile uf WHERE name = :name AND directory = :directory";
		Query q = em.createQuery(query).setParameter("name", filename).setParameter("directory", dir);
		
		try {
			q.getSingleResult();
		} catch (NoResultException e) {
			return false;
		}
		return true;
	}
	
	
	//Transactional???
//	public Directory getUserBaseDirectory(User user) {
//		int bDirId = user.getUserDirId();
//		
//		String query = "FROM Directory dir WHERE id = :id";
//		Query q = em.createQuery(query).setParameter("id", bDirId);
//		
//		return (Directory) q.getSingleResult();
//	}

}
