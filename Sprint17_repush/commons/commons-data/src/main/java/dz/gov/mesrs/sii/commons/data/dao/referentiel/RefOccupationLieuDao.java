package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefOccupationLieu;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface RefOccupationLieuDao {

	public  void persist(RefOccupationLieu transientInstance);

	public  void remove(RefOccupationLieu persistentInstance);

	public  RefOccupationLieu merge(RefOccupationLieu detachedInstance);

	public  RefOccupationLieu findById(int id);
	
	public  List<RefOccupationLieu> findByQuery(String query) ;
	
	public  List<RefOccupationLieu> findAll();
	
	public abstract List<RefOccupationLieu> findByIdLieu(int IdLieu);

	public abstract List<RefOccupationLieu> findByIdEquipement(int idEquipement);

}