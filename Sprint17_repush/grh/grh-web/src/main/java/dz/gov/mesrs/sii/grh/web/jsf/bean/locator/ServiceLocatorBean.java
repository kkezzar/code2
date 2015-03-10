package dz.gov.mesrs.sii.grh.web.jsf.bean.locator;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import dz.gov.mesrs.sii.commons.web.jsf.bean.locator.CommonServiceLocatorBean;
import dz.gov.mesrs.sii.grh.business.service.CarriereService;
import dz.gov.mesrs.sii.grh.business.service.ChangementPositionService;
import dz.gov.mesrs.sii.grh.business.service.DifficulteService;
import dz.gov.mesrs.sii.grh.business.service.EmployeProposeService;
import dz.gov.mesrs.sii.grh.business.service.PromotionEmployeService;
import dz.gov.mesrs.sii.grh.business.service.PromotionProposeService;
import dz.gov.mesrs.sii.grh.business.service.PropostionAvancementService;
import dz.gov.mesrs.sii.grh.business.service.absence.AbsenceService;
import dz.gov.mesrs.sii.grh.business.service.absence.AutorisationAbsenceService;
import dz.gov.mesrs.sii.grh.business.service.conges.CongeEmployeService;
import dz.gov.mesrs.sii.grh.business.service.conges.PrevisionCongeService;
import dz.gov.mesrs.sii.grh.business.service.dossieremploye.ConjointService;
import dz.gov.mesrs.sii.grh.business.service.dossieremploye.DiplomeEmployeService;
import dz.gov.mesrs.sii.grh.business.service.dossieremploye.DistinctionService;
import dz.gov.mesrs.sii.grh.business.service.dossieremploye.DossierEmployeService;
import dz.gov.mesrs.sii.grh.business.service.dossieremploye.EnfantService;
import dz.gov.mesrs.sii.grh.business.service.evaluation.EvaluationEmployeService;
import dz.gov.mesrs.sii.grh.business.service.evaluation.EvaluationPeriodeService;
import dz.gov.mesrs.sii.grh.business.service.finactivite.FinActiviteService;
import dz.gov.mesrs.sii.grh.business.service.finactivite.PieceDossierRetraiteService;
import dz.gov.mesrs.sii.grh.business.service.formation.BesoinFormationService;
import dz.gov.mesrs.sii.grh.business.service.formation.CatalogueFormationService;
import dz.gov.mesrs.sii.grh.business.service.formation.CycleFormationService;
import dz.gov.mesrs.sii.grh.business.service.formation.DetailBesoinFormationService;
import dz.gov.mesrs.sii.grh.business.service.formation.EvaluationSessionFormationService;
import dz.gov.mesrs.sii.grh.business.service.formation.FormationCatalogueService;
import dz.gov.mesrs.sii.grh.business.service.formation.InscriptionSessionFormationService;
import dz.gov.mesrs.sii.grh.business.service.formation.ProgrammeFormationGrhService;
import dz.gov.mesrs.sii.grh.business.service.formation.SessionFormationService;
import dz.gov.mesrs.sii.grh.business.service.mutation.MutationService;
import dz.gov.mesrs.sii.grh.business.service.poste.AffectationPosteService;
import dz.gov.mesrs.sii.grh.business.service.poste.PosteEmploiService;
import dz.gov.mesrs.sii.grh.business.service.recrutement.BesoinRecrutementService;
import dz.gov.mesrs.sii.grh.business.service.recrutement.CandidatEmployeService;
import dz.gov.mesrs.sii.grh.business.service.recrutement.DetailRecrutementService;
import dz.gov.mesrs.sii.grh.business.service.recrutement.StageService;
import dz.gov.mesrs.sii.grh.business.service.referentiel.CategorieProfessionnelleService;
import dz.gov.mesrs.sii.grh.business.service.referentiel.CategorieToGradeService;
import dz.gov.mesrs.sii.grh.business.service.referentiel.CategorieToNiveauService;
import dz.gov.mesrs.sii.grh.business.service.referentiel.CorpsService;
import dz.gov.mesrs.sii.grh.business.service.referentiel.FiliereService;
import dz.gov.mesrs.sii.grh.business.service.referentiel.GradeService;
import dz.gov.mesrs.sii.grh.business.service.referentiel.GrilleIndiciaireService;
import dz.gov.mesrs.sii.grh.business.service.referentiel.GroupeService;
import dz.gov.mesrs.sii.grh.business.service.referentiel.PosteSuperieureService;
import dz.gov.mesrs.sii.grh.business.service.referentiel.RubriqueService;
import dz.gov.mesrs.sii.grh.business.service.referentiel.StatutService;
import dz.gov.mesrs.sii.grh.business.service.suivimedical.DossierMedicalService;
import dz.gov.mesrs.sii.grh.business.service.suivimedical.VaccinService;
import dz.gov.mesrs.sii.grh.business.service.suivimedical.VisiteMedicaleService;
import dz.gov.mesrs.sii.referentiel.business.service.LdapService;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefCompteService;
import dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService;
import dz.gov.mesrs.sii.referentiel.business.service.RefHoraireAccessService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametrageService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametreEtabService;
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
public class ServiceLocatorBean extends CommonServiceLocatorBean implements ServiceLocator {

