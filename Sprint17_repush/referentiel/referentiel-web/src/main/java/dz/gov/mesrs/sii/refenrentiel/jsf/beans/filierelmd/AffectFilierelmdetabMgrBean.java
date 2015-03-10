
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.filierelmd;

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

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectFiliereLmdEtabDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefAffectFiliereLmdEtabService;
import dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author BELBRIK Oualid on : 20 aout. 2014 13:47:10
 */
@ManagedBean(name ="affectationfilierelmdetabMgrBean")
@RequestScoped
public class AffectFilierelmdetabMgrBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELBRIK Oualid on : 19 aout. 2014 14:24:15
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * [AffectationEtablissementBean.AffectationEtablissementBean()] Constructor
	 * 
	 * @author BELBRIK Oualid on : 18 aout. 2014 13:47:10
	 */
	private List<RefAffectFiliereLmdEtabDto> listRefEtablissementDto;
	@ManagedProperty(value = "#{refAffectFiliereLmdEtabServiceImpl}")
	private RefAffectFiliereLmdEtabService refAffectFiliereLmdEtabServiceImpl;
	@ManagedProperty(value = "#{refEtablissementServiceImpl}")
	private RefEtablissementService refEtablissementServiceImpl;
	private RefAffectFiliereLmdEtabDto refAffectFiliereLmdEtabDto;
	private ResourceBundle bundle;
	private static final Log log = LogFactory.getLog(AffectFilierelmdetabMgrBean.class);
	@ManagedProperty("#{param.id}")
	private String id;
	private Integer idFiliere;
	@ManagedProperty("#{param.searchValue}")
	private String searchValue;
	@ManagedProperty("#{param.editMode}")
	private boolean editMode;
	@ManagedProperty(value = "#{FiliereAffectationCrudBean}")
	private FiliereAffectationCrudBean filiereAffectationCrudBean;
	@ManagedProperty("#{param.idr}")
	private String idr;
	@ManagedProperty("#{param.entitiname}")
	private String entitiname;


	public AffectFilierelmdetabMgrBean() {
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
			this.idFiliere = _id;
		}
		
		log.info("var request*******************************************editMode: "+b+ " id:"+id);
		refAffectFiliereLmdEtabDto = new RefAffectFiliereLmdEtabDto();
		bundle = context.getApplication().getResourceBundle(context,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
	}
	
	@PostConstruct
	public void init() {
		load();
	  
	}

	/**
	 * [AffectationEtablissementBean.refAffectFiliereLmdEtabServiceImpl] Getter
	 * 
	 * @author BELBRIK Oualid on : 19 aout. 2014 13:50:32
	 * @return the refAffectFiliereLmdEtabServiceImpl
	 */
	public RefAffectFiliereLmdEtabService getRefAffectFiliereLmdEtabServiceImpl() {
		return refAffectFiliereLmdEtabServiceImpl;
	}

	/**
	 * [AffectationEtablissementBean.refAffectFiliereLmdEtabServiceImpl] Setter
	 * 
	 * @author BELBRIK Oualid on : 19 aout. 2014 13:50:32
	 * @param refAffectFiliereLmdEtabServiceImpl
	 *            the refAffectFiliereLmdEtabServiceImpl to set
	 */
	public void setRefAffectFiliereLmdEtabServiceImpl(
			RefAffectFiliereLmdEtabService refAffectFiliereLmdEtabServiceImpl) {
		this.refAffectFiliereLmdEtabServiceImpl = refAffectFiliereLmdEtabServiceImpl;
	}


	/**
	 * [AffectFiliereLmdetabMgrBean.equipementBckBean] Getter 
	 * @author BELBRIK oualid on : 22 mars 2014  16:10:24
	 * @return the equipementBckBean
	 */
	public FiliereAffectationCrudBean getFiliereAffectationCrudBean() {
		return filiereAffectationCrudBean;
	}
	
	/**
	 * [AffectFiliereLmdetabMgrBean.equipementBckBean] Setter 
	 * @author BELBRIK oualid on : 22 mars 2014  16:10:24
	 * @param equipementBckBean the equipementBckBean to set
	 */
	public void setFiliereAffectationCrudBean(FiliereAffectationCrudBean filiereAffectationCrudBean) {
		this.filiereAffectationCrudBean = filiereAffectationCrudBean;
	}
	
	/**
	 * [AffectFiliereLmdetabMgrBean.crud] Method 
	 * @author BELBRIK oualid  on : 22 mars 2014  16:10:27
	 * @param createAllow
	 * @param editAllow
	 * @param deleteAllow
	 */
	public void crud(boolean createAllow, boolean editAllow, boolean deleteAllow) {
		filiereAffectationCrudBean.setEditAllow(editAllow);
		filiereAffectationCrudBean.setCreateAllow(createAllow);
		filiereAffectationCrudBean.setDeleteAllow(deleteAllow);
	}

	
	
	public void load() {
		try {
			List<RefAffectFiliereLmdEtabDto> result = null;
			if(idFiliere != null) {
			 result = refAffectFiliereLmdEtabServiceImpl.findByIdFilierelmd(idFiliere);
			}
			if (result == null || result.isEmpty()) {
				log.info("list of affectation is null");
				setListRefEtablissementDto(null);				

			} else {
				
					listRefEtablissementDto = new ArrayList<RefAffectFiliereLmdEtabDto>();
					
					for (RefAffectFiliereLmdEtabDto currentRefAffectFiliereLmdEtabDto : result) {
						if(currentRefAffectFiliereLmdEtabDto.getIdentifiantEtablissement() != null) {
							listRefEtablissementDto.add(currentRefAffectFiliereLmdEtabDto);
						
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
	 * @author BELBRIK Oualid on : 19 aout. 2014  16:09:29
	 * @return the listRefEtablissementDto
	 */
	public List<RefAffectFiliereLmdEtabDto> getListRefEtablissementDto() {
		return listRefEtablissementDto;
	}

	/**
	 * [AffectationEtablissementBean.listRefEtablissementDto] Setter 
	 * @author BELBRIK Oualid on : 19 aout. 2014  16:09:29
	 * @param listRefEtablissementDto the listRefEtablissementDto to set
	 */
	public void setListRefEtablissementDto(List<RefAffectFiliereLmdEtabDto> listRefEtablissementDto) {
		this.listRefEtablissementDto = listRefEtablissementDto;
	}

	
	/**
	 * [AffectationEtablissementBean.refEtablissementServiceImpl] Getter 
	 * @author BELBRIK Oualid on : 19 aout. 2014  11:18:37
	 * @return the refEtablissementServiceImpl
	 */
	public RefEtablissementService getRefEtablissementServiceImpl() {
		return refEtablissementServiceImpl;
	}

	/**
	 * [AffectationEtablissementBean.refEtablissementServiceImpl] Setter 
	 * @author BELBRIK Oualid on : 19 aout. 2014  11:18:37
	 * @param refEtablissementServiceImpl the refEtablissementServiceImpl to set
	 */
	public void setRefEtablissementServiceImpl(
			RefEtablissementService refEtablissementServiceImpl) {
		this.refEtablissementServiceImpl = refEtablissementServiceImpl;
	}

		
	
	public void addEtablissement() {
		FacesMessage msg = new FacesMessage();
		if ((refAffectFiliereLmdEtabDto != null) && (refAffectFiliereLmdEtabDto.getIdEtablissement()!=null)) {
			try {
				refAffectFiliereLmdEtabDto.setIdFiliereLMD(idFiliere);
				log.info("saving Etablissement id = "+refAffectFiliereLmdEtabDto.getIdEtablissement());
							
			  refAffectFiliereLmdEtabServiceImpl.save(refAffectFiliereLmdEtabDto);
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
	
	public void deleteEtablissement(RefAffectFiliereLmdEtabDto selectedEtablissementAffectationEtablissementDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedEtablissementAffectationEtablissementDto != null) && (selectedEtablissementAffectationEtablissementDto.getIdentifiantEtablissement()!=null)) {
			try {
				log.info("deleting Etablissement id = "+selectedEtablissementAffectationEtablissementDto.getIdentifiantEtablissement());			
			  refAffectFiliereLmdEtabServiceImpl.delete(selectedEtablissementAffectationEtablissementDto);
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
			log.info("Mgr Bean saveAffectFiliereLmdEtab");
			refAffectFiliereLmdEtabDto.setId(Integer.parseInt(idr));
			refAffectFiliereLmdEtabDto.setIdFiliereLMD(idFiliere);
			
				refAffectFiliereLmdEtabServiceImpl.update(refAffectFiliereLmdEtabDto);
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
	 * [AffectationEtablissementBean.refAffectFiliereLmdEtabDto] Getter 
	 * @author BELBRIK Oualid on : 19 aout. 2014  15:51:43
	 * @return the refAffectFiliereLmdEtabDto
	 */
	public RefAffectFiliereLmdEtabDto getRefAffectFiliereLmdEtabDto() {
		return refAffectFiliereLmdEtabDto;
	}

	/**
	 * [AffectationEtablissementBean.refAffectFiliereLmdEtabDto] Setter 
	 * @author BELBRIK Oualid on : 19 aout. 2014  15:51:43
	 * @param refAffectFiliereLmdEtabDto the refAffectFiliereLmdEtabDto to set
	 */
	public void setRefAffectFiliereLmdEtabDto(RefAffectFiliereLmdEtabDto refAffectFiliereLmdEtabDto) {
		this.refAffectFiliereLmdEtabDto = refAffectFiliereLmdEtabDto;
	}

	
      
	/**
	 * onEtablissementCancel, Cancel modify the  signature date and reference
	 * @author BELDI Jamel on : 19 aout. 2014  15:51:43
	 * @param event the RowEditEvent
	 */
    public void onSignatureCancel(RowEditEvent event) {  
      
    }

	/**
	 * [AffectFiliereLmdetabMgrBean.bundle] Getter 
	 * @author BELBRIK Oualid on : 19 aout. 2014  17:03:32
	 * @return the bundle
	 */
	public ResourceBundle getBundle() {
		return bundle;
	}

	/**
	 * [AffectFiliereLmdetabMgrBean.bundle] Setter 
	 * @author BELBRIK Oualid on : 19 aout. 2014  17:03:32
	 * @param bundle the bundle to set
	 */
	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	

	/**
	 * [AffectFiliereLmdetabMgrBean.searchValue] Getter 
	 * @author BELBRIK Oualid on : 19 aout. 2014  17:03:32
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [AffectFiliereLmdetabMgrBean.searchValue] Setter 
	 * @author BELBRIK Oualid on : 19 aout. 2014  17:03:32
	 * @param searchValue the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		if ((searchValue != null) && (searchValue.equals("null"))) {
			this.searchValue = "";
		} else	this.searchValue = searchValue;
	}

	/**
	 * [AffectFiliereLmdetabMgrBean.editMode] Getter 
	 * @author BELBRIK Oualid on : 19 aout. 2014  11:39:03
	 * @return the editMode
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * [AffectFiliereLmdetabMgrBean.editMode] Setter 
	 * @author BELBRIK Oualid on : 19 aout. 2014  17:39:03
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
	 * [AffectFiliereLmdetabMgrBean.id] Getter 
	 * @author BELBRIK oualid on : 15 aout 2014  16:10:45
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * [AffectFiliereLmdetabMgrBean.id] Setter 
	 * @author BELBRIK oualid on : 15 aout 2014  16:10:45
	 * @param id the id to set
	 */
	public void setId(String id) {
		if ((id != null) && (id.equals("null"))) {
			this.id = null;
		} else	this.id = id;
		int _id = RefUtilConstant.strToInt(id);
		if(_id != -1) {
			setIdFiliere(_id);
		}
	}

	/**
	 * [AffectFiliereLmdetabMgrBean.idFiliere] Getter 
	 * @author BELBRIK oualid on : 15 aout 2014  16:15:17
	 * @return the idFiliere
	 */
	public Integer getIdFiliere() {
		return idFiliere;
	}

	/**
	 * [AffectFiliereLmdetabMgrBean.idFiliere] Setter 
	 * @author BELBRIK oualid on : 15 aout 2014  16:15:17
	 * @param idFiliere the idFiliere to set
	 */
	public void setIdFiliere(Integer idFiliere) {
		this.idFiliere = idFiliere;
	}
	
	
	

}
