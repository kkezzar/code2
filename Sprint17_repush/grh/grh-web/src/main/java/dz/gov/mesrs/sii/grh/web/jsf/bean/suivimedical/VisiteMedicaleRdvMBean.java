package dz.gov.mesrs.sii.grh.web.jsf.bean.suivimedical;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.VisiteMedicaleDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;

/**
 * 
 * @author BELDI Jamel
 * 
 */
@ManagedBean(name = "visiteMedicaleRdvMBean")
@ViewScoped
public class VisiteMedicaleRdvMBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VisiteMedicaleDto visiteMedicaleDto;
	private DossierEmployeDto dossierEmployeDto;
	private List<VisiteMedicaleDto> listeVisiteMedicale;

	public VisiteMedicaleRdvMBean() {

	}

	@PostConstruct
	public void init() {
		getConnectedEmploye();
		loadlisteVisiteMedicale();
		initDetail();
	}

	private void initDetail() {
        visiteMedicaleDto = null;
	}

	private void loadlisteVisiteMedicale() {
		if(dossierEmployeDto!=null){
		VisiteMedicaleDto example = new VisiteMedicaleDto();
		example.setDossierEmployeDto(dossierEmployeDto);
		listeVisiteMedicale = serviceLocator.getVisiteMedicaleService()
				.findAllByExample(example);
		}
	}

	private void getConnectedEmploye() {
		RefIndividuDto refIndividuDto = sessionBean.getUser();
		dossierEmployeDto = serviceLocator.getDossierEmployeService()
				.findByIndividuId(refIndividuDto.getId());
	

	}

	public void onNew() {
		visiteMedicaleDto = new VisiteMedicaleDto();
		visiteMedicaleDto.setDossierEmployeDto(dossierEmployeDto);
	}

	public void onSave() {
		if (validateForm()) {
			visiteMedicaleDto.setDateDemande(new Date());
			serviceLocator.getVisiteMedicaleService().save(visiteMedicaleDto);
			loadlisteVisiteMedicale();
			initDetail();
			CommonMessagesUtils.showSuccessSaveMessage();
		}
	}

	private boolean validateForm() {
		boolean result = true;
		if (visiteMedicaleDto.getDateRdv().before(new Date())
				|| serviceLocator.getRefJourOuvreService().isWeekendDay(
						visiteMedicaleDto.getDateRdv())
				|| serviceLocator.getRefJourOuvreService().isJourOuvre(
						visiteMedicaleDto.getDateRdv())) {
			GRHMessagesUtils
					.showErrorMessage("visite_medicale_date_souhaitee_invalide");
			return false;
		}

		return result;
	}

	public VisiteMedicaleDto getVisiteMedicaleDto() {
		return visiteMedicaleDto;
	}

	public void setVisiteMedicaleDto(VisiteMedicaleDto visiteMedicaleDto) {
		this.visiteMedicaleDto = visiteMedicaleDto;
	}

	public DossierEmployeDto getDossierEmployeDto() {
		return dossierEmployeDto;
	}

	public void setDossierEmployeDto(DossierEmployeDto dossierEmployeDto) {
		this.dossierEmployeDto = dossierEmployeDto;
	}

	public List<VisiteMedicaleDto> getListeVisiteMedicale() {
		return listeVisiteMedicale;
	}

	public void setListeVisiteMedicale(List<VisiteMedicaleDto> listeVisiteMedicale) {
		this.listeVisiteMedicale = listeVisiteMedicale;
	}

	
	
}
