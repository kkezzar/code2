/**
 * [dz.gov.mesrs.sii.referentiel.business.service.impl.RefGroupeServiceImpl.java] 
 * @author BELBRIK Oualid on : 6 janv. 2014  15:45:30
 */
package dz.gov.mesrs.sii.referentiel.business.service.impl;

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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefGroupeDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefSituationDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEntiteSituation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefGroupe;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSituation;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefGroupeDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefGroupeService;
import dz.gov.mesrs.sii.referentiel.business.service.RefHistoriqueService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametrageService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametreEtabService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author BELBRIK Oualid on : 6 janv. 2014 15:45:30
 */
@Service("refGroupeServiceImpl")
public class RefGroupeServiceImpl implements RefGroupeService {

	private static final Log log = LogFactory
			.getLog(RefGroupeServiceImpl.class);
	@Autowired
	@Qualifier("refGroupeDaoImpl")
	private RefGroupeDao refGroupeDao;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.Mapper mapper;
	@Autowired
	@Qualifier("refSituationDaoImpl")
	private RefSituationDao refSituationDao;
	@Autowired
	@Qualifier("refParametrageServiceImpl")
	private RefParametrageService refParametrageServiceImpl;
	@Autowired
	@Qualifier("refParametreEtabServiceImpl")
	private RefParametreEtabService refParametreEtabServiceImpl;
	private Integer identifiantLength = 0;
	
	@Autowired
	@Qualifier("refHistoriqueServiceImpl")
	private RefHistoriqueService refHistoriqueServiceImpl;
	

	/**
	 * [RefGroupeServiceImpl.refParametrageServiceImpl] Getter
	 * 
	 * @author BELDI Jamel on : 7 avr. 2014 14:59:45
	 * @return the refParametrageServiceImpl
	 */
	public RefParametrageService getRefParametrageServiceImpl() {
		return refParametrageServiceImpl;
	}

