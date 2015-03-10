package dz.gov.mesrs.sii.grh.business.service.referentiel.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.grh.GrilleIndiciaire;
import dz.gov.mesrs.sii.grh.business.model.dto.GrilleIndiciaireDto;
import dz.gov.mesrs.sii.commons.data.dao.grh.GrilleIndiciaireDao;
import dz.gov.mesrs.sii.grh.business.service.referentiel.GrilleIndiciaireService;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation des services
 * 
 */

@Service("grilleIndiciaireService")
public class GrilleIndiciaireServiceImpl extends CommonServiceImpl<GrilleIndiciaire,GrilleIndiciaireDto, Integer> implements GrilleIndiciaireService  {
	
	private GrilleIndiciaireDao	grilleIndiciaireDao;

	@Override
	protected CommonDao<GrilleIndiciaire, Integer> getDao() {
		return grilleIndiciaireDao;
	}

	/**
	 * @return the grilleIndiciaireDao
	 */
	public GrilleIndiciaireDao getGrilleIndiciaireDao() {
		return grilleIndiciaireDao;
	}

	/**
	 * @param grilleIndiciaireDao  to set
	 */
	@Autowired
	public void setGrilleIndiciaireDao(GrilleIndiciaireDao grilleIndiciaireDao) {
		this.grilleIndiciaireDao = grilleIndiciaireDao;
	}
	
}



