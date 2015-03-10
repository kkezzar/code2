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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefFiliereLmdDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefSituationDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAffectFiliereLmdEtab;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEntiteSituation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefFiliereLmd;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSituation;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectFiliereLmdEtabDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFiliereLmdDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefFiliereLmdService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametrageService;

/**
 * @author BELBRIK Oualid on : 21 04. 2014 10:59:45
 */
@Service("refFiliereLmdServiceImpl")
public class RefFiliereLmdServiceImpl implements RefFiliereLmdService {

	/**
	 * [RefFiliereLmdServiceImpl.RefFiliereLmdServiceImpl()] Constructor
	 * 
	 * @author BELBRIK Oualid on : 21 04. 2014 10:59:45
	 */
	private static final Logger log = LoggerFactory.getLogger(RefFiliereLmdServiceImpl.class.getName());
	@Autowired
	@Qualifier("refFiliereLmdDaoImpl")
	private RefFiliereLmdDao refFiliereLmdDao;
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
	 * [RefFiliereLmdServiceImpl.getRefParametrageServiceImpl] Method
	 * 
	 * @author BELBRIK Oualid on : 15 avr. 2014 11:41:07
	 * @return
	 */
	public RefParametrageService getRefParametrageServiceImpl() {
		return refParametrageServiceImpl;
	}

	/**
	 * [RefFiliereLmdServiceImpl.setRefParametrageServiceImpl] Method
	 * 
	 * @author BELBRIK Oualid on : 15 avr. 2014 11:41:11
	 * @param refParametrageServiceImpl
	 */
	public void setRefParametrageServiceImpl(
			RefParametrageService refParametrageServiceImpl) {
		this.refParametrageServiceImpl = refParametrageServiceImpl;
	}

