package dz.gov.mesrs.sii.grh.business.service;


import java.util.List;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.model.grh.PropostionAvancement;
import dz.gov.mesrs.sii.grh.business.model.dto.PropostionAvancementDto;



/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Interface  Service PropostionAvancement
 * 
 */
 
 
public interface  PropostionAvancementService extends CommonService<PropostionAvancement,PropostionAvancementDto,Long>{

		/**
	 * for specific method
	 */

	List<String> maxDatepropositionAvancement(Integer situation,
			Integer refetablicement, Integer typeavancement);

		void deleteAllEmployProposition(
				PropostionAvancementDto propostionAvancementDto);

}