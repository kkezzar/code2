package dz.gov.mesrs.sii.fve.business.service.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.MatiereConstitutiveDto;

/**
 * 
 * @author Kheireddine OMRANI
 *
 */
public interface  MatiereConstitutiveService {

	/**
	 * 
	 * @param matiereConstitutiveDto
	 * @return
	 */
	MatiereConstitutiveDto insertOrUpdate( MatiereConstitutiveDto matiereConstitutiveDto);
	
	/**
	 * 
	 * @param matiereConstitutiveDto
	 */
	void remove( MatiereConstitutiveDto matiereConstitutiveDto);	
	

	MatiereConstitutiveDto invalidate(int matiereConstitutiveId);

	MatiereConstitutiveDto validate(int matiereConstitutiveId); 
	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	MatiereConstitutiveDto findById(int id);

	/**
	 * 
	 * @param fullTextKeyword
	 * @return
	 */
	List<MatiereConstitutiveDto> findWithFullText(String fullTextKeyword);
	
    List<MatiereConstitutiveDto> findAll() ;
    
	MatiereConstitutiveDto findByCode(String code);
	
	
}