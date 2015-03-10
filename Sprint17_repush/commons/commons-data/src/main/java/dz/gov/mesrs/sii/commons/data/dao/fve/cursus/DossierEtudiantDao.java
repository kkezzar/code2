package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierEtudiant;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

public interface DossierEtudiantDao {

	public void persist(DossierEtudiant transientInstance);

	public void remove(DossierEtudiant persistentInstance);

	public DossierEtudiant merge(DossierEtudiant detachedInstance);

	public DossierEtudiant findById(int id);

	public List<DossierEtudiant> findByQuery(String query);

	public List<DossierEtudiant> findAll();

	public List<DossierEtudiant> findAdvanced(DossierEtudiant searchBo);

	public List<DossierEtudiant> findByIdIndividu(Integer id,
			Integer idEtablissment);

	public void flushAndClear();

	public String generateMatricule(DossierEtudiant dossierEtudiant);

	/**
	 * Rechercher le derniere dossier d'etudiant enregistr� par l'id de
	 * l'individu
	 * 
	 * @author Mounir.MESSAOUDI on : 30 oct. 2014 07:33:42
	 * @param idIndividu
	 * @return
	 */

	public DossierEtudiant findLastByIdIndividu(Integer idIndividu);

	/**
	 * Recherche avancee avec pagination
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 15:41:08
	 * @param searchDto
	 * @return
	 */
	public List<DossierEtudiant> findAdvanced(DossierEtudiant searchDto,
			String sortField, Integer pageSize, Integer first,
			Boolean descending);

	/**
	 * Nombre de resultats de la recherche avancee
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 15:42:53
	 * @param searchDto
	 * @return
	 */
	public Long count(DossierEtudiant searchDto);

}