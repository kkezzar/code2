package dz.gov.mesrs.sii.grh.business.exception;

import dz.gov.mesrs.sii.commons.business.exception.ProgessFunctionalException;

public class CongeNbJourException extends ProgessFunctionalException {

	private static final long serialVersionUID = 6059032201377972183L;
	
	public CongeNbJourException() {
		super();
	}

	public CongeNbJourException(String msg) {
		super(msg);
	}

	public CongeNbJourException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
