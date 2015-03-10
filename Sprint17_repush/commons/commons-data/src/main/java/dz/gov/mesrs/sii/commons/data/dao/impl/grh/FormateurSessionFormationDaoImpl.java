package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.FormateurSessionFormationDao;
import dz.gov.mesrs.sii.commons.data.model.grh.FormateurSessionFormation;





@Repository("formateurSessionFormationDao")
public class FormateurSessionFormationDaoImpl extends CommonDaoImpl<FormateurSessionFormation, Long> implements FormateurSessionFormationDao {

	@Override
	protected Class<FormateurSessionFormation> getDomainClass() {
		return FormateurSessionFormation.class;
	}

	
	
	
}
