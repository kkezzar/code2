package dz.gov.mesrs.sii.gfc.web.jsf.bean.parametrageBudget.comptesTresorsFonds;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.dao.DataIntegrityViolationException;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.gfc.business.model.dto.FondsDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

/**
 * @author MESSOAUDI MOUNIR (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:06:31
 */
@ManagedBean(name = "fondsBean")
@ViewScoped
public class FondsMBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;

	private LazyDataModel<FondsDto> model;
	private FondsDto fondsDto;
	private FondsDto fondsSearchDto;

	// UI

	private boolean isView;
	private boolean isCrud;

	private List<SelectItem> listEtablissements;
	private List<SelectItem> listStructures;

	private List<SelectItem> listChapitres;
	private List<SelectItem> listArticles;

	public FondsMBean() {
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

	private void initUI() {
		fondsSearchDto = new FondsDto();
		listStructures = new ArrayList<SelectItem>();
	}

	private void loadSearchResults() {
		SituationEntiteDto situation = new SituationEntiteDto();

		if (viewId.equals("FondsEdit")) {
			fondsSearchDto.setSituation(situation);
		} else if (viewId.equals("FondsCloture")) {
			situation.setCodeSituation(UtilConstants.SITUATION_CLOTUREE_CODE);
			fondsSearchDto.setSituation(situation);
		}

		model = new LazyDataModel<FondsDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(FondsDto fondsDto) {
				return fondsDto.getId();
			}

			@Override
			public List<FondsDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {

				Boolean descending = null;

				if (sortOrder != null) {
					if (sortOrder.equals(SortOrder.DESCENDING)) {
						descending = Boolean.TRUE;
					} else {
						descending = Boolean.FALSE;
					}
				}

				SearchMode searchMode = new SearchMode(pageSize, first);
				Collection<Integer> collection = new ArrayList<Integer>();
				switch (viewId) {
				case "FondsEdit":
					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_ENTITE_CODE,
									UtilConstants.SITUATION_VALIDEE_CODE).getId());
					searchMode.addFilter(new Filter("situation.id", collection.toArray(), FilterMode.NOT_IN));
					break;
				case "FondsEditCloture":
					collection.add(serviceLocator
							.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_ENTITE_CODE,
									UtilConstants.SITUATION_VALIDEE_CODE).getId());
					searchMode.addFilter(new Filter("situation.id", collection.toArray(), FilterMode.IN));
					break;
				default:
					break;
				}

				model.setRowCount(serviceLocator.getFondsService().countAllByExample(fondsSearchDto, searchMode));

				return serviceLocator.getFondsService().findAllByExample(fondsSearchDto, searchMode);
			}
		};
	}

	public void searchAction() {
		loadSearchResults();
	}

	/**
	 * Evenement de selection d'un agent comptable
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 18:04:03
	 */
	public void onRowSelect(SelectEvent event) {
		fondsDto = (FondsDto) event.getObject();
		isCrud = true;
		isView = true;
	}

	/**
	 * Ajouter un nouveau fonds
	 * 
	 * @author Mounir.MESSAOUDI on : 15 déc. 2014 17:25:15
	 */
	public void addAction() {
		fondsDto = new FondsDto();
		isCrud = true;
	}

	/**
	 * Enregistrer le fonds
	 * 
	 * @author Mounir.MESSAOUDI on : 15 déc. 2014 15:28:41
	 */
	public void saveAction() {
		try {
			if (fondsDto != null) {
				if (fondsDto.getSituation().getId() == 0) {
					// set etablissement ( par default/etablissement de
					// l'utilisateur en cours)
					RefEtablissementDto etablissement = new RefEtablissementDto();
					etablissement.setId(sessionBean.getCompte().getEtabId());
					fondsDto.setEtablissement(etablissement);
					SituationEntiteDto situation = serviceLocator.getSituationService()
							.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_ENTITE_CODE,
									UtilConstants.SITUATION_CREEE_CODE);
					fondsDto.setSituation(situation);

					fondsDto = serviceLocator.getFondsService().save(fondsDto);
					CommonMessagesUtils.showSuccessSaveMessage();
				} else {
					serviceLocator.getFondsService().save(fondsDto);
					CommonMessagesUtils.showSuccessUpdateMessage();
				}
			}
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	/**
	 * Envoyer le fonds a la validation
	 * 
	 * @author Mounir.MESSAOUDI on : 15 déc. 2014 15:29:03
	 */
	public void sendToValidationAction() {
		try {
			SituationEntiteDto situation = serviceLocator.getSituationService().findBySituationEntiteByCodeSituation(
					UtilConstants.ENTITE_ENTITE_CODE, UtilConstants.SITUATION_SOUMISE_VALIDATION_CODE);
			situation.setIdEntite(fondsDto.getId());
			fondsDto.setSituation(situation);
			serviceLocator.getFondsService().save(fondsDto);

			CommonMessagesUtils.showSuccessSendToValidationMessage();
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	/**
	 * Valider le fonds
	 * 
	 * @author Mounir.MESSAOUDI on : 15 déc. 2014 15:29:15
	 */
	public void validateAction() {
		try {
			SituationEntiteDto situation = serviceLocator.getSituationService().findBySituationEntiteByCodeSituation(
					UtilConstants.ENTITE_ENTITE_CODE, UtilConstants.SITUATION_VALIDEE_CODE);
			situation.setIdEntite(fondsDto.getId());
			fondsDto.setSituation(situation);
			serviceLocator.getFondsService().save(fondsDto);

			CommonMessagesUtils.showSuccessValidationMessage();
			isCrud = false;
		} catch (Exception e) {
			e.printStackTrace();
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	/**
	 * Cloturer le fonds
	 * 
	 * @author Mounir.MESSAOUDI on : 15 déc. 2014 17:24:58
	 */
	public void closingAction() {
		try {
			if (fondsDto != null) {
				SituationEntiteDto situation = serviceLocator.getSituationService()
						.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_ENTITE_CODE,
								UtilConstants.SITUATION_CLOTUREE_CODE);
				situation.setIdEntite(fondsDto.getId());
				fondsDto.setSituation(situation);
				serviceLocator.getFondsService().save(fondsDto);
				CommonMessagesUtils.showSuccessClosingMessage();

				isCrud = false;
			}
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	/**
	 * Supprimer le compte
	 * 
	 * @author Mounir.MESSAOUDI on : 15 déc. 2014 15:29:22
	 */
	public void deleteAction() {
		try {
			if (fondsDto != null) {
				serviceLocator.getFondsService().remove(fondsDto);
				CommonMessagesUtils.showSuccessDeleteMessage();
				isCrud = false;
			}
		} catch (DataIntegrityViolationException e) {
			CommonMessagesUtils.showConstraintViolationErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	/**
	 * Annuler l'operation sur le compte
	 * 
	 * @author Mounir.MESSAOUDI on : 15 déc. 2014 15:29:29
	 */
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

	public LazyDataModel<FondsDto> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<FondsDto> model) {
		this.model = model;
	}

	public FondsDto getFondsDto() {
		return fondsDto;
	}

	public void setFondsDto(FondsDto fondDto) {
		this.fondsDto = fondDto;
	}

	public FondsDto getFondsSearchDto() {
		return fondsSearchDto;
	}

	public void setFondsSearchDto(FondsDto fondsSearchDto) {
		this.fondsSearchDto = fondsSearchDto;
	}

	public List<SelectItem> getListChapitres() {
		return listChapitres;
	}

	public void setListChapitres(List<SelectItem> listChapitres) {
		this.listChapitres = listChapitres;
	}

	public List<SelectItem> getListArticles() {
		return listArticles;
	}

	public void setListArticles(List<SelectItem> listArticles) {
		this.listArticles = listArticles;
	}

	/**
	 * Renvoi la liste des etablissement
	 * 
	 * @author Mounir.MESSAOUDI on : 15 déc. 2014 13:57:01
	 * @return liste des etablissements
	 */
	public List<SelectItem> getListEtablissements() {
		if (listEtablissements == null || listEtablissements.isEmpty())
			listEtablissements = findListeEtablissements();
		return listEtablissements;
	}

	public void setListEtablissements(List<SelectItem> listEtablissements) {
		this.listEtablissements = listEtablissements;
	}

	/**
	 * Renvoi la liste des structures
	 * 
	 * @author Mounir.MESSAOUDI on : 16 déc. 2014 10:48:04
	 * @return
	 */
	public List<SelectItem> getListStructures() {
		if (listStructures.isEmpty()) {
			Integer idEtablissement = sessionBean.getCompte().getEtabId();
			List<RefStructureDto> listStructuresDto = serviceLocator.getStructureService().findAll(idEtablissement);
			for (RefStructureDto ls : listStructuresDto) {
				listStructures.add(new SelectItem(ls.getId(), ls.getLlStructureLatin()));
			}
		}
		return listStructures;
	}

	public void setListStructures(List<SelectItem> listStructures) {
		this.listStructures = listStructures;
	}

}
