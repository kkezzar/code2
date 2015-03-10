package dz.gov.mesrs.sii.commons.business.model.dto;

import java.io.Serializable;

public abstract class AbstractNotificationSearchDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2409499136339978874L;

	private String searchKey;

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	
	public abstract int getId();
	

}
