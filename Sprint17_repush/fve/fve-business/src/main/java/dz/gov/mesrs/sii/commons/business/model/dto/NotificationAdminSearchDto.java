package dz.gov.mesrs.sii.commons.business.model.dto;

import java.io.Serializable;

public class NotificationAdminSearchDto extends AbstractNotificationSearchDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -693388060142540500L;
	
	
	private int idEtablissement;


	public NotificationAdminSearchDto(int idEtablissement) {
		super();
		this.idEtablissement =  idEtablissement;
	}
	
	



	@Override
	public int getId() {
		return this.idEtablissement;
	}

	

	

	
	
	
	
	
	

}
