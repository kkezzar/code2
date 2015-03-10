package dz.gov.mesrs.sii.referentiel.ws.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefParametreEtabDto;

/**
 * * @WebService : Interface web service pour la gestion des parametres de
 * configuration par etablissement
 * 
 * @author Mounir.MESSAOUDI on : 10 sept. 2014 17:40:09
 */
@WebService
public interface ParametreEtabWS {

	@WebMethod
	public RefParametreEtabDto findByKeyEtab(
			@WebParam(name = "idfEtab") String idfEtab,
			@WebParam(name = "key") String key) throws Exception;

}
