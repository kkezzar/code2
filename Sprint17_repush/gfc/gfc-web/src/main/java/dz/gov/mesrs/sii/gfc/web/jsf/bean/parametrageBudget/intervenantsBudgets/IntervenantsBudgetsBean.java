package dz.gov.mesrs.sii.gfc.web.jsf.bean.parametrageBudget.intervenantsBudgets;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;

/**
 * @author MESSOAUDI MOUNIR (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:06:31
 */
@ManagedBean(name = "intervenantsBudgets")
@ViewScoped
public class IntervenantsBudgetsBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;

	// UI

	private boolean isView;
	private boolean isCrud;

	public IntervenantsBudgetsBean() {
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
	}

	/**
	 * Charger les resultats de la recherche
	 * 
	 * @author Mounir.MESSAOUDI on : 1 déc. 2014 11:11:46
	 */
	private void loadSearchResults() {
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
			isCrud = true;
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
	}

	public void saveAction() {

	}

	public void sendToValidationAction() {

	}

	public void validateAction() {

	}

	public void cancelAction() {

	}

	public boolean isView() {
		return isView;
	}

	public boolean isCrud() {
		return isCrud;
	}

}
