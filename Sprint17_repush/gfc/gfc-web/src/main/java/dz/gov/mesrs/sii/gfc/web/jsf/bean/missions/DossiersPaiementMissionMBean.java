package dz.gov.mesrs.sii.gfc.web.jsf.bean.missions;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.DossierPaiementDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;

/**
 * Gere les dossiers de paiement des missions
 * 
 * @author MESSOAUDI MOUNIR (MESRS CCM)
 * @version 1.0
 * @created 15/02/2015 10:06:31
 */
@ManagedBean(name = "dossiersPaiementMissionBean")
@ViewScoped
public class DossiersPaiementMissionMBean extends BaseBean {

	public final static String MARCHES_BUNDLE_MSG_NAME = "missionsMsgs";

	private static final long serialVersionUID = 1L;

	private LazyDataModel<DossierPaiementDto> model;

	// UI
	private boolean isView;
	private boolean isCrud;

	public String keyWords;
	private List<SituationEntiteOccurrenceDto> dossierPaiementHistory;

	public DossiersPaiementMissionMBean() {
	}

	@PostConstruct
	public void init() {
		initUI();
		loadSearchResults();
	}

	private void initUI() {

	}

	private void loadSearchResults() {
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public void searchAction() {
		loadSearchResults();
	}

	public LazyDataModel<DossierPaiementDto> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<DossierPaiementDto> model) {
		this.model = model;
	}

	public void onRowSelect(SelectEvent event) {

		isCrud = true;
		isView = true;
	}

	public void saveAction() {

	}

	public void validateAction() {

	}

	public void deleteAction() {

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

	public List<SituationEntiteOccurrenceDto> getDossierPaiementHistory() {
		return dossierPaiementHistory;
	}
}