	/**
	 * [RefGroupeServiceImpl.refParametrageServiceImpl] Setter
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
	 * [RefGroupeServiceImpl.RefGroupeServiceImpl()] Constructor
	 * 
	 * @author BELBRIK Oualid on : 6 janv. 2014 15:45:30
	 */
	public RefGroupeServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefGroupeService#findGeneric
	 * (java.lang.String)
	 */
	@Override
	public List<RefGroupeDto> findGeneric(String value) {
		List<RefGroupeDto> refGroupeListDto = new ArrayList<RefGroupeDto>();
		try {
			List<RefGroupe> refGroupeList = refGroupeDao.findGeneric(value);
			if (refGroupeList != null) {
				log.debug("Groupe list success with size =  "
						+ refGroupeList.size());

				for (RefGroupe currentRefGroupe : refGroupeList) {
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(currentRefGroupe.getId(),
									RefGroupe.class.getSimpleName());
					RefGroupeDto refGroupeDto = new RefGroupeDto();
					mapper.map(currentRefGroupe, refGroupeDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, refGroupeDto);
					}
					refGroupeListDto.add(refGroupeDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refGroupeListDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefGroupeService#findAdvanced
	 * (dz.gov.mesrs.sii.referentiel.business.model.dto.RefGroupeDto)
	 */
	@Override
	public List<RefGroupeDto> findAdvanced(RefGroupeDto refGroupeDto) {
		List<RefGroupeDto> refGroupeListDto = new ArrayList<RefGroupeDto>();
		try {
			RefGroupe refGroupe = new RefGroupe();
			mapper.map(refGroupeDto, refGroupe);
			List<RefGroupe> refGroupeList = refGroupeDao
					.findAdvanced(refGroupe);
			if (refGroupeList != null && !refGroupeList.isEmpty()) {
				log.debug("Groupe list success with size =  "
						+ refGroupeList.size());

				for (RefGroupe currentRefGroupe : refGroupeList) {
					RefGroupeDto currentRefGroupeDto = new RefGroupeDto();
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(currentRefGroupe.getId(),
									RefGroupe.class.getSimpleName());
					mapper.map(currentRefGroupe, currentRefGroupeDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, currentRefGroupeDto);
					}
					refGroupeListDto.add(currentRefGroupeDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refGroupeListDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefGroupeService#save(dz
	 * .gov.mesrs.sii.referentiel.business.model.dto.RefGroupeDto)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public RefGroupeDto save(RefGroupeDto refGroupeDto) {
		log.info("service saveGroupe");
		RefGroupe refGroupe = new RefGroupe();
		mapper.map(refGroupeDto, refGroupe);
		try {
			refGroupeDao.persist(refGroupe);
			RefSituation refSituation = new RefSituation();
			refSituation.setDateSituation(new Date());
			refSituation.setIdEntite(refGroupe.getId());
			refSituation.setNomEntite(RefGroupe.class.getSimpleName());
			RefEntiteSituation refEntiteSituation = new RefEntiteSituation();
			refEntiteSituation.setId(7/*Integer.parseInt(refParametrageServiceImpl
					.getParamConfiguration(
							UtilConstant.GROUPE_SITUATION_CREE_ID_KEY,
							UtilConstant.CONFIGURATION_UTIL).getValue())*/);
			refSituation.setRefEntiteSituation(refEntiteSituation);
			refSituationDao.persist(refSituation);
			log.debug("save successful");
//			RefHistoriqueDto refHistoriqueDto = UtilConstant.getRefHistoriqueDto(refGroupeDto.getIdCompte(), refGroupeDto.getIdEtablissement(), refGroupeDto.getIdFonction(), refGroupe.getId(), UtilConstant.INSERT_ACTION);
//			refHistoriqueServiceImpl.save(refHistoriqueDto);
			mapper.map(refGroupe, refGroupeDto);
		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}
		return refGroupeDto;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefGroupeService#update
	 * (dz.gov.mesrs.sii.referentiel.business.model.dto.RefGroupeDto)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void update(RefGroupeDto refGroupeDto) {
		RefGroupe refGroupe = new RefGroupe();
		mapper.map(refGroupeDto, refGroupe);
		try {
			refGroupeDao.merge(refGroupe);
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
	 * dz.gov.mesrs.sii.referentiel.business.service.RefGroupeService#saveOrUpdate
	 * (dz.gov.mesrs.sii.referentiel.business.model.dto.RefGroupeDto)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public RefGroupeDto saveOrUpdate(RefGroupeDto refGroupeDto) {
		try {
			if (refGroupeDto == null)
				return null;
			RefGroupe refGroupe = new RefGroupe();
			
			if (refGroupeDto.getId() != null) {
				mapper.map(refGroupeDto, refGroupe);
				refGroupe = refGroupeDao.merge(refGroupe);
			} else {
				String idfGroupe = generateCode(refGroupeDto);
				if (idfGroupe == null || idfGroupe.length() > identifiantLength) {
					return null;
				}
				refGroupeDto.setIdentifiant(idfGroupe);
				mapper.map(refGroupeDto, refGroupe);
				refGroupeDao.persist(refGroupe);
			}
			if (refGroupe != null) {
				log.debug(" refGroupeDao.findById(id) success with id =  "
						+ refGroupe.getIdentifiant());
				mapper.map(refGroupe, refGroupeDto);
				return refGroupeDto;
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
	 * dz.gov.mesrs.sii.referentiel.business.service.RefGroupeService#findById
	 * (java.lang.String)
	 */
	@Override
	public RefGroupeDto findById(Integer id) {
		try {
			RefGroupe refGroupe = refGroupeDao.findById(id);
			if (refGroupe != null) {
				log.debug(" refGroupeDao.findById(id) success with id =  "
						+ refGroupe.getIdentifiant());
				RefGroupeDto refGroupeDto = new RefGroupeDto();
				mapper.map(refGroupe, refGroupeDto);
				return refGroupeDto;
			}

		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}

		return null;
	}

	/**
	 * [RefGroupeServiceImpl.refSituationDao] Getter
	 * 
	 * @author BELBRIK Oualid on : 2 fï¿½vr. 2014 16:40:04
	 * @return the refSituationDao
	 */
	public RefSituationDao getRefSituationDao() {
		return refSituationDao;
	}

	/**
	 * [RefGroupeServiceImpl.refSituationDao] Setter
	 * 
	 * @author BELBRIK Oualid on : 2 fï¿½vr. 2014 16:40:04
	 * @param refSituationDao
	 *            the refSituationDao to set
	 */
	public void setRefSituationDao(RefSituationDao refSituationDao) {
		this.refSituationDao = refSituationDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefGroupeService#delete
	 * (java.lang.String)
	 */
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	/**
	 * [RefGroupeServiceImpl.refGroupeDao] Getter
	 * 
	 * @author BELBRIK Oualid on : 6 janv. 2014 15:47:04
	 * @return the refGroupeDao
	 */
	public RefGroupeDao getRefGroupeDao() {
		return refGroupeDao;
	}

	/**
	 * [RefGroupeServiceImpl.refGroupeDao] Setter
	 * 
	 * @author BELBRIK Oualid on : 6 janv. 2014 15:47:04
	 * @param refGroupeDao
	 *            the refGroupeDao to set
	 */
	public void setRefGroupeDao(RefGroupeDao refGroupeDao) {
		this.refGroupeDao = refGroupeDao;
	}

	/**
	 * [RefGroupeServiceImpl.mapper] Getter
	 * 
	 * @author BELBRIK Oualid on : 6 janv. 2014 15:47:04
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefGroupeServiceImpl.mapper] Setter
	 * 
	 * @author BELBRIK Oualid on : 6 janv. 2014 15:47:04
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
	 * dz.gov.mesrs.sii.referentiel.business.service.RefGroupeService#findAll()
	 */
	@Override
	public List<RefGroupeDto> findAll() {
		List<RefGroupeDto> refGroupeListDto = new ArrayList<RefGroupeDto>();
		try {
			List<RefGroupe> refGroupeList = refGroupeDao.findAll();
			if (refGroupeList != null) {
				log.debug("groupe list success with size =  "
						+ refGroupeList.size());
				for (RefGroupe currentRefGroupe : refGroupeList) {
					RefGroupeDto refGroupeDto = new RefGroupeDto();
					mapper.map(currentRefGroupe, refGroupeDto);
					refGroupeListDto.add(refGroupeDto);
				}
				return refGroupeListDto;
			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}

		return null;
	}

	@Override
	public List<RefGroupeDto> findGeneric(Integer etabId, String value) {
		List<RefGroupeDto> refGroupeListDto = new ArrayList<RefGroupeDto>();
		try {
			List<RefGroupe> refGroupeList = refGroupeDao.findGeneric(
					etabId, value);
			if (refGroupeList != null) {
				log.debug("Groupe list success with size =  "
						+ refGroupeList.size());

				for (RefGroupe currentRefGroupe : refGroupeList) {
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(currentRefGroupe.getId(),
									RefGroupe.class.getSimpleName());
					RefGroupeDto refGroupeDto = new RefGroupeDto();
					mapper.map(currentRefGroupe, refGroupeDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, refGroupeDto);
					}
					refGroupeListDto.add(refGroupeDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refGroupeListDto;
	}

	@Override
	public List<RefGroupeDto> findAdvanced(Integer etabId,
			RefGroupeDto refGroupeDto) {
		List<RefGroupeDto> refGroupeListDto = new ArrayList<RefGroupeDto>();
		try {
			RefGroupe refGroupe = new RefGroupe();
			mapper.map(refGroupeDto, refGroupe);
			List<RefGroupe> refGroupeList = refGroupeDao.findAdvanced(
					etabId, refGroupe);
			if (refGroupeList != null && !refGroupeList.isEmpty()) {
				log.debug("Groupe list success with size =  "
						+ refGroupeList.size());

				for (RefGroupe currentRefGroupe : refGroupeList) {
					RefGroupeDto currentRefGroupeDto = new RefGroupeDto();
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(currentRefGroupe.getId(),
									RefGroupe.class.getSimpleName());
					mapper.map(currentRefGroupe, currentRefGroupeDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, currentRefGroupeDto);
					}
					refGroupeListDto.add(currentRefGroupeDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refGroupeListDto;
	}

	@Override
	public List<RefGroupeDto> findAll(Integer etabId) {
		List<RefGroupeDto> refGroupeListDto = new ArrayList<RefGroupeDto>();
		try {
			List<RefGroupe> refGroupeList = refGroupeDao.findAll(etabId);
			if (refGroupeList != null) {
				log.debug("groupe list success with size =  "
						+ refGroupeList.size());
				for (RefGroupe currentRefGroupe : refGroupeList) {
					RefGroupeDto refGroupeDto = new RefGroupeDto();
					mapper.map(currentRefGroupe, refGroupeDto);
					refGroupeListDto.add(refGroupeDto);
				}
				return refGroupeListDto;
			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}

		return null;
	}

	@Override
	public String generateIdentify(String prefix, int orderLength) {
		try {
			if (prefix == null) {
				return null;
			}

			Integer lastOrder = refGroupeDao.findLastOrder(prefix, orderLength);
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

	@Override
	public RefGroupeDto findByCode(String code) {
		try {
			RefGroupe refGroupe = refGroupeDao.findByCode(code);
			if (refGroupe != null) {
				log.debug(" findByCodes with code =  " + code);
				RefGroupeDto refGroupeDto = new RefGroupeDto();
				mapper.map(refGroupe, refGroupeDto);
				return refGroupeDto;
			}

		} catch (RuntimeException e) {
			log.error("findByCode failed", e);
			throw e;
		}

		return null;
	}
	
	public String generateCode(RefGroupeDto refGroupeDto) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(refGroupeDto.getDateCreation());
			Integer year = cal.get(Calendar.YEAR);

			String order = refParametreEtabServiceImpl
					.findValueOfKey(refGroupeDto.getIdEtablissement(), RefUtilConstant.CODIFICATION_GROUPE_ORDER_LENGTH_KEY);
			if (order == null) {
				return null;
			}
			int orderLength = Integer.parseInt(order);
			String _year = refParametreEtabServiceImpl
					.findValueOfKey(refGroupeDto.getIdEtablissement(), RefUtilConstant.CODIFICATION_GROUPE_YEAR_LENGTH_KEY);
			int yearLength;
			if (_year == null) {
				yearLength = 2;
			} else {
				yearLength = Integer.parseInt(_year);
			}

			String year2 = year.toString().substring(4 - yearLength);
			String prefix = refGroupeDto.getIdfEtablissement() + year2;
			int prefixLength = prefix.length();
			identifiantLength = prefixLength + yearLength + orderLength;
			return generateIdentify(prefix, orderLength);
		} catch (Exception e) {

		}
		return null;

	}

	/**
	 * [RefGroupeServiceImpl.refParametreEtabServiceImpl] Getter 
	 * @author MAKERRI Sofiane on : 11 juin 2014  18:50:38
	 * @return the refParametreEtabServiceImpl
	 */
	public RefParametreEtabService getRefParametreEtabServiceImpl() {
		return refParametreEtabServiceImpl;
	}

	/**
	 * [RefGroupeServiceImpl.refParametreEtabServiceImpl] Setter 
	 * @author MAKERRI Sofiane on : 11 juin 2014  18:50:38
	 * @param refParametreEtabServiceImpl the refParametreEtabServiceImpl to set
	 */
	public void setRefParametreEtabServiceImpl(
			RefParametreEtabService refParametreEtabServiceImpl) {
		this.refParametreEtabServiceImpl = refParametreEtabServiceImpl;
	}

	/**
	 * [RefGroupeServiceImpl.identifiantLength] Getter 


	 * @author MAKERRI Sofiane on : 12 juin 2014  08:07:57
	 * @return the identifiantLength
	 */
	public Integer getIdentifiantLength() {
		return identifiantLength;
	}

	/**
	 * [RefGroupeServiceImpl.identifiantLength] Setter 
	 * @author MAKERRI Sofiane on : 12 juin 2014  08:07:57
	 * @param identifiantLength the identifiantLength to set
	 */
	public void setIdentifiantLength(Integer identifiantLength) {
		this.identifiantLength = identifiantLength;
	}

	/**
	 * [RefGroupeServiceImpl.refHistoriqueServiceImpl] Getter 
	 * @author MAKERRI Sofiane on : 18 juin 2014  11:00:38
	 * @return the refHistoriqueServiceImpl
	 */
	public RefHistoriqueService getRefHistoriqueServiceImpl() {
		return refHistoriqueServiceImpl;
	}

	/**
	 * [RefGroupeServiceImpl.refHistoriqueServiceImpl] Setter 
	 * @author MAKERRI Sofiane on : 18 juin 2014  11:00:38
	 * @param refHistoriqueServiceImpl the refHistoriqueServiceImpl to set
	 */
	public void setRefHistoriqueServiceImpl(
			RefHistoriqueService refHistoriqueServiceImpl) {
		this.refHistoriqueServiceImpl = refHistoriqueServiceImpl;
	}

	@Override
	public String generateIdentify(Date dateCreation, Integer idEtab, String codeEtab) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateCreation);
			Integer year = cal.get(Calendar.YEAR);

			String order = refParametreEtabServiceImpl.findValueOfKey(
					idEtab,
					RefUtilConstant.CODIFICATION_GROUPE_ORDER_LENGTH_KEY);
			if (order == null) {
				return null;
			}
			int orderLength = Integer.parseInt(order);
			String _year = refParametreEtabServiceImpl.findValueOfKey(
					idEtab,
					RefUtilConstant.CODIFICATION_GROUPE_YEAR_LENGTH_KEY);
			int yearLength;
			if (_year == null) {
				yearLength = 2;
			} else {
				yearLength = Integer.parseInt(_year);
			}

			String year2 = year.toString().substring(4 - yearLength);
			String prefix = codeEtab + year2;
			int prefixLength = prefix.length();
			identifiantLength = prefixLength + yearLength + orderLength;
			return generateIdentify(prefix, orderLength);
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * [RefGroupeService.findByEtab] Method 
	 * Overridden By : @author rlaib  on : 18 déc. 2014  14:41:42
	 * @param idEtab
	 * @return
	 */
	@Override
	public List<RefGroupeDto> findByEtab(Integer idEtab) {
		
		List<RefGroupeDto> refGroupeDtos = new ArrayList<RefGroupeDto>();
		try {
			List<RefGroupe> groupes = refGroupeDao.findByEtablissement(idEtab);
			if (groupes != null) {
				log.debug("groupe list success with size =  "+ groupes.size());
				for (RefGroupe _groupe : groupes) {
					RefGroupeDto groupeDto = new RefGroupeDto();
					mapper.map(_groupe, groupeDto);
					refGroupeDtos.add(groupeDto);
				}
				return refGroupeDtos;
			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return null;
	}

	
	/**
	 * [RefGroupeService.findGeneric] Method 
	 * Overridden By : @author rlaib  on : 13 janv. 2015  10:14:37
	 * @param etabId
	 * @param value
	 * @param idType
	 * @return
	 */
	@Override
	public List<RefGroupeDto> findGeneric(Integer etabId, String value,
			Integer idTypeGroupe) {
	
		try {
			List<RefGroupe> refGroupes= refGroupeDao.findGeneric(etabId, value, idTypeGroupe);
			List<RefGroupeDto> refGroupeDtos = new ArrayList<RefGroupeDto>();
			for (RefGroupe refGroupe : refGroupes) {
				RefGroupeDto refGroupeDto = new RefGroupeDto();
				mapper.map(refGroupe, refGroupeDto);
				refGroupeDtos.add(refGroupeDto);
			}
			return refGroupeDtos;
		} catch (RuntimeException re) {
			log.error("findGeneric RefGroupeDto", re);
			throw re;
		}
	}
	

}
