/**
 * [dz.gov.mesrs.sii.referentiel.business.service.impl.RefAffectationServiceImpl.java] 
 * @author BELDI Jamel on : 16 janv. 2014  15:33:05
 */
package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.util.ArrayList;
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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefAffectationDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAffectation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectationDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefAffectationService;

/**
 * @author BELDI Jamel on : 16 janv. 2014 15:33:05
 */
@Service("refAffectationServiceImpl")
public class RefAffectationServiceImpl implements RefAffectationService {
	private static final Logger log = LoggerFactory.getLogger(RefAffectationServiceImpl.class.getName());
	@Autowired
	@Qualifier("refAffectationDaoImpl")
	private RefAffectationDao refAffectationDao;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.Mapper mapper;
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * [RefAffectationServiceImpl.refAffectationDao] Getter
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 17:40:26
	 * @return the refAffectationDao
	 */
	public RefAffectationDao getRefAffectationDao() {
		return refAffectationDao;
	}

	/**
	 * [RefAffectationServiceImpl.refAffectationDao] Setter
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 17:40:26
	 * @param refAffectationDao
	 *            the refAffectationDao to set
	 */
	public void setRefAffectationDao(RefAffectationDao refAffectationDao) {
		this.refAffectationDao = refAffectationDao;
	}

	/**
	 * [RefAffectationServiceImpl.mapper] Getter
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 17:40:26
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefAffectationServiceImpl.mapper] Setter
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 17:40:26
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * @param transientInstance
	 * @see dz.gov.mesrs.sii.commons.data.dao.referentiel.RefAffectationDao#persist(dz.gov.mesrs.sii.commons.data.model.referentiel.RefAffectation)
	 */
	public void persist(RefAffectation transientInstance) {
		refAffectationDao.persist(transientInstance);
	}

	/**
	 * @param persistentInstance
	 * @see dz.gov.mesrs.sii.commons.data.dao.referentiel.RefAffectationDao#remove(dz.gov.mesrs.sii.commons.data.model.referentiel.RefAffectation)
	 */
	public void remove(RefAffectation persistentInstance) {
		refAffectationDao.remove(persistentInstance);
	}

	/**
	 * @param detachedInstance
	 * @return
	 * @see dz.gov.mesrs.sii.commons.data.dao.referentiel.RefAffectationDao#merge(dz.gov.mesrs.sii.commons.data.model.referentiel.RefAffectation)
	 */
	public RefAffectation merge(RefAffectation detachedInstance) {
		return refAffectationDao.merge(detachedInstance);
	}

	/**
	 * [RefAffectationServiceImpl.RefAffectationServiceImpl()] Constructor
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 15:33:05
	 */
	public RefAffectationServiceImpl() {

	}

