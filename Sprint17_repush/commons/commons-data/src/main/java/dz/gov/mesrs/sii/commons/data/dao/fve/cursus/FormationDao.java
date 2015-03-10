package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Formation;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

public interface FormationDao {

	public void persist(Formation transientInstance);

	public void remove(Formation persistentInstance);

	public Formation merge(Formation detachedInstance);

	public Formation findById(int id);

	public List<Formation> findAll();

	public List<Formation> findFormationsByIdDossier(int idDossier);

}