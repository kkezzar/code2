package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.AffectationEtabStrAgentComptableDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.AffectationEtabStrAgentComptable;

/**
 * Dao Implementation for domain model class AffectationEtabStrAgentComptable.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.AffectationEtabStrAgentComptable
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("affectationEtabStrAgentComptableDao")
@Transactional
public class AffectationEtabStrAgentComptableDaoImpl extends
		CommonDaoImpl<AffectationEtabStrAgentComptable, Integer> implements
		AffectationEtabStrAgentComptableDao {

	@Override
	protected Class<AffectationEtabStrAgentComptable> getDomainClass() {
		return AffectationEtabStrAgentComptable.class;

	}

}