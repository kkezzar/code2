package dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.ue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.MatiereConstitutiveDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.RattachementMcDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.UniteEnseignementDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.MatiereConstitutiveService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.RattachementMcService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.UniteEnseignementService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Const;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;

/**
 * Classe de type Backingbean pour la gestion en m�me temps de la vue Edit et
 * Detail d'une unit� d'enseignement
 * 
 * @author kheireddine omrani
 * 
 */

@ManagedBean(name = "ueBB")
@ViewScoped
public class UeBB implements Serializable {

	/**
	 * cvcvcv
	 * 
	 */
	@ManagedProperty(value = "#{matiereConstitutiveService}")
	private MatiereConstitutiveService matiereConstitutiveService;
	@ManagedProperty(value = "#{utilBean}")
	private UtilBean utilBean;

	private static final long serialVersionUID = 1950368111085835320L;

	/****************************************************************************/
	/******************************** Services **********************************/
	/****************************************************************************/

	/**
	 * R�r�rence vers le service de gestion des unit�s d'enseignement
	 */
	@ManagedProperty(value = "#{uniteEnseignementService}")
	private UniteEnseignementService uniteEnseignementService;

	/**
	 * R�f�rence vers le service de gestion des rattachements Mc.
	 */
	@ManagedProperty(value = "#{rattachementMcService}")
	private RattachementMcService rattachementMcService;

	@ManagedProperty(value = "#{rattachementMcServiceImpl}")
	private RattachementMcService rattachementMcServiceImpl;

	/****************************************************************************/
	/*********************** propri�t�s & composants ****************************/
	/****************************************************************************/

		private ResourceBundle offreFormationRessourcesBundle;

	private ResourceBundle commonRessourcesBundle;

	private UniteEnseignementDto ue;
	
	private String node;

	private String activeAnglet;

	private int currentLevel = 1;

	private List<SelectItem> appreciationUEList;

	private List<SelectItem> natureUEList;

	private List<SelectItem> caractereUEList;

	private List<RattachementMcDto> ratachementMcList;

	private List<SelectItem> mcItemList;

	private List<MatiereConstitutiveDto> mcList;

	private List<SelectItem> listMatiere;

	private RattachementMcDto rattachementMcDto;

	private String fullTextSearchKeyword;

	private List<UniteEnseignementDto> ueList;

	private boolean showDetail;

	private ResourceBundle ueBundle;

	/**
	 * 
	 */
	public UeBB() {
		super();

		FacesContext facesContext = FacesContext.getCurrentInstance();

		// Initialisation des ressources bundles
		commonRessourcesBundle = Utility
				.getCommonRessourcesBundle(facesContext);
		offreFormationRessourcesBundle = Utility
				.getOffreFormationRessourcesBundle(facesContext);

		ueBundle = Utility.geUeRessourcesBundle(facesContext);
	}

	@PostConstruct
	public void init() {

		ue = new UniteEnseignementDto();
		ue.setDateCreation(new Date());
		ue.setDateModification(new Date());
		rattachementMcDto = new RattachementMcDto();
		loadNatureUeList();
		loadCategorieUeList();
	}

	/**
	 * [UeBB.loadNatureUeList] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 nov. 2014 13:56:14
	 */
	private void loadNatureUeList() {
		if(natureUEList == null || natureUEList.isEmpty()) {
		natureUEList = utilBean
				.loadNomenclatureLibelleItem(Const.LMD_NATURE_UE);
		}
	}

	/**
	 * [UeBB.loadCategorieUeList] Method 
	 * @author MAKERRI Sofiane  on : 26 nov. 2014  11:46:33
	 */
	private void loadCategorieUeList() {
		if(caractereUEList == null || caractereUEList.isEmpty()) {
		caractereUEList = utilBean
				.loadNomenclatureLibelleItem(Const.LMD_CARACTERE_UE);
		}
	}

