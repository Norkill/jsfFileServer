package view;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import service.FileService;

@Named("fileUploadBean") @RequestScoped
public class FileUploadBean {

	@Inject
	private FileService fs;
	
	private Part file;
	
	public String save() {
		fs.upload(file);
		return null;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}
	
	
	
}
