package dz.gov.mesrs.sii.commons.data.dao.bpm;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.bpm.Role;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface RoleDao {

	public  void persist(Role transientInstance);

	public  void remove(Role persistentInstance);

	public  Role merge(Role detachedInstance);

	public  Role findById(int id);
	
	public  List<Role> findByQuery(String query) ;
	
	public  List<Role> findAll();

}