	/**
	 * [UeBB.loadRattachementMc] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 nov. 2014 08:41:23
	 */
	public void loadRattachementMc() {
		ratachementMcList = new ArrayList<RattachementMcDto>();
		if (ue.getId() != 0) {
			ratachementMcList = rattachementMcService.find(ue.getId(), null);
		}
	}

	/**
	 * [UeBB.loadMcList] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 nov. 2014 11:17:38
	 */
	public void loadMcList() {
		mcItemList = new ArrayList<SelectItem>();
		mcList = matiereConstitutiveService.findAll();
		if (mcList != null) {
			for (MatiereConstitutiveDto mcDto : mcList) {
				mcItemList.add(new SelectItem(mcDto.getId(), mcDto.getCode()
						+ " " + mcDto.getLibelleFr()));
			}
		}
	}

	/**
	 * [UeBB.mcChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 nov. 2014 11:33:32
	 */
	public void mcChanged() {

		if (rattachementMcDto.getMcId() != null) {
			if (mcList != null) {
				for (MatiereConstitutiveDto mcDto : mcList) {
					if (rattachementMcDto.getMcId().equals(mcDto.getId())) {
						rattachementMcDto.setMcCode(mcDto.getCode());
						rattachementMcDto.setMcLibelleFr(mcDto.getLibelleFr());
						rattachementMcDto.setMcLibelleAr(mcDto.getLibelleAr());
						rattachementMcDto
								.setCoefficient(mcDto.getCoefficient());
						rattachementMcDto.setCredit(mcDto.getCredits());
						rattachementMcDto.setNoteDeBase(mcDto.getNoteBase());
						rattachementMcDto.setNoteObtension(mcDto
								.getNoteObtention());
						rattachementMcDto.setNoteEliminatoire(mcDto
								.getNoteEliminatoire());
					}
				}
			}
		} else {
			rattachementMcDto.setMcCode(null);
			rattachementMcDto.setMcLibelleFr(null);
			rattachementMcDto.setMcLibelleAr(null);
//			rattachementMcDto.setCoefficient(null);
//			rattachementMcDto.setCredit(null);
//			rattachementMcDto.setNoteDeBase(null);
//			rattachementMcDto.setNoteObtension(null);
//			rattachementMcDto.setNoteEliminatoire(null);
		}
	}

	/**
	 * [UeBB.searchFullText] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 nov. 2014 08:34:52
	 * @param fullTextKeyword
	 */
	public void searchFullText() {
		showDetail = false;
		ueList = uniteEnseignementService
				.findWithFullText(fullTextSearchKeyword);
       init();
		if (ueList != null && ueList.size() == 1) {
			ue = ueList.get(0);
			showDetail = true;
			loading();
		}
		if (ueList == null || ueList.isEmpty()) {
			
			Utility.showWarningMessage(ueBundle
					.getString("unite_enseignement_empty_result"));
			ueList = null;
		}

	}

	// ****************** getter/setter des services **********************/
	/**
	 * Obtient l'objet service de type {@link UniteEnseignementService}
	 * permettant la gestion des unit�s d'enseignement
	 * 
	 * @return le service de gestion des unit�s d'enseignement.
	 */
	public UniteEnseignementService getUniteEnseignementService() {
		return uniteEnseignementService;
	}

	/**
	 * Met � jour l'objet service de type {@link UniteEnseignementService}
	 * permettant la gestion des unit�s d'enseignement
	 * 
	 * @param uniteEnseignementService
	 *            : Le nouveau service de gestion des unit�s d'enseignement.
	 */
	public void setUniteEnseignementService(
			UniteEnseignementService uniteEnseignementService) {
		this.uniteEnseignementService = uniteEnseignementService;
	}

	public RattachementMcService getRattachementMcService() {
		return rattachementMcService;
	}

	public void setRattachementMcService(
			RattachementMcService rattachementMcService) {
		this.rattachementMcService = rattachementMcService;
	}

	// ************ getter/setter des propri�t� & composants **************/

