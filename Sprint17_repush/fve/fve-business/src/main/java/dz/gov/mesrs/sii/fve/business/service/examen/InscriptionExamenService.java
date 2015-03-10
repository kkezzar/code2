package dz.gov.mesrs.sii.fve.business.service.examen;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.InscriptionExamenDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.SalleExamenDto;



/**
 * 
 * @author BELDI Jamel  on : 17 sept. 2014  17:09:02
 */
 
public interface  InscriptionExamenService {

	public  InscriptionExamenDto insertOrUpdate( InscriptionExamenDto inscriptionExamenDto);
	
	public  void remove( InscriptionExamenDto inscriptionExamenDto);	
	
	public  InscriptionExamenDto findById(long id);
	
	public  List<InscriptionExamenDto> findAll() ;	
	
	public  List<InscriptionExamenDto> findBySalleExamen(int idSalleExamen);
	
	public Double calculAverage(Long idExamen);
	
	public  List<InscriptionExamenDto> findFailledStudent(Long idExamen, Double noteObtention);
	
	public  List<InscriptionExamenDto> findByPlanning(Long idPlanning);
	
	public  List<InscriptionExamenDto> findByPlanningId(Long idPlanning);
	
	public  List<DossierInscriptionAdministrativeDto> findDiaByPlanning(Long idPlanning);
	
	public  List<InscriptionExamenDto> findNoteByPlanning(Long idPlanning);

	public List<InscriptionExamenDto> findByExamen(Long examenSessionId);
	
	public List<InscriptionExamenDto> findNotSubscribedOnExamen(Long examenSessionId, Integer ouvertureOffreFormationId);
	
	public List<InscriptionExamenDto> findAdvancedNotSubscribedOnExamen(Long examenSessionId, DossierInscriptionAdministrativeDto searchDto);

	public List<InscriptionExamenDto> findByPlanningAndStudent(Long idPlanning, Integer idDossierInscriptionAdministrative);
	
	public List<InscriptionExamenDto> findSubscribedAndNotSubscribed(SalleExamenDto salleExamenDto);
	
	public List<InscriptionExamenDto> findAbsentByPlanningAndRattachementMc(Long planningId, Integer rattachementMcId, Boolean absenceJustifie);
	
	public List<InscriptionExamenDto> findAdvanced(InscriptionExamenDto searchDto);
	

}
