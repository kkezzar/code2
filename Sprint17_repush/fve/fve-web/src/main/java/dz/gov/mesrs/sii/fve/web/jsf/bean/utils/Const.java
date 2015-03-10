package dz.gov.mesrs.sii.fve.web.jsf.bean.utils;

public class Const {

	// Bundles resources names (see faces-config.xml)

	public static final String COMMON_RESSOURCES_BUNDLE_FILE_NAME = "commonmsgs";
	public static final String OFFRE_FORMATION_RESSOURCES_BUNDLE_FILE_NAME = "ofmsgs";
	public static final String MATIERE_CONSTITUTIVE_RESSOURCES_BUNDLE_FILE_NAME = "matiereConstitutiveMsgs";
	public static final String UNITE_ENSEIGNEMENT_RESSOURCES_BUNDLE_FILE_NAME = "uniteEnseignementMsgs";
	public static final String REPORTING_MESSAGES_FILE_NAME = "reportingMsgs";
	public static final String PIECE_CONSTITUTIVE_RESSOURCES_BUNDLE_FILE_NAME = "pieceMsgs";
	public static final String DOSSIER_INSCRIPTION_ADMINISTRATIF_RESSOURCES_BUNDLE_FILE_NAME = "dossierInscriptionAdministrativeMsgs";

	// Noms des nomenclature dans le referentiel
	public static final String LMD_MODE_EVALUATION = "LMD_MODE_EVALUATION";
	public static final String LANGUE = "LANGUE";
	public static final String LMD_TYPE_MC = "LMD_TYPE_MC";
	public static final String LMD_NATURE_UE = "LMD_NATURE_UE";
	public static final String LMD_CARACTERE_UE = "LMD_CARACTERE_UE";
	//public static final String LMD_TYPE_UE = "LMD_TYPE_UE";
	public static final String LMD_APPRECIATION_UE = "LMD_APPRECIATION_UE";
	public static final String NC_LMD_TYPE_DECISION_ADMISSION = "LMD_TYPE_DECISION_ADMISSION";
	public static final String NC_LMD_TYPE_DECISION_ADMISSION_PERIODE = "LMD_TYPE_DECISION_ADMISSION_PERIODE";
	public static final String NC_LMD_SIGNATURE_DIPLOME = "LMD_SIGNATAIRE_DIPLOME";
	
	public static final String NC_LMD_TYPE_DECISION_ADMISSION_VALIDATION_PERIODE_VALUE = "DAP001";

	public static final String LMD_TYPE_DOSSIER_INSCRIPTION = "LMD_TYPE_DOSSIER_INSCRIPTION";
	public static final String LMD_NATURE_DOSSIER_INSCRIPTION = "LMD_NATURE_DOSSIER_INSCRIPTION";
	public static final String LMD_STATUT_ETUDIANT = "LMD_STATUT_ETUDIANT";
	public static final String LMD_CYCLE = "LMD_CYCLE";
	public static final String LMD_PALIER = "LMD_PALIER";
	public static final String NC_ROLE = "ROLE";
	public static final String REF_CODE_ROLE_MEMBRE = "RGRP001";
	public static final String REF_CODE_ROLE_ETUDIANT = "ETDROLE";
	public static final String PARAM_FVE_CODE_GROUPE_BACHELIERS_KEY = "FVE_CODE_GROUPE_BACHELIERS";
	public static final String PARAM_FVE_CODE_GROUPE_RESPONSABLE_EXAMEN__KEY = "FVE_CODE_GROUPE_RESPONSABLE_EXAMEN";

	public static final String LMD_TYPE_DEMANDE_TRANSFERT = "LMD_TYPE_DEMANDE_TRANSFERT";
	public static final String LMD_AVIS_DEMANDE_TRANSFERT = "LMD_AVIS_DEMANDE_TRANSFERT";
	public static final String LMD_MOTIF_REJET_DEMANDE_TRANSFERT = "LMD_MOTIF_REJET_DEMANDE_TRANSFERT";
	public static final String NC_LMD_TYPE_EVALUATION_CONTROLE_CONTINU = "LMD_TYPE_EVALUATION_CONTROLE_CONTINU";
	public static final String NC_LMD_TYPE_AP = "LMD_TYPE_AP";
	public static final String NC_LMD_MODE_ENSEIGNEMENT = "LMD_MODE_ENSEIGNEMENT";

	public static final Integer INSERT_ACTION_CODE = 0;
	public static final Integer UPDATE_ACTION_CODE = 1;
	public static final Integer DELETE_ACTION_CODE = 2;
	public static final Integer SAVE_ACTION_CODE = 3;
	public static final Integer VALIDATE_ACTION_CODE = 4;
	public static final Integer ANNULATION_VALIDATE_ACTION_CODE = 5;
	public static final String FONCTION_GERER_DOSSIER_ETUDIANT_CODE = "FCT00103";
	public static final String DOSSIER_INSCRIPTION_ADMINISTRATIF_ENTITY_NAME = "DossierInscriptionAdministrative";
	public static final String DOMAINE_FVE_NAME = "FVE";

