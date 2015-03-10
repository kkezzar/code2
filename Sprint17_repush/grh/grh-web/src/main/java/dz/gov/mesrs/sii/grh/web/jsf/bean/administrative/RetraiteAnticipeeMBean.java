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

@ManagedBean(name = "retraiteAnticipeeMBean")
@ViewScoped
public class RetraiteAnticipeeMBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -166331668139686207L;

	private String searchKeyWords;
	private FinActiviteDto demandeDepartDto;
	private DossierEmployeDto dossierEmployeDto;
	private LazyDataModel<FinActiviteDto> demandeDepartModel;
	private LazyDataModel<DossierEmployeDto> dossierEmployeModel;
	private DossierEmployeDto employeSearchDto;
	private Integer idEtablissement;
	private boolean renderMotifRefus;
	private boolean renderDateAcceptee;

	// init
	@PostConstruct
	public void init() {
		idEtablissement = getSessionBean().getCompte().getEtabId();
		loadDemandeDepartModel();
	}

	// Ajax event
	public void onSearch() {
		loadDemandeDepartModel();
	}

	public void onSearchDossierEmploye() {
		dossierEmployeDto = null;
		loaddossierEmployeModel();
	}

	public void onNew() {
		demandeDepartDto = new FinActiviteDto();
		employeSearchDto = new DossierEmployeDto();
		employeSearchDto.setRefEtablissementDto(sessionBean.getRefEtablissementDto());
		dossierEmployeDto = new DossierEmployeDto();
		demandeDepartDto.setEmploye(dossierEmployeDto);
//		onSearchDossierEmploye();
	}

	public void onSave() {
		if (validForm()) {
			demandeDepartDto = serviceLocator.getFinActiviteService().saveDemandeRetraiteAnticipe(demandeDepartDto);
			if (demandeDepartDto.getAccepte() != null) {
				demandeDepartDto = null;
			}
			CommonMessagesUtils.showSuccessSaveMessage();
			loadDemandeDepartModel();

		}

	}

	public void onRemove() {
		serviceLocator.getFinActiviteService().remove(demandeDepartDto);
		demandeDepartDto = null;
		CommonMessagesUtils.showSuccessDeleteMessage();
	}

	// Demande Retraite Anticipee DataTable
	public void onRowSelected(SelectEvent event) {
		demandeDepartDto = (FinActiviteDto) event.getObject();
		dossierEmployeDto = demandeDepartDto.getEmploye();
		setFormAction();
	}

	// Employe DataTable
	public void onRowSelect(SelectEvent event) {
		dossierEmployeDto = (DossierEmployeDto) event.getObject();
		demandeDepartDto = new FinActiviteDto();
		// TODO recuperer la demande demission
		demandeDepartDto.setEmploye(dossierEmployeDto);

	}

	public void onInit() {
		// TODO
	}

	public void onDecisionSelected() {
		setFormAction();
	}

	private void setFormAction() {
		if (demandeDepartDto.getAccepte() != null) {
			if (Boolean.TRUE.equals(demandeDepartDto.getAccepte())) {
				renderDateAcceptee = true;
				renderMotifRefus = false;
			} else {
				renderDateAcceptee = false;
				renderMotifRefus = true;
			}

		} else {
			renderDateAcceptee = false;
			renderMotifRefus = false;
		}

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
				dossierEmployeModel.setRowCount(serviceLocator.getDossierEmployeService().countAllActif(
						employeSearchDto));
				List<DossierEmployeDto> dtos = serviceLocator.getDossierEmployeService().findAllActif(employeSearchDto,
						searchMode);
				return dtos;
			}
		};

	}

	private void loadDemandeDepartModel() {
		demandeDepartModel = new LazyDataModel<FinActiviteDto>() {
			private static final long serialVersionUID = 4453742764020984653L;

			@Override
			public List<FinActiviteDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				SearchMode searchMode = new SearchMode(pageSize, first);
				demandeDepartModel.setRowCount(serviceLocator.getFinActiviteService().countAllPendingRetraiteAnticipe(
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
				return serviceLocator.getFinActiviteService().findAllPendingRetraiteAnticipe(idEtablissement,
						sortField, searchMode);
			}
		};
	}

	// validation
	private boolean validForm() {
		boolean valid = true;
		Date dateDemande = demandeDepartDto.getDateDemande();
		Date dateTitutlarisation = demandeDepartDto.getEmploye().getDateTitularisation();
		Date dateDepart = demandeDepartDto.getDateDepart();

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
	public FinActiviteDto getDemandeDepartDto() {
		return demandeDepartDto;
	}

	public void setDemandeDepartDto(FinActiviteDto demandeDepartDto) {
		this.demandeDepartDto = demandeDepartDto;
	}

	public String getSearchKeyWords() {
		return searchKeyWords;
	}

	public void setSearchKeyWords(String searchKeyWords) {
		this.searchKeyWords = searchKeyWords;
	}

	public LazyDataModel<FinActiviteDto> getDemandeDepartModel() {
		return demandeDepartModel;
	}

	public void setDemandeDepartModel(LazyDataModel<FinActiviteDto> demandeDepartModel) {
		this.demandeDepartModel = demandeDepartModel;
	}

	public DossierEmployeDto getDossierEmployeDto() {
		return dossierEmployeDto;
	}

	public void setDossierEmployeDto(DossierEmployeDto dossierEmployeDto) {
		this.dossierEmployeDto = dossierEmployeDto;
	}

	public LazyDataModel<DossierEmployeDto> getDossierEmployeModel() {
		return dossierEmployeModel;
	}

	public void setDossierEmployeModel(LazyDataModel<DossierEmployeDto> dossierEmployeModel) {
		this.dossierEmployeModel = dossierEmployeModel;
	}

	public DossierEmployeDto getEmployeSearchDto() {
		return employeSearchDto;
	}

	public void setEmployeSearchDto(DossierEmployeDto employeSearchDto) {
		this.employeSearchDto = employeSearchDto;
	}

	public boolean isRenderMotifRefus() {
		return renderMotifRefus;
	}

	public void setRenderMotifRefus(boolean renderMotifRefus) {
		this.renderMotifRefus = renderMotifRefus;
	}

	public boolean isRenderDateAcceptee() {
		return renderDateAcceptee;
	}

	public void setRenderDateAcceptee(boolean renderDateAcceptee) {
		this.renderDateAcceptee = renderDateAcceptee;
	}

}
