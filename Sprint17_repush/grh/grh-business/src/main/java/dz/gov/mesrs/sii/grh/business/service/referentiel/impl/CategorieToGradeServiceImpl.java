package dz.gov.mesrs.sii.grh.business.service.referentiel.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.ModelBaseRelationship;
import dz.gov.mesrs.sii.commons.data.model.grh.CategorieToGrade;
import dz.gov.mesrs.sii.grh.business.model.dto.CategorieToGradeDto;
import dz.gov.mesrs.sii.commons.data.dao.grh.CategorieToGradeDao;
import dz.gov.mesrs.sii.grh.business.service.referentiel.CategorieToGradeService;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation des services
 * 
 */
 

@Service("categorieToGradeService")
public class CategorieToGradeServiceImpl extends CommonServiceImpl<CategorieToGrade,CategorieToGradeDto, ModelBaseRelationship.Id> implements CategorieToGradeService  {
	
	private CategorieToGradeDao	categorieToGradeDao;

	@Override
	protected CommonDao<CategorieToGrade, ModelBaseRelationship.Id> getDao() {
		return categorieToGradeDao;
	}

	/**
	 * @return the categorieToGradeDao
	 */
	public CategorieToGradeDao getCategorieToGradeDao() {
		return categorieToGradeDao;
	}

	/**
	 * @param categorieToGradeDao  to set
	 */
	@Autowired
	public void setCategorieToGradeDao(CategorieToGradeDao categorieToGradeDao) {
		this.categorieToGradeDao = categorieToGradeDao;
	}

	
	
}



