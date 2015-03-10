package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.CompteDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.Compte;

/**
 * Dao Implementation for domain model class Compte.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.Compte
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("compteDao")
@Transactional
public class CompteDaoImpl extends CommonDaoImpl<Compte, Integer> implements CompteDao {

	@Override
	protected Class<Compte> getDomainClass() {
		return Compte.class;
	}

	@Override
	protected Criteria getCriteria(Compte example) {
		Criteria criteria = super.getCriteria(example);

		if (example.getType() != null && example.getType().getId() != 0) {
			criteria.createAlias("type", "type");
			criteria.add(Restrictions.eq("type.id", example.getType().getId()));
		}
		if (example.getAgentComptable() != null && example.getAgentComptable().getId() != null) {
			criteria.createAlias("agentComptable", "agentComptable");
			criteria.add(Restrictions.eq("agentComptable.id", example.getAgentComptable().getId()));
		}
		if (example.getSituation() != null && example.getSituation().getId() != 0) {
			criteria.createAlias("situation", "situation");
			criteria.add(Restrictions.eq("situation.id", example.getSituation().getId()));
		}
		return criteria;
	}
}