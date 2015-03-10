package dz.gov.mesrs.sii.fve.business.service.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDetailContentDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationI18nDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationPartenaireDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationStructureDto;


/**
 * @author  Rafik LAIB  on : 5 avr. 2014  22:07:59
 */
public interface  OffreFormationService {

	public  OffreFormationDto insertOrUpdate(OffreFormationDto offreFormationDto, List<OffreFormationDetailContentDto> details);
	
	public  OffreFormationStructureDto insertOrUpdate(OffreFormationStructureDto offreFormationStructureDto);
	
	public  void remove(OffreFormationDto offreFormationDto);	
	
	public  void removeStructure(int idStructure);	
	
	public  OffreFormationDto findById(int id);
	
	public  List<OffreFormationDto> findGeneric(String text, Integer idEtab) ;
	
	public  List<OffreFormationDto> findAll() ;
	
	public  List<OffreFormationDto> findByIdEtablissement(int etabId) ;
	
	public  List<OffreFormationDto> findAdvanced(OffreFormationDto offreFormationDto, String text, Integer idEtab) ;

    public  List<OffreFormationPartenaireDto> findPartnersByOfId(int ofId) ;
    
    public  List<OffreFormationDto> findOffreToHabilitation(OffreFormationDto ofSearchDto) ;
    
    public boolean validateSavingOf(int ofId);
    
    public int getLastOfIndex(Integer idEtab);
    
    public  List<OffreFormationDto> findByCodeSituation(String codeSituation) ;
    
    public  OffreFormationI18nDto findI18nByOfId(int ofId, String langue);
    
    public  List<OffreFormationStructureDto> findStructuresByOfId(int ofId) ;
}