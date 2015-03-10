/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.authentification.LoginBean.java] 
 * @author BELDI Jamel on : 1 mars 2014  10:57:20
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.authentification;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.SessionValues;
import dz.gov.mesrs.sii.refenrentiel.jsf.beans.compte.PasswordUtil;
import dz.gov.mesrs.sii.refenrentiel.jsf.beans.etablissement.EtablissementPgiBean;
import dz.gov.mesrs.sii.refenrentiel.jsf.beans.permission.MenuBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCompteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.service.LdapService;
import dz.gov.mesrs.sii.referentiel.business.service.RefCompteService;
import dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService;
import dz.gov.mesrs.sii.referentiel.business.service.RefHoraireAccessService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametrageService;
import dz.gov.mesrs.sii.referentiel.business.service.RefPlageAdresseService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author BELDI Jamel on : 1 mars 2014 10:57:20
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 1 mars 2014 10:57:27
	 */
	private static final long serialVersionUID = 1L;
	private RefIndividuDto user;
	private RefCompteDto compteDto;
	private ResourceBundle bundle;
	private ResourceBundle authentificationBundle;
	private String userName = null;
	private String password = null;
	private FacesMessage msg;
	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuServiceImpl;
	@ManagedProperty(value = "#{ldapServiceImpl}")
	private LdapService ldapServiceImpl = null;
	@ManagedProperty(value = "#{refCompteServiceImpl}")
	private RefCompteService refCompteServiceImpl;
	@ManagedProperty(value = "#{refIndividuService}")
	private RefIndividuService refIndividuService;
	@ManagedProperty(value = "#{refPlageAdresseServiceImpl}")
	private RefPlageAdresseService refPlageAdresseServiceImpl;
	@ManagedProperty(value = "#{refHoraireAccessServiceImpl}")
	private RefHoraireAccessService refHoraireAccessServiceImpl;
	private String msgPassword;

	private static final Log log = LogFactory.getLog(LoginBean.class);
