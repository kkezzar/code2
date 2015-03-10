/**
 * [dz.gov.mesrs.sii.fve.business.persistence.bac.DossierBachelierDao.java] 
 * @author  Rafik LAIB on : 21 mai 2014  14:10:13
 */
package dz.gov.mesrs.sii.commons.data.dao.fve.bac;

import java.util.List;
import java.util.Map;

import dz.gov.mesrs.sii.commons.data.model.fve.bac.DossierBachelier;

/**
 * @author  Rafik LAIB  on : 21 mai 2014  14:10:13
 */
public interface DossierBachelierDao {

	public  void persist(DossierBachelier transientInstance);

	public  void remove(DossierBachelier persistentInstance);

	public  DossierBachelier merge(DossierBachelier detachedInstance);

	public  DossierBachelier findById(int id);
	
	public  List<DossierBachelier> findGeneric(String text) ;
	
	public  List<DossierBachelier> findAll();
	
	public  DossierBachelier findByMatricule(String matricule);
	
	public  List<DossierBachelier> findAdvanced(Map<String, String> attributesForKeyWordSearch, Map<String, String> attributesDossierBachelier, Map<String, String> attributesAffectationBachelier
			,  boolean withStudentFile, boolean hasAffectationCorrespondanceCode, String year
			, Integer start, Integer pageSize);
	
	public  List<DossierBachelier> findAdvancedWithPaging(Map<String, String> attributesForKeyWordSearch, Map<String, String> attributesDossierBachelier, Map<String, String> attributesAffectationBachelier,  boolean withStudentFile, String year, int start, int page);

	public  Integer getFindAdvancedCount(Map<String, String> attributesForKeyWordSearch, 
			Map<String, String> attributesDossierBachelier,
			Map<String, String> attributesAffectationBachelier
, boolean withStudentFile, boolean hasAffectationCorrespondanceCode, String year);

	public List<DossierBachelier> findByImportId(int idImport);
	
	public  DossierBachelier findByMatriculeByIdImportation(String matricule, int idImport);
	
	public void flushAndClear();
	
	public Map<String, Object> generateStudentsFiles(String anneeAcademique, String ancienCodeEtablissement
    		, String nouveauCodeEtablissement, Integer idEtab);
}

