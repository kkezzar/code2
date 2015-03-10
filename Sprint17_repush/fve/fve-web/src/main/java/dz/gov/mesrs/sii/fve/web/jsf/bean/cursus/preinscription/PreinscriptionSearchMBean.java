package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.preinscription;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DecisionDemandePreinscriptionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DemandePreinscriptionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.SearchPreinscriptionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.TitreAccesDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.NiveauDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.PreinscriptionService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.NiveauService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAdresseDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAdresseElectroniqueDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectDomLmdEtabDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineLMDDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFiliereLmdDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefSpecialiteLmdDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefAffectDomLmdEtabService;
import dz.gov.mesrs.sii.referentiel.business.service.RefDomaineLMDService;
import dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService;
import dz.gov.mesrs.sii.referentiel.business.service.RefFiliereLmdService;
import dz.gov.mesrs.sii.referentiel.business.service.RefSpecialiteLmdService;

@ManagedBean(name = "preinscriptionSearchMBean")
@ViewScoped
public class PreinscriptionSearchMBean implements Serializable {
    private static final long serialVersionUID = -9091290749564488072L;

    @ManagedProperty("#{preinscriptionService}")
    private PreinscriptionService preinscriptionService;

    private SearchPreinscriptionDto searchDto = new SearchPreinscriptionDto();

    private List<SelectItem> anneeAcademiques;
    private List<SelectItem> etablissements;
    private List<SelectItem> pays;
    private List<SelectItem> pays1;

    private List<SelectItem> civilites;
    private List<SelectItem> nationalites;

    private List<SelectItem> paysObtentions;
    private List<SelectItem> typeTitreAcces;
    private List<SelectItem> mentions;
    private List<SelectItem> langues1;
    private List<SelectItem> langues2;
    private List<SelectItem> titreAccesSpecialites;

    private List<SelectItem> anneeAcademiquesDemande;
    private List<SelectItem> etablissementsDemande;
    private List<SelectItem> filieres;
    private List<SelectItem> cycles;
    private List<SelectItem> niveaux;
    private List<SelectItem> domaines;
    private List<SelectItem> specialites;

    private boolean tabTitreAccesDisabled = true;
    private boolean tabDemandeDisabled = true;
    private boolean tabDecisionDisabled = true;
    private boolean tabHistoriqueDisabled = true;
    private boolean tabDocumentDisabled = true;

    private boolean generationDisabed = true;
    private boolean deleteDisabed = true;
    private boolean saveDisabed = false;
    private boolean editionMode = true;

    private Date currentDate;

    @ManagedProperty("#{anneeAcademiqueService}")
    private AnneeAcademiqueService anneeAcademiqueService;
    @ManagedProperty("#{refEtablissementServiceImpl}")
    private RefEtablissementService etablissementService;
    @ManagedProperty("#{nomenclatureServiceImpl}")
    private NomenclatureService nomenclatureService;
    @ManagedProperty("#{ouvertureOffreFormationService}")
    private OuvertureOffreFormationService formationService;
    @ManagedProperty("#{refDomaineLMDServiceImpl}")
    private RefDomaineLMDService domaineService;
    @ManagedProperty("#{niveauService}")
    private NiveauService niveauService;
    @ManagedProperty("#{refSpecialiteLmdServiceImpl}")
    private RefSpecialiteLmdService specialiteLmdService;
    @ManagedProperty("#{refAffectDomLmdEtabServiceImpl}")
    private RefAffectDomLmdEtabService affectDomLmdEtabService;
    @ManagedProperty("#{refFiliereLmdServiceImpl}")
    private RefFiliereLmdService refFiliereLmdService;
    @ManagedProperty("#{situationService}")
    private SituationService situationService;

    private List<DemandePreinscriptionDto> results;
    private DemandePreinscriptionDto dto;
    private List<SituationEntiteOccurrenceDto> demandeHistory;
    private boolean renderDemandePanel;

    @PostConstruct
    public void init() {
	currentDate = new Date();
	initAnneesAcademiqueList();
	initEtablissementList();
	initPaysList();
	initCiviliteList();
	initSpecialiteList();
	initMentionList();
	initLangueList();
	initTypeTitreList();
	initNationaliteList();

    }

