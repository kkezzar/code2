package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefPeriode;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface RefPeriodeDao {

	public  void persist(RefPeriode transientInstance);

	public  void remove(RefPeriode persistentInstance);

	public  RefPeriode merge(RefPeriode detachedInstance);

	public  RefPeriode findById(int id);
	
	public  RefPeriode findByIdentifiant(String identifiant);
	
	public RefPeriode findByName(String name);
	
	public  List<RefPeriode> findByQuery(String query) ;
	
	public  List<RefPeriode> findGeneric(String value) ;
	
	public  List<RefPeriode> findAll();
	
	public Integer findLastOrder(String prefix, int orderLength);

}