package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.FicheBesoinsDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.FicheBesoins;

/**
 * Dao Implementation for domain model class FicheBesoins.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.FicheBesoins
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("ficheBesoinsDao")
@Transactional
public class FicheBesoinsDaoImpl extends CommonDaoImpl<FicheBesoins, Integer> implements FicheBesoinsDao {

	@Override
	protected Class<FicheBesoins> getDomainClass() {
		return FicheBesoins.class;

	}

	@Override
	protected Criteria getCriteria(FicheBesoins example) {
		Criteria criteria = super.getCriteria(example);

		if (example.getEtablissement() != null && example.getEtablissement().getId() != 0) {
			criteria.createAlias("etablissement", "etablissement");
			criteria.add(Restrictions.eq("etablissement", example.getEtablissement()));
		}
		if (example.getProjetBudget() != null && example.getProjetBudget().getId() != null) {
			criteria.createAlias("projetBudget", "projetBudget");
			criteria.add(Restrictions.eq("projetBudget", example.getProjetBudget()));
		}

		return criteria;
	}

	/**
	 * Consolider la fiche des besoins
	 * 
	 * @author Mounir.MESSAOUDI on : 23 déc. 2014 14:27:24
	 * @param ficheBesoins
	 * @return
	 */
	@Override
	public FicheBesoins consoliderFicheBesoins(FicheBesoins ficheBesoins) {

		// supprimer l'ancien consolidations !
		// ficheBesoins.setConsolidationBesoins(null);
		// ficheBesoins = save(ficheBesoins);

		// inserer les consolidations a partir du detail de la fiche des besoins
		String queryString = "INSERT into gfc.consolidation_besoins (id_fiche_besoins, id_chapitre, id_article, montant_demande)  "
				+ "SELECT id_fiche_besoins, id_chapitre, id_article, sum(montant) "
				+ "FROM gfc.details_fiche_besoins "
				+ "WHERE id_fiche_besoins = :id_fiche_besoins GROUP BY id_chapitre, id_article, id_fiche_besoins;";

		SQLQuery query = getSession().createSQLQuery(queryString);
		query.setParameter("id_fiche_besoins", ficheBesoins.getId());
		query.executeUpdate();
		getSession().flush();
		getSession().clear();
		return findById(ficheBesoins.getId());
	}

	@Override
	public List<Object[]> getTotalOfFicheBesoins(FicheBesoins ficheBesoinsDto) {
		Query query = getSession()
				.createSQLQuery(
						"select id_structure,recette_type,total_montant from gfc.v_fiche_besoins_total where id_fiche_besoins = :id_fiche_besoins");
		query.setParameter("id_fiche_besoins", ficheBesoinsDto.getId());
		return query.list();

	}

	@Override
	public List<Object[]> getTotalOfConsolidationBesoins(FicheBesoins ficheBesoinsDto) {
		Query query = getSession()
				.createSQLQuery(
						"select recette_type,total_montant_demande,total_montant_propose from gfc.v_consolidation_besoins_total where id_fiche_besoins = :id_fiche_besoins");
		query.setParameter("id_fiche_besoins", ficheBesoinsDto.getId());
		return query.list();

	}
}