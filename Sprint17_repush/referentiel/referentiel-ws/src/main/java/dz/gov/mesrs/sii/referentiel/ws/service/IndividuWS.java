/**
 * [dz.gov.mesrs.sii.referentiel.ws.service.IndividuWS.java] 
 * @author rlaib on : 11 mars 2014  10:48:36
 */
package dz.gov.mesrs.sii.referentiel.ws.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;

/**
 * @author rlaib  on : 11 mars 2014  10:48:36
 */
@WebService
public interface IndividuWS {
	
	@WebMethod
	public List<RefIndividuDto> findGeneric(@WebParam(name="value")String value) throws Exception  ;

	@WebMethod
	public List<RefIndividuDto> findAdvanced(@WebParam(name="refIndividuDto")RefIndividuDto refIndividuDto) throws Exception  ;
	
}
