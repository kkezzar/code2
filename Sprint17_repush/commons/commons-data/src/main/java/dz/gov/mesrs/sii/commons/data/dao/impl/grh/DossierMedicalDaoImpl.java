package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.DossierMedicalDao;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierMedical;

@Repository("dossierMedicalDao")
public class DossierMedicalDaoImpl extends CommonDaoImpl<DossierMedical, Integer> implements DossierMedicalDao {

	@Override
	protected Class<DossierMedical> getDomainClass() {
		return DossierMedical.class;
	}

	
	@Override
	protected Criteria getCriteria(DossierMedical example) {
		Criteria criteria = super.getCriteria(example);
		if (example.getDossierEmploye() != null) {
			criteria.createAlias("dossierEmploye", "employe", CriteriaSpecification.LEFT_JOIN);
			criteria.add(Restrictions.eq("employe.id", example.getDossierEmploye().getId()));
		}
		
		criteria.addOrder(Order.desc("dateSurvenue"));
		
		return criteria;
	}

}
