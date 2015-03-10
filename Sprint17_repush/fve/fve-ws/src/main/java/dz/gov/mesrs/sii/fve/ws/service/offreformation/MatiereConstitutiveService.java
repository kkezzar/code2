package dz.gov.mesrs.sii.fve.ws.service.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.fve.ws.bo.offreformation.MatiereConstitutive;

public interface  MatiereConstitutiveService {
	
	public  List<MatiereConstitutive> findByQuery(String query) ;
	
	public  List<MatiereConstitutive> findAll() ;	


}