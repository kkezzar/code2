/**
 * [dz.gov.mesrs.sii.referentiel.business.service.RefCoordonneeService.java] 
 * @author MAKERRI Sofiane on : 15 janv. 2014  12:40:19
 */
package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCoordonneeDto;

/**
 * @author MAKERRI Sofiane  on : 15 janv. 2014  12:40:19
 */
public interface RefCoordonneeService {
	
	public List<RefCoordonneeDto> findByRefIndividuId(Integer id);
	
	public List<RefCoordonneeDto> findByRefStructureId(Integer id);
	
	public List<RefCoordonneeDto> findByRefLieuId(Integer id);
	
	public List<RefCoordonneeDto> findByRefEtablissementId(Integer identifiant);
	
	public void save(RefCoordonneeDto refCoordonneeDto);
	
	public void update(RefCoordonneeDto refCoordonneeDto);
	
	public void saveOrUpdate(RefCoordonneeDto refCoordonneeDto);
	
	public void  delete(Integer id);
	

}