    private void initTypeTitreList() {
	typeTitreAcces = new ArrayList<>();
	List<NomenclatureDto> dtos = nomenclatureService.findByName(CursusConstants.NC_TYPE_TITRE_ACCES);
	for (NomenclatureDto dto : dtos) {
	    typeTitreAcces.add(new SelectItem(dto.getId(), dto.getLibelleLongFr()));
	}
    }

    public void nouvellePreinscription() {
	dto = new DemandePreinscriptionDto();
	dto.setIndividuDto(new RefIndividuDto());
	dto.setAdresseDto(new RefAdresseDto());
	dto.setAdresseElectroniqueDto(new RefAdresseElectroniqueDto());
	domaines = null;
	etablissementsDemande = null;
	filieres = null;
	cycles = null;
	niveaux = null;
	specialites = null;
	renderDemandePanel = true;
	tabDecisionDisabled = true;
	tabTitreAccesDisabled = true;
	tabHistoriqueDisabled = true;
	tabDemandeDisabled = true;
	tabDocumentDisabled = true;
	generationDisabed = true;
	deleteDisabed = true;
	saveDisabed = false;
	editionMode = true;
    }

    public void save() {
	if (!validate()) {
	    return;
	}
	this.dto = preinscriptionService.save(dto);
	if (dto.getTitreAccesDto() == null) {
	    dto.setTitreAccesDto(new TitreAccesDto());
	    tabHistoriqueDisabled = false;
	    tabDocumentDisabled = false;
	} else if (dto.getTitreAccesDto().getId() > 0) {
	    tabDemandeDisabled = false;
	}
	// on identfie que la demande a ete faite...
	if (dto.getDomaineId() != null) {
	    tabDecisionDisabled = false;
	    if (dto.getDecisionDto() == null) {
		dto.setDecisionDto(new DecisionDemandePreinscriptionDto());
	    }
	}
	tabTitreAccesDisabled = false;
	retrieveHistory();
	defineActionsRights();
	Utility.showSuccessSaveMessage();

    }

    private boolean validate() {
	boolean valid = true;
	TitreAccesDto titreAccesDto = dto.getTitreAccesDto();
	if (titreAccesDto != null) {
	    String codeLangue1 = titreAccesDto.getRefCodeLangueEtrangere1();
	    String codeLangue2 = titreAccesDto.getRefCodeLangueEtrangere2();
	    if (codeLangue1.equals(codeLangue2)) {
		FacesMessage msg = new FacesMessage();
		msg.setSeverity(FacesMessage.SEVERITY_WARN);
		msg.setSummary("Les langues etrangere sont identiques");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return false;
	    }

	}
	if(dto.getAdresseDto()!=null){
	    String codePostal = dto.getAdresseDto().getCodePostal(); 
	    if(!StringUtils.isEmpty(codePostal)&&!StringUtils.isNumeric(codePostal)){
		FacesMessage msg = new FacesMessage();
		msg.setSeverity(FacesMessage.SEVERITY_WARN);
		msg.setSummary("Le code postal doit etre un nombre");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return false;
	    }
	}
	return valid;

    }

    private void defineActionsRights() {
	String situtationCode = dto.getSituationCode();
	if (UtilConstants.SITUTAION_CREEE_CODE.equals(situtationCode)) {
	    deleteDisabed = false;
	    generationDisabed = true;
	    saveDisabed = false;
	    editionMode = true;
	} else if (UtilConstants.SITUTAION_VALIDEE_CODE.equals(situtationCode)) {
	    deleteDisabed = false;
	    generationDisabed = false;
	    saveDisabed = false;
	    editionMode = true;
	} else if (UtilConstants.SITUTAION_GENEREE_AUTO_CODE.equals(situtationCode)) {
	    deleteDisabed = true;
	    generationDisabed = true;
	    saveDisabed = true;
	    editionMode = false;
	    // TODO ne plus mettre de document
	} else {
	    deleteDisabed = false;
	    generationDisabed = true;
	    saveDisabed = false;
	}

    }

    private void retrieveHistory() {
	demandeHistory = situationService.getEntityOccurrenceHistory(UtilConstants.ENTITE_DEMANDE_PREINSCRIPTION,
		((Long) dto.getId()).intValue());

    }

