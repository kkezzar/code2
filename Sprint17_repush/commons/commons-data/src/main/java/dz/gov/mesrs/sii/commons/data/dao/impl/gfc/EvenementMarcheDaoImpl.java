package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.EvenementMarcheDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.EvenementMarche;

/**
 * Dao Implementation for domain model class EvenementMarche.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.EvenementMarche
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("evenementMarcheDao")
@Transactional
public class EvenementMarcheDaoImpl extends CommonDaoImpl<EvenementMarche, Integer> implements EvenementMarcheDao {

	@Override
	protected Class<EvenementMarche> getDomainClass() {
		return EvenementMarche.class;

	}
}