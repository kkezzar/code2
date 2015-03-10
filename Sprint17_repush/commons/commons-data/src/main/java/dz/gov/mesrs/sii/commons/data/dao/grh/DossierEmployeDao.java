package dz.gov.mesrs.sii.commons.data.dao.grh;

import java.util.Collection;
import java.util.List;

import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;

public interface DossierEmployeDao extends CommonDao<DossierEmploye, Long> {

	List<Long> findListeAptitudePromotionID(List<Object> paramettres,
			Collection<Integer> listNomenclatureActifDetachementID);

	

	


	List<Long> findListePromotionParModaliteID(List<Object> paramettres,
			Collection<Integer> listNomenclatureActifDetachementID,
			Collection<Integer> listnomenclatureBytypePromotionModaliteID,
			Collection<Integer> listNomenclatureModaliteRecrutementID);






	List<Long> findListePromotionParModalConfID(List<Object> paramettres,
			Collection<Integer> listNomenclatureActifDetachementID,
			Collection<Integer> listnomenclatureBytypePromotionModaliteID,
			Collection<Integer> listNomenclatureModaliteRecrutementID);

}



////COMMIT 15	:  40