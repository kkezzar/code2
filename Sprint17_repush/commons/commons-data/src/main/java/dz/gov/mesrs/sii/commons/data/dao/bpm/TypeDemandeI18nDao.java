package dz.gov.mesrs.sii.commons.data.dao.bpm;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.bpm.TypeDemandeI18n;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface TypeDemandeI18nDao {

	public  void persist(TypeDemandeI18n transientInstance);

	public  void remove(TypeDemandeI18n persistentInstance);

	public  TypeDemandeI18n merge(TypeDemandeI18n detachedInstance);

	public  TypeDemandeI18n findById(int id);
	
	public  List<TypeDemandeI18n> findByQuery(String query) ;
	
	public  List<TypeDemandeI18n> findAll();

}