	/**
	 * [RefAffectationServiceImpl.save] save new RefAffectation
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 17:40:26
	 * @param the
	 *            RefAffectationDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void save(RefAffectationDto refAffectationDto) {
		RefAffectation refAffectation = new RefAffectation();
		try {
			mapper.map(refAffectationDto, refAffectation);

			refAffectationDao.persist(refAffectation);
			log.debug("save successful");
		} catch (javax.persistence.PersistenceException cve) {
			throw cve;

		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}

	}

	/**
	 * [RefAffectationServiceImpl.update] update new RefAffectation
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 17:40:26
	 * @param the
	 *            RefAffectationDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void update(RefAffectationDto refAffectationDto) {
		RefAffectation refAffectation = new RefAffectation();
		try {
			mapper.map(refAffectationDto, refAffectation);
			refAffectationDao.merge(refAffectation);
			log.debug("update successful");
		} catch (RuntimeException e) {
			log.error("update failed", e);
			throw e;
		}

	}

	/**
	 * [RefAffectationServiceImpl.findById] find the RefAffectation by Identity
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 17:40:26
	 * @param the
	 *            Id
	 * @return the RefAffectationDto
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public RefAffectationDto findById(int id) {
		RefAffectationDto refAffectationDto = null;
		try {
			RefAffectation refAffectation = refAffectationDao.findById(id);

			if (refAffectation != null) {

				refAffectationDto = new RefAffectationDto();
				mapper.map(refAffectation, refAffectationDto);

			}

		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refAffectationDto;

	}

	/**
	 * [RefAffectationServiceImpl.delete] delete the RefAffectation
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 17:40:26
	 * @param the
	 *            RefAffectationDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void delete(RefAffectationDto refAffectationDto) {
		RefAffectation refAffectation = new RefAffectation();
		try {
			mapper.map(refAffectationDto, refAffectation);
			refAffectation = refAffectationDao.merge(refAffectation);
			refAffectationDao.remove(refAffectation);
			log.debug("delete successful");
		} catch (RuntimeException e) {
			log.error("delete failed", e);
			throw e;
		}

	}

	/**
	 * [RefAffectationServiceImpl.findStructures] Find the STRUCTURES Affected
	 * to INDIVIDU or the STRUCTURES of GROUPE
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 17:40:26
	 * @param the
	 *            entity (individu, groupe), the idEntity
	 * @return List of RefAffectationDto
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public List<RefAffectationDto> findStructures(String entity,
			Integer idEntity) {
		List<RefAffectationDto> refAffectationListDto = new ArrayList<RefAffectationDto>();
		try {
			List<RefAffectation> refAffectationList = null;
			if (entity != null && idEntity != null && entity.equals("individu")) {
				refAffectationList = (List<RefAffectation>) refAffectationDao
						.findStructuresForIndividu(idEntity);
			} else if (entity != null && idEntity != null
					&& entity.equals("groupe")) {
				refAffectationList = (List<RefAffectation>) refAffectationDao
						.findStructuresOfGroupe(idEntity);
			} else if (entity != null && idEntity != null
					&& entity.equals("evenement")) {
				refAffectationList = (List<RefAffectation>) refAffectationDao
						.findStructuresForEvenement(idEntity);
			} else {
				return refAffectationListDto;
			}
			if (refAffectationList != null) {
				log.debug("affectation list success with size {}  ", new Object[]{ refAffectationList.size()});
				for (RefAffectation currentRefAffectation : refAffectationList) {
					RefAffectationDto refAffectationDto = new RefAffectationDto();
					mapper.map(currentRefAffectation, refAffectationDto);
					refAffectationListDto.add(refAffectationDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refAffectationListDto;
	}

	/**
	 * [RefAffectationServiceImpl.findGroupes] Find the GROUPES Affected to
	 * INDIVIDU or the GROUPES Affected to STRUCTURE
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 17:40:26
	 * @param the
	 *            entity , the idEntity
	 * @return List of RefAffectationDto
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public List<RefAffectationDto> findGroupes(String entity, Integer idEntity) {
		List<RefAffectationDto> refAffectationListDto = new ArrayList<RefAffectationDto>();
		try {
			List<RefAffectation> refAffectationList = null;
			if (entity != null && idEntity != null && entity.equals("individu")) {
				refAffectationList = (List<RefAffectation>) refAffectationDao
						.findGroupesForIndividu(idEntity);
			} else if (entity != null && idEntity != null
					&& entity.equals("structure")) {
				refAffectationList = (List<RefAffectation>) refAffectationDao
						.findGroupesForStructure(idEntity);
			} else if (entity != null && idEntity != null
					&& entity.equals("groupe")) {
				refAffectationList = (List<RefAffectation>) refAffectationDao
						.findGroupesofGroupe(idEntity);
			} else if (entity != null && idEntity != null
					&& entity.equals("evenement")) {
				refAffectationList = (List<RefAffectation>) refAffectationDao
						.findGroupesForEvenement(idEntity);
			} else {
				return refAffectationListDto;
			}
			if (refAffectationList != null) {
				log.debug("affectation list success with size {}  ", new Object[]{ refAffectationList.size()});
				for (RefAffectation currentRefAffectation : refAffectationList) {
					RefAffectationDto refAffectationDto = new RefAffectationDto();
					mapper.map(currentRefAffectation, refAffectationDto);
					refAffectationListDto.add(refAffectationDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refAffectationListDto;
	}
	
	/**
	 * [RefAffectationServiceImpl.findDomaines] Find the Domaine Affected to
	 *  to STRUCTURE
	 * 
	 * @author BELBRIK Oualid on : 30 sept. 2014 10:40:26
	 * @param the
	 *            entity , the idEntity
	 * @return List of RefAffectationDto
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public List<RefAffectationDto> findDomaines(String entity, Integer idEntity) {
		List<RefAffectationDto> refAffectationListDto = new ArrayList<RefAffectationDto>();
		try {
			List<RefAffectation> refAffectationList = null;
			 if (entity != null && idEntity != null
					&& entity.equals("structure")) {
				refAffectationList = (List<RefAffectation>) refAffectationDao
						.findDomainesForStructure(idEntity);
			} else {
				return refAffectationListDto;
			}
			if (refAffectationList != null) {
				log.debug("affectation list success with size {} ", new Object[]{ refAffectationList.size()});
				for (RefAffectation currentRefAffectation : refAffectationList) {
					RefAffectationDto refAffectationDto = new RefAffectationDto();
					mapper.map(currentRefAffectation, refAffectationDto);
					refAffectationListDto.add(refAffectationDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refAffectationListDto;
	}
	
	
	/**
	 * [RefAffectationServiceImpl.findFilieres] Find the Filiere Affected to
	 *  to STRUCTURE
	 * 
	 * @author BELBRIK Oualid on : 30 sept. 2014 16:40:26
	 * @param the
	 *            entity , the idEntity
	 * @return List of RefAffectationDto
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public List<RefAffectationDto> findFilieres(String entity, Integer idEntity) {
		List<RefAffectationDto> refAffectationListDto = new ArrayList<RefAffectationDto>();
		try {
			List<RefAffectation> refAffectationList = null;
			 if (entity != null && idEntity != null
					&& entity.equals("structure")) {
				refAffectationList = (List<RefAffectation>) refAffectationDao
						.findFilieresForStructure(idEntity);
			} else {
				return refAffectationListDto;
			}
			if (refAffectationList != null) {
				log.debug("affectation list success with size {}  ", new Object[]{ refAffectationList.size()});
				for (RefAffectation currentRefAffectation : refAffectationList) {
					RefAffectationDto refAffectationDto = new RefAffectationDto();
					mapper.map(currentRefAffectation, refAffectationDto);
					refAffectationListDto.add(refAffectationDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refAffectationListDto;
	}


	/**
	 * [RefAffectationServiceImpl.findIndividus] Find the INDIVIDUS of GROUPE or
	 * the INDIVIDUS of STRUCTURE
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 17:40:26
	 * @param the
	 *            entity , the idEntity
	 * @return List of RefAffectationDto
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public List<RefAffectationDto> findIndividus(String entity, Integer idEntity) {
		List<RefAffectationDto> refAffectationListDto = new ArrayList<RefAffectationDto>();
		try {
			List<RefAffectation> refAffectationList = null;
			if (entity != null && idEntity != null
					&& entity.equals("structure")) {
				refAffectationList = (List<RefAffectation>) refAffectationDao
						.findIndividusOfStructure(idEntity);
			} else if (entity != null && idEntity != null
					&& entity.equals("groupe")) {
				refAffectationList = (List<RefAffectation>) refAffectationDao
						.findIndividusOfGroupe(idEntity);
			} else if (entity != null && idEntity != null
					&& entity.equals("evenement")) {
				refAffectationList = (List<RefAffectation>) refAffectationDao
						.findIndividusOfEvenement(idEntity);
			} else {
				return refAffectationListDto;
			}
			if (refAffectationList != null && !refAffectationList.isEmpty()) {
				log.debug("affectation list success with size {}  ", new Object[]{ refAffectationList.size()});
				for (RefAffectation currentRefAffectation : refAffectationList) {
					RefAffectationDto refAffectationDto = new RefAffectationDto();
					mapper.map(currentRefAffectation, refAffectationDto);
					refAffectationListDto.add(refAffectationDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refAffectationListDto;
	}

	@Override
	public List<RefAffectationDto> findAffectationByIdIndividu(Integer id) {
		List<RefAffectationDto> refAffectationDtos = null;
		try {
			List<RefAffectation> refAffectations = refAffectationDao
					.findAffectationByIdIndividu(id);

			refAffectationDtos = new ArrayList<RefAffectationDto>();

			for (RefAffectation refAffectation : refAffectations) {
				refAffectationDtos.add(mapper.map(refAffectation,
						RefAffectationDto.class));
			}
		} catch (RuntimeException e) {
			log.error("get RefPermissionDto list failed", e);
			throw e;
		}
		log.info("get RefPermissionDto list successed");
		return refAffectationDtos;
	}

	@Override
	public List<RefAffectationDto> findAffectationByIdIndividu(
			Integer etabId, Integer id) {
		List<RefAffectationDto> refAffectationDtos = null;
		try {
			List<RefAffectation> refAffectations = refAffectationDao
					.findAffectationByIdIndividu(etabId, id);

			refAffectationDtos = new ArrayList<RefAffectationDto>();

			for (RefAffectation refAffectation : refAffectations) {
				refAffectationDtos.add(mapper.map(refAffectation,
						RefAffectationDto.class));
			}
		} catch (RuntimeException e) {
			log.error("get RefPermissionDto list failed", e);
			throw e;
		}
		log.info("get RefPermissionDto list successed");
		return refAffectationDtos;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public RefAffectationDto saveOrUpdate(RefAffectationDto refAffectationDto) {
		try {
			if (refAffectationDto == null)
				return null;
			RefAffectation refAffectation = new RefAffectation();
			mapper.map(refAffectationDto, refAffectation);
			if (refAffectationDto.getId() != null) {

				refAffectation = refAffectationDao.merge(refAffectation);
			} else {
				refAffectationDao.merge(refAffectation);
			}
			if (refAffectation != null) {
				log.debug(" saveOrUpdate success");
				mapper.map(refAffectation, refAffectationDto);
				return refAffectationDto;
			}

		} catch (RuntimeException e) {
			log.error("saveOrUpdate failed", e);
			throw e;
		}

		return null;

	}

	@Override
	public RefAffectationDto findGroupeParent(String entity, String codeGroupe) {
		if (entity == null || codeGroupe == null) {
			return null;
		}
		RefAffectationDto refAffectationDto = null;
		try {
			RefAffectation refAffectation = null;
			if (entity.equals("groupe")) {
				refAffectation = refAffectationDao
						.findGroupeParentForGroupe(codeGroupe);
			}
			if (refAffectation != null) {
				refAffectationDto = new RefAffectationDto();
				mapper.map(refAffectation, refAffectationDto);
			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refAffectationDto;
	}

	@Override
	public List<RefAffectationDto> findIndividus(String entity,
			String codeEntity) {
		List<RefAffectationDto> refAffectationListDto = new ArrayList<RefAffectationDto>();
		try {
			List<RefAffectation> refAffectationList = null;
			if (entity != null && codeEntity != null
					&& entity.equals("structure")) {
				refAffectationList = (List<RefAffectation>) refAffectationDao
						.findIndividusOfStructure(codeEntity);
			} else if (entity != null && codeEntity != null
					&& entity.equals("groupe")) {
				refAffectationList = (List<RefAffectation>) refAffectationDao
						.findIndividusOfGroupe(codeEntity);
			} else if (entity != null && codeEntity != null
					&& entity.equals("evenement")) {
				refAffectationList = (List<RefAffectation>) refAffectationDao
						.findIndividusOfEvenement(codeEntity);
			} else {
				return refAffectationListDto;
			}
			if (refAffectationList != null && !refAffectationList.isEmpty()) {
				log.debug("affectation list success with size {}  ", new Object[]{ refAffectationList.size()});
				for (RefAffectation currentRefAffectation : refAffectationList) {
					RefAffectationDto refAffectationDto = new RefAffectationDto();
					mapper.map(currentRefAffectation, refAffectationDto);
					refAffectationListDto.add(refAffectationDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refAffectationListDto;
	}

	@Override
	public RefAffectationDto findGroupeParent(String entity, Integer idGroupe) {
		if (entity == null || idGroupe == null) {
			return null;
		}
		RefAffectationDto refAffectationDto = null;
		try {
			RefAffectation refAffectation = null;
			if (entity.equals("groupe")) {
				refAffectation = refAffectationDao
						.findGroupeParentForGroupe(idGroupe);
			}
			if (refAffectation != null) {
				refAffectationDto = new RefAffectationDto();
				mapper.map(refAffectation, refAffectationDto);
			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refAffectationDto;
	}

	@Override
	public List<RefAffectationDto> findStructuresByIndividuAndRole(
			Integer idIndividu, Integer idRole) {
		List<RefAffectationDto> refAffectationListDto = new ArrayList<RefAffectationDto>();
		try {
			if (idIndividu==null || idRole==null){
				return refAffectationListDto;	
			}
			List<RefAffectation> refAffectationList = null;
			refAffectationList = (List<RefAffectation>) refAffectationDao.findStructuresByIndividuAndRole(idIndividu, idRole);
			if (refAffectationList != null) {
				log.debug("affectation list success with size {}  ", new Object[]{ refAffectationList.size()});
				for (RefAffectation currentRefAffectation : refAffectationList) {
					RefAffectationDto refAffectationDto = new RefAffectationDto();
					mapper.map(currentRefAffectation, refAffectationDto);
					refAffectationListDto.add(refAffectationDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refAffectationListDto;
	}

	@Override
	public List<RefAffectationDto> findAffectationByIdGroupe(Integer etabId,
			Integer idGroupe) {
		
		List<RefAffectationDto> refAffectationDtos = null;
		try {
				List<RefAffectation> refAffectations = refAffectationDao.findAffectationByIdGroupe(etabId, idGroupe);
				refAffectationDtos = new ArrayList<RefAffectationDto>();
				for (RefAffectation refAffectation : refAffectations) {
					RefAffectationDto refAffectationDto  = new RefAffectationDto();
					mapper.map(refAffectation, refAffectationDto);
					refAffectationDtos.add(refAffectationDto);
			}
			return refAffectationDtos;
		} catch (RuntimeException e) {
			log.error("findAffectationByIdGroupe failed", e);
			throw e;
		}
		
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefAffectationService#findIndividuByRole(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<RefIndividuDto> findIndividuByRole(Integer idEtab,
			Integer idRole) {
		List<RefIndividuDto> refIndividuDtos = null;
		try {
				List<RefIndividu> refIndividus = refAffectationDao.findIndividuByRole(idEtab, idRole);
				refIndividuDtos = new ArrayList<RefIndividuDto>();
				for (RefIndividu refIndividu : refIndividus) {
					RefIndividuDto refIndividuDto  = new RefIndividuDto();
					mapper.map(refIndividu, refIndividuDto);
					refIndividuDtos.add(refIndividuDto);
			}
			return refIndividuDtos;
		} catch (RuntimeException e) {
			log.error("findIndividuByRole failed", e);
			throw e;
		}
	}

}
