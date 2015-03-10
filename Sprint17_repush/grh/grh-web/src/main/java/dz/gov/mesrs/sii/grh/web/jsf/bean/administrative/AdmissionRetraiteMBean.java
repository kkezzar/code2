package dz.gov.mesrs.sii.grh.web.jsf.bean.administrative;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.FinActiviteDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

@ManagedBean(name = "admissionRetraiteMBean")
@ViewScoped
public class AdmissionRetraiteMBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 7710331688244781823L;

	private String searchKeyWords;

	private LazyDataModel<FinActiviteDto> admissionRetraiteModel;
	private LazyDataModel<DossierEmployeDto> dossierEmployeModel;

	private Date dateDebutPeriode;
	private Date dateFinPeriode;

	private NomenclatureDto retraiteCessationType;

	private boolean renderAdmissionSelectionTable;

	private List<DossierEmployeDto> selectedEmployes;
	private List<DossierEmployeDto> admissibleEmployes;
	private List<DossierEmployeDto> admisEmployes;

	private Integer idEtablissement;

	@PostConstruct
	public void init() {
		idEtablissement = getSessionBean().getCompte().getEtabId();
		retraiteCessationType = serviceLocator.getNomenclatureService().findByCode(UtilConstant.NC_GRH_TYPE_CESSATION,
				UtilConstant.NC_GRH_TYPE_CESSATION_RETRAITE_VALUE);
		selectedEmployes = new ArrayList<>();
		onSearchAdmisRetraite();

	}

	public void onSearchAdmisRetraite() {
		admissionRetraiteModel = new LazyDataModel<FinActiviteDto>() {
			private static final long serialVersionUID = 4453742764020984653L;

			@Override
			public List<FinActiviteDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				SearchMode searchMode = new SearchMode(pageSize, first);
				admissionRetraiteModel.setRowCount(serviceLocator.getFinActiviteService().countAllAdmisRetraite(
						idEtablissement, searchKeyWords));

				if (!StringUtils.isEmpty(sortField)) {
					SearchMode.SortOrder order = null;
					if (sortOrder != null) {
						if (sortOrder.equals(SortOrder.DESCENDING)) {
							order = SearchMode.SortOrder.DESC;
						} else {
							order = SearchMode.SortOrder.ASC;
						}

					}
					SearchMode.SortField sort = new SearchMode.SortField(sortField, order);
					searchMode.addSortField(sort);
				}

				return serviceLocator.getFinActiviteService().findAllAdmisRetraite(idEtablissement, searchKeyWords,
						searchMode);
			}
		};
	}

	public void onSearchEmployeAdmissible() {
		renderAdmissionSelectionTable = true;
		dossierEmployeModel = new LazyDataModel<DossierEmployeDto>() {
			private static final long serialVersionUID = 4453742764020984653L;

			@Override
			public List<DossierEmployeDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				SearchMode searchMode = new SearchMode(pageSize, first);
				if (!StringUtils.isEmpty(sortField)) {
					SearchMode.SortOrder order = null;
					if (sortOrder != null) {
						if (sortOrder.equals(SortOrder.DESCENDING)) {
							order = SearchMode.SortOrder.DESC;
						} else {
							order = SearchMode.SortOrder.ASC;
						}

					}
					SearchMode.SortField sort = new SearchMode.SortField(sortField, order);
					searchMode.addSortField(sort);
				}

				dossierEmployeModel.setRowCount(serviceLocator.getDossierEmployeService().countAllAdmissibleRetraite(
						dateDebutPeriode, dateFinPeriode));
				List<DossierEmployeDto> results = serviceLocator.getDossierEmployeService().findAllAdmissibleRetraite(
						new DossierEmployeDto(), searchMode, dateDebutPeriode, dateFinPeriode);
				admissibleEmployes = new ArrayList<>();
				admisEmployes = new ArrayList<>();
				if (results != null) {
					admissibleEmployes.addAll(results);
					for (DossierEmployeDto dto : results) {
						
						if(!dto.getFinActiviteDtos().isEmpty()){
							NomenclatureDto typeCessation = dto.getFinActiviteDtos().get(0).getNomenclature();
							if(retraiteCessationType.getId().equals(typeCessation.getId())){
								admisEmployes.add(dto);	
							}
						}
					}
				}

				selectedEmployes = new ArrayList<>();
				selectedEmployes.addAll(admisEmployes);
				return results;
			}

		};
	}

	public void onSaveAdmissibles() {
		List<FinActiviteDto> toSaveDtos = new ArrayList<>();
		List<FinActiviteDto> toDeleteDtos = new ArrayList<>();
		for (DossierEmployeDto initial : admissibleEmployes) {
			if (selectedEmployes.contains(initial) && admisEmployes.contains(initial)) {
				continue;
			} else if (!selectedEmployes.contains(initial) && !admisEmployes.contains(initial)) {
				continue;

			} else {
				DossierEmployeDto updatedDto = admissibleEmployes.get(admissibleEmployes.indexOf(initial));
				if (!selectedEmployes.contains(initial) && admisEmployes.contains(initial)) {
					//TODO verifier
					toDeleteDtos.add(updatedDto.getFinActiviteDtos().get(0));
				} else {
					FinActiviteDto finActiviteDto = new FinActiviteDto();
					finActiviteDto.setDateDemande(new Date());
					finActiviteDto.setEmploye(updatedDto);
					finActiviteDto.setDateDepart(calculAgeDepart(updatedDto));
					toSaveDtos.add(finActiviteDto);
				}
			}
		}
		serviceLocator.getFinActiviteService().saveAdmissionRetraite(toSaveDtos, toDeleteDtos);
		CommonMessagesUtils.showSuccessSaveMessage();
		onSearchAdmisRetraite();
	}

	public Date calculAgeDepart(DossierEmployeDto dto) {
		return serviceLocator.getFinActiviteService().calculAgeRetraite(dto);
	}

	public void onRowSelect(SelectEvent event) {
		DossierEmployeDto dto = (DossierEmployeDto) event.getObject();
		dto.getConjointDtos();
	}

	public void onRowUnselect(UnselectEvent event) {
		DossierEmployeDto dto = (DossierEmployeDto) event.getObject();
		dto.getConjointDtos();
	}

	public String getSearchKeyWords() {
		return searchKeyWords;
	}

	public void setSearchKeyWords(String searchKeyWords) {
		this.searchKeyWords = searchKeyWords;
	}

	public LazyDataModel<FinActiviteDto> getAdmissionRetraiteModel() {
		return admissionRetraiteModel;
	}

	public void setAdmissionRetraiteModel(LazyDataModel<FinActiviteDto> admissionRetraiteModel) {
		this.admissionRetraiteModel = admissionRetraiteModel;
	}

	public LazyDataModel<DossierEmployeDto> getDossierEmployeModel() {
		return dossierEmployeModel;
	}

	public void setDossierEmployeModel(LazyDataModel<DossierEmployeDto> dossierEmployeModel) {
		this.dossierEmployeModel = dossierEmployeModel;
	}

	public Date getDateDebutPeriode() {
		return dateDebutPeriode;
	}

	public void setDateDebutPeriode(Date dateDebutPeriode) {
		this.dateDebutPeriode = dateDebutPeriode;
	}

	public Date getDateFinPeriode() {
		return dateFinPeriode;
	}

	public void setDateFinPeriode(Date dateFinPeriode) {
		this.dateFinPeriode = dateFinPeriode;
	}

	public NomenclatureDto getDepartCessationType() {
		return retraiteCessationType;
	}

	public void setDepartCessationType(NomenclatureDto departCessationType) {
		this.retraiteCessationType = departCessationType;
	}

	public boolean isRenderAdmissionSelectionTable() {
		return renderAdmissionSelectionTable;
	}

	public void setRenderAdmissionSelectionTable(boolean renderAdmissionSelectionTable) {
		this.renderAdmissionSelectionTable = renderAdmissionSelectionTable;
	}

	public List<DossierEmployeDto> getSelectedEmployes() {
		return selectedEmployes;
	}

	public void setSelectedEmployes(List<DossierEmployeDto> selectedEmployes) {
		this.selectedEmployes = selectedEmployes;
	}

}
