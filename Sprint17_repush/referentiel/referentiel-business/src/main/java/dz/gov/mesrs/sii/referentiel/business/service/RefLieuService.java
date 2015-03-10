/**
 * [dz.gov.mesrs.sii.referentiel.business.service.RefLieuService.java] 
 * @author BELBRIK Oualid : 11 02. 2014  15:57:52
 */
package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefLieuDto;

/**
 * @author BELBRIK Oualid : 11 02. 2014  15:57:52
 */
public interface RefLieuService {
	public List<RefLieuDto> findAll();
	
	public List<RefLieuDto> findGeneric(String value);
	
	public List<RefLieuDto> findAdvanced(RefLieuDto refLieuDto);
	
	public RefLieuDto save(RefLieuDto refLieuDto);
	
	public void update(RefLieuDto refLieuDto);
	
	public void saveOrUpdate(RefLieuDto refLieuDto);
	
	public  RefLieuDto findById(Integer id);
	
	public void  delete(Integer id);
	
	public List<RefLieuDto> findAll(Integer etabId);
	
	public List<RefLieuDto> findGeneric(Integer etabId, String value);
	
	public List<RefLieuDto> findAdvanced(Integer etabId, RefLieuDto refLieuDto);
	
	public  RefLieuDto findByCode(String code);
}
