package dz.gov.mesrs.sii.fve.business.service.scolarite;

import java.util.List;
import java.util.Map;

import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.DossierEmployeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantFicheServicesDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantFicheVoeuxDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantVoeuDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantVoeuLigneDto;
 
/**
 * @author rlaib  on : 12 oct. 2014  16:31:00
 */
public interface  EnseignantFicheVoeuxService {

	public  EnseignantFicheVoeuxDto insertOrUpdate( EnseignantFicheVoeuxDto enseignantFicheVoeuxDto);
	
	public  EnseignantFicheServicesDto generate(EnseignantFicheServicesDto enseignantFicheServicesDto);
	
	public  void remove(Integer id, String entityName);	
	
	public  EnseignantFicheVoeuxDto findById(int id);
	
	public  List<EnseignantFicheVoeuxDto> findByEnseignantId(int idEnseignant, int idAnnee);
	
	public  List<EnseignantFicheVoeuxDto> findAdvanced(EnseignantFicheVoeuxDto enseignantFicheVoeuxDto);
	
	public  List<EnseignantFicheVoeuxDto> findAll();
	
	public  List<EnseignantFicheVoeuxDto> findFichesByEtabByUserByOfByPeriodeByYear(Integer idEtab, Long idUser, Integer idAnnee, Integer idNcPeriode,boolean toSearch);
	
	public  List<EnseignantVoeuDto> findVoeuxByIdFiche(int idFiche);
	
	public EnseignantVoeuDto insertOrUpdate(EnseignantVoeuDto enseignantVoeuDto);
	
	public EnseignantVoeuLigneDto insertOrUpdate(EnseignantVoeuLigneDto enseignantVoeuLigneDto);
	
	public  List<EnseignantVoeuLigneDto> findLignesByIdVoeu(int idVoeu);
	
	public  List<EnseignantVoeuLigneDto> findLignesByIdFiche(int idFiche);
	
	public  List<EnseignantVoeuDto> saveVoeux( List<EnseignantVoeuDto> listVoeux);

	public  Map<Integer,Object> saveFicheVoeuxDetails(EnseignantFicheVoeuxDto enseignantFicheVoeuxDto, List<EnseignantVoeuDto> listVoeux, List<EnseignantVoeuLigneDto> listLignesVoeux);

	public  List<EnseignantVoeuLigneDto> saveLignesVoeu( List<EnseignantVoeuLigneDto> listLignes);
	
	public  List<EnseignantFicheVoeuxDto> findFichesVoeuxByEtabBySituation(Integer idEtab, Integer idAnnee, Integer idSituation);

	public DossierEmployeDto getEnseignantByIdUser(Integer idUser);
	
	public DossierEmployeDto getEnseignantById(Long id);
	
	public List<DossierEmployeDto> getAllEnseignants();
	
	public List<DossierEmployeDto> searchEnseignants(Integer idEtab,String query);
}