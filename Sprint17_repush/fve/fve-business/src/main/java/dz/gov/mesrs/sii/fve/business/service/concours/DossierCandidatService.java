package dz.gov.mesrs.sii.fve.business.service.concours;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.concours.ConcoursDto;
import dz.gov.mesrs.sii.fve.business.model.dto.concours.DossierCandidatDto;

public interface DossierCandidatService {

	DossierCandidatDto find(DossierCandidatDto dto);

	DossierCandidatDto save(DossierCandidatDto dto);

	void delete(DossierCandidatDto dto);

	List<DossierCandidatDto> findAll(DossierCandidatDto dto);

	DossierCandidatDto genererDossierInsicription(DossierCandidatDto dto);

	/**
	 * @author Mounir.MESSAOUDI on : 2 nov. 2014 09:26:36
	 * @param idConcours
	 * @return
	 */
	public List<DossierCandidatDto> findAllAdmisByIdConcours(Long idConcours);

	/**
	 * @author Mounir.MESSAOUDI on : 2 nov. 2014 09:28:58
	 * @param idConcours
	 * @param idEtabAdmission
	 * @return
	 */
	public List<DossierCandidatDto> findAllAdmisByIdConcoursIdEtabAdmission(
			Long idConcours, Integer idEtabAdmission);

	void calculMoyenne(ConcoursDto concoursDto, DossierCandidatDto candidat);

	DossierCandidatDto enregisterResultat(DossierCandidatDto dto);

	int countByExample(DossierCandidatDto dto);

	List<DossierCandidatDto> findByExample(DossierCandidatDto dto, String sortField, int pageSize, int first,
		Boolean descending);
}
