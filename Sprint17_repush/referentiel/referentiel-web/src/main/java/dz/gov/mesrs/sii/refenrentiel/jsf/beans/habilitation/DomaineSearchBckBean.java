/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.habilitation.domaine.DomaineSearchBckBean.java] 
 * @author MAKERRI Sofiane on : 18 févr. 2014  09:56:10
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.habilitation;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefDomaineService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane on : 18 févr. 2014 09:56:10
 */
@ManagedBean(name = "domaineSearchBckBean")
@ViewScoped
public class DomaineSearchBckBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 18 févr. 2014 09:56:25
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory
			.getLog(DomaineSearchBckBean.class);
	@ManagedProperty(value = "#{refDomaineServiceImpl}")
	private RefDomaineService refDomaineServiceImpl;
	private List<RefDomaineDto> listRefDomaineDto;
	private List<RefDomaineDto> listSubDomaineDto;
	private String searchValue;
	private ResourceBundle bundle;
	private TreeNode root;

	/**
	 * [DomaineSearchBckBean.DomaineSearchBckBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 18 févr. 2014 09:56:10
	 */
	public DomaineSearchBckBean() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
	}

	@PostConstruct
	public void init() {
		if (refDomaineServiceImpl != null) {
			
			listRefDomaineDto = refDomaineServiceImpl
					.findGeneric(this.searchValue);
			setTreeTable();

		}
	}

	/**
	 * [DomaineSearchBckBean.setTreeTable] Method 
	 * @author MAKERRI Sofiane  on : 25 févr. 2014  15:56:28
	 */
	public void setTreeTable() {
		if (listRefDomaineDto != null) {
			root = new DefaultTreeNode("root", null);
			for (RefDomaineDto currentRefDomaineDto : listRefDomaineDto) {
				TreeNode domaine = new DefaultTreeNode(currentRefDomaineDto,
						root);
				List<RefDomaineDto> ListSubDomaineDto = currentRefDomaineDto
						.getListSubDomaine();
				if (ListSubDomaineDto != null) {
					for (RefDomaineDto currentRefSubDomaineDto : ListSubDomaineDto) {
						TreeNode subDomaine = new DefaultTreeNode(
								currentRefSubDomaineDto, domaine);
					}
				}

			}
		}
	}

	/**
	 * [DomaineSearchBckBean.refDomaineServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 févr. 2014 09:59:50
	 * @return the refDomaineServiceImpl
	 */
	public RefDomaineService getRefDomaineServiceImpl() {
		return refDomaineServiceImpl;
	}

	/**
	 * [DomaineSearchBckBean.refDomaineServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 févr. 2014 09:59:50
	 * @param refDomaineServiceImpl
	 *            the refDomaineServiceImpl to set
	 */
	public void setRefDomaineServiceImpl(RefDomaineService refDomaineServiceImpl) {
		this.refDomaineServiceImpl = refDomaineServiceImpl;
	}

	/**
	 * [DomaineSearchBckBean.searchValue] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 févr. 2014 10:19:31
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [DomaineSearchBckBean.searchValue] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 févr. 2014 10:19:31
	 * @param searchValue
	 *            the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		if ((searchValue != null) && (searchValue.equals("null"))) {
			this.searchValue = "";
		} else {
			this.searchValue = searchValue;
		}
	}

	/**
	 * [DomaineSearchBckBean.findGeneric] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 févr. 2014 10:20:05
	 * @param value
	 */
	public void findGeneric(String value) {
		FacesMessage msg = new FacesMessage();
		setSearchValue(value);
		try {
			List<RefDomaineDto> result = refDomaineServiceImpl
					.findGeneric(value);
			if (result == null || result.isEmpty()) {
				setListRefDomaineDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				setListRefDomaineDto(result);
				setTreeTable();

			}
		} catch (Exception e) {
			setListRefDomaineDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	/**
	 * [DomaineSearchBckBean.toNewDomaine] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 févr. 2014 10:20:41
	 * @return
	 */
	public String toNew() {
		return "toNew";
	}

	/**
	 * [DomaineSearchBckBean.toEdit] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 févr. 2014 10:27:15
	 * @return
	 */
	public String toEdit() {
		return "toEdit";
	}

	/**
	 * [DomaineSearchBckBean.toDetails] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 févr. 2014 10:27:17
	 * @return
	 */
	public String toDetails() {
		return "toDetails";
	}

	/**
	 * [DomaineSearchBckBean.listRefDomaineDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 févr. 2014 10:21:16
	 * @return the listRefDomaineDto
	 */
	public List<RefDomaineDto> getListRefDomaineDto() {
		return listRefDomaineDto;
	}

	/**
	 * [DomaineSearchBckBean.listRefDomaineDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 févr. 2014 10:21:16
	 * @param listRefDomaineDto
	 *            the listRefDomaineDto to set
	 */
	public void setListRefDomaineDto(List<RefDomaineDto> listRefDomaineDto) {
		this.listRefDomaineDto = listRefDomaineDto;
	}

	/**
	 * [DomaineSearchBckBean.loadSubDomaines] Method
	 * 
	 * @author MAKERRI Sofiane on : 19 févr. 2014 10:44:29
	 */
	public List<RefDomaineDto> loadSubDomaines(int id) {
		listSubDomaineDto = refDomaineServiceImpl.findSubDomaine(id);
		return listSubDomaineDto;
	}

	/**
	 * [DomaineSearchBckBean.listSubDomaineDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 févr. 2014 10:45:10
	 * @return the listSubDomaineDto
	 */
	public List<RefDomaineDto> getListSubDomaineDto() {
		return listSubDomaineDto;
	}

	/**
	 * [DomaineSearchBckBean.listSubDomaineDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 févr. 2014 10:45:10
	 * @param listSubDomaineDto
	 *            the listSubDomaineDto to set
	 */
	public void setListSubDomaineDto(List<RefDomaineDto> listSubDomaineDto) {
		this.listSubDomaineDto = listSubDomaineDto;
	}

	/**
	 * [DomaineSearchBckBean.root] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 févr. 2014 11:51:28
	 * @return the root
	 */
	public TreeNode getRoot() {
		return root;
	}

	/**
	 * [DomaineSearchBckBean.root] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 févr. 2014 11:51:28
	 * @param root
	 *            the root to set
	 */
	public void setRoot(TreeNode root) {
		this.root = root;
	}
	
}
