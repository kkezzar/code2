package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.VaccinDao;
import dz.gov.mesrs.sii.commons.data.model.grh.Vaccin;

@Repository("vaccinDao")
public class VaccinDaoImpl extends CommonDaoImpl<Vaccin, Integer> implements VaccinDao {

	@Override
	protected Class<Vaccin> getDomainClass() {
		return Vaccin.class;
	}

	
	@Override
	protected Criteria getCriteria(Vaccin example) {
		Criteria criteria = super.getCriteria(example);
		if (example.getDossierEmploye() != null) {
			criteria.createAlias("dossierEmploye", "employe", CriteriaSpecification.LEFT_JOIN);
			criteria.add(Restrictions.eq("employe.id", example.getDossierEmploye().getId()));
		}
		
		criteria.addOrder(Order.desc("dateVaccin"));
		
		return criteria;
	}

}
