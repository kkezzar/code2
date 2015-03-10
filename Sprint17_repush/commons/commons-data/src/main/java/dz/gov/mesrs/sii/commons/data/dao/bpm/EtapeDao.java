package dz.gov.mesrs.sii.commons.data.dao.bpm;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.bpm.Etape;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface EtapeDao {

	public  void persist(Etape transientInstance);

	public  void remove(Etape persistentInstance);

	public  Etape merge(Etape detachedInstance);

	public  Etape findById(int id);
	
	public  List<Etape> findByQuery(String query) ;
	
	public  List<Etape> findAll();

	public  Etape findByCode(String codeEtape);

}