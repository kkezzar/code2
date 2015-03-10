package dz.gov.mesrs.sii.grh.business.service.referentiel.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.grh.Grade;
import dz.gov.mesrs.sii.grh.business.model.dto.GradeDto;
import dz.gov.mesrs.sii.commons.data.dao.grh.GradeDao;
import dz.gov.mesrs.sii.grh.business.service.referentiel.GradeService;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation des services
 * 
 */
 
@Transactional
@Service("gradeService")
public class GradeServiceImpl extends CommonServiceImpl<Grade,GradeDto, Integer> implements GradeService  {
	
	private GradeDao	gradeDao;

	@Override
	protected CommonDao<Grade, Integer> getDao() {
		return gradeDao;
	}

	/**
	 * @return the gradeDao
	 */
	public GradeDao getGradeDao() {
		return gradeDao;
	}

	/**
	 * @param gradeDao  to set
	 */
	@Autowired
	public void setGradeDao(GradeDao gradeDao) {
		this.gradeDao = gradeDao;
	}
	
}



