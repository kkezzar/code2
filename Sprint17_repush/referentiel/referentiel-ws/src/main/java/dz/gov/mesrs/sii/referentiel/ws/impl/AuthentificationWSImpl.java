
package dz.gov.mesrs.sii.referentiel.ws.impl;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCompteDto;
import dz.gov.mesrs.sii.referentiel.business.service.LdapService;
import dz.gov.mesrs.sii.referentiel.business.service.RefCompteService;
import dz.gov.mesrs.sii.referentiel.business.service.RefHoraireAccessService;
import dz.gov.mesrs.sii.referentiel.business.service.RefPlageAdresseService;
import dz.gov.mesrs.sii.referentiel.ws.service.AuthentificationWS;



@Service("authentificationWSImpl")
public class AuthentificationWSImpl implements AuthentificationWS {

	private static final Log log = LogFactory.getLog(AuthentificationWSImpl.class);

	
	
	public AuthentificationWSImpl() {

	}



	@Autowired
	@Qualifier("refCompteServiceImpl")
	private RefCompteService refCompteService;
	@Autowired
	@Qualifier("ldapServiceImpl")
	private LdapService ldapService ;
	@Autowired
	@Qualifier("refPlageAdresseServiceImpl")
	private RefPlageAdresseService refPlageAdresseService;
	@Autowired
	@Qualifier("refHoraireAccessServiceImpl")
	private RefHoraireAccessService refHoraireAccessService;



	/**
	 * [AuthentificationWSImpl.refCompteService] Getter 
	 * @author BELDI Jamel on : 16 juin 2014  16:06:04
	 * @return the refCompteService
	 */
	public RefCompteService getRefCompteService() {
		return refCompteService;
	}
	/**
	 * [AuthentificationWSImpl.refCompteService] Setter 
	 * @author BELDI Jamel on : 16 juin 2014  16:06:04
	 * @param refCompteService the refCompteService to set
	 */
	public void setRefCompteService(RefCompteService refCompteService) {
		this.refCompteService = refCompteService;
	}
	/**
	 * [AuthentificationWSImpl.ldapService] Getter 
	 * @author BELDI Jamel on : 16 juin 2014  16:06:04
	 * @return the ldapService
	 */
	public LdapService getLdapService() {
		return ldapService;
	}
	/**
	 * [AuthentificationWSImpl.ldapService] Setter 
	 * @author BELDI Jamel on : 16 juin 2014  16:06:04
	 * @param ldapService the ldapService to set
	 */
	public void setLdapService(LdapService ldapService) {
		this.ldapService = ldapService;
	}
	/**
	 * [AuthentificationWSImpl.refPlageAdresseService] Getter 
	 * @author BELDI Jamel on : 16 juin 2014  16:06:04
	 * @return the refPlageAdresseService
	 */
	public RefPlageAdresseService getRefPlageAdresseService() {
		return refPlageAdresseService;
	}
	/**
	 * [AuthentificationWSImpl.refPlageAdresseService] Setter 
	 * @author BELDI Jamel on : 16 juin 2014  16:06:04
	 * @param refPlageAdresseService the refPlageAdresseService to set
	 */
	public void setRefPlageAdresseService(
			RefPlageAdresseService refPlageAdresseService) {
		this.refPlageAdresseService = refPlageAdresseService;
	}
	/**
	 * [AuthentificationWSImpl.refHoraireAccessService] Getter 
	 * @author BELDI Jamel on : 16 juin 2014  16:06:04
	 * @return the refHoraireAccessService
	 */
	public RefHoraireAccessService getRefHoraireAccessService() {
		return refHoraireAccessService;
	}
	/**
	 * [AuthentificationWSImpl.refHoraireAccessService] Setter 
	 * @author BELDI Jamel on : 16 juin 2014  16:06:04
	 * @param refHoraireAccessService the refHoraireAccessService to set
	 */
	public void setRefHoraireAccessService(
			RefHoraireAccessService refHoraireAccessService) {
		this.refHoraireAccessService = refHoraireAccessService;
	}
	@Override
	public boolean verifierUserLdap(String login, String password)
			throws Exception {
		try {
			boolean  result = ldapService.verifierUserLdap(login, password);			
			log.debug("verifierUserLdap successful");
			return result;
		} catch (Exception e) {
			log.error("verifierUserLdap  failed", e);
			throw e;
		}
		
	}
	@Override
	public RefCompteDto findByLogin(String nomUtilisateur) throws Exception {
		try {
			RefCompteDto  result = refCompteService.findByLogin(nomUtilisateur);		
			log.debug("findByLogin successful");
			return result;
		} catch (Exception e) {
			log.error("findByLogin  failed", e);
			throw e;
		}
		
	}
	@Override
	public boolean verifyUserIp(String userAddr, String adresseDebut,
			String adresseFin) throws Exception {
		try {
			boolean  result = refPlageAdresseService.verifyUserIp(userAddr, adresseDebut, adresseFin);		
			log.debug("verifyUserIp successful");
			return result;
		} catch (Exception e) {
			log.error("verifyUserIp  failed", e);
			throw e;
		}
		
	}
	@Override
	public boolean verifyHoraireAcces(Date heureAcces, Date heureDebut,
			Date heureFin) throws Exception {
		try {
			boolean  result = refHoraireAccessService.verifyHoraireAcces(heureAcces, heureDebut, heureFin);		
			log.debug("verifyHoraireAcces successful");
			return result;
		} catch (Exception e) {
			log.error("verifyHoraireAcces  failed", e);
			throw e;
		}
		
	}
	@Override
	public RefCompteDto insertOrUpdateCompte(RefCompteDto refCompteDto)
			throws Exception {
		try {
			RefCompteDto  result = refCompteService.insertOrUpdate(refCompteDto);		
			log.debug("insertOrUpdate successful");
			return result;
		} catch (Exception e) {
			log.error("insertOrUpdate  failed", e);
			throw e;
		}
	}
	@Override
	public RefCompteDto findCompteByIndividu(Integer individuId)
			throws Exception {
		try {
			RefCompteDto  result = refCompteService.findByUser(individuId);		
			log.debug("findCompteByIndividu successful");
			return result;
		} catch (Exception e) {
			log.error("findCompteByIndividu  failed", e);
			throw e;
		}
	}
	
	

	
	
	

}
