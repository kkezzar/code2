/**
 * [dz.gov.mesrs.sii.referentiel.business.service.RefStructureService.java] 
 * @author MAKERRI Sofiane on : 5 janv. 2014  10:57:52
 */
package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefStructure;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

/**
 * @author MAKERRI Sofiane  on : 5 janv. 2014  10:57:52
 */
public interface RefStructureService {
	
	public List<RefStructureDto> findAll();
	
	public List<RefStructureDto> findGeneric(String value);
	
	public List<RefStructureDto> findAdvanced(RefStructureDto refStructureDto);
	
	public RefStructureDto save(RefStructureDto refStructureDto);
	
	public void update(RefStructureDto refStructureDto);
	
	public void saveOrUpdate(RefStructureDto refStructureDto);
	
	public  RefStructureDto findById(Integer id);
	
	public void  delete(String id);
	
	public List<RefStructureDto> findAll(Integer etabId);
	
	public List<RefStructureDto> findGeneric(Integer etabId, String value);
	
	public List<RefStructureDto> findAdvanced(Integer etabId, RefStructureDto refStructureDto);
	
	public String  generateIdentify(String prefix, int orderLength);
	
	/**
	 * [RefStructureService.findStrcuturesByTypeByEtab] Method 
	 * @author rlaib  on : 11 oct. 2014  10:46:44
	 * @param etabId
	 * @param typeStructureId
	 * @return
	 */
	public List<RefStructureDto> findStructuresByTypeByEtab(Integer etabId, int typeStructureId);
	
	public List<RefStructureDto> findStructuresOfMasterStructureByEtab(Integer etabId, Integer masterStructureId);

}
