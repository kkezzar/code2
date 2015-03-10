package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.DetailEngagementMarcheDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.DetailEngagementMarche;

/**
 * Dao Implementation for domain model class DetailEngagementMarche.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.DetailEngagementMarche
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("detailEngagementMarcheDao")
@Transactional
public class DetailEngagementMarcheDaoImpl extends CommonDaoImpl<DetailEngagementMarche, Integer> implements
		DetailEngagementMarcheDao {

	@Override
	protected Class<DetailEngagementMarche> getDomainClass() {
		return DetailEngagementMarche.class;

	}
}