/**
 * [dz.gov.mesrs.sii.fve.business.util.UtilConstants.java] 
 * @author  Rafik LAIB on : 3 mai 2014  23:27:30
 */
package dz.gov.mesrs.sii.commons.business.util;

/**
 * 
 * @author Mounir.MESSAOUDI on : 30 nov. 2014 15:07:00
 */
public class UtilConstants {

	// Entites Codes
	/**
	 * Entite commun qui peux avoir divers situations (cree, validee, ....etc)
	 */
	public static final String ENTITE_ENTITE_CODE = "ENT";

	public static final String ENTITE_DEMANDE_CODE = "DM";
	public static final String ENTITE_DECISION_CODE = "DC";
	public static final String ENTITE_TACHE_CODE = "CH";
	public static final String ENTITE_DOSSIER_CODE = "DOSS";
	public static final String ENTITE_DOSSIER_EMPLOYE_CODE = "DE";
	public static final String ENTITE_BESOIN_RECRUTEMENT_CODE = "BR";
	public static final String ENTITE_CANDIDAT_EMPLOYE_CODE = "CE";
	public static final String ENTITE_STAGE_CODE = "STG";
	public static final String ENTITE_PROPOSITION_AVANCEMENT_CODE = "PROAV";

	public static final String ENTITE_MUTATION_EMPLOYE_CODE = "MTE";
	public static final String ENTITE_CONGE_EMPLOYE_CODE = "GRH_CNGE";
	public static final String ENTITE_BESOIN_FORMATION_CODE = "GRH_BF";
	public static final String ENTITE_CYCLE_FORMATION_CODE = "GRH_CF";
	public static final String ENTITE_SESSION_FORMATION_CODE = "GRH_SF";
	
	public static final String SITUATION_CREEE_CODE = "CRE";
	public static final String SITUATION_A_VALIDER_CODE = "AVLD";
	public static final String SITUATION_VALIDEE_CODE = "VLD";
	public static final String SITUATION_ENREGISTREE_CODE = "ERG";
	public static final String SITUATION_ENREGISTREE_AVANT_VALIDATION_CODE = "ERGAV";
	public static final String SITUATION_GENEREE_AUTO_CODE = "GNR";
	public static final String SITUATION_TRAITEE_CODE = "TRT";
	public static final String SITUATION_SOUMISE_VALIDATION_CODE = "SMSV";
	public static final String SITUATION_A_REFORMULER_CODE = "RFM";
	public static final String SITUATION_PUBLIEE_CODE = "PBL";
	public static final String SITUATION_CLOTUREE_CODE = "CLTR";
	public static final String SITUATION_REJETEE_CODE = "REJ";
	public static final String SITUATION_EN_STAGE_CODE = "ESTG";
	public static final String SITUATION_TITULARISE_CODE = "TTLRS";
	public static final String SITUATION_STAGE_PROLONGE_CODE = "STGP";
	public static final String SITUATION_STAGE_CONCLUANT_CODE = "STGC";
	public static final String SITUATION_LICENCIE_CODE = "LC";
	public static final String SITUATION_DEMISSIONNE_CODE = "DMS";
	public static final String SITUATION_SOUMISE_SELECTION_CANDIDAT_CODE = "SMSSLCTC";
	public static final String SITUATION_SOUMISE_DECISION_CODE = "SMSDCS";
	public static final String SITUATION_SOUMISE_DESISTEMENT_CANDIDAT_CODE = "SMSDSSTMC";
	public static final String SITUATION_SOUMISE_INSTALLATION_CANDIDAT_CODE = "SMSINSTLTC";
	public static final String SITUATION_SOUMISE_VALIDATION_CANDIDAT_CODE = "SMSVLDTC";
	public static final String SITUATION_SOUMISE_CLOTURE_RECRUTEMENT_CODE = "SMSCLTBR";
	public static final String SITUATION_SOUMISE_ENREGISTREMENT_DOSSIER_CANDIDAT_CODE = "SMSEDC";
	public static final String SITUATION_EN_COURS_PREPARATION_CODE = "ECP";
	public static final String SITUATION_NOTIFIEE_CODE = "SNOTIF";
	public static final String SITUATION_INSTALLEE_CODE = "INSTL";
	public static final String SITUATION_CONSOLIDE_CODE = "CNSOL";
	public static final String SITUATION_FINALE = "FINAL";
	public static final String SITUATION_SIGNEE = "SIGNEE";
	public static final String SITUATION_ANNULEE_CODE = "ANN";
	public static final String SITUATION_EVALUEE_CODE = "EVLE";
	public static final String SITUATION_ENVOYE_CODE = "ENVOYEE";

