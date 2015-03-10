package dz.gov.mesrs.sii.fve.business.service.scolarite;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantChargePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantFicheServicesDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantServiceFaitDto;
 
/**
 * @author rlaib  on : 12 oct. 2014  16:31:00
 */
public interface  EnseignantFicheServicesService {

	public  EnseignantFicheServicesDto insertOrUpdate( EnseignantFicheServicesDto enseignantFicheServicesDto);
	
	public  EnseignantFicheServicesDto validate( Integer idFicheServices);
	
	public  EnseignantFicheServicesDto findById( Integer id);

	public  List<EnseignantFicheServicesDto> findFichesServicesByEtabByUserByPeriodeByYear(Integer idEtab, Long idUser, Integer idAnnee, Integer idPeriode);
	
	public  List<EnseignantFicheServicesDto> findFichesServicesByEtabBySituation(Integer idEtab, Integer idSituation);
	
	public  List<EnseignantChargePedagogiqueDto> findChargesPedagogiquesByFicheServicesId(Integer idFicheService);

	public List<EnseignantChargePedagogiqueDto> saveChargesPedagogiques(List<EnseignantChargePedagogiqueDto> listCharges);
	
}