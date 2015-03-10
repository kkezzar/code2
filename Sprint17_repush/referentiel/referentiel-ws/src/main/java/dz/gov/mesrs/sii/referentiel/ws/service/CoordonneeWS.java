package dz.gov.mesrs.sii.referentiel.ws.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAdresseDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAdresseElectroniqueDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCoordonneeDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefTelephoneDto;

/**
 * @author BELDI Jamel on : 27 mai 2014 15:38:45
 */
@WebService
public interface CoordonneeWS {

	@WebMethod
	public List<RefCoordonneeDto> findCoordonneesByIdEntity(
			@WebParam(name = "entityName") String entityName,
			@WebParam(name = "entityId") Integer entityId) throws Exception;

	@WebMethod
	public List<RefAdresseDto> findAdressesByListCoordonnee(
			@WebParam(name = "refCoordonneeList") List<RefCoordonneeDto> refCoordonneeList)
			throws Exception;

	@WebMethod
	public List<RefAdresseElectroniqueDto> findAdresseElectroniquesByListCoordonnee(
			@WebParam(name = "refCoordonneeList") List<RefCoordonneeDto> refCoordonneeList)
			throws Exception;

	@WebMethod
	public List<RefTelephoneDto> findTelephonesByListCoordonnee(
			@WebParam(name = "refCoordonneeList") List<RefCoordonneeDto> refCoordonneeList)
			throws Exception;

	@WebMethod
	public RefAdresseDto saveAdresse(
			@WebParam(name = "refAdresseDto") RefAdresseDto refAdresseDto)
			throws Exception;

	@WebMethod
	public void deleteAdresse(
			@WebParam(name = "refAdresseDto") RefAdresseDto refAdresseDto)
			throws Exception;

	@WebMethod
	public RefAdresseElectroniqueDto saveAdresseElectronique(
			@WebParam(name = "refAdresseElectroniqueDto") RefAdresseElectroniqueDto refAdresseElectroniqueDto)
			throws Exception;

	@WebMethod
	public void deleteAdresseElectronique(
			@WebParam(name = "refAdresseElectroniqueDto") RefAdresseElectroniqueDto refAdresseElectroniqueDto)
			throws Exception;

	@WebMethod
	public RefTelephoneDto saveTelephone(
			@WebParam(name = "refTelephoneDto") RefTelephoneDto refTelephoneDto)
			throws Exception;

	@WebMethod
	public void deleteTelephone(
			@WebParam(name = "refTelephoneDto") RefTelephoneDto refTelephoneDto)
			throws Exception;

	@WebMethod
	public RefAdresseDto findPrincipalAdresseForIndividu(
			@WebParam(name = "typeAdresse") String typeAdresse,
			@WebParam(name = "idIndividu") Integer idIndividu) throws Exception;
	
	@WebMethod
	public RefTelephoneDto findPrincipalTelephoneForIndividu(
			@WebParam(name = "typeTel") String typeTel,
			@WebParam(name = "natureTel") String natureTel,
			@WebParam(name = "idIndividu") Integer idIndividu) throws Exception;
	
	@WebMethod
	public RefAdresseElectroniqueDto findPrincipalAdresseElectroniqueForIndividu(
			@WebParam(name = "typeAdrElectro") String typeAdrElectro,
			@WebParam(name = "natureAdrElectro") String natureAdrElectro,
			@WebParam(name = "idIndividu") Integer idIndividu) throws Exception;
	
}
