/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.habilitation.domaine.ModuleSearchBckBean.java] 
 * @author MAKERRI Sofiane on : 23 f�vr. 2014  15:08:17
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
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefModuleDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefModuleService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane on : 23 f�vr. 2014 15:08:17
 */
@ManagedBean(name = "moduleSearchBckBean")
@ViewScoped
public class ModuleSearchBckBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 15:08:32
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(ModuleSearchBckBean.class);
	@ManagedProperty(value = "#{refModuleServiceImpl}")
	private RefModuleService refModuleServiceImpl;
	private List<RefModuleDto> listRefModuleDto;
	private List<RefDomaineDto> listDomaineDto;
	private String searchValue;
	private ResourceBundle bundle;
	private TreeNode root;

	/**
	 * [ModuleSearchBckBean.ModuleSearchBckBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 15:08:17
	 */
	public ModuleSearchBckBean() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
	}

	@PostConstruct
	public void init() {
		if (refModuleServiceImpl != null) {
			listRefModuleDto = refModuleServiceImpl
					.findGeneric(this.searchValue);
			setTreeTable();

		}
	}

	/**
	 * [ModuleSearchBckBean.setTreeTable] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 15:23:28
	 */
	public void setTreeTable() {
		if (refModuleServiceImpl != null) {
			root = new DefaultTreeNode("root", null);
			root.setExpanded(true);

			try {
				for (RefModuleDto currentRefModuleDto : listRefModuleDto) {
					TreeNode domaine = null;
					TreeNode domaineParent = null;
					boolean nodeParentExist = false;
					boolean nodeDomaineExist = false;
					if (currentRefModuleDto.getIdDomaineParent() != null) {
						TreeModele lastDomaineParentDto = new TreeModele(
								currentRefModuleDto.getIdDomaineParent(),
								currentRefModuleDto
										.getIdentifiantDomaineParent(),
								currentRefModuleDto.getNomDomaineParent(),
								currentRefModuleDto.getRangDomaineParent(), currentRefModuleDto.getIdentifiantDomaine(),
								true);
						for (TreeNode node1 : root.getChildren()) {
							TreeModele nodeDomaine = (TreeModele) node1
									.getData();
							if (nodeDomaine.getId().equals(
									lastDomaineParentDto.getId())) {
								nodeParentExist = true;
								domaineParent = node1;
								break;
							}
						}
						if (!nodeParentExist) {
							domaineParent = new DefaultTreeNode(
									lastDomaineParentDto, root);
							domaineParent.setExpanded(true);
						}
					}

					if (currentRefModuleDto.getIdDomaine() != null) {
						TreeModele lastDomaineDto = new TreeModele(
								currentRefModuleDto.getIdDomaine(),
								currentRefModuleDto.getIdentifiantDomaine(),
								currentRefModuleDto.getNomDomaine(), 
								currentRefModuleDto.getRangDomaine(), currentRefModuleDto.getIdentifiantDomaine(), true);
						/* if(!nodeParentExist) */{
							for (TreeNode node1 : root.getChildren()) {
								TreeModele nodeDomaineParent = (TreeModele) node1
										.getData();
								if (nodeDomaineParent != null
										&& nodeDomaineParent.getId().equals(
												lastDomaineDto.getId())) {
									nodeDomaineExist = true;
									domaine = node1;
									break;
								}
								for (TreeNode node2 : node1.getChildren()) {
									TreeModele nodeDomaine = (TreeModele) node2
											.getData();
									if (nodeDomaine != null
											&& nodeDomaine.getId().equals(
													lastDomaineDto.getId())) {
										nodeDomaineExist = true;
										domaine = node2;
										break;
									}
								}
							}
							if (!nodeDomaineExist) {
								if (nodeParentExist) {
									domaine = new DefaultTreeNode(
											lastDomaineDto, domaineParent);
								} else {
									domaine = new DefaultTreeNode(
											lastDomaineDto, root);
								}
								domaine.setExpanded(true);
							}

						} /*
						 * else { domaine = new DefaultTreeNode(lastDomaineDto,
						 * domaineParent); domaine.setExpanded(true); }
						 */

					}
					TreeModele lastModuleDto = new TreeModele(
							currentRefModuleDto.getId(),
							currentRefModuleDto.getIdentifiant(),
							currentRefModuleDto.getNom(),
							currentRefModuleDto.getRang(), currentRefModuleDto.getIdentifiantDomaine(), false);

					TreeNode module = new DefaultTreeNode(lastModuleDto,
							domaine);
					module.setExpanded(true);

				}
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());

			}
		}

	}

	/**
	 * [ModuleSearchBckBean.refModuleServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 15:22:44
	 * @return the refModuleServiceImpl
	 */
	public RefModuleService getRefModuleServiceImpl() {
		return refModuleServiceImpl;
	}

	/**
	 * [ModuleSearchBckBean.refModuleServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 15:22:44
	 * @param refModuleServiceImpl
	 *            the refModuleServiceImpl to set
	 */
	public void setRefModuleServiceImpl(RefModuleService refModuleServiceImpl) {
		this.refModuleServiceImpl = refModuleServiceImpl;
	}

	/**
	 * [ModuleSearchBckBean.listRefModuleDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 15:22:44
	 * @return the listRefModuleDto
	 */
	public List<RefModuleDto> getListRefModuleDto() {
		return listRefModuleDto;
	}

	/**
	 * [ModuleSearchBckBean.listRefModuleDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 15:22:44
	 * @param listRefModuleDto
	 *            the listRefModuleDto to set
	 */
	public void setListRefModuleDto(List<RefModuleDto> listRefModuleDto) {
		this.listRefModuleDto = listRefModuleDto;
	}

	/**
	 * [ModuleSearchBckBean.listDomaineDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 15:22:44
	 * @return the listDomaineDto
	 */
	public List<RefDomaineDto> getListDomaineDto() {
		return listDomaineDto;
	}

	/**
	 * [ModuleSearchBckBean.listDomaineDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 15:22:44
	 * @param listDomaineDto
	 *            the listDomaineDto to set
	 */
	public void setListDomaineDto(List<RefDomaineDto> listDomaineDto) {
		this.listDomaineDto = listDomaineDto;
	}

	/**
	 * [ModuleSearchBckBean.searchValue] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 15:22:44
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [ModuleSearchBckBean.searchValue] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 15:22:44
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
	 * [ModuleSearchBckBean.bundle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 15:22:44
	 * @return the bundle
	 */
	public ResourceBundle getBundle() {
		return bundle;
	}

	/**
	 * [ModuleSearchBckBean.bundle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 15:22:44
	 * @param bundle
	 *            the bundle to set
	 */
	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * [ModuleSearchBckBean.root] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 15:22:44
	 * @return the root
	 */
	public TreeNode getRoot() {
		return root;
	}

	/**
	 * [ModuleSearchBckBean.root] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 15:22:44
	 * @param root
	 *            the root to set
	 */
	public void setRoot(TreeNode root) {
		this.root = root;
	}

	/**
	 * [ModuleSearchBckBean.findGeneric] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 16:15:06
	 * @param value
	 */
	public void findGeneric(String value) {
		FacesMessage msg = new FacesMessage();
		setSearchValue(value);
		try {
			List<RefModuleDto> result = refModuleServiceImpl.findGeneric(value);
			if (result == null || result.isEmpty()) {
				setListRefModuleDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				setListRefModuleDto(result);
				setTreeTable();

			}
		} catch (Exception e) {
			setListRefModuleDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	/**
	 * [ModuleSearchBckBean.toEdit] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 16:38:57
	 * @return
	 */
	public String toEdit() {
		return "toEdit";
	}

	/**
	 * [ModuleSearchBckBean.toNew] Method 
	 * @author MAKERRI Sofiane  on : 10 mars 2014  16:20:07
	 * @return
	 */
	public String toNew() {
		return "toNew";
	}
	
	
	/**
	 * [ModuleSearchBckBean.toNewFonction] Method 
	 * @author MAKERRI Sofiane  on : 10 mars 2014  16:20:05
	 * @return
	 */
	public String toNewFonction() {
		return "toNewFonction";
	}

	/**
	 * [ModuleSearchBckBean.toDetails] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 f�vr. 2014 16:39:00
	 * @return
	 */
	public String toDetails() {
		return "toDetails";
	}

	

}
