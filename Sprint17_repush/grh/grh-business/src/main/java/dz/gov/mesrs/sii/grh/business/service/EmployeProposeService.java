package dz.gov.mesrs.sii.grh.business.service;


import java.util.List;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.dao.grh.EmployeProposeDao;
import dz.gov.mesrs.sii.commons.data.model.grh. EmployePropose;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.EmployeProposeDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;



/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Interface  Service  EmployePropose
 * 
 */
 
public interface  EmployeProposeService extends CommonService<  EmployePropose,EmployeProposeDto,Long>{

	
	/**
	 * for specific method
	 */

	List<DossierEmployeDto> findlistEmployesDernierAvancementPromotionParList(RefEtablissementDto refEtablissementDto);
}