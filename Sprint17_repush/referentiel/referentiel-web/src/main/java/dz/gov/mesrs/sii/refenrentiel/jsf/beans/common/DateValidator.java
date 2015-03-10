/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.periode.DateValidator.java] 
 * @author MAKERRI Sofiane on : 2 avr. 2014  17:54:41
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.common;

import java.util.Date;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane on : 2 avr. 2014 17:54:41
 */
@FacesValidator(value = "dateValidator")
public class DateValidator implements Validator {

	/**
	 * [DateValidator.DateValidator()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 2 avr. 2014 17:54:42
	 */
	public DateValidator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		UIInput sd = (UIInput) component.getAttributes().get("firstDate");
		Date firstDate = null;
		
		if (sd != null) {
			firstDate = (Date) sd.getValue();
		}
		Date secondDate = (Date) value;
		/*if (secondDate == null) {
			ResourceBundle bundle = context.getApplication().getResourceBundle(
					context, UtilConstant.COMMON_MESSAGES_FILE_NAME);
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_date_invalid"));
			throw new ValidatorException(msg);
		}*/
		if (secondDate != null &&firstDate != null && !firstDate.before(secondDate)) {
			ResourceBundle bundle = context.getApplication().getResourceBundle(
					context, RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_date_debut_fin"));
			// FacesContext.getCurrentInstance().addMessage(null, msg);

			throw new ValidatorException(msg);
		}

	}

}
