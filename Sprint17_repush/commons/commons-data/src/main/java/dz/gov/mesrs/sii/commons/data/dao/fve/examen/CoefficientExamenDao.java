/**
 * [dz.gov.mesrs.sii.commons.data.dao.fve.cursus.ParametrageCoefficientExamenDao.java] 
 * @author MAKERRI Sofiane on : 5 janv. 2015  09:16:09
 */
package dz.gov.mesrs.sii.commons.data.dao.fve.examen;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.examen.CoefficientExamen;

/**
 * @author MAKERRI Sofiane  on : 5 janv. 2015  09:16:09
 */

public interface CoefficientExamenDao {
	
	public void persist(CoefficientExamen transientInstance);

	public void remove(CoefficientExamen persistentInstance);

	public CoefficientExamen merge(CoefficientExamen detachedInstance);

	public CoefficientExamen findById(int id);

	public List<CoefficientExamen> findAll();
	
	public List<CoefficientExamen> findByOofAndPeriode(Integer oofId, Integer periodeId);
	
	public CoefficientExamen findUnique(Integer oofId, Integer periodeId, Integer rattachementMcId);

}
