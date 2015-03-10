/**
 * [dz.gov.mesrs.sii.referentiel.business.service.impl.RefAdresseElectroniqueServiceImpl.java] 
 * @author MAKERRI Sofiane on : 19 janv. 2014  10:40:43
 */
package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefAdresseElectroniqueDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefCoordonneeDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAdresseElectronique;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefCoordonnee;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAdresseElectroniqueDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCoordonneeDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefAdresseElectroniqueService;

/**
 * @author MAKERRI Sofiane on : 19 janv. 2014 10:40:43
 */
@Service("refAdresseElectroniqueServiceImpl")
public class RefAdresseElectroniqueServiceImpl implements
		RefAdresseElectroniqueService {

	private static final Logger log = LoggerFactory.getLogger(RefAdresseElectroniqueServiceImpl.class.getName());
	@Autowired
	@Qualifier("refCoordonneeDaoImpl")
	private RefCoordonneeDao refCoordonneeDao;
	@Autowired
	@Qualifier("refAdresseElectroniqueDaoImpl")
	private RefAdresseElectroniqueDao refAdresseElectroniqueDao;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.Mapper mapper;
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * [RefAdresseElectroniqueServiceImpl.RefAdresseElectroniqueServiceImpl()]
	 * Constructor
	 * 
	 * @author MAKERRI Sofiane on : 19 janv. 2014 10:40:43
	 */
	public RefAdresseElectroniqueServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Find List of Electronic address of individu
	 */
	@Override
	public List<RefAdresseElectroniqueDto> findByRefIndividuId(
			Integer id) {
		List<RefAdresseElectroniqueDto> refAdresseElectroniqueListDto = new ArrayList<RefAdresseElectroniqueDto>();
		try {
			List<RefCoordonnee> refCoordonneeList = refCoordonneeDao
					.findByRefIndividuId(UtilConstant.COORDONNEE_EMAIL_TYPE,
							id);
			if (refCoordonneeList != null) {
				log.debug("coordonnees list success with size =  {}",new Object[]{
						refCoordonneeList.size()});

				for (RefCoordonnee currentRefCoordonnee : refCoordonneeList) {
					RefAdresseElectroniqueDto refAdresseElectroniqueDto = new RefAdresseElectroniqueDto();
					mapper.map(currentRefCoordonnee, refAdresseElectroniqueDto);
					RefAdresseElectronique refAdresseElectronique = refAdresseElectroniqueDao
							.findById(currentRefCoordonnee.getId());
					if (refAdresseElectronique != null) {
						mapper.map(refAdresseElectronique,
								refAdresseElectroniqueDto);
						log.info("refAdresseElectronique found  =  "
								+ refAdresseElectroniqueDto.getNomAdresse());
					} else {
						log.info("adresseElectronique not found");
					}
					refAdresseElectroniqueListDto
							.add(refAdresseElectroniqueDto);
				}
			}
		} catch (RuntimeException e) {
			log.error("get adresseElectronique list failed", e);
			throw e;
		}
		return refAdresseElectroniqueListDto;
	}

	/**
	 * Find List of Electronic Address of Structure
	 */
	@Override
	public List<RefAdresseElectroniqueDto> findByRefStructureId(
			Integer id) {
		List<RefAdresseElectroniqueDto> refAdresseElectroniqueListDto = new ArrayList<RefAdresseElectroniqueDto>();
		try {
			List<RefCoordonnee> refCoordonneeList = refCoordonneeDao
					.findByRefStructureId(UtilConstant.COORDONNEE_EMAIL_TYPE,
							id);
			if (refCoordonneeList != null) {
				log.debug("coordonnees list success with size {}  ", new Object[]{refCoordonneeList.size()});

				for (RefCoordonnee currentRefCoordonnee : refCoordonneeList) {
					RefAdresseElectroniqueDto refAdresseElectroniqueDto = new RefAdresseElectroniqueDto();
					mapper.map(currentRefCoordonnee, refAdresseElectroniqueDto);
					RefAdresseElectronique refAdresseElectronique = refAdresseElectroniqueDao
							.findById(currentRefCoordonnee.getId());
					if (refAdresseElectronique != null) {
						mapper.map(refAdresseElectronique,
								refAdresseElectroniqueDto);
						log.info("refAdresseElectronique found  =  "
								+ refAdresseElectroniqueDto.getNomAdresse());
					} else {
						log.info("adresseElectronique not found");
					}
					refAdresseElectroniqueListDto
							.add(refAdresseElectroniqueDto);
				}
			}
		} catch (RuntimeException e) {
			log.error("get adresseElectronique list failed", e);
			throw e;
		}
		return refAdresseElectroniqueListDto;

	}

	/**
	 * Find List of Electronic address of Lieu
	 */
	@Override
	public List<RefAdresseElectroniqueDto> findByRefLieuId(Integer id) {
		List<RefAdresseElectroniqueDto> refAdresseElectroniqueListDto = new ArrayList<RefAdresseElectroniqueDto>();
		try {
			List<RefCoordonnee> refCoordonneeList = refCoordonneeDao
					.findByRefLieuId(UtilConstant.COORDONNEE_EMAIL_TYPE,
							id);
			if (refCoordonneeList != null) {
				log.debug("coordonnees list success with size {}  ", new Object[]{
						 refCoordonneeList.size()});

				for (RefCoordonnee currentRefCoordonnee : refCoordonneeList) {
					RefAdresseElectroniqueDto refAdresseElectroniqueDto = new RefAdresseElectroniqueDto();
					mapper.map(currentRefCoordonnee, refAdresseElectroniqueDto);
					RefAdresseElectronique refAdresseElectronique = refAdresseElectroniqueDao
							.findById(currentRefCoordonnee.getId());
					if (refAdresseElectronique != null) {
						mapper.map(refAdresseElectronique,
								refAdresseElectroniqueDto);
						log.info("refAdresseElectronique found  =  "
								+ refAdresseElectroniqueDto.getNomAdresse());
					} else {
						log.info("adresseElectronique not found");
					}
					refAdresseElectroniqueListDto
							.add(refAdresseElectroniqueDto);
				}
			}
		} catch (RuntimeException e) {
			log.error("get adresseElectronique list failed", e);
			throw e;
		}
		return refAdresseElectroniqueListDto;
	}

	/**
	 * Save Electronic Address DTO
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public RefAdresseElectroniqueDto save(
			RefAdresseElectroniqueDto refAdresseElectroniqueDto) {
		RefAdresseElectronique refAdresseElectronique = new RefAdresseElectronique();
		RefCoordonnee refCoordonnee = new RefCoordonnee();
		try {
			refAdresseElectroniqueDto.setId(null);// pour resoudre datached
													// exception
			mapper.map(refAdresseElectroniqueDto, refCoordonnee);
			refCoordonneeDao.persist(refCoordonnee);
			log.debug("save refCoordonnee successful {}" , new Object[]{ refCoordonnee.getId()});
			refAdresseElectroniqueDto.setId(refCoordonnee.getId());
			mapper.map(refAdresseElectroniqueDto, refAdresseElectronique);
			refAdresseElectroniqueDao.persist(refAdresseElectronique);
			mapper.map(refCoordonnee, refAdresseElectroniqueDto);
			mapper.map(refAdresseElectronique, refAdresseElectroniqueDto);
			log.debug("save refAdresseElectronique successful ");
			return refAdresseElectroniqueDto;
		} catch (RuntimeException e) {
			log.error("save refCoordonnee or refAdresse failed", e);
			throw e;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefAdresseElectroniqueService
	 * #update(dz.gov.mesrs.sii.referentiel.business.model.dto.
	 * RefAdresseElectroniqueDto)
	 */
	@Override
	public void update(RefAdresseElectroniqueDto refAdresseElectroniqueDto) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefAdresseElectroniqueService
	 * #saveOrUpdate(dz.gov.mesrs.sii.referentiel.business.model.dto.
	 * RefAdresseElectroniqueDto)
	 */
	@Override
	public void saveOrUpdate(RefAdresseElectroniqueDto refAdresseElectroniqueDto) {
		// TODO Auto-generated method stub

	}

	/**
	 * Delete Electronic Address DTO
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void delete(RefAdresseElectroniqueDto refAdresseElectroniqueDto) {
		RefAdresseElectronique refAdresseElectronique = new RefAdresseElectronique();
		RefCoordonnee refCoordonnee = new RefCoordonnee();
		try {
			mapper.map(refAdresseElectroniqueDto, refAdresseElectronique);
			refAdresseElectronique = refAdresseElectroniqueDao
					.merge(refAdresseElectronique);
			refAdresseElectroniqueDao.remove(refAdresseElectronique);
			log.debug("remove refAdresseElectronique successful");
			mapper.map(refAdresseElectroniqueDto, refCoordonnee);
			refCoordonnee = refCoordonneeDao.merge(refCoordonnee);
			refCoordonneeDao.remove(refCoordonnee);
			log.debug("remove refCoordonnee successful");
		} catch (RuntimeException e) {
			log.error("remove refAdresseElectronique or refCoordonnee failed",
					e);
			throw e;
		}

	}

	/**
	 * [RefAdresseElectroniqueServiceImpl.refCoordonneeDao] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 janv. 2014 10:43:19
	 * @return the refCoordonneeDao
	 */
	public RefCoordonneeDao getRefCoordonneeDao() {
		return refCoordonneeDao;
	}

	/**
	 * [RefAdresseElectroniqueServiceImpl.refCoordonneeDao] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 janv. 2014 10:43:19
	 * @param refCoordonneeDao
	 *            the refCoordonneeDao to set
	 */
	public void setRefCoordonneeDao(RefCoordonneeDao refCoordonneeDao) {
		this.refCoordonneeDao = refCoordonneeDao;
	}

	/**
	 * [RefAdresseElectroniqueServiceImpl.refAdresseElectroniqueDao] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 janv. 2014 10:43:19
	 * @return the refAdresseElectroniqueDao
	 */
	public RefAdresseElectroniqueDao getRefAdresseElectroniqueDao() {
		return refAdresseElectroniqueDao;
	}

	/**
	 * [RefAdresseElectroniqueServiceImpl.refAdresseElectroniqueDao] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 janv. 2014 10:43:19
	 * @param refAdresseElectroniqueDao
	 *            the refAdresseElectroniqueDao to set
	 */
	public void setRefAdresseElectroniqueDao(
			RefAdresseElectroniqueDao refAdresseElectroniqueDao) {
		this.refAdresseElectroniqueDao = refAdresseElectroniqueDao;
	}

	/**
	 * [RefAdresseElectroniqueServiceImpl.mapper] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 janv. 2014 10:43:19
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefAdresseElectroniqueServiceImpl.mapper] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 janv. 2014 10:43:19
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<RefAdresseElectroniqueDto> findByRefEtablissementId(
			Integer id) {
		List<RefAdresseElectroniqueDto> refAdresseElectroniqueListDto = new ArrayList<RefAdresseElectroniqueDto>();
		try {
			List<RefCoordonnee> refCoordonneeList = refCoordonneeDao
					.findByRefEtablissementId(
							UtilConstant.COORDONNEE_EMAIL_TYPE, id);
			if (refCoordonneeList != null) {
				log.debug("coordonnees list success with size {}  ", new Object[]{ refCoordonneeList.size()});

				for (RefCoordonnee currentRefCoordonnee : refCoordonneeList) {
					RefAdresseElectroniqueDto refAdresseElectroniqueDto = new RefAdresseElectroniqueDto();
					mapper.map(currentRefCoordonnee, refAdresseElectroniqueDto);
					RefAdresseElectronique refAdresseElectronique = refAdresseElectroniqueDao
							.findById(currentRefCoordonnee.getId());
					if (refAdresseElectronique != null) {
						mapper.map(refAdresseElectronique,
								refAdresseElectroniqueDto);
						log.info("refAdresseElectronique found  =  "
								+ refAdresseElectroniqueDto.getNomAdresse());
					} else {
						log.info("adresseElectronique not found");
					}
					refAdresseElectroniqueListDto
							.add(refAdresseElectroniqueDto);
				}
			}
		} catch (RuntimeException e) {
			log.error("get adresseElectronique list failed", e);
			throw e;
		}
		return refAdresseElectroniqueListDto;
	}

	@Override
	public List<RefAdresseElectroniqueDto> findByRefCoordonnee(
			List<RefCoordonneeDto> refCoordonneeList) {
		if (refCoordonneeList == null) {
			return null;
		}
		List<RefAdresseElectroniqueDto> refAdresseElectroniqueListDto = new ArrayList<RefAdresseElectroniqueDto>();
		try {

			if (refCoordonneeList != null) {
				for (RefCoordonneeDto currentRefCoordonnee : refCoordonneeList) {
					if (currentRefCoordonnee.getTypeCoordonnee().equals(
							UtilConstant.COORDONNEE_EMAIL_TYPE)) {
						RefAdresseElectroniqueDto refAdresseElectroniqueDto = new RefAdresseElectroniqueDto();
						/*mapper.map(currentRefCoordonnee,
								refAdresseElectroniqueDto);*/
						RefAdresseElectronique refAdresseElectronique = refAdresseElectroniqueDao
								.findById(currentRefCoordonnee.getId());
						if (refAdresseElectronique != null) {
							mapper.map(refAdresseElectronique,
									refAdresseElectroniqueDto);
							log.info("refAdresseElectronique found  =  "
									+ refAdresseElectroniqueDto.getNomAdresse());
						} else {
							log.info("adresseElectronique not found");
						}
						refAdresseElectroniqueListDto
								.add(refAdresseElectroniqueDto);
					}
				}
			}
		} catch (RuntimeException e) {
			log.error("get adresseElectronique list failed", e);
			throw e;
		}
		return refAdresseElectroniqueListDto;
	}

	@Override
	public RefAdresseElectroniqueDto findPrincipalAdresseElectroniqueForIndividu(
			String typeAdrElectro, String natureAdrElectro, Integer idIndividu) {
		try {
			RefAdresseElectronique refAdresseElectronique = refAdresseElectroniqueDao
					.findPrincipalAdresseElectroniqueForIndividu(typeAdrElectro, natureAdrElectro,
							idIndividu);

			if (refAdresseElectronique != null) {
				RefAdresseElectroniqueDto refAdresseElectroniqueDto = new RefAdresseElectroniqueDto();

				mapper.map(refAdresseElectronique, refAdresseElectroniqueDto);

				return refAdresseElectroniqueDto;
			} else
				return null;

		} catch (RuntimeException e) {
			log.error("findPrincipalAdresseForIndividu failed", e);
			throw e;
		}
	}

}
