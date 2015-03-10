package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.RegisseurDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.Regisseur;

/**
 * Dao Implementation for domain model class Regisseur.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.Regisseur
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("regisseurDao")
@Transactional
public class RegisseurDaoImpl extends CommonDaoImpl<Regisseur, Integer>
		implements RegisseurDao {

	@Override
	protected Class<Regisseur> getDomainClass() {
		return Regisseur.class;

	}

}