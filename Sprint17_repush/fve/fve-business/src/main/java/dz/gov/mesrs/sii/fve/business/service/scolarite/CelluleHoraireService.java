package dz.gov.mesrs.sii.fve.business.service.scolarite;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.CelluleHoraireDto;



/**
 * 
 * @author BELDI Jamel  on : 7 oct. 2014  17:54:14
 */
 
 
public interface  CelluleHoraireService {

	public  CelluleHoraireDto insertOrUpdate( CelluleHoraireDto celluleHoraireDto);
	
	public  void remove( CelluleHoraireDto celluleHoraireDto);	
	
	public  CelluleHoraireDto findById(int id);
	
	public  List<CelluleHoraireDto> findAll() ;	
	
	public  CelluleHoraireDto findByJourAndPlageHoraire(int idJour, int idPlageHoraire);

}