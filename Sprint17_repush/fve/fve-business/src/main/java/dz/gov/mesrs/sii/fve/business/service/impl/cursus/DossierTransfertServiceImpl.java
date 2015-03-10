package dz.gov.mesrs.sii.fve.business.service.impl.cursus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationDao;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteDao;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteOccurrenceDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.bac.DossierBachelierDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DossierDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DossierEtudiantDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DossierTransfertDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntiteOccurrence;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Dossier;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierTransfert;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OuvertureOffreFormation;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierTransfertDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierTransfertService;

@Service("dossierTransfertService")
public class DossierTransfertServiceImpl implements DossierTransfertService {
	private static final Log log = LogFactory.getLog(DossierTransfertServiceImpl.class);
	@Autowired
	@Qualifier("dossierTransfertDao")
	private DossierTransfertDao dossierTransfertDao;
	@Autowired
	@Qualifier("dossierDao")
	private DossierDao dossierDao;

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	@Autowired
	@Qualifier("situationEntiteDao")
	private SituationEntiteDao situationEntiteDao;

	@Autowired
	@Qualifier("dossierBachelierDao")
	private DossierBachelierDao dossierBachelierDao;

	@Autowired
	@Qualifier("dossierEtudiantDao")
	private DossierEtudiantDao dossierEtudiantDao;

	@Autowired
	@Qualifier("situationDao")
	SituationDao situationDao;

	@Autowired
	@Qualifier("situationEntiteOccurrenceDao")
	SituationEntiteOccurrenceDao situationEntiteOccurrenceDao;

	public DossierTransfertServiceImpl() {

	}

	@Override
	@Transactional()
	public DossierTransfertDto insertOrUpdate(DossierTransfertDto dossierTransfertDto) {
		try {
			Dossier dossier = new Dossier();
			mapper.map(dossierTransfertDto, dossier);
			DossierTransfert dossierTransfert = mapper.map(dossierTransfertDto, DossierTransfert.class);

			if (dossierTransfert.getId() == 0) {
				dossier.setDateCreation(new Date());

				dossier = dossierDao.persist(dossier);
				// mapper.map(dossier, dossierTransfert); ceci ecrase la valeur
				// de inscription administrative
				dossierTransfert.setId(dossier.getId());
				dossierTransfert.setDossier(null);
				dossierTransfertDao.persist(dossierTransfert);
			} else {
				dossier = dossierDao.merge(dossier);
				dossierTransfert.setDossier(null);
				// fix object references an unsaved transient instance
				OuvertureOffreFormation o = dossierTransfert.getOuvertureOffreFormation();
				if (o != null) {
					if (o.getId() == 0)
						dossierTransfert.setOuvertureOffreFormation(null);
				}
				dossierTransfert = dossierTransfertDao.merge(dossierTransfert);
			}

			// TODO Important !!! enregistrer l'historique de la situation
			SituationEntiteOccurrence ss = new SituationEntiteOccurrence();
			ss.setSituationEntite(dossier.getSituationEntite());
			ss.setIdOccurrence(dossier.getId());
			ss.setDateSituation(new java.util.Date());
			situationEntiteOccurrenceDao.persist(ss);

			dossierTransfert.setDossier(dossier);
			mapper.map(dossierTransfert, dossierTransfertDto);

			// mapper.map(dossierTransfert.getDossierInscriptionAdministrative().getDossierEtudiant(),
			// dossierTransfertDto);
			// mapper.map(dossierTransfert.getDossierInscriptionAdministrative().getDossierEtudiant().getDossierBachelier(),
			// dossierTransfertDto);
			// mapper.map(dossierTransfert.getDossierInscriptionAdministrative().getDossierEtudiant().get,
			// dossierTransfertDto);
			log.info("insertOrUpdate success");
			return dossierTransfertDto;
		} catch (Exception e) {
			log.error("insertOrUpdate failed", e);
			throw e;

		}

		// DossierTransfert dossierTransfert = mapper.map(dossierTransfertDto,
		// DossierTransfert.class);
		//
		// if (dossierTransfert.getId() == 0)
		// dossierTransfertDao.persist(dossierTransfert);
		// else
		// dossierTransfert = dossierTransfertDao.merge(dossierTransfert);
		//
		// mapper.map(dossierTransfert, dossierTransfertDto);
		//
		// return dossierTransfertDto;
		// } catch (RuntimeException e) {
		// log.error("insertOrUpdate failed", e);
		// throw e;
		//
		// }
	}

	@Override
	@Transactional()
	public void remove(DossierTransfertDto dossierTransfertDto) {
		try {
			DossierTransfert dossierTransfert = mapper.map(dossierTransfertDto, DossierTransfert.class);

			dossierTransfertDao.remove(dossierTransfert);
		} catch (RuntimeException e) {
			log.error("remove failed", e);
			throw e;

		}
	}

