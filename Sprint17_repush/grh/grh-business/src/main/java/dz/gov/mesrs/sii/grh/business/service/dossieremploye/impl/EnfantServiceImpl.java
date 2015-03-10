package dz.gov.mesrs.sii.grh.business.service.dossieremploye.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.grh.Enfant;
import dz.gov.mesrs.sii.grh.business.model.dto.EnfantDto;
import dz.gov.mesrs.sii.commons.data.dao.grh.EnfantDao;
import dz.gov.mesrs.sii.grh.business.service.dossieremploye.EnfantService;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation des services
 * 
 */
 

@Service("enfantService")
public class EnfantServiceImpl extends CommonServiceImpl<Enfant, EnfantDto,Integer> implements EnfantService  {
	
	private EnfantDao	enfantDao;

	@Override
	protected CommonDao<Enfant, Integer> getDao() {
		return enfantDao;
	}

	/**
	 * @return the enfantDao
	 */
	public EnfantDao getEnfantDao() {
		return enfantDao;
	}

	/**
	 * @param enfantDao  to set
	 */
	@Autowired
	public void setEnfantDao(EnfantDao enfantDao) {
		this.enfantDao = enfantDao;
	}
	
}



