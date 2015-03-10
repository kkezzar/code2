package dz.gov.mesrs.sii.fve.business.service.cursus;

import java.util.List;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierTransfertDto;


 
/**
 * @author BELDI Jamel  on : 8 juin 2014  12:29:16
 */
public interface  DossierTransfertService {

	public  DossierTransfertDto insertOrUpdate( DossierTransfertDto dossierTransfertDto);
	
	public  void remove(DossierTransfertDto dossierTransfertDto);	
	
	public  DossierTransfertDto findById(int id);
	
	public  List<DossierTransfertDto> findAll() ;

	public List<DossierTransfertDto> findAdvanced(
			DossierTransfertDto dossierTransfertSearchDto);	

	/**
	 * Recherche avanncee par situation
	 * @author Mounir.MESSAOUDI on : 2 sept. 2014 10:01:50 
	 * @param searchDto
	 * @param searchSitutationEntiteDtos
	 * @return
	 */
	public List<DossierTransfertDto> findAdvanced(DossierTransfertDto searchDto, List<SituationEntiteDto> searchSitutationEntiteDtos);
	
	/**
	 * Recherche le nombre de resultats 
	 * @author Mounir.MESSAOUDI on : 10 sept. 2014 12:49:55 
	 * @param searchDto
	 * @return
	 */
	public Long findCountDossiersTransfert(DossierTransfertDto searchDto);
	
	/**
	 * Recherche avanncee par situation
	 * 
	 * @author Mounir.MESSAOUDI on : 15 sept. 2014 12:42:12 
	 * @param searchDto
	 * @param typesDossiersTransfert
	 * @param searchSitutationEntiteDtos
	 * @return
	 */
	public List<DossierTransfertDto> findAdvanced(
			DossierTransfertDto searchDto, List<String> typesDossiersTransfert,
			List<SituationEntiteDto> searchSitutationEntiteDtos);

}