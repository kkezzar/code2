package dz.gov.mesrs.sii.grh.business.exception;

import dz.gov.mesrs.sii.commons.business.exception.ProgessFunctionalException;

public class CongeDateDemandeException extends ProgessFunctionalException {

	private static final long serialVersionUID = 6059032201377972183L;
	public CongeDateDemandeException() {
		super();
	}

	public CongeDateDemandeException(String msg) {
		super(msg);
	}

	public CongeDateDemandeException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
