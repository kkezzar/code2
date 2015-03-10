package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.DetailRealisationProduitDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.DetailRealisationProduit;

/**
 * Dao Implementation for domain model class DocumentRealisationMarche.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.DetailRealisationProduit
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("detailRealisationProduitDao")
@Transactional
public class DetailRealisationProduitDaoImpl extends CommonDaoImpl<DetailRealisationProduit, Integer> implements
		DetailRealisationProduitDao {

	@Override
	protected Class<DetailRealisationProduit> getDomainClass() {
		return DetailRealisationProduit.class;

	}
}