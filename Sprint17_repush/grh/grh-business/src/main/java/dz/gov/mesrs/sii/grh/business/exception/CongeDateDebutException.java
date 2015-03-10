package dz.gov.mesrs.sii.grh.business.exception;

import dz.gov.mesrs.sii.commons.business.exception.ProgessFunctionalException;

public class CongeDateDebutException extends ProgessFunctionalException {

	private static final long serialVersionUID = 6059032201377972183L;
	
	public CongeDateDebutException() {
		super();
	}

	public CongeDateDebutException(String msg) {
		super(msg);
	}

	public CongeDateDebutException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
