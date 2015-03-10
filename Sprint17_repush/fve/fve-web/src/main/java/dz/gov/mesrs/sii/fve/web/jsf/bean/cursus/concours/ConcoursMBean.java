package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.concours;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.fve.business.model.dto.concours.ConcoursDto;
import dz.gov.mesrs.sii.fve.business.model.dto.concours.EtablissementAdmissionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.concours.ExamenConcoursDto;
import dz.gov.mesrs.sii.fve.business.model.dto.concours.PieceConcoursDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.service.concours.ConcoursService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectDomLmdEtabDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFiliereLmdDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefLieuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefSpecialiteLmdDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefDomaineLMDService;
import dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService;
import dz.gov.mesrs.sii.referentiel.business.service.RefFiliereLmdService;
import dz.gov.mesrs.sii.referentiel.business.service.RefGroupeService;
import dz.gov.mesrs.sii.referentiel.business.service.RefLieuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefSpecialiteLmdService;

@ViewScoped
@ManagedBean(name = "concoursMBean")
public class ConcoursMBean implements Serializable {

    private static final long serialVersionUID = 8971093747651862728L;

    private final static Logger logger = LoggerFactory.getLogger(ConcoursMBean.class.getName());

    @ManagedProperty("#{concoursServiceImpl}")
    private ConcoursService concoursService;
    @ManagedProperty("#{refDomaineLMDServiceImpl}")
    private RefDomaineLMDService domaineService;
    @ManagedProperty("#{refFiliereLmdServiceImpl}")
    private RefFiliereLmdService filiereService;
    @ManagedProperty("#{refSpecialiteLmdServiceImpl}")
    private RefSpecialiteLmdService specialiteService;
    @ManagedProperty("#{nomenclatureServiceImpl}")
    private NomenclatureService nomenclatureService;
    @ManagedProperty("#{anneeAcademiqueService}")
    private AnneeAcademiqueService anneeAcademiqueService;
    @ManagedProperty("#{situationService}")
    private SituationService situationService;
    @ManagedProperty("#{refEtablissementServiceImpl}")
    private RefEtablissementService etablissementService;
    @ManagedProperty("#{refLieuServiceImpl}")
    private RefLieuService lieuService;
    @ManagedProperty("#{refGroupeServiceImpl}")
    private RefGroupeService groupeService;
    @ManagedProperty("#{sessionBeanFve}")
    private SessionBean sessionBean;

    private ConcoursDto concoursDto;
    private List<ConcoursDto> concoursDtos;
    private List<SituationEntiteOccurrenceDto> concoursHistory;

    private List<SelectItem> domaines;
    private List<SelectItem> typeConcours;
    private List<SelectItem> filieres;
    private List<SelectItem> specialites;
    private List<SelectItem> anneeAcademiques;
    private List<SelectItem> etablissementsAdmission;
    private List<SelectItem> typeExamens;
    private List<SelectItem> epreuves;
    private List<SelectItem> lieux;
    private List<SelectItem> typePieces;
    private List<SelectItem> responsables;
    private List<SelectItem> anneeAcademiquesSearch;

    private Integer selectedEtablissementAdmissionIndex;
    private Integer selectedPieceConstitutiveIndex;
    private Integer selectedExamenIndex;
    private Integer selectedAnneeAcademiqueId;

    private boolean editMode = true;
    private boolean renderEditPanel = false;
    private boolean renderConcoursDataTable;

    @PostConstruct
    public void init() {
	initDomaineList();
	initTypeConcoursList();
	initAnneeAcademiquesList();
	initEtablissementList();
	initTypeExamensList();
	initEpreuvesList();
	initLieuxList();
	initTypePiecesList();
	initResponsableList();
    }

    private void initEpreuvesList() {
	epreuves = new ArrayList<>();
	List<NomenclatureDto> dtos = nomenclatureService.findByName(CursusConstants.NC_TYPE_EXAMEN);
	for (NomenclatureDto dto : dtos) {
	    epreuves.add(new SelectItem(dto.getId(), dto.getLibelleLongFr()));
	}
	// TODO c'est quoi le type depreuve

    }

