package dz.gov.mesrs.sii.plhs.web.jsf.bean;

import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefLieuService;

public interface ServiceLocator {

	RefLieuService getRefLieuService();
	
	NomenclatureService getNomenclatureService();
}
