package dz.gov.mesrs.sii.grh.business.service.dossieremploye.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.grh.Conjoint;
import dz.gov.mesrs.sii.grh.business.model.dto.ConjointDto;
import dz.gov.mesrs.sii.commons.data.dao.grh.ConjointDao;
import dz.gov.mesrs.sii.grh.business.service.dossieremploye.ConjointService;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation des services
 * 
 */
@Service("conjointService")
public class ConjointServiceImpl extends CommonServiceImpl<Conjoint , ConjointDto,Integer> implements ConjointService  {
	
	private ConjointDao	conjointDao;

	@Override
	protected CommonDao<Conjoint, Integer> getDao() {
		return conjointDao;
	}

	/**
	 * @return the conjointDao
	 */
	public ConjointDao getConjointDao() {
		return conjointDao;
	}

	/**
	 * @param conjointDao  to set
	 */
	@Autowired
	public void setConjointDao(ConjointDao conjointDao) {
		this.conjointDao = conjointDao;
	}
	
}



