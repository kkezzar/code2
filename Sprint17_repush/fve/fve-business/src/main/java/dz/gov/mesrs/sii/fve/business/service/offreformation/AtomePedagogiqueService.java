package dz.gov.mesrs.sii.fve.business.service.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AtomePedagogiqueDto;

/**
 * 
 * @author Kheireddine OMRANI
 *
 */
public interface  AtomePedagogiqueService {

	public  AtomePedagogiqueDto insertOrUpdate( AtomePedagogiqueDto atomePedagogiqueDto);
	
	public  void remove( AtomePedagogiqueDto atomePedagogiqueDto);	
	
	public  AtomePedagogiqueDto findById(int id);

	public  List<AtomePedagogiqueDto> findByQuery(String query) ;
		
	public  List<AtomePedagogiqueDto> findAll() ;	

	public  List<AtomePedagogiqueDto> find(String code, Integer idMatiereConstitutive, String libelleFr, String libelleAr) ;

	public  List<AtomePedagogiqueDto> findAPOfMC(int idMatiereConstitutive) ;
	
	public  List<AtomePedagogiqueDto> findByRattachementMC(int idRattachementMc) ;
	
	public  AtomePedagogiqueDto findByTypeAndRattachementMC(String codeType , int idRattachementMc) ;
	
	public List<AtomePedagogiqueDto> findApByGroupeId(Integer sectionId);
	
	public  AtomePedagogiqueDto findUniqueByType(int idMc, int ncTypeId);
	
	public  AtomePedagogiqueDto findUniqueByCode(String codeAp, int idMc);

}