/**
 * 
 */
package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefEvenementDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefSituationDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEntiteSituation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEvenement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSituation;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEvenementDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefEvenementService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametrageService;

/**
 * @author BELDI Jamel on : 13 f�vr. 2014 12:09:10
 */
@Service("refEvenementServiceImpl")
public class RefEvenementServiceImpl implements RefEvenementService {
	private static final Logger log = LoggerFactory.getLogger(RefEvenementServiceImpl.class.getName());
	@Autowired
	@Qualifier("refEvenementDaoImpl")
	private RefEvenementDao refEvenementDao;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.Mapper mapper;
	@Autowired
	@Qualifier("refSituationDaoImpl")
	private RefSituationDao refSituationDao;
	@Autowired
	@Qualifier("refParametrageServiceImpl")
	private RefParametrageService refParametrageServiceImpl;

	/**
	 * [RefEvenementServiceImpl.refParametrageServiceImpl] Getter
	 * 
	 * @author BELDI Jamel on : 7 avr. 2014 14:59:45
	 * @return the refParametrageServiceImpl
	 */
	public RefParametrageService getRefParametrageServiceImpl() {
		return refParametrageServiceImpl;
	}

	/**
	 * [RefEvenementServiceImpl.refParametrageServiceImpl] Setter
	 * 
	 * @author BELDI Jamel on : 7 avr. 2014 14:59:45
	 * @param refParametrageServiceImpl
	 *            the refParametrageServiceImpl to set
	 */
	public void setRefParametrageServiceImpl(
			RefParametrageService refParametrageServiceImpl) {
		this.refParametrageServiceImpl = refParametrageServiceImpl;
	}

	/**
	 * @return the refEvenementDao
	 */
	public RefEvenementDao getRefEvenementDao() {
		return refEvenementDao;
	}

	/**
	 * @param refEvenementDao
	 *            the refEvenementDao to set
	 */
	public void setRefEvenementDao(RefEvenementDao refEvenementDao) {
		this.refEvenementDao = refEvenementDao;
	}

	/**
	 * @return the refSituationDao
	 */
	public RefSituationDao getRefSituationDao() {
		return refSituationDao;
	}

	/**
	 * @param refSituationDao
	 *            the refSituationDao to set
	 */
	public void setRefSituationDao(RefSituationDao refSituationDao) {
		this.refSituationDao = refSituationDao;
	}

	/**
	 * 
	 */
	public RefEvenementServiceImpl() {
	}

