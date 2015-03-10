package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCompteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;

 

/**
 * @author BELDI Jamel  on : 24 f�vr. 2014  10:16:29
 */
public interface RefCompteService {

	public List<RefCompteDto> findAll();
	
	public List<RefCompteDto> findGeneric(String value);
	
	public List<RefCompteDto> findGeneric(Integer etabId, String value);
	
	public List<RefCompteDto> findAdvanced(RefCompteDto refCompteDto);
	
	public List<RefIndividuDto> findByEtablissement(String nom, String prenom, Integer idEtab);
	
	public Integer save(RefCompteDto refCompteDto);
	
	public void update(RefCompteDto refCompteDto);
	
	public  RefCompteDto findById(Integer id);
	
	public  RefCompteDto findByUser(Integer individuId);
	
	public  RefCompteDto findByLogin(String nomUtilisateur);
	
	public void  delete(Integer id);
	
	//public String  generateUsername(String prefix);
	
	public RefCompteDto insertOrUpdate(RefCompteDto refCompteDto);

}