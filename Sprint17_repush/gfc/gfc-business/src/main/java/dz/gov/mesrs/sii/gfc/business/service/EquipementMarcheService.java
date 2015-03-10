package dz.gov.mesrs.sii.gfc.business.service;

import java.math.BigDecimal;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.model.gfc.EquipementMarche;
import dz.gov.mesrs.sii.gfc.business.model.dto.EquipementMarcheDto;

/**
 * Service Interface for domain model class EquipementMarche.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
public interface EquipementMarcheService extends CommonService<EquipementMarche, EquipementMarcheDto, Integer> {

	public BigDecimal calculerMontantTTC(EquipementMarcheDto equipementMarcheDto);
}