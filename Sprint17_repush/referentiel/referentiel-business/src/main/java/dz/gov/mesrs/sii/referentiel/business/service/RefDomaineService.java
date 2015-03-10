/**
 * [dz.gov.mesrs.sii.referentiel.business.service.RefDomaineService.java] 
 * @author MAKERRI Sofiane on : 18 f�vr. 2014  09:39:07
 */
package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineDto;

/**
 * @author MAKERRI Sofiane  on : 18 f�vr. 2014  09:39:07
 */
public interface RefDomaineService {
	
    public  RefDomaineDto insertOrUpdate(RefDomaineDto refDomaineDto);
	
	public  void remove(RefDomaineDto refDomaineDto);	
	
	public  RefDomaineDto findById(int id);
	
	public  RefDomaineDto findByIdentifiant(String identifiant);
	
	public  RefDomaineDto findByName(String name);
	
	public  RefDomaineDto findByNameArabe(String nameArabe);
	
	public  List<RefDomaineDto> findByQuery(String query) ;
	
	public  List<RefDomaineDto> findAll() ;	
	
    public RefDomaineDto save(RefDomaineDto refDomaineDto);
	
	public void update(RefDomaineDto refDomaineDto);
	
	public  List<RefDomaineDto> findByIdRef(int id) ;
	
	public List<RefDomaineDto> findGeneric(String value);
	
	public List<RefDomaineDto> findSubDomaine(int id);
	
	public List<RefDomaineDto> findSubDomaine(String identifiant);
	
	public void updateRangSubDomaine(String identifiant);
	
	public int findSubDomaineLastRang(int id);
	
	public int findDomaineLastRang();
	
	public List<RefDomaineDto> findDomaines();
	
	public List<RefDomaineDto> findDomainesAndSubDomaines();
	
	public String  generateIdentify(String prefix, int orderLength);

}
