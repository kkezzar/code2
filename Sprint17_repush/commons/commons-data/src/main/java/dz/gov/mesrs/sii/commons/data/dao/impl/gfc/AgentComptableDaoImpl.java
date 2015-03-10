package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.AgentComptableDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.AgentComptable;

/**
 * Dao Implementation for domain model class AgentComptable.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.AgentComptable
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("agentComptableDao")
@Transactional
public class AgentComptableDaoImpl extends
		CommonDaoImpl<AgentComptable, Integer> implements AgentComptableDao {

	@Override
	protected Class<AgentComptable> getDomainClass() {
		return AgentComptable.class;

	}

}