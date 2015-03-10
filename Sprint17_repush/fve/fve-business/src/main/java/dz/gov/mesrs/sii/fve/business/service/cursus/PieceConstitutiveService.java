package dz.gov.mesrs.sii.fve.business.service.cursus;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.PieceConstitutiveDto;



/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 
 
public interface  PieceConstitutiveService {

	public  PieceConstitutiveDto insertOrUpdate( PieceConstitutiveDto pieceConstitutiveDto);
	
	public  void remove( PieceConstitutiveDto pieceConstitutiveDto);	
	
	public  PieceConstitutiveDto findById(int id);
	
	public  List<PieceConstitutiveDto> findByQuery(String query) ;
	
	public  List<PieceConstitutiveDto> findAll() ;	
	
	public  List<PieceConstitutiveDto> findByIdDossier(Integer id);
	
	public  PieceConstitutiveDto findByIdDossierAndCodePiece(Integer id, Integer pieceId);
	
	public  boolean isAllValid(Integer dossierId);
	
	public  boolean isInRequiredValid(Integer pieceId, Integer dossierId);
	
	public  boolean isPieceEntreeValid(Integer dossierId);


}