    private void initTypeExamensList() {
	typeExamens = new ArrayList<>();
	List<NomenclatureDto> dtos = nomenclatureService.findByName(CursusConstants.NC_TYPE_EXAMEN);
	for (NomenclatureDto dto : dtos) {
	    typeExamens.add(new SelectItem(dto.getId(), dto.getLibelleLongFr()));
	}
    }

    private void initLieuxList() {
	lieux = new ArrayList<>();
	List<RefLieuDto> dtos = lieuService.findAll(sessionBean.getIdEtablissement());
	for (RefLieuDto dto : dtos) {
	    lieux.add(new SelectItem(dto.getId(), dto.getDesignation()));
	}

    }

    private void initTypePiecesList() {
	typePieces = new ArrayList<>();
	List<NomenclatureDto> dtos = nomenclatureService.findByName(CursusConstants.NC_TYPE_PIECE);
	for (NomenclatureDto dto : dtos) {
	    typePieces.add(new SelectItem(dto.getId(), dto.getLibelleLongFr()));
	}

    }

    private void initEtablissementList() {
	etablissementsAdmission = new ArrayList<>();
	List<RefEtablissementDto> dtos = etablissementService.findAll();
	for (RefEtablissementDto dto : dtos) {
	    etablissementsAdmission.add(new SelectItem(dto.getId(), dto.getLlEtablissementLatin()));
	}

    }

    private void initAnneeAcademiquesList() {
	anneeAcademiques = new ArrayList<>();
	anneeAcademiquesSearch = new ArrayList<>();
	List<AnneeAcademiqueDto> dtos = anneeAcademiqueService.findAll();
	for (AnneeAcademiqueDto dto : dtos) {
	    anneeAcademiques.add(new SelectItem(dto.getId(), dto.getPremiereAnnee() + "/" + dto.getDeuxiemeAnnee()));
	    anneeAcademiquesSearch.add(new SelectItem(dto.getId(), dto.getCode()));
	}

    }

    private void initTypeConcoursList() {
	typeConcours = new ArrayList<>();
	List<NomenclatureDto> dtos = nomenclatureService.findByName(UtilConstants.NC_TYPE_CONCOURS_NAME);
	for (NomenclatureDto dto : dtos) {
	    typeConcours.add(new SelectItem(dto.getId(), dto.getLibelleLongFr()));
	}
    }

    private void initDomaineList() {
	domaines = new ArrayList<>();
	Integer sEtablissementId = sessionBean.getIdEtablissement();
	List<RefAffectDomLmdEtabDto> dtos = domaineService.findDomainesLMDByEtablissement(sEtablissementId);
	for (RefAffectDomLmdEtabDto dto : dtos) {
	    domaines.add(new SelectItem(dto.getIdDomaineLMD(), dto.getLlDomaine()));
	}
    }

    private void initResponsableList() {
	responsables = new ArrayList<>();
	List<RefIndividuDto> dtos = concoursService.findResponsableExamens(sessionBean.getIdEtablissement());
	for (RefIndividuDto dto : dtos) {
	    responsables.add(new SelectItem(dto.getId(), dto.getPrenomLatin() + " " + dto.getNomLatin()));
	}

    }

    private void retrieveHistory() {
	concoursHistory = situationService.getEntityOccurrenceHistory(UtilConstants.ENTITE_CONCOURS,
		((Long) concoursDto.getId()).intValue());
	if (concoursHistory != null && !concoursHistory.isEmpty()) {
	    concoursDto.setDateCreation(concoursHistory.get(concoursHistory.size() - 1).getDateSituation());
	}
    }

    public void onSearchConcours() {
	renderEditPanel = false;
	renderConcoursDataTable = true;
	concoursDto = null;
	ConcoursDto example = new ConcoursDto();
	example.setEtablissementId(sessionBean.getIdEtablissement());
	example.setAnneeAcademiqueId(selectedAnneeAcademiqueId);
	concoursDtos = concoursService.findAll(example);

    }

