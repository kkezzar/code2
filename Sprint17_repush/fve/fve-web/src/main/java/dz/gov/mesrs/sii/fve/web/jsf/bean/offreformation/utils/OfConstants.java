/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfConstants.java] 
 * @author rlaib on : 29 janv. 2014  16:19:31
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils;

/**
 * @author rlaib  on : 29 janv. 2014  16:19:31
 */
public  class  OfConstants {
	
	// Nomenclatures Constants for web services Calls
	public static final String NC_NAME_DOMAINES =  "LMD_DOMAINE_FORMATION";
	public static final String NC_NAME_FILIERES =  "LMD_FILIERE";
	public static final String NC_NAME_SPECIALITES =  "LMD_SPECIALITE";
	public static final String NC_NAME_ETAB =  "LMD_ETABLISSEMENT"; 
	public static final String NC_NAME_ETAB_TYPE =  "LMD_ETABLISSEMENT_TYPE";
	public static final String NC_NAME_MODE_ENSEIGNEMENT =  "LMD_MODE_ENSEIGNEMENT";
	public static final String NC_NAME_TYPE_FORMATION =  "LMD_TYPE_FORMATION";
	public static final String NC_NAME_CYCLE =  "LMD_CYCLE";
	public static final String NC_NAME_TYPE_ENSEIGNEMENT =  "LMD_TYPE_ENSEIGNEMENT";
	public static final String NC_NAME_MODE_EVALUATION =  "LMD_MODE_EVALUATION";
	public static final String NC_NAME_VOCATION =  "LMD_VOCATION";
	public static final String NC_NAME_NATURE_OF =  "LMD_NATURE_OF";
	public static final String NC_NAME_NATURE_UE =  "LMD_NATURE_UE";
	public static final String NC_NAME_TYPE_MC =  "LMD_TYPE_MC";
	public static final String NC_NAME_TYPE_UE =  "LMD_TYPE_UE";
	public static final String NC_NAME_PALIER =  "LMD_PALIER";
	public static final String NC_NAME_SEMESTRE =  "LMD_SEMESTRE";
	public static final String NC_NAME_STATUT_OF =  "LMD_STATUT_OF";
	public static final String NC_NAME_STATUT_UE =  "LMD_STATUT_UE";
	public static final String NC_NAME_STATUT_AP =  "LMD_TYPE_AP";
	public static final String NC_NAME_TYPE_COMPETENCE =  "LMD_TYPE_COMPETENCE";
	public static final String NC_NAME_ROLE_EQUIPE_OF =  "LMD_ROLE_EQUIPE_OF";
	public static final String NC_NAME_TYPE_TARIF =  "LMD_TYPE_TARIF";
	public static final String NC_NAME_LMD =  "LMD";
	public static final String NC_NAME_TYPE_DEMANDE =  "LMD_TYPE_DEMANDE";
	public static final String NC_NAME_CIVILITE =  "CIVILITE";
	public static final String NC_NAME_NATIONALITE =  "NATIONALITE";
	public static final String OF_ADVANCED_SEARCH_OPERATOR =  " AND ";
	public static final String OF_SEARCH_ORDERBY_STATMENT = " ORDER BY of.dateDerniereModif desc NULLS LAST, of.dateCreation desc";
	public static final String OF_SEARCH_MODE_NORMAL = "normal";
	public static final String OF_SEARCH_MODE_ADVANCED = "advanced";
	public static final String OF_SEARCH_MODE_PAGING = "paging";
	public static final String OF_SEARCH_QUERY_KEY = "ofSearchQuery";
	public static final String OF_COMPONENT_EDIT_MODE = "edit";
	public static final String OF_COMPONENT_DETAIL_MODE = "detail";
	public static final String OF_COMPONENT_NEW_MODE = "new";
	public static final String NC_CIVILITE_MR =  "Mr";
	public static final String NC_CIVILITE_MME =  "Mme";
	public static final String NC_NATIONALITE_ALG =  "ALG";
	public static final String NC_NAME_TYPE_CYCLE_PASSAGE =  "LMD_TYPE_CYCLE_PASSAGE";
	public static final String NC_NAME_CYCLE_PERIODE_PERIODICITE =  "LMD_CYCLE_PERIODE_PERIODICITE";
	public static final String NC_NAME_PERIODE_PAR_PERIODICITE =  "PERIODE_PAR_PERIODICITE";
	public static final String NC_NAME_WILAYA =  "WILAYA";
	public static final String NC_NAME_TYPE_STRUCTURE =  "TYPE_STUCTURE";
	public static final String NC_NAME_ROLE =  "ROLE";
	
