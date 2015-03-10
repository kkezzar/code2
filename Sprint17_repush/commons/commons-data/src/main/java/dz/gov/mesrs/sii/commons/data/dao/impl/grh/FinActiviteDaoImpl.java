package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.FinActiviteDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.grh.FinActivite;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation DAO FinActivite
 * 
 */

@Repository("finActiviteDao")
public class FinActiviteDaoImpl extends CommonDaoImpl<FinActivite, Long> implements FinActiviteDao {

	@Override
	protected Class<FinActivite> getDomainClass() {
		return FinActivite.class;
	}

	@Override
	protected Criteria getCriteria(FinActivite example) {
		Criteria criteria = super.getCriteria(example);
		Nomenclature nomenclature = example.getNomenclature();
		SituationEntite situtation = example.getSituation();
		if (nomenclature != null) {
			criteria.createAlias("nomenclature", "nomenclature");
			criteria.add(Restrictions.eq("nomenclature.id", nomenclature.getId()));
		}
		if (situtation != null) {
			criteria.createAlias("situation", "situation");
			criteria.add(Restrictions.eq("situation.id", situtation.getId()));
		}
		return criteria;
	}

	@Override
	protected Criteria getCriteriaForKeyWord(String keyWord) {
		Criteria criteria = getSession().createCriteria(FinActivite.class);
		criteria.createAlias("employe", "employe", CriteriaSpecification.LEFT_JOIN);
		// criteria.createAlias("employe.refIndividu", "individu",
		// CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("employe.refEtablissement", "etablissement", CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("nomenclature", "nomenclature", CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("situation", "situation", CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("situation.situation", "situationCode", CriteriaSpecification.LEFT_JOIN);
		// criteria.setFetchMode("employe", FetchMode.SELECT);
		// criteria.setFetchMode("employe.etablissement", FetchMode.SELECT);
		criteria.setFetchMode("situation", FetchMode.SELECT);
		criteria.setFetchMode("nomenclature", FetchMode.SELECT);
		// criteria.setFetchMode("nomenclature.nomenclature", FetchMode.SELECT);
		// criteria.setFetchMode("nomenclature.ncNames", FetchMode.SELECT);
		// criteria.setFetchMode("employe.refIndividu", FetchMode.SELECT);
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

}
