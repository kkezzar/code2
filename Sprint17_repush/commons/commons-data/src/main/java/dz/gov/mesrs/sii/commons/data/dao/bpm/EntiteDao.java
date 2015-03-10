package dz.gov.mesrs.sii.commons.data.dao.bpm;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.bpm.Entite;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface EntiteDao {

	public  void persist(Entite transientInstance);

	public  void remove(Entite persistentInstance);

	public  Entite merge(Entite detachedInstance);

	public  Entite findById(int id);
	
	public  List<Entite> findByQuery(String query) ;
	
	public  List<Entite> findAll();
	
	Entite findByCode(String codeEntite);

	List<Entite> findByCodeDomaine(String codeDomaine);

}