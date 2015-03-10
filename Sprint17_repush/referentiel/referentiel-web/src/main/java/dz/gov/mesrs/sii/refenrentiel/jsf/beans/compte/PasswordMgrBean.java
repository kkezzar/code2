/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.compte.CompteMgrBean.java] 
 * @author BELDI Jamel on : 23 f�vr. 2014  09:15:14
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.compte;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.authentification.LoginBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCompteDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefCompteService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author BELDI Jamel  on : 23 f�vr. 2014  09:15:14
 */
@ManagedBean(name = "passwordMgrBean")
@RequestScoped
public class PasswordMgrBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(PasswordMgrBean.class);
	
	
	
	private RefCompteDto refCompteDto;
	private ResourceBundle bundle;
	private ResourceBundle compteBundle;
	
	@ManagedProperty(value = "#{refCompteServiceImpl}")
	private RefCompteService refCompteServiceImpl;
	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;
	@ManagedProperty(value = "#{passwordCrudBean}")
	private PasswordCrudBean passwordCrudBean;
	
	/**
	 * [PasswordMgrBean.loginBean] Getter 
	 * @author BELDI Jamel on : 1 mars 2014  11:08:38
	 * @return the loginBean
	 */
	public LoginBean getLoginBean() {
		return loginBean;
	}





	/**
	 * [PasswordMgrBean.loginBean] Setter 
	 * @author BELDI Jamel on : 1 mars 2014  11:08:38
	 * @param loginBean the loginBean to set
	 */
	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}





	/**
	 * [CompteMgrBean.CompteMgrBean()] Constructor
	 * @author BELDI Jamel  on : 23 f�vr. 2014  09:15:15	
	 */
	public PasswordMgrBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		compteBundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMPTE_MESSAGES_FILE_NAME);
		refCompteDto = new RefCompteDto();
		
	}

	@PostConstruct
	public void init(){
		
		//refCompteDto = refCompteServiceImpl.findByUser(loginBean.getUser().getIdentifiant());
	}
	
	
	/**
	 * [CompteMgrBean.refCompteDto] Getter 
	 * @author BELDI Jamel on : 23 f�vr. 2014  10:31:06
	 * @return the refCompteDto
	 */
	public RefCompteDto getRefCompteDto() {
		return refCompteDto;
	}

	/**
	 * [CompteMgrBean.refCompteDto] Setter 
	 * @author BELDI Jamel on : 23 f�vr. 2014  10:31:06
	 * @param refCompteDto the refCompteDto to set
	 */
	public void setRefCompteDto(RefCompteDto refCompteDto) {
		this.refCompteDto = refCompteDto;
	}

	

	/**
	 * [CompteMgrBean.refCompteServiceImpl] Getter 
	 * @author BELDI Jamel on : 23 f�vr. 2014  10:31:06
	 * @return the refCompteServiceImpl
	 */
	public RefCompteService getRefCompteServiceImpl() {
		return refCompteServiceImpl;
	}

	/**
	 * [CompteMgrBean.refCompteServiceImpl] Setter 
	 * @author BELDI Jamel on : 23 f�vr. 2014  10:31:06
	 * @param refCompteServiceImpl the refCompteServiceImpl to set
	 */
	public void setRefCompteServiceImpl(RefCompteService refCompteServiceImpl) {
		this.refCompteServiceImpl = refCompteServiceImpl;
	}
	
	

	


	/**
	 * Check Password on Focus
	 * @param event
	 */
	public void checkPassword(AjaxBehaviorEvent event) {
		log.info("*******************************************check Password******************************************* ");
		
		if(refCompteDto.getMotPasse()==null|| refCompteDto.getMotPasse().length()<6){
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(compteBundle.getString("compte_password_error") );
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}
	/**
	 * Check Confirm Password on Focus
	 * @param event
	 */
	public void checkConfirmPassword(AjaxBehaviorEvent event) {
		log.info("*******************************************check Confirm Password******************************************* ");
		// verify confirm password
		if (refCompteDto.getMotPasse()==null|| refCompteDto.getMotPasse().length()<6||!refCompteDto.getMotPasse().equals(refCompteDto.getConfirmationMotPasse())) {
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(compteBundle.getString("compte_confirm_password_error") );
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
		}
	}
	
	
	
	/**
	 * Change Password
	 * [CompteMgrBean.changeMyPassword] Method 
	 * @author BELDI Jamel  on : 26 f�vr. 2014  11:22:51
	 */
	public void changeMyPassword() {
		FacesMessage msg = new FacesMessage();
		try {
			
			// verify confirm password
			if (refCompteDto.getMotPasse()==null|| refCompteDto.getMotPasse().length()<6||!refCompteDto.getMotPasse().equals(refCompteDto.getConfirmationMotPasse())) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(compteBundle.getString("compte_confirm_password_error") );
				FacesContext.getCurrentInstance().addMessage(null, msg);
				
			}else{
				
			RefCompteDto refCompteDtoOld = refCompteServiceImpl.findByUser(loginBean.getUser().getId());
			//verify actual Password
			if(!PasswordUtil.encryptAES(refCompteDto.getMotPasseActuel()).equals(refCompteDtoOld.getMotPasse())){
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(compteBundle.getString("compt_password_actuel_incorrect") );
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} 
			
			//compare width actual Password
			else if(PasswordUtil.encryptAES(refCompteDto.getMotPasse()).equals(refCompteDtoOld.getMotPasse())){
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(compteBundle.getString("compt_password_different") );
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} 

			else
			{
			//Build the RefCompteDto
			refCompteDto.setAccessJourFerie(refCompteDtoOld.getAccessJourFerie());
			refCompteDto.setChangementMotPasse(false);
			refCompteDto.setDateDebut(refCompteDtoOld.getDateDebut());
			refCompteDto.setDateFin(refCompteDtoOld.getDateFin());
			refCompteDto.setHoraireAccessId(refCompteDtoOld.getHoraireAccessId());
			refCompteDto.setIdCompte(refCompteDtoOld.getIdCompte());
			refCompteDto.setIndividuIdentifiant(refCompteDtoOld.getIndividuIdentifiant());
			refCompteDto.setNomUtilisateur(refCompteDtoOld.getNomUtilisateur());
			refCompteDto.setPlageAdresseId(refCompteDtoOld.getPlageAdresseId());
			refCompteDto.setPremiereSession(refCompteDtoOld.getPremiereSession());
		    refCompteDto.setMotPasse(PasswordUtil.encryptAES(refCompteDto.getMotPasse()));//, PasswordUtil.KEY));
		    refCompteDto.setEtabId(refCompteDtoOld.getEtabId());
		    refCompteDto.setIndividuId(refCompteDtoOld.getIndividuId());
		    refCompteDto.setAdmin(refCompteDtoOld.getAdmin());
		  //persist data	
		    refCompteServiceImpl.update(refCompteDto);
		    loginBean.setMsgPassword(null);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_modification"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				refCompteDto = new RefCompteDto();
			}
		}
			
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.info(e.getMessage());
		}
		
	}





	/**
	 * [PasswordMgrBean.passwordCrudBean] Getter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  14:19:04
	 * @return the passwordCrudBean
	 */
	public PasswordCrudBean getPasswordCrudBean() {
		return passwordCrudBean;
	}





	/**
	 * [PasswordMgrBean.passwordCrudBean] Setter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  14:19:04
	 * @param passwordCrudBean the passwordCrudBean to set
	 */
	public void setPasswordCrudBean(PasswordCrudBean passwordCrudBean) {
		this.passwordCrudBean = passwordCrudBean;
	}
	

	/**
	 * [PasswordMgrBean.crud] Method 
	 * @author MAKERRI Sofiane  on : 22 mars 2014  14:20:13
	 * @param createAllow
	 * @param editAllow
	 * @param deleteAllow
	 */
	public void crud(boolean createAllow, boolean editAllow, boolean deleteAllow) {
		passwordCrudBean.setEditAllow(editAllow);
		passwordCrudBean.setCreateAllow(createAllow);
		passwordCrudBean.setDeleteAllow(deleteAllow);
	}
	
	
	
}
