package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.PosteEmploiDao;
import dz.gov.mesrs.sii.commons.data.model.grh.PosteEmploi;

@Repository
public class PosteEmploiDaoImpl extends CommonDaoImpl<PosteEmploi, Long> implements PosteEmploiDao {

	@Override
	protected Class<PosteEmploi> getDomainClass() {
		return PosteEmploi.class;
	}

	@Override
	protected Criteria getCriteriaForKeyWord(String keyWord) {
		Criteria criteria = getSession().createCriteria(PosteEmploi.class);
		criteria.createAlias("etablissement", "etablissement", CriteriaSpecification.LEFT_JOIN);
		criteria.setFetchMode("etablissement", FetchMode.SELECT);
		if (StringUtils.isEmpty(keyWord)) {
			return criteria;
		}

		String[] words = keyWord.split(" ");
		for (String word : words) {
			criteria.add(Restrictions.disjunction()
					.add(Restrictions.like("code", word, MatchMode.ANYWHERE).ignoreCase())
					.add(Restrictions.like("objet", word, MatchMode.ANYWHERE).ignoreCase()));

		}
		return criteria;
	}

}
