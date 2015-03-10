package dz.gov.mesrs.sii.referentiel.ws.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEvenementDto;

/**
 * @WebService : Interface web service pour la gestion des ev�nnements
 * 
 * @author Kheireddine OMRANI
 *
 */
@WebService
public interface EvenementWS {
	
	/**
	 * Permet de rechercher par texte int�grales des �v�nements
	 */
	@WebMethod
	public List<RefEvenementDto> findGeneric(@WebParam(name="value")String value) throws Exception  ;

	/**
	 * Permet de rechercher des �v�nements qui ressemblent � l'objet �v�nement dto pass� en param�tre. 
	 */
	@WebMethod
	public List<RefEvenementDto> findAdvanced(@WebParam(name="refEvenementDto")RefEvenementDto refEvenementDto) throws Exception  ;
	
}
