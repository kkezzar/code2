/**
 * [dz.gov.mesrs.sii.fve.business.service.bac.BacService.java] 
 * @author  BELBRIK Oualid on : 21 octobre 2014  15:11:07
 */
package dz.gov.mesrs.sii.fve.business.service.cursus;

import java.util.List;
import java.util.Map;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.GenerationDiplomeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanSessionDto;

/**
 * @author  Oualid BELBRIK  on : 21 octobre 2014  15:11:07
 */
public interface GenerationDiplomeService {

	public GenerationDiplomeDto saveGenerationDiplome(GenerationDiplomeDto generationDiplomeDto);
	
	public List<GenerationDiplomeDto> getAllGenerations();
	
	public List<GenerationDiplomeDto> findGenerationsByEtab(String codeEtab);
	
	public  Map<String, Object>  generateDiplomesFiles(List<BilanSessionDto> inputList,int idAnneeAcademique, String codeEtablissement);
        

}

