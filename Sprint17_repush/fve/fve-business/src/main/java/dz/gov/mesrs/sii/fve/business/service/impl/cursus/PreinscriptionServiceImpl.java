package dz.gov.mesrs.sii.fve.business.service.impl.cursus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteDao;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteOccurrenceDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DossierDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DossierEtudiantDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DossierInscriptionAdministrativeDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.PreinscriptionDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.TitreAccesDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.AnneeAcademiqueDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.OuvertureOffreFormationDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.NomenclatureDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefDocumentDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefDomaineLMDDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefFiliereLmdDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefSpecialiteLmdDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntiteOccurrence;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DecisionDemandePreinscription;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DemandePreinscription;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Dossier;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierEtudiant;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierInscriptionAdministrative;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.TitreAcces;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormation;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OuvertureOffreFormation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefCoordonnee;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefDocument;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DemandePreinscriptionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.SearchPreinscriptionDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.PreinscriptionService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;

@Service("preinscriptionService")
public class PreinscriptionServiceImpl implements PreinscriptionService {

    private final static Logger logger = LoggerFactory.getLogger(PreinscriptionServiceImpl.class.getName());

    @Autowired
    private PreinscriptionDao preinscriptionDao;
    @Autowired
    private OuvertureOffreFormationDao ouvertureOffreFormationDao;
    @Autowired
    private DozerBeanMapper mapper;
    @Autowired
    @Qualifier("refFiliereLmdDaoImpl")
    private RefFiliereLmdDao filiereLmdDao;
    @Autowired
    @Qualifier("refDomaineLMDDaoImpl")
    private RefDomaineLMDDao domaineLMDDao;
    @Autowired
    @Qualifier("refSpecialiteLmdDaoImpl")
    private RefSpecialiteLmdDao specialiteLmdDao;
    @Autowired
    private DossierEtudiantDao dossierEtudiantDao;
    @Autowired
    private SituationEntiteDao situationEntiteDao;
    @Autowired
    private DossierDao dossierDao;
    @Autowired
    private TitreAccesDao titreAccesDao;
    @Autowired
    private AnneeAcademiqueDao academiqueDao;
    @Autowired
    private DossierInscriptionAdministrativeDao dossierInscriptionAdministrativeDao;
    @Autowired
    private NomenclatureDao nomenclatureDao;
    @Autowired
    private SituationEntiteOccurrenceDao situationEntiteOccurrenceDao;
    @Autowired
    private RefDocumentDao documentDao;

    @Override
    @Transactional
    public DemandePreinscriptionDto save(DemandePreinscriptionDto dto) {
	logger.debug("Persisting {}", new Object[] { dto });

	DemandePreinscription demandePreinscription = mapper.map(dto, DemandePreinscription.class);
	defineSituation(demandePreinscription);
	if (StringUtils.isEmpty(demandePreinscription.getRefIndividu().getIdentifiant())) {
	    String identifiant = generateIdentifiant(demandePreinscription);
	    demandePreinscription.getRefIndividu().setIdentifiant(identifiant);
	}
	RefCoordonnee coordonnee = demandePreinscription.getRefIndividu().getRefCoordonnees().iterator().next();
	if (coordonnee.getId() <= 0) {
	    coordonnee.setPrincipal(true);
	    int typeCoordonnees = 1;
	    Nomenclature nomenclature = nomenclatureDao.findByNameAndValue(UtilConstant.NC_TYPE_ADRESSE_NAME,
		    UtilConstant.NC_TYPE_ADRESSE_PERSONELLE_VALUE).get(0);
	    if (nomenclature != null) {
		typeCoordonnees = nomenclature.getId();
	    }
	    coordonnee.setTypeCoordonnee(typeCoordonnees);
	    Nomenclature nomenclatureAdresse = nomenclatureDao.findByNameAndValue(UtilConstant.NC_TYPE_ADRESSE_NAME,
		    UtilConstant.NC_TYPE_ADRESSE_PERSONELLE_VALUE).get(0);
	    coordonnee.getRefAdresse().setNomenclatureByTypeAdresse(nomenclatureAdresse);

	    Nomenclature nomenclatureTypeEmail = nomenclatureDao.findByNameAndValue(UtilConstant.NC_TYPE_ADRESSE_NAME,
		    UtilConstant.NC_TYPE_ADRESSE_ELECTRONIQUE_PERSONELLE_VALUE).get(0);
	    coordonnee.getRefAdresseElectronique().setNomenclatureByTypeAdresseElectronique(nomenclatureTypeEmail);
	    Nomenclature nomenclatureNatureEmail = nomenclatureDao.findByNameAndValue(UtilConstant.NC_NAT_EMAIL_NAME,
		    UtilConstant.NC_NATURE_ADRESSE_ELECTRONIQUE_EMAIL_VALUE).get(0);
	    coordonnee.getRefAdresseElectronique().setNomenclatureByNatureAdresseElectronique(nomenclatureNatureEmail);
	    coordonnee.setRefIndividu(demandePreinscription.getRefIndividu());

	}
	DecisionDemandePreinscription decision = demandePreinscription.getDecision();
	if (decision != null) {
	    decision.setDemandePreinscription(demandePreinscription);
	}
	preinscriptionDao.saveDemandeur(demandePreinscription);
	preinscriptionDao.saveTitreAcces(demandePreinscription);
	demandePreinscription = preinscriptionDao.save(demandePreinscription);
	persistSituationOccurence(demandePreinscription);
	logger.debug("Persisted {}", new Object[] { demandePreinscription });
	return mapper.map(demandePreinscription, DemandePreinscriptionDto.class);
    }

