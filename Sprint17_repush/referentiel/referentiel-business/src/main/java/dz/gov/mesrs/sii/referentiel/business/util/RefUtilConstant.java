/**
 * 
 */
package dz.gov.mesrs.sii.referentiel.business.util;

import java.util.Date;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAffectation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefDomaineLMD;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefFiliereLmd;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefGroupe;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSpecialiteLmd;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefStructure;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefHistoriqueDto;

/**
 * @author jbeldi
 * 
 */
public class RefUtilConstant {

	/**
	 * 
	 */
	public static final String DOMAINE_REFERENTIEL_NAME = "Referentiel";
	
	
	

	
	
	
	
	/***
	 * 
	 * resource-propeties
	 * 
	 */
	public static final String COMMON_MESSAGES_FILE_NAME = "commonmsgs";
	public static final String NC_MESSAGES_FILE_NAME = "nomenclaturemsgs";
	public static final String INDIVIDU_MESSAGES_FILE_NAME = "individumsgs";
	public static final String HABILITATION_MESSAGES_FILE_NAME = "habilitationmsgs";
	public static final String COMPTE_MESSAGES_FILE_NAME = "comptemsgs";
	public static final String HORAIRE_MESSAGES_FILE_NAME = "horaireaccesmsgs";
	public static final String PLAGEADRESSE_MESSAGES_FILE_NAME = "plageadressemsgs";
	public static final String AUTHENTIFICATION_MESSAGES_FILE_NAME = "authentificationmsgs";
	public static final String DOCUMENT_MESSAGES_FILE_NAME = "documentmsgs";
	public static final String OCCUPATION_MESSAGES_FILE_NAME = "lieusmsgs";
	public static final String PERIODE_MESSAGES_FILE_NAME = "periodemsgs";
	public static final String AFFECTATION_MESSAGES_FILE_NAME = "affectationmsgs";
	public static final String ETABLISSEMENT_MESSAGES_FILE_NAME = "etablissementmsgs";
	public static final String COORDONNEESS_MESSAGES_FILE_NAME = "coordonneemsgs";
	public static final String ETAT_VALIDATION_MESSAGES_FILE_NAME = "etatvalidationmsgs";
	public static final String CONFIGURATION_MESSAGES_FILE_NAME = "parametragemsgs";
	public static final String CONTRAT_MESSAGES_FILE_NAME = "contratmsgs";
	public static final String EQUIPEMENT_MESSAGES_FILE_NAME = "equipementmsgs";
	public static final String LIEU_MESSAGES_FILE_NAME = "lieumsgs";
	
	
	public static RefGroupe refGroupe;
	public static RefStructure refStructure;
	public static RefDomaineLMD refDomaineLMD;
	public static RefFiliereLmd refFiliereLmd;
	public static RefSpecialiteLmd refSpecialiteLmd;
	public static RefAffectation refAffectation;
	public static Integer idGroupe;
	public static Integer idStructure;
	public static String idDomainelmd;
	public static String idFilierelmd;
	public static String idSpecialitelmd;
	
	/**
	 * entities names 
	 */
	
	
	/**
	 * Parametrage
	 */
	
	public static final String CONFIGURATION_BD = "BD";
	public static final String CONFIGURATION_LDAP = "LDAP";
	public static final String CONFIGURATION_WS = "WS";
	public static final String CONFIGURATION_UTIL = "UTIL";
	public static final String CONFIGURATION_DOC = "DOC";
	
	/**
	 * Parametre Key
	 */

	public static final String SUPER_USER = "super";
	public static final String LOGIN_ADMIN = "admin";
	
	public static final String DOC_UPLOAD_URL = "doc.upload.url";
	public static final String DOC_TEMP_URL = "doc.temp.url";
	public static final String DOC_FILE_ALLOW_TYPES = "doc.file.allow.types";
	public static final String DOC_FILE_MAX_SIZE = "doc.file.max.size";
	
	//public static final String ETABLISSEMENT_KEY = "etablissement.lc.latin";
	public static final String ETABLISSEMENT_CODE_KEY = "etablissement.code";
	public static final int QUERY_INDIVIDU_RESULT_MAX = 500;
    public static String CHAR_SEPARATOR = ";";
	
