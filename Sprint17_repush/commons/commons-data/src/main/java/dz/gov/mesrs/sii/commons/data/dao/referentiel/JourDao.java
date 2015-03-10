package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.Jour;

/**
 * 
 * @author BELDI Jamel  on : 7 oct. 2014  17:40:03
 */
 

public interface JourDao {

	public  void persist(Jour transientInstance);

	public  void remove(Jour persistentInstance);

	public  Jour merge(Jour detachedInstance);

	public  Jour findById(int id);
		
	public  List<Jour> findAll();

}