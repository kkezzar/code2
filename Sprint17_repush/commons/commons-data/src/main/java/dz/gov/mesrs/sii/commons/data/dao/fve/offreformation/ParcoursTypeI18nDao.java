package dz.gov.mesrs.sii.commons.data.dao.fve.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.ParcoursTypeI18n;


/**
 * @author  Rafik LAIB  on : 5 avr. 2014  22:05:19
 */
public interface ParcoursTypeI18nDao {

	public  void persist(ParcoursTypeI18n transientInstance);

	public  void remove(ParcoursTypeI18n persistentInstance);

	public  ParcoursTypeI18n merge(ParcoursTypeI18n detachedInstance);

	public  ParcoursTypeI18n findById(int id);
	
	public  List<ParcoursTypeI18n> findByOfId(int ofId);
	

}