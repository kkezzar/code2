package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.DetailRealisationEquipementDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.DetailRealisationEquipement;

/**
 * Dao Implementation for domain model class DetailRealisationEquipement.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.DetailRealisationEquipement
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("detailRealisationEquipementDao")
@Transactional
public class DetailRealisationEquipementDaoImpl extends CommonDaoImpl<DetailRealisationEquipement, Integer> implements
		DetailRealisationEquipementDao {

	@Override
	protected Class<DetailRealisationEquipement> getDomainClass() {
		return DetailRealisationEquipement.class;

	}
}