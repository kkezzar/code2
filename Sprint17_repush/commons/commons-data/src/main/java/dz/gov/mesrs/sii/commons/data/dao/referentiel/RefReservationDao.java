package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefReservation;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface RefReservationDao {

	public  void persist(RefReservation transientInstance);

	public  void remove(RefReservation persistentInstance);

	public  RefReservation merge(RefReservation detachedInstance);

	public  RefReservation findById(int id);
	
	public  List<RefReservation> findByQuery(String query) ;
	
	public  List<RefReservation> findAll();
		
	public abstract List<RefReservation> findByIdLieu(int IdLieu);

	public abstract List<RefReservation> findByIdEquipement(int idEquipement);


}