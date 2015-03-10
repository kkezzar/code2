package dz.gov.mesrs.sii.commons.data.dao.grh;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.grh.Rubrique;



/**
 * 
 * @author BELBRIK Oualid
 * @version V1.0
 * @date 17-10-2014
 * @description Interface  DAO Rubrique
 * 
 */
public interface RubriqueDao extends CommonDao<Rubrique, Integer> {

	/**
	 * for specific method
	 */

	
	public  void persist(Rubrique transientInstance);

	public  void remove(Rubrique persistentInstance);

	public  Rubrique merge(Rubrique detachedInstance);

	public  Rubrique findById(int id);
		
	public  List<Rubrique> findAll();
}