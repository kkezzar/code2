package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefModule;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface RefModuleDao {

	public  void persist(RefModule transientInstance);

	public  void remove(RefModule persistentInstance);

	public  RefModule merge(RefModule detachedInstance);

	public  RefModule findById(int id);
	
	public  List<RefModule> findByQuery(String query) ;
	
	public  List<RefModule> findAll();
	
	public  List<RefModule> findGeneric(String value);
	
	public  RefModule findByIdentifiant(String identifiant);
	
	public  RefModule findByName(Integer id, String name);
	
	public  RefModule findByNameArabe(Integer id, String name);
	
	public int findModuleLastRang(int id);
	
	public  List<RefModule> findModules(int id);
	
	public  List<RefModule> findModules();
	
	public Integer findLastOrder(String prefix, int orderLength);
	
}