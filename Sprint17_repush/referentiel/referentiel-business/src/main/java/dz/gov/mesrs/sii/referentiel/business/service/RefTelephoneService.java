/**
 * [dz.gov.mesrs.sii.referentiel.business.service.RefTelephoneService.java] 
 * @author MAKERRI Sofiane on : 16 janv. 2014  15:51:06
 */
package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCoordonneeDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefTelephoneDto;

/**
 * @author MAKERRI Sofiane  on : 16 janv. 2014  15:51:06
 */
public interface RefTelephoneService {
	
	public List<RefTelephoneDto> findByRefIndividuId(Integer id);
	
	public List<RefTelephoneDto> findByRefStructureId(Integer id);
	
	public List<RefTelephoneDto> findByRefEtablissementId(Integer id);
	
	public List<RefTelephoneDto> findByRefLieuId(Integer id);
	
	public RefTelephoneDto save(RefTelephoneDto refTelephoneDto);
	
	public void update(RefTelephoneDto refTelephoneDto);
	
	public void saveOrUpdate(RefTelephoneDto refTelephoneDto);
	
	public void  delete(Integer id);
	
	public void  delete(RefTelephoneDto refTelephoneDto);
	
	public List<RefTelephoneDto> findByRefCoordonnee(
			List<RefCoordonneeDto> refCoordonneeList);
	
	public RefTelephoneDto findPrincipalTelephoneForIndividu(String identifiant);
	
	public RefTelephoneDto findPrincipalTelephoneForIndividu(String typeTel, String natureTel, Integer idIndividu);


}
