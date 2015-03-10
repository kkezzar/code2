package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.SessionFormationDao;
import dz.gov.mesrs.sii.commons.data.model.grh.SessionFormation;




@Repository("sessionFormationDao")
public class SessionFormationDaoImpl extends CommonDaoImpl<SessionFormation, Long> implements SessionFormationDao {

	@Override
	protected Class<SessionFormation> getDomainClass() {
		return SessionFormation.class;
	}

	@Override
	protected Criteria getCriteriaForKeyWord(String keyWord) {
		Criteria criteria = getSession().createCriteria(SessionFormation.class);

		criteria.createAlias("cycleFormation", "cycleFormation" ,CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("formationCatalogue", "formationCatalogue", CriteriaSpecification.LEFT_JOIN);
		criteria.setFetchMode("cycleFormation", FetchMode.SELECT);
		criteria.setFetchMode("formationCatalogue", FetchMode.SELECT);
		if (StringUtils.isEmpty(keyWord)) {
			return criteria;
		}
		String[] words = keyWord.split(" ");
		for (String word : words) {
			criteria.add(Restrictions.disjunction()
					.add(Restrictions.like("cycleFormation.intitule", word, MatchMode.ANYWHERE).ignoreCase())
					.add(Restrictions.like("formationCatalogue.intitule", word, MatchMode.ANYWHERE).ignoreCase())
			      );
		}
		criteria.addOrder(Order.desc("dateDebut"));
		return criteria;
	
	}
	
	
	
	

	
	
	
}