	private static final long serialVersionUID = 1L;
	/**
	 * @author Salem
	 * @version 1.0
	 * @description Les noms des services
	 */
	private static final String ABSENCE_SERVICE_NAME = "absenceService";
	private static final String AUTORISATION_ABSENCE_SERVICE_NAME = "autorisationAbsenceService";
	private static final String BESOIN_RECRUTEMENT_SERVICE_NAME = "besoinRecrutementService";
	private static final String CANDIDAT_ENMPLOYE_SERVICE_NAME = "candidatEmployeService";
	private static final String CARRIERE_SERVICE_NAME = "carriereService";
	private static final String CATEGORIE_PROFESSIONNELLE_SERVICE_NAME = "categorieProfessionnelleService";
	private static final String CATEGORIE_TO_NIVEAU_SERVICE_NAME = "categorieToNiveauService";
	private static final String CATEGORIE_TO_GRADE_SERVICE_NAME = "categorieToGradeService";
	private static final String CHANGEMENT_POSITION_SERVICE_NAME = "changementPositionService";
	private static final String CONJOINT_SERVICE_NAME = "conjointService";
	private static final String CORPS_SERVICE_NAME = "corpsService";
	private static final String DETAIL_RECRUTEMENT_SERVICE_NAME = "detailRecrutementService";
	private static final String DIFFICULTE_SERVICE_NAME = "difficulteService";
	private static final String DISTINCTION_SERVICE_NAME = "distinctionService";
	private static final String DOSSIER_EMPLOYE_SERVICE_NAME = "dossierEmployeService";
	private static final String EMPLOYE_PROPOSE_SERVICE_NAME = "employeProposeService";
	private static final String ENFANT_SERVICE_NAME = "enfantService";
	private static final String FIN_ACTIVITE_SERVICE_NAME = "finActiviteService";
	private static final String GRADE_SERVICE_NAME = "gradeService";
	private static final String GRILLE_INDICIAIRE_SERVICE_NAME = "grilleIndiciaireService";
	private static final String MUTATION_SERVICE_NAME = "mutationService";
	private static final String PIECE_DOSSIER_RETRAITE_SERVICE_NAME = "pieceDossierRetraiteService";
	private static final String POSTE_SUPERIEURE_SERVICE_NAME = "posteSuperieureService";
	private static final String PREVISION_CONGE_SERVICE_NAME = "previsionCongeService";
	private static final String PROMOTION_EMPLOYE_SERVICE_NAME = "promotionEmployeService";
	private static final String PROPOSITION_AVANCEMENT_SERVICE_NAME = "propostionAvancementService";
	private static final String PROMOTION_PROPOSE_SERVICE_NAME = "promotionProposeService";
	private static final String STATUT_SERVICE_NAME = "statutService";
	private static final String STAGE_SERVICE_NAME = "stageService";
	private static final String LDAP_SERVICE_NAME = "ldapServiceImpl";
	private static final String REF_COMPTE_SERVICE_NAME = "refCompteServiceImpl";
	private static final String REF_INDIVIDU_SERVICE_NAME = "refIndividuServiceImpl";
	private static final String REF_PLAGE_ADRESSE_SERVICE_NAME = "refPlageAdresseServiceImpl";
	private static final String REF_HORAIREE_ACSSES_SERVICE_NAME = "refHoraireAccessServiceImpl";
	private static final String REF_PRAMETRAGE_SERVICE_NAME = "refParametrageServiceImpl";
	private static final String RUBRIQUE_SERVICE_NAME = "rubriqueService";
	private static final String REF_NOMCLATURE_SERVICE_NAME = "nomenclatureServiceImpl";
	private static final String FILIERE_SERVICE_NAME = "filiereService";
	private static final String REF_STRUCTURE_SERVICE_NAME = "refStructureServiceImpl";
	private static final String GROUPE_SERVICE_NAME = "groupeService";
	private static final String DIPLOME_EMPLOYE_SERVICE_NAME = "diplomeEmployeService";
	private static final String REF_ETABLISSMENT_SERVICE_NAME = "refEtablissementServiceImpl";
	private static final String REF_PARAMETRE_ETAB_SERVICE_NAME = "refParametreEtabServiceImpl";
	private static final String CONGE_EMPLOYE_SERVICE_NAME = "congeEmployeService";
	private static final String DOSSIER_MEDICAL_SERVICE_NAME = "dossierMedicalService";
	private static final String VACCIN_SERVICE_NAME = "vaccinService";
	private static final String VISITE_MEDICALE_SERVICE_NAME = "visiteMedicaleService";
	private static final String POSTE_EMPLOI_SERVICE_NAME = "posteEmploiService";
	private static final String AFFECTATION_POSTE_SERVIE_NAME = "affectationPosteService";
	private static final String CATALOGUE_FORMATION_SERVICE_NAME = "catalogueFormationService";
	private static final String FORMATION_CATALOGUE_SERVICE_NAME = "formationCatalogueService";
	private static final String BESOIN_FORMATION_SERVICE_NAME = "besoinFormationService";
	private static final String DETAIL_BESOIN_FORMATION_SERVICE_NAME = "detailBesoinFormationService";
	private static final String EVALUATION_EMPLOYE_SERVICE_NAME = "evaluationEmployeService";
	private static final String EVALUATION_PERIODE_SERVICE_NAME = "evaluationPeriodeService";
	private static final String PROGRAMME_FORMATION_GRH_SERVICE_NAME = "programmeFormationGrhService";
	private static final String CYCLE_FORMATION_SERVICE_NAME = "cycleFormationService";
	private static final String SESSION_FORMATION_SERVICE_NAME = "sessionFormationService";
	private static final String INSCRIPTION_SESSION_FORMATION_SERVICE_NAME = "inscriptionSessionFormationService";
	private static final String EVALUATION_SESSION_FORMATION_SERVICE_NAME = "evaluationSessionFormationService";
	/**
	 * @author Salem
	 * @version 1.0
	 * @description Liste des services
	 */

