package dz.gov.mesrs.sii.fve.business.service.impl.offreformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteDao;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteOccurrenceDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.OffreFormationContratDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.OffreFormationDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.OffreFormationDetailContentDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.OffreFormationI18nDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.OffreFormationPartenaireDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.OffreFormationStructureDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.ParcoursTypeDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.ParcoursTypeI18nDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntiteOccurrence;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormation;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationContrat;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationDetailContent;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationI18n;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationPartenaire;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationStructure;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.ParcoursType;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDetailContentDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationI18nDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationPartenaireDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationStructureDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Service("offreFormationService")
public class OffreFormationServiceImpl implements OffreFormationService {

	private static final Log log = LogFactory
			.getLog(OffreFormationServiceImpl.class);

	@Autowired
	@Qualifier("offreFormationDao")
	private OffreFormationDao offreFormationDao;

	@Autowired
	@Qualifier("parcoursTypeDao")
	private ParcoursTypeDao parcoursTypeDao;

	@Autowired
	@Qualifier("offreFormationI18nDao")
	private OffreFormationI18nDao offreFormationI18nDao;

	@Autowired
	@Qualifier("parcoursTypeI18nDao")
	private ParcoursTypeI18nDao parcoursTypeI18nDao;

	@Autowired
	@Qualifier("offreFormationDetailContentDao")
	private OffreFormationDetailContentDao offreFormationDetailContentDao;

	@Autowired
	@Qualifier("offreFormationContratDao")
	private OffreFormationContratDao offreFormationContratDao;

	@Autowired
	@Qualifier("offreFormationPartenaireDao")
	private OffreFormationPartenaireDao offreFormationPartenaireDao;

	@Autowired
	@Qualifier("situationEntiteDao")
	private SituationEntiteDao situationEntiteDao;

	@Autowired
	@Qualifier("situationEntiteOccurrenceDao")
	private SituationEntiteOccurrenceDao situationEntiteOccurrenceDao;

	@Autowired
	@Qualifier("offreFormationStructureDao")
	private OffreFormationStructureDao offreFormationStructureDao;

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	/**
	 * [OffreFormationServiceImpl.OffreFormationServiceImpl()] Constructor
	 * 
	 * @author Rafik LAIB on : 5 avr. 2014 21:58:29
	 */
	public OffreFormationServiceImpl() {

	}

