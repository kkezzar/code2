package dz.gov.mesrs.sii.commons.data.dao.grh;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.grh.ChangementPosition;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;

public interface ChangementPositionDao extends CommonDao<ChangementPosition, Long> {

	List<DossierEmploye> findListEmployesParPosition(RefEtablissement refEtablissement,
			List<Nomenclature> listNomenclatureByPositionAgent);

	Nomenclature findPosition(Long id);

}