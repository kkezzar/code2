package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.RepartitionBudgetDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.RepartitionBudget;

/**
 * Dao Implementation for domain model class RepartitionBudget.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.RepartitionBudget
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("repartitionBudgetDao")
@Transactional
public class RepartitionBudgetDaoImpl extends CommonDaoImpl<RepartitionBudget, Integer> implements RepartitionBudgetDao {

	@Override
	protected Class<RepartitionBudget> getDomainClass() {
		return RepartitionBudget.class;

	}

	@Override
	protected Criteria getCriteria(RepartitionBudget example) {
		Criteria criteria = super.getCriteria(example);

		if (example.getDecisionBudget() != null && example.getDecisionBudget().getId() != null) {
			criteria.createAlias("decisionBudget", "decisionBudget");
			criteria.add(Restrictions.eq("decisionBudget", example.getDecisionBudget()));
		}

		if (example.getSituation() != null && example.getSituation().getId() != 0) {
			criteria.createAlias("situation", "situation");
			criteria.add(Restrictions.eq("situation.id", example.getSituation().getId()));
		}

		return criteria;
	}

	@Override
	public List<Object[]> getTotalOfDetailsRepartitionBudget(RepartitionBudget repartitionBudget) {
		Query query = getSession()
				.createSQLQuery(
						"select recette_type, montant_total "
								+ "from gfc.v_details_reparatition_budget_total where id_repartition_budget = :id_repartition_budget");
		query.setParameter("id_repartition_budget", repartitionBudget.getId());
		return query.list();
	}
}