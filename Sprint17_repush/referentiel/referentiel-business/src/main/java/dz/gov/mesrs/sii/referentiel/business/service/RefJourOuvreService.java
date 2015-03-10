package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefJourOuvreDto;



/**
 * 
 * @author BELDI Jamel
 *
 */
 
 
public interface  RefJourOuvreService {

	  RefJourOuvreDto insertOrUpdate( RefJourOuvreDto refJourOuvreDto);
	
	  void remove( RefJourOuvreDto refJourOuvreDto);	
	
	  RefJourOuvreDto findById(int id);
	
	  List<RefJourOuvreDto> findAll() ;	
	
	 boolean isJourOuvre(Date date);
	 
	 int calculateNbJourOuvreBetweenTwoDate(Date dateDebut, Date dateFin);

	List<RefJourOuvreDto> findBetweenTwoDate(Date dateDebut, Date dateFin);
	
	 RefJourOuvreDto findByDate(Date date);
	 
	 Date nextWorkingDay(Date dateDebut, double nbJour);
	 
	 int getNbWeekendDay(Date dateDebut, Date dateFin);

	boolean isWeekendDay(Date date);
	
	int calculateWorkingDaysBetweenTwoDate(Date dateDebut, Date dateFin);
	 
	 
	
	
	
	

}