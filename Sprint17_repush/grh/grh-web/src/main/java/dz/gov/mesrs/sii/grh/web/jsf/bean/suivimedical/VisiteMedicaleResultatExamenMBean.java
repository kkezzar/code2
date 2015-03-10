package dz.gov.mesrs.sii.grh.web.jsf.bean.suivimedical;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.grh.business.model.dto.ExamenMedicalDto;
import dz.gov.mesrs.sii.grh.business.model.dto.VisiteMedicaleDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;

/**
 * 
 * @author BELDI Jamel
 * 
 */
@ManagedBean(name = "visiteMedicaleResultatExamenMBean")
@ViewScoped
public class VisiteMedicaleResultatExamenMBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String searchKeyWords;
	private VisiteMedicaleDto visiteMedicaleDto;	
	private List<VisiteMedicaleDto> listeVisiteMedicale;
	
	private List<ExamenMedicalDto> listExamens;
	
	
	public VisiteMedicaleResultatExamenMBean() {

	}

	@PostConstruct
	public void init() {
		searchKeyWords = "";
		
		onSearch();
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
		listeVisiteMedicale = serviceLocator.getVisiteMedicaleService().findAllVisiteAvecExamenNonEncoreRenseignes(sessionBean.getIdEtablissement(), searchKeyWords);
	}

	
	public void onVisiteSelect(SelectEvent event) {
		visiteMedicaleDto = (VisiteMedicaleDto) event.getObject();
		listExamens = visiteMedicaleDto.getExamenMedicalDtos();
		
	}
	

	public void onSave() {
		if (validateForm()) {
			visiteMedicaleDto.setFinExamen(Boolean.TRUE);
			serviceLocator.getVisiteMedicaleService().save(visiteMedicaleDto);
			loadlisteVisiteMedicale();
			initDetail();
			CommonMessagesUtils.showSuccessSaveMessage();
		}
	}

	private boolean validateForm() {
		boolean result = true;
	for (ExamenMedicalDto examen : listExamens) {
		
	
		if (examen.getDateExamen().before(visiteMedicaleDto.getDateVisite()))
				 {
			
			GRHMessagesUtils
					.showErrorMessage("visite_medicale_date_examen_invalide");
			result = false;
		}
	}
		
		return result;
	}

	public VisiteMedicaleDto getVisiteMedicaleDto() {
		return visiteMedicaleDto;
	}

	public void setVisiteMedicaleDto(VisiteMedicaleDto visiteMedicaleDto) {
		this.visiteMedicaleDto = visiteMedicaleDto;
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

	

	public List<ExamenMedicalDto> getListExamens() {
		return listExamens;
	}

	public void setListExamens(List<ExamenMedicalDto> listExamens) {
		this.listExamens = listExamens;
	}

	
	
	
}
