package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefRelationGroupe;

public interface RefRelationGroupeDao {

	public abstract void persist(RefRelationGroupe transientInstance);

	public abstract void remove(RefRelationGroupe persistentInstance);

	public abstract RefRelationGroupe merge(RefRelationGroupe detachedInstance);

	public abstract RefRelationGroupe findById(int id);

}