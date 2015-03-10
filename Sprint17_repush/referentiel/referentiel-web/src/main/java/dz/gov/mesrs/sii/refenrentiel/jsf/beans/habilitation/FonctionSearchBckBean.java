/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.habilitation.domaine.FonctionSearchBckBean.java] 
 * @author MAKERRI Sofiane on : 27 févr. 2014  14:20:52
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

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFonctionDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefFonctionService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane on : 27 févr. 2014 14:20:52
 */
@ManagedBean(name = "fonctionSearchBckBean")
@ViewScoped
public class FonctionSearchBckBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 27 févr. 2014 14:21:20
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory
			.getLog(FonctionSearchBckBean.class);
	@ManagedProperty(value = "#{refFonctionServiceImpl}")
	private RefFonctionService refFonctionServiceImpl;
	private List<RefFonctionDto> listRefFonctionDto;
	private String searchValue;
	private ResourceBundle bundle;
	private TreeNode root;

	/**
	 * [FonctionSearchBckBean.FonctionSearchBckBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 27 févr. 2014 14:20:52
	 */
	public FonctionSearchBckBean() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
	}

	@PostConstruct
	public void init() {
		if (refFonctionServiceImpl != null) {

			listRefFonctionDto = refFonctionServiceImpl
					.findGeneric(this.searchValue);
			setTreeTable();

		}
	}

	/**
	 * [DomaineSearchBckBean.setTreeTable] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 févr. 2014 15:56:28
	 */
	public void setTreeTable() {
		if (refFonctionServiceImpl != null) {
			root = new DefaultTreeNode("root", null);
			root.setExpanded(true);

			try {
				for (RefFonctionDto currentRefFonctionDto : listRefFonctionDto) {
					TreeNode module = null;
					TreeNode fonction = null;
					boolean nodeModuleExist = false;

					if (currentRefFonctionDto.getIdModule() != null) {
						TreeModele lastModuleDto = new TreeModele(
								currentRefFonctionDto.getIdModule(),
								currentRefFonctionDto.getIdfModule(),
								currentRefFonctionDto.getNomModule(),
								currentRefFonctionDto.getUrl(),
								currentRefFonctionDto.getRangModule(),
								null,
								true);

						for (TreeNode node1 : root.getChildren()) {
							TreeModele nodeModuleParent = (TreeModele) node1
									.getData();
							if (nodeModuleParent != null
									&& nodeModuleParent.getId().equals(
											lastModuleDto.getId())) {
								nodeModuleExist = true;
								module = node1;
								break;
							}
							for (TreeNode node2 : node1.getChildren()) {
								TreeModele nodeDomaine = (TreeModele) node2
										.getData();
								if (nodeDomaine != null
										&& nodeDomaine.getId().equals(
												lastModuleDto.getId())) {
									nodeModuleExist = true;
									module = node2;
									break;
								}
							}
						}
						if(!nodeModuleExist) {
							module = new DefaultTreeNode(lastModuleDto, root);
							module.setExpanded(true);
						} 
						TreeModele lastFonction = new TreeModele(
								currentRefFonctionDto.getId(),
								currentRefFonctionDto.getIdentifiant(),
								currentRefFonctionDto.getNom(),
								currentRefFonctionDto.getUrl(),
								currentRefFonctionDto.getRang(), 
								currentRefFonctionDto.getIdfModule(), false);
						fonction = new DefaultTreeNode(lastFonction, module);
						fonction.setExpanded(true);
						
					}

				}
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());

			}

		}

	}

	/**
	 * [FonctionSearchBckBean.findGeneric] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 févr. 2014 14:28:49
	 * @param value
	 */
	public void findGeneric(String value) {
		FacesMessage msg = new FacesMessage();
		setSearchValue(value);
		try {
			List<RefFonctionDto> result = refFonctionServiceImpl
					.findGeneric(value);
			if (result == null || result.isEmpty()) {
				setListRefFonctionDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				setListRefFonctionDto(result);
				setTreeTable();

			}
		} catch (Exception e) {
			setListRefFonctionDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	/**
	 * [FonctionSearchBckBean.refFonctionServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 févr. 2014 14:23:48
	 * @return the refFonctionServiceImpl
	 */
	public RefFonctionService getRefFonctionServiceImpl() {
		return refFonctionServiceImpl;
	}

	/**
	 * [FonctionSearchBckBean.refFonctionServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 févr. 2014 14:23:48
	 * @param refFonctionServiceImpl
	 *            the refFonctionServiceImpl to set
	 */
	public void setRefFonctionServiceImpl(
			RefFonctionService refFonctionServiceImpl) {
		this.refFonctionServiceImpl = refFonctionServiceImpl;
	}

	/**
	 * [FonctionSearchBckBean.listRefFonctionDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 févr. 2014 14:23:48
	 * @return the listRefFonctionDto
	 */
	public List<RefFonctionDto> getListRefFonctionDto() {
		return listRefFonctionDto;
	}

	/**
	 * [FonctionSearchBckBean.listRefFonctionDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 févr. 2014 14:23:48
	 * @param listRefFonctionDto
	 *            the listRefFonctionDto to set
	 */
	public void setListRefFonctionDto(List<RefFonctionDto> listRefFonctionDto) {
		this.listRefFonctionDto = listRefFonctionDto;
	}

	/**
	 * [FonctionSearchBckBean.searchValue] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 févr. 2014 14:23:48
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [FonctionSearchBckBean.searchValue] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 févr. 2014 14:23:48
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
	 * [FonctionSearchBckBean.bundle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 févr. 2014 14:23:48
	 * @return the bundle
	 */
	public ResourceBundle getBundle() {
		return bundle;
	}

	/**
	 * [FonctionSearchBckBean.bundle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 févr. 2014 14:23:48
	 * @param bundle
	 *            the bundle to set
	 */
	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * [FonctionSearchBckBean.root] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 févr. 2014 14:23:48
	 * @return the root
	 */
	public TreeNode getRoot() {
		return root;
	}

	/**
	 * [FonctionSearchBckBean.root] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 févr. 2014 14:23:48
	 * @param root
	 *            the root to set
	 */
	public void setRoot(TreeNode root) {
		this.root = root;
	}

	/**
	 * [FonctionSearchBckBean.toNew] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 févr. 2014 14:56:31
	 * @return
	 */
	public String toNew() {
		return "toEdit";
	}

	/**
	 * [FonctionSearchBckBean.toDetails] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 févr. 2014 14:56:43
	 * @return
	 */
	public String toDetails() {
		return "toDetails";
	}

	/**
	 * [FonctionSearchBckBean.toEdit] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 févr. 2014 14:57:07
	 * @return
	 */
	public String toEdit() {
		return "toEdit";
	}

}
