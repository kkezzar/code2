package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.NcHistory;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface NcHistoryDao {

	public  void persist(NcHistory transientInstance);

	public  void remove(NcHistory persistentInstance);

	public  NcHistory merge(NcHistory detachedInstance);

	public  NcHistory findById(int id);
	
	public  List<NcHistory> findByQuery(String query) ;
	
	public  List<NcHistory> findAll();
	
	public  List<NcHistory> findByIdRef(int id) ;

}