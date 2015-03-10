package dz.gov.mesrs.sii.commons.data.dao.fve.offreformation;

import java.util.List;
import java.util.Map;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormation;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationI18n;


/**
 * @author  Rafik LAIB  on : 5 avr. 2014  22:05:19
 */
public interface OffreFormationDao {

	public  void persist(OffreFormation transientInstance);

	public  void remove(OffreFormation persistentInstance);

	public  OffreFormation merge(OffreFormation detachedInstance);

	public  OffreFormation findById(int id);
	
	public  List<OffreFormation> findByCode(String codeNat);
	
	public  List<OffreFormation> findGeneric(Map<String, String> attributes, Integer idEtab) ;
	
	public  List<OffreFormation> findAdvanced(Map<String, String> attributes, Map<String, Object> attributesDto,Integer idEtab) ;	
	
	public  List<OffreFormation> findAll();
	
	public  List<OffreFormation> findByIdEtablissement(int idEtab);
	
	public  List<OffreFormation> findOffreToHabilitation(OffreFormation offreFormation) ;	

	public  List<OffreFormation> findByCodeSituation(String codeSituation) ;

	public  int getLastOfIndex(Integer idEtab) ;
	
	public void flushAndClear();	
	
	public  OffreFormationI18n  findI18nByOfId(int ofId, String langue);
	
}