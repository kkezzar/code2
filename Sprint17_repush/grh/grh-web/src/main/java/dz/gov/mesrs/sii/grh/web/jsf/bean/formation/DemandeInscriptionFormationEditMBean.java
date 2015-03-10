package dz.gov.mesrs.sii.grh.web.jsf.bean.formation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.web.util.SearchModeMapper;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.InscriptionSessionFormationDto;
import dz.gov.mesrs.sii.grh.business.model.dto.SessionFormationDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;

/**
 * 
 * @author BELDI Jamel
 * 
 */
@ManagedBean(name = "demandeInscriptionFormationEditMBean")
@ViewScoped
public class DemandeInscriptionFormationEditMBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String searchKeyWords;
	private SessionFormationDto sessionFormationDto;
	private List<SessionFormationDto> listeSessionFormation;
	private List<InscriptionSessionFormationDto> listeInscriptions;
	private DossierEmployeDto employeSearchDto;
	private LazyDataModel<DossierEmployeDto> dossierEmployeModel;
	private InscriptionSessionFormationDto inscriptionSessionFormationDto;
	public DemandeInscriptionFormationEditMBean() {

	}

	@PostConstruct
	public void init() {
		searchKeyWords = "";
		employeSearchDto = new DossierEmployeDto();
		initSelectItemLists();
		onSearchSession();
	}

	private void initSelectItemLists() {
		
		
	}

	public void initDetail() {
		sessionFormationDto = null;
		listeInscriptions=null;
	}

	

	public void onSearchSession() {
		try {
			loadlisteSessionFormation();
			initDetail();
		} catch (Exception e) {

		}
	}

	private void loadlisteSessionFormation() {
		listeSessionFormation = serviceLocator.getSessioFormationService().findAllSessionFormationCreesAndCyclePublie(new SearchMode(), searchKeyWords); 
				//.findAllSessionFormationCrees( new SearchMode(), searchKeyWords); 
	}

	public void onSessionSelect(SelectEvent event) {
		this.sessionFormationDto = (SessionFormationDto) event.getObject();
		this.listeInscriptions = this.sessionFormationDto.getInscriptionSessionFormationDtos();
		
		
		
	}

	public void onSearchDossierEmploye() {
		loaddossierEmployeModel();
	}

	private void loaddossierEmployeModel() {
		dossierEmployeModel = new LazyDataModel<DossierEmployeDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(DossierEmployeDto dossierEmployeDto) {
				return dossierEmployeDto.getId();
			}

			@Override
			public List<DossierEmployeDto> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				SearchMode searchMode =new SearchMode();
				dossierEmployeModel.setRowCount(serviceLocator
						.getDossierEmployeService().countAllByExample(
								employeSearchDto, searchMode));
				searchMode = SearchModeMapper.map(first, pageSize,
						sortField, sortOrder);
				List<DossierEmployeDto> dtos = serviceLocator
						.getDossierEmployeService().findAllByExample(
								employeSearchDto, searchMode);
				return dtos;
			}
		};


	}
	public void onRowSelect(SelectEvent event) throws Exception {
		DossierEmployeDto dossierEmployeDto = (DossierEmployeDto) event.getObject();
		inscriptionSessionFormationDto.setDossierEmployeDto(dossierEmployeDto);	
	}
	
	public void onAddDemandeInscription(){
		inscriptionSessionFormationDto = new InscriptionSessionFormationDto();
		inscriptionSessionFormationDto.setSessionFormationDto(sessionFormationDto);
	}
	
	public void onDemandeInscriptionSelect(SelectEvent event){
		this.inscriptionSessionFormationDto = (InscriptionSessionFormationDto) event.getObject();
	}
	
	public void onSaveDemandeInscription() {
		if(sessionFormationDto.getDateDebut().after(inscriptionSessionFormationDto.getDateDemande())){
		 inscriptionSessionFormationDto.setSessionFormationDto(sessionFormationDto);
		 if(inscriptionSessionFormationDto.getId()==null){
			if (this.listeInscriptions == null) {
				this.listeInscriptions = new ArrayList<InscriptionSessionFormationDto>();
			}
			inscriptionSessionFormationDto.setId(this.listeInscriptions == null?1:Long.valueOf(this.listeInscriptions.size()+1));
			this.listeInscriptions.add(inscriptionSessionFormationDto);
		 }
			RequestContext.getCurrentInstance().execute("PF('demandeInscriptionDialog').hide()");
		}else{
			GRHMessagesUtils
			.showErrorMessage("demande_inscription_session_formation_date_demande_invalide");
		}
	}

	public void onRemoveDemandeInscription() {
		this.listeInscriptions.remove(inscriptionSessionFormationDto);
	}
	
	
	
	public void onSave() {
		if (validateForm()) {	
			sessionFormationDto.setInscriptionSessionFormationDtos(this.listeInscriptions);
			sessionFormationDto = serviceLocator.getSessioFormationService().saveDemandesInsciption(sessionFormationDto);
			loadlisteSessionFormation();
			initDetail();
			CommonMessagesUtils.showSuccessSaveMessage();
		}
	}
	
	private boolean validateForm() {
		boolean result = true;
		
		return result;
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

	public List<InscriptionSessionFormationDto> getListeInscriptions() {
		return listeInscriptions;
	}

	public void setListeInscriptions(
			List<InscriptionSessionFormationDto> listeInscriptions) {
		this.listeInscriptions = listeInscriptions;
	}

	public DossierEmployeDto getEmployeSearchDto() {
		return employeSearchDto;
	}

	public void setEmployeSearchDto(DossierEmployeDto employeSearchDto) {
		this.employeSearchDto = employeSearchDto;
	}

	public LazyDataModel<DossierEmployeDto> getDossierEmployeModel() {
		return dossierEmployeModel;
	}

	public void setDossierEmployeModel(
			LazyDataModel<DossierEmployeDto> dossierEmployeModel) {
		this.dossierEmployeModel = dossierEmployeModel;
	}

	public InscriptionSessionFormationDto getInscriptionSessionFormationDto() {
		return inscriptionSessionFormationDto;
	}

	public void setInscriptionSessionFormationDto(
			InscriptionSessionFormationDto inscriptionSessionFormationDto) {
		this.inscriptionSessionFormationDto = inscriptionSessionFormationDto;
	}

	

	

}
