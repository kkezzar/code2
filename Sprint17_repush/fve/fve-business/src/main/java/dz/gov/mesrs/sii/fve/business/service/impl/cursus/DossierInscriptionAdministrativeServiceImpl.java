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
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DossierDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DossierInscriptionAdministrativeDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.OffreFormationDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.OffreFormationI18nDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.OuvertureOffreFormationDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefDomaineLMDDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefEtablissementDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefFiliereLmdDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefSpecialiteLmdDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Dossier;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierInscriptionAdministrative;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.GroupePedagogique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormation;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OuvertureOffreFormation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefDomaineLMD;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefFiliereLmd;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSpecialiteLmd;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.GroupePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierInscriptionAdministrativeService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;

@Service("dossierInscriptionAdministrativeService")
public class DossierInscriptionAdministrativeServiceImpl implements
		DossierInscriptionAdministrativeService {

	@Autowired
	@Qualifier("dossierInscriptionAdministrativeDao")
	private DossierInscriptionAdministrativeDao dossierInscriptionAdministrativeDao;

	@Autowired
	@Qualifier("dossierDao")
	private DossierDao dossierDao;

	@Autowired
	@Qualifier("offreFormationDao")
	private OffreFormationDao offreFormationDao;

	@Autowired
	@Qualifier("offreFormationI18nDao")
	private OffreFormationI18nDao offreFormationI18nDao;

	@Autowired
	@Qualifier("situationEntiteDao")
	private SituationEntiteDao situationEntiteDao;

	@Autowired
	@Qualifier("refDomaineLMDDaoImpl")
	private RefDomaineLMDDao refDomaineLMDDao;

	@Autowired
	@Qualifier("refFiliereLmdDaoImpl")
	private RefFiliereLmdDao refFiliereLmdDao;

	@Autowired
	@Qualifier("refSpecialiteLmdDaoImpl")
	private RefSpecialiteLmdDao refSpecialiteLmdDao;

	@Autowired
	@Qualifier("refEtablissementDaoImpl")
	private RefEtablissementDao refEtablissementDao;

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	@Autowired
	@Qualifier("ouvertureOffreFormationDao")
	private OuvertureOffreFormationDao ouvertureOffreFormationDao;

	private static final Log log = LogFactory
			.getLog(DossierInscriptionAdministrativeServiceImpl.class);

	public DossierInscriptionAdministrativeServiceImpl() {

	}

	@Override
	public DossierInscriptionAdministrativeDto insertOrUpdate(
			DossierInscriptionAdministrativeDto diaDto) {
		try {
			Dossier dossier = new Dossier();
			mapper.map(diaDto, dossier);
			DossierInscriptionAdministrative _dia = mapper.map(diaDto,
					DossierInscriptionAdministrative.class);

			// TODO solution provisoire pour fixer object references an unsaved
			// transient instance - save the transient instance before flushing
			// dozer cree des objets Nomencalture avec id = 0
			// il faut changer les ids en Long
			if (_dia.getTypeDemandeHebergement() != null
					&& _dia.getTypeDemandeHebergement().getId() == 0) {
				_dia.setTypeDemandeHebergement(null);
			}

			if (_dia.getTypeDemandeBourse() != null
					&& _dia.getTypeDemandeBourse().getId() == 0) {
				_dia.setTypeDemandeBourse(null);
			}

			if (_dia.getTypeHebergement() != null
					&& _dia.getTypeHebergement().getId() == 0) {
				_dia.setTypeHebergement(null);
			}

			if (_dia.getId() == 0) {

				SituationEntite _se = situationEntiteDao
						.findByCodeSituationByCodeEntite(
								UtilConstants.ENTITE_DOSSIER_CODE,
								UtilConstants.SITUTAION_CREEE_CODE);

				dossier.setDateCreation(new Date());
				dossier.setSituationEntite(_se);
				dossierDao.persist(dossier);

				_dia.setId(dossier.getId());
				_dia.setDossier(dossier);

				generateNewNumeroInscrition(_dia);

				dossierInscriptionAdministrativeDao.persist(_dia);

			} else {
				dossier = dossierDao.merge(dossier);
				_dia.setDateInscription(new Date());
				_dia.setDossier(null);
				_dia = dossierInscriptionAdministrativeDao.merge(_dia);
				_dia.setDossier(dossier);

			}

			mapper.map(_dia, diaDto);
			return diaDto;

		} catch (RuntimeException e) {
			throw e;
		}
	}

	private void generateNewNumeroInscrition(
			DossierInscriptionAdministrative dia) {

		if (dia != null
				&& dia.getDossierEtudiant() != null
				&& dia.getDossierEtudiant().getId() != 0
				&& dia.getDossierEtudiant().getNumeroMatricule() != null
				&& !dia.getDossierEtudiant().getNumeroMatricule().trim()
						.isEmpty()) {
			String numEtudiant = dia.getDossierEtudiant().getNumeroMatricule();

			List<DossierInscriptionAdministrative> _diaList = dossierInscriptionAdministrativeDao
					.findDossierInscriptionsBy(dia.getDossierEtudiant().getId());

			int nextIncrement = _diaList.size() + 1;
			String _numeroInscription = numEtudiant + nextIncrement;
			dia.setNumeroInscription(_numeroInscription);
		} // else
			// throw new
			// Exception("dossier étudiant manquant, ou ne possèdant pas matricule.");
	}

	@Override
	public void remove(
			DossierInscriptionAdministrativeDto dossierInscriptionAdminDto) {

		DossierInscriptionAdministrative _dia = mapper.map(
				dossierInscriptionAdminDto,
				DossierInscriptionAdministrative.class);

		dossierInscriptionAdministrativeDao.remove(_dia);
	}

	@Override
	public DossierInscriptionAdministrativeDto findById(int id) {

		DossierInscriptionAdministrativeDto _diaDto = null;

		DossierInscriptionAdministrative _dia = dossierInscriptionAdministrativeDao
				.findById(id);

		if (_dia != null) {
			_diaDto = mapper.map(_dia,
					DossierInscriptionAdministrativeDto.class);

			initDtoInfos(_diaDto, _dia, true);
		}

		return _diaDto;
	}

	@Override
	public List<DossierInscriptionAdministrativeDto> findAdvanced(
			DossierInscriptionAdministrativeDto dto, boolean loadOfInfo) {

		DossierInscriptionAdministrative _dia = mapper.map(dto,
				DossierInscriptionAdministrative.class);

		List<DossierInscriptionAdministrative> _diaList = dossierInscriptionAdministrativeDao
				.findAdvanced(_dia);

		return convertToDtoList(_diaList, loadOfInfo);
	}

	@Override
	public List<DossierInscriptionAdministrativeDto> findDossierInscriptionsBy(
			int dossierEtudiantId) {

		List<DossierInscriptionAdministrative> _diaList = dossierInscriptionAdministrativeDao
				.findDossierInscriptionsBy(dossierEtudiantId);

		return convertToDtoList(_diaList, true);
	}

	@Override
	public DossierInscriptionAdministrativeDto findPresentDossierInscriptionForEtudiant(
			int dossierEtudiantId) {

		DossierInscriptionAdministrative _dia = dossierInscriptionAdministrativeDao
				.findPresentDossierInscriptionForEtudiant(dossierEtudiantId);

		if (_dia != null) {
			DossierInscriptionAdministrativeDto _diaDto = mapper.map(_dia,
					DossierInscriptionAdministrativeDto.class);

			initDtoInfos(_diaDto, _dia, true);

			return _diaDto;

		}

		return null;
	}

	/**
	 * Rechercher le dossier d'inscription administrative de l'annee academique
	 * precedent
	 * 
	 * @author Mounir.MESSAOUDI on : 2 oct. 2014 08:44:28
	 * @param dossierEtudiantId
	 * @return
	 */
	public DossierInscriptionAdministrativeDto findPrecdentDossierInscriptionForEtudiant(
			int dossierEtudiantId) {
		DossierInscriptionAdministrative _dia = dossierInscriptionAdministrativeDao
				.findPrecdentDossierInscriptionForEtudiant(dossierEtudiantId);

		if (_dia != null) {
			DossierInscriptionAdministrativeDto _diaDto = mapper.map(_dia,
					DossierInscriptionAdministrativeDto.class);

			initDtoInfos(_diaDto, _dia, true);

			return _diaDto;

		}

		return null;
	}

	/**
	 * Converte la liste des bo en liste de dto.
	 * 
	 * @param _diaList
	 * @return
	 */
	private List<DossierInscriptionAdministrativeDto> convertToDtoList(
			List<DossierInscriptionAdministrative> _diaList, boolean loadOfInfo) {

		// Initialisation de la liste résultat à renvoyer.
		List<DossierInscriptionAdministrativeDto> _diaDtoList = new ArrayList<DossierInscriptionAdministrativeDto>();

		DossierInscriptionAdministrativeDto _diaDto = null;
		for (DossierInscriptionAdministrative _diaItem : _diaList) {

			// Créer un nouveau dto et le Mapper par l'objet bo encours
			_diaDto = mapper.map(_diaItem,
					DossierInscriptionAdministrativeDto.class);

			initDtoInfos(_diaDto, _diaItem, loadOfInfo);

			// Ajout de l'objet dto à la liste résultat.
			_diaDtoList.add(_diaDto);
		}

		return _diaDtoList;
	}

	/**
	 * intialise le libellé situation du dto
	 * 
	 * @param diaDto
	 */
	private void initDtoInfos(DossierInscriptionAdministrativeDto diaDto,
			DossierInscriptionAdministrative dia, boolean loadOfInfo) {

		if (loadOfInfo) {
			if (diaDto.getOuvertureOffreFormationId() != null) {
				OuvertureOffreFormation _oof = ouvertureOffreFormationDao
						.findById(diaDto.getOuvertureOffreFormationId());

				if (_oof != null && _oof.getOffreFormation() != null) {
					OffreFormation _of = offreFormationDao.findById(_oof
							.getOffreFormation().getId());

					// initialisation des propriétés issues de l'offre de
					// formation.
					if (_of != null) {
						diaDto.setOffreFormationId(_of.getId());
						diaDto.setResultRefCodeDomaine(_of.getRefDomaineLMD()
								.getIdentifiant());
						diaDto.setResultRefCodeFiliere(_of.getRefFiliereLmd()
								.getIdentifiant());
						diaDto.setResultRefCodeSpecialite(_of
								.getRefSpecialiteLmd().getIdentifiant());
//						diaDto.setOffreFormationLibelleFr(_of
//								.getLibelleLongFr());
//						diaDto.setOffreFormationLibelleAr(_of
//								.getLibelleLongAr());

					}
				}
			}
		}

		// recuperer les libelles du domaine / filiere / specialite
		String refCodeDomaine = diaDto.getRefCodeDomaine();
		String refCodeFiliere = diaDto.getRefCodeFiliere();
		String refCodeSpecialite = diaDto.getRefCodeSpecialite();

		if (refCodeDomaine != null) {
			RefDomaineLMD d = refDomaineLMDDao
					.findByIdentifiant(refCodeDomaine);
			if (d != null) {
				diaDto.setRefLibelleDomaine(d.getLlDomaine());
				diaDto.setRefLibelleDomaineArabe(d.getLlDomaineArabe());
			}
		}

		if (refCodeFiliere != null) {
			RefFiliereLmd f = refFiliereLmdDao
					.findByIdentifiant(refCodeFiliere);
			if (f != null) {
				diaDto.setRefLibelleFiliere(f.getLlFiliere());
				diaDto.setRefLibelleFiliereArabe(f.getLlFiliereArabe());
			}

		}

		if (refCodeSpecialite != null) {
			RefSpecialiteLmd s = refSpecialiteLmdDao
					.findByIdentifiant(refCodeSpecialite);
			if (s != null) {
				diaDto.setRefLibelleSpecialite(s.getLlSpecialite());
				diaDto.setRefLibelleSpecialiteArabe(s.getLlSpecialiteArabe());
			}

		}

	}

	@Override
	public List<Object[]> findFiliere(DossierInscriptionAdministrativeDto dto) {

		try {
			DossierInscriptionAdministrative _dia = mapper.map(dto,
					DossierInscriptionAdministrative.class);
			List<Object[]> _diaList = dossierInscriptionAdministrativeDao
					.findFiliere(_dia);

			return (_diaList);
		} catch (RuntimeException e) {
			log.error("findFiliereByCodeNiveau failed", e);
			throw e;

		}

	}

	@Override
	public List<Object[]> findDomaine(DossierInscriptionAdministrativeDto dto) {

		try {
			DossierInscriptionAdministrative _dia = mapper.map(dto,
					DossierInscriptionAdministrative.class);

			List<Object[]> _diaList = dossierInscriptionAdministrativeDao
					.findDomaine(_dia);

			return (_diaList);
		} catch (RuntimeException e) {
			log.error("findDomaineByCodeNiveau failed", e);
			throw e;

		}

	}

	@Override
	public List<DossierInscriptionAdministrativeDto> findByMatriculeBac(
			DossierInscriptionAdministrativeDto dto) {
		DossierInscriptionAdministrative _dia = mapper.map(dto,
				DossierInscriptionAdministrative.class);

		List<DossierInscriptionAdministrative> _diaList = dossierInscriptionAdministrativeDao
				.findByMatriculeBac(_dia);

		return convertToDtoList(_diaList, true);
	}

	@Override
	public List<DossierInscriptionAdministrativeDto> findByAnneeAcademiqueAndEtab(
			int annee, Integer idEtab) {
		List<DossierInscriptionAdministrative> _diaList = dossierInscriptionAdministrativeDao
				.findByAnneeAcademiqueAndEtab(annee, idEtab);

		return convertToDtoList(_diaList, true);
	}

	@Override
	public List<DossierInscriptionAdministrativeDto> findEtudiant(
			DossierInscriptionAdministrativeDto dto, Integer gpId,
			Integer sectionId) {
		try {
			List<DossierInscriptionAdministrativeDto> result = new ArrayList<DossierInscriptionAdministrativeDto>();
			DossierInscriptionAdministrative _dia = mapper.map(dto,
					DossierInscriptionAdministrative.class);

			List<DossierInscriptionAdministrative> _diaList = dossierInscriptionAdministrativeDao
					.findEtudiant(_dia, gpId, sectionId);
			for (DossierInscriptionAdministrative de : _diaList) {
				result.add(mapper.map(de,
						DossierInscriptionAdministrativeDto.class));
			}

			return result;

		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<DossierInscriptionAdministrativeDto> findAdvanced(
			DossierInscriptionAdministrativeDto dto, String codeDoamine,
			String codeFiliere) {
		try {
			DossierInscriptionAdministrative _dia = mapper.map(dto,
					DossierInscriptionAdministrative.class);
			List<DossierInscriptionAdministrativeDto> _diaDtoList = new ArrayList<DossierInscriptionAdministrativeDto>();

			DossierInscriptionAdministrativeDto _diaDto = null;

			List<DossierInscriptionAdministrative> _diaList = dossierInscriptionAdministrativeDao
					.findAdvanced(_dia, codeDoamine, codeFiliere);
			for (DossierInscriptionAdministrative _diaItem : _diaList) {

				// Créer un nouveau dto et le Mapper par l'objet bo encours
				_diaDto = mapper.map(_diaItem,
						DossierInscriptionAdministrativeDto.class);

				// Ajout de l'objet dto à la liste résultat.
				_diaDtoList.add(_diaDto);
			}

			return _diaDtoList;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<DossierInscriptionAdministrativeDto> _findEtudiant(
			DossierInscriptionAdministrativeDto dto,
			GroupePedagogiqueDto groupePedagogiqueDto) {
		try {
			DossierInscriptionAdministrative _dia = mapper.map(dto,
					DossierInscriptionAdministrative.class);
			GroupePedagogique _gp = mapper.map(groupePedagogiqueDto,
					GroupePedagogique.class);
			List<DossierInscriptionAdministrativeDto> _diaDtoList = new ArrayList<DossierInscriptionAdministrativeDto>();

			DossierInscriptionAdministrativeDto _diaDto = null;

			List<DossierInscriptionAdministrative> _diaList = dossierInscriptionAdministrativeDao
					._findEtudiant(_dia, _gp);
			for (DossierInscriptionAdministrative _diaItem : _diaList) {
				_diaDto = mapper.map(_diaItem,
						DossierInscriptionAdministrativeDto.class);

				_diaDtoList.add(_diaDto);
			}

			return _diaDtoList;
		} catch (RuntimeException e) {
			throw e;
		}

	}

	// @Override
	// public List<DossierInscriptionAdministrativeDto> findEtudiantOfSection(
	// DossierInscriptionAdministrativeDto dto, Integer sectionId) {
	// try {
	// List<DossierInscriptionAdministrativeDto> result = new
	// ArrayList<DossierInscriptionAdministrativeDto>();
	// DossierInscriptionAdministrative _dia = mapper.map(dto,
	// DossierInscriptionAdministrative.class);
	//
	// List<DossierInscriptionAdministrative> _diaList =
	// dossierInscriptionAdministrativeDao
	// .findEtudiantOfSection(_dia, sectionId);
	// for (DossierInscriptionAdministrative de : _diaList) {
	// result.add(mapper.map(de,
	// DossierInscriptionAdministrativeDto.class));
	// }
	//
	// return result;
	//
	// } catch (RuntimeException e) {
	// throw e;
	// }
	// }

	/**
	 * Rechercher tout les dossiers d'inscriptions administrative qui ont la
	 * situtation dans idsSituations et l'ouverture de l'offre de formation dans
	 * idsOuvertureOffreFormation
	 * 
	 * @author Mounir.MESSAOUDI on : 20 oct. 2014 10:27:08
	 * @param diaSearch
	 * @param searchOuverturesOffreFormationBos
	 * @param searchSitutationEntiteBos
	 * @return
	 */
	@Override
	public List<DossierInscriptionAdministrativeDto> findAdvancedByOuvertureOffres(
			DossierInscriptionAdministrativeDto diaSearch,
			List<OuvertureOffreFormationDto> searchOuverturesOffreFormationDtos,
			List<SituationEntiteDto> searchSitutationEntiteDtos) {

		DossierInscriptionAdministrative _dia = mapper.map(diaSearch,
				DossierInscriptionAdministrative.class);

		List<SituationEntite> searchSituationEntiteBos = new ArrayList<SituationEntite>();
		if (searchSitutationEntiteDtos != null
				&& !searchSitutationEntiteDtos.isEmpty()) {
			for (SituationEntiteDto situationEntiteDto : searchSitutationEntiteDtos) {
				SituationEntite situationEntite = new SituationEntite();
				mapper.map(situationEntiteDto, situationEntite);
				searchSituationEntiteBos.add(situationEntite);
			}
		}

		List<OuvertureOffreFormation> searchOuverturesOffreFormationBos = new ArrayList<OuvertureOffreFormation>();
		if (searchOuverturesOffreFormationDtos != null
				&& !searchOuverturesOffreFormationDtos.isEmpty()) {
			for (OuvertureOffreFormationDto oo : searchOuverturesOffreFormationDtos) {
				OuvertureOffreFormation o = new OuvertureOffreFormation();
				mapper.map(oo, o);
				searchOuverturesOffreFormationBos.add(o);
			}
		}

		List<DossierInscriptionAdministrative> _diaList = dossierInscriptionAdministrativeDao
				.findAdvancedByOuvertureOffres(_dia,
						searchOuverturesOffreFormationBos,
						searchSituationEntiteBos);

		return convertToDtoList(_diaList, true);
	}

	/**
	 * @author Mounir.MESSAOUDI on : 28 oct. 2014 11:33:29
	 * @param diaDto
	 * @return
	 */
	@Override
	@Transactional
	public Long countByDia(DossierInscriptionAdministrativeDto diaDto) {
		DossierInscriptionAdministrative diaBo = mapper.map(diaDto,
				DossierInscriptionAdministrative.class);
		return dossierInscriptionAdministrativeDao.countByDia(diaBo);
	}

	/**
	 * @author Mounir.MESSAOUDI on : 28 oct. 2014 11:33:29
	 * @param diaDto
	 * @return
	 */
	@Override
	@Transactional
	public Long countByDia(
			DossierInscriptionAdministrativeDto diaDto,
			List<OuvertureOffreFormationDto> searchOuverturesOffreFormationDtos,
			List<SituationEntiteDto> searchSitutationEntiteDtos) {
		DossierInscriptionAdministrative diaBo = mapper.map(diaDto,
				DossierInscriptionAdministrative.class);

		List<SituationEntite> searchSituationEntiteBos = new ArrayList<SituationEntite>();
		if (searchSitutationEntiteDtos != null
				&& !searchSitutationEntiteDtos.isEmpty()) {
			for (SituationEntiteDto situationEntiteDto : searchSitutationEntiteDtos) {
				SituationEntite situationEntite = new SituationEntite();
				mapper.map(situationEntiteDto, situationEntite);
				searchSituationEntiteBos.add(situationEntite);
			}
		}

		List<OuvertureOffreFormation> searchOuverturesOffreFormationBos = new ArrayList<OuvertureOffreFormation>();
		if (searchOuverturesOffreFormationDtos != null
				&& !searchOuverturesOffreFormationDtos.isEmpty()) {
			for (OuvertureOffreFormationDto oo : searchOuverturesOffreFormationDtos) {
				OuvertureOffreFormation o = new OuvertureOffreFormation();
				mapper.map(oo, o);
				searchOuverturesOffreFormationBos.add(o);
			}
		}

		return dossierInscriptionAdministrativeDao.countByDia(diaBo,
				searchOuverturesOffreFormationBos, searchSituationEntiteBos);
	}

	/**
	 * @author Mounir.MESSAOUDI on : 28 oct. 2014 11:34:47
	 * @param diaDto
	 * @param sortField
	 * @param pageSize
	 * @param first
	 * @param descending
	 * @return
	 */
	@Override
	@Transactional
	public List<DossierInscriptionAdministrativeDto> findByDia(
			DossierInscriptionAdministrativeDto diaDto, String sortField,
			Integer pageSize, Integer first, Boolean descending) {

		DossierInscriptionAdministrative diaBo = mapper.map(diaDto,
				DossierInscriptionAdministrative.class);

		List<DossierInscriptionAdministrative> _diaList = dossierInscriptionAdministrativeDao
				.findByDia(diaBo, sortField, pageSize, first, descending);

		return convertToDtoList(_diaList, true);
	}

	/**
	 * @author Mounir.MESSAOUDI on : 28 oct. 2014 11:34:47
	 * @param diaDto
	 * @param searchOuverturesOffreFormationDtos
	 * @param searchSitutationEntiteDtos
	 * @param sortField
	 * @param pageSize
	 * @param first
	 * @param descending
	 * @return
	 */
	@Override
	@Transactional
	public List<DossierInscriptionAdministrativeDto> findByDia(
			DossierInscriptionAdministrativeDto diaDto,
			List<OuvertureOffreFormationDto> searchOuverturesOffreFormationDtos,
			List<SituationEntiteDto> searchSitutationEntiteDtos,
			String sortField, Integer pageSize, Integer first,
			Boolean descending) {

		DossierInscriptionAdministrative diaBo = mapper.map(diaDto,
				DossierInscriptionAdministrative.class);

		List<SituationEntite> searchSituationEntiteBos = new ArrayList<SituationEntite>();
		if (searchSitutationEntiteDtos != null
				&& !searchSitutationEntiteDtos.isEmpty()) {
			for (SituationEntiteDto situationEntiteDto : searchSitutationEntiteDtos) {
				SituationEntite situationEntite = new SituationEntite();
				mapper.map(situationEntiteDto, situationEntite);
				searchSituationEntiteBos.add(situationEntite);
			}
		}

		List<OuvertureOffreFormation> searchOuverturesOffreFormationBos = new ArrayList<OuvertureOffreFormation>();
		if (searchOuverturesOffreFormationDtos != null
				&& !searchOuverturesOffreFormationDtos.isEmpty()) {
			for (OuvertureOffreFormationDto oo : searchOuverturesOffreFormationDtos) {
				OuvertureOffreFormation o = new OuvertureOffreFormation();
				mapper.map(oo, o);
				searchOuverturesOffreFormationBos.add(o);
			}
		}

		List<DossierInscriptionAdministrative> _diaList = dossierInscriptionAdministrativeDao
				.findByDia(diaBo, searchOuverturesOffreFormationBos,
						searchSituationEntiteBos, sortField, pageSize, first,
						descending);

		return convertToDtoList(_diaList, true);
	}

	/**
	 * echercher le derniere dossier d'inscription ajoutee d'un etudiant
	 * 
	 * @author Mounir.MESSAOUDI on : 6 nov. 2014 14:53:40
	 * @param dossierEtudiantId
	 * @return
	 */
	@Override
	@Transactional
	public DossierInscriptionAdministrativeDto findDernierDossierInscriptionForEtudiant(
			int dossierEtudiantId) {

		DossierInscriptionAdministrative _dia = dossierInscriptionAdministrativeDao
				.findDernierDossierInscriptionForEtudiant(dossierEtudiantId);

		if (_dia != null) {
			DossierInscriptionAdministrativeDto _diaDto = mapper.map(_dia,
					DossierInscriptionAdministrativeDto.class);

			initDtoInfos(_diaDto, _dia, true);

			return _diaDto;

		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.fve.business.service.cursus.
	 * DossierInscriptionAdministrativeService
	 * #findStudentAdvanced(dz.gov.mesrs.sii
	 * .fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto)
	 */
	@Override
	public List<DossierInscriptionAdministrativeDto> findStudentAdvanced(
			DossierInscriptionAdministrativeDto dto) {
		try {
			DossierInscriptionAdministrative _dia = mapper.map(dto,
					DossierInscriptionAdministrative.class);

			List<DossierInscriptionAdministrative> _diaList = dossierInscriptionAdministrativeDao
					.findStudentAdvanced(_dia);
			if (_diaList != null) {
				List<DossierInscriptionAdministrativeDto> result = new ArrayList<DossierInscriptionAdministrativeDto>();
				for (DossierInscriptionAdministrative dia : _diaList) {
					result.add(mapper.map(dia,
							DossierInscriptionAdministrativeDto.class));
				}
				return result;
			}

			return null;
		} catch (RuntimeException e) {
			throw e;
		}
	}
}
