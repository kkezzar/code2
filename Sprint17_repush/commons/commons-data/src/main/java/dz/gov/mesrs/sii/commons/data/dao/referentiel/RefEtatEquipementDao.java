package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtatEquipement;


public interface RefEtatEquipementDao {

	public abstract void persist(RefEtatEquipement transientInstance);
	
	public abstract void remove(RefEtatEquipement persistentInstance);
	
	public abstract RefEtatEquipement merge(RefEtatEquipement detachedInstance);
	
	public abstract RefEtatEquipement findById(int id);
	
	public abstract List<RefEtatEquipement> findByIdEquipement(int idEquipement);
	
	
}