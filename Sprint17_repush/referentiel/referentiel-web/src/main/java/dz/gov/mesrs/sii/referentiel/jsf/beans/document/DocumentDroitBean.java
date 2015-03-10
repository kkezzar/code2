/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.contrat.DocumentDroitBean.java] 
 * @author MAKERRI Sofiane on : 4 mars 2014  17:40:41
 */
package dz.gov.mesrs.sii.referentiel.jsf.beans.document;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.CrudBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPermissionDto;

/**
 * @author MAKERRI Sofiane  on : 4 mars 2014  17:40:41
 */
@ManagedBean(name = "documentDroitBean")
@SessionScoped
public class DocumentDroitBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean createAllow;
	private boolean editAllow;
	private boolean deleteAllow;
	private boolean readAllow;
	@ManagedProperty(value = "#{crudBean}")
	private CrudBean crudBean;
	private List<Boolean> listDroit;
	private List<RefPermissionDto> listActions;
	
	/**
	 * [DocumentDroitBean.listActions] Getter 
	 * @author MAKERRI Sofiane on : 23 mars 2014  12:14:19
	 * @return the listActions
	 */
	public List<RefPermissionDto> getListActions() {
		return listActions;
	}

	/**
	 * [DocumentDroitBean.listActions] Setter 
	 * @author MAKERRI Sofiane on : 23 mars 2014  12:14:19
	 * @param listActions the listActions to set
	 */
	public void setListActions(List<RefPermissionDto> listActions) {
		this.listActions = listActions;
	}

	/**
	 * [DocumentDroitBean.DocumentDroitBean()] Constructor
	 * @author MAKERRI Sofiane  on : 4 mars 2014  17:40:41	
	 */
	public DocumentDroitBean() {
		super();
	}
	
	@PostConstruct
	public void init() {
		if (listDroit == null) {
			listDroit = crudBean.loadCrudDroit();
			listActions = crudBean.getListActions();
			if (listDroit == null) {
				setCreateAllow(false);
				setEditAllow(false);
				setDeleteAllow(false);
				setReadAllow(false);
			} else {
				setCreateAllow(listDroit.get(0));
				setEditAllow(listDroit.get(1));
				setDeleteAllow(listDroit.get(2));
				setReadAllow(listDroit.get(3));
			}
		}
	}

	/**
	 * [DocumentDroitBean.createAllow] Getter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:41:33
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}

	/**
	 * [DocumentDroitBean.createAllow] Setter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:41:33
	 * @param createAllow the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}

	/**
	 * [DocumentDroitBean.editAllow] Getter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:41:33
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}

	/**
	 * [DocumentDroitBean.editAllow] Setter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:41:33
	 * @param editAllow the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}

	/**
	 * [DocumentDroitBean.deleteAllow] Getter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:41:33
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}

	/**
	 * [DocumentDroitBean.deleteAllow] Setter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:41:33
	 * @param deleteAllow the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}

	/**
	 * [DocumentDroitBean.readAllow] Getter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:41:33
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}

	/**
	 * [DocumentDroitBean.readAllow] Setter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:41:33
	 * @param readAllow the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}

	/**
	 * [DocumentDroitBean.crudBean] Getter 
	 * @author MAKERRI Sofiane on : 5 mars 2014  11:45:25
	 * @return the crudBean
	 */
	public CrudBean getCrudBean() {
		return crudBean;
	}

	/**
	 * [DocumentDroitBean.crudBean] Setter 
	 * @author MAKERRI Sofiane on : 5 mars 2014  11:45:25
	 * @param crudBean the crudBean to set
	 */
	public void setCrudBean(CrudBean crudBean) {
		this.crudBean = crudBean;
	}

	/**
	 * [DocumentDroitBean.listDroit] Getter 
	 * @author MAKERRI Sofiane on : 5 mars 2014  11:45:25
	 * @return the listDroit
	 */
	public List<Boolean> getListDroit() {
		return listDroit;
	}

	/**
	 * [DocumentDroitBean.listDroit] Setter 
	 * @author MAKERRI Sofiane on : 5 mars 2014  11:45:25
	 * @param listDroit the listDroit to set
	 */
	public void setListDroit(List<Boolean> listDroit) {
		this.listDroit = listDroit;
	}
	
	
	

}
