package dz.gov.mesrs.sii.fve.ws.service.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.fve.ws.bo.offreformation.OffreFormation;

 
public interface  OffreFormationService {
	
	public  OffreFormation findByCode(String code);
	
	public  List<OffreFormation> findAll() ;	

}