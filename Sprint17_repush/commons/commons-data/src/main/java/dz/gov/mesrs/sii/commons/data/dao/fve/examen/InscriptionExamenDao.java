package dz.gov.mesrs.sii.commons.data.dao.fve.examen;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierInscriptionAdministrative;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.InscriptionExamen;

/**
 * 
 * @author BELDI Jamel  on : 17 sept. 2014  16:35:08
 */
 

public interface InscriptionExamenDao {

	public  void persist(InscriptionExamen transientInstance);

	public  void remove(InscriptionExamen persistentInstance);

	public  InscriptionExamen merge(InscriptionExamen detachedInstance);

	public  InscriptionExamen findById(long id);
		
	public  List<InscriptionExamen> findAll();
	
	public  List<InscriptionExamen> findBySalleExamen(int idSalleExamen);
	
	public Double calculAverage(Long idExamen);
	
	public  List<InscriptionExamen> findFailledStudent(Long idExamen, Double noteObtention);
	
	public  List<InscriptionExamen> findByPlanning(Long idPlanning);
	
	public  List<InscriptionExamen> findByPlanningId(Long idPlanning);
	
	public  List<DossierInscriptionAdministrative> findDiaByPlanning(Long idPlanning);
	
	public  List<InscriptionExamen> findNoteByPlanning(Long idPlanning);

	public List<InscriptionExamen> findByExamen(Long examenSessionId);

	public List<DossierInscriptionAdministrative> findNotSubscribedOnExamen(
			Long examenSessionId, Integer ouvertureOffreFormationId);
	
	public List<DossierInscriptionAdministrative> findAdvancedNotSubscribedOnExamen(
			Long examenSessionId, DossierInscriptionAdministrative search);

	public List<InscriptionExamen> findByPlanningAndStudent(
			Long idPlanning, Integer idDossierInscriptionAdministrative);
	
	public List<InscriptionExamen> findAbsentByPlanningAndRattachementMc(Long planningId, Integer rattachementMcId, Boolean absenceJustifie);
	
	public List<InscriptionExamen> findAdvanced(InscriptionExamen search);
	

}
