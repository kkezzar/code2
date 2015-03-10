/**
 * [dz.gov.mesrs.sii.recherche.business.service.RechercheGroupeService.java] 
 * @author rlaib on : 14 d�c. 2014  17:33:39
 */
package dz.gov.mesrs.sii.recherche.business.service;

import java.util.List;

import dz.gov.mesrs.sii.recherche.business.model.dto.GroupeDto;

/**
 * @author rlaib  on : 14 dec. 2014  17:33:39
 */
public interface RechercheGroupeService {
	
	GroupeDto insertOrUpdate(GroupeDto groupeDto);
	void remove( Long id);
	List<GroupeDto> findAll();
	List<GroupeDto> findStructureRechercheGroupes(Long idStructure);
	public  GroupeDto findById(Long id);
}
