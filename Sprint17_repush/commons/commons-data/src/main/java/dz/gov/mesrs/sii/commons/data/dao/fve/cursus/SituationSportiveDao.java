package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;

import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.SituationSportive;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

public interface SituationSportiveDao {

	public void persist(SituationSportive transientInstance);

	public void remove(SituationSportive persistentInstance);

	public SituationSportive merge(SituationSportive detachedInstance);

	public SituationSportive findById(int id);

	public List<SituationSportive> findByQuery(String query);

	public List<SituationSportive> findAll();

	public List<SituationSportive> findByIdDossier(int id);

	public SituationSportive findSituationSportive(int id, Date dateDebut,
			Date dateFin, String refCodeType);

}