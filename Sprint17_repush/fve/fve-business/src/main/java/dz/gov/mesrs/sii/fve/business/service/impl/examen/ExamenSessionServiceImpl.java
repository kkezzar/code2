package dz.gov.mesrs.sii.fve.business.service.impl.examen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.examen.ExamenSessionDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.examen.InscriptionExamenDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.NomenclatureDao;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.ExamenSession;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.InscriptionExamen;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.PlanningSession;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.RattachementMc;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.ExamenSessionByDateDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.ExamenSessionDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.ExamenSessionService;

/**
 * 
 * @author BELDI Jamel on : 17 sept. 2014 17:26:28
 */

@Service("examenSessionService")
public class ExamenSessionServiceImpl implements ExamenSessionService {

	@Autowired
	@Qualifier("examenSessionDao")
	private ExamenSessionDao examenSessionDao;
	@Autowired
	@Qualifier("nomenclatureDaoImpl")
	private NomenclatureDao nomenclatureDao;
	private static final Log log = LogFactory.getLog(ExamenSessionServiceImpl.class);

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	@Autowired
	@Qualifier("inscriptionExamenDao")
	private InscriptionExamenDao inscriptionExamenDao;

	public ExamenSessionServiceImpl() {

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public ExamenSessionDto insertOrUpdate(ExamenSessionDto examenSessionDto) {

		ExamenSession examenSession = mapper.map(examenSessionDto, ExamenSession.class);
		try {
			if (examenSession.getId() == 0) {
				examenSessionDao.persist(examenSession);
			} else {
				examenSession = examenSessionDao.merge(examenSession);
			}

			mapper.map(examenSession, examenSessionDto);

			log.info("insertorupdate ExamenSession succes..");

			return examenSessionDto;

		} catch (RuntimeException e) {
			log.error("insertorupdate ExamenSession failed--", e);
			throw e;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(ExamenSessionDto examenSessionDto) {
		try {

			ExamenSession examenSession = mapper.map(examenSessionDto, ExamenSession.class);

			examenSession = examenSessionDao.merge(examenSession);

			examenSessionDao.remove(examenSession);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public ExamenSessionDto findById(long id) {
		try {
			ExamenSession examenSession = examenSessionDao.findById(id);

			if (examenSession != null) {
				ExamenSessionDto _examenSessionDto = new ExamenSessionDto();
				_examenSessionDto.setRefTypeExamenLibelle(nomenclatureDao.findByCode("TYPE_EXAMEN",
				        examenSession.getRefTypeExamen()).getLibelleLongFr());
				mapper.map(examenSession, _examenSessionDto);
				return _examenSessionDto;
			}
		} catch (RuntimeException e) {
			log.error("findById failed", e);
			throw e;
		}
		return null;

	}

	@Override
	public List<ExamenSessionDto> findAll() {
		try {
			List<ExamenSession> examenSessions = examenSessionDao.findAll();

			List<ExamenSessionDto> examenSessionDtos = new ArrayList<ExamenSessionDto>();

			for (ExamenSession examenSession : examenSessions) {
				ExamenSessionDto _examenSessionDto = new ExamenSessionDto();
				_examenSessionDto.setRefTypeExamenLibelle(nomenclatureDao.findByCode("TYPE_EXAMEN",
				        examenSession.getRefTypeExamen()).getLibelleLongFr());
				mapper.map(examenSession, _examenSessionDto);
				examenSessionDtos.add(_examenSessionDto);
			}

			return examenSessionDtos;
		} catch (RuntimeException e) {
			log.error("findAll", e);
			throw e;
		}

	}

	@Override
	public List<ExamenSessionDto> findBySession(long idSession) {

		try {
			if (idSession != 0) {
				List<ExamenSession> examenSessions = examenSessionDao.findBySession(idSession);
				if (examenSessions != null) {
					List<ExamenSessionDto> examenSessionDtos = new ArrayList<ExamenSessionDto>();

					for (ExamenSession examenSession : examenSessions) {
						ExamenSessionDto _examenSessionDto = new ExamenSessionDto();
						_examenSessionDto.setRefTypeExamenLibelle(nomenclatureDao.findByCode("TYPE_EXAMEN",
						        examenSession.getRefTypeExamen()).getLibelleLongFr());
						mapper.map(examenSession, _examenSessionDto);
						List<InscriptionExamen> inscrits = inscriptionExamenDao.findByExamen(examenSession.getId());
						if (inscrits == null || inscrits.isEmpty()) {
							_examenSessionDto.setNbInscrits(0);
						} else {
							_examenSessionDto.setNbInscrits(inscrits.size());
						}
						examenSessionDtos.add(_examenSessionDto);
					}

					return examenSessionDtos;
				}

			}
		} catch (RuntimeException e) {
			log.error("findBySession failed", e);
			throw e;
		}
		return null;
	}

	@Override
	public List<ExamenSessionDto> findAdvanced(ExamenSessionDto examenSessionDto) {
		try {
			if (examenSessionDto != null) {
				ExamenSession examenSession = new ExamenSession();
				mapper.map(examenSessionDto, examenSession);
				List<ExamenSession> examenSessions = examenSessionDao.findAdvanced(examenSession);
				if (examenSessions != null) {
					List<ExamenSessionDto> examenSessionDtos = new ArrayList<ExamenSessionDto>();

					for (ExamenSession examen : examenSessions) {
						ExamenSessionDto _examenSessionDto = new ExamenSessionDto();
						_examenSessionDto.setRefTypeExamenLibelle(nomenclatureDao.findByCode("TYPE_EXAMEN",
						        examen.getRefTypeExamen()).getLibelleLongFr());
						mapper.map(examen, _examenSessionDto);
						examenSessionDtos.add(_examenSessionDto);
					}

					return examenSessionDtos;
				}

			}
		} catch (RuntimeException e) {
			log.error("findAdvanced failed", e);
			throw e;
		}
		return null;
	}

	@Override
	public List<ExamenSessionByDateDto> regroupeByDate(List<ExamenSessionDto> examensSessionList) {
		List<ExamenSessionByDateDto> result = new ArrayList<ExamenSessionByDateDto>();
		try {
			if (examensSessionList != null && !examensSessionList.isEmpty()) {
				ExamenSessionByDateDto examenSessionByDateDto = new ExamenSessionByDateDto();
				examenSessionByDateDto.setDateExamen(examensSessionList.get(0).getDateExamen());
				List<ExamenSessionDto> examens = new ArrayList<ExamenSessionDto>();
				for (ExamenSessionDto examenSessionDto : examensSessionList) {

					if (examenSessionDto.getDateExamen().equals(examenSessionByDateDto.getDateExamen())) {
						examens.add(examenSessionDto);
					} else {
						examenSessionByDateDto.setExamens(examens);
						result.add(examenSessionByDateDto);
						examenSessionByDateDto = new ExamenSessionByDateDto();
						examenSessionByDateDto.setDateExamen(examenSessionDto.getDateExamen());
						examens = new ArrayList<ExamenSessionDto>();
						examens.add(examenSessionDto);
					}

				}
				examenSessionByDateDto.setExamens(examens);
				result.add(examenSessionByDateDto);

			}
		} catch (RuntimeException e) {
			log.error("regroupeByDate failed", e);
			throw e;
		}
		return result;
	}

	@Override
	public ExamenSessionDto verifyExistingExamen(long idSession, int idRMC, Date dateExamen, Date heureDebut,
	        String refTypeExamen) {
		try {
			if (idSession != 0 && idRMC != 0 && dateExamen != null && heureDebut != null && refTypeExamen != null) {
				ExamenSession examenSession = new ExamenSession(0, new RattachementMc(idRMC), new PlanningSession(
				        idSession), dateExamen, heureDebut);
				List<ExamenSession> result = examenSessionDao.findAdvanced(examenSession);
				if (result != null && !result.isEmpty()) {
					return mapper.map(result.get(0), ExamenSessionDto.class);
				}
			}
			return null;

		} catch (RuntimeException e) {
			log.error("verifyExistingExamen failed", e);
			throw e;
		}
	}

	@Override
	public List<ExamenSessionDto> findExamenByPlanning(Long planningId) {
		try {

			if (planningId == null) {
				return null;
			}
			List<ExamenSessionDto> examenSessionDtos = new ArrayList<ExamenSessionDto>();
			List<ExamenSession> examenSessions = examenSessionDao.findExamenByPlanning(planningId);
			if (examenSessions != null) {
				for (ExamenSession _examenSession : examenSessions) {
					ExamenSessionDto _examenSessionDto = new ExamenSessionDto();
					mapper.map(_examenSession, _examenSessionDto);
					examenSessionDtos.add(_examenSessionDto);
				}
			}

			return examenSessionDtos;

		} catch (RuntimeException e) {
			log.error("findExamenByPlanning failed", e);
			throw e;
		}
	}

}
