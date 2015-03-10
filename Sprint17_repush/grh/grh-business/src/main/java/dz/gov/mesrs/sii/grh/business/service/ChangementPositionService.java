package dz.gov.mesrs.sii.grh.business.service;

import java.util.List;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.model.grh.ChangementPosition;
import dz.gov.mesrs.sii.grh.business.model.dto.ChangementPositionDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

public interface ChangementPositionService extends CommonService<ChangementPosition, ChangementPositionDto, Long> {

	List<DossierEmployeDto> findListEmployesParPositionDto(RefEtablissementDto refEtablissementDto,
			List<NomenclatureDto> listNomenclatureByPositionAgentDto);

	NomenclatureDto findPosition(Long id);

	List<ChangementPositionDto> findAllPendingRequest(Integer etablissementId, String searchKeyWords,
			SearchMode searchMode);

	ChangementPositionDto findPendingChangementPosition(Long dossierEmployeId);

	int countAllPendingRequest(Integer etablissementId, String searchKeyWords);

	ChangementPositionDto saveDemande(ChangementPositionDto dto);

	void traiterDemande(ChangementPositionDto changementPositionDto);

	int countAllAcceptedRequest(int etablissementId, String searchKeyWords);

	List<ChangementPositionDto> findAllAcceptedRequest(int etablissementId, String searchKeyWords, SearchMode searchMode);

	void validate(ChangementPositionDto changementPositionDto);

	void reintegrer(ChangementPositionDto changementPositionDto);

	int countAllValidatedRequest(int etablissementId, String searchKeyWords);

	List<ChangementPositionDto> findAllValidatedRequest(int etablissementId, String searchKeyWords,
			SearchMode searchMode);

	ChangementPositionDto saveChangementDroit(ChangementPositionDto changementPositionDto);

	void validateChangementDroit(ChangementPositionDto changementPositionDto);

	int countAllPendingChangementDroit(Integer etablissementId, String searchKeyWords);

	List<ChangementPositionDto> findAllPendingChangementDroit(Integer etablissementId, String searchKeyWords,
			SearchMode searchMode);

	List<ChangementPositionDto> findHistorique(Long dossierEmployeId);

}