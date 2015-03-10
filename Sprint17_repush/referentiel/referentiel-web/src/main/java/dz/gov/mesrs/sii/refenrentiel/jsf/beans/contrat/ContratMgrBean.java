/**
 * 
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.contrat;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.FlowEvent;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefContratDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefContratService;
import dz.gov.mesrs.sii.referentiel.business.service.RefStructureService;

/**
 * @author jbeldi
 * 
 */
@ManagedBean(name = "contratMgrBean")
@SessionScoped
public class ContratMgrBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private List<RefContratDto> listRefContratDto;
	private String searchInput;
	private ResourceBundle bundle;
	private static final String MESSAGES_FILE_NAME = "commonmsgs";
	@ManagedProperty(value = "#{refContratServiceImpl}")
	private RefContratService refContratServiceImpl;
	@ManagedProperty(value = "#{refStructureServiceImpl}")
	private RefStructureService refStructureServiceImpl;
	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureServiceImpl;
	private static final Log log = LogFactory.getLog(ContratMgrBean.class);
	private RefContratDto refContratDto;
	private RefContratDto searchDto;
	private boolean updateMode;
	private boolean readMode;
	@ManagedProperty(value = "#{contratBckBean}")
	private ContratBckBean contratBckBean;
	
	
	/**
	 * [ContratBean.ContratBean()] Constructor
	 * @author MAKERRI Sofiane  on : 31 déc. 2013  16:28:06	
	 */
	public ContratMgrBean() {
		super();
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext()
			    .getSession(false);
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				ContratMgrBean.MESSAGES_FILE_NAME);
		//contratBckBean = (ContratBckBean) session.getAttribute("contratBckBean");
		searchDto = new RefContratDto();
	}
	

	
	/**
	 * [ContratMgrBean.nomenclatureServiceImpl] Getter 
	 * @author BELDI Jamel on : 21 janv. 2014  18:47:25
	 * @return the nomenclatureServiceImpl
	 */
	public NomenclatureService getNomenclatureServiceImpl() {
		return nomenclatureServiceImpl;
	}



	/**
	 * [ContratMgrBean.nomenclatureServiceImpl] Setter 
	 * @author BELDI Jamel on : 21 janv. 2014  18:47:25
	 * @param nomenclatureServiceImpl the nomenclatureServiceImpl to set
	 */
	public void setNomenclatureServiceImpl(
			NomenclatureService nomenclatureServiceImpl) {
		this.nomenclatureServiceImpl = nomenclatureServiceImpl;
	}



	/**
	 * @return the searchInput
	 */
	public String getSearchInput() {
		if(contratBckBean.getSearchInput()!=null){
			searchInput = contratBckBean.getSearchInput();
		} else {
			searchInput = null;
		}
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
		/*if(contratBckBean.getRefContratDto()!=null){
			refContratDto = contratBckBean.getRefContratDto();
		} else {
			refContratDto = new RefContratDto();
		}*/
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
		String saveSearchInput = contratBckBean.getSearchInput();
		RefContratDto saveSerachDto = contratBckBean.getSearchDto();
		if(saveSearchInput != null)
		{
			setSearchInput(saveSearchInput);
			findGeneric();
		} else  if(saveSerachDto != null ) {
			setSearchDto(saveSerachDto);
			findAdvanced();
		}
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
		//refContratDto = contratBckBean.getRefContratDto();
		setReadMode(true);
		setUpdateMode(false);
		contratBckBean.setSearchInput(null);
		contratBckBean.setSearchDto(null);
		return "toDetails";
	}

	/**
	 * 
	 * @return
	 */
	public String toNewContrat() {
		log.info("--------------------------------------------------------------------------toNewContrat-----------------------------");
		setReadMode(true);
		setUpdateMode(false);
		refContratDto = new RefContratDto();
		//contratBckBean.setRefContratDto(null);
		return "toNewContrat";
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

	/**
	 * 
	 * @param event
	 * @return
	 */
	public String toEditContrat() {
		log.info("--------------------------------------------------------------------------toEditContrat-----------------------------");
		//refContratDto = contratBckBean.getRefContratDto();
		setReadMode(false);
		setUpdateMode(true);
		contratBckBean.setSearchInput(null);
		contratBckBean.setSearchDto(null);
		return "toEditContrat";
	}

	/**
	 * 
	 */
	public void findGeneric() {
		FacesMessage msg = new FacesMessage();
		try {
			List<RefContratDto> result = refContratServiceImpl
					.findGeneric(this.searchInput, false);
			contratBckBean.setSearchInput(searchInput);
			contratBckBean.setSearchDto(null);
			if (result == null || result.isEmpty()) {
				setListRefContratDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				setListRefContratDto(result);//listRefContratDto = result;
				
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
			List<RefContratDto> result = refContratServiceImpl
					.findAdvanced(searchDto, false);
			contratBckBean.setSearchInput(null);
			contratBckBean.setSearchDto(searchDto);
			if (result == null || result.isEmpty()) {
				setListRefContratDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				setListRefContratDto(result);//listRefContratDto = result;
				
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

	public String onFlowProcess(FlowEvent event) {
		log.info("Current wizard step:" + event.getOldStep());
		log.info("Next step:" + event.getNewStep());

		return event.getNewStep();

	}

	public void saveContrat(boolean avenant) {
		FacesMessage msg = new FacesMessage();
		try {
			if (updateMode) {
				refContratServiceImpl.update(refContratDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_modification"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				refContratServiceImpl.save(refContratDto, avenant);
				setUpdateMode(true);
				contratBckBean.setRefContratDto(refContratDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_enregistrement"));
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
	 * @return the readMode
	 */
	public boolean isReadMode() {
		return readMode;
	}

	/**
	 * @param readMode the readMode to set
	 */
	public void setReadMode(boolean readMode) {
		this.readMode = readMode;
	}

	/**
	 * listener on change gestionnaire
	 */
	public void onChangeGestionnaire() {
		FacesMessage msg = new FacesMessage();
		log.info("--------------------onChangeGestionnairee:-------------------------------------");
		try {
			RefStructureDto refStructureDto = refStructureServiceImpl.findById(refContratDto.getIdStructure());
			if(refStructureDto!=null){
				//refContratDto.setIdEtablissement(refStructureDto.getIdMotherStructure());
				refContratDto.setLlEtablissementArabe(refStructureDto.getLlArMotherStructure());
				refContratDto.setLlEtablissementLatin(refStructureDto.getLlLtMotherStructure());
				log.info("--------------------onChangeGestionnairee:"+refStructureDto.getIdMotherStructure());
			}
		}catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.info(e.getMessage());
		}
	}
	/**
	 * listener on change Unite Monetaire
	 */
	public void onChangeUniteMonetaire() {
		FacesMessage msg = new FacesMessage();
		log.info("onChangeUniteMonetaire:"+refContratDto.getUniteMonitaireId());
		try {
			NomenclatureDto ncUniteMonetaireDto = nomenclatureServiceImpl.findById(refContratDto.getUniteMonitaireId());
			
			if(ncUniteMonetaireDto!=null){
				log.info("onChangeUniteMonetaire:"+ncUniteMonetaireDto.getLibelleLongFr());
				refContratDto.setUniteMonitaireCode(ncUniteMonetaireDto.getCode());
				refContratDto.setUniteMonitaireLibelleLongAr(ncUniteMonetaireDto.getLibelleLongAr());
				refContratDto.setUniteMonitaireLibelleLongFr(ncUniteMonetaireDto.getLibelleLongFr());
				refContratDto.setUniteMonitaireLibelleCourtAr(ncUniteMonetaireDto.getLibelleCourtAr());
				refContratDto.setUniteMonitaireLibelleCourtAr(ncUniteMonetaireDto.getLibelleCourtAr());
			}
		}catch (Exception e) {
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
		if(contratBckBean.getSearchDto()!=null){
			searchDto = contratBckBean.getSearchDto();
		} else {
			searchDto = new RefContratDto();
		}
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
	 * @param refStructureServiceImpl the refStructureServiceImpl to set
	 */
	public void setRefStructureServiceImpl(
			RefStructureService refStructureServiceImpl) {
		this.refStructureServiceImpl = refStructureServiceImpl;
	}




	/**
	 * [ContratMgrBean.contratBckBean] Getter 
	 * @author MAKERRI Sofiane on : 9 janv. 2014  11:10:18
	 * @return the contratBckBean
	 */
	public ContratBckBean getContratBckBean() {
		return contratBckBean;
	}




	/**
	 * [ContratMgrBean.contratBckBean] Setter 
	 * @author MAKERRI Sofiane on : 9 janv. 2014  11:10:18
	 * @param contratBckBean the contratBckBean to set
	 */
	public void setContratBckBean(ContratBckBean contratBckBean) {
		this.contratBckBean = contratBckBean;
	}
	
	
	public void onMontantChange() {
		refContratDto.setMontantTtc(refContratDto.getMontantHt().add(refContratDto.getMontantTva()));
		
	}
	

}
