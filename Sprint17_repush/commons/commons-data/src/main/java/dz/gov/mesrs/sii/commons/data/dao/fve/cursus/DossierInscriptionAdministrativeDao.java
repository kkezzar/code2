package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierInscriptionAdministrative;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.GroupePedagogique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OuvertureOffreFormation;

public interface DossierInscriptionAdministrativeDao {

	void persist(DossierInscriptionAdministrative transientInstance);

	void remove(DossierInscriptionAdministrative persistentInstance);

	DossierInscriptionAdministrative merge(
			DossierInscriptionAdministrative detachedInstance);

	DossierInscriptionAdministrative findById(int id);

	List<DossierInscriptionAdministrative> findByQuery(String query);

	List<DossierInscriptionAdministrative> findAll();

	List<DossierInscriptionAdministrative> findAdvanced(
			DossierInscriptionAdministrative _dia);

	public List<DossierInscriptionAdministrative> findAdvanced(
			DossierInscriptionAdministrative diaSearch, String codeDoamine,
			String codefiliere);

	List<DossierInscriptionAdministrative> findDossierInscriptionsBy(
			int dossierEtudiantId);

	DossierInscriptionAdministrative findPresentDossierInscriptionForEtudiant(
			int dossierEtudiantId);

	/**
	 * Rechercher le dossier d'inscription administrative de l'annee academique
	 * precedente
	 * 
	 * @author Mounir.MESSAOUDI on : 2 oct. 2014 08:44:28
	 * @param dossierEtudiantId
	 * @return
	 */
	public DossierInscriptionAdministrative findPrecdentDossierInscriptionForEtudiant(
			int dossierEtudiantId);

	public List<Object[]> findFiliere(DossierInscriptionAdministrative dia);

	public List<Object[]> findDomaine(DossierInscriptionAdministrative dia);

	public List<DossierInscriptionAdministrative> findByMatriculeBac(
			DossierInscriptionAdministrative _dia);

	List<DossierInscriptionAdministrative> findByAnneeAcademiqueAndEtab(
			int annee, Integer idEtab);

	public List<DossierInscriptionAdministrative> findEtudiant(
			DossierInscriptionAdministrative _dia, Integer gpId,
			Integer sectionId);

	public List<DossierInscriptionAdministrative> _findEtudiant(
			DossierInscriptionAdministrative _dia,
			GroupePedagogique groupePedagogique);

	// public List<DossierInscriptionAdministrative> findEtudiantOfSection(
	// DossierInscriptionAdministrative _dia, int sectionId);

	public void flushAndClear();

	/**
	 * @author Mounir.MESSAOUDI on : 20 oct. 2014 10:33:49
	 * @param diaSearch
	 * @param searchOuverturesOffreFormationBos
	 * @param searchSitutationEntiteBos
	 * @return
	 */
	List<DossierInscriptionAdministrative> findAdvancedByOuvertureOffres(
			DossierInscriptionAdministrative diaSearch,
			List<OuvertureOffreFormation> searchOuverturesOffreFormationBos,
			List<SituationEntite> searchSitutationEntiteBos);

	/**
	 * @author Mounir.MESSAOUDI on : 28 oct. 2014 11:33:29
	 * @param dia
	 * @return
	 */
	public Long countByDia(DossierInscriptionAdministrative dia);

	/**
	 * @author Mounir.MESSAOUDI on : 28 oct. 2014 11:34:47
	 * @param dia
	 * @param sortField
	 * @param pageSize
	 * @param first
	 * @param descending
	 * @return
	 */
	public List<DossierInscriptionAdministrative> findByDia(
			DossierInscriptionAdministrative dia, String sortField,
			Integer pageSize, Integer first, Boolean descending);

	/**
	 * @author Mounir.MESSAOUDI on : 29 oct. 2014 11:47:55
	 * @param diaSearch
	 * @param searchOuverturesOffreFormationBos
	 * @param searchSitutationEntiteBo
	 * @return
	 */
	public Long countByDia(DossierInscriptionAdministrative diaSearch,
			List<OuvertureOffreFormation> searchOuverturesOffreFormationBos,
			List<SituationEntite> searchSitutationEntiteBo);

	/**
	 * @author Mounir.MESSAOUDI on : 29 oct. 2014 11:49:31
	 * @param dia
	 * @param searchOuverturesOffreFormationBos
	 * @param searchSitutationEntiteBo
	 * @param sortField
	 * @param pageSize
	 * @param first
	 * @param descending
	 * @return
	 */
	public List<DossierInscriptionAdministrative> findByDia(
			DossierInscriptionAdministrative dia,
			List<OuvertureOffreFormation> searchOuverturesOffreFormationBos,
			List<SituationEntite> searchSitutationEntiteBo, String sortField,
			Integer pageSize, Integer first, Boolean descending);

	/**
	 * echercher le derniere dossier d'inscription ajoutee d'un etudiant
	 * 
	 * @author Mounir.MESSAOUDI on : 6 nov. 2014 14:53:40
	 * @param dossierEtudiantId
	 * @return
	 */
	public DossierInscriptionAdministrative findDernierDossierInscriptionForEtudiant(
			int dossierEtudiantId);
	
	List<DossierInscriptionAdministrative> findStudentAdvanced(
			DossierInscriptionAdministrative _dia);

}