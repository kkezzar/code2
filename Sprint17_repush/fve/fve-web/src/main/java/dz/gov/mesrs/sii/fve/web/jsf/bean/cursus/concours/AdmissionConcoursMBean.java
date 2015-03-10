package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.concours;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.fve.business.model.dto.concours.ConcoursDto;
import dz.gov.mesrs.sii.fve.business.model.dto.concours.DossierCandidatDto;
import dz.gov.mesrs.sii.fve.business.model.dto.concours.EtablissementAdmissionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.service.concours.AdmissionService;
import dz.gov.mesrs.sii.fve.business.service.concours.ConcoursService;
import dz.gov.mesrs.sii.fve.business.service.concours.DossierCandidatService;
import dz.gov.mesrs.sii.fve.business.service.concours.NombreAdmisAtteintException;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;

@ManagedBean(name = "admissionConcoursMBean")
@ViewScoped
public class AdmissionConcoursMBean implements Serializable {

    private static final long serialVersionUID = -5820641729855246674L;

    private List<SelectItem> anneeAcademiques;
    private List<SelectItem> concours;
    private List<SelectItem> etablissementAdmssions;

    private Integer currentAnneeAcademiqueId;
    private Long currentConcoursId;
    private Long selectedEtablissementId;
    private boolean displayCandidatDataTable;
    private boolean displayCandidatAdmissionPanel;
    private Integer nombreAdmissibles;
    private Integer nombreAdmis;
    private int candidatTabViewActiveIndex =0;

    private LazyDataModel<DossierCandidatDto> candidatDtos;
    private ConcoursDto concoursDto;
    private DossierCandidatDto candidatDto;

    @ManagedProperty("#{anneeAcademiqueService}")
    private AnneeAcademiqueService anneeAcademiqueService;
    @ManagedProperty("#{concoursServiceImpl}")
    private ConcoursService concoursService;
    @ManagedProperty("#{dossierCandidatServiceImpl}")
    private DossierCandidatService candidatService;
    @ManagedProperty("#{sessionBeanFve}")
    private SessionBean sessionBean;
    @ManagedProperty("#{admissionServiceImpl}")
    private AdmissionService admissionService;

    @PostConstruct
    public void init() {
	initAnneeAcademiqueList();
    }

    private void initAnneeAcademiqueList() {
	anneeAcademiques = new ArrayList<>();
	List<AnneeAcademiqueDto> dtos = anneeAcademiqueService.findAll();
	for (AnneeAcademiqueDto dto : dtos) {
	    anneeAcademiques.add(new SelectItem(dto.getId(), dto.getCode()));
	}
    }

    public void onAnneeAcademiqueSelected() {
	currentConcoursId = null;
	this.concours = new ArrayList<>();
	ConcoursDto concoursDto = new ConcoursDto();
	concoursDto.setEtablissementId(sessionBean.getIdEtablissement());
	concoursDto.setAnneeAcademiqueId(currentAnneeAcademiqueId);
	List<ConcoursDto> dtos = concoursService.findAll(concoursDto);
	if (dtos != null) {
	    for (ConcoursDto dto : dtos) {
		if (dto.getDatePublicationConcours() != null) {
		    this.concours.add(new SelectItem(dto.getId(), dto.getIntituleLatin()));
		}

	    }
	} else {
	    Utility.showWarningMessage("Auncun concours pour cette année académique");
	}

	displayCandidatDataTable = false;
	displayCandidatAdmissionPanel = false;
	candidatDtos = null;
	candidatDto = null;
    }

