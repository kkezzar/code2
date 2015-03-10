package dz.gov.mesrs.sii.recherche.web.jsf.bean.locator;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

import dz.gov.mesrs.sii.commons.business.service.bpm.ProcessusService;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.recherche.business.service.RechercheActiviteProjetService;
import dz.gov.mesrs.sii.recherche.business.service.RechercheDemandeBudgetService;

import dz.gov.mesrs.sii.recherche.business.service.RechercheEtapeValidationService;
import dz.gov.mesrs.sii.recherche.business.service.RechercheEvaluationIndicateurService;
import dz.gov.mesrs.sii.recherche.business.service.RechercheEvaluationProjetService;

import dz.gov.mesrs.sii.recherche.business.service.RechercheEvaluationChercheurService;

import dz.gov.mesrs.sii.recherche.business.service.RechercheGroupeService;
import dz.gov.mesrs.sii.recherche.business.service.RechercheIndEvaluationService;
import dz.gov.mesrs.sii.recherche.business.service.RechercheMotcleService;
import dz.gov.mesrs.sii.recherche.business.service.RecherchePartenaireService;
import dz.gov.mesrs.sii.recherche.business.service.RechercheProgrammeService;
import dz.gov.mesrs.sii.recherche.business.service.RechercheProjetPartenaireService;
import dz.gov.mesrs.sii.recherche.business.service.RechercheProjetService;
import dz.gov.mesrs.sii.recherche.business.service.RechercheStructureService;
import dz.gov.mesrs.sii.recherche.business.service.RechercheThemeService;
import dz.gov.mesrs.sii.referentiel.business.service.LdapService;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefAffectationService;
import dz.gov.mesrs.sii.referentiel.business.service.RefCompteService;
import dz.gov.mesrs.sii.referentiel.business.service.RefEquipementService;
import dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService;
import dz.gov.mesrs.sii.referentiel.business.service.RefGroupeService;
import dz.gov.mesrs.sii.referentiel.business.service.RefHoraireAccessService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametrageService;
import dz.gov.mesrs.sii.referentiel.business.service.RefPartenaireService;
import dz.gov.mesrs.sii.referentiel.business.service.RefPlageAdresseService;
import dz.gov.mesrs.sii.referentiel.business.service.RefStructureService;

/**
 * @author rlaib  on : 14 dï¿½c. 2014  16:51:15
 */
@ManagedBean(name = "serviceLocatorBean")
@ApplicationScoped
public class ServiceLocatorBean implements ServiceLocator {

	private static final long serialVersionUID = 1L;
	private static final String BPM_SITUATION_SERVICE_NAME = "situationService";
	private static final String BPM_PROCESSUS_SERVICE_NAME = "processusService";
	private static final String REF_ETABLISSEMENT_SERVICE_NAME = "refEtablissementServiceImpl";
	private static final String REF_STRUCTURE_SERVICE_NAME = "refStructureServiceImpl";
	private static final String LDAP_SERVICE_NAME = "ldapServiceImpl";
	private static final String REF_COMPTE_SERVICE_NAME = "refCompteServiceImpl";
	private static final String REF_INDIVIDU_SERVICE_NAME = "refIndividuServiceImpl";
	private static final String REF_PLAGE_ADRESSE_SERVICE_NAME = "refPlageAdresseServiceImpl";
	private static final String REF_HORAIREE_ACSSES_SERVICE_NAME = "refHoraireAccessServiceImpl";
	private static final String REF_PARAMETRAGE_SERVICE_NAME = "refParametrageServiceImpl";
	private static final String REF_NOMCLATURE_SERVICE_NAME = "nomenclatureServiceImpl";
	private static final String REF_GROUPE_SERVICE_NAME = "refGroupeServiceImpl";
	private static final String REF_AFFECTATION_SERVICE_NAME = "refAffectationServiceImpl";
	private static final String REF_EQUIPEMENT_SERVICE_NAME = "refEquipementServiceImpl";
	private static final String REF_PARTENAIRE_SERVICE_NAME = "refPartenaireServiceImpl";
	private static final String RECHERCHE_STRUCTURE_SERVICE_NAME = "rechercheStructureService";
	private static final String RECHERCHE_GROUPE_SERVICE_NAME = "rechercheGroupeService";
	private static final String RECHERCHE_THEME_SERVICE_NAME = "rechercheThemeService";
	private static final String RECHERCHE_PARTENAIRE_SERVICE_NAME = "recherchePartenaireService";
	private static final String RECHERCHE_PROGRAMME_SERVICE_NAME = "rechercheProgrammeService";
	private static final String RECHERCHE_DEMANDE_BUDGET_SERVICE_NAME = "rechercheDemandeBudgetService";
	private static final String RECHERCHE_PROJET_SERVICE_NAME = "rechercheProjetService";

