/**
 * [dz.gov.mesrs.sii.referentiel.business.service.RefParametreEtabService.java] 
 * @author MAKERRI Sofiane on : 27 avr. 2014  14:19:11
 */
package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefParametreEtabDto;

/**
 * @author MAKERRI Sofiane  on : 27 avr. 2014  14:19:11
 */
public interface RefParametreEtabService {

    public List<RefParametreEtabDto> findGeneric(Integer idEtab, String value);
    
    public List<RefParametreEtabDto> findAll(Integer idEtab);
	
	public RefParametreEtabDto findById(Integer id);
	
	public RefParametreEtabDto findByKey(Integer id, String key);
	
	public RefParametreEtabDto findByKey(Integer id, String idfEtab, String key);
	
	public RefParametreEtabDto findByKeyEtab(String idfEtab, String key);
		
	public String findValueOfKey(String key);
	
	public String findValueOfKey(Integer idEtab,String key);
	
	public RefParametreEtabDto save(RefParametreEtabDto refParametreEtabDto);
	
	public void update(RefParametreEtabDto refParametreEtabDto);
	
	public void  delete(RefParametreEtabDto refParametreEtabDto);

}
