package dz.gov.mesrs.sii.referentiel.ws.service;

import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCompteDto;

@WebService
public interface AuthentificationWS {
	@WebMethod
	boolean verifierUserLdap(@WebParam(name = "login") String login,
			@WebParam(name = "password") String password) throws Exception;

	@WebMethod
	public RefCompteDto findByLogin(
			@WebParam(name = "nomUtilisateur") String nomUtilisateur)
			throws Exception;

	@WebMethod
	public boolean verifyUserIp(@WebParam(name = "userAddr") String userAddr,
			@WebParam(name = "adresseDebut") String adresseDebut,
			@WebParam(name = "adresseFin") String adresseFin) throws Exception;

	@WebMethod
	public boolean verifyHoraireAcces(
			@WebParam(name = "heureAcces") Date heureAcces,
			@WebParam(name = "heureDebut") Date heureDebut,
			@WebParam(name = "heureFin") Date heureFin) throws Exception;

	@WebMethod
	public RefCompteDto insertOrUpdateCompte(
			@WebParam(name = "refCompteDto") RefCompteDto refCompteDto)
			throws Exception;
	
	@WebMethod
	public RefCompteDto findCompteByIndividu(
			@WebParam(name = "individuId") Integer individuId)
			throws Exception;

}
