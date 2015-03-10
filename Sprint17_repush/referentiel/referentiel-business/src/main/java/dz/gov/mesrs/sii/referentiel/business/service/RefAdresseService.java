/**
 * [dz.gov.mesrs.sii.referentiel.business.service.RefAdresseService.java] 
 * @author MAKERRI Sofiane on : 15 janv. 2014  13:12:13
 */
package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAdresseDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCoordonneeDto;

/**
 * @author MAKERRI Sofiane on : 15 janv. 2014 13:12:13
 */
public interface RefAdresseService {

	public List<RefAdresseDto> findByRefIndividuId(Integer id);

	public List<RefAdresseDto> findByRefStructureId(Integer id);

	public RefAdresseDto save(RefAdresseDto refAdresseDto);

	public void update(RefAdresseDto refAdresseDto);

	public void saveOrUpdate(RefAdresseDto refAdresseDto);

	public void delete(RefAdresseDto refAdresseDto);

	public List<RefAdresseDto> findByRefLieuId(Integer id);

	public List<RefAdresseDto> findByRefEtablissementId(Integer id);

	public List<RefAdresseDto> findByRefCoordonnee(
			List<RefCoordonneeDto> refCoordonneeList);

	public RefAdresseDto findPrincipalAdresseForIndividu(String identifiant);
	
	public RefAdresseDto findPrincipalAdresseForIndividu(String typeAdresse, Integer idIndividu);

}
