/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.plageadresse.PlageAccessDroitBean.java] 
 * @author MAKERRI Sofiane on : 23 mars 2014  12:15:12
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.plageadresse;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.CrudBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPermissionDto;

/**
 * @author MAKERRI Sofiane  on : 23 mars 2014  12:15:12
 */
@ManagedBean(name = "plageAdresseDroitBean")
@SessionScoped
public class PlageAdresseDroitBean implements Serializable  {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 23 mars 2014  12:15:30
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
	 * [PlageAccessDroitBean.PlageAccessDroitBean()] Constructor
	 * @author MAKERRI Sofiane  on : 23 mars 2014  12:15:12	
	 */
	public PlageAdresseDroitBean() {
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
	 * [PlageAccessDroitBean.createAllow] Getter 
	 * @author MAKERRI Sofiane on : 23 mars 2014  12:16:26
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}

	/**
	 * [PlageAccessDroitBean.createAllow] Setter 
	 * @author MAKERRI Sofiane on : 23 mars 2014  12:16:26
	 * @param createAllow the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}

	/**
	 * [PlageAccessDroitBean.editAllow] Getter 
	 * @author MAKERRI Sofiane on : 23 mars 2014  12:16:26
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}

	/**
	 * [PlageAccessDroitBean.editAllow] Setter 
	 * @author MAKERRI Sofiane on : 23 mars 2014  12:16:26
	 * @param editAllow the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}

	/**
	 * [PlageAccessDroitBean.deleteAllow] Getter 
	 * @author MAKERRI Sofiane on : 23 mars 2014  12:16:26
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}

	/**
	 * [PlageAccessDroitBean.deleteAllow] Setter 
	 * @author MAKERRI Sofiane on : 23 mars 2014  12:16:26
	 * @param deleteAllow the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}

	/**
	 * [PlageAccessDroitBean.readAllow] Getter 
	 * @author MAKERRI Sofiane on : 23 mars 2014  12:16:26
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}

	/**
	 * [PlageAccessDroitBean.readAllow] Setter 
	 * @author MAKERRI Sofiane on : 23 mars 2014  12:16:26
	 * @param readAllow the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}

	/**
	 * [PlageAccessDroitBean.crudBean] Getter 
	 * @author MAKERRI Sofiane on : 23 mars 2014  12:16:26
	 * @return the crudBean
	 */
	public CrudBean getCrudBean() {
		return crudBean;
	}

	/**
	 * [PlageAccessDroitBean.crudBean] Setter 
	 * @author MAKERRI Sofiane on : 23 mars 2014  12:16:26
	 * @param crudBean the crudBean to set
	 */
	public void setCrudBean(CrudBean crudBean) {
		this.crudBean = crudBean;
	}

	/**
	 * [PlageAccessDroitBean.listDroit] Getter 
	 * @author MAKERRI Sofiane on : 23 mars 2014  12:16:26
	 * @return the listDroit
	 */
	public List<Boolean> getListDroit() {
		return listDroit;
	}

	/**
	 * [PlageAccessDroitBean.listDroit] Setter 
	 * @author MAKERRI Sofiane on : 23 mars 2014  12:16:26
	 * @param listDroit the listDroit to set
	 */
	public void setListDroit(List<Boolean> listDroit) {
		this.listDroit = listDroit;
	}

	/**
	 * [PlageAccessDroitBean.listActions] Getter 
	 * @author MAKERRI Sofiane on : 23 mars 2014  12:16:26
	 * @return the listActions
	 */
	public List<RefPermissionDto> getListActions() {
		return listActions;
	}

	/**
	 * [PlageAccessDroitBean.listActions] Setter 
	 * @author MAKERRI Sofiane on : 23 mars 2014  12:16:26
	 * @param listActions the listActions to set
	 */
	public void setListActions(List<RefPermissionDto> listActions) {
		this.listActions = listActions;
	}
	
	
	

}