	private static final String RECHERCHE_ACTIVITE_PROJET_SERVICE_NAME = "activiteProjetService";
	private static final String RECHERCHE_ETAPE_VALIDATION_NAME = "rechercheEtapeValidationService";
	private static final String RECHERCHE_MOT_CLE_NAME = "rechercheMotcleService";
	private static final String RECHERCHE_INDICATEUR_NAME = "rechercheIndEvaluationService";
	private static final String RECHERCHE_EVALUATION_PROJET_NAME = "evaluationProjetService";
	private static final String RECHERCHE_EVALUATION_INDICATEUR_NAME = "evaluationIndicateurService";
	private static final String RECHERCHE_PROJET_PARTENAIRE_NAME = "rechercheProjetPartenaireService";
	
	

	private static final String RECHERCHE_EVALUATION_CHERCHEUR_SERVICE_NAME = "rechercheEvaluationChercheurService";

	private final ApplicationContext appContext;
	private final SituationService situationService;
	private final ProcessusService processusService;
	private final RefEtablissementService etablissementService;
	private final RefStructureService structureService;
	private final LdapService ldapService;
	private final RefCompteService refCompteService;
	private final RefIndividuService refIndividuService;
	private final RefPlageAdresseService refPlageAdresseService;
	private final RefHoraireAccessService refHoraireAccessService;
	private final RefParametrageService refParametrageService;
	private final NomenclatureService nomenclatureService;
	private final RechercheStructureService rechercheStructureService;
	private final RechercheGroupeService rechercheGroupeService;
	private final RefGroupeService refGroupeService;
	private final RefEquipementService refEquipementService;
	private final RechercheThemeService rechercheThemeService;
	private final RefPartenaireService refPartenaireService;
	private final RefAffectationService refAffectationService;
	private final RecherchePartenaireService recherchePartenaireService;
	private final RechercheProgrammeService rechercheProgrammeService;
	private final RechercheDemandeBudgetService rechercheDemandeBudgetService;
	private final RechercheProjetService rechercheProjetService;

	private final RechercheActiviteProjetService rechercheActiviteProjetService;	
	private final RechercheEtapeValidationService rechercheEtapeValidationService;
	private final RechercheMotcleService rechercheMotcleService;	
	private final RechercheIndEvaluationService rechercheIndEvaluationService;
	private final RechercheEvaluationProjetService rechercheEvaluationProjetService;
	private final RechercheEvaluationIndicateurService rechercheEvaluationIndicateurService;
	private final RechercheProjetPartenaireService rechercheProjetPartenaireService;
	
	
	
	
	
	