//	@ManagedProperty(value = "#{menuBean}")
//	private MenuBean menuBean;
	@ManagedProperty(value = "#{refParametrageServiceImpl}")
	private RefParametrageService refParametrageServiceImpl;
	private RefEtablissementDto refEtablissementDto;
	@ManagedProperty(value = "#{refEtablissementServiceImpl}")
	private RefEtablissementService refEtablissementServiceImpl;
	@ManagedProperty(value = "#{etablissementPgiBean}")
	private EtablissementPgiBean etablissementPgiBean;

	

	public LoginBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		authentificationBundle = facesContext.getApplication()
				.getResourceBundle(facesContext,
						RefUtilConstant.AUTHENTIFICATION_MESSAGES_FILE_NAME);
		//menuBean = new MenuBean();
	}
	
	@PostConstruct
	public void init() {
		//ssoLogin();
	}
	
	/**
	 * 
	 */
	public void ssoLogin(){
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();
			userName = request.getRemoteUser();
			
			compteDto = refCompteServiceImpl.findByLogin(userName);
			
			user  = refIndividuServiceImpl.findById(compteDto.getIndividuId());
			
			refEtablissementDto = new RefEtablissementDto(compteDto.getEtabId(),
					compteDto.getEtabIdf(), compteDto.getEtabLcAr(), compteDto.getEtabLcFr(), compteDto.getEtabLlAr(),
					compteDto.getEtabLlFr(), compteDto.getEtabAncienCode());
			//menuBean.setCompte(compteDto);
			initSession();
			//loadAffectation();
			
		
	}
	
	

	/**
	 * [LoginBean.userName] Getter
	 * 
	 * @author BELDI Jamel on : 3 mars 2014 09:27:07
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * [LoginBean.userName] Setter
	 * 
	 * @author BELDI Jamel on : 3 mars 2014 09:27:07
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * [LoginBean.password] Getter
	 * 
	 * @author BELDI Jamel on : 3 mars 2014 09:27:07
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * [LoginBean.password] Setter
	 * 
	 * @author BELDI Jamel on : 3 mars 2014 09:27:07
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * [LoginBean.LoginBean()] Constructor
	 * 
	 * @author BELDI Jamel on : 1 mars 2014 10:57:20
	 */

	/**
	 * [LoginBean.refIndividuServiceImpl] Getter
	 * 
	 * @author BELDI Jamel on : 1 mars 2014 11:05:21
	 * @return the refIndividuServiceImpl
	 */
	public RefIndividuService getRefIndividuServiceImpl() {
		return refIndividuServiceImpl;
	}

	/**
	 * [LoginBean.refIndividuServiceImpl] Setter
	 * 
	 * @author BELDI Jamel on : 1 mars 2014 11:05:21
	 * @param refIndividuServiceImpl
	 *            the refIndividuServiceImpl to set
	 */
	public void setRefIndividuServiceImpl(
			RefIndividuService refIndividuServiceImpl) {
		this.refIndividuServiceImpl = refIndividuServiceImpl;
	}

	/**
	 * [LoginBean.ldapServiceImpl] Getter
	 * 
	 * @author BELDI Jamel on : 3 mars 2014 10:42:24
	 * @return the ldapServiceImpl
	 */
	public LdapService getLdapServiceImpl() {
		return ldapServiceImpl;
	}

	/**
	 * [LoginBean.ldapServiceImpl] Setter
	 * 
	 * @author BELDI Jamel on : 3 mars 2014 10:42:24
	 * @param ldapServiceImpl
	 *            the ldapServiceImpl to set
	 */
	public void setLdapServiceImpl(LdapService ldapServiceImpl) {
		this.ldapServiceImpl = ldapServiceImpl;
	}

	/**
	 * [LoginBean.refCompteServiceImpl] Getter
	 * 
	 * @author BELDI Jamel on : 3 mars 2014 10:42:24
	 * @return the refCompteServiceImpl
	 */
	public RefCompteService getRefCompteServiceImpl() {
		return refCompteServiceImpl;
	}

	/**
	 * [LoginBean.refCompteServiceImpl] Setter
	 * 
	 * @author BELDI Jamel on : 3 mars 2014 10:42:24
	 * @param refCompteServiceImpl
	 *            the refCompteServiceImpl to set
	 */
	public void setRefCompteServiceImpl(RefCompteService refCompteServiceImpl) {
		this.refCompteServiceImpl = refCompteServiceImpl;
	}

	/**
	 * [LoginBean.refIndividuService] Getter
	 * 
	 * @author BELDI Jamel on : 3 mars 2014 11:43:59
	 * @return the refIndividuService
	 */
	public RefIndividuService getRefIndividuService() {
		return refIndividuService;
	}

	/**
	 * [LoginBean.refIndividuService] Setter
	 * 
	 * @author BELDI Jamel on : 3 mars 2014 11:43:59
	 * @param refIndividuService
	 *            the refIndividuService to set
	 */
	public void setRefIndividuService(RefIndividuService refIndividuService) {
		this.refIndividuService = refIndividuService;
	}

	/**
	 * [LoginBean.user] Getter
	 * 
	 * @author BELDI Jamel on : 1 mars 2014 11:04:04
	 * @return the user
	 */
	public RefIndividuDto getUser() {
		
		return user;
	}

	/**
	 * [LoginBean.user] Setter
	 * 
	 * @author BELDI Jamel on : 1 mars 2014 11:04:04
	 * @param user
	 *            the user to set
	 */
	public void setUser(RefIndividuDto user) {
		this.user = user;
	}

	/**
	 * [LoginBean.compteDto] Getter
	 * 
	 * @author BELDI Jamel on : 3 mars 2014 10:21:34
	 * @return the compteDto
	 */
	public RefCompteDto getCompteDto() {
		return compteDto;
	}

	/**
	 * [LoginBean.msg] Getter
	 * 
	 * @author BELDI Jamel on : 3 mars 2014 10:53:25
	 * @return the msg
	 */
	public FacesMessage getMsg() {
		return msg;
	}

	/**
	 * [LoginBean.msg] Setter
	 * 
	 * @author BELDI Jamel on : 3 mars 2014 10:53:25
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(FacesMessage msg) {
		this.msg = msg;
	}

	/**
	 * [LoginBean.compteDto] Setter
	 * 
	 * @author BELDI Jamel on : 3 mars 2014 10:21:34
	 * @param compteDto
	 *            the compteDto to set
	 */
	public void setCompteDto(RefCompteDto compteDto) {
		this.compteDto = compteDto;
	}

	public String doLogin() {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			HttpServletRequest request = (HttpServletRequest) context
					.getRequest();
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					userName, password);
			// generate session if one doesn't exist
			request.getSession();
			token.setDetails(new WebAuthenticationDetails(request));
			// Authentication authenticatedUser =
			// authenticationManager.authenticate(token);
			boolean isAuthenticated = ldapServiceImpl.verifierUserLdap(
					userName, password);
			// String userHost = request.getRemoteHost();
			String userAddr = request.getRemoteAddr();
			Date heureAcces = new Date();
			log.info("userAddr" + userAddr);
			log.info("heureAcces r�el:" + heureAcces);
			log.info("Saisie Password:" + password);
			log.info("Saisie Password encrypt�e:"
					+ PasswordUtil.encryptAES(password));// encrypt(password,
															// PasswordUtil.KEY));

			// initialisation pour v�rifier l'horraire d'acc�s
			heureAcces.setDate(1);
			heureAcces.setMonth(0);
			heureAcces.setYear(70);
			log.info("heureAcces pour v�rifier l'horraire d'acc�s:"
					+ heureAcces);
			if (!isAuthenticated) {
				// Failed Authentication Ldap
				msg = new FacesMessage(
						authentificationBundle
								.getString("authentification_loginFailed")
								+ ": "
								+ authentificationBundle
										.getString("authentification_loginFailed_ldap"));
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				facesContext.addMessage(null, msg);
				return null;
			} else {
				// After Authentication LDAP
				if (userName != null && userName.equals(RefUtilConstant.SUPER_USER/*refParametrageServiceImpl.getParamConfiguration(UtilConstant.SUPER_USER_KEY, UtilConstant.CONFIGURATION_UTIL).getValue()*/)) {
					compteDto = refCompteServiceImpl.findByLogin(RefUtilConstant.LOGIN_ADMIN/*refParametrageServiceImpl.getParamConfiguration(UtilConstant.LOGIN_ADMIN_KEY, UtilConstant.CONFIGURATION_UTIL).getValue()*/);
					user = refIndividuServiceImpl.findById(compteDto
							.getIndividuId());
					// Management of List of roles
					List<String> listRole = new ArrayList<String>();
					// Find list of role of User
					listRole.add("USER");
					// set Spring Role
					Authentication authenticatedUser = ldapServiceImpl
							.authenticate(userName, isAuthenticated, listRole);
					request.getSession()
							.setAttribute(
									HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
									SecurityContextHolder.getContext());
					SecurityContextHolder.getContext().setAuthentication(
							authenticatedUser);
					log.info("//////////////login successful");
					//menuBean.setCompte(compteDto);
					initSession();
					
					return "success";
				} else {
					compteDto = refCompteServiceImpl.findByLogin(userName);
					if (compteDto == null) {
						// Failed compte not found on BD
						msg = new FacesMessage(
								authentificationBundle
										.getString("authentification_loginFailed")
										+ ": "
										+ authentificationBundle
												.getString("authentification_loginFailed_badCredentiels"));
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						facesContext.addMessage(null, msg);
						return null;
					} else if (!PasswordUtil.encryptAES(password).equals(
							compteDto.getMotPasse())) {// encrypt(password,
														// PasswordUtil.KEY)
						log.info("User Password encrypt�e:"
								+ compteDto.getMotPasse());
						// Failed compte found on BD but password is not valid
						msg = new FacesMessage(
								authentificationBundle
										.getString("authentification_loginFailed")
										+ ": "
										+ authentificationBundle
												.getString("authentification_loginFailed_password_incorrect"));
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						facesContext.addMessage(null, msg);
						return null;
					} else if (compteDto.getIdSituation() == null
							|| !compteDto.getIdSituation().equals(
									Integer.valueOf(272))) {
						// Failed compte found on BD but is not active
						msg = new FacesMessage(
								authentificationBundle
										.getString("authentification_loginFailed_accessDenied")
										+ ": "
										+ authentificationBundle
												.getString("authentification_loginFailed_disabledUser"));
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						facesContext.addMessage(null, msg);
						return null;
					} else if (compteDto.getPlageAdresseId() != null
							&& !refPlageAdresseServiceImpl.verifyUserIp(
									userAddr,
									compteDto.getPlageAdresseAdresseDebut(),
									compteDto.getPlageAdresseAdresseFin())) {

						// Failed compte found on BD active but off palge
						// d'adresse
						msg = new FacesMessage(
								authentificationBundle
										.getString("authentification_loginFailed_accessDenied")
										+ ": "
										+ authentificationBundle
												.getString("authentification_loginFailed_off_adress"));
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						facesContext.addMessage(null, msg);
						return null;
					} else if (compteDto.getHoraireAccessId() != null
							&& !refHoraireAccessServiceImpl.verifyHoraireAcces(
									heureAcces,
									compteDto.getHoraireAccessHeureDebut(),
									compteDto.getHoraireAccessHeureFin())) {
						log.info("compteDto.getHoraireAccessId()"
								+ compteDto.getHoraireAccessId());
						log.info("heureAcces" + heureAcces);
						log.info("compteDto.getHoraireAccessHeureDebut()"
								+ compteDto.getHoraireAccessHeureDebut());
						log.info("compteDto.getHoraireAccessHeureFin()"
								+ compteDto.getHoraireAccessHeureFin());
						// Failed compte found on BD active but off palge
						// d'adresse
						msg = new FacesMessage(
								authentificationBundle
										.getString("authentification_loginFailed_accessDenied")
										+ ": "
										+ authentificationBundle
												.getString("authentification_loginFailed_off_horaire_acces"));
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						facesContext.addMessage(null, msg);
						return null;
					}

					else {
						if (/*compteDto.getChangementMotPasse() != null
								&&*/ compteDto.getChangementMotPasse()) {
							setMsgPassword(authentificationBundle
									.getString("authentification_changement_password_msg"));
						}
						user = refIndividuServiceImpl.findById(compteDto
								.getIndividuId());
						// Management of List of roles
						List<String> listRole = new ArrayList<String>();
						// Find list of role of User
						listRole.add("USER");
						// set Spring Role
						Authentication authenticatedUser = ldapServiceImpl
								.authenticate(userName, isAuthenticated,
										listRole);
						request.getSession()
								.setAttribute(
										HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
										SecurityContextHolder.getContext());
						SecurityContextHolder.getContext().setAuthentication(
								authenticatedUser);
						log.info("//////////////login successful");
						initSession();
						//menuBean.setCompte(compteDto);
						return "success";
					}

				}
			}
		} catch (BadCredentialsException e) {
			msg = new FacesMessage(
					authentificationBundle
							.getString("authentification_loginFailed")
							+ ": "
							+ authentificationBundle
									.getString("authentification_loginFailed_accessDenied"));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		} catch (Exception e) {
			msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;

		}

	}
	
	
	

