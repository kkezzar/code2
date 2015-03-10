package dz.gov.mesrs.sii.grh.web.jsf.bean.locator;

import java.io.Serializable;

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
import dz.gov.mesrs.sii.grh.business.service.referentiel.StatutService;
import dz.gov.mesrs.sii.grh.business.service.suivimedical.DossierMedicalService;
import dz.gov.mesrs.sii.grh.business.service.suivimedical.VaccinService;
import dz.gov.mesrs.sii.grh.business.service.suivimedical.VisiteMedicaleService;
import dz.gov.mesrs.sii.referentiel.business.service.LdapService;
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
 * @date 21-10-2014
 * @description Interface ServiceLocator.
 * 
 */
public interface ServiceLocator extends Serializable {

	/**
	 * 
	 * @return AbsenceService
	 */
	public AbsenceService getAbsenceService();

	/**
	 * 
	 * @return AutorisationAbsenceService
	 */
	public AutorisationAbsenceService getAutorisationAbsenceService();

	/**
	 * 
	 * @return BesoinRecrutementService.
	 */
	public BesoinRecrutementService getBesoinRecrutementService();

	/**
	 * 
	 * @return CandidatEmployeService
	 */
	public CandidatEmployeService getCandidatEmployeService();

	/**
	 * 
	 * @return CarriereService
	 */
	public CarriereService getCarriereService();

	/**
	 * 
	 * @return CategorieProfessionnelleService
	 */
	public CategorieProfessionnelleService getCategorieProfessionnelleService();

	/**
	 * 
	 * @return CategorieToNiveauService
	 */
	public CategorieToNiveauService getCategorieToNiveauService();

	/**
	 * 
	 * @return CategorieToGradeService
	 */
	public CategorieToGradeService getCategorieToGradeService();

	/**
	 * 
	 * @return ChangementPositionService
	 */
	public ChangementPositionService getChangementPositionService();

	/**
	 * 
	 * @return ConjointService
	 */
	public ConjointService getConjointService();

	/**
	 * 
	 * @return CorpsService
	 */
	public CorpsService getCorpsService();

	/**
	 * 
	 * @return DetailRecrutementService
	 */
	public DetailRecrutementService getDetailRecrutementService();

	/**
	 * 
	 * @return DifficulteService
	 */
	public DifficulteService getDifficulteService();

	/**
	 * 
	 * @return DistinctionService
	 */
	public DistinctionService getDistinctionService();

	/**
	 * 
	 * @return DossierEmployeService
	 */
	public DossierEmployeService getDossierEmployeService();

	/**
	 * 
	 * @return EmployeProposeService
	 */
	public EmployeProposeService getEmployeProposeService();

	/**
	 * 
	 * @return EnfantService
	 */
	public EnfantService getEnfantService();

	/**
	 * 
	 * @return FinActiviteService
	 */
	public FinActiviteService getFinActiviteService();

	/**
	 * 
	 * @return GradeService
	 */
	public GradeService getGradeService();

	/**
	 * 
	 * @return GrilleIndiciaireService
	 */
	public GrilleIndiciaireService getGrilleIndiciaireService();

	/**
	 * 
	 * @return MutationService
	 */
	public MutationService getMutationService();

	/**
	 * 
	 * @return PieceDossierRetraiteService
	 */
	public PieceDossierRetraiteService getPieceDossierRetraiteService();

	/**
	 * 
	 * @return PosteSuperieureService
	 */
	public PosteSuperieureService getPosteSuperieureService();

	/**
	 * 
	 * @return PrevisionCongeService
	 */
	public PrevisionCongeService getPrevisionCongeService();

	/**
	 * 
	 * @return PromotionEmployeService
	 */
	public PromotionEmployeService getPromotionEmployeService();

	/**
	 * 
	 * @return PromotionProposeService
	 */
	public PromotionProposeService getPromotionProposeService();

	/**
	 * 
	 * @return PropostionAvancementService
	 */
	public PropostionAvancementService getPropostionAvancementService();

	/**
	 * 
	 * @return Stage
	 */
	public StageService getStageService();

	/**
	 * 
	 * @return StatutService
	 */
	public StatutService getStatutService();

	/**
	 * 
	 * @return LdapService
	 */
	public LdapService getLdapService();

	/**
	 * 
	 * @return LdapService
	 */
	public RefCompteService getRefCompteService();

	/**
	 * 
	 * @return refIndividuService
	 */
	public RefIndividuService getRefIndividuService();

	/**
	 * 
	 * @return refPlageAdresseService
	 */
	public RefPlageAdresseService getRefPlageAdresseService();

	/**
	 * 
	 * @return refHoraireAccessService
	 */
	public RefHoraireAccessService getRefHoraireAccessService();

	/**
	 * 
	 * @return RefParametrageService
	 */
	public RefParametrageService getRefParametrageService();

	/**
	 * 
	 * @return refStructureService
	 */
	public RefStructureService getRefStructureService();

	/**
	 * 
	 * @return GroupeService
	 */
	public GroupeService getGroupeService();

	/**
	 * 
	 * @return FiliereService
	 */
	public FiliereService getFiliereService();

	/**
	 * 
	 * @return DiplomeEmployeService
	 */
	public DiplomeEmployeService getDiplomeEmployeService();

	/**
	 * 
	 * @return RefEtablissementService
	 */
	public RefEtablissementService getRefEtablissementService();
	
	/**
	 * 
	 * @return RefParametreEtabService
	 */
	public RefParametreEtabService getRefParametreEtabService();
	/**
	 * 
	 * @return CongeEmployeService
	 */
    public CongeEmployeService getCongeEmployeService();
    
    
    /**
	 * 
	 * @return DossierMedicalService
	 */
	public DossierMedicalService getDossierMedicalService();
	
	/**
	 * 
	 * @return VaccinService
	 */
	public VaccinService getVaccinService();
	
	/**
	 * 
	 * @return VisiteMedicaleService
	 */
	public VisiteMedicaleService getVisiteMedicaleService();
	
	PosteEmploiService getPosteEmploiService();
	
	AffectationPosteService getAffectationPosteService();

	/**
	 * 
	 * @return CatalogueFormationService
	 */
	CatalogueFormationService getCatalogueFormationService();

	/**
	 * 
	 * @return FormationCatalogueService
	 */
	FormationCatalogueService getFormationCatalogueService();

	/**
	 * 
	 * @return DetailBesoinFormationService
	 */
	DetailBesoinFormationService getDetailBesoinFormationService();

	/**
	 * 
	 * @return BesoinFormationService
	 */
	BesoinFormationService getBesoinFormationService();
	
	/**
	 * 
	 * @return EvaluationPeriodeService
	 */
	EvaluationPeriodeService getEvaluationPeriodeService();
	
	/**
	 * 
	 * @return EvaluationEmployeService
	 */
	EvaluationEmployeService getEvaluationEmployeService();

	/**
	 * 
	 * @return EvaluationSessionFormationService
	 */
	EvaluationSessionFormationService getEvaluationSessionFormationService();

	/**
	 * 
	 * @return InscriptionSessionFormationService
	 */
	InscriptionSessionFormationService getInscriptionSessionFormationService();

	/**
	 * 
	 * @return SessionFormationService
	 */
	SessionFormationService getSessioFormationService();

	/**
	 * 
	 * @return CycleFormationService
	 */
	CycleFormationService getCycleFormationService();

	/**
	 * 
	 * @return ProgrammeFormationGrhService
	 */
	ProgrammeFormationGrhService getProgrammeFormationGrhService();
}