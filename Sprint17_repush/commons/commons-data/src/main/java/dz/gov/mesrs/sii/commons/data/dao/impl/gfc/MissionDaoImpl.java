package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.MissionDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.Mission;

/**
 * Dao Implementation for domain model class Mission.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.Mission
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("missionDao")
@Transactional
public class MissionDaoImpl extends CommonDaoImpl<Mission, Integer> implements MissionDao {

	@Override
	protected Class<Mission> getDomainClass() {
		return Mission.class;
	}

}