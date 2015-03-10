package dz.gov.mesrs.sii.commons.data.dao.fve.scolarite;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.AffectationLieuStructure;

/**
 * 
 * @author BELDI Jamel  on : 7 oct. 2014  17:51:41
 */
 

public interface AffectationLieuStructureDao {

	public  void persist(AffectationLieuStructure transientInstance);

	public  void remove(AffectationLieuStructure persistentInstance);

	public  AffectationLieuStructure merge(AffectationLieuStructure detachedInstance);

	public  AffectationLieuStructure findById(int id);
		
	public  List<AffectationLieuStructure> findAll();
	
	public  List<AffectationLieuStructure> findAdvanced(AffectationLieuStructure searchBo);

}