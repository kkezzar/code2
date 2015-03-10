package dz.gov.mesrs.sii.fve.ws.service.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.fve.ws.bo.offreformation.AtomePedagogique;

public interface  AtomePedagogiqueService {

	public  List<AtomePedagogique> findByQuery(String query) ;
	
	public  List<AtomePedagogique> findAll() ;	


}