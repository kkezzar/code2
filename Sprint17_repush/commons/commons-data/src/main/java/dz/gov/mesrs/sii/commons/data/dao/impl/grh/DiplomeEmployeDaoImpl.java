package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.DiplomeEmployeDao;
import dz.gov.mesrs.sii.commons.data.model.grh.DiplomeEmploye;

@Repository("diplomeEmployeDao")
public class DiplomeEmployeDaoImpl extends CommonDaoImpl<DiplomeEmploye, Integer> implements DiplomeEmployeDao {

	@Override
	protected Class<DiplomeEmploye> getDomainClass() {
		return DiplomeEmploye.class;
	}

}
