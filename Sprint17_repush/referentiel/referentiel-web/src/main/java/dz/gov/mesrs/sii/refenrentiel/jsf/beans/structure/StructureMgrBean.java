/**
 * 
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.structure;

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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.ComboBckBean;
import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.SessionValues;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametreEtabService;
import dz.gov.mesrs.sii.referentiel.business.service.RefStructureService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author jbeldi
 * 
 */
@ManagedBean(name = "structureMgrBean")
@ViewScoped
public class StructureMgrBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String searchInput;
	private ResourceBundle bundle;
	private static final String STRUCTURE_EXTERNE = "Externe";
	@ManagedProperty(value = "#{refStructureServiceImpl}")
	private RefStructureService refStructureServiceImpl;
	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureServiceImpl;
	private static final Log log = LogFactory.getLog(StructureMgrBean.class);
	private RefStructureDto refStructureDto;
	private RefStructureDto searchDto;
	private boolean updateMode;
	private Boolean readMode;
	private RefStructureDto refStructureDtoMother;
	@ManagedProperty(value = "#{structureCrudBean}")
	private StructureCrudBean structureCrudBean;
	@ManagedProperty("#{flash}")
	private Flash flash;
	@ManagedProperty(value = "#{refParametreEtabServiceImpl}")
	private RefParametreEtabService refParametreEtabServiceImpl;
	private int identifiantLength;
	@ManagedProperty(value = "#{comboBckBean}")
	private ComboBckBean comboBckBean;
	private boolean codeChange;
	private List<RefStructureDto > listRefStructureDto;

	/**
	 * [StructureMgrBean.StructureMgrBean()] Constructor
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 09:19:25
	 */
	public StructureMgrBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		searchDto = new RefStructureDto();

	}
	
	@PostConstruct
	public void init() {
		refStructureDto = (RefStructureDto) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("refStructureDto");
		readMode = (Boolean) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("readMode");
		if(readMode == null) {
			readMode = false;
		}
		if(refStructureDto == null) {
			refStructureDto = new RefStructureDto();
			setDefaultValues();
		}
	}

	/**
	 * [StructureMgrBean.getSearchInput] Method Getter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 09:19:43
	 * @return the searchInput
	 */
	public String getSearchInput() {

		return searchInput;
	}

	/**
	 * [StructureMgrBean.setSearchInput] Method Setter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 09:20:24
	 * @param searchInput
	 */
	public void setSearchInput(String searchInput) {
		this.searchInput = searchInput;
	}

	/**
	 * @return the refStructureDto
	 */
	public RefStructureDto getRefStructureDto() {
		return refStructureDto;
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
	 * @return the readMode
	 */
	public Boolean getReadMode() {
		return readMode;
	}

	/**
	 * @param readMode
	 *            the readMode to set
	 */
	public void setReadMode(Boolean readMode) {
		this.readMode = readMode;
	}

	/**
	 * 
	 * @param event
	 * @return
	 */
	public String toDetails(RefStructureDto current) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("refStructureDto", current);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("readMode", true);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("entity", "structure");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("idEntity", current.getId());
		return "toDetails";
	}

	/**
	 * navigate to new Structure page
	 * 
	 * @return outcome
	 */
	public String toNew() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("readMode", false);
		return "toNew";
	}

	/**
	 * 
	 * navigate to edit Structure page
	 * 
	 * @return
	 */
	public String toEdit(RefStructureDto current) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("refStructureDto", current);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("readMode", false);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("entity", "structure");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("idEntity", current.getId());
		return "toEdit";
	}

	/**
	 * [StructureMgrBean.findGeneric] Method Find the list of Structure by the
	 * the search input
	 * 
	 * @author jbeldi on : 15 janv. 2014 11:18:14
	 */
	public void findGeneric() {
		FacesMessage msg = new FacesMessage();
		try {
			listRefStructureDto = refStructureServiceImpl.findGeneric(
					SessionValues.getIdEtablissement(), this.searchInput);
			if (listRefStructureDto == null || listRefStructureDto.isEmpty()) {
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				
			}
		} catch (Exception e) {
		
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	/**
	 * [IndividuMgrBean.findAdvanced] Method to Find the list of individu by the
	 * the advanced search
	 * 
	 * @author jbeldi on : 15 janv. 2014 11:17:49
	 */
	public void findAdvanced() {
		FacesMessage msg = new FacesMessage();
		try {
			listRefStructureDto= refStructureServiceImpl
					.findAdvanced(SessionValues.getIdEtablissement(), searchDto);

			if (listRefStructureDto == null || listRefStructureDto.isEmpty()) {
				
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				
																// = result;

			}
		} catch (Exception e) {
			
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	/**
	 * 
	 * @param event
	 * @return
	 */
	public String toSearch() {
		return "toSearchStructure";
	}

	public String saveStructure() {
		FacesMessage msg = new FacesMessage();
		try {
			log.info("Mgr Bean saveStructure");
			refStructureDto.setIdEtablissement(SessionValues
					.getIdEtablissement());
			if (codeChange || refStructureDto.getIdentifiant()==null) {
				String nextIdentifiy = generateIdentify();
				if (nextIdentifiy == null) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec") + ": "
							+ bundle.getString("error_generation_identify"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return null;
				} else if (nextIdentifiy.length() > identifiantLength) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec")
							+ ": "
							+ bundle.getString("error_identify_max_legnt_affected"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return null;
				}
				refStructureDto.setIdentifiant(nextIdentifiy);
			}
			setCodeChange(false);
			if (refStructureDto.getId() != null) {
				refStructureServiceImpl.update(refStructureDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_modification"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {

				refStructureServiceImpl.save(refStructureDto);
				setUpdateMode(true);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_enregistrement"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				flash.setKeepMessages(true);
				FacesContext.getCurrentInstance().getExternalContext().getFlash().put("refStructureDto", refStructureDto);
				FacesContext.getCurrentInstance().getExternalContext().getFlash().put("readMode", false);
				FacesContext.getCurrentInstance().getExternalContext().getFlash().put("entity", "structure");
				FacesContext.getCurrentInstance().getExternalContext().getFlash().put("idEntity", refStructureDto.getId());
				return "structureEdit?faces-redirect=true";
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
	 * [StructureMgrBean.refStructureServiceImpl] Getter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 09:42:23
	 * @return the refStructureServiceImpl
	 */
	public RefStructureService getRefStructureServiceImpl() {
		return refStructureServiceImpl;
	}

	/**
	 * [StructureMgrBean.refStructureServiceImpl] Setter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 09:42:23
	 * @param refStructureServiceImpl
	 *            the refStructureServiceImpl to set
	 */
	public void setRefStructureServiceImpl(
			RefStructureService refStructureServiceImpl) {
		this.refStructureServiceImpl = refStructureServiceImpl;
	}

	/**
	 * [StructureMgrBean.searchDto] Getter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 09:42:23
	 * @return the searchDto
	 */
	public RefStructureDto getSearchDto() {
		return searchDto;
	}

	/**
	 * [StructureMgrBean.searchDto] Setter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 09:42:23
	 * @param searchDto
	 *            the searchDto to set
	 */
	public void setSearchDto(RefStructureDto searchDto) {
		this.searchDto = searchDto;
	}


	/**
	 * [StructureMgrBean.refStructureDto] Setter
	 * 
	 * @author BELDI Jamel on : 29 janv. 2014 09:42:23
	 * @param refStructureDto
	 *            the refStructureDto to set
	 */
	public void setRefStructureDto(RefStructureDto refStructureDto) {
		this.refStructureDto = refStructureDto;
	}

	/**
	 * [StructureMgrBean.refStructureDtoMother] Getter
	 * 
	 * @author BELDI Jamel on : 2 f�vr. 2014 15:41:15
	 * @return the refStructureDtoMother
	 */
	public RefStructureDto getRefStructureDtoMother() {
		if (refStructureDto != null
				&& refStructureDto.getIdMotherStructure() != null) {
			refStructureDtoMother = refStructureServiceImpl
					.findById(refStructureDto.getIdMotherStructure());
		} else {
			refStructureDtoMother = new RefStructureDto();
		}
		return refStructureDtoMother;
	}

	/**
	 * [StructureMgrBean.refStructureDtoMother] Setter
	 * 
	 * @author BELDI Jamel on : 2 f�vr. 2014 15:41:15
	 * @param refStructureDtoMother
	 *            the refStructureDtoMother to set
	 */
	public void setRefStructureDtoMother(RefStructureDto refStructureDtoMother) {
		this.refStructureDtoMother = refStructureDtoMother;
	}

	/**
	 * [StructureMgrBean.structureCrudBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 mars 2014 09:46:50
	 * @return the structureCrudBean
	 */
	public StructureCrudBean getStructureCrudBean() {
		return structureCrudBean;
	}

	/**
	 * [StructureMgrBean.structureCrudBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 mars 2014 09:46:50
	 * @param structureCrudBean
	 *            the structureCrudBean to set
	 */
	public void setStructureCrudBean(StructureCrudBean structureCrudBean) {
		this.structureCrudBean = structureCrudBean;
	}

	/**
	 * [StructureMgrBean.crud] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 mars 2014 09:47:03
	 * @param createAllow
	 * @param editAllow
	 * @param deleteAllow
	 */
	public void crud(boolean createAllow, boolean editAllow, boolean deleteAllow) {
		structureCrudBean.setEditAllow(editAllow);
		structureCrudBean.setCreateAllow(createAllow);
		structureCrudBean.setDeleteAllow(deleteAllow);
	}

	/**
	 * [StructureMgrBean.flash] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 avr. 2014 17:33:20
	 * @return the flash
	 */
	public Flash getFlash() {
		return flash;
	}

	/**
	 * [StructureMgrBean.flash] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 avr. 2014 17:33:20
	 * @param flash
	 *            the flash to set
	 */
	public void setFlash(Flash flash) {
		this.flash = flash;
	}

	/**
	 * [StructureMgrBean.generateIdentify] Method
	 * 
	 * @author MAKERRI Sofiane on : 20 avr. 2014 15:15:55
	 * @return
	 */
	public String generateIdentify() {
		NomenclatureDto ncTypeStructureDto = nomenclatureServiceImpl
				.findById(refStructureDto.getTypeId());
		if (ncTypeStructureDto != null && ncTypeStructureDto.getCode() != null) {
			refStructureDto.setTypeCode(ncTypeStructureDto.getCode());
		}

		String prefix;
		if (ncTypeStructureDto.getLibelleLongFr().toLowerCase()
				.equals(STRUCTURE_EXTERNE.toLowerCase())) {
			prefix = refParametreEtabServiceImpl.findValueOfKey(
					SessionValues.getIdEtablissement(),
					RefUtilConstant.CODIFICATION_ETABLISSEMENT_MESRS_KEY);
			if (prefix == null) {
				return null;
			}
			prefix = prefix + refStructureDto.getTypeCode();

		} else {
			prefix = SessionValues.getIdfEtablissement()
					+ refStructureDto.getTypeCode();
		}
		int prefixLength = prefix.length();
		String order = refParametreEtabServiceImpl.findValueOfKey(
				SessionValues.getIdEtablissement(),
				RefUtilConstant.CODIFICATION_STRUCTURE_ORDER_LENGTH_KEY);
		if (order == null) {
			return null;
		}
		int orderLength = Integer.parseInt(order);
		identifiantLength = prefixLength + orderLength;
		return refStructureServiceImpl.generateIdentify(prefix, orderLength);

	}

	/**
	 * [StructureMgrBean.nomenclatureServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 avr. 2014 15:15:20
	 * @return the nomenclatureServiceImpl
	 */
	public NomenclatureService getNomenclatureServiceImpl() {
		return nomenclatureServiceImpl;
	}

	/**
	 * [StructureMgrBean.nomenclatureServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 avr. 2014 15:15:20
	 * @param nomenclatureServiceImpl
	 *            the nomenclatureServiceImpl to set
	 */
	public void setNomenclatureServiceImpl(
			NomenclatureService nomenclatureServiceImpl) {
		this.nomenclatureServiceImpl = nomenclatureServiceImpl;
	}

	/**
	 * [StructureMgrBean.refParametreEtabServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 17:49:08
	 * @return the refParametreEtabServiceImpl
	 */
	public RefParametreEtabService getRefParametreEtabServiceImpl() {
		return refParametreEtabServiceImpl;
	}

	/**
	 * [StructureMgrBean.refParametreEtabServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 17:49:08
	 * @param refParametreEtabServiceImpl
	 *            the refParametreEtabServiceImpl to set
	 */
	public void setRefParametreEtabServiceImpl(
			RefParametreEtabService refParametreEtabServiceImpl) {
		this.refParametreEtabServiceImpl = refParametreEtabServiceImpl;
	}

	/**
	 * [StructureMgrBean.identifiantLength] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 avr. 2014 11:37:58
	 * @return the identifiantLength
	 */
	public int getIdentifiantLength() {
		return identifiantLength;
	}

	/**
	 * [StructureMgrBean.identifiantLength] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 avr. 2014 11:37:58
	 * @param identifiantLength
	 *            the identifiantLength to set
	 */
	public void setIdentifiantLength(int identifiantLength) {
		this.identifiantLength = identifiantLength;
	}

	/**
	 * [StructureMgrBean.comboBckBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 avr. 2014 11:00:35
	 * @return the comboBckBean
	 */
	public ComboBckBean getComboBckBean() {
		return comboBckBean;
	}

	/**
	 * [StructureMgrBean.comboBckBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 avr. 2014 11:00:35
	 * @param comboBckBean
	 *            the comboBckBean to set
	 */
	public void setComboBckBean(ComboBckBean comboBckBean) {
		this.comboBckBean = comboBckBean;
	}

	/**
	 * [StructureMgrBean.setDefaultValues] Method
	 * 
	 * @author MAKERRI Sofiane on : 29 avr. 2014 11:02:46
	 */
	public void setDefaultValues() {
		refStructureDto.setTypeId(comboBckBean.getDefaultTypeStructure());
		refStructureDto.setStatusId(comboBckBean.getDefaultStatut());
		refStructureDto.setFormeJuridiqueId(comboBckBean
				.getDefaultFormeJuridique());
	}

	/**
	 * [StructureMgrBean.codeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 juil. 2014 18:09:05
	 * @param event
	 */
	public void codeChanged(ValueChangeEvent event) {
		codeChange = true;
	}

	/**
	 * [StructureMgrBean.codeChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 juil. 2014 18:08:48
	 * @return the codeChange
	 */
	public boolean isCodeChange() {
		return codeChange;
	}

	/**
	 * [StructureMgrBean.codeChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 juil. 2014 18:08:48
	 * @param codeChange
	 *            the codeChange to set
	 */
	public void setCodeChange(boolean codeChange) {
		this.codeChange = codeChange;
	}

	/**
	 * [StructureMgrBean.listRefStructureDto] Getter 
	 * @author MAKERRI Sofiane on : 6 janv. 2015  15:25:53
	 * @return the listRefStructureDto
	 */
	public List<RefStructureDto> getListRefStructureDto() {
		return listRefStructureDto;
	}

	/**
	 * [StructureMgrBean.listRefStructureDto] Setter 
	 * @author MAKERRI Sofiane on : 6 janv. 2015  15:25:53
	 * @param listRefStructureDto the listRefStructureDto to set
	 */
	public void setListRefStructureDto(List<RefStructureDto> listRefStructureDto) {
		this.listRefStructureDto = listRefStructureDto;
	}
	
	
	

}
