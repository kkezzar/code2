package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefLieu;

public interface RefLieuDao {

	public void persist(RefLieu transientInstance);

	public void remove(RefLieu persistentInstance);

	public RefLieu merge(RefLieu detachedInstance);

	public RefLieu findById(int id);

	public List<RefLieu> findAll();

	public List<RefLieu> findGeneric(String value);

	public List<RefLieu> findAdvanced(RefLieu refLieu);

	public List<RefLieu> findAll(Integer etabId);

	public List<RefLieu> findGeneric(Integer etabId, String value);

	public List<RefLieu> findAdvanced(Integer etabId, RefLieu refLieu);
	
	public RefLieu findByCode(String code);
	
	public  List<RefLieu> findSalleAndAmphi(Integer etabId);
	

}