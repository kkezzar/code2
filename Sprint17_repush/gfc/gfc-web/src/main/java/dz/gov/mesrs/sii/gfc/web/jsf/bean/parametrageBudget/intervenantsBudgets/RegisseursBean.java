package dz.gov.mesrs.sii.gfc.web.jsf.bean.parametrageBudget.intervenantsBudgets;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.validation.ConstraintViolationException;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.gfc.business.model.dto.RegieDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.RegisseurDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;

/**
 * Managedbean qui gere les ecrans de gestion des regisseurs
 * 
 * @author MESSOAUDI MOUNIR (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:06:31
 */
@ManagedBean(name = "regisseursBean")
@ViewScoped
public class RegisseursBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;

	private LazyDataModel<RegisseurDto> model;
	private RegisseurDto regisseurDto;
	private RegisseurDto regisseurSearchDto;

	// UI

	private List<SelectItem> listRegies;

	private boolean isView;
	private boolean isCrud;

	public RegisseursBean() {
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
		listRegies = new ArrayList<SelectItem>();
		List<RegieDto> listRegiesDos = serviceLocator.getRegieService().findAll();
		if (listRegiesDos != null) {
			for (RegieDto regieDto : listRegiesDos) {
				listRegies.add(new SelectItem(regieDto.getId(), regieDto.getObjetFr()));
			}
		}
	}

	/**
	 * Charger les resultats de la recherche
	 * 
	 * @author Mounir.MESSAOUDI on : 1 déc. 2014 11:11:46
	 */
	private void loadSearchResults() {
		regisseurSearchDto = new RegisseurDto();

		// programmeNationalDto.setIntituleAr(searchByKeywords);

		model = new LazyDataModel<RegisseurDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public List<RegisseurDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				Boolean descending = null;

				SearchMode searchMode = new SearchMode(pageSize, first);

				// searchMode.addSortField(sortField);

				if (sortOrder != null) {
					if (sortOrder.equals(SortOrder.DESCENDING)) {
						descending = Boolean.TRUE;
					} else {
						descending = Boolean.FALSE;
					}

				}
				model.setRowCount(serviceLocator.getRegisseurService().countAllByExample(regisseurSearchDto));
				return serviceLocator.getRegisseurService().findAllByExample(regisseurSearchDto, searchMode);
			}
		};
	}

	/**
	 * Lancer la recherche
	 * 
	 * @author Mounir.MESSAOUDI on : 1 déc. 2014 11:12:13
	 */
	public void searchAction() {
	}

	/**
	 * Evenement de selection d'un dossier etudiant
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 18:04:03
	 */
	public void onRowSelect(SelectEvent event) {
		try {
			regisseurDto = (RegisseurDto) event.getObject();
			isCrud = true;
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	/**
	 * @author Mounir.MESSAOUDI on : 1 déc. 2014 11:54:25
	 */
	public void addAction() {
		regisseurDto = new RegisseurDto();
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
		regisseurDto.setRefIndividu(refIndividuDto);
	}

	/**
	 * Enregistrer le regisseur
	 * 
	 * @author Mounir.MESSAOUDI on : 1 déc. 2014 11:12:55
	 */
	public void saveAction() {
		try {
			if (regisseurDto != null) {
				if (regisseurDto.getId() == null) {
					regisseurDto = serviceLocator.getRegisseurService().save(regisseurDto);
					CommonMessagesUtils.showSuccessSaveMessage();
				} else {
					serviceLocator.getRegisseurService().save(regisseurDto);
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
	 * Supprimer le regisseur
	 * 
	 * @author Mounir.MESSAOUDI on : 1 déc. 2014 15:32:17
	 */
	public void deleteAction() {
		try {
			if (regisseurDto != null) {
				serviceLocator.getRegisseurService().remove(regisseurDto);
				CommonMessagesUtils.showSuccessDeleteMessage();
				isCrud = false;
			}
		} catch (ConstraintViolationException e) {
			CommonMessagesUtils.showConstraintViolationErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	public void cancelAction() {
		regisseurDto = null;
		isCrud = false;
		isView = false;
	}

	public boolean isView() {
		return isView;
	}

	public boolean isCrud() {
		return isCrud;
	}

	public LazyDataModel<RegisseurDto> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<RegisseurDto> model) {
		this.model = model;
	}

	public RegisseurDto getRegisseurDto() {
		return regisseurDto;
	}

	public void setRegisseurDto(RegisseurDto regisseurDto) {
		this.regisseurDto = regisseurDto;
	}

	public RegisseurDto getRegisseurSearchDto() {
		return regisseurSearchDto;
	}

	public void setRegisseurSearchDto(RegisseurDto regisseurSearchDto) {
		this.regisseurSearchDto = regisseurSearchDto;
	}

	public List<SelectItem> getListRegies() {
		return listRegies;
	}

	public void setListRegies(List<SelectItem> listRegies) {
		this.listRegies = listRegies;
	}

}
