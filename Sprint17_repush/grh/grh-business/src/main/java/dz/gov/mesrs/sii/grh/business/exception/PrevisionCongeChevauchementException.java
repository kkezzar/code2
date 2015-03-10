package dz.gov.mesrs.sii.grh.business.exception;

import dz.gov.mesrs.sii.commons.business.exception.ProgessFunctionalException;

public class PrevisionCongeChevauchementException extends ProgessFunctionalException {

	private static final long serialVersionUID = 6059032201377972183L;
	
	public PrevisionCongeChevauchementException() {
		super();
	}

	public PrevisionCongeChevauchementException(String msg) {
		super(msg);
	}

	public PrevisionCongeChevauchementException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
