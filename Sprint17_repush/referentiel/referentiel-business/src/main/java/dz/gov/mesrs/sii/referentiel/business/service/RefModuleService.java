/**
 * [dz.gov.mesrs.sii.referentiel.business.service.RefModuleService.java] 
 * @author MAKERRI Sofiane on : 23 f�vr. 2014  15:17:41
 */
package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefModuleDto;

/**
 * @author MAKERRI Sofiane  on : 23 f�vr. 2014  15:17:41
 */
public interface RefModuleService {
	
    public  RefModuleDto insertOrUpdate(RefModuleDto refModuleDto);
	
	public  void remove( RefModuleDto refModuleDto);	
	
	public  RefModuleDto findById(int id);
	
	public  List<RefModuleDto> findByQuery(String query) ;
	
	public  List<RefModuleDto> findAll();
	
	public  List<RefModuleDto> findGeneric(String value);
	
	public RefModuleDto findByIdentifiant(String identifiant);
	
	public  RefModuleDto findByName(Integer id, String name);
	
	public  RefModuleDto findByNameArabe(Integer id, String name);
	
    public RefModuleDto save(RefModuleDto refModuleDto);
	
	public void update(RefModuleDto refModuleDto);
	
	public int findModuleLastRang(int id);
	
	public  List<RefModuleDto>findModules(int id);
	
	public List<RefModuleDto> findModules();
	
	public String  generateIdentify(String prefix, int orderLength);


}