    public void onNew() {
	concoursDto = new ConcoursDto();
	concoursHistory = null;
	concoursDto.setEtablissementId(sessionBean.getIdEtablissement());
	filieres = null;
	specialites = null;
	renderEditPanel = true;
    }

    public void onDomaineSelected() {
	specialites = null;
	filieres = null;
	if (concoursDto.getDomaineId() != null) {
	    List<RefFiliereLmdDto> dtos = filiereService.findByIdDomainelmd(concoursDto.getDomaineId());
	    if (dtos != null && dtos.size() > 0) {
		filieres = new ArrayList<>();
		for (RefFiliereLmdDto dto : dtos) {
		    filieres.add(new SelectItem(dto.getId(), dto.getLlFiliere()));
		}
	    }
	}

    }

    public void onConcoursSelect(SelectEvent event) {
	concoursDto = (ConcoursDto) event.getObject();
	retrieveHistory();
	renderEditPanel = true;
    }

    public void onFiliereSelected() {
	specialites = null;
	if (concoursDto.getFiliereLmdDto() != null && concoursDto.getFiliereLmdDto().getId() != null) {
	    List<RefSpecialiteLmdDto> dtos = specialiteService.findByIdFilierelmd(concoursDto.getFiliereLmdDto()
		    .getId());
	    if (dtos != null && dtos.size() > 0) {
		specialites = new ArrayList<>();
		for (RefSpecialiteLmdDto dto : dtos) {
		    specialites.add(new SelectItem(dto.getId(), dto.getLlSpecialite()));
		}
	    }
	}

    }

    public void onDelete() {
	concoursService.delete(concoursDto);
	concoursDto = new ConcoursDto();
    }

    public void onSave() {
	try {
	    if (validateForm()) {
		this.concoursDto = concoursService.save(concoursDto);
		retrieveHistory();
		Utility.showSuccessSaveMessage();
	    } else {
		return;
	    }

	} catch (Exception e) {
	    logger.error("Erreur lors de la sauvegarde de " + concoursDto, e);
	    Utility.showErrorMessage("une erreur s'est produite");
	}

    }

    public void onValidate() {
	try {
	    if (validateForm()) {
		this.concoursDto = concoursService.validate(concoursDto);
		retrieveHistory();
		Utility.showSuccessMessage("Concours validé");
	    } else {
		return;
	    }

	} catch (Exception e) {
	    logger.error("Erreur lors de la validation " + concoursDto, e);
	    Utility.showErrorMessage("une erreur s'est produite");
	}

    }

    private boolean validateForm() {
	boolean valid = true;
	Date dateDebutConcours = concoursDto.getDateDebut();
	Date dateFinConcours = concoursDto.getDateFin();
	if (dateDebutConcours.compareTo(dateFinConcours) > 0) {
	    Utility.showErrorMessage("La date de début du concours ne doit pas dépasser la date de fin");
	    valid = false;
	}
	// Date limite depot dossier
	if (concoursDto.getDateLimite().compareTo(dateDebutConcours) >= 0) {
	    Utility.showErrorMessage("La date limite de dépôt des dossiers doit être inférieure à la date de début du concours");
	    valid = false;
	}
	// Date publication du concours
	Date datePublicationConcours = concoursDto.getDatePublicationConcours();
	if (datePublicationConcours != null && datePublicationConcours.compareTo(concoursDto.getDateCreation()) <= 0) {
	    Utility.showErrorMessage("Vérifier la date de publication avec la date de création du concours.");
	    valid = false;
	}

	// La liste de établissements
	List<EtablissementAdmissionDto> etablissements = concoursDto.getEtablissementAdmissionDtos();
	if (etablissements != null && etablissements.size() > 0) {
	    List<Integer> ids = new ArrayList<>();
	    for (EtablissementAdmissionDto dto : etablissements) {
		if (ids.contains(dto.getEtablissementId())) {
		    Utility.showErrorMessage("Vous avez choisi plusieurs fois le même établissement d'admission");
		    valid = false;
		    break;
		} else {
		    ids.add(dto.getEtablissementId());
		}
	    }

	}
	// les dates des examens
	List<ExamenConcoursDto> examens = concoursDto.getExamenConcoursDtos();
	if (examens != null && examens.size() > 0) {
	    for (ExamenConcoursDto examen : examens) {
		Date dateDebutExamen = examen.getDateDebut();
		if (dateDebutExamen.compareTo(dateDebutConcours) < 0 || dateDebutExamen.compareTo(dateFinConcours) > 0) {
		    Utility.showErrorMessage("Vérifier la date début de l'examen avec les dates de début et de fin du concours");
		    valid = false;
		    break;
		}
	    }

	}
	return valid;

    }

