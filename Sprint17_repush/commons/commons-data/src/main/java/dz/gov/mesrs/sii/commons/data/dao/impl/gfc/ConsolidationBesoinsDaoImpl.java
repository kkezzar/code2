package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.ConsolidationBesoinsDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.ConsolidationBesoins;

/**
 * Dao Implementation for domain model class ConsolidationBesoins.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.ConsolidationBesoins
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("consolidationBesoinsDao")
@Transactional
public class ConsolidationBesoinsDaoImpl extends
		CommonDaoImpl<ConsolidationBesoins, Integer> implements
		ConsolidationBesoinsDao {

	@Override
	protected Class<ConsolidationBesoins> getDomainClass() {
		return ConsolidationBesoins.class;

	}

}