package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.MutationDao;
import dz.gov.mesrs.sii.commons.data.model.grh.Mutation;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation DAO Mutation
 * 
 */

@Repository("mutationDao")
@Transactional
public class MutationDaoImpl extends CommonDaoImpl<Mutation, Integer> implements MutationDao {

	@Override
	protected Class<Mutation> getDomainClass() {
		return Mutation.class;
	}

	@Override
	protected Criteria getCriteriaForKeyWord(String keyWord) {
		Criteria criteria = getSession().createCriteria(Mutation.class);

		criteria.createAlias("dossierEmploye", "dossierEmploye");
		criteria.createAlias("dossierEmploye.refIndividu", "refIndividu");
		
		if (StringUtils.isEmpty(keyWord)) {
			return criteria;
		}

		String[] words = keyWord.split(" ");
		for (String word : words) {
			criteria.add(Restrictions.disjunction()
					.add(Restrictions.like("dossierEmploye.matricule", word, MatchMode.ANYWHERE).ignoreCase())
					.add(Restrictions.like("refIndividu.nomLatin", word, MatchMode.ANYWHERE).ignoreCase())
					.add(Restrictions.like("refIndividu.prenomLatin", word, MatchMode.ANYWHERE).ignoreCase()));
			
		}
		criteria.addOrder(Order.asc("dateDecision"));
		return criteria;
		
	}
}
