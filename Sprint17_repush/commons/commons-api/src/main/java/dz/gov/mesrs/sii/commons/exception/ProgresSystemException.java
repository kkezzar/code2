package dz.gov.mesrs.sii.commons.exception;

/**
 * Represente une classe d'exception personnalisee pour les exceptions
 * specifiques du systeme.
 * 
 * @author Mounir.MESSAOUDI
 * 
 */
public class ProgresSystemException extends ProgresException {

	/**
	 * Class serial id.
	 */
	private static final long serialVersionUID = -4556451012987848066L;

	public ProgresSystemException() {
		super();
	}

	public String getLocalizedMessage() {
		return super.getMessage();
	}
}