    private void persistSituationOccurence(DemandePreinscription demandePreinscription) {
	SituationEntiteOccurrence entiteOccurrence = new SituationEntiteOccurrence();
	entiteOccurrence.setDateSituation(new Date());
	entiteOccurrence.setIdOccurrence(demandePreinscription.getId().intValue());
	entiteOccurrence.setSituationEntite(demandePreinscription.getSituationEntite());
	situationEntiteOccurrenceDao.persist(entiteOccurrence);

    }

    private void defineSituation(DemandePreinscription demandePreinscription) {
	SituationEntite situationEntite = null;
	if (demandePreinscription.getId() == 0L) {
	    situationEntite = situationEntiteDao.findByCodeSituationByCodeEntite(
		    UtilConstants.ENTITE_DEMANDE_PREINSCRIPTION, UtilConstants.SITUTAION_CREEE_CODE);
	} else if (demandePreinscription.getDecision() != null) {
	    DecisionDemandePreinscription decision = demandePreinscription.getDecision();
	    if (decision.getAcceptee()) {
		situationEntite = situationEntiteDao.findByCodeSituationByCodeEntite(
			UtilConstants.ENTITE_DEMANDE_PREINSCRIPTION, UtilConstants.SITUTAION_VALIDEE_CODE);
	    } else {
		situationEntite = situationEntiteDao.findByCodeSituationByCodeEntite(
			UtilConstants.ENTITE_DEMANDE_PREINSCRIPTION, UtilConstants.SITUTAION_REJETEE_CODE);
	    }
	} else {
	    situationEntite = situationEntiteDao.findByCodeSituationByCodeEntite(
		    UtilConstants.ENTITE_DEMANDE_PREINSCRIPTION, UtilConstants.SITUTAION_ENREGISTREE_CODE);
	}
	demandePreinscription.setSituationEntite(situationEntite);

    }

