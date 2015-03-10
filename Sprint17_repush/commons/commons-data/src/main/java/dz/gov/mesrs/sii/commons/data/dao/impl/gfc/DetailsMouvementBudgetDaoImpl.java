package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.DetailsMouvementBudgetDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.DetailsMouvementBudget;

/**
 * Dao Implementation for domain model class DetailsMouvementBudget.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.DetailsMouvementBudget
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("detailsMouvementBudgetDao")
@Transactional
public class DetailsMouvementBudgetDaoImpl extends
		CommonDaoImpl<DetailsMouvementBudget, Integer> implements
		DetailsMouvementBudgetDao {

	@Override
	protected Class<DetailsMouvementBudget> getDomainClass() {
		return DetailsMouvementBudget.class;

	}

}