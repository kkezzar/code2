package dz.gov.mesrs.sii.commons.data.dao.bpm;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.bpm.RoleI18n;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface RoleI18nDao {

	public  void persist(RoleI18n transientInstance);

	public  void remove(RoleI18n persistentInstance);

	public  RoleI18n merge(RoleI18n detachedInstance);

	public  RoleI18n findById(int id);
	
	public  List<RoleI18n> findByQuery(String query) ;
	
	public  List<RoleI18n> findAll();

	public List<RoleI18n> findListRoleI18nByRole(int idRole);

}