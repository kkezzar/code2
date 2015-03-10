package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectDomLmdEtabDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectFiliereLmdEtabDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFiliereLmdDto;
/**
 * @author BELBRIK Oualid : 21 04. 2014  11:57:52
 */
public interface RefFiliereLmdService {
	List<RefFiliereLmdDto> findAll();
	List<RefFiliereLmdDto> findGeneric(String value);
	List<RefFiliereLmdDto> findAdvanced(RefFiliereLmdDto refFiliereLmdDto);
	RefFiliereLmdDto save(RefFiliereLmdDto refFiliereLmdDto);
	void update(RefFiliereLmdDto refFiliereLmdDto);
	void saveOrUpdate(RefFiliereLmdDto refFiliereLmdDto);
	RefFiliereLmdDto findByIdentifiant(String identifiant);
	RefFiliereLmdDto findById(int id);
	void  delete(String id);
	void delete(RefFiliereLmdDto refFiliereLmdDto);
	List<RefFiliereLmdDto> findByCodeDomainelmd(String value);
	List<RefFiliereLmdDto> findByIdDomainelmd(Integer value);
	List<RefAffectFiliereLmdEtabDto> findFilieresLMDByEtablissement(int idEtab,  String codeDomaine);
	List<RefAffectFiliereLmdEtabDto> findFilieresLMDByCodeEtablissement(String codeEtab);


}
