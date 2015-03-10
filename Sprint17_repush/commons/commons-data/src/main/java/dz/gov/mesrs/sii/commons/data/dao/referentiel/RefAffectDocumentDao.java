package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAffectDocument;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */
 

public interface RefAffectDocumentDao {

	public  void persist(RefAffectDocument transientInstance);
	public  void remove(RefAffectDocument persistentInstance);
	public  RefAffectDocument merge(RefAffectDocument detachedInstance);
	public  RefAffectDocument findById(int id);	
	public  List<RefAffectDocument> findByQuery(String query) ;	
	public  List<RefAffectDocument> findAll();
	
	public List<RefAffectDocument> findIndividusOfDocument(String idDocument);	
	public List<RefAffectDocument> findGroupesOfDocument(String idDocument) ;	
	public List<RefAffectDocument> findStructuresOfDocument(String idDocument);

}