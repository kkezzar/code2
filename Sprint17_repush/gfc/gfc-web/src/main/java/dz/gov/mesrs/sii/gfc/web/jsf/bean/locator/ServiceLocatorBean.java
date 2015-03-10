package dz.gov.mesrs.sii.gfc.web.jsf.bean.locator;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.gfc.business.service.AffectationEtabChapitreArticleService;
import dz.gov.mesrs.sii.gfc.business.service.AffectationEtabStrAgentComptableService;
import dz.gov.mesrs.sii.gfc.business.service.AgentComptableService;
import dz.gov.mesrs.sii.gfc.business.service.ArticleService;
import dz.gov.mesrs.sii.gfc.business.service.ChapitreService;
import dz.gov.mesrs.sii.gfc.business.service.CompteService;
import dz.gov.mesrs.sii.gfc.business.service.ConsolidationBesoinsService;
import dz.gov.mesrs.sii.gfc.business.service.DecisionBudgetService;
import dz.gov.mesrs.sii.gfc.business.service.DetailEngagementMarcheService;
import dz.gov.mesrs.sii.gfc.business.service.DetailRealisationEquipementService;
import dz.gov.mesrs.sii.gfc.business.service.DetailRealisationPrestationService;
import dz.gov.mesrs.sii.gfc.business.service.DetailRealisationProduitService;
import dz.gov.mesrs.sii.gfc.business.service.DetailsFicheBesoinsService;
import dz.gov.mesrs.sii.gfc.business.service.DetailsMouvementBudgetService;
import dz.gov.mesrs.sii.gfc.business.service.DetailsNotificationDecisionBudgetService;
import dz.gov.mesrs.sii.gfc.business.service.DetailsProjetBudgetEtablissementService;
import dz.gov.mesrs.sii.gfc.business.service.DetailsProjetBudgetService;
import dz.gov.mesrs.sii.gfc.business.service.DetailsReparatitionBudgetService;
import dz.gov.mesrs.sii.gfc.business.service.DocumentRealisationMarcheService;
import dz.gov.mesrs.sii.gfc.business.service.DossierPaiementService;
import dz.gov.mesrs.sii.gfc.business.service.EngagementMarcheService;
import dz.gov.mesrs.sii.gfc.business.service.EquipementMarcheService;
import dz.gov.mesrs.sii.gfc.business.service.EvenementMarcheService;
import dz.gov.mesrs.sii.gfc.business.service.ExerciceBudgetaireService;
import dz.gov.mesrs.sii.gfc.business.service.FicheBesoinsService;
import dz.gov.mesrs.sii.gfc.business.service.FondsService;
import dz.gov.mesrs.sii.gfc.business.service.MarcheService;
import dz.gov.mesrs.sii.gfc.business.service.MouvementBudgetService;
import dz.gov.mesrs.sii.gfc.business.service.OrdonnateurService;
import dz.gov.mesrs.sii.gfc.business.service.ParagrapheService;
import dz.gov.mesrs.sii.gfc.business.service.PartieService;
import dz.gov.mesrs.sii.gfc.business.service.PrestationMarcheService;
import dz.gov.mesrs.sii.gfc.business.service.ProduitMarcheService;
import dz.gov.mesrs.sii.gfc.business.service.ProgrammeNationalService;
import dz.gov.mesrs.sii.gfc.business.service.ProjetBudgetService;
import dz.gov.mesrs.sii.gfc.business.service.RegieService;
import dz.gov.mesrs.sii.gfc.business.service.RegisseurService;
import dz.gov.mesrs.sii.gfc.business.service.RepartitionBudgetService;
import dz.gov.mesrs.sii.gfc.business.service.SectionService;
import dz.gov.mesrs.sii.gfc.business.service.SousSectionService;
import dz.gov.mesrs.sii.gfc.business.service.TarifMissionService;
import dz.gov.mesrs.sii.gfc.business.service.TitreService;
import dz.gov.mesrs.sii.referentiel.business.service.LdapService;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefCompteService;
import dz.gov.mesrs.sii.referentiel.business.service.RefContratService;
import dz.gov.mesrs.sii.referentiel.business.service.RefEquipementService;
import dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService;
import dz.gov.mesrs.sii.referentiel.business.service.RefHoraireAccessService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametrageService;
import dz.gov.mesrs.sii.referentiel.business.service.RefPlageAdresseService;
import dz.gov.mesrs.sii.referentiel.business.service.RefStructureService;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 12-04-2011
 * @description Impl�mentation de ServiceLocator.
 * 
 */
