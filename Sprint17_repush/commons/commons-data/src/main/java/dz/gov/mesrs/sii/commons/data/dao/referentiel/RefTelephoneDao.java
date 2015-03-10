package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefTelephone;

public interface RefTelephoneDao {

	public abstract void persist(RefTelephone transientInstance);

	public abstract void remove(RefTelephone persistentInstance);

	public abstract RefTelephone merge(RefTelephone detachedInstance);

	public abstract RefTelephone findById(int id);

	public abstract RefTelephone findPrincipalTelephoneForIndividu(
			String identifiant);
	
	public RefTelephone findPrincipalTelephoneForIndividu(String typeTel, String natureTel, Integer idIndividu);
	

}