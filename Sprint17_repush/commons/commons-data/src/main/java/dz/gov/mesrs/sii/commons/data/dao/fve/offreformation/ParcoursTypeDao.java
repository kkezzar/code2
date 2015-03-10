package dz.gov.mesrs.sii.commons.data.dao.fve.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.ParcoursType;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface ParcoursTypeDao {

	public  void persist(ParcoursType transientInstance);

	public  void remove(ParcoursType persistentInstance);

	public  ParcoursType merge(ParcoursType detachedInstance);

	public  ParcoursType findById(int id);
	
	public  List<ParcoursType> findByQuery(String query) ;
	
	public  List<ParcoursType> findAll();
	
	public  List<ParcoursType> findByOfId(int ofId);
}