    public boolean isConcoursPubliable() {
	boolean result = false;
	SituationEntiteDto situationEntiteDto = concoursDto.getSituationEntiteDto();
	if (situationEntiteDto != null
		&& UtilConstants.SITUTAION_VALIDEE_CODE.equals(situationEntiteDto.getCodeSituation())) {
	    result = true;
	}
	return result;
    }

    public void addEtablissementAdmission() {
	EtablissementAdmissionDto dto = new EtablissementAdmissionDto(0);
	this.concoursDto.addEtablissementAdmissionDto(dto);
    }

    public void addExamen() {
	ExamenConcoursDto dto = new ExamenConcoursDto();
	this.concoursDto.addExamenConcoursDto(dto);
    }

    public void addPieceConstitutive() {
	PieceConcoursDto dto = new PieceConcoursDto();
	this.concoursDto.addPieceConstitutive(dto);
    }

    public void removeEtablissementAdmission() {
	EtablissementAdmissionDto dto = concoursDto.getEtablissementAdmissionDtos().get(
		selectedEtablissementAdmissionIndex);
	concoursDto.removeEtablissementAdmissionDto(dto);
    }

    public void removeExamen() {
	ExamenConcoursDto dto = concoursDto.getExamenConcoursDtos().get(selectedExamenIndex);
	concoursDto.removeExamenConcoursDto(dto);
    }

    public void removePieceConstitutive() {
	PieceConcoursDto dto = concoursDto.getPieceConstitutiveDtos().get(selectedPieceConstitutiveIndex);
	concoursDto.removePieceConstitutive(dto);
    }

    public boolean isEditMode() {
	return editMode;
    }

    public void setEditMode(boolean editMode) {
	this.editMode = editMode;
    }

    public boolean isRenderEditPanel() {
	return renderEditPanel;
    }

    public void setRenderEditPanel(boolean renderEditPanel) {
	this.renderEditPanel = renderEditPanel;
    }

    public ConcoursDto getConcoursDto() {
	return concoursDto;
    }

    public void setConcoursDto(ConcoursDto concoursDto) {
	this.concoursDto = concoursDto;
    }

    public SessionBean getSessionBean() {
	return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean) {
	this.sessionBean = sessionBean;
    }

    public List<SelectItem> getDomaines() {
	return domaines;
    }

    public void setDomaines(List<SelectItem> domaines) {
	this.domaines = domaines;
    }

    public List<SelectItem> getTypeConcours() {
	return typeConcours;
    }

    public void setTypeConcours(List<SelectItem> typeConcours) {
	this.typeConcours = typeConcours;
    }

    public void setConcoursService(ConcoursService concoursService) {
	this.concoursService = concoursService;
    }

    public void setDomaineService(RefDomaineLMDService domaineService) {
	this.domaineService = domaineService;
    }

    public void setNomenclatureService(NomenclatureService nomenclatureService) {
	this.nomenclatureService = nomenclatureService;
    }

    public List<SelectItem> getFilieres() {
	return filieres;
    }

    public void setFilieres(List<SelectItem> filieres) {
	this.filieres = filieres;
    }

    public List<SelectItem> getSpecialites() {
	return specialites;
    }

    public void setSpecialites(List<SelectItem> specialites) {
	this.specialites = specialites;
    }

    public void setFiliereService(RefFiliereLmdService filiereService) {
	this.filiereService = filiereService;
    }

    public void setSpecialiteService(RefSpecialiteLmdService specialiteService) {
	this.specialiteService = specialiteService;
    }

    public List<SelectItem> getAnneeAcademiques() {
	return anneeAcademiques;
    }

