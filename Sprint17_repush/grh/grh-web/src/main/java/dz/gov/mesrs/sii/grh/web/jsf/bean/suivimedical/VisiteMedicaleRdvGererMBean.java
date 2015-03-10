package dz.gov.mesrs.sii.grh.web.jsf.bean.suivimedical;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.grh.business.model.dto.VisiteMedicaleDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;

/**
 * 
 * @author BELDI Jamel
 * 
 */
@ManagedBean(name = "visiteMedicaleRdvGererMBean")
@ViewScoped
public class VisiteMedicaleRdvGererMBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String searchKeyWords;
	private VisiteMedicaleDto visiteMedicaleDto;	
	private List<VisiteMedicaleDto> listeVisiteMedicale;

	public VisiteMedicaleRdvGererMBean() {

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
		listeVisiteMedicale = serviceLocator.getVisiteMedicaleService().findAllRdvVisiteMedicales(sessionBean.getIdEtablissement(), searchKeyWords);
	}

	
	public void onRdvSelect(SelectEvent event) {
		visiteMedicaleDto = (VisiteMedicaleDto) event.getObject();
		visiteMedicaleDto.setDateConvenue(visiteMedicaleDto.getDateRdv());
		visiteMedicaleDto.setHeureConvenue(visiteMedicaleDto.getHeureRdv());
		
	}
	

	public void onSave() {
		if (validateForm()) {
			serviceLocator.getVisiteMedicaleService().save(visiteMedicaleDto);
			loadlisteVisiteMedicale();
			initDetail();
			CommonMessagesUtils.showSuccessSaveMessage();
		}
	}

	private boolean validateForm() {
		boolean result = true;
		if(visiteMedicaleDto.getResultat()!=null && visiteMedicaleDto.getResultat()==Boolean.TRUE){
		if (visiteMedicaleDto.getDateConvenue().before(new Date())
				|| serviceLocator.getRefJourOuvreService().isWeekendDay(
						visiteMedicaleDto.getDateConvenue())
				|| serviceLocator.getRefJourOuvreService().isJourOuvre(
						visiteMedicaleDto.getDateConvenue())) {
			
			GRHMessagesUtils
					.showErrorMessage("visite_medicale_date_convenue_invalide");
			result = false;
		}
		if(result){
			visiteMedicaleDto.setMotifRefus(null);	
		}
		} else if(visiteMedicaleDto.getResultat()!=null && visiteMedicaleDto.getResultat()==Boolean.TRUE){
			visiteMedicaleDto.setDateConvenue(null);
			visiteMedicaleDto.setHeureConvenue(null);
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

	
	
}
