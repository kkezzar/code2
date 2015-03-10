/**
 * [dz.gov.mesrs.sii.fve.business.service.bac.BacService.java] 
 * @author  Rafik LAIB on : 21 mai 2014  15:11:07
 */
package dz.gov.mesrs.sii.fve.business.service.bac;

import java.util.List;
import java.util.Map;

import dz.gov.mesrs.sii.fve.business.model.dto.bac.AffectationBachelierDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.DossierBachelierDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.GenerationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.ImportationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.NotesBachelierDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.TypeImportationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.PieceConstitutiveDto;

/**
 * @author  Rafik LAIB  on : 21 mai 2014  15:11:07
 */
public interface BacService {

	public  DossierBachelierDto saveOneBachelorFile(DossierBachelierDto dossierBachelierDto);
	
	public  ImportationDto saveListBachelorsFiles(ImportationDto importationDto,   List<DossierBachelierDto> dossierBachelierDtos, Boolean replaceLastImport);
	
	public  ImportationDto getImportById(int idImport);
	
	public  DossierBachelierDto findBachelorFileById(int idBachelorFile);
	
	public List<TypeImportationDto> getAllTypesImport();
	
	public List<ImportationDto> getAllImports();
	
	public TypeImportationDto getImportTypeByCode(String code);
	
	public  List<DossierBachelierDto> findAdvanced(DossierBachelierDto dossierBachelierDto, String keyWord,
			boolean withStudentFile,boolean hasAffectationCorrespondanceCode,  String year
			, Integer start, Integer pageSize);
	
	public  List<DossierBachelierDto> findAdvancedWithPaging(DossierBachelierDto dossierBachelierDto, String keyWord, Boolean withStudentFile, String year, int start, int page);
	
	public  Integer getFindAdvancedCount(DossierBachelierDto searchDto, String keyWord,
			boolean withStudentFile,boolean hasAffectationCorrespondanceCode,  String year);
	
	public  List<DossierBachelierDto> findDataToGenerate(DossierBachelierDto dossierBachelierDto, String keyWord, Boolean withStudentFile, String year , int start, int page);

	public List<ImportationDto> getImportByCodeTypeByYear(String typeCode, String year);
	
	public ImportationDto saveListBachelorsAffectations(ImportationDto bachelorFilesImportation, ImportationDto importationDto,  List<AffectationBachelierDto> affectationsBachelierDtos, Boolean replaceLastImport);
	
	public  List<DossierBachelierDto> getListBachelorsFilesByImportId(int idImport);
	
	public  List<AffectationBachelierDto> getListBachelorsAffectationsByImportId(int idImport);
	
	public  List<DossierBachelierDto> findListBachelorsAndAffectationsByYearAndEtablissement(String year, String codeEtablissement);

	public List<String> getAllYears();

	public List<AffectationBachelierDto> findAffectationsByIdImportByEtablissement(int idImport,  String codeEtablissement);

	public ImportationDto saveImport(ImportationDto importationDto);
	
	public GenerationDto saveGeneration(GenerationDto generationDto);
	
	public List<GenerationDto> getAllGenerations();
	
	public List<GenerationDto> findGenerationsByEtab(String codeEtab);
        
    public List<NotesBachelierDto> findByIdDossierBachelier(String matriculeBachelier);
        
    public List<NotesBachelierDto> findByIdDossierBachelierRefCodeMatiere(String matriculeBachelier, String refCodeMatiere);

    public  Map<String, Object>  generateBach(String anneeAcademique, String ancienCodeEtablissement
    		, String nouveauCodeEtablissement, Integer idEtab);
    
}

