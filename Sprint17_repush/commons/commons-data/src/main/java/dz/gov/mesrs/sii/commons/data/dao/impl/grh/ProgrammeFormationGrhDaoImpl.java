package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.ProgrammeFormationGrhDao;
import dz.gov.mesrs.sii.commons.data.model.grh.ProgrammeFormationGrh;


@Repository("programmeFormationGrhDao")
public class ProgrammeFormationGrhDaoImpl extends CommonDaoImpl<ProgrammeFormationGrh, Long> implements ProgrammeFormationGrhDao {

	@Override
	protected Class<ProgrammeFormationGrh> getDomainClass() {
		return ProgrammeFormationGrh.class;
	}

	@Override
	protected Criteria getCriteriaForKeyWord(String keyWord) {
		Criteria criteria = getSession().createCriteria(ProgrammeFormationGrh.class);
		String[] words = keyWord.split(" ");
		for (String word : words) {
			criteria.add(Restrictions.disjunction()
			        .add(Restrictions.like("intitule", word, MatchMode.ANYWHERE).ignoreCase())
			        .add(Restrictions.like("objectif", word, MatchMode.ANYWHERE).ignoreCase())
			        .add(Restrictions.like("code", word, MatchMode.ANYWHERE).ignoreCase())
					);
		}
		criteria.addOrder(Order.desc("dateDebut"));
		return criteria;
	
	}
	
	
}
