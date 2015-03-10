/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.individu.IndividuDroitBean.java] 
 * @author MAKERRI Sofiane on : 4 mars 2014  16:50:00
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.individu;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.CrudBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPermissionDto;

/**
 * @author MAKERRI Sofiane on : 4 mars 2014 16:50:00
 */
@ManagedBean(name = "individuDroitBean")
@SessionScoped
public class IndividuDroitBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 4 mars 2014 17:37:36
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
	 * [IndividuDroitBean.IndividuDroitBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 4 mars 2014 16:50:00
	 */
	public IndividuDroitBean() {
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
	 * [IndividuDroitBean.createAllow] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 mars 2014 16:51:44
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}

	/**
	 * [IndividuDroitBean.createAllow] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 mars 2014 16:51:44
	 * @param createAllow
	 *            the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}

	/**
	 * [IndividuDroitBean.editAllow] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 mars 2014 16:51:44
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}

	/**
	 * [IndividuDroitBean.editAllow] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 mars 2014 16:51:44
	 * @param editAllow
	 *            the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}

	/**
	 * [IndividuDroitBean.deleteAllow] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 mars 2014 16:51:44
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}

	/**
	 * [IndividuDroitBean.deleteAllow] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 mars 2014 16:51:44
	 * @param deleteAllow
	 *            the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}

	/**
	 * [IndividuDroitBean.readAllow] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 mars 2014 16:51:44
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}

	/**
	 * [IndividuDroitBean.readAllow] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 mars 2014 16:51:44
	 * @param readAllow
	 *            the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}

	/**
	 * [IndividuDroitBean.crudBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 mars 2014 11:02:51
	 * @return the crudBean
	 */
	public CrudBean getCrudBean() {
		return crudBean;
	}

	/**
	 * [IndividuDroitBean.crudBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 mars 2014 11:02:51
	 * @param crudBean
	 *            the crudBean to set
	 */
	public void setCrudBean(CrudBean crudBean) {
		this.crudBean = crudBean;
	}

	/**
	 * [IndividuDroitBean.listDroit] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 mars 2014 11:05:09
	 * @return the listDroit
	 */
	public List<Boolean> getListDroit() {
		return listDroit;
	}

	/**
	 * [IndividuDroitBean.listDroit] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 mars 2014 11:05:09
	 * @param listDroit
	 *            the listDroit to set
	 */
	public void setListDroit(List<Boolean> listDroit) {
		this.listDroit = listDroit;
	}

	/**
	 * [IndividuDroitBean.listActions] Getter 
	 * @author MAKERRI Sofiane on : 23 mars 2014  11:58:47
	 * @return the listActions
	 */
	public List<RefPermissionDto> getListActions() {
		//listActions = crudBean.getListActions();
		return listActions;
	}

	/**
	 * [IndividuDroitBean.listActions] Setter 
	 * @author MAKERRI Sofiane on : 23 mars 2014  11:58:47
	 * @param listActions the listActions to set
	 */
	public void setListActions(List<RefPermissionDto> listActions) {
		this.listActions = listActions;
	}
	
	

}
