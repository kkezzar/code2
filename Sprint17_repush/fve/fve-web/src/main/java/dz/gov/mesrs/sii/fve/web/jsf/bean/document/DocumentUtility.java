/**
 * 
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.document;

import java.io.Serializable;
import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDocumentDto;

public class DocumentUtility implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Conversion du param�tre Web en nom du BO, cette methode utilise la switch
	 * de java 7 [DocumentComponentBean.entityToBo] Method
	 * 
	 * @author yselmane on : 3 avr. 2014 09:18:49
	 * @param entity
	 * @return le nom du BO de l'entité
	 */
	public static String entityToBoaaa(String entity) {
		
		String entityBo = null;

		if (entity != null && !entity.trim().isEmpty()) {

			entity = entity.trim().toLowerCase();

			if (entity.equals("individu")) {
				entityBo = "RefIndividu";
			} else if (entity.equals("structure")) {
				entityBo = "RefStructure";
			} else if (entity.equals("groupe")) {
				entityBo = "RefGroupe";
			} else if (entity.equals("contrat")) {
				entityBo = "RefContrat";
			} else if (entity.equals("avenant")) {
				// ce Bo n'existe pas mais pour séparer avec le BO RefContrat
				entityBo = "RefAvenant";
			} else if (entity.equals("evenement")) {
				entityBo = "RefEvenement";
			} else if (entity.equals("equipement")) {
				entityBo = "RefEquipement";
			} else if (entity.equals("lieu")) {
				entityBo = "RefLieu";
			} else if (entity.equals("etablissement")) {
				entityBo = "RefEtablissement";
			} else if (entity.equals("filierelmd")) {
				entityBo = "RefFiliereLmd";
			} else if (entity.equals("specialitelmd")) {
				entityBo = "RefSpecialiteLmd";
			} 
						
			else if (entity.equals("offreformation")) {
				entityBo = "OffreFormation";
			}else if (entity.equals("dossieretudiant")) {
				entityBo = "DossierEtudiant";
			}
		}

		return entityBo;
	}
	

	/**
	 * comparer le contenu de deux RefDocumentDto
	 * [DocumentComponentWSBean.compareDocuments] Method
	 * 
	 * @author yselmane on : 19 mai 2014 12:18:48
	 * @param refDocumentDto1
	 * @param refDocumentDto2
	 * @return vrai ou faux
	 */
	public static boolean compareDocuments(RefDocumentDto refDocumentDto1,
			RefDocumentDto refDocumentDto2) {

		boolean bool = true;

		if (refDocumentDto1 != null && refDocumentDto2 != null) {

			if (refDocumentDto1.getTypeDocumentId().intValue() != refDocumentDto2
					.getTypeDocumentId().intValue())
				bool = false;
			if (refDocumentDto1.getClassementId().intValue() != refDocumentDto2
					.getClassementId().intValue())
				bool = false;
			if (refDocumentDto1.getNatureDocumentId().intValue() != refDocumentDto2
					.getNatureDocumentId().intValue())
				bool = false;
			if (!refDocumentDto1.getTitreDocument().equals(
					refDocumentDto2.getTitreDocument()))
				bool = false;
			if (!refDocumentDto1.getReferenceDocument().equals(
					refDocumentDto2.getReferenceDocument()))
				bool = false;
			if (!refDocumentDto1.getIdentifiant().equals(
					refDocumentDto2.getIdentifiant()))
				bool = false;
			if (!refDocumentDto1.getDateDocument().equals(
					refDocumentDto2.getDateDocument()))
				bool = false;
			if (!refDocumentDto1.getDateCreation().equals(
					refDocumentDto2.getDateCreation()))
				bool = false;
			if (!refDocumentDto1.getUrl().equals(refDocumentDto2.getUrl()))
				bool = false;
			if (!refDocumentDto1.getDescription().equals(
					refDocumentDto2.getDescription()))
				bool = false;
			if (refDocumentDto1.getLangueId().intValue() != refDocumentDto2
					.getLangueId().intValue())
				bool = false;
			if (! ((refDocumentDto1.getIdParentDocument()==null && refDocumentDto2.getIdParentDocument()==null ) ||
					(refDocumentDto1.getIdParentDocument()!=null && refDocumentDto2.getIdParentDocument()!=null && refDocumentDto1.getIdParentDocument().equals(
					refDocumentDto2.getIdParentDocument()))))
				bool = false;
			if (!refDocumentDto1.getObjetDocument().equals(
					refDocumentDto2.getObjetDocument()))
				bool = false;
		} else
			bool = false;

		return bool;
	}

	/**
	 * compare deux listes de mots cl�s
	 * [DocumentUtility.compareList] Method 
	 * @author yselmane  on : 19 mai 2014  18:02:57
	 * @param list1
	 * @param list2
	 * @return vrai ou faux
	 */
	public static boolean compareList(List<NomenclatureDto> list1,
			List<NomenclatureDto> list2) {

		if (list1 == null && list2 == null)
			return true;
		if (list1 != null && list2 != null && list1.isEmpty()
				&& list2.isEmpty())
			return true;

		boolean bool = true;

		if (list1 != null && !list1.isEmpty() && list2 != null
				&& !list2.isEmpty() && list1.size() == list2.size()) {

			for (NomenclatureDto nomenclatureDto : list1) {
				if (!list2.contains(nomenclatureDto))
					bool = false;
			}

		}

		else
			bool = false;

		return bool;
	}


}
