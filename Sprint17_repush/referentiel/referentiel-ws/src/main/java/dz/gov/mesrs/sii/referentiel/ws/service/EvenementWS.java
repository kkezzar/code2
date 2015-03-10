package dz.gov.mesrs.sii.referentiel.ws.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEvenementDto;

/**
 * @WebService : Interface web service pour la gestion des evènnements
 * 
 * @author Kheireddine OMRANI
 *
 */
@WebService
public interface EvenementWS {
	
	/**
	 * Permet de rechercher par texte intégrales des évènements
	 */
	@WebMethod
	public List<RefEvenementDto> findGeneric(@WebParam(name="value")String value) throws Exception  ;

	/**
	 * Permet de rechercher des évènements qui ressemblent à l'objet évènement dto passè en paramètre. 
	 */
	@WebMethod
	public List<RefEvenementDto> findAdvanced(@WebParam(name="refEvenementDto")RefEvenementDto refEvenementDto) throws Exception  ;
	
}
