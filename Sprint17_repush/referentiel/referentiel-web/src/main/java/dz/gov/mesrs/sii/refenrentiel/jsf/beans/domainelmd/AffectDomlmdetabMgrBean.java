
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.domainelmd;

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
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.RowEditEvent; 

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectDomLmdEtabDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefAffectDomLmdEtabService;
import dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author BELBRIK Oualid on : 20 avril. 2014 13:47:10
 */
@ManagedBean(name ="affectationdomlmdetabMgrBean")
@RequestScoped
public class AffectDomlmdetabMgrBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELBRIK Oualid on : 19 avril. 2014 14:24:15
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * [AffectationEtablissementBean.AffectationEtablissementBean()] Constructor
	 * 
	 * @author BELBRIK Oualid on : 2 janv. 2014 13:47:10
	 */
	private List<RefAffectDomLmdEtabDto> listRefEtablissementDto;
	@ManagedProperty(value = "#{refAffectDomLmdEtabServiceImpl}")
	private RefAffectDomLmdEtabService refAffectDomLmdEtabServiceImpl;
	@ManagedProperty(value = "#{refEtablissementServiceImpl}")
	private RefEtablissementService refEtablissementServiceImpl;
	private RefAffectDomLmdEtabDto refAffectDomLmdEtabDto;
	private ResourceBundle bundle;
	private static final Log log = LogFactory.getLog(AffectDomlmdetabMgrBean.class);
	@ManagedProperty("#{param.id}")
	private String id;
	private Integer idDomaine;
	@ManagedProperty("#{param.searchValue}")
	private String searchValue;
	@ManagedProperty("#{param.editMode}")
	private boolean editMode;
	@ManagedProperty(value = "#{DomaineAffectationCrudBean}")
	private DomaineAffectationCrudBean domaineAffectationCrudBean;
	@ManagedProperty("#{param.idr}")
	private String idr;
	@ManagedProperty("#{param.entitiname}")
	private String entitiname;


	public AffectDomlmdetabMgrBean() {
		super();
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
		Boolean b= (Boolean) request.getAttribute("editMode");
		Integer _id =  (Integer) request.getAttribute("id");
		if(b!=null){
			this.editMode=b;
		}
		if(_id != null){
			this.id = _id.toString();
			this.idDomaine = _id;
		}
		
		log.info("var request*******************************************editMode: "+b+ " id:"+id);
		refAffectDomLmdEtabDto = new RefAffectDomLmdEtabDto();
		bundle = context.getApplication().getResourceBundle(context,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
	}
	
	@PostConstruct
	public void init() {
		load();
	  
	}

	/**
	 * [AffectationEtablissementBean.refAffectDomLmdEtabServiceImpl] Getter
	 * 
	 * @author BELBRIK Oualid on : 19 avril. 2014 13:50:32
	 * @return the refAffectDomLmdEtabServiceImpl
	 */
	public RefAffectDomLmdEtabService getRefAffectDomLmdEtabServiceImpl() {
		return refAffectDomLmdEtabServiceImpl;
	}

	/**
	 * [AffectationEtablissementBean.refAffectDomLmdEtabServiceImpl] Setter
	 * 
	 * @author BELBRIK Oualid on : 19 avril. 2014 13:50:32
	 * @param refAffectDomLmdEtabServiceImpl
	 *            the refAffectDomLmdEtabServiceImpl to set
	 */
	public void setRefAffectDomLmdEtabServiceImpl(
			RefAffectDomLmdEtabService refAffectDomLmdEtabServiceImpl) {
		this.refAffectDomLmdEtabServiceImpl = refAffectDomLmdEtabServiceImpl;
	}


	/**
	 * [AffectDomlmdetabMgrBean.equipementBckBean] Getter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  16:10:24
	 * @return the equipementBckBean
	 */
	public DomaineAffectationCrudBean getDomaineAffectationCrudBean() {
		return domaineAffectationCrudBean;
	}
	
	/**
	 * [AffectDomlmdetabMgrBean.equipementBckBean] Setter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  16:10:24
	 * @param equipementBckBean the equipementBckBean to set
	 */
	public void setDomaineAffectationCrudBean(DomaineAffectationCrudBean domaineAffectationCrudBean) {
		this.domaineAffectationCrudBean = domaineAffectationCrudBean;
	}
	
	/**
	 * [AffectDomlmdetabMgrBean.crud] Method 
	 * @author MAKERRI Sofiane  on : 22 mars 2014  16:10:27
	 * @param createAllow
	 * @param editAllow
	 * @param deleteAllow
	 */
	public void crud(boolean createAllow, boolean editAllow, boolean deleteAllow) {
		domaineAffectationCrudBean.setEditAllow(editAllow);
		domaineAffectationCrudBean.setCreateAllow(createAllow);
		domaineAffectationCrudBean.setDeleteAllow(deleteAllow);
	}

	
	
	public void load() {
		try {
			List<RefAffectDomLmdEtabDto> result = null;
			if(idDomaine != null) {
			 result = refAffectDomLmdEtabServiceImpl.findByIdDomainelmd(idDomaine);
			}
			if (result == null || result.isEmpty()) {
				log.info("list of affectation is null");
				setListRefEtablissementDto(null);				

			} else {
				
					listRefEtablissementDto = new ArrayList<RefAffectDomLmdEtabDto>();
					
					for (RefAffectDomLmdEtabDto currentRefAffectDomLmdEtabDto : result) {
						if(currentRefAffectDomLmdEtabDto.getIdentifiantEtablissement() != null) {
							listRefEtablissementDto.add(currentRefAffectDomLmdEtabDto);
						
					}
					log.info("list of Etablissement :"+listRefEtablissementDto.size());
					
					}	
			}
		} catch (Exception e) {
			log.info(e.getMessage());	
			
		}
		
	}


	/**
	 * [AffectationEtablissementBean.listRefEtablissementDto] Getter 
	 * @author BELBRIK Oualid on : 19 avril. 2014  16:09:29
	 * @return the listRefEtablissementDto
	 */
	public List<RefAffectDomLmdEtabDto> getListRefEtablissementDto() {
		return listRefEtablissementDto;
	}

	/**
	 * [AffectationEtablissementBean.listRefEtablissementDto] Setter 
	 * @author BELBRIK Oualid on : 19 avril. 2014  16:09:29
	 * @param listRefEtablissementDto the listRefEtablissementDto to set
	 */
	public void setListRefEtablissementDto(List<RefAffectDomLmdEtabDto> listRefEtablissementDto) {
		this.listRefEtablissementDto = listRefEtablissementDto;
	}

	
	/**
	 * [AffectationEtablissementBean.refEtablissementServiceImpl] Getter 
	 * @author BELBRIK Oualid on : 19 avril. 2014  11:18:37
	 * @return the refEtablissementServiceImpl
	 */
	public RefEtablissementService getRefEtablissementServiceImpl() {
		return refEtablissementServiceImpl;
	}

	/**
	 * [AffectationEtablissementBean.refEtablissementServiceImpl] Setter 
	 * @author BELBRIK Oualid on : 19 avril. 2014  11:18:37
	 * @param refEtablissementServiceImpl the refEtablissementServiceImpl to set
	 */
	public void setRefEtablissementServiceImpl(
			RefEtablissementService refEtablissementServiceImpl) {
		this.refEtablissementServiceImpl = refEtablissementServiceImpl;
	}

		
	
	public void addEtablissement() {
		FacesMessage msg = new FacesMessage();
		if ((refAffectDomLmdEtabDto != null) && (refAffectDomLmdEtabDto.getIdEtablissement()!=null)) {
			try {
				refAffectDomLmdEtabDto.setIdDomaineLMD(idDomaine);
				log.info("saving Etablissement id = "+refAffectDomLmdEtabDto.getIdEtablissement());
							
			  refAffectDomLmdEtabServiceImpl.save(refAffectDomLmdEtabDto);
			  load(); 
			  msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_enregistrement"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			catch(Exception e) {
				log.info(e.getMessage());
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundle.getString("error_echec") + ": "
						+ bundle.getString("enregistrement_existe"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
		
	}
	
	public void deleteEtablissement(RefAffectDomLmdEtabDto selectedEtablissementAffectationEtablissementDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedEtablissementAffectationEtablissementDto != null) && (selectedEtablissementAffectationEtablissementDto.getIdentifiantEtablissement()!=null)) {
			try {
				log.info("deleting Etablissement id = "+selectedEtablissementAffectationEtablissementDto.getIdentifiantEtablissement());			
			  refAffectDomLmdEtabServiceImpl.delete(selectedEtablissementAffectationEtablissementDto);
			  load();
			  msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_suppression"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			catch(Exception e) {
				log.info(e.getMessage());	
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundle.getString("error_echec") + ": "
						+ bundle.getString("error_echec_inconnue"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
		
	}
	
	
	public void save() {
		FacesMessage msg = new FacesMessage();
		try {
			log.info("Mgr Bean saveAffectDomLmdEtab");
			refAffectDomLmdEtabDto.setId(Integer.parseInt(idr));
			refAffectDomLmdEtabDto.setIdDomaineLMD(idDomaine);
			
				refAffectDomLmdEtabServiceImpl.update(refAffectDomLmdEtabDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_modification"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.info(e.getMessage());
		}
	}

        
	/**
	 * [AffectationEtablissementBean.refAffectDomLmdEtabDto] Getter 
	 * @author BELBRIK Oualid on : 19 avril. 2014  15:51:43
	 * @return the refAffectDomLmdEtabDto
	 */
	public RefAffectDomLmdEtabDto getRefAffectDomLmdEtabDto() {
		return refAffectDomLmdEtabDto;
	}

	/**
	 * [AffectationEtablissementBean.refAffectDomLmdEtabDto] Setter 
	 * @author BELBRIK Oualid on : 19 avril. 2014  15:51:43
	 * @param refAffectDomLmdEtabDto the refAffectDomLmdEtabDto to set
	 */
	public void setRefAffectDomLmdEtabDto(RefAffectDomLmdEtabDto refAffectDomLmdEtabDto) {
		this.refAffectDomLmdEtabDto = refAffectDomLmdEtabDto;
	}

	
      
	/**
	 * onEtablissementCancel, Cancel modify the  signature date and reference
	 * @author BELDI Jamel on : 19 avril. 2014  15:51:43
	 * @param event the RowEditEvent
	 */
    public void onSignatureCancel(RowEditEvent event) {  
      
    }

	/**
	 * [AffectDomlmdetabMgrBean.bundle] Getter 
	 * @author BELBRIK Oualid on : 19 avril. 2014  17:03:32
	 * @return the bundle
	 */
	public ResourceBundle getBundle() {
		return bundle;
	}

	/**
	 * [AffectDomlmdetabMgrBean.bundle] Setter 
	 * @author BELBRIK Oualid on : 19 avril. 2014  17:03:32
	 * @param bundle the bundle to set
	 */
	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	

	/**
	 * [AffectDomlmdetabMgrBean.searchValue] Getter 
	 * @author BELBRIK Oualid on : 19 avril. 2014  17:03:32
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [AffectDomlmdetabMgrBean.searchValue] Setter 
	 * @author BELBRIK Oualid on : 19 avril. 2014  17:03:32
	 * @param searchValue the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		if ((searchValue != null) && (searchValue.equals("null"))) {
			this.searchValue = "";
		} else	this.searchValue = searchValue;
	}

	/**
	 * [AffectDomlmdetabMgrBean.editMode] Getter 
	 * @author BELBRIK Oualid on : 19 avril. 2014  11:39:03
	 * @return the editMode
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * [AffectDomlmdetabMgrBean.editMode] Setter 
	 * @author BELBRIK Oualid on : 19 avril. 2014  17:39:03
	 * @param editMode the editMode to set
	 */
	public void setEditMode(boolean editMode) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
		Boolean b= (Boolean) request.getAttribute("editMode");
		log.info("var request in set editMode: "+b );
		if(b!=null){
			this.editMode=b;
		}
		else{
		this.editMode = editMode;
		}
	}  
    
	public String getIdr() {
		return idr;
	}


	public void setIdr(String idr) {
		if ((idr != null) && (idr.equals("null"))) {
			this.idr  = null;
			
		} else {
			this.idr = idr;
		}
	}
	
	public String getEntitiname() {
		return entitiname;
	}


	public void setEntitiname(String entitiname) {
		if ((entitiname != null) && (entitiname.equals("null"))) {
			this.entitiname  = null;
			
		} else {
			this.entitiname = entitiname;
		}
	}

	/**
	 * [AffectDomlmdetabMgrBean.id] Getter 
	 * @author MAKERRI Sofiane on : 15 mai 2014  16:10:45
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * [AffectDomlmdetabMgrBean.id] Setter 
	 * @author MAKERRI Sofiane on : 15 mai 2014  16:10:45
	 * @param id the id to set
	 */
	public void setId(String id) {
		if ((id != null) && (id.equals("null"))) {
			this.id = null;
		} else	this.id = id;
		int _id = RefUtilConstant.strToInt(id);
		if(_id != -1) {
			setIdDomaine(_id);
		}
	}

	/**
	 * [AffectDomlmdetabMgrBean.idDomaine] Getter 
	 * @author MAKERRI Sofiane on : 15 mai 2014  16:15:17
	 * @return the idDomaine
	 */
	public Integer getIdDomaine() {
		return idDomaine;
	}

	/**
	 * [AffectDomlmdetabMgrBean.idDomaine] Setter 
	 * @author MAKERRI Sofiane on : 15 mai 2014  16:15:17
	 * @param idDomaine the idDomaine to set
	 */
	public void setIdDomaine(Integer idDomaine) {
		this.idDomaine = idDomaine;
	}
	
	
	

}
