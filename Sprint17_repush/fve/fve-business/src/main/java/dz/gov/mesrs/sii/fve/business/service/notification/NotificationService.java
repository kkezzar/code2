package dz.gov.mesrs.sii.fve.business.service.notification;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierTransfertDto;

/**
 * Service de notification des actions aux utilisateurs.
 * 
 * @author abelaid
 * 
 */

public interface NotificationService {
	String MAIL_TEMPLATE_FOLDER = "velocity/";
	String DATE_FORMAT = "dd/MM/yyyy";

	String TRANSFER_APROVED_MAIL_TEMPLATE = "transfer-aproved.vm";
	String TRANSFER_REFUSED_MAIL_TEMPLATE = "transfer-refused.vm";
	
	void notifyTransferResult(int refIdEtablissement, DossierTransfertDto dto);
}