    public void setAnneeAcademiques(List<SelectItem> anneeAcademiques) {
	this.anneeAcademiques = anneeAcademiques;
    }

    public void setAnneeAcademiqueService(AnneeAcademiqueService anneeAcademiqueService) {
	this.anneeAcademiqueService = anneeAcademiqueService;
    }

    public void setSituationService(SituationService situationService) {
	this.situationService = situationService;
    }

    public List<SelectItem> getEtablissementsAdmission() {
	return etablissementsAdmission;
    }

    public void setEtablissementsAdmission(List<SelectItem> etablissementsAdmission) {
	this.etablissementsAdmission = etablissementsAdmission;
    }

    public void setEtablissementService(RefEtablissementService etablissementService) {
	this.etablissementService = etablissementService;
    }

    public Integer getSelectedEtablissementAdmissionIndex() {
	return selectedEtablissementAdmissionIndex;
    }

    public void setSelectedEtablissementAdmissionIndex(Integer selectedEtablissementAdmissionIndex) {
	this.selectedEtablissementAdmissionIndex = selectedEtablissementAdmissionIndex;
    }

    public void setSelectedExamenIndex(Integer selectedExamenIndex) {
	this.selectedExamenIndex = selectedExamenIndex;
    }

    public Integer getSelectedExamenIndex() {
	return selectedExamenIndex;
    }

    public List<SelectItem> getTypeExamens() {
	return typeExamens;
    }

    public void setTypeExamens(List<SelectItem> typeExamens) {
	this.typeExamens = typeExamens;
    }

    public List<SelectItem> getEpreuves() {
	return epreuves;
    }

    public void setEpreuves(List<SelectItem> epreuves) {
	this.epreuves = epreuves;
    }

    public List<SelectItem> getLieux() {
	return lieux;
    }

    public void setLieux(List<SelectItem> lieux) {
	this.lieux = lieux;
    }

    public void setLieuService(RefLieuService lieuService) {
	this.lieuService = lieuService;
    }

    public Integer getSelectedPieceConstitutiveIndex() {
	return selectedPieceConstitutiveIndex;
    }

    public void setSelectedPieceConstitutiveIndex(Integer selectedPieceConstitutiveIndex) {
	this.selectedPieceConstitutiveIndex = selectedPieceConstitutiveIndex;
    }

    public List<SelectItem> getTypePieces() {
	return typePieces;
    }

    public void setTypePieces(List<SelectItem> typePieces) {
	this.typePieces = typePieces;
    }

    public void setGroupeService(RefGroupeService groupeService) {
	this.groupeService = groupeService;
    }

    public void setResponsables(List<SelectItem> responsables) {
	this.responsables = responsables;
    }

    public List<SelectItem> getResponsables() {
	return responsables;
    }

    public List<SelectItem> getAnneeAcademiquesSearch() {
	return anneeAcademiquesSearch;
    }

    public void setAnneeAcademiquesSearch(List<SelectItem> anneeAcademiquesSearch) {
	this.anneeAcademiquesSearch = anneeAcademiquesSearch;
    }

    public Integer getSelectedAnneeAcademiqueId() {
	return selectedAnneeAcademiqueId;
    }

    public void setSelectedAnneeAcademiqueId(Integer selectedAnneeAcademiqueId) {
	this.selectedAnneeAcademiqueId = selectedAnneeAcademiqueId;
    }

    public boolean isRenderConcoursDataTable() {
	return renderConcoursDataTable;
    }

    public void setRenderConcoursDataTable(boolean renderConcoursDataTable) {
	this.renderConcoursDataTable = renderConcoursDataTable;
    }

    public List<ConcoursDto> getConcoursDtos() {
	return concoursDtos;
    }

    public void setConcoursDtos(List<ConcoursDto> concoursDtos) {
	this.concoursDtos = concoursDtos;
    }

    public List<SituationEntiteOccurrenceDto> getConcoursHistory() {
	return concoursHistory;
    }

    public void setConcoursHistory(List<SituationEntiteOccurrenceDto> concoursHistory) {
	this.concoursHistory = concoursHistory;
    }

}
