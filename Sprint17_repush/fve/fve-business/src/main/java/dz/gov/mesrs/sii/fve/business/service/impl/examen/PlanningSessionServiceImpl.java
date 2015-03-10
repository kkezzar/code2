package dz.gov.mesrs.sii.fve.business.service.impl.examen;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.examen.PlanningSessionDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.NomenclatureDao;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.ExamenSession;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.PlanningSession;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.ExamenSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.PlanningSessionDto;
import dz.gov.mesrs.sii.fve.business.service.examen.PlanningSessionService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;

/**
 * 
 * @author BELDI Jamel on : 17 sept. 2014 17:30:53
 */

@Service("planningSessionService")
public class PlanningSessionServiceImpl implements PlanningSessionService {

	@Autowired
	@Qualifier("planningSessionDao")
	private PlanningSessionDao planningSessionDao;

	@Autowired
	@Qualifier("situationEntiteDao")
	private SituationEntiteDao situationEntiteDao;

	private static final Log log = LogFactory
			.getLog(PlanningSessionServiceImpl.class);

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	@Autowired
	@Qualifier("nomenclatureDaoImpl")
	private NomenclatureDao nomenclatureDao;

	public PlanningSessionServiceImpl() {

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public PlanningSessionDto insertOrUpdate(
			PlanningSessionDto planningSessionDto) {

		if (planningSessionDto.getCoefficient() == 0.0) {
			planningSessionDto.setCoefficient(1.0);
		}

		PlanningSession planningSession = mapper.map(planningSessionDto,
				PlanningSession.class);
		try {
			if (planningSession.getId() == 0) {
				planningSessionDao.persist(planningSession);

			} else {
				planningSession = planningSessionDao.merge(planningSession);
			}

			mapper.map(planningSession, planningSessionDto);
			mapSituation(planningSessionDto, planningSession);
			if (planningSession.getExamenSessions() != null
					&& !planningSession.getExamenSessions().isEmpty()) {
				Set<ExamenSession> _examens = planningSession
						.getExamenSessions();
				Set<ExamenSessionDto> examenSessionDtos = new HashSet<ExamenSessionDto>();
				for (ExamenSession _examenSession : _examens) {
					ExamenSessionDto _examenSessionDto = new ExamenSessionDto();
					mapper.map(_examenSession, _examenSessionDto);
					examenSessionDtos.add(_examenSessionDto);
				}
				planningSessionDto.setExamenSessionDtos(examenSessionDtos);
			}

			log.error("insertorupdate PlanningSession succes..");

			return planningSessionDto;

		} catch (RuntimeException e) {
			log.error("insertorupdate PlanningSession failed--", e);
			throw e;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(PlanningSessionDto planningSessionDto) {
		try {

			PlanningSession planningSession = mapper.map(planningSessionDto,
					PlanningSession.class);

			planningSession = planningSessionDao.merge(planningSession);

			planningSessionDao.remove(planningSession);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public PlanningSessionDto findById(long id) {

		PlanningSession _planningSession = planningSessionDao.findById(id);

		if (_planningSession != null) {
			PlanningSessionDto _planningSessionDto = new PlanningSessionDto();

			mapper.map(_planningSession, _planningSessionDto);
			// Nomenclature typeSession =
			// nomenclatureDao.findByCode("TYPE_SESSION",
			// _planningSession.getRefCodeTypeSession());
			// _planningSessionDto.setRefTypeSessionSessionLibelleFr(typeSession!=null?typeSession.getLibelleLongFr():null);
			mapSituation(_planningSessionDto, _planningSession);
			if (_planningSession.getExamenSessions() != null
					&& !_planningSession.getExamenSessions().isEmpty()) {
				Set<ExamenSession> _examens = _planningSession
						.getExamenSessions();
				Set<ExamenSessionDto> examenSessionDtos = new HashSet<ExamenSessionDto>();
				for (ExamenSession _examenSession : _examens) {
					ExamenSessionDto _examenSessionDto = new ExamenSessionDto();
					mapper.map(_examenSession, _examenSessionDto);
					examenSessionDtos.add(_examenSessionDto);

				}
				_planningSessionDto.setExamenSessionDtos(examenSessionDtos);
			}
			return _planningSessionDto;
		}

		return null;
	}

	@Override
	public List<PlanningSessionDto> findAll() {

		List<PlanningSession> planningSessions = planningSessionDao.findAll();

		List<PlanningSessionDto> planningSessionDtos = new ArrayList<PlanningSessionDto>();

		for (PlanningSession planningSession : planningSessions) {

			PlanningSessionDto _planningSessionDto = new PlanningSessionDto();
			mapper.map(planningSession, _planningSessionDto);
			// Nomenclature typeSession =
			// nomenclatureDao.findByCode("TYPE_SESSION",
			// planningSession.getRefCodeTypeSession());
			// _planningSessionDto.setRefTypeSessionSessionLibelleFr(typeSession!=null?typeSession.getLibelleLongFr():null);
			mapSituation(_planningSessionDto, planningSession);
			if (planningSession.getExamenSessions() != null
					&& !planningSession.getExamenSessions().isEmpty()) {
				Set<ExamenSession> _examens = planningSession
						.getExamenSessions();
				Set<ExamenSessionDto> examenSessionDtos = new HashSet<ExamenSessionDto>();
				for (ExamenSession _examenSession : _examens) {
					ExamenSessionDto _examenSessionDto = new ExamenSessionDto();
					mapper.map(_examenSession, _examenSessionDto);
					examenSessionDtos.add(_examenSessionDto);
				}
				_planningSessionDto.setExamenSessionDtos(examenSessionDtos);
			}

			planningSessionDtos.add(_planningSessionDto);
		}

		return planningSessionDtos;

	}

	@Override
	/**
	 * Find One PlanningSessionDto By Year an OF and Level and Period
	 */
	public PlanningSessionDto findByYearAndOfAndLevelAndPeriodAndTypeSession(
			PlanningSessionDto searchDto) {

		try {
			if (searchDto != null && searchDto.getAnneeAcademiqueId() != null
					&& searchDto.getOuvertureOffreFormationId() != null
					&& searchDto.getNiveauId() != null
					&& searchDto.getIdPeriode() != null) {
				PlanningSession _planningSession = planningSessionDao
						.findByYearAndOfAndLevelAndPeriodAndTypeSession(
								searchDto.getAnneeAcademiqueId(),
								searchDto.getOuvertureOffreFormationId(),
								searchDto.getNiveauId(),
								searchDto.getIdPeriode(),
								searchDto.getNcTypeSessionId());
				if (_planningSession != null) {
					PlanningSessionDto _planningSessionDto = new PlanningSessionDto();
					mapper.map(_planningSession, _planningSessionDto);
					// Nomenclature typeSession =
					// nomenclatureDao.findByCode("TYPE_SESSION",
					// _planningSession.getRefCodeTypeSession());
					// _planningSessionDto.setRefTypeSessionSessionLibelleFr(typeSession!=null?typeSession.getLibelleLongFr():null);
					mapSituation(_planningSessionDto, _planningSession);
					if (_planningSession.getExamenSessions() != null
							&& !_planningSession.getExamenSessions().isEmpty()) {
						Set<ExamenSession> _examens = _planningSession
								.getExamenSessions();
						Set<ExamenSessionDto> examenSessionDtos = new HashSet<ExamenSessionDto>();
						for (ExamenSession _examenSession : _examens) {
							ExamenSessionDto _examenSessionDto = new ExamenSessionDto();
							mapper.map(_examenSession, _examenSessionDto);
							examenSessionDtos.add(_examenSessionDto);

						}
						_planningSessionDto
								.setExamenSessionDtos(examenSessionDtos);

					}
					return _planningSessionDto;
				}

			}
		} catch (RuntimeException e) {
			log.error("findByYearAndOfAndLevelAndPeriod failed", e);
			throw e;
		}
		return null;
	}

	/**
	 * Publish PlanningSession
	 */
	@Override
	public boolean publish(long id) {
		try {
			PlanningSession planningSession = planningSessionDao.findById(id);
			if (planningSession != null) {
				planningSession
						.setSituationEntite(situationEntiteDao
								.findById(situationEntiteDao
										.findByCodeSituationByCodeEntite(
												UtilConstants.ENTITE_PLANNING_SESSION_CODE,
												UtilConstants.SITUTAION_PUBLIEE_CODE)
										.getId()));
				planningSession.setDatePublication(new Date());
				planningSessionDao.merge(planningSession);
				return true;
			}

		} catch (RuntimeException e) {
			log.error("publish failed", e);
			throw e;

		}
		return false;
	}

	/**
	 * Fence PlanningSession
	 */
	@Override
	public boolean fence(long id) {
		try {
			PlanningSession planningSession = planningSessionDao.findById(id);
			if (planningSession != null) {
				planningSession
						.setSituationEntite(situationEntiteDao
								.findById(situationEntiteDao
										.findByCodeSituationByCodeEntite(
												UtilConstants.ENTITE_PLANNING_SESSION_CODE,
												UtilConstants.SITUTAION_CLOTUREE_CODE)
										.getId()));
				planningSession.setDateCloture(new Date());
				planningSessionDao.merge(planningSession);
				return true;
			}

		} catch (RuntimeException e) {
			log.error("fence failed", e);
			throw e;

		}

		return false;
	}

	/**
	 * 
	 * [PlanningSessionServiceImpl.mapSituation] Method
	 * 
	 * @author BELDI Jamel on : 28 sept. 2014 16:56:35
	 * @param planningSessionDto
	 * @param planningSession
	 */
	private void mapSituation(PlanningSessionDto planningSessionDto,
			PlanningSession planningSession) {
//		if (planningSession != null) {
//
//			// initialisation de la situation.
//			if (planningSession.getSituationEntite() != null) {
//				List<SituationI18n> _situationI18n = situationEntiteDao
//						.findByIdSituationEntite(planningSession
//								.getSituationEntite().getId());
//				if (_situationI18n != null) {
//					for (SituationI18n _item : _situationI18n) {
//						if (_item.getLangue().equalsIgnoreCase("fr"))
//							planningSessionDto.setSituationLibelleFr(_item
//									.getLibelle());
//						if (_item.getLangue().equalsIgnoreCase("ar"))
//							planningSessionDto.setSituationLibelleAr(_item
//									.getLibelle());
//					}
//				}
//			}
//		}
	}

	@Override
	public List<PlanningSessionDto> findAdvanced(PlanningSessionDto searchDto) {
		try {
			PlanningSession planningSession = mapper.map(searchDto,
					PlanningSession.class);
			List<PlanningSession> planningSessions = planningSessionDao
					.findAdvanced(planningSession);

			List<PlanningSessionDto> planningSessionDtos = new ArrayList<PlanningSessionDto>();
			if (planningSessions != null) {
				for (PlanningSession _planningSession : planningSessions) {
					PlanningSessionDto _planningSessionDto = new PlanningSessionDto();
					mapper.map(_planningSession, _planningSessionDto);
					mapSituation(_planningSessionDto, _planningSession);
					if (_planningSession.getExamenSessions() != null
							&& !_planningSession.getExamenSessions().isEmpty()) {
						Set<ExamenSession> _examens = _planningSession
								.getExamenSessions();
						Set<ExamenSessionDto> examenSessionDtos = new HashSet<ExamenSessionDto>();
						for (ExamenSession _examenSession : _examens) {
							ExamenSessionDto _examenSessionDto = new ExamenSessionDto();
							mapper.map(_examenSession, _examenSessionDto);
							examenSessionDtos.add(_examenSessionDto);
						}
						_planningSessionDto
								.setExamenSessionDtos(examenSessionDtos);
					}
					planningSessionDtos.add(_planningSessionDto);
				}
			}

			return planningSessionDtos;

		} catch (RuntimeException e) {
			log.error("findByYearAndOfAndLevelAndPeriod failed", e);
			throw e;
		}

	}

	@Override
	public List<PlanningSessionDto> findSessionforStudent(
			int idDossierInsciptionAdministrative) {
		try {
			List<PlanningSessionDto> planningSessionDtos = new ArrayList<PlanningSessionDto>();
			if (idDossierInsciptionAdministrative == 0) {
				return planningSessionDtos;
			}
			List<PlanningSession> planningSessions = planningSessionDao
					.findSessionforStudent(idDossierInsciptionAdministrative);
			if (planningSessions != null) {
				for (PlanningSession _planningSession : planningSessions) {
					PlanningSessionDto _planningSessionDto = new PlanningSessionDto();
					mapper.map(_planningSession, _planningSessionDto);
//					mapSituation(_planningSessionDto, _planningSession);
					planningSessionDtos.add(_planningSessionDto);
				}
			}

			return planningSessionDtos;

		} catch (RuntimeException e) {
			log.error("findByYearAndOfAndLevelAndPeriod failed", e);
			throw e;
		}

	}

}
