package dz.gov.mesrs.sii.commons.data.model;

import java.io.Serializable;

public interface Identifiable<T extends Serializable>{

	T getIdenfiant();
	
	String getIdentifiantName();
}
