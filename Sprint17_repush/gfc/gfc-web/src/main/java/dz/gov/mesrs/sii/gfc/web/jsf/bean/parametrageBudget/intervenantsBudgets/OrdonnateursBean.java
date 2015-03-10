package dz.gov.mesrs.sii.gfc.web.jsf.bean.parametrageBudget.intervenantsBudgets;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.validation.ConstraintViolationException;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.gfc.business.model.dto.OrdonnateurDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.GFCConstantBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

/**
 * Managedbean qui gere les ecrans de gestion des ordonnateurs
 * 
 * @author MESSOAUDI MOUNIR (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:06:31
 */
@ManagedBean(name = "ordonnateursBean")
@ViewScoped
public class OrdonnateursBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;

	private LazyDataModel<OrdonnateurDto> model;
	private OrdonnateurDto ordonnateurDto;
	private OrdonnateurDto ordonnateurSearchDto;

	// UI

	private List<SelectItem> listTypesOrdonnateurs;
	private List<SelectItem> listEtablissements;
	private List<SelectItem> listStructures;

	private boolean isView;
	private boolean isCrud;

	public OrdonnateursBean() {
	}

	@PostConstruct
	public void init() {
		try {
			initUI();
			loadSearchResults();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * Initialiser l'UI
	 * 
	 * @author Mounir.MESSAOUDI on : 26 nov. 2014 18:06:39
	 */
	private void initUI() {
		listEtablissements = findListeEtablissements();
		listStructures = new ArrayList<SelectItem>();

		if (viewId.equals("OrdonnateursEdit")) {
			listTypesOrdonnateurs = findNomenclatureList(GFCConstantBean.CODE_TYPE_ORDONNATEUR);
		}
	}

	/**
	 * Charger les resultats de la recherche
	 * 
	 * @author Mounir.MESSAOUDI on : 1 déc. 2014 11:11:46
	 */
	private void loadSearchResults() {
		ordonnateurSearchDto = new OrdonnateurDto();

		// programmeNationalDto.setIntituleAr(searchByKeywords);

		model = new LazyDataModel<OrdonnateurDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public List<OrdonnateurDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				Boolean descending = null;

				// searchMode.addSortField(sortField);

				if (sortOrder != null) {
					if (sortOrder.equals(SortOrder.DESCENDING)) {
						descending = Boolean.TRUE;
					} else {
						descending = Boolean.FALSE;
					}

				}
				model.setRowCount(serviceLocator.getOrdonnateurService().countAllByExample(ordonnateurSearchDto));
				SearchMode searchMode = new SearchMode(pageSize, first);
				return serviceLocator.getOrdonnateurService().findAllByExample(ordonnateurSearchDto, searchMode);
			}
		};
	}

	/**
	 * Handle changement de l'etalissement
	 * 
	 * @author Mounir.MESSAOUDI on : 2 déc. 2014 16:38:44
	 * @param event
	 * @throws Exception
	 */
	public void handleEtabSelectOneMenuChange(AjaxBehaviorEvent event) throws Exception {
		if (this.ordonnateurDto != null) {
			listStructures = new ArrayList<SelectItem>();

			Integer idEtablissement = this.ordonnateurDto.getEtablissement().getId();
			List<RefStructureDto> listStructuresDto = serviceLocator.getStructureService().findAll(idEtablissement);
			for (RefStructureDto ls : listStructuresDto) {
				listStructures.add(new SelectItem(ls.getId(), ls.getLlStructureLatin()));
			}
		}
	}

	/**
	 * Lancer la recherche
	 * 
	 * @author Mounir.MESSAOUDI on : 1 déc. 2014 11:12:13
	 */
	public void searchAction() {
		loadSearchResults();
	}

	/**
	 * Evenement de selection d'un dossier etudiant
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 18:04:03
	 */
	public void onRowSelect(SelectEvent event) {
		try {
			ordonnateurDto = (OrdonnateurDto) event.getObject();
			listStructures = new ArrayList<SelectItem>();

			Integer idEtablissement = this.ordonnateurDto.getEtablissement().getId();
			List<RefStructureDto> listStructuresDto = serviceLocator.getStructureService().findAll(idEtablissement);
			for (RefStructureDto ls : listStructuresDto) {
				listStructures.add(new SelectItem(ls.getId(), ls.getLlStructureLatin()));
			}

			isCrud = true;
			isView = true;
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	/**
	 * @author Mounir.MESSAOUDI on : 1 déc. 2014 11:54:25
	 */
	public void addAction() {
		ordonnateurDto = new OrdonnateurDto();
		isCrud = true;
	}

	/**
	 * Evenement de selection d'un individu depuis la boite de dialgue
	 * 
	 * @author Mounir.MESSAOUDI on : 11 d�c. 2014 12:16:58
	 * @param event
	 */
	public void onIndividuSelect(SelectEvent event) {
		RefIndividuDto refIndividuDto = (RefIndividuDto) event.getObject();
		ordonnateurDto.setRefIndividu(refIndividuDto);
	}

	/**
	 * Enregistrer l'ordonnateur
	 * 
	 * @author Mounir.MESSAOUDI on : 1 déc. 2014 11:12:55
	 */
	public void saveAction() {
		try {
			if (ordonnateurDto != null) {
				// fix transient instance with non-mandatory @ManyToOne
				if (ordonnateurDto.getStructure().getId() == null || ordonnateurDto.getStructure().getId() == 0) {
					ordonnateurDto.setStructure(null);
				}

				ordonnateurDto = serviceLocator.getOrdonnateurService().save(ordonnateurDto);
				if (ordonnateurDto.getId() == null) {
					CommonMessagesUtils.showSuccessSaveMessage();
				} else {
					CommonMessagesUtils.showSuccessUpdateMessage();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	/**
	 * Supprimer l'ordonnateur
	 * 
	 * @author Mounir.MESSAOUDI on : 1 déc. 2014 15:32:17
	 */
	public void deleteAction() {
		try {
			if (ordonnateurDto != null) {
				serviceLocator.getOrdonnateurService().remove(ordonnateurDto);
				CommonMessagesUtils.showSuccessDeleteMessage();
				isCrud = false;
			}
		} catch (ConstraintViolationException e) {
			CommonMessagesUtils.showConstraintViolationErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}

	}

	public void cancelAction() {
		isCrud = false;
		isView = false;
	}

	public boolean isView() {
		return isView;
	}

	public boolean isCrud() {
		return isCrud;
	}

	public LazyDataModel<OrdonnateurDto> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<OrdonnateurDto> model) {
		this.model = model;
	}

	public OrdonnateurDto getOrdonnateurDto() {
		return ordonnateurDto;
	}

	public void setOrdonnateurDto(OrdonnateurDto ordonnateurDto) {
		this.ordonnateurDto = ordonnateurDto;
	}

	public OrdonnateurDto getOrdonnateurSearchDto() {
		return ordonnateurSearchDto;
	}

	public void setOrdonnateurSearchDto(OrdonnateurDto ordonnateurSearchDto) {
		this.ordonnateurSearchDto = ordonnateurSearchDto;
	}

	public List<SelectItem> getListTypesOrdonnateurs() {
		return listTypesOrdonnateurs;
	}

	public void setListTypesOrdonnateurs(List<SelectItem> listTypesOrdonnateurs) {
		this.listTypesOrdonnateurs = listTypesOrdonnateurs;
	}

	public List<SelectItem> getListEtablissements() {
		return listEtablissements;
	}

	public void setListEtablissements(List<SelectItem> listEtablissements) {
		this.listEtablissements = listEtablissements;
	}

	public List<SelectItem> getListStructures() {
		return listStructures;
	}

	public void setListStructures(List<SelectItem> listStructures) {
		this.listStructures = listStructures;
	}

}
