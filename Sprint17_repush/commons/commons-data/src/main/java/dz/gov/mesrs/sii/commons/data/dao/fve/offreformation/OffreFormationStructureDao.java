package dz.gov.mesrs.sii.commons.data.dao.fve.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationStructure;


/**
 * @author rlaib  on : 8 oct. 2014  16:15:21
 */
public interface OffreFormationStructureDao {

	public  void persist(OffreFormationStructure transientInstance);

	public  void remove(int idOffreFormationStructure);

	public  OffreFormationStructure merge(OffreFormationStructure detachedInstance);

	public  OffreFormationStructure findById(int id);
	
	public  List<OffreFormationStructure> findByOfId(int ofId);
	
}