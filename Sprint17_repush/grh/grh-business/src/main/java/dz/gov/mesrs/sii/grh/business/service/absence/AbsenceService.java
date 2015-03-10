package dz.gov.mesrs.sii.grh.business.service.absence;

import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.model.grh.Absence;
import dz.gov.mesrs.sii.grh.business.model.dto.AbsenceDto;

public interface AbsenceService extends CommonService<Absence, AbsenceDto, Integer> {

	AbsenceDto enregisterAbsence(AbsenceDto dto);

	void confirmerAbsence(AbsenceDto dto);

	List<AbsenceDto> findAllAbsencesEnregistrees(int etablissmentId, SearchMode searchMode, String searKeyWords);

	int countAllAbsencesEnregistrees(int etablissmentId, String searKeyWords);

	boolean isEmployeAbsent(Long employeId, Date dateDebut, Date dateFin);

	List<AbsenceDto> findAllAbsencesEmploye(Long id);

}