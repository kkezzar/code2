/**
 * [dz.gov.mesrs.sii.referentiel.business.service.impl.RefEtablissementServiceImpl.java] 
 * @author MAKERRI Sofiane on : 10 avr. 2014  12:15:49
 */
package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefEtablissementDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefSituationDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEntiteSituation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSituation;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametrageService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane on : 10 avr. 2014 12:15:49
 */
@Service("refEtablissementServiceImpl")
public class RefEtablissementServiceImpl implements RefEtablissementService {

	private static final Log log = LogFactory
			.getLog(RefEtablissementServiceImpl.class);
	@Autowired
	@Qualifier("refEtablissementDaoImpl")
	private RefEtablissementDao refEtablissementDaoImpl;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.Mapper mapper;
	@Autowired
	@Qualifier("refSituationDaoImpl")
	private RefSituationDao refSituationDao;
	@Autowired
	@Qualifier("refParametrageServiceImpl")
	private RefParametrageService refParametrageServiceImpl;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService
	 * #findAll()
	 */
	@Override
	public List<RefEtablissementDto> findAll() {
		List<RefEtablissementDto> refEtablissementDtos = new ArrayList<RefEtablissementDto>();
		try {
			List<RefEtablissement> refEtablissementList = refEtablissementDaoImpl
					.findAll();
			if (refEtablissementList != null) {
				log.debug("etablissement list success with size =  "
						+ refEtablissementList.size());
				for (RefEtablissement currentRefEtablissement : refEtablissementList) {
					RefEtablissementDto refEtablissementDto = new RefEtablissementDto();
					mapper.map(currentRefEtablissement, refEtablissementDto);
					refEtablissementDtos.add(refEtablissementDto);
				}
				return refEtablissementDtos;

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
	 * dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService
	 * #findGeneric(java.lang.String)
	 */
	@Override
	public List<RefEtablissementDto> findGeneric(String value) {
		List<RefEtablissementDto> refEtablissementListDto = new ArrayList<RefEtablissementDto>();
		try {
			List<RefEtablissement> refEtablissementList = refEtablissementDaoImpl
					.findGeneric(value);
			if (refEtablissementList != null) {
				log.debug("Etablissement list success with size =  "
						+ refEtablissementList.size());

				for (RefEtablissement currentRefEtablissement : refEtablissementList) {
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(
									currentRefEtablissement.getId(),
									RefEtablissement.class.getSimpleName());
					RefEtablissementDto refEtablissementDto = new RefEtablissementDto();
					mapper.map(currentRefEtablissement, refEtablissementDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, refEtablissementDto);
					}
					refEtablissementListDto.add(refEtablissementDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get Etablissement list failed", e);
			throw e;
		}
		return refEtablissementListDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService
	 * #findAdvanced
	 * (dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto)
	 */
	@Override
	public List<RefEtablissementDto> findAdvanced(
			RefEtablissementDto refEtablissementDto) {
		List<RefEtablissementDto> refEtablissementListDto = new ArrayList<RefEtablissementDto>();
		try {
			RefEtablissement refEtablissement = new RefEtablissement();
			mapper.map(refEtablissementDto, refEtablissement);
			List<RefEtablissement> refEtablissementList = refEtablissementDaoImpl
					.findAdvanced(refEtablissement);
			if (refEtablissementList != null) {
				log.debug("Etablissement list success with size =  "
						+ refEtablissementList.size());

				for (RefEtablissement currentRefEtablissement : refEtablissementList) {
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(
									currentRefEtablissement.getId(),
									RefEtablissement.class.getSimpleName());
					// RefEtablissementDto refEtablissementDto = new
					// RefEtablissementDto();
					mapper.map(currentRefEtablissement, refEtablissementDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, refEtablissementDto);
					}
					refEtablissementListDto.add(refEtablissementDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get Etablissement list failed", e);
			throw e;
		}
		return refEtablissementListDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService
	 * #save
	 * (dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public RefEtablissementDto save(RefEtablissementDto refEtablissementDto) {
		RefEtablissement refEtablissement = new RefEtablissement();
		mapper.map(refEtablissementDto, refEtablissement);
		try {
			refEtablissementDaoImpl.persist(refEtablissement);
			RefSituation refSituation = new RefSituation();
			refSituation.setDateSituation(new Date());
			refSituation.setIdEntite(refEtablissement.getId());
			refSituation.setNomEntite(RefEtablissement.class.getSimpleName());
			RefEntiteSituation refEntiteSituation = new RefEntiteSituation();
			refEntiteSituation.setId(30/*Integer.parseInt(refParametrageServiceImpl
					.getParamConfiguration(
							UtilConstant.ETABLISSEMENT_SITUATION_CREE_ID_KEY,
							UtilConstant.CONFIGURATION_UTIL).getValue())*/);
			refSituation.setRefEntiteSituation(refEntiteSituation);
			refSituationDao.persist(refSituation);
			log.debug("save successful");
			mapper.map(refEtablissement, refEtablissementDto);
			log.debug("save successful");
		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}

		return refEtablissementDto;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService
	 * #update
	 * (dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void update(RefEtablissementDto refEtablissementDto) {
		RefEtablissement refEtablissement = new RefEtablissement();
		mapper.map(refEtablissementDto, refEtablissement);
		try {
			refEtablissementDaoImpl.merge(refEtablissement);
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
	 * dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService
	 * #saveOrUpdate
	 * (dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto)
	 */
	@Override
	public void saveOrUpdate(RefEtablissementDto refEtablissementDto) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService
	 * #findById(java.lang.String)
	 */
	@Override
	public RefEtablissementDto findById(Integer id) {
		try {
			RefEtablissement refEtablissement = refEtablissementDaoImpl
					.findById(id);
			if (refEtablissement != null) {
				log.debug("RefEtablissementDto findById success with id =  "
						+ id);
				RefEtablissementDto refEtablissementDto = new RefEtablissementDto();
				mapper.map(refEtablissement, refEtablissementDto);
				return refEtablissementDto;
			}

		} catch (RuntimeException e) {
			log.error("RefEtablissementDto findById failed", e);
			throw e;
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService
	 * #delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {

	}

	/**
	 * [RefEtablissementServiceImpl.refEtablissementDaoImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:17:30
	 * @return the refEtablissementDaoImpl
	 */
	public RefEtablissementDao getRefEtablissementDaoImpl() {
		return refEtablissementDaoImpl;
	}

	/**
	 * [RefEtablissementServiceImpl.refEtablissementDaoImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:17:30
	 * @param refEtablissementDaoImpl
	 *            the refEtablissementDaoImpl to set
	 */
	public void setRefEtablissementDaoImpl(
			RefEtablissementDao refEtablissementDaoImpl) {
		this.refEtablissementDaoImpl = refEtablissementDaoImpl;
	}

	/**
	 * [RefEtablissementServiceImpl.refSituationDao] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:17:30
	 * @return the refSituationDao
	 */
	public RefSituationDao getRefSituationDao() {
		return refSituationDao;
	}

	/**
	 * [RefEtablissementServiceImpl.refSituationDao] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 avr. 2014 12:17:30
	 * @param refSituationDao
	 *            the refSituationDao to set
	 */
	public void setRefSituationDao(RefSituationDao refSituationDao) {
		this.refSituationDao = refSituationDao;
	}

	/**
	 * [RefEtablissementServiceImpl.refParametrageServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 avr. 2014 16:15:24
	 * @return the refParametrageServiceImpl
	 */
	public RefParametrageService getRefParametrageServiceImpl() {
		return refParametrageServiceImpl;
	}

	/**
	 * [RefEtablissementServiceImpl.refParametrageServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 avr. 2014 16:15:24
	 * @param refParametrageServiceImpl
	 *            the refParametrageServiceImpl to set
	 */
	public void setRefParametrageServiceImpl(
			RefParametrageService refParametrageServiceImpl) {
		this.refParametrageServiceImpl = refParametrageServiceImpl;
	}

	/**
	 * [RefEtablissementServiceImpl.mapper] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 avr. 2014 16:36:24
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefEtablissementServiceImpl.mapper] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 avr. 2014 16:36:24
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public RefEtablissementDto findByLlLatin(String llLatin) {
		try {
			RefEtablissement refEtablissement = refEtablissementDaoImpl
					.findByLlLatin(llLatin);
			if (refEtablissement != null) {
				log.debug("RefEtablissementDto findByllLatin success with id =  "
						+ llLatin);
				RefEtablissementDto refEtablissementDto = new RefEtablissementDto();
				mapper.map(refEtablissement, refEtablissementDto);
				return refEtablissementDto;
			}

		} catch (RuntimeException e) {
			log.error("RefEtablissementDto findByllLatin failed", e);
			throw e;
		}

		return null;
	}

	@Override
	public RefEtablissementDto findByLlArabe(String llArabe) {
		try {
			RefEtablissement refEtablissement = refEtablissementDaoImpl
					.findByLlArabe(llArabe);
			if (refEtablissement != null) {
				log.debug("RefEtablissementDto findByllArabe success with id =  "
						+ llArabe);
				RefEtablissementDto refEtablissementDto = new RefEtablissementDto();
				mapper.map(refEtablissement, refEtablissementDto);
				return refEtablissementDto;
			}

		} catch (RuntimeException e) {
			log.error("RefEtablissementDto findByllArabe failed", e);
			throw e;
		}

		return null;
	}

	@Override
	public RefEtablissementDto findByLcLatin(String lcLatin) {
		try {
			RefEtablissement refEtablissement = refEtablissementDaoImpl
					.findByLcLatin(lcLatin);
			if (refEtablissement != null) {
				log.debug("RefEtablissementDto findBylcLatin success with id =  "
						+ lcLatin);
				RefEtablissementDto refEtablissementDto = new RefEtablissementDto();
				mapper.map(refEtablissement, refEtablissementDto);
				return refEtablissementDto;
			}

		} catch (RuntimeException e) {
			log.error("RefEtablissementDto findBylcLatin failed", e);
			throw e;
		}

		return null;
	}

	@Override
	public RefEtablissementDto findByLcArabe(String lcArabe) {
		try {
			RefEtablissement refEtablissement = refEtablissementDaoImpl
					.findByLcArabe(lcArabe);
			if (refEtablissement != null) {
				log.debug("RefEtablissementDto findBylcArabe success with id =  "
						+ lcArabe);
				RefEtablissementDto refEtablissementDto = new RefEtablissementDto();
				mapper.map(refEtablissement, refEtablissementDto);
				return refEtablissementDto;
			}

		} catch (RuntimeException e) {
			log.error("RefEtablissementDto findBylcArabe failed", e);
			throw e;
		}

		return null;
	}

	@Override
	public RefEtablissementDto findByIdentifiant(String identifiant) {
		try {
			RefEtablissement refEtablissement = refEtablissementDaoImpl
					.findByIdentifiant(identifiant);
			if (refEtablissement != null) {
				log.debug("RefEtablissementDto findByIdentifiant success with id =  "
						+ identifiant);
				RefEtablissementDto refEtablissementDto = new RefEtablissementDto();
				mapper.map(refEtablissement, refEtablissementDto);
				return refEtablissementDto;
			}

		} catch (RuntimeException e) {
			log.error("RefEtablissementDto findByIdentifiant failed", e);
			throw e;
		}

		return null;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void delete(RefEtablissementDto refEtablissementDto) {
		try {
			RefEtablissement refEtablissement = mapper.map(refEtablissementDto,
					RefEtablissement.class);
			refEtablissement = refEtablissementDaoImpl.merge(refEtablissement);
			refEtablissementDaoImpl.remove(refEtablissement);
			log.debug("remove refEtablissementDto successful");
		} catch (RuntimeException e) {
			log.error("remove refEtablissementDto failed", e);
			throw e;
		}

	}

	@Override
	public String generateIdentify(String prefix, int orderLength) {
		try {
			if (prefix == null) {
				return null;
			}

			Integer lastOrder = refEtablissementDaoImpl.findLastOrder(prefix,
					orderLength);
			if (lastOrder == null) {
				return null;
			}
			lastOrder++;
			prefix = prefix
					+ RefUtilConstant.formatOrder(lastOrder.toString(),
							orderLength);
			log.debug("calculateIdentify successful");
		} catch (RuntimeException e) {
			log.error("calculateIdentify failed", e);
			throw e;
		}
		return prefix;	
	}

}
