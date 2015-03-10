package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.DetailRealisationPrestationDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.DetailRealisationPrestation;

/**
 * Dao Implementation for domain model class DetailRealisationPrestation.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.DetailRealisationPrestation
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("detailRealisationPrestationDao")
@Transactional
public class DetailRealisationPrestationDaoImpl extends CommonDaoImpl<DetailRealisationPrestation, Integer> implements
		DetailRealisationPrestationDao {

	@Override
	protected Class<DetailRealisationPrestation> getDomainClass() {
		return DetailRealisationPrestation.class;

	}
}