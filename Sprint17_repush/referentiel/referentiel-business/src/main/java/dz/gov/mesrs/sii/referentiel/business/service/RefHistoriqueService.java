package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefHistoriqueDto;
 
public interface  RefHistoriqueService {

	public  RefHistoriqueDto insertOrUpdate( RefHistoriqueDto refHistoriqueDto);
	
	public  RefHistoriqueDto save( RefHistoriqueDto refHistoriqueDto);
	
	public  void remove( RefHistoriqueDto refHistoriqueDto);	
	
	public  RefHistoriqueDto findById(int id);
	
	public  List<RefHistoriqueDto> findAll() ;	
	
	public  List<RefHistoriqueDto> findAdvanced(RefHistoriqueDto refHistoriqueDto) ;
	
	public  List<RefHistoriqueDto> findByPeriode(RefHistoriqueDto refHistoriqueDto, Date dateDebut, Date dateFin) ;

}