	/**
	 * OBtient l'instance UE en cours de cr�ation, consultation ou modification
	 * 
	 * @return l'UE en cours de gestion
	 */
	public UniteEnseignementDto getUe() {
		return ue;
	}

	public void setUe(UniteEnseignementDto ue) {
		this.ue = ue;
	}

		public List<RattachementMcDto> getRatachementMcList() {
		return ratachementMcList;
	}

	public String getNode() {
		if (node == null)
			node = offreFormationRessourcesBundle
					.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_OBJECTIFS);
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	/**
	 * Obtient la liste des apreciatsion, UEs
	 * 
	 * @return La liste des apreciatsions UEs
	 */
	public List<SelectItem> getAppreciationUEList() {
		if (appreciationUEList == null)
			appreciationUEList = utilBean
					.loadNomenclatureLibelleItem(Const.LMD_APPRECIATION_UE);

		return appreciationUEList;
	}

	/**
	 * Obtient la liste des caract�res UEs
	 * 
	 * @return La liste des caract�res UEs
	 */
	public List<SelectItem> getCaractereUEList() {

		if (caractereUEList == null)
			caractereUEList = utilBean
					.loadNomenclatureLibelleItem(Const.LMD_CARACTERE_UE);

		return caractereUEList;
	}

	/**
	 * Obtient la liste des natures UEs
	 * 
	 * @return La liste des natures UEs
	 */
	public List<SelectItem> getNatureUEList() {
		return natureUEList;
	}

