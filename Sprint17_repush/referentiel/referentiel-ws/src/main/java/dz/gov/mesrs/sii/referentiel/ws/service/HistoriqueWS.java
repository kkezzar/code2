/**
 * [dz.gov.mesrs.sii.referentiel.ws.service.HistoriqueWS.java] 
 * @author MAKERRI Sofiane on : 18 juin 2014  12:23:54
 */
package dz.gov.mesrs.sii.referentiel.ws.service;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEvenementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefHistoriqueDto;

/**
 * @author MAKERRI Sofiane on : 18 juin 2014 12:23:54
 */
@WebService
public interface HistoriqueWS {

	@WebMethod
	public RefHistoriqueDto saveHistorique(
			@WebParam(name = "refHistoriqueDto") RefHistoriqueDto refHistoriqueDto)
			throws Exception;

	@WebMethod
	public List<RefHistoriqueDto> findAdvancedHistorique(
			@WebParam(name = "refHistoriqueDto") RefHistoriqueDto refHistoriqueDto)
			throws Exception;

	@WebMethod
	public List<RefHistoriqueDto> findByPeriodeHistorique(
			@WebParam(name = "refHistoriqueDto") RefHistoriqueDto refHistoriqueDto,
			@WebParam(name = "dateDebut") Date dateDebut,
			@WebParam(name = "dateFin") Date dateFin) throws Exception;

	@WebMethod
	public List<RefEvenementDto> findByCodeTypeEvenement(
			@WebParam(name = "etabId") Integer etabId,
			@WebParam(name = "codeType") String codeType,
			@WebParam(name = "year") Integer year) throws Exception;

}
