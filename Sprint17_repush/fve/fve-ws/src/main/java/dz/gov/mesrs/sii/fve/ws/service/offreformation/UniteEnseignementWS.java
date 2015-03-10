package dz.gov.mesrs.sii.fve.ws.service.offreformation;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import dz.gov.mesrs.sii.fve.ws.bo.offreformation.UniteEnseignement;

@WebService(name="UniteEnseignementWS")
public interface  UniteEnseignementWS {

	@WebMethod
	public  UniteEnseignement findByCode(@WebParam(name="code") String code) ;

	@WebMethod
	public  List<UniteEnseignement> findAll() ;	

}