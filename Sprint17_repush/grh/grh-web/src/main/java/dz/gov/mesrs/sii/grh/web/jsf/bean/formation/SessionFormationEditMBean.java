package dz.gov.mesrs.sii.grh.web.jsf.bean.formation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.grh.business.model.dto.CycleFormationDto;
import dz.gov.mesrs.sii.grh.business.model.dto.FormateurSessionFormationDto;
import dz.gov.mesrs.sii.grh.business.model.dto.FormationCatalogueDto;
import dz.gov.mesrs.sii.grh.business.model.dto.SessionFormationDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefLieuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

/**
 * 
 * @author BELDI Jamel
 * 
 */
@ManagedBean(name = "sessionFormationEditMBean")
@ViewScoped
public class SessionFormationEditMBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String searchKeyWords;
	private SessionFormationDto sessionFormationDto;
	private List<SessionFormationDto> listeSessionFormation;
	private List<FormateurSessionFormationDto> listeFormateur;
	private List<SelectItem> listeCycles;
	private List<SelectItem> listFormationsCatalogue;
	private List<SelectItem> listeOrganismes;
	private List<SelectItem> listeLieux;
	private Integer selectedFormateurIndex;
	private CycleFormationDto cycleFormationDto;
	public SessionFormationEditMBean() {

	}

	@PostConstruct
	public void init() {
		searchKeyWords = "";
		initSelectItemLists();
		onSearchSession();
	}

	private void initSelectItemLists() {
		listeCycles = findListCycleFormationGroupedByProgramme();
		listFormationsCatalogue = findListFormationGroupedByCatalogue();
		listeOrganismes = findListeStructure(sessionBean.getIdEtablissement());
		listeLieux = findListeLieu(sessionBean.getIdEtablissement());
		
	}

	public void initDetail() {
		sessionFormationDto = null;
		listeFormateur=null;
		cycleFormationDto=null;

	}

	public void onNew() {
		sessionFormationDto = new SessionFormationDto();
		sessionFormationDto.setCycleFormationDto(new CycleFormationDto());
		sessionFormationDto.setFormationCatalogueDto(new FormationCatalogueDto());
		sessionFormationDto.setRefStructureDto(new RefStructureDto());
		sessionFormationDto.setRefLieuDto(new RefLieuDto());
		sessionFormationDto.setExterne(false);
		listeFormateur=null;
		cycleFormationDto=null;
		

	}

	public void onSearchSession() {
		try {
			loadlisteSessionFormation();
			initDetail();
		} catch (Exception e) {

		}
	}

	private void loadlisteSessionFormation() {
		listeSessionFormation = serviceLocator.getSessioFormationService().findAllSessionFormationCreesAndCycleCree(new SearchMode(), searchKeyWords); 
				//.findAllSessionFormationCrees( new SearchMode(), searchKeyWords); 
	}

	public void onSessionSelect(SelectEvent event) {
		sessionFormationDto = (SessionFormationDto) event.getObject();
		listeFormateur = sessionFormationDto.getFormateurSessionFormationDtos();
		findCycleFormationDto();
	}

	public void onTypeLieuChange(AjaxBehaviorEvent event) {
		if(sessionFormationDto.getExterne()!=null && sessionFormationDto.getExterne()==Boolean.TRUE ){
			sessionFormationDto.setRefLieuDto(null);
		}else{
			sessionFormationDto.setRefLieuDto(new RefLieuDto());
			sessionFormationDto.setLieuExterne(null);
		}

	}

	public void onIndividuSelect(SelectEvent event) {
		try {
			RefIndividuDto refIndividuDto = (RefIndividuDto) event.getObject();
			// Traitement Specifique
			FormateurSessionFormationDto formateur = new FormateurSessionFormationDto();
			formateur.setRefIndividuDto(refIndividuDto);
			addFormateur(formateur);
		} catch (Exception e) {

		}
	}
	public void addFormateur(FormateurSessionFormationDto formateur) {
		    formateur.setSessionFormationDto(sessionFormationDto);
			if (this.listeFormateur == null) {
				this.listeFormateur = new ArrayList<FormateurSessionFormationDto>();
			}
			this.listeFormateur.add(formateur);
	}

	public void onRemoveFormateur() {
		FormateurSessionFormationDto formateur = this.listeFormateur.get(selectedFormateurIndex);
		this.listeFormateur.remove(formateur);

	}

	
	
	public void onSave() {
		if (validateForm()) {
			sessionFormationDto.setFormateurSessionFormationDtos(this.listeFormateur);
			sessionFormationDto = serviceLocator.getSessioFormationService().saveSessionFormation(sessionFormationDto);
			loadlisteSessionFormation();
			CommonMessagesUtils.showSuccessSaveMessage();
		}
	}
	public void onDelete() {
			serviceLocator.getSessioFormationService().remove(sessionFormationDto);
			loadlisteSessionFormation();
			initDetail();
			CommonMessagesUtils.showSuccessDeleteMessage();
	}
	private boolean validateForm() {
		boolean result = true;
		if (serviceLocator.getRefJourOuvreService().isWeekendDay(
				sessionFormationDto.getDateDebut())
				|| serviceLocator.getRefJourOuvreService().isJourOuvre(
						sessionFormationDto.getDateDebut())) {
			GRHMessagesUtils
					.showErrorMessage("session_formation_date_debut_invalide");
			result = false;
		}
		if (serviceLocator.getRefJourOuvreService().isWeekendDay(
				sessionFormationDto.getDateFin())
				|| serviceLocator.getRefJourOuvreService().isJourOuvre(
						sessionFormationDto.getDateFin())) {
			GRHMessagesUtils
					.showErrorMessage("session_formation_date_fin_invalide");
			result = false;
		}
		if (sessionFormationDto.getDateDebut().after(
				sessionFormationDto.getDateFin())) {
			GRHMessagesUtils
					.showErrorMessage("session_formation_date_debut_fin_invalide");
			result = false;
		}
		
		if (cycleFormationDto.getDateDebut().after(sessionFormationDto.getDateDebut()
				)) {
			GRHMessagesUtils
					.showErrorMessage("session_formation_date_debut_cycle_date_debut_invalide");
			result = false;
		}
		
		if (sessionFormationDto.getDateFin().after(cycleFormationDto.getDateFin()
				)) {
			GRHMessagesUtils
					.showErrorMessage("session_formation_date_fin_cycle_date_fin_invalide");
			result = false;
		}
		return result;
	}

	public void onCycleChange(AjaxBehaviorEvent event) {
		findCycleFormationDto();

	}
	


	private void findCycleFormationDto() {
		if(sessionFormationDto.getCycleFormationDto().getId()!=null){
			cycleFormationDto = serviceLocator.getCycleFormationService().findById(sessionFormationDto.getCycleFormationDto().getId());
			}else {
				cycleFormationDto = null;	
			}

	}

	public String getSearchKeyWords() {
		return searchKeyWords;
	}

	public void setSearchKeyWords(String searchKeyWords) {
		this.searchKeyWords = searchKeyWords;
	}

	public SessionFormationDto getSessionFormationDto() {
		return sessionFormationDto;
	}

	public void setSessionFormationDto(SessionFormationDto sessionFormationDto) {
		this.sessionFormationDto = sessionFormationDto;
	}

	public List<SessionFormationDto> getListeSessionFormation() {
		return listeSessionFormation;
	}

	public void setListeSessionFormation(
			List<SessionFormationDto> listeSessionFormation) {
		this.listeSessionFormation = listeSessionFormation;
	}

	public List<SelectItem> getListeCycles() {
		return listeCycles;
	}

	public void setListeCycles(List<SelectItem> listeCycles) {
		this.listeCycles = listeCycles;
	}

	public List<SelectItem> getListFormationsCatalogue() {
		return listFormationsCatalogue;
	}

	public void setListFormationsCatalogue(List<SelectItem> listFormationsCatalogue) {
		this.listFormationsCatalogue = listFormationsCatalogue;
	}

	public List<SelectItem> getListeOrganismes() {
		return listeOrganismes;
	}

	public void setListeOrganismes(List<SelectItem> listeOrganismes) {
		this.listeOrganismes = listeOrganismes;
	}

	public List<SelectItem> getListeLieux() {
		return listeLieux;
	}

	public void setListeLieux(List<SelectItem> listeLieux) {
		this.listeLieux = listeLieux;
	}

	public List<FormateurSessionFormationDto> getListeFormateur() {
		return listeFormateur;
	}

	public void setListeFormateur(List<FormateurSessionFormationDto> listeFormateur) {
		this.listeFormateur = listeFormateur;
	}

	public Integer getSelectedFormateurIndex() {
		return selectedFormateurIndex;
	}

	public void setSelectedFormateurIndex(Integer selectedFormateurIndex) {
		this.selectedFormateurIndex = selectedFormateurIndex;
	}

	public CycleFormationDto getCycleFormationDto() {
		return cycleFormationDto;
	}

	public void setCycleFormationDto(CycleFormationDto cycleFormationDto) {
		this.cycleFormationDto = cycleFormationDto;
	}

	

	

}
