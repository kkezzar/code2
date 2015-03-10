package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEvenementDto;

 

/**
 * @author BELDI Jamel  on : 13 f�vr. 2014  11:40:29
 */
public interface RefEvenementService {

	public List<RefEvenementDto> findAll();
	public List<RefEvenementDto> findGeneric(String value);
	public List<RefEvenementDto> findAdvanced(RefEvenementDto refEvenmentDto);
	public Integer save(RefEvenementDto refEvenmentDto);
	public void update(RefEvenementDto refEvenmentDto);
	public  RefEvenementDto findById(Integer id);
	public void  delete(Integer id);
	public List<RefEvenementDto> findAll(String etabLcLatin);
	public List<RefEvenementDto> findGeneric(Integer etabId, String value);
	public List<RefEvenementDto> findAdvanced(Integer etabId, RefEvenementDto refEvenmentDto);
	
	public List<RefEvenementDto> findByCodeType(Integer etabId, String codeType, Integer year);

}