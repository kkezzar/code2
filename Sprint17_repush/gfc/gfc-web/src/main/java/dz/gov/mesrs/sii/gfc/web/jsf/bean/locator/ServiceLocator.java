package dz.gov.mesrs.sii.gfc.web.jsf.bean.locator;

import java.io.Serializable;

import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.gfc.business.service.AffectationEtabChapitreArticleService;
import dz.gov.mesrs.sii.gfc.business.service.AffectationEtabStrAgentComptableService;
import dz.gov.mesrs.sii.gfc.business.service.AgentComptableService;
import dz.gov.mesrs.sii.gfc.business.service.ArticleService;
import dz.gov.mesrs.sii.gfc.business.service.ChapitreService;
import dz.gov.mesrs.sii.gfc.business.service.CompteService;
import dz.gov.mesrs.sii.gfc.business.service.ConsolidationBesoinsService;
import dz.gov.mesrs.sii.gfc.business.service.DecisionBudgetService;
import dz.gov.mesrs.sii.gfc.business.service.DetailsFicheBesoinsService;
import dz.gov.mesrs.sii.gfc.business.service.DetailsMouvementBudgetService;
import dz.gov.mesrs.sii.gfc.business.service.DetailsNotificationDecisionBudgetService;
import dz.gov.mesrs.sii.gfc.business.service.DetailsProjetBudgetEtablissementService;
import dz.gov.mesrs.sii.gfc.business.service.DetailsProjetBudgetService;
import dz.gov.mesrs.sii.gfc.business.service.DetailsReparatitionBudgetService;
import dz.gov.mesrs.sii.gfc.business.service.ExerciceBudgetaireService;
import dz.gov.mesrs.sii.gfc.business.service.FicheBesoinsService;
import dz.gov.mesrs.sii.gfc.business.service.FondsService;
import dz.gov.mesrs.sii.gfc.business.service.MouvementBudgetService;
import dz.gov.mesrs.sii.gfc.business.service.OrdonnateurService;
import dz.gov.mesrs.sii.gfc.business.service.ParagrapheService;
import dz.gov.mesrs.sii.gfc.business.service.PartieService;
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
import dz.gov.mesrs.sii.referentiel.business.service.RefCompteService;
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
 * @date 21-10-2014
 * @description Interface ServiceLocator.
 * 
 */
public interface ServiceLocator extends Serializable {

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

	public RefEquipementService getEquipementService();

	/**
	 * 
	 * @return AffectationEtabChapitreArticleService
	 */
	public AffectationEtabChapitreArticleService getAffectationEtabChapitreArticleService();

	/**
	 * 
	 * @return affectationEtabStrAgentComptableService
	 */
	public AffectationEtabStrAgentComptableService getAffectationEtabStrAgentComptableService();

	/**
	 * 
	 * @return agentComptableService
	 */
	public AgentComptableService getAgentComptableService();

	/**
	 * 
	 * @return articleService
	 */
	public ArticleService getArticleService();

	/**
	 * 
	 * @return chapitreService
	 */
	public ChapitreService getChapitreService();

	/**
	 * 
	 * @return compteService
	 */
	public CompteService getCompteService();

	/**
	 * 
	 * @return ConsolidationBesoinsService
	 */
	public ConsolidationBesoinsService getConsolidationBesoinsService();

	/**
	 * 
	 * @return decisionBudgetService
	 */
	public DecisionBudgetService getDecisionBudgetService();

	/**
	 * 
	 * @return detailsFicheBesoinsService
	 */
	public DetailsFicheBesoinsService getDetailsFicheBesoinsService();

	/**
	 * 
	 * @return detailsMouvementBudgetService
	 */
	public DetailsMouvementBudgetService getDetailsMouvementBudgetService();

	/**
	 * 
	 * @return detailsNotificationDecisionBudgetService
	 */
	public DetailsNotificationDecisionBudgetService getDetailsNotificationDecisionBudgetService();

	/**
	 * 
	 * @return detailsProjetBudgetService
	 */
	public DetailsProjetBudgetService getDetailsProjetBudgetService();

	/**
	 * 
	 * @return detailsReparatitionBudgetService
	 */
	public DetailsReparatitionBudgetService getDetailsReparatitionBudgetService();

	/**
	 * 
	 * @return exerciceBudgetaireService
	 */
	public ExerciceBudgetaireService getExerciceBudgetaireService();

	/**
	 * 
	 * @return ficheBesoinsService
	 */
	public FicheBesoinsService getFicheBesoinsService();

	/**
	 * 
	 * @return fondsService
	 */
	public FondsService getFondsService();

	/**
	 * 
	 * @return mouvementBudgetService
	 */
	public MouvementBudgetService getMouvementBudgetService();

	/**
	 * 
	 * @return ordonnateurService
	 */
	public OrdonnateurService getOrdonnateurService();

	/**
	 * 
	 * @return paragrapheService
	 */
	public ParagrapheService getParagrapheService();

	/**
	 * 
	 * @return partieService
	 */
	public PartieService getPartieService();

	/**
	 * 
	 * @return programmeNationalService
	 */
	public ProgrammeNationalService getProgrammeNationalService();

	/**
	 * 
	 * @return projetBudgetService
	 */
	public ProjetBudgetService getProjetBudgetService();

	/**
	 * 
	 * @return regieService
	 */
	public RegieService getRegieService();

	/**
	 * 
	 * @return regisseurService
	 */
	public RegisseurService getRegisseurService();

	/**
	 * 
	 * @return repartitionBudgetService
	 */
	public RepartitionBudgetService getRepartitionBudgetService();

	/**
	 * 
	 * @return sectionService
	 */
	public SectionService getSectionService();

	/**
	 * 
	 * @return sousSectionService
	 */
	public SousSectionService getSousSectionService();

	/**
	 * 
	 * @return titreService
	 */
	public TitreService getTitreService();

	/**
	 * @author Mounir.MESSAOUDI on : 30 nov. 2014 14:59:24
	 * @return
	 */
	SituationService getSituationService();

	/**
	 * @author Mounir.MESSAOUDI on : 1 déc. 2014 17:42:56
	 * @return
	 */
	RefStructureService getStructureService();

	/**
	 * @author Mounir.MESSAOUDI on : 1 déc. 2014 17:43:07
	 * @return
	 */
	RefEtablissementService getEtablissementService();

	/**
	 * 
	 * @return detailsProjetBudgetEtablissementService
	 */
	public DetailsProjetBudgetEtablissementService getDetailsProjetBudgetEtablissementService();


	/**
	 * 
	 * @return tarifService
	 */
	public TarifMissionService getTarifMissionService();
}