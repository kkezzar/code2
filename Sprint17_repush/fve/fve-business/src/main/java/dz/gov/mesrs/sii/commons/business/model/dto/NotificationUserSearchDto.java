package dz.gov.mesrs.sii.commons.business.model.dto;

import java.io.Serializable;

public class NotificationUserSearchDto extends AbstractNotificationSearchDto
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5277005676358986609L;
	//FIXME this is an integer in JPA but must be a long
	private int userId;

	public NotificationUserSearchDto(int userId) {
		super();
		this.userId = userId;
	}

	@Override
	public int getId() {
		return this.userId;
	}

}
