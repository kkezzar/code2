package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;
import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.GroupePedagogique;


/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface GroupePedagogiqueDao {

	public  GroupePedagogique persist(GroupePedagogique transientInstance);

	public  void remove(GroupePedagogique persistentInstance);

	public  GroupePedagogique merge(GroupePedagogique detachedInstance);

	public  GroupePedagogique findById(int id);
	
	public  List<GroupePedagogique> findByQuery(String query) ;
	
	public  List<GroupePedagogique> findAll();
	
	public  List<GroupePedagogique> findGeneric(String value, String refCodeEtab);
	
	public  List<GroupePedagogique> findAdvanced(GroupePedagogique gp);
	
	public  List<GroupePedagogique> findByOf(Integer oofId, String refCodeEtab);
	
	public  List<GroupePedagogique> findSection(Integer oofId, Integer idPeriode);
	
	public GroupePedagogique groupeNameExist(GroupePedagogique groupePedagogique);
	
	public  List<GroupePedagogique> findGroupesOfSection(GroupePedagogique groupePedagogique);
	
	public  List<GroupePedagogique> findGroupesOfSection(Integer idSection);

}