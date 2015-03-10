package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.concours;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.fve.business.model.dto.concours.ConcoursDto;
import dz.gov.mesrs.sii.fve.business.model.dto.concours.DossierCandidatDto;
import dz.gov.mesrs.sii.fve.business.model.dto.concours.ExamenConcoursDto;
import dz.gov.mesrs.sii.fve.business.model.dto.concours.ResultatExamenDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.service.concours.ConcoursService;
import dz.gov.mesrs.sii.fve.business.service.concours.DossierCandidatService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;

@ManagedBean(name = "resultatConcoursMBean")
@ViewScoped
public class ResultatConcoursMBean implements Serializable {

    private static final long serialVersionUID = 9096938781861890726L;

    private ConcoursDto concoursDto;
    private LazyDataModel<DossierCandidatDto> candidatDtos;
    private DossierCandidatDto selectedCandidatDto;
    private Long currentConcoursId;
    private Integer currentAnneeAcademiqueId;

    private List<SelectItem> anneeAcademiques;
    private List<SelectItem> concours;

    @ManagedProperty("#{anneeAcademiqueService}")
    private AnneeAcademiqueService anneeAcademiqueService;
    @ManagedProperty("#{concoursServiceImpl}")
    private ConcoursService concoursService;
    @ManagedProperty("#{dossierCandidatServiceImpl}")
    private DossierCandidatService candidatService;
    @ManagedProperty("#{sessionBeanFve}")
    private SessionBean sessionBean;

    private boolean displayCandidatDataTable;
    private boolean displayCandidatResultPanel;

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
	displayCandidatResultPanel = false;
	candidatDtos = null;
    }

    public void onConcoursSelected() {
	if (currentConcoursId == null) {
	    candidatDtos = null;
	    displayCandidatDataTable = false;
	    displayCandidatResultPanel = false;
	} else {
	    displayCandidatDataTable = true;
	    displayCandidatResultPanel = false;
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
	}

    }

    public void onCandidatSelected(SelectEvent event) {
	displayCandidatResultPanel = true;
	selectedCandidatDto = (DossierCandidatDto) event.getObject();
	if (selectedCandidatDto.getResultatExamenDtos() == null
		|| selectedCandidatDto.getResultatExamenDtos().isEmpty()) {
	    for (ExamenConcoursDto examenConcoursDto : concoursDto.getExamenConcoursDtos()) {
		ResultatExamenDto resultat = new ResultatExamenDto(examenConcoursDto);
		selectedCandidatDto.addResultatExamenDto(resultat);
	    }

	}
    }

    public void onSaveResultatCandidat() {
	selectedCandidatDto = candidatService.enregisterResultat(selectedCandidatDto);
	Utility.showSuccessSaveMessage();
	onConcoursSelected();
	displayCandidatResultPanel = true;
    }

    public void onRowEdit(RowEditEvent event) {
	ResultatExamenDto dto = (ResultatExamenDto) event.getObject();
	DossierCandidatDto candidat = dto.getDossierCandidatDto();
	candidatService.calculMoyenne(concoursDto, candidat);
    }

    public ConcoursDto getConcoursDto() {
	return concoursDto;
    }

    public void setConcoursDto(ConcoursDto concoursDto) {
	this.concoursDto = concoursDto;
    }

    public void setConcoursService(ConcoursService concoursService) {
	this.concoursService = concoursService;
    }

    public void setCandidatService(DossierCandidatService candidatService) {
	this.candidatService = candidatService;
    }

    public void setSessionBean(SessionBean sessionBean) {
	this.sessionBean = sessionBean;
    }

    public boolean isDisplayCandidatDataTable() {
	return displayCandidatDataTable;
    }

    public void setDisplayCandidatDataTable(boolean displayCandidatDataTable) {
	this.displayCandidatDataTable = displayCandidatDataTable;
    }

    public void setAnneeAcademiques(List<SelectItem> anneeAcademiques) {
	this.anneeAcademiques = anneeAcademiques;
    }

    public List<SelectItem> getAnneeAcademiques() {
	return anneeAcademiques;
    }

    public void setConcours(List<SelectItem> concours) {
	this.concours = concours;
    }

    public List<SelectItem> getConcours() {
	return concours;
    }

    public void setAnneeAcademiqueService(AnneeAcademiqueService anneeAcademiqueService) {
	this.anneeAcademiqueService = anneeAcademiqueService;
    }

    public void setCandidatDtos(LazyDataModel<DossierCandidatDto> candidatDtos) {
	this.candidatDtos = candidatDtos;
    }

    public LazyDataModel<DossierCandidatDto> getCandidatDtos() {
	return candidatDtos;
    }

    public DossierCandidatDto getSelectedCandidatDto() {
	return selectedCandidatDto;
    }

    public void setSelectedCandidatDto(DossierCandidatDto selectedCandidatDto) {
	this.selectedCandidatDto = selectedCandidatDto;
    }

    public boolean isDisplayCandidatResultPanel() {
	return displayCandidatResultPanel;
    }

    public void setDisplayCandidatResultPanel(boolean displayCandidatResultPanel) {
	this.displayCandidatResultPanel = displayCandidatResultPanel;
    }

    public Long getCurrentConcoursId() {
	return currentConcoursId;
    }

    public void setCurrentConcoursId(Long currentConcoursId) {
	this.currentConcoursId = currentConcoursId;
    }

    public Integer getCurrentAnneeAcademiqueId() {
	return currentAnneeAcademiqueId;
    }

    public void setCurrentAnneeAcademiqueId(Integer currentAnneeAcademiqueId) {
	this.currentAnneeAcademiqueId = currentAnneeAcademiqueId;
    }

}