	// Code des differentes situations d'un projet de budget
	public static final String SITUATION_A_ENVOYER_MF_CODE = "GFC_PB_AEMF";
	public static final String SITUATION_ENVOYE_MF_CODE = "GFC_PB_EMF";

	// Code des differentes situations d'une fiche des besoins (GFC)
	public static final String SITUATION_EN_COURS_PREPARATION_ENREG_DEMANDES_BUDG_CODE = "GFC_ECPEDB";
	public static final String SITUATION_EN_COURS_PREPARATION_ENREG_PROP_BUDG_CODE = "GFC_ECPEPB";
	public static final String SITUATION_A_ENVOYER_MESRS_CODE = "GFC_AEMESRS";
	public static final String SITUATION_ENVOYEE_MESRS_CODE = "GFC_EMESRS";

	// codes des differents situations d'une decision de notification
	public static final String SITUATION_REPARTI = "REPARTI";

	// Code des differentes situations d'une demande
	public static final String DEMANDE_SITUATION_VALIDEE_CODE = "VLD";
	public static final String DEMANDE_SITUATION_INVALIDEE_CODE = "INV";
	public static final String DEMANDE_SITUATION_CREEE_CODE = "CRE";
	public static final String DEMANDE_SITUATION_SOUMISE_VALIDATION_CODE = "SMSV";
	public static final String DEMANDE_SITUATION_HABILITEE_CODE = "HAB";
	public static final String DEMANDE_SITUATION_REJETEE_CODE = "REJ";
	public static final String DEMANDE_SITUATION_A_REFORMULER_CODE = "RFM";

	// Les codes des jours
	public static final String DIMANCHE_CODE = "DIM";
	public static final String LUNDI_CODE = "LUN";
	public static final String MARDI_CODE = "MAR";
	public static final String MERCREDI_CODE = "MER";
	public static final String JEUDI_CODE = "JEU";
	public static final String VENDREDI_CODE = "VEN";
	public static final String SAMEDI_CODE = "SAM";

	// entite GFC
	public static final String ENTITE_GFC_PROJET_BUDGET_CODE = "GFC_PROJET_BUDGET";
	public static final String ENTITE_GFC_REGIE_CODE = "GFC_REGIE";
	public static final String ENTITE_GFC_PROGRAMME_NATIONNAUX_CODE = "GFC_PROG_NAT";
	public static final String ENTITE_GFC_FICHE_BESOINS_CODE = "GFC_FICHE_BESOINS";
	public static final String ENTITE_GFC_EXERCICE_BUDGETAIRE_CODE = "GFC_EXERCICE_BUDGETAIRE";
	public static final String ENTITE_GFC_DECISION_NOTIFICATION = "GFC_DECISION_NOTIFICATION";
	public static final String ENTITE_GFC_DECISION_REPARTITION = "GFC_DECISION_REPARTITION";
	public static final String ENTITE_GFC_MOUVEMENT = "GFC_MOUVEMENT";

	public static final String ENTITE_GFC_MARCHE = "GFC_MARCHE";
	public static final String ENTITE_GFC_ENGAGEMENT_MARCHE = "GFC_ENGAGEMENT_MARCHE";
	public static final String ENTITE_GFC_EVENT_MARCHE = "GFC_EVENT_MARCHE";
	public static final String ENTITE_GFC_DOC_REALISATION = "GFC_DOC_REALISATION";

	public static final String ENTITE_GFC_MISSION = "GFC_MISSION";
	public static final String ENTITE_GFC_TARIF_MISSION = "GFC_TARIF_MISSION";
	public static final String ENTITE_GFC_DOSSIER_PAIEMENT = "GFC_DOSSIER_PAIEMENT";
}
