package dz.gov.mesrs.sii.grh.business.service.formation;


import java.util.List;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.model.grh.SessionFormation;
import dz.gov.mesrs.sii.grh.business.model.dto.SessionFormationDto;

 
public interface  SessionFormationService extends CommonService< SessionFormation,SessionFormationDto,Long>{
	
	 List<SessionFormationDto> findAllSessionFormationCrees(SearchMode searchMode, String searchKeyWords);
	 List<SessionFormationDto> findAllSessionFormationCreesAndCycleCree(SearchMode searchMode, String searchKeyWords);
	 List<SessionFormationDto> findAllSessionFormationCreesAndCyclePublie(SearchMode searchMode, String searchKeyWords);
	 SessionFormationDto saveSessionFormation(SessionFormationDto sessionFormationDto);
	 SessionFormationDto saveDemandesInsciption(SessionFormationDto sessionFormationDto);

}