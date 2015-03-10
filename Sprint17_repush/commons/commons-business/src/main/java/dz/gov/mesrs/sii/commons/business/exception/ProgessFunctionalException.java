package dz.gov.mesrs.sii.commons.business.exception;

public abstract class ProgessFunctionalException extends Exception {

	private static final long serialVersionUID = -1139549171673620609L;

	public ProgessFunctionalException() {
		super();
	}

	public ProgessFunctionalException(String msg) {
		super(msg);
	}

	public ProgessFunctionalException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
