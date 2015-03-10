package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEvenement;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

public interface RefEvenementDao {

	public void persist(RefEvenement transientInstance);

	public void remove(RefEvenement persistentInstance);

	public RefEvenement merge(RefEvenement detachedInstance);

	public RefEvenement findById(int id);

	public List<RefEvenement> findByQuery(String query);

	public List<RefEvenement> findAll();

	public List<RefEvenement> findGeneric(String value);

	public List<RefEvenement> findAdvanced(RefEvenement refEvenement);

	public List<RefEvenement> findAll(String etabLcLatin);

	public List<RefEvenement> findGeneric(Integer etabId, String value);

	public List<RefEvenement> findAdvanced(Integer etabId, RefEvenement refEvenement);
	
	public List<RefEvenement> findByCodeType(Integer etabId, String codeType, Integer year);

}