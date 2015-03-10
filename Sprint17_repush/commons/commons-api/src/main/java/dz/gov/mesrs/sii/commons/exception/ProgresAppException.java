package dz.gov.mesrs.sii.commons.exception;

/**
 * Represente une classe personnalisee d'exception pour les exceptions
 * specifiques a l'application.
 * 
 * @author Mounir.MESSAOUDI
 * 
 */
public class ProgresAppException extends ProgresException {

	/**
	 * Class serial id.
	 */
	private static final long serialVersionUID = -1004771925657146922L;

	public ProgresAppException() {
		super();
	}

	public ProgresAppException(String strErrorCode) {
		super(strErrorCode);
	}

	public ProgresAppException(Throwable cause) {
		super(cause);
	}

	public ProgresAppException(String strErrorCode, Throwable cause) {
		super(strErrorCode, cause);
	}

	public ProgresAppException(String strErrorMsg, String strErrorCode, Throwable cause) {
		super(strErrorMsg, strErrorCode, cause);
	}
}