/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.compte.CompteMgrBean.java] 
 * @author BELDI Jamel on : 24 f�vr. 2014  09:15:14
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.compte;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ComponentSystemEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.SessionValues;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCompteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefCompteService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author BELDI Jamel on : 24 f�vr. 2014 09:15:14
 */
@ManagedBean(name = "compteMgrBean")
@RequestScoped
public class CompteMgrBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(CompteMgrBean.class);
	private String searchInput;
	private RefCompteDto searchDto;
	private List<RefCompteDto> listRefCompteDto;
	private RefCompteDto refCompteDto;
	private ResourceBundle bundle;
	private ResourceBundle compteBundle;
	private boolean updateMode;
	@ManagedProperty(value = "#{refCompteServiceImpl}")
	private RefCompteService refCompteServiceImpl;
	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuServiceImpl;
	private RefCompteDto refCompteDtoInitial;
	private RefIndividuDto refIndividuDto;
	@ManagedProperty("#{param.id}")
	private String id;
	@ManagedProperty("#{flash}")
	private Flash flash;
	@ManagedProperty("#{param.load}")
	private String load;
	private Boolean loadIndividu;
	@ManagedProperty("#{param.individuId}")
	private String individuId;
	private Integer idIndividu;
	@ManagedProperty("#{param.individuNom}")
	private String individuNom;
	@ManagedProperty("#{param.individuPrenom}")
	private String individuPrenom;
	@ManagedProperty(value = "#{compteCrudBean}")
	private CompteCrudBean compteCrudBean;
	private List<RefIndividuDto> listRefIndividuDto;
	private List<RefIndividuDto> filtredLlistIndividu;
	private Boolean showEtabList;
	@ManagedProperty("#{param.etabId}")
	private String etabId;
	private Integer idEtab;
	private Boolean admin;
	@ManagedProperty("#{param.searchNom}")
	private String searchNom;
	@ManagedProperty("#{param.searchPrenom}")
	private String searchPrenom;

	/**
	 * [CompteMgrBean.CompteMgrBean()] Constructor
	 * 
	 * @author BELDI Jamel on : 24 f�vr. 2014 09:15:15
	 */
	public CompteMgrBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		compteBundle = facesContext.getApplication().getResourceBundle(
				facesContext, RefUtilConstant.COMPTE_MESSAGES_FILE_NAME);
		searchDto = new RefCompteDto();

	}

	@PostConstruct
	public void init() {

		refCompteDto = new RefCompteDto();

		if (id != null && !id.equals("null") || updateMode) {
			refCompteDto = refCompteServiceImpl.findById(Integer.valueOf(id));
			if (refCompteDto != null) {
				// if (refCompteDto.getIndividuId() != null) {
				// refIndividuDto = refIndividuServiceImpl
				// .findById(refCompteDto.getIndividuId());
				// }
				if (idEtab == null) {
					setIdEtab(refCompteDto.getEtabId());
				}
				refIndividuDto = new RefIndividuDto();
				refIndividuDto.setIdentifiant(refCompteDto
						.getIndividuIdentifiant());
				refIndividuDto.setNomArabe(refCompteDto.getIndividuNomArabe());
				refIndividuDto.setNomLatin(refCompteDto.getIndividuNomLatin());
				refIndividuDto.setPrenomArabe(refCompteDto
						.getIndividuPrenomArabe());
				refIndividuDto.setPrenomLatin(refCompteDto
						.getIndividuPrenomLatin());
			}
			updateMode = true;
		} else {

			pullValuesFromFlash(null);
			updateMode = false;
		}
		if (loadIndividu) {
			loadIndividuList();
		}
		admin = (SessionValues.getIdEtablissement() == null);
	}

	/**
	 * [CompteMgrBean.loadIndividuList] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 12:17:37
	 */
	public void loadIndividuList() {
		listRefIndividuDto = refCompteServiceImpl
				.findByEtablissement(searchNom, searchPrenom, SessionValues.getIdEtablissement());
		if (listRefIndividuDto != null)
			log.info("listRefIndividuDto size == " + listRefIndividuDto.size());
	}

	/**
	 * [CompteMgrBean.searchInput] Getter
	 * 
	 * @author BELDI Jamel on : 24 f�vr. 2014 10:31:06
	 * @return the searchInput
	 */
	public String getSearchInput() {
		return searchInput;
	}

	/**
	 * [CompteMgrBean.searchInput] Setter
	 * 
	 * @author BELDI Jamel on : 24 f�vr. 2014 10:31:06
	 * @param searchInput
	 *            the searchInput to set
	 */
	public void setSearchInput(String searchInput) {
		this.searchInput = searchInput;
	}

	/**
	 * [CompteMgrBean.searchDto] Getter
	 * 
	 * @author BELDI Jamel on : 24 f�vr. 2014 10:31:06
	 * @return the searchDto
	 */
	public RefCompteDto getSearchDto() {
		return searchDto;
	}

	/**
	 * [CompteMgrBean.searchDto] Setter
	 * 
	 * @author BELDI Jamel on : 24 f�vr. 2014 10:31:06
	 * @param searchDto
	 *            the searchDto to set
	 */
	public void setSearchDto(RefCompteDto searchDto) {
		this.searchDto = searchDto;
	}

	/**
	 * [CompteMgrBean.listRefCompteDto] Getter
	 * 
	 * @author BELDI Jamel on : 24 f�vr. 2014 10:31:06
	 * @return the listRefCompteDto
	 */
	public List<RefCompteDto> getListRefCompteDto() {
		return listRefCompteDto;
	}

	/**
	 * [CompteMgrBean.listRefCompteDto] Setter
	 * 
	 * @author BELDI Jamel on : 24 f�vr. 2014 10:31:06
	 * @param listRefCompteDto
	 *            the listRefCompteDto to set
	 */
	public void setListRefCompteDto(List<RefCompteDto> listRefCompteDto) {
		this.listRefCompteDto = listRefCompteDto;
	}

	/**
	 * [CompteMgrBean.refCompteDto] Getter
	 * 
	 * @author BELDI Jamel on : 24 f�vr. 2014 10:31:06
	 * @return the refCompteDto
	 */
	public RefCompteDto getRefCompteDto() {
		return refCompteDto;
	}

	/**
	 * [CompteMgrBean.refCompteDto] Setter
	 * 
	 * @author BELDI Jamel on : 24 f�vr. 2014 10:31:06
	 * @param refCompteDto
	 *            the refCompteDto to set
	 */
	public void setRefCompteDto(RefCompteDto refCompteDto) {
		this.refCompteDto = refCompteDto;
	}

	/**
	 * [CompteMgrBean.updateMode] Getter
	 * 
	 * @author BELDI Jamel on : 24 f�vr. 2014 10:31:06
	 * @return the updateMode
	 */
	public boolean getUpdateMode() {
		return updateMode;
	}

	/**
	 * [CompteMgrBean.updateMode] Setter
	 * 
	 * @author BELDI Jamel on : 24 f�vr. 2014 10:31:06
	 * @param updateMode
	 *            the updateMode to set
	 */
	public void setUpdateMode(boolean updateMode) {
		this.updateMode = updateMode;
	}

	/**
	 * [CompteMgrBean.refCompteServiceImpl] Getter
	 * 
	 * @author BELDI Jamel on : 24 f�vr. 2014 10:31:06
	 * @return the refCompteServiceImpl
	 */
	public RefCompteService getRefCompteServiceImpl() {
		return refCompteServiceImpl;
	}

	/**
	 * [CompteMgrBean.refCompteServiceImpl] Setter
	 * 
	 * @author BELDI Jamel on : 24 f�vr. 2014 10:31:06
	 * @param refCompteServiceImpl
	 *            the refCompteServiceImpl to set
	 */
	public void setRefCompteServiceImpl(RefCompteService refCompteServiceImpl) {
		this.refCompteServiceImpl = refCompteServiceImpl;
	}

	/**
	 * [CompteMgrBean.idf] Getter
	 * 
	 * @author BELDI Jamel on : 25 f�vr. 2014 19:52:54
	 * @return the idf
	 */
	public String getId() {
		return id;
	}

	/**
	 * [CompteMgrBean.idf] Setter
	 * 
	 * @author BELDI Jamel on : 25 f�vr. 2014 19:52:54
	 * @param idf
	 *            the idf to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * [CompteMgrBean.flash] Getter
	 * 
	 * @author BELDI Jamel on : 26 f�vr. 2014 09:19:08
	 * @return the flash
	 */
	public Flash getFlash() {
		return flash;
	}

	/**
	 * [CompteMgrBean.flash] Setter
	 * 
	 * @author BELDI Jamel on : 26 f�vr. 2014 09:19:08
	 * @param flash
	 *            the flash to set
	 */
	public void setFlash(Flash flash) {
		this.flash = flash;
	}

	/**
	 * to search account page
	 * 
	 * @param event
	 * @return
	 */
	public String toSearch() {
		return "toSearchCompte";
	}

	/**
	 * Check Password on Focus
	 * 
	 * @param event
	 */
	public void checkPassword(AjaxBehaviorEvent event) {
		log.info("*******************************************check Password******************************************* ");

		if (refCompteDto.getMotPasse() == null
				|| refCompteDto.getMotPasse().length() < 6) {
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(compteBundle.getString("compte_password_error"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	/**
	 * Check Confirm Password on Focus
	 * 
	 * @param event
	 */
	public void checkConfirmPassword(AjaxBehaviorEvent event) {
		log.info("*******************************************check Confirm Password******************************************* ");
		// verify confirm password
		if (refCompteDto.getMotPasse() == null
				|| refCompteDto.getMotPasse().length() < 6
				|| !refCompteDto.getMotPasse().equals(
						refCompteDto.getConfirmationMotPasse())) {
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(compteBundle
					.getString("compte_confirm_password_error"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}

	/**
	 * save or update account [CompteMgrBean.saveCompte] Method
	 * 
	 * @author BELDI Jamel on : 26 f�vr. 2014 10:28:39
	 * @return
	 */
	public String saveCompte() {
		FacesMessage msg = new FacesMessage();
		if (SessionValues.getIdEtablissement() != null) {
			refCompteDto.setEtabId(SessionValues.getIdEtablissement());
		} else if (idEtab != null) {
			refCompteDto.setEtabId(idEtab);
		} else {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(compteBundle
					.getString("compte_etablissement_empty_error"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}
		if (admin) {
			refCompteDto.setAdmin(true);
		}
		try {
//			refCompteDto.setIndividuId(refIndividuDto.getId());
//			refCompteDto
//					.setIndividuIdentifiant(refIndividuDto.getIdentifiant());
//			refCompteDto.setIndividuNomArabe(refIndividuDto.getNomArabe());
//			refCompteDto.setIndividuNomLatin(refIndividuDto.getNomLatin());
//			refCompteDto
//					.setIndividuPrenomArabe(refIndividuDto.getPrenomArabe());
//			refCompteDto
//					.setIndividuPrenomLatin(refIndividuDto.getPrenomLatin());
			if (refCompteDto.getEtabId() == null) {
				refCompteDto.setEtabId(SessionValues.getIdEtablissement());
			}

			if (updateMode) {
				if (refCompteDto.getDateDebut() != null
						&& refCompteDto.getDateFin() != null
						&& refCompteDto.getDateDebut().after(
								refCompteDto.getDateFin())) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(compteBundle
							.getString("compte_date_debut_fin_error"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
				} else {
					refCompteServiceImpl.update(refCompteDto);
					msg.setSeverity(FacesMessage.SEVERITY_INFO);
					msg.setSummary(bundle.getString("msg_success") + ": "
							+ bundle.getString("msg_success_modification"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			} else {
				// New account
				// verify confirm password
				if (refCompteDto.getMotPasse() == null
						|| refCompteDto.getMotPasse().length() < 6
						|| !refCompteDto.getMotPasse().equals(
								refCompteDto.getConfirmationMotPasse())) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(compteBundle
							.getString("compte_confirm_password_error"));
					FacesContext.getCurrentInstance().addMessage(null, msg);

				} else if (refCompteDto.getDateDebut() != null
						&& refCompteDto.getDateFin() != null
						&& refCompteDto.getDateDebut().after(
								refCompteDto.getDateFin())) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(compteBundle
							.getString("compte_date_debut_fin_error"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
				} else {
					// verify if user has an account
					if (refCompteServiceImpl.findByUser(refCompteDto
							.getIndividuId()) != null) {
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						msg.setSummary(compteBundle
								.getString("compte_user_has_account_error"));
						FacesContext.getCurrentInstance().addMessage(null, msg);
					}
					// verify if login is used
					else if (refCompteServiceImpl.findByLogin(refCompteDto
							.getNomUtilisateur()) != null) {
						msg.setSeverity(FacesMessage.SEVERITY_WARN);
						msg.setSummary(compteBundle
								.getString("compte_login_is_used_error"));
						FacesContext.getCurrentInstance().addMessage(null, msg);
					} else {
						// persist data
						refCompteDto.setMotPasse(PasswordUtil
								.encryptAES(refCompteDto.getMotPasse()));// ,
						if (individuPrenom == null || individuNom == null) {
							msg.setSeverity(FacesMessage.SEVERITY_ERROR);
							msg.setSummary(bundle.getString("error_echec")
									+ ": "
									+ compteBundle.getString("compte_error_individu_not_select"));
							FacesContext.getCurrentInstance().addMessage(null,
									msg);
							return null;
						}
//						String username = generateUsername();
						if (refCompteDto.getAdmin() == null) {
							refCompteDto.setAdmin(false);
						}
						/*if (username == null) {
							msg.setSeverity(FacesMessage.SEVERITY_ERROR);
							msg.setSummary(bundle.getString("error_echec")
									+ ": "
									+ bundle.getString("error_generation_identify"));
							FacesContext.getCurrentInstance().addMessage(null,
									msg);
							return null;
						}*/ /*
						 * else if (username.length() >
						 * UtilConstant.CODIFICATION_STRUCTURE_LENGTH) {
						 * msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						 * msg.setSummary(bundle.getString("error_echec") + ": "
						 * +
						 * bundle.getString("error_identify_max_legnt_affected"
						 * ));
						 * FacesContext.getCurrentInstance().addMessage(null,
						 * msg); return null; }
						 */
						//refCompteDto.setNomUtilisateur(username);
						refCompteDto.setIndividuNomLatin(individuNom);
						refCompteDto.setIndividuPrenomLatin(individuPrenom);
						refCompteDto.setIndividuId(idIndividu);
						// PasswordUtil.KEY));
						Integer id = refCompteServiceImpl.save(refCompteDto);
						refCompteDto.setIdCompte(id);
						setId(id.toString());
						setUpdateMode(true);
						msg.setSeverity(FacesMessage.SEVERITY_INFO);
						msg.setSummary(bundle.getString("msg_success")
								+ ": "
								+ bundle.getString("msg_success_enregistrement"));
						FacesContext.getCurrentInstance().addMessage(null, msg);
					}
				}
			}
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.info(e.getMessage());
			
		}
		flash.put("refCompteDto", refCompteDto);
		flash.keep("refCompteDto");
		refIndividuDto.setNomLatin(individuNom);
		refIndividuDto.setPrenomLatin(individuPrenom);
		flash.put("refIndividuDto", refIndividuDto);
		flash.keep("refIndividuDto");
		flash.setKeepMessages(true);
		return "compteEdit?faces-redirect=true&id=" + id;
	}

	/**
	 * Change Password [CompteMgrBean.changePassword] Method
	 * 
	 * @author BELDI Jamel on : 26 f�vr. 2014 11:22:51
	 */
	public void changePassword() {
		FacesMessage msg = new FacesMessage();
		try {
			refCompteDto
					.setIndividuIdentifiant(refIndividuDto.getIdentifiant());
			refCompteDto.setIndividuNomArabe(refIndividuDto.getNomArabe());
			refCompteDto.setIndividuNomLatin(refIndividuDto.getNomLatin());
			refCompteDto
					.setIndividuPrenomArabe(refIndividuDto.getPrenomArabe());
			refCompteDto
					.setIndividuPrenomLatin(refIndividuDto.getPrenomLatin());
			// verify confirm password
			if (refCompteDto.getMotPasse() == null
					|| refCompteDto.getMotPasse().length() < 6
					|| !refCompteDto.getMotPasse().equals(
							refCompteDto.getConfirmationMotPasse())) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(compteBundle
						.getString("compte_confirm_password_error"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

			} else {
				// persist data
				refCompteDto.setMotPasse(PasswordUtil.encryptAES(refCompteDto
						.getMotPasse()));// , PasswordUtil.KEY));
				refCompteServiceImpl.update(refCompteDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_modification"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
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
	 * [CompteMgrBean.refCompteDtoInitial] Getter
	 * 
	 * @author BELDI Jamel on : 25 f�vr. 2014 11:59:20
	 * @return the refCompteDtoInitial
	 */
	public RefCompteDto getRefCompteDtoInitial() {

		return refCompteDtoInitial;
	}

	/**
	 * [CompteMgrBean.refCompteDtoInitial] Setter
	 * 
	 * @author BELDI Jamel on : 25 f�vr. 2014 11:59:20
	 * @param refCompteDtoInitial
	 *            the refCompteDtoInitial to set
	 */
	public void setRefCompteDtoInitial(RefCompteDto refCompteDtoInitial) {
		this.refCompteDtoInitial = refCompteDtoInitial;
	}

	/**
	 * [CompteMgrBean.refIndividuDto] Getter
	 * 
	 * @author BELDI Jamel on : 25 f�vr. 2014 14:41:59
	 * @return the refIndividuDto
	 */
	public RefIndividuDto getRefIndividuDto() {
		return refIndividuDto;
	}

	/**
	 * [CompteMgrBean.refIndividuDto] Setter
	 * 
	 * @author BELDI Jamel on : 25 f�vr. 2014 14:41:59
	 * @param refIndividuDto
	 *            the refIndividuDto to set
	 */
	public void setRefIndividuDto(RefIndividuDto refIndividuDto) {
		this.refIndividuDto = refIndividuDto;
	}

	/**
	 * System event called before view rendering used to pull values from flash
	 * and set to bean properties
	 * 
	 * @param e
	 */
	public void pullValuesFromFlash(ComponentSystemEvent e) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
		refCompteDto = (RefCompteDto) flash.get("refCompteDto");
		flash.keep("refCompteDto");
		refIndividuDto = (RefIndividuDto) flash.get("refIndividuDto");
		flash.keep("refIndividuDto");
		if (refCompteDto == null) {
			refCompteDto = new RefCompteDto();
		}
		if (refIndividuDto == null) {
			refIndividuDto = new RefIndividuDto();
		}
		log.info("******************************pullValuesFromFlash: "
				+ listRefCompteDto);

	}

	/**
	 * [CompteMgrBean.compteCrudBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 mars 2014 14:13:01
	 * @return the compteCrudBean
	 */
	public CompteCrudBean getCompteCrudBean() {
		return compteCrudBean;
	}

	/**
	 * [CompteMgrBean.compteCrudBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 mars 2014 14:13:01
	 * @param compteCrudBean
	 *            the compteCrudBean to set
	 */
	public void setCompteCrudBean(CompteCrudBean compteCrudBean) {
		this.compteCrudBean = compteCrudBean;
	}

	/**
	 * [CompteMgrBean.crud] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 mars 2014 14:14:41
	 * @param createAllow
	 * @param editAllow
	 * @param deleteAllow
	 */
	public void crud(boolean createAllow, boolean editAllow, boolean deleteAllow) {
		compteCrudBean.setEditAllow(editAllow);
		compteCrudBean.setCreateAllow(createAllow);
		compteCrudBean.setDeleteAllow(deleteAllow);
	}

	/**
	 * [CompteMgrBean.generateUsername] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 10:57:05
	 * @return
	 */
//	public String generateUsername() {
//		if (individuPrenom == null || individuNom == null) {
//			return null;
//		}
//		String prefix = individuPrenom.substring(0, 1).toLowerCase() + "."
//				+ individuNom.toLowerCase().trim().replaceAll(" ", "");
//		return refCompteServiceImpl.generateUsername(prefix);
//
//	}

	/**
	 * [CompteMgrBean.adminChange] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 10:57:00
	 * @param event
	 */
	public void adminChange() {
		System.out.println("OK");

	}

	/**
	 * [CompteMgrBean.listRefIndividuDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 12:16:06
	 * @return the listRefIndividuDto
	 */
	public List<RefIndividuDto> getListRefIndividuDto() {
		return listRefIndividuDto;
	}

	/**
	 * [CompteMgrBean.listRefIndividuDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 12:16:06
	 * @param listRefIndividuDto
	 *            the listRefIndividuDto to set
	 */
	public void setListRefIndividuDto(List<RefIndividuDto> listRefIndividuDto) {
		this.listRefIndividuDto = listRefIndividuDto;
	}

	/**
	 * [CompteMgrBean.filtredLlistIndividu] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 14:19:55
	 * @return the filtredLlistIndividu
	 */
	public List<RefIndividuDto> getFiltredLlistIndividu() {
		return filtredLlistIndividu;
	}

	/**
	 * [CompteMgrBean.filtredLlistIndividu] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 14:19:55
	 * @param filtredLlistIndividu
	 *            the filtredLlistIndividu to set
	 */
	public void setFiltredLlistIndividu(
			List<RefIndividuDto> filtredLlistIndividu) {
		this.filtredLlistIndividu = filtredLlistIndividu;
	}

	/**
	 * [CompteMgrBean.selectedIndividu] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 14:34:49
	 * @param selectedIndividu
	 */
	public void selectedIndividu(RefIndividuDto selectedIndividu) {
		this.refIndividuDto = selectedIndividu;
		if (selectedIndividu.getId() != null) {
			setIndividuId(selectedIndividu.getId().toString());
		}

		setIndividuNom(selectedIndividu.getNomLatin());
		setIndividuPrenom(selectedIndividu.getPrenomLatin());

	}

	/**
	 * [CompteMgrBean.etabChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 14:58:41
	 */
	public void etabChanged() {

	}

	/**
	 * [CompteMgrBean.loadIndividuList] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 15:00:36
	 */
	public void showIndividuList() {
		loadIndividuList();
		// RequestContext.getCurrentInstance().update("@(.dialogStyle)");
		// RequestContext.getCurrentInstance().execute("PF('individuWidget').show();");
	}

	/**
	 * [CompteMgrBean.load] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 15:20:45
	 * @return the load
	 */
	public String getLoad() {
		return load;
	}

	/**
	 * [CompteMgrBean.load] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 15:20:45
	 * @param load
	 *            the load to set
	 */
	public void setLoad(String load) {
		if (load != null && load.equals("null")) {
			this.load = null;
		} else {
			this.load = load;
			setLoadIndividu(Boolean.parseBoolean(load));
		}
	}

	/**
	 * [CompteMgrBean.loadIndividu] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 15:20:45
	 * @return the loadIndividu
	 */
	public Boolean getLoadIndividu() {
		return loadIndividu;
	}

	/**
	 * [CompteMgrBean.loadIndividu] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 15:20:45
	 * @param loadIndividu
	 *            the loadIndividu to set
	 */
	public void setLoadIndividu(Boolean loadIndividu) {
		this.loadIndividu = loadIndividu;
	}

	/**
	 * [CompteMgrBean.showEtabList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 15:45:44
	 * @return the showEtabList
	 */
	public Boolean getShowEtabList() {
		showEtabList = (SessionValues.getIdEtablissement() == null);
		return showEtabList;
	}

	/**
	 * [CompteMgrBean.showEtabList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 15:45:44
	 * @param showEtabList
	 *            the showEtabList to set
	 */
	public void setShowEtabList(Boolean showEtabList) {
		this.showEtabList = showEtabList;
	}

	/**
	 * [CompteMgrBean.individuId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 16:01:53
	 * @return the individuId
	 */
	public String getIndividuId() {
		return individuId;
	}

	/**
	 * [CompteMgrBean.individuId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 16:01:53
	 * @param individuId
	 *            the individuId to set
	 */
	public void setIndividuId(String individuId) {
		if (individuId != null && individuId.equals("null")) {
			this.individuId = null;
		} else {
			this.individuId = individuId;
			try {
				if (individuId != null) {
					setIdIndividu(Integer.parseInt(individuId));
				}
			} catch (Exception e) {

			}
		}
	}

	/**
	 * [CompteMgrBean.individuNom] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 16:01:53
	 * @return the individuNom
	 */
	public String getIndividuNom() {
		return individuNom;
	}

	/**
	 * [CompteMgrBean.individuNom] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 16:01:53
	 * @param individuNom
	 *            the individuNom to set
	 */
	public void setIndividuNom(String individuNom) {
		if (individuNom != null && individuNom.equals("null")) {
			this.individuNom = null;
		} else {
			this.individuNom = individuNom;
		}
	}

	/**
	 * [CompteMgrBean.individuPrenom] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 16:01:53
	 * @return the individuPrenom
	 */
	public String getIndividuPrenom() {
		return individuPrenom;
	}

	/**
	 * [CompteMgrBean.individuPrenom] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 16:01:53
	 * @param individuPrenom
	 *            the individuPrenom to set
	 */
	public void setIndividuPrenom(String individuPrenom) {
		if (individuPrenom != null && individuPrenom.equals("null")) {
			this.individuPrenom = null;
		} else {
			this.individuPrenom = individuPrenom;

		}

	}

	/**
	 * [CompteMgrBean.idIndividu] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 16:04:01
	 * @return the idIndividu
	 */
	public Integer getIdIndividu() {
		return idIndividu;
	}

	/**
	 * [CompteMgrBean.idIndividu] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 16:04:01
	 * @param idIndividu
	 *            the idIndividu to set
	 */
	public void setIdIndividu(Integer idIndividu) {
		this.idIndividu = idIndividu;
	}

	/**
	 * [CompteMgrBean.refIndividuServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 18:02:55
	 * @return the refIndividuServiceImpl
	 */
	public RefIndividuService getRefIndividuServiceImpl() {
		return refIndividuServiceImpl;
	}

	/**
	 * [CompteMgrBean.refIndividuServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 juil. 2014 18:02:55
	 * @param refIndividuServiceImpl
	 *            the refIndividuServiceImpl to set
	 */
	public void setRefIndividuServiceImpl(
			RefIndividuService refIndividuServiceImpl) {
		this.refIndividuServiceImpl = refIndividuServiceImpl;
	}

	/**
	 * [CompteMgrBean.idEtab] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 juil. 2014 09:43:38
	 * @return the idEtab
	 */
	public Integer getIdEtab() {
		return idEtab;
	}

	/**
	 * [CompteMgrBean.idEtab] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 juil. 2014 09:43:38
	 * @param idEtab
	 *            the idEtab to set
	 */
	public void setIdEtab(Integer idEtab) {
		this.idEtab = idEtab;
		if (idEtab != null) {
			this.etabId = idEtab.toString();
		}
	}

	/**
	 * [CompteMgrBean.etabId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 juil. 2014 09:48:29
	 * @return the etabId
	 */
	public String getEtabId() {
		return etabId;
	}

	/**
	 * [CompteMgrBean.etabId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 juil. 2014 09:48:29
	 * @param etabId
	 *            the etabId to set
	 */
	public void setEtabId(String etabId) {
		if (etabId != null && etabId.equals("null")) {
			this.etabId = null;
		} else {
			this.etabId = etabId;
			try {
				if (etabId != null) {
					this.idEtab = Integer.parseInt(etabId);
				}
			} catch (Exception e) {

			}
		}

	}

	/**
	 * [CompteMgrBean.admin] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 juil. 2014 10:51:49
	 * @return the admin
	 */
	public Boolean getAdmin() {
		return admin;
	}

	/**
	 * [CompteMgrBean.admin] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 juil. 2014 10:51:49
	 * @param admin
	 *            the admin to set
	 */
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	/**
	 * [CompteMgrBean.searchNom] Getter 
	 * @author MAKERRI Sofiane on : 17 juil. 2014  14:08:11
	 * @return the searchNom
	 */
	public String getSearchNom() {
		return searchNom;
	}

	/**
	 * [CompteMgrBean.searchNom] Setter 
	 * @author MAKERRI Sofiane on : 17 juil. 2014  14:08:11
	 * @param searchNom the searchNom to set
	 */
	public void setSearchNom(String searchNom) {
		if (searchNom != null && searchNom.equals("null")) {
			this.searchNom = null;
		} else {
			this.searchNom = searchNom;
		}
	}

	/**
	 * [CompteMgrBean.searchPrenom] Getter 
	 * @author MAKERRI Sofiane on : 17 juil. 2014  14:08:11
	 * @return the searchPrenom
	 */
	public String getSearchPrenom() {
		return searchPrenom;
	}

	/**
	 * [CompteMgrBean.searchPrenom] Setter 
	 * @author MAKERRI Sofiane on : 17 juil. 2014  14:08:11
	 * @param searchPrenom the searchPrenom to set
	 */
	public void setSearchPrenom(String searchPrenom) {
		if (searchPrenom != null && searchPrenom.equals("null")) {
			this.searchPrenom = null;
		} else {
			this.searchPrenom = searchPrenom;
		}
	}

	
	

}
