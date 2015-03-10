package dz.gov.mesrs.sii.fve.business.service.cursus;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.AttestationFinEtudeDto;



/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */
 
 
public interface  AttestationFinEtudeService {


public AttestationFinEtudeDto insertOrUpdate(AttestationFinEtudeDto attestationFinEtudeDto);
	
public  void remove( AttestationFinEtudeDto attestationFinEtudeDto);	
	
public  AttestationFinEtudeDto findById(int id);
	
public  List<AttestationFinEtudeDto> findAll() ;
	
public  List<AttestationFinEtudeDto> findBySession(int idSession) ;
	
public  List<AttestationFinEtudeDto> findByQuery(String query) ;

public List<AttestationFinEtudeDto> findAdvanced(AttestationFinEtudeDto searchDto);

public List<AttestationFinEtudeDto> findAdvanced2(AttestationFinEtudeDto searchDto);

public AttestationFinEtudeDto findByIdDossier(int idDossier);

void delete(AttestationFinEtudeDto attestationFinEtudeDto);

public AttestationFinEtudeDto findByIdInscriptionAdministrative(int idInscriptionAdministrative);

public List<AttestationFinEtudeDto> findAttestationtoValidate(String codeEtab);

public AttestationFinEtudeDto findByIdInscriptionAdministrativeAndPeriode(int idInscriptionAdministrative, int idPeriode);


}