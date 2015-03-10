/**
 * 
 */
package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefPermissionDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefSituationDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAffectation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefPermission;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSituation;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectationDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPermissionDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefPermissionService;

/**
 * @author BELDI Jamel on :04 mars. 2014 10:18:10
 */
@Service("refPermissionServiceImpl")
public class RefPermissionServiceImpl implements RefPermissionService {
	private static final Logger log = LoggerFactory.getLogger(RefPermissionServiceImpl.class.getName());
	@Autowired
	@Qualifier("refPermissionDaoImpl")
	private RefPermissionDao refPermissionDaoImpl;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.Mapper mapper;
	@Autowired
	@Qualifier("refSituationDaoImpl")
	private RefSituationDao refSituationDao;

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
	public RefPermissionServiceImpl() {
	}

	@Override
	public RefPermissionDto findById(Integer id) {
		RefPermissionDto refPermissionDto = null;
		try {
			RefPermission refPermission = refPermissionDaoImpl.findById(id);
			if (refPermission != null) {
				refPermissionDto = new RefPermissionDto();
				mapper.map(refPermission, refPermissionDto);
				log.info("success get dto: " + refPermissionDto.getId());
				return refPermissionDto;
			}
		} catch (Exception e) {
			log.error("get failed", e);

		}
		return refPermissionDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefPermissionService#findBAll
	 * ()
	 */
	@Override
	public List<RefPermissionDto> findAll() {
		List<RefPermissionDto> refPermissionListDto = new ArrayList<RefPermissionDto>();
		try {
			List<RefPermission> refPermissionList = refPermissionDaoImpl
					.findAll();
			if (refPermissionList != null) {
				log.debug("permission list success with size =  {}", new Object[]{refPermissionList.size()});
				for (RefPermission currentRefPermission : refPermissionList) {
					RefPermissionDto refPermissionDto = new RefPermissionDto();
					mapper.map(currentRefPermission, refPermissionDto);
					refPermissionListDto.add(refPermissionDto);
				}
				return refPermissionListDto;
			}
		} catch (RuntimeException e) {
			log.error("get permission list failed", e);
			throw e;
		}

		return null;
	}

	/**
	 * [RefPermissionServiceImpl.mapper] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2014 17:04:29
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefPermissionServiceImpl.mapper] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2014 17:04:29
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<RefPermissionDto> findGeneric(String value) {
		List<RefPermissionDto> refPermissionListDto = new ArrayList<RefPermissionDto>();
		try {
			List<RefPermission> refPermissionList = refPermissionDaoImpl
					.findGeneric(value);
			if (refPermissionList != null) {
				log.debug("permission list success with size =  {}", new Object[]{refPermissionList.size()});

				for (RefPermission currentRefPermission : refPermissionList) {
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(
									currentRefPermission.getId(),
									RefPermission.class.getSimpleName());
					RefPermissionDto refPermissionDto = new RefPermissionDto();
					mapper.map(currentRefPermission, refPermissionDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, refPermissionDto);
					}
					refPermissionListDto.add(refPermissionDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refPermissionListDto;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void save(RefPermissionDto refPermissionDto) {
		RefPermission refPermission = new RefPermission();
		mapper.map(refPermissionDto, refPermission);
		try {
			refPermissionDaoImpl.persist(refPermission);
			log.debug("save successful");
		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void update(RefPermissionDto refPermissionDto) {
		RefPermission refPermission = new RefPermission();
		mapper.map(refPermissionDto, refPermission);
		try {
			refPermissionDaoImpl.merge(refPermission);
			log.debug("update successful");
		} catch (RuntimeException e) {
			log.error("update failed", e);
			throw e;
		}

	}

	@Override
	public void saveOrUpdate(RefPermissionDto refPermissionDto) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void delete(RefPermissionDto refPermissionDto) {
		RefPermission refPermission = new RefPermission();
		try {
			mapper.map(refPermissionDto, refPermission);
			refPermission = refPermissionDaoImpl.merge(refPermission);
			refPermissionDaoImpl.remove(refPermission);
			log.debug("delete successful");
		} catch (RuntimeException e) {
			log.error("delete failed", e);
			throw e;
		}

	}

	@Override
	public List<RefPermissionDto> findByIdfIndividu(String identifiant) {
		List<RefPermissionDto> refPermissionDtos = null;
		try {
			List<RefPermission> refPermissions = refPermissionDaoImpl
					.findByIdfIndividu(identifiant);

			refPermissionDtos = new ArrayList<RefPermissionDto>();

			for (RefPermission refPermission : refPermissions) {
				refPermissionDtos.add(mapper.map(refPermission,
						RefPermissionDto.class));
			}
		} catch (RuntimeException e) {
			log.error("get RefPermissionDto list failed", e);
			throw e;
		}
		log.info("get RefPermissionDto list successed");
		return refPermissionDtos;
	}

	@Override
	public RefPermissionDto findByIdFonction(int id) {
		RefPermissionDto refPermissionDto = null;
		try {
			RefPermission refPermission = refPermissionDaoImpl
					.findByIdFonction(id);
			if (refPermission != null) {
				refPermissionDto = new RefPermissionDto();
				log.debug("get RefPermissionDto found successfully with function id = {}", new Object[]{id});
				mapper.map(refPermission, refPermissionDto);

			}
		} catch (RuntimeException e) {
			log.error("get RefPermissionDto failed", e);
			throw e;
		}
		return refPermissionDto;
	}

	@Override
	public RefPermissionDto findByName(String ncName) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * [RefPermissionServiceImpl.refPermissionDaoImpl] Getter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 09:33:20
	 * @return the refPermissionDaoImpl
	 */
	public RefPermissionDao getRefPermissionDaoImpl() {
		return refPermissionDaoImpl;
	}

	/**
	 * [RefPermissionServiceImpl.refPermissionDaoImpl] Setter
	 * 
	 * @author BELBRIK Oualid on : 6 mars 2014 09:33:20
	 * @param refPermissionDaoImpl
	 *            the refPermissionDaoImpl to set
	 */
	public void setRefPermissionDaoImpl(RefPermissionDao refPermissionDaoImpl) {
		this.refPermissionDaoImpl = refPermissionDaoImpl;
	}

	@Override
	public List<RefPermissionDto> findByIdModule(int idRole, int id) {
		List<RefPermissionDto> refPermissionDtos = null;
		try {
			List<RefPermission> refPermissions = refPermissionDaoImpl
					.findByIdModule(idRole, id);

			refPermissionDtos = new ArrayList<RefPermissionDto>();

			for (RefPermission refPermission : refPermissions) {
				refPermissionDtos.add(mapper.map(refPermission,
						RefPermissionDto.class));
			}
		} catch (RuntimeException e) {
			log.error("get RefPermissionDto list failed", e);
			throw e;
		}
		log.info("get RefPermissionDto list successed");
		return refPermissionDtos;
	}

	@Override
	public List<RefPermissionDto> findByIdDomaine(int _idRole, int id) {
		List<RefPermissionDto> refPermissionDtos = null;
		try {
			List<RefPermission> refPermissions = refPermissionDaoImpl
					.findByIdDomaine(_idRole, id);

			refPermissionDtos = new ArrayList<RefPermissionDto>();

			for (RefPermission refPermission : refPermissions) {
				refPermissionDtos.add(mapper.map(refPermission,
						RefPermissionDto.class));
			}
		} catch (RuntimeException e) {
			log.error("get RefPermissionDto list failed", e);
			throw e;
		}
		log.info("get RefPermissionDto list successed");
		return refPermissionDtos;
	}

	@Override
	public List<RefPermissionDto> findByIdfGroupe(String idf) {
		List<RefPermissionDto> refPermissionDtos = null;
		try {
			List<RefPermission> refPermissions = refPermissionDaoImpl
					.findByIdfGroupe(idf);

			refPermissionDtos = new ArrayList<RefPermissionDto>();

			for (RefPermission refPermission : refPermissions) {
				refPermissionDtos.add(mapper.map(refPermission,
						RefPermissionDto.class));
			}
		} catch (RuntimeException e) {
			log.error("get RefPermissionDto list failed", e);
			throw e;
		}
		log.info("get RefPermissionDto list successed");
		return refPermissionDtos;
	}

	@Override
	public List<RefPermissionDto> findByIdfStructure(String idf) {
		List<RefPermissionDto> refPermissionDtos = null;
		try {
			List<RefPermission> refPermissions = refPermissionDaoImpl
					.findByIdfStructure(idf);

			refPermissionDtos = new ArrayList<RefPermissionDto>();

			for (RefPermission refPermission : refPermissions) {
				refPermissionDtos.add(mapper.map(refPermission,
						RefPermissionDto.class));
			}
		} catch (RuntimeException e) {
			log.error("get RefPermissionDto list failed", e);
			throw e;
		}
		log.info("get RefPermissionDto list successed");
		return refPermissionDtos;
	}

	@Override
	public List<RefPermissionDto> findByIdRole(String domaine, int idf) {
		List<RefPermissionDto> refPermissionDtos = null;
		try {
			List<RefPermission> refPermissions = refPermissionDaoImpl
					.findByIdRole(domaine, idf);

			refPermissionDtos = new ArrayList<RefPermissionDto>();

			for (RefPermission refPermission : refPermissions) {
				refPermissionDtos.add(mapper.map(refPermission,
						RefPermissionDto.class));
			}
		} catch (RuntimeException e) {
			log.error("get RefPermissionDto list failed", e);
			throw e;
		}
		log.info("get RefPermissionDto list successed");
		return refPermissionDtos;
	}

	@Override
	public RefPermissionDto findByIdFonction(int idRole, int id) {
		RefPermissionDto refPermissionDto = null;
		try {
			RefPermission refPermission = refPermissionDaoImpl
					.findByIdFonction(idRole, id);
			if (refPermission != null) {
				refPermissionDto = new RefPermissionDto();
				log.debug("get RefPermissionDto found successfully with function id = {}", new Object[]{id});
				mapper.map(refPermission, refPermissionDto);

			}
		} catch (RuntimeException e) {
			log.error("get RefPermissionDto failed", e);
			throw e;
		}
		return refPermissionDto;
	}

	@Override
	public List<RefIndividuDto> findModele(String identifiant,
			int idfAffectation) {
		List<RefIndividuDto> refIndividuDtos = null;
		try {
			List<RefIndividu> refIndividus = refPermissionDaoImpl.findModele(
					identifiant, idfAffectation);

			refIndividuDtos = new ArrayList<RefIndividuDto>();

			for (RefIndividu refIndividu : refIndividus) {
				refIndividuDtos.add(mapper.map(refIndividu,
						RefIndividuDto.class));
			}
		} catch (RuntimeException e) {
			log.error("get RefIndividuDto list failed", e);
			throw e;
		}
		log.info("get RefIndividuDto list successed");
		return refIndividuDtos;
	}

	@Override
	public List<RefPermissionDto> findByIdfIndividu(int idAffectation,
			String identifiant) {
		List<RefPermissionDto> refPermissionDtos = null;
		try {
			List<RefPermission> refPermissions = refPermissionDaoImpl
					.findByIdfIndividu(idAffectation, identifiant);

			refPermissionDtos = new ArrayList<RefPermissionDto>();

			for (RefPermission refPermission : refPermissions) {
				refPermissionDtos.add(mapper.map(refPermission,
						RefPermissionDto.class));
			}
		} catch (RuntimeException e) {
			log.error("get RefPermissionDto list failed", e);
			throw e;
		}
		log.info("get RefPermissionDto list successed");
		return refPermissionDtos;
	}

	@Override
	public RefAffectationDto findAffectationModele(String identifiant,
			int idfAffectation) {
		RefAffectationDto refAffectationDto = null;
		try {
			RefAffectation refAffectation = refPermissionDaoImpl
					.findAffectationModele(identifiant, idfAffectation);

			refAffectationDto = new RefAffectationDto();

			if (refAffectation != null) {
				mapper.map(refAffectation, refAffectationDto);
			}
		} catch (RuntimeException e) {
			log.error("get RefAffectationDto list failed", e);
			throw e;
		}
		log.info("get RefAffectationDto list successed");
		return refAffectationDto;
	}

	@Override
	public RefPermissionDto findByIdFonction(String idfIndividu, int id) {
		RefPermissionDto refPermissionDto = null;
		try {
			RefPermission refPermission = refPermissionDaoImpl
					.findByIdFonction(idfIndividu, id);
			if (refPermission != null) {
				refPermissionDto = new RefPermissionDto();
				log.debug("get RefPermissionDto found successfully with function id = {}", new Object[]{id});
				mapper.map(refPermission, refPermissionDto);

			}
		} catch (RuntimeException e) {
			log.error("get RefPermissionDto failed", e);
			throw e;
		}
		return refPermissionDto;
	}

	@Override
	public List<RefPermissionDto> findByIdAction(int idRole, int id) {
		List<RefPermissionDto> refPermissionDtos = null;
		try {
			List<RefPermission> refPermissions = refPermissionDaoImpl
					.findByIdAction(idRole, id);

			refPermissionDtos = new ArrayList<RefPermissionDto>();

			for (RefPermission refPermission : refPermissions) {
				refPermissionDtos.add(mapper.map(refPermission,
						RefPermissionDto.class));
			}
		} catch (RuntimeException e) {
			log.error("get RefPermissionDto list failed", e);
			throw e;
		}
		log.info("get RefPermissionDto list successed");
		return refPermissionDtos;
	}

	@Override
	public List<RefPermissionDto> findActions(int idRole, int id) {
		List<RefPermissionDto> refPermissionDtos = null;
		try {
			List<RefPermission> refPermissions = refPermissionDaoImpl
					.findActions(idRole, id);

			refPermissionDtos = new ArrayList<RefPermissionDto>();

			for (RefPermission refPermission : refPermissions) {
				RefPermissionDto refPermissionDto = new RefPermissionDto();
				mapper.map(refPermission, refPermissionDto);
				if (refPermissionDto.getIdPeriode() == null
						|| verifyPeriode(
								refPermissionDto.getDateDebutPeriode(),
								refPermissionDto.getDateFinPeriode(),
								refPermissionDto.getPeriodique())) {
					refPermissionDtos.add(refPermissionDto);
				}
			}
		} catch (RuntimeException e) {
			log.error("get RefPermissionDto list failed", e);
			throw e;
		}
		log.info("get RefPermissionDto list successed");
		return refPermissionDtos;
	}

	@Override
	public boolean verifyPeriode(Date startDate, Date endDate,
			boolean periodique) {
		if (startDate == null || endDate == null) {
			return false;
		}
		try {
			Date currentDate = new Date();
			if (periodique) {

				Calendar cStrart = Calendar.getInstance();
				cStrart.setTime(startDate);
				cStrart.set(Calendar.YEAR, 2000);
				Calendar cEnd = Calendar.getInstance();
				cEnd.setTime(endDate);
				cEnd.set(Calendar.YEAR, 2000);
				Calendar cCurrent = Calendar.getInstance();
				cCurrent.setTime(currentDate);
				cCurrent.set(Calendar.YEAR, 2000);
				currentDate = cCurrent.getTime();
				startDate = cStrart.getTime();
				endDate = cEnd.getTime();
			}

			if (currentDate.before(startDate) || (currentDate.after(endDate))) {
				return false;
			}

		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
