package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefPartenaire;

public interface RefPartenaireDao {

	public abstract void persist(RefPartenaire transientInstance);

	public abstract void remove(RefPartenaire persistentInstance);

	public abstract RefPartenaire merge(RefPartenaire detachedInstance);

	public abstract RefPartenaire findById(int id);
	
	public abstract List<RefPartenaire> findByIdContrat(int IdContrat);
	
	public abstract List<RefPartenaire> findByCodeContrat(String IdfContrat);
	
	public abstract List<RefPartenaire> findPartenairesStructure(Integer idStructure);

}