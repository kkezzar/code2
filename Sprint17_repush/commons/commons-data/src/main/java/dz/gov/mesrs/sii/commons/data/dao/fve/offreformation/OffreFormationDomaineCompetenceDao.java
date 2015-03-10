package dz.gov.mesrs.sii.commons.data.dao.fve.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationDomaineCompetence;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface OffreFormationDomaineCompetenceDao {

	public  void persist(OffreFormationDomaineCompetence transientInstance);

	public  void remove(OffreFormationDomaineCompetence persistentInstance);

	public  OffreFormationDomaineCompetence merge(OffreFormationDomaineCompetence detachedInstance);

	public  OffreFormationDomaineCompetence findById(int id);
	
	public  List<OffreFormationDomaineCompetence> findByQuery(String query) ;
	
	public  List<OffreFormationDomaineCompetence> findAll();

}