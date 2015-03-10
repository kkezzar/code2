package dz.gov.mesrs.sii.fve.business.service.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.UniteEnseignementDto;

/**
 * Interface des Services de gestion des unités d'enseignement.
 * 
 * @author Kheireddine OMRANI
 * 
 */
public interface UniteEnseignementService {

	/**
	 * Permet d'inserer ou de modifier si elle existe déja dans le système
	 * l'unité d'ensignement passé en paramètre.
	 * 
	 * @param uniteEnseignementDto
	 *            : l'UE à insérer ou à MAJ.
	 * @return l'UE tel qu'elle a été insérée ou MAJ. Il est possible que
	 *         certaines propriétés seront MAJ après l'opération d'insertion ou
	 *         de modification, exemple : l'identifiant de l'UE et sa date de
	 *         création après insertion, et la date de la dernière modification
	 *         après modification.
	 */
	UniteEnseignementDto insertOrUpdate(
			UniteEnseignementDto uniteEnseignementDto);

	/**
	 * Supprime physiquement du système une unité d'enseignement. Une UE ne peut
	 * être suprimer si elle est valide ou non valide mais utilisée dans une
	 * offre de formation ou rattachée à une matière constitutive
	 * 
	 * @param uniteEnseignementDto
	 *            : l'UE à supprimer.
	 */
	void remove(UniteEnseignementDto uniteEnseignementDto);

	/**
	 * Valide une UE, une unité d'enseignement ne peut être utilisée dans une
	 * offre de formation ou rattachée à une matière sauf si elle est valide.,
	 * 
	 * @param uniteEnseignementId
	 *            : l'identifiant de l'UE à valider
	 * @return l'UE validée.
	 */
	UniteEnseignementDto validate(int uniteEnseignementId);

	/**
	 * Annule la validation d'une unité d'enseiegnement, les UEs non valides ne
	 * peuvent être utilisées dans des offres ou avec des MCs
	 * 
	 * @param uniteEnseignementId
	 *            : l'identifiant de l'UE à invalider
	 * @return l'UE invalidée.
	 */
	UniteEnseignementDto invalidate(int uniteEnseignementId);

	/**
	 * Recherche l'UE dont l'identifiant est passé en paramètre, elle retourne
	 * null si aucune UE ne corésponde à ce dernier.
	 * 
	 * @param id
	 *            : l'identifiant de l'UE recherchée.
	 * @return l'UE résultat, null si aucune UE ne coresponde à l'Id.
	 */
	UniteEnseignementDto findById(int id);

	/**
	 * Rechercher toutes les unités d'enseignement répondant à un ou à plusieurs
	 * des critères de recherche passés en paramètres,
	 * 
	 * @param code
	 *            : le code de l'UE, s'il est précisé, la liste résultat
	 *            contient au plus une UE, si le code existe dans la base de
	 *            données.
	 * @param libelleFr
	 *            : une partie du libellé Fr des UEs recherchées.
	 * @param libelleAr
	 *            : une partie du libellé Ar des UEs rechercher.
	 * @param abreviationFr
	 *            : une partie de l'abréviation fr des UEs recherchées
	 * @param abreviationAr
	 *            : une partie de l'abréviation ar des UEs recherchées
	 * 
	 * @return Lsite des Ues répondants résultat; une la liste vide sera renvoyé
	 *         si aucune UE ne réponde au critères de recherche.
	 */
	List<UniteEnseignementDto> find(String code, String libelleFr,
			String libelleAr, String abreviationFr, String abreviationAr);

	/**
	 * Recherche par texte intégrale des unités d'enseignements.
	 * 
	 * @param fullTextKeyword
	 *            : le mot clé de la recherche intégrale.
	 * @return Liste des UEs contenant dans une de leurs propriétés le mot clé
	 *         de la recherche.
	 */
	List<UniteEnseignementDto> findWithFullText(String fullTextKeyword);
	
	
	  List<UniteEnseignementDto> findAll() ;
	  
	  UniteEnseignementDto findByCode(String code);

}