	private AbsenceService absenceService;
	private AutorisationAbsenceService autorisationAbsenceService;
	private BesoinRecrutementService besoinRecrutementService;
	private CandidatEmployeService candidatEmployeService;
	private CarriereService carriereService;
	private CategorieProfessionnelleService categorieProfessionnelleService;
	private CategorieToNiveauService categorieToNiveauService;
	private CategorieToGradeService categorieToGradeService;
	private ChangementPositionService changementPositionService;
	private ConjointService conjointService;
	private CorpsService corpsService;
	private DetailRecrutementService detailRecrutementService;
	private DifficulteService difficulteService;
	private DistinctionService distinctionService;
	private DossierEmployeService dossierEmployeService;
	private EmployeProposeService employeProposeService;
	private EnfantService enfantService;
	private FinActiviteService finActiviteService;
	private GradeService gradeService;
	private GrilleIndiciaireService grilleIndiciaireService;
	private MutationService mutationService;
	private PieceDossierRetraiteService pieceDossierRetraiteService;
	private PosteSuperieureService posteSuperieureService;
	private PrevisionCongeService previsionCongeService;
	private PromotionEmployeService promotionEmployeService;
	private PromotionProposeService promotionProposeService;
	private PropostionAvancementService propostionAvancementService;
	private StageService stageService;
	private StatutService statutService;
	private LdapService ldapService;
	private RefCompteService refCompteService;
	private RefIndividuService refIndividuService;
	private RefPlageAdresseService refPlageAdresseService;
	private RefHoraireAccessService refHoraireAccessService;
	private RefParametrageService refParametrageService;
	private final RubriqueService rubriqueService;
	private final NomenclatureService nomenclatureService;
	private final FiliereService filiereService;
	private final RefStructureService refStructureService;
	private final GroupeService groupeService;
	private final DiplomeEmployeService diplomeEmployeService;
	private final RefEtablissementService refEtablissementService;
	private final RefParametreEtabService refParametreEtabService;
	private CongeEmployeService congeEmployeService;
	private DossierMedicalService dossierMedicalService;
	private VaccinService vaccinService;
	private VisiteMedicaleService visiteMedicaleService;
	private PosteEmploiService posteEmploiService;
	private AffectationPosteService affectationPosteService;
	private CatalogueFormationService catalogueFormationService;
	private FormationCatalogueService formationCatalogueService;
	private BesoinFormationService besoinFormationService;
	private DetailBesoinFormationService detailBesoinFormationService;
	private EvaluationPeriodeService evaluationPeriodeService;
	private EvaluationEmployeService evaluationEmployeService;
	private ProgrammeFormationGrhService programmeFormationGrhService;
	private CycleFormationService cycleFormationService;
	private SessionFormationService sessioFormationService;
	private InscriptionSessionFormationService inscriptionSessionFormationService;
	private EvaluationSessionFormationService evaluationSessionFormationService;
	
