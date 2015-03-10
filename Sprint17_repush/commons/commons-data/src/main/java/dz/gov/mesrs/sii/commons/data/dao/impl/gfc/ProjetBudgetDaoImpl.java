package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.ProjetBudgetDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.ProjetBudget;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;

/**
 * Dao Implementation for domain model class ProjetBudget.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.ProjetBudget
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("projetBudgetDao")
@Transactional
public class ProjetBudgetDaoImpl extends CommonDaoImpl<ProjetBudget, Integer> implements ProjetBudgetDao {

	@Override
	protected Class<ProjetBudget> getDomainClass() {
		return ProjetBudget.class;

	}

	/**
	 * Consolider les consolidations de tous les fiches des besoins
	 * 
	 * @author Mounir.MESSAOUDI on : 23 dÃ©c. 2014 14:27:24
	 * @param projetBudget
	 * @return
	 */
	@Override
	public Boolean consoliderProjetBudget(ProjetBudget projetBudget) {
		// supprimer l'ancien detail !
		// projetBudget.setDetailsProjetBudgets(null);
		// projetBudget = save(projetBudget);

		// inserer les consolidations a partir des consolidations des fiches des
		// besoins

		// String queryString =
		// "INSERT into gfc.details_projet_budget (id_projet_budget, id_chapitre, id_article, montant_besoins) "
		// +
		// "SELECT :id_projet_budget as idpro, id_chapitre, id_article, sum(montant_propose) "
		// + "FROM gfc.consolidation_besoins GROUP BY id_chapitre, id_article;";

		// String queryString =
		// "INSERT into gfc.details_projet_budget (id_projet_budget, id_etablissement, id_chapitre, montant_besoins) "
		// +
		// "SELECT :id_projet_budget as idpro, cc.id_etablissement, id_chapitre, sum(montant_propose) "
		// + "FROM gfc.consolidation_besoins, gfc.fiche_besoins cc "
		// + "WHERE gfc.consolidation_besoins.id_fiche_besoins = cc.id "
		// + "GROUP BY id_chapitre, id_etablissement;";

		// consolider le budget par chapitre
		String queryString = "INSERT into gfc.details_projet_budget (id_projet_budget, id_chapitre, montant_besoins) "
				+ "SELECT :id_projet_budget as idpro, id_chapitre, sum(montant_propose) "
				+ "FROM gfc.consolidation_besoins GROUP BY id_chapitre;";

		// consolider
		SQLQuery query = getSession().createSQLQuery(queryString);
		query.setParameter("id_projet_budget", projetBudget.getId());
		int result = query.executeUpdate();
		getSession().flush();
		getSession().clear();

		queryString = "INSERT into gfc.details_projet_budget_etablissement (id_projet_budget, id_etablissement, id_chapitre, montant_besoins) "
				+ "SELECT :id_projet_budget as idpro, cc.id_etablissement, id_chapitre, sum(montant_propose) "
				+ "FROM gfc.consolidation_besoins, gfc.fiche_besoins cc "
				+ "WHERE gfc.consolidation_besoins.id_fiche_besoins = cc.id "
				+ "GROUP BY id_chapitre,cc.id_etablissement";
		query = getSession().createSQLQuery(queryString);
		query.setParameter("id_projet_budget", projetBudget.getId());
		query.executeUpdate();
		getSession().flush();
		getSession().clear();

		if (result == 1)
			return Boolean.TRUE;
		else
			return Boolean.FALSE;

	}

	@Override
	public List<Object[]> getTotalOfDetailsProjetBudget(ProjetBudget projetBudget) {
		Query query = getSession().createSQLQuery(
				"select recette_type,total_montant_besoins,montant_final "
						+ "from gfc.v_details_projet_budget_total where id_projet_budget = :id_projet_budget");
		query.setParameter("id_projet_budget", projetBudget.getId());
		return query.list();
	}

	@Override
	public List<Object[]> getTotalOfDetailsProjetBudgetByEtab(ProjetBudget projetBudget, RefEtablissement etablissement) {
		Query query = getSession()
				.createSQLQuery(
						"select recette_type,total_montant_besoins,montant_final "
								+ "from gfc.v_details_projet_budget_total where id_projet_budget = :id_projet_budget and id_etablissement =:id_etablissement");
		query.setParameter("id_projet_budget", projetBudget.getId());
		query.setParameter("id_etablissement", etablissement.getId());
		return query.list();
	}

	@Override
	public List<Object[]> getAllTotalOfDetailsProjetBudgetByEtab(ProjetBudget projetBudget) {
		Query query = getSession().createSQLQuery(
				"select recette_type,total_montant_besoins,montant_final "
						+ "from gfc.v_details_projet_budget_total where id_projet_budget = :id_projet_budget");
		query.setParameter("id_projet_budget", projetBudget.getId());
		return query.list();
	}
}