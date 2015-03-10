/**
 * [dz.gov.mesrs.sii.referentiel.business.service.impl.RefAdresseServiceImpl.java] 
 * @author MAKERRI Sofiane on : 15 janv. 2014  13:14:06
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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefAdresseDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefCoordonneeDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAdresse;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefCoordonnee;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAdresseDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCoordonneeDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefAdresseService;

/**
 * @author MAKERRI Sofiane on : 15 janv. 2014 13:14:06
 */
@Service("refAdresseServiceImpl")
public class RefAdresseServiceImpl implements RefAdresseService {

	private static final Logger log = LoggerFactory.getLogger(RefAdresseServiceImpl.class.getName());
	@Autowired
	@Qualifier("refCoordonneeDaoImpl")
	private RefCoordonneeDao refCoordonneeDao;
	@Autowired
	@Qualifier("refAdresseDaoImpl")
	private RefAdresseDao refAdresseDao;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.Mapper mapper;
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * [RefAdresseServiceImpl.RefAdresseServiceImpl()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 15 janv. 2014 13:14:06
	 */
	public RefAdresseServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * save Address Service
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public RefAdresseDto save(RefAdresseDto refAdresseDto) {
		RefAdresse refAdresse = new RefAdresse();
		RefCoordonnee refCoordonnee = new RefCoordonnee();
		try {
			refAdresseDto.setId(null);
			mapper.map(refAdresseDto, refCoordonnee);
			refCoordonneeDao.persist(refCoordonnee);
			log.debug("save refCoordonnee successful {} " , new Object[]{  refCoordonnee.getId()});
			refAdresseDto.setId(refCoordonnee.getId());
			mapper.map(refAdresseDto, refAdresse);
			refAdresseDao.persist(refAdresse);
			mapper.map(refCoordonnee, refAdresseDto);
			mapper.map(refAdresse, refAdresseDto);
			log.debug("save refAdresse successful ");
			return refAdresseDto;
		} catch (RuntimeException e) {
			log.error("save refCoordonnee or refAdresse failed", e);
			throw e;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefAdresseService#saveOrUpdate
	 * (dz.gov.mesrs.sii.referentiel.business.model.dto.RefAdresseDto)
	 */
	@Override
	public void saveOrUpdate(RefAdresseDto refAdresseDto) {
		// TODO Auto-generated method stub

	}

	/**
	 * Delete address service
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void delete(RefAdresseDto refAdresseDto) {
		RefAdresse refAdresse = new RefAdresse();
		RefCoordonnee refCoordonnee = new RefCoordonnee();
		try {
			mapper.map(refAdresseDto, refAdresse);
			refAdresse = refAdresseDao.merge(refAdresse);
			refAdresseDao.remove(refAdresse);
			log.debug("remove refAdresse successful");
			mapper.map(refAdresseDto, refCoordonnee);
			refCoordonnee = refCoordonneeDao.merge(refCoordonnee);
			refCoordonneeDao.remove(refCoordonnee);
			log.debug("remove refCoordonnee successful");
		} catch (RuntimeException e) {
			log.error("remove refAdresse or refCoordonnee failed", e);
			throw e;
		}

	}

	/**
	 * Find list of address of INDIVIDU
	 */
	@Override
	public List<RefAdresseDto> findByRefIndividuId(Integer id) {
		List<RefAdresseDto> refAdresseListDto = new ArrayList<RefAdresseDto>();
		try {
			List<RefCoordonnee> refCoordonneeList = refCoordonneeDao
					.findByRefIndividuId(UtilConstant.COORDONNEE_ADRESSE_TYPE,
							id);
			if (refCoordonneeList != null) {
				log.debug("coordonnees list success with size {}  ", new Object[]{ refCoordonneeList.size()});

				for (RefCoordonnee currentRefCoordonnee : refCoordonneeList) {
					RefAdresseDto refAdresseDto = new RefAdresseDto();
					mapper.map(currentRefCoordonnee, refAdresseDto);
					RefAdresse refAdresse = refAdresseDao
							.findById(currentRefCoordonnee.getId());
					if (refAdresse != null) {
						mapper.map(refAdresse, refAdresseDto);
						log.info("refAdresse found  =  "
								+ refAdresseDto.getLibelleAdresseLatin());
					} else {
						log.info("adresse not found");
					}
					refAdresseListDto.add(refAdresseDto);
				}
			}
		} catch (RuntimeException e) {
			log.error("get adresse list failed", e);
			throw e;
		}
		return refAdresseListDto;
	}

	/**
	 * Find list of address of STRUCURE
	 */
	@Override
	public List<RefAdresseDto> findByRefStructureId(Integer id) {
		List<RefAdresseDto> refAdresseListDto = new ArrayList<RefAdresseDto>();
		try {
			List<RefCoordonnee> refCoordonneeList = refCoordonneeDao
					.findByRefStructureId(UtilConstant.COORDONNEE_ADRESSE_TYPE,
							id);
			if (refCoordonneeList != null) {
				log.debug("coordonnees list success with size {}  ", new Object[]{ refCoordonneeList.size()});

				for (RefCoordonnee currentRefCoordonnee : refCoordonneeList) {
					RefAdresseDto refAdresseDto = new RefAdresseDto();
					mapper.map(currentRefCoordonnee, refAdresseDto);
					RefAdresse refAdresse = refAdresseDao
							.findById(currentRefCoordonnee.getId());
					if (refAdresse != null) {
						mapper.map(refAdresse, refAdresseDto);
						log.info("refAdresse found  =  "
								+ refAdresseDto.getLibelleAdresseLatin());
					} else {
						log.info("adresse not found");
					}
					refAdresseListDto.add(refAdresseDto);
				}
			}
		} catch (RuntimeException e) {
			log.error("get adresse list failed", e);
			throw e;
		}
		return refAdresseListDto;
	}

	/**
	 * Find list of address of Lieu
	 */
	@Override
	public List<RefAdresseDto> findByRefLieuId(Integer id) {
		List<RefAdresseDto> refAdresseListDto = new ArrayList<RefAdresseDto>();
		try {
			List<RefCoordonnee> refCoordonneeList = refCoordonneeDao
					.findByRefLieuId(UtilConstant.COORDONNEE_ADRESSE_TYPE,
							id);
			if (refCoordonneeList != null) {
				log.debug("coordonnees list success with size {}  ",  new Object[]{ refCoordonneeList.size()});

				for (RefCoordonnee currentRefCoordonnee : refCoordonneeList) {
					RefAdresseDto refAdresseDto = new RefAdresseDto();
					mapper.map(currentRefCoordonnee, refAdresseDto);
					RefAdresse refAdresse = refAdresseDao
							.findById(currentRefCoordonnee.getId());
					if (refAdresse != null) {
						mapper.map(refAdresse, refAdresseDto);
						log.info("refAdresse found  =  "
								+ refAdresseDto.getLibelleAdresseLatin());
					} else {
						log.info("adresse not found");
					}
					refAdresseListDto.add(refAdresseDto);
				}
			}
		} catch (RuntimeException e) {
			log.error("get adresse list failed", e);
			throw e;
		}
		return refAdresseListDto;
	}

	/**
	 * [RefAdresseServiceImpl.refCoordonneeDao] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 janv. 2014 13:18:56
	 * @return the refCoordonneeDao
	 */
	public RefCoordonneeDao getRefCoordonneeDao() {
		return refCoordonneeDao;
	}

	/**
	 * [RefAdresseServiceImpl.refCoordonneeDao] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 janv. 2014 13:18:56
	 * @param refCoordonneeDao
	 *            the refCoordonneeDao to set
	 */
	public void setRefCoordonneeDao(RefCoordonneeDao refCoordonneeDao) {
		this.refCoordonneeDao = refCoordonneeDao;
	}

	/**
	 * [RefAdresseServiceImpl.refAdresseDao] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 janv. 2014 13:18:56
	 * @return the refAdresseDao
	 */
	public RefAdresseDao getRefAdresseDao() {
		return refAdresseDao;
	}

	/**
	 * [RefAdresseServiceImpl.refAdresseDao] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 janv. 2014 13:18:56
	 * @param refAdresseDao
	 *            the refAdresseDao to set
	 */
	public void setRefAdresseDao(RefAdresseDao refAdresseDao) {
		this.refAdresseDao = refAdresseDao;
	}

	/**
	 * [RefAdresseServiceImpl.mapper] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 janv. 2014 13:18:56
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefAdresseServiceImpl.mapper] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 janv. 2014 13:18:56
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
	 * dz.gov.mesrs.sii.referentiel.business.service.RefAdresseService#update
	 * (dz.gov.mesrs.sii.referentiel.business.model.dto.RefAdresseDto)
	 */
	@Override
	public void update(RefAdresseDto refAdresseDto) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<RefAdresseDto> findByRefEtablissementId(Integer id) {
		List<RefAdresseDto> refAdresseListDto = new ArrayList<RefAdresseDto>();
		try {
			List<RefCoordonnee> refCoordonneeList = refCoordonneeDao
					.findByRefEtablissementId(
							UtilConstant.COORDONNEE_ADRESSE_TYPE, id);
			if (refCoordonneeList != null) {
				log.debug("coordonnees list success with size {}  ", new Object[]{ refCoordonneeList.size()});

				for (RefCoordonnee currentRefCoordonnee : refCoordonneeList) {
					RefAdresseDto refAdresseDto = new RefAdresseDto();
					mapper.map(currentRefCoordonnee, refAdresseDto);
					RefAdresse refAdresse = refAdresseDao
							.findById(currentRefCoordonnee.getId());
					if (refAdresse != null) {
						mapper.map(refAdresse, refAdresseDto);
						log.info("refAdresse found  =  "
								+ refAdresseDto.getLibelleAdresseLatin());
					} else {
						log.info("adresse not found");
					}
					refAdresseListDto.add(refAdresseDto);
				}
			}
		} catch (RuntimeException e) {
			log.error("get adresse list failed", e);
			throw e;
		}
		return refAdresseListDto;
	}

	@Override
	public List<RefAdresseDto> findByRefCoordonnee(
			List<RefCoordonneeDto> refCoordonneeList) {
		
		if(refCoordonneeList == null) {
			return null;
		}
		List<RefAdresseDto> refAdresseListDto = new ArrayList<RefAdresseDto>();
		try {
			for (RefCoordonneeDto currentRefCoordonnee : refCoordonneeList) {
				
				if (currentRefCoordonnee.getTypeCoordonnee().equals(UtilConstant.COORDONNEE_ADRESSE_TYPE)) {
					RefAdresseDto refAdresseDto = new RefAdresseDto();
					RefAdresse refAdresse = refAdresseDao
							.findById(currentRefCoordonnee.getId());
					if (refAdresse != null) {
						mapper.map(refAdresse, refAdresseDto);
						log.info("refAdresse found  =  "
								+ refAdresseDto.getLibelleAdresseLatin());
					} else {
						log.info("adresse not found");
					}
					refAdresseListDto.add(refAdresseDto);
				}
			}

		} catch (RuntimeException e) {
			log.error("get adresse list failed", e);
			throw e;
		}
		return refAdresseListDto;
	}

	
	@Override
	public RefAdresseDto findPrincipalAdresseForIndividu(String identifiant) {
				
		try {
			RefAdresse refAdresse = refAdresseDao
					.findPrincipalAdresseForIndividu(identifiant);
			
			if (refAdresse != null) {				
				RefAdresseDto refAdresseDto = new RefAdresseDto();
				
				mapper.map(refAdresse, refAdresseDto);
				
				return refAdresseDto;				
			}
			else 	return null;
			
		} catch (RuntimeException e) {
			log.error("findPrincipalAdresseForIndividu failed", e);
			throw e;
		}
		
	}

	@Override
	public RefAdresseDto findPrincipalAdresseForIndividu(String typeAdresse, Integer idIndividu) {
		try {
			RefAdresse refAdresse = refAdresseDao
					.findPrincipalAdresseForIndividu(typeAdresse, idIndividu);
			
			if (refAdresse != null) {				
				RefAdresseDto refAdresseDto = new RefAdresseDto();
				
				mapper.map(refAdresse, refAdresseDto);
				
				return refAdresseDto;				
			}
			else 	return null;
			
		} catch (RuntimeException e) {
			log.error("findPrincipalAdresse failed", e);
			throw e;
		}
	}
}