    public void genererDossierInscription() {
	preinscriptionService.genereDossierEtudiant(dto);
	deleteDisabed = true;
	generationDisabed = true;
	saveDisabed = true;
	editionMode = false;
	// TODO ne plus mettre de document
	retrieveHistory();
	Utility.showSuccessMessage("Le dossier d'inscription a ete genere");

    }

    public void delete() {
	preinscriptionService.delete(dto);
	nouvellePreinscription();
	Utility.showSuccessDeleteMessage();
	renderDemandePanel = false;
	dto = new DemandePreinscriptionDto();
	if (results != null && !results.isEmpty()) {
	    search();
	}

    }

    // FIXME 1 Attention l'id genere n'est pas unique
    // TODO suppprimer reinitialiser le form
    public void onDemandePreinscriptionRowSelect(SelectEvent event) {
	// TODO remove

	dto = preinscriptionService.find(dto.getId());

	if (UtilConstants.SITUTAION_GENEREE_AUTO_CODE.equals(dto.getSituationCode())) {
	    editionMode = false;
	    setupForConsultation();
	} else {
	    editionMode = true;
	    setupForEdition();
	}

	renderDemandePanel = true;
	retrieveHistory();
    }

    private void setupForConsultation() {
	tabDemandeDisabled = false;
	tabTitreAccesDisabled = false;
	tabDecisionDisabled = false;
	tabHistoriqueDisabled = false;
	tabDocumentDisabled = false;
	generationDisabed = true;
	// deleteDisabed = true;
	// saveDisabed = true;
	// editionMode = false;
	defineActionsRights();
	if (dto.getTitreAccesDto() == null) {
	    dto.setTitreAccesDto(new TitreAccesDto());
	}
	if (dto.getDecisionDto() == null)
	    dto.setDecisionDto(new DecisionDemandePreinscriptionDto());
    }

    private void setupForEdition() {
	tabHistoriqueDisabled = false;
	tabDocumentDisabled = false;
	if (dto.getIndividuDto() != null) {
	    tabTitreAccesDisabled = false;
	    if (dto.getTitreAccesDto() != null) {
		tabDemandeDisabled = false;
		if (dto.getDomaineId() != null) {
		    setDomaineList();
		    setEtablissementListForDomain();
		    setFiliereListForDomainAndEtablissemnt();
		    setCycleListForFiliere();
		    setNiveauListForCyle();
		    setSpecialiteListForFiliereAndCycle();
		    tabDecisionDisabled = false;
		    if (dto.getDecisionDto() == null) {
			dto.setDecisionDto(new DecisionDemandePreinscriptionDto());
		    }
		}
	    } else {
		dto.setTitreAccesDto(new TitreAccesDto());
	    }
	}
	defineActionsRights();
    }

    public void search() {
	results = preinscriptionService.search(searchDto);
	if (results == null || results.isEmpty()) {
	    Utility.showWarningMessage("Auncun enregistrement trouve");
	}
	dto = new DemandePreinscriptionDto();
	renderDemandePanel = false;

    }

    public void onCycleSelected() {
	if (dto.getCycleId() == null) {
	    clearNiveauList();
	    clearSpecialiteList();
	} else {
	    dto.setNiveauId(null);
	    dto.setSpecialiteId(null);
	    setSpecialiteListForFiliereAndCycle();
	    setNiveauListForCyle();
	}
    }

    public void onFiliereSelected() {

	if (dto.getFiliereId() == null) {
	    clearCycleList();
	    clearNiveauList();
	    clearSpecialiteList();
	}
	setCycleListForFiliere();
    }

    public void onEtablissementSelected() {
	clearNiveauList();
	clearSpecialiteList();
	if (dto.getEtablissementId() != null && dto.getDomaineId() == null) {
	    setDomainListForEtablissement();
	    clearFiliereList();
	    clearSpecialiteList();
	    clearCycleList();
	} else if (dto.getEtablissementId() == null) {
	    initDomaineList();
	    initEtablissementDemandeList();
	    clearFiliereList();
	    clearCycleList();
	} else if (dto.getEtablissementId() != null) {
	    dto.setFiliereId(null);
	    setFiliereListForDomainAndEtablissemnt();
	}
    }