	/********************** Codification ******************************/
	public static final String CODIFICATION_ETABLISSEMENT_MESRS_KEY = "CODIFICATION_ETABLISSEMENT_MESRS";
	public static final String CODIFICATION_ETABLISSEMENT_ORDER_LENGTH_KEY = "CODIFICATION_ETABLISSEMENT_ORDER_LENGTH";
	public static final String CODIFICATION_STRUCTURE_ORDER_LENGTH_KEY = "CODIFICATION_STRUCTURE_ORDER_LENGTH";
	public static final String CODIFICATION_GROUPE_ORDER_LENGTH_KEY = "CODIFICATION_GROUPE_ORDER_LENGTH";
	public static final String CODIFICATION_GROUPE_YEAR_LENGTH_KEY = "CODIFICATION_GROUPE_YEAR_LENGTH";
	public static final String CODIFICATION_MODULE_PREFIX_KEY = "CODIFICATION_MODULE_PREFIX";
	public static final String CODIFICATION_MODULE_ORDER_LENGTH_KEY = "CODIFICATION_MODULE_ORDER_LENGTH";
	public static final String CODIFICATION_FUNCTION_PREFIX_KEY = "CODIFICATION_FUNCTION_PREFIX";
	public static final String CODIFICATION_FUNCTION_ORDER_LENGTH_KEY = "CODIFICATION_FUNCTION_ORDER_LENGTH";
	public static final String CODIFICATION_ACTION_PREFIX_KEY = "CODIFICATION_ACTION_PREFIX";
	public static final String CODIFICATION_ACTION_ORDER_LENGTH_KEY = "CODIFICATION_ACTION_ORDER_LENGTH";
	public static final String CODIFICATION_DOMAINE_PREFIX_KEY = "CODIFICATION_DOMAINE_PREFIX";
	public static final String CODIFICATION_DOMAINE_ORDER_LENGTH_KEY = "CODIFICATION_DOMAINE_ORDER_LENGTH";
	public static final String CODIFICATION_SUB_DOMAINE_PREFIX_KEY = "CODIFICATION_SUB_DOMAINE_PREFIX";
	public static final String CODIFICATION_SUB_DOMAINE_ORDER_LENGTH_KEY = "CODIFICATION_SUB_DOMAINE_ORDER_LENGTH";
	public static final String CODIFICATION_HORAIRE_ACCESS_PREFIX_KEY = "CODIFICATION_HORAIRE_ACCESS_PREFIX";
	public static final String CODIFICATION_HORAIRE_ACCESS_ORDER_LENGTH_KEY = "CODIFICATION_HORAIRE_ACCESS_ORDER_LENGTH";
	public static final String CODIFICATION_PLAGE_ADRESSE_PREFIX_KEY = "CODIFICATION_PLAGE_ADRESSE_PREFIX";
	public static final String CODIFICATION_PLAGE_ADRESSE_ORDER_LENGTH_KEY = "CODIFICATION_PLAGE_ADRESSE_ORDER_LENGTH";
	public static final String CODIFICATION_PERIODE_PREFIX_KEY = "CODIFICATION_PERIODE_PREFIX";
	public static final String CODIFICATION_PERIODE_ORDER_LENGTH_KEY = "CODIFICATION_PERIODE_ORDER_LENGTH";
	public static final String MAX_SEARCH_INDIVIDU_KEY = "MAX_SEARCH_INDIVIDU";
	
	public static final Integer INSERT_ACTION_CODE = 0;
	public static final Integer UPDATE_ACTION_CODE = 1;
	public static final Integer DELETE_ACTION_CODE = 2;
	public static final Integer SAVE_ACTION_CODE = 3;
	public static final String INSERT_ACTION_LIBELLE = "Ajout";
	public static final String UPDATE_ACTION_LIBELLE = "Mise à jour";
	public static final String DELETE_ACTION_LIBELLE = "Suppression";
	public static final String SAVE_ACTION_LIBELLE = "Enregistrement";
	public static final String VALIDATE_ACTION_LIBELLE = "Validation";
	
	
	/************ Libellés de nomencalture *************************/


	/***
	 * 
	 * [UtilConstant.UtilConstant()] Constructor
	 * @author MAKERRI Sofiane  on : 29 mars 2014  12:38:36
	 */
	
	
	public RefUtilConstant() {

	}

	
	
