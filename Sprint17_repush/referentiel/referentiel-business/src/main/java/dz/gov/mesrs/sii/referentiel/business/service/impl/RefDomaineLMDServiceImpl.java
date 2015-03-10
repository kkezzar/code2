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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefDomaineLMDDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefSituationDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAffectDomLmdEtab;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefDomaineLMD;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEntiteSituation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSituation;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectDomLmdEtabDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineLMDDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefDomaineLMDService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametrageService;

/**
 * @author BELBRIK Oualid on : 11 04. 2014 10:59:45
 */
@Service("refDomaineLMDServiceImpl")
public class RefDomaineLMDServiceImpl implements RefDomaineLMDService {

	/**
	 * [RefDomaineLMDServiceImpl.RefDomaineLMDServiceImpl()] Constructor
	 * 
	 * @author BELBRIK Oualid on : 11 04. 2014 10:59:45
	 */
	private static final Logger log = LoggerFactory.getLogger(RefDomaineLMDServiceImpl.class.getName()); 
	@Autowired
	@Qualifier("refDomaineLMDDaoImpl")
	private RefDomaineLMDDao refDomaineLMDDao;
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
	 * [RefDomaineLMDServiceImpl.getRefParametrageServiceImpl] Method 
	 * @author BELBRIK Oualid  on : 15 avr. 2014  11:41:07
	 * @return
	 */
	public RefParametrageService getRefParametrageServiceImpl() {
		return refParametrageServiceImpl;
	}

	
	/**
	 * [RefDomaineLMDServiceImpl.setRefParametrageServiceImpl] Method 
	 * @author BELBRIK Oualid  on : 15 avr. 2014  11:41:11
	 * @param refParametrageServiceImpl
	 */
	public void setRefParametrageServiceImpl(
			RefParametrageService refParametrageServiceImpl) {
		this.refParametrageServiceImpl = refParametrageServiceImpl;
	}