    public void onDomaineSelected() {
	if (dto.getDomaineId() != null) {
	    dto.setEtablissementId(null);
	    setEtablissementListForDomain();
	    clearFiliereList();
	    clearCycleList();
	    clearNiveauList();
	    clearSpecialiteList();
	} else {
	    initDomaineList();
	    initEtablissementDemandeList();
	    clearFiliereList();
	    clearCycleList();
	    clearNiveauList();
	    clearSpecialiteList();
	}
    }

    public void onAnneeAcademiqueDemandeSelected() {
	clearCycleList();
	clearFiliereList();
	clearNiveauList();
	clearSpecialiteList();
	if (dto.getAnneeAcademiqueId() == null) {
	    clearDomaineList();
	    clearEtablissmentDemandeList();
	} else {
	    initDomaineList();
	    initEtablissementDemandeList();
	}

    }

    private void clearEtablissmentDemandeList() {
	dto.setEtablissementId(null);
	etablissementsDemande = null;
    }

    private void clearDomaineList() {
	dto.setDomaineId(null);
	domaines = null;

    }

    private void setEtablissementListForDomain() {
	etablissementsDemande = new ArrayList<>();
	List<RefAffectDomLmdEtabDto> dtos = affectDomLmdEtabService.findByIdDomainelmd(dto.getDomaineId());
	for (RefAffectDomLmdEtabDto dto : dtos) {
	    etablissementsDemande.add(new SelectItem(dto.getIdEtablissement(), dto.getLlEtablissementLatin()));
	}
    }

    private void setDomainListForEtablissement() {
	dto.setDomaineId(null);
	domaines = new ArrayList<>();
	List<RefAffectDomLmdEtabDto> dtos = affectDomLmdEtabService.findByIdEtablissement(dto.getEtablissementId());
	for (RefAffectDomLmdEtabDto dto : dtos) {
	    domaines.add(new SelectItem(dto.getIdDomaineLMD(), dto.getLlDomaine()));
	}

    }

    public void setFiliereListForDomainAndEtablissemnt() {
	if (dto.getDomaineId() == null) {
	    return;
	}
	OuvertureOffreFormationDto searchDto = new OuvertureOffreFormationDto();
	searchDto.setAnneeAcademiqueId(dto.getAnneeAcademiqueId());
	searchDto.setEstOuverte(true);
	searchDto.setOfRefCodeDomaine(domaineService.findById(dto.getDomaineId()).getIdentifiant());
	searchDto.setIdEtablissement(etablissementService.findById(dto.getEtablissementId()).getId());
	List<OuvertureOffreFormationDto> dtos = formationService.findAdvanced(searchDto);
	if (dtos != null) {
	    filieres = new ArrayList<>();
	    List<String> filiereCodes = new ArrayList<>();
	    for (OuvertureOffreFormationDto dto : dtos) {
		String codeFiliere = dto.getOfRefCodeFiliere();
		if (!filiereCodes.contains(codeFiliere)) {
		    RefFiliereLmdDto filiereLmdDto = refFiliereLmdService.findByIdentifiant(codeFiliere);
		    filieres.add(new SelectItem(filiereLmdDto.getId(), filiereLmdDto.getLlFiliere()));
		    filiereCodes.add(codeFiliere);
		}

	    }
	}

    }

    private void initDomaineList() {
	dto.setDomaineId(null);
	setDomaineList();
    }

    private void setDomaineList() {
	domaines = new ArrayList<>();
	List<RefDomaineLMDDto> dtos = domaineService.findAll();
	for (RefDomaineLMDDto dto : dtos) {
	    domaines.add(new SelectItem(dto.getId(), dto.getLlDomaine()));
	}
    }

    private void initMentionList() {
	mentions = new ArrayList<>();
	List<NomenclatureDto> dtos = nomenclatureService.findByName(CursusConstants.NC_MENTION);
	for (NomenclatureDto dto : dtos) {
	    mentions.add(new SelectItem(dto.getId(), dto.getLibelleLongFr()));
	}

    }

