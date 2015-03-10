package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.BesoinFormationDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.grh.BesoinFormation;
import dz.gov.mesrs.sii.commons.data.model.grh.CongeEmploye;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefGroupe;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefStructure;

@Repository("besoinFormationDao")
public class BesoinFormationDaoImpl extends CommonDaoImpl<BesoinFormation, Integer> implements BesoinFormationDao {

	@Override
	protected Class<BesoinFormation> getDomainClass() {
		return BesoinFormation.class;
	}

	@Override
	protected Criteria getCriteriaForKeyWord(String keyWord) {
		Criteria criteria = getSession().createCriteria(BesoinFormation.class);
		String[] words = keyWord.split(" ");
		for (String word : words) {
			criteria.add(Restrictions.disjunction()
			        .add(Restrictions.like("objectif", word, MatchMode.ANYWHERE).ignoreCase()));
		}
		criteria.addOrder(Order.desc("dateDebut"));
		return criteria;
	
	}
	
	protected Criteria getCriteria(BesoinFormation example) {
	 Criteria criteria = super.getCriteria(example);
	    RefEtablissement refEtablissement = example.getRefEtablissement();
		DossierEmploye dossierEmploye = example.getDossierEmploye();
		Nomenclature typeBesoin = example.getNomenclature();
		RefStructure refStructure = example.getRefStructure();
		RefGroupe refGroupe = example.getRefGroupe();
		if (refEtablissement != null) {
			criteria.createAlias("refEtablissement", "refEtablissement");
			if (refEtablissement.getId() != Long.valueOf(0)) {
				criteria.add(Restrictions.disjunction().add(
						Restrictions.eq("refEtablissement.id", refEtablissement.getId())));
			}
		}
		if (dossierEmploye != null) {
			criteria.createAlias("dossierEmploye", "dossierEmploye");
			if (dossierEmploye.getId()!=null && dossierEmploye.getId()!= Long.valueOf(0)) {
				criteria.add(Restrictions.disjunction().add(
						Restrictions.eq("dossierEmploye.id", dossierEmploye.getId())));
			}
		}
		if (typeBesoin != null) {
			criteria.createAlias("nomenclature", "typeBesoin");
			if (typeBesoin.getId() != 0) {
				criteria.add(Restrictions.disjunction().add(
						Restrictions.eq("typeBesoin.id", typeBesoin.getId())));
			}
		}
		if (refStructure != null) {
			criteria.createAlias("refStructure", "refStructure");
			if (refStructure.getId() != 0) {
				criteria.add(Restrictions.disjunction().add(
						Restrictions.eq("refStructure.id", refStructure.getId())));
			}
		}
		
		if (refGroupe != null) {
			criteria.createAlias("refGroupe", "refGroupe");
			if (refGroupe.getId() != 0) {
				criteria.add(Restrictions.disjunction().add(
						Restrictions.eq("refGroupe.id", refGroupe.getId())));
			}
		}
		return criteria;
	}
}
