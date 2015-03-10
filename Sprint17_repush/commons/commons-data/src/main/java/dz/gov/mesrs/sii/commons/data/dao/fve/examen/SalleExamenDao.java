package dz.gov.mesrs.sii.commons.data.dao.fve.examen;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.examen.SalleExamen;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */
 

public interface SalleExamenDao {

	public  void persist(SalleExamen transientInstance);

	public  void remove(SalleExamen persistentInstance);

	public  SalleExamen merge(SalleExamen detachedInstance);

	public  SalleExamen findById(int id);
		
	public  List<SalleExamen> findAll();

	public List<SalleExamen> findByExamen(Long idExamen);

	public List<SalleExamen> findByExamenAndSalle(Long idExamen, Integer idSalle);

	public List<SalleExamen> findBySession(long idSession);
	
}