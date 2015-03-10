package dz.gov.mesrs.sii.grh.business.service.formation;


import java.util.List;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.model.grh.BesoinFormation;
import dz.gov.mesrs.sii.grh.business.model.dto.BesoinFormationDto;

 
public interface  BesoinFormationService extends CommonService< BesoinFormation,BesoinFormationDto,Integer>{
	
	 List<BesoinFormationDto> findAllBesoinFormationCrees(int etablissmentId, SearchMode searchMode, String searchKeyWords);
	 int countAllBesoinFormationCrees(int etablissmentId, SearchMode searchMode, String searchKeyWords);
	 BesoinFormationDto saveBesoinFormation(BesoinFormationDto besoinFormationDto);
	 BesoinFormationDto saveEvaluationBesoinFormation(BesoinFormationDto besoinFormationDto);
}