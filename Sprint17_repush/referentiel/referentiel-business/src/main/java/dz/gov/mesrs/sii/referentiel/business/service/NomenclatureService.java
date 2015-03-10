package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 
 
public interface  NomenclatureService {

	public  NomenclatureDto insertOrUpdate( NomenclatureDto ncValuesDto);
	
	public  void remove( NomenclatureDto ncValuesDto);	
	
	public  NomenclatureDto findById(int id);
	
	public  List<NomenclatureDto> findByQuery(String query) ;
	
	public  List<NomenclatureDto> findAll() ;
	
	public  List<NomenclatureDto> findByName(String name) ;
	
	public  List<NomenclatureDto> findGeneric(String name, String value);
	
	public  List<NomenclatureDto> findByName(String name, String orderby) ;
	
	public boolean existInNc(String ncName);
	
	public  List<NomenclatureDto> findByIdNc(int id) ;
	
	public  boolean existIdNc(int id) ;
	
	public  NomenclatureDto findByCode(int idNcName, String code);
	
	public  NomenclatureDto findByCode(String ncName, String code);
	
	public  NomenclatureDto findByLlLatin(int idNcName, String llLatin);
	
	public  NomenclatureDto findByLlArabe(int idNcName, String llArabe);
	
    public NomenclatureDto save(NomenclatureDto nomenclatureDto);
	
	public void update( NomenclatureDto nomenclatureDto);
	
	public List<NomenclatureDto> findNcReference(String name);
	
	public List<NomenclatureDto> findNcCompositeList(String ncName, String code, String ncTarget);
	
	public List<NomenclatureDto> findNcCompositeList(int id, String ncTarget);
	
	public List<NomenclatureDto> findByDomaine(String ncDomaine);
	
	public List<NomenclatureDto> findByReference(String ncName,  Integer idReference );
	
	public List<NomenclatureDto> findByNcNameLikeLibellefr(String ncName, String value );
	
	public List<NomenclatureDto>  findTypePiece(Integer idTypeDossier);
	
	public NomenclatureDto findReference(String ncCode);
	
}
