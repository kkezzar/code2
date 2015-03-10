package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAffectDomLmdEtab;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface RefAffectDomLmdEtabDao {

	public  void persist(RefAffectDomLmdEtab transientInstance);

	public  void remove(RefAffectDomLmdEtab persistentInstance);

	public  RefAffectDomLmdEtab merge(RefAffectDomLmdEtab detachedInstance);

	public  RefAffectDomLmdEtab findById(int id);
	
	public  List<RefAffectDomLmdEtab> findByQuery(String query) ;
	
	public  List<RefAffectDomLmdEtab> findAll();
	//requete sur l'identifiant de l'establissement
	public abstract List<RefAffectDomLmdEtab> findByIdEtablissement(String idEtablissement);
	
	//requete sur l'id  de l'establissement
	List<RefAffectDomLmdEtab> findByIdEtablissement(Integer idEtablissement);

	public abstract List<RefAffectDomLmdEtab> findByCodeDomainelmd(String codeDomainelmd);
	
	public abstract List<RefAffectDomLmdEtab> findByIdDomainelmd(Integer idDomainelmd);
	
	public abstract List<RefAffectDomLmdEtab> findByCodeEtablissement(String codeEtablissement);

}