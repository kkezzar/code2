/**
 * [dz.gov.mesrs.sii.referentiel.business.service.RefEquipementService.java] 
 * @author BELBRIK Oualid : 06 03. 2014  15:57:52
 */
package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEquipementDto;

public interface RefEquipementService {
	public List<RefEquipementDto> findAll();
	public List<RefEquipementDto> findGeneric(String value);
	public List<RefEquipementDto> findAdvanced(RefEquipementDto refEquipementDto);
	public RefEquipementDto save(RefEquipementDto refEquipementDto);
	public void update(RefEquipementDto refEquipementDto);
	public void saveOrUpdate(RefEquipementDto refEquipementDto);
	public  RefEquipementDto findById(String id);
	public void  delete(String id);
	public List<RefEquipementDto> findAll(Integer etabId);
	public List<RefEquipementDto> findGeneric(Integer etabId, String value);
	public List<RefEquipementDto> findAdvanced(Integer etabId, RefEquipementDto refEquipementDto);
}