	// Colors
	public static final String ROW_ROLE_COLOR_MEMBER = "2c4df2";
	public static final String ROW_ROLE_COLOR_RESPONSIBLE = "d90351";
	// Bundles resources names (see faces-config.xml)
	public static final String OF_EDIT_BUNDLE_MSG_NAME = "ofEditMsgs";
	public static final String OF_SEARCH_BUNDLE_MSG_NAME = "ofSearchMsgs";
	public static final String DEMANDE_HAB_MESSAGES_FILE_NAME = "demandeHabilitationMsgs";
	public static final String COMMON_MESSAGES_FILE_NAME = "commonmsgs";
	public static final String OFFRE_FORMATION_RESSOURCES_FILE_NAME = "ofmsgs";
	public static final String OUVERTURE_OF_MESSAGES_FILE_NAME = "ouvertureOfMsgs";
	public static final String DOSSIERETUDIANT_OF_MESSAGES_FILE_NAME = "dossieretudiantOfMsgs";
	public static final String ASSIDUITE_OF_MESSAGES_FILE_NAME = "assiduiteOfMsgs";
	public static final String DOSSIER_INSCRIPTION_MESSAGES_FILE_NAME = "dossierInscriptionAdministrativeMsgs";
	public static final String CYCLE_FORMATION_MESSAGES_FILE_NAME = "cycleMsgs";
	public static final String CHARGE_ENSEIGNANT_MESSAGES_FILE_NAME = "chargeMsgs";
	public static final String BAC_GENERATION_DOSSIERS_MESSAGES_FILE_NAME = "generationBacMsgs";
	
	public static final String CHARGE_ENSEIGNANT_ENSEIGNANT_ROLE_CODE= "ENS_ROLE";

	// Bundles resources key messages names (see .properties resources content)
	// Offre de formation
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_PAGE_TITLE_NEW = "EDIT_OF_PAGE_TITLE_NEW";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_PAGE_TITLE_EDIT = "EDIT_OF_PAGE_TITLE_EDIT";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_PAGE_TITLE_DETAIL = "EDIT_OF_PAGE_TITLE_DETAIL";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_TAB1_TITLE = "EDIT_OF_MSG_TAB1_TITLE";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_TAB2_TITLE = "EDIT_OF_MSG_TAB2_TITLE";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_TAB3_TITLE = "EDIT_OF_MSG_TAB3_TITLE";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_TAB4_TITLE = "EDIT_OF_MSG_TAB4_TITLE";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_TAB5_TITLE = "EDIT_OF_MSG_TAB5_TITLE";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_TAB6_TITLE = "EDIT_OF_MSG_TAB6_TITLE";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_TAB7_TITLE = "EDIT_OF_MSG_TAB7_TITLE";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_TAB8_TITLE = "EDIT_OF_MSG_TAB8_TITLE";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_SAVE_DESCRIPTIF_SUCCESS = "EDIT_OF_MSG_SAVE_DESCRIPTIF_SUCCESS";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_SAVE_DESCRIPTIF_FAILURE = "EDIT_OF_MSG_SAVE_DESCRIPTIF_FAILURE";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_SAVE_DESCRIPTIF_ERROR_EXISTING_CODE = "EDIT_OF_MSG_SAVE_DESCRIPTIF_ERROR_EXISTING_CODE";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_SAVE_DESCRIPTIF_NO_MODIFCATION_TO_SAVE = "EDIT_OF_MSG_SAVE_NO_MODIFCATION_TO_SAVE";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_SAVE_TEAM_SUCCESS = "EDIT_OF_MSG_SAVE_TEAM_SUCCESS";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_SAVE_TEAM_FAILURE = "EDIT_OF_MSG_SAVE_TEAM_FAILURE";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_ADD_TEAM_MEMBER_SUCCESS = "EDIT_OF_MSG_ADD_TEAM_MEMBER_SUCCESS";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_ADD_TEAM_MEMBER_FAILURE = "EDIT_OF_MSG_ADD_TEAM_MEMBER_FAILURE";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_ADD_TEAM_MEMBER_ALREADY_EXIST = "EDIT_OF_MSG_ADD_TEAM_MEMBER_ALREADY_EXIST";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_REMOVE_TEAM_MEMBER_SUCCESS = "EDIT_OF_MSG_REMOVE_TEAM_MEMBER_SUCCESS";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_REMOVE_TEAM_MEMBER_FAILURE = "EDIT_OF_MSG_REMOVE_TEAM_MEMBER_FAILURE";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_ADD_PARTNERS_SUCCESS = "EDIT_OF_MSG_ADD_PARTNERS_SUCCESS";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_ADD_PARTNERS_FAILURE = "EDIT_OF_MSG_ADD_PARTNERS_FAILURE";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_ADD_PARTNERS_ALREADY_EXIST = "EDIT_OF_MSG_ADD_PARTNERS_ALREADY_EXIST";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_LIST_FACULTES_DEFAULT = "EDIT_OF_LABEL_TAB_DESCRIPTIF_FACULTES_LIST_DEFAULT";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_LIST_FACULTES_EMPTY = "EDIT_OF_LABEL_TAB_DESCRIPTIF_FACULTES_LIST_EMPTY";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_LIST_DEPARTEMENTS_DEFAULT = "EDIT_OF_LABEL_TAB_DESCRIPTIF_DEPARTEMENTS_LIST_DEFAULT";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_LIST_DEPARTEMENTS_EMPTY = "EDIT_OF_LABEL_TAB_DESCRIPTIF_DEPARTEMENTS_LIST_EMPTY";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_LIST_FILIERES_DEFAULT = "EDIT_OF_LABEL_TAB_DESCRIPTIF_FILIERES_LIST_DEFAULT";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_LIST_FILIERES_EMPTY = "EDIT_OF_LABEL_TAB_DESCRIPTIF_FILIERES_LIST_EMPTY";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_LIST_SPECIALITES_DEFAULT = "EDIT_OF_LABEL_TAB_DESCRIPTIF_SPECIALITE_LIST_DEFAULT";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_LIST_SPECIALITES_EMPTY = "EDIT_OF_LABEL_TAB_DESCRIPTIF_SPECIALITE_LIST_EMPTY";
	