	/**
	 * [ConverterService.strToInt] Method 
	 * @author MAKERRI Sofiane  on : 4 f�vr. 2014  15:48:06
	 * @param str
	 * @return
	 */
	public static int strToInt(String str) {
		try {
			return Integer.parseInt(str);
			
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	/**
	 * [UtilConstant.refGroupe] Getter 
	 * @author MAKERRI Sofiane on : 17 mars 2014  18:12:53
	 * @return the refGroupe
	 */
	public static RefGroupe getRefGroupe() {
		return refGroupe;
	}

	/**
	 * [UtilConstant.refGroupe] Setter 
	 * @author MAKERRI Sofiane on : 17 mars 2014  18:12:53
	 * @param refGroupe the refGroupe to set
	 */
	public static void setRefGroupe(RefGroupe refGroupe) {
		RefUtilConstant.refGroupe = refGroupe;
	}

	/**
	 * [UtilConstant.refStructure] Getter 
	 * @author MAKERRI Sofiane on : 17 mars 2014  18:12:53
	 * @return the refStructure
	 */
	public static RefStructure getRefStructure() {
		return refStructure;
	}

	/**
	 * [UtilConstant.refStructure] Setter 
	 * @author MAKERRI Sofiane on : 17 mars 2014  18:12:53
	 * @param refStructure the refStructure to set
	 */
	public static void setRefStructure(RefStructure refStructure) {
		RefUtilConstant.refStructure = refStructure;
	}

	public static RefDomaineLMD getRefDomaineLMD() {
		return refDomaineLMD;
	}

	public static void setRefDomaineLMD(RefDomaineLMD refDomaineLMD) {
		RefUtilConstant.refDomaineLMD = refDomaineLMD;
	}
	
	public static RefFiliereLmd getRefFiliereLmd() {
		return refFiliereLmd;
	}

	public static void setRefFiliereLmd(RefFiliereLmd refFiliereLmd) {
		RefUtilConstant.refFiliereLmd = refFiliereLmd;
	}
	
	public static RefSpecialiteLmd getRefSpecialiteLmd() {
		return refSpecialiteLmd;
	}

	public static void setRefSpecialiteLmd(RefSpecialiteLmd refSpecialiteLmd) {
		RefUtilConstant.refSpecialiteLmd = refSpecialiteLmd;
	}
	
	

	/**
	 * [UtilConstant.refAffectation] Getter 
	 * @author MAKERRI Sofiane on : 17 mars 2014  18:17:42
	 * @return the refAffectation
	 */
	public static RefAffectation getRefAffectation() {
		return refAffectation;
	}

	/**
	 * [UtilConstant.refAffectation] Setter 
	 * @author MAKERRI Sofiane on : 17 mars 2014  18:17:42
	 * @param refAffectation the refAffectation to set
	 */
	public static void setRefAffectation(RefAffectation refAffectation) {
		if(refAffectation != null) {
			if(refAffectation.getRefGroupeByGroupe() != null) {
				setRefGroupe(refAffectation.getRefGroupeByGroupe());
			} else if(refAffectation.getRefStructure() != null) {
				setRefStructure(refAffectation.getRefStructure());
			} 
		}
		RefUtilConstant.refAffectation = refAffectation;
	}

	/**
	 * [UtilConstant.idGroupe] Getter 
	 * @author MAKERRI Sofiane on : 17 mars 2014  18:29:43
	 * @return the idGroupe
	 */
	public static Integer getIdGroupe() {
		return idGroupe;
	}

	/**
	 * [UtilConstant.idGroupe] Setter 
	 * @author MAKERRI Sofiane on : 17 mars 2014  18:29:43
	 * @param idGroupe the idGroupe to set
	 */
	public static void setIdGroupe(Integer idGroupe) {
		RefUtilConstant.idGroupe = idGroupe;
	}

	/**
	 * [UtilConstant.idStructure] Getter 
	 * @author MAKERRI Sofiane on : 17 mars 2014  18:29:43
	 * @return the idStructure
	 */
	public static Integer getIdStructure() {
		return idStructure;
	}

	/**
	 * [UtilConstant.idStructure] Setter 
	 * @author MAKERRI Sofiane on : 17 mars 2014  18:29:43
	 * @param idStructure the idStructure to set
	 */
	public static void setIdStructure(Integer idStructure) {
		RefUtilConstant.idStructure = idStructure;
	}

	/**
	 * [UtilConstant.formatOrder] Method 
	 * @author MAKERRI Sofiane  on : 21 avr. 2014  09:57:20
	 * @param prefix
	 * @param length
	 * @return
	 */
	public static String formatOrder(String prefix, int length) {
		if(prefix.length() >= length) {
			return prefix; 
		}
		int size = length - prefix.length();
		for(int i = 0; i < size; i++) {
			prefix = "0" + prefix;	
		}
		return prefix;
			
	}
	

	/**
	 * [UtilConstant.getRefHistoriqueDto] Method 
	 * @author MAKERRI Sofiane  on : 18 juin 2014  12:02:48
	 * @param idCompte
	 * @param idEtablissement
	 * @param idFonction
	 * @param idOccurence
	 * @param typeAction
	 * @return
	 */
	public static RefHistoriqueDto getRefHistoriqueDto(Integer idCompte, Integer idEtablissement, Integer idFonction, Integer idOccurence, Integer typeAction) {
		RefHistoriqueDto refHistoriqueDto = new RefHistoriqueDto();
		refHistoriqueDto.setDateAction(new Date());
		refHistoriqueDto.setHeureAction(new Date());
		refHistoriqueDto.setTypeAction(typeAction);
		refHistoriqueDto.setIdOccurence(idOccurence);
		refHistoriqueDto.setIdCompte(idCompte);
		refHistoriqueDto.setIdFonction(idFonction);
		refHistoriqueDto.setIdEtablissement(idEtablissement);
		return refHistoriqueDto;
		
	}
	
}
