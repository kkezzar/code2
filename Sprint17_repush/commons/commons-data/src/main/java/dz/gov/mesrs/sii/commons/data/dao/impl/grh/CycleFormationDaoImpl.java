package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.CycleFormationDao;
import dz.gov.mesrs.sii.commons.data.model.grh.CycleFormation;



@Repository("cycleFormationDao")
public class CycleFormationDaoImpl extends CommonDaoImpl<CycleFormation, Long> implements CycleFormationDao {

	@Override
	protected Class<CycleFormation> getDomainClass() {
		return CycleFormation.class;
	}

	@Override
	protected Criteria getCriteriaForKeyWord(String keyWord) {
		Criteria criteria = getSession().createCriteria(CycleFormation.class);
		String[] words = keyWord.split(" ");
		for (String word : words) {
			criteria.add(Restrictions.disjunction()
			        .add(Restrictions.like("intitule", word, MatchMode.ANYWHERE).ignoreCase()));
		}
		criteria.addOrder(Order.desc("dateDebut"));
		return criteria;
	
	}
	
	
}
