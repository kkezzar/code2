package dz.gov.mesrs.sii.grh.web.jsf.bean.administrative;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.commons.web.util.SearchModeMapper;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.FinActiviteDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;

@ManagedBean(name = "demandeDemissionMBean")
@ViewScoped
public class DemandeDemissionMBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -614275620816232090L;

	private String searchKeyWords;
	private FinActiviteDto demandeDemissionDto;
	private DossierEmployeDto dossierEmployeDto;
	private DossierEmployeDto employeSearchDto;
	private LazyDataModel<FinActiviteDto> demandeDemissionModel;
	private LazyDataModel<DossierEmployeDto> dossierEmployeModel;
	private Integer idEtablissement;
	private boolean renderMotif;
	private boolean renderDateDepartAcceptee;

	// init
	@PostConstruct
	public void init() {
		idEtablissement = getSessionBean().getCompte().getEtabId();
		loadDemandeDemissionModel();
	}

	// Ajax event
	public void onSearch() {
		loadDemandeDemissionModel();
	}

	public void onSearchDossierEmploye() {
		dossierEmployeDto = null;
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
			public List<DossierEmployeDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				SearchMode searchMode = SearchModeMapper.map(first, pageSize, sortField, sortOrder);
				dossierEmployeModel.setRowCount(serviceLocator.getDossierEmployeService().countAllActif(employeSearchDto));
				List<DossierEmployeDto> dtos = serviceLocator.getDossierEmployeService().findAllActif(
						employeSearchDto, searchMode);
				return dtos;
			}
		};

	}

	public void onDecisionSelected() {
		setFormAction();
	}

	public void onNew() {
		demandeDemissionDto = new FinActiviteDto();
		employeSearchDto = new DossierEmployeDto();
		employeSearchDto.setRefEtablissementDto(sessionBean.getRefEtablissementDto());
		dossierEmployeDto  = new DossierEmployeDto();
		demandeDemissionDto.setEmploye(dossierEmployeDto);
//		onSearchDossierEmploye();
//		dossierEmployeModel = null;
	}

	public void onSave() {
		if (validForm()) {
			demandeDemissionDto.setEmploye(dossierEmployeDto);
			demandeDemissionDto = serviceLocator.getFinActiviteService().saveDemandeDemission(demandeDemissionDto);
			if (demandeDemissionDto.getAccepte() != null) {
				demandeDemissionDto = null;
			}
			CommonMessagesUtils.showSuccessSaveMessage();
			loadDemandeDemissionModel();

		}

	}

	public void onRemove() {
		serviceLocator.getFinActiviteService().remove(demandeDemissionDto);
		demandeDemissionDto = null;
		CommonMessagesUtils.showSuccessDeleteMessage();
	}

	// FinActivite DataTable
	public void onRowSelected(SelectEvent event) {
		demandeDemissionDto = (FinActiviteDto) event.getObject();
		dossierEmployeDto = demandeDemissionDto.getEmploye();
		setFormAction();
	}

	// Employe DataTable
	public void onRowSelect(SelectEvent event) {
		dossierEmployeDto = (DossierEmployeDto) event.getObject();
		demandeDemissionDto = new FinActiviteDto();
		demandeDemissionDto.setEmploye(dossierEmployeDto);

	}

	private void setFormAction() {
		if (demandeDemissionDto.getAccepte() != null) {
			if (Boolean.TRUE.equals(demandeDemissionDto.getAccepte())) {
				renderDateDepartAcceptee = true;
				renderMotif = false;
			} else {
				renderDateDepartAcceptee = false;
				renderMotif = true;
			}
		} else {
			renderDateDepartAcceptee = false;
			renderMotif = false;
		}

	}

	public void onInit() {
		// TODO
	}

	private void loadDemandeDemissionModel() {
		demandeDemissionModel = new LazyDataModel<FinActiviteDto>() {
			private static final long serialVersionUID = 4453742764020984653L;

			@Override
			public List<FinActiviteDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				SearchMode searchMode = new SearchMode(pageSize, first);
				demandeDemissionModel.setRowCount(serviceLocator.getFinActiviteService().countAllPendingDemission(
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

				return serviceLocator.getFinActiviteService().findAllPendingDemission(idEtablissement, searchKeyWords,
						searchMode);
			}
		};
	}

	// validation
	private boolean validForm() {
		boolean valid = true;
		Date dateDemande = demandeDemissionDto.getDateDemande();
		Date dateTitutlarisation = demandeDemissionDto.getEmploye().getDateTitularisation();
		Date dateDepart = demandeDemissionDto.getDateDepart();

		if (dateTitutlarisation != null && dateTitutlarisation.after(dateDemande)) {
			valid = false;
			GRHMessagesUtils.showErrorMessage(MSG_BUNDLE_FIN_ACTIVITE, "msg_date_demande_dem_titularisation_invalid");
		}
		if (dateDemande.after(dateDepart)) {
			valid = false;
			GRHMessagesUtils.showErrorMessage(MSG_BUNDLE_FIN_ACTIVITE, "msg_date_demande_dem_depart_invalid");
		}
		return valid;
	}

	// getter setter
	public FinActiviteDto getDemandeDemissionDto() {
		return demandeDemissionDto;
	}

	public void setDemandeDemissionDto(FinActiviteDto demandeDemissionDto) {
		this.demandeDemissionDto = demandeDemissionDto;
	}

	public String getSearchKeyWords() {
		return searchKeyWords;
	}

	public void setSearchKeyWords(String searchKeyWords) {
		this.searchKeyWords = searchKeyWords;
	}

	public LazyDataModel<FinActiviteDto> getDemandeDemissionModel() {
		return demandeDemissionModel;
	}

	public void setDemandeDemissionModel(LazyDataModel<FinActiviteDto> demandeDemissionModel) {
		this.demandeDemissionModel = demandeDemissionModel;
	}

	public DossierEmployeDto getDossierEmployeDto() {
		return dossierEmployeDto;
	}

	public LazyDataModel<DossierEmployeDto> getDossierEmployeModel() {
		return dossierEmployeModel;
	}

	public void setDossierEmployeModel(LazyDataModel<DossierEmployeDto> dossierEmployeModel) {
		this.dossierEmployeModel = dossierEmployeModel;
	}

	public void setDossierEmployeDto(DossierEmployeDto dossierEmployeDto) {
		this.dossierEmployeDto = dossierEmployeDto;
	}

	public DossierEmployeDto getEmployeSearchDto() {
		return employeSearchDto;
	}

	public void setEmployeSearchDto(DossierEmployeDto employeSearchDto) {
		this.employeSearchDto = employeSearchDto;
	}

	public boolean isRenderMotif() {
		return renderMotif;
	}

	public void setRenderMotif(boolean renderMotif) {
		this.renderMotif = renderMotif;
	}

	public boolean isRenderDateDepartAcceptee() {
		return renderDateDepartAcceptee;
	}

	public void setRenderDateDepartAcceptee(boolean renderDateDepartAcceptee) {
		this.renderDateDepartAcceptee = renderDateDepartAcceptee;
	}

}
