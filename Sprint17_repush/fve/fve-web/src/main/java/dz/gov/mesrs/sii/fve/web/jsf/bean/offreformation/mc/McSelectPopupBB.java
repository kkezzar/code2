package dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.mc;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.RattachementMcDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.MatiereConstitutiveDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.MatiereConstitutiveService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.RattachementMcService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;

/**
 * Classe de type BackingBean permettant la selection d'une mati�res
 * constitutive (� rajouter par exemple � une UE)
 * 
 * @author kheireddine omrani
 * 
 */
@ManagedBean(name = "mcSelectPopup")
@RequestScoped
public class McSelectPopupBB implements Serializable {

	private static final long serialVersionUID = 4953917728171340562L;

	/**
	 * 
	 */
	public McSelectPopupBB() {
		
		super();

		FacesContext facesContext = FacesContext.getCurrentInstance();

		// Initialisation des ressources bundles
		commonRessourcesBundle = Utility
				.getCommonRessourcesBundle(facesContext);
	}

	@PostConstruct
	public void init() {
		if (fullTextSearchKeyword != null
				&& !fullTextSearchKeyword.trim().isEmpty())
			mcList = matiereConstitutiveService
					.findWithFullText(fullTextSearchKeyword);
	}

	/****************************************************************************/
	/******************************** Services **********************************/
	/****************************************************************************/

	@ManagedProperty(value = "#{matiereConstitutiveService}")
	private MatiereConstitutiveService matiereConstitutiveService;

	/**
	 * R�f�rence vers le service de gestion des rattachements Mc.
	 */
	@ManagedProperty(value = "#{rattachementMcService}")
	private RattachementMcService rattachementMcService;

	/****************************************************************************/
	/*********************** propri�t�s & composants ****************************/
	/****************************************************************************/

	/**
	 * Liste des mati�res contitutive r�sultat de la recherche
	 */
	private List<MatiereConstitutiveDto> mcList;

	/**
	 * Le crit�re de recherche en texte int�grale, il est r�cup�r� � partir des
	 * param�tres de l'url
	 */
	@ManagedProperty("#{param.fullTextSearchKeyword}")
	private String fullTextSearchKeyword;

	@ManagedProperty("#{param.ueId}")
	private String ueId;
	
	@ManagedProperty("#{param.checkId}")
	private boolean checkId;

	private int selectedMcId;
	
	private RattachementMcDto rattachement;
	
	private ResourceBundle commonRessourcesBundle;

	/****************************************************************************/
	/********************************* getter/setter ***************************/
	/****************************************************************************/

	// ****************** getter/setter des services **********************/

	/**
	 * Obtient la r�f�rence vers le service de gestion des mati�res
	 * constitutive.
	 * 
	 * @return le service de gestion des udes mati�res constitutive.
	 */
	public MatiereConstitutiveService getMatiereConstitutiveService() {
		return matiereConstitutiveService;
	}

	/**
	 * Met � jour la r�f�rence vers le service de gestion des mati�res
	 * constitutive.
	 * 
	 * @param matiereConstitutiveService
	 *            : La nouvelle r�f�rence de l'objet service de gestion des
	 *            mati�res constitutive.
	 */
	public void setMatiereConstitutiveService(
			MatiereConstitutiveService matiereConstitutiveService) {
		this.matiereConstitutiveService = matiereConstitutiveService;
	}

	public RattachementMcService getRattachementMcService() {
		return rattachementMcService;
	}

	public void setRattachementMcService(
			RattachementMcService rattachementMcService) {
		this.rattachementMcService = rattachementMcService;
	}

	// ***************** getter/setter des propri�t�s *******************/

	/**
	 * Obtient la liste des mati�res contitutive r�sultat de la recherche
	 * 
	 * @return
	 */
	public List<MatiereConstitutiveDto> getMcList() {
		return mcList;
	}

	/**
	 * Obtient le crit�re de recherche full texte saisie par l'utilisateur.
	 * 
	 * @return : le crit�re de recherche simple (ou par texte int�grale)
	 */
	public String getFullTextSearchKeyword() {
		return fullTextSearchKeyword;
	}

	/**
	 * Met � jour la valeur du crit�re de recherche full texte saisie par
	 * l'utilisateur.
	 * 
	 * @param fullTextSearchKeyword
	 */
	public void setFullTextSearchKeyword(String fullTextSearchKeyword) {
		this.fullTextSearchKeyword = fullTextSearchKeyword;
	}

	public String getUeId() {
		return ueId;
	}

	public void setUeId(String ueId) {
		this.ueId = ueId;
	}

	public int getSelectedMcId() {
		return selectedMcId;
	}

	public void setSelectedMcId(int selectedMcId) {
		this.selectedMcId = selectedMcId;
	}

	/****************************************************************************/
	/************************** Persistance & navigation ************************/
	/****************************************************************************/

	/**
	 * 
	 * @param value
	 */
	public void searchFullText(String fullTextKeyword) {
		if (fullTextKeyword != null && !fullTextKeyword.trim().isEmpty())
			mcList = matiereConstitutiveService
					.findWithFullText(fullTextKeyword);
	}

	/**
	 * Permet de s�lectionner une MC et retourner vers l'appelant
	 * 
	 */
	public void selectMc() {
		
		FacesMessage msg = new FacesMessage();			 
			 
			 try {
				 if (ueId != null && !ueId.isEmpty() && !ueId.equals("0") &&
						 !ueId.equals("null")) {
						 rattachement = new RattachementMcDto();
						 rattachement.setMcId(selectedMcId);
						 rattachement.setUeId(Integer.valueOf(ueId));
						 rattachement.setOptionnelle(Boolean.valueOf(checkId));
						 rattachementMcService.insertOrUpdate(rattachement);
						 }
				 
				 RequestContext.getCurrentInstance().closeDialog(null);
				 
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
	 * Permet de fermer la vue popup et retourner vers l'appelant.
	 * 
	 */
	public void close() {
		RequestContext.getCurrentInstance().closeDialog(null);
	}

	/**
	 * [McSelectPopupBB.rattachement] Getter 
	 * @author BELBRIK Oualid on : 12 ao�t 2014  20:00:54
	 * @return the rattachement
	 */
	public RattachementMcDto getRattachement() {
		return rattachement;
	}

	/**
	 * [McSelectPopupBB.rattachement] Setter 
	 * @author BELBRIK Oualid on : 12 ao�t 2014  20:00:54
	 * @param rattachement the rattachement to set
	 */
	public void setRattachement(RattachementMcDto rattachement) {
		this.rattachement = rattachement;
	}

	/**
	 * [McSelectPopupBB.checkId] Getter 
	 * @author BELBRIK Oualid on : 9 sept. 2014  18:13:56
	 * @return the checkId
	 */
	public boolean isCheckId() {
		return checkId;
	}

	/**
	 * [McSelectPopupBB.checkId] Setter 
	 * @author BELBRIK Oualid on : 9 sept. 2014  18:13:56
	 * @param checkId the checkId to set
	 */
	public void setCheckId(boolean checkId) {
		this.checkId = checkId;
	}

}
