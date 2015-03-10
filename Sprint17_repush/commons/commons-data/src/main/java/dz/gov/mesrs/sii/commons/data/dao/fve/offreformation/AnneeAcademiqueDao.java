package dz.gov.mesrs.sii.commons.data.dao.fve.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;



/**
 * @author BELDI Jamel  on : 12 mai 2014  14:14:30
 */
public interface AnneeAcademiqueDao {

	public  void persist(AnneeAcademique transientInstance);

	public  void remove(AnneeAcademique persistentInstance);

	public  AnneeAcademique merge(AnneeAcademique detachedInstance);

	public  AnneeAcademique findById(int id);
	
	public  List<AnneeAcademique> findByQuery(String query) ;
	
	public  List<AnneeAcademique> findAll();
	
	public AnneeAcademique findByFirstAndSecondYear(int premiereAnnee,
			int deuxiemeAnnee);
	
	public AnneeAcademique findCurrentAnneeAcademique();

}