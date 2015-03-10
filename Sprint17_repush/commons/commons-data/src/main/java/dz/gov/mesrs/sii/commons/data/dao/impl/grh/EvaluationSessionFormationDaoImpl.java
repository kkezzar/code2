package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.EvaluationSessionFormationDao;
import dz.gov.mesrs.sii.commons.data.model.grh.EvaluationSessionFormation;





@Repository("evaluationSessionFormationDao")
public class EvaluationSessionFormationDaoImpl extends CommonDaoImpl<EvaluationSessionFormation, Long> implements EvaluationSessionFormationDao {

	@Override
	protected Class<EvaluationSessionFormation> getDomainClass() {
		return EvaluationSessionFormation.class;
	}

	@Override
	protected Criteria getCriteriaForKeyWord(String keyWord) {
		Criteria criteria = getSession().createCriteria(EvaluationSessionFormation.class);
		String[] words = keyWord.split(" ");
		for (String word : words) {
			criteria.add(Restrictions.disjunction()
			        .add(Restrictions.like("observation", word, MatchMode.ANYWHERE).ignoreCase()));
		}
		
		return criteria;
	
	}
	
	
}
