package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.FondsDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.Fonds;

/**
 * Dao Implementation for domain model class Fonds.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.Fonds
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("fondsDao")
@Transactional
public class FondsDaoImpl extends CommonDaoImpl<Fonds, Integer> implements FondsDao {

	@Override
	protected Class<Fonds> getDomainClass() {
		return Fonds.class;

	}

	@Override
	protected Criteria getCriteria(Fonds example) {
		Criteria criteria = super.getCriteria(example);

		if (example.getSituation() != null && example.getSituation().getId() != 0) {
			criteria.createAlias("situation", "situation");
			criteria.add(Restrictions.eq("situation.id", example.getSituation().getId()));
		}
		return criteria;
	}
}