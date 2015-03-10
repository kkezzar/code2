package dz.gov.mesrs.sii.grh.business.service.poste;

import java.util.List;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.model.grh.AffectationPoste;
import dz.gov.mesrs.sii.grh.business.model.dto.AffectationPosteDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;

public interface AffectationPosteService extends CommonService<AffectationPoste, AffectationPosteDto, Long> {

	int countEmployeProposes(Long id);

	List<DossierEmployeDto> findEmployeProposes(DossierEmployeDto searchEmployeProposeDto, Long id,
			SearchMode searchMode);

}
