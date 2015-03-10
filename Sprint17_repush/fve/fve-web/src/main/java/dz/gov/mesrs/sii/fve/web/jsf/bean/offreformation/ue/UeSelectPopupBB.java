package dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.ue;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.context.RequestContext;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.UniteEnseignementDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.UniteEnseignementService;

/**
 * 
 * Classe de type Backingbean pour la selection d'une unité d'enseignement
 * 
 * @author kheireddine omrani
 * 
 */
@ManagedBean(name = "ueSelectPopupBB")
@RequestScoped
public class UeSelectPopupBB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4953917728171340562L;

	/**
	 * 
	 */
	public UeSelectPopupBB() {
	}

	@PostConstruct
	public void init() {
		if (fullTextSearchKeyword != null
				&& !fullTextSearchKeyword.trim().isEmpty())
			ueList = uniteEnseignementService
					.findWithFullText(fullTextSearchKeyword);
	}

	/****************************************************************************/
	/******************************** Services **********************************/
	/****************************************************************************/

	@ManagedProperty(value = "#{uniteEnseignementService}")
	private UniteEnseignementService uniteEnseignementService;

	/****************************************************************************/
	/*********************** propriétés & composants ****************************/
	/****************************************************************************/

	private List<UniteEnseignementDto> ueList;

	@ManagedProperty("#{param.fullTextSearchKeyword}")
	private String fullTextSearchKeyword;

	@ManagedProperty("#{param.parcoursId}")
	private String parcoursId;

	@ManagedProperty("#{param.refCodeSemestre}")
	private String refCodeSemestre;

	/****************************************************************************/
	/***************************** setter/getter ********************************/
	/****************************************************************************/

	// ****************** setter/getter services ********************

	/**
	 * Obtient une référence vers le service permettant la gestion des unités
	 * d'enseignement
	 * 
	 * @return référence du service de gestion des unités d'enseignement.
	 */
	public UniteEnseignementService getUniteEnseignementService() {
		return uniteEnseignementService;
	}

	/**
	 * Met à jour la référence vers le service de gestion des unités
	 * d'enseignement
	 * 
	 * @param uniteEnseignementService
	 *            : La nouvelle référence de gestion des unités
	 *            d'enseignement.
	 */
	public void setUniteEnseignementService(
			UniteEnseignementService uniteEnseignementService) {
		this.uniteEnseignementService = uniteEnseignementService;
	}

	// ****************** setter/getter ********************

	/**
	 * 
	 * @return
	 */
	public List<UniteEnseignementDto> getUeList() {
		return ueList;
	}

	public String getFullTextSearchKeyword() {
		return fullTextSearchKeyword;
	}

	public void setFullTextSearchKeyword(String fullTextSearchKeyword) {
		this.fullTextSearchKeyword = fullTextSearchKeyword;
	}

	public String getParcoursId() {
		return parcoursId;
	}

	public void setParcoursId(String parcoursId) {
		this.parcoursId = parcoursId;
	}

	public String getRefCodeSemestre() {
		return refCodeSemestre;
	}

	public void setRefCodeSemestre(String refCodeSemestre) {
		this.refCodeSemestre = refCodeSemestre;
	}

	/****************************************************************************/
	/************************** Persistance & navigation ************************/
	/****************************************************************************/

	public void ssearchFullText(String fullTextKeyword) {
		if (fullTextKeyword != null && !fullTextKeyword.trim().isEmpty())
			ueList = uniteEnseignementService.findWithFullText(fullTextKeyword);
	}

	public void addNewUe() {

	}

	/**
	 * Permet de sélectionnez une UE avant de fermer la boite de dialogue.
	 * 
	 */
	public void SelectUe(String selectedUeId) {
		RequestContext.getCurrentInstance().closeDialog(selectedUeId);
	}
	
	
	/**
	 * Permet de s�lectionner une MC et retourner vers l'appelant
	 * 
	 */
	public void selecttUe() {
		

		RequestContext.getCurrentInstance().closeDialog(null);
	}
	
	/**
	 * Permet de fermer la vue popup et retourner vers l'appelant.
	 * 
	 */
	public void close() {
		RequestContext.getCurrentInstance().closeDialog(null);
	}
}
