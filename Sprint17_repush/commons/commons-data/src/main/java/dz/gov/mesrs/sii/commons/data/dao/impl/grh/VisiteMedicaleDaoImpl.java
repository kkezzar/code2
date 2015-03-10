package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.VisiteMedicaleDao;
import dz.gov.mesrs.sii.commons.data.model.grh.VisiteMedicale;

@Repository("visiteMedicaleDao")
public class VisiteMedicaleDaoImpl extends CommonDaoImpl<VisiteMedicale, Long> implements VisiteMedicaleDao {

	@Override
	protected Class<VisiteMedicale> getDomainClass() {
		return VisiteMedicale.class;
	}

	
	@Override
	protected Criteria getCriteria(VisiteMedicale example) {
		Criteria criteria = super.getCriteria(example);
		if (example.getDossierEmploye() != null) {
			criteria.createAlias("dossierEmploye", "employe");
			criteria.add(Restrictions.eq("employe.id", example.getDossierEmploye().getId()));
			if (example.getDossierEmploye().getRefIndividu()  != null) {
				criteria.createAlias("employe.refIndividu", "individu"
				);
				criteria.add(Restrictions.eq("individu.id", example.getDossierEmploye().getRefIndividu().getId()));
			}
		}
		criteria.addOrder(Order.desc("dateRdv"));
		
		
		return criteria;
	}

	@Override
	protected Criteria getCriteriaForKeyWord(String keyWord) {
		Criteria criteria = getSession().createCriteria(VisiteMedicale.class);
		
		
		criteria.createAlias("dossierEmploye", "dossierEmploye" ,CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("dossierEmploye.refIndividu", "refIndividu",  CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("dossierEmploye.refEtablissement", "refEtablissement", CriteriaSpecification.LEFT_JOIN);
		criteria.setFetchMode("dossierEmploye", FetchMode.SELECT);
		if (StringUtils.isEmpty(keyWord)) {
			return criteria;
		}

		String[] words = keyWord.split(" ");
		for (String word : words) {
			criteria.add(Restrictions.disjunction()
					.add(Restrictions.like("dossierEmploye.matricule", word, MatchMode.ANYWHERE).ignoreCase())
					.add(Restrictions.like("refIndividu.nomLatin", word, MatchMode.ANYWHERE).ignoreCase())
					.add(Restrictions.like("refIndividu.prenomLatin", word, MatchMode.ANYWHERE).ignoreCase())
			        .add(Restrictions.like("objet", word, MatchMode.ANYWHERE).ignoreCase())
			       .add(Restrictions.like("diagnostic", word, MatchMode.ANYWHERE).ignoreCase())
			      .add(Restrictions.like("motifRefus", word, MatchMode.ANYWHERE).ignoreCase()));
			
		}
		criteria.addOrder(Order.desc("dateRdv"));
		return criteria;
	
	}
	
}
