package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefCoordonnee;

public interface RefCoordonneeDao {

	public abstract void persist(RefCoordonnee transientInstance);

	public abstract void remove(RefCoordonnee persistentInstance);

	public abstract RefCoordonnee merge(RefCoordonnee detachedInstance);

	public abstract RefCoordonnee findById(int id);
	
	public abstract List<RefCoordonnee> findByRefIndividuId(int type, int id);
	
	public abstract List<RefCoordonnee> findByRefStructureId(int type, int id);
	
	public abstract List<RefCoordonnee> findByRefLieuId(int type, int id);
	
	public abstract List<RefCoordonnee> findByRefEtablissementId(int type, int id);
	
	public abstract List<RefCoordonnee> findByRefIndividuId(int id);
	
    public abstract List<RefCoordonnee> findByRefStructureId(int id);
	
	public abstract List<RefCoordonnee> findByRefLieuId(int id);
	
	public abstract List<RefCoordonnee> findByRefEtablissementId(int id);

}