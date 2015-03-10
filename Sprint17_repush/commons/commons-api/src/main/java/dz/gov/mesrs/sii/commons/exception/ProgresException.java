package dz.gov.mesrs.sii.commons.exception;

import java.util.ResourceBundle;

/**
 * Represente la classe exception de base pour SII. T outes les exceptions
 * personnalises deefinis pour le systeme devraient etendre cette classe.
 * 
 * @author Mounir.MESSAOUDI
 */

public abstract class ProgresException extends Exception {

	/**
	 * Class serial id.
	 */
	private static final long serialVersionUID = -230773364309409472L;

	private static final String PROGRES_ERROR_MESSAGE_FILE_NAME = "progresErrorMessages";

	private ResourceBundle errorMessageBundle;

	private String errorCode;

	public ProgresException() {

		super();
	}

	public ProgresException(Throwable cause) {
		super(cause);
	}

	public ProgresException(String errCode) {
		super();
		this.errorCode = errCode;
	}

	public ProgresException(String errCode, Throwable cause) {
		super(cause);
		this.errorCode = errCode;
	}

	public ProgresException(String strErrorMsg, String errCode, Throwable cause) {
		super(strErrorMsg, cause);
		this.errorCode = errCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errCode) {
		this.errorCode = errCode;
	}

	@Override
	public String getLocalizedMessage() {
		if (this.errorCode == null || this.errorCode.isEmpty()) {
			return getMessage();
		}
		StringBuffer message = new StringBuffer(getErrorMessage(errorCode));
		message.append(getMessage());
		return message.toString();
	}

	public String getErrorMessage(String errorKey) {
		getErrorMessageBundle();
		if (this.errorMessageBundle.containsKey(errorKey)) {
			return this.errorMessageBundle.getString(errorKey);
		}
		return null;
	}

	private ResourceBundle getErrorMessageBundle() {
		this.errorMessageBundle = ResourceBundle.getBundle("locale/" + PROGRES_ERROR_MESSAGE_FILE_NAME);
		return errorMessageBundle;
	}
}
