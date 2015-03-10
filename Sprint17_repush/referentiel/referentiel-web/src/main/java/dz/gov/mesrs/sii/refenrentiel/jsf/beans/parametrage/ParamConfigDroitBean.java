/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.parametrage.ParamConfigDroitBean.java] 
 * @author MAKERRI Sofiane on : 29 mars 2014  11:47:52
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.parametrage;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.CrudBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPermissionDto;

/**
 * @author MAKERRI Sofiane on : 29 mars 2014 11:47:52
 */
@ManagedBean(name = "paramConfigDroitBean")
@SessionScoped
public class ParamConfigDroitBean implements Serializable {

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
	 * [ParamConfigDroitBean.ParamConfigDroitBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 29 mars 2014 11:47:52
	 */
	public ParamConfigDroitBean() {
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
	 * [ParamConfigDroitBean.createAllow] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 mars 2014 11:48:47
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}

	/**
	 * [ParamConfigDroitBean.createAllow] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 mars 2014 11:48:47
	 * @param createAllow
	 *            the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}

	/**
	 * [ParamConfigDroitBean.editAllow] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 mars 2014 11:48:47
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}

	/**
	 * [ParamConfigDroitBean.editAllow] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 mars 2014 11:48:47
	 * @param editAllow
	 *            the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}

	/**
	 * [ParamConfigDroitBean.deleteAllow] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 mars 2014 11:48:47
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}

	/**
	 * [ParamConfigDroitBean.deleteAllow] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 mars 2014 11:48:47
	 * @param deleteAllow
	 *            the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}

	/**
	 * [ParamConfigDroitBean.readAllow] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 mars 2014 11:48:47
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}

	/**
	 * [ParamConfigDroitBean.readAllow] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 mars 2014 11:48:47
	 * @param readAllow
	 *            the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}

	/**
	 * [ParamConfigDroitBean.crudBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 mars 2014 11:48:47
	 * @return the crudBean
	 */
	public CrudBean getCrudBean() {
		return crudBean;
	}

	/**
	 * [ParamConfigDroitBean.crudBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 mars 2014 11:48:47
	 * @param crudBean
	 *            the crudBean to set
	 */
	public void setCrudBean(CrudBean crudBean) {
		this.crudBean = crudBean;
	}

	/**
	 * [ParamConfigDroitBean.listDroit] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 mars 2014 11:48:47
	 * @return the listDroit
	 */
	public List<Boolean> getListDroit() {
		return listDroit;
	}

	/**
	 * [ParamConfigDroitBean.listDroit] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 mars 2014 11:48:47
	 * @param listDroit
	 *            the listDroit to set
	 */
	public void setListDroit(List<Boolean> listDroit) {
		this.listDroit = listDroit;
	}

	/**
	 * [ParamConfigDroitBean.listActions] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 mars 2014 11:48:47
	 * @return the listActions
	 */
	public List<RefPermissionDto> getListActions() {
		return listActions;
	}

	/**
	 * [ParamConfigDroitBean.listActions] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 mars 2014 11:48:47
	 * @param listActions
	 *            the listActions to set
	 */
	public void setListActions(List<RefPermissionDto> listActions) {
		this.listActions = listActions;
	}

}
