package dz.gov.mesrs.sii.commons.web.jsf.bean.locator;

import java.io.Serializable;

import org.dozer.Mapper;

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
 * Interface Commun Service locator
 * 
 * @author Mounir.MESSAOUDI on : 7 déc. 2014 15:50:43
 */
public interface CommonServiceLocator extends Serializable {

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
	 * @return the Mapper
	 */
	public Mapper getMapper();

	/**
	 * @return the SituationService
	 */
	public SituationService getSituationService();
	
	/**
	 * @return the RefJourOuvreService
	 */
	public RefJourOuvreService getRefJourOuvreService() ;
	
	/**
	 * [CommonServiceLocator.getRefAffectationService] Method 
	 * @author MAKERRI Sofiane  on : 28 janv. 2015  14:07:24
	 * @return
	 */
	public RefAffectationService getRefAffectationService() ;
	
	public RefGroupeService getRefGroupeService();

	RefStructureService getRefStructureService();
	
	RefLieuService getRefLieuService();

}