	// Cycle de formation - Diplomes
	public static final String CYCLE_BUNDLE_KEY_DIALOG_DIPLOME_ADD_DIPLOME_IN = "cycle_diplomes_dialog_titre_add_diplome_in";
	public static final String CYCLE_BUNDLE_KEY_DIALOG_DIPLOME_UPDATE_DIPLOME_IN = "cycle_diplomes_dialog_titre_update_diplome_in";
	public static final String CYCLE_BUNDLE_KEY_DIALOG_DIPLOME_ADD_DIPLOME_OUT = "cycle_diplomes_dialog_titre_add_diplome_out";
	public static final String CYCLE_BUNDLE_KEY_DIALOG_DIPLOME_UPDATE_DIPLOME_OUT = "cycle_diplomes_dialog_titre_update_diplome_out";
	public static final String CYCLE_BUNDLE_KEY_DIALOG_DIPLOME_MSG_ADD_DIPLOME_IN_SUCCESS = "cycle_diplome_message_save_diplome_in_success";
	public static final String CYCLE_BUNDLE_KEY_DIALOG_DIPLOME_MSG_ADD_DIPLOME_OUT_SUCCESS = "cycle_diplome_message_save_diplome_out_success";
	public static final String CYCLE_BUNDLE_KEY_DIALOG_DIPLOME_MSG_UPDATE_DIPLOME_IN_SUCCESS = "cycle_diplome_message_update_diplome_in_success";
	public static final String CYCLE_BUNDLE_KEY_DIALOG_DIPLOME_MSG_UPDATE_DIPLOME_OUT_SUCCESS = "cycle_diplome_message_update_diplome_out_success";
	public static final String CYCLE_BUNDLE_KEY_DIALOG_DIPLOME_MSG_SAVE_DIPLOME_IN_FAILURE= "cycle_diplome_message_save_diplome_in_failure";
	public static final String CYCLE_BUNDLE_KEY_DIALOG_DIPLOME_MSG_SAVE_DIPLOME_OUT_FAILURE= "cycle_diplome_message_save_diplome_out_failure";
	public static final String CYCLE_BUNDLE_KEY_DIALOG_DIPLOME_MSG_SAVE_DIPLOME_ALREADY_EXISTS= "cycle_diplome_message_save_diplome_already_exists";
	
	public static final String CYCLE_BUNDLE_KEY_DIPLOME_MSG_DELETE_DIPLOME_IN_SUCCESS= "cycle_diplome_message_delete_diplome_in_success";
	public static final String CYCLE_BUNDLE_KEY_DIPLOME_MSG_DELETE_DIPLOME_IN_FAILURE= "cycle_diplome_message_delete_diplome_in_failure";
	public static final String CYCLE_BUNDLE_KEY_DIPLOME_MSG_DELETE_DIPLOME_OUT_SUCCESS= "cycle_diplome_message_delete_diplome_out_success";
	public static final String CYCLE_BUNDLE_KEY_DIPLOME_MSG_DELETE_DIPLOME_OUT_FAILURE= "cycle_diplome_message_delete_diplome_out_failure";
	