    private void initLangueList() {
	langues1 = new ArrayList<>();
	langues2 = new ArrayList<>();
	List<NomenclatureDto> dtos = nomenclatureService.findByName(CursusConstants.NC_LANGUE_NAME);
	for (NomenclatureDto dto : dtos) {
	    langues1.add(new SelectItem(dto.getId(), dto.getLibelleLongFr()));
	    langues2.add(new SelectItem(dto.getId(), dto.getLibelleLongFr()));
	}

    }

    public void setNiveauListForCyle() {
	if (dto.getCycleId() == null) {
	    return;
	}
	List<NiveauDto> dtos = niveauService.findByCycleId(dto.getCycleId());
	if (dtos != null) {
	    niveaux = new ArrayList<>();
	    for (NiveauDto dto : dtos) {
		niveaux.add(new SelectItem(dto.getId(), dto.getLibelleLongLt()));
	    }
	}
    }

    private void initCiviliteList() {
	civilites = new ArrayList<>();
	List<NomenclatureDto> dtos = nomenclatureService.findByName(CursusConstants.NC_CIVILITE_NAME);
	for (NomenclatureDto dto : dtos) {
	    civilites.add(new SelectItem(dto.getId(), dto.getLibelleLongFr()));
	}

    }

    private void initSpecialiteList() {
	titreAccesSpecialites = new ArrayList<>();
	List<NomenclatureDto> dtos = nomenclatureService.findByName(OfConstants.NC_NAME_SPECIALITES);
	for (NomenclatureDto dto : dtos) {
	    titreAccesSpecialites.add(new SelectItem(dto.getId(), dto.getLibelleLongFr()));
	}

    }

    private void initPaysList() {
	List<NomenclatureDto> dtos = null;
	if (pays == null || pays1 == null || paysObtentions == null) {
	    dtos = nomenclatureService.findByName(CursusConstants.NC_PAYS_NAME);
	}
	if (pays == null) {
	    pays = new ArrayList<>();
	    for (NomenclatureDto dto : dtos) {
		pays.add(new SelectItem(dto.getId(), dto.getLibelleLongFr()));
	    }
	}

	if (pays1 == null) {
	    pays1 = new ArrayList<>();
	    for (NomenclatureDto dto : dtos) {
		pays1.add(new SelectItem(dto.getId(), dto.getLibelleLongFr()));
	    }
	}

	if (paysObtentions == null) {
	    paysObtentions = new ArrayList<>();
	    for (NomenclatureDto dto : dtos) {
		paysObtentions.add(new SelectItem(dto.getId(), dto.getLibelleLongFr()));
	    }
	}

    }

    private void initNationaliteList() {
	nationalites = new ArrayList<>();
	List<NomenclatureDto> dtos = nomenclatureService.findByName(CursusConstants.NC_NATIONALITE_NAME);
	for (NomenclatureDto dto : dtos) {
	    nationalites.add(new SelectItem(dto.getId(), dto.getLibelleLongFr()));
	}
    }

    private void initEtablissementList() {
	List<RefEtablissementDto> dtos = etablissementService.findAll();
	etablissements = new ArrayList<>();
	for (RefEtablissementDto dto : dtos) {
	    etablissements.add(new SelectItem(dto.getId(), dto.getLlEtablissementLatin()));
	}

    }

    private void initEtablissementDemandeList() {
	dto.setEtablissementId(null);
	List<RefEtablissementDto> dtos = etablissementService.findAll();
	etablissementsDemande = new ArrayList<>();
	for (RefEtablissementDto dto : dtos) {
	    etablissementsDemande.add(new SelectItem(dto.getId(), dto.getLlEtablissementLatin()));
	}

    }

    private void initAnneesAcademiqueList() {
	anneeAcademiques = new ArrayList<>();
	anneeAcademiquesDemande = new ArrayList<>();
	List<AnneeAcademiqueDto> dtos = anneeAcademiqueService.findAll();
	for (AnneeAcademiqueDto dto : dtos) {
	    anneeAcademiques.add(new SelectItem(dto.getId(), dto.getCode()));
	    anneeAcademiquesDemande.add(new SelectItem(dto.getId(), dto.getCode()));
	}
    }

