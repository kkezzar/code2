package dz.gov.mesrs.sii.grh.business.service.formation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.EvaluationSessionFormationDao;
import dz.gov.mesrs.sii.commons.data.model.grh.EvaluationSessionFormation;
import dz.gov.mesrs.sii.grh.business.model.dto.EvaluationSessionFormationDto;
import dz.gov.mesrs.sii.grh.business.service.formation.EvaluationSessionFormationService;


@Service("evaluationSessionFormationService")
public class EvaluationSessionFormationServiceImpl extends CommonServiceImpl<EvaluationSessionFormation , EvaluationSessionFormationDto,Long> implements EvaluationSessionFormationService  {
	
	private EvaluationSessionFormationDao	evaluationSessionFormationDao;

	@Override
	protected CommonDao<EvaluationSessionFormation, Long> getDao() {
		return evaluationSessionFormationDao;
	}

	/**
	 * @return the EvaluationSessionFormationDao
	 */
	public EvaluationSessionFormationDao getEvaluationSessionFormationDao() {
		return evaluationSessionFormationDao;
	}

	/**
	 * @param EvaluationSessionFormationDao  to set
	 */
	@Autowired
	public void setEvaluationSessionFormationDao(EvaluationSessionFormationDao evaluationSessionFormationDao) {
		this.evaluationSessionFormationDao = evaluationSessionFormationDao;
	}

	
	
}



