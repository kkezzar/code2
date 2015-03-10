package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.CandidatEmployeDao;
import dz.gov.mesrs.sii.commons.data.model.grh.BesoinRecrutement;
import dz.gov.mesrs.sii.commons.data.model.grh.CandidatEmploye;
import dz.gov.mesrs.sii.commons.data.model.grh.DetailRecrutement;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation DAO CandidatEmploye
 * 
 */

@Repository("candidatEmployeDao")
public class CandidatEmployeDaoImpl extends CommonDaoImpl<CandidatEmploye, Integer> implements CandidatEmployeDao {

	@Override
	protected Class<CandidatEmploye> getDomainClass() {
		return CandidatEmploye.class;
	}

	@Override
	protected Criteria getCriteria(CandidatEmploye example) {
		// Criteria criteria = getSession().createCriteria(example.getClass());
		Criteria criteria = super.getCriteria(example);
		DetailRecrutement detailRecrutement = example.getDetailRecrutement();
		if (detailRecrutement != null) {
			criteria.createAlias("detailRecrutement", "detailRecrutement");
			if (detailRecrutement.getId() != null) {
				criteria.add(Restrictions.eq("detailRecrutement..id", detailRecrutement.getId()));
			} else {
				BesoinRecrutement besoinRecrutement = detailRecrutement.getBesoinRecrutement();
				if (besoinRecrutement != null) {

					if (besoinRecrutement.getId() != null) {
						criteria.add(Restrictions.eq("detailRecrutement.besoinRecrutement.id",
								besoinRecrutement.getId()));
					}
				}
			}
		}
		return criteria;
	}

}
