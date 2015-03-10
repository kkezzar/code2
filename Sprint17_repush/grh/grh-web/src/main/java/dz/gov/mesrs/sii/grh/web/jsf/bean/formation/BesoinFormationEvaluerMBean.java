package dz.gov.mesrs.sii.grh.web.jsf.bean.formation;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.grh.business.model.dto.BesoinFormationDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DetailBesoinFormationDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

/**
 * 
 * @author BELDI Jamel
 * 
 */
@ManagedBean(name = "besoinFormationEvaluerMBean")
@ViewScoped
public class BesoinFormationEvaluerMBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String searchKeyWords;
	private BesoinFormationDto besoinFormationDto;
	private List<BesoinFormationDto> listeBesoinFormation;
	
	private boolean renderEmploye;
	private boolean renderStrucrure;
	private boolean renderGroupe;
	List<DetailBesoinFormationDto> listFormationsStructure;
	List<DetailBesoinFormationDto> listFormationsGroupe;
	List<DetailBesoinFormationDto> listFormationsEmploye;
	
	

	public BesoinFormationEvaluerMBean() {

	}

	@PostConstruct
	public void init() {
		searchKeyWords = "";
		
		initSelectItemLists();
		onSearchBesoin();
	}

	private void initSelectItemLists() {
		
	}

	public void initDetail() {
		besoinFormationDto = null;

	}

	

	public void onSearchBesoin() {
		try {
			loadlisteBesoinFormation();
			initDetail();
		} catch (Exception e) {

		}
	}

	private void loadlisteBesoinFormation() {
		listeBesoinFormation = serviceLocator.getBesoinFormationService().findAllBesoinFormationCrees(sessionBean
				.getIdEtablissement(), new SearchMode(), searchKeyWords);
			
	}

	public void onBesoinSelect(SelectEvent event) {
		besoinFormationDto = (BesoinFormationDto) event.getObject();
		verifyTypeBesoin();
		if (renderStrucrure) {
			listFormationsStructure = besoinFormationDto
					.getDetailBesoinFormationDtos();
		} else if (renderGroupe) {
			listFormationsGroupe = besoinFormationDto
					.getDetailBesoinFormationDtos();
		} else if (renderEmploye) {
			listFormationsEmploye = besoinFormationDto
					.getDetailBesoinFormationDtos();
		}

	}

	

	
	
	

	public void onSave() {
		if(validateForm()){
		if (renderStrucrure) {
			besoinFormationDto.setDetailBesoinFormationDtos(listFormationsStructure);
		} else if (renderGroupe) {
			besoinFormationDto.setDetailBesoinFormationDtos(listFormationsGroupe);
		} else if (renderEmploye) {
			besoinFormationDto.setDetailBesoinFormationDtos(listFormationsEmploye);
		}
		
		besoinFormationDto = serviceLocator.getBesoinFormationService().saveEvaluationBesoinFormation(besoinFormationDto);
		loadlisteBesoinFormation();
		initDetail();
		CommonMessagesUtils.showSuccessSaveMessage();
		}
	}
	
	
	
	
	private boolean validateForm(){
		boolean result = true;
		if ( serviceLocator.getRefJourOuvreService().isWeekendDay(
				besoinFormationDto.getDateEvaluation())
				|| serviceLocator.getRefJourOuvreService().isJourOuvre(
						besoinFormationDto.getDateEvaluation())) {
			GRHMessagesUtils
					.showErrorMessage("evaluation_besoin_formation_date_evaluation_invalide");
			result = false;
		}
		
		if(besoinFormationDto.getDateFin().after(besoinFormationDto.getDateEvaluation())){
			GRHMessagesUtils
			.showErrorMessage("evaluation_besoin_formation_date_evaluation_fin_invalide");
	     result = false;
		}
		return result;
	}
	
	
	
	private void verifyTypeBesoin() {
		NomenclatureDto typeBesoin = besoinFormationDto.getNomenclatureDto();
				
		if (typeBesoin == null) {
			renderStrucrure = false;
			renderGroupe = false;
			renderEmploye = false;
		} else if (typeBesoin.getCode().equals(
				UtilConstant.NC_GRH_BESOIN_STRUCTURE_VALUE)) {
			renderStrucrure = true;
			renderGroupe = false;
			renderEmploye = false;
		} else if (typeBesoin.getCode().equals(
				UtilConstant.NC_GRH_BESOIN_GROUPE_VALUE)) {
			renderStrucrure = false;
			renderGroupe = true;
			renderEmploye = false;

		} else if (typeBesoin.getCode().equals(
				UtilConstant.NC_GRH_BESOIN_INDIVIDUEL_VALUE)) {
			renderStrucrure = false;
			renderGroupe = false;
			renderEmploye = true;

		}
	}

	

	public String getSearchKeyWords() {
		return searchKeyWords;
	}

	public void setSearchKeyWords(String searchKeyWords) {
		this.searchKeyWords = searchKeyWords;
	}

	public BesoinFormationDto getBesoinFormationDto() {
		return besoinFormationDto;
	}

	public void setBesoinFormationDto(BesoinFormationDto besoinFormationDto) {
		this.besoinFormationDto = besoinFormationDto;
	}

	public List<BesoinFormationDto> getListeBesoinFormation() {
		return listeBesoinFormation;
	}

	public void setListeBesoinFormation(
			List<BesoinFormationDto> listeBesoinFormation) {
		this.listeBesoinFormation = listeBesoinFormation;
	}

	

	public boolean isRenderEmploye() {
		return renderEmploye;
	}

	public void setRenderEmploye(boolean renderEmploye) {
		this.renderEmploye = renderEmploye;
	}

	public boolean isRenderStrucrure() {
		return renderStrucrure;
	}

	public void setRenderStrucrure(boolean renderStrucrure) {
		this.renderStrucrure = renderStrucrure;
	}

	public boolean isRenderGroupe() {
		return renderGroupe;
	}

	public void setRenderGroupe(boolean renderGroupe) {
		this.renderGroupe = renderGroupe;
	}

	public List<DetailBesoinFormationDto> getListFormationsStructure() {
		return listFormationsStructure;
	}

	public void setListFormationsStructure(
			List<DetailBesoinFormationDto> listFormationsStructure) {
		this.listFormationsStructure = listFormationsStructure;
	}

	public List<DetailBesoinFormationDto> getListFormationsGroupe() {
		return listFormationsGroupe;
	}

	public void setListFormationsGroupe(
			List<DetailBesoinFormationDto> listFormationsGroupe) {
		this.listFormationsGroupe = listFormationsGroupe;
	}

	public List<DetailBesoinFormationDto> getListFormationsEmploye() {
		return listFormationsEmploye;
	}

	public void setListFormationsEmploye(
			List<DetailBesoinFormationDto> listFormationsEmploye) {
		this.listFormationsEmploye = listFormationsEmploye;
	}

	
}
