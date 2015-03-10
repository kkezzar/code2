package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEntityDto;

 


/**
 * @author BELDI Jamel  on : 19 mars 2014  16:22:45
 */
public interface RefEntityService {

	public List<RefEntityDto> findAll();
	public  RefEntityDto findById(Integer id);
	public List<RefEntityDto> findByDomain(Integer idDomain);
}