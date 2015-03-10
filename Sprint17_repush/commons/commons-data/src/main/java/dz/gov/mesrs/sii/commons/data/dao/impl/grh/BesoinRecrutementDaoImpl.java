package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.BesoinRecrutementDao;
import dz.gov.mesrs.sii.commons.data.model.grh.BesoinRecrutement;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation DAO BesoinRecrutement
 * 
 */

@Repository("besoinRecrutementDao")
public class BesoinRecrutementDaoImpl extends CommonDaoImpl<BesoinRecrutement, Integer> implements BesoinRecrutementDao {

	@Override
	protected Class<BesoinRecrutement> getDomainClass() {
		return BesoinRecrutement.class;
	}

	@Override
	protected Criteria getCriteriaForKeyWord(String keyWord) {
		Criteria criteria = getSession().createCriteria(BesoinRecrutement.class);

		criteria.createAlias("nomenclatureByTypeRecrutement", "type");
		criteria.createAlias("nomenclatureByModeRecrutement", "mode");

		criteria.add(Restrictions.disjunction()
				.add(Restrictions.like("type.libelleLongFr", keyWord, MatchMode.ANYWHERE).ignoreCase())
				.add(Restrictions.like("mode.libelleLongFr", keyWord, MatchMode.ANYWHERE).ignoreCase())
				.add(Restrictions.like("objet", keyWord, MatchMode.ANYWHERE).ignoreCase())
				.add(Restrictions.like("observation", keyWord, MatchMode.ANYWHERE).ignoreCase()));
		return criteria;
	}
}