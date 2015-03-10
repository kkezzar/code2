package dz.gov.mesrs.sii.commons.data.dao.fve.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationI18n;


/**
 * @author  Rafik LAIB  on : 5 avr. 2014  22:05:19
 */
public interface OffreFormationI18nDao {

	public  void persist(OffreFormationI18n transientInstance);

	public  void remove(OffreFormationI18n persistentInstance);

	public  OffreFormationI18n merge(OffreFormationI18n detachedInstance);

	public  OffreFormationI18n findById(int id);
	
	public  List<OffreFormationI18n> findByOfId(int ofId);
	

}