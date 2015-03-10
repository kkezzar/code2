package dz.gov.mesrs.sii.commons.data.dao.fve.examen;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.examen.PlanningSession;

/**
 * 
 * @author BELDI Jamel  on : 17 sept. 2014  16:35:17
 */
 

public interface PlanningSessionDao {

	public  void persist(PlanningSession transientInstance);

	public  void remove(PlanningSession persistentInstance);

	public  PlanningSession merge(PlanningSession detachedInstance);

	public  PlanningSession findById(long id);
		
	public  List<PlanningSession> findAll();
	
	public  PlanningSession findByYearAndOfAndLevelAndPeriodAndTypeSession(int idAnneeAcademique, int idOuvertureOf, int idNiveau, int idPeriode, int ncTypeSessionId);
	
	public  List<PlanningSession> findAdvanced(PlanningSession planningSession);

	public List<PlanningSession> findSessionforStudent(int idDossierInsciptionAdministrative);

}