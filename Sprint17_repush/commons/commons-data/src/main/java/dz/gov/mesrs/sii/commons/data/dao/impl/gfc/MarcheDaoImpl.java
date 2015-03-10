package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.MarcheDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.Marche;

/**
 * Dao Implementation for domain model class Marche.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.Marche
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("marcheDao")
public class MarcheDaoImpl extends CommonDaoImpl<Marche, Integer> implements MarcheDao {

	@Override
	protected Class<Marche> getDomainClass() {
		return Marche.class;

	}

	@Override
	protected Criteria getCriteria(Marche example) {
		Criteria criteria = super.getCriteria(example);

		if (example.getEtablissement() != null && example.getEtablissement().getId() != 0) {
			criteria.createAlias("etablissement", "etablissement");
			criteria.add(Restrictions.eq("etablissement", example.getEtablissement()));
		}
		if (example.getStructure() != null && example.getStructure().getId() != 0) {
			criteria.createAlias("structure", "structure");
			criteria.add(Restrictions.eq("structure", example.getStructure()));
		}

		if (example.getSituation() != null && example.getSituation().getId() != 0) {
			criteria.createAlias("situation", "situation");
			criteria.add(Restrictions.eq("situation.id", example.getSituation().getId()));
		}

		if (example.getType() != null && example.getType().getId() != 0) {
			criteria.createAlias("type", "type");
			criteria.add(Restrictions.eq("type", example.getType()));
		}
		return criteria;
	}
}