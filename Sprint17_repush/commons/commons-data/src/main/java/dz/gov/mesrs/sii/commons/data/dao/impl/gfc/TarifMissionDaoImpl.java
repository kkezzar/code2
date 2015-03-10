package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.TarifMissionDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.Marche;
import dz.gov.mesrs.sii.commons.data.model.gfc.TarifMission;

/**
 * Dao Implementation for domain model class TarifMission.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.TarifMission
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("tarifMissionDao")
@Transactional
public class TarifMissionDaoImpl extends CommonDaoImpl<TarifMission, Integer> implements TarifMissionDao {

	@Override
	protected Class<TarifMission> getDomainClass() {
		return TarifMission.class;
	}

	@Override
	protected Criteria getCriteria(TarifMission example) {
		Criteria criteria = super.getCriteria(example);

//		
//		if (example.getSituation() != null && example.getSituation().getId() != 0) {
//			criteria.createAlias("situation", "situation");
//			criteria.add(Restrictions.eq("situation.id", example.getSituation().getId()));
//		}

	
		return criteria;
	}
}