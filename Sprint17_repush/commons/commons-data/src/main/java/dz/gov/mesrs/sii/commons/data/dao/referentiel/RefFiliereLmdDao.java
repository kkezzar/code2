package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAffectFiliereLmdEtab;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefFiliereLmd;

/**
 * 
 * @author 
 *
 */
public interface RefFiliereLmdDao {

	void persist(RefFiliereLmd transientInstance);

	void remove(RefFiliereLmd persistentInstance);

	RefFiliereLmd merge(RefFiliereLmd detachedInstance);

	RefFiliereLmd findById(int id);
	
	RefFiliereLmd findByIdentifiant(String identifiant);
	
	List<RefFiliereLmd> findByQuery(String query) ;
	
	List<RefFiliereLmd> findAll();
	
	List<RefFiliereLmd> findGeneric(String value);
		
	List<RefFiliereLmd> findAdvanced(RefFiliereLmd refFiliereLmd);

	List<RefFiliereLmd> findByCodeDomainelmd(String codeDomaine);
	
	List<RefFiliereLmd> findByIdDomainelmd(Integer idDomaine);
	
	List<RefFiliereLmd> findByAncienCodeFiliere(String ancienCodeFiliere);
	
	List<RefAffectFiliereLmdEtab> findFilieresLMDByEtab(int idEtab,  String codeDomaine);
	
	List<RefAffectFiliereLmdEtab> findFilieresLMDByCodeEtab(String codeEtab);


}