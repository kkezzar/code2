package dz.gov.mesrs.sii.commons.data.dao.fve.examen;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.examen.ExamenSession;

/**
 * 
 * @author BELDI Jamel on : 17 sept. 2014 16:34:58
 */

public interface ExamenSessionDao {

	public void persist(ExamenSession transientInstance);

	public void remove(ExamenSession persistentInstance);

	public ExamenSession merge(ExamenSession detachedInstance);

	public ExamenSession findById(long id);

	public List<ExamenSession> findAll();

	public List<ExamenSession> findBySession(long idSession);

	public List<ExamenSession> findAdvanced(ExamenSession examenSession);

	public List<ExamenSession> findExamenByPlanning(Long planningId);

}