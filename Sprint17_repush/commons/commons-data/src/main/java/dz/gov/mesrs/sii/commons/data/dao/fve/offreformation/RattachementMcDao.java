package dz.gov.mesrs.sii.commons.data.dao.fve.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.RattachementMc;

/**
 * 
 * 
 * @author Kheireddine OMRANI
 */
public interface RattachementMcDao {

	void persist(RattachementMc transientInstance);

	void remove(RattachementMc transientInstance);
	
	RattachementMc merge(RattachementMc transientInstance);

	void remove(int ueId, int mcId); 

	RattachementMc findById(int id);

	List<RattachementMc> find(Integer ueId, Integer mcId);
	
	List<RattachementMc> findOptionnelle(Integer ueId, Integer mcId, Boolean optionnelle);
	
	RattachementMc findUnique(int ueId, int mcId);
	
}