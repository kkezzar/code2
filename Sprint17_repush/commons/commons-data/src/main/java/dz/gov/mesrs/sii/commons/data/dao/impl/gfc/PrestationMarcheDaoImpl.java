package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.PrestationMarcheDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.PrestationMarche;

/**
 * Dao Implementation for domain model class PrestationMarche.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.PrestationMarche
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("prestationMarcheDao")
@Transactional
public class PrestationMarcheDaoImpl extends CommonDaoImpl<PrestationMarche, Integer> implements PrestationMarcheDao {

	@Override
	protected Class<PrestationMarche> getDomainClass() {
		return PrestationMarche.class;

	}
}