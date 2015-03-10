package dz.gov.mesrs.sii.commons.data.dao.fve.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Cycle;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

public interface CycleDao {

	public  void persist(Cycle transientInstance);

	public  void remove(int  id);

	public  Cycle merge(Cycle detachedInstance);

	public  Cycle findById(int id);
	
	public  Cycle findByCode(String code);
	
	public  List<Cycle> findAll();
	
	public  List<Cycle> findByTypeFormationCode(String codeTypeFormation);
	
	public  Cycle findByTypeFormationByCode(String codeTypeFormation,String codeCycle);
	
}