	// Generation dossiers etudiants
	public static final String BAC_GENERATION_BUNDLE_KEY_MSG_INIT_DATA_AFFECTATIONS_ERRONEE = "bac_generation_init_data_affectation_bachelier_erronee";
	public static final String BAC_GENERATION_BUNDLE_KEY_MSG_INIT_DATA_AUCUN_DOSSIER = "bac_generation_init_data_aucun_dossier_a_generer";
	public static final String BAC_GENERATION_BUNDLE_KEY_MSG_GENERATiON_SUCCESS = "bac_generation_generation_operation_success";
	public static final String BAC_GENERATION_BUNDLE_KEY_MSG_GENERATiON_FAILURE = "bac_generation_generation_operation_failure";

	// Charge pedagogique
	public static final String CHARGE_PEDAGOGIQUE_FICHE_VOEUX_LIST_OFFRE_FORMATION_DEFAULT = "fiche_veoux_list_offres_formation_default";
	public static final String CHARGE_PEDAGOGIQUE_FICHE_VOEUX_LIST_OFFRE_FORMATION_EMPTY = "fiche_veoux_list_periodes_empty";
	public static final String CHARGE_PEDAGOGIQUE_FICHE_VOEUX_LIST_NIVEAUX_DEFAULT = "fiche_veoux_list_niveaux_default";
	public static final String CHARGE_PEDAGOGIQUE_FICHE_VOEUX_LIST_PERIDOES_DEFAULT = "fiche_veoux_list_periodes_default";
	public static final String CHARGE_PEDAGOGIQUE_FICHE_VOEUX_LIST_ETABLISSEMENTS_DEFAULT = "fiche_veoux_list_etablissement_default";
	public static final String CHARGE_PEDAGOGIQUE_FICHE_VOEUX_LIST_ETABLISSEMENTS_EMPTY = "fiche_veoux_list_etablissement_empty";
	
	public static final String CHARGE_PEDAGOGIQUE_FICHE_VOEUX_EDIT_MSG_PERIOD_MISSING_PARAM= "fiche_veoux_message_period_missing_param";
	public static final String CHARGE_PEDAGOGIQUE_FICHE_VOEUX_LISTE_OFFRE_FORMATION_VIDE = "fiche_veoux_message_liste_offre_formation_vide";
	public static final String CHARGE_PEDAGOGIQUE_FICHE_VOEUX_EDIT_MSG_MAX_VOEUX_ATTEINT= "fiche_veoux_message_max_voeux_atteint";
	public static final String CHARGE_PEDAGOGIQUE_FICHE_VOEUX_EDIT_MSG_MAX_LIGNES_VOEU_ATTEINT= "fiche_veoux_message_max_ligne_voeu_atteint";
	public static final String CHARGE_PEDAGOGIQUE_FICHE_VOEUX_VALIDATE_MSG_NO_LINE_SELECTED_TO_VALIDATE= "fiche_veoux_validate_control_checked_lines";
	public static final String CHARGE_PEDAGOGIQUE_PERIODE_PARAM_MAX_VOEUX = "MAX_VOEUX";
	public static final String CHARGE_PEDAGOGIQUE_PERIODE_PARAM_MAX_LIGNES_VOEU = "MAX_LIGNES";
	public static final String CHARGE_PEDAGOGIQUE_PERIODE_PARAM_NOMBRE_SEMAINES = "NBS";
	public static final String CHARGE_PEDAGOGIQUE_PERIODE_PARAM_HEURE_REFERENCE= "HREF";
	public static final String CHARGE_PEDAGOGIQUE_PERIODE_PARAM_COEFF_CM_EQUI_CM= "HREFCM";
	public static final String CHARGE_PEDAGOGIQUE_PERIODE_PARAM_COEFF_CM_EQUI_TP= "HREFTP";
	public static final String CHARGE_PEDAGOGIQUE_PERIODE_PARAM_VOLUME_HORAIRE_HEBDO_CM = "VHHCM";
	public static final String CHARGE_PEDAGOGIQUE_PERIODE_PARAM_VOLUME_HORAIRE_HEBDO_TD = "VHHTD";
	public static final String CHARGE_PEDAGOGIQUE_PERIODE_PARAM_VOLUME_HORAIRE_HEBDO_TP = "VHHTP";
	public static final String CHARGE_PEDAGOGIQUE_AP_TYPE_CM_CODE = "CM";
	public static final String CHARGE_PEDAGOGIQUE_AP_TYPE_TD_CODE = "TD";
	public static final String CHARGE_PEDAGOGIQUE_AP_TYPE_TP_CODE = "TP";
	public static final String CHARGE_PEDAGOGIQUE_PERIODE_PARAM_TAUX_VOLUME_HORAIRE_COMP = "TauxSeuilVHComp";
	public static final String CHARGE_PEDAGOGIQUE_VOEUX_SAVE_SUCCESS= "fiche_veoux_save_voeux_success";
	public static final String CHARGE_PEDAGOGIQUE_VOEUX_SAVE_FAILURE= "fiche_veoux_save_voeux_failure";
	public static final String CHARGE_PEDAGOGIQUE_VOEUX_FORMULATE_FAILURE= "fiche_veoux_formulate_voeux_failure";
	public static final String CHARGE_PEDAGOGIQUE_VOEUX_DELETE_SUCCESS= "fiche_veoux_delete_voeux_success";
	public static final String CHARGE_PEDAGOGIQUE_VOEUX_DELETE_FAILURE= "fiche_veoux_delete_voeux_failure";
	public static final String CHARGE_PEDAGOGIQUE_FICHE_VOEUX_CREATE_SUCCESS= "fiche_veoux_create_fiche_voeux_success";
	public static final String CHARGE_PEDAGOGIQUE_FICHE_VOEUX_CREATE_FAILURE= "fiche_veoux_create_fiche_voeux_failure"; 
	public static final String CHARGE_PEDAGOGIQUE_FICHE_VOEUX_DOSSIER_ENSEIGNANT_MANQUANT = "fiche_veoux_create_fiche_voeux_dossier_enseignant_manquant"; 
	
