package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.ProgrammeNationalDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.ProgrammeNational;

/**
 * Dao Implementation for domain model class ProgrammeNational.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.ProgrammeNational
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("programmeNationalDao")
@Transactional
public class ProgrammeNationalDaoImpl extends
		CommonDaoImpl<ProgrammeNational, Integer> implements
		ProgrammeNationalDao {

	@Override
	protected Class<ProgrammeNational> getDomainClass() {
		return ProgrammeNational.class;

	}

}