/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.parametrage.ParamIhmDroitBean.java] 
 * @author MAKERRI Sofiane on : 29 mars 2014  11:47:34
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
 * @author MAKERRI Sofiane on : 29 mars 2014 11:47:34
 */
@ManagedBean(name = "paramIhmDroitBean")
@SessionScoped
public class ParamIhmDroitBean implements Serializable {

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
	 * [ParamIhmDroitBean.ParamIhmDroitBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 29 mars 2014 11:47:34
	 */
	public ParamIhmDroitBean() {
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
	 * [ParamIhmDroitBean.createAllow] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 mars 2014 11:49:57
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}

	/**
	 * [ParamIhmDroitBean.createAllow] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 mars 2014 11:49:57
	 * @param createAllow
	 *            the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}

	/**
	 * [ParamIhmDroitBean.editAllow] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 mars 2014 11:49:57
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}

	/**
	 * [ParamIhmDroitBean.editAllow] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 mars 2014 11:49:57
	 * @param editAllow
	 *            the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}

	/**
	 * [ParamIhmDroitBean.deleteAllow] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 mars 2014 11:49:57
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}

	/**
	 * [ParamIhmDroitBean.deleteAllow] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 mars 2014 11:49:57
	 * @param deleteAllow
	 *            the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}

	/**
	 * [ParamIhmDroitBean.readAllow] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 mars 2014 11:49:57
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}

	/**
	 * [ParamIhmDroitBean.readAllow] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 mars 2014 11:49:57
	 * @param readAllow
	 *            the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}

	/**
	 * [ParamIhmDroitBean.crudBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 mars 2014 11:49:57
	 * @return the crudBean
	 */
	public CrudBean getCrudBean() {
		return crudBean;
	}

	/**
	 * [ParamIhmDroitBean.crudBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 mars 2014 11:49:57
	 * @param crudBean
	 *            the crudBean to set
	 */
	public void setCrudBean(CrudBean crudBean) {
		this.crudBean = crudBean;
	}

	/**
	 * [ParamIhmDroitBean.listDroit] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 mars 2014 11:49:57
	 * @return the listDroit
	 */
	public List<Boolean> getListDroit() {
		return listDroit;
	}

	/**
	 * [ParamIhmDroitBean.listDroit] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 mars 2014 11:49:57
	 * @param listDroit
	 *            the listDroit to set
	 */
	public void setListDroit(List<Boolean> listDroit) {
		this.listDroit = listDroit;
	}

	/**
	 * [ParamIhmDroitBean.listActions] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 mars 2014 11:49:57
	 * @return the listActions
	 */
	public List<RefPermissionDto> getListActions() {
		return listActions;
	}

	/**
	 * [ParamIhmDroitBean.listActions] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 mars 2014 11:49:57
	 * @param listActions
	 *            the listActions to set
	 */
	public void setListActions(List<RefPermissionDto> listActions) {
		this.listActions = listActions;
	}

}