	@Override
	@Transactional(readOnly = true)
	public DossierTransfertDto findById(int id) {
		try {
			DossierTransfert dossierTransfert = dossierTransfertDao.findById(id);
			DossierTransfertDto dossierTransfertDto = new DossierTransfertDto();
			if (dossierTransfert != null) {
				mapper.map(dossierTransfert, dossierTransfertDto);
				return dossierTransfertDto;
			}

		} catch (RuntimeException e) {
			log.error("findById failed", e);
			throw e;

		}
		return null;

	}

	@Override
	@Transactional(readOnly = true)
	public List<DossierTransfertDto> findAll() {
		try {
			List<DossierTransfert> dossierTransferts = dossierTransfertDao.findAll();

			List<DossierTransfertDto> dossierTransfertDtos = new ArrayList<DossierTransfertDto>();

			for (DossierTransfert dossierTransfert : dossierTransferts) {
				dossierTransfertDtos.add(mapper.map(dossierTransfert, DossierTransfertDto.class));
			}

			return dossierTransfertDtos;
		} catch (RuntimeException e) {
			log.error("findAll failed", e);
			throw e;

		}
	}

	/**
	 * [DossierTransfertServiceImpl.dossierTransfertDao] Getter
	 * 
	 * @author BELDI Jamel on : 10 juin 2014 16:34:42
	 * @return the dossierTransfertDao
	 */
	public DossierTransfertDao getDossierTransfertDao() {
		return dossierTransfertDao;
	}

	/**
	 * [DossierTransfertServiceImpl.dossierTransfertDao] Setter
	 * 
	 * @author BELDI Jamel on : 10 juin 2014 16:34:42
	 * @param dossierTransfertDao
	 *            the dossierTransfertDao to set
	 */
	public void setDossierTransfertDao(DossierTransfertDao dossierTransfertDao) {
		this.dossierTransfertDao = dossierTransfertDao;
	}

	/**
	 * [DossierTransfertServiceImpl.dossierDao] Getter
	 * 
	 * @author BELDI Jamel on : 10 juin 2014 16:34:42
	 * @return the dossierDao
	 */
	public DossierDao getDossierDao() {
		return dossierDao;
	}

	/**
	 * [DossierTransfertServiceImpl.dossierDao] Setter
	 * 
	 * @author BELDI Jamel on : 10 juin 2014 16:34:42
	 * @param dossierDao
	 *            the dossierDao to set
	 */
	public void setDossierDao(DossierDao dossierDao) {
		this.dossierDao = dossierDao;
	}

	public SituationDao getSituationDao() {
		return situationDao;
	}

	public void setSituationDao(SituationDao situationDao) {
		this.situationDao = situationDao;
	}

	/**
	 * [DossierTransfertServiceImpl.mapper] Getter
	 * 
	 * @author BELDI Jamel on : 10 juin 2014 16:34:42
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [DossierTransfertServiceImpl.mapper] Setter
	 * 
	 * @author BELDI Jamel on : 10 juin 2014 16:34:42
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * [DossierTransfertServiceImpl.situationEntiteDao] Getter
	 * 
	 * @author BELDI Jamel on : 10 juil. 2014 14:11:09
	 * @return the situationEntiteDao
	 */
	public SituationEntiteDao getSituationEntiteDao() {
		return situationEntiteDao;
	}

	/**
	 * [DossierTransfertServiceImpl.situationEntiteDao] Setter
	 * 
	 * @author BELDI Jamel on : 10 juil. 2014 14:11:09
	 * @param situationEntiteDao
	 *            the situationEntiteDao to set
	 */
	public void setSituationEntiteDao(SituationEntiteDao situationEntiteDao) {
		this.situationEntiteDao = situationEntiteDao;
	}

	@Override
	@Transactional(readOnly = true)
	public List<DossierTransfertDto> findAdvanced(DossierTransfertDto dossierTransfertSearchDto) {
		try {
			List<DossierTransfertDto> result = new ArrayList<DossierTransfertDto>();
			DossierTransfert searchBo = new DossierTransfert();
			mapper.map(dossierTransfertSearchDto, searchBo);
			List<DossierTransfert> resultListDao = dossierTransfertDao.findAdvanced(searchBo);
			if (resultListDao != null && !resultListDao.isEmpty()) {
				for (DossierTransfert dossierTransfert : resultListDao) {
					DossierTransfertDto dossierTransfertDto = new DossierTransfertDto();
					mapper.map(dossierTransfert, dossierTransfertDto);

					// DossierEtudiant dossierEtudiant =
					// dossierEtudiantDao.findById(dossierTransfertDto.getDossierEtudiantId());
					// mapper.map(dossierEtudiant, dossierTransfertDto);
					//
					// DossierBachelier dossierBachelier =
					// dossierBachelierDao.findById(dossierTransfertDto.getBacId());
					// mapper.map(dossierBachelier, dossierTransfertDto);

					result.add(dossierTransfertDto);
				}
				return result;
			}

		} catch (Exception e) {
			log.error("findAdvanced failed", e);
			throw e;
		}
		return new ArrayList<DossierTransfertDto>();
	}

