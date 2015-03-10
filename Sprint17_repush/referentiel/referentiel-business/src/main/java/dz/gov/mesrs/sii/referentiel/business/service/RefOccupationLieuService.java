/**
 * [dz.gov.mesrs.sii.referentiel.business.service.RefOccupationLieuService.java] 
 * @author MAKERRI Sofiane on : 2 janv. 2014  12:29:03
 */
package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefOccupationLieuDto;

/**
 * @author BELBRIK Oualid  on : 19 02. 2014  17:29:03
 */
public interface RefOccupationLieuService {
	
	public List<RefOccupationLieuDto> findByIdLieu(Integer value);
	
	public List<RefOccupationLieuDto> findByIdEquipement(Integer value);
	
	public void save(RefOccupationLieuDto refOccupationLieuDto);
	
	public void update(RefOccupationLieuDto refOccupationLieuDto);
	
	public void saveOrUpdate(RefOccupationLieuDto refOccupationLieuDto);
	
	public void  delete(RefOccupationLieuDto refOccupationLieuDto);

}
