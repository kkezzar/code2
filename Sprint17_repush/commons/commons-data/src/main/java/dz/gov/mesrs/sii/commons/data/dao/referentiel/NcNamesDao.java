package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.NcNames;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface NcNamesDao {

	public  void persist(NcNames transientInstance);

	public  void remove(NcNames persistentInstance);

	public  NcNames merge(NcNames detachedInstance);

	public  NcNames findById(int id);
	
	public  List<NcNames> findByQuery(String query) ;
	
	public  List<NcNames> findAll();
	
	public  List<NcNames> findGeneric(String value);
	
	public  NcNames findByName(String ncName) ;
    
	public boolean hasReference(int id);
	
	public NcNames findDefaultValue(String ncName);

}