    public void setSpecialiteListForFiliereAndCycle() {
	if (dto.getFiliereId() == null) {
	    return;
	}
	specialites = new ArrayList<>();
	OuvertureOffreFormationDto searchDto = new OuvertureOffreFormationDto();
	searchDto.setAnneeAcademiqueId(dto.getAnneeAcademiqueId());
	searchDto.setEstOuverte(true);
	searchDto.setOfRefCodeDomaine(domaineService.findById(dto.getDomaineId()).getIdentifiant());
	searchDto.setIdEtablissement(etablissementService.findById(dto.getEtablissementId()).getId());
	searchDto.setIdFiliere(dto.getFiliereId());
	List<OuvertureOffreFormationDto> dtos = formationService.findAdvanced(searchDto);
	if (dtos != null) {

	    List<String> specialiteCodes = new ArrayList<>();
	    for (OuvertureOffreFormationDto dto : dtos) {

		if (!specialiteCodes.contains(dto.getOfRefCodeSpecialite())
			&& dto.getCycleId().equals(dto.getCycleId())) {
		    specialiteCodes.add(dto.getOfRefCodeSpecialite());
		    RefSpecialiteLmdDto specialiteDto = specialiteLmdService.findByIdentifiant(dto
			    .getOfRefCodeSpecialite());
		    if (specialiteDto.getIdFiliere().equals(this.dto.getFiliereId())) {
			specialites.add(new SelectItem(specialiteDto.getId(), specialiteDto.getLlSpecialite()));
		    }

		}
	    }
	}
	if (specialites.isEmpty()) {
	    specialites = null;
	}

    }

    private void clearFiliereList() {
	dto.setFiliereId(null);
	filieres = null;
    }

    private void clearNiveauList() {
	dto.setNiveauId(null);
	niveaux = null;
    }

    private void clearCycleList() {
	dto.setCycleId(null);
	cycles = null;
    }

    private void clearSpecialiteList() {
	dto.setSpecialiteId(null);
	specialites = null;
    }

    public void setCycleListForFiliere() {
	if (dto.getFiliereId() == null) {
	    return;
	}
	OuvertureOffreFormationDto searchDto = new OuvertureOffreFormationDto();
	searchDto.setAnneeAcademiqueId(dto.getAnneeAcademiqueId());
	searchDto.setEstOuverte(true);
	searchDto.setOfRefCodeDomaine(domaineService.findById(dto.getDomaineId()).getIdentifiant());
	searchDto.setIdEtablissement(etablissementService.findById(dto.getEtablissementId()).getId());
	searchDto.setIdFiliere(dto.getFiliereId());
	List<OuvertureOffreFormationDto> dtos = formationService.findAdvanced(searchDto);

	if (dtos != null) {
	    List<Integer> cycleIds = new ArrayList<Integer>();
	    cycles = new ArrayList<>();
	    for (OuvertureOffreFormationDto dto : dtos) {
		if (!cycleIds.contains(dto.getCycleId())) {
		    cycleIds.add(dto.getCycleId());
		    cycles.add(new SelectItem(dto.getCycleId(), dto.getCycleLibelleLongLt()));
		}
	    }
	} else {
	    dto.setCycleId(null);
	    cycles = null;
	}

    }

    public PreinscriptionService getPreinscriptionService() {
	return preinscriptionService;
    }

    public void setPreinscriptionService(PreinscriptionService preinscriptionService) {
	this.preinscriptionService = preinscriptionService;
    }

    public SearchPreinscriptionDto getSearchDto() {
	return searchDto;
    }

    public void setSearchDto(SearchPreinscriptionDto searchDto) {
	this.searchDto = searchDto;
    }

    public AnneeAcademiqueService getAnneeAcademiqueService() {
	return anneeAcademiqueService;
    }

    public void setAnneeAcademiqueService(AnneeAcademiqueService anneeAcademiqueService) {
	this.anneeAcademiqueService = anneeAcademiqueService;
    }

    public void setEtablissementService(RefEtablissementService etablissementService) {
	this.etablissementService = etablissementService;
    }

    public RefEtablissementService getEtablissementService() {
	return etablissementService;
    }

