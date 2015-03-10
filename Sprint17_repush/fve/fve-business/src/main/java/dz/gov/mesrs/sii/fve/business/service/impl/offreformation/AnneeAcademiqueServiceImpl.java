/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.offreformation.AnneeAcademiqueImpl.java] 
 * @author BELDI Jamel on : 12 mai 2014  15:37:49
 */
package dz.gov.mesrs.sii.fve.business.service.impl.offreformation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.AnneeAcademiqueDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;

/**
 * @author BELDI Jamel on : 12 mai 2014 15:37:49
 */
@Service("anneeAcademiqueService")
public class AnneeAcademiqueServiceImpl implements AnneeAcademiqueService {
	private static final Log log = LogFactory
			.getLog(AnneeAcademiqueServiceImpl.class);

	@Autowired
	@Qualifier("anneeAcademiqueDao")
	private AnneeAcademiqueDao anneeAcademiqueDao;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.DozerBeanMapper mapper;

	/**
	 * [AnneeAcademiqueServiceImpl.anneeAcademiqueDao] Getter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:47:31
	 * @return the anneeAcademiqueDao
	 */
	public AnneeAcademiqueDao getAnneeAcademiqueDao() {
		return anneeAcademiqueDao;
	}

	/**
	 * [AnneeAcademiqueServiceImpl.anneeAcademiqueDao] Setter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:47:31
	 * @param anneeAcademiqueDao
	 *            the anneeAcademiqueDao to set
	 */
	public void setAnneeAcademiqueDao(AnneeAcademiqueDao anneeAcademiqueDao) {
		this.anneeAcademiqueDao = anneeAcademiqueDao;
	}

	/**
	 * [AnneeAcademiqueServiceImpl.mapper] Getter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:47:31
	 * @return the mapper
	 */
	public org.dozer.DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [AnneeAcademiqueServiceImpl.mapper] Setter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:47:31
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(org.dozer.DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * [AnneeAcademiqueImpl.AnneeAcademiqueImpl()] Constructor
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:37:49
	 */
	public AnneeAcademiqueServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademique#
	 * insertOrUpdate
	 * (dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto
	 * )
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public AnneeAcademiqueDto insertOrUpdate(
			AnneeAcademiqueDto anneeAcademiqueDto) {
		try {

			AnneeAcademique anneeAcademique = new AnneeAcademique();
			mapper.map(anneeAcademiqueDto, anneeAcademique);

			if (anneeAcademique.getId() == 0) {
				anneeAcademiqueDao.persist(anneeAcademique);
			} else {
				anneeAcademique = anneeAcademiqueDao.merge(anneeAcademique);
			}
			mapper.map(anneeAcademique, anneeAcademiqueDto);

			return anneeAcademiqueDto;
		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademique#
	 * remove
	 * (dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto
	 * )
	 */
	@Override
	public void remove(AnneeAcademiqueDto anneeAcademiqueDto) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademique#
	 * findById(int)
	 */
	@Override
	public AnneeAcademiqueDto findById(int id) {
		try {

			AnneeAcademique anneeAcademique = anneeAcademiqueDao.findById(id);
			if (anneeAcademique != null) {
				AnneeAcademiqueDto anneeAcademiqueDto = new AnneeAcademiqueDto();
				mapper.map(anneeAcademique, anneeAcademiqueDto);
				return anneeAcademiqueDto;
			}
			return null;

		} catch (RuntimeException e) {

			log.error("findById failed", e);
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademique#
	 * findAll()
	 */
	@Override
	public List<AnneeAcademiqueDto> findAll() {
		try {
			List<AnneeAcademiqueDto> result = new ArrayList<AnneeAcademiqueDto>();

			List<AnneeAcademique> resultListDao = anneeAcademiqueDao.findAll();
			if (resultListDao != null && !resultListDao.isEmpty()) {
				for (AnneeAcademique anneeAcademique : resultListDao) {
					AnneeAcademiqueDto anneeAcademiqueDto = new AnneeAcademiqueDto();
					mapper.map(anneeAcademique, anneeAcademiqueDto);
					result.add(anneeAcademiqueDto);
				}
				return result;
			}

		} catch (RuntimeException e) {
			log.error("findAll failed", e);
			throw e;

		}

		return null;
	}

	@Override
	public AnneeAcademiqueDto findByFirstAndSecondYear(int premiereAnnee,
			int deuxiemeAnnee) {
		try {

			AnneeAcademique anneeAcademique = anneeAcademiqueDao
					.findByFirstAndSecondYear(premiereAnnee, deuxiemeAnnee);
			if (anneeAcademique != null) {
				AnneeAcademiqueDto anneeAcademiqueDto = new AnneeAcademiqueDto();
				mapper.map(anneeAcademique, anneeAcademiqueDto);
				return anneeAcademiqueDto;
			}
			return null;

		} catch (RuntimeException e) {

			log.error("findByFirstAndSecondYear failed", e);
			throw e;
		}
	}

	@Override
	public AnneeAcademiqueDto findCurrentAnneeAcademique() {
		AnneeAcademique anneeAcademique = anneeAcademiqueDao
				.findCurrentAnneeAcademique();
		if (anneeAcademique != null) {
			AnneeAcademiqueDto anneeAcademiqueDto = new AnneeAcademiqueDto();
			mapper.map(anneeAcademique, anneeAcademiqueDto);
			return anneeAcademiqueDto;
		}
		return null;
	}

}
