/**
 * [dz.gov.mesrs.sii.fve.business.util.UtilConstants.java] 
 * @author  Rafik LAIB on : 3 mai 2014  23:27:30
 */
package dz.gov.mesrs.sii.fve.business.util;

/**
 * @author Rafik LAIB on : 3 mai 2014 23:27:30
 */
public class UtilConstants {

	// Entites Codes
	public static final String ENTITE_OFFRE_FORMATION_CODE = "OF";
	public static final String ENTITE_DEMANDE_CODE = "DM";
	public static final String ENTITE_DECISION_CODE = "DC";
	public static final String ENTITE_TACHE_CODE = "CH";
	public static final String ENTITE_DOSSIER_CODE = "DOSS";
	public static final String ENTITE_PARCOURS_INDIVIDUALISE_CODE = "PI";
	public static final String ENTITE_DOSSIER_TRANSFERT_ETUDIANT = "DTC";
	public static final String ENTITE_PLANNING_SESSION_CODE = "PS";
	public static final String ENTITE_FICHE_VOEUX_REINSCRIPTION = "FVR";
	public static final String ENTITE_FICHE_VOEUX_ENSEIGNANT = "FHVE";
	public static final String ENTITE_FICHE_SERVICES_ENSEIGNANT = "FHSE";
	public static final String ENTITE_CHARGE_PEDAGOGIQUE_ENSEIGNANT = "CHDG";
	public static final String ENTITE_CHARGE_SERVICE_FAIT = "SNCPG";
	public static final String ENTITE_EVAL_CONTROL_CONTINU_CODE = "ECC";
	public static final String ENTITE_DELIBERATION_SESSION_CODE = "DLB";
	public static final String ENTITE_CONCOURS = "CCR";
	public static final String ENTITE_DEMANDE_PREINSCRIPTION = "DPR";

	// Code des differentes situations d'une offre de formation;
	public static final String OFFRE_FORMATION_SITUTAION_CREEE_CODE = "CRE";
	public static final String OFFRE_FORMATION_SITUTAION_ENREGISTREE_PARTIELLEMENT_CODE = "EPR";
	public static final String OFFRE_FORMATION_SITUTAION_ENREGISTREE_DEFINITIVEMENT_CODE = "EDF";
	public static final String OFFRE_FORMATION_SITUTAION_VALIDEE_CODE = "VLD";
	public static final String OFFRE_FORMATION_SITUTAION_HABILITEE_CODE = "HAB";
	public static final String OFFRE_FORMATION_SITUTAION_REJETEE_CODE = "REJ";
	public static final String OFFRE_FORMATION_SITUTAION_A_REFORMULER_CODE = "RFM";
	public static final String OFFRE_FORMATION_SITUTAION_ANNULEE_CODE = "ANN";
	public static final String OFFRE_FORMATION_SITUTAION_SUPPRIMEE_CODE = "SUP";
	public static final String OFFRE_FORMATION_SITUTAION_AMENDEE_CODE = "AMN";
	public static final String OFFRE_FORMATION_SITUTAION_SOUMISE_HABILITATION_CODE = "SMSH";
	public static final String OFFRE_FORMATION_SITUTAION_SOUMISE_AMENDEMENT_CODE = "SMSA";
	public static final String OFFRE_FORMATION_SITUTAION_DEMANDE_EN_INSTANCE_CODE = "ADMS";

	public static final String SITUTAION_CREEE_CODE = "CRE";
	public static final String SITUTAION_VALIDEE_CODE = "VLD";
	public static final String SITUTAION_ENREGISTREE_CODE = "ERG";
	public static final String SITUTAION_ENREGISTREE_AVANT_VALIDATION_CODE = "ERGAV";
	public static final String SITUTAION_GENEREE_AUTO_CODE = "GNR";
	public static final String SITUTAION_TRAITEE_CODE = "TRT";
	public static final String SITUTAION_SOUMISE_VALIDATION_CODE = "SMSV";
	public static final String SITUTAION_A_REFORMULER_CODE = "RFM";
	public static final String SITUTAION_PUBLIEE_CODE = "PBL";
	public static final String SITUTAION_CLOTUREE_CODE = "CLTR";
	public static final String SITUTAION_REJETEE_CODE = "REJ";

	// Code des differentes situations d'une demande
	public static final String DEMANDE_SITUTAION_VALIDEE_CODE = "VLD";
	public static final String DEMANDE_SITUTAION_INVALIDEE_CODE = "INV";
	public static final String DEMANDE_SITUTAION_CREEE_CODE = "CRE";
	public static final String DEMANDE_SITUTAION_SOUMISE_VALIDATION_CODE = "SMSV";
	public static final String DEMANDE_SITUTAION_HABILITEE_CODE = "HAB";
	public static final String DEMANDE_SITUTAION_REJETEE_CODE = "REJ";
	public static final String DEMANDE_SITUTAION_A_REFORMULER_CODE = "RFM";

