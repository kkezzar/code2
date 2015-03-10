package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.RepartitionBudgetStructureDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.RepartitionBudgetStructure;

/**
 * Dao Implementation for domain model class RepartitionBudgetStructure.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.RepartitionBudgetStructure
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 25/02/2014 11:54:25
 */
@Repository("repartitionBudgetStructureDao")
@Transactional
public class RepartitionBudgetStructureDaoImpl extends CommonDaoImpl<RepartitionBudgetStructure, Integer> implements
		RepartitionBudgetStructureDao {

	@Override
	protected Class<RepartitionBudgetStructure> getDomainClass() {
		return RepartitionBudgetStructure.class;

	}

	@Override
	protected Criteria getCriteria(RepartitionBudgetStructure example) {
		Criteria criteria = super.getCriteria(example);

		if (example.getEtablissement() != null && example.getEtablissement().getId() != 0) {
			criteria.createAlias("etablissement", "etablissement");
			criteria.add(Restrictions.eq("etablissement", example.getEtablissement()));
		}
		if (example.getStructure() != null && example.getStructure().getId() != 0) {
			criteria.createAlias("structure", "structure");
			criteria.add(Restrictions.eq("structure", example.getStructure()));
		}

		if (example.getChapitre() != null && example.getChapitre().getId() != null) {
			criteria.createAlias("chapitre", "chapitre");
			criteria.add(Restrictions.eq("chapitre", example.getChapitre()));
		}

		if (example.getArticle() != null && example.getArticle().getId() != null) {
			criteria.createAlias("article", "article");
			criteria.add(Restrictions.eq("article", example.getArticle()));
		}
		return criteria;
	}
}