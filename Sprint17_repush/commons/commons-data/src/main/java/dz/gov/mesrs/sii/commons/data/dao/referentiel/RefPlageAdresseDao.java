package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefPlageAdresse;


/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface RefPlageAdresseDao {

	public  void persist(RefPlageAdresse transientInstance);

	public  void remove(RefPlageAdresse persistentInstance);

	public  RefPlageAdresse merge(RefPlageAdresse detachedInstance);

	public  RefPlageAdresse findById(int id);
	
	public  List<RefPlageAdresse> findByQuery(String query) ;
	
	public  List<RefPlageAdresse> findAll();

    public List<RefPlageAdresse> findGeneric(String value);
    
    public  List<RefPlageAdresse> findAll(Integer etabId);

    public List<RefPlageAdresse> findGeneric(Integer etabId, String value);
    
    public Integer findLastOrder(String prefix, int orderLength);
	

}