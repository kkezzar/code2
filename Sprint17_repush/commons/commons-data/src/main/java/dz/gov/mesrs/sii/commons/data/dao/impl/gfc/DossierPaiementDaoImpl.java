package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.DossierPaiementDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.DossierPaiement;

/**
 * Dao Implementation for domain model class DossierPaiement.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.DossierPaiement
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("dossierPaiementDao")
@Transactional
public class DossierPaiementDaoImpl extends CommonDaoImpl<DossierPaiement, Integer> implements DossierPaiementDao {

	@Override
	protected Class<DossierPaiement> getDomainClass() {
		return DossierPaiement.class;
	}

	@Override
	protected Criteria getCriteria(DossierPaiement example) {
		Criteria criteria = super.getCriteria(example);

		if (example.getSituation() != null && example.getSituation().getId() != 0) {
			criteria.createAlias("situation", "situation");
			criteria.add(Restrictions.eq("situation.id", example.getSituation().getId()));
		}

		return criteria;
	}
}