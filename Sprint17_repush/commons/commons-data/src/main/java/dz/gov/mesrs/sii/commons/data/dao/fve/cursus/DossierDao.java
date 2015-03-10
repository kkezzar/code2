package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Dossier;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface DossierDao {

	public  Dossier persist(Dossier transientInstance);

	public  void remove(Dossier persistentInstance);

	public  Dossier merge(Dossier detachedInstance);

	public  Dossier findById(int id);
	
	public  Dossier findByCode(String code);
	
	public  List<Dossier> findByQuery(String query) ;
	
	public  List<Dossier> findAll();

	public void flushAndClear();

}