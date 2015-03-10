/**
 * [dz.gov.mesrs.sii.referentiel.ws.service.PermissionWS.java] 
 * @author MAKERRI Sofiane on : 29 juin 2014  14:51:32
 */
package dz.gov.mesrs.sii.referentiel.ws.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFonctionDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPermissionDto;

/**
 * @author MAKERRI Sofiane  on : 29 juin 2014  14:51:32
 */
@WebService
public interface PermissionWS {
	@WebMethod
	public List<RefPermissionDto> findPermissionByIdRole(
			@WebParam(name = "domaine") String domaine,
			@WebParam(name = "idf") int idf)
			throws Exception;
	
	@WebMethod
	public List<RefFonctionDto> findFonctionsByDomaine(
			@WebParam(name = "domaine") String domaine)
			throws Exception;
	

}