	/**
	 * @author Salem
	 * @version 1.0
	 * @description Constructeur du ServiceLocatorBean
	 */
	public ServiceLocatorBean() {
		super();

		this.absenceService = (AbsenceService) this.lookupService(ABSENCE_SERVICE_NAME);
		this.autorisationAbsenceService = (AutorisationAbsenceService) this
				.lookupService(AUTORISATION_ABSENCE_SERVICE_NAME);
		this.besoinRecrutementService = (BesoinRecrutementService) this.lookupService(BESOIN_RECRUTEMENT_SERVICE_NAME);
		this.candidatEmployeService = (CandidatEmployeService) this.lookupService(CANDIDAT_ENMPLOYE_SERVICE_NAME);
		this.carriereService = (CarriereService) this.lookupService(CARRIERE_SERVICE_NAME);
		this.categorieProfessionnelleService = (CategorieProfessionnelleService) this
				.lookupService(CATEGORIE_PROFESSIONNELLE_SERVICE_NAME);
		this.categorieToNiveauService = (CategorieToNiveauService) this.lookupService(CATEGORIE_TO_NIVEAU_SERVICE_NAME);
		this.categorieToGradeService = (CategorieToGradeService) this.lookupService(CATEGORIE_TO_GRADE_SERVICE_NAME);
		this.changementPositionService = (ChangementPositionService) this
				.lookupService(CHANGEMENT_POSITION_SERVICE_NAME);
		this.conjointService = (ConjointService) this.lookupService(CONJOINT_SERVICE_NAME);
		this.corpsService = (CorpsService) this.lookupService(CORPS_SERVICE_NAME);
		this.detailRecrutementService = (DetailRecrutementService) this.lookupService(DETAIL_RECRUTEMENT_SERVICE_NAME);
		this.difficulteService = (DifficulteService) this.lookupService(DIFFICULTE_SERVICE_NAME);
		this.distinctionService = (DistinctionService) this.lookupService(DISTINCTION_SERVICE_NAME);
		this.dossierEmployeService = (DossierEmployeService) this.lookupService(DOSSIER_EMPLOYE_SERVICE_NAME);
		this.employeProposeService = (EmployeProposeService) this.lookupService(EMPLOYE_PROPOSE_SERVICE_NAME);
		this.enfantService = (EnfantService) this.lookupService(ENFANT_SERVICE_NAME);
		this.finActiviteService = (FinActiviteService) this.lookupService(FIN_ACTIVITE_SERVICE_NAME);
		this.gradeService = (GradeService) this.lookupService(GRADE_SERVICE_NAME);
		this.grilleIndiciaireService = (GrilleIndiciaireService) this.lookupService(GRILLE_INDICIAIRE_SERVICE_NAME);
		this.mutationService = (MutationService) this.lookupService(MUTATION_SERVICE_NAME);
		this.pieceDossierRetraiteService = (PieceDossierRetraiteService) this
				.lookupService(PIECE_DOSSIER_RETRAITE_SERVICE_NAME);
		this.posteSuperieureService = (PosteSuperieureService) this.lookupService(POSTE_SUPERIEURE_SERVICE_NAME);
		this.previsionCongeService = (PrevisionCongeService) this.lookupService(PREVISION_CONGE_SERVICE_NAME);
		this.promotionEmployeService = (PromotionEmployeService) this.lookupService(PROMOTION_EMPLOYE_SERVICE_NAME);
		this.promotionProposeService = (PromotionProposeService) this.lookupService(PROMOTION_PROPOSE_SERVICE_NAME);
		this.propostionAvancementService = (PropostionAvancementService) this
				.lookupService(PROPOSITION_AVANCEMENT_SERVICE_NAME);
		this.stageService = (StageService) this.lookupService(STAGE_SERVICE_NAME);
		this.statutService = (StatutService) this.lookupService(STATUT_SERVICE_NAME);
		this.ldapService = (LdapService) this.lookupService(LDAP_SERVICE_NAME);
		this.refCompteService = (RefCompteService) this.lookupService(REF_COMPTE_SERVICE_NAME);
		this.refIndividuService = (RefIndividuService) this.lookupService(REF_INDIVIDU_SERVICE_NAME);
		this.refPlageAdresseService = (RefPlageAdresseService) this.lookupService(REF_PLAGE_ADRESSE_SERVICE_NAME);
		this.refHoraireAccessService = (RefHoraireAccessService) this.lookupService(REF_HORAIREE_ACSSES_SERVICE_NAME);
		this.refParametrageService = (RefParametrageService) this.lookupService(REF_PRAMETRAGE_SERVICE_NAME);
		this.rubriqueService = (RubriqueService) this.lookupService(RUBRIQUE_SERVICE_NAME);
		this.absenceService = (AbsenceService) this.lookupService(ABSENCE_SERVICE_NAME);
		this.autorisationAbsenceService = (AutorisationAbsenceService) this
				.lookupService(AUTORISATION_ABSENCE_SERVICE_NAME);
		this.besoinRecrutementService = (BesoinRecrutementService) this.lookupService(BESOIN_RECRUTEMENT_SERVICE_NAME);
		this.candidatEmployeService = (CandidatEmployeService) this.lookupService(CANDIDAT_ENMPLOYE_SERVICE_NAME);
		this.carriereService = (CarriereService) this.lookupService(CARRIERE_SERVICE_NAME);
		this.categorieProfessionnelleService = (CategorieProfessionnelleService) this
				.lookupService(CATEGORIE_PROFESSIONNELLE_SERVICE_NAME);
		this.categorieToNiveauService = (CategorieToNiveauService) this.lookupService(CATEGORIE_TO_NIVEAU_SERVICE_NAME);
		this.categorieToGradeService = (CategorieToGradeService) this.lookupService(CATEGORIE_TO_GRADE_SERVICE_NAME);
		this.changementPositionService = (ChangementPositionService) this
				.lookupService(CHANGEMENT_POSITION_SERVICE_NAME);
		this.conjointService = (ConjointService) this.lookupService(CONJOINT_SERVICE_NAME);
		this.corpsService = (CorpsService) this.lookupService(CORPS_SERVICE_NAME);
		this.detailRecrutementService = (DetailRecrutementService) this.lookupService(DETAIL_RECRUTEMENT_SERVICE_NAME);
		this.difficulteService = (DifficulteService) this.lookupService(DIFFICULTE_SERVICE_NAME);
		this.distinctionService = (DistinctionService) this.lookupService(DISTINCTION_SERVICE_NAME);
		this.dossierEmployeService = (DossierEmployeService) this.lookupService(DOSSIER_EMPLOYE_SERVICE_NAME);
		this.employeProposeService = (EmployeProposeService) this.lookupService(EMPLOYE_PROPOSE_SERVICE_NAME);
		this.enfantService = (EnfantService) this.lookupService(ENFANT_SERVICE_NAME);
		this.finActiviteService = (FinActiviteService) this.lookupService(FIN_ACTIVITE_SERVICE_NAME);
		this.gradeService = (GradeService) this.lookupService(GRADE_SERVICE_NAME);
		this.grilleIndiciaireService = (GrilleIndiciaireService) this.lookupService(GRILLE_INDICIAIRE_SERVICE_NAME);
		this.mutationService = (MutationService) this.lookupService(MUTATION_SERVICE_NAME);
		this.pieceDossierRetraiteService = (PieceDossierRetraiteService) this
				.lookupService(PIECE_DOSSIER_RETRAITE_SERVICE_NAME);
		this.posteSuperieureService = (PosteSuperieureService) this.lookupService(POSTE_SUPERIEURE_SERVICE_NAME);
		this.previsionCongeService = (PrevisionCongeService) this.lookupService(PREVISION_CONGE_SERVICE_NAME);
		this.promotionEmployeService = (PromotionEmployeService) this.lookupService(PROMOTION_EMPLOYE_SERVICE_NAME);
		this.promotionProposeService = (PromotionProposeService) this.lookupService(PROMOTION_PROPOSE_SERVICE_NAME);
		this.propostionAvancementService = (PropostionAvancementService) this
				.lookupService(PROPOSITION_AVANCEMENT_SERVICE_NAME);
		this.stageService = (StageService) this.lookupService(STAGE_SERVICE_NAME);
		this.statutService = (StatutService) this.lookupService(STATUT_SERVICE_NAME);
		this.ldapService = (LdapService) this.lookupService(LDAP_SERVICE_NAME);
		this.refCompteService = (RefCompteService) this.lookupService(REF_COMPTE_SERVICE_NAME);
		this.refIndividuService = (RefIndividuService) this.lookupService(REF_INDIVIDU_SERVICE_NAME);
		this.refPlageAdresseService = (RefPlageAdresseService) this.lookupService(REF_PLAGE_ADRESSE_SERVICE_NAME);
		this.refHoraireAccessService = (RefHoraireAccessService) this.lookupService(REF_HORAIREE_ACSSES_SERVICE_NAME);
		this.refParametrageService = (RefParametrageService) this.lookupService(REF_PRAMETRAGE_SERVICE_NAME);
		this.nomenclatureService = (NomenclatureService) this.lookupService(REF_NOMCLATURE_SERVICE_NAME);
		this.filiereService = (FiliereService) this.lookupService(FILIERE_SERVICE_NAME);
		this.refStructureService = (RefStructureService) this.lookupService(REF_STRUCTURE_SERVICE_NAME);
		this.groupeService = (GroupeService) this.lookupService(GROUPE_SERVICE_NAME);
		this.diplomeEmployeService = (DiplomeEmployeService) this.lookupService(DIPLOME_EMPLOYE_SERVICE_NAME);
		this.refEtablissementService = (RefEtablissementService) this.lookupService(REF_ETABLISSMENT_SERVICE_NAME);
		this.refParametreEtabService = (RefParametreEtabService) this.lookupService(REF_PARAMETRE_ETAB_SERVICE_NAME);
		this.congeEmployeService = (CongeEmployeService) this.lookupService(CONGE_EMPLOYE_SERVICE_NAME);
		this.dossierMedicalService = (DossierMedicalService) this.lookupService(DOSSIER_MEDICAL_SERVICE_NAME);
		this.vaccinService = (VaccinService) this.lookupService(VACCIN_SERVICE_NAME);
		this.visiteMedicaleService = (VisiteMedicaleService) this.lookupService(VISITE_MEDICALE_SERVICE_NAME);
		this.posteEmploiService = (PosteEmploiService) this.lookupService(POSTE_EMPLOI_SERVICE_NAME);
		this.affectationPosteService = (AffectationPosteService) this.lookupService(AFFECTATION_POSTE_SERVIE_NAME);
		this.catalogueFormationService = (CatalogueFormationService) this.lookupService(CATALOGUE_FORMATION_SERVICE_NAME);
		this.formationCatalogueService = (FormationCatalogueService) this.lookupService(FORMATION_CATALOGUE_SERVICE_NAME);
		this.besoinFormationService = (BesoinFormationService) this.lookupService(BESOIN_FORMATION_SERVICE_NAME);
		this.detailBesoinFormationService = (DetailBesoinFormationService) this.lookupService(DETAIL_BESOIN_FORMATION_SERVICE_NAME);
		this.evaluationEmployeService = (EvaluationEmployeService) this.lookupService(EVALUATION_EMPLOYE_SERVICE_NAME);
		this.evaluationPeriodeService = (EvaluationPeriodeService) this.lookupService(EVALUATION_PERIODE_SERVICE_NAME);
		this.programmeFormationGrhService = (ProgrammeFormationGrhService) this.lookupService(PROGRAMME_FORMATION_GRH_SERVICE_NAME);
		this.cycleFormationService = (CycleFormationService) this.lookupService(CYCLE_FORMATION_SERVICE_NAME);
	    this.sessioFormationService = (SessionFormationService) this.lookupService(SESSION_FORMATION_SERVICE_NAME);
	    this.inscriptionSessionFormationService = (InscriptionSessionFormationService) this.lookupService(INSCRIPTION_SESSION_FORMATION_SERVICE_NAME);
	    this.evaluationSessionFormationService = (EvaluationSessionFormationService) this.lookupService(EVALUATION_SESSION_FORMATION_SERVICE_NAME);
	}

