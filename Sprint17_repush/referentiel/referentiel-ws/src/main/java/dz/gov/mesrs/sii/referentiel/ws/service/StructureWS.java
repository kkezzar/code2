/**
 * [dz.gov.mesrs.sii.referentiel.ws.service.StructureWS.java] 
 * @author rlaib on : 12 mars 2014  10:35:06
 */
package dz.gov.mesrs.sii.referentiel.ws.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

/**
 * @author rlaib  on : 12 mars 2014  10:35:06
 */
@WebService
public interface StructureWS {
	
	@WebMethod
	public List<RefStructureDto> findGeneric(@WebParam(name="value")String value) throws Exception  ;

	@WebMethod
	public List<RefStructureDto> findAdvanced(@WebParam(name="refStructureDto")RefStructureDto refStructureDto) throws Exception  ;
	
	
}
