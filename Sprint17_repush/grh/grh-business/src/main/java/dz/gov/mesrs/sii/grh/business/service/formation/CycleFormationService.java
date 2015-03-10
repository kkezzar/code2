package dz.gov.mesrs.sii.grh.business.service.formation;


import java.util.List;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.model.grh.CycleFormation;
import dz.gov.mesrs.sii.grh.business.model.dto.CycleFormationDto;

 
public interface  CycleFormationService extends CommonService< CycleFormation,CycleFormationDto,Long>{
	 List<CycleFormationDto> findAllCycleFormationCrees(SearchMode searchMode, String searchKeyWords);
	 CycleFormationDto saveCycleFormation(CycleFormationDto cycleFormationDto);
	 CycleFormationDto publierCycleFormation(CycleFormationDto cycleFormationDto);

}