    private String generateIdentifiant(DemandePreinscription demandePreinscription) {
	String result = "";
	result += demandePreinscription.getRefIndividu().getNomenclatureByCivilite().getId();
	result += demandePreinscription.getRefIndividu().getNomenclatureByNationalite().getId();
	Random r = new Random();
	int random = r.nextInt(2000000000 - 1000000000) + 1000000000;
	result += String.valueOf(random);
	return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DemandePreinscriptionDto> search(SearchPreinscriptionDto searchDto) {
	if(StringUtils.isEmpty(searchDto.getNomDemandeur())){
	    searchDto.setNomDemandeur(null);
	}
	if(StringUtils.isEmpty(searchDto.getPrenomDemandeur())){
	    searchDto.setPrenomDemandeur(null);
	}
	DemandePreinscription demande = mapper.map(searchDto, DemandePreinscription.class);
	List<DemandePreinscription> entities = preinscriptionDao.findByExample(demande);
	List<DemandePreinscriptionDto> dtos = null;
	if (entities != null && entities.size() > 0) {
	    dtos = new ArrayList<>();
	    for (DemandePreinscription entity : entities) {
		DemandePreinscriptionDto dto = mapper.map(entity, DemandePreinscriptionDto.class);
		dtos.add(dto);
	    }
	}

	return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public DemandePreinscriptionDto find(Long demandeId) {
	DemandePreinscription demande = preinscriptionDao.finById(demandeId);
	DemandePreinscriptionDto dto = mapper.map(demande, DemandePreinscriptionDto.class);
	TitreAcces titreAcces = demande.getTitreAcces();
	if (titreAcces != null) {
	    Nomenclature nomenclature = null;

	    Integer langue1Id = Integer.valueOf(titreAcces.getRefCodeLangueEtrangere1());
	    nomenclature = nomenclatureDao.findById(langue1Id);
	    dto.getTitreAccesDto().setLangueEtrangere1LibelleLongFr(nomenclature.getLibelleLongFr());

	    Integer langue2Id = Integer.valueOf(titreAcces.getRefCodeLangueEtrangere2());
	    nomenclature = nomenclatureDao.findById(langue2Id);
	    dto.getTitreAccesDto().setLangueEtrangere2LibelleLongFr(nomenclature.getLibelleLongFr());

	    Integer codeTypeId = Integer.valueOf(titreAcces.getRefCodeTypeTitre());
	    nomenclature = nomenclatureDao.findById(codeTypeId);
	    dto.getTitreAccesDto().setTypeTitreLibelleLongFr(nomenclature.getLibelleLongFr());

	    Integer codeMentionId = Integer.valueOf(titreAcces.getRefCodeMention());
	    nomenclature = nomenclatureDao.findById(codeMentionId);
	    dto.getTitreAccesDto().setMentionLibelleLongFr(nomenclature.getLibelleLongFr());

	    String specialite = titreAcces.getRefCodeSpecialite();
	    if (!StringUtils.isEmpty(specialite)) {
		Integer specialiteId = Integer.valueOf(specialite);
		nomenclature = nomenclatureDao.findById(specialiteId);
		dto.getTitreAccesDto().setLibelleFr(nomenclature.getLibelleLongFr());
	    }
	}
	return dto;

    }

    @Override
    public void genereDossierEtudiant(DemandePreinscriptionDto dto) {
	DemandePreinscription demandePreinscription = preinscriptionDao.finById(dto.getId());

	Dossier dossier = new Dossier();
	dossier.setDateCreation(new Date());
	dossier.setTypeDossier(UtilConstants.TYPE_DOSSIER_ETUDIANT_CODE);
	SituationEntite situationEntite = situationEntiteDao.findByCodeSituationByCodeEntite(
		UtilConstants.ENTITE_DOSSIER_CODE, UtilConstants.SITUTAION_GENEREE_AUTO_CODE);
	dossier.setSituationEntite(situationEntite);

	dossier = dossierDao.persist(dossier);

	DossierEtudiant dossierEtudiant = new DossierEtudiant();
	dossierEtudiant.setDossier(dossier);
	dossierEtudiant.setId(dossier.getId());
	dossierEtudiant.setIndividu(demandePreinscription.getRefIndividu());
	dossierEtudiant.setNumeroMatricule(demandePreinscription.getRefIndividu().getIdentifiant());
	RefEtablissement refEtablissement = new RefEtablissement();
	refEtablissement.setId(demandePreinscription.getEtablissement().getId());
	dossierEtudiant.setRefEtablissement(refEtablissement);
	//dossierEtudiant.setRefIndividu(demandePreinscription.getRefIndividu().getIdentifiant());
	dossierEtudiant.setIndividu(demandePreinscription.getRefIndividu());

	dossierEtudiantDao.persist(dossierEtudiant);

	TitreAcces titreAcces = demandePreinscription.getTitreAcces();
	titreAcces.setDossierEtudiant(dossierEtudiant);
	titreAccesDao.merge(titreAcces);

	DossierInscriptionAdministrative dossierInscriptionAdministrative = new DossierInscriptionAdministrative();
	dossierInscriptionAdministrative.setAnneeAcademique(demandePreinscription.getAnneeAcademique());
	dossierInscriptionAdministrative.setDossierEtudiant(dossierEtudiant);
	dossierInscriptionAdministrative.setDossier(dossier);
	dossierInscriptionAdministrative.setId(dossier.getId());
	dossierInscriptionAdministrative.setNiveau(demandePreinscription.getNiveau());
	dossierInscriptionAdministrative.setRefCodeDomaine(demandePreinscription.getDomaineLMD().getIdentifiant());
	RefEtablissement _etablissement = new RefEtablissement();
	_etablissement.setId(demandePreinscription.getEtablissement().getId());
	dossierInscriptionAdministrative.setRefEtablissement(_etablissement);
	dossierInscriptionAdministrative.setRefCodeFiliere(demandePreinscription.getFiliereLmd().getIdentifiant());

	OuvertureOffreFormation ouvertureOffreFormation = new OuvertureOffreFormation();
	ouvertureOffreFormation.setOffreFormation(getOffreFormationFromEntity(demandePreinscription));
	ouvertureOffreFormation.setAnneeAcademique(demandePreinscription.getAnneeAcademique());
	ouvertureOffreFormation.setRefEtablissement(refEtablissement);
	ouvertureOffreFormation.setEstOuverte(true);
	List<OuvertureOffreFormation> ouvertureOffreFormations = ouvertureOffreFormationDao
		.findAdvanced(ouvertureOffreFormation);
	if (ouvertureOffreFormations == null || ouvertureOffreFormations.size() != 1) {
	    throw new IllegalStateException("Probleme lors de la recuperation de l'offre de formation");
	}
	ouvertureOffreFormation = ouvertureOffreFormations.get(0);
	dossierInscriptionAdministrative.setOuvertureOffreFormation(ouvertureOffreFormation);

	dossierInscriptionAdministrativeDao.persist(dossierInscriptionAdministrative);

	demandePreinscription.setGeneree(true);
	SituationEntite demandeSituationEntite = situationEntiteDao.findByCodeSituationByCodeEntite(
		UtilConstants.ENTITE_DEMANDE_PREINSCRIPTION, UtilConstants.SITUTAION_GENEREE_AUTO_CODE);
	demandePreinscription.setSituationEntite(demandeSituationEntite);
	preinscriptionDao.save(demandePreinscription);
	persistSituationOccurence(demandePreinscription);
	copierLesDocumentsAttaches(demandePreinscription,dossierInscriptionAdministrative);

    }

    private void copierLesDocumentsAttaches(DemandePreinscription demandePreinscription, DossierInscriptionAdministrative dossierInscriptionAdministrative) {
	List<RefDocument> documents = documentDao.findDocumentsOfEntity("DemandePreinscription", demandePreinscription.getId().toString());
	for(RefDocument document :documents){
	    RefDocument documentEtablissement = mapper.map(document, RefDocument.class);
	    documentEtablissement.setId(0);
	    documentEtablissement.setNomEntite("DossierInscriptionAdministrative");
	    documentEtablissement.setIdentifiantEntite(String.valueOf(dossierInscriptionAdministrative.getId()));
	    RefEtablissement etablissement = new RefEtablissement();
	    etablissement.setId(demandePreinscription.getEtablissement().getId());
	    documentEtablissement.setRefEtablissement(etablissement);
	    documentDao.persist(documentEtablissement);
	}
	
    }

    private OffreFormation getOffreFormationFromEntity(DemandePreinscription entity) {

	OffreFormation result = new OffreFormation();
	result.setCycle(entity.getCycle());
	result.setRefDomaineLMD(entity.getDomaineLMD());
	result.setRefFiliereLmd(entity.getFiliereLmd());
	result.setRefEtablissement(entity.getEtablissement());
	if (entity.getSpecialiteLmd() != null) {
	    result.setRefSpecialiteLmd(entity.getSpecialiteLmd());
	}

	return result;
    }

    @Override
    @Transactional
    public void delete(DemandePreinscriptionDto dto) {
	DemandePreinscription demande = preinscriptionDao.finById(dto.getId());
	preinscriptionDao.remove(demande);
	supprimerLesDocumentsAttaches(demande);
    }

    private void supprimerLesDocumentsAttaches(DemandePreinscription demande) {
	// TODO Auto-generated method stub
	
    }
    
 
}
