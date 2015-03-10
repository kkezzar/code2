
package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectDomLmdEtabDto;

/**
 * @author BELBRIK Oualid  on : 19 02. 2014  17:29:03
 */
public interface RefAffectDomLmdEtabService {
    
    	//requete sur l'identifiant etablissement
	public List<RefAffectDomLmdEtabDto> findByIdEtablissement(String value);
	//requete sur l'id etablissement
	public List<RefAffectDomLmdEtabDto> findByIdEtablissement(Integer idEtablissement);
	
	public List<RefAffectDomLmdEtabDto> findByCodeDomainelmd(String value);
	
	public List<RefAffectDomLmdEtabDto> findByIdDomainelmd(Integer value);
	
	public void save(RefAffectDomLmdEtabDto refAffectDomLmdEtabDto);
	
	public void update(RefAffectDomLmdEtabDto refAffectDomLmdEtabDto);
	
	public void saveOrUpdate(RefAffectDomLmdEtabDto refAffectDomLmdEtabDto);
	
	public void  delete(RefAffectDomLmdEtabDto refAffectDomLmdEtabDto);
	
	public List<RefAffectDomLmdEtabDto> findByCodeEtablissement(String value);

}
