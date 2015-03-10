package dz.gov.mesrs.sii.fve.business.service.cursus;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.LigneReleveDeNotesDto;



/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 
 
public interface  LigneReleveDeNotesService {

	public  LigneReleveDeNotesDto insertOrUpdate( LigneReleveDeNotesDto ligneReleveDeNotesDto);
	
	public  void remove( LigneReleveDeNotesDto ligneReleveDeNotesDto);	
	
	public  LigneReleveDeNotesDto findById(int id);
	
	public  List<LigneReleveDeNotesDto> findAll() ;	
	
	public List<LigneReleveDeNotesDto> findLignesOfReleve(int idReleve);
	
	public void deleteAllLignesOfReleve (int idReleve);


}