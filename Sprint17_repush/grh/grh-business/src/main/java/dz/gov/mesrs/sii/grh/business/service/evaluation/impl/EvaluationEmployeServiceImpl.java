package dz.gov.mesrs.sii.grh.business.service.evaluation.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.EvaluationEmployeDao;
import dz.gov.mesrs.sii.commons.data.model.grh.EvaluationEmploye;
import dz.gov.mesrs.sii.grh.business.model.dto.EvaluationEmployeDto;
import dz.gov.mesrs.sii.grh.business.service.evaluation.EvaluationEmployeService;

@Service("evaluationEmployeService")
@Transactional
public class EvaluationEmployeServiceImpl extends CommonServiceImpl<EvaluationEmploye, EvaluationEmployeDto, Long>
		implements EvaluationEmployeService {

	private final static Logger logger = LoggerFactory.getLogger(EvaluationEmployeServiceImpl.class.getName());

	private EvaluationEmployeDao evaluationEmployeDao;

	// private

	// Getters & Setters
	@Override
	protected CommonDao<EvaluationEmploye, Long> getDao() {
		return evaluationEmployeDao;
	}

	@Autowired
	public void setEvaluationEmployeDao(EvaluationEmployeDao evaluationEmployeDao) {
		this.evaluationEmployeDao = evaluationEmployeDao;
	}

}
