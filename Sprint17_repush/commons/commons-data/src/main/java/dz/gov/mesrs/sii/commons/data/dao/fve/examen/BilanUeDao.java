/**
 * [dz.gov.mesrs.sii.fve.business.persistence.cursus.BilanPeriodeDao.java] 
 * @author MAKERRI Sofiane on : 22 oct. 2014  15:46:41
 */
package dz.gov.mesrs.sii.commons.data.dao.fve.examen;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.examen.BilanUe;

/**
 * @author MAKERRI Sofiane on : 22 oct. 2014 15:46:41
 */
public interface BilanUeDao {

	public void persist(BilanUe transientInstance);

	public void remove(BilanUe persistentInstance);

	public BilanUe merge(BilanUe detachedInstance);

	public BilanUe findById(int id);

	public List<BilanUe> findAll();
	
	public BilanUe findUnique(long bilanSessionId, int repartitionUeId, int type);
	
	public List<BilanUe> findByBilanSessionId(int bilanSessionId);
	
	public List<BilanUe> findByOofAndNiveau(Integer oofId, Integer niveauId);

}
