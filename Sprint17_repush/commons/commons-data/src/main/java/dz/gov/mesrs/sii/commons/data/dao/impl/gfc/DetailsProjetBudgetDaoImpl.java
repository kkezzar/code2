package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.DetailsProjetBudgetDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.DetailsProjetBudget;

/**
 * Dao Implementation for domain model class DetailsProjetBudget.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.DetailsProjetBudget
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("detailsProjetBudgetDao")
@Transactional
public class DetailsProjetBudgetDaoImpl extends
		CommonDaoImpl<DetailsProjetBudget, Integer> implements
		DetailsProjetBudgetDao {

	@Override
	protected Class<DetailsProjetBudget> getDomainClass() {
		return DetailsProjetBudget.class;

	}

}