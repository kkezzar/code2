package dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.mc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AtomePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.MatiereConstitutiveDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AtomePedagogiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.MatiereConstitutiveService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.ap.ApBB;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Const;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;

/**
 * Classe de type Backingbean pour la gestion en m�me temps de la vue Edit et
 * Detail d'une mati�re constitutive
 * 
 * @author kheireddine omrani
 * 
 */

@ManagedBean(name = "mcBB")
@ViewScoped
public class McBB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6781271146732768965L;
	private boolean showDetail;

	@ManagedProperty(value = "#{matiereConstitutiveService}")
	private MatiereConstitutiveService matiereConstitutiveService;

	@ManagedProperty(value = "#{atomePedagogiqueService}")
	private AtomePedagogiqueService atomePedagogiqueService;

	@ManagedProperty(value = "#{utilBean}")
	private UtilBean utilBean;

	@ManagedProperty(value = "#{apBB}")
	private ApBB apBB;

	private final ResourceBundle offreFormationRessourcesBundle;

	private final ResourceBundle commonRessourcesBundle;

	private MatiereConstitutiveDto mc;

	private List<AtomePedagogiqueDto> apList;

	private String apId;

	private String activeAnglet;

	private int currentLevel = 1;

	List<SelectItem> typeMcList;

	List<SelectItem> langueList;

	List<SelectItem> modeEvaluationList;

	/**
	 * L'element root du composant tree view de l'anglet d�tail MC
	 */
	private TreeNode mcDetailTreeView;

	/**
	 * le noeud du tree view selectionn�.
	 */
	private String node;

	/**
	 * la valeur du noeud selectionn�.
	 */
	private String nodeValue;

	private String fullTextSearchKeyword;

	private List<MatiereConstitutiveDto> mcList;

	private ResourceBundle mcBundle;

	/**
	 * 
	 */
	public McBB() {
		super();

		FacesContext facesContext = FacesContext.getCurrentInstance();
		// Initialisation des ressources bundles
		commonRessourcesBundle = Utility
				.getCommonRessourcesBundle(facesContext);
		offreFormationRessourcesBundle = Utility
				.getOffreFormationRessourcesBundle(facesContext);
		mcBundle = Utility.getMcRessourcesBundle(facesContext);

	}

	@PostConstruct
	public void init() {
		mc = new MatiereConstitutiveDto();
	}

	/**
	 * [McBB.loadAps] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 09:41:30
	 */
	public void loadAps() {
		apList = new ArrayList<AtomePedagogiqueDto>();
		if (mc.getId() != 0) {
			apList = atomePedagogiqueService.findAPOfMC(mc.getId());
		}
	}

	/**
	 * [McBB.getMatiereConstitutiveService] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 11:55:20
	 * @return
	 */
	public MatiereConstitutiveService getMatiereConstitutiveService() {
		return matiereConstitutiveService;
	}

	/**
	 * [McBB.setMatiereConstitutiveService] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 11:55:23
	 * @param matiereConstitutiveService
	 */
	public void setMatiereConstitutiveService(
			MatiereConstitutiveService matiereConstitutiveService) {
		this.matiereConstitutiveService = matiereConstitutiveService;
	}

	/**
	 * [McBB.getAtomePedagogiqueService] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 11:55:25
	 * @return
	 */
	public AtomePedagogiqueService getAtomePedagogiqueService() {
		return atomePedagogiqueService;
	}

	/**
	 * [McBB.setAtomePedagogiqueService] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 11:55:28
	 * @param atomePedagogiqueService
	 */
	public void setAtomePedagogiqueService(
			AtomePedagogiqueService atomePedagogiqueService) {
		this.atomePedagogiqueService = atomePedagogiqueService;
	}

	/**
	 * 
	 * @return
	 */
	public MatiereConstitutiveDto getMc() {
		return mc;
	}

	/**
	 * [McBB.setMc] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 11:55:38
	 * @param mc
	 */
	public void setMc(MatiereConstitutiveDto mc) {
		this.mc = mc;
	}

	/**
	 * [McBB.getApId] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 11:55:43
	 * @return
	 */
	public String getApId() {
		return apId;
	}

	/**
	 * [McBB.setApId] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 11:55:45
	 * @param apId
	 */
	public void setApId(String apId) {
		this.apId = apId;
	}

	/**
	 * [McBB.getApList] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 11:55:48
	 * @return
	 */
	public List<AtomePedagogiqueDto> getApList() {
		return apList;
	}

	/**
	 * [McBB.getActiveAnglet] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 11:55:51
	 * @return
	 */
	public String getActiveAnglet() {
		return activeAnglet;
	}

	/**
	 * [McBB.setActiveAnglet] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 11:55:53
	 * @param activeAnglet
	 */
	public void setActiveAnglet(String activeAnglet) {
		this.activeAnglet = activeAnglet;
	}

	/**
	 * [McBB.getCurrentLevel] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 11:55:56
	 * @return
	 */
	public int getCurrentLevel() {
		if (activeAnglet != null)
			currentLevel = Integer.parseInt(activeAnglet);
		return currentLevel;
	}

	/**
	 * [McBB.setCurrentLevel] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 11:55:59
	 * @param currentLevel
	 */
	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	/**
	 * [McBB.getTypeMcList] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 11:56:02
	 * @return
	 */
	public List<SelectItem> getTypeMcList() {
		if (typeMcList == null)
			typeMcList = utilBean
					.loadNomenclatureLibelleItem(Const.LMD_TYPE_MC);

		return typeMcList;
	}

	/**
	 * [McBB.getLangueList] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 11:56:05
	 * @return
	 */
	public List<SelectItem> getLangueList() {
		if (langueList == null)
			langueList = utilBean.loadNomenclatureLibelleItem(Const.LANGUE);

		return langueList;
	}

	/**
	 * [McBB.getModeEvaluationList] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 11:56:08
	 * @return
	 */
	public List<SelectItem> getModeEvaluationList() {
		if (modeEvaluationList == null)
			modeEvaluationList = utilBean
					.loadNomenclatureLibelleItem(Const.LMD_MODE_EVALUATION);

		return modeEvaluationList;
	}

	/**
	 * [McBB.getNode] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 11:56:11
	 * @return
	 */
	public String getNode() {
		if (node == null)
			node = offreFormationRessourcesBundle
					.getString(OfConstants.OFFRE_FORMATION_MC_EDIT_OBJECTIFS);
		return node;
	}

	/**
	 * [McBB.setNode] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 11:56:13
	 * @param node
	 */
	public void setNode(String node) {
		this.node = node;
	}

	/**
	 * [McBB.getMcDetailTreeView] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 11:56:16
	 * @return
	 */
	public TreeNode getMcDetailTreeView() {
		return mcDetailTreeView;
	}

	/**
	 * [McBB.setMceDetailTreeView] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 11:56:19
	 * @param mcDetailTreeView
	 */
	public void setMceDetailTreeView(TreeNode mcDetailTreeView) {
		this.mcDetailTreeView = mcDetailTreeView;
	}

	/**
	 * [McBB.getNodeValue] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 11:56:21
	 * @return
	 */
	public String getNodeValue() {
		return nodeValue;
	}

	/**
	 * [McBB.setNodeValue] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 11:56:24
	 * @param nodeValue
	 */
	public void setNodeValue(String nodeValue) {
		this.nodeValue = nodeValue;
	}

	/**
	 * Permet d'aller � la vue de modification de la mati�re constitutive en
	 * cours
	 * 
	 * @return navigation-case = "mcEdit"
	 */
	public String updateMc() {
		return "mcEdit";
	}

	/**
	 * Annuler les changements �ffectu�s sur la mati�re constitutive en cours
	 * 
	 * @return navigation-case = "mcSearch"
	 */
	public String cancel() {
		return "mcSearch";

	}

	/**
	 * Retour vers la vue recherche des mati�res constitutive
	 * 
	 * @return navigation-case = "mcSearch"
	 */
	public String goback() {
		return "mcSearch";
	}

	/**
	 * Appliquer les changements �ffectu�s sur la mati�re constitutive en cours
	 * 
	 */
	public void saveMc() {
		try {
			// Appliquer les changements sur la MC
			updateMcDetailValues();
			MatiereConstitutiveDto _mc = matiereConstitutiveService
					.findByCode(mc.getCode());
			if (_mc != null && _mc.getId() != mc.getId()) {
				Utility.showErrorMessage(mcBundle
						.getString("matiere_constitutive_erreur_message_mc_code_exist_deja"));
				return;
			}
			mc = matiereConstitutiveService.insertOrUpdate(mc);
			// apBB.init();
			Utility.showSuccessUpdateMessage();

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
		}
		return;
	}

	/**
	 * [McBB.validateMc] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 11:59:17
	 */
	public void validateMc() {
		try {
			// valider la MC
			mc = matiereConstitutiveService.validate(mc.getId());

			// message de succ�s
			Utility.showSuccessMessage(commonRessourcesBundle
					.getString("msg_success_validation"));
		} catch (Exception e) {
			// message d'erreur
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [McBB.invalidateMc] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 11:59:03
	 */
	public void invalidateMc() {
		try {
			// invalider la MC
			mc = matiereConstitutiveService.invalidate(mc.getId());

			Utility.showSuccessSaveMessage();
		} catch (Exception e) {
			// message d'erreur
			Utility.showErrorMessage(e.getMessage());

		}
	}

	/* ********************** Gestion des AP ******************************* */

	public void addApDialogReturn(SelectEvent event) {

	}

	/**
	 * Permet d'ouvrir la boite de dialogue de'ajout d'une nouvelle AP.
	 * 
	 * @return
	 */
	public void createNewAp() {
		apBB.createNewAp();
	}

	/**
	 * [McBB.deleteAp] Method
	 * 
	 * @author MAKERRI Sofiane on : 24 nov. 2014 15:36:10
	 * @param selectedAp
	 */
	public void deleteAp() {
		try {
			apBB.deleteAp();
			loading();
			apBB.init();
			Utility.showSuccessDeleteMessage();
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * Permet d'aller � la vue d�tail de l'AP selectionn�e.
	 * 
	 * @return navigation-case = "apDetail"
	 */
	public void detailAp() {

		// Remplissag des param�tres.
		Map<String, List<String>> _params = getApDialogParams(true);
		// Remplissage des options d'affichage de la boite de dialogue
		Map<String, Object> _options = getDialogOptions();

		// Ouverture de la boite de dialogue
		RequestContext.getCurrentInstance().openDialog("apDetail", _options,
				_params);
	}

	/**
	 * Permet d'aller � la boit de dialog d'edition de l'AP selectionn�e.
	 * 
	 */
	public void updateAp() {

		// Remplissag des param�tres.
		Map<String, List<String>> _params = getApDialogParams(true);
		// Remplissage des options d'affichage de la boite de dialogue
		Map<String, Object> _options = getDialogOptions();

		// Ouverture de la boite de dialogue
		RequestContext.getCurrentInstance().openDialog("apEdit", _options,
				_params);
	}

	/**
	 * Fournir les param�tres � envoyer � la boite de dialog de gestion des APs.
	 * 
	 * @param includApId
	 *            : true si l'identifiant AP est inclu dans la liste des
	 *            param�tres, false sinon
	 * 
	 * @return : liste des param�tres � envoyer.
	 */
	private Map<String, List<String>> getApDialogParams(boolean includApId) {

		Map<String, List<String>> _params = new HashMap<String, List<String>>();

		List<String> _mcParamsValues = new ArrayList<String>();
		// _mcParamsValues.add(mcId);
		_params.put("mcId", _mcParamsValues);

		if (includApId) {
			List<String> _apParamsValues = new ArrayList<String>();
			_apParamsValues.add(apId);
			_params.put("apId", _apParamsValues);
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
		_options.put("contentWidth", 500);
		_options.put("contentHeight", 250);

		return _options;
	}

	private void loadMcDetailTreeView() {

		mcDetailTreeView = new DefaultTreeNode("Root", null);

		new DefaultTreeNode(
				offreFormationRessourcesBundle
						.getString(OfConstants.OFFRE_FORMATION_MC_EDIT_OBJECTIFS),
				mcDetailTreeView);
		new DefaultTreeNode(
				offreFormationRessourcesBundle
						.getString(OfConstants.OFFRE_FORMATION_MC_EDIT_CONTENU),
				mcDetailTreeView);
		new DefaultTreeNode(
				offreFormationRessourcesBundle
						.getString(OfConstants.OFFRE_FORMATION_MC_EDIT_COMPETENCES),
				mcDetailTreeView);
		new DefaultTreeNode(
				offreFormationRessourcesBundle
						.getString(OfConstants.OFFRE_FORMATION_MC_EDIT_RECOMMANDATIONS),
				mcDetailTreeView);
		new DefaultTreeNode(
				offreFormationRessourcesBundle
						.getString(OfConstants.OFFRE_FORMATION_MC_EDIT_ORGANISATION),
				mcDetailTreeView);
		new DefaultTreeNode(
				offreFormationRessourcesBundle
						.getString(OfConstants.OFFRE_FORMATION_MC_EDIT_AIDES_ETUDIANT),
				mcDetailTreeView);
		new DefaultTreeNode(
				offreFormationRessourcesBundle
						.getString(OfConstants.OFFRE_FORMATION_MC_EDIT_ADMISSION),
				mcDetailTreeView);
		new DefaultTreeNode(
				offreFormationRessourcesBundle
						.getString(OfConstants.OFFRE_FORMATION_MC_EDIT_PUBLIC_CIBLE),
				mcDetailTreeView);
		new DefaultTreeNode(
				offreFormationRessourcesBundle
						.getString(OfConstants.OFFRE_FORMATION_MC_EDIT_OBSERVATION),
				mcDetailTreeView);

		if (node == null)
			selectNode(offreFormationRessourcesBundle
					.getString(OfConstants.OFFRE_FORMATION_MC_EDIT_OBJECTIFS));
	}

	public void selectNode(String selectedNode) {

		if (selectedNode.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_MC_EDIT_OBJECTIFS)))
			nodeValue = mc.getObjectifs();
		else if (selectedNode.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_MC_EDIT_CONTENU)))
			nodeValue = mc.getContenu();
		else if (selectedNode.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_MC_EDIT_COMPETENCES)))
			nodeValue = mc.getCompetences();
		else if (node
				.equals(offreFormationRessourcesBundle
						.getString(OfConstants.OFFRE_FORMATION_MC_EDIT_RECOMMANDATIONS)))
			nodeValue = mc.getRecommandations();
		else if (selectedNode.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_MC_EDIT_ORGANISATION)))
			nodeValue = mc.getOrganisation();
		else if (selectedNode.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_MC_EDIT_AIDES_ETUDIANT)))
			nodeValue = mc.getAidesEtudiant();
		else if (selectedNode.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_MC_EDIT_ADMISSION)))
			nodeValue = mc.getAdmission();
		else if (selectedNode.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_MC_EDIT_PUBLIC_CIBLE)))
			nodeValue = mc.getPublicCible();
		else if (selectedNode.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_MC_EDIT_OBSERVATION)))
			nodeValue = mc.getObservation();
	}

	private void updateMcDetailValues() {

		if (node == null)
			return;

		if (node.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_MC_EDIT_OBJECTIFS)))
			mc.setObjectifs(nodeValue);
		else if (node.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_MC_EDIT_CONTENU)))
			mc.setContenu(nodeValue);
		else if (node.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_MC_EDIT_COMPETENCES)))
			mc.setCompetences(nodeValue);
		else if (node
				.equals(offreFormationRessourcesBundle
						.getString(OfConstants.OFFRE_FORMATION_MC_EDIT_RECOMMANDATIONS)))
			mc.setRecommandations(nodeValue);
		else if (node.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_MC_EDIT_ORGANISATION)))
			mc.setOrganisation(nodeValue);
		else if (node.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_MC_EDIT_AIDES_ETUDIANT)))
			mc.setAidesEtudiant(nodeValue);
		else if (node.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_MC_EDIT_ADMISSION)))
			mc.setAdmission(nodeValue);
		else if (node.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_MC_EDIT_PUBLIC_CIBLE)))
			mc.setPublicCible(nodeValue);
		else if (node.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_MC_EDIT_OBSERVATION)))
			mc.setObservation(nodeValue);
	}

	/**
	 * [McBB.onRowSelect] Method
	 * 
	 * @author MAKERRI Sofiane on : 20 nov. 2014 16:59:53
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {
		try {
			mc = ((MatiereConstitutiveDto) event.getObject());
			apBB.createNewAp();
			loading();

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [McBB.loading] Method
	 * 
	 * @author MAKERRI Sofiane on : 24 nov. 2014 15:20:00
	 */
	public void loading() {
		loadAps();
		loadMcDetailTreeView();
		showDetail = true;
		apBB.setMc(mc);
	}

	/**
	 * [McBB.searchFullText] Method
	 * 
	 * @author MAKERRI Sofiane on : 24 nov. 2014 15:20:07
	 */
	public void searchFullText() {
		showDetail = false;
		if (fullTextSearchKeyword != null
				&& !fullTextSearchKeyword.trim().isEmpty()) {
			mcList = matiereConstitutiveService
					.findWithFullText(fullTextSearchKeyword);
		} else {
			mcList = matiereConstitutiveService.findAll();
		}

		if (mcList != null && mcList.size() == 1) {
			setMc(mcList.get(0));
			loading();
		}
		if (mcList == null || mcList.isEmpty()) {
			Utility.showWarningMessage(mcBundle
					.getString("matiere_constitutive_empty_result"));
			mcList = null;
		}
	}

	/**
	 * [McBB.createNewMc] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 12:35:00
	 */
	public void createNewMc() {
		mc = new MatiereConstitutiveDto();
		loading();

	}

	/**
	 * [McBB.showDetail] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 nov. 2014 17:05:37
	 * @return the showDetail
	 */
	public boolean isShowDetail() {
		return showDetail;
	}

	/**
	 * [McBB.showDetail] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 nov. 2014 17:05:37
	 * @param showDetail
	 *            the showDetail to set
	 */
	public void setShowDetail(boolean showDetail) {
		this.showDetail = showDetail;
	}

	/**
	 * [McBB.utilBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 nov. 2014 17:15:24
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [McBB.utilBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 nov. 2014 17:15:24
	 * @param utilBean
	 *            the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}

	/**
	 * [McBB.apBB] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 12:00:13
	 * @return the apBB
	 */
	public ApBB getApBB() {
		return apBB;
	}

	/**
	 * [McBB.apBB] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 12:00:13
	 * @param apBB
	 *            the apBB to set
	 */
	public void setApBB(ApBB apBB) {
		this.apBB = apBB;
	}

	/**
	 * [McBB.getFullTextSearchKeyword] Method
	 * 
	 * @author MAKERRI Sofiane on : 24 nov. 2014 15:20:41
	 * @return
	 */
	public String getFullTextSearchKeyword() {
		return fullTextSearchKeyword;
	}

	/**
	 * [McBB.setFullTextSearchKeyword] Method
	 * 
	 * @author MAKERRI Sofiane on : 24 nov. 2014 15:20:44
	 * @param fullTextSearchKeyword
	 */
	public void setFullTextSearchKeyword(String fullTextSearchKeyword) {
		this.fullTextSearchKeyword = fullTextSearchKeyword;
	}

	/**
	 * [McBB.getMcList] Method
	 * 
	 * @author MAKERRI Sofiane on : 24 nov. 2014 15:20:48
	 * @return
	 */
	public List<MatiereConstitutiveDto> getMcList() {
		return mcList;
	}

	/**
	 * [McBB.setMcList] Method
	 * 
	 * @author MAKERRI Sofiane on : 24 nov. 2014 15:20:51
	 * @param mcList
	 */
	public void setMcList(List<MatiereConstitutiveDto> mcList) {
		this.mcList = mcList;
	}

	/**
	 * [McBB.saveAp] Method
	 * 
	 * @author MAKERRI Sofiane on : 24 nov. 2014 15:26:25
	 */
	public void saveAp() {
		try {
			if (apBB.apCodeAlreadyExist()) {
				Utility.showErrorMessage(mcBundle
						.getString("matiere_constitutive_erreur_message_ap_code_exist_deja"));
				return;
			}
			if (apBB.apAlreadyExist()) {
				Utility.showErrorMessage(mcBundle
						.getString("matiere_constitutive_erreur_message_ap_exist_deja"));
				return;
			}
			apBB.saveAp();
			Utility.showSuccessSaveMessage();
			loading();
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
		}

	}

}
