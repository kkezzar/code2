package dz.gov.mesrs.sii.fve.business.service.impl.examen;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.examen.ResponsableExamenDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefAffectationDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefGroupeDao;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.ResponsableExamen;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAffectation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefGroupe;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.ResponsableExamenDto;
import dz.gov.mesrs.sii.fve.business.service.examen.ResponsableExamenService;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */

@Service("responsableExamenService")
public class ResponsableExamenServiceImpl implements ResponsableExamenService {

	@Autowired
	@Qualifier("responsableExamenDao")
	private ResponsableExamenDao responsableExamenDao;

	private static final Log log = LogFactory
			.getLog(ResponsableExamenServiceImpl.class);

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	@Autowired
	@Qualifier("refAffectationDaoImpl")
	private RefAffectationDao refAffectationDao;
	@Autowired
	@Qualifier("refGroupeDaoImpl")
	private RefGroupeDao refGroupeDao;

	public ResponsableExamenServiceImpl() {

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public ResponsableExamenDto insertOrUpdate(
			ResponsableExamenDto absenceResponsableDto) {

		ResponsableExamen absenceResponsable = mapper.map(
				absenceResponsableDto, ResponsableExamen.class);
		try {
			if (absenceResponsable.getId() == 0) {
				responsableExamenDao.persist(absenceResponsable);
			} else {
				absenceResponsable = responsableExamenDao
						.merge(absenceResponsable);
			}
			mapper.map(absenceResponsable, absenceResponsableDto);

			log.error("insertorupdate AbsenceResponsable succes..");

			return absenceResponsableDto;

		} catch (RuntimeException e) {
			log.error("insertorupdate AbsenceResponsable failed--", e);
			throw e;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(ResponsableExamenDto absenceResponsableDto) {
		try {

			ResponsableExamen absenceResponsable = mapper.map(
					absenceResponsableDto, ResponsableExamen.class);

			absenceResponsable = responsableExamenDao.merge(absenceResponsable);

			responsableExamenDao.remove(absenceResponsable);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public ResponsableExamenDto findById(int id) {

		ResponsableExamen absenceResponsable = responsableExamenDao
				.findById(id);

		if (absenceResponsable != null)
			return mapper.map(absenceResponsable, ResponsableExamenDto.class);

		return null;
	}

	@Override
	public List<ResponsableExamenDto> findAll() {

		List<ResponsableExamen> absenceResponsables = responsableExamenDao
				.findAll();

		List<ResponsableExamenDto> absenceResponsableDtos = new ArrayList<ResponsableExamenDto>();

		for (ResponsableExamen absenceResponsable : absenceResponsables) {
			absenceResponsableDtos.add(mapper.map(absenceResponsable,
					ResponsableExamenDto.class));
		}

		return absenceResponsableDtos;

	}

	@Override
	public List<ResponsableExamenDto> findByExamen(long idExamen) {

		try {
			if (idExamen != 0) {
				List<ResponsableExamen> absenceResponsables = responsableExamenDao
						.findByExamen(idExamen);
				if (absenceResponsables != null) {
					List<ResponsableExamenDto> absenceResponsableDtos = new ArrayList<ResponsableExamenDto>();

					for (ResponsableExamen absenceResponsable : absenceResponsables) {
						absenceResponsableDtos
								.add(mapper.map(absenceResponsable,
										ResponsableExamenDto.class));
					}

					return absenceResponsableDtos;
				}

			}
		} catch (RuntimeException e) {
			log.error("findByExamen failed", e);
			throw e;
		}
		return null;
	}

	@Override
	public void removeByExamen(int idExamen) {
		try {
			if (idExamen != 0) {
				responsableExamenDao.removeByExamen(idExamen);

			}

		} catch (RuntimeException e) {
			log.error("findByExamen failed", e);
			throw e;
		}

	}

	@Override
	public List<ResponsableExamenDto> findNotSubscribedOnExamen(
			Long examenSessionId, Integer etabId, Integer roleId) {
		try {
			if (examenSessionId == null || examenSessionId == 0
					|| etabId == null || etabId == 0 || roleId == null) {
				return null;
			}
			// List<RefGroupe> groupes = refGroupeDao.findAdvanced(etabId, new
			// RefGroupe(refCodeGroupe, null));
			// if (groupes == null || groupes.isEmpty()) {
			// return null;
			// }
			// List<RefAffectation> _refAffectations =
			// refAffectationDao.findIndividusOfGroupe(groupes.get(0).getId());
			List<RefIndividu> _refIndividus = refAffectationDao
					.findIndividuByRole(etabId, roleId);
			if (_refIndividus != null) {
				List<ResponsableExamenDto> responsableDtos = new ArrayList<ResponsableExamenDto>();

				for (RefIndividu refIndividu : _refIndividus) {
					if (responsableExamenDao.findByExamenAndIndividu(
							examenSessionId, refIndividu.getId()) == null) {
						responsableDtos.add(mapper.map(refIndividu,
								ResponsableExamenDto.class));
					}
				}

				return responsableDtos;
			}

		} catch (RuntimeException e) {
			log.error("findByExamen failed", e);
			throw e;
		}
		return null;
	}

	@Override
	public List<ResponsableExamenDto> findBySalleExamen(Integer idSalleExamen) {
		try {
			if (idSalleExamen != 0) {
				List<ResponsableExamen> absenceResponsables = responsableExamenDao
						.findBySalleExamen(idSalleExamen);
				if (absenceResponsables != null) {
					List<ResponsableExamenDto> absenceResponsableDtos = new ArrayList<ResponsableExamenDto>();

					for (ResponsableExamen absenceResponsable : absenceResponsables) {
						absenceResponsableDtos
								.add(mapper.map(absenceResponsable,
										ResponsableExamenDto.class));
					}

					return absenceResponsableDtos;
				}

			}
		} catch (RuntimeException e) {
			log.error("findByExamen failed", e);
			throw e;
		}
		return null;
	}

	// @Override
	// @Transactional(value = "transactionManager", propagation =
	// Propagation.REQUIRED)
	// public void saveResponsablesByExamenAndGroupe(int idExamen, int idGroupe)
	// {
	// try {
	// if (idExamen != 0 && idGroupe!=0) {
	// List<RefAffectation> listAffectation =
	// refAffectationDao.findIndividusOfGroupe(idGroupe);
	// if(listAffectation==null || listAffectation.isEmpty()){
	// return;
	// }
	// for (RefAffectation refAffectation : listAffectation) {
	// AbsenceResponsable absenceResponsable = new AbsenceResponsable();
	// absenceResponsable.setExamenSession(new ExamenSession(idExamen));
	// absenceResponsable.setRefIndividu(refAffectation.getRefIndividu());
	// responsableExamenDao
	// .persist(absenceResponsable);
	// }
	//
	//
	// }
	//
	// } catch (RuntimeException e) {
	// log.error("saveResponsablesByExamenAndGroupe failed", e);
	// throw e;
	// }
	//
	// }

}
