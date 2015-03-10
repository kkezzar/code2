package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefTypePieceConstitutiveDto;



/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */
 
 
public interface  RefTypePieceConstitutiveService {

	public  RefTypePieceConstitutiveDto insertOrUpdate( RefTypePieceConstitutiveDto refTypePieceConstitutiveDto);
	
	public  RefTypePieceConstitutiveDto update( RefTypePieceConstitutiveDto refTypePieceConstitutiveDto);
	
	public  void remove( RefTypePieceConstitutiveDto refTypePieceConstitutiveDto);	
	
	public  RefTypePieceConstitutiveDto findById(int id);
	
	public  List<RefTypePieceConstitutiveDto> findByQuery(String query) ;
	
	public  List<RefTypePieceConstitutiveDto> findAll() ;	
	
	public  List<RefTypePieceConstitutiveDto> findByIdTypeDossierDate(Integer idTypeDossier, Date annee) ;
	
	public  List<RefTypePieceConstitutiveDto> findByIdTypeDossier(Integer idTypeDossier, boolean aFournir) ;
	
	public  List<RefTypePieceConstitutiveDto> findByIdTypeDossier(Integer idTypeDossier) ;
	
	public  List<RefTypePieceConstitutiveDto> findByCodeTypeDossierDate(String codeTypeDossier, Date annee) ;
	
	public  List<RefTypePieceConstitutiveDto> findByTypeDossierDate(String typeDossier, Date annee) ;
	
	public int findLastRang(Integer idTypeDossier, boolean aFournir);

}