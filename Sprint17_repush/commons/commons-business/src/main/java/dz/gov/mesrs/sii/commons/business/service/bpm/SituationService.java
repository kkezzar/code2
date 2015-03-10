/**
 * [dz.gov.mesrs.sii.commons.business.service.habilitation.TacheService.java] 
 * @author rlaib on : 29 avr. 2014  16:33:43
 */
package dz.gov.mesrs.sii.commons.business.service.bpm;

import java.util.List;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;

/**
 * @author rlaib  on : 29 avr. 2014  16:33:43
 */
public interface SituationService {

	
	public  SituationEntiteDto findBySituationEntiteByCodeSituation(String codeEntite, String codeSituation);
	
	public  SituationEntiteOccurrenceDto saveSituationOccurrence(SituationEntiteOccurrenceDto situationEntiteOccurrenceDto);

	public  List<SituationEntiteOccurrenceDto> getEntityOccurrenceHistory(String entiteCode, Integer entiteOccurrenceId);
	
	public  List<SituationEntiteDto> findSituationsByEntiteCode(String codeEntite);
	
	public void saveSituationOccurrence(SituationEntiteDto situationEntiteDto , Integer idEntite);
}
