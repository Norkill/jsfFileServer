package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("passwordEqualityValidator")
public class PasswordEqualityValidator implements Validator<String> {

	@Override
	public void validate(FacesContext context, UIComponent component, String pwd) throws ValidatorException {
		UIInput pwdConfirmComponent = (UIInput) component.getAttributes().get("confirm");
		String pwdConfirm = (String) pwdConfirmComponent.getSubmittedValue();
		
		if(!pwdConfirm.equals(pwd)) {
			FacesMessage message = new FacesMessage();
			message.setDetail("Both provided passwords must be equal");
			message.setSummary("Both provided passwords must be equal");
			message.setSeverity(FacesMessage.SEVERITY_WARN);
			throw new ValidatorException(message);
			
		}
	}

	
	
}
