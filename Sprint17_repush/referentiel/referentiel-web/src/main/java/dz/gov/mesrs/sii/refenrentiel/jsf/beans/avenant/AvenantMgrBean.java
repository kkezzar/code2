/**
 * 
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.avenant;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.SessionValues;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefContratDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefContratService;

/**
 * @author obelbrik
 * 
 */
@ManagedBean(name = "avenantBean")
@SessionScoped
public class AvenantMgrBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private RefContratDto refContratDto;
	private RefContratDto searchDto;
	private boolean updateMode;
	private boolean readMode;
	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureServiceImpl;
	@ManagedProperty("#{flash}")
	private Flash flash;

	/**
	 * @return the nomenclatureServiceImpl
	 */
	public NomenclatureService getNomenclatureServiceImpl() {
		return nomenclatureServiceImpl;
	}

	/**
	 * @param nomenclatureImpl
	 *            the nomenclatureServiceImpl to set
	 */
	public void setNomenclatureServiceImpl(
			NomenclatureService nomenclatureServiceImpl) {
		this.nomenclatureServiceImpl = nomenclatureServiceImpl;
	}

	/**
	 * @return the searchDto
	 */
	public RefContratDto getSearchDto() {
		return searchDto;
	}

	/**
	 * @param searchDto
	 *            the searchDto to set
	 */
	public void setSearchDto(RefContratDto searchDto) {
		this.searchDto = searchDto;
	}

	private List<RefContratDto> listRefContratDto;
	private String searchInput;
	private ResourceBundle bundle;
	private static final String MESSAGES_FILE_NAME = "commonmsgs";
	@ManagedProperty(value = "#{refContratServiceImpl}")
	private RefContratService refContratServiceImpl;
	private static final Log log = LogFactory.getLog(AvenantMgrBean.class);

	public AvenantMgrBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				AvenantMgrBean.MESSAGES_FILE_NAME);
		searchDto = new RefContratDto();
	}

	/**
	 * @return the searchInput
	 */
	public String getSearchInput() {
		return searchInput;
	}

	/**
	 * @param searchInput
	 *            the searchInput to set
	 */
	public void setSearchInput(String searchInput) {
		this.searchInput = searchInput;
	}

	/**
	 * @return the refContratDto
	 */
	public RefContratDto getRefContratDto() {
		return refContratDto;
	}

	/**
	 * @param refContratDto
	 *            the refContratDto to set
	 */
	public void setRefContratDto(RefContratDto refContratDto) {
		this.refContratDto = refContratDto;
	}

	/**
	 * @return the listRefContratDto
	 */
	public List<RefContratDto> getListRefContratDto() {
		return listRefContratDto;
	}

	/**
	 * @param listRefContratDto
	 *            the listRefContratDto to set
	 */
	public void setListRefContratDto(List<RefContratDto> listRefContratDto) {
		this.listRefContratDto = listRefContratDto;
	}

	/**
	 * 
	 * @param event
	 * @return
	 */
	public String toDetails() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext
				.getExternalContext().getRequest();
    	request.setAttribute("id", refContratDto.getId());
		return "toDetails";
	}

	/**
	 * 
	 * @return
	 */
	public String toNew() {
		setReadMode(false);
		setUpdateMode(false);
		refContratDto = new RefContratDto();
		return "toNew";
	}

	/**
	 * 
	 * @param event
	 * @return
	 */
	public String toEdit() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext
				.getExternalContext().getRequest();
    	request.setAttribute("id", refContratDto.getId());
		setReadMode(false);
		setUpdateMode(true);
		return "toEdit";
	}

	/**
	 * 
	 */
	public void findGeneric() {
		FacesMessage msg = new FacesMessage();

		try {
			List<RefContratDto> result = refContratServiceImpl.findGeneric(SessionValues.getIdEtablissement(),
					this.searchInput, true);
			if (result == null || result.isEmpty()) {
				setListRefContratDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				setListRefContratDto(result);
			}
		} catch (Exception e) {
			setListRefContratDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void findAdvanced() {
		FacesMessage msg = new FacesMessage();
		try {
			List<RefContratDto> result = refContratServiceImpl.findAdvanced(SessionValues.getIdEtablissement(),
					searchDto, true);
			if (result == null || result.isEmpty()) {
				setListRefContratDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				setListRefContratDto(result);
			}
		} catch (Exception e) {
			setListRefContratDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	/**
	 * @return the refContratServiceImpl
	 */
	public RefContratService getRefContratServiceImpl() {
		return refContratServiceImpl;
	}

	/**
	 * @param refContratServiceImpl
	 *            the refContratServiceImpl to set
	 */
	public void setRefContratServiceImpl(RefContratService refContratServiceImpl) {
		this.refContratServiceImpl = refContratServiceImpl;
	}

	/**
	 * @return the readMode
	 */
	public boolean isReadMode() {
		return readMode;
	}

	/**
	 * @param readMode
	 *            the readMode to set
	 */
	public void setReadMode(boolean readMode) {
		this.readMode = readMode;
	}

	/**
	 * @return the updateMode
	 */
	public boolean isUpdateMode() {
		return updateMode;
	}

	/**
	 * @param updateMode
	 *            the updateMode to set
	 */
	public void setUpdateMode(boolean updateMode) {
		this.updateMode = updateMode;
	}

	public String saveAvenant(boolean avenant) {
		FacesMessage msg = new FacesMessage();
		try {
			if (updateMode) {
				refContratDto.setIdEtablissement(SessionValues
						.getIdEtablissement());
				refContratServiceImpl.update(refContratDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_modification"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				refContratDto.setIdEtablissement(SessionValues
						.getIdEtablissement());
				refContratServiceImpl.save(refContratDto, avenant);
				setUpdateMode(true);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_enregistrement"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				flash.setKeepMessages(true);
				/*FacesContext facesContext = FacesContext.getCurrentInstance();
				HttpServletRequest request = (HttpServletRequest) facesContext
						.getExternalContext().getRequest();
		    	request.setAttribute("id", refContratDto.getId());*/
				return  "avenantEdit?faces-redirect=true&id="+refContratDto.getId();
			}
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.info(e.getMessage());
		}
		return null;
	}

	public void onChangeContrat() {
		FacesMessage msg = new FacesMessage();
		try {

			RefContratDto contrat = refContratServiceImpl
					.findById(refContratDto.getIdContrat());
			if (contrat != null) {
				refContratDto.setObjetContratAvenantFr(contrat
						.getObjetContratFr());
			} else {
				refContratDto.setObjetContratAvenantFr("");
			}
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.info(e.getMessage());
		}

	}

	public void onChangeUnite() {
		FacesMessage msg = new FacesMessage();
		try {

			NomenclatureDto unite = nomenclatureServiceImpl
					.findById(refContratDto.getUniteMonitaireId());
			if (unite != null) {
				refContratDto.setUniteMonitaireCode(unite.getCode());
				refContratDto.setUniteMonitaireLibelleCourtFr(unite
						.getLibelleCourtFr());
				refContratDto.setUniteMonitaireLibelleLongFr(unite
						.getLibelleLongFr());
				refContratDto.setUniteMonitaireLibelleCourtAr(unite
						.getLibelleCourtAr());
				refContratDto.setUniteMonitaireLibelleLongAr(unite
						.getLibelleLongAr());
			} else {
				refContratDto.setObjetContratAvenantFr("");
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
	 * [AvenantMgrBean.flash] Getter 
	 * @author MAKERRI Sofiane on : 9 avr. 2014  11:58:40
	 * @return the flash
	 */
	public Flash getFlash() {
		return flash;
	}

	/**
	 * [AvenantMgrBean.flash] Setter 
	 * @author MAKERRI Sofiane on : 9 avr. 2014  11:58:40
	 * @param flash the flash to set
	 */
	public void setFlash(Flash flash) {
		this.flash = flash;
	}
	
	
	

}
