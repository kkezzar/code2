package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.inscription;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierInscriptionAdministrativeService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.ReferentielHelper;

/**
 * Classe de type Backingbean pour la gestion du composant listing des dossiers
 * d'nscription d'un etudiant
 * 
 * @author kheireddine omrani
 * @param <DossierInscriptionAdministrativeDto>
 * 
 */

@ManagedBean(name = "dossierInscriptionListBB")
@RequestScoped
public class DossierInscriptionListBB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public DossierInscriptionListBB() {
		super();
	}

	@PostConstruct
	public void init() {

		if (dossierEtudiantId != null && !dossierEtudiantId.trim().isEmpty()
				&& !dossierEtudiantId.trim().equals("null")) {
			dossierInscriptionList = dossierInscriptionAdministrativeService
					.findDossierInscriptionsBy(Integer 
							.parseInt(dossierEtudiantId));

//			for (DossierInscriptionAdministrativeDto _diaDto : dossierInscriptionList) {
//				OuvertureOffreFormationDto ouverture = ouvertureOffreFormationService
//						.findById(_diaDto.getOuvertureOffreFormationId());
//				OffreFormationDto _ofDto = offreFormationService
//						.findById(ouverture.getOffreFormationId());
//
//				_diaDto.setOffreFormationLibelle(_ofDto.getI18nDtos().get("fr")
//						.getLibelle());
//			}
		}
	}

	/****************************************************************************/
	/******************************** Services **********************************/
	/****************************************************************************/

	/**
	 * Rererence vers le service de gestion des dossiers d'nscription
	 */
	@ManagedProperty(value = "#{dossierInscriptionAdministrativeService}")
	private DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService;

	@ManagedProperty(value = "#{ouvertureOffreFormationService}")
	private OuvertureOffreFormationService ouvertureOffreFormationService;

	@ManagedProperty(value = "#{offreFormationService}")
	private OffreFormationService offreFormationService;

	@ManagedProperty(value = "#{referentielHelper}")
	private ReferentielHelper referentielHelper;

	/****************************************************************************/
	/*********************** proprietes & composants ****************************/
	/****************************************************************************/

	private List<DossierInscriptionAdministrativeDto> dossierInscriptionList;

	@ManagedProperty("#{param.dossierEtudiantId}")
	private String dossierEtudiantId;
	
	@ManagedProperty("#{param.dossierInscriptionId}")
	private String dossierInscriptionId;

	/****************************************************************************/
	/********************************* getter/setter ***************************/
	/****************************************************************************/


	// ****************** getter/setter des services **********************/
	/**
	 * Obtient la reference du service permettant la gestion des dossiers
	 * d'nscription
	 * 
	 * @return le service de gestion des dossiers d'nscription.
	 */
	public DossierInscriptionAdministrativeService getDossierInscriptionAdministrativeService() {
		return dossierInscriptionAdministrativeService;
	}

	/**
	 * Met ejour la reference du service permettant la gestion des dossiers
	 * d'nscription
	 * 
	 * @param dossierInscriptionService
	 *            : Le nouveau service de gestion des dossiers d'nscription.
	 */
	public void setDossierInscriptionAdministrativeService(
			DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService) {
		this.dossierInscriptionAdministrativeService = dossierInscriptionAdministrativeService;
	}

	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	public void setOffreFormationService(
			OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	public void setOuvertureOffreFormationService(
			OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	public ReferentielHelper getReferentielHelper() {
		return referentielHelper;
	}

	public void setReferentielHelper(ReferentielHelper referentielHelper) {
		this.referentielHelper = referentielHelper;
	}

	// ************ getter/setter des propriete & composants **************/

	public List<DossierInscriptionAdministrativeDto> getDossierInscriptionList() {
		return dossierInscriptionList;
	}

	public void setDossierInscriptionList(
			List<DossierInscriptionAdministrativeDto> dossierInscriptionList) {
		this.dossierInscriptionList = dossierInscriptionList;
	}

	public String getDossierEtudiantId() {
		return dossierEtudiantId;
	}

	public void setDossierEtudiantId(String dossierEtudiantId) {
		this.dossierEtudiantId = dossierEtudiantId;
	}


	public String getDossierInscriptionId() {
		return dossierInscriptionId;
	}

	public void setDossierInscriptionId(String dossierInscriptionId) {
		this.dossierInscriptionId = dossierInscriptionId;
	}
	
	/****************************************************************************/
	/************************** Persistance & navigation ************************/
	/****************************************************************************/

	/**
	 * Permet d'aller e la vue de creation d'un nouveau dossier d'inscription.
	 * 
	 * @return navigation-case = "dossierInscriptionEdit"
	 */
	public void createNewDossierInscription() {

		// Remplissag des parametres.
		Map<String, List<String>> _params = getApDialogParams(false);
		// Remplissage des options d'affichage de la boite de dialogue
		Map<String, Object> _options = getDialogOptions();

		// Ouverture de la boite de dialogue
		RequestContext.getCurrentInstance().openDialog(
				"dossierInscriptionEdit", _options, _params);
	}

	/**
	 * Permet d'aller e la vue de modification du dossier d'inscription.
	 * 
	 * @return navigation-case = "dossierInscriptionEdit"
	 */
	public void updateDossierInscription() {
		
		// Remplissag des parametres.
		Map<String, List<String>> _params = getApDialogParams(true);
		// Remplissage des options d'affichage de la boite de dialogue
		Map<String, Object> _options = getDialogOptions();

		// Ouverture de la boite de dialogue
		RequestContext.getCurrentInstance().openDialog(
				"dossierInscriptionEdit", _options, _params);
	}

	public void addDioDialogReturn(SelectEvent event) {

	}
	
	public void parcoursIndiv(){
		
	}

	
	public void deleteDossierInscription(DossierInscriptionAdministrativeDto selectedDossierInscription) {
		dossierInscriptionAdministrativeService.remove(selectedDossierInscription);
	}

	/**
	 * Permet d'aller ela boit de dialog du detail du dossier d'inscription selectionne.
	 * 
	 * @return navigation-case = "dossierInscriptionDetail"
	 */
	public void detailDossierInscription() {

		// Remplissag des parametres.
		Map<String, List<String>> _params = getApDialogParams(true);
		// Remplissage des options d'affichage de la boite de dialogue
		Map<String, Object> _options = getDialogOptions();

		// Ouverture de la boite de dialogue
		RequestContext.getCurrentInstance().openDialog("dossierInscriptionDetail", _options,
				_params);
	}


	/**
	 * Fournir les parametres e envoyer e la boite de dialog de gestion des dossiers.
	 * 
	 * @param includDiaId
	 *            : true si l'identifiant du dossier d'inscription est inclu dans la liste des
	 *            parametres, false sinon
	 * 
	 * @return : liste des parametres e envoyer.
	 */
	private Map<String, List<String>> getApDialogParams(boolean includDiaId) {

		Map<String, List<String>> _params = new HashMap<String, List<String>>();

		List<String> _deParamsValues = new ArrayList<String>();
		_deParamsValues.add(dossierEtudiantId);
		_params.put("dossierEtudiantId", _deParamsValues);

		List<String> _piecesParamsValues = new ArrayList<String>();
		_piecesParamsValues.add("D002");
		_params.put("codeTypeDossier", _piecesParamsValues);		
		
		List<String> _dossierParamsValues = new ArrayList<String>();
		_dossierParamsValues.add(dossierInscriptionId);
		_params.put("dossierId", _dossierParamsValues);
		
		if (includDiaId) {
			List<String> _diaParamsValues = new ArrayList<String>();
			_diaParamsValues.add(dossierInscriptionId);
			_params.put("dossierInscriptionId", _diaParamsValues);
		}

		return _params;
	}

	/**
	 * Fournir la liste des options d'ouverture de la bialog box
	 * 
	 * @return
	 */
	private Map<String, Object> getDialogOptions() {

		Map<String, Object> _options = new HashMap<String, Object>();
		_options.put("modal", true);
		_options.put("draggable", false);
		_options.put("resizable", false);
		_options.put("contentWidth",900);
		_options.put("contentHeight", 700);

		return _options;
	}

}
