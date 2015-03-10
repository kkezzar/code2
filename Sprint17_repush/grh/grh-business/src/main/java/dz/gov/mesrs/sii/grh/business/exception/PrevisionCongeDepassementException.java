package dz.gov.mesrs.sii.grh.business.exception;

import dz.gov.mesrs.sii.commons.business.exception.ProgessFunctionalException;

public class PrevisionCongeDepassementException extends ProgessFunctionalException {

	private static final long serialVersionUID = 6059032201377972183L;
	
	public PrevisionCongeDepassementException() {
		super();
	}

	public PrevisionCongeDepassementException(String msg) {
		super(msg);
	}

	public PrevisionCongeDepassementException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
