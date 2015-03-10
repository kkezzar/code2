package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefHoraireAccess;
/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface RefHoraireAccessDao {

	public  void persist(RefHoraireAccess transientInstance);

	public  void remove(RefHoraireAccess persistentInstance);

	public  RefHoraireAccess merge(RefHoraireAccess detachedInstance);

	public  RefHoraireAccess findById(int id);
	
	public  List<RefHoraireAccess> findByQuery(String query) ;
	
	public  List<RefHoraireAccess> findAll();

    public List<RefHoraireAccess> findGeneric(String value);
    
    public Integer findLastOrder(String prefix, int orderLength);
}