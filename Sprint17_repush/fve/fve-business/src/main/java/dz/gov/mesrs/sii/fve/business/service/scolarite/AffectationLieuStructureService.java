package dz.gov.mesrs.sii.fve.business.service.scolarite;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.AffectationLieuStructureDto;



/**
 * 
 * @author BELDI Jamel  on : 7 oct. 2014  17:54:04
 */
 
 
public interface  AffectationLieuStructureService {

	public  AffectationLieuStructureDto insertOrUpdate( AffectationLieuStructureDto affectationLieuStructureDto);
	
	public  void remove( AffectationLieuStructureDto affectationLieuStructureDto);	
	
	public  AffectationLieuStructureDto findById(int id);
	
	public  List<AffectationLieuStructureDto> findAll() ;	
	
	public  List<AffectationLieuStructureDto> findAdvanced(AffectationLieuStructureDto searchDto) ;	

}