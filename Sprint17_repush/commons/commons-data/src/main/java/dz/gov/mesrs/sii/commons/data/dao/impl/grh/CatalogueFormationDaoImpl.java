package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.CatalogueFormationDao;
import dz.gov.mesrs.sii.commons.data.model.grh.CatalogueFormation;

@Repository("catalogueFormationDao")
public class CatalogueFormationDaoImpl extends CommonDaoImpl<CatalogueFormation, Integer> implements CatalogueFormationDao {

	@Override
	protected Class<CatalogueFormation> getDomainClass() {
		return CatalogueFormation.class;
	}

	@Override
	protected Criteria getCriteriaForKeyWord(String keyWord) {
		Criteria criteria = getSession().createCriteria(CatalogueFormation.class);
		String[] words = keyWord.split(" ");
		for (String word : words) {
			criteria.add(Restrictions.disjunction()
			        .add(Restrictions.like("objet", word, MatchMode.ANYWHERE).ignoreCase()));
		}
		criteria.addOrder(Order.desc("dateCreation"));
		return criteria;
	
	}
	
	
}
