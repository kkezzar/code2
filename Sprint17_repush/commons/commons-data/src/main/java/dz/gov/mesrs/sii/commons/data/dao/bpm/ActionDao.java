package dz.gov.mesrs.sii.commons.data.dao.bpm;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.bpm.Action;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface ActionDao {

	public  void persist(Action transientInstance);

	public  void remove(Action persistentInstance);

	public  Action merge(Action detachedInstance);

	public  Action findById(int id);
	
	public  List<Action> findByQuery(String query) ;
	
	public  List<Action> findAll();

    public Action findByCode(String code);
	
}