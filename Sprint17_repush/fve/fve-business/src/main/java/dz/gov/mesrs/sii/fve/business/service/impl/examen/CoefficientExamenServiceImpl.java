/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.notation.ParametrageCoefficientExamenServiceImpl.java] 
 * @author MAKERRI Sofiane on : 5 janv. 2015  09:28:55
 */
package dz.gov.mesrs.sii.fve.business.service.impl.examen;

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
import dz.gov.mesrs.sii.commons.data.dao.fve.examen.CoefficientExamenDao;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.CoefficientExamen;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.CoefficientExamenDto;
import dz.gov.mesrs.sii.fve.business.service.examen.CoefficientExamenService;

/**
 * @author MAKERRI Sofiane on : 5 janv. 2015 09:28:55
 */
@Service("coefficientExamenService")
public class CoefficientExamenServiceImpl implements
		CoefficientExamenService {

	private static final Log log = LogFactory
			.getLog(CoefficientExamenServiceImpl.class);
	@Autowired
	private CoefficientExamenDao coefficientExamenDao;

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.fve.business.service.notation.
	 * ParametrageCoefficientExamenService
	 * #insertOrUpdate(dz.gov.mesrs.sii.fve.business
	 * .model.dto.notation.ParametrageCoefficientExamenDto)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public CoefficientExamenDto insertOrUpdate(
			CoefficientExamenDto parametrageCoefficientExamenDto) {
		try {
			CoefficientExamen parametrageCoefficientExamen = mapper
					.map(parametrageCoefficientExamenDto,
							CoefficientExamen.class);

			if (parametrageCoefficientExamen.getId() == 0)
				coefficientExamenDao
						.persist(parametrageCoefficientExamen);
			else
				parametrageCoefficientExamen = coefficientExamenDao
						.merge(parametrageCoefficientExamen);

			mapper.map(parametrageCoefficientExamen,
					parametrageCoefficientExamenDto);

			return parametrageCoefficientExamenDto;
		} catch (RuntimeException e) {
			log.error(
					"insertorupdate parametrageCoefficientExamenDto failed--",
					e);
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.fve.business.service.notation.
	 * ParametrageCoefficientExamenService
	 * #remove(dz.gov.mesrs.sii.fve.business.model
	 * .dto.notation.ParametrageCoefficientExamenDto)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(
			CoefficientExamenDto parametrageCoefficientExamenDto) {
		try {

			CoefficientExamen parametrageCoefficientExamen = mapper
					.map(parametrageCoefficientExamenDto,
							CoefficientExamen.class);

			parametrageCoefficientExamen = coefficientExamenDao
					.merge(parametrageCoefficientExamen);

			coefficientExamenDao
					.remove(parametrageCoefficientExamen);
		} catch (RuntimeException e) {
			throw e;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.fve.business.service.notation.
	 * ParametrageCoefficientExamenService#findById(int)
	 */
	@Override
	public CoefficientExamenDto findById(int id) {
		CoefficientExamen parametrageCoefficientExamen = coefficientExamenDao
				.findById(id);

		if (parametrageCoefficientExamen != null) {
			return mapper.map(parametrageCoefficientExamen,
					CoefficientExamenDto.class);
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.fve.business.service.notation.
	 * ParametrageCoefficientExamenService#findAll()
	 */
	@Override
	public List<CoefficientExamenDto> findAll() {
		List<CoefficientExamen> parametrageCoefficientExamens = coefficientExamenDao
				.findAll();

		return Utility.map(mapper, parametrageCoefficientExamens,
				CoefficientExamenDto.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.fve.business.service.examen.
	 * ParametrageCoefficientExamenService
	 * #findByOofAndPeriode(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<CoefficientExamenDto> findByOofAndPeriode(Integer oofId,
			Integer periodeId) {
		try {

			 List<CoefficientExamen> parametrageCoefficientExamens = coefficientExamenDao
					.findByOofAndPeriode(oofId, periodeId);
			return Utility.map(mapper, parametrageCoefficientExamens, CoefficientExamenDto.class);

		} catch (RuntimeException e) {
			log.error("findByOofAndPeriode failed--", e);
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.fve.business.service.examen.CoefficientExamenService#findUnique(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public CoefficientExamenDto findUnique(Integer oofId, Integer periodeId,
			Integer rattachementMcId) {
		try {
			 CoefficientExamen coefficientExamen = coefficientExamenDao
					.findUnique(oofId, periodeId, rattachementMcId);
			 if(coefficientExamen == null) {
				 return null;
			 }
			return mapper.map(coefficientExamen, CoefficientExamenDto.class);

		} catch (RuntimeException e) {
			log.error("findUnique failed--", e);
			throw e;
		}
	}

}
