package dz.gov.mesrs.sii.gfc.web.jsf.bean.misePlaceBudgetFonctionnement;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dz.gov.mesrs.sii.gfc.web.jsf.bean.BaseBean;

/**
 * @author MESSOAUDI MOUNIR (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:06:31
 */
@ManagedBean(name = "misePlaceBudgetFonctionnement")
@ViewScoped
public class MisePlaceBudgetFonctionnementBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;

	// UI

	private boolean isView;
	private boolean isCrud;

	public MisePlaceBudgetFonctionnementBean() {
	}

	@PostConstruct
	public void init() {

	}

	private void loadSearchResults() {
	}

	public void searchAction() {
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
