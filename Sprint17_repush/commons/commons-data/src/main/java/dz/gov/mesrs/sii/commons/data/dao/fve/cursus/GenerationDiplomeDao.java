
package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.GenerationDiplome;

/**
 * @author  Oualid BELBRIK  on : 23 octobre 2014  14:10:13
 */
public interface GenerationDiplomeDao {

	public  void persist(GenerationDiplome transientInstance);
	
	public  GenerationDiplome merge(GenerationDiplome detachedInstance);

	public  GenerationDiplome findById(int id);
	
	public  List<GenerationDiplome> findAll();
	
	public  List<GenerationDiplome> findByEtab(String codeEtab);
	
	public  List<GenerationDiplome> findByYear(String codeEtablissement, String year);
	
	public  List<String> getAllYears();
	
	public void flushAndClear();

	
}
