package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefMotCle;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface RefMotCleDao {

	public  void persist(RefMotCle transientInstance);

	public  void remove(RefMotCle persistentInstance);
	
	public  void removeMotsClesDocument(String idDocument);

	public  RefMotCle merge(RefMotCle detachedInstance);

	public  RefMotCle findById(int id);
	
	public  List<RefMotCle> findByQuery(String query) ;
	
	public  List<RefMotCle> findAll();
	
	public  List<RefMotCle> findMotsClesDocument(String idDocument);
	
	public  RefMotCle findMotCleDocument(String idDocument, Integer idMotCle);
	
	public List<Nomenclature> findNomenclatureMotsClesDocument(String idDocument);

}