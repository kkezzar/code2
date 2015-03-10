package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefJourOuvre;

/**
 * 
 * @author BELDI Jamel
 *
 */
 

public interface RefJourOuvreDao {

	public  void persist(RefJourOuvre transientInstance);

	public  void remove(RefJourOuvre persistentInstance);

	public  RefJourOuvre merge(RefJourOuvre detachedInstance);

	public  RefJourOuvre findById(int id);
		
	public  List<RefJourOuvre> findAll();
	
	public RefJourOuvre findByDate(Date date);
	
	public List<RefJourOuvre> findBetweenTwoDate(Date dateDebut, Date dateFin);

}