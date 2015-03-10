package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.CongeEmployeDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.grh.CongeEmploye;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
/**
 * 
 * @author BELDI Jamel
 *
 */
@Repository("congeEmployeDao")
public class CongeEmployeDaoImpl extends CommonDaoImpl<CongeEmploye, Long>
		implements CongeEmployeDao {

	@Override
	protected Class<CongeEmploye> getDomainClass() {
		return CongeEmploye.class;
	}
	
	protected Criteria getCriteria(CongeEmploye example) {
		//Criteria criteria = getSession().createCriteria(example.getClass());
	 Criteria criteria = super.getCriteria(example);
		DossierEmploye dossierEmploye = example.getDossierEmploye();
		if (dossierEmploye != null) {
			criteria.createAlias("dossierEmploye", "dossierEmploye");
			if (dossierEmploye.getId()!= null && dossierEmploye.getId() != 0) {
				criteria.add(Restrictions.disjunction().add(
						Restrictions.eq("dossierEmploye.id", dossierEmploye.getId())));
			}
		}
		
		SituationEntite situationEntite = example.getSituationEntite();
		
		if (situationEntite != null) {
			criteria.createAlias("situationEntite", "situationEntite");
			if (situationEntite.getId() != 0) {
				criteria.add(Restrictions.disjunction().add(
						Restrictions.eq("situationEntite.id", situationEntite.getId())));
			}
		}
		return criteria;
	}

	
	@Override
	protected Criteria getCriteriaForKeyWord(String keyWord) {
		Criteria criteria = getSession().createCriteria(CongeEmploye.class);
		
		
		criteria.createAlias("dossierEmploye", "dossierEmploye" ,CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("dossierEmploye.refIndividu", "refIndividu",  CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("dossierEmploye.refEtablissement", "refEtablissement", CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("nomenclatureByTypeConge", "nomenclatureByTypeConge", CriteriaSpecification.LEFT_JOIN);
		criteria.setFetchMode("nomenclatureByTypeConge", FetchMode.SELECT);
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
			        .add(Restrictions.like("nomenclatureByTypeConge.libelleLongFr", word, MatchMode.ANYWHERE).ignoreCase())
			       .add(Restrictions.like("nomenclatureByTypeConge.libelleCourtFr", word, MatchMode.ANYWHERE).ignoreCase())
			      .add(Restrictions.like("nomenclatureByTypeConge.code", word, MatchMode.ANYWHERE).ignoreCase()));
			
		}
		
		return criteria;
	
	}
	
}
