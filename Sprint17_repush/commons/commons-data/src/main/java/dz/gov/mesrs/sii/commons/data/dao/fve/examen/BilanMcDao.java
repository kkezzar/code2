/**
 * [dz.gov.mesrs.sii.fve.business.persistence.cursus.BilanPeriodeDao.java] 
 * @author MAKERRI Sofiane on : 22 oct. 2014  15:46:41
 */
package dz.gov.mesrs.sii.commons.data.dao.fve.examen;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.examen.BilanMc;

/**
 * @author MAKERRI Sofiane on : 22 oct. 2014 15:46:41
 */
public interface BilanMcDao {

	public void persist(BilanMc transientInstance);

	public void remove(BilanMc persistentInstance);

	public BilanMc merge(BilanMc detachedInstance);

	public BilanMc findById(int id);

	public List<BilanMc> findAll();
	
	public BilanMc findUnique(int bilanUeId, int rattachementMcId, int type);
	
	public List<BilanMc> findByBilanUeId(int bilanUeId);
	
	public List<BilanMc> findByOofAndNiveau(Integer oofId, Integer niveauId);

}
