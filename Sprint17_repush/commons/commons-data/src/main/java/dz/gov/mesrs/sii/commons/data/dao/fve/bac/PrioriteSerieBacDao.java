package dz.gov.mesrs.sii.commons.data.dao.fve.bac;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.bac.PrioriteSerieBac;

/**
 * @author Mounir.MESSAOUDI on : 14 ao�t 2014 12:13:43
 */
public interface PrioriteSerieBacDao {

	public  void persist(PrioriteSerieBac transientInstance);

	public  void remove(PrioriteSerieBac persistentInstance);

	public  PrioriteSerieBac merge(PrioriteSerieBac detachedInstance);

	public  PrioriteSerieBac findById(int id);
		
	public  List<PrioriteSerieBac> findAll();

}