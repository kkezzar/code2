package dz.gov.mesrs.sii.grh.business.service.referentiel.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.CorpsDao;
import dz.gov.mesrs.sii.commons.data.model.grh.Corps;
import dz.gov.mesrs.sii.grh.business.exception.CorpsAlreadySavedException;
import dz.gov.mesrs.sii.grh.business.model.dto.CorpsDto;
import dz.gov.mesrs.sii.grh.business.service.referentiel.CorpsService;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation des services
 * 
 */

@Service("corpsService")
public class CorpsServiceImpl extends CommonServiceImpl<Corps, CorpsDto, Integer> implements CorpsService {

	private CorpsDao corpsDao;

	@Override
	protected CommonDao<Corps, Integer> getDao() {
		return corpsDao;
	}

	@Override
	public CorpsDto saveCorps(CorpsDto dto) throws CorpsAlreadySavedException {
		//
		// if(dto.){
		//
		// }
		// TODO Auto-generated method stub
		return super.save(dto);
	}

	/**
	 * @return the corpsDao
	 */
	public CorpsDao getCorpsDao() {
		return corpsDao;
	}

	/**
	 * @param corpsDao
	 *            to set
	 */
	@Autowired
	public void setCorpsDao(CorpsDao corpsDao) {
		this.corpsDao = corpsDao;
	}

}
