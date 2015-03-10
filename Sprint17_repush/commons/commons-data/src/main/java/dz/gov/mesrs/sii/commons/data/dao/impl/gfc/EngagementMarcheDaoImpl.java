package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.EngagementMarcheDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.EngagementMarche;

/**
 * Dao Implementation for domain model class EngagementMarche.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.EngagementMarche
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("engagementMarcheDao")
@Transactional
public class EngagementMarcheDaoImpl extends CommonDaoImpl<EngagementMarche, Integer> implements EngagementMarcheDao {

	@Override
	protected Class<EngagementMarche> getDomainClass() {
		return EngagementMarche.class;

	}
}