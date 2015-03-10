package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.DossierMissionDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.DossierMission;

/**
 * Dao Implementation for domain model class DossierMission.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.DossierMission
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("dossierMissionDao")
@Transactional
public class DossierMissionDaoImpl extends CommonDaoImpl<DossierMission, Integer> implements DossierMissionDao {

	@Override
	protected Class<DossierMission> getDomainClass() {
		return DossierMission.class;
	}

}