	/**
	 * Recherche avanncee par situation
	 * 
	 * @author Mounir.MESSAOUDI on : 2 sept. 2014 10:01:50
	 * @param searchBo
	 * @param searchSitutationEntiteDtos
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public List<DossierTransfertDto> findAdvanced(DossierTransfertDto searchDto,
			List<SituationEntiteDto> searchSitutationEntiteDtos) {
		try {
			List<DossierTransfertDto> result = new ArrayList<DossierTransfertDto>();
			DossierTransfert searchBo = new DossierTransfert();
			mapper.map(searchDto, searchBo);

			List<SituationEntite> searchSituationEntiteBos = new ArrayList<SituationEntite>();
			if (!searchSitutationEntiteDtos.isEmpty()) {
				for (SituationEntiteDto situationEntiteDto : searchSitutationEntiteDtos) {
					SituationEntite situationEntite = new SituationEntite();
					mapper.map(situationEntiteDto, situationEntite);
					searchSituationEntiteBos.add(situationEntite);
				}
			}
			List<DossierTransfert> resultListDao = dossierTransfertDao.findAdvanced(searchBo, searchSituationEntiteBos);

			if (resultListDao != null && !resultListDao.isEmpty()) {
				for (DossierTransfert dossierTransfert : resultListDao) {
					DossierTransfertDto dossierTransfertDto = new DossierTransfertDto();
					mapper.map(dossierTransfert, dossierTransfertDto);
					result.add(dossierTransfertDto);
				}
				return result;
			}

		} catch (Exception e) {
			log.error("findAdvanced failed", e);
			throw e;
		}
		return new ArrayList<DossierTransfertDto>();
	}

	/**
	 * Recherche avanncee par situation
	 * 
	 * @author Mounir.MESSAOUDI on : 15 sept. 2014 12:42:12
	 * @param searchDto
	 * @param typesDossiersTransfert
	 * @param searchSitutationEntiteDtos
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public List<DossierTransfertDto> findAdvanced(DossierTransfertDto searchDto, List<String> typesDossiersTransfert,
			List<SituationEntiteDto> searchSitutationEntiteDtos) {
		try {
			List<DossierTransfertDto> result = new ArrayList<DossierTransfertDto>();
			DossierTransfert searchBo = new DossierTransfert();
			mapper.map(searchDto, searchBo);

			List<SituationEntite> searchSituationEntiteBos = new ArrayList<SituationEntite>();
			if (searchSitutationEntiteDtos != null && !searchSitutationEntiteDtos.isEmpty()) {
				for (SituationEntiteDto situationEntiteDto : searchSitutationEntiteDtos) {
					SituationEntite situationEntite = new SituationEntite();
					mapper.map(situationEntiteDto, situationEntite);
					searchSituationEntiteBos.add(situationEntite);
				}
			}
			List<DossierTransfert> resultListDao = dossierTransfertDao.findAdvanced(searchBo, typesDossiersTransfert,
					searchSituationEntiteBos);

			if (resultListDao != null && !resultListDao.isEmpty()) {
				for (DossierTransfert dossierTransfert : resultListDao) {
					DossierTransfertDto dossierTransfertDto = new DossierTransfertDto();
					mapper.map(dossierTransfert, dossierTransfertDto);
					result.add(dossierTransfertDto);
				}
				return result;
			}

		} catch (Exception e) {
			log.error("findAdvanced failed", e);
			throw e;
		}
		return new ArrayList<DossierTransfertDto>();
	}

	/**
	 * Recherche avancee nombre de resultat
	 * 
	 * @author Mounir.MESSAOUDI on : 10 sept. 2014 10:01:50
	 * @param searchBo
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public Long findCountDossiersTransfert(DossierTransfertDto searchDto) {
		try {
			DossierTransfert searchBo = new DossierTransfert();
			mapper.map(searchDto, searchBo);
			return dossierTransfertDao.findCountDossiersTransfert(searchBo);

		} catch (Exception e) {
			log.error("findAdvanced failed", e);
			throw e;
		}

	}

	public SituationEntiteOccurrenceDao getSituationEntiteOccurrencDao() {
		return situationEntiteOccurrenceDao;
	}

	public void setSituationEntiteOccurrencDao(SituationEntiteOccurrenceDao situationEntiteOccurrencDao) {
		this.situationEntiteOccurrenceDao = situationEntiteOccurrencDao;
	}

}
