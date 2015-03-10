package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.DetailsFicheBesoinsDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.DetailsFicheBesoins;

/**
 * Dao Implementation for domain model class DetailsFicheBesoins.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.DetailsFicheBesoins
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("detailsFicheBesoinsDao")
@Transactional
public class DetailsFicheBesoinsDaoImpl extends CommonDaoImpl<DetailsFicheBesoins, Integer> implements
		DetailsFicheBesoinsDao {

	@Override
	protected Class<DetailsFicheBesoins> getDomainClass() {
		return DetailsFicheBesoins.class;

	}

	@Override
	protected Criteria getCriteria(DetailsFicheBesoins example) {
		Criteria criteria = super.getCriteria(example);

		if (example.getEtablissement() != null && example.getEtablissement().getId() != 0) {
			criteria.createAlias("etablissement", "etablissement");
			criteria.add(Restrictions.eq("etablissement", example.getEtablissement()));
		}

		if (example.getStructure() != null && example.getStructure().getId() != 0) {
			criteria.createAlias("structure", "structure");
			criteria.add(Restrictions.eq("structure", example.getStructure()));
		}

		if (example.getFicheBesoins() != null && example.getFicheBesoins().getId() != null) {
			criteria.createAlias("ficheBesoins", "ficheBesoins");
			criteria.add(Restrictions.eq("ficheBesoins", example.getFicheBesoins()));
		}

		// type de chapitre recette/depense
		if (example.getChapitre() != null && example.getChapitre().getRecetteType() != null) {
			criteria.createAlias("chapitre", "chapitre");
			criteria.add(Restrictions.eq("chapitre.recetteType", example.getChapitre().getRecetteType()));
		}

		return criteria;
	}

}