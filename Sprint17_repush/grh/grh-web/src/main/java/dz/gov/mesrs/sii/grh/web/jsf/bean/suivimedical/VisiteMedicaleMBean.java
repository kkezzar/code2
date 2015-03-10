package dz.gov.mesrs.sii.grh.web.jsf.bean.suivimedical;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.grh.business.model.dto.ExamenMedicalDto;
import dz.gov.mesrs.sii.grh.business.model.dto.MedicamentDto;
import dz.gov.mesrs.sii.grh.business.model.dto.VisiteMedicaleDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

/**
 * 
 * @author BELDI Jamel
 * 
 */
@ManagedBean(name = "visiteMedicaleMBean")
@ViewScoped
public class VisiteMedicaleMBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String searchKeyWords;
	private VisiteMedicaleDto visiteMedicaleDto;	
	private List<VisiteMedicaleDto> listeVisiteMedicale;
	private List<MedicamentDto> listMedicaments;
	private List<SelectItem> listeNcMedicament;
	private Integer selectedMedicamentIndex;
	private List<ExamenMedicalDto> listExamens;
	private List<SelectItem> listeNcExamen;
	private Integer selectedExamenIndex;
	
	public VisiteMedicaleMBean() {

	}

	@PostConstruct
	public void init() {
		searchKeyWords = "";
		initSelectItemLists();
		onSearch();
	}

	private void initSelectItemLists() {
		listeNcMedicament = findListeNcMedicament();
		listeNcExamen = findListeNcExamenMedical();
	}
	
	private void initDetail() {
        visiteMedicaleDto = null;
	}

	
	public void onSearch() {
		try {
			loadlisteVisiteMedicale();
			initDetail();
		} catch (Exception e) {
			

		}
	}
	private void loadlisteVisiteMedicale() {
		listeVisiteMedicale = serviceLocator.getVisiteMedicaleService().findAllVisiteMedicalesAcceptees(sessionBean.getIdEtablissement(), searchKeyWords);
	}

	
	public void onVisiteSelect(SelectEvent event) {
		visiteMedicaleDto = (VisiteMedicaleDto) event.getObject();
		visiteMedicaleDto.setDateVisite(visiteMedicaleDto.getDateConvenue());
		visiteMedicaleDto.setHeureVisite(visiteMedicaleDto.getHeureConvenue());
		listMedicaments = visiteMedicaleDto.getMedicamentDtos();
		listExamens = visiteMedicaleDto.getExamenMedicalDtos();
		
	}
	

	public void onSave() {
		if (validateForm()) {
			visiteMedicaleDto.setFinVisite(Boolean.TRUE);
        if(visiteMedicaleDto.getExamenMedicalDtos()!=null && !visiteMedicaleDto.getExamenMedicalDtos().isEmpty()){
        	visiteMedicaleDto.setAvecExamen(Boolean.TRUE);
        }
			serviceLocator.getVisiteMedicaleService().save(visiteMedicaleDto);
			loadlisteVisiteMedicale();
			initDetail();
			CommonMessagesUtils.showSuccessSaveMessage();
		}
	}

	private boolean validateForm() {
		boolean result = true;
	
		if (visiteMedicaleDto.getDateVisite().before(new Date())
				|| serviceLocator.getRefJourOuvreService().isWeekendDay(
						visiteMedicaleDto.getDateVisite())
				|| serviceLocator.getRefJourOuvreService().isJourOuvre(
						visiteMedicaleDto.getDateVisite())) {
			
			GRHMessagesUtils
					.showErrorMessage("visite_medicale_date_visite_invalide");
			result = false;
		}
		
		
		return result;
	}

	public VisiteMedicaleDto getVisiteMedicaleDto() {
		return visiteMedicaleDto;
	}

	public void setVisiteMedicaleDto(VisiteMedicaleDto visiteMedicaleDto) {
		this.visiteMedicaleDto = visiteMedicaleDto;
	}

	public void addMedicament() {
		MedicamentDto medicamentDto = new MedicamentDto();
		medicamentDto.setNomenclatureDto(new NomenclatureDto());
		if (this.listMedicaments == null) {
			this.listMedicaments = new ArrayList<MedicamentDto>();
		}
		medicamentDto.setVisiteMedicaleDto(this.visiteMedicaleDto);
		this.listMedicaments.add(medicamentDto);
	}

	public void removeMedicament() {
		MedicamentDto medicamentDto = this.listMedicaments.get(selectedMedicamentIndex);
		this.listMedicaments.remove(medicamentDto);

	}

	
	public void addExamen() {
		ExamenMedicalDto examenMedicalDto = new ExamenMedicalDto();
		examenMedicalDto.setNomenclatureDto(new NomenclatureDto());
		if (this.listExamens == null) {
			this.listExamens  = new ArrayList<ExamenMedicalDto>();
		}
		examenMedicalDto.setVisiteMedicaleDto(this.visiteMedicaleDto);
		this.listExamens.add(examenMedicalDto);
	}

	public void removeExamen() {
		ExamenMedicalDto examenMedicalDto = this.listExamens.get(selectedExamenIndex);
		this.listMedicaments.remove(examenMedicalDto);

	}
	public List<VisiteMedicaleDto> getListeVisiteMedicale() {
		return listeVisiteMedicale;
	}

	public void setListeVisiteMedicale(List<VisiteMedicaleDto> listeVisiteMedicale) {
		this.listeVisiteMedicale = listeVisiteMedicale;
	}

	public String getSearchKeyWords() {
		return searchKeyWords;
	}

	public void setSearchKeyWords(String searchKeyWords) {
		this.searchKeyWords = searchKeyWords;
	}

	public List<MedicamentDto> getListMedicaments() {
		return listMedicaments;
	}

	public void setListMedicaments(List<MedicamentDto> listMedicaments) {
		this.listMedicaments = listMedicaments;
	}

	public List<SelectItem> getListeNcmedicament() {
		return listeNcMedicament;
	}

	public void setListeNcMedicament(List<SelectItem> listeNcMedicament) {
		this.listeNcMedicament = listeNcMedicament;
	}

	public Integer getSelectedMedicamentIndex() {
		return selectedMedicamentIndex;
	}

	public void setSelectedMedicamentIndex(Integer selectedMedicamentIndex) {
		this.selectedMedicamentIndex = selectedMedicamentIndex;
	}

	public List<ExamenMedicalDto> getListExamens() {
		return listExamens;
	}

	public void setListExamens(List<ExamenMedicalDto> listExamens) {
		this.listExamens = listExamens;
	}

	public List<SelectItem> getListeNcExamen() {
		return listeNcExamen;
	}

	public void setListeNcExamen(List<SelectItem> listeNcExamen) {
		this.listeNcExamen = listeNcExamen;
	}

	public Integer getSelectedExamenIndex() {
		return selectedExamenIndex;
	}

	public void setSelectedExamenIndex(Integer selectedExamenIndex) {
		this.selectedExamenIndex = selectedExamenIndex;
	}

	public List<SelectItem> getListeNcMedicament() {
		return listeNcMedicament;
	}

	
	
}
