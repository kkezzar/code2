package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefConfigurationDto;

 


/**
 * @author BELDI Jamel  on : 19 mars 2014  16:22:45
 */
public interface RefConfigurationService {

	public List<RefConfigurationDto> findAll();
	public  RefConfigurationDto findById(Integer id);
	public List<RefConfigurationDto> findByDomain(Integer idDomain);
}