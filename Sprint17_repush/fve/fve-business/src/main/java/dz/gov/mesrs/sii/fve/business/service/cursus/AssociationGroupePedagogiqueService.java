/**
 * [dz.gov.mesrs.sii.fve.business.service.cursus.AssociationGroupePedagogiqueService.java] 
 * @author MAKERRI Sofiane on : 2 oct. 2014  09:55:55
 */
package dz.gov.mesrs.sii.fve.business.service.cursus;


import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.AssociationGroupePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.GroupePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.RattachementMcDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.UniteEnseignementDto;



/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */
 
 
public interface  AssociationGroupePedagogiqueService {

	public  AssociationGroupePedagogiqueDto insertOrUpdate( AssociationGroupePedagogiqueDto associationGroupePedagogiqueDto);
	
	public  void remove( AssociationGroupePedagogiqueDto associationGroupePedagogiqueDto);	
	
	public  AssociationGroupePedagogiqueDto findById(int id);
	
	public  AssociationGroupePedagogiqueDto findUnique(Integer gpId, Integer rattachementMc, Integer apId);
	
	public  List<AssociationGroupePedagogiqueDto> findByGroupePedagogiqueId(Integer gpId);
	
	public  List<AssociationGroupePedagogiqueDto> findByRattachementMcAndAp(Integer rattachementMcId, Integer apId);
	
	public  List<AssociationGroupePedagogiqueDto> findAdvanced(Integer rattachementMcId, Integer apId, Integer oofId, Integer periodeId);
	
	public  List<GroupePedagogiqueDto> findByRattachementMcId(Integer oofId, Integer periodeId, Integer rattachementMcId);
	
	public  List<AssociationGroupePedagogiqueDto> findAll() ;
	
	public boolean associationExist(Integer id, Integer gpId, Integer rattachementMcId, Integer apId);
	
	public boolean groupeNameExist(Integer oofId, Integer periodeId, String nom, AssociationGroupePedagogiqueDto associationGroupePedagogiqueDto);
	
	public List<UniteEnseignementDto> findUeByGroupePedagogiqueId(Integer gpId);
	
	public List<RattachementMcDto> findRattachementMcByGroupePedagogiqueId(Integer gpId);
	
}
