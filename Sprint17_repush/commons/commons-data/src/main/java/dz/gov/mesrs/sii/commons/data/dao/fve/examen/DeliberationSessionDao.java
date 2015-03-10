/**
 * [dz.gov.mesrs.sii.fve.business.persistence.cursus.BilanControleContinuDao.java] 
 * @author MAKERRI Sofiane on : 16 oct. 2014  17:17:17
 */
package dz.gov.mesrs.sii.commons.data.dao.fve.examen;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.examen.DeliberationSession;

/**
 * @author MAKERRI Sofiane  on : 16 oct. 2014  17:17:17
 */
public interface DeliberationSessionDao {
	
	public  void persist(DeliberationSession transientInstance);

	public  void remove(DeliberationSession persistentInstance);

	public  DeliberationSession merge(DeliberationSession detachedInstance);

	public  DeliberationSession findById(int id);
		
	public  List<DeliberationSession> findAll();
	
	public  DeliberationSession findByPlanningId(Long planningId);
	
	public  List<DeliberationSession> findAdvanced(DeliberationSession serachBo);
	
	public  List<DeliberationSession> findBilanPeriode(Integer anneeId, int oofId, Integer niveauId);
	
	public  DeliberationSession bilanPeriodeExist(int anneeId, int oofId, int periodeId);
	
}
