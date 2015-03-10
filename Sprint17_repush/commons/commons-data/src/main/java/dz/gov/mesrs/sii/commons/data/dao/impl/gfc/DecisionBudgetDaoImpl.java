package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.DecisionBudgetDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.DecisionBudget;

/**
 * Dao Implementation for domain model class DecisionBudget.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.DecisionBudget
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("decisionBudgetDao")
@Transactional
public class DecisionBudgetDaoImpl extends CommonDaoImpl<DecisionBudget, Integer> implements DecisionBudgetDao {

	@Override
	protected Class<DecisionBudget> getDomainClass() {
		return DecisionBudget.class;

	}

	@Override
	protected Criteria getCriteria(DecisionBudget example) {
		Criteria criteria = super.getCriteria(example);

		if (example.getEtablissement() != null && example.getEtablissement().getId() != 0) {
			criteria.createAlias("etablissement", "etablissement");
			criteria.add(Restrictions.eq("etablissement", example.getEtablissement()));
		}

		return criteria;
	}

	@Override
	public List<Object[]> getTotalOfNotificationsBudget(DecisionBudget decisionBudget) {
		Query query = getSession()
				.createSQLQuery(
						"select recette_type, montant_total "
								+ "from gfc.v_details_notification_decision_budget_total where id_decision_budget = :id_decision_budget");
		query.setParameter("id_decision_budget", decisionBudget.getId());
		return query.list();
	}

}