/**
 * [dz.gov.mesrs.sii.fve.business.persistence.cursus.BilanPeriodeDao.java] 
 * @author MAKERRI Sofiane on : 22 oct. 2014  15:46:41
 */
package dz.gov.mesrs.sii.commons.data.dao.fve.examen;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.examen.BilanSession;

/**
 * @author MAKERRI Sofiane on : 22 oct. 2014 15:46:41
 */
public interface BilanSessionDao {

	public void persist(BilanSession transientInstance);

	public void remove(BilanSession persistentInstance);

	public BilanSession merge(BilanSession detachedInstance);

	public BilanSession findUnique(Long deliberationId, Integer diaId, int type);
	
	public BilanSession findById(long id);

	public List<BilanSession> findAll();

	public List<BilanSession> findByDeliberation(Long deliberationId);
	
	public List<BilanSession> findByDeliberationAnDia(Long deliberationId, Integer diaId);

	public List<BilanSession> findByOofAndNiveau(Integer oofId, Integer niveauId);
	
	public List<BilanSession> findBilanPeriodeByNiveau(Integer oofId, Integer niveauId);
	
	public List<BilanSession> findBilanPeriode(Integer oofId, Integer periodeId);
	
	public List<BilanSession> findByPeriode(Integer oofId, Integer periodeId);
	
	public List<BilanSession> findBilanSession(Integer oofId, Integer periodeId);

	public List<BilanSession> findBilanFinal(Integer oofId, Integer niveauId);
	
	public List<BilanSession> findBilanAnnuel(Integer oofId, Integer niveauId);
	
	/**
	 * Recherche avancee dans la table des bilans annuel
	 * 
	 * @author Mounir.MESSAOUDI on : 26 oct. 2014 11:29:27
	 * @param searchBo
	 * @return
	 */

	public List<BilanSession> findAdvanced(BilanSession searchBo);

	/**
	 * Rechercher le bilan final par id dossier inscription administrative
	 * 
	 * @author Mounir.MESSAOUDI on : 26 oct. 2014 11:33:11
	 * @param diaId
	 * @return
	 */

	public BilanSession findBilanFinalByIdDossierInscrAdmin(Integer diaId);

	public List<BilanSession> findByOffAnnee(Integer anneeId, Integer oofId);
	
	 /** Rechercher la liste des diplomes non g�n�r�s*/

	public List<BilanSession> findBilanFinalDiplomeNonGenerer(Integer oofId,Integer niveauId);
	
	public List<BilanSession> findBilanFinCycle(Integer oofId,Integer niveauId);


}
