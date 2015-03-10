package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.ProduitMarcheDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.ProduitMarche;

/**
 * Dao Implementation for domain model class ProduitMarche.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.ProduitMarche
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("produitMarcheDao")
@Transactional
public class ProduitMarcheDaoImpl extends CommonDaoImpl<ProduitMarche, Integer> implements ProduitMarcheDao {

	@Override
	protected Class<ProduitMarche> getDomainClass() {
		return ProduitMarche.class;

	}
}