	/**
	 * @return the absenceService
	 */
	@Override
	public AbsenceService getAbsenceService() {
		return absenceService;
	}

	/**
	 * @return the autorisationAbsenceService
	 */
	@Override
	public AutorisationAbsenceService getAutorisationAbsenceService() {
		return autorisationAbsenceService;
	}

	/**
	 * @return the besoinRecrutementService
	 */
	@Override
	public BesoinRecrutementService getBesoinRecrutementService() {
		return besoinRecrutementService;
	}

	/**
	 * @return the candidatEmployeService
	 */
	@Override
	public CandidatEmployeService getCandidatEmployeService() {
		return candidatEmployeService;
	}

	/**
	 * @return the carriereService
	 */
	@Override
	public CarriereService getCarriereService() {
		return carriereService;
	}

	/**
	 * @return the categorieProfessionnelleService
	 */
	@Override
	public CategorieProfessionnelleService getCategorieProfessionnelleService() {
		return categorieProfessionnelleService;
	}

	/**
	 * @return the categorieToNiveauService
	 */
	@Override
	public CategorieToNiveauService getCategorieToNiveauService() {
		return categorieToNiveauService;
	}

	/**
	 * @return the categorieToGradeService
	 */
	@Override
	public CategorieToGradeService getCategorieToGradeService() {
		return categorieToGradeService;
	}

