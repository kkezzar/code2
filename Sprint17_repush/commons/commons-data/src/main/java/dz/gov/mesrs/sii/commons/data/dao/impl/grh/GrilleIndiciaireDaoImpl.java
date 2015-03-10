package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.GrilleIndiciaireDao;
import dz.gov.mesrs.sii.commons.data.model.grh.GrilleIndiciaire;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation DAO GrilleIndiciaire
 * 
 */

@Repository("grilleIndiciaireDao")
public class GrilleIndiciaireDaoImpl extends CommonDaoImpl<GrilleIndiciaire, Integer> implements GrilleIndiciaireDao {

	@Override
	protected Class<GrilleIndiciaire> getDomainClass() {
		return GrilleIndiciaire.class;
	}

	@Override
	protected Criteria getCriteria(GrilleIndiciaire example) {
		Criteria criteria = super.getCriteria(example);
		if (example.getCategorieProfessionnelle() != null) {
			criteria.createAlias("categorieProfessionnelle", "categorieProfessionnelle");
			criteria.add(Restrictions.eq("categorieProfessionnelle", example.getCategorieProfessionnelle()));

		}
		return criteria;
	}
}
