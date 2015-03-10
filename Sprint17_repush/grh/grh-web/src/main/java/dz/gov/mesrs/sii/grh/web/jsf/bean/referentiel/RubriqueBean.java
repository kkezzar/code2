package dz.gov.mesrs.sii.grh.web.jsf.bean.referentiel;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.grh.business.model.dto.EnfantDto;
import dz.gov.mesrs.sii.grh.business.model.dto.FiliereDto;
import dz.gov.mesrs.sii.grh.business.model.dto.RubriqueDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;


/**
 * @author BELBRIK Oualid  on : 11 octob 2014  14:41:09
 */
//@ManagedBean(name = "rubriqueBean")
//@ViewScoped
//public class RubriqueBean implements Serializable {
	
	@ManagedBean(name= "rubriqueBean")
	@ViewScoped
	public class RubriqueBean extends BaseBean {

	/**
	 * serialVersionUID
	 * 
	 * @author BELBRIK Oualid on : 15 nov. 2014 10:03:10
	 */
	private static final long serialVersionUID = 1L;
	
	private RubriqueDto rubriqueDto;
	private List<RubriqueDto> rubriqueList;
	private RubriqueDto searchDto;
	private FiliereDto filiereDto;
	private List<FiliereDto> filiereList;
	private FiliereDto searchFiliereDto;
	public RubriqueDto newRubrique;
	public RubriqueDto editRubrique;
	public List<RubriqueDto> listRubriques ;
	private boolean showView=false;
	private List<SelectItem> lsitIemsRubrique;
	private boolean newElement=false;
	private List<SelectItem> listeNcRubriques;
	private List<SelectItem> listeNcTypeRubriques;
	private List<SelectItem> listeNcPeriodecite;
	private List<SelectItem> listeNcFiliere;
	private Integer selectedFiliereIndex;
	private List<FiliereDto> filieresListe;
	private List<RubriqueDto> filteredRubrique;
	private boolean entityChange;

	

	/**
	 * [rubriqueBean.rubriqueBean()] Constructor
	 * 
	 * @author BELBRIK Oualid on : 15 octob. 2014 10:03:05
	 */
	public RubriqueBean() {
		super();
	}

	@PostConstruct
	public void init() {

		searchDto = new RubriqueDto();
		initDetail2();
		searchFiliereDto = new FiliereDto();
		filiereDto = new FiliereDto();
		rubriqueDto = null;	
		listRubriques = new ArrayList<RubriqueDto>();
		findListRubriques();
		
		
	}
	
	public void findListRubriques() {

		listRubriques = new ArrayList<RubriqueDto>();
		try {
			listRubriques = this.serviceLocator.getRubriqueService().findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage();
		rubriqueDto = ((RubriqueDto) event.getObject());
		filieresListe = rubriqueDto.getFiliereDtos();
		try{

		showView=true;
		listeNcRubriques = findNcRubriqueList();
		listeNcTypeRubriques = findNcTypeRubriqueList();
		listeNcPeriodecite = findNcPriodeciteList();
		listeNcFiliere=findFiliereProferssionnelleList();
		
		if (rubriqueDto.getTauxOuMantant().equals(true)) {
		setEntityChange(true);
		}else {
		setEntityChange(false);
		}
		} catch (Exception e) {
			
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}
	
	
	public void onSave() {
		beforeSave();
		rubriqueDto = this.serviceLocator.getRubriqueService().save(rubriqueDto);
		afterSave();
		findListRubriques();
		CommonMessagesUtils.showSuccessSaveMessage();
	}


	
	public void addFiliere() {
		FiliereDto filiereDto = new FiliereDto();
		if (this.filieresListe == null) {
			this.filieresListe = new ArrayList<FiliereDto>();
		}
		filiereDto.setRubriqueDto(this.rubriqueDto);
		this.filieresListe.add(filiereDto);
	}



	public void removeFiliere() {
		FiliereDto filiereDto = this.filieresListe.get(selectedFiliereIndex);
		this.filieresListe.remove(filiereDto);

	}

	
	
	private void beforeSave() {
		if (rubriqueDto.getNomenclatureByRubriqueDto().getId() == null
				|| rubriqueDto.getNomenclatureByRubriqueDto().getId() == 0) {
			rubriqueDto.setNomenclatureByRubriqueDto(null);
		}
		if (rubriqueDto.getNomenclatureByTypeRubriqueDto().getId() == null
				|| rubriqueDto.getNomenclatureByTypeRubriqueDto().getId() == 0) {
			rubriqueDto.setNomenclatureByTypeRubriqueDto(null);
		}
		if (rubriqueDto.getNomenclatureByPeriodiciteDto().getId() == null
				|| rubriqueDto.getNomenclatureByPeriodiciteDto().getId() == 0) {
			rubriqueDto.setNomenclatureByPeriodiciteDto(null);
		}
		
		if (filieresListe != null) {
			List<FiliereDto> filiereDtos = new ArrayList<FiliereDto>();
			for (FiliereDto filiereDto : filieresListe) {
				if (filiereDto.getIdentifiant() != null && filiereDto.getNomenclatureByFiliereDto().getId() != null ) {
					
					filiereDtos.add(filiereDto);
				}

			}
			rubriqueDto.setFiliereDtos(filiereDtos);
		}
		
	}
	
	private void afterSave() {
		filieresListe = rubriqueDto.getFiliereDtos();
		findListRubriques();

	}
	

	

	/**
	 * [rubriqueValidateBean.rubriqueList] Getter 
	 * @author BELBRIK Oualid on : 10 ao�t 2014  13:53:10
	 * @return the rubriqueList
	 */
	public List<RubriqueDto> getRubriqueList() {
		return rubriqueList;
	}

	/**
	 * [rubriqueValidateBean.rubriqueList] Setter 
	 * @author BELBRIK Oualid on : 10 ao�t 2014  13:53:10
	 * @param rubriqueList the rubriqueList to set
	 */
	public void setRubriqueList(
			List<RubriqueDto> rubriqueList) {
		this.rubriqueList = rubriqueList;
	}

	

	/**
	 * [rubriqueConsultBean.searchDto] Getter 
	 * @author BELBRIK Oualid on : 13 ao�t 2014  11:21:48
	 * @return the searchDto
	 */
	public RubriqueDto getSearchDto() {
		return searchDto;
	}

	/**
	 * [rubriqueConsultBean.searchDto] Setter 
	 * @author BELBRIK Oualid on : 13 ao�t 2014  11:21:48
	 * @param searchDto the searchDto to set
	 */
	public void setSearchDto(RubriqueDto searchDto) {
		this.searchDto = searchDto;
	}

	
	/**
	 * 
	 * [rubriqueStatisticBean.ofChanged] Method
	 * 
	 * @author BELBRIK Oualid on : 14 ao�t 2014 12:34:36
	 */
	public void ofChanged() {

	}

	/**
	 * [rubriqueGererBean.rubriqueDto] Getter 
	 * @author BELBRIK Oualid on : 9 oct. 2014  16:02:43
	 * @return the rubriqueDto
	 */
	public RubriqueDto getRubriqueDto() {
		return rubriqueDto;
	}

	/**
	 * [rubriqueGererBean.rubriqueDto] Setter 
	 * @author BELBRIK Oualid on : 9 oct. 2014  16:02:43
	 * @param rubriqueDto the rubriqueDto to set
	 */
	public void setRubriqueDto(RubriqueDto rubriqueDto) {
		this.rubriqueDto = rubriqueDto;
	}

	
	
	public void saveRubrique() {
		rubriqueDto = this.serviceLocator.getRubriqueService().save(rubriqueDto);
		CommonMessagesUtils.showSuccessSaveMessage();
	}
	
	
	public void saveFiliere() {
		filiereDto = this.serviceLocator.getFiliereService().save(filiereDto);
		CommonMessagesUtils.showSuccessSaveMessage();
	}


/**
 * 
 * [filiereConsultBean.initDetail] Method 
 * @author BELBRIK Oualid  on : 13 ao�t 2014  17:01:17
 */
	private void initDetail2(){
		filiereDto = null;
	}
	
		
	
	public void onRowSelect2(SelectEvent event) {
		FacesMessage msg = new FacesMessage();
		
		FiliereDto _Filiere = ((FiliereDto) event.getObject());
		
		try{
		
		
		} catch (Exception e) {
			
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}


	/**
	 * [filiereValidateBean.filiereList] Getter 
	 * @author BELBRIK Oualid on : 10 ao�t 2014  13:53:10
	 * @return the filiereList
	 */
	public List<FiliereDto> getFiliereList() {
		return filiereList;
	}

	/**
	 * [filiereValidateBean.filiereList] Setter 
	 * @author BELBRIK Oualid on : 10 ao�t 2014  13:53:10
	 * @param filiereList the filiereList to set
	 */
	public void setFiliereList(
			List<FiliereDto> filiereList) {
		this.filiereList = filiereList;
	}

	

	/**
	 * [filiereConsultBean.searchFiliereDto] Getter 
	 * @author BELBRIK Oualid on : 13 ao�t 2014  11:21:48
	 * @return the searchDto
	 */
	public FiliereDto getSearchFiliereDto() {
		return searchFiliereDto;
	}

	/**
	 * [filiereConsultBean.searchFiliereDto] Setter 
	 * @author BELBRIK Oualid on : 13 ao�t 2014  11:21:48
	 * @param searchFiliereDto the searchFiliereDto to set
	 */
	public void setSearchFiliereDto(FiliereDto searchFiliereDto) {
		this.searchFiliereDto = searchFiliereDto;
	}

	

	/**
	 * [filiereGererBean.filiereDto] Getter 
	 * @author BELBRIK Oualid on : 9 oct. 2014  16:02:43
	 * @return the filiereDto
	 */
	public FiliereDto getFiliereDto() {
		return filiereDto;
	}

	/**
	 * [filiereGererBean.filiereDto] Setter 
	 * @author BELBRIK Oualid on : 9 oct. 2014  16:02:43
	 * @param filiereDto the filiereDto to set
	 */
	public void setFiliereDto(FiliereDto filiereDto) {
		this.filiereDto = filiereDto;
	}
	


	/**
	 * [RubriqueBean.newRubrique] Getter 
	 * @author BELBRIK Oualid on : 20 nov. 2014  17:19:40
	 * @return the newRubrique
	 */
	public RubriqueDto getNewRubrique() {
		return newRubrique;
	}

	/**
	 * [RubriqueBean.newRubrique] Setter 
	 * @author BELBRIK Oualid on : 20 nov. 2014  17:19:40
	 * @param newRubrique the newRubrique to set
	 */
	public void setNewRubrique(RubriqueDto newRubrique) {
		this.newRubrique = newRubrique;
	}

	/**
	 * [RubriqueBean.editRubrique] Getter 
	 * @author BELBRIK Oualid on : 20 nov. 2014  17:19:40
	 * @return the editRubrique
	 */
	public RubriqueDto getEditRubrique() {
		return editRubrique;
	}

	/**
	 * [RubriqueBean.editRubrique] Setter 
	 * @author BELBRIK Oualid on : 20 nov. 2014  17:19:40
	 * @param editRubrique the editRubrique to set
	 */
	public void setEditRubrique(RubriqueDto editRubrique) {
		this.editRubrique = editRubrique;
	}

	/**
	 * [RubriqueBean.listRubriques] Getter 
	 * @author BELBRIK Oualid on : 20 nov. 2014  17:19:40
	 * @return the listRubriques
	 */
	public List<RubriqueDto> getListRubriques() {
		return listRubriques;
	}

	/**
	 * [RubriqueBean.listRubriques] Setter 
	 * @author BELBRIK Oualid on : 20 nov. 2014  17:19:40
	 * @param listRubriques the listRubriques to set
	 */
	public void setListRubriques(List<RubriqueDto> listRubriques) {
		this.listRubriques = listRubriques;
	}

	/**
	 * [RubriqueBean.lsitIemsRubrique] Getter 
	 * @author BELBRIK Oualid on : 20 nov. 2014  17:19:40
	 * @return the lsitIemsRubrique
	 */
	public List<SelectItem> getLsitIemsRubrique() {
		return lsitIemsRubrique;
	}

	/**
	 * [RubriqueBean.lsitIemsRubrique] Setter 
	 * @author BELBRIK Oualid on : 20 nov. 2014  17:19:40
	 * @param lsitIemsRubrique the lsitIemsRubrique to set
	 */
	public void setLsitIemsRubrique(List<SelectItem> lsitIemsRubrique) {
		this.lsitIemsRubrique = lsitIemsRubrique;
	}

	/**
	 * [RubriqueBean.newElement] Getter 
	 * @author BELBRIK Oualid on : 20 nov. 2014  17:19:40
	 * @return the newElement
	 */
	public boolean isNewElement() {
		return newElement;
	}

	/**
	 * [RubriqueBean.newElement] Setter 
	 * @author BELBRIK Oualid on : 20 nov. 2014  17:19:40
	 * @param newElement the newElement to set
	 */
	public void setNewElement(boolean newElement) {
		this.newElement = newElement;
	}

	/**
	 * [RubriqueBean.listeNcRubriques] Getter 
	 * @author BELBRIK Oualid on : 20 nov. 2014  17:19:40
	 * @return the listeNcRubriques
	 */
	public List<SelectItem> getListeNcRubriques() {
		return listeNcRubriques;
	}

	/**
	 * [RubriqueBean.listeNcRubriques] Setter 
	 * @author BELBRIK Oualid on : 20 nov. 2014  17:19:40
	 * @param listeNcRubriques the listeNcRubriques to set
	 */
	public void setListeNcRubriques(List<SelectItem> listeNcRubriques) {
		this.listeNcRubriques = listeNcRubriques;
	}

	/**
	 * [RubriqueBean.listeNcTypeRubriques] Getter 
	 * @author BELBRIK Oualid on : 20 nov. 2014  17:19:40
	 * @return the listeNcTypeRubriques
	 */
	public List<SelectItem> getListeNcTypeRubriques() {
		return listeNcTypeRubriques;
	}

	/**
	 * [RubriqueBean.listeNcTypeRubriques] Setter 
	 * @author BELBRIK Oualid on : 20 nov. 2014  17:19:40
	 * @param listeNcTypeRubriques the listeNcTypeRubriques to set
	 */
	public void setListeNcTypeRubriques(List<SelectItem> listeNcTypeRubriques) {
		this.listeNcTypeRubriques = listeNcTypeRubriques;
	}

	/**
	 * [RubriqueBean.listeNcPeriodecite] Getter 
	 * @author BELBRIK Oualid on : 20 nov. 2014  17:19:40
	 * @return the listeNcPeriodecite
	 */
	public List<SelectItem> getListeNcPeriodecite() {
		return listeNcPeriodecite;
	}

	/**
	 * [RubriqueBean.listeNcPeriodecite] Setter 
	 * @author BELBRIK Oualid on : 20 nov. 2014  17:19:40
	 * @param listeNcPeriodecite the listeNcPeriodecite to set
	 */
	public void setListeNcPeriodecite(List<SelectItem> listeNcPeriodecite) {
		this.listeNcPeriodecite = listeNcPeriodecite;
	}

	/**
	 * [RubriqueBean.listeNcFiliere] Getter 
	 * @author BELBRIK Oualid on : 20 nov. 2014  17:19:40
	 * @return the listeNcFiliere
	 */
	public List<SelectItem> getListeNcFiliere() {
		return listeNcFiliere;
	}

	/**
	 * [RubriqueBean.listeNcFiliere] Setter 
	 * @author BELBRIK Oualid on : 20 nov. 2014  17:19:40
	 * @param listeNcFiliere the listeNcFiliere to set
	 */
	public void setListeNcFiliere(List<SelectItem> listeNcFiliere) {
		this.listeNcFiliere = listeNcFiliere;
	}

	/**
	 * [RubriqueBean.filieresListe] Getter 
	 * @author BELBRIK Oualid on : 20 nov. 2014  17:19:40
	 * @return the filieresListe
	 */
	public List<FiliereDto> getFilieresListe() {
		return filieresListe;
	}

	/**
	 * [RubriqueBean.filieresListe] Setter 
	 * @author BELBRIK Oualid on : 20 nov. 2014  17:19:40
	 * @param filieresListe the filieresListe to set
	 */
	public void setFilieresListe(List<FiliereDto> filieresListe) {
		this.filieresListe = filieresListe;
	}

	public boolean isShowView() {
		return showView;
	}

	public void setShowView(boolean showView) {
		this.showView = showView;
	}

	public Integer getSelectedFiliereIndex() {
		return selectedFiliereIndex;
	}

	public void setSelectedFiliereIndex(Integer selectedFiliereIndex) {
		this.selectedFiliereIndex = selectedFiliereIndex;
	}
	
	public void addNewRubrique() {
		
		editRubrique = new RubriqueDto();
		showView=true;
		listeNcRubriques = findNcRubriqueList();
		listeNcTypeRubriques = findNcTypeRubriqueList();
		listeNcPeriodecite = findNcPriodeciteList();
		this.rubriqueDto = null;
		rubriqueDto = new RubriqueDto();
		setEntityChange(true);
	}
	
	
	
	
	public void removeRubrique() {
				
		this.serviceLocator.getRubriqueService().remove(rubriqueDto);
		findListRubriques();
		CommonMessagesUtils.showSuccessDeleteMessage();

	}

	/**
	 * [RubriqueBean.filteredRubrique] Getter 
	 * @author BELBRIK Oualid on : 24 nov. 2014  16:26:29
	 * @return the filteredRubrique
	 */
	public List<RubriqueDto> getFilteredRubrique() {
		return filteredRubrique;
	}

	/**
	 * [RubriqueBean.filteredRubrique] Setter 
	 * @author BELBRIK Oualid on : 24 nov. 2014  16:26:29
	 * @param filteredRubrique the filteredRubrique to set
	 */
	public void setFilteredRubrique(List<RubriqueDto> filteredRubrique) {
		this.filteredRubrique = filteredRubrique;
	}
	
	public void entityChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			setEntityChange(true);
		}
	}

	public boolean isEntityChange() {
		return entityChange;
	}

	public void setEntityChange(boolean entityChange) {
		this.entityChange = entityChange;
	}
	
}
