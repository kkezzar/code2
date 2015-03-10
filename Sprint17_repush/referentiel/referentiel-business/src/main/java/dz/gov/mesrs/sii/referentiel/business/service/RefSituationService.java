/**
 * 
 */
package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefSituationDto;

/**
 * @author obelbrik
 *
 */
public interface RefSituationService {
	public void save(RefSituationDto refSituationDto);
	public List<RefSituationDto> findListSituationByEntityName(String entityName);
	public List<RefSituationDto> findAll();
	public List<RefSituationDto> findGeneric(String value);
	public List<RefSituationDto> findAdvanced(RefSituationDto refSituationDto);
	public void update(RefSituationDto refSituationDto);
	public void saveOrUpdate(RefSituationDto refSituationDto);
	public  RefSituationDto findById(String id);
	public void  delete(String id);
	void delete(RefSituationDto refSituationDto);
	List<RefSituationDto> findSituations(String entity, Integer idEntity);

	

}
