package dz.gov.mesrs.sii.commons.data.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.EvaluationEmployeDao;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.commons.data.model.grh.EvaluationEmploye;
import dz.gov.mesrs.sii.commons.data.model.grh.EvaluationPeriode;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;

@Repository
public class EvaluationEmployeDaoImpl extends CommonDaoImpl<EvaluationEmploye, Long> implements EvaluationEmployeDao {

	@Override
	protected Class<EvaluationEmploye> getDomainClass() {
		return EvaluationEmploye.class;
	}

	@Override
	protected Criteria getCriteria(EvaluationEmploye example) {
		Criteria criteria = super.getCriteria(example);
		DossierEmploye employe = example.getEmploye();
		if (employe != null) {
			criteria.createAlias("employe", "employe");
			if (!StringUtils.isEmpty(employe.getMatricule())) {
				criteria.add(Restrictions.like("employe.matricule", employe.getMatricule(), MatchMode.ANYWHERE));
			}
			RefIndividu individu = employe.getRefIndividu();
			if (individu != null) {
				criteria.createAlias("employe.refIndividu", "individu");
				if (!StringUtils.isEmpty(individu.getNomLatin())) {
					criteria.add(Restrictions.like("individu.nomLatin", individu.getNomLatin(), MatchMode.ANYWHERE));
				}
				if (!StringUtils.isEmpty(individu.getPrenomLatin())) {
					criteria.add(Restrictions.like("individu.prenomLatin", individu.getPrenomLatin(),
							MatchMode.ANYWHERE));
				}
			}
		}
		EvaluationPeriode periode = example.getPeriode();
		if (periode != null) {
			criteria.createAlias("periode", "periode");
			if (periode.getId() != null) {
				criteria.add(Restrictions.eq("periode.id", periode.getId()));
			}
		}
		return criteria;
	}
}
