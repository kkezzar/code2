/**

 * [dz.gov.mesrs.sii.referentiel.business.service.RefAffectationService.java] 
 * @author BELDI Jamel on : 16 janv. 2014  08:57:58
 */
package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectationDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;

/**
 * @author BELDI Jamel on : 16 janv. 2014 08:57:58
 */
public interface RefAffectationService {
	public void save(RefAffectationDto refAffectationDto);

	public void update(RefAffectationDto refAffectationDto);

	public RefAffectationDto findById(int id);

	public void delete(RefAffectationDto refAffectationDto);

	public List<RefAffectationDto> findStructures(String entity,
			Integer idEntity);

	public List<RefAffectationDto> findGroupes(String entity, Integer idEntity);

	public List<RefAffectationDto> findDomaines(String entity, Integer idEntity);

	public List<RefAffectationDto> findFilieres(String entity, Integer idEntity);

	public List<RefAffectationDto> findIndividus(String entity, Integer idEntity);

	public List<RefAffectationDto> findIndividus(String entity,
			String codeEntity);

	public List<RefAffectationDto> findAffectationByIdIndividu(Integer id);

	public List<RefAffectationDto> findAffectationByIdIndividu(Integer etabId,
			Integer id);

	public RefAffectationDto saveOrUpdate(RefAffectationDto refAffectationDto);

	public RefAffectationDto findGroupeParent(String entity, String codeGroupe);

	public RefAffectationDto findGroupeParent(String entity, Integer idGroupe);

	public List<RefAffectationDto> findStructuresByIndividuAndRole(
			Integer idIndividu, Integer idRole);

	public List<RefAffectationDto> findAffectationByIdGroupe(Integer etabId,
			Integer idGroupe);

	public List<RefIndividuDto> findIndividuByRole(Integer idEtab,
			Integer idRole);
}
