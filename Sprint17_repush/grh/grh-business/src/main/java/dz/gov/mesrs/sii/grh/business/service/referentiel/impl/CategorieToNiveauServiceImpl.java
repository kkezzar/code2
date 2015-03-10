package dz.gov.mesrs.sii.grh.business.service.referentiel.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.ModelBaseRelationship;
import dz.gov.mesrs.sii.commons.data.model.grh.CategorieToNiveau;
import dz.gov.mesrs.sii.grh.business.model.dto.CategorieToNiveauDto;
import dz.gov.mesrs.sii.commons.data.dao.grh.CategorieToNiveauDao;
import dz.gov.mesrs.sii.grh.business.service.referentiel.CategorieToNiveauService;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation des services
 * 
 */
 

@Service("categorieToNiveauService")
public class CategorieToNiveauServiceImpl extends CommonServiceImpl<CategorieToNiveau, CategorieToNiveauDto,ModelBaseRelationship.Id> implements CategorieToNiveauService  {
	
	private CategorieToNiveauDao	categorieToNiveauDao;

	@Override
	protected CommonDao<CategorieToNiveau, ModelBaseRelationship.Id> getDao() {
		return categorieToNiveauDao;
	}

	/**
	 * @return the categorieToNiveauDao
	 */
	public CategorieToNiveauDao getCategorieToNiveauDao() {
		return categorieToNiveauDao;
	}

	/**
	 * @param categorieToNiveauDao  to set
	 */
	@Autowired
	public void setCategorieToNiveauDao(CategorieToNiveauDao categorieToNiveauDao) {
		this.categorieToNiveauDao = categorieToNiveauDao;
	}
	
}