	public RefFiliereLmdServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<RefFiliereLmdDto> findGeneric(String value) {
		List<RefFiliereLmdDto> refFiliereLmdListDto = new ArrayList<RefFiliereLmdDto>();
		try {
			List<RefFiliereLmd> refFiliereLmdList = refFiliereLmdDao
					.findGeneric(value);
			if (refFiliereLmdList != null) {
				log.debug("stucture list success with size =  {}", new Object[]{refFiliereLmdList.size()});

				for (RefFiliereLmd currentRefFiliereLmd : refFiliereLmdList) {
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(currentRefFiliereLmd.getId(),
									RefFiliereLmd.class.getSimpleName());
					RefFiliereLmdDto refFiliereLmdDto = new RefFiliereLmdDto();
					mapper.map(currentRefFiliereLmd, refFiliereLmdDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, refFiliereLmdDto);
					}
					refFiliereLmdListDto.add(refFiliereLmdDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refFiliereLmdListDto;
	}

	public List<RefFiliereLmdDto> findAdvanced(RefFiliereLmdDto refFiliereLmdDto) {
		List<RefFiliereLmdDto> refFiliereLmdListDto = new ArrayList<RefFiliereLmdDto>();
		try {
			RefFiliereLmd refFiliereLmd = new RefFiliereLmd();
			mapper.map(refFiliereLmdDto, refFiliereLmd);
			List<RefFiliereLmd> refFiliereLmdList = refFiliereLmdDao
					.findAdvanced(refFiliereLmd);
			if (refFiliereLmdList != null && !refFiliereLmdList.isEmpty()) {
				log.debug("Filiere list success with size = {} ", new Object[]{refFiliereLmdList.size()});

				for (RefFiliereLmd currentRefFiliereLmd : refFiliereLmdList) {
					RefFiliereLmdDto currentRefFiliereLmdDto = new RefFiliereLmdDto();
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(currentRefFiliereLmd.getId(),
									RefFiliereLmd.class.getSimpleName());
					mapper.map(currentRefFiliereLmd, currentRefFiliereLmdDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, currentRefFiliereLmdDto);
					}
					refFiliereLmdListDto.add(currentRefFiliereLmdDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refFiliereLmdListDto;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public RefFiliereLmdDto save(RefFiliereLmdDto refFiliereLmdDto) {
		log.info("service saveFiliere");
		RefFiliereLmd refFiliereLmd = new RefFiliereLmd();
		mapper.map(refFiliereLmdDto, refFiliereLmd);
		try {
			refFiliereLmdDao.persist(refFiliereLmd);
			RefSituation refSituation = new RefSituation();
			refSituation.setDateSituation(new Date());
			refSituation.setIdEntite(refFiliereLmd.getId());
			refSituation.setNomEntite(RefFiliereLmd.class.getSimpleName());
			RefEntiteSituation refEntiteSituation = new RefEntiteSituation();
			refEntiteSituation.setId(36/*Integer.parseInt(refParametrageServiceImpl
					.getParamConfiguration(
							UtilConstant.FILIERELMD_SITUATION_CREE_ID_KEY,
							UtilConstant.CONFIGURATION_UTIL).getValue())*/);
			refSituation.setRefEntiteSituation(refEntiteSituation);
			refSituationDao.persist(refSituation);
			mapper.map(refFiliereLmd, refFiliereLmdDto);
			log.debug("save successful");
			return refFiliereLmdDto;
		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void update(RefFiliereLmdDto refFiliereLmdDto) {
		RefFiliereLmd refFiliereLmd = new RefFiliereLmd();
		mapper.map(refFiliereLmdDto, refFiliereLmd);
		try {

			refFiliereLmdDao.merge(refFiliereLmd);
			log.debug("update successful");
		} catch (RuntimeException e) {
			log.error("update failed", e);
			throw e;
		}

	}

	@Override
	public RefFiliereLmdDto findById(int id) {
		try {
			RefFiliereLmd refFiliereLmd = refFiliereLmdDao.findById(id);
			if (refFiliereLmd != null) {
				log.debug(" refFiliereLmdDao.findById(id) success with id =  {}", new Object[]{refFiliereLmd.getId()});
				RefFiliereLmdDto refFiliereLmdDto = new RefFiliereLmdDto();
				mapper.map(refFiliereLmd, refFiliereLmdDto);
				return refFiliereLmdDto;
			}

		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}

		return null;
	}

	@Override
	public RefFiliereLmdDto findByIdentifiant(String identifiant) {
		try {
			RefFiliereLmd refFiliereLmd = refFiliereLmdDao
					.findByIdentifiant(identifiant);

			if (refFiliereLmd != null) {
				log.debug("findByIdentifiant success with identifiant =  {}", new Object[]{refFiliereLmd.getIdentifiant()});
				RefFiliereLmdDto refFiliereLmdDto = new RefFiliereLmdDto();
				mapper.map(refFiliereLmd, refFiliereLmdDto);
				return refFiliereLmdDto;
			}
		} catch (RuntimeException e) {
			log.error("findByIdentifiant failed", e);
			throw e;
		}

		return null;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void delete(RefFiliereLmdDto refFiliereLmdDto) {
		RefFiliereLmd refFiliereLmd = new RefFiliereLmd();
		try {
			mapper.map(refFiliereLmdDto, refFiliereLmd);
			refFiliereLmd = refFiliereLmdDao.merge(refFiliereLmd);
			refFiliereLmdDao.remove(refFiliereLmd);
			log.debug("remove FiliereLmd successful");
		} catch (RuntimeException e) {
			log.error("remove failed", e);
			throw e;
		}

	}

	/**
	 * [RefFiliereLmdServiceImpl.refFiliereLmdDao] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 02. 2014 11:01:53
	 * @return the refFiliereLmdDao
	 */
	public RefFiliereLmdDao getRefFiliereLmdDao() {
		return refFiliereLmdDao;
	}

	/**
	 * [RefFiliereLmdServiceImpl.refFiliereLmdDao] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 02. 2014 11:01:53
	 * @param refFiliereLmdDao
	 *            the refFiliereLmdDao to set
	 */
	public void setRefFiliereLmdDao(RefFiliereLmdDao refFiliereLmdDao) {
		this.refFiliereLmdDao = refFiliereLmdDao;
	}

	/**
	 * [RefFiliereLmdServiceImpl.mapper] Getter
	 * 
	 * @author BELBRIK Oualid on : 11 02. 2014 11:01:53
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefFiliereLmdServiceImpl.mapper] Setter
	 * 
	 * @author BELBRIK Oualid on : 11 02. 2014 11:01:53
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<RefFiliereLmdDto> findAll() {
		List<RefFiliereLmdDto> refFiliereLmdListDto = new ArrayList<RefFiliereLmdDto>();
		try {
			List<RefFiliereLmd> refFiliereLmdList = refFiliereLmdDao.findAll();
			if (refFiliereLmdList != null) {
				log.debug("Filiere list success with size = {} ", new Object[]{refFiliereLmdList.size()});
				for (RefFiliereLmd currentRefFiliereLmd : refFiliereLmdList) {
					RefFiliereLmdDto refFiliereLmdDto = new RefFiliereLmdDto();
					mapper.map(currentRefFiliereLmd, refFiliereLmdDto);
					refFiliereLmdListDto.add(refFiliereLmdDto);
				}
				return refFiliereLmdListDto;

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
	public void saveOrUpdate(RefFiliereLmdDto refFiliereLmdDto) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<RefFiliereLmdDto> findByCodeDomainelmd(String value) {

		List<RefFiliereLmdDto> refFiliereLmdListDto = new ArrayList<RefFiliereLmdDto>();
		try {
			List<RefFiliereLmd> refFiliereLmdList = (List<RefFiliereLmd>) refFiliereLmdDao
					.findByCodeDomainelmd(value);
			if (refFiliereLmdList != null) {
				log.debug("Filiere list success with size = {} ", new Object[]{refFiliereLmdList.size()});
				for (RefFiliereLmd currentRefFiliereLmd : refFiliereLmdList) {
					RefFiliereLmdDto refFiliereLmdDto = new RefFiliereLmdDto();
					mapper.map(currentRefFiliereLmd, refFiliereLmdDto);
					refFiliereLmdListDto.add(refFiliereLmdDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refFiliereLmdListDto;

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<RefFiliereLmdDto> findByIdDomainelmd(Integer value) {
		List<RefFiliereLmdDto> refFiliereLmdListDto = new ArrayList<RefFiliereLmdDto>();
		try {
			List<RefFiliereLmd> refFiliereLmdList = (List<RefFiliereLmd>) refFiliereLmdDao
					.findByIdDomainelmd(value);
			if (refFiliereLmdList != null) {
				log.debug("Filiere list success with size = {} ", new Object[]{refFiliereLmdList.size()});
				for (RefFiliereLmd currentRefFiliereLmd : refFiliereLmdList) {
					RefFiliereLmdDto refFiliereLmdDto = new RefFiliereLmdDto();
					mapper.map(currentRefFiliereLmd, refFiliereLmdDto);
					refFiliereLmdListDto.add(refFiliereLmdDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refFiliereLmdListDto;
	}
	
	
	@Override
	public List<RefAffectFiliereLmdEtabDto> findFilieresLMDByEtablissement(int idEtab,  String codeDomaine) {
		
		List<RefAffectFiliereLmdEtabDto> refAffectFiliereLmdEtabDtos = new ArrayList<RefAffectFiliereLmdEtabDto>();
		try {
			List<RefAffectFiliereLmdEtab> refAffectFiliereLmdEtabs = refFiliereLmdDao.findFilieresLMDByEtab(idEtab, codeDomaine);
			
			if (refAffectFiliereLmdEtabs != null) {
				log.debug("findFilieresLMDByEtablissement list success with size =  {}", new Object[]{refAffectFiliereLmdEtabs.size()});
				
				for (RefAffectFiliereLmdEtab refAffectFiliereLmdEtab : refAffectFiliereLmdEtabs) {
					
					RefAffectFiliereLmdEtabDto refAffectFiliereLmdEtabDto = new RefAffectFiliereLmdEtabDto();
					mapper.map(refAffectFiliereLmdEtab, refAffectFiliereLmdEtabDto);
					refAffectFiliereLmdEtabDtos.add(refAffectFiliereLmdEtabDto);
				}
				return refAffectFiliereLmdEtabDtos;

			}
		} catch (RuntimeException e) {
			log.error("findFilieresLMDByEtablissement failed", e);
			throw e;
		}

		return null;
	}

	@Override
	public List<RefAffectFiliereLmdEtabDto> findFilieresLMDByCodeEtablissement(String codeEtab) {
		
		List<RefAffectFiliereLmdEtabDto> refAffectFiliereLmdEtabDtos = new ArrayList<RefAffectFiliereLmdEtabDto>();
		try {
			List<RefAffectFiliereLmdEtab> refAffectFiliereLmdEtabs = refFiliereLmdDao.findFilieresLMDByCodeEtab(codeEtab);
			
			if (refAffectFiliereLmdEtabs != null) {
				log.debug("findFilieresLMDByEtablissement list success with size =  {}", new Object[]{refAffectFiliereLmdEtabs.size()});
				
				for (RefAffectFiliereLmdEtab refAffectFiliereLmdEtab : refAffectFiliereLmdEtabs) {
					
					RefAffectFiliereLmdEtabDto refAffectFiliereLmdEtabDto = new RefAffectFiliereLmdEtabDto();
					mapper.map(refAffectFiliereLmdEtab, refAffectFiliereLmdEtabDto);
					refAffectFiliereLmdEtabDtos.add(refAffectFiliereLmdEtabDto);
				}
				return refAffectFiliereLmdEtabDtos;

			}
		} catch (RuntimeException e) {
			log.error("findFilieresLMDByEtablissement failed", e);
			throw e;
		}

		return null;
	}





	

}
