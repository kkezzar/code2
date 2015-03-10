
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.domainelmd;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.RowEditEvent;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFiliereLmdDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefFiliereLmdService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author BELBRIK Oualid on : 23 mars. 2014 13:47:10
 */
@ManagedBean(name ="domainefiliereMgrBean")
@RequestScoped
public class DomaineFiliereMgrBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELBRIK Oualid on : 23 mars. 2014 14:24:15
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * [FiliereLieuBean.FiliereLieuBean()] Constructor
	 * 
	 * @author BELBRIK Oualid on : 2 janv. 2014 13:47:10
	 */
	private List<RefFiliereLmdDto> listRefFiliereLmdDto;
	@ManagedProperty(value = "#{refFiliereLmdServiceImpl}")
	private RefFiliereLmdService refFiliereLmdServiceImpl;
		private RefFiliereLmdDto refFiliereLmdDto;
	private ResourceBundle bundle;
	private static final Log log = LogFactory.getLog(DomaineFiliereMgrBean.class);
	@ManagedProperty("#{param.idf}")
	private String idf;
	private Integer id;
	@ManagedProperty("#{param.searchValue}")
	private String searchValue;
	@ManagedProperty("#{param.editMode}")
	private boolean editMode;
	@ManagedProperty("#{param.entitiname}")
	private String entitiname;
	
	public DomaineFiliereMgrBean() {
		super();
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
		Boolean b= (Boolean) request.getAttribute("editMode");
		Integer _id =  (Integer) request.getAttribute("id");
		if(b!=null){
			this.editMode=b;
		}
		if(_id!=null){
			this.idf = _id.toString();
			this.id = _id;
		}
		log.info("var request*******************************************editMode: "+b+ " idf:"+idf);
		refFiliereLmdDto = new RefFiliereLmdDto();
		bundle = context.getApplication().getResourceBundle(context,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
	}
	
	@PostConstruct
	public void init() {
		load();
	  
	}

	/**
	 * [FiliereLieuBean.refFiliereLmdServiceImpl] Getter
	 * 
	 * @author BELBRIK Oualid on : 23 mars. 2014 13:50:32
	 * @return the refFiliereLmdServiceImpl
	 */
	public RefFiliereLmdService getRefFiliereLmdServiceImpl() {
		return refFiliereLmdServiceImpl;
	}

	/**
	 * [FiliereLieuBean.refFiliereLmdServiceImpl] Setter
	 * 
	 * @author BELBRIK Oualid on : 23 mars. 2014 13:50:32
	 * @param refFiliereLmdServiceImpl
	 *            the refFiliereLmdServiceImpl to set
	 */
	public void setRefFiliereLmdServiceImpl(
			RefFiliereLmdService refFiliereLmdServiceImpl) {
		this.refFiliereLmdServiceImpl = refFiliereLmdServiceImpl;
	}


	
	public void load() {
		try {
			List<RefFiliereLmdDto> result = null;
			if(id != null) {
			 result = refFiliereLmdServiceImpl.findByIdDomainelmd(id);
			}
			if (result == null || result.isEmpty()) {
				log.info("list of FiliereLmd is null");
				setListRefFiliereLmdDto(null);

			} else {
				
					listRefFiliereLmdDto = new ArrayList<RefFiliereLmdDto>();
									
					for (RefFiliereLmdDto currentRefFiliereLmdDto : result) {
						if(currentRefFiliereLmdDto.getIdentifiant() != null) {
							listRefFiliereLmdDto.add(currentRefFiliereLmdDto);
						}
											   
					}
					log.info("list of FiliereLmd:"+listRefFiliereLmdDto.size());
					
					
			}
		} catch (Exception e) {
			log.info(e.getMessage());	
			
		}
		
	}

	
	
	
	/**
	 * [FiliereLieuBean.listRefFiliereLmdDto] Getter 
	 * @author BELBRIK Oualid on : 23 mars. 2014  16:09:29
	 * @return the listRefFiliereLmdDto
	 */
	public List<RefFiliereLmdDto> getListRefFiliereLmdDto() {
		return listRefFiliereLmdDto;
	}

	/**
	 * [FiliereLieuBean.listRefFiliereLmdDto] Setter 
	 * @author BELBRIK Oualid on : 23 mars. 2014  16:09:29
	 * @param listRefFiliereLmdDto the listRefFiliereLmdDto to set
	 */
	public void setListRefFiliereLmdDto(List<RefFiliereLmdDto> listRefFiliereLmdDto) {
		this.listRefFiliereLmdDto = listRefFiliereLmdDto;
	}

	/**
	 * [FiliereLieuBean.listRefIndividuDto] Getter 
	 * @author BELBRIK Oualid on :23 mars. 2014  17:51:32
	 * @return the listRefIndividuDto
	 */
	
    
    
	/**
	 * [FiliereLieuBean.refFiliereLmdDto] Getter 
	 * @author BELBRIK Oualid on : 23 mars. 2014  15:51:43
	 * @return the refFiliereLmdDto
	 */
	public RefFiliereLmdDto getRefFiliereLmdDto() {
		return refFiliereLmdDto;
	}

	/**
	 * [FiliereLieuBean.refFiliereLmdDto] Setter 
	 * @author BELBRIK Oualid on : 23 mars. 2014  15:51:43
	 * @param refFiliereLmdDto the refFiliereLmdDto to set
	 */
	public void setRefFiliereLmdDto(RefFiliereLmdDto refFiliereLmdDto) {
		this.refFiliereLmdDto = refFiliereLmdDto;
	}

	
      
	/**
	 * onFiliereLmdCancel, Cancel modify the  signature date and reference
	 * @author BELDI Jamel on : 23 mars. 2014  15:51:43
	 * @param event the RowEditEvent
	 */
    public void onSignatureCancel(RowEditEvent event) {  
      
    }

	/**
	 * [FiliereEquipementMgrBean.bundle] Getter 
	 * @author BELBRIK Oualid on : 23 mars. 2014  17:03:32
	 * @return the bundle
	 */
	public ResourceBundle getBundle() {
		return bundle;
	}

	/**
	 * [FiliereEquipementMgrBean.bundle] Setter 
	 * @author BELBRIK Oualid on : 23 mars. 2014  17:03:32
	 * @param bundle the bundle to set
	 */
	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * [FiliereEquipementMgrBean.idf] Getter 
	 * @author BELBRIK Oualid on : 23 mars. 2014  17:03:32
	 * @return the idf
	 */
	public String getIdf() {
		return idf;
	}

	/**
	 * [FiliereEquipementMgrBean.idf] Setter 
	 * @author BELBRIK Oualid on : 23 mars. 2014  17:03:32
	 * @param idf the idf to set
	 */
	public void setIdf(String idf) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		Integer _id = (Integer) request.getAttribute("id");
		log.info("var request*******************************************id: "
				+ _id);
		if (_id != null) {
			this.idf = _id.toString();
			this.id = _id;
		} else {

			//this.identifiant = identifiant;
			if ((idf != null) && (idf.equals("null"))) {
				this.idf  = null;
				
			} else {
				this.idf = idf;
			}
		}
	}

	/**
	 * [FiliereEquipementMgrBean.searchValue] Getter 
	 * @author BELBRIK Oualid on : 23 mars. 2014  17:03:32
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [FiliereEquipementMgrBean.searchValue] Setter 
	 * @author BELBRIK Oualid on : 23 mars. 2014  17:03:32
	 * @param searchValue the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		if ((searchValue != null) && (searchValue.equals("null"))) {
			this.searchValue = "";
		} else	this.searchValue = searchValue;
	}

	/**
	 * [FiliereEquipementMgrBean.editMode] Getter 
	 * @author BELBRIK Oualid on : 23 mars. 2014  11:39:03
	 * @return the editMode
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * [FiliereEquipementMgrBean.editMode] Setter 
	 * @author BELBRIK Oualid on : 23 mars. 2014  17:39:03
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



	/**
	 * [FiliereEquipementMgrBean.entitiname] Getter 
	 * @author BELBRIK Oualid on : 22 mars 2014  12:05:41
	 * @return the entitiname
	 */
	public String getEntitiname() {
		return entitiname;
	}

	/**
	 * [FiliereEquipementMgrBean.entitiname] Setter 
	 * @author BELBRIK Oualid on : 22 mars 2014  12:05:41
	 * @param entitiname the entitiname to set
	 */
	public void setEntitiname(String entitiname) {
		if ((entitiname != null) && (entitiname.equals("null"))) {
			this.entitiname  = null;
			
		} else {
			this.entitiname = entitiname;
		}
	}

	/**
	 * [DomaineFiliereMgrBean.id] Getter 
	 * @author MAKERRI Sofiane on : 18 mai 2014  08:19:39
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [DomaineFiliereMgrBean.id] Setter 
	 * @author MAKERRI Sofiane on : 18 mai 2014  08:19:39
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	
}