	/**
	 * @return the changementPositionService
	 */
	@Override
	public ChangementPositionService getChangementPositionService() {
		return changementPositionService;
	}

	/**
	 * @return the conjointService
	 */
	@Override
	public ConjointService getConjointService() {
		return conjointService;
	}

	/**
	 * @return the corpsService
	 */
	@Override
	public CorpsService getCorpsService() {
		return corpsService;
	}

	/**
	 * @return the detailRecrutementService
	 */
	@Override
	public DetailRecrutementService getDetailRecrutementService() {
		return detailRecrutementService;
	}

	/**
	 * @return the difficulteService
	 */
	@Override
	public DifficulteService getDifficulteService() {
		return difficulteService;
	}

	/**
	 * @return the distinctionService
	 */
	@Override
	public DistinctionService getDistinctionService() {
		return distinctionService;
	}

	/**
	 * @return the dossierEmployeService
	 */
	@Override
	public DossierEmployeService getDossierEmployeService() {
		return dossierEmployeService;
	}

	/**
	 * @return the employeProposeService
	 */
	@Override
	public EmployeProposeService getEmployeProposeService() {
		return employeProposeService;
	}

	/**
	 * @return the enfantService
	 */
	@Override
	public EnfantService getEnfantService() {
		return enfantService;
	}

