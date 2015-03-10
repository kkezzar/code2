package dz.gov.mesrs.sii.grh.business.service.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.model.grh.Statut;
import dz.gov.mesrs.sii.grh.business.model.dto.StatutDto;


/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Interface  Service Statut
 * 
 */
 
 
public interface  StatutService extends CommonService< Statut,StatutDto,Integer>{
	/**
	 * for specific method
	 */

public List<StatutDto> getListValideStatuts();
}