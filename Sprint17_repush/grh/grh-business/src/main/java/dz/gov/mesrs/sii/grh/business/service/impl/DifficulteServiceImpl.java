package dz.gov.mesrs.sii.grh.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.grh.Difficulte;
import dz.gov.mesrs.sii.grh.business.model.dto.DifficulteDto;
import dz.gov.mesrs.sii.commons.data.dao.grh.DifficulteDao;
import dz.gov.mesrs.sii.grh.business.service.DifficulteService;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation des services
 * 
 */

@Service("difficulteService")
public class DifficulteServiceImpl extends CommonServiceImpl<Difficulte,DifficulteDto, Integer> implements DifficulteService  {
	
	private DifficulteDao	difficulteDao;

	@Override
	protected CommonDao<Difficulte, Integer> getDao() {
		return difficulteDao;
	}

	/**
	 * @return the difficulteDao
	 */
	public DifficulteDao getDifficulteDao() {
		return difficulteDao;
	}

	/**
	 * @param difficulteDao  to set
	 */
	@Autowired
	public void setDifficulteDao(DifficulteDao difficulteDao) {
		this.difficulteDao = difficulteDao;
	}
	
}



