/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.offreformation.OuvertureOffreFormationServiceImpl.java] 
 * @author BELDI Jamel on : 12 mai 2014  15:39:29
 */
package dz.gov.mesrs.sii.fve.business.service.impl.offreformation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.util.Utility;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.OuvertureOffreFormationDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OuvertureOffreFormation;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;

/**
 * @author BELDI Jamel on : 12 mai 2014 15:39:29
 */
@Service("ouvertureOffreFormationService")
public class OuvertureOffreFormationServiceImpl implements
		OuvertureOffreFormationService {

	private static final Log log = LogFactory
			.getLog(OuvertureOffreFormationServiceImpl.class);

	@Autowired
	@Qualifier("ouvertureOffreFormationDao")
	private OuvertureOffreFormationDao ouvertureOffreFormationDao;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.DozerBeanMapper mapper;

	/**
	 * [OuvertureOffreFormationServiceImpl.ouvertureOffreFormationDao] Getter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:46:24
	 * @return the ouvertureOffreFormationDao
	 */
	public OuvertureOffreFormationDao getOuvertureOffreFormationDao() {
		return ouvertureOffreFormationDao;
	}

	/**
	 * [OuvertureOffreFormationServiceImpl.ouvertureOffreFormationDao] Setter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:46:24
	 * @param ouvertureOffreFormationDao
	 *            the ouvertureOffreFormationDao to set
	 */
	public void setOuvertureOffreFormationDao(
			OuvertureOffreFormationDao ouvertureOffreFormationDao) {
		this.ouvertureOffreFormationDao = ouvertureOffreFormationDao;
	}

	/**
	 * [OuvertureOffreFormationServiceImpl.mapper] Getter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:46:24
	 * @return the mapper
	 */
	public org.dozer.DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [OuvertureOffreFormationServiceImpl.mapper] Setter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:46:24
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(org.dozer.DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * [OuvertureOffreFormationServiceImpl.OuvertureOffreFormationServiceImpl()]
	 * Constructor
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:39:29
	 */
	public OuvertureOffreFormationServiceImpl() {

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public OuvertureOffreFormationDto insertOrUpdate(
			OuvertureOffreFormationDto ouvertureOffreFormationDto) {
		try {

			OuvertureOffreFormation ouvertureOffreFormation = new OuvertureOffreFormation();
			mapper.map(ouvertureOffreFormationDto, ouvertureOffreFormation);

			if (ouvertureOffreFormation.getId() == 0) {
				ouvertureOffreFormationDao.persist(ouvertureOffreFormation);
			} else {
				ouvertureOffreFormation = ouvertureOffreFormationDao
						.merge(ouvertureOffreFormation);
			}
			mapper.map(ouvertureOffreFormation, ouvertureOffreFormationDto);

			return ouvertureOffreFormationDto;
		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.fve.business.service.offreformation.
	 * OuvertureOffreFormationService
	 * #remove(dz.gov.mesrs.sii.fve.business.model.
	 * dto.offreformation.OuvertureOffreFormationDto)
	 */
	@Override
	public void remove(OuvertureOffreFormationDto ouvertureOffreFormationDto) {
		// TODO Auto-generated method stub

	}

	@Override
	public OuvertureOffreFormationDto findById(int id) {
		try {

			OuvertureOffreFormation ouvertureOffreFormation = ouvertureOffreFormationDao
					.findById(id);
			if (ouvertureOffreFormation != null) {
				OuvertureOffreFormationDto ouvertureOffreFormationDto = new OuvertureOffreFormationDto();
				mapper.map(ouvertureOffreFormation, ouvertureOffreFormationDto);
				return ouvertureOffreFormationDto;
			}
			return null;

		} catch (RuntimeException e) {
			log.error("findById failed", e);
			throw e;
		}
	}

	@Override
	public List<OuvertureOffreFormationDto> findAll() {
		try {
			List<OuvertureOffreFormationDto> result = new ArrayList<OuvertureOffreFormationDto>();

			List<OuvertureOffreFormation> resultListDao = ouvertureOffreFormationDao
					.findAll();
			if (resultListDao != null && !resultListDao.isEmpty()) {
				for (OuvertureOffreFormation ouvertureOffreFormation : resultListDao) {
					OuvertureOffreFormationDto ouvertureOffreFormationDto = new OuvertureOffreFormationDto();
					mapper.map(ouvertureOffreFormation,
							ouvertureOffreFormationDto);
					result.add(ouvertureOffreFormationDto);
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
	public List<OuvertureOffreFormationDto> findAdvanced(
			OuvertureOffreFormationDto searchDto) {
		try {
			List<OuvertureOffreFormationDto> result = new ArrayList<OuvertureOffreFormationDto>();
			OuvertureOffreFormation searchBo = new OuvertureOffreFormation();
			mapper.map(searchDto, searchBo);
			List<OuvertureOffreFormation> resultListDao = ouvertureOffreFormationDao
					.findAdvanced(searchBo);
			result = Utility.map(mapper, resultListDao, OuvertureOffreFormationDto.class);
			return result;
		} catch (RuntimeException e) {
			log.error("findAdvanced failed", e);
			throw e;

		}
		
	}

	@Override
	public List<OuvertureOffreFormationDto> findAdvanced(
			String refCodeEtablissement, String refCodeSpecialite,
			Integer idAnneeAcademique, Boolean estOuverte) {

		List<OuvertureOffreFormationDto> result = new ArrayList<OuvertureOffreFormationDto>();

		try {
			List<OuvertureOffreFormation> resultListDao = ouvertureOffreFormationDao
					.findAdvanced(refCodeEtablissement, refCodeSpecialite,
							idAnneeAcademique, estOuverte);

			if (resultListDao != null && !resultListDao.isEmpty()) {
				for (OuvertureOffreFormation ouvertureOffreFormation : resultListDao) {
					OuvertureOffreFormationDto ouvertureOffreFormationDto = new OuvertureOffreFormationDto();
					mapper.map(ouvertureOffreFormation,
							ouvertureOffreFormationDto);
					result.add(ouvertureOffreFormationDto);
				}
			}
		} catch (RuntimeException e) {
			log.error("findAdvanced failed", e);
			throw e;
		}
		return result;
	}


}
