package dz.gov.mesrs.sii.grh.business.service.dossieremploye.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;







import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.grh.Distinction;
import dz.gov.mesrs.sii.grh.business.model.dto.DistinctionDto;
import dz.gov.mesrs.sii.commons.data.dao.grh.DistinctionDao;
import dz.gov.mesrs.sii.grh.business.service.dossieremploye.DistinctionService;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation des services
 * 
 */

@Service("distinctionService")
public class DistinctionServiceImpl extends CommonServiceImpl<Distinction,DistinctionDto, Integer> implements DistinctionService  {

	
	private DistinctionDao	distinctionDao;

	@Override
	protected CommonDao<Distinction, Integer> getDao() {
		return distinctionDao;
	}

	/**
	 * @return the distinctionDao
	 */
	public DistinctionDao getDistinctionDao() {
		return distinctionDao;
	}

	/**
	 * @param distinctionDao  to set
	 */
	@Autowired
	public void setDistinctionDao(DistinctionDao distinctionDao) {
		this.distinctionDao = distinctionDao;
	}
}