	/**
	 * [OffreFormationServiceImpl.insertOrUpdate] Method overridden By : @author
	 * Rafik LAIB On : 5 avr. 2014 21:58:20
	 * 
	 * @param offreFormationDto
	 * @param details
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public OffreFormationDto insertOrUpdate(
			OffreFormationDto offreFormationDto,
			List<OffreFormationDetailContentDto> details) {

		OffreFormation offreFormation = mapper.map(offreFormationDto,
				OffreFormation.class);
		try {
			offreFormationDao.flushAndClear();
			if (offreFormation.getId() == 0) {

				// Une nouvelle Offre de formation a inserer
				// Verification si le codeNat existe deja
				String code = offreFormation.getCode();
				List<OffreFormation> result = offreFormationDao
						.findByCode(code);
				if (result.size() == 0) {

					// Le code n existe pas, alors l offre peut etre inseree
					// Insertiond de l'objet OffreFormation
					offreFormation.setDateCreation(new java.util.Date());
					offreFormationDao.persist(offreFormation);

					// Insertion du parcours type correspondant
					ParcoursType parcoursTypeToPersist = new ParcoursType();
					parcoursTypeToPersist.setCode(offreFormation.getCode());
					parcoursTypeToPersist.setOffreFormation(offreFormation);
					parcoursTypeDao.persist(parcoursTypeToPersist);

					// Insertion du detail (complement, evaluation et mobilite)
					if (details != null) {
						for (OffreFormationDetailContentDto offreFormationDetailContentDto : details) {
							OffreFormationDetailContent offreFormationDetailContent = mapper
									.map(offreFormationDetailContentDto,
											OffreFormationDetailContent.class);
							offreFormationDetailContent
									.setOffreFormation(offreFormation);
							offreFormationDetailContentDao
									.persist(offreFormationDetailContent);
						}
					}
				} else {
					// Le code existe deja, alors l offre ne peut pas etre
					// inseree
					offreFormation.setId(-1);
				}

			} else {

				// Merge OffreFormation
				offreFormation.setDateModification(new java.util.Date());
				offreFormationDao.merge(offreFormation);
				OffreFormation OffreFormationMerged = offreFormationDao
						.findById(offreFormation.getId());

				// Merge ParcoursType
				ParcoursType parcoursTypeToMerge = parcoursTypeDao.findByOfId(
						OffreFormationMerged.getId()).get(0);
				parcoursTypeToMerge.setOffreFormation(OffreFormationMerged);
				parcoursTypeDao.merge(parcoursTypeToMerge);

			}
		}

		catch (Exception e) {

			offreFormation.setId(0);
		}

		mapper.map(offreFormation, offreFormationDto);

		return offreFormationDto;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public OffreFormationStructureDto insertOrUpdate(
			OffreFormationStructureDto offreFormationStructureDto) {

		OffreFormationStructure offreFormationStructure = mapper.map(
				offreFormationStructureDto, OffreFormationStructure.class);

		if (offreFormationStructure.getId() == 0)
			offreFormationStructureDao.persist(offreFormationStructure);
		else
			offreFormationStructure = offreFormationStructureDao
					.merge(offreFormationStructure);

		mapper.map(offreFormationStructure, offreFormationStructureDto);
		return offreFormationStructureDto;
	}

	/**
	 * [OffreFormationServiceImpl.remove] Method overridden By : @author Rafik
	 * LAIB On : 5 avr. 2014 21:58:10
	 * 
	 * @param offreFormationDto
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(OffreFormationDto offreFormationDto) {

		OffreFormation offreFormation = mapper.map(offreFormationDto,
				OffreFormation.class);

		offreFormationDao.remove(offreFormation);
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void removeStructure(int idStructure) {

		offreFormationStructureDao.remove(idStructure);
	}

	/**
	 * [OffreFormationServiceImpl.findByQuery] Method overridden By : @author
	 * Rafik LAIB On : 5 avr. 2014 21:58:03
	 * 
	 * @param query
	 * @return
	 */
	@Override
	public List<OffreFormationDto> findGeneric(String text, Integer idEtab) {

		try {
			Map<String, String> attributes = new HashMap<String, String>();
			if (text != null && !text.trim().isEmpty()) {
				attributes.put("code", text);
				attributes.put("numeroArrete", text);
				attributes.put("libelleLongFr", text);
				attributes.put("libelleCourtFr", text);
				attributes.put("abrevFr", text);
				attributes.put("libelleLongAr", text);
				attributes.put("libelleCourtAr", text);
				attributes.put("abrevAr", text);
				attributes.put("description", text);
			}
			List<OffreFormationDto> offreFormationDtos = null;
			List<OffreFormation> offreFormations = offreFormationDao
					.findGeneric(attributes, idEtab);
			if (offreFormations != null && offreFormations.size() > 0) {
				offreFormationDtos = new ArrayList<OffreFormationDto>();
				for (OffreFormation offreFormation : offreFormations) {
					OffreFormationDto offreFormationDto = mapper.map(
							offreFormation, OffreFormationDto.class);
					offreFormationDtos.add(offreFormationDto);
				}
			}
			return offreFormationDtos;
		} catch (RuntimeException re) {
			log.error("findGeneric 'OffreFormationDto' failed", re);
			throw re;
		}
	}

	/**
	 * [OffreFormationServiceImpl.findAll] Method
	 * 
	 * @author rlaib on : 11 oct. 2014 16:39:34
	 * @return
	 */
	@Override
	public List<OffreFormationDto> findAll() {

		List<OffreFormation> offreFormations = offreFormationDao.findAll();
		if (offreFormations != null && offreFormations.size() > 0) {
			List<OffreFormationDto> offreFormationDtos = new ArrayList<OffreFormationDto>();
			for (OffreFormation offreFormation : offreFormations) {
				OffreFormationDto offreFormationDto = new OffreFormationDto();
				offreFormationDto = mapper.map(offreFormation,
						OffreFormationDto.class);
				offreFormationDtos.add(offreFormationDto);
			}
			return offreFormationDtos;
		}
		return null;
	}

