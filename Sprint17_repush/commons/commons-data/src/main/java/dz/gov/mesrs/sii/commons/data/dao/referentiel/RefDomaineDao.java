package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefDomaine;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface RefDomaineDao {

	public  void persist(RefDomaine transientInstance);

	public  void remove(RefDomaine persistentInstance);

	public  RefDomaine merge(RefDomaine detachedInstance);

	public  RefDomaine findById(int id);
	
	public RefDomaine findByIdentifiant(String identifiant);
	
	public  RefDomaine findByName(String name);
	
	public  RefDomaine findByNameArabe(String nameArabe);
	
	public  List<RefDomaine> findByQuery(String query) ;
	
	public  List<RefDomaine> findAll();
	
	public List<RefDomaine> findGeneric(String value);
	
	public List<RefDomaine> findSubDomaine(int id);
	
	public List<RefDomaine> findSubDomaine(String identifiant);
	
	public int findSubDomaineLastRang(int id);
	
	public int findDomaineLastRang();
	
	public List<RefDomaine> findDomaines();
	
	public List<RefDomaine> findDomainesAndSubDomaine();
	
	public Integer findLastOrder(String prefix, int orderLength);

}