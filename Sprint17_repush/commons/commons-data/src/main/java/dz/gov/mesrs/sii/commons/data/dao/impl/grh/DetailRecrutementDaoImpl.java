package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.DetailRecrutementDao;
import dz.gov.mesrs.sii.commons.data.model.grh.BesoinRecrutement;
import dz.gov.mesrs.sii.commons.data.model.grh.DetailRecrutement;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation DAO DetailRecrutement
 * 
 */

@Repository("detailRecrutementDao")
@Transactional
public class DetailRecrutementDaoImpl extends CommonDaoImpl<DetailRecrutement, Integer> implements DetailRecrutementDao {

	@Override
	protected Class<DetailRecrutement> getDomainClass() {
		return DetailRecrutement.class;
	}

	@Override
	protected Criteria getCriteria(DetailRecrutement example) {
		// Criteria criteria = getSession().createCriteria(example.getClass());
		Criteria criteria = super.getCriteria(example);
		BesoinRecrutement besoinRecrutement = example.getBesoinRecrutement();
		if (besoinRecrutement != null) {
			criteria.createAlias("besoinRecrutement", "besoinRecrutement");
			if (besoinRecrutement.getId() != null) {
				criteria.add(Restrictions.eq("besoinRecrutement.id", besoinRecrutement.getId()));
			}
		}

		return criteria;
	}
}
