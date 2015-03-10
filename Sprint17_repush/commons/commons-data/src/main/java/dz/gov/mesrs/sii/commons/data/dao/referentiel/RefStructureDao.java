package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefStructure;

public interface RefStructureDao {

	public void persist(RefStructure transientInstance);

	public void remove(RefStructure persistentInstance);

	public RefStructure merge(RefStructure detachedInstance);

	public RefStructure findById(Integer id);

	public List<RefStructure> findAll();

	public List<RefStructure> findGeneric(String value);

	public List<RefStructure> findAdvanced(RefStructure refStructure);
	
	public List<RefStructure> findAll(Integer etabId);

	public List<RefStructure> findGeneric(Integer etabId, String value);

	public List<RefStructure> findAdvanced(Integer etabId, RefStructure refStructure);
	
	public Integer findLastOrder(String prefix, int orderLength);
	
	/**
	 * [RefStructureDao.findStrcuturesByTypeByEtab] Method 
	 * @author rlaib  on : 11 oct. 2014  10:44:02
	 * @param etabId
	 * @param typeStructureId
	 * @return
	 */
	public List<RefStructure> findStructuresByTypeByEtab(Integer etabId, int typeStructureId);
	
	public List<RefStructure> findStructuresOfMasterStructureByEtab(Integer etabId, Integer masterStructureId);


}