package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.AffectationPosteDao;
import dz.gov.mesrs.sii.commons.data.model.grh.AffectationPoste;

@Repository
public class AffectationPosteDaoImpl extends CommonDaoImpl<AffectationPoste, Long> implements AffectationPosteDao {

	@Override
	protected Class<AffectationPoste> getDomainClass() {
		return AffectationPoste.class;
	}

	@Override
	protected Criteria getCriteria(AffectationPoste example) {
		Criteria criteria = super.getCriteria(example);
		criteria.addOrder(Order.desc("dateDebutAffectation"));
		if (example.getPoste() != null) {
			criteria.createAlias("poste", "poste");
			if (example.getPoste().getId() != null) {
				criteria.add(Restrictions.eq("poste.id", example.getPoste().getId()));
			}
		}
		return criteria;
	}

}
