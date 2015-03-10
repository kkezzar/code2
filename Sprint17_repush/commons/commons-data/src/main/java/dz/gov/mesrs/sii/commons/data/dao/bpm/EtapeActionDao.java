package dz.gov.mesrs.sii.commons.data.dao.bpm;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.bpm.EtapeAction;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface EtapeActionDao {

	public  void persist(EtapeAction transientInstance);

	public  void remove(EtapeAction persistentInstance);

	public  EtapeAction merge(EtapeAction detachedInstance);

	public  EtapeAction findById(int id);
	
	public  List<EtapeAction> findByQuery(String query) ;
	
	public  List<EtapeAction> findAll();
	
	public  List<EtapeAction> findByEtapeId(int etapeId);
	
	public  EtapeAction findByCodeEtapeByCodeAction(String codeEtape, String codeAction);
	
	
	

}