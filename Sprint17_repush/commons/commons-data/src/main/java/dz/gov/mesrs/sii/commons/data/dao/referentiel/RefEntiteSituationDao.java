package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEntiteSituation;

public interface RefEntiteSituationDao {

	public abstract void persist(RefEntiteSituation transientInstance);

	public abstract void remove(RefEntiteSituation persistentInstance);

	public abstract RefEntiteSituation merge(RefEntiteSituation detachedInstance);

	public abstract RefEntiteSituation findById(int id);
	
	public abstract List<RefEntiteSituation> findListSituationByEntityName(String entityName);

}