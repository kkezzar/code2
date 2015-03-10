package dz.gov.mesrs.sii.grh.business.service.conges;

import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.model.grh.CongeEmploye;
import dz.gov.mesrs.sii.grh.business.exception.CongeDateDebutException;
import dz.gov.mesrs.sii.grh.business.exception.CongeDateDemandeException;
import dz.gov.mesrs.sii.grh.business.exception.CongeDateRepriseException;
import dz.gov.mesrs.sii.grh.business.exception.CongeNbJourException;
import dz.gov.mesrs.sii.grh.business.model.dto.AnneeGrhDto;
import dz.gov.mesrs.sii.grh.business.model.dto.CongeEmployeDto;

/**
 * 
 * @author BELDI Jamel
 * 
 */

public interface CongeEmployeService extends
		CommonService<CongeEmploye, CongeEmployeDto, Long> {
	public double calculateDroitConge(AnneeGrhDto anneeGrhDto, Long employeId, Date dateRecrutement);
	public double calculateSoldeConge(AnneeGrhDto anneeGrhDto, Long employeId, Date dateRecrutement);
	public double calulateCongeTakenInPeriod(AnneeGrhDto anneeGrhDto, Long employeId);
	boolean isEmployeConges(Long employeId, Date dateDebut, Date dateFin);
	public List<CongeEmployeDto> getListEmployeCongeTakenInPeriod(AnneeGrhDto anneeGrhDto, Long employeId);
	public CongeEmployeDto saveDemande(CongeEmployeDto congeEmployeDto, AnneeGrhDto anneeGrhDto) throws CongeDateDemandeException, CongeDateDebutException, CongeNbJourException;
	public CongeEmployeDto saveResultatConge(CongeEmployeDto congeEmployeDto) throws CongeDateDebutException, CongeNbJourException;
	public void saveAnnulationConge(CongeEmployeDto congeEmployeDto);
	public CongeEmployeDto saveRepriseConge(CongeEmployeDto congeEmployeDto) throws CongeDateRepriseException;
	public List<CongeEmployeDto> findAllDemandesConges(int etablissmentId, SearchMode searchMode, String searchKeyWords);
	public int countAllDemandesConges(int etablissmentId, SearchMode searchMode, String searchKeyWords);
	public List<CongeEmployeDto> findAllCongesAcceptes(int etablissmentId, SearchMode searchMode, String searchKeyWords);
	public int countAllCongesAcceptes(int etablissmentId, SearchMode searchMode, String searchKeyWords);
	public List<CongeEmployeDto> findAllDemandesOrResultsConges(int etablissmentId, SearchMode searchMode, String searchKeyWords);
	public int countAllDemandesOrResultsConges(int etablissmentId, SearchMode searchMode, String searchKeyWords);

}