	public static final String CHARGE_PEDAGOGIQUE_FICHE_VOEUX_SUBMIT_CONTROL= "fiche_veoux_message_submit_fiche_voeux_control";
	public static final String CHARGE_PEDAGOGIQUE_FICHE_VOEUX_SUBMIT_SUCCESS= "fiche_veoux_submit_fiche_voeux_success";
	public static final String CHARGE_PEDAGOGIQUE_FICHE_VOEUX_SUBMIT_FAILURE= "fiche_veoux_submit_fiche_voeux_failure";
	public static final String CHARGE_PEDAGOGIQUE_FICHE_VOEUX_VALIDATE_SUCCESS= "fiche_veoux_validate_fiche_voeux_success";
	public static final String CHARGE_PEDAGOGIQUE_FICHE_VOEUX_VALIDATE_FAILURE= "fiche_veoux_validate_fiche_voeux_failure";
	public static final String CHARGE_PEDAGOGIQUE_FICHE_SERVICES_GENERATE_SUCCESS= "fiche_veoux_genarate_fiche_services_success";
	public static final String CHARGE_PEDAGOGIQUE_FICHE_SERVICES_GENERATE_FAILURE= "fiche_veoux_genarate_fiche_services_failure";
	public static final String CHARGE_PEDAGOGIQUE_FICHE_SERVICES_VALIDATE_SUCCESS= "fiche_services_validate_fiche_services_success";
	public static final String CHARGE_PEDAGOGIQUE_FICHE_SERVICES_VALIDATE_FAILURE= "fiche_services_validate_fiche_services_failure";
	public static final String CHARGE_PEDAGOGIQUE_FICHE_SERVICES_SAVE_SUCCESS= "fiche_services_save_fiche_services_success";
	public static final String CHARGE_PEDAGOGIQUE_FICHE_SERVICES_SAVE_FAILURE= "fiche_services_save_fiche_services_failure";
	public static final String CHARGE_PEDAGOGIQUE_FICHE_SERVICES_VALIDATE_CONTROL_TOTAL_COMPL= "fiche_services_validate_fiche_services_control_total_compl";
	public static final String CHARGE_PEDAGOGIQUE_FICHE_SERVICES_VALIDATE_CONTROL_TOTAL_VH_EMPTY= "fiche_services_validate_fiche_services_control_total_vh_empty";
	
	public static final String CHARGE_PEDAGOGIQUE_LIGNE_VOEU_SAVE_SUCCESS= "fiche_veoux_save_ligne_voeu_success";
	public static final String CHARGE_PEDAGOGIQUE_LIGNE_VOEU_SAVE_FAILURE= "fiche_veoux_save_ligne_voeu_failure";
	public static final String CHARGE_PEDAGOGIQUE_LIGNE_VOEU_DELETE_SUCCESS= "fiche_veoux_delete_ligne_voeu_success";
	public static final String CHARGE_PEDAGOGIQUE_LIGNE_VOEU_DELETE_FAILURE= "fiche_veoux_delete_ligne_voeu_failure";
	public static final String SERVICE_FAIT_ENSEIGNANT_SAVE_SUCCESS= "service_fait_save_success";
	public static final String SERVICE_FAIT_ENSEIGNANT_SAVE_FAILURE= "service_fait_save_failure";
	public static final String SERVICE_FAIT_ENSEIGNANT_SAVE_CONTROLE_NB_PRESENCES = "service_fait_message_nb_presences_required";

