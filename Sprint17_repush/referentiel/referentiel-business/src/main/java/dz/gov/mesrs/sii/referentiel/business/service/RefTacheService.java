package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefTacheDto;

 

/**
 * @author BELDI Jamel  on : 13 f�vr. 2014  11:40:29
 */
public interface RefTacheService {

	public List<RefTacheDto> findAll();
	public List<RefTacheDto> findByEvenement(Integer evenementId);
	
	public void save(RefTacheDto refTacheDto);
	public void update(RefTacheDto refTacheDto);
	public  RefTacheDto findById(Integer id);
	public void  delete(Integer id);
	public List<RefTacheDto> findByDomaineLMD(String identifiant);

}