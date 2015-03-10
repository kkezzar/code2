package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAdresseElectronique;

public interface RefAdresseElectroniqueDao {

	public abstract void persist(RefAdresseElectronique transientInstance);

	public abstract void remove(RefAdresseElectronique persistentInstance);

	public abstract RefAdresseElectronique merge(RefAdresseElectronique detachedInstance);

	public abstract RefAdresseElectronique findById(int id);
	
	public RefAdresseElectronique findPrincipalAdresseElectroniqueForIndividu(String typeAdrElectro, String natureAdrElectro, Integer idIndividu);

}