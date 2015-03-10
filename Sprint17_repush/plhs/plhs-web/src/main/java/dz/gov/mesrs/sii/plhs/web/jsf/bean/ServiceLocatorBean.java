package dz.gov.mesrs.sii.plhs.web.jsf.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import dz.gov.mesrs.sii.commons.web.jsf.bean.locator.CommonServiceLocatorBean;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefLieuService;

@ManagedBean(name = "serviceLocatorBean")
@ApplicationScoped
public class ServiceLocatorBean extends CommonServiceLocatorBean implements
		ServiceLocator {

	private static final long serialVersionUID = 1982503539168166221L;

	private final static String REF_LIEU_SERVICE = "refLieuServiceImpl";
	private final static String NOMENCLATURE_SERVICE = "nomenclatureServiceImpl";
	
	
	private NomenclatureService nomenclatureService;
	private RefLieuService lieuService;
	

	@PostConstruct
	public void lookUp() {
		nomenclatureService = (NomenclatureService) super.lookupService(NOMENCLATURE_SERVICE);
		lieuService = (RefLieuService) super.lookupService(REF_LIEU_SERVICE);
	}

	@Override
	public RefLieuService getRefLieuService() {
		return lieuService;
	}

	@Override
	public NomenclatureService getNomenclatureService() {
		return this.nomenclatureService;
	}

}
