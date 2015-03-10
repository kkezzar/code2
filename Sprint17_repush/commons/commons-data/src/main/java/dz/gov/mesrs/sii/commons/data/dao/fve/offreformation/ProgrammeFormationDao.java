package dz.gov.mesrs.sii.commons.data.dao.fve.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.ProgrammeFormation;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface ProgrammeFormationDao {

	public  void persist(ProgrammeFormation transientInstance);

	public  void remove(ProgrammeFormation persistentInstance);

	public  ProgrammeFormation merge(ProgrammeFormation detachedInstance);

	public  ProgrammeFormation findById(int id);
	
	public  List<ProgrammeFormation> findByQuery(String query) ;
	
	public  List<ProgrammeFormation> findAll();

}