/**
 * [dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService.java] 
 * @author MAKERRI Sofiane on : 10 avr. 2014  12:14:00
 */
package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

/**
 * @author MAKERRI Sofiane  on : 10 avr. 2014  12:14:00
 */
public interface RefEtablissementService {
	
	public List<RefEtablissementDto> findAll();
	
	public List<RefEtablissementDto> findGeneric(String value);
	
	public List<RefEtablissementDto> findAdvanced(RefEtablissementDto refEtablissementDto);
	
	public RefEtablissementDto save(RefEtablissementDto refEtablissementDto);
	
	public void update(RefEtablissementDto refEtablissementDto);
	
	public void saveOrUpdate(RefEtablissementDto refEtablissementDto);
	
	public  RefEtablissementDto findById(Integer id);
	
	public  RefEtablissementDto findByIdentifiant(String identifiant);
	
	public  RefEtablissementDto findByLlLatin(String llLatin);
	
	public  RefEtablissementDto findByLlArabe(String llArabe);
	
    public  RefEtablissementDto findByLcLatin(String lcLatin);
  	
	public  RefEtablissementDto findByLcArabe(String lcArabe);
	
	public void  delete(String id);
	
	public void  delete(RefEtablissementDto refEtablissementDto);
	
	public String  generateIdentify(String prefix, int orderLength);

}
