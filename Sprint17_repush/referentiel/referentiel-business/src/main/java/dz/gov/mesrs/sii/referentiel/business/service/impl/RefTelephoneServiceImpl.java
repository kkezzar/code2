/**
 * [dz.gov.mesrs.sii.referentiel.business.service.impl.RefTelephoneServiceImpl.java] 
 * @author MAKERRI Sofiane on : 16 janv. 2014  15:52:26
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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefCoordonneeDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefTelephoneDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefCoordonnee;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefTelephone;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCoordonneeDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefTelephoneDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefTelephoneService;

/**
 * @author MAKERRI Sofiane on : 16 janv. 2014 15:52:26
 */
@Service("refTelephoneServiceImpl")
public class RefTelephoneServiceImpl implements RefTelephoneService {

	private static final Logger log = LoggerFactory.getLogger(RefTelephoneServiceImpl.class.getName());
	@Autowired
	@Qualifier("refCoordonneeDaoImpl")
	private RefCoordonneeDao refCoordonneeDao;
	@Autowired
	@Qualifier("refTelephoneDaoImpl")
	private RefTelephoneDao refTelephoneDao;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.Mapper mapper;
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * [RefTelephoneServiceImpl.RefTelephoneServiceImpl()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 15:52:26
	 */
	public RefTelephoneServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Find List of Phone of INDIVIDU
	 */
	@Override
	public List<RefTelephoneDto> findByRefIndividuId(Integer id) {
		List<RefTelephoneDto> refAdresseListDto = new ArrayList<RefTelephoneDto>();
		try {
			List<RefCoordonnee> refCoordonneeList = refCoordonneeDao
					.findByRefIndividuId(
							UtilConstant.COORDONNEE_TELEPHONE_TYPE, id);
			if (refCoordonneeList != null) {
				log.debug("coordonnees list success with size {}  ", new Object[]{refCoordonneeList.size()});

				for (RefCoordonnee currentRefCoordonnee : refCoordonneeList) {
					RefTelephoneDto refTelephoneDto = new RefTelephoneDto();
					mapper.map(currentRefCoordonnee, refTelephoneDto);
					RefTelephone refTelephone = refTelephoneDao
							.findById(currentRefCoordonnee.getId());
					if (refTelephone != null) {
						mapper.map(refTelephone, refTelephoneDto);
						log.info("refTelephone found  =  "
								+ refTelephoneDto.getNumeroTelephone());
					} else {
						log.info("refTelephone not found");
					}
					refAdresseListDto.add(refTelephoneDto);
				}
			}
		} catch (RuntimeException e) {
			log.error("get telephone list failed", e);
			throw e;
		}
		return refAdresseListDto;

	}

	/**
	 * Find List of Phone of STRUCTURE
	 */
	@Override
	public List<RefTelephoneDto> findByRefStructureId(Integer id) {
		List<RefTelephoneDto> refAdresseListDto = new ArrayList<RefTelephoneDto>();
		try {
			List<RefCoordonnee> refCoordonneeList = refCoordonneeDao
					.findByRefStructureId(
							UtilConstant.COORDONNEE_TELEPHONE_TYPE, id);
			if (refCoordonneeList != null) {
				log.debug("coordonnees list success with size {}  ", new Object[]{refCoordonneeList.size()});

				for (RefCoordonnee currentRefCoordonnee : refCoordonneeList) {
					RefTelephoneDto refTelephoneDto = new RefTelephoneDto();
					mapper.map(currentRefCoordonnee, refTelephoneDto);
					RefTelephone refTelephone = refTelephoneDao
							.findById(currentRefCoordonnee.getId());
					if (refTelephone != null) {
						mapper.map(refTelephone, refTelephoneDto);
						log.info("refTelephone found  =  "
								+ refTelephoneDto.getNumeroTelephone());
					} else {
						log.info("refTelephone not found");
					}
					refAdresseListDto.add(refTelephoneDto);
				}
			}
		} catch (RuntimeException e) {
			log.error("get telephone list failed", e);
			throw e;
		}
		return refAdresseListDto;

	}

	/**
	 * Find List of Phone of Lieu
	 */
	@Override
	public List<RefTelephoneDto> findByRefLieuId(Integer id) {
		List<RefTelephoneDto> refAdresseListDto = new ArrayList<RefTelephoneDto>();
		try {
			List<RefCoordonnee> refCoordonneeList = refCoordonneeDao
					.findByRefLieuId(UtilConstant.COORDONNEE_TELEPHONE_TYPE, id);
			if (refCoordonneeList != null) {
				log.debug("coordonnees list success with size {}  ", new Object[]{refCoordonneeList.size()});

				for (RefCoordonnee currentRefCoordonnee : refCoordonneeList) {
					RefTelephoneDto refTelephoneDto = new RefTelephoneDto();
					mapper.map(currentRefCoordonnee, refTelephoneDto);
					RefTelephone refTelephone = refTelephoneDao
							.findById(currentRefCoordonnee.getId());
					if (refTelephone != null) {
						mapper.map(refTelephone, refTelephoneDto);
						log.info("refTelephone found  =  "
								+ refTelephoneDto.getNumeroTelephone());
					} else {
						log.info("refTelephone not found");
					}
					refAdresseListDto.add(refTelephoneDto);
				}
			}
		} catch (RuntimeException e) {
			log.error("get telephone list failed", e);
			throw e;
		}
		return refAdresseListDto;

	}

	/**
	 * Save Phone DTO
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public RefTelephoneDto save(RefTelephoneDto refTelephoneDto) {
		RefTelephone refTelephone = new RefTelephone();
		RefCoordonnee refCoordonnee = new RefCoordonnee();
		try {
			refTelephoneDto.setId(null);
			mapper.map(refTelephoneDto, refCoordonnee);
			refCoordonneeDao.persist(refCoordonnee);
			log.debug("save refCoordonnee successful {}", new Object[]{refCoordonnee.getId()});
			refTelephoneDto.setId(refCoordonnee.getId());
			mapper.map(refTelephoneDto, refTelephone);
			refTelephoneDao.persist(refTelephone);
			mapper.map(refCoordonnee, refTelephoneDto);
			mapper.map(refTelephone, refTelephoneDto);
			log.debug("save refTelephone successful ");
			return refTelephoneDto;
		} catch (RuntimeException e) {
			log.error("save refCoordonnee or refTelephone failed", e);
			throw e;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefTelephoneService#update
	 * (dz.gov.mesrs.sii.referentiel.business.model.dto.RefTelephoneDto)
	 */
	@Override
	public void update(RefTelephoneDto refTelephoneDto) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefTelephoneService#
	 * saveOrUpdate
	 * (dz.gov.mesrs.sii.referentiel.business.model.dto.RefTelephoneDto)
	 */
	@Override
	public void saveOrUpdate(RefTelephoneDto refTelephoneDto) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefTelephoneService#delete
	 * (java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	/**
	 * [RefTelephoneServiceImpl.refCoordonneeDao] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 15:54:13
	 * @return the refCoordonneeDao
	 */
	public RefCoordonneeDao getRefCoordonneeDao() {
		return refCoordonneeDao;
	}

	/**
	 * [RefTelephoneServiceImpl.refCoordonneeDao] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 15:54:13
	 * @param refCoordonneeDao
	 *            the refCoordonneeDao to set
	 */
	public void setRefCoordonneeDao(RefCoordonneeDao refCoordonneeDao) {
		this.refCoordonneeDao = refCoordonneeDao;
	}

	/**
	 * [RefTelephoneServiceImpl.refTelephoneDao] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 15:54:13
	 * @return the refTelephoneDao
	 */
	public RefTelephoneDao getRefTelephoneDao() {
		return refTelephoneDao;
	}

	/**
	 * [RefTelephoneServiceImpl.refTelephoneDao] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 15:54:13
	 * @param refTelephoneDao
	 *            the refTelephoneDao to set
	 */
	public void setRefTelephoneDao(RefTelephoneDao refTelephoneDao) {
		this.refTelephoneDao = refTelephoneDao;
	}

	/**
	 * [RefTelephoneServiceImpl.mapper] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 15:54:13
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefTelephoneServiceImpl.mapper] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 15:54:13
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * Delete Phone DTO
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void delete(RefTelephoneDto refTelephoneDto) {
		RefTelephone refTelephone = new RefTelephone();
		RefCoordonnee refCoordonnee = new RefCoordonnee();
		try {
			mapper.map(refTelephoneDto, refTelephone);
			refTelephone = refTelephoneDao.merge(refTelephone);
			refTelephoneDao.remove(refTelephone);
			log.debug("remove refTelephone successful");
			mapper.map(refTelephoneDto, refCoordonnee);
			refCoordonnee = refCoordonneeDao.merge(refCoordonnee);
			refCoordonneeDao.remove(refCoordonnee);
			log.debug("remove refCoordonnee successful");
		} catch (RuntimeException e) {
			log.error("remove refTelephone or refCoordonnee failed", e);
			throw e;
		}

	}

	@Override
	public List<RefTelephoneDto> findByRefEtablissementId(Integer id) {
		List<RefTelephoneDto> refAdresseListDto = new ArrayList<RefTelephoneDto>();
		try {
			List<RefCoordonnee> refCoordonneeList = refCoordonneeDao
					.findByRefEtablissementId(
							UtilConstant.COORDONNEE_TELEPHONE_TYPE, id);
			if (refCoordonneeList != null) {
				log.debug("coordonnees list success with size {}  ", new Object[]{refCoordonneeList.size()});

				for (RefCoordonnee currentRefCoordonnee : refCoordonneeList) {
					RefTelephoneDto refTelephoneDto = new RefTelephoneDto();
					mapper.map(currentRefCoordonnee, refTelephoneDto);
					RefTelephone refTelephone = refTelephoneDao
							.findById(currentRefCoordonnee.getId());
					if (refTelephone != null) {
						mapper.map(refTelephone, refTelephoneDto);
						log.info("refTelephone found  =  "
								+ refTelephoneDto.getNumeroTelephone());
					} else {
						log.info("refTelephone not found");
					}
					refAdresseListDto.add(refTelephoneDto);
				}
			}
		} catch (RuntimeException e) {
			log.error("get telephone list failed", e);
			throw e;
		}
		return refAdresseListDto;
	}

	@Override
	public List<RefTelephoneDto> findByRefCoordonnee(
			List<RefCoordonneeDto> refCoordonneeList) {
		if (refCoordonneeList == null) {
			return null;
		}
		List<RefTelephoneDto> refAdresseListDto = new ArrayList<RefTelephoneDto>();
		try {

			for (RefCoordonneeDto currentRefCoordonnee : refCoordonneeList) {
				if (currentRefCoordonnee.getTypeCoordonnee().equals(
						UtilConstant.COORDONNEE_TELEPHONE_TYPE)) {
					RefTelephoneDto refTelephoneDto = new RefTelephoneDto();
					// mapper.map(currentRefCoordonnee, refTelephoneDto);
					RefTelephone refTelephone = refTelephoneDao
							.findById(currentRefCoordonnee.getId());
					if (refTelephone != null) {
						mapper.map(refTelephone, refTelephoneDto);
						log.info("refTelephone found  =  "
								+ refTelephoneDto.getNumeroTelephone());
					} else {
						log.info("refTelephone not found");
					}
					refAdresseListDto.add(refTelephoneDto);
				}
			}

		} catch (RuntimeException e) {
			log.error("get telephone list failed", e);
			throw e;
		}
		return refAdresseListDto;
	}

	@Override
	public RefTelephoneDto findPrincipalTelephoneForIndividu(String identifiant) {
		try {
			RefTelephone refTelephone = refTelephoneDao
					.findPrincipalTelephoneForIndividu(identifiant);

			if (refTelephone != null) {
				RefTelephoneDto refTelephoneDto = new RefTelephoneDto();

				mapper.map(refTelephone, refTelephoneDto);

				return refTelephoneDto;
			} else
				return null;

		} catch (RuntimeException e) {
			log.error("findPrincipalTelephoneForIndividu failed", e);
			throw e;
		}

	}

	@Override
	public RefTelephoneDto findPrincipalTelephoneForIndividu(String typeTel,
			String natureTel, Integer idIndividu) {
		try {
			RefTelephone refTelephone = refTelephoneDao
					.findPrincipalTelephoneForIndividu(typeTel, natureTel,
							idIndividu);

			if (refTelephone != null) {
				RefTelephoneDto refTelephoneDto = new RefTelephoneDto();

				mapper.map(refTelephone, refTelephoneDto);

				return refTelephoneDto;
			} else
				return null;

		} catch (RuntimeException e) {
			log.error("findPrincipalTelephoneForIndividu failed", e);
			throw e;
		}
	}
}
