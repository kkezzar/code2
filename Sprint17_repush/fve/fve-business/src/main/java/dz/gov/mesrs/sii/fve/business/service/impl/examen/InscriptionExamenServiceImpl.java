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
import org.springframework.util.Assert;

import dz.gov.mesrs.sii.commons.business.util.Utility;
import dz.gov.mesrs.sii.commons.data.dao.fve.examen.InscriptionExamenDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.NomenclatureDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierInscriptionAdministrative;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.InscriptionExamen;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.InscriptionExamenDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.SalleExamenDto;
import dz.gov.mesrs.sii.fve.business.service.examen.InscriptionExamenService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;

/**
 * 
 * @author BELDI Jamel on : 17 sept. 2014 17:30:44
 */

@Service("inscriptionExamenService")
public class InscriptionExamenServiceImpl implements InscriptionExamenService {

	@Autowired
	@Qualifier("inscriptionExamenDao")
	private InscriptionExamenDao inscriptionExamenDao;
	@Autowired
	@Qualifier("nomenclatureDaoImpl")
	private NomenclatureDao nomenclatureDao;
	private static final Log log = LogFactory
			.getLog(InscriptionExamenServiceImpl.class);

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	public InscriptionExamenServiceImpl() {

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public InscriptionExamenDto insertOrUpdate(
			InscriptionExamenDto inscriptionExamenDto) {
		try {
			InscriptionExamen inscriptionExamen = mapper.map(
					inscriptionExamenDto, InscriptionExamen.class);

			if (inscriptionExamen.getId() == 0) {

				inscriptionExamenDao.persist(inscriptionExamen);
			} else {
				inscriptionExamen = inscriptionExamenDao
						.merge(inscriptionExamen);
			}
			mapper.map(inscriptionExamen, inscriptionExamenDto);

			return inscriptionExamenDto;

		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(InscriptionExamenDto inscriptionExamenDto) {
		try {

			InscriptionExamen inscriptionExamen = mapper.map(
					inscriptionExamenDto, InscriptionExamen.class);

			inscriptionExamen = inscriptionExamenDao.merge(inscriptionExamen);

			inscriptionExamenDao.remove(inscriptionExamen);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public InscriptionExamenDto findById(long id) {

		InscriptionExamen inscriptionExamen = inscriptionExamenDao.findById(id);

		if (inscriptionExamen != null)
			return mapper.map(inscriptionExamen, InscriptionExamenDto.class);

		return null;
	}

	@Override
	public List<InscriptionExamenDto> findAll() {

		List<InscriptionExamen> inscriptionExamens = inscriptionExamenDao
				.findAll();

		List<InscriptionExamenDto> inscriptionExamenDtos = new ArrayList<InscriptionExamenDto>();

		for (InscriptionExamen inscriptionExamen : inscriptionExamens) {
			inscriptionExamenDtos.add(mapper.map(inscriptionExamen,
					InscriptionExamenDto.class));
		}

		return inscriptionExamenDtos;

	}

	@Override
	public List<InscriptionExamenDto> findBySalleExamen(int idSalleExamen) {

		try {
			if (idSalleExamen != 0) {
				List<InscriptionExamen> inscriptionExamens = inscriptionExamenDao
						.findBySalleExamen(idSalleExamen);
				if (inscriptionExamens != null) {
					List<InscriptionExamenDto> inscriptionExamenDtos = new ArrayList<InscriptionExamenDto>();

					for (InscriptionExamen inscriptionExamen : inscriptionExamens) {
						inscriptionExamenDtos.add(mapper.map(inscriptionExamen,
								InscriptionExamenDto.class));
					}

					return inscriptionExamenDtos;
				}

			}
		} catch (RuntimeException e) {
			log.error("findByExamen failed", e);
			throw e;
		}
		return null;
	}

	@Override
	public Double calculAverage(Long idExamen) {
		try {
			if (idExamen != null) {
				return inscriptionExamenDao.calculAverage(idExamen);
			}
		} catch (RuntimeException e) {
			log.error("calculAverage failed", e);
			throw e;
		}
		return null;

	}

	@Override
	public List<InscriptionExamenDto> findFailledStudent(Long idExamen,
			Double noteObtention) {
		try {
			List<InscriptionExamen> inscriptionExamens = inscriptionExamenDao
					.findFailledStudent(idExamen, noteObtention);

			List<InscriptionExamenDto> inscriptionExamenDtos = new ArrayList<InscriptionExamenDto>();

			for (InscriptionExamen inscriptionExamen : inscriptionExamens) {
				inscriptionExamenDtos.add(mapper.map(inscriptionExamen,
						InscriptionExamenDto.class));
			}

			return inscriptionExamenDtos;
		} catch (RuntimeException e) {
			log.error("findFailledStudent failed", e);
			throw e;
		}
	}

	@Override
	public List<InscriptionExamenDto> findByPlanning(Long idPlanning) {
		try {
			List<InscriptionExamen> inscriptionExamens = inscriptionExamenDao
					.findByPlanning(idPlanning);

			List<InscriptionExamenDto> inscriptionExamenDtos = new ArrayList<InscriptionExamenDto>();

			for (InscriptionExamen inscriptionExamen : inscriptionExamens) {
				inscriptionExamenDtos.add(mapper.map(inscriptionExamen,
						InscriptionExamenDto.class));
			}

			return inscriptionExamenDtos;
		} catch (RuntimeException e) {
			log.error("findFailledStudent failed", e);
			throw e;
		}
	}

	@Override
	public List<InscriptionExamenDto> findByExamen(Long examenSessionId) {
		try {
			if (examenSessionId != null && examenSessionId != 0) {
				List<InscriptionExamen> inscriptionExamens = inscriptionExamenDao
						.findByExamen(examenSessionId);
				if (inscriptionExamens != null) {
					List<InscriptionExamenDto> inscriptionExamenDtos = new ArrayList<InscriptionExamenDto>();

					for (InscriptionExamen inscriptionExamen : inscriptionExamens) {
						inscriptionExamenDtos.add(mapper.map(inscriptionExamen,
								InscriptionExamenDto.class));
					}

					return inscriptionExamenDtos;
				}

			}
		} catch (RuntimeException e) {
			log.error("findByExamen failed", e);
			throw e;
		}
		return null;
	}

	@Override
	public List<InscriptionExamenDto> findNotSubscribedOnExamen(
			Long examenSessionId, Integer ouvertureOffreFormationId) {
		try {
			if (examenSessionId != null && examenSessionId != 0
					&& ouvertureOffreFormationId != null
					&& ouvertureOffreFormationId != 0) {
				List<DossierInscriptionAdministrative> _dossierinscriptions = inscriptionExamenDao
						.findNotSubscribedOnExamen(examenSessionId,
								ouvertureOffreFormationId);
				if (_dossierinscriptions != null) {
					List<InscriptionExamenDto> inscriptionExamenDtos = new ArrayList<InscriptionExamenDto>();

					for (DossierInscriptionAdministrative dossierInscriptionAdministrative : _dossierinscriptions) {
						inscriptionExamenDtos.add(mapper.map(
								dossierInscriptionAdministrative,
								InscriptionExamenDto.class));
					}

					return inscriptionExamenDtos;
				}

			}
		} catch (RuntimeException e) {
			log.error("findByExamen failed", e);
			throw e;
		}
		return null;

	}

	@Override
	public List<InscriptionExamenDto> findNoteByPlanning(Long idPlanning) {
		try {
			List<InscriptionExamen> inscriptionExamens = inscriptionExamenDao
					.findNoteByPlanning(idPlanning);

			List<InscriptionExamenDto> inscriptionExamenDtos = new ArrayList<InscriptionExamenDto>();

			for (InscriptionExamen inscriptionExamen : inscriptionExamens) {
				inscriptionExamenDtos.add(mapper.map(inscriptionExamen,
						InscriptionExamenDto.class));
			}

			return inscriptionExamenDtos;
		} catch (RuntimeException e) {
			log.error("findNoteByPlanning failed", e);
			throw e;
		}
	}

	@Override
	public List<InscriptionExamenDto> findByPlanningAndStudent(
			Long idPlanning, Integer idDossierInscriptionAdministrative) {
		try {
			if (idPlanning != null && idPlanning != 0
					&& idDossierInscriptionAdministrative != null
					&& idDossierInscriptionAdministrative != 0) {
				List<InscriptionExamen> inscriptionExamens = inscriptionExamenDao
						.findByPlanningAndStudent(idPlanning,
								idDossierInscriptionAdministrative);
				if (inscriptionExamens != null) {
					List<InscriptionExamenDto> inscriptionExamenDtos = new ArrayList<InscriptionExamenDto>();

					for (InscriptionExamen inscriptionExamen : inscriptionExamens) {
						InscriptionExamenDto _inscriptionExamenDto = new InscriptionExamenDto();
						mapper.map(inscriptionExamen, _inscriptionExamenDto);
						setNoteJuryToDispaly(_inscriptionExamenDto);
						_inscriptionExamenDto
								.setRefTypeExamenLibelle(nomenclatureDao
										.findByCode(
												"TYPE_EXAMEN",
												inscriptionExamen
														.getSalleExamen()
														.getExamenSession()
														.getRefTypeExamen())
										.getLibelleLongFr());
						inscriptionExamenDtos.add(_inscriptionExamenDto);
					}
					return inscriptionExamenDtos;
				}

			}
			return null;

		} catch (RuntimeException e) {
			log.error("findByExamen failed", e);
			throw e;
		}

	}

	/**
	 * [InscriptionExamenServiceImpl.setNoteJuryToDispaly] Method
	 * 
	 * @author MAKERRI Sofiane on : 5 nov. 2014 14:12:03
	 * @param _inscriptionExamenDto
	 */
	private void setNoteJuryToDispaly(InscriptionExamenDto _inscriptionExamenDto) {

		if (_inscriptionExamenDto != null) {
			if (_inscriptionExamenDto.getNoteJury() != null
					&& _inscriptionExamenDto.getNoteExamen() != null
					&& _inscriptionExamenDto.getNoteJury().equals(
							_inscriptionExamenDto.getNoteExamen())) {
				_inscriptionExamenDto.setNoteJuryToDisplay(null);
			} else {
				_inscriptionExamenDto
						.setNoteJuryToDisplay(_inscriptionExamenDto
								.getNoteJury());
			}
		}

	}

	@Override
	public List<InscriptionExamenDto> findSubscribedAndNotSubscribed(
			SalleExamenDto salleExamenDto) {
		try {
			Assert.notNull(salleExamenDto, "Search Dto should not be null");

			switch (salleExamenDto.getNcTypeSessionCode()) {
			case UtilConstants.SESSION_NORMALE_CODE:
				// Les inscrits dans l'offre et les etudaints qui ont des dettes
			case UtilConstants.SESSION_REMPLACEMENT_CODE:
				// les absents dans la session normale
			case UtilConstants.SESSION_RATTRAPPAGE_CODE:
				// les �tudiants qui ont note< � la moyenne dans la session
				// normale

			}

		} catch (RuntimeException e) {
			log.error("findSubscribedAndNotSubscribed failed", e);
			throw e;
		}
		return null;
	}

	@Override
	public List<InscriptionExamenDto> findAdvancedNotSubscribedOnExamen(
			Long examenSessionId,
			DossierInscriptionAdministrativeDto searchDto) {
		try {
			if (examenSessionId != null && examenSessionId != 0
					&& searchDto != null) {
				DossierInscriptionAdministrative search = new DossierInscriptionAdministrative();
				search = mapper.map(searchDto,
						DossierInscriptionAdministrative.class);
				List<DossierInscriptionAdministrative> _dossierinscriptions = inscriptionExamenDao
						.findAdvancedNotSubscribedOnExamen(examenSessionId,
								search);
				if (_dossierinscriptions != null) {
					List<InscriptionExamenDto> inscriptionExamenDtos = new ArrayList<InscriptionExamenDto>();

					for (DossierInscriptionAdministrative dossierInscriptionAdministrative : _dossierinscriptions) {
						inscriptionExamenDtos.add(mapper.map(
								dossierInscriptionAdministrative,
								InscriptionExamenDto.class));
					}

					return inscriptionExamenDtos;
				}

			}
		} catch (RuntimeException e) {
			log.error("findByExamen failed", e);
			throw e;
		}
		return null;
	}

	@Override
	public List<InscriptionExamenDto> findAbsentByPlanningAndRattachementMc(
			Long planningId, Integer rattachementMcId,
			Boolean absenceJustifie) {
		try {
			if (rattachementMcId != null && rattachementMcId != 0) {
				List<InscriptionExamen> _dossierinscriptions = inscriptionExamenDao
						.findAbsentByPlanningAndRattachementMc(planningId,
								rattachementMcId, absenceJustifie);
				if (_dossierinscriptions != null) {
					List<InscriptionExamenDto> inscriptionExamenDtos = new ArrayList<InscriptionExamenDto>();

					for (InscriptionExamen inscriptionExamen : _dossierinscriptions) {
						inscriptionExamenDtos.add(mapper.map(inscriptionExamen,
								InscriptionExamenDto.class));
					}

					return inscriptionExamenDtos;
				}

			}
		} catch (RuntimeException e) {
			log.error("findAbsentByPlanningAndRattachementMc failed", e);
			throw e;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.fve.business.service.cursus.InscriptionExamenService
	 * #findAdvanced
	 * (dz.gov.mesrs.sii.fve.business.model.dto.cursus.InscriptionExamenDto)
	 */
	@Override
	public List<InscriptionExamenDto> findAdvanced(
			InscriptionExamenDto searchDto) {
		try {
			if (searchDto != null) {
				InscriptionExamen search = mapper.map(searchDto,
						InscriptionExamen.class);
				List<InscriptionExamen> _dossierinscriptions = inscriptionExamenDao
						.findAdvanced(search);
				if (_dossierinscriptions != null) {
					List<InscriptionExamenDto> inscriptionExamenDtos = new ArrayList<InscriptionExamenDto>();

					for (InscriptionExamen inscriptionExamen : _dossierinscriptions) {
						inscriptionExamenDtos.add(mapper.map(inscriptionExamen,
								InscriptionExamenDto.class));
					}

					return inscriptionExamenDtos;
				}

			}
		} catch (RuntimeException e) {
			log.error("findAdvanced failed", e);
			throw e;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.fve.business.service.cursus.InscriptionExamenService
	 * #findDiaByPlanning(java.lang.Integer)
	 */
	@Override
	public List<DossierInscriptionAdministrativeDto> findDiaByPlanning(
			Long idPlanning) {
		try {
			List<DossierInscriptionAdministrative> dossierInscriptionAdministratives = inscriptionExamenDao
					.findDiaByPlanning(idPlanning);

			return Utility.map(mapper, dossierInscriptionAdministratives,
					DossierInscriptionAdministrativeDto.class);
		} catch (RuntimeException e) {
			log.error("findDiaByPlanning failed", e);
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.fve.business.service.examen.InscriptionExamenService#findByPlanningId(java.lang.Long)
	 */
	@Override
	public List<InscriptionExamenDto> findByPlanningId(Long idPlanning) {
		try {
			List<InscriptionExamen> inscriptionExamens = inscriptionExamenDao
					.findByPlanningId(idPlanning);

			List<InscriptionExamenDto> inscriptionExamenDtos = new ArrayList<InscriptionExamenDto>();

			for (InscriptionExamen inscriptionExamen : inscriptionExamens) {
				inscriptionExamenDtos.add(mapper.map(inscriptionExamen,
						InscriptionExamenDto.class));
			}

			return inscriptionExamenDtos;
		} catch (RuntimeException e) {
			log.error("findByPlanningId failed", e);
			throw e;
		}
	}
}
