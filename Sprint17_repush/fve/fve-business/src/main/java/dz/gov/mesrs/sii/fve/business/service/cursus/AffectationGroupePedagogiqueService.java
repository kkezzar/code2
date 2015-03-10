/**
 * [dz.gov.mesrs.sii.fve.business.service.cursus.AffectationGroupePedagogiqueService.java] 
 * @author MAKERRI Sofiane on : 22 juil. 2014  13:01:40
 */
package dz.gov.mesrs.sii.fve.business.service.cursus;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.AffectationGroupePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;

/**
 * @author MAKERRI Sofiane  on : 22 juil. 2014  13:01:40
 */
public interface AffectationGroupePedagogiqueService {
	
	public AffectationGroupePedagogiqueDto  insertOrUpdate(AffectationGroupePedagogiqueDto affectationGroupePedagogiqueDto);
	
	public void remove(AffectationGroupePedagogiqueDto affectationGroupePedagogiqueDto);
	
	public AffectationGroupePedagogiqueDto findById(int id);
	
	public List<AffectationGroupePedagogiqueDto> findAll();	
	
	public List<AffectationGroupePedagogiqueDto> findByGroupePedagogiqueId(Integer gpId);
	
	public List<AffectationGroupePedagogiqueDto> findOnlyBySectionId(Integer sectionId);
	
	public List<AffectationGroupePedagogiqueDto> findAffectationInGroupe(Integer diaId, Integer sectionId);
	
	public List<AffectationGroupePedagogiqueDto> findByAssociationGpId(Integer assocId);
	
	public List<DossierInscriptionAdministrativeDto> findEtudiantsOfSection(Integer idSection);
	
}



	



