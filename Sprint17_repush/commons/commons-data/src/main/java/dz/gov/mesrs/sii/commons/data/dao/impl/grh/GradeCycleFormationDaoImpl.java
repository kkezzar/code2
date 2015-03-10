package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.GradeCycleFormationDao;
import dz.gov.mesrs.sii.commons.data.model.grh.GradeCycleFormation;




@Repository("gradeCycleFormationDao")
public class GradeCycleFormationDaoImpl extends CommonDaoImpl<GradeCycleFormation, Long> implements GradeCycleFormationDao {

	@Override
	protected Class<GradeCycleFormation> getDomainClass() {
		return GradeCycleFormation.class;
	}

	@Override
	protected Criteria getCriteriaForKeyWord(String keyWord) {
		Criteria criteria = getSession().createCriteria(GradeCycleFormation.class);
		String[] words = keyWord.split(" ");
		for (String word : words) {
			criteria.add(Restrictions.disjunction()
			        .add(Restrictions.like("observation", word, MatchMode.ANYWHERE).ignoreCase()));
		}
		
		return criteria;
	
	}
	
	
}