	private final RechercheEvaluationChercheurService rechercheEvaluationChercheurService;

	
	public ServiceLocatorBean() {

		ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		this.appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		this.situationService = (SituationService) this.lookupService(BPM_SITUATION_SERVICE_NAME);
		this.etablissementService = (RefEtablissementService) this.lookupService(REF_ETABLISSEMENT_SERVICE_NAME);
		this.structureService = (RefStructureService) this.lookupService(REF_STRUCTURE_SERVICE_NAME);
		this.ldapService = (LdapService) this.lookupService(LDAP_SERVICE_NAME);
		this.refCompteService = (RefCompteService) this.lookupService(REF_COMPTE_SERVICE_NAME);
		this.refIndividuService = (RefIndividuService) this.lookupService(REF_INDIVIDU_SERVICE_NAME);
		this.refPlageAdresseService = (RefPlageAdresseService) this.lookupService(REF_PLAGE_ADRESSE_SERVICE_NAME);
		this.refHoraireAccessService = (RefHoraireAccessService) this.lookupService(REF_HORAIREE_ACSSES_SERVICE_NAME);
		this.refParametrageService = (RefParametrageService) this.lookupService(REF_PARAMETRAGE_SERVICE_NAME);
		this.nomenclatureService = (NomenclatureService) this.lookupService(REF_NOMCLATURE_SERVICE_NAME);
		this.rechercheStructureService = (RechercheStructureService) this.lookupService(RECHERCHE_STRUCTURE_SERVICE_NAME);
		this.refGroupeService = (RefGroupeService) this.lookupService(REF_GROUPE_SERVICE_NAME);
		this.rechercheGroupeService = (RechercheGroupeService) this.lookupService(RECHERCHE_GROUPE_SERVICE_NAME);
		this.refEquipementService = (RefEquipementService) this.lookupService(REF_EQUIPEMENT_SERVICE_NAME);
		this.rechercheThemeService = (RechercheThemeService) this.lookupService(RECHERCHE_THEME_SERVICE_NAME);
		this.refPartenaireService = (RefPartenaireService) this.lookupService(REF_PARTENAIRE_SERVICE_NAME);
		this.recherchePartenaireService = (RecherchePartenaireService) this.lookupService(RECHERCHE_PARTENAIRE_SERVICE_NAME);
		this.refAffectationService = (RefAffectationService) this.lookupService(REF_AFFECTATION_SERVICE_NAME);
		this.processusService = (ProcessusService) this.lookupService(BPM_PROCESSUS_SERVICE_NAME);
		this.rechercheProgrammeService = (RechercheProgrammeService) this.lookupService(RECHERCHE_PROGRAMME_SERVICE_NAME);
		this.rechercheDemandeBudgetService = (RechercheDemandeBudgetService) this.lookupService(RECHERCHE_DEMANDE_BUDGET_SERVICE_NAME);
		this.rechercheProjetService=(RechercheProjetService) this.lookupService(RECHERCHE_PROJET_SERVICE_NAME);

		this.rechercheActiviteProjetService=(RechercheActiviteProjetService) this.lookupService(RECHERCHE_ACTIVITE_PROJET_SERVICE_NAME);
		this.rechercheEtapeValidationService=(RechercheEtapeValidationService) this.lookupService(RECHERCHE_ETAPE_VALIDATION_NAME);
		this.rechercheMotcleService=(RechercheMotcleService) this.lookupService(RECHERCHE_MOT_CLE_NAME);
		this.rechercheIndEvaluationService=(RechercheIndEvaluationService) this.lookupService(RECHERCHE_INDICATEUR_NAME);
		this.rechercheEvaluationProjetService=(RechercheEvaluationProjetService) this.lookupService(RECHERCHE_EVALUATION_PROJET_NAME);
		this.rechercheEvaluationIndicateurService=(RechercheEvaluationIndicateurService) this.lookupService(RECHERCHE_EVALUATION_INDICATEUR_NAME);
		this.rechercheProjetPartenaireService=(RechercheProjetPartenaireService) this.lookupService(RECHERCHE_PROJET_PARTENAIRE_NAME);
		
	

		this.rechercheEvaluationChercheurService=(RechercheEvaluationChercheurService) this.lookupService(RECHERCHE_EVALUATION_CHERCHEUR_SERVICE_NAME);
		}


	public Object lookupService(String serviceBeanName) {

		return appContext.getBean(serviceBeanName);
	}

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

	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [ServiceLocatorBean.situationService] Getter 
	 * @author rlaib on : 14 dï¿½c. 2014  16:50:38
	 * @return the situationService
	 */
	public SituationService getSituationService() {
		return situationService;
	}

	/**
	 * [ServiceLocatorBean.etablissementService] Getter 
	 * @author rlaib on : 14 dï¿½c. 2014  16:50:38
	 * @return the etablissementService
	 */
	public RefEtablissementService getEtablissementService() {
		return etablissementService;
	}

	/**
	 * [ServiceLocatorBean.structureService] Getter 
	 * @author rlaib on : 14 dï¿½c. 2014  16:50:38
	 * @return the structureService
	 */
	public RefStructureService getStructureService() {
		return structureService;
	}

	/**
	 * [ServiceLocator.getRechercheStructureService] Method 
	 * Overridden By : @author rlaib  on : 16 dï¿½c. 2014  16:00:17
	 * @return
	 */
	@Override
	public RechercheStructureService getRechercheStructureService() {
		return rechercheStructureService;
	}

