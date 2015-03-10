/**
 * [dz.gov.mesrs.sii.referentiel.business.service.RefParametreConfigurationService.java] 
 * @author MAKERRI Sofiane on : 22 avr. 2014  12:46:27
 */
package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefParametreDto;

/**
 * @author MAKERRI Sofiane on : 22 avr. 2014 12:46:27
 */
public interface RefParametreService {

	public List<RefParametreDto> findGeneric(String value);

	public List<RefParametreDto> findAllNotGeneric();

	public RefParametreDto findById(Integer id);

	public RefParametreDto findByKey(Integer id, String key);

	/*
	 * public RefParametreDto findByKey(Integer id, String idfEtab, String key);
	 * 
	 * public String findValueOfKey(String key);
	 * 
	 * public String findValueOfKey(String idfEtab,String key);
	 */

	public RefParametreDto save(RefParametreDto refParametreConfigurationDto);

	public void update(RefParametreDto refParametreConfigurationDto);

	public void delete(RefParametreDto refParametreConfigurationDto);

}
