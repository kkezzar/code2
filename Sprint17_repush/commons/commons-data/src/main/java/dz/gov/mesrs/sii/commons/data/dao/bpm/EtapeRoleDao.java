package dz.gov.mesrs.sii.commons.data.dao.bpm;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.bpm.EtapeRole;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface EtapeRoleDao {

	public  void persist(EtapeRole transientInstance);

	public  void remove(EtapeRole persistentInstance);

	public  EtapeRole merge(EtapeRole detachedInstance);

	public  EtapeRole findById(int id);
	
	public  List<EtapeRole> findByQuery(String query) ;
	
	public  List<EtapeRole> findAll();

	public  EtapeRole findByCodeEtapeByCodeRole(String codeEtape, String codeRole);

}