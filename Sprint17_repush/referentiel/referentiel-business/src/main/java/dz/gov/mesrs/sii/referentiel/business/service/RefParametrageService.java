package dz.gov.mesrs.sii.referentiel.business.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFileConfigDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefParamDto;

 

/**
 * @author BELDI Jamel  on : 13 fevr. 2014  11:40:29
 */
public interface RefParametrageService {
	public RefFileConfigDto findById(Integer id);
	public List<RefFileConfigDto> findResourcesBundle(Integer idDomain, Integer idEntity, Integer idLanguage);
	public List<RefFileConfigDto> findResourcesConfig(Integer idDomain, Integer idConfiguration);
	public List<RefParamDto> viewParams(RefFileConfigDto refFileConfigDto) throws FileNotFoundException;
	public void updateParamValue(RefParamDto refParamDto) throws IOException;
	public void initDefaultConfig(RefFileConfigDto refFileConfigDto) throws IOException;
	public RefParamDto getParamConfiguration(String key, String configuration);

}