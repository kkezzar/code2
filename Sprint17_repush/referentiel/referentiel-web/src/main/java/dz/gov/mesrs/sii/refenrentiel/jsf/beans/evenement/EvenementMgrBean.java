/**
 * 
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.evenement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ComponentSystemEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.RowEditEvent;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.ComboBckBean;
import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.SessionValues;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEvenementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefTacheDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefEvenementService;
import dz.gov.mesrs.sii.referentiel.business.service.RefTacheService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author jbeldi
 * 
 */
@ManagedBean(name = "evenementMgrBean")
@RequestScoped
public class EvenementMgrBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(EvenementMgrBean.class);

	private String searchInput;
	private RefEvenementDto searchDto;
	private List<RefEvenementDto> listRefEvenementDto;
	private List<RefTacheDto> listTacheOfEvenement;
	private RefEvenementDto refEvenementDto;
	private ResourceBundle bundle;
	private static final String MESSAGES_FILE_NAME = "commonmsgs";
	private boolean updateMode;
	@ManagedProperty(value = "#{refEvenementServiceImpl}")
	private RefEvenementService refEvenementServiceImpl;
	@ManagedProperty(value = "#{refTacheServiceImpl}")
	private RefTacheService refTacheServiceImpl;
	@ManagedProperty("#{param.idf}")
	private String idf;
	private Integer id;
	@ManagedProperty("#{flash}")
	private Flash flash;
	@ManagedProperty(value = "#{comboBckBean}")
	private ComboBckBean comboBckBean;

	/**
	 * Constructor of EvenementMgrBean
	 */
	public EvenementMgrBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				EvenementMgrBean.MESSAGES_FILE_NAME);
		searchDto = new RefEvenementDto();

	}

	@PostConstruct
	public void init() {
		log.info("******************************@PostConstruct: ");
		 if(idf!=null && !idf.equals("null") || updateMode){
	        	refEvenementDto = refEvenementServiceImpl.findById(Integer.valueOf(idf));
	        	updateMode = true;
	        	pullValuesFromFlash(null);
	        	if(listTacheOfEvenement==null|| listTacheOfEvenement.isEmpty()){
	        		loadTaches();
	        	}
	        	
	        	
	        }else{
	        	pullValuesFromFlash(null);
	        	if(listTacheOfEvenement==null){
	        		loadTaches();
	        	}
	        	
	        	updateMode = false;
	        }
	}

	public void setFlash(Flash flash) {
		this.flash = flash;
	}

	public Flash getFlash() {
		return flash;
	}

	/**
	 * [EvenementMgrBean.refEvenementServiceImpl] Getter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 12:41:32
	 * @return the refEvenementServiceImpl
	 */
	public RefEvenementService getRefEvenementServiceImpl() {
		return refEvenementServiceImpl;
	}

	/**
	 * [EvenementMgrBean.refEvenementServiceImpl] Setter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 12:41:32
	 * @param refEvenementServiceImpl
	 *            the refEvenementServiceImpl to set
	 */
	public void setRefEvenementServiceImpl(
			RefEvenementService refEvenementServiceImpl) {
		this.refEvenementServiceImpl = refEvenementServiceImpl;
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
	 * [EvenementMgrBean.searchDto] Getter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 12:43:12
	 * @return the searchDto
	 */
	public RefEvenementDto getSearchDto() {
		return searchDto;
	}

	/**
	 * [EvenementMgrBean.searchDto] Setter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 12:43:12
	 * @param searchDto
	 *            the searchDto to set
	 */
	public void setSearchDto(RefEvenementDto searchDto) {
		this.searchDto = searchDto;
	}

	/**
	 * @return the updateMode
	 */
	public boolean getUpdateMode() {

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
	public String toSearch() {

		return "toSearchEvenement";
	}

	
	
	
	
	
	public String saveEvenement() {
		log.info("******************************saveEvenement***********");
		FacesMessage msg = new FacesMessage();
		try {
			if(refEvenementDto.getDateDebut()==null || refEvenementDto.getDateFin()==null || refEvenementDto.getDateDebut().after(refEvenementDto.getDateFin()) ){
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundle.getString("error_date_debut_fin") );
				FacesContext.getCurrentInstance().addMessage(null, msg);	
			}else if (updateMode) {
				refEvenementDto.setIdEtablissement(SessionValues.getIdEtablissement());
				refEvenementServiceImpl.update(refEvenementDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_modification"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				
			} else {
				refEvenementDto.setIdEtablissement(SessionValues.getIdEtablissement());
				Integer id = refEvenementServiceImpl.save(refEvenementDto);
				refEvenementDto.setId(id);
				setIdf(id.toString());
				setUpdateMode(true);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_enregistrement"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			
			log.info("******************************Success:saveEvenement***********");
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.info(e.getMessage());
		}
//		flash.put("refEvenementDto", refEvenementDto);
//		flash.keep("refEvenementDto");
		flash.setKeepMessages(true);

		return  "evenementEdit?faces-redirect=true&idf="+idf;
	}

	/**
	 * [EvenementMgrBean.listRefEvenementDto] Getter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 12:45:37
	 * @return the listRefEvenementDto
	 */
	public List<RefEvenementDto> getListRefEvenementDto() {

		return listRefEvenementDto;
	}

	/**
	 * [EvenementMgrBean.listRefEvenementDto] Setter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 12:45:37
	 * @param listRefEvenementDto
	 *            the listRefEvenementDto to set
	 */
	public void setListRefEvenementDto(List<RefEvenementDto> listRefEvenementDto) {
		this.listRefEvenementDto = listRefEvenementDto;
	}

	/**
	 * [EvenementMgrBean.refEvenementDto] Getter
	 * 
	 * @author BELDI Jamel on : 16 f�vr. 2014 18:06:47
	 * @return the refEvenementDto
	 */
	public RefEvenementDto getRefEvenementDto() {
		return refEvenementDto;
	}

	/**
	 * [EvenementMgrBean.refEvenementDto] Setter
	 * 
	 * @author BELDI Jamel on : 16 f�vr. 2014 18:06:47
	 * @param refEvenementDto
	 *            the refEvenementDto to set
	 */
	public void setRefEvenementDto(RefEvenementDto refEvenementDto) {
		this.refEvenementDto = refEvenementDto;
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
//		refEvenementDto = (RefEvenementDto) flash
//				.get("refEvenementDto");		
//		flash.keep("refEvenementDto");
		if(refEvenementDto==null){
			refEvenementDto = new RefEvenementDto();
			setDefaultValues();
		}
		listTacheOfEvenement = (List<RefTacheDto>) flash
				.get("listTacheOfEvenement");	
		flash.keep("listTacheOfEvenement");
		log.info("******************************pullValuesFromFlash refEvenementDto: "
				+ refEvenementDto);
		log.info("******************************pullValuesFromFlash listTacheOfEvenement: "
				+ listTacheOfEvenement);

	}

	

	private void loadTaches() {
		if (refEvenementDto != null) {
			try {
				listTacheOfEvenement = refTacheServiceImpl
						.findByEvenement(refEvenementDto.getId());
//				flash.put("listTacheOfEvenement", listTacheOfEvenement);
//				flash.keep("listTacheOfEvenement");
				log.info(listTacheOfEvenement.size());
				
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage();
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundle.getString("error_echec") + ": "
						+ bundle.getString("error_echec_inconnue"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				log.info(e.getMessage());
			}
		} else {
			listTacheOfEvenement = new ArrayList<RefTacheDto>();
//			flash.put("listTacheOfEvenement", listTacheOfEvenement);
//			flash.keep("listTacheOfEvenement");
			log.info(listTacheOfEvenement.size());
		}
	}

	/**
	 * [EvenementMgrBean.listTacheOfEvenement] Getter
	 * 
	 * @author BELDI Jamel on : 19 f�vr. 2014 16:04:57
	 * @return the listTacheOfEvenement
	 */
	public List<RefTacheDto> getListTacheOfEvenement() {
		//loadTaches();
		return listTacheOfEvenement;
	}

	/**
	 * [EvenementMgrBean.listTacheOfEvenement] Setter
	 * 
	 * @author BELDI Jamel on : 19 f�vr. 2014 16:04:57
	 * @param listTacheOfEvenement
	 *            the listTacheOfEvenement to set
	 */
	public void setListTacheOfEvenement(List<RefTacheDto> listTacheOfEvenement) {
		this.listTacheOfEvenement = listTacheOfEvenement;
	}

	public void onEdit(RowEditEvent event) {
		// FacesMessage msg = new FacesMessage("palier Edited",
		log.info("onEdit:");
		
		RefTacheDto element = ((RefTacheDto) event.getObject());
		element.setActive(true);
		if(element.getId()!=null){
		refTacheServiceImpl.update( element);
		}else{
			element.setEvenementId(Integer.valueOf(idf));
			refTacheServiceImpl.save( element);
		}
		loadTaches();
		// FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCancel(RowEditEvent event) {
		// FacesMessage msg = new FacesMessage("Palier Cancelled",
		// ((Bareme0cdrPalierDTO) event.getObject()).getnPalier());
		log.info("onCancel:");
		// FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String addTache() {
		
		log.info("addTache:");
		
		RefTacheDto element = new RefTacheDto();
		listTacheOfEvenement.add(element);
		flash.put("listTacheOfEvenement", listTacheOfEvenement);
		flash.keep("listTacheOfEvenement");
		return  "evenementEdit?faces-redirect=true&idf="+idf;
	}

	public void deleteTache(RefTacheDto element) {
		log.info("deleteTache:");
		 if (element!=null && element.getId()!=null){
			 element.setActive(false);
			 //suppression physique
			// refTacheServiceImpl.delete(element.getId());
			 //suppression logique
			refTacheServiceImpl.update( element);
			listTacheOfEvenement.remove(element);
		 }
		 else{
			 listTacheOfEvenement.remove(element);
		 }
		  
		
	}

	
	

	/**
	 * [EvenementMgrBean.refTacheServiceImpl] Getter
	 * 
	 * @author BELDI Jamel on : 19 f�vr. 2014 16:03:34
	 * @return the refTacheServiceImpl
	 */
	public RefTacheService getRefTacheServiceImpl() {
		return refTacheServiceImpl;
	}

	/**
	 * [EvenementMgrBean.refTacheServiceImpl] Setter
	 * 
	 * @author BELDI Jamel on : 19 f�vr. 2014 16:03:34
	 * @param refTacheServiceImpl
	 *            the refTacheServiceImpl to set
	 */
	public void setRefTacheServiceImpl(RefTacheService refTacheServiceImpl) {
		this.refTacheServiceImpl = refTacheServiceImpl;
	}

	/**
	 * [EvenementMgrBean.idf] Getter 
	 * @author BELDI Jamel on : 5 mars 2014  11:16:18
	 * @return the idf
	 */
	public String getIdf() {
		return idf;
	}

	/**
	 * [EvenementMgrBean.idf] Setter 
	 * @author BELDI Jamel on : 5 mars 2014  11:16:18
	 * @param idf the idf to set
	 */
	public void setIdf(String idf) {
		if(idf != null && !idf.trim().isEmpty()) {
			int _id = RefUtilConstant.strToInt(idf);
			if(_id != -1) {
				setId(new Integer(_id));
			}
		}
		this.idf = idf;
	}
	
	
	/**
	 * [EvenementMgrBean.setDefaultValues] Method 
	 * @author MAKERRI Sofiane  on : 29 avr. 2014  12:05:44
	 */
	public void setDefaultValues() {
		refEvenementDto.setTypeId(comboBckBean.getDefaultTypeEvenement());
		refEvenementDto.setFrequenceRepetitionId(comboBckBean.getDefaultRepetition());
	}

	/**
	 * [EvenementMgrBean.comboBckBean] Getter 
	 * @author MAKERRI Sofiane on : 29 avr. 2014  12:05:55
	 * @return the comboBckBean
	 */
	public ComboBckBean getComboBckBean() {
		return comboBckBean;
	}

	/**
	 * [EvenementMgrBean.comboBckBean] Setter 
	 * @author MAKERRI Sofiane on : 29 avr. 2014  12:05:55
	 * @param comboBckBean the comboBckBean to set
	 */
	public void setComboBckBean(ComboBckBean comboBckBean) {
		this.comboBckBean = comboBckBean;
	}

	/**
	 * [EvenementMgrBean.id] Getter 
	 * @author MAKERRI Sofiane on : 12 mai 2014  13:21:05
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [EvenementMgrBean.id] Setter 
	 * @author MAKERRI Sofiane on : 12 mai 2014  13:21:05
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	
	
}
