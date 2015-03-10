package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.AbsenceDao;
import dz.gov.mesrs.sii.commons.data.model.grh.Absence;

@Repository("absenceDao")
public class AbsenceDaoImpl extends CommonDaoImpl<Absence, Integer> implements AbsenceDao {

	@Override
	protected Class<Absence> getDomainClass() {
		return Absence.class;
	}

	@Override
	protected Criteria getCriteriaForKeyWord(String keyWord) {
		Criteria criteria = getSession().createCriteria(Absence.class);
		criteria.createAlias("employe", "employe", CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("employe.refEtablissement", "etablissement", CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("nomenclature", "nomenclature", CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("situation", "situation", CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("situation.situation", "situationCode", CriteriaSpecification.LEFT_JOIN);
		criteria.setFetchMode("situation", FetchMode.SELECT);
		criteria.setFetchMode("nomenclature", FetchMode.SELECT);
		criteria.setFetchMode("situation.situation", FetchMode.SELECT);
		criteria.setFetchMode("situation.situation.situationEntites", FetchMode.SELECT);
		criteria.setFetchMode("situation.situation.i18n", FetchMode.SELECT);
		criteria.setFetchMode("situation.situationEntiteOccurrences", FetchMode.SELECT);

		criteria.createAlias("employe.refIndividu", "individu", CriteriaSpecification.LEFT_JOIN);
		criteria.setFetchMode("employe", FetchMode.SELECT);

		if (StringUtils.isEmpty(keyWord)) {
			return criteria;
		}

		String[] words = keyWord.split(" ");
		for (String word : words) {
			criteria.add(Restrictions.disjunction()
					.add(Restrictions.like("employe.matricule", word, MatchMode.ANYWHERE).ignoreCase())
					.add(Restrictions.like("individu.nomLatin", word, MatchMode.ANYWHERE).ignoreCase())
					.add(Restrictions.like("individu.prenomLatin", word, MatchMode.ANYWHERE).ignoreCase()));

		}
		return criteria;
	}

	@Override
	protected Criteria getCriteria(Absence example) {
		Criteria criteria = super.getCriteria(example);
		if (example.getEmploye() != null) {
			criteria.createAlias("employe", "employe", CriteriaSpecification.LEFT_JOIN);
			criteria.add(Restrictions.eq("employe.id", example.getEmploye().getId()));
		}
		
		criteria.addOrder(Order.desc("dateDebut"));
		
		return criteria;
	}

}