	/**
	 * [ServiceLocator.getRefStructureService] Method 
	 * Overridden By : @author rlaib  on : 17 dï¿½c. 2014  11:29:13
	 * @return
	 */
	@Override
	public RefStructureService getRefStructureService() {
		return structureService;
	}

	
	/**
	 * [ServiceLocator.getRefGroupeService] Method 
	 * Overridden By : @author rlaib  on : 18 dï¿½c. 2014  14:17:19
	 * @return
	 */
	@Override
	public RefGroupeService getRefGroupeService() {
		return refGroupeService;
	}

	/**
	 * [ServiceLocator.getRechercheGroupeService] Method 
	 * @author Rafik  on : 21 dÃ©c. 2014  11:45:56
	 * @return
	 */
	@Override
	public RechercheGroupeService getRechercheGroupeService() {
		return this.rechercheGroupeService;
	}

	/**
	 * [ServiceLocator.getRefEquipementService] Method 
	 * Overridden By : @author rlaib  on : 21 déc. 2014  16:21:15
	 * @return
	 */
	@Override
	public RefEquipementService getRefEquipementService() {
	return this.refEquipementService;
	}

	/**
	 * [ServiceLocator.getRechercheThemeService] Method 
	 * Overridden By : @author rlaib  on : 22 déc. 2014  15:10:48
	 * @return
	 */
	@Override
	public RechercheThemeService getRechercheThemeService() {
		 return this.rechercheThemeService;
	}

	/**
	 * [ServiceLocator.getRefPartenaireService] Method 
	 * Overridden By : @author rlaib  on : 23 déc. 2014  16:07:47
	 * @return
	 */
	@Override
	public RefPartenaireService getRefPartenaireService() {
		return this.refPartenaireService;
	}

	/**
	 * [ServiceLocator.getRecherchePartenaireService] Method 
	 * Overridden By : @author rlaib  on : 24 déc. 2014  09:30:00
	 * @return
	 */
	@Override
	public RecherchePartenaireService getRecherchePartenaireService() {
		return this.recherchePartenaireService;
	}

	/**
	 * [ServiceLocator.getRefAffectationService] Method 
	 * Overridden By : @author rlaib  on : 4 janv. 2015  08:56:08
	 * @return
	 */
	@Override
	public RefAffectationService getRefAffectationService() {
		return this.refAffectationService;
	}

	/**
	 * [ServiceLocator.getProcessusService] Method 
	 * Overridden By : @author rlaib  on : 15 janv. 2015  15:51:03
	 * @return
	 */
	@Override
	public ProcessusService getProcessusService() {
		
		return this.processusService;
	}

	/**
	 * [ServiceLocator.getRechercheProgrammeService] Method 
	 * Overridden By : @author rlaib  on : 25 janv. 2015  12:31:10
	 * @return
	 */
	@Override
	public RechercheProgrammeService getRechercheProgrammeService() {
		return this.rechercheProgrammeService;
	}
	

	/**
	 * [ServiceLocator.getRechercheDemandeBudgetService] Method 
	 * Overridden By : @author rlaib  on : 3 févr. 2015  13:03:19
	 * @return
	 */
	@Override
	public RechercheDemandeBudgetService getRechercheDemandeBudgetService() {
		return this.rechercheDemandeBudgetService;
	}

	@Override
	public RechercheProjetService getRechercheProjetService() {
		return this.rechercheProjetService;
	}

	@Override

	public RechercheActiviteProjetService getRechercheActiviteProjetService() {
		return rechercheActiviteProjetService;
	}
	@Override
	public RechercheEtapeValidationService getRechercheEtapeValidationService() {
		return rechercheEtapeValidationService;
	}
	@Override
	public RechercheMotcleService getRechercheMotcleService() {
		return rechercheMotcleService;
	}
	@Override
	public RechercheIndEvaluationService getRechercheIndEvaluationService() {
		return rechercheIndEvaluationService;
	}
	@Override
	public RechercheEvaluationProjetService getRechercheEvaluationProjetService() {
		return rechercheEvaluationProjetService;
	}
	@Override
	public RechercheEvaluationIndicateurService getRechercheEvaluationIndicateurService() {
		return rechercheEvaluationIndicateurService;
	}
	@Override
	public RechercheProjetPartenaireService getRechercheProjetPartenaireService() {
		return rechercheProjetPartenaireService;
	}

	
	
	
	

	public RechercheEvaluationChercheurService getRechercheEvaluationChercheurService() {
			return this.rechercheEvaluationChercheurService;
	}


	
}
