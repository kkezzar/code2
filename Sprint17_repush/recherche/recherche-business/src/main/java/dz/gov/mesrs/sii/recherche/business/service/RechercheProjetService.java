/**
 * [dz.gov.mesrs.sii.recherche.business.service.RecherchePartenaireService.java] 
 * @author sRamdane  on : 08 fev 2015   17:33:39
 */
package dz.gov.mesrs.sii.recherche.business.service;

import java.util.List;

import dz.gov.mesrs.sii.recherche.business.model.dto.ProjetDto;

/**
 * @author sRamdane  on : 08 fev 2015  17:33:39
 */
public interface RechercheProjetService {
	
	

	List<ProjetDto> findByKeyWords(String keyWord);

	

	ProjetDto saveProjet(ProjetDto projetDto, String annee, String structure);

	List<ProjetDto> findByCodeProjet(String codeProjet, Integer idSituation);

	
	
	
	
	
	
	
	
	
}
