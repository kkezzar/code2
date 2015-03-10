package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.concours;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.fve.business.model.dto.concours.ConcoursDto;
import dz.gov.mesrs.sii.fve.business.model.dto.concours.DossierCandidatDto;
import dz.gov.mesrs.sii.fve.business.model.dto.concours.PieceConcoursDto;
import dz.gov.mesrs.sii.fve.business.model.dto.concours.PieceFournieDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.service.concours.ConcoursService;
import dz.gov.mesrs.sii.fve.business.service.concours.DossierCandidatService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;

@ManagedBean(name = "candidatMBean")
@ViewScoped
public class CandidatMBean implements Serializable {

    private static final long serialVersionUID = 8894137612380359002L;

    private List<SelectItem> anneeAcademiques;
    private List<SelectItem> concours;
    private Integer currentAnneeAcademiqueId;
    private Long currentConcoursId;

    private boolean addEnabled;
    private boolean renderEditPanel;
    private boolean candidatEditable;
    private boolean renderCandidatDataPanel;
    private boolean renderIndividuDataPanel;
    private boolean renderIndividuDetailPanel;
    private boolean renderIndividuSearchPanel;
    private boolean saveEnabled;
    private boolean dossierTabActive;
    private int currentTabViewDossierIndex = 0;

    private DossierCandidatDto dto;
    private ConcoursDto concoursDto;
    private LazyDataModel<DossierCandidatDto> candidatsDtos;
    private LazyDataModel<RefIndividuDto> individuDtos;
    private String nomIndividuRecherche;
    private String prenomIndividuRecherche;

    @ManagedProperty("#{concoursServiceImpl}")
    private ConcoursService concoursService;
    @ManagedProperty("#{anneeAcademiqueService}")
    private AnneeAcademiqueService anneeAcademiqueService;
    @ManagedProperty("#{dossierCandidatServiceImpl}")
    private DossierCandidatService dossierCandidatService;
    @ManagedProperty("#{refIndividuServiceImpl}")
    private RefIndividuService individuService;
    @ManagedProperty("#{sessionBeanFve}")
    private SessionBean sessionBean;

    @PostConstruct
    public void init() {
	initAnneeAcademiqueList();
    }

    private void initAnneeAcademiqueList() {
	List<AnneeAcademiqueDto> dtos = anneeAcademiqueService.findAll();
	anneeAcademiques = new ArrayList<>();
	for (AnneeAcademiqueDto dto : dtos) {
	    anneeAcademiques.add(new SelectItem(dto.getId(), dto.getCode()));
	}
    }

    public void onAnneeAcademiqueSelected() {
	currentConcoursId = null;
	addEnabled = false;
	renderCandidatDataPanel = false;
	concours = new ArrayList<>();
	ConcoursDto example = new ConcoursDto();
	example.setEtablissementId(sessionBean.getIdEtablissement());
	example.setAnneeAcademiqueId(currentAnneeAcademiqueId);
	List<ConcoursDto> concoursDtos = concoursService.findAll(example);
	if (concoursDtos != null) {
	    for (ConcoursDto dto : concoursDtos) {
		if (dto.getDatePublicationConcours() != null) {
		    concours.add(new SelectItem(dto.getId(), dto.getIntituleLatin()));
		}

	    }
	}

    }

    public void onConcoursSelected() {
	dossierTabActive = false;
	if (currentConcoursId != null) {
	    addEnabled = true;
	    concoursDto = new ConcoursDto();
	    concoursDto.setId(currentConcoursId);
	    concoursDto = concoursService.find(concoursDto);
	    searchCandidats();
	    renderEditPanel = false;
	} else {
	    addEnabled = false;
	}

    }

    private void searchCandidats() {
	final DossierCandidatDto dto = new DossierCandidatDto();
	dto.setConcoursId(currentConcoursId);
	candidatsDtos = new LazyDataModel<DossierCandidatDto>() {

	    private static final long serialVersionUID = 8943235766499326192L;

	    @Override
	    public List<DossierCandidatDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
		    Map<String, String> filters) {
		candidatsDtos.setRowCount(dossierCandidatService.countByExample(dto));
		Boolean descending = null;
		if (sortOrder != null) {
		    if (sortOrder.equals(SortOrder.DESCENDING)) {
			descending = Boolean.TRUE;
		    } else {
			descending = Boolean.FALSE;
		    }

		}
		return dossierCandidatService.findByExample(dto, sortField, pageSize, first, descending);
	    }

	};

