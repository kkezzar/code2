package dz.gov.mesrs.sii.grh.business.exception;

import dz.gov.mesrs.sii.commons.business.exception.ProgessFunctionalException;

public class PrevisionCongeHorsPeriodeException extends ProgessFunctionalException {

	private static final long serialVersionUID = 6059032201377972183L;
	
	public PrevisionCongeHorsPeriodeException() {
		super();
	}

	public PrevisionCongeHorsPeriodeException(String msg) {
		super(msg);
	}

	public PrevisionCongeHorsPeriodeException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
