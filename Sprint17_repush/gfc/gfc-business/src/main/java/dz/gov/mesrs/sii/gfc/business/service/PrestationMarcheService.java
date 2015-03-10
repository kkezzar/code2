package dz.gov.mesrs.sii.gfc.business.service;

import java.math.BigDecimal;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.model.gfc.PrestationMarche;
import dz.gov.mesrs.sii.gfc.business.model.dto.PrestationMarcheDto;

/**
 * Service Interface for domain model class PrestationMarche.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
public interface PrestationMarcheService extends CommonService<PrestationMarche, PrestationMarcheDto, Integer> {

	public BigDecimal calculerMontantTTC(PrestationMarcheDto prestationMarcheDto);
}