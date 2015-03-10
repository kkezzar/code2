/**
 * 
 */
package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefContratDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefSituationDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefContrat;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEntiteSituation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSituation;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefContratDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefContratService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametrageService;

/**
 * @author jbeldi
 * 
 */
@Service("refContratServiceImpl")
public class RefContratServiceImpl implements RefContratService {
	private static final Logger log = LoggerFactory.getLogger(RefContratServiceImpl.class.getName());
	@Autowired
	@Qualifier("refContratDaoImpl")
	private RefContratDao refContratDao;
	@Autowired
	@Qualifier("refSituationDaoImpl")
	private RefSituationDao refSituationDao;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.Mapper mapper;
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	@Qualifier("refParametrageServiceImpl")
	private RefParametrageService refParametrageServiceImpl;
	/**
	 * @return the refContratDao
	 */
	public RefContratDao getRefContratDao() {
		return refContratDao;
	}

	/**
	 * @param refContratDao
	 *            the refContratDao to set
	 */
	public void setRefContratDao(RefContratDao refContratDao) {
		this.refContratDao = refContratDao;
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
	public RefContratServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefContratService#findGeneric
	 * (java.lang.String)
	 */
	@Override
	public List<RefContratDto> findGeneric(String value, boolean avenant) {
		List<RefContratDto> refContratListDto = new ArrayList<RefContratDto>();
		try {
			List<RefContrat> refContratList = refContratDao.findGeneric(value, avenant);
			if (refContratList != null) {
				log.debug("contrat list success with size {}  ", new Object[]{ refContratList.size()});

				for (RefContrat currentRefContrat : refContratList) {
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(
									currentRefContrat.getId(),
									RefContrat.class.getSimpleName());
					RefContratDto refContratDto = new RefContratDto();
					mapper.map(currentRefContrat, refContratDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, refContratDto);
					}
					refContratListDto.add(refContratDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refContratListDto;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefContratService#findAdvanced
	 * (dz.gov.mesrs.sii.referentiel.business.model.dto.RefContratDto)
	 */
	@Override
	public List<RefContratDto> findAdvanced(RefContratDto refContratDto, boolean avenant) {

		List<RefContratDto> refContratListDto = new ArrayList<RefContratDto>();
		try {
			RefContrat refContrat = new RefContrat();
			mapper.map(refContratDto, refContrat);
			List<RefContrat> refContratList = refContratDao
					.findAdvanced(refContrat, avenant);
			if (refContratList != null && !refContratList.isEmpty()) {
				log.debug("contrat list success with size {}  ", new Object[]{ refContratList.size()});

				for (RefContrat currentRefContrat : refContratList) {
					RefContratDto currentRefContratDto = new RefContratDto();
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(
									currentRefContrat.getId(),
									RefContrat.class.getSimpleName());
					mapper.map(currentRefContrat, currentRefContratDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, currentRefContratDto);
					}
					// currentRefContratDto =
					// ConverterService.toRefContratDto(currentRefContrat,
					// refCurrentSituation);
					refContratListDto.add(currentRefContratDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refContratListDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefContratService#save(
	 * dz.gov.mesrs.sii.referentiel.business.model.dto.RefContratDto)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public RefContratDto save(RefContratDto refContratDto, boolean avenant) {
		RefContrat refContrat = new RefContrat();
		refContratDto.setDateCreation(new Date());
		refContratDto.setAvenant(avenant);
		mapper.map(refContratDto, refContrat);
		
		try {
			refContratDao.persist(refContrat);
			RefSituation refSituation = new RefSituation();
			refSituation.setDateSituation(new Date());
			refSituation.setIdEntite(refContrat.getId());
			refSituation.setNomEntite(RefContrat.class.getSimpleName());
			RefEntiteSituation refEntiteSituation = new RefEntiteSituation();
			refEntiteSituation.setId(10/*Integer.parseInt(refParametrageServiceImpl.getParamConfiguration(UtilConstant.CONTRAT_SITUATION_CREE_ID_KEY, UtilConstant.CONFIGURATION_UTIL).getValue())*/);
			refSituation.setRefEntiteSituation(refEntiteSituation);
			refSituationDao.persist(refSituation);
			log.debug("save successful");
			mapper.map(refContrat, refContratDto);
		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}
		return refContratDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefContratService#update
	 * (dz.gov.mesrs.sii.referentiel.business.model.dto.RefContratDto)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void update(RefContratDto refContratDto) {
		RefContrat refContrat = new RefContrat();
		mapper.map(refContratDto, refContrat);
		try {
			refContratDao.merge(refContrat);
			log.debug("update successful");
		} catch (RuntimeException e) {
			log.error("update failed", e);
			throw e;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefContratService#saveOrUpdate
	 * (dz.gov.mesrs.sii.referentiel.business.model.dto.RefContratDto)
	 */
	@Override
	public void saveOrUpdate(RefContratDto refContratDto) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefContratService#findById
	 * (int)
	 */
	@Override
	public RefContratDto findById(Integer id) {
		try {
			RefContrat refContrat = refContratDao.findById(id);
			if (refContrat != null) {
				log.debug("contrat findById success with id {} ", new Object[]{ id});
				return mapper.map(refContrat, RefContratDto.class);	
			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefContratService#delete
	 * (int)
	 */
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	/**
	 * [RefContratServiceImpl.refParametrageServiceImpl] Getter 
	 * @author BELDI Jamel on : 7 avr. 2014  14:32:22
	 * @return the refParametrageServiceImpl
	 */
	public RefParametrageService getRefParametrageServiceImpl() {
		return refParametrageServiceImpl;
	}

	/**
	 * [RefContratServiceImpl.refParametrageServiceImpl] Setter 
	 * @author BELDI Jamel on : 7 avr. 2014  14:32:22
	 * @param refParametrageServiceImpl the refParametrageServiceImpl to set
	 */
	public void setRefParametrageServiceImpl(
			RefParametrageService refParametrageServiceImpl) {
		this.refParametrageServiceImpl = refParametrageServiceImpl;
	}

	@Override
	public List<RefContratDto> findGeneric(Integer etabId, String value,
			boolean avenant) {
		List<RefContratDto> refContratListDto = new ArrayList<RefContratDto>();
		try {
			List<RefContrat> refContratList = refContratDao.findGeneric(etabId, value, avenant);
			if (refContratList != null) {
				log.debug("contrat list success with size {}  ", new Object[]{ refContratList.size()});

				for (RefContrat currentRefContrat : refContratList) {
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(
									currentRefContrat.getId(),
									RefContrat.class.getSimpleName());
					RefContratDto refContratDto = new RefContratDto();
					mapper.map(currentRefContrat, refContratDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, refContratDto);
					}
					refContratListDto.add(refContratDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refContratListDto;


	}

	@Override
	public List<RefContratDto> findAdvanced(Integer etabId,
			RefContratDto refContratDto, boolean avenant) {
		List<RefContratDto> refContratListDto = new ArrayList<RefContratDto>();
		try {
			RefContrat refContrat = new RefContrat();
			mapper.map(refContratDto, refContrat);
			List<RefContrat> refContratList = refContratDao
					.findAdvanced(etabId, refContrat, avenant);
			if (refContratList != null && !refContratList.isEmpty()) {
				log.debug("contrat list success with size {}  ", new Object[]{ refContratList.size()});

				for (RefContrat currentRefContrat : refContratList) {
					RefContratDto currentRefContratDto = new RefContratDto();
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(
									currentRefContrat.getId(),
									RefContrat.class.getSimpleName());
					mapper.map(currentRefContrat, currentRefContratDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, currentRefContratDto);
					}
					refContratListDto.add(currentRefContratDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refContratListDto;
	}

	@Override
	public RefContratDto findByCode(String identifiant) {
		try {
			RefContrat refContrat = refContratDao.findByCode(identifiant);
			if (refContrat != null) {
				log.debug("contrat findById success with identifiant {} ", new Object[]{  identifiant});
				return mapper.map(refContrat, RefContratDto.class);	
			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return null;
	}
	
	

}
