/**
 * [dz.gov.mesrs.sii.referentiel.business.service.RefPeriodeService.java] 
 * @author MAKERRI Sofiane on : 25 mars 2014  16:44:45
 */
package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPeriodeDto;

/**
 * @author MAKERRI Sofiane  on : 25 mars 2014  16:44:45
 */
public interface RefPeriodeService {
	
	public List<RefPeriodeDto> findAll();
	
	public List<RefPeriodeDto> findGeneric(String value);
	
	public RefPeriodeDto save(RefPeriodeDto refPeriodeDto);
	
	public void update(RefPeriodeDto refPeriodeDto);
	
	public  RefPeriodeDto findById(Integer id);
	
	public void  delete(RefPeriodeDto refPeriodeDto);
	
	public void saveOrUpdate(RefPeriodeDto refPeriodeDto);
	
	public  RefPeriodeDto findByIdentifiant(String identifiant);
	
	public RefPeriodeDto findByName(String name);
	
	public String  generateIdentify(String prefix, int orderLength);
	

}