	/************ Libelles de nomencalture *************************/
	public static final String NC_TYPE_ADRESSE_PERSONELLE_VALUE = "Personnelle";
	public static final String NC_TYPE_ADRESSE_PROFESSIONELLE_VALUE = "Professionnelle";
	public static final String NC_TYPE_TELEPHONE_PERSONELLE_VALUE = "Personnel";
	public static final String NC_TYPE_TELEPHONE_PROFESSIONELLE_VALUE = "Professionnel";
	public static final String NC_NATURE_TELEPHONE_FAX_VALUE = "Fax";
	public static final String NC_NATURE_TELEPHONE_FIXE_VALUE = "Fixe";
	public static final String NC_NATURE_TELEPHONE_MOBILE_VALUE = "Mobile";
	public static final String NC_NATURE_TELEPHONE_NUMERO_INTERNE_VALUE = "Num�ro interne";
	public static final String NC_TYPE_ADRESSE_ELECTRONIQUE_PERSONELLE_VALUE = "Personnelle";
	public static final String NC_TYPE_ADRESSE_ELECTRONIQUE_PROFESSIONELLE_VALUE = "Professionnelle";
	public static final String NC_NATURE_ADRESSE_ELECTRONIQUE_EMAIL_VALUE = "Email";
	public static final String NC_NATURE_ADRESSE_ELECTRONIQUE_SITE_WEB_VALUE = "Site web";
	public static final String NC_LMD_TYPE_AP_MC_CODE_VALUE = "CM";

	public static final String DATE_FORMAT_STANDARD = "dd/MM/yyyy";

	public static final String REF_PARAM_FVE_WITH_CONTROLE_CONTINU_KEY = "FVE_WITH_CONTROLE_CONTINU";
	public static final String REF_PARAM_FVE_COEFFICIENT_CONTROLE_CONTINU_KEY = "FVE_COEFFICIENT_CONTROLE_CONTINU";
	public static final String REF_PARAM_FVE_COEFFICIENT_EXAMEN_KEY = "FVE_COEFFICIENT_EXAMEN";

	// Code Preference utilisateur
	public static final String PREF_USER_FONT_SIZE_CODE = "PREF_USER_FONT_SIZE";
	
	public static final int NOTATION_PASSAGE_L1_L2_CREDIT_MIN_PERIODE = 20;
	
	public static final int NOTATION_PASSAGE_L1_L2_CREDIT_MIN_ANNEE = 30;
	
	public static final String NOTATION_DECISION_JURY_PASSAGE_AVEC_DETTE_CODE = "EDJ002";
	
	public static final String NOTATION_DECISION_JURY_PASSAGE_SANS_DETTE_STYLE_CLASS = "ui-decision-passage-sans-dette";
	
	public static final String NOTATION_DECISION_JURY_PASSAGE_AVEC_DETTE_STYLE_CLASS = "ui-decision-passage-avec-dette";
	
	public static final String JASPER_PARAM_OFFRE_FORMATION_KEY = "OFFRE_FORMATION";
	
	public static final String JASPER_PARAM_OFFRE_FORMATION_ARABE_KEY = "OFFRE_FORMATION_ARABE";
	
	public static final String JASPER_PARAM_SPECIALITE_ARABE_KEY = "SPECIALITE_ARABE";
	
	public static final String JASPER_PARAM_SPECIALITE_KEY = "SPECIALITE";
	
	public static final String JASPER_PARAM_ANNEE_ACADEMIQUE_KEY = "ANNEE_ACADEMIQUE";
	
	public static final String JASPER_PARAM_IMG_PAPS_KEY = "IMG_PAPS";
	
	public static final String JASPER_PARAM_IMG_LOGO_KEY = "IMG_LOGO";
	
	public static final String JASPER_PARAM_SUBREPORT_DIR_KEY = "SUBREPORT_DIR";
	
	public static final String JASPER_PARAM_PERIODE_KEY = "PERIODE";
	
	public static final String JASPER_PARAM_NIVEAU_KEY = "NIVEAU";
	
	public static final String JASPER_PARAM_CYCLE_KEY = "CYCLE";
	
	public static final String JASPER_PARAM_DATE_DEBUT_KEY = "DATE_DEBUT";
	
	public static final String JASPER_PARAM_DATE_FIN_KEY = "DATE_FIN";
	
	public static final String JASPER_PARAM_INTITULE_KEY = "INTITULE";
	
	public static final String JASPER_PARAM_TYPE_SESSION_KEY = "TYPE_SESSION";
	
	public static final String JASPER_PARAM_TITLE_KEY = "TITLE";
	
	public static final String JASPER_PARAM_ETABLISSEMENT_KEY = "ETABLISSEMENT";
	
	public static final String JASPER_PARAM_IS_BILAN_ANNUEL_KEY = "IS_BILAN_ANNUEL";
	
}
