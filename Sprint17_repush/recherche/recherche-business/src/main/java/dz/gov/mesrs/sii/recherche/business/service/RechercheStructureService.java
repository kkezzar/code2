/**
 * [dz.gov.mesrs.sii.recherche.business.service.RechercheStructureService.java] 
 * @author rlaib on : 14 d�c. 2014  17:33:39
 */
package dz.gov.mesrs.sii.recherche.business.service;

import java.util.List;

import dz.gov.mesrs.sii.recherche.business.model.dto.StructureDto;

/**
 * @author rlaib  on : 14 dec. 2014  17:33:39
 */
public interface RechercheStructureService {
	
	StructureDto insertOrUpdate( StructureDto structureDto);
	void remove( Integer id);
	List<StructureDto> findAll();
	List<StructureDto> findByEtablicement(Integer id);
	StructureDto findById(Long id);
}
