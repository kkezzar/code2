package dz.gov.mesrs.sii.fve.business.service.impl.examen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.util.Utility;
import dz.gov.mesrs.sii.commons.data.dao.fve.examen.EvaluationControleContinuDao;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.EvaluationControleContinu;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.EvaluationControleContinuDto;
import dz.gov.mesrs.sii.fve.business.service.examen.EvaluationControleContinuService;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */

@Service("evaluationControleContinuService")
public class EvaluationControleContinuServiceImpl implements
		EvaluationControleContinuService {

	@Autowired
	@Qualifier("evaluationControleContinuDao")
	private EvaluationControleContinuDao evaluationControleContinuDao;

	private static final Log log = LogFactory
			.getLog(EvaluationControleContinuServiceImpl.class);

	 @Autowired
	 @Qualifier("mapper")
	private DozerBeanMapper mapper;

	public EvaluationControleContinuServiceImpl() {
//		final List<String> mappings = new ArrayList<String>(1);
//		mappings.add(UtilConstants.DOZER_EVAL_CONTROLE_CONTINU_MAPPING_NAME);
//		mapper = new DozerBeanMapper();
//		mapper.setMappingFiles(mappings);
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public EvaluationControleContinuDto insertOrUpdate(
			EvaluationControleContinuDto evaluationControleContinuDto) {

		EvaluationControleContinu evaluationControleContinu = mapper.map(
				evaluationControleContinuDto, EvaluationControleContinu.class);
		try {
			if (evaluationControleContinu.getId() == 0) {
				evaluationControleContinuDao.persist(evaluationControleContinu);
			} else {
				evaluationControleContinu = evaluationControleContinuDao
						.merge(evaluationControleContinu);
			}
			mapper.map(evaluationControleContinu, evaluationControleContinuDto);

			log.error("insertorupdate EvaluationControleContinu succes..");

			return evaluationControleContinuDto;

		} catch (RuntimeException e) {
			log.error("insertorupdate EvaluationControleContinu failed--", e);
			throw e;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(EvaluationControleContinuDto evaluationControleContinuDto) {
		try {

			EvaluationControleContinu evaluationControleContinu = mapper.map(
					evaluationControleContinuDto,
					EvaluationControleContinu.class);

			evaluationControleContinu = evaluationControleContinuDao
					.merge(evaluationControleContinu);

			evaluationControleContinuDao.remove(evaluationControleContinu);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public EvaluationControleContinuDto findById(long id) {

		EvaluationControleContinu evaluationControleContinu = evaluationControleContinuDao
				.findById(id);

		if (evaluationControleContinu != null)
			return mapper.map(evaluationControleContinu,
					EvaluationControleContinuDto.class);

		return null;
	}

	@Override
	public List<EvaluationControleContinuDto> findAll() {

		List<EvaluationControleContinu> evaluationControleContinus = evaluationControleContinuDao
				.findAll();

		List<EvaluationControleContinuDto> evaluationControleContinuDtos = new ArrayList<EvaluationControleContinuDto>();

		for (EvaluationControleContinu evaluationControleContinu : evaluationControleContinus) {
			evaluationControleContinuDtos.add(mapper.map(
					evaluationControleContinu,
					EvaluationControleContinuDto.class));
		}

		return evaluationControleContinuDtos;

	}

	@Override
	public List<EvaluationControleContinuDto> findAdvanced(
			EvaluationControleContinuDto evaluationControleContinuDto) {
		try {
			EvaluationControleContinu search = new EvaluationControleContinu();
			mapper.map(evaluationControleContinuDto, search);
			List<EvaluationControleContinu> evaluationControleContinuList = evaluationControleContinuDao
					.findAdvanced(search);
			return Utility.map(mapper, evaluationControleContinuList, EvaluationControleContinuDto.class);

		} catch (RuntimeException e) {
			log.error("findAdvanced failed", e);
			throw e;

		}
		
	}

	@Override
	public EvaluationControleContinuDto findByGpIdAndDateEval(Long evalId,
			Integer gpId, Date dateEval) {
		try {
			EvaluationControleContinu evaluationControleContinu = evaluationControleContinuDao
					.findByGpIdAndDateEval(evalId, gpId, dateEval);

			if (evaluationControleContinu != null)
				return mapper.map(evaluationControleContinu,
						EvaluationControleContinuDto.class);

		} catch (RuntimeException e) {
			log.error("findByGroupePedagogiqueId failed", e);
			throw e;

		}
		return null;
	}

	@Override
	public List<EvaluationControleContinuDto> findByGpAssociation(Long evalId,
			Integer gpAssocId) {
		try {
			List<EvaluationControleContinu> evaluationControleContinuList = evaluationControleContinuDao
					.findByGpAssociation(evalId, gpAssocId);
			if (evaluationControleContinuList != null) {
				List<EvaluationControleContinuDto> evaluationControleContinuDtoList = new ArrayList<EvaluationControleContinuDto>();
				for (EvaluationControleContinu currentEvaluationControleContinu : evaluationControleContinuList) {
					EvaluationControleContinuDto evalControleContinuDto = new EvaluationControleContinuDto();
					mapper.map(currentEvaluationControleContinu,
							evalControleContinuDto);

					evaluationControleContinuDtoList
							.add(evalControleContinuDto);
				}
				return evaluationControleContinuDtoList;
			}

		} catch (RuntimeException e) {
			log.error("findByGpAssociation failed", e);
			throw e;

		}
		return null;

	}

}