	/**
	 * @return the finActiviteService
	 */
	@Override
	public FinActiviteService getFinActiviteService() {
		return finActiviteService;
	}

	/**
	 * @return the gradeService
	 */
	@Override
	public GradeService getGradeService() {
		return gradeService;
	}

	/**
	 * @return the grilleIndiciaireService
	 */
	@Override
	public GrilleIndiciaireService getGrilleIndiciaireService() {
		return grilleIndiciaireService;
	}

	/**
	 * @return the mutationService
	 */
	@Override
	public MutationService getMutationService() {
		return mutationService;
	}

	/**
	 * @return the pieceDossierRetraiteService
	 */
	@Override
	public PieceDossierRetraiteService getPieceDossierRetraiteService() {
		return pieceDossierRetraiteService;
	}

	/**
	 * @return the posteSuperieureService
	 */
	@Override
	public PosteSuperieureService getPosteSuperieureService() {
		return posteSuperieureService;
	}

	/**
	 * @return the previsionCongeService
	 */
	@Override
	public PrevisionCongeService getPrevisionCongeService() {
		return previsionCongeService;
	}

	/**
	 * @return the promotionEmployeService
	 */
	@Override
	public PromotionEmployeService getPromotionEmployeService() {
		return promotionEmployeService;
	}

