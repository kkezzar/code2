/**
 * [dz.gov.mesrs.sii.referentiel.business.service.impl.RefAdresseElectroniqueService.java] 
 * @author MAKERRI Sofiane on : 19 janv. 2014  10:40:08
 */
package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAdresseElectroniqueDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCoordonneeDto;

/**
 * @author MAKERRI Sofiane  on : 19 janv. 2014  10:40:08
 */
public interface RefAdresseElectroniqueService {
	
	public List<RefAdresseElectroniqueDto> findByRefIndividuId(Integer id);
	
	public List<RefAdresseElectroniqueDto> findByRefStructureId(Integer id);
	
	public List<RefAdresseElectroniqueDto> findByRefEtablissementId(Integer id);
	
	public List<RefAdresseElectroniqueDto> findByRefLieuId(Integer id);
	
	public RefAdresseElectroniqueDto save(RefAdresseElectroniqueDto refAdresseElectroniqueDto);
	
	public void update(RefAdresseElectroniqueDto refAdresseElectroniqueDto);
	
	public void saveOrUpdate(RefAdresseElectroniqueDto refAdresseElectroniqueDto);
	
	public void  delete(RefAdresseElectroniqueDto refAdresseElectroniqueDto);
	
	public List<RefAdresseElectroniqueDto> findByRefCoordonnee(
			List<RefCoordonneeDto> refCoordonneeList);
	
	public RefAdresseElectroniqueDto findPrincipalAdresseElectroniqueForIndividu(String typeAdrElectro, String natureAdrElectro, Integer idIndividu);

}