	// Repartition  parcours type
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_CHANGE_CYLE_ERROR = "EDIT_OF_MSG_CHANGE_CYLE_ERROR";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_CHANGE_TYPE_FORMATION_ERROR = "EDIT_OF_MSG_CHANGE_TYPE_FORMATION_ERROR";
	public static final String OF_EDIT_BUNDLE_KEY_EDIT_OF_MSG_REMOVE_REPARTITION_UE_TOTAL = "EDIT_OF_MSG_REMOVE_REPARTITION_UE_TOTAL";

	
	 // detail UE
	public static final String OFFRE_FORMATION_UE_EDIT_OBJECTIFS = "offre_formation_ue_edit_objectifs";
	public static final String OFFRE_FORMATION_UE_EDIT_PREREQUIS = "offre_formation_ue_edit_prerequis";
	public static final String OFFRE_FORMATION_UE_EDIT_CONTENU = "offre_formation_ue_edit_contenu";
	public static final String OFFRE_FORMATION_UE_EDIT_COMPETENCES = "offre_formation_ue_edit_competences";
	public static final String OFFRE_FORMATION_UE_EDIT_RECOMMANDATIONS = "offre_formation_ue_edit_recommandations";
	public static final String OFFRE_FORMATION_UE_EDIT_ORGANISATION = "offre_formation_ue_edit_organisation";
	public static final String OFFRE_FORMATION_UE_EDIT_EVALUATION = "offre_formation_ue_edit_evaluation";
	public static final String OFFRE_FORMATION_UE_EDIT_AIDES_ETUDIANT = "offre_formation_ue_edit_aides_etudiant";
	public static final String OFFRE_FORMATION_UE_EDIT_ADMISSION = "offre_formation_ue_edit_admission";
	public static final String OFFRE_FORMATION_UE_EDIT_PUBLIC_CIBLE = "offre_formation_ue_edit_public_cible";
	public static final String OFFRE_FORMATION_UE_EDIT_OBSERVATION = "offre_formation_ue_edit_observation";
	public static final String OFFRE_FORMATION_UE_DETAIL_OBJECTIFS = "offre_formation_ue_detail_objectifs";
	public static final String OFFRE_FORMATION_UE_DETAIL_PREREQUIS = "offre_formation_ue_detail_prerequis";
	public static final String OFFRE_FORMATION_UE_DETAIL_CONTENU = "offre_formation_ue_detail_contenu";
	public static final String OFFRE_FORMATION_UE_DETAIL_COMPETENCES = "offre_formation_ue_detail_competences";
	public static final String OFFRE_FORMATION_UE_DETAIL_RECOMMANDATIONS = "offre_formation_ue_detail_recommandations";
	public static final String OFFRE_FORMATION_UE_DETAIL_ORGANISATION = "offre_formation_ue_detail_organisation";
	public static final String OFFRE_FORMATION_UE_DETAIL_EVALUATION = "offre_formation_ue_detail_evaluation";
	public static final String OFFRE_FORMATION_UE_DETAIL_AIDES_ETUDIANT = "offre_formation_ue_detail_aides_etudiant";
	public static final String OFFRE_FORMATION_UE_DETAIL_ADMISSION = "offre_formation_ue_detail_admission";
	public static final String OFFRE_FORMATION_UE_DETAIL_PUBLIC_CIBLE = "offre_formation_ue_detail_public_cible";
	public static final String OFFRE_FORMATION_UE_DETAIL_OBSERVATION = "offre_formation_ue_detail_observation";	
	
	 // detail MC
	
