package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Diplome;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

public interface DiplomeDao {

	public void persist(Diplome transientInstance);

	public void remove(Diplome persistentInstance);

	public Diplome merge(Diplome detachedInstance);

	public Diplome findById(int id);

	public List<Diplome> findAll();

	public List<Diplome> findDiplomesByIdDossier(int idDossier);

}