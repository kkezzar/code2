package dz.gov.mesrs.sii.grh.business.service.referentiel.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.grh.CategorieProfessionnelle;
import dz.gov.mesrs.sii.grh.business.model.dto.CategorieProfessionnelleDto;
import dz.gov.mesrs.sii.commons.data.dao.grh.CategorieProfessionnelleDao;
import dz.gov.mesrs.sii.grh.business.service.referentiel.CategorieProfessionnelleService;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation des services
 * 
 */
 

@Service("categorieProfessionnelleService")
public class CategorieProfessionnelleServiceImpl extends CommonServiceImpl<CategorieProfessionnelle,CategorieProfessionnelleDto, Integer> implements CategorieProfessionnelleService  {
	
	private CategorieProfessionnelleDao	categorieProfessionnelleDao;

	@Override
	protected CommonDao<CategorieProfessionnelle, Integer> getDao() {
		return categorieProfessionnelleDao;
	}

	/**
	 * @return the categorieProfessionnelleDao
	 */
	public CategorieProfessionnelleDao getCategorieProfessionnelleDao() {
		return categorieProfessionnelleDao;
	}

	/**
	 * @param categorieProfessionnelleDao  to set
	 */
	@Autowired
	public void setCategorieProfessionnelleDao(CategorieProfessionnelleDao categorieProfessionnelleDao) {
		this.categorieProfessionnelleDao = categorieProfessionnelleDao;
	}
	
}



