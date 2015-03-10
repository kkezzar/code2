package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAdresse;

public interface RefAdresseDao {

	public abstract void persist(RefAdresse transientInstance);

	public abstract void remove(RefAdresse persistentInstance);

	public abstract RefAdresse merge(RefAdresse detachedInstance);

	public abstract RefAdresse findById(int id);
	
	public abstract RefAdresse findPrincipalAdresseForIndividu(String identifiant);
	
	public abstract RefAdresse findPrincipalAdresseForIndividu(String typeAdresse,Integer idIndividu);

}