@ManagedBean(name = "serviceLocatorBean")
@ApplicationScoped
public class ServiceLocatorBean implements ServiceLocator {

	private static final long serialVersionUID = 1L;
	/**
	 * @author Salem
	 * @version 1.0
	 * @description Les noms des services
	 */

	private static final String BPM_SITUATION_SERVICE_NAME = "situationService";
	private static final String REF_ETABLISSEMENT_SERVICE_NAME = "refEtablissementServiceImpl";
	private static final String REF_STRUCTURE_SERVICE_NAME = "refStructureServiceImpl";
	private static final String REF_EQUIPEMENT_SERVICE_NAME = "refEquipementServiceImpl";
	private static final String REF_CONTRAT_SERVICE_NAME = "refContratServiceImpl";

	private static final String LDAP_SERVICE_NAME = "ldapServiceImpl";
	private static final String REF_COMPTE_SERVICE_NAME = "refCompteServiceImpl";
	private static final String REF_INDIVIDU_SERVICE_NAME = "refIndividuServiceImpl";
	private static final String REF_PLAGE_ADRESSE_SERVICE_NAME = "refPlageAdresseServiceImpl";
	private static final String REF_HORAIREE_ACSSES_SERVICE_NAME = "refHoraireAccessServiceImpl";
	private static final String REF_PRAMETRAGE_SERVICE_NAME = "refParametrageServiceImpl";
	private static final String REF_NOMCLATURE_SERVICE_NAME = "nomenclatureServiceImpl";

	private static final String GFC_AFFECTATIONCHAPARTICLE_SERVICE_NAME = "affectationEtabChapitreArticleService";
	private static final String GFC_AFFECTATIONETABCOMPTABLE_SERVICE_NAME = "affectationEtabStrAgentComptableService";
	private static final String GFC_AGENT_COMPTABLE_SERVICE_NAME = "agentComptableService";
	private static final String GFC_ARTICLE_SERVICE_NAME = "articleService";
	private static final String GFC_CHAPITRE_SERVICE_NAME = "chapitreService";
	private static final String GFC_COMPTE_SERVICE_NAME = "compteService";
	private static final String GFC_CONSOLIDATION_SERVICE_NAME = "consolidationBesoinsService";
	private static final String GFC_DECISION_BUDGET_SERVICE_NAME = "decisionBudgetService";
	private static final String GFC_DETAILS_FICHE_BESOIN_SERVICE_NAME = "detailsFicheBesoinsService";
	private static final String GFC_DETAILS_MOUVEMENT_SERVICE_NAME = "detailsMouvementBudgetService";
	private static final String GFC_DETAILS_NOTIFICATION_SERVICE_NAME = "detailsNotificationDecisionBudgetService";
	private static final String GFC_DETAILS_PROJET_SERVICE_NAME = "detailsProjetBudgetService";
	private static final String GFC_DETAILS_REPARTITION_SERVICE_NAME = "detailsReparatitionBudgetService";
	private static final String GFC_EXERCICE_BUDGET_SERVICE_NAME = "exerciceBudgetaireService";
	private static final String GFC_FICHE_BESOINS_SERVICE_NAME = "ficheBesoinsService";
	private static final String GFC_FONDS_SERVICE_NAME = "fondsService";
	private static final String GFC_MOUVEMENT_BUDGET_SERVICE_NAME = "mouvementBudgetService";
	private static final String GFC_ORDONNATEUR_SERVICE_NAME = "ordonnateurService";
	private static final String GFC_PARAGRAPHE_SERVICE_NAME = "paragrapheService";
	private static final String GFC_PARTIE_SERVICE_NAME = "partieService";
	private static final String GFC_PROGRAMME_NATIONNAL_SERVICE_NAME = "programmeNationalService";
	private static final String GFC_PROJET_BUDGET_SERVICE_NAME = "projetBudgetService";
	private static final String GFC_REGIE_SERVICE_NAME = "regieService";
	private static final String GFC_REGISSEUR_SERVICE_NAME = "regisseurService";
	private static final String GFC_REPARTITION_BUDGET_SERVICE_NAME = "repartitionBudgetService";
	private static final String GFC_SECTION_SERVICE_NAME = "sectionService";
	private static final String GFC_SOUS_SECTION_SERVICE_NAME = "sousSectionService";
	private static final String GFC_TITRE_SERVICE_NAME = "titreService";
	private static final String GFC_DETAILS_PROJET_ETABILLISEMENT_SERVICE_NAME = "detailsProjetBudgetEtablissementService";