	public int getCurrentLevel() {
		if (activeAnglet != null)
			currentLevel = Integer.parseInt(activeAnglet);
		return currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	public String getActiveAnglet() {
		return activeAnglet;
	}

	public void setActiveAnglet(String activeAnglet) {
		this.activeAnglet = activeAnglet;
	}

	/****************************************************************************/
	/************************** Persistance & navigation ************************/
	/****************************************************************************/

	/**
	 * Permet d'aller � la vue de cr�ation d'une nouvelle unit� d'enseignement.
	 * 
	 * @return navigation-case = "ueEdit"
	 */
	public void createNewUe() {
		ue = new UniteEnseignementDto();
		loading();
	}

	/**
	 * [UeBB.createNewMc] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 nov. 2014 15:53:00
	 */
	public void createNewMc() {
		rattachementMcDto = new RattachementMcDto();
		//rattachementMcDto.setTauxExamen(1.0);
	}

	/**
	 * Retour vers la vue recherche des unit�s d'enseignement.
	 * 
	 * @return navigation-case = "ueSerach"
	 */
	public String goback() {
		return "ueSearch";
	}

	/**
	 * Appliquer les changements �ff�ctu�es sur l'unite d'enseignement en cours
	 * 
	 */
	public void saveUe() {
		try {
			// Appliquer les changements sur l'UE
			updateUeDetailValues();
			UniteEnseignementDto ueDto = uniteEnseignementService.findByCode(ue
					.getCode());
			boolean newUe = (ue.getId() == 0);
			if (ueDto != null && ueDto.getId() != ue.getId()) {
				Utility.showErrorMessage(ueBundle
						.getString("unite_enseignement_erreur_code_exist"));
				return;
			}
			ue = uniteEnseignementService.insertOrUpdate(ue);
			Utility.showSuccessSaveOrUpdateMessage(newUe);

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
		}

	}

	public void validateUe() {
		FacesMessage msg = new FacesMessage();
		try {
			// valider l'UE
			ue = uniteEnseignementService.validate(ue.getId());

			// message de succ�s
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(commonRessourcesBundle.getString("msg_success")
					+ ": "
					+ commonRessourcesBundle
							.getString("msg_success_validation"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			// message d'erreur
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(commonRessourcesBundle.getString("error_echec")
					+ ": "
					+ commonRessourcesBundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void invalidateUe() {
		FacesMessage msg = new FacesMessage();
		try {
			// invalider l'UE
			ue = uniteEnseignementService.invalidate(ue.getId());

			// message de succ�s
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(commonRessourcesBundle.getString("msg_success")
					+ ": "
					+ commonRessourcesBundle
							.getString("msg_success_modification"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			// message d'erreur
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(commonRessourcesBundle.getString("error_echec")
					+ ": "
					+ commonRessourcesBundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	/**
	 * [UeBB.deleteMc] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 nov. 2014 15:58:41
	 */
	public void deleteMc() {
		try {
			if (rattachementMcDto != null && rattachementMcDto.getId() != 0) {
				// RattachementMcDto _rmc = rattachementMcService.findById(
				// rattachementMcDto.getId());
				// if(_rmc != null) {
				// Utility.showErrorMessage(ueBundle.getString(key));
				// return;
				// }
				rattachementMcService.remove(rattachementMcDto);
				loadRattachementMc();
				rattachementMcDto = new RattachementMcDto();
				Utility.showSuccessDeleteMessage();
			}
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
		}

	}

	/**
	 * [UeBB.saveRattachementMc] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 nov. 2014 12:21:45
	 */
	public void saveRattachementMc() {
		try {
			if (rattachementMcDto != null) {
				if (ue == null && ue.getId() == 0) {
					Utility.showErrorMessage(ueBundle
							.getString("unite_enseignement_ratachement_erreur_ue_vide"));
					return;
				}
				if (rattachementMcDto.getMcId() == null) {
					Utility.showErrorMessage(ueBundle
							.getString("unite_enseignement_ratachement_erreur_mc_vide"));
					return;
				}
//				if ((rattachementMcDto.getTauxControleContinu() + rattachementMcDto.getTauxExamen() + rattachementMcDto.getTauxControleIntermediaire()) != 1) {
//					Utility.showErrorMessage(ueBundle
//							.getString("unite_enseignement_ratachement_erreur_somme_coefficient_examen_controle_continu"));
//					return;
//				}
				RattachementMcDto rmc = rattachementMcService.findUnique(
						ue.getId(), rattachementMcDto.getMcId());
				if (rmc != null && rmc.getId() != rattachementMcDto.getMcId()) {
					Utility.showErrorMessage(ueBundle
							.getString("unite_enseignement_ratachement_erreur_mc_exist_deja"));
					return;
				}
				rattachementMcDto.setUeId(ue.getId());
				boolean newRMC = (rattachementMcDto.getId() == 0);
				rattachementMcDto = rattachementMcService.insertOrUpdate(rattachementMcDto);
				loadRattachementMc();
				Utility.showSuccessSaveOrUpdateMessage(newRMC);
			}
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
		}

	}

	private TreeNode ueDetailTreeView;

	private String nodeValue;

	public TreeNode getUeDetailTreeView() {
		return ueDetailTreeView;
	}

	public void setUeDetailTreeView(TreeNode ueDetailTreeView) {
		this.ueDetailTreeView = ueDetailTreeView;
	}

	public String getNodeValue() {
		return nodeValue;
	}

	public void setNodeValue(String nodeValue) {
		this.nodeValue = nodeValue;
	}

	private void loadUeDetailTreeView() {

		ueDetailTreeView = new DefaultTreeNode("Root", null);

		new DefaultTreeNode(
				offreFormationRessourcesBundle
						.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_OBJECTIFS),
				ueDetailTreeView);
		new DefaultTreeNode(
				offreFormationRessourcesBundle
						.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_PREREQUIS),
				ueDetailTreeView);
		new DefaultTreeNode(
				offreFormationRessourcesBundle
						.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_CONTENU),
				ueDetailTreeView);
		new DefaultTreeNode(
				offreFormationRessourcesBundle
						.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_COMPETENCES),
				ueDetailTreeView);
		new DefaultTreeNode(
				offreFormationRessourcesBundle
						.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_RECOMMANDATIONS),
				ueDetailTreeView);
		new DefaultTreeNode(
				offreFormationRessourcesBundle
						.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_ORGANISATION),
				ueDetailTreeView);
		new DefaultTreeNode(
				offreFormationRessourcesBundle
						.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_EVALUATION),
				ueDetailTreeView);
		new DefaultTreeNode(
				offreFormationRessourcesBundle
						.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_AIDES_ETUDIANT),
				ueDetailTreeView);
		new DefaultTreeNode(
				offreFormationRessourcesBundle
						.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_ADMISSION),
				ueDetailTreeView);
		new DefaultTreeNode(
				offreFormationRessourcesBundle
						.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_PUBLIC_CIBLE),
				ueDetailTreeView);
		new DefaultTreeNode(
				offreFormationRessourcesBundle
						.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_OBSERVATION),
				ueDetailTreeView);

		if (node == null)
			selectNode(offreFormationRessourcesBundle
					.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_OBJECTIFS));
	}

	public void selectNode(String selectedNode) {

		if (selectedNode.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_OBJECTIFS)))
			nodeValue = ue.getObjectifs();
		else if (selectedNode.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_PREREQUIS)))
			nodeValue = ue.getPrerequis();
		else if (selectedNode.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_CONTENU)))
			nodeValue = ue.getContenu();
		else if (selectedNode.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_COMPETENCES)))
			nodeValue = ue.getCompetences();
		else if (node
				.equals(offreFormationRessourcesBundle
						.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_RECOMMANDATIONS)))
			nodeValue = ue.getRecommandations();
		else if (selectedNode.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_ORGANISATION)))
			nodeValue = ue.getOrganisation();
		else if (selectedNode.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_EVALUATION)))
			nodeValue = ue.getEvaluation();
		else if (selectedNode.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_AIDES_ETUDIANT)))
			nodeValue = ue.getAidesEtudiant();
		else if (selectedNode.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_ADMISSION)))
			nodeValue = ue.getAdmission();
		else if (selectedNode.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_PUBLIC_CIBLE)))
			nodeValue = ue.getPublicCible();
		else if (selectedNode.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_OBSERVATION)))
			nodeValue = ue.getObservation();
	}

	private void updateUeDetailValues() {

		if (node == null)
			return;

		if (node.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_OBJECTIFS)))
			ue.setObjectifs(nodeValue);
		else if (node.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_PREREQUIS)))
			ue.setPrerequis(nodeValue);
		else if (node.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_CONTENU)))
			ue.setContenu(nodeValue);
		else if (node.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_COMPETENCES)))
			ue.setCompetences(nodeValue);
		else if (node
				.equals(offreFormationRessourcesBundle
						.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_RECOMMANDATIONS)))
			ue.setRecommandations(nodeValue);
		else if (node.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_ORGANISATION)))
			ue.setOrganisation(nodeValue);
		else if (node.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_EVALUATION)))
			ue.setEvaluation(nodeValue);
		else if (node.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_AIDES_ETUDIANT)))
			ue.setAidesEtudiant(nodeValue);
		else if (node.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_ADMISSION)))
			ue.setAdmission(nodeValue);
		else if (node.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_PUBLIC_CIBLE)))
			ue.setPublicCible(nodeValue);
		else if (node.equals(offreFormationRessourcesBundle
				.getString(OfConstants.OFFRE_FORMATION_UE_EDIT_OBSERVATION)))
			ue.setObservation(nodeValue);
	}

	
	/**
	 * [UeBB.matiereConstitutiveService] Getter
	 * 
	 * @author BELBRIK Oualid on : 12 ao�t 2014 10:43:35
	 * @return the matiereConstitutiveService
	 */
	public MatiereConstitutiveService getMatiereConstitutiveService() {
		return matiereConstitutiveService;
	}

	/**
	 * [UeBB.matiereConstitutiveService] Setter
	 * 
	 * @author BELBRIK Oualid on : 12 ao�t 2014 10:43:35
	 * @param matiereConstitutiveService
	 *            the matiereConstitutiveService to set
	 */
	public void setMatiereConstitutiveService(
			MatiereConstitutiveService matiereConstitutiveService) {
		this.matiereConstitutiveService = matiereConstitutiveService;
	}

	/**
	 * [UeBB.natureUEList] Setter
	 * 
	 * @author BELBRIK Oualid on : 12 ao�t 2014 10:43:35
	 * @param natureUEList
	 *            the natureUEList to set
	 */
	public void setNatureUEList(List<SelectItem> natureUEList) {
		this.natureUEList = natureUEList;
	}

	/**
	 * [UeBB.caractereUEList] Setter
	 * 
	 * @author BELBRIK Oualid on : 12 ao�t 2014 10:43:35
	 * @param caractereUEList
	 *            the caractereUEList to set
	 */
	public void setCaractereUEList(List<SelectItem> caractereUEList) {
		this.caractereUEList = caractereUEList;
	}

	/**
	 * [UeBB.ratachementMcList] Setter
	 * 
	 * @author BELBRIK Oualid on : 12 ao�t 2014 10:43:35
	 * @param ratachementMcList
	 *            the ratachementMcList to set
	 */
	public void setRatachementMcList(List<RattachementMcDto> ratachementMcList) {
		this.ratachementMcList = ratachementMcList;
	}

	public RattachementMcDto getRattachementMcDto() {
		return rattachementMcDto;
	}

	public void setRattachementMcDto(RattachementMcDto rattachementMcDto) {
		this.rattachementMcDto = rattachementMcDto;
	}

	/**
	 * [UeBB.rattachementMcServiceImpl] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 ao�t 2014 13:33:16
	 * @return the rattachementMcServiceImpl
	 */
	public RattachementMcService getRattachementMcServiceImpl() {
		return rattachementMcServiceImpl;
	}

	/**
	 * [UeBB.rattachementMcServiceImpl] Setter
	 * 
	 * @author BELBRIK Oualid on : 13 ao�t 2014 13:33:16
	 * @param rattachementMcServiceImpl
	 *            the rattachementMcServiceImpl to set
	 */
	public void setRattachementMcServiceImpl(
			RattachementMcService rattachementMcServiceImpl) {
		this.rattachementMcServiceImpl = rattachementMcServiceImpl;
	}

	/**
	 * [UeBB.appreciationUEList] Setter
	 * 
	 * @author BELBRIK Oualid on : 14 ao�t 2014 15:34:11
	 * @param appreciationUEList
	 *            the appreciationUEList to set
	 */
	public void setAppreciationUEList(List<SelectItem> appreciationUEList) {
		this.appreciationUEList = appreciationUEList;
	}

	/**
	 * [UeBB.deleteUe] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 nov. 2014 16:13:33
	 * @param selectedUe
	 */
	public void deleteUe() {
		uniteEnseignementService.remove(ue);

	}

	/**
	 * [UeBB.availableMc] Getter
	 * 
	 * @author BELBRIK Oualid on : 26 oct. 2014 17:58:40
	 * @return the availableMc
	 */
	public List<SelectItem> getAvailableMc() {
		return listMatiere;
	}

	/**
	 * [UeBB.onRowSelect] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 nov. 2014 08:39:23
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {
		try {
			ue = ((UniteEnseignementDto) event.getObject());
			loading();

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [UeBB.mcOnRowSelect] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 nov. 2014 09:55:25
	 * @param event
	 */
	public void mcOnRowSelect(SelectEvent event) {
		try {
			rattachementMcDto = ((RattachementMcDto) event.getObject());
			loading();
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [UeBB.loading] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 nov. 2014 08:39:54
	 */
	public void loading() {
		loadUeDetailTreeView();
		loadRattachementMc();
		loadMcList();
		showDetail = true;
		// apBB.setMc(mc);
	}

	public List<SelectItem> getListMatiere() {
		FacesMessage msg = new FacesMessage();
		try {
			if (listMatiere == null || listMatiere.isEmpty()) {
				listMatiere = new ArrayList<SelectItem>();
				List<MatiereConstitutiveDto> liste = matiereConstitutiveService
						.findAll();
				if (liste != null && !liste.isEmpty()) {
					for (MatiereConstitutiveDto matiereConstitutiveDto : liste) {
						SelectItem si = new SelectItem(
								matiereConstitutiveDto.getId(),
								matiereConstitutiveDto.getLibelleFr()
										+ "( Code MC="
										+ matiereConstitutiveDto.getCode()
										+ ") (Credit="
										+ matiereConstitutiveDto.getCredits()
										+ ") (Coeficient="
										+ matiereConstitutiveDto
												.getCoefficient() + ")");
						listMatiere.add(si);
					}
				}
			}

		} catch (Exception e) {

			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(commonRessourcesBundle.getString("error_echec")
					+ ": "
					+ commonRessourcesBundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
		return listMatiere;
	}

	public void setListMatiere(List<SelectItem> listMatiere) {
		this.listMatiere = listMatiere;
	}

	/**
	 * [UeBB.getUtilBean] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 nov. 2014 08:33:30
	 * @return
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [UeBB.setUtilBean] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 nov. 2014 08:33:33
	 * @param utilBean
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}

	/**
	 * [UeBB.fullTextSearchKeyword] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 nov. 2014 08:34:11
	 * @return the fullTextSearchKeyword
	 */
	public String getFullTextSearchKeyword() {
		return fullTextSearchKeyword;
	}

	/**
	 * [UeBB.fullTextSearchKeyword] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 nov. 2014 08:34:11
	 * @param fullTextSearchKeyword
	 *            the fullTextSearchKeyword to set
	 */
	public void setFullTextSearchKeyword(String fullTextSearchKeyword) {
		this.fullTextSearchKeyword = fullTextSearchKeyword;
	}

	/**
	 * [UeBB.ueList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 nov. 2014 08:35:31
	 * @return the ueList
	 */
	public List<UniteEnseignementDto> getUeList() {
		return ueList;
	}

	/**
	 * [UeBB.ueList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 nov. 2014 08:35:31
	 * @param ueList
	 *            the ueList to set
	 */
	public void setUeList(List<UniteEnseignementDto> ueList) {
		this.ueList = ueList;
	}

	/**
	 * [UeBB.showDetail] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 nov. 2014 08:42:15
	 * @return the showDetail
	 */
	public boolean getShowDetail() {
		return showDetail;
	}

	/**
	 * [UeBB.showDetail] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 nov. 2014 08:42:15
	 * @param showDetail
	 *            the showDetail to set
	 */
	public void setShowDetail(boolean showDetail) {
		this.showDetail = showDetail;
	}

	/**
	 * [UeBB.mcList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 nov. 2014 11:12:42
	 * @return the mcList
	 */
	public List<MatiereConstitutiveDto> getMcList() {
		return mcList;
	}

	/**
	 * [UeBB.mcList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 nov. 2014 11:12:42
	 * @param mcList
	 *            the mcList to set
	 */
	public void setMcList(List<MatiereConstitutiveDto> mcList) {
		this.mcList = mcList;
	}

	/**
	 * [UeBB.mcItemList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 nov. 2014 11:20:41
	 * @return the mcItemList
	 */
	public List<SelectItem> getMcItemList() {
		return mcItemList;
	}

	/**
	 * [UeBB.mcItemList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 nov. 2014 11:20:41
	 * @param mcItemList
	 *            the mcItemList to set
	 */
	public void setMcItemList(List<SelectItem> mcItemList) {
		this.mcItemList = mcItemList;
	}

	/**
	 * [UeBB.calculCoefficientCc] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 nov. 2014 15:34:31
	 */
//	public void calculCoefficientCc() {
//		if (rattachementMcDto != null) {
//			rattachementMcDto.setTauxControleContinu(Utility
//					.double2position(1 - rattachementMcDto.getTauxExamen()));
//		}
//	}

}
