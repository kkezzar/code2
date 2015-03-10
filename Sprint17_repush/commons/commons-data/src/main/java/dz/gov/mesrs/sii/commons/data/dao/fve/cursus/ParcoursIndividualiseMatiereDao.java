package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.ParcoursIndividualiseMatiere;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */
 

public interface ParcoursIndividualiseMatiereDao {

	public  void persist(ParcoursIndividualiseMatiere transientInstance);

	public  void remove(ParcoursIndividualiseMatiere persistentInstance);

	public  ParcoursIndividualiseMatiere merge(ParcoursIndividualiseMatiere detachedInstance);

	public  ParcoursIndividualiseMatiere findById(int id);
	
	public  List<ParcoursIndividualiseMatiere> findByIdParcours(int idParcoursIndividualise);
		
	public  List<ParcoursIndividualiseMatiere> findAll();

}