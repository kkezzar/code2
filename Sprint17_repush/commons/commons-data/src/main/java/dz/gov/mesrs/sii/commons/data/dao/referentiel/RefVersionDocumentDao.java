package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefVersionDocument;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface RefVersionDocumentDao {

	public  void persist(RefVersionDocument transientInstance);

	public  void remove(RefVersionDocument persistentInstance);

	public  RefVersionDocument merge(RefVersionDocument detachedInstance);

	public  RefVersionDocument findById(int id);
	
	public  List<RefVersionDocument> findByQuery(String query) ;
	
	public  List<RefVersionDocument> findAll();
	
	public  List<RefVersionDocument> findVersionsOfDocument(String idDocument);

}