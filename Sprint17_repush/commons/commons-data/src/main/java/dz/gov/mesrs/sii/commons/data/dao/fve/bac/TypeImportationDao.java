/**
 * [dz.gov.mesrs.sii.fve.business.persistence.bac.DossierBachelierDao.java] 
 * @author  Rafik LAIB on : 21 mai 2014  14:10:13
 */
package dz.gov.mesrs.sii.commons.data.dao.fve.bac;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.bac.TypeImportation;

/**
 * @author  Rafik LAIB  on : 21 mai 2014  14:10:13
 */
public interface TypeImportationDao {


	public  TypeImportation findById(int id);
	
	public  TypeImportation findByCode(String  code);
	
	public  List<TypeImportation> findAll();
}
