package dz.gov.mesrs.sii.commons.data.dao.impl.gfc;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.gfc.DocumentRealisationMarcheDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.DocumentRealisationMarche;

/**
 * Dao Implementation for domain model class DocumentRealisationMarche.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.impl.business.model.bo.DocumentRealisationMarche
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Repository("documentRealisationMarcheDao")
@Transactional
public class DocumentRealisationMarcheDaoImpl extends CommonDaoImpl<DocumentRealisationMarche, Integer> implements
		DocumentRealisationMarcheDao {

	@Override
	protected Class<DocumentRealisationMarche> getDomainClass() {
		return DocumentRealisationMarche.class;

	}

	@Override
	protected Criteria getCriteria(DocumentRealisationMarche example) {
		Criteria criteria = super.getCriteria(example);

		if (example.getMarche() != null) {
			criteria.createAlias("marche", "marche");
			if (example.getMarche().getId() != null) {

				criteria.add(Restrictions.eq("marche", example.getMarche()));
			}

			if (example.getMarche().getEtablissement() != null && example.getMarche().getEtablissement().getId() != 0) {
				criteria.add(Restrictions.eq("marche.etablissement", example.getMarche().getEtablissement()));
			}

			if (example.getMarche().getStructure() != null && example.getMarche().getStructure().getId() != 0) {
				criteria.add(Restrictions.eq("marche.structure", example.getMarche().getStructure()));
			}

			if (example.getMarche().getSituation() != null && example.getMarche().getSituation().getId() != 0) {
				criteria.add(Restrictions.eq("marche.situation.id", example.getMarche().getSituation().getId()));
			}
		}

		if (example.getTypeDocument() != null && example.getTypeDocument().getId() != 0) {
			criteria.createAlias("typeDocument", "typeDocument");
			criteria.add(Restrictions.eq("typeDocument", example.getTypeDocument()));
		}

		if (example.getSituation() != null && example.getSituation().getId() != 0) {
			criteria.createAlias("situation", "situation");
			criteria.add(Restrictions.eq("situation.id", example.getSituation().getId()));
		}

		return criteria;
	}
}