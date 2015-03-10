/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.habilitation.ActionDroitBean.java] 
 * @author MAKERRI Sofiane on : 18 mars 2014  18:33:34
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.habilitation;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.CrudBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPermissionDto;

/**
 * @author MAKERRI Sofiane  on : 18 mars 2014  18:33:34
 */
@ManagedBean(name = "actionDroitBean")
@SessionScoped
public class ActionDroitBean implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 18 mars 2014  18:33:51
	 */
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
	 * [ActionDroitBean.listActions] Getter 
	 * @author MAKERRI Sofiane on : 23 mars 2014  12:05:06
	 * @return the listActions
	 */
	public List<RefPermissionDto> getListActions() {
		return listActions;
	}

	/**
	 * [ActionDroitBean.listActions] Setter 
	 * @author MAKERRI Sofiane on : 23 mars 2014  12:05:06
	 * @param listActions the listActions to set
	 */
	public void setListActions(List<RefPermissionDto> listActions) {
		this.listActions = listActions;
	}

	/**
	 * [ActionDroitBean.ActionDroitBean()] Constructor
	 * @author MAKERRI Sofiane  on : 18 mars 2014  18:33:34	
	 */
	public ActionDroitBean() {
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
	 * [ActionDroitBean.createAllow] Getter 
	 * @author MAKERRI Sofiane on : 18 mars 2014  18:34:50
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}

	/**
	 * [ActionDroitBean.createAllow] Setter 
	 * @author MAKERRI Sofiane on : 18 mars 2014  18:34:50
	 * @param createAllow the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}

	/**
	 * [ActionDroitBean.editAllow] Getter 
	 * @author MAKERRI Sofiane on : 18 mars 2014  18:34:50
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}

	/**
	 * [ActionDroitBean.editAllow] Setter 
	 * @author MAKERRI Sofiane on : 18 mars 2014  18:34:50
	 * @param editAllow the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}

	/**
	 * [ActionDroitBean.deleteAllow] Getter 
	 * @author MAKERRI Sofiane on : 18 mars 2014  18:34:50
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}

	/**
	 * [ActionDroitBean.deleteAllow] Setter 
	 * @author MAKERRI Sofiane on : 18 mars 2014  18:34:50
	 * @param deleteAllow the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}

	/**
	 * [ActionDroitBean.readAllow] Getter 
	 * @author MAKERRI Sofiane on : 18 mars 2014  18:34:50
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}

	/**
	 * [ActionDroitBean.readAllow] Setter 
	 * @author MAKERRI Sofiane on : 18 mars 2014  18:34:50
	 * @param readAllow the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}

	/**
	 * [ActionDroitBean.crudBean] Getter 
	 * @author MAKERRI Sofiane on : 18 mars 2014  18:34:50
	 * @return the crudBean
	 */
	public CrudBean getCrudBean() {
		return crudBean;
	}

	/**
	 * [ActionDroitBean.crudBean] Setter 
	 * @author MAKERRI Sofiane on : 18 mars 2014  18:34:50
	 * @param crudBean the crudBean to set
	 */
	public void setCrudBean(CrudBean crudBean) {
		this.crudBean = crudBean;
	}

	/**
	 * [ActionDroitBean.listDroit] Getter 
	 * @author MAKERRI Sofiane on : 18 mars 2014  18:34:50
	 * @return the listDroit
	 */
	public List<Boolean> getListDroit() {
		return listDroit;
	}

	/**
	 * [ActionDroitBean.listDroit] Setter 
	 * @author MAKERRI Sofiane on : 18 mars 2014  18:34:50
	 * @param listDroit the listDroit to set
	 */
	public void setListDroit(List<Boolean> listDroit) {
		this.listDroit = listDroit;
	}
	
	
	

}