    public List<SelectItem> getAnneeAcademiques() {
	return anneeAcademiques;
    }

    public void setAnneeAcademiques(List<SelectItem> anneeAcademiques) {
	this.anneeAcademiques = anneeAcademiques;
    }

    public List<SelectItem> getEtablissements() {
	return etablissements;
    }

    public void setEtablissements(List<SelectItem> etablissements) {
	this.etablissements = etablissements;
    }

    public List<SelectItem> getPays() {
	return pays;
    }

    public void setPays(List<SelectItem> pays) {
	this.pays = pays;
    }

    public List<SelectItem> getPays1() {
	return pays1;
    }

    public void setPays1(List<SelectItem> pays1) {
	this.pays1 = pays1;
    }

    public List<SelectItem> getCivilites() {
	return civilites;
    }

    public void setCivilites(List<SelectItem> civilites) {
	this.civilites = civilites;
    }

    public List<DemandePreinscriptionDto> getResults() {
	return results;
    }

    public void setResults(List<DemandePreinscriptionDto> results) {
	this.results = results;
    }

    public NomenclatureService getNomenclatureService() {
	return nomenclatureService;
    }

    public void setNomenclatureService(NomenclatureService nomenclatureService) {
	this.nomenclatureService = nomenclatureService;
    }

    public DemandePreinscriptionDto getDto() {
	return dto;
    }

    public void setDto(DemandePreinscriptionDto dto) {
	this.dto = dto;
    }

    public boolean isRenderDemandePanel() {
	return renderDemandePanel;
    }

    public void setRenderDemandePanel(boolean renderDemandePanel) {
	this.renderDemandePanel = renderDemandePanel;
    }

    public List<SelectItem> getPaysObtentions() {
	return paysObtentions;
    }

    public void setPaysObtentions(List<SelectItem> paysObtentions) {
	this.paysObtentions = paysObtentions;
    }

    public List<SelectItem> getTitreAccesSpecialites() {
	return titreAccesSpecialites;
    }

    public void setTitreAccesSpecialites(List<SelectItem> titreAccesSpecialites) {
	this.titreAccesSpecialites = titreAccesSpecialites;
    }

    public List<SelectItem> getTypeTitreAcces() {
	return typeTitreAcces;
    }

    public void setTypeTitreAcces(List<SelectItem> typeTitreAcces) {
	this.typeTitreAcces = typeTitreAcces;
    }

    public List<SelectItem> getMentions() {
	return mentions;
    }

    public void setMentions(List<SelectItem> mentions) {
	this.mentions = mentions;
    }

    public List<SelectItem> getLangues1() {
	return langues1;
    }

    public void setLangues1(List<SelectItem> langues1) {
	this.langues1 = langues1;
    }

    public List<SelectItem> getLangues2() {
	return langues2;
    }

    public void setLangues2(List<SelectItem> langues2) {
	this.langues2 = langues2;
    }

    public List<SelectItem> getAnneeAcademiquesDemande() {
	return anneeAcademiquesDemande;
    }

    public void setAnneeAcademiquesDemande(List<SelectItem> anneeAcademiquesDemande) {
	this.anneeAcademiquesDemande = anneeAcademiquesDemande;
    }

    public List<SelectItem> getDomaines() {
	return domaines;
    }

    public void setDomaines(List<SelectItem> domaines) {
	this.domaines = domaines;
    }

    public List<SelectItem> getEtablissementsDemande() {
	return etablissementsDemande;
    }

    public void setEtablissementsDemande(List<SelectItem> etablissementsDemande) {
	this.etablissementsDemande = etablissementsDemande;
    }

    public List<SelectItem> getFilieres() {
	return filieres;
    }

    public void setFilieres(List<SelectItem> filieres) {
	this.filieres = filieres;
    }

    public List<SelectItem> getCycles() {
	return cycles;
    }

    public void setCycles(List<SelectItem> cycles) {
	this.cycles = cycles;
    }

    public List<SelectItem> getNiveaux() {
	return niveaux;
    }

    public void setNiveaux(List<SelectItem> niveaux) {
	this.niveaux = niveaux;
    }

    public boolean isTabTitreAccesDisabled() {
	return tabTitreAccesDisabled;
    }