	/**
	 * [OffreFormationServiceImpl.findAdvanced] Method
	 * 
	 * @author rlaib on : 11 oct. 2014 16:39:43
	 * @param searchDto
	 * @param text
	 * @param codeEtab
	 * @return
	 */
	@Override
	public List<OffreFormationDto> findAdvanced(OffreFormationDto searchDto,
			String text, Integer idEtab) {
		Map<String, String> attributes = new HashMap<String, String>();
		if (text != null && !text.trim().isEmpty()) {
			attributes.put("code", text);
			attributes.put("numeroArrete", text);
			attributes.put("libelleLongFr", text);
			attributes.put("libelleCourtFr", text);
			attributes.put("abrevFr", text);
			attributes.put("libelleLongAr", text);
			attributes.put("libelleCourtAr", text);
			attributes.put("abrevAr", text);
			attributes.put("description", text);
		}
		Map<String, Object> attributesDto = new HashMap<String, Object>();
		if (searchDto != null) {
			if (searchDto.getNumeroArrete() != null
					&& !searchDto.getNumeroArrete().trim().equals(""))
				attributesDto.put("numeroArrete", searchDto.getNumeroArrete());
			if (searchDto.getIdDomaine() != null)
				attributesDto.put("idDomaine", searchDto.getIdDomaine());
			if (searchDto.getIdFiliere() != null)
				attributesDto.put("idFiliere", searchDto.getIdFiliere());
			if (searchDto.getIdSpecialite() != null)
				attributesDto.put("idSpecialite", searchDto.getIdSpecialite());
		}
		List<OffreFormationDto> offreFormationDtos = null;
		List<OffreFormation> offreFormations = offreFormationDao.findAdvanced(
				attributes, attributesDto, idEtab);
		if (offreFormations != null && offreFormations.size() > 0) {
			offreFormationDtos = new ArrayList<OffreFormationDto>();
			for (OffreFormation offreFormation : offreFormations) {
				OffreFormationDto offreFormationDto = mapper.map(
						offreFormation, OffreFormationDto.class);
				offreFormationDtos.add(offreFormationDto);
			}
		}
		return offreFormationDtos;
	}

	/**
	 * [OffreFormationServiceImpl.findById] Method overridden By : @author Rafik
	 * LAIB On : 5 avr. 2014 21:57:49
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public OffreFormationDto findById(int id) {

		try {
		OffreFormation offreFormation = offreFormationDao.findById(id);
		OffreFormationDto offreFormationDto = null;
		if (offreFormation != null) {

			offreFormationDto = new OffreFormationDto();
			offreFormationDto = mapper.map(offreFormation,
					OffreFormationDto.class);
		}
		return offreFormationDto;
		}
		catch (RuntimeException re)  {
			throw re;
		}
	}

	/**
	 * [OffreFormationServiceImpl.findPartnersByOfId] Method
	 * 
	 * @author rlaib on : 4 mai 2014 13:10:14
	 * @param ofId
	 * @return
	 */
	public List<OffreFormationPartenaireDto> findPartnersByOfId(int ofId) {

		List<OffreFormationPartenaireDto> offreFormationPartenaireDtos = new ArrayList<OffreFormationPartenaireDto>();

		List<OffreFormationContrat> offreFormationContrats = offreFormationContratDao
				.findByOfId(ofId);
		for (OffreFormationContrat offreFormationContrat : offreFormationContrats) {

			List<OffreFormationPartenaire> offreFormationPartenaires = offreFormationPartenaireDao
					.findPartnersByContractId(offreFormationContrat.getId());
			for (OffreFormationPartenaire offreFormationPartenaire : offreFormationPartenaires) {

				offreFormationPartenaireDtos.add(mapper.map(
						offreFormationPartenaire,
						OffreFormationPartenaireDto.class));
			}
		}
		return offreFormationPartenaireDtos;

	}

	/**
	 * [OffreFormationServiceImpl.findOffreToHabilitation] Method
	 * 
	 * @author rlaib on : 4 mai 2014 13:10:24
	 * @param ofSearchDto
	 * @return
	 */
	@Override
	public List<OffreFormationDto> findOffreToHabilitation(
			OffreFormationDto ofSearchDto) {

		List<OffreFormation> offreFormations = offreFormationDao
				.findOffreToHabilitation(mapper.map(ofSearchDto,
						OffreFormation.class));
		List<OffreFormationDto> offreFormationDtos = new ArrayList<OffreFormationDto>();
		if (offreFormations != null && !offreFormations.isEmpty()) {
			for (OffreFormation offreFormation : offreFormations) {

				OffreFormationDto offreFormationDto = mapper.map(
						offreFormation, OffreFormationDto.class);
				offreFormationDtos.add(offreFormationDto);
			}
		}
		return offreFormationDtos;
	}