	@Override
	public RefEvenementDto findById(Integer id) {
		RefEvenementDto refEvenementDto = null;
		try {
			RefEvenement refEvenement = refEvenementDao.findById(id);
			if (refEvenement != null) {
				refEvenementDto = new RefEvenementDto();
				mapper.map(refEvenement, refEvenementDto);
				log.info("success get dto: " + refEvenementDto.getObjet());
				return refEvenementDto;
			}
		} catch (Exception e) {
			log.error("get failed", e);

		}
		return refEvenementDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefEvenementService#findBAll
	 * ()
	 */
	@Override
	public List<RefEvenementDto> findAll() {
		List<RefEvenementDto> refEvenementListDto = new ArrayList<RefEvenementDto>();
		try {
			List<RefEvenement> refEvenementList = refEvenementDao.findAll();
			if (refEvenementList != null) {
				log.debug("individu list success with size =  {}", new Object[]{refEvenementList.size()});
				for (RefEvenement currentRefEvenement : refEvenementList) {
					RefEvenementDto refEvenementDto = new RefEvenementDto();
					mapper.map(currentRefEvenement, refEvenementDto);
					refEvenementListDto.add(refEvenementDto);
				}
				return refEvenementListDto;
			}
		} catch (RuntimeException e) {
			log.error("get individu list failed", e);
			throw e;
		}

		return null;
	}

	/**
	 * [RefEvenementServiceImpl.mapper] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2014 17:04:29
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefEvenementServiceImpl.mapper] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2014 17:04:29
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<RefEvenementDto> findGeneric(String value) {
		List<RefEvenementDto> refEvenementListDto = new ArrayList<RefEvenementDto>();
		try {
			List<RefEvenement> refEvenementList = refEvenementDao
					.findGeneric(value);
			if (refEvenementList != null) {
				log.debug("contrat list success with size = {} ", new Object[]{refEvenementList.size()});

				for (RefEvenement currentRefEvenement : refEvenementList) {
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(
									currentRefEvenement.getId(),
									RefEvenement.class.getSimpleName());
					RefEvenementDto refEvenementDto = new RefEvenementDto();
					mapper.map(currentRefEvenement, refEvenementDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, refEvenementDto);
					}
					refEvenementListDto.add(refEvenementDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refEvenementListDto;

	}

	@Override
	public List<RefEvenementDto> findAdvanced(RefEvenementDto refEvenementDto) {

		List<RefEvenementDto> refEvenementListDto = new ArrayList<RefEvenementDto>();
		try {
			RefEvenement refEvenement = new RefEvenement();
			mapper.map(refEvenementDto, refEvenement);
			List<RefEvenement> refEvenementList = refEvenementDao
					.findAdvanced(refEvenement);
			if (refEvenementList != null && !refEvenementList.isEmpty()) {
				log.debug("contrat list success with size = {} ", new Object[]{refEvenementList.size()});

				for (RefEvenement currentRefEvenement : refEvenementList) {
					RefEvenementDto currentRefEvenementDto = new RefEvenementDto();
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(
									currentRefEvenement.getId(),
									RefEvenement.class.getSimpleName());
					mapper.map(currentRefEvenement, currentRefEvenementDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, currentRefEvenementDto);
					}
					refEvenementListDto.add(currentRefEvenementDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refEvenementListDto;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public Integer save(RefEvenementDto refEvenementDto) {
		RefEvenement refEvenement = new RefEvenement();
		mapper.map(refEvenementDto, refEvenement);
		try {
			RefSituation refSituation = new RefSituation();
			refEvenementDao.persist(refEvenement);
			refSituation.setDateSituation(new Date());
			refSituation.setIdEntite(refEvenement.getId());
			refSituation.setNomEntite(RefEvenement.class.getSimpleName());
			RefEntiteSituation refEntiteSituation = new RefEntiteSituation();
			refEntiteSituation.setId(13/*Integer.parseInt(refParametrageServiceImpl
					.getParamConfiguration(
							UtilConstant.EVENEMENT_SITUATION_CREE_ID_KEY,
							UtilConstant.CONFIGURATION_UTIL).getValue())*/);
			refSituation.setRefEntiteSituation(refEntiteSituation);
			refSituationDao.persist(refSituation);
			log.debug("save successful");
			return refEvenement.getId();
		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void update(RefEvenementDto refEvenementDto) {
		RefEvenement refEvenement = new RefEvenement();
		mapper.map(refEvenementDto, refEvenement);
		try {
			refEvenementDao.merge(refEvenement);
			log.debug("update successful");
		} catch (RuntimeException e) {
			log.error("update failed", e);
			throw e;
		}

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<RefEvenementDto> findAll(String etabLcLatin) {
		List<RefEvenementDto> refEvenementListDto = new ArrayList<RefEvenementDto>();
		try {
			List<RefEvenement> refEvenementList = refEvenementDao
					.findAll(etabLcLatin);
			if (refEvenementList != null) {
				log.debug("individu list success with size =  {}", new Object[]{refEvenementList.size()});
				for (RefEvenement currentRefEvenement : refEvenementList) {
					RefEvenementDto refEvenementDto = new RefEvenementDto();
					mapper.map(currentRefEvenement, refEvenementDto);
					refEvenementListDto.add(refEvenementDto);
				}
				return refEvenementListDto;
			}
		} catch (RuntimeException e) {
			log.error("get individu list failed", e);
			throw e;
		}

		return null;
	}

	@Override
	public List<RefEvenementDto> findGeneric(Integer etabId, String value) {
		List<RefEvenementDto> refEvenementListDto = new ArrayList<RefEvenementDto>();
		try {
			List<RefEvenement> refEvenementList = refEvenementDao.findGeneric(
					etabId, value);
			if (refEvenementList != null) {
				log.debug("contrat list success with size = {} ", new Object[]{refEvenementList.size()});

				for (RefEvenement currentRefEvenement : refEvenementList) {
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(
									currentRefEvenement.getId(),
									RefEvenement.class.getSimpleName());
					RefEvenementDto refEvenementDto = new RefEvenementDto();
					mapper.map(currentRefEvenement, refEvenementDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, refEvenementDto);
					}
					refEvenementListDto.add(refEvenementDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refEvenementListDto;

	}

	@Override
	public List<RefEvenementDto> findAdvanced(Integer etabId,
			RefEvenementDto refEvenmentDto) {
		List<RefEvenementDto> refEvenementListDto = new ArrayList<RefEvenementDto>();
		try {
			RefEvenement refEvenement = new RefEvenement();
			mapper.map(refEvenmentDto, refEvenement);
			List<RefEvenement> refEvenementList = refEvenementDao.findAdvanced(
					etabId, refEvenement);
			if (refEvenementList != null && !refEvenementList.isEmpty()) {
				log.debug("contrat list success with size = {} ", new Object[]{refEvenementList.size()});

				for (RefEvenement currentRefEvenement : refEvenementList) {
					RefEvenementDto currentRefEvenementDto = new RefEvenementDto();
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(
									currentRefEvenement.getId(),
									RefEvenement.class.getSimpleName());
					mapper.map(currentRefEvenement, currentRefEvenementDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, currentRefEvenementDto);
					}
					refEvenementListDto.add(currentRefEvenementDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refEvenementListDto;
	}

	@Override
	public List<RefEvenementDto> findByCodeType(Integer etabId,
			String codeType, Integer year) {
		List<RefEvenementDto> refEvenementListDto = new ArrayList<RefEvenementDto>();
		try {
			RefEvenement refEvenement = new RefEvenement();
			
			List<RefEvenement> refEvenementList = refEvenementDao.findByCodeType(
					etabId, codeType, year);
			if (refEvenementList != null && !refEvenementList.isEmpty()) {
				log.debug("contrat list success with size = {} ", new Object[]{refEvenementList.size()});

				for (RefEvenement currentRefEvenement : refEvenementList) {
					RefEvenementDto currentRefEvenementDto = new RefEvenementDto();
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(
									currentRefEvenement.getId(),
									RefEvenement.class.getSimpleName());
					mapper.map(currentRefEvenement, currentRefEvenementDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, currentRefEvenementDto);
					}
					refEvenementListDto.add(currentRefEvenementDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refEvenementListDto;
	}

}
