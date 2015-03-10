package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;

import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.SituationHandicap;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface SituationHandicapDao {

	public  void persist(SituationHandicap transientInstance);

	public  void remove(SituationHandicap persistentInstance);

	public  SituationHandicap merge(SituationHandicap detachedInstance);

	public  SituationHandicap findById(int id);
	
	public  List<SituationHandicap> findByQuery(String query) ;
	
	public  List<SituationHandicap> findAll();
	
	public  List<SituationHandicap> findByIdDossier(int id);
	
	public  SituationHandicap findSituationHandicap(int id, Date dateDebut, Date dateFin, String refCodeType);

}