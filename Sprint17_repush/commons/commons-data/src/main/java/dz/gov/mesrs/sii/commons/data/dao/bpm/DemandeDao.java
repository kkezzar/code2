package dz.gov.mesrs.sii.commons.data.dao.bpm;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.bpm.Demande;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface DemandeDao {

	public  void persist(Demande transientInstance);

	public  void remove(Demande persistentInstance);

	public  Demande merge(Demande detachedInstance);

	public  Demande findById(int id);
	
	public  List<Demande> findByQuery(String query) ;
	
	public  List<Demande> findAll();
	
	public List<Demande> findAdvanced(Demande demande) ;

}
