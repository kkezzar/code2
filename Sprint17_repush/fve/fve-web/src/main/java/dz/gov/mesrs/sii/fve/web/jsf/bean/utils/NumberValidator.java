/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.utils.NumberValidator.java] 
 * @author MAKERRI Sofiane on : 12 juin 2014  11:09:04
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.utils;

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
 * @author MAKERRI Sofiane on : 12 juin 2014 11:09:04
 */
@FacesValidator(value = "numberValidator")
public class NumberValidator implements Validator {

	/**
	 * [NumberValidator.NumberValidator()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 12 juin 2014 11:09:04
	 */
	public NumberValidator() {
		super();
	}

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		ResourceBundle bundle = context.getApplication().getResourceBundle(
				context, CursusConstants.COMMON_BUNDLE_MSG_NAME);
		UIInput min = (UIInput) component.getAttributes().get("min");

		Integer minValue = null;
		if (min != null) {
			Double doubleValue = (Double) min.getValue();
			if (doubleValue != null) {
				minValue = doubleValue.intValue();
				if (doubleValue.doubleValue() != doubleValue.intValue() || minValue == 0) {
					FacesMessage msg = new FacesMessage();
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec") + ": "
							+ bundle.getString("error_number_invalid"));
					throw new ValidatorException(msg);
				}
			}
		}
		Double doubleValue = (Double) value;
		Integer maxValue = null;
		if (doubleValue != null) {
			maxValue = doubleValue.intValue();
			if (value != null && (maxValue == null || doubleValue.doubleValue() != doubleValue.intValue() || maxValue == 0)) {

				FacesMessage msg = new FacesMessage();
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundle.getString("error_echec") + ": "
						+ bundle.getString("error_number_invalid"));
				throw new ValidatorException(msg);
			}
		}
		if (maxValue != null && min != null && minValue != null
				&& maxValue.intValue() < minValue.intValue()) {

			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_validator_min_max"));
			throw new ValidatorException(msg);
		}

	}

}
