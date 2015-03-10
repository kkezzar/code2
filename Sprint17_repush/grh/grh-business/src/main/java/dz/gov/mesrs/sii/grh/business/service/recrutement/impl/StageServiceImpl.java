package dz.gov.mesrs.sii.grh.business.service.recrutement.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.grh.Stage;
import dz.gov.mesrs.sii.grh.business.model.dto.StageDto;
import dz.gov.mesrs.sii.commons.data.dao.grh.StageDao;
import dz.gov.mesrs.sii.grh.business.service.recrutement.StageService;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation des services
 * 
 */

@Service("stageService")
public class StageServiceImpl extends CommonServiceImpl<Stage,StageDto, Integer> implements StageService  {

	
	private StageDao	stageDao;

	@Override
	protected CommonDao<Stage, Integer> getDao() {
		return stageDao;
	}

	/**
	 * @return the stageDao
	 */
	public StageDao getStageDao() {
		return stageDao;
	}

	/**
	 * @param stageDao  to set
	 */
	@Autowired
	public void setStageDao(StageDao stageDao) {
		this.stageDao = stageDao;
	}
}



