package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.DetailsMouvementBudgetStructureDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.DetailsMouvementBudgetStructure;

/**
 * Dao Implementation for domain model class DetailsMouvementBudgetStructure.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.DetailsMouvementBudgetStructure
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 25/02/2014 11:54:25
 */
@Repository("detailsMouvementBudgetStructureDao")
@Transactional
public class DetailsMouvementBudgetStructureDaoImpl extends CommonDaoImpl<DetailsMouvementBudgetStructure, Integer>
		implements DetailsMouvementBudgetStructureDao {

	@Override
	protected Class<DetailsMouvementBudgetStructure> getDomainClass() {
		return DetailsMouvementBudgetStructure.class;

	}

}