	public RefDomaineLMDServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public List<RefDomaineLMDDto> findGeneric(String value) { 
		List<RefDomaineLMDDto> refDomaineLMDListDto = new ArrayList<RefDomaineLMDDto>();
		try {
			List<RefDomaineLMD> refDomaineLMDList = refDomaineLMDDao.findGeneric(value);
			if (refDomaineLMDList != null) {
				log.debug("stucture list success with size {}  ", new Object[]{refDomaineLMDList.size()});

				for (RefDomaineLMD currentRefDomaineLMD : refDomaineLMDList) {
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(
									currentRefDomaineLMD.getId(),
									RefDomaineLMD.class.getSimpleName());
					RefDomaineLMDDto refDomaineLMDDto = new RefDomaineLMDDto();
					mapper.map(currentRefDomaineLMD, refDomaineLMDDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, refDomaineLMDDto);
					}
					refDomaineLMDListDto.add(refDomaineLMDDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refDomaineLMDListDto;
	}

	
	public List<RefDomaineLMDDto> findAdvanced(RefDomaineLMDDto refDomaineLMDDto) {
		List<RefDomaineLMDDto> refDomaineLMDListDto = new ArrayList<RefDomaineLMDDto>();
		try {
			RefDomaineLMD refDomaineLMD = new RefDomaineLMD();
			mapper.map(refDomaineLMDDto, refDomaineLMD);
			List<RefDomaineLMD> refDomaineLMDList = refDomaineLMDDao
					.findAdvanced(refDomaineLMD);
			if (refDomaineLMDList != null && !refDomaineLMDList.isEmpty()) {
				log.debug("Domaine list success with size {}  ", new Object[]{refDomaineLMDList.size()});

				for (RefDomaineLMD currentRefDomaineLMD : refDomaineLMDList) {
					RefDomaineLMDDto currentRefDomaineLMDDto = new RefDomaineLMDDto();
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(
									currentRefDomaineLMD.getId(),
									RefDomaineLMD.class.getSimpleName());
					mapper.map(currentRefDomaineLMD, currentRefDomaineLMDDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, currentRefDomaineLMDDto);
					}
					refDomaineLMDListDto.add(currentRefDomaineLMDDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refDomaineLMDListDto;
	}

	
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public RefDomaineLMDDto save(RefDomaineLMDDto refDomaineLMDDto) {
		log.info("service saveDomaine");
		RefDomaineLMD refDomaineLMD = new RefDomaineLMD();
		mapper.map(refDomaineLMDDto, refDomaineLMD);
		try {
			refDomaineLMDDao.persist(refDomaineLMD);			
			RefSituation refSituation = new RefSituation();
			refSituation.setDateSituation(new Date());
			refSituation.setIdEntite(refDomaineLMD.getId());
			refSituation.setNomEntite(RefDomaineLMD.class.getSimpleName());
			RefEntiteSituation refEntiteSituation = new RefEntiteSituation();
			refEntiteSituation.setId(33/*Integer.parseInt(refParametrageServiceImpl.getParamConfiguration(UtilConstant.DOMAINELMD_SITUATION_CREE_ID_KEY, UtilConstant.CONFIGURATION_UTIL).getValue())*/);
			refSituation.setRefEntiteSituation(refEntiteSituation);
			refSituationDao.persist(refSituation);
			mapper.map(refDomaineLMD, refDomaineLMDDto);
			log.debug("save successful");
			return refDomaineLMDDto;
		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}

	}

	
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void update(RefDomaineLMDDto refDomaineLMDDto) {
		RefDomaineLMD refDomaineLMD = new RefDomaineLMD();
		mapper.map(refDomaineLMDDto, refDomaineLMD);
		try {
			
			refDomaineLMDDao.merge(refDomaineLMD);
			log.debug("update successful");
		} catch (RuntimeException e) {
			log.error("update failed", e);
			throw e;
		}

	}

	
	@Override
	public void saveOrUpdate(RefDomaineLMDDto refDomaineLMDDto) {
		// TODO Auto-generated method stub

	}

	@Override
	public RefDomaineLMDDto findById(int id) {
		try {
			RefDomaineLMD refDomaineLMD = refDomaineLMDDao.findById(id);
			if (refDomaineLMD != null) {
				log.debug(" refDomaineLMDDao.findById(id) success with id {}  ", new Object[]{refDomaineLMD.getId()});
				RefDomaineLMDDto refDomaineLMDDto = new RefDomaineLMDDto();
				mapper.map(refDomaineLMD, refDomaineLMDDto);
				return refDomaineLMDDto;
			}

		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}

		return null;
	} 

	@Override
	public RefDomaineLMDDto findByIdentifiant(String identifiant) {
		try {
			RefDomaineLMD refDomaineLMD = refDomaineLMDDao.findByIdentifiant(identifiant);
			if (refDomaineLMD != null) {
				log.debug(" findByIdentifiant success with id = {} ", new Object[]{refDomaineLMD.getIdentifiant()});
				RefDomaineLMDDto refDomaineLMDDto = new RefDomaineLMDDto();
				mapper.map(refDomaineLMD, refDomaineLMDDto);
				return refDomaineLMDDto;
			}

		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}

		return null;
	}	
	
	
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void delete(RefDomaineLMDDto refDomaineLMDDto) {
		RefDomaineLMD refDomaineLMD = new RefDomaineLMD();
		try {
			mapper.map(refDomaineLMDDto, refDomaineLMD);
			refDomaineLMD = refDomaineLMDDao.merge(refDomaineLMD);
			refDomaineLMDDao.remove(refDomaineLMD);
			log.debug("remove DomaineLMD successful");
		} catch (RuntimeException e) {
			log.error("remove failed", e);
			throw e;
		}

	}

	/**
	 * [RefDomaineLMDServiceImpl.refDomaineLMDDao] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 02. 2014 11:01:53
	 * @return the refDomaineLMDDao
	 */
	public RefDomaineLMDDao getRefDomaineLMDDao() {
		return refDomaineLMDDao;
	}

	/**
	 * [RefDomaineLMDServiceImpl.refDomaineLMDDao] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 02. 2014 11:01:53
	 * @param refDomaineLMDDao
	 *            the refDomaineLMDDao to set
	 */
	public void setRefDomaineLMDDao(RefDomaineLMDDao refDomaineLMDDao) {
		this.refDomaineLMDDao = refDomaineLMDDao;
	}

	/**
	 * [RefDomaineLMDServiceImpl.mapper] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 02. 2014 11:01:53
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefDomaineLMDServiceImpl.mapper] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 02. 2014 11:01:53
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}


	@Override
	public List<RefDomaineLMDDto> findAll() {
		List<RefDomaineLMDDto> refDomaineLMDListDto = new ArrayList<RefDomaineLMDDto>();
		try {
			List<RefDomaineLMD> refDomaineLMDList = refDomaineLMDDao.findAll();
			if (refDomaineLMDList != null) {
				log.debug("Domaine list success with size = {} ", new Object[]{refDomaineLMDList.size()});
				for (RefDomaineLMD currentRefDomaineLMD : refDomaineLMDList) {
					RefDomaineLMDDto refDomaineLMDDto = new RefDomaineLMDDto();
					mapper.map(currentRefDomaineLMD, refDomaineLMDDto);
					refDomaineLMDListDto.add(refDomaineLMDDto);
				}
				return refDomaineLMDListDto;

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}

		return null;

	}


	public RefSituationDao getRefSituationDao() {
		return refSituationDao;
	}


	public void setRefSituationDao(RefSituationDao refSituationDao) {
		this.refSituationDao = refSituationDao;
	}


	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<RefAffectDomLmdEtabDto> findDomainesLMDByEtablissement(Integer idEtab) {
		
		List<RefAffectDomLmdEtabDto> refAffectDomLmdEtabDtos = new ArrayList<RefAffectDomLmdEtabDto>();
		try {
			List<RefAffectDomLmdEtab> refAffectDomLmdEtabs = refDomaineLMDDao.findDomainesLMDByEtab(idEtab);
			
			if (refAffectDomLmdEtabs != null) {
				log.debug("findDomainesLMDByEtablissement list success with size = {} ", new Object[]{refAffectDomLmdEtabs.size()});
				
				for (RefAffectDomLmdEtab refAffectDomLmdEtab : refAffectDomLmdEtabs) {
					
					RefAffectDomLmdEtabDto refAffectDomLmdEtabDto = new RefAffectDomLmdEtabDto();
					mapper.map(refAffectDomLmdEtab, refAffectDomLmdEtabDto);
					refAffectDomLmdEtabDtos.add(refAffectDomLmdEtabDto);
				}
				return refAffectDomLmdEtabDtos;

			}
		} catch (RuntimeException e) {
			log.error("findDomainesLMDByEtablissement failed", e);
			throw e;
		}

		return null;
	}


	@Override
	public List<RefAffectDomLmdEtabDto> findDomainesLMDByCodeEtablissement(String codeEtab) {
		
		List<RefAffectDomLmdEtabDto> refAffectDomLmdEtabDtos = new ArrayList<RefAffectDomLmdEtabDto>();
		try {
			List<RefAffectDomLmdEtab> refAffectDomLmdEtabs = refDomaineLMDDao.findDomainesLMDByCodeEtab(codeEtab);
			
			if (refAffectDomLmdEtabs != null) {
				log.debug("findDomainesLMDByCodeEtablissement list success with size =  {}", new Object[]{refAffectDomLmdEtabs.size()});
				
				for (RefAffectDomLmdEtab refAffectDomLmdEtab : refAffectDomLmdEtabs) {
					
					RefAffectDomLmdEtabDto refAffectDomLmdEtabDto = new RefAffectDomLmdEtabDto();
					mapper.map(refAffectDomLmdEtab, refAffectDomLmdEtabDto);
					refAffectDomLmdEtabDtos.add(refAffectDomLmdEtabDto);
				}
				return refAffectDomLmdEtabDtos;

			}
		} catch (RuntimeException e) {
			log.error("findDomainesLMDByCodeEtablissement failed", e);
			throw e;
		}

		return null;
	}
	
	/**
	 * [RefDomaineLMDService.findDomainesByEtablissement] Method 
	 * @author rlaib  on : 18 nov. 2014  11:59:01
	 * @param idEtab
	 * @return
	 */
	@Override
	public List<RefDomaineLMDDto> findDomainesByEtablissement(Integer idEtab) {
		List<RefDomaineLMDDto> result = new ArrayList<RefDomaineLMDDto>();
		try {
				List<RefDomaineLMD> refDomaineLMDs = refDomaineLMDDao.findDomainesByEtab(idEtab);
				for (RefDomaineLMD refDomaineLMD : refDomaineLMDs) {
					
					RefDomaineLMDDto refDomaineLMDDto = new RefDomaineLMDDto();
					mapper.map(refDomaineLMD, refDomaineLMDDto);
					result.add(refDomaineLMDDto);
				}
				return result;
			
		} catch (RuntimeException e) {
			log.error("findDomainesByEtablissement failed", e);
			throw e;
		}
	}


	
	
	
}
