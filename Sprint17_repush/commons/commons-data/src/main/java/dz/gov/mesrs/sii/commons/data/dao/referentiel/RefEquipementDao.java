package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEquipement;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface RefEquipementDao {

	public  void persist(RefEquipement transientInstance);

	public  void remove(RefEquipement persistentInstance);

	public  RefEquipement merge(RefEquipement detachedInstance);

	public  RefEquipement findById(String id);
	
	public  List<RefEquipement> findByQuery(String query) ;
	
	public  List<RefEquipement> findAll();
	
	public  List<RefEquipement> findGeneric(String value);
		
	public  List<RefEquipement> findAdvanced(RefEquipement refEquipement);
	
    public  List<RefEquipement> findAll(Integer etabId);
	
	public  List<RefEquipement> findGeneric(Integer etabId, String value);
		
	public  List<RefEquipement> findAdvanced(Integer etabId, RefEquipement refEquipement);

}