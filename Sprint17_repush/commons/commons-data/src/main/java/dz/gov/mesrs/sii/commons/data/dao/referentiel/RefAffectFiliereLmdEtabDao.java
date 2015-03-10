package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAffectFiliereLmdEtab;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 18-08-2014 10:44:07
 */
 

public interface RefAffectFiliereLmdEtabDao {

	public  void persist(RefAffectFiliereLmdEtab transientInstance);

	public  void remove(RefAffectFiliereLmdEtab persistentInstance);

	public  RefAffectFiliereLmdEtab merge(RefAffectFiliereLmdEtab detachedInstance);

	public  RefAffectFiliereLmdEtab findById(int id);
	
	public  List<RefAffectFiliereLmdEtab> findByQuery(String query) ;
	
	public  List<RefAffectFiliereLmdEtab> findAll();
	//search by identifiant
	public abstract List<RefAffectFiliereLmdEtab> findByIdEtablissement(String idEtablissement);
	
	//search by id
	List<RefAffectFiliereLmdEtab> findByIdEtablissement(Integer idEtablissement);

	public abstract List<RefAffectFiliereLmdEtab> findByCodeFilierelmd(String codeFiliereainelmd);
	
	public abstract List<RefAffectFiliereLmdEtab> findByIdFilierelmd(Integer idFiliereainelmd);

}