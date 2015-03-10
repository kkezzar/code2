package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.TitreAcces;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

public interface TitreAccesDao {

	public void persist(TitreAcces transientInstance);

	public void remove(TitreAcces persistentInstance);

	public TitreAcces merge(TitreAcces detachedInstance);

	public TitreAcces findById(int id);

	public List<TitreAcces> findAll();

	public TitreAcces findByIdDossier(int idDossier);

}