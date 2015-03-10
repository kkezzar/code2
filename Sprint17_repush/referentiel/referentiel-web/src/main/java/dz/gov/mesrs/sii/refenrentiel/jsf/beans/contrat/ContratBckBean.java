/**
 * 
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.contrat;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.FlowEvent;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.ComboBckBean;
import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.SessionValues;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefContratDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefContratService;
import dz.gov.mesrs.sii.referentiel.business.service.RefStructureService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author jbeldi
 * 
 */
@ManagedBean(name = "contratBckBean")
@ViewScoped
public class ContratBckBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<RefContratDto> listRefContratDto;
	private String searchInput;
	private ResourceBundle bundle;
	@ManagedProperty(value = "#{refContratServiceImpl}")
	private RefContratService refContratServiceImpl;
	@ManagedProperty(value = "#{refStructureServiceImpl}")
	private RefStructureService refStructureServiceImpl;
	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureServiceImpl;
	private static final Log log = LogFactory.getLog(ContratBckBean.class);
	private RefContratDto refContratDto;
	private RefContratDto searchDto;
	private String idf;
	private Integer id;
	private String searchValue;
	private String advSearchValue;
	private int currentLevel = 1;
	private boolean entityChange;
	private boolean nextStep;
	@ManagedProperty("#{flash}")
	private Flash flash;
	@ManagedProperty(value = "#{comboBckBean}")
	private ComboBckBean comboBckBean;

	/**
	 * [ContratBean.ContratBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2013 16:28:06
	 */
	public ContratBckBean() {
		super();

		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		HttpServletRequest request = (HttpServletRequest) facesContext
				.getExternalContext().getRequest();

		
		id = (Integer) request.getAttribute("id");
		log.info("var request*******************************************id: "
				+ id);

		searchDto = new RefContratDto();
	}

	@PostConstruct
	public void init() {
		if (id != null) {
			//int _idc = UtilConstant.strToInt(idc);
			refContratDto = refContratServiceImpl.findById(id);
		}
		if (refContratDto == null) {
			refContratDto = new RefContratDto();
			setDefaultValues();
		}
	}

	/**
	 * [ContratMgrBean.nomenclatureServiceImpl] Getter
	 * 
	 * @author BELDI Jamel on : 21 janv. 2014 18:47:25
	 * @return the nomenclatureServiceImpl
	 */
	public NomenclatureService getNomenclatureServiceImpl() {
		return nomenclatureServiceImpl;
	}

	/**
	 * [ContratMgrBean.nomenclatureServiceImpl] Setter
	 * 
	 * @author BELDI Jamel on : 21 janv. 2014 18:47:25
	 * @param nomenclatureServiceImpl
	 *            the nomenclatureServiceImpl to set
	 */
	public void setNomenclatureServiceImpl(
			NomenclatureService nomenclatureServiceImpl) {
		this.nomenclatureServiceImpl = nomenclatureServiceImpl;
	}

	/**
	 * @return the searchInput
	 */
	public String getSearchInput() {
		/*
		 * if(contratBckBean.getSearchInput()!=null){ searchInput =
		 * contratBckBean.getSearchInput(); } else { searchInput = null; }
		 */
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
		log.info("--------------------------------------------------------------------------toDetailContrat-----------------------------");
		return "toDetails";
	}

	/**
	 * 
	 * @return
	 */

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

	public String onFlowProcess(FlowEvent event) {
		log.info("Current wizard step:" + event.getOldStep());
		log.info("Next step:" + event.getNewStep());

		return event.getNewStep();

	}

	public String saveContrat(boolean avenant) {
		FacesMessage msg = new FacesMessage();

		try {

			if (!entityChange) {
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_update_none"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return null;
			}

			if (idf != null) {
				refContratDto.setIdEtablissement(SessionValues
						.getIdEtablissement());
				refContratServiceImpl.update(refContratDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_modification"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				setEntityChange(false);
			} else {
				refContratDto.setIdEtablissement(SessionValues
						.getIdEtablissement());
				refContratServiceImpl.save(refContratDto, avenant);

				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_enregistrement"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				setEntityChange(false);
				FacesContext facesContext = FacesContext.getCurrentInstance();
				HttpServletRequest request = (HttpServletRequest) facesContext
						.getExternalContext().getRequest();
            	request.setAttribute("id", refContratDto.getId());
				setIdf(refContratDto.getId().toString());
				flash.setKeepMessages(true);
				return "contratEdit?faces-redirect=true&idf=" + idf;
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

	/**
	 * listener on change gestionnaire
	 */
	/*
	 * public void onChangeGestionnaire() { FacesMessage msg = new
	 * FacesMessage(); log.info(
	 * "--------------------onChangeGestionnairee:-------------------------------------"
	 * ); try { RefStructureDto refStructureDto = refStructureServiceImpl
	 * .findById(refContratDto.getIdStructure()); if (refStructureDto != null) {
	 * refContratDto.setIdEtablissement(refStructureDto
	 * .getIdMotherStructure());
	 * refContratDto.setLlEtablissementArabe(refStructureDto
	 * .getLlArMotherStructure());
	 * refContratDto.setLlEtablissementLatin(refStructureDto
	 * .getLlLtMotherStructure());
	 * log.info("--------------------onChangeGestionnairee:" +
	 * refStructureDto.getIdMotherStructure()); } } catch (Exception e) {
	 * msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	 * msg.setSummary(bundle.getString("error_echec") + ": " +
	 * bundle.getString("error_echec_inconnue"));
	 * FacesContext.getCurrentInstance().addMessage(null, msg);
	 * log.info(e.getMessage()); } }
	 */

	/**
	 * listener on change Unite Monetaire
	 */
	public void onChangeUniteMonetaire() {
		FacesMessage msg = new FacesMessage();
		log.info("onChangeUniteMonetaire:"
				+ refContratDto.getUniteMonitaireId());
		try {
			NomenclatureDto ncUniteMonetaireDto = nomenclatureServiceImpl
					.findById(refContratDto.getUniteMonitaireId());

			if (ncUniteMonetaireDto != null) {
				log.info("onChangeUniteMonetaire:"
						+ ncUniteMonetaireDto.getLibelleLongFr());
				refContratDto.setUniteMonitaireCode(ncUniteMonetaireDto
						.getCode());
				refContratDto
						.setUniteMonitaireLibelleLongAr(ncUniteMonetaireDto
								.getLibelleLongAr());
				refContratDto
						.setUniteMonitaireLibelleLongFr(ncUniteMonetaireDto
								.getLibelleLongFr());
				refContratDto
						.setUniteMonitaireLibelleCourtAr(ncUniteMonetaireDto
								.getLibelleCourtAr());
				refContratDto
						.setUniteMonitaireLibelleCourtAr(ncUniteMonetaireDto
								.getLibelleCourtAr());
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

	/**
	 * @return the refStructureServiceImpl
	 */
	public RefStructureService getRefStructureServiceImpl() {
		return refStructureServiceImpl;
	}

	/**
	 * @param refStructureServiceImpl
	 *            the refStructureServiceImpl to set
	 */
	public void setRefStructureServiceImpl(
			RefStructureService refStructureServiceImpl) {
		this.refStructureServiceImpl = refStructureServiceImpl;
	}

	/**
	 * [ContratMgrBean.contratBckBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 janv. 2014 11:10:18
	 * @return the contratBckBean
	 */
	/*
	 * public ContratBckBean getContratBckBean() { return contratBckBean; }
	 */

	/**
	 * [ContratMgrBean.contratBckBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 janv. 2014 11:10:18
	 * @param contratBckBean
	 *            the contratBckBean to set
	 */
	/*
	 * public void setContratBckBean(ContratBckBean contratBckBean) {
	 * this.contratBckBean = contratBckBean; }
	 */

	public void onMontantChange() {
		refContratDto.setMontantTtc(refContratDto.getMontantHt().add(
				refContratDto.getMontantTva()));

	}

	/**
	 * [ContratMgrBean.idf] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 f�vr. 2014 10:27:04
	 * @return the idf
	 */
	public String getIdf() {
		return idf;
	}

	/**
	 * [ContratMgrBean.idf] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 f�vr. 2014 10:27:04
	 * @param idf
	 *            the idf to set
	 */
	public void setIdf(String idf) {
		if ((idf != null) && (idf.equals("null"))) {
			this.idf = null;
		} else {
			int _idc = RefUtilConstant.strToInt(idf);
			if(_idc != -1) {
				this.id = _idc;
			}
			this.idf = idf;
		}
	}

	/**
	 * [ContratBckBean.bundle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 f�vr. 2014 10:54:43
	 * @return the bundle
	 */
	public ResourceBundle getBundle() {
		return bundle;
	}

	/**
	 * [ContratBckBean.bundle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 f�vr. 2014 10:54:43
	 * @param bundle
	 *            the bundle to set
	 */
	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * [ContratBckBean.searchValue] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 f�vr. 2014 10:54:43
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [ContratBckBean.searchValue] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 f�vr. 2014 10:54:43
	 * @param searchValue
	 *            the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		if ((searchValue != null) && (searchValue.equals("null"))) {
			this.searchValue = null;
		} else
			this.searchValue = searchValue;
	}

	/**
	 * [ContratBckBean.advSearchValue] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 avr. 2014 11:32:09
	 * @return the advSearchValue
	 */
	public String getAdvSearchValue() {
		return advSearchValue;
	}

	/**
	 * [ContratBckBean.advSearchValue] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 avr. 2014 11:32:09
	 * @param advSearchValue
	 *            the advSearchValue to set
	 */
	public void setAdvSearchValue(String advSearchValue) {
		if ((advSearchValue != null) && (advSearchValue.equals("null"))) {
			this.advSearchValue = null;
		} else
			this.advSearchValue = advSearchValue;
	}

	/**
	 * [ContratBckBean.currentLevel] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 f�vr. 2014 10:00:16
	 * @return the currentLevel
	 */
	public int getCurrentLevel() {
		return currentLevel;
	}

	/**
	 * [ContratBckBean.currentLevel] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 f�vr. 2014 10:00:16
	 * @param currentLevel
	 *            the currentLevel to set
	 */
	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	/**
	 * [ContratBckBean.toInfo] Method
	 * 
	 * @author MAKERRI Sofiane on : 9 f�vr. 2014 15:35:29
	 */
	public void toInfo() {
		setCurrentLevel(1);
	}

	/**
	 * [ContratBckBean.toPartenaire] Method
	 * 
	 * @author MAKERRI Sofiane on : 9 f�vr. 2014 15:21:10
	 */
	public void toPartenaire() {
		setCurrentLevel(2);
	}

	/**
	 * [ContratBckBean.toValidation] Method
	 * 
	 * @author MAKERRI Sofiane on : 9 f�vr. 2014 15:29:22
	 */
	public void toValidation() {
		setCurrentLevel(3);
	}

	/**
	 * [ContratBckBean.toSignature] Method
	 * 
	 * @author MAKERRI Sofiane on : 9 f�vr. 2014 15:28:50
	 */
	public void toSignature() {
		setCurrentLevel(4);
	}

	/**
	 * [ContratBckBean.terminate] Method
	 * 
	 * @author MAKERRI Sofiane on : 10 f�vr. 2014 08:36:21
	 */
	public String terminate() {
		return "toSearch";
	}

	/**
	 * [ContratBckBean.entityChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 10 f�vr. 2014 11:22:09
	 * @param event
	 */
	public void entityChanged(ValueChangeEvent event) {
		entityChange = true;
	}

	/**
	 * [ContratBckBean.entityChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 f�vr. 2014 11:22:19
	 * @return the entityChange
	 */
	public boolean isEntityChange() {
		return entityChange;
	}

	/**
	 * [ContratBckBean.entityChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 f�vr. 2014 11:22:19
	 * @param entityChange
	 *            the entityChange to set
	 */
	public void setEntityChange(boolean entityChange) {
		this.entityChange = entityChange;
	}

	/**
	 * [ContratBckBean.nextStep] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 f�vr. 2014 12:00:45
	 * @return the nextStep
	 */
	public boolean isNextStep() {
		return nextStep;
	}

	/**
	 * [ContratBckBean.nextStep] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 f�vr. 2014 12:00:45
	 * @param nextStep
	 *            the nextStep to set
	 */
	public void setNextStep(boolean nextStep) {
		this.nextStep = nextStep;
	}

	/**
	 * [ContratBckBean.flash] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 avr. 2014 15:06:37
	 * @return the flash
	 */
	public Flash getFlash() {
		return flash;
	}

	/**
	 * [ContratBckBean.flash] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 avr. 2014 15:06:37
	 * @param flash
	 *            the flash to set
	 */
	public void setFlash(Flash flash) {
		this.flash = flash;
	}

	/**
	 * [ContratBckBean.comboBckBean] Getter 
	 * @author MAKERRI Sofiane on : 29 avr. 2014  11:22:26
	 * @return the comboBckBean
	 */
	public ComboBckBean getComboBckBean() {
		return comboBckBean;
	}

	/**
	 * [ContratBckBean.comboBckBean] Setter 
	 * @author MAKERRI Sofiane on : 29 avr. 2014  11:22:26
	 * @param comboBckBean the comboBckBean to set
	 */
	public void setComboBckBean(ComboBckBean comboBckBean) {
		this.comboBckBean = comboBckBean;
	}
	
	
	/**
	 * [ContratBckBean.setDefaultValues] Method 
	 * @author MAKERRI Sofiane  on : 29 avr. 2014  11:37:20
	 */
	public void setDefaultValues() {
		refContratDto.setNatureId(comboBckBean.getDefaultNatureContrat());
		refContratDto.setUniteMonitaireId(comboBckBean.getDefaultUniteMonetaire());
		refContratDto.setDisciplineId(comboBckBean.getDefaultDiscipline());
	}

	/**
	 * [ContratBckBean.id] Getter 
	 * @author MAKERRI Sofiane on : 11 mai 2014  16:33:03
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [ContratBckBean.id] Setter 
	 * @author MAKERRI Sofiane on : 11 mai 2014  16:33:03
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	

}
