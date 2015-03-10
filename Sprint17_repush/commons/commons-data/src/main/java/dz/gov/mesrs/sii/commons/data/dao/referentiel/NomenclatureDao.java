package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface NomenclatureDao {

	public  void persist(Nomenclature transientInstance);

	public  void remove(Nomenclature persistentInstance);

	public  Nomenclature merge(Nomenclature detachedInstance);

	public  Nomenclature findById(int id);
	
	public  List<Nomenclature> findByQuery(String query) ;
	
	public  List<Nomenclature> findAll();
	
	public  List<Nomenclature> findByName(String name);
	
	public  List<Nomenclature> findByNameAndValue(String name, String value);
	
	public  List<Nomenclature> findByName(String name, String orderBy);
	
	public  List<Nomenclature> findByIdNc(int id);
	
	public  Nomenclature findByCode(int idNcName, String code);
	
	public  Nomenclature findByCode(String ncName, String code);
	
	public  Nomenclature findByLlLatin(int idNcName, String llLatin);
	
	public  Nomenclature findByLlArabe(int idNcName, String llArabe);
	
	public List<Nomenclature> findNcReference(String name);
	
	public List<Nomenclature> findNcCompositeList(String ncName, String code, String ncTarget);
	
	public List<Nomenclature> findNcCompositeList(int id, String ncTarget);
	public List<Nomenclature> findByReference(String ncName,
			Integer idReference);
	
	public List<Nomenclature> findByDomaine(String ncDomaine);
	
	public List<Nomenclature> findByNcNameLikeLibellefr(String name, String value);
	
	public List<Nomenclature> findTypePiece(int idTypePiece);
	
	public Nomenclature findReference(String ncCode);
	
}
