package dz.gov.mesrs.sii.grh.business.service.suivimedical;

import java.util.List;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.model.grh.VisiteMedicale;
import dz.gov.mesrs.sii.grh.business.model.dto.VaccinDto;
import dz.gov.mesrs.sii.grh.business.model.dto.VisiteMedicaleDto;

public interface VisiteMedicaleService extends
		CommonService<VisiteMedicale, VisiteMedicaleDto, Long> {
	
	List<VisiteMedicaleDto> findAllRdvVisiteMedicales(int etablissmentId,
			String searchKeyWords);

	List<VisiteMedicaleDto> findAllVisiteMedicalesAcceptees(int etablissmentId,
			String searchKeyWords);

	List<VisiteMedicaleDto> findAllVisiteAvecExamenNonEncoreRenseignes(
			int etablissmentId, String searchKeyWords);

	List<VisiteMedicaleDto> findAllVisitesEmploye(Long employeId);
}