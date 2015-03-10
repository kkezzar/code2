package dz.gov.mesrs.sii.fve.web.jsf.bean.scolarite.chargepedagogique;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

@ManagedBean(name = "ficheVoeuxConsult")
@ViewScoped
public class FicheVoeuxConsult {

	// ===================================================================
	// Constructors
	// ===================================================================
	public FicheVoeuxConsult() {

	}

	@PostConstruct
	public void init() {

		try {

	 		ficheVoeuxBB.initSessionInfos();
	 		ficheVoeuxBB.initNomenclatures();
	 		ficheVoeuxBB.initYearsList();
	 		ficheVoeuxBB.initOffresFormation(false);
	 		ficheVoeuxBB.setToSubmit(false);
			ficheVoeuxBB.setToEdit(false);
			ficheVoeuxBB.setToConsult(true);
			ficheVoeuxBB.setToValidate(false);
			ficheVoeuxBB.setEnseignantDto(null);
			ficheVoeuxBB.checkExistingFiches();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ===================================================================
	// Beans Services
	// ===================================================================
	@ManagedProperty(value = "#{ficheVoeuxBB}")
	private FicheVoeuxBB ficheVoeuxBB;

	// ===================================================================
	// Beans Services Getters / Setters
	// ===================================================================

	/**
	 * [FicheVoeuxConsult.ficheVoeuxBB] Getter
	 * 
	 * @author rlaib on : 19 oct. 2014 18:01:23
	 * @return the ficheVoeuxBB
	 */
	public FicheVoeuxBB getFicheVoeuxBB() {
		return ficheVoeuxBB;
	}

	/**
	 * [FicheVoeuxConsult.ficheVoeuxBB] Setter
	 * 
	 * @author rlaib on : 19 oct. 2014 18:01:23
	 * @param ficheVoeuxBB
	 *            the ficheVoeuxBB to set
	 */
	public void setFicheVoeuxBB(FicheVoeuxBB ficheVoeuxBB) {
		this.ficheVoeuxBB = ficheVoeuxBB;
	}

	// ===================================================================
	// Properties Model
	// ===================================================================

	// ===================================================================
	// Session Infos
	// ===================================================================

	// ===================================================================
	// Listeners
	// ===================================================================
	/**
	 * [FicheVoeuxConsult.onFichesVoeuxRowSelect] Method 
	 * @author rlaib  on : 6 nov. 2014  10:15:52
	 * @param event
	 */
	public void onFichesVoeuxRowSelect(SelectEvent event) {

		try {

			ficheVoeuxBB.loadFicheVoeuxDetails();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	// ===================================================================
	// ActionListener
	// ===================================================================

	// ===================================================================
	// Listeners
	// ===================================================================

	// ===================================================================
	// Actions and Methods
	// ===================================================================

	// ===================================================================
	// Functions Helper
	// ===================================================================

}