    public void setTabTitreAccesDisabled(boolean tabTitreAccesDisabled) {
	this.tabTitreAccesDisabled = tabTitreAccesDisabled;
    }

    public boolean isTabDemandeDisabled() {
	return tabDemandeDisabled;
    }

    public void setTabDemandeDisabled(boolean tabDemandeDisabled) {
	this.tabDemandeDisabled = tabDemandeDisabled;
    }

    public boolean isTabDecisionDisabled() {
	return tabDecisionDisabled;
    }

    public void setTabDecisionDisabled(boolean tabDecisionDisabled) {
	this.tabDecisionDisabled = tabDecisionDisabled;
    }

    public boolean isTabHistoriqueDisabled() {
	return tabHistoriqueDisabled;
    }

    public void setTabHistoriqueDisabled(boolean tabHistoriqueDisabled) {
	this.tabHistoriqueDisabled = tabHistoriqueDisabled;
    }

    public List<SelectItem> getSpecialites() {
	return specialites;
    }

    public void setSpecialites(List<SelectItem> specialites) {
	this.specialites = specialites;
    }

    public OuvertureOffreFormationService getFormationService() {
	return formationService;
    }

    public void setFormationService(OuvertureOffreFormationService formationService) {
	this.formationService = formationService;
    }

    public RefDomaineLMDService getDomaineService() {
	return domaineService;
    }

    public void setDomaineService(RefDomaineLMDService domaineService) {
	this.domaineService = domaineService;
    }

    public NiveauService getNiveauService() {
	return niveauService;
    }

    public void setNiveauService(NiveauService niveauService) {
	this.niveauService = niveauService;
    }

    public RefSpecialiteLmdService getSpecialiteLmdService() {
	return specialiteLmdService;
    }

    public void setSpecialiteLmdService(RefSpecialiteLmdService specialiteLmdService) {
	this.specialiteLmdService = specialiteLmdService;
    }

    public RefAffectDomLmdEtabService getAffectDomLmdEtabService() {
	return affectDomLmdEtabService;
    }

    public void setAffectDomLmdEtabService(RefAffectDomLmdEtabService affectDomLmdEtabService) {
	this.affectDomLmdEtabService = affectDomLmdEtabService;
    }

    public RefFiliereLmdService getRefFiliereLmdService() {
	return refFiliereLmdService;
    }

    public void setRefFiliereLmdService(RefFiliereLmdService refFiliereLmdService) {
	this.refFiliereLmdService = refFiliereLmdService;
    }

    public SituationService getSituationService() {
	return situationService;
    }

    public void setSituationService(SituationService situationService) {
	this.situationService = situationService;
    }

    public boolean isTabDocumentDisabled() {
	return tabDocumentDisabled;
    }

    public void setTabDocumentDisabled(boolean tabDocumentDisabled) {
	this.tabDocumentDisabled = tabDocumentDisabled;
    }

    public List<SituationEntiteOccurrenceDto> getDemandeHistory() {
	return demandeHistory;
    }

    public void setDemandeHistory(List<SituationEntiteOccurrenceDto> demandeHistory) {
	this.demandeHistory = demandeHistory;
    }

    public boolean isGenerationDisabed() {
	return generationDisabed;
    }

    public void setGenerationDisabed(boolean generationDisabed) {
	this.generationDisabed = generationDisabed;
    }

    public boolean isDeleteDisabed() {
	return deleteDisabed;
    }

    public void setDeleteDisabed(boolean deleteDisabed) {
	this.deleteDisabed = deleteDisabed;
    }

    public boolean isEditionMode() {
	return editionMode;
    }

    public void setEditionMode(boolean editionMode) {
	this.editionMode = editionMode;
    }

    public boolean isSaveDisabed() {
	return saveDisabed;
    }

    public void setSaveDisabed(boolean saveDisabed) {
	this.saveDisabed = saveDisabed;
    }

    public List<SelectItem> getNationalites() {
	return nationalites;
    }

    public void setNationalites(List<SelectItem> nationalites) {
	this.nationalites = nationalites;
    }

    public Date getCurrentdate() {
	return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
	this.currentDate = currentDate;
    }
}
