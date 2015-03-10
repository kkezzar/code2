package dz.gov.mesrs.sii.grh.business.service.referentiel;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.model.grh.Corps;
import dz.gov.mesrs.sii.grh.business.exception.CorpsAlreadySavedException;
import dz.gov.mesrs.sii.grh.business.model.dto.CorpsDto;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Interface Service Corps
 * 
 */
public interface CorpsService extends CommonService<Corps, CorpsDto, Integer> {

	CorpsDto saveCorps(CorpsDto dto) throws CorpsAlreadySavedException;

}