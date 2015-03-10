/**
 * [dz.gov.mesrs.sii.referentiel.business.service.RefGroupeService.java] 
 * @author MAKERRI Sofiane on : 6 janv. 2014  15:43:40
 */
package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefGroupeDto;

/**
 * @author MAKERRI Sofiane  on : 6 janv. 2014  15:43:40
 */
public interface RefGroupeService {
	
	public List<RefGroupeDto> findGeneric(String value);
	
	public List<RefGroupeDto> findAdvanced(RefGroupeDto refGroupeDto);
	
	public List<RefGroupeDto> findAll();
	
	public RefGroupeDto save(RefGroupeDto refGroupeDto);
	
	public void update(RefGroupeDto refGroupeDto);
	
	public RefGroupeDto saveOrUpdate(RefGroupeDto refGroupeDto);
	
	public  RefGroupeDto findById(Integer id);
	
	public  RefGroupeDto findByCode(String code);
	
	public void  delete(String id);
	
	public List<RefGroupeDto> findGeneric(Integer etabId, String value);
	
	public List<RefGroupeDto> findGeneric(Integer etabId, String value, Integer idTypeGroupe);
	
	public List<RefGroupeDto> findAdvanced(Integer etabId, RefGroupeDto refGroupeDto);
	
	public List<RefGroupeDto> findAll(Integer etabId);
	
	public String  generateIdentify(String prefix, int orderLength);
	
	public String generateIdentify(Date dateCreation, Integer idEtab, String codeEtab);
	
	public List<RefGroupeDto> findByEtab(Integer idEtab);
	
}
