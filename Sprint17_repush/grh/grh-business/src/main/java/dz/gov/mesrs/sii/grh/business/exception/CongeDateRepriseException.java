package dz.gov.mesrs.sii.grh.business.exception;

import dz.gov.mesrs.sii.commons.business.exception.ProgessFunctionalException;

public class CongeDateRepriseException extends ProgessFunctionalException {

	private static final long serialVersionUID = 6059032201377972183L;
	
	public CongeDateRepriseException() {
		super();
	}

	public CongeDateRepriseException(String msg) {
		super(msg);
	}

	public CongeDateRepriseException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
