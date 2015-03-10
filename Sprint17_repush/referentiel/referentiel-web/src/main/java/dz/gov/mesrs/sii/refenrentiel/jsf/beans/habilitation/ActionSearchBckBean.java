/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.habilitation.ActionSearchBckBean.java] 
 * @author MAKERRI Sofiane on : 18 mars 2014  18:35:24
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.habilitation;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
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
 * @author MAKERRI Sofiane on : 18 mars 2014 18:35:24
 */
@ManagedBean(name = "actionSearchBckBean")
@ViewScoped
public class ActionSearchBckBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 18 mars 2014 18:36:02
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * [ActionSearchBckBean.ActionSearchBckBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 18 mars 2014 18:35:24
	 */
	private static final Log log = LogFactory.getLog(ActionSearchBckBean.class);
	@ManagedProperty(value = "#{refFonctionServiceImpl}")
	private RefFonctionService refFonctionServiceImpl;
	private List<RefFonctionDto> listRefFonctionDto;
	private String searchValue;
	private ResourceBundle bundle;
	private TreeNode root;

	public ActionSearchBckBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
	}

	@PostConstruct
	public void init() {
		if (refFonctionServiceImpl != null) {

			listRefFonctionDto = refFonctionServiceImpl
					.findActionGeneric(this.searchValue);
			setTreeTable();

		}
	}

	/**
	 * [ActionSearchBckBean.setTreeTable] Method 
	 * @author MAKERRI Sofiane  on : 18 mars 2014  18:38:23
	 */
	public void setTreeTable() {
		if (refFonctionServiceImpl != null) {
			root = new DefaultTreeNode("root", null);
			root.setExpanded(true);

			try {
				for (RefFonctionDto currentAction : listRefFonctionDto) {
					TreeNode fonction = null;
					TreeNode action = null;
					boolean nodeFonctionExist = false;

					if (currentAction.getIdFonctionMere() != null) {
						TreeModele lastFonctionDto = new TreeModele(
								currentAction.getIdfFonctionMere(),
								currentAction.getNomFonctionMere(),
								currentAction.getUrlFonctionMere(),
								currentAction.getRangFonctionMere(),
								currentAction.getIdfModule(),
								currentAction.getIdFonctionMere(),
								true);

						for (TreeNode node1 : root.getChildren()) {
							TreeModele nodeFonctionMere = (TreeModele) node1
									.getData();
							if (nodeFonctionMere != null
									&& nodeFonctionMere.getId().equals(
											lastFonctionDto.getId())) {
								nodeFonctionExist = true;
								fonction = node1;
								break;
							}
							for (TreeNode node2 : node1.getChildren()) {
								TreeModele nodeModule = (TreeModele) node2
										.getData();
								if (nodeModule != null
										&& nodeModule.getId().equals(
												lastFonctionDto.getId())) {
									nodeFonctionExist = true;
									fonction = node2;
									break;
								}
							}
						}
						if (!nodeFonctionExist) {
							fonction = new DefaultTreeNode(lastFonctionDto, root);
							fonction.setExpanded(true);
						}
						TreeModele lastAction = new TreeModele(
								currentAction.getIdentifiant(),
								currentAction.getNom(),
								currentAction.getUrl(),
								currentAction.getRang(),
								currentAction.getIdfFonctionMere(),
								currentAction.getId(), false);
						action = new DefaultTreeNode(lastAction, fonction);
						action.setExpanded(true);

					}

				}
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());

			}

		}

	}

	/**
	 * [ActionSearchBckBean.findGeneric] Method 
	 * @author MAKERRI Sofiane  on : 18 mars 2014  18:38:16
	 * @param value
	 */
	public void findGeneric(String value) {
		FacesMessage msg = new FacesMessage();
		setSearchValue(value);
		try {
			List<RefFonctionDto> result = refFonctionServiceImpl
					.findActionGeneric(value);
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
	 * [ActionSearchBckBean.refFonctionServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 mars 2014 18:37:07
	 * @return the refFonctionServiceImpl
	 */
	public RefFonctionService getRefFonctionServiceImpl() {
		return refFonctionServiceImpl;
	}

	/**
	 * [ActionSearchBckBean.refFonctionServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 mars 2014 18:37:07
	 * @param refFonctionServiceImpl
	 *            the refFonctionServiceImpl to set
	 */
	public void setRefFonctionServiceImpl(
			RefFonctionService refFonctionServiceImpl) {
		this.refFonctionServiceImpl = refFonctionServiceImpl;
	}

	/**
	 * [ActionSearchBckBean.listRefFonctionDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 mars 2014 18:37:07
	 * @return the listRefFonctionDto
	 */
	public List<RefFonctionDto> getListRefFonctionDto() {
		return listRefFonctionDto;
	}

	/**
	 * [ActionSearchBckBean.listRefFonctionDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 mars 2014 18:37:07
	 * @param listRefFonctionDto
	 *            the listRefFonctionDto to set
	 */
	public void setListRefFonctionDto(List<RefFonctionDto> listRefFonctionDto) {
		this.listRefFonctionDto = listRefFonctionDto;
	}

	/**
	 * [ActionSearchBckBean.searchValue] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 mars 2014 18:37:07
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [ActionSearchBckBean.searchValue] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 mars 2014 18:37:07
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
	 * [ActionSearchBckBean.root] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 mars 2014 18:37:07
	 * @return the root
	 */
	public TreeNode getRoot() {
		return root;
	}

	/**
	 * [ActionSearchBckBean.root] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 mars 2014 18:37:07
	 * @param root
	 *            the root to set
	 */
	public void setRoot(TreeNode root) {
		this.root = root;
	}

	/**
	 * [ActionSearchBckBean.toNew] Method 
	 * @author MAKERRI Sofiane  on : 18 mars 2014  18:37:56
	 * @return
	 */
	public String toNew() {
		return "toNew";
	}

	/**
	 * [ActionSearchBckBean.toDetails] Method 
	 * @author MAKERRI Sofiane  on : 18 mars 2014  18:37:53
	 * @return
	 */
	public String toDetails() {
		return "toDetails";
	}

	/**
	 * [ActionSearchBckBean.toEdit] Method 
	 * @author MAKERRI Sofiane  on : 18 mars 2014  18:37:51
	 * @return
	 */
	public String toEdit() {
		return "toEdit";
	}

}
