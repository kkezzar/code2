package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.FormationCatalogueDao;
import dz.gov.mesrs.sii.commons.data.model.grh.FormationCatalogue;

@Repository("formationCatalogueDao")
public class FormationCatalogueDaoImpl extends CommonDaoImpl<FormationCatalogue, Integer> implements FormationCatalogueDao {

	@Override
	protected Class<FormationCatalogue> getDomainClass() {
		return FormationCatalogue.class;
	}

	
	
}
