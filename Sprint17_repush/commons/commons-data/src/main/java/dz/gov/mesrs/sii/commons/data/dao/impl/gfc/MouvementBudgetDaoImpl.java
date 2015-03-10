package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.MouvementBudgetDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.MouvementBudget;

/**
 * Dao Implementation for domain model class MouvementBudget.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.MouvementBudget
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("mouvementBudgetDao")
@Transactional
public class MouvementBudgetDaoImpl extends
		CommonDaoImpl<MouvementBudget, Integer> implements MouvementBudgetDao {

	@Override
	protected Class<MouvementBudget> getDomainClass() {
		return MouvementBudget.class;

	}

}