	public static final String OFFRE_FORMATION_MC_EDIT_OBJECTIFS = "offre_formation_mc_edit_objectifs";
	public static final String OFFRE_FORMATION_MC_EDIT_CONTENU = "offre_formation_mc_edit_contenu";
	public static final String OFFRE_FORMATION_MC_EDIT_COMPETENCES = "offre_formation_mc_edit_competences";
	public static final String OFFRE_FORMATION_MC_EDIT_RECOMMANDATIONS = "offre_formation_mc_edit_recommandations";
	public static final String OFFRE_FORMATION_MC_EDIT_ORGANISATION = "offre_formation_mc_edit_organisation";
	public static final String OFFRE_FORMATION_MC_EDIT_ADMISSION = "offre_formation_mc_edit_admission";
	public static final String OFFRE_FORMATION_MC_EDIT_AIDES_ETUDIANT = "offre_formation_mc_edit_aides_etudiant";
	public static final String OFFRE_FORMATION_MC_EDIT_PUBLIC_CIBLE = "offre_formation_mc_edit_public_cible";
	public static final String OFFRE_FORMATION_MC_EDIT_OBSERVATION = "offre_formation_mc_edit_observation";
	public static final String OFFRE_FORMATION_MC_DETAIL_OBJECTIFS = "offre_formation_mc_detail_objectifs";
	public static final String OFFRE_FORMATION_MC_DETAIL_CONTENU = "offre_formation_mc_detail_contenu";
	public static final String OFFRE_FORMATION_MC_DETAIL_COMPETENCES = "offre_formation_mc_detail_competences";
	public static final String OFFRE_FORMATION_MC_DETAIL_RECOMMANDATIONS = "offre_formation_mc_detail_recommandations";
	public static final String OFFRE_FORMATION_MC_DETAIL_ORGANISATION = "offre_formation_mc_detail_organisation";
	public static final String OFFRE_FORMATION_MC_DETAIL_ADMISSION = "offre_formation_mc_detail_admission";
	public static final String OFFRE_FORMATION_MC_DETAIL_AIDES_ETUDIANT = "offre_formation_mc_detail_aides_etudiant";
	public static final String OFFRE_FORMATION_MC_DETAIL_PUBLIC_CIBLE = "offre_formation_mc_detail_public_cible";
	public static final String OFFRE_FORMATION_MC_DETAIL_OBSERVATION = "offre_formation_mc_detail_observation";		
	
