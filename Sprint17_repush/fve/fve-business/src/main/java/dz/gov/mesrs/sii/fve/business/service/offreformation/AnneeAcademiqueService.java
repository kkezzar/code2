/**
 * [dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademique.java] 
 * @author BELDI Jamel on : 12 mai 2014  15:29:16
 */
package dz.gov.mesrs.sii.fve.business.service.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;

/**
 * @author BELDI Jamel  on : 12 mai 2014  15:29:16
 */
public interface AnneeAcademiqueService {
	
   public  AnneeAcademiqueDto insertOrUpdate( AnneeAcademiqueDto anneeAcademiqueDto);
   
	public  void remove( AnneeAcademiqueDto anneeAcademiqueDto);	
	
	public  AnneeAcademiqueDto findById(int id);
	
	public  AnneeAcademiqueDto findByFirstAndSecondYear(int premiereAnnee, int deuxiemeAnnee);
	
	public  List<AnneeAcademiqueDto> findAll() ;
	
	public AnneeAcademiqueDto findCurrentAnneeAcademique() ;
	
}