	renderCandidatDataPanel = true;

    }

    public void onNew() {
	renderEditPanel = true;
	candidatEditable = true;
	renderIndividuDataPanel = false;
	renderIndividuSearchPanel = true;
	renderIndividuDetailPanel = false;
	saveEnabled = false;
	dossierTabActive = false;
	dto = new DossierCandidatDto();
	dto.setConcoursId(currentConcoursId);
	prenomIndividuRecherche = null;
	nomIndividuRecherche = null;
	currentTabViewDossierIndex = 0;

    }

    public void onPieceAFournirChecked(AjaxBehaviorEvent event) {
	Integer rang = (Integer) event.getComponent().getAttributes().get("rangPiece");
	SelectBooleanCheckbox checkbox = ((SelectBooleanCheckbox) event.getSource());
	PieceFournieDto pieceFournieDto = new PieceFournieDto();
	PieceConcoursDto pieceConcoursDto = concoursDto.getPieceConstitutiveDtos().get(rang);
	pieceFournieDto.setPieceConcoursDto(pieceConcoursDto);
	pieceFournieDto.setDossierCandidatDto(dto);
	if (checkbox.isSelected()) {
	    dto.addPieceFournieDto(pieceFournieDto);
	} else {
	    dto.removePieceFournieDto(pieceFournieDto);
	}
    }

    public void searchIndividu() {
	renderIndividuDataPanel = true;
	renderIndividuDetailPanel = false;
	final RefIndividuDto searchDto = new RefIndividuDto();
	searchDto.setNomLatin(nomIndividuRecherche);
	searchDto.setPrenomLatin(prenomIndividuRecherche);
	individuDtos = new LazyDataModel<RefIndividuDto>() {
	    private static final long serialVersionUID = 8943235766499326192L;

	    @Override
	    public List<RefIndividuDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
		    Map<String, String> filters) {
		Boolean descending = null;
		if (sortOrder != null) {
		    if (sortOrder.equals(SortOrder.DESCENDING)) {
			descending = Boolean.TRUE;
		    } else {
			descending = Boolean.FALSE;
		    }

		}
		individuDtos.setRowCount(individuService.countByExample(searchDto));
		List<RefIndividuDto> dtos = individuService.findByExample(searchDto, sortField, pageSize, first,
			descending);
		return dtos;
	    }
	};
    }

    public void onIndividuSelect(SelectEvent event) {
	RefIndividuDto dto = (RefIndividuDto) event.getObject();
	DossierCandidatDto exampleDto = new DossierCandidatDto();
	exampleDto.setConcoursId(currentConcoursId);
	exampleDto.setIndividu(dto);
	exampleDto = dossierCandidatService.find(exampleDto);
	renderIndividuDetailPanel = true;
	this.dto.setIndividu(dto);
	if (exampleDto != null) {
	    Utility.showWarningMessage("L'individu a déjà été inscrit au concours");
	    saveEnabled = false;
	    dossierTabActive = false;
	} else {
	    // TODO show detail individu panel
	    this.dto.setPieceFournieDtos(new ArrayList<PieceFournieDto>());
	    for (PieceConcoursDto pieceConcours : concoursDto.getPieceConstitutiveDtos()) {
		pieceConcours.setFournie(false);
	    }

	    saveEnabled = true;
	    dossierTabActive = true;
	}

    }

    public void onCandidatSelect(SelectEvent event) {
	dto = (DossierCandidatDto) event.getObject();
	renderEditPanel = true;
	candidatEditable = true;
	renderIndividuDataPanel = false;
	renderIndividuSearchPanel = false;
	renderIndividuDetailPanel = true;
	saveEnabled = true;
	dossierTabActive = true;
	currentTabViewDossierIndex = 0;
	for (PieceConcoursDto pieceConcours : concoursDto.getPieceConstitutiveDtos()) {
	    pieceConcours.setFournie(false);
	}
	for (PieceConcoursDto pieceConcours : concoursDto.getPieceConstitutiveDtos()) {
	    for (PieceFournieDto pieceFournieDto : dto.getPieceFournieDtos()) {
		if (pieceFournieDto.getPieceConcoursDto().getId().equals(pieceConcours.getId())) {
		    pieceConcours.setFournie(true);
		    break;
		}
	    }
	}

    }

    public void onSave() {
	if (this.dto.getIndividu() == null) {
	    Utility.showErrorMessage("Veuillez sélectionner un individu");
	    return;
	}
	if (!validateForm()) {
	    return;
	}
	for (PieceConcoursDto pieceConcours : concoursDto.getPieceConstitutiveDtos()) {
	    if (pieceConcours.isFournie()) {
		dto.addPieceConcours(pieceConcours);
	    } else {
		dto.removePieceConcours(pieceConcours);
	    }
	}
	this.dto = dossierCandidatService.save(dto);
	searchCandidats();
	Utility.showSuccessSaveMessage();
    }

    private boolean validateForm() {
	boolean valid = true;
	if (dto.getDateDepot().compareTo(concoursDto.getDatePublicationConcours()) < 0) {
	    Utility.showErrorMessage("Vérifier la date de dépôt  avec la date de publication du concours. ");
	    valid = false;
	}

	return valid;
    }

    public void setConcoursService(ConcoursService concoursService) {
	this.concoursService = concoursService;
    }

    public void setAnneeAcademiqueService(AnneeAcademiqueService anneeAcademiqueService) {
	this.anneeAcademiqueService = anneeAcademiqueService;
    }

    public void setDossierCandidatService(DossierCandidatService dossierCandidatService) {
	this.dossierCandidatService = dossierCandidatService;
    }

    public void setSessionBean(SessionBean sessionBean) {
	this.sessionBean = sessionBean;
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

    public boolean isAddEnabled() {
	return addEnabled;
    }

    public void setAddEnabled(boolean addEnabled) {
	this.addEnabled = addEnabled;
    }

    public DossierCandidatDto getDto() {
	return dto;
    }

    public void setDto(DossierCandidatDto dto) {
	this.dto = dto;
    }

    public List<SelectItem> getAnneeAcademiques() {
	return anneeAcademiques;
    }

    public List<SelectItem> getConcours() {
	return concours;
    }

    public void setRenderEditPanel(boolean renderEditPanel) {
	this.renderEditPanel = renderEditPanel;
    }

    public boolean isRenderEditPanel() {
	return renderEditPanel;
    }

    public ConcoursDto getConcoursDto() {
	return concoursDto;
    }

    public void setConcoursDto(ConcoursDto concoursDto) {
	this.concoursDto = concoursDto;
    }

    public void setConcours(List<SelectItem> concours) {
	this.concours = concours;
    }

    public boolean isCandidatEditable() {
	return candidatEditable;
    }

    public void setCandidatEditable(boolean candidatEditable) {
	this.candidatEditable = candidatEditable;
    }

    public LazyDataModel<DossierCandidatDto> getCandidatsDtos() {
	return candidatsDtos;
    }

    public void setCandidatsDtos(LazyDataModel<DossierCandidatDto> candidatsDtos) {
	this.candidatsDtos = candidatsDtos;
    }

    public boolean isRenderCandidatDataPanel() {
	return renderCandidatDataPanel;
    }

    public void setRenderCandidatDataPanel(boolean renderCandidatDataPanel) {
	this.renderCandidatDataPanel = renderCandidatDataPanel;
    }

    public boolean isRenderIndividuDataPanel() {
	return renderIndividuDataPanel;
    }

    public void setRenderIndividuDataPanel(boolean renderIndividuDataPanel) {
	this.renderIndividuDataPanel = renderIndividuDataPanel;
    }

    public LazyDataModel<RefIndividuDto> getIndividuDtos() {
	return individuDtos;
    }

    public void setIndividuDtos(LazyDataModel<RefIndividuDto> individuDtos) {
	this.individuDtos = individuDtos;
    }

    public int getCurrentTabViewDossierIndex() {
	return currentTabViewDossierIndex;
    }

    public void setCurrentTabViewDossierIndex(int currentTabViewDossierIndex) {
	this.currentTabViewDossierIndex = currentTabViewDossierIndex;
    }

    public void setIndividuService(RefIndividuService individuService) {
	this.individuService = individuService;
    }

    public boolean isRenderIndividuDetailPanel() {
	return renderIndividuDetailPanel;
    }

    public void setRenderIndividuDetailPanel(boolean renderIndividuDetailPanel) {
	this.renderIndividuDetailPanel = renderIndividuDetailPanel;
    }

    public boolean isRenderIndividuSearchPanel() {
	return renderIndividuSearchPanel;
    }

    public void setRenderIndividuSearchPanel(boolean renderIndividuSearchPanel) {
	this.renderIndividuSearchPanel = renderIndividuSearchPanel;
    }

    public boolean isSaveEnabled() {
	return saveEnabled;
    }

    public void setSaveEnabled(boolean saveEnabled) {
	this.saveEnabled = saveEnabled;
    }

    public boolean isDossierTabActive() {
	return dossierTabActive;
    }

    public void setDossierTabActive(boolean dossierTabActive) {
	this.dossierTabActive = dossierTabActive;
    }

    public String getNomIndividuRecherche() {
	return nomIndividuRecherche;
    }

    public void setNomIndividuRecherche(String nomIndividuRecherche) {
	this.nomIndividuRecherche = nomIndividuRecherche;
    }

    public String getPrenomIndividuRecherche() {
	return prenomIndividuRecherche;
    }

    public void setPrenomIndividuRecherche(String prenomIndividuRecherche) {
	this.prenomIndividuRecherche = prenomIndividuRecherche;
    }

}
