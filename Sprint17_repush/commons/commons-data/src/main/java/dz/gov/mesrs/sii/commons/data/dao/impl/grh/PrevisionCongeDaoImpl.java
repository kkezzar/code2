package dz.gov.mesrs.sii.commons.data.dao.impl.grh;


import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.commons.data.model.grh.PrevisionConge;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.dao.grh.PrevisionCongeDao;

/**
 * 
 * @author BELDI Jamel
 *
 */

@Repository ("previsionCongeDao")  
public class PrevisionCongeDaoImpl extends CommonDaoImpl<PrevisionConge, Long> implements PrevisionCongeDao {
	
	@Override
	protected Class<PrevisionConge> getDomainClass() {
		return PrevisionConge.class;
	}
	protected Criteria getCriteria(PrevisionConge example) {
		//Criteria criteria = getSession().createCriteria(example.getClass());
	 Criteria criteria = super.getCriteria(example);
		DossierEmploye dossierEmploye = example.getDossierEmploye();
		if (dossierEmploye != null) {
			criteria.createAlias("dossierEmploye", "dossierEmploye");
			if (dossierEmploye.getId() != 0) {
				criteria.add(Restrictions.disjunction().add(
						Restrictions.eq("dossierEmploye.id", dossierEmploye.getId())));
			}
		}
		return criteria;
	}

}
