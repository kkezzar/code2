/**
 * [dz.gov.mesrs.sii.fve.business.persistence.cursus.BilanControleContinuDao.java] 
 * @author MAKERRI Sofiane on : 16 oct. 2014  17:17:17
 */
package dz.gov.mesrs.sii.commons.data.dao.fve.examen;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.examen.BilanControleContinu;

/**
 * @author MAKERRI Sofiane on : 16 oct. 2014 17:17:17
 */
public interface BilanControleContinuDao {

	public void persist(BilanControleContinu transientInstance);

	public void remove(BilanControleContinu persistentInstance);

	public BilanControleContinu merge(BilanControleContinu detachedInstance);

	public BilanControleContinu findById(int id);

	public List<BilanControleContinu> findAll();

	public BilanControleContinu findUnique(Integer anneeId, Integer oofId,
			Integer periodeId, Integer affGpId, Integer rattachementMcId);

	public BilanControleContinu findUniqueByDiaId(Integer anneeId,
			Integer oofId, Integer periodeId, Integer diaId,
			Integer rattachementMcId);

	public List<BilanControleContinu> findAdvanced(BilanControleContinu search);

}
