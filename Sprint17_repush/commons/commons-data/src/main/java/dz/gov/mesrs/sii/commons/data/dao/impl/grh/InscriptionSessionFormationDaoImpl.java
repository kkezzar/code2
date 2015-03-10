package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.InscriptionSessionFormationDao;
import dz.gov.mesrs.sii.commons.data.model.grh.InscriptionSessionFormation;





@Repository("inscriptionSessionFormationDao")
public class InscriptionSessionFormationDaoImpl extends CommonDaoImpl<InscriptionSessionFormation, Long> implements InscriptionSessionFormationDao {

	@Override
	protected Class<InscriptionSessionFormation> getDomainClass() {
		return InscriptionSessionFormation.class;
	}

	@Override
	protected Criteria getCriteriaForKeyWord(String keyWord) {
		Criteria criteria = getSession().createCriteria(InscriptionSessionFormation.class);
		String[] words = keyWord.split(" ");
		for (String word : words) {
//			criteria.add(Restrictions.disjunction()
//			        .add(Restrictions.like("intitule", word, MatchMode.ANYWHERE).ignoreCase()));
		}
		criteria.addOrder(Order.desc("dateDemande"));
		return criteria;
	
	}
	
	
}
