package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;


import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Exclusion;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 01-07-2014 16:44:07
 */
 
    public interface ExclusionDao {

	public  void persist(Exclusion transientInstance);

	public  void remove(Exclusion persistentInstance);

	public  Exclusion merge(Exclusion detachedInstance);

	public  Exclusion findById(int id);
	
	public  List<Exclusion> findByQuery(String query) ;
	
	public  List<Exclusion> findAll();

	public  List<Exclusion> findByCode(String codeNat);
	
	public  List<Exclusion> findAdvanced(Exclusion searchBo);
	
	public  List<Exclusion> findAdvanced2(Exclusion searchBo);
	
	public Exclusion findByIdDossier(int idDossier);



	
}