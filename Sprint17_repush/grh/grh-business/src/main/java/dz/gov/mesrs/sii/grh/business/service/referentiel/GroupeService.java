package dz.gov.mesrs.sii.grh.business.service.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.grh.business.model.dto.GroupeDto;

public interface GroupeService {

	List<GroupeDto> findAll();

	List<GroupeDto> findAllByStatutId(Integer selectedStatutId);
}
