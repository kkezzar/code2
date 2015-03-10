package dz.gov.mesrs.sii.fve.business.service.concours;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.concours.ConcoursDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;

public interface ConcoursService {

	ConcoursDto save(ConcoursDto dto);

	ConcoursDto findById(Long idConcours);

	ConcoursDto find(ConcoursDto dto);

	List<ConcoursDto> findAll(ConcoursDto dto);

	void delete(ConcoursDto dto);
	
	List<RefIndividuDto> findResponsableExamens(int etablissementId);

	ConcoursDto validate(ConcoursDto concoursDto);

}
