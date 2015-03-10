/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.utils.DateValidator.java] 
 * @author MAKERRI Sofiane on : 29 mai 2014  08:11:30
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.utils;

import java.util.Date;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;

/**
 * @author MAKERRI Sofiane  on : 29 mai 2014  08:11:30
 */
@FacesValidator(value = "dateValidator")
public class DateValidator implements Validator {

	/**
	 * [DateValidator.DateValidator()] Constructor
	 * @author MAKERRI Sofiane  on : 29 mai 2014  08:11:30	
	 */
	public DateValidator() {
		super();
	}

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		UIInput sd = (UIInput) component.getAttributes().get("firstDate");
		Date firstDate = null;
		
		if (sd != null) {
			firstDate = (Date) sd.getValue();
			/*if (firstDate == null) {
				ResourceBundle bundle = context.getApplication().getResourceBundle(
						context, CursusConstants.COMMON_BUNDLE_MSG_NAME);
				FacesMessage msg = new FacesMessage();
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundle.getString("error_echec") + ": "
						+ bundle.getString("error_date_invalid"));
				throw new ValidatorException(msg);
			}*/
		}
		Date secondDate = (Date) value;
		if (value != null &&  secondDate == null) {
			ResourceBundle bundle = context.getApplication().getResourceBundle(
					context, CursusConstants.COMMON_BUNDLE_MSG_NAME);
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_date_invalid"));
			throw new ValidatorException(msg);
		}
		if (secondDate != null &&firstDate != null && !firstDate.before(secondDate)) {
			ResourceBundle bundle = context.getApplication().getResourceBundle(
					context, CursusConstants.COMMON_BUNDLE_MSG_NAME);
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_date_debut_fin"));
					throw new ValidatorException(msg);
		}

 
		
	}

}
