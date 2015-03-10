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

import dz.gov.mesrs.sii.commons.data.dao.fve.examen.InscriptionExamenDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.examen.ResponsableExamenDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.examen.SalleExamenDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefLieuDao;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.InscriptionExamen;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.ResponsableExamen;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.SalleExamen;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefLieu;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.InscriptionExamenDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.ResponsableExamenDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.SalleExamenDto;
import dz.gov.mesrs.sii.fve.business.service.examen.SalleExamenService;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */

@Service("salleExamenService")
public class SalleExamenServiceImpl implements SalleExamenService {

	@Autowired
	@Qualifier("salleExamenDao")
	private SalleExamenDao salleExamenDao;
	@Autowired
	@Qualifier("refLieuDaoImpl")
	private RefLieuDao refLieuDao;
	@Autowired
	@Qualifier("inscriptionExamenDao")
	private InscriptionExamenDao inscriptionExamenDao;
	@Autowired
	@Qualifier("responsableExamenDao")
	private ResponsableExamenDao responsableExamenDao;
	private static final Log log = LogFactory
			.getLog(SalleExamenServiceImpl.class);

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	public SalleExamenServiceImpl() {

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public SalleExamenDto insertOrUpdate(SalleExamenDto salleExamenDto) {

		SalleExamen salleExamen = mapper.map(salleExamenDto, SalleExamen.class);
		try {
			if (salleExamen.getId() == 0) {
				salleExamenDao.persist(salleExamen);
				List<InscriptionExamen> etudiantList = salleExamen
						.getInscriptionExamens();
				List<ResponsableExamen> responsableList = salleExamen
						.getResponsableExamens();
				if (etudiantList != null) {
					for (InscriptionExamen _etudiant : etudiantList) {
						_etudiant.setSalleExamen(salleExamen);
					}
				}
				if (responsableList != null) {
					for (ResponsableExamen _responsable : responsableList) {
						_responsable.setSalleExamen(salleExamen);
					}
				}

			} else {
				salleExamen = salleExamenDao.merge(salleExamen);
			}
			mapper.map(salleExamen, salleExamenDto);

			log.error("insertorupdate SalleExamen succes..");

			return salleExamenDto;

		} catch (RuntimeException e) {
			log.error("insertorupdate SalleExamen failed--", e);
			throw e;
		}
	}

	@Override
	public void remove(SalleExamenDto salleExamenDto) {
		try {

			SalleExamen salleExamen = mapper.map(salleExamenDto,
					SalleExamen.class);
			salleExamen = salleExamenDao.findById(salleExamen.getId());
			// salleExamen = salleExamenDao.merge(salleExamen);
			if (salleExamen != null) {
				salleExamenDao.remove(salleExamen);
			}

		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public SalleExamenDto findById(int id) {
		try {
			SalleExamen salleExamen = salleExamenDao.findById(id);

			if (salleExamen != null)
				return mapper.map(salleExamen, SalleExamenDto.class);

			return null;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<SalleExamenDto> findAll() {
		try {
			List<SalleExamen> salleExamens = salleExamenDao.findAll();

			List<SalleExamenDto> salleExamenDtos = new ArrayList<SalleExamenDto>();

			for (SalleExamen salleExamen : salleExamens) {
				salleExamenDtos.add(mapper.map(salleExamen,
						SalleExamenDto.class));
			}

			return salleExamenDtos;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<SalleExamenDto> findByExamen(Long idExamen) {
		try {
			List<SalleExamen> salleExamens = salleExamenDao
					.findByExamen(idExamen);

			List<SalleExamenDto> salleExamenDtos = new ArrayList<SalleExamenDto>();

			for (SalleExamen salleExamen : salleExamens) {
				salleExamenDtos.add(mapper.map(salleExamen,
						SalleExamenDto.class));
			}

			return salleExamenDtos;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<SalleExamenDto> findSalleNotaffectedToExamen(Long idExamen,
			Integer etabId) {
		try {
			List<SalleExamenDto> salleExamenDtos = new ArrayList<SalleExamenDto>();
			List<RefLieu> _lieux = refLieuDao.findSalleAndAmphi(etabId);
			if (_lieux != null && !_lieux.isEmpty()) {
				for (RefLieu refLieu : _lieux) {
					List<SalleExamen> salleExamens = salleExamenDao
							.findByExamenAndSalle(idExamen, refLieu.getId());
					if (salleExamens == null || salleExamens.isEmpty()) {
						salleExamenDtos.add(mapper.map(refLieu,
								SalleExamenDto.class));
					}
				}

			}

			return salleExamenDtos;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	/**
	 * [SalleExamenServiceImpl.salleExamenDao] Getter
	 * 
	 * @author BELDI Jamel on : 15 oct. 2014 11:02:35
	 * @return the salleExamenDao
	 */
	public SalleExamenDao getSalleExamenDao() {
		return salleExamenDao;
	}

	/**
	 * [SalleExamenServiceImpl.salleExamenDao] Setter
	 * 
	 * @author BELDI Jamel on : 15 oct. 2014 11:02:35
	 * @param salleExamenDao
	 *            the salleExamenDao to set
	 */
	public void setSalleExamenDao(SalleExamenDao salleExamenDao) {
		this.salleExamenDao = salleExamenDao;
	}

	/**
	 * [SalleExamenServiceImpl.refLieuDao] Getter
	 * 
	 * @author BELDI Jamel on : 15 oct. 2014 11:02:35
	 * @return the refLieuDao
	 */
	public RefLieuDao getRefLieuDao() {
		return refLieuDao;
	}

	/**
	 * [SalleExamenServiceImpl.refLieuDao] Setter
	 * 
	 * @author BELDI Jamel on : 15 oct. 2014 11:02:35
	 * @param refLieuDao
	 *            the refLieuDao to set
	 */
	public void setRefLieuDao(RefLieuDao refLieuDao) {
		this.refLieuDao = refLieuDao;
	}

	@Override
	public List<SalleExamenDto> findBySession(long idSession,
			boolean withStudentList, boolean withListResponsableList) {
		try {
			List<SalleExamenDto> salleExamenDtos = new ArrayList<SalleExamenDto>();
			if (idSession == 0) {
				return salleExamenDtos;
			}

			List<SalleExamen> salleExamens = salleExamenDao
					.findBySession(idSession);
			if (salleExamens != null && !salleExamens.isEmpty()) {

				for (SalleExamen salleExamen : salleExamens) {
					SalleExamenDto salleExamenDto = new SalleExamenDto();
					mapper.map(salleExamen, salleExamenDto);
					if (withStudentList) {
						List<InscriptionExamen> inscriptionExamens = inscriptionExamenDao
								.findBySalleExamen(salleExamen.getId());
						List<InscriptionExamenDto> inscriptionExamenDtos = new ArrayList<InscriptionExamenDto>();
						if (inscriptionExamens != null) {
							for (InscriptionExamen inscriptionExamen : inscriptionExamens) {
								inscriptionExamenDtos.add(mapper.map(
										inscriptionExamen,
										InscriptionExamenDto.class));
							}
						}

						salleExamenDto.setEtudiants(inscriptionExamenDtos);
					}
					if (withListResponsableList) {
						List<ResponsableExamenDto> absenceResponsableDtos = new ArrayList<ResponsableExamenDto>();
						List<ResponsableExamen> absenceResponsables = responsableExamenDao
								.findBySalleExamen(salleExamen.getId());
						if (absenceResponsables != null) {
							for (ResponsableExamen absenceResponsable : absenceResponsables) {
								absenceResponsableDtos.add(mapper.map(
										absenceResponsable,
										ResponsableExamenDto.class));
							}

						}
						salleExamenDto.setResponsables(absenceResponsableDtos);

					}
					salleExamenDtos.add(salleExamenDto);
				}
			}
			return salleExamenDtos;
		} catch (RuntimeException e) {
			throw e;
		}
	}

}
