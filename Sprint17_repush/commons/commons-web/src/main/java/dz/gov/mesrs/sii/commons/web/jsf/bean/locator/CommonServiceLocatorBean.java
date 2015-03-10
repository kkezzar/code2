package dz.gov.mesrs.sii.commons.web.jsf.bean.locator;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.dozer.Mapper;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.referentiel.business.service.LdapService;
import dz.gov.mesrs.sii.referentiel.business.service.RefAffectationService;
import dz.gov.mesrs.sii.referentiel.business.service.RefCompteService;
import dz.gov.mesrs.sii.referentiel.business.service.RefGroupeService;
import dz.gov.mesrs.sii.referentiel.business.service.RefHoraireAccessService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefJourOuvreService;
import dz.gov.mesrs.sii.referentiel.business.service.RefLieuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametrageService;
import dz.gov.mesrs.sii.referentiel.business.service.RefPlageAdresseService;
import dz.gov.mesrs.sii.referentiel.business.service.RefStructureService;

/**
 * L'implementation du Commun service locator
 * 
 * @author Mounir.MESSAOUDI on : 7 déc. 2014 15:50:17
 */
@ManagedBean(name = "commonServiceLocatorBean")
@ApplicationScoped
public class CommonServiceLocatorBean implements CommonServiceLocator {

	private static final long serialVersionUID = 1L;

	private final ApplicationContext appContext;

	private static final String LDAP_SERVICE_NAME = "ldapServiceImpl";
	private static final String REF_COMPTE_SERVICE_NAME = "refCompteServiceImpl";
	private static final String REF_INDIVIDU_SERVICE_NAME = "refIndividuServiceImpl";
	private static final String REF_PLAGE_ADRESSE_SERVICE_NAME = "refPlageAdresseServiceImpl";
	private static final String REF_HORAIREE_ACSSES_SERVICE_NAME = "refHoraireAccessServiceImpl";
	private static final String REF_PRAMETRAGE_SERVICE_NAME = "refParametrageServiceImpl";
	private static final String MAPPER_SERVICE_NAME = "mapper";
	private static final String SITUATION_SERVICE_NAME = "situationService";
	private static final String REF_JOUR_OUVRE_SERVICE_NAME = "refJourOuvreService";
	private static final String REF_AFFECTATION_SERVICE_NAME = "refAffectationServiceImpl";
	private static final String REF_GROUPE_SERVICE_NAME = "refGroupeServiceImpl";
	private static final String REF_STRUCURE_SERVICE_NAME = "refStructureServiceImpl";
	private static final String REF_LIEU_SERVICE_NAME = "refLieuServiceImpl";
	private final LdapService ldapService;
	private final RefCompteService refCompteService;
	private final RefIndividuService refIndividuService;
	private final RefPlageAdresseService refPlageAdresseService;
	private final RefHoraireAccessService refHoraireAccessService;
	private final RefParametrageService refParametrageService;
	public Mapper mapper;
	private final SituationService situationService;
	private RefJourOuvreService refJourOuvreService;
	private RefAffectationService refAffectationService;
	private RefGroupeService refGroupeService;
	private RefStructureService refStructureService;
	private RefLieuService refLieuService;
	public CommonServiceLocatorBean() {
		ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

		this.appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(context);

		this.ldapService = (LdapService) this.lookupService(LDAP_SERVICE_NAME);
		this.refCompteService = (RefCompteService) this.lookupService(REF_COMPTE_SERVICE_NAME);
		this.refIndividuService = (RefIndividuService) this.lookupService(REF_INDIVIDU_SERVICE_NAME);
		this.refPlageAdresseService = (RefPlageAdresseService) this.lookupService(REF_PLAGE_ADRESSE_SERVICE_NAME);
		this.refHoraireAccessService = (RefHoraireAccessService) this.lookupService(REF_HORAIREE_ACSSES_SERVICE_NAME);
		this.refParametrageService = (RefParametrageService) this.lookupService(REF_PRAMETRAGE_SERVICE_NAME);
		this.mapper = (Mapper) this.lookupService(MAPPER_SERVICE_NAME);
		this.situationService = (SituationService) this.lookupService(SITUATION_SERVICE_NAME);
		this.refJourOuvreService = (RefJourOuvreService) this.lookupService(REF_JOUR_OUVRE_SERVICE_NAME);
		this.refAffectationService = (RefAffectationService) this.lookupService(REF_AFFECTATION_SERVICE_NAME);
		this.refGroupeService = (RefGroupeService)this.lookupService(REF_GROUPE_SERVICE_NAME);
		this.refStructureService = (RefStructureService) this.lookupService(REF_STRUCURE_SERVICE_NAME);
		this.refLieuService = (RefLieuService) this.lookupService(REF_LIEU_SERVICE_NAME);
	}

	/**
	 * @author Salem
	 * @version 1.0
	 * @description Appeler un service
	 * @param serviceBeanName
	 * @return
	 */
	public Object lookupService(String serviceBeanName) {
		return appContext.getBean(serviceBeanName);
	}

	/**
	 * @author Salem
	 * @version 1.0
	 * @description getter des services
	 * @return
	 */

	/**
	 * @return the LdapService
	 */
	@Override
	public LdapService getLdapService() {
		return ldapService;
	}

	/**
	 * @return the RefCompteService
	 */
	@Override
	public RefCompteService getRefCompteService() {
		return refCompteService;
	}

	/**
	 * @return the refIndividuService
	 */
	@Override
	public RefIndividuService getRefIndividuService() {
		return refIndividuService;
	}

	/**
	 * @return the refPlageAdresseService
	 */
	@Override
	public RefPlageAdresseService getRefPlageAdresseService() {
		return refPlageAdresseService;
	}

	/**
	 * @return the RefHoraireAccessService
	 */
	@Override
	public RefHoraireAccessService getRefHoraireAccessService() {

		return refHoraireAccessService;
	}

	/**
	 * @return the refParametrageService
	 */
	@Override
	public RefParametrageService getRefParametrageService() {

		return refParametrageService;
	}

	/**
	 * @return the Mapper
	 */
	@Override
	public Mapper getMapper() {
		return mapper;
	}

	@Override
	public SituationService getSituationService() {
		return situationService;
	}
	
	@Override
	public RefJourOuvreService getRefJourOuvreService() {
		return refJourOuvreService;
	}
	
	@Override
	public RefAffectationService getRefAffectationService() {
		return refAffectationService;
	}
	
	@Override
	public RefGroupeService getRefGroupeService() {
		return refGroupeService;
	}
	@Override
	public RefStructureService getRefStructureService() {
		return refStructureService;
	}
	
	@Override
	public RefLieuService getRefLieuService() {
		return refLieuService;
	}

	
	
}
