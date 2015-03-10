/**
 * [dz.gov.mesrs.sii.referentiel.business.service.RefOccupationLieuService.java] 
 * @author MAKERRI Sofiane on : 2 janv. 2014  12:29:03
 */
package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefReservationDto;

/**
 * @author BELBRIK Oualid  on : 17 03. 2014  15:00:03
 */
public interface RefReservationService {
	
	public List<RefReservationDto> findByIdLieu(Integer value);
	
	public List<RefReservationDto> findByIdEquipement(Integer value);
	
	public void save(RefReservationDto refReservationDto);
	
	public void update(RefReservationDto refReservationDto);
	
	public void saveOrUpdate(RefReservationDto refReservationDto);
	
	public void  delete(RefReservationDto refReservationDto);


}


	