    public void onConcoursSelected() {
	displayCandidatAdmissionPanel = false;
	if (currentConcoursId == null) {
	    candidatDtos = null;
	    displayCandidatDataTable = false;
	    etablissementAdmssions = null;
	} else {
	    displayCandidatDataTable = true;
	    concoursDto = new ConcoursDto();
	    concoursDto.setEtablissementId(sessionBean.getIdEtablissement());
	    concoursDto.setId(currentConcoursId.longValue());
	    concoursDto = concoursService.find(concoursDto);
	    final DossierCandidatDto dtoExample = new DossierCandidatDto();
	    dtoExample.setConcoursId(concoursDto.getId());
	    dtoExample.setAccepte(true);
	    candidatDtos = new LazyDataModel<DossierCandidatDto>() {

		private static final long serialVersionUID = 8943235766499326192L;

		@Override
		public List<DossierCandidatDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, String> filters) {
		    candidatDtos.setRowCount(candidatService.countByExample(dtoExample));
		    Boolean descending = null;
		    if (sortOrder != null) {
			if (sortOrder.equals(SortOrder.DESCENDING)) {
			    descending = Boolean.TRUE;
			} else {
			    descending = Boolean.FALSE;
			}

		    }
		    return candidatService.findByExample(dtoExample, sortField, pageSize, first, descending);
		}

	    };
	    etablissementAdmssions = new ArrayList<>();
	    for (EtablissementAdmissionDto etablissmentAdmissionDto : concoursDto.getEtablissementAdmissionDtos()) {
		etablissementAdmssions.add(new SelectItem(etablissmentAdmissionDto.getId(), etablissmentAdmissionDto
			.getEtablissementLibelleFr()));
	    }
	    calculerNombreAdmis();
	    calculerNombreAdmissible();
	}
    }

    private void calculerNombreAdmissible() {
	nombreAdmissibles = 0;
	for (EtablissementAdmissionDto dto : concoursDto.getEtablissementAdmissionDtos()) {
	    nombreAdmissibles += dto.getNombreAdmettre();
	}
    }

    private void calculerNombreAdmis() {
	nombreAdmis = 0;
	for (EtablissementAdmissionDto dto : concoursDto.getEtablissementAdmissionDtos()) {
	    admissionService.countNombreAdmis(dto);
	    nombreAdmis += dto.getNombreAdmis();
	}

    }

    public void onCandidatSelected(SelectEvent event) {
	candidatTabViewActiveIndex = 0;
	displayCandidatAdmissionPanel = true;
	candidatDto = (DossierCandidatDto) event.getObject();
	if (candidatDto.getEtablissementAdmissionDto() == null) {
	    selectedEtablissementId = null;
	} else {
	    selectedEtablissementId = candidatDto.getEtablissementAdmissionDto().getId();
	    admissionService.countNombreAdmis(candidatDto.getEtablissementAdmissionDto());
	}
    }

    public void onEtablissementSelected() {
	for (EtablissementAdmissionDto dto : concoursDto.getEtablissementAdmissionDtos()) {
	    if (dto.getId().equals(selectedEtablissementId)) {
		candidatDto.setEtablissementAdmissionDto(dto);
		break;
	    }
	}
	admissionService.countNombreAdmis(candidatDto.getEtablissementAdmissionDto());
    }

    public void onSave() {
	if (!validateCandidatForm()) {
	    return;
	}
	try {
	    if (candidatDto.getDateDesistement() == null) {
		admissionService.admettreCandidat(candidatDto);
	    } else {
		candidatDto.setDesistement(true);
		candidatDto = candidatService.save(candidatDto);
	    }

	    onConcoursSelected();
	    displayCandidatAdmissionPanel = true;
	    admissionService.countNombreAdmis(candidatDto.getEtablissementAdmissionDto());
	    Utility.showSuccessSaveMessage();
	} catch (NombreAdmisAtteintException e) {
	    Utility.showErrorMessage("Le nombre d'admissibles pour cette établissement a été atteint");
	}

    }

    private boolean validateCandidatForm() {
	boolean valid = true;
	Date dateDesistementCandidat = candidatDto.getDateDesistement();
	Date datePublicationResultatConcours = concoursDto.getDatePublicationResultats();
	if (dateDesistementCandidat != null) {
	    if (datePublicationResultatConcours == null
		    || dateDesistementCandidat.compareTo(datePublicationResultatConcours) < 0) {
		valid = false;
		Utility.showErrorMessage("Vérifier la date de désistement avec la date de publication du résultat du concours. ");
	    }
	}
	return valid;
    }

    public void onSaveConcours() {
	if (validateConcoursForm()) {
	    concoursService.save(concoursDto);
	    Utility.showSuccessSaveMessage();
	}

    }

    private boolean validateConcoursForm() {
	boolean valid = true;
	Date datePublicationResultat = concoursDto.getDatePublicationResultats();
	Date datePublicationListComplementaire = concoursDto.getDatePublicationListeComplementaire();
	if (datePublicationResultat != null
		&& datePublicationResultat.compareTo(concoursDto.getDatePublicationConcours()) < 0) {
	    valid = false;
	    Utility.showErrorMessage("Vérifier la date de publication des résultats avec la date de publication du concours. ");
	}
	if (datePublicationListComplementaire != null
		&& datePublicationListComplementaire.compareTo(datePublicationResultat) < 0) {
	    valid = false;
	    Utility.showErrorMessage("Vérifier la date de publication de la liste complémentaire avec  la date de publication de la liste finale . ");
	}
	return valid;
    }

    public List<SelectItem> getAnneeAcademiques() {
	return anneeAcademiques;
    }

    public void setAnneeAcademiques(List<SelectItem> anneeAcademiques) {
	this.anneeAcademiques = anneeAcademiques;
    }

    public List<SelectItem> getConcours() {
	return concours;
    }

    public void setConcours(List<SelectItem> concours) {
	this.concours = concours;
    }

    public Integer getCurrentAnneeAcademiqueId() {
	return currentAnneeAcademiqueId;
    }

    public void setCurrentAnneeAcademiqueId(Integer currentAnneeAcademiqueId) {
	this.currentAnneeAcademiqueId = currentAnneeAcademiqueId;
    }

    public Long getCurrentConcoursId() {
	return currentConcoursId;
    }

    public void setCurrentConcoursId(Long currentConcoursId) {
	this.currentConcoursId = currentConcoursId;
    }

    public boolean isDisplayCandidatDataTable() {
	return displayCandidatDataTable;
    }

    public void setDisplayCandidatDataTable(boolean displayCandidatDataTable) {
	this.displayCandidatDataTable = displayCandidatDataTable;
    }

    public LazyDataModel<DossierCandidatDto> getCandidatDtos() {
	return candidatDtos;
    }

    public void setCandidatDtos(LazyDataModel<DossierCandidatDto> candidatDtos) {
	this.candidatDtos = candidatDtos;
    }

    public AnneeAcademiqueService getAnneeAcademiqueService() {
	return anneeAcademiqueService;
    }

    public void setAnneeAcademiqueService(AnneeAcademiqueService anneeAcademiqueService) {
	this.anneeAcademiqueService = anneeAcademiqueService;
    }

    public ConcoursService getConcoursService() {
	return concoursService;
    }

    public void setConcoursService(ConcoursService concoursService) {
	this.concoursService = concoursService;
    }

    public DossierCandidatService getCandidatService() {
	return candidatService;
    }

    public void setCandidatService(DossierCandidatService candidatService) {
	this.candidatService = candidatService;
    }

    public SessionBean getSessionBean() {
	return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean) {
	this.sessionBean = sessionBean;
    }

    public boolean isDisplayCandidatAdmissionPanel() {
	return displayCandidatAdmissionPanel;
    }

    public void setDisplayCandidatAdmissionPanel(boolean displayCandidatAdmissionPanel) {
	this.displayCandidatAdmissionPanel = displayCandidatAdmissionPanel;
    }

    public ConcoursDto getConcoursDto() {
	return concoursDto;
    }

    public void setConcoursDto(ConcoursDto concoursDto) {
	this.concoursDto = concoursDto;
    }

    public DossierCandidatDto getCandidatDto() {
	return candidatDto;
    }

    public void setCandidatDto(DossierCandidatDto candidatDto) {
	this.candidatDto = candidatDto;
    }

    public List<SelectItem> getEtablissementAdmssions() {
	return etablissementAdmssions;
    }

    public void setEtablissementAdmssions(List<SelectItem> etablissementAdmssions) {
	this.etablissementAdmssions = etablissementAdmssions;
    }

    public Long getSelectedEtablissementId() {
	return selectedEtablissementId;
    }

    public void setSelectedEtablissementId(Long selectedEtablissementId) {
	this.selectedEtablissementId = selectedEtablissementId;
    }

    public void setAdmissionService(AdmissionService admissionService) {
	this.admissionService = admissionService;
    }

    public Integer getNombreAdmissibles() {
	return nombreAdmissibles;
    }

    public void setNombreAdmissibles(Integer nombreAdmissibles) {
	this.nombreAdmissibles = nombreAdmissibles;
    }

    public Integer getNombreAdmis() {
	return nombreAdmis;
    }

    public void setNombreAdmis(Integer nombreAdmis) {
	this.nombreAdmis = nombreAdmis;
    }
public int getCandidatTabViewActiveIndex() {
    return candidatTabViewActiveIndex;
}

public void setCandidatTabViewActiveIndex(int candidatTabViewActiveIndex) {
    this.candidatTabViewActiveIndex = candidatTabViewActiveIndex;
}
}
