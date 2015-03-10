package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.RegieDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.Regie;

/**
 * Dao Implementation for domain model class Regie.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.Regie
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("regieDao")
@Transactional
public class RegieDaoImpl extends CommonDaoImpl<Regie, Integer> implements
		RegieDao {

	@Override
	protected Class<Regie> getDomainClass() {
		return Regie.class;

	}
	@Override
	protected Criteria getCriteriaForKeyWord(String keyWord) {
		Criteria criteria = getSession().createCriteria(Regie.class);
//
	criteria.createAlias("typeRegie", "typeRegie");
//		criteria.createAlias("section", "mode");

		criteria.add(Restrictions.disjunction()
				
				.add(Restrictions.like("objetFr", keyWord, MatchMode.ANYWHERE).ignoreCase())
				.add(Restrictions.like("objetAr", keyWord, MatchMode.ANYWHERE).ignoreCase())
		
		.add(Restrictions.like("typeRegie.libelleLongFr", keyWord, MatchMode.ANYWHERE).ignoreCase()));
		return criteria;
	}


}