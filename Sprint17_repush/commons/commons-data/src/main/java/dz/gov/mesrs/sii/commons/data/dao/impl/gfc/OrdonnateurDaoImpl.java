package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.OrdonnateurDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.Ordonnateur;

/**
 * Dao Implementation for domain model class Ordonnateur.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.Ordonnateur
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("ordonnateurDao")
@Transactional
public class OrdonnateurDaoImpl extends CommonDaoImpl<Ordonnateur, Integer>
		implements OrdonnateurDao {

	@Override
	protected Class<Ordonnateur> getDomainClass() {
		return Ordonnateur.class;

	}

}