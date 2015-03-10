package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.DetailsProjetBudgetEtablissementDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.DetailsFicheBesoins;
import dz.gov.mesrs.sii.commons.data.model.gfc.DetailsProjetBudgetEtablissement;

/**
 * Dao Implementation for domain model class DetailsProjetBudget.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.DetailsProjetBudget
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("detailsProjetBudgetEtablissementDao")
@Transactional
public class DetailsProjetBudgetEtablissementDaoImpl extends
		CommonDaoImpl<DetailsProjetBudgetEtablissement, Integer> implements
		DetailsProjetBudgetEtablissementDao {

	@Override
	protected Class<DetailsProjetBudgetEtablissement> getDomainClass() {
		return DetailsProjetBudgetEtablissement.class;

	}

	@Override
	protected Criteria getCriteria(DetailsProjetBudgetEtablissement example) {
		Criteria criteria = super.getCriteria(example);

		if (example.getEtablissement() != null && example.getEtablissement().getId() != 0) {
			criteria.createAlias("etablissement", "etablissement");
			criteria.add(Restrictions.eq("etablissement", example.getEtablissement()));
		}

		

		if (example.getProjetBudget() != null && example.getProjetBudget().getId() != null) {
			criteria.createAlias("projetBudget", "projetBudget");
			criteria.add(Restrictions.eq("projetBudget", example.getProjetBudget()));
		}

		// type de chapitre recette/depense
		if (example.getChapitre() != null && example.getChapitre().getRecetteType() != null) {
			criteria.createAlias("chapitre", "chapitre");
			criteria.add(Restrictions.eq("chapitre.recetteType", example.getChapitre().getRecetteType()));
		}

		return criteria;
	}

}