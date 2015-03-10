package dz.gov.mesrs.sii.grh.business.service.conges;


import java.text.ParseException;
import java.util.List;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.model.grh.PrevisionConge;
import dz.gov.mesrs.sii.grh.business.exception.PrevisionCongeChevauchementException;
import dz.gov.mesrs.sii.grh.business.exception.PrevisionCongeDepassementException;
import dz.gov.mesrs.sii.grh.business.exception.PrevisionCongeHorsPeriodeException;
import dz.gov.mesrs.sii.grh.business.model.dto.AnneeGrhDto;
import dz.gov.mesrs.sii.grh.business.model.dto.PrevisionCongeDto;



/**
 * 
 * @author BELDI Jamel
 *
 */
 
public interface  PrevisionCongeService extends CommonService<PrevisionConge,PrevisionCongeDto,Long>{
	
	/**
	  * Permet de chercher Les prevsions de congé d'un employé pour une année 
	  * @param dossierEmployeId
	  * @param anneGrhDto
	  * @return List<PrevisionCongeDto>
	 * @throws ParseException 
	  */
	 List<PrevisionCongeDto> findPrevisionConges(Long dossierEmployeId, AnneeGrhDto anneGrhDto) ;
	 
	
/**
 * Permet d'enregistrer  les prévisions de congé d'un employé pour une anneeGrh
 * @param dtosToSave
 * @param dtosToDelete
 * @param anneeGrh
 * @param droitConge
 * @throws PrevisionCongeChevauchementException 
 * @throws PrevisionCongeDepassementException 
 * @throws PrevisionCongeHorsPeriodeException 
 */
	void saveEmployePrevisionConges(List<PrevisionCongeDto> dtosToSave,
			List<PrevisionCongeDto> dtosToDelete, AnneeGrhDto anneeGrh,
			Double droitConge) throws PrevisionCongeChevauchementException, PrevisionCongeDepassementException, PrevisionCongeHorsPeriodeException;

}