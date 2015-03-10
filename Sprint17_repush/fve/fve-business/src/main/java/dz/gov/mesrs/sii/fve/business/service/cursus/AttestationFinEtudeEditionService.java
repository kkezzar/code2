package dz.gov.mesrs.sii.fve.business.service.cursus;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.AttestationFinEtudeEditionDto;



/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */
 
 
public interface  AttestationFinEtudeEditionService {

	public  AttestationFinEtudeEditionDto insertOrUpdate( AttestationFinEtudeEditionDto attestationFinEtudeEditionDto);
	
	public  void remove( AttestationFinEtudeEditionDto attestationFinEtudeEditionDto);	
	
	public  AttestationFinEtudeEditionDto findById(int id);
	
	public  List<AttestationFinEtudeEditionDto> findAll() ;	


	void delete(AttestationFinEtudeEditionDto attestationFinEtudeEditionDto);

	
	public  List<AttestationFinEtudeEditionDto> findEditionByIdAttestation(int idAttestationFinEtude) ;

	List<AttestationFinEtudeEditionDto> findAdvanced(AttestationFinEtudeEditionDto dto);



	

}