package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAffectDomLmdEtab;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefDomaineLMD;


/**
 * @author MESRS CCM
 * @version 1.0
 * @created 14-04-2014 10:44:07
 */
public interface RefDomaineLMDDao {

	void persist(RefDomaineLMD transientInstance);

	void remove(RefDomaineLMD persistentInstance);

	RefDomaineLMD merge(RefDomaineLMD detachedInstance);

	RefDomaineLMD findById(int id);
	
	RefDomaineLMD findByIdentifiant(String identifiant);
	
	List<RefDomaineLMD> findByQuery(String query) ;
	
	List<RefDomaineLMD> findAll();
	
    List<RefDomaineLMD> findGeneric(String value);
	
	List<RefDomaineLMD> findAdvanced(RefDomaineLMD refDomaineLMD);

	List<RefAffectDomLmdEtab> findDomainesLMDByEtab(Integer idEtab);
	
	List<RefDomaineLMD> findDomainesByEtab(Integer idEtab);
	
	List<RefAffectDomLmdEtab> findDomainesLMDByCodeEtab(String codeEtab);

}