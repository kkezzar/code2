/**
 * [dz.gov.mesrs.sii.fve.business.persistence.bac.DossierBachelierDao.java] 
 * @author  Rafik LAIB on : 21 mai 2014  14:10:13
 */
package dz.gov.mesrs.sii.commons.data.dao.fve.bac;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.bac.AffectationBachelier;

/**
 * @author  Rafik LAIB  on : 21 mai 2014  14:10:13
 */
public interface AffectationBachelierDao {

	public  void persist(AffectationBachelier transientInstance);

	public  void remove(AffectationBachelier persistentInstance);

	public  AffectationBachelier merge(AffectationBachelier detachedInstance);

	public  AffectationBachelier findById(int id);
	
	public  AffectationBachelier findByMatriculeByIdImport(String matricule, int idImport);
	
	public List<AffectationBachelier> findByImportId(int idImport);
	
	public List<AffectationBachelier> findByYearAndEtablissement(String year,  String codeEtablissement);
	
	public List<AffectationBachelier> findByIdImportByEtablissement(int idImport,  String codeEtablissement);
}

