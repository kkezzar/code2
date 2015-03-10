/**
 * [dz.gov.mesrs.sii.referentiel.business.service.RefPartenaireService.java] 
 * @author MAKERRI Sofiane on : 2 janv. 2014  12:29:03
 */
package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPartenaireDto;

/**
 * @author MAKERRI Sofiane  on : 2 janv. 2014  12:29:03
 */
public interface RefPartenaireService {
	
	public List<RefPartenaireDto> findByIdContrat(Integer value);
	
	public List<RefPartenaireDto> findByCodeContrat(String value);
	
	public void save(RefPartenaireDto refPartenaireDto);
	
	public void update(RefPartenaireDto refPartenaireDto);
	
	public void saveOrUpdate(RefPartenaireDto refPartenaireDto);
	
	public void  delete(RefPartenaireDto refPartenaireDto);

	public List<RefPartenaireDto> findPartenairesStructure(Integer idStructure);
}
