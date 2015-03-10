package dz.gov.mesrs.sii.grh.business.service.absence;

import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.model.grh.AutorisationAbsence;
import dz.gov.mesrs.sii.grh.business.model.dto.AutorisationAbsenceDto;

public interface AutorisationAbsenceService extends CommonService<AutorisationAbsence, AutorisationAbsenceDto, Integer> {

	AutorisationAbsenceDto enregisterDemande(AutorisationAbsenceDto dto);

	AutorisationAbsenceDto enregisterResultat(AutorisationAbsenceDto dto);

	List<AutorisationAbsenceDto> findAllPendingRequest(int etablissementId, SearchMode searchMode, String searchKeyWord);
	
	int countAllPendingRequest(int etablissementId, String searchKeyWord);
	
	boolean isEmployeAutoriseAbsence(Long employeId, Date dateDebut , Date dateFin);

}