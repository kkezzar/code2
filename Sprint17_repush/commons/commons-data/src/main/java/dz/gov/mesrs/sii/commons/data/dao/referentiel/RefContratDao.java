package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefContrat;

public interface RefContratDao {

	public abstract void persist(RefContrat transientInstance);
	
	public abstract void remove(RefContrat persistentInstance);
	
	public abstract RefContrat merge(RefContrat detachedInstance);
	
	public abstract RefContrat findById(int id);
	
	public abstract RefContrat findByCode(String identifiant);
	
	public abstract List<RefContrat> findGeneric(String value, boolean avenant);
	
	public abstract List<RefContrat> findAdvanced(RefContrat refContrat, boolean avenant);
	
	public abstract List<RefContrat> findGeneric(Integer etabId, String value, boolean avenant);
	
	public abstract List<RefContrat> findAdvanced(Integer etabId, RefContrat refContrat, boolean avenant);
	
}