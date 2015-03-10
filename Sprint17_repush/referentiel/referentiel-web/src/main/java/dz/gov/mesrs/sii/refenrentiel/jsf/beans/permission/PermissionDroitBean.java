/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.permission.PermissionDroitBean.java] 
 * @author MAKERRI Sofiane on : 11 mars 2014  14:17:16
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.permission;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.CrudBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPermissionDto;

/**
 * @author MAKERRI Sofiane  on : 11 mars 2014  14:17:16
 */
@ManagedBean(name = "permissionDroitBean")
@SessionScoped
public class PermissionDroitBean implements Serializable {

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
	 * [PermissionDroitBean.PermissionDroitBean()] Constructor
	 * @author MAKERRI Sofiane  on : 11 mars 2014  14:17:16	
	 */
	public PermissionDroitBean() {
		// TODO Auto-generated constructor stub
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
	 * [PermissionDroitBean.createAllow] Getter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:40:08
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}
	/**
	 * [PermissionDroitBean.createAllow] Setter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:40:08
	 * @param createAllow the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}
	/**
	 * [PermissionDroitBean.editAllow] Getter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:40:08
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}
	/**
	 * [PermissionDroitBean.editAllow] Setter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:40:08
	 * @param editAllow the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}
	/**
	 * [PermissionDroitBean.deleteAllow] Getter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:40:08
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}
	/**
	 * [PermissionDroitBean.deleteAllow] Setter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:40:08
	 * @param deleteAllow the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}
	/**
	 * [PermissionDroitBean.readAllow] Getter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:40:08
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}
	/**
	 * [PermissionDroitBean.readAllow] Setter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:40:08
	 * @param readAllow the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}

	/**
	 * [PermissionDroitBean.crudBean] Getter 
	 * @author MAKERRI Sofiane on : 5 mars 2014  11:44:42
	 * @return the crudBean
	 */
	public CrudBean getCrudBean() {
		return crudBean;
	}

	/**
	 * [PermissionDroitBean.crudBean] Setter 
	 * @author MAKERRI Sofiane on : 5 mars 2014  11:44:42
	 * @param crudBean the crudBean to set
	 */
	public void setCrudBean(CrudBean crudBean) {
		this.crudBean = crudBean;
	}

	/**
	 * [PermissionDroitBean.listDroit] Getter 
	 * @author MAKERRI Sofiane on : 5 mars 2014  11:44:42
	 * @return the listDroit
	 */
	public List<Boolean> getListDroit() {
		return listDroit;
	}

	/**
	 * [PermissionDroitBean.listDroit] Setter 
	 * @author MAKERRI Sofiane on : 5 mars 2014  11:44:42
	 * @param listDroit the listDroit to set
	 */
	public void setListDroit(List<Boolean> listDroit) {
		this.listDroit = listDroit;
	}

	/**
	 * [PermissionDroitBean.listActions] Getter 
	 * @author MAKERRI Sofiane on : 23 mars 2014  12:09:45
	 * @return the listActions
	 */
	public List<RefPermissionDto> getListActions() {
		return listActions;
	}

	/**
	 * [PermissionDroitBean.listActions] Setter 
	 * @author MAKERRI Sofiane on : 23 mars 2014  12:09:45
	 * @param listActions the listActions to set
	 */
	public void setListActions(List<RefPermissionDto> listActions) {
		this.listActions = listActions;
	}
	
	

}
