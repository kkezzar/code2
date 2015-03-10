/**
 * [dz.gov.mesrs.sii.referentiel.ws.service.Nomenclature.java] 
 * @author BELDI Jamel on : 23 janv. 2014  17:47:12
 */
package dz.gov.mesrs.sii.referentiel.ws.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

/**
 * @author BELDI Jamel  on : 23 janv. 2014  17:47:12
 */
@WebService
public interface NomenclatureWS {
	
	@WebMethod
	public List<NomenclatureDto> findByName(@WebParam(name="ncName")String ncName) throws Exception  ;
	
	@WebMethod
	public List<NomenclatureDto> findByReference(@WebParam(name="ncName")String ncName, @WebParam(name="idReference") Integer idReference ) throws Exception  ;
	
	@WebMethod
	public List<NomenclatureDto> findByDomaine(@WebParam(name="ncDomaine")String ncDomaine) throws Exception  ;
	
	@WebMethod
	public NomenclatureDto findByIdNomenclature(@WebParam(name="id")int id) throws Exception  ;
	
	@WebMethod	
	public List<NomenclatureDto> findByNcNameLikeLibellefr(@WebParam(name="ncName")String ncName, @WebParam(name="value") String value )throws Exception  ;

	@WebMethod
	public List<NomenclatureDto> findNcCompositeList(@WebParam(name="id")int id, @WebParam(name="ncTarget")String ncTarget) throws Exception  ;
	
	@WebMethod
	public List<NomenclatureDto> findNcCompositeListByCode(@WebParam(name="ncName")String ncName,@WebParam(name="code")String code, @WebParam(name="ncTarget")String ncTarget) throws Exception  ;

	@WebMethod
	public NomenclatureDto findByCode(@WebParam(name="ncName")String ncName, @WebParam(name="code") String code) throws Exception  ;
	
	@WebMethod
	public NomenclatureDto findReference(@WebParam(name="ncCode")String ncCode) throws Exception  ;
	
}

