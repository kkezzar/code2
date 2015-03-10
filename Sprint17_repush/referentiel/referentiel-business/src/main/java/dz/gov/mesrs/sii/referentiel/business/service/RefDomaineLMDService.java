package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectDomLmdEtabDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineLMDDto;

/**
 * @author BELBRIK Oualid : 11 04. 2014  15:57:52
 */
public interface RefDomaineLMDService {
	List<RefDomaineLMDDto> findAll();
	List<RefDomaineLMDDto> findGeneric(String value);
	List<RefDomaineLMDDto> findAdvanced(RefDomaineLMDDto refDomaineLMDDto);
	RefDomaineLMDDto findById(int id);
	RefDomaineLMDDto findByIdentifiant(String identifiant);
	RefDomaineLMDDto save(RefDomaineLMDDto refDomaineLMDDto);
	void update(RefDomaineLMDDto refDomaineLMDDto);
	void saveOrUpdate(RefDomaineLMDDto refDomaineLMDDto);
	void  delete(String id);
	void delete(RefDomaineLMDDto refDomaineLMDDto);
	List<RefAffectDomLmdEtabDto> findDomainesLMDByEtablissement(Integer idEtab);
	List<RefAffectDomLmdEtabDto> findDomainesLMDByCodeEtablissement(String codeEtab);
	List<RefDomaineLMDDto> findDomainesByEtablissement(Integer idEtab);

}
