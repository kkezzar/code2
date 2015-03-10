package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.model.grh.Statut;
import dz.gov.mesrs.sii.commons.data.dao.grh.StatutDao;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation DAO Statut
 * 
 */

@Repository("statutDao")
public class StatutDaoImpl extends CommonDaoImpl<Statut, Integer> implements StatutDao {

	@Override
	protected Class<Statut> getDomainClass() {
		return Statut.class;
	}

	@Override
	protected Criteria getCriteriaForKeyWord(String keyWord) {
		Criteria criteria = getSession().createCriteria(Statut.class);

		criteria.add(Restrictions.disjunction()
				.add(Restrictions.like("numero", keyWord, MatchMode.ANYWHERE).ignoreCase())
//				.add(Restrictions.like("annee", keyWord, MatchMode.ANYWHERE).ignoreCase())
				.add(Restrictions.like("intitule", keyWord, MatchMode.ANYWHERE).ignoreCase()));
		return criteria;
	}

}
