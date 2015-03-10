package dz.gov.mesrs.sii.grh.business.service.suivimedical;


import java.util.List;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierMedical;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierMedicalDto;




public interface  DossierMedicalService extends CommonService< DossierMedical,DossierMedicalDto,Integer>{
	
	List<DossierMedicalDto> findAllAntcedentsEmploye(Long employeId);
	List<DossierMedicalDto> findAllAllergiesEmploye(Long employeId);
	List<DossierMedicalDto> findAllPathologiesEmploye(Long employeId);
	

}