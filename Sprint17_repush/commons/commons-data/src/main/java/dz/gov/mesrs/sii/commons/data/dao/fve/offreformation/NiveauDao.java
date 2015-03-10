package dz.gov.mesrs.sii.commons.data.dao.fve.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Niveau;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

public interface NiveauDao {

	public void persist(Niveau transientInstance);

	public void remove(int id);

	public Niveau merge(Niveau detachedInstance);

	public Niveau findById(int id);

	public List<Niveau> findAll();

	public List<Niveau> findByCycleId(int idCycle);

	/**
	 * [NiveauDao.findByCycleCode] Method
	 * 
	 * @author rlaib on : 30 oct. 2014 11:08:52
	 * @param codeCycle
	 * @return
	 */
	public List<Niveau> findByCycleCode(String codeCycle);

	/**
	 * Cette fonction renvoi le prochain niveau du niveau niveauBo
	 * 
	 * @author Mounir.MESSAOUDI on : 26 oct. 2014 15:06:20
	 * @param niveauBo
	 * @return
	 */
	public Niveau findProchainNiveauOf(Niveau niveauBo);

	/**
	 * [NiveauDao.findFirstLevelWithinCycle] Method
	 * 
	 * @author rlaib on : 30 oct. 2014 11:35:54
	 * @param codeCycle
	 * @return
	 */
	public Niveau findFirstLevelWithinCycle(String codeCycle);

	/**
	 * Cette fonction renvoi le premier niveau dans un cycle
	 * 
	 * @author Mounir.MESSAOUDI on : 4 nov. 2014 07:59:23
	 * @param cycleId
	 * @return
	 */
	public Niveau findPremierNiveauOf(Integer cycleId);
	
	public Niveau findLastNiveauOf(Integer cycleId);

}