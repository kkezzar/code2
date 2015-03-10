package dz.gov.mesrs.sii.fve.business.service.scolarite;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantChargePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantServiceFaitDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.TypeSeanceDto;
 
/**
 * @author rlaib  on : 12 oct. 2014  16:31:00
 */
public interface  EnseignantServiceFaitService {

	public  EnseignantServiceFaitDto insertOrUpdate( EnseignantServiceFaitDto enseignantServiceFaitDto);
	
	public  EnseignantServiceFaitDto findById(int id);
	
	public  List<EnseignantServiceFaitDto> findServicesFaitsByEtabByUserByOfByPeriodeByYearByCharge(Integer idEtab
			, Long idUser
			, Integer idAnnee
			, Integer idOf
			, Integer idPeriode
			, Integer idCharge);
	
	public  List<Object[]> findSyntheseChargesEnseignant(Integer idEtab
			, Long idUser
			, Integer idAnnee
			, Integer idPeriode
			);
	
	
	public List<TypeSeanceDto> getAllTypesSeances();
}