	private static final String GFC_DETAIL_ENGAGEMENT_MARCHE_SERVICE_NAME = "detailEngagementMarcheService";
	private static final String GFC_DETAIL_REALISATION_EQUIPEMENT_SERVICE_NAME = "detailRealisationEquipementService";
	private static final String GFC_DETAIL_REALISATION_PRESTATION_SERVICE_NAME = "detailRealisationPrestationService";
	private static final String GFC_DETAIL_REALISATION_PRODUIT_SERVICE_NAME = "detailRealisationProduitService";
	private static final String GFC_DOCUMENT_REALISATION_MARCHE_SERVICE_NAME = "documentRealisationMarcheService";
	private static final String GFC_DOSSIER_PAIEMENT_SERVICE_NAME = "dossierPaiementService";
	private static final String GFC_ENGAGEMENT_MARCHE_SERVICE_NAME = "engagementMarcheService";
	private static final String GFC_EQUIPEMENT_MARCHE_SERVICE_NAME = "equipementMarcheService";
	private static final String GFC_EVENEMENT_MARCHE_SERVICE_NAME = "evenementMarcheService";
	private static final String GFC_MARCHE_SERVICE_NAME = "marcheService";
	private static final String GFC_PRESTATION_MARCHE_SERVICE_NAME = "prestationMarcheService";
	private static final String GFC_PRODUIT_MARCHE_SERVICE_NAME = "produitMarcheService";
	private static final String GFC_TARIF_MISSION_SERVICE_NAME = "tarifMissionService";

	/**
	 * @author Salem
	 * @version 1.0
	 * @description le contexte Spring
	 */
	private final ApplicationContext appContext;

	/**
	 * @author Salem
	 * @version 1.0
	 * @description Liste des services
	 */

	private final SituationService situationService;

	private final RefEtablissementService etablissementService;
	private final RefStructureService structureService;
	private final RefEquipementService equipementService;
	private final RefContratService contratService;

	private final LdapService ldapService;
	private final RefCompteService refCompteService;
	private final RefIndividuService refIndividuService;
	private final RefPlageAdresseService refPlageAdresseService;
	private final RefHoraireAccessService refHoraireAccessService;
	private final RefParametrageService refParametrageService;
	private final NomenclatureService nomenclatureService;

	private final AffectationEtabChapitreArticleService affectationEtabChapitreArticleService;
	private final AffectationEtabStrAgentComptableService affectationEtabStrAgentComptableService;
	private final AgentComptableService agentComptableService;
	private final ArticleService articleService;
	private final ChapitreService chapitreService;
	private final CompteService compteService;
	private final ConsolidationBesoinsService consolidationBesoinsService;
	private final DecisionBudgetService decisionBudgetService;
	private final DetailsFicheBesoinsService detailsFicheBesoinsService;
	private final DetailsMouvementBudgetService detailsMouvementBudgetService;
	private final DetailsNotificationDecisionBudgetService detailsNotificationDecisionBudgetService;
	private final DetailsProjetBudgetService detailsProjetBudgetService;
	private final DetailsReparatitionBudgetService detailsReparatitionBudgetService;
	private final ExerciceBudgetaireService exerciceBudgetaireService;
	private final FicheBesoinsService ficheBesoinsService;
	private final FondsService fondsService;
	private final MouvementBudgetService mouvementBudgetService;
	private final OrdonnateurService ordonnateurService;
	private final ParagrapheService paragrapheService;
	private final PartieService partieService;
	private final ProgrammeNationalService programmeNationalService;
	private final ProjetBudgetService projetBudgetService;
	private final RegieService regieService;
	private final RegisseurService regisseurService;
	private final RepartitionBudgetService repartitionBudgetService;
	private final SectionService sectionService;
	private final SousSectionService sousSectionService;
	private final TitreService titreService;
	private final DetailsProjetBudgetEtablissementService detailsProjetBudgetEtablissementService;

