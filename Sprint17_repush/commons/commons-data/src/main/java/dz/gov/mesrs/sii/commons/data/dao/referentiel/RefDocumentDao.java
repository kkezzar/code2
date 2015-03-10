package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefDocument;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

public interface RefDocumentDao {

	public void persist(RefDocument transientInstance);

	public void remove(RefDocument persistentInstance);

	public RefDocument merge(RefDocument detachedInstance);

	public RefDocument findById(int id);

	public List<RefDocument> findByQuery(String query);

	public List<RefDocument> findAll();

	public List<RefDocument> findGeneric(String value);

	public List<RefDocument> findAdvanced(RefDocument refDocument, List<Nomenclature> nomenclatureList);

	public Boolean findByRefDocument(RefDocument refDocument);
	
	public List<RefDocument> findFilsOfDocument(RefDocument refDocument) ;
	
	public List<RefDocument> findDocumentsOfEntity(String entity, String idEntity) ;
	
	public RefDocument findDocumentByCode(String code);
	
	public RefDocument findMaxDocumentByType(RefDocument refDocument);
	
	public List<RefDocument> findGeneric(String etabCode, String value);

	public List<RefDocument> findAdvanced(String etabCode, RefDocument refDocument, List<Nomenclature> nomenclatureList);
	
	public List<RefDocument> findAll(String etabCode);

}