	/**
	 * [OffreFormationServiceImpl.validateSavingOf] Method
	 * 
	 * @author rlaib on : 4 mai 2014 13:10:32
	 * @param ofId
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public boolean validateSavingOf(int ofId) {

		try {

			OffreFormation offreFormation = offreFormationDao.findById(ofId);

			// Preparation de la situation de la nouvelle offre de formation
			SituationEntite situationEntite = situationEntiteDao
					.findByCodeSituationByCodeEntite(
							UtilConstants.ENTITE_OFFRE_FORMATION_CODE,
							UtilConstants.OFFRE_FORMATION_SITUTAION_ENREGISTREE_DEFINITIVEMENT_CODE);
			offreFormation.setSituationEntite(situationEntite);
			offreFormation.setEstCompletee(true);
			offreFormation.setDateModification(new java.util.Date());

			offreFormationDao.merge(offreFormation);

			// Enregistrement de l'historique des situations
			SituationEntiteOccurrence situationEntiteOccurrence = new SituationEntiteOccurrence();
			situationEntiteOccurrence.setSituationEntite(situationEntite);
			situationEntiteOccurrence.setIdOccurrence(offreFormation.getId());
			situationEntiteOccurrence.setDateSituation(new java.util.Date());
			situationEntiteOccurrenceDao.persist(situationEntiteOccurrence);

			return true;
		} catch (Exception e) {

			return false;
		}

	}

	@Override
	public int getLastOfIndex(Integer idEtab) {

		try {
			return offreFormationDao.getLastOfIndex(idEtab);
		} catch (RuntimeException re) {

			log.error("getLastOfIndex failed", re);
			throw re;
		}
	}

	@Override
	public List<OffreFormationDto> findByCodeSituation(String codeSituation) {

		List<OffreFormation> offreFormations = offreFormationDao
				.findByCodeSituation(codeSituation);
		List<OffreFormationDto> offreFormationDtos = null;
		if (offreFormations != null && offreFormations.size() > 0) {

			offreFormationDtos = new ArrayList<OffreFormationDto>();
			for (OffreFormation offreFormation : offreFormations) {

				OffreFormationDto offreFormationDto = mapper.map(
						offreFormation, OffreFormationDto.class);
				offreFormationDtos.add(offreFormationDto);
			}
		}
		return offreFormationDtos;
	}

	@Override
	public OffreFormationI18nDto findI18nByOfId(int ofId, String langue) {

		try {
			OffreFormationI18n offreFormationI18n = offreFormationDao
					.findI18nByOfId(ofId, langue);
			OffreFormationI18nDto offreFormationI18nDto = null;
			if (offreFormationI18n != null) {
				offreFormationI18nDto = new OffreFormationI18nDto();
				mapper.map(offreFormationI18n, offreFormationI18nDto);

			}
			return offreFormationI18nDto;
		} catch (RuntimeException re) {

			log.error("findI18nByOfId failed", re);
			throw re;
		}
	}

	/**
	 * [OffreFormationServiceImpl.findStructuresByOfId] Method
	 * 
	 * @author rlaib on : 8 oct. 2014 16:37:49
	 * @param ofId
	 * @return
	 */
	@Override
	public List<OffreFormationStructureDto> findStructuresByOfId(int ofId) {

		List<OffreFormationStructureDto> offreFormationStructureDtos = new ArrayList<OffreFormationStructureDto>();
		List<OffreFormationStructure> offreFormationStructures = offreFormationStructureDao
				.findByOfId(ofId);
		for (OffreFormationStructure offreFormationStructure : offreFormationStructures) {
			offreFormationStructureDtos.add(mapper.map(offreFormationStructure,
					OffreFormationStructureDto.class));
		}
		return offreFormationStructureDtos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService
	 * #findByIdEtablissement(int)
	 */
	@Override
	public List<OffreFormationDto> findByIdEtablissement(int etabId) {
		try {
			List<OffreFormation> offreFormations = offreFormationDao
					.findByIdEtablissement(etabId);
			List<OffreFormationDto> offreFormationDtos = null;
			if (offreFormations != null) {
				offreFormationDtos = new ArrayList<OffreFormationDto>();
				for (OffreFormation offreFormation : offreFormations) {
					offreFormationDtos.add(mapper.map(offreFormation,
							OffreFormationDto.class));
				}
			}
			return offreFormationDtos;
		} catch (RuntimeException re) {

			log.error("findByIdEtablissement failed", re);
			throw re;
		}
	}
}
