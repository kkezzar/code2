package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefSpecialiteLmdDto;

/**
 * @author BELBRIK Oualid : 21 04. 2014  11:57:52
 */
public interface RefSpecialiteLmdService {
	
	List<RefSpecialiteLmdDto> findAll();
	
	List<RefSpecialiteLmdDto> findGeneric(String value);
	
	List<RefSpecialiteLmdDto> findAdvanced(RefSpecialiteLmdDto refSpecialiteLmdDto);
	
	RefSpecialiteLmdDto save(RefSpecialiteLmdDto refSpecialiteLmdDto);
	
	void update(RefSpecialiteLmdDto refSpecialiteLmdDto);
	
	void saveOrUpdate(RefSpecialiteLmdDto refSpecialiteLmdDto);
	
	RefSpecialiteLmdDto findById(int id);
	
	RefSpecialiteLmdDto findByIdentifiant(String identifiant);
	
	void  delete(String id);
	
	void delete(RefSpecialiteLmdDto refSpecialiteLmdDto);
	
	List<RefSpecialiteLmdDto> findByCodeFilierelmd(String value);
	
	List<RefSpecialiteLmdDto> findByIdFilierelmd(Integer value);



}