	/**
	 * @return the promotionProposeService
	 */
	@Override
	public PromotionProposeService getPromotionProposeService() {
		return promotionProposeService;
	}

	/**
	 * @return the propostionAvancementService
	 */
	@Override
	public PropostionAvancementService getPropostionAvancementService() {
		return propostionAvancementService;
	}

	/**
	 * @return the stageService
	 */
	@Override
	public StageService getStageService() {
		return stageService;
	}

	/**
	 * @return the statutService
	 */
	@Override
	public StatutService getStatutService() {
		return statutService;
	}

	/**
	 * @return the RubriqueService
	 */
	public RubriqueService getRubriqueService() {
		return rubriqueService;
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

	/**
	 * @return the FiliereService
	 */
	@Override
	public FiliereService getFiliereService() {
		return filiereService;
	}

	/**
	 * @return the refStructureService
	 */
	@Override
	public RefStructureService getRefStructureService() {
		return refStructureService;
	}

	@Override
	public GroupeService getGroupeService() {
		return groupeService;
	}

	@Override
	public DiplomeEmployeService getDiplomeEmployeService() {
		return diplomeEmployeService;
	}

	@Override
	public RefEtablissementService getRefEtablissementService() {
		return refEtablissementService;
	}

	@Override
	public RefParametreEtabService getRefParametreEtabService() {
		return refParametreEtabService;
	}
	
	@Override
	public CongeEmployeService getCongeEmployeService() {
		return congeEmployeService;
	}
	
	@Override
	public DossierMedicalService getDossierMedicalService() {
		return dossierMedicalService;
	}
	
	@Override
	public VaccinService getVaccinService() {
		return vaccinService;
	}

	@Override
	public VisiteMedicaleService getVisiteMedicaleService() {
		return visiteMedicaleService;
	}
	
	
	@Override
	public PosteEmploiService getPosteEmploiService() {
		return posteEmploiService;
	}

	@Override
	public AffectationPosteService getAffectationPosteService() {
		return affectationPosteService;
	}

	@Override
	public CatalogueFormationService getCatalogueFormationService() {
		return catalogueFormationService;
	}
	@Override
	public FormationCatalogueService getFormationCatalogueService() {
		return formationCatalogueService;
	}
	
	@Override
	public BesoinFormationService getBesoinFormationService() {
		return besoinFormationService;
	}

	@Override
	public DetailBesoinFormationService getDetailBesoinFormationService() {
		return detailBesoinFormationService;
	}
	
	@Override
	public EvaluationPeriodeService getEvaluationPeriodeService() {
		return evaluationPeriodeService;
	}

	@Override
	public EvaluationEmployeService getEvaluationEmployeService() {
		return evaluationEmployeService;
	}

	@Override
	public ProgrammeFormationGrhService getProgrammeFormationGrhService() {
		return programmeFormationGrhService;
	}

	@Override
	public CycleFormationService getCycleFormationService() {
		return cycleFormationService;
	}

	@Override
	public SessionFormationService getSessioFormationService() {
		return sessioFormationService;
	}

	@Override
	public InscriptionSessionFormationService getInscriptionSessionFormationService() {
		return inscriptionSessionFormationService;
	}
	
	@Override
	public EvaluationSessionFormationService getEvaluationSessionFormationService() {
		return evaluationSessionFormationService;
	}

	
	
	
}
