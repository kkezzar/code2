/**
 * 
 */
package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;









import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtatEquipementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefReservationDto;

/**
 * @author obelbrik
 *
 */
public interface RefEtatEquipementService {
	public void save(RefEtatEquipementDto refEtatEquipementDto);
	
	public void update(RefEtatEquipementDto refEtatEquipementDto);
	
	public  RefEtatEquipementDto findById(int id);
	
	public void  delete(RefEtatEquipementDto refEtatEquipementDto);
	
	public List<RefEtatEquipementDto> findByIdEquipement(Integer value);
	
	public Object findEtat(String idf);

	

}
