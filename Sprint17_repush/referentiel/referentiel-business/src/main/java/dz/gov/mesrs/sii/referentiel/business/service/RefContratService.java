/**
 * 
 */
package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefContratDto;

/**
 * @author jbeldi
 *
 */
public interface RefContratService {
	
	public List<RefContratDto> findGeneric(String value, boolean avenant);
	
	public List<RefContratDto> findAdvanced(RefContratDto refContratDto, boolean avenant);
	
	public RefContratDto save(RefContratDto refContratDto, boolean avenant);
	
	public void update( RefContratDto refContratDto);
	
	public void saveOrUpdate(RefContratDto refContratDto);
	
	public  RefContratDto findById(Integer id);
	
	public  RefContratDto findByCode(String identifiant);
	
	public void  delete(int id);
	
	public List<RefContratDto> findGeneric(Integer etabId, String value, boolean avenant);
	
	public List<RefContratDto> findAdvanced(Integer etabId, RefContratDto refContratDto, boolean avenant);
	

}