//	/**
//	 * 
//	 * [LoginBean.menuBean] Getter
//	 * 
//	 * @author MAKERRI Sofiane on : 4 mars 2014 08:40:27
//	 * @return the menuBean
//	 */
//	public MenuBean getMenuBean() {
//		return menuBean;
//	}
//
//	/**
//	 * [LoginBean.menuBean] Setter
//	 * 
//	 * @author MAKERRI Sofiane on : 4 mars 2014 08:40:27
//	 * @param menuBean
//	 *            the menuBean to set
//	 */
//	public void setMenuBean(MenuBean menuBean) {
//		this.menuBean = menuBean;
//	}

	/*
	 * [LoginBean.refPlageAdresseServiceImpl] Getter
	 * 
	 * @author BELDI Jamel on : 4 mars 2014 11:12:50
	 * 
	 * @return the refPlageAdresseServiceImpl
	 */
	public RefPlageAdresseService getRefPlageAdresseServiceImpl() {
		return refPlageAdresseServiceImpl;
	}

	/**
	 * [LoginBean.refPlageAdresseServiceImpl] Setter
	 * 
	 * @author BELDI Jamel on : 4 mars 2014 11:12:50
	 * @param refPlageAdresseServiceImpl
	 *            the refPlageAdresseServiceImpl to set
	 */
	public void setRefPlageAdresseServiceImpl(
			RefPlageAdresseService refPlageAdresseServiceImpl) {
		this.refPlageAdresseServiceImpl = refPlageAdresseServiceImpl;
	}

	/**
	 * [LoginBean.refHoraireAccessServiceImpl] Getter
	 * 
	 * @author BELDI Jamel on : 4 mars 2014 15:07:40
	 * @return the refHoraireAccessServiceImpl
	 */
	public RefHoraireAccessService getRefHoraireAccessServiceImpl() {
		return refHoraireAccessServiceImpl;
	}

	/**
	 * [LoginBean.refHoraireAccessServiceImpl] Setter
	 * 
	 * @author BELDI Jamel on : 4 mars 2014 15:07:40
	 * @param refHoraireAccessServiceImpl
	 *            the refHoraireAccessServiceImpl to set
	 */
	public void setRefHoraireAccessServiceImpl(
			RefHoraireAccessService refHoraireAccessServiceImpl) {
		this.refHoraireAccessServiceImpl = refHoraireAccessServiceImpl;
	}

	/**
	 * [LoginBean.msgPassword] Getter
	 * 
	 * @author BELDI Jamel on : 11 mars 2014 12:32:53
	 * @return the msgPassword
	 */
	public String getMsgPassword() {
		return msgPassword;
	}

	/**
	 * [LoginBean.msgPassword] Setter
	 * 
	 * @author BELDI Jamel on : 11 mars 2014 12:32:53
	 * @param msgPassword
	 *            the msgPassword to set
	 */
	public void setMsgPassword(String msgPassword) {
		this.msgPassword = msgPassword;
	}

	/**
	 * [LoginBean.refParametrageServiceImpl] Getter 
	 * @author BELDI Jamel on : 7 avr. 2014  11:15:04
	 * @return the refParametrageServiceImpl
	 */
	public RefParametrageService getRefParametrageServiceImpl() {
		return refParametrageServiceImpl;
	}

	/**
	 * [LoginBean.refParametrageServiceImpl] Setter 
	 * @author BELDI Jamel on : 7 avr. 2014  11:15:04
	 * @param refParametrageServiceImpl the refParametrageServiceImpl to set
	 */
	public void setRefParametrageServiceImpl(
			RefParametrageService refParametrageServiceImpl) {
		this.refParametrageServiceImpl = refParametrageServiceImpl;
	}
	
	

	/**
	 * [LoginBean.refEtablissementDto] Getter 
	 * @author MAKERRI Sofiane on : 14 avr. 2014  15:41:04
	 * @return the refEtablissementDto
	 */
	public RefEtablissementDto getRefEtablissementDto() {
		return refEtablissementDto;
	}

	/**
	 * [LoginBean.refEtablissementDto] Setter 
	 * @author MAKERRI Sofiane on : 14 avr. 2014  15:41:04
	 * @param refEtablissementDto the refEtablissementDto to set
	 */
	public void setRefEtablissementDto(RefEtablissementDto refEtablissementDto) {
		this.refEtablissementDto = refEtablissementDto;
	}

	/**
	 * [LoginBean.refEtablissementServiceImpl] Getter 
	 * @author MAKERRI Sofiane on : 14 avr. 2014  15:41:04
	 * @return the refEtablissementServiceImpl
	 */
	public RefEtablissementService getRefEtablissementServiceImpl() {
		return refEtablissementServiceImpl;
	}

	/**
	 * [LoginBean.refEtablissementServiceImpl] Setter 
	 * @author MAKERRI Sofiane on : 14 avr. 2014  15:41:04
	 * @param refEtablissementServiceImpl the refEtablissementServiceImpl to set
	 */
	public void setRefEtablissementServiceImpl(
			RefEtablissementService refEtablissementServiceImpl) {
		this.refEtablissementServiceImpl = refEtablissementServiceImpl;
	}

	/**
	 * [LoginBean.etablissementPgiBean] Getter 
	 * @author MAKERRI Sofiane on : 14 avr. 2014  15:41:04
	 * @return the etablissementPgiBean
	 */
	public EtablissementPgiBean getEtablissementPgiBean() {
		return etablissementPgiBean;
	}

	/**
	 * [LoginBean.etablissementPgiBean] Setter 
	 * @author MAKERRI Sofiane on : 14 avr. 2014  15:41:04
	 * @param etablissementPgiBean the etablissementPgiBean to set
	 */
	public void setEtablissementPgiBean(EtablissementPgiBean etablissementPgiBean) {
		this.etablissementPgiBean = etablissementPgiBean;
	}

	/**
	 * [LoginBean.initSession] Method 
	 * @author MAKERRI Sofiane  on : 14 avr. 2014  16:05:40
	 */
	public void initSession() {
//		String etabCode = refParametrageServiceImpl.getParamConfiguration(UtilConstant.ETABLISSEMENT_CODE_KEY, UtilConstant.CONFIGURATION_UTIL).getValue();
//		if(etabCode != null) {
//			refEtablissementDto = refEtablissementServiceImpl.findByIdentifiant(etabCode);
//			if(refEtablissementDto == null) {
//				refEtablissementDto = new RefEtablissementDto();
//			}
//			etablissementPgiBean.setRefEtablissementDto(refEtablissementDto);
//			SessionValues.setIdEtablissement(refEtablissementDto.getId());
//			SessionValues.setIdfEtablissement(refEtablissementDto.getIdentifiant());
//			SessionValues.setLcLatinEtablissement(refEtablissementDto.getLcEtablissementLatin());
//			SessionValues.setIdCompte(compteDto.getIdCompte());
//		}
		if(compteDto != null && compteDto.getEtabId() != null) {
			SessionValues.setIdCompte(compteDto.getIdCompte());
			refEtablissementDto = refEtablissementServiceImpl.findById(compteDto.getEtabId());
			if(refEtablissementDto == null) {
				refEtablissementDto = new RefEtablissementDto();
			}
			etablissementPgiBean.setRefEtablissementDto(refEtablissementDto);
			SessionValues.setIdEtablissement(refEtablissementDto.getId());
			SessionValues.setIdfEtablissement(refEtablissementDto.getIdentifiant());
			SessionValues.setLcLatinEtablissement(refEtablissementDto.getLcEtablissementLatin());
		} else {
			etablissementPgiBean.setRefEtablissementDto(null);
			SessionValues.setIdEtablissement(null);
			SessionValues.setIdfEtablissement(null);
			SessionValues.setLcLatinEtablissement(null);
		}
	}
	
	

	
	
	


	
}
