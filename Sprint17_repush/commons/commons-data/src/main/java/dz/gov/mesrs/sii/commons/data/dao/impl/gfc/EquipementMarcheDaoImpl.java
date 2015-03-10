package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.EquipementMarcheDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.EquipementMarche;

/**
 * Dao Implementation for domain model class EquipementMarche.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.EquipementMarche
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("equipementMarcheDao")
@Transactional
public class EquipementMarcheDaoImpl extends CommonDaoImpl<EquipementMarche, Integer> implements EquipementMarcheDao {

	@Override
	protected Class<EquipementMarche> getDomainClass() {
		return EquipementMarche.class;

	}
}