package dz.gov.mesrs.sii.grh.business.service.suivimedical;

import java.util.List;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.model.grh.Vaccin;
import dz.gov.mesrs.sii.grh.business.model.dto.VaccinDto;

public interface VaccinService extends CommonService<Vaccin, VaccinDto, Integer> {
	List<VaccinDto> findAllVaccinsEmploye(Long employeId);

}