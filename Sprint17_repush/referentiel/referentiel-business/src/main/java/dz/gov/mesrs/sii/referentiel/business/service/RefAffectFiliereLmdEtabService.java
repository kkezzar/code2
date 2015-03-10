
package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectFiliereLmdEtabDto;

/**
 * @author BELBRIK Oualid  on : 19 08. 2014  11:29:03
 */
public interface RefAffectFiliereLmdEtabService {
	
    	//search by identifiant
	public List<RefAffectFiliereLmdEtabDto> findByIdEtablissement(String value);
	
	//search by id
	public List<RefAffectFiliereLmdEtabDto> findByIdEtablissement(Integer etablissementId);
		
	public List<RefAffectFiliereLmdEtabDto> findByCodeFilierelmd(String value);
	
	public List<RefAffectFiliereLmdEtabDto> findByIdFilierelmd(Integer value);
	
	public void save(RefAffectFiliereLmdEtabDto refAffectFiliereLmdEtabDto);
	
	public void update(RefAffectFiliereLmdEtabDto refAffectFiliereLmdEtabDto);
	
	public void saveOrUpdate(RefAffectFiliereLmdEtabDto refAffectFiliereLmdEtabDto);
	
	public void  delete(RefAffectFiliereLmdEtabDto refAffectFiliereLmdEtabDto);

}
