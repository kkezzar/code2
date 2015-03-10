/**
 * EtapeRoleService
 */
package dz.gov.mesrs.sii.commons.business.service.bpm;

import java.util.List;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeRoleDto;


/**
 * @author BELDI Jamel  on : 5 mai 2014  12:49:16
 */
public interface EtapeRoleService {

	public  EtapeRoleDto insertOrUpdate( EtapeRoleDto etapeRoleDto);
	
	public  void remove( EtapeRoleDto etapeRoleDto);	
	
	public  EtapeRoleDto findById(int id);
		
	public  List<EtapeRoleDto> findAll() ;	

	public  EtapeRoleDto findByCodeEtapeAndCodeRole(String etapeCode, String roleCode);
}
