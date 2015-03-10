package dz.gov.mesrs.sii.gfc.web.jsf.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dz.gov.mesrs.sii.commons.business.util.UtilConstants;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 27-10-2014
 * @description Managed bean pour les constantes
 * 
 */
@ManagedBean(name = "gfcConstantBean")
@SessionScoped
public class GFCConstantBean {

	//
	// private static final Integer ID_COMPANY_SIEC = 1;

	/**
	 * Types des programmes natianaux
	 */
	public final static String CODE_TYPE_PROGRAMME_NATIONAL = "GFC_TYPE_PROGRAMME_NATIONAL";

	/**
	 * Types de comptes: ccp ou tresors
	 */
	public final static String CODE_TYPE_COMPTE = "GFC_TYPE_COMPTE";
	public final static String CODE_TYPE_COMPTE_CCP = "CCP";
	public final static String CODE_TYPE_COMPTE_TRESOR = "TRESOR";

	/**
	 * Types de regies: depenses ou recettes
	 */
	public final static String CODE_TYPE_REGIE = "GFC_TYPE_REGIE";

	/**
	 * Types d'ordonnateurs: principal ou secondaire
	 */
	public final static String CODE_TYPE_ORDONNATEUR = "GFC_TYPE_ORDONNATEUR";

	/**
	 * Types de budget : Initial, complementaire
	 */
	public final static String CODE_TYPE_BUDGET = "GFC_TYPE_BUDGET";

	/**
	 * Types d'unites monetaires
	 */
	public final static String CODE_TYPE_UNITE_MONETAIRE = "GFC_TYPE_UNITE_MONETAIRE";

	/**
	 * types des marches
	 */
	public final static String CODE_TYPE_MARCHE = "GFC_TYPE_MARCHE";
	public final static String CODE_TYPE_MARCHE_SOUS_CONTRAT = "MSC";

	/**
	 * Modes de passation des marches
	 */
	public final static String CODE_MODE_PASSATION = "GFC_MODE_PASSATION";

	/**
	 * Types evenements des marches
	 */
	public final static String CODE_TYPE_EVENEMENT_MARCHE = "GFC_TYPE_EVENEMENT_MARCHE";

	/**
	 * types des marches
	 */
	public final static String CODE_TYPE_PRESTATION = "GFC_TYPE_PRESTATION";

	/**
	 * Types de cloture des marches
	 */
	public final static String CODE_TYPE_CLOTURE_MARCHE = "GFC_TYPE_CLOTURE_MARCHE";

	/**
	 * Nature des demandes d'engagement des marches
	 */
	public final static String CODE_NATURE_ENGAGEMENT_MARCHE = "GFC_NATURE_ENG_MARCHE";

	/**
	 * Type des demandes d'engagement des marches
	 */
	public final static String CODE_TYPE_ENGAGEMENT_MARCHE = "GFC_TYPE_ENG_MARCHE";
	public final static String CODE_TYPE_ENGAGEMENT_MARCHE_DEPENSE = "DEP";

	/**
	 * types de documents de realisation d'un marche
	 */
	public final static String CODE_TYPE_DOCUMENT_REALISATION_MARCHE = "GFC_TYPE_DOC_REALISATION_MARCHE";
	
	/**
	 * 
	 * missions
	 */
	public final static String CODE_TYPE_TARIF = "GFC_TYPE_TARIF";
	

	/**
	 * modes de paiements dans un dossier dossier de paiement
	 */
	public final static String CODE_MODES_PAIEMENT = "GFC_MODES_PAIEMENT";

	public String getSituationCreeeCode() {
		return UtilConstants.SITUATION_CREEE_CODE;
	}

	public String getSituationValideeCode() {
		return UtilConstants.SITUATION_VALIDEE_CODE;
	}

	public String getSituationEnregistreeCode() {
		return UtilConstants.SITUATION_ENREGISTREE_CODE;
	}

	public String getSituationEnregistreeAvantValidationCode() {
		return UtilConstants.SITUATION_ENREGISTREE_AVANT_VALIDATION_CODE;
	}

	public String getSituationGenereeAutoCode() {
		return UtilConstants.SITUATION_GENEREE_AUTO_CODE;
	}

	public String getSituationTraiteeCode() {
		return UtilConstants.SITUATION_TRAITEE_CODE;
	}

	public String getSituationSoumiseValidationCode() {
		return UtilConstants.SITUATION_SOUMISE_VALIDATION_CODE;
	}

	public String getSituationAReformulerCode() {
		return UtilConstants.SITUATION_A_REFORMULER_CODE;
	}

	public String getSituationPublieeCode() {
		return UtilConstants.SITUATION_PUBLIEE_CODE;
	}

	public String getSituationClotureeCode() {
		return UtilConstants.SITUATION_CLOTUREE_CODE;
	}

	public String getSituationRejeteeCode() {
		return UtilConstants.SITUATION_REJETEE_CODE;
	}

	public String getSituationNotifieeCode() {
		return UtilConstants.SITUATION_NOTIFIEE_CODE;
	}

	public String getSituationConsolideeCode() {
		return UtilConstants.SITUATION_CONSOLIDE_CODE;
	}

	public String getSituationEnCoursPreparationCode() {
		return UtilConstants.SITUATION_EN_COURS_PREPARATION_CODE;
	}

	public String getSituationEnCoursPreparationEnregDemBudgetairesCode() {
		return UtilConstants.SITUATION_EN_COURS_PREPARATION_ENREG_DEMANDES_BUDG_CODE;
	}

	public String getSituationEnCoursPreparationEnregPrepBudgetairesCode() {
		return UtilConstants.SITUATION_EN_COURS_PREPARATION_ENREG_PROP_BUDG_CODE;
	}

	public String getDemandeSituationValideeCode() {
		return UtilConstants.DEMANDE_SITUATION_VALIDEE_CODE;
	}

	public String getDemandeSituationInvalideeCode() {
		return UtilConstants.DEMANDE_SITUATION_INVALIDEE_CODE;
	}

	public String getDemandeSituationCreeeCode() {
		return UtilConstants.DEMANDE_SITUATION_CREEE_CODE;
	}

	public String getDemandeSituationSoumiseValidationCode() {
		return UtilConstants.DEMANDE_SITUATION_SOUMISE_VALIDATION_CODE;
	}

	public String getDemandeSituationHabiliteeCode() {
		return UtilConstants.DEMANDE_SITUATION_HABILITEE_CODE;
	}

	public String getDemandeSituationRejeteeCode() {
		return UtilConstants.DEMANDE_SITUATION_REJETEE_CODE;
	}

	public String getDemandeSituationAReformulerCode() {
		return UtilConstants.DEMANDE_SITUATION_A_REFORMULER_CODE;
	}

}
