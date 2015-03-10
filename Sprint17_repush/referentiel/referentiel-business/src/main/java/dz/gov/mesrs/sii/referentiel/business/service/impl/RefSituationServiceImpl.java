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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefEntiteSituationDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefSituationDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEntiteSituation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSituation;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefSituationDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefSituationService;

@Service("refSituationServiceImpl")
public class RefSituationServiceImpl implements RefSituationService {

	private static final Logger log = LoggerFactory.getLogger(RefSituationServiceImpl.class.getName());
	
	@Autowired
	@Qualifier("refSituationDaoImpl")
	private RefSituationDao refSituationDao;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.Mapper mapper;
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	@Qualifier("refEntiteSituationDaoImpl")
	private RefEntiteSituationDao refEntiteSituationDaoImpl;

	public RefSituationServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<RefSituationDto> findGeneric(String value) {
		return null;
	}

	@Override
	public List<RefSituationDto> findAdvanced(RefSituationDto refSituationDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(RefSituationDto refSituationDto) {
		// TODO Auto-generated method stub

	}

	@Override
	public RefSituationDto findById(String id) {
		try {
			RefSituation refSituation = refSituationDao.findById(id);
			if (refSituation != null) {
				log.debug(" refSituationDao.findById(id) success with id =  {}", new Object[]{refSituation.getId()});
				RefSituationDto refSituationDto = new RefSituationDto();
				mapper.map(refSituation, refSituationDto);
				return refSituationDto;
			}

		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}

		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	public RefSituationDao getRefSituationDao() {
		return refSituationDao;
	}

	public void setRefSituationDao(RefSituationDao refSituationDao) {
		this.refSituationDao = refSituationDao;
	}

	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<RefSituationDto> findAll() {
		List<RefSituationDto> refSituationListDto = new ArrayList<RefSituationDto>();
		try {
			List<RefSituation> refSituationList = refSituationDao.findAll();
			if (refSituationList != null) {
				log.debug("Situation list success with size =  {}", new Object[]{refSituationList.size()});
				for (RefSituation currentRefSituation : refSituationList) {
					RefSituationDto refSituationDto = new RefSituationDto();
					mapper.map(currentRefSituation, refSituationDto);
					refSituationListDto.add(refSituationDto);
				}
				return refSituationListDto;

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}

		return null;

	}

	@Override
	public List<RefSituationDto> findListSituationByEntityName(String entityName) {
		List<RefSituationDto> refSituationListDto = new ArrayList<RefSituationDto>();
		try {
			List<RefEntiteSituation> refEntiteSituationList = refEntiteSituationDaoImpl
					.findListSituationByEntityName(entityName);
			if (refEntiteSituationList != null) {
				log.debug("Situation list success with size = {} ", new Object[]{refEntiteSituationList.size()});
				for (RefEntiteSituation refEntiteSituation : refEntiteSituationList) {
					RefSituationDto refSituationDto = new RefSituationDto();
					mapper.map(refEntiteSituation, refSituationDto);
					refSituationListDto.add(refSituationDto);
				}
				return refSituationListDto;

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}

		return null;
	}

	public void persist(RefSituation transientInstance) {
		refSituationDao.persist(transientInstance);
	}

	public void remove(RefSituation persistentInstance) {
		refSituationDao.remove(persistentInstance);
	}

	public RefSituation merge(RefSituation detachedInstance) {
		return refSituationDao.merge(detachedInstance);
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void save(RefSituationDto refSituationDto) {
		RefSituation refSituation = new RefSituation();
		try {
			mapper.map(refSituationDto, refSituation);
			refSituation.setDateSituation(new Date());
			refSituation.setNomEntite(refSituationDto.getNomEntite());
			refSituationDao.persist(refSituation);
			log.debug("save successful");
		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void update(RefSituationDto refSituationDto) {
		RefSituation refSituation = new RefSituation();
		try {
			mapper.map(refSituationDto, refSituation);
			refSituationDao.merge(refSituation);
			log.debug("update successful");
		} catch (RuntimeException e) {
			log.error("update failed", e);
			throw e;
		}

	}


	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void delete(RefSituationDto refSituationDto) {
		RefSituation refSituation = new RefSituation();
		try {

			mapper.map(refSituationDto, refSituation);
			EntityManager refSituationDao = null;
			refSituation = refSituationDao.merge(refSituation);
			refSituationDao.remove(refSituation);
			log.debug("delete successful");
		} catch (RuntimeException e) {
			log.error("delete failed", e);
			throw e;
		}

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public List<RefSituationDto> findSituations(String entity, Integer idEntity) {
		List<RefSituationDto> refSituationListDto = new ArrayList<RefSituationDto>();
		try {
			List<RefSituation> refSituationList = null;

			if (entity != null && idEntity != null && entity.equals("individu")) {
				refSituationList = (List<RefSituation>) refSituationDao
						.findSituationsForIndividu(idEntity);
			} else if (entity != null && idEntity != null
					&& entity.equals("structure")) {
				refSituationList = (List<RefSituation>) refSituationDao
						.findSituationsForStructure(idEntity);
			} 
			else if (entity != null && idEntity != null
					&& entity.equals("groupe")) {
				refSituationList = (List<RefSituation>) refSituationDao
						.findSituationsForGroupe(idEntity);
			}
			else if (entity != null && idEntity != null
					&& entity.equals("contrat")) {
				refSituationList = (List<RefSituation>) refSituationDao
						.findSituationsForContrat(idEntity);
			}
			else if (entity != null && idEntity != null
					&& entity.equals("compte")) {
				refSituationList = (List<RefSituation>) refSituationDao
						.findSituationsForCompte(idEntity);
			}
			else if (entity != null && idEntity != null
					&& entity.equals("lieu")) {
				refSituationList = (List<RefSituation>) refSituationDao
						.findSituationsForLieu(idEntity);
			}
			else if (entity != null && idEntity != null
					&& entity.equals("evenement")) {
				refSituationList = (List<RefSituation>) refSituationDao
						.findSituationsForEvenement(idEntity);
			}
			else if (entity != null && idEntity != null
					&& entity.equals("equipement")) {
				refSituationList = (List<RefSituation>) refSituationDao
						.findSituationsForEquipement(idEntity);
			}
			else if (entity != null && idEntity != null
					&& entity.equals("reservation")) {
				refSituationList = (List<RefSituation>) refSituationDao
						.findSituationsForReservation(idEntity);
			}
			else if (entity != null && idEntity != null
					&& entity.equals("domainelmd")) {
				refSituationList = (List<RefSituation>) refSituationDao
						.findSituationsForDomaineLMD(idEntity);
			}
			else if (entity != null && idEntity != null
					&& entity.equals("etablissement")) {
				refSituationList = (List<RefSituation>) refSituationDao
						.findSituationsForEtablissement(idEntity);
			}
			else if (entity != null && idEntity != null
					&& entity.equals("filierelmd")) {
				refSituationList = (List<RefSituation>) refSituationDao
						.findSituationsForFiliereLmd(idEntity);
			}
			else if (entity != null && idEntity != null
					&& entity.equals("specialitelmd")) {
				refSituationList = (List<RefSituation>) refSituationDao
						.findSituationsForSpecialiteLmd(idEntity);
			}
			else {
				return refSituationListDto;
			}

			if (refSituationList != null) {
				log.debug("Situation list success with size = {} ", new Object[]{refSituationList.size()});
				for (RefSituation refSituation : refSituationList) {
					RefSituationDto refSituationDto = new RefSituationDto();
					mapper.map(refSituation, refSituationDto);
					refSituationListDto.add(refSituationDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refSituationListDto;
	}

	/**
	 * [RefSituationServiceImpl.refEntiteSituationDaoImpl] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 f�vr. 2014 12:57:41
	 * @return the refEntiteSituationDaoImpl
	 */
	public RefEntiteSituationDao getRefEntiteSituationDaoImpl() {
		return refEntiteSituationDaoImpl;
	}

	/**
	 * [RefSituationServiceImpl.refEntiteSituationDaoImpl] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 f�vr. 2014 12:57:41
	 * @param refEntiteSituationDaoImpl
	 *            the refEntiteSituationDaoImpl to set
	 */
	public void setRefEntiteSituationDaoImpl(
			RefEntiteSituationDao refEntiteSituationDaoImpl) {
		this.refEntiteSituationDaoImpl = refEntiteSituationDaoImpl;
	}

}
