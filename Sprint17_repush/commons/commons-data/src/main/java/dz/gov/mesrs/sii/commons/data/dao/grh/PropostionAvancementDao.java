package dz.gov.mesrs.sii.commons.data.dao.grh;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.grh.PropostionAvancement;



/**
 * 
 * @author Salem
 * @version V1.0
 * @date 21-10-2014
 * @description Interface  DAO PropostionAvancement	
 * 
 */

public interface PropostionAvancementDao extends CommonDao<PropostionAvancement,Long> {

	

	List<String> maxDatepropositionAvancement(Integer situation,
			Integer refetablicement, Integer typeavancement);

	void deleteAllEmployProposition(PropostionAvancement propostionAvancement);

	/**
	 * for specific method
	 */

}