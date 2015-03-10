package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.ChangementPositionDao;
import dz.gov.mesrs.sii.commons.data.model.grh.ChangementPosition;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;


@Repository("changementPositionDao")
public class ChangementPositionDaoImpl extends CommonDaoImpl<ChangementPosition, Long> implements
		ChangementPositionDao {

	@Override
	protected Class<ChangementPosition> getDomainClass() {
		return ChangementPosition.class;
	}

	@Override
	public List<DossierEmploye> findListEmployesParPosition(RefEtablissement refEtablissement,
			List<Nomenclature> listNomenclatureByPositionAgent) {
		List<DossierEmploye> listChangementPosition = new ArrayList<DossierEmploye>();
		if ((refEtablissement != null) && (listNomenclatureByPositionAgent != null)
				&& (!listNomenclatureByPositionAgent.isEmpty())) {
			Criteria criteria = getSession().createCriteria(ChangementPosition.class, "chgpos");
			criteria.createAlias("dossierEmploye", "dossierEmploye");
			criteria.add(Restrictions.eq("dossierEmploye.refEtablissement", refEtablissement));
			// Criterion rest1 =Restrictions.isNull("dateFin");
			// Criterion rest2 =Restrictions.isNull("dateFin");
			// criteria.add(Restrictions.or(rest1, rest2));
			if (listNomenclatureByPositionAgent != null)
				if (!listNomenclatureByPositionAgent.isEmpty()) {
					Collection<Integer> listIdnomenclatureByPosition = new ArrayList<Integer>();
					for (Nomenclature nomenclature : listNomenclatureByPositionAgent) {
						listIdnomenclatureByPosition.add(nomenclature.getId());
					}
					criteria.add(Restrictions.in("nomenclatureByPosition.id", listIdnomenclatureByPosition));
				}
			criteria.add(Restrictions.eq("acceptee", true));
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ChangementPosition.class, "chgposmaxdate");
			ProjectionList proj = Projections.projectionList();
			proj.add(Projections.distinct(Projections.max("dateDebut")));
			detachedCriteria.add(Property.forName("chgposmaxdate.dossierEmploye.id").eqProperty(
					"chgpos.dossierEmploye.id"));
			detachedCriteria.setProjection(proj);
			criteria.add(Property.forName("dateDebut").eq(detachedCriteria));
			criteria.setProjection(Projections.distinct(Projections.property("dossierEmploye")));
			listChangementPosition = criteria.list();
		}
		return listChangementPosition;
	}

	@Override
	public Nomenclature findPosition(Long id) {
		List<Nomenclature> listChangementPosition = new ArrayList<Nomenclature>();
		Criteria criteria = getSession().createCriteria(ChangementPosition.class, "chgpos");
		criteria.createAlias("dossierEmploye", "dossierEmploye");
		criteria.add(Property.forName("dossierEmploye.id").eq(id));
		criteria.add(Restrictions.eq("acceptee", true));
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ChangementPosition.class, "chgposmaxdate");
		ProjectionList proj = Projections.projectionList();
		proj.add(Projections.distinct(Projections.max("dateDebut")));
		detachedCriteria
				.add(Property.forName("chgposmaxdate.dossierEmploye.id").eqProperty("chgpos.dossierEmploye.id"));
		detachedCriteria.setProjection(proj);
		criteria.add(Property.forName("dateDebut").eq(detachedCriteria));
		criteria.setProjection(Projections.distinct(Projections.property("nomenclatureByPosition")));
		listChangementPosition = criteria.list();
		if (listChangementPosition != null) {
			if (!listChangementPosition.isEmpty()) {
				return listChangementPosition.get(0);
			} else {
				return new Nomenclature();
			}
		} else {
			return new Nomenclature();
		}
	}

	@Override
	protected Criteria getCriteriaForKeyWord(String keyWord) {
		Criteria criteria = getSession().createCriteria(ChangementPosition.class);
		criteria.createAlias("dossierEmploye", "employe", CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("dossierEmploye.refEtablissement", "etablissement", CriteriaSpecification.LEFT_JOIN);

		criteria.createAlias("situation", "situation", CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("situation.situation", "situationCode", CriteriaSpecification.LEFT_JOIN);
		criteria.setFetchMode("situation", FetchMode.SELECT);
		criteria.setFetchMode("situation.situation", FetchMode.SELECT);
		criteria.setFetchMode("situation.situation.situationEntites", FetchMode.SELECT);
		criteria.setFetchMode("situation.situation.i18n", FetchMode.SELECT);
		criteria.setFetchMode("situation.situationEntiteOccurrences", FetchMode.SELECT);
		
		
		criteria.createAlias("nomenclatureByMotif", "nomenclatureByMotif", CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("nomenclatureByPosition", "nomenclatureByPosition", CriteriaSpecification.LEFT_JOIN);
		criteria.setFetchMode("nomenclatureByPosition", FetchMode.SELECT);
		criteria.setFetchMode("nomenclatureByMotif", FetchMode.SELECT);
		
		criteria.createAlias("dossierEmploye.refIndividu", "individu", CriteriaSpecification.LEFT_JOIN);
		criteria.setFetchMode("dossierEmploye", FetchMode.SELECT);

		if (StringUtils.isEmpty(keyWord)) {
			return criteria;
		}

		String[] words = keyWord.split(" ");
		for (String word : words) {
			criteria.add(Restrictions.disjunction()
//					.add(Restrictions.like("employe.matricule", word, MatchMode.ANYWHERE).ignoreCase())
					.add(Restrictions.like("individu.nomLatin", word, MatchMode.ANYWHERE).ignoreCase())
					.add(Restrictions.like("individu.prenomLatin", word, MatchMode.ANYWHERE).ignoreCase()));

		}
		return criteria;
	}

}
