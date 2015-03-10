package dz.gov.mesrs.sii.fve.business.service.cursus;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.GroupePedagogiqueDto;




/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 
 
public interface  GroupePedagogiqueService {

	public  GroupePedagogiqueDto insertOrUpdate( GroupePedagogiqueDto groupePedagogiqueDto);
	
	public  void remove( GroupePedagogiqueDto groupePedagogiqueDto);	
	
	public  GroupePedagogiqueDto findById(int id);
	
	public  List<GroupePedagogiqueDto> findByQuery(String query) ;
	
	public  List<GroupePedagogiqueDto> findAll() ;	
	
	public List<GroupePedagogiqueDto> findGeneric(String value, String refCodeEtab);
	
	public List<GroupePedagogiqueDto> findByOf(Integer oofId, String refCodeEtab);
	
	public List<GroupePedagogiqueDto> findAdvanced(GroupePedagogiqueDto searchDto);
	
	public List<GroupePedagogiqueDto> findSection(Integer oofId, Integer idPeriode);
	
	public GroupePedagogiqueDto groupeNameExist(GroupePedagogiqueDto groupePedagogiqueDto);
	
	public List<GroupePedagogiqueDto> findGroupesOfSection(GroupePedagogiqueDto groupePedagogiqueDto);
	
	public List<GroupePedagogiqueDto> findGroupesOfSection(Integer idSection);
	
}