	// gestion des marches
	private final DetailEngagementMarcheService detailEngagementMarcheService;
	private final DetailRealisationEquipementService detailRealisationEquipementService;
	private final DetailRealisationPrestationService detailRealisationPrestationService;
	private final DetailRealisationProduitService detailRealisationProduitService;
	private final DocumentRealisationMarcheService documentRealisationMarcheService;
	private final DossierPaiementService dossierPaiementService;
	private final EngagementMarcheService engagementMarcheService;
	private final EquipementMarcheService equipementMarcheService;
	private final EvenementMarcheService evenementMarcheService;
	private final MarcheService marcheService;
	private final PrestationMarcheService prestationMarcheService;
	private final ProduitMarcheService produitMarcheService;

	// gestion des missions
	private final TarifMissionService tarifMissionService;
	/**
	 * @author Salem
	 * @version 1.0
	 * @description Constructeur du ServiceLocatorBean
	 */
	public ServiceLocatorBean() {

		ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		this.appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(context);

		this.situationService = (SituationService) this.lookupService(BPM_SITUATION_SERVICE_NAME);

		this.etablissementService = (RefEtablissementService) this.lookupService(REF_ETABLISSEMENT_SERVICE_NAME);
		this.structureService = (RefStructureService) this.lookupService(REF_STRUCTURE_SERVICE_NAME);
		this.equipementService = (RefEquipementService) this.lookupService(REF_EQUIPEMENT_SERVICE_NAME);
		this.contratService = (RefContratService) this.lookupService(REF_CONTRAT_SERVICE_NAME);

		this.ldapService = (LdapService) this.lookupService(LDAP_SERVICE_NAME);
		this.refCompteService = (RefCompteService) this.lookupService(REF_COMPTE_SERVICE_NAME);
		this.refIndividuService = (RefIndividuService) this.lookupService(REF_INDIVIDU_SERVICE_NAME);
		this.refPlageAdresseService = (RefPlageAdresseService) this.lookupService(REF_PLAGE_ADRESSE_SERVICE_NAME);
		this.refHoraireAccessService = (RefHoraireAccessService) this.lookupService(REF_HORAIREE_ACSSES_SERVICE_NAME);
		this.refParametrageService = (RefParametrageService) this.lookupService(REF_PRAMETRAGE_SERVICE_NAME);

		this.nomenclatureService = (NomenclatureService) this.lookupService(REF_NOMCLATURE_SERVICE_NAME);

		this.affectationEtabChapitreArticleService = (AffectationEtabChapitreArticleService) this
				.lookupService(GFC_AFFECTATIONCHAPARTICLE_SERVICE_NAME);
		this.affectationEtabStrAgentComptableService = (AffectationEtabStrAgentComptableService) this
				.lookupService(GFC_AFFECTATIONETABCOMPTABLE_SERVICE_NAME);
		this.agentComptableService = (AgentComptableService) this.lookupService(GFC_AGENT_COMPTABLE_SERVICE_NAME);
		this.articleService = (ArticleService) this.lookupService(GFC_ARTICLE_SERVICE_NAME);
		this.chapitreService = (ChapitreService) this.lookupService(GFC_CHAPITRE_SERVICE_NAME);
		this.compteService = (CompteService) this.lookupService(GFC_COMPTE_SERVICE_NAME);
		this.consolidationBesoinsService = (ConsolidationBesoinsService) this
				.lookupService(GFC_CONSOLIDATION_SERVICE_NAME);
		this.decisionBudgetService = (DecisionBudgetService) this.lookupService(GFC_DECISION_BUDGET_SERVICE_NAME);
		this.detailsFicheBesoinsService = (DetailsFicheBesoinsService) this
				.lookupService(GFC_DETAILS_FICHE_BESOIN_SERVICE_NAME);
		this.detailsMouvementBudgetService = (DetailsMouvementBudgetService) this
				.lookupService(GFC_DETAILS_MOUVEMENT_SERVICE_NAME);
		this.detailsNotificationDecisionBudgetService = (DetailsNotificationDecisionBudgetService) this
				.lookupService(GFC_DETAILS_NOTIFICATION_SERVICE_NAME);
		this.detailsProjetBudgetService = (DetailsProjetBudgetService) this
				.lookupService(GFC_DETAILS_PROJET_SERVICE_NAME);
		this.detailsReparatitionBudgetService = (DetailsReparatitionBudgetService) this
				.lookupService(GFC_DETAILS_REPARTITION_SERVICE_NAME);
		this.exerciceBudgetaireService = (ExerciceBudgetaireService) this
				.lookupService(GFC_EXERCICE_BUDGET_SERVICE_NAME);
		this.ficheBesoinsService = (FicheBesoinsService) this.lookupService(GFC_FICHE_BESOINS_SERVICE_NAME);
		this.fondsService = (FondsService) this.lookupService(GFC_FONDS_SERVICE_NAME);
		this.mouvementBudgetService = (MouvementBudgetService) this.lookupService(GFC_MOUVEMENT_BUDGET_SERVICE_NAME);
		this.ordonnateurService = (OrdonnateurService) this.lookupService(GFC_ORDONNATEUR_SERVICE_NAME);
		this.paragrapheService = (ParagrapheService) this.lookupService(GFC_PARAGRAPHE_SERVICE_NAME);
		this.partieService = (PartieService) this.lookupService(GFC_PARTIE_SERVICE_NAME);
		this.programmeNationalService = (ProgrammeNationalService) this
				.lookupService(GFC_PROGRAMME_NATIONNAL_SERVICE_NAME);
		this.projetBudgetService = (ProjetBudgetService) this.lookupService(GFC_PROJET_BUDGET_SERVICE_NAME);
		this.regieService = (RegieService) this.lookupService(GFC_REGIE_SERVICE_NAME);
		this.regisseurService = (RegisseurService) this.lookupService(GFC_REGISSEUR_SERVICE_NAME);
		this.repartitionBudgetService = (RepartitionBudgetService) this
				.lookupService(GFC_REPARTITION_BUDGET_SERVICE_NAME);
		this.sectionService = (SectionService) this.lookupService(GFC_SECTION_SERVICE_NAME);
		this.sousSectionService = (SousSectionService) this.lookupService(GFC_SOUS_SECTION_SERVICE_NAME);
		this.titreService = (TitreService) this.lookupService(GFC_TITRE_SERVICE_NAME);
		this.detailsProjetBudgetEtablissementService = (DetailsProjetBudgetEtablissementService) this
				.lookupService(GFC_DETAILS_PROJET_ETABILLISEMENT_SERVICE_NAME);

		// marche
		this.detailEngagementMarcheService = (DetailEngagementMarcheService) this
				.lookupService(GFC_DETAIL_ENGAGEMENT_MARCHE_SERVICE_NAME);
		this.detailRealisationEquipementService = (DetailRealisationEquipementService) this
				.lookupService(GFC_DETAIL_REALISATION_EQUIPEMENT_SERVICE_NAME);
		this.detailRealisationPrestationService = (DetailRealisationPrestationService) this
				.lookupService(GFC_DETAIL_REALISATION_PRESTATION_SERVICE_NAME);
		this.detailRealisationProduitService = (DetailRealisationProduitService) this
				.lookupService(GFC_DETAIL_REALISATION_PRODUIT_SERVICE_NAME);
		this.documentRealisationMarcheService = (DocumentRealisationMarcheService) this
				.lookupService(GFC_DOCUMENT_REALISATION_MARCHE_SERVICE_NAME);
		this.dossierPaiementService = (DossierPaiementService) this.lookupService(GFC_DOSSIER_PAIEMENT_SERVICE_NAME);
		this.engagementMarcheService = (EngagementMarcheService) this.lookupService(GFC_ENGAGEMENT_MARCHE_SERVICE_NAME);
		this.equipementMarcheService = (EquipementMarcheService) this.lookupService(GFC_EQUIPEMENT_MARCHE_SERVICE_NAME);
		this.evenementMarcheService = (EvenementMarcheService) this.lookupService(GFC_EVENEMENT_MARCHE_SERVICE_NAME);
		this.marcheService = (MarcheService) this.lookupService(GFC_MARCHE_SERVICE_NAME);
		this.prestationMarcheService = (PrestationMarcheService) this.lookupService(GFC_PRESTATION_MARCHE_SERVICE_NAME);
		this.produitMarcheService = (ProduitMarcheService) this.lookupService(GFC_PRODUIT_MARCHE_SERVICE_NAME);
		
		this.tarifMissionService= (TarifMissionService)this.lookupService(GFC_TARIF_MISSION_SERVICE_NAME);

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
	 * 
	 * @author Mounir.MESSAOUDI on : 1 déc. 2014 17:42:48
	 * @return
	 */
	@Override
	public RefEtablissementService getEtablissementService() {
		return etablissementService;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 1 déc. 2014 17:42:52
	 * @return
	 */
	@Override
	public RefStructureService getStructureService() {
		return structureService;
	}

	/**
	 * @author Salem
	 * @version 1.0
	 * @description getter des services
	 * @return
	 */
	@Override
	public RefEquipementService getEquipementService() {
		return equipementService;
	}

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

	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	public RefContratService getContratService() {
		return contratService;
	}

	/**
	 * @return the affectationEtabChapitreArticleService
	 */
	@Override
	public AffectationEtabChapitreArticleService getAffectationEtabChapitreArticleService() {
		return affectationEtabChapitreArticleService;
	}

	/**
	 * @return the affectationEtabStrAgentComptableService
	 */
	@Override
	public AffectationEtabStrAgentComptableService getAffectationEtabStrAgentComptableService() {
		return affectationEtabStrAgentComptableService;
	}

	/**
	 * @return the agentComptableService
	 */
	@Override
	public AgentComptableService getAgentComptableService() {
		return agentComptableService;
	}

	/**
	 * @return the articleService
	 */
	@Override
	public ArticleService getArticleService() {
		return articleService;
	}

	/**
	 * @return the chapitreService
	 */
	@Override
	public ChapitreService getChapitreService() {
		return chapitreService;
	}

	/**
	 * @return the compteService
	 */
	@Override
	public CompteService getCompteService() {
		return compteService;
	}

	/**
	 * @return the consolidationBesoinsService
	 */
	@Override
	public ConsolidationBesoinsService getConsolidationBesoinsService() {
		return consolidationBesoinsService;
	}

	/**
	 * @return the decisionBudgetService
	 */
	@Override
	public DecisionBudgetService getDecisionBudgetService() {
		return decisionBudgetService;
	}

	/**
	 * @return the detailsFicheBesoinsService
	 */
	@Override
	public DetailsFicheBesoinsService getDetailsFicheBesoinsService() {
		return detailsFicheBesoinsService;
	}

	/**
	 * @return the detailsMouvementBudgetService
	 */
	@Override
	public DetailsMouvementBudgetService getDetailsMouvementBudgetService() {
		return detailsMouvementBudgetService;
	}

	/**
	 * @return the detailsNotificationDecisionBudgetService
	 */
	@Override
	public DetailsNotificationDecisionBudgetService getDetailsNotificationDecisionBudgetService() {
		return detailsNotificationDecisionBudgetService;
	}

	/**
	 * @return the detailsProjetBudgetService
	 */
	@Override
	public DetailsProjetBudgetService getDetailsProjetBudgetService() {
		return detailsProjetBudgetService;
	}

	/**
	 * @return the detailsReparatitionBudgetService
	 */
	@Override
	public DetailsReparatitionBudgetService getDetailsReparatitionBudgetService() {
		return detailsReparatitionBudgetService;
	}

	/**
	 * @return the exerciceBudgetaireService
	 */
	@Override
	public ExerciceBudgetaireService getExerciceBudgetaireService() {
		return exerciceBudgetaireService;
	}

	/**
	 * @return the ficheBesoinsService
	 */
	@Override
	public FicheBesoinsService getFicheBesoinsService() {
		return ficheBesoinsService;
	}

	/**
	 * @return the fondsService
	 */
	@Override
	public FondsService getFondsService() {
		return fondsService;
	}

	/**
	 * @return the mouvementBudgetService
	 */
	@Override
	public MouvementBudgetService getMouvementBudgetService() {
		return mouvementBudgetService;
	}

	/**
	 * @return the ordonnateurService
	 */
	@Override
	public OrdonnateurService getOrdonnateurService() {
		return ordonnateurService;
	}

	/**
	 * @return the paragrapheService
	 */
	@Override
	public ParagrapheService getParagrapheService() {
		return paragrapheService;
	}

	/**
	 * @return the partieService
	 */
	@Override
	public PartieService getPartieService() {
		return partieService;
	}

	/**
	 * @return the programmeNationalService
	 */
	@Override
	public ProgrammeNationalService getProgrammeNationalService() {
		return programmeNationalService;
	}

	/**
	 * @return the projetBudgetService
	 */
	@Override
	public ProjetBudgetService getProjetBudgetService() {
		return projetBudgetService;
	}

	/**
	 * @return the regieService
	 */
	@Override
	public RegieService getRegieService() {
		return regieService;
	}

	/**
	 * @return the regisseurService
	 */
	@Override
	public RegisseurService getRegisseurService() {
		return regisseurService;
	}

	/**
	 * @return the repartitionBudgetService
	 */
	@Override
	public RepartitionBudgetService getRepartitionBudgetService() {
		return repartitionBudgetService;
	}

	/**
	 * @return the sectionService
	 */
	@Override
	public SectionService getSectionService() {
		return sectionService;
	}

	/**
	 * @return the sousSectionService
	 */
	@Override
	public SousSectionService getSousSectionService() {
		return sousSectionService;
	}

	/**
	 * @return the titreService
	 */
	@Override
	public TitreService getTitreService() {
		return titreService;
	}

	@Override
	public SituationService getSituationService() {
		return situationService;
	}

	/**
	 * @return the detailsProjetBudgetEtablissementService
	 */
	@Override
	public DetailsProjetBudgetEtablissementService getDetailsProjetBudgetEtablissementService() {
		return detailsProjetBudgetEtablissementService;
	}

	public DetailEngagementMarcheService getDetailEngagementMarcheService() {
		return detailEngagementMarcheService;
	}

	public DetailRealisationEquipementService getDetailRealisationEquipementService() {
		return detailRealisationEquipementService;
	}

	public DetailRealisationPrestationService getDetailRealisationPrestationService() {
		return detailRealisationPrestationService;
	}

	public DetailRealisationProduitService getDetailRealisationProduitService() {
		return detailRealisationProduitService;
	}

	public DocumentRealisationMarcheService getDocumentRealisationMarcheService() {
		return documentRealisationMarcheService;
	}

	public DossierPaiementService getDossierPaiementService() {
		return dossierPaiementService;
	}

	public EngagementMarcheService getEngagementMarcheService() {
		return engagementMarcheService;
	}

	public EquipementMarcheService getEquipementMarcheService() {
		return equipementMarcheService;
	}

	public EvenementMarcheService getEvenementMarcheService() {
		return evenementMarcheService;
	}

	public MarcheService getMarcheService() {
		return marcheService;
	}

	public PrestationMarcheService getPrestationMarcheService() {
		return prestationMarcheService;
	}

	public ProduitMarcheService getProduitMarcheService() {
		return produitMarcheService;
	}

	/**
	 * @return the tarifMissionService
	 */
	public TarifMissionService getTarifMissionService() {
		return tarifMissionService;
	}

}