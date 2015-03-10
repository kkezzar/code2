/**
 * [dz.gov.mesrs.sii.fve.business.persistence.cursus.BilanPeriodeDao.java] 
 * @author MAKERRI Sofiane on : 22 oct. 2014  15:46:41
 */
package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Mention;

/**
 * @author MAKERRI Sofiane on : 22 oct. 2014 15:46:41
 */
public interface MentionDao {

	public void persist(Mention transientInstance);

	public void remove(Mention persistentInstance);

	public Mention merge(Mention detachedInstance);

	public Mention findById(int id);

	public List<Mention> findAll();
	
	public Mention findByNote(Integer anneeAcademiqueId, double note);
	
	public List<Mention> findByAnneeAcademique(Integer anneeAcademiqueId);

}