	// Detail param�trage Cycles formation
	public static final String CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_TAB1_TITLE = "cycle_tabview_cycles";
	public static final String CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_SAVE_SUCCESS = "cycle_message_enregistrement_statut_succes";
	public static final String CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_SAVE_FAILURE = "cycle_message_enregistrement_statut_echec";
	public static final String CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_EXISTING_RANGE= "cycle_message_enregistrement_existing_range";
	public static final String CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_WRONG_RANGE= "cycle_message_enregistrement_wrong_range";
	public static final String CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_WRONG_RANGE_SEQUENCE= "cycle_message_enregistrement_wrong_range_sequence";
	public static final String CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_RANGE_FISRT= "cycle_message_enregistrement_range_first";
	public static final String CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_PANEL_TITLE = "cycle_panel_detail_legend";
	public static final String CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_PANEL_NEW_TITLE = "cycle_panel_detail_new_legend";
	public static final String CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_EXISTING_CODE= "cycle_message_enregistrement_existing_code";
	public static final String CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_EXISTING_LABEL= "cycle_message_enregistrement_existing_label";
	public static final String LEVEL_EDIT_BUNDLE_KEY_EDIT_PAGE_PANEL_TITLE = "level_panel_detail_legend";
	public static final String LEVEL_EDIT_BUNDLE_KEY_EDIT_PAGE_PANEL_NEW_TITLE = "level_panel_detail_new_legend";
	public static final String PERIOD_EDIT_BUNDLE_KEY_EDIT_PAGE_PANEL_TITLE = "period_panel_detail_legend";
	public static final String PERIOD_EDIT_BUNDLE_KEY_EDIT_PAGE_PANEL_NEW_TITLE = "period_panel_detail_new_legend";
	public static final String PERIOD_EDIT_BUNDLE_KEY_EDIT_PAGE_REMOVE_SUCCESS = "period_message_remove_statut_succes";
	public static final String PERIOD_EDIT_BUNDLE_KEY_EDIT_PAGE_REMOVE_FAILURE = "period_message_remove_statut_echec";
	public static final String LEVEL_EDIT_BUNDLE_KEY_EDIT_PAGE_REMOVE_SUCCESS = "level_message_remove_statut_succes";
	public static final String LEVEL_EDIT_BUNDLE_KEY_EDIT_PAGE_REMOVE_FAILURE = "level_message_remove_statut_echec";
	public static final String CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_REMOVE_SUCCESS = "cycle_message_remove_statut_succes";
	public static final String CYCLE_EDIT_BUNDLE_KEY_EDIT_PAGE_REMOVE_FAILURE = "cycle_message_remove_statut_echec";
	public static final String PERIOD_PARAM_EDIT_BUNDLE_KEY_EDIT_SAVE_SUCCESS = "period_param_message_enregistrement_statut_succes";
	public static final String PERIOD_PARAM_EDIT_BUNDLE_KEY_EDIT_SAVE_FAILURE = "period_param_message_enregistrement_statut_failure";
	public static final String PERIOD_PARAM_EDIT_BUNDLE_KEY_EDIT_REMOVE_SUCCESS = "period_param_message_remove_statut_succes";
	public static final String PERIOD_PARAM_EDIT_BUNDLE_KEY_EDIT_REMOVE_FAILURE = "period_param_message_remove_statut_failure";
	public static final String PERIOD_PARAM_EDIT_BUNDLE_KEY_ADD_EXISTING_CODE = "period_param_message_add_existing_code";
	public static final String PERIOD_PARAM_EDIT_BUNDLE_KEY_PARAM_TAB_TITLE = "period_detail_tabview_params";
	public static final String OF_COMPONENT_MSG_TITLE = "Offre de formation : ";
	public static final String OF_COMPONENT_TEAM_MSG_TITLE = "Offre de formation - Equipe : ";
	public static final String OF_COMPONENT_TREE_EMPTY_NODE_DEFAULT = "Veuillez selectionner un noeud fils SVP. ";
	public static final String OF_COMPONENT_SAVE_SUCCESS = "Enregistrement reussi.";
	public static final String OF_COMPONENT_CYCLE_UPDATE = "Repartition parcours type mise a� jour : ";
	public static final String OF_COMPONENT_SAVE_RULE_EXISTING_CODE = "Code national deja existant !";
	public static final String OF_COMPONENT_SAVE_FAILURE = "Une erreur s'est produite lors de l'enregistrement  !";
	public static final String OF_PAGE_TITLE_NEW = "Nouvelle Offre de formation";
	public static final String OF_PAGE_TITLE_EDIT = "Edition de l'Offre de formation";
	public static final String OF_PAGE_TITLE_DETAIL = "Consultation de l'Offre de formation";
	public static final String OF_TEAM_EXISTING_MEMBER = "Membre déjà existant";
	public static final String OF_TEAM_EXISTING_PARTNER = "Partenaire déjà existant";
	public static final String OF_TEAM_MEMBER_DELETE_SUCCESS = "Membre retiré avec succès.";
	public static final String OF_TEAM_PARTNER_DELETE_SUCCESS = "Partenaire supprimé avec succès.";
	public static final String OF_TEAM_DELETE_FAILURE = "Une erreur inconnue s'est produite lors de la suppresion du membre.";
	public static final String OF_PARTNER_DELETE_FAILURE = "Une erreur inconnue s'est produite lors de la suppresion du partenaire.";
	public static final String OF_TEAM_EXISTING_RESPONSIBLE = "Une équipe doit avoir un seul responsable";
	public static final String OF_TEAM_SUCCESS_MEMBER_ADD = "Ajout membre reussi.";
	public static final String OF_TEAM_SUCCESS_PARTNER_ADD = "Ajout partenaire(s) reussi.";
	public static final String OF_TEAM_MEMBER_FAILURE_ADD = "Une erreur inconnue s'est produite lors de l'ajout du membre.";
	public static final String OF_PARTENAIRE_FAILURE_ADD = "Une erreur inconnue s'est produite lors de l'ajout des partenaires.";
	public static final String OF_PARTENAIRE_NO_SELECTION = "Aucun partenaire choisi.";
	
	/*Composant search Demande Hab Fonction key on properties file
	 * demande_habilitation_fonction_soumission=Soumission
	 * demande_habilitation_fonction_gestion=Gestion
	 * demande_habilitation_fonction_validation=Validation
	*/
	
	public static final String HABILITATION_FONCTION_SOUMISSION_KEY = "demande_habilitation_fonction_soumission";
	public static final String HABILITATION_FONCTION_GESTION_KEY = "demande_habilitation_fonction_gestion";
	public static final String HABILITATION_FONCTION_VALIDATION_KEY = "demande_habilitation_fonction_validation";
	
	// Codes roles equipe offre de formation
	public static final String OF_TEAM_MEMBER_ROLE_RESPONSIBLE_CODE = "RESP";
	public static final String OF_TEAM_MEMBER_ROLE_MEMBER_CODE = "MBR";
	
	// Codes Types Structures
	public static final String NC_TYPE_STRUCTURE_FACULTE_CODE =  "006";
	public static final String NC_TYPE_STRUCTURE_DEPARTEMENT_CODE =  "009";

	// Cycles - Diplomes
	public static final String NC_FVE_NIVEAUX_DIPLOMES = "NIVEAU_DIPLOME";
	public static final String NC_FVE_TYPES_DIPLOMES = "TYPE_DIPLOME";
	public static final String CYCLE_DIPLOME_SENS_IN = "IN";
	public static final String CYCLE_DIPLOME_SENS_OUT = "OUT";
}

