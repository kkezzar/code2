package dz.gov.mesrs.sii.grh.business.service.dossieremploye;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.grh.business.model.dto.CarriereDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;

public interface DossierEmployeService extends CommonService<DossierEmploye, DossierEmployeDto, Long> {

	int countAllAdmissibleRetraite(Date dateDebutPeriode, Date dateFinPeriode);

	List<DossierEmployeDto> findAllAdmissibleRetraite(DossierEmployeDto dossierEmployeDto, SearchMode searchMode,
			Date dateDebutPeriode, Date dateFinPeriode);

	DossierEmployeDto saveTitularisation(Date dateTitularisation, CarriereDto carriereDto);

	List<Long> findListeAptitudePromotionID(List<Object> paramettres,
			Collection<Integer> listNomenclatureActifDetachementID);

	List<Long> findListePromotionParModaliteID(List<Object> paramettres,
			Collection<Integer> listNomenclatureActifDetachementID,
			Collection<Integer> listnomenclatureBytypePromotionModaliteID,
			Collection<Integer> listNomenclatureModaliteRecrutementID);
	


	
	/**
	 * Retourne tous les employes d'un etbalissement qui ne sont pas en fin 
	 * d'activite.
	 * 
	 * @param etablissementId
	 * @param searchKeyWords
	 * @param searchMode
	 * @return
	 */
	List<DossierEmployeDto> findAllActif(DossierEmployeDto example, SearchMode searchMode);

	int countAllActif(DossierEmployeDto example);

	List<Long> findListePromotionAconfirmarID(List<Object> paramettres,
			Collection<Integer> listNomenclatureActifDetachementID,
			Collection<Integer> listnomenclatureBytypePromotionModaliteID,
			Collection<Integer> listNomenclatureModaliteRecrutementID);

	DossierEmployeDto saveDossierEmployeByCarriere(CarriereDto carriereDto);

	List<Long> findListePromotionParModalConfID(List<Object> paramettres,
			Collection<Integer> listNomenclatureActifDetachementID,
			Collection<Integer> listnomenclatureBytypePromotionModaliteID,
			Collection<Integer> listNomenclatureModaliteRecrutementID);
	
    DossierEmployeDto findByIndividuId(Integer IndividuId);
}