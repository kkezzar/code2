package dz.gov.mesrs.sii.commons.data.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.EvaluationPeriodeDao;
import dz.gov.mesrs.sii.commons.data.model.grh.EvaluationPeriode;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

@Repository
public class EvaluationPeriodeDaoImpl extends CommonDaoImpl<EvaluationPeriode, Long> implements EvaluationPeriodeDao {

	@Override
	protected Class<EvaluationPeriode> getDomainClass() {
		return EvaluationPeriode.class;
	}

	@Override
	protected Criteria getCriteria(EvaluationPeriode example) {
		Criteria criteria = super.getCriteria(example);
		if (example.getEtablissement() != null && example.getEtablissement().getId() != 0) {
			criteria.createAlias("etablissement", "etablissement");
			criteria.add(Restrictions.eq("etablissement.id", example.getEtablissement().getId()));
		}
		criteria.createAlias("situation", "situation", CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("situation.situation", "situationCode", CriteriaSpecification.LEFT_JOIN);
		criteria.setFetchMode("situation", FetchMode.SELECT);
		criteria.setFetchMode("situation.situation.situationEntites", FetchMode.SELECT);
		criteria.setFetchMode("situation.situation.i18n", FetchMode.SELECT);
		criteria.setFetchMode("situation.situationEntiteOccurrences", FetchMode.SELECT);
		return criteria;
	}

	@Override
	public EvaluationPeriode findLastPeriodeEvaluation(int etablissementId) {
		RefEtablissement etablissement = new RefEtablissement();
		etablissement.setId(etablissementId);
		EvaluationPeriode periode = new EvaluationPeriode();
		periode.setEtablissement(etablissement);
		Criteria criteria = this.getCriteria(periode);
		criteria.setMaxResults(1);
		criteria.add(Restrictions.ne("situationCode.code", UtilConstant.SITUTAION_CLOTUREE_CODE));
		criteria.addOrder(Order.desc("dateFinPeriode"));
		return (EvaluationPeriode) criteria.uniqueResult();
	}

}
