package dz.gov.mesrs.sii.recherche.web.jsf.bean.locator;

import java.io.Serializable;

import dz.gov.mesrs.sii.commons.business.service.bpm.ProcessusService;
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
import dz.gov.mesrs.sii.referentiel.business.service.RefAffectationService;
import dz.gov.mesrs.sii.referentiel.business.service.RefCompteService;
import dz.gov.mesrs.sii.referentiel.business.service.RefEquipementService;
import dz.gov.mesrs.sii.referentiel.business.service.RefGroupeService;
import dz.gov.mesrs.sii.referentiel.business.service.RefHoraireAccessService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametrageService;
import dz.gov.mesrs.sii.referentiel.business.service.RefPartenaireService;
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
	
	public RechercheStructureService getRechercheStructureService();
	
	public RechercheGroupeService getRechercheGroupeService();

	public RechercheThemeService getRechercheThemeService();
	
	public RefStructureService getRefStructureService();
	
	public RefGroupeService getRefGroupeService();
	
	public RefEquipementService getRefEquipementService();

	public RefPartenaireService getRefPartenaireService();

	public RecherchePartenaireService getRecherchePartenaireService();
	
	public RefAffectationService getRefAffectationService();
	
	public ProcessusService getProcessusService();

	public RechercheProgrammeService getRechercheProgrammeService();
	
	public RechercheDemandeBudgetService getRechercheDemandeBudgetService();
	
	public RechercheProjetService  getRechercheProjetService();
	

	public RechercheActiviteProjetService  getRechercheActiviteProjetService();
	public RechercheEtapeValidationService getRechercheEtapeValidationService();

	public RechercheMotcleService getRechercheMotcleService();

	public RechercheIndEvaluationService getRechercheIndEvaluationService();

	public RechercheEvaluationProjetService getRechercheEvaluationProjetService();

	public RechercheEvaluationIndicateurService getRechercheEvaluationIndicateurService();

	RechercheProjetPartenaireService getRechercheProjetPartenaireService();

	public RechercheEvaluationChercheurService  getRechercheEvaluationChercheurService();


}