	// codes des differentes situation d'une demmande de transfert d'un etudiant
	public static final String DEMANDE_TRANSFERT_ETUDIANT_SITUTAION_CREEE_CODE = "DT_CRE";
	public static final String DEMANDE_TRANSFERT_ETUDIANT_ENREGISTREE_CODE = "DT_ERG";

	public static final String DEMANDE_TRANSFERT_ETUDIANT_VALIDEE_ETUDIANT_CODE = "DT_VPE";
	public static final String DEMANDE_TRANSFERT_ETUDIANT_ANNULER_ETUDIANT_CODE = "DT_APE";

	public static final String DEMANDE_TRANSFERT_ETUDIANT_INVALIDEE_GESTIONNAIRE_AC_CODE = "DT_ING";

	public static final String DEMANDE_TRANSFERT_ETUDIANT_REJETEE_GESTIONNAIRE_AC_CODE = "DT_REJ_ETA";
	public static final String DEMANDE_TRANSFERT_ETUDIANT_REJETEE_GESTIONNAIRE_OR_CODE = "DT_REJ_ETO";

	public static final String DEMANDE_TRANSFERT_ETUDIANT_ENCOURS_TRAITMENT_GESTIONNAIRE_AC_CODE = "DT_ETGA";
	public static final String DEMANDE_TRANSFERT_ETUDIANT_ENCOURS_TRAITMENT_GESTIONNAIRE_OR_CODE = "DT_ETGO";

	// codes des differentes situation d'une fiche de voeux de reinscription
	// d'un etudiant
	public static final String FICHE_VOEUX_REINSCRIPTION_SITUTAION_CREEE_CODE = "FVR_CRE";
	public static final String FICHE_VOEUX_REINSCRIPTION_SITUTAION_VALIDEE_ETUDIANT_CODE = "FVR_VPE";
	public static final String FICHE_VOEUX_REINSCRIPTION_SITUTAION_ENCOURS_TRAITMENT_ETUDIANT_CODE = "FVR_ETE";
	public static final String FICHE_VOEUX_REINSCRIPTION_SITUTAION_ENCOURS_TRAITMENT_EQUIPE_PEDAGOGIQUE_CODE = "FVR_ETEP";
	public static final String FICHE_VOEUX_REINSCRIPTION_SITUTAION_VALIDEE_EQUIPE_PEDAGOGIQUE_CODE = "FVR_VPEP";
	public static final String FICHE_VOEUX_REINSCRIPTION_SITUTAION_ENCOURS_TRAITMENT_REINSCRIPTION_CODE = "FVR_ETR";
	public static final String FICHE_VOEUX_REINSCRIPTION_SITUTAION_TRAITEE_CODE = "FVR_T";

	// Commission
	public static final String DEMANDE_TRANSFERT_ETUDIANT_ENCOURS_TRAITMENT_COM_CODE = "DT_ETC";
	public static final String DEMANDE_TRANSFERT_ETUDIANT_ACCEPTE_COM_CODE = "DT_AC";
	public static final String DEMANDE_TRANSFERT_ETUDIANT_NON_ACCEPTE_COM_CODE = "DT_NAC";

	// Types demandes de transfert
	public static final String DEMANDE_TRANSFERT_ETUDIANT_INTER_UNIVERSITAIRE = "TIU";
	public static final String DEMANDE_TRANSFERT_ETUDIANT_INTERNE = "TI";
	public static final String DEMANDE_TRANSFERT_ETUDIANT_INTERNECRU = "TIC";

	// Codes Types demandes
	public static final String TYPE_DEMANDE_EVALUATION_CODE = "EVL";
	public static final String TYPE_DEMANDE_HABILITATION_CODE = "HAB";
	public static final String TYPE_DEMANDE_AMENDEMENT_CODE = "AMD";

	// Codes Etapes
	public static final String EVALUATION_ETAPE_SOUMISSION_DEMANDE_CODE = "SDME_OF";
	public static final String EVALUATION_ETAPE_VALIDATION_DEMANDE_CODE = "VDME_OF";
	public static final String HABILITATION_SOUMISSION_DEMANDE_CODE = "SDMH_OF";
	public static final String HABILITATION_VALIDATION_DEMANDE_CODE = "VDMH_OF";
	// Codes Roles
	public static final String EVALUATION_ROLE_SOUMISSION_DEMANDE_CODE = "PP";
	public static final String EVALUATION_ROLE_VALIDATION_DEMANDE_CODE_1 = "RespDom";
	public static final String EVALUATION_ROLE_VALIDATION_DEMANDE_CODE_2 = "ChefDept";
	public static final String EVALUATION_ROLE_VALIDATION_DEMANDE_CODE_3 = "Doyen";
	public static final String HABILITATION_ROLE_PORTEUR_PROJET = "PP";
	public static final String HABILITATION_ROLE_RESPONSABLE_DOMOMAINE = "RespDom";
	public static final String HABILITATION_ROLE_CHEF_DEPARTEMENT = "ChefDept";
	public static final String HABILITATION_ROLE_COMMITE_SCIENTIFIQUE_DEPARTEMENT = "ComSDept";
	public static final String HABILITATION_ROLE_CONSEIL_SCIENTIFIQUE_FACULTE = "ConSFacult";
	public static final String HABILITATION_ROLE_DOYEN = "Doyen";
	public static final String HABILITATION_ROLE_CONS_ETABLISSEMENT = "ConSEtab";
	public static final String HABILITATION_ROLE_CHEF_ETABLISSEMENT = "ChefEtab";
	public static final String HABILITATION_ROLE_VICE_RECTEUR = "VRect";
	public static final String HABILITATION_ROLE_CONFERENCE_REGIONALE_UNIVERSITE = "ConfRU";
	public static final String HABILITATION_ROLE_DIR_GEPS = "DirGEPS";
	public static final String HABILITATION_ROLE_COMITE_PEDAGOGIQUE_NATIONALE = "ComPedNat";
	public static final String HABILITATION_ROLE_CNH_COMMNH = "CNH/CommNH";
	public static final String HABILITATION_ROLE_MINISTRE = "Ministre";

	// Code des differentes situations d'une tache
	public static final String TACHE_SITUTAION_EN_COURS_CODE = "CRS";
	public static final String TACHE_SITUTAION_TERMINEE_CODE = "TRM";

	// Code des actions
	public static final String ACTION_SMTR = "SMTR";
	public static final String ACTION_VLDR = "VLDR";
	public static final String ACTION_NTFR = "NTFR";
	public static final String ACTION_HBTR = "HBTR";
	public static final String ACTION_RJTR = "RJTR";
	public static final String ACTION_RFMR = "RFMR";
	public static final String ACTION_ENGR = "ENGR";
	public static final String ACTION_ANLR = "ANLR";
	public static final String ACTION_INVR = "INVR";

	/*
	 * ======================================================= Dozer files
	 * =======================================================
	 */
	//public static final String DOZER_PIECE_CONSTITUTIVE_MAPPING = "dozer-piece-constitutive-mapping.xml";
	//public static final String DOZER_SITUATION_SPORTIVE_MAPPING_NAME = "dozer-situation-sportive-mapping.xml";
	//public static final String DOZER_GROUPE_PEDAGOGIQUE_MAPPING_NAME = "dozer-groupe-pedagogique-mapping.xml";
	//public static final String DOZER_DOSSIER_INSCRIPTION_ADMINISTRATIF_MAPPING_NAME = "dozer-dossier-inscription-administratif-mapping.xml";
	//public static final String DOZER_GROUPE_PEDAGOGIQUE_AP_MAPPING_NAME = "dozer-groupe-pedagogique-ap-mapping.xml";
	//public static final String DOZER_REINTEGRATION_MAPPING_NAME = "dozer-reintegration-mapping.xml";
	//public static final String DOZER_EVAL_CONTROLE_CONTINU_MAPPING_NAME = "dozer-eval-controle-continu-mapping.xml";
	//public static final String DOZER_BILAN_CONTROLE_CONTINU_MAPPING_NAME = "dozer-bilan-controle-continu-mapping.xml";

	// Importation de dossier bacheliers
	public static final String BAC_IMPORT_TYPE_BACHELOR_FILE_CODE = "DBC";
	public static final String BAC_IMPORT_TYPE_BACHELOR_AFFECTATION_CODE = "AFF";
	public static final String BAC_IMPORT_TYPE_BACHELOR_FILES_AFFECTATIONS_CODE = "ALL";
	public static final String BAC_IMPORT_TYPE_BACHELOR_NOTES_CODE = "NTB";
	public static final String BAC_IMPORT_STATUS_LAUNCHED_CODE = "En cours";
	public static final String BAC_IMPORT_STATUS_TERMINATED_CODE = "Terminee";
	public static final String BAC_IMPORT_STATUS_CANCELED_CODE = "Annulee";

	public static final String BAC_BACHELOR_PHOTO_MALE_NAME = "boy.png";
	public static final String BAC_BACHELOR_PHOTO_FEMALE_NAME = "girl.png";
	public static final String BAC_BACHELOR_PHOTO_DEFAULT_NAME = "default_user.png";
	public static final String BAC_BACHELOR_FEMALE_CODE = "FEMININ";
	public static final String BAC_BACHELOR_MALE_CODE = "MASCULIN";

	/*
	 * ======================================================= Groupes
	 * pédagogiques =======================================================
	 */
	public static final String REF_TYPE_GROUPE_PEDAGOGIQUE_CODE = "FGRP001";

	public static final String TYPE_DIPLOME_CODE = "001";

	// Codes types de dossiers (etudiant + inscription 1ere annee)
	// Ajoute par Rafik : 24/06/2014

	public static final String LMD_PREMIER_CYCLE_CODE = "L";
	public static final String CLASS_PREMIER_CYCLE_CODE = "PC";
	
	public static final String TYPE_DOSSIER_ETUDIANT_CODE = "D001";
	public static final String TYPE_DOSSIER_INSCRIPTION_PREMIERE_ANNEE_CODE = "D002";

	// Codes types inscription
	// Ajoute par Rafik : 24/06/2014
	public static final String TYPE_INSCRIPTION_LMD_LICENCE_CODE = "TYP_INSC_INSC_L";
	public static final String TYPE_INSCRIPTION_CLASSIQUE_CODE = "TYP_INSC_INSC_C";

	// Codes niveaux inscriptions 1ere annee
	// Ajoute par Rafik : 24/06/2014
	public static final String NIVEAU1_INSCRIPTION_LMD_CODE = "L1";
	public static final String NIVEAU2_INSCRIPTION_LMD_CODE = "L2";
	public static final String NIVEAU1_INSCRIPTION_CLASSIQUE_CODE = "C1";
	public static final String CODE_TYPE_DOSSIER_ETUDIANT = "D001";
	public static final String CODE_TYPE_DOSSIER_INSCRIPTION_PREMIERE_ANNEE = "D002";
	public static final int CODIFICATION_MATRICULE_ETUDIANT_LENGTH = 11;
	public static final int CODIFICATION_MATRICULE_BACHELIER_LENGTH = 8;
	public static final String CODIFICATION_FVE_DIPLOME_ORDER_LENGTH_KEY = "CODIFICATION_FVE_DIPLOME_ORDER_LENGTH";

	// parametres de l'etablissements
	public static final String ETABLISSEMENT_MAX_DEMANDES_TRANSFERT = "MAX_DEMANDES_TRANSFERT";

	/******************************** TYPE DE BILAN **********************************/
	public static final int BILAN_TYPE_SESSION = 0;
	public static final int BILAN_TYPE_PERIODE = 1;
	public static final int BILAN_TYPE_ANNUEL = 2;
	
	//Les codes des jours
	public static final String DIMANCHE_CODE="DIM";
	public static final String LUNDI_CODE="LUN";
	public static final String MARDI_CODE="MAR";
	public static final String MERCREDI_CODE="MER";
	public static final String JEUDI_CODE="JEU";
	public static final String VENDREDI_CODE="VEN";
	public static final String SAMEDI_CODE="SAM";

	/**
	 * [UtilConstants.LikeContain] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 juin 2014 10:56:01
	 * @param param
	 * @return
	 */
	public static String LikeContain(Object param) {
		String result;
		result = "'%" + param + "%'";
		return result;
	}

	/**
	 * [UtilConstants.quotedString] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 juin 2014 10:56:04
	 * @param value
	 * @return
	 */
	public static String quotedString(Object value) {
		return "'" + value + "'";
	}

	public static final String PERIODE_PARAM_CODE_DATE_DEBUT = "DATE_DEBUT";
	public static final String PERIODE_PARAM_CODE_DATE_FIN = "DATE_FIN";
	
	public static final String NC_TYPE_CONCOURS_NAME ="TYPE_CONCOURS";
	public static final String NC_TYPE_CONCOURS_ENTRETIEN ="TC001";
	public static final String NC_TYPE_CONCOURS_EXAMEN ="TC002";
	public static final String GROUPE_RESPONSABLE_EXAMEN_NAME ="Resp  Examens";
	
	public static final String NC_NAME_NATIONALITE =  "NATIONALITE";
	public static final String NC_NATIONALITE_ALG =  "ALG";
	
	// Codes types de formation
	public static final String TYPE_FORMATION_LMD_CODE =  "LMD";
	public static final String TYPE_FORMATION_CLASSIQUE_CODE =  "CLS";
	
	//Codes Types Session Examens:
	public static final String SESSION_NORMALE_CODE = "session_1";
	public static final String SESSION_REMPLACEMENT_CODE = "remplacement";
	public static final String SESSION_RATTRAPPAGE_CODE = "rattrappage";
	
}
