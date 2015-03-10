/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.avenant.AvenantDroitBean.java] 
 * @author MAKERRI Sofiane on : 4 mars 2014  17:37:13
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.avenant;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.CrudBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPermissionDto;

/**
 * @author MAKERRI Sofiane  on : 4 mars 2014  17:37:13
 */
@ManagedBean(name = "avenantDroitBean")
@SessionScoped
public class AvenantDroitBean implements Serializable{
    
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
	 * [AvenantDroitBean.AvenantDroitBean()] Constructor
	 * @author MAKERRI Sofiane  on : 4 mars 2014  17:37:13	
	 */
	public AvenantDroitBean() {
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
	 * [AvenantDroitBean.createAllow] Getter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:38:33
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}
	/**
	 * [AvenantDroitBean.createAllow] Setter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:38:33
	 * @param createAllow the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}
	/**
	 * [AvenantDroitBean.editAllow] Getter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:38:33
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}
	/**
	 * [AvenantDroitBean.editAllow] Setter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:38:33
	 * @param editAllow the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}
	/**
	 * [AvenantDroitBean.deleteAllow] Getter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:38:33
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}
	/**
	 * [AvenantDroitBean.deleteAllow] Setter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:38:33
	 * @param deleteAllow the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}
	/**
	 * [AvenantDroitBean.readAllow] Getter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:38:33
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}
	/**
	 * [AvenantDroitBean.readAllow] Setter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:38:33
	 * @param readAllow the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}
	/**
	 * [AvenantDroitBean.crudBean] Getter 
	 * @author MAKERRI Sofiane on : 5 mars 2014  11:43:53
	 * @return the crudBean
	 */
	public CrudBean getCrudBean() {
		return crudBean;
	}
	/**
	 * [AvenantDroitBean.crudBean] Setter 
	 * @author MAKERRI Sofiane on : 5 mars 2014  11:43:53
	 * @param crudBean the crudBean to set
	 */
	public void setCrudBean(CrudBean crudBean) {
		this.crudBean = crudBean;
	}
	/**
	 * [AvenantDroitBean.listDroit] Getter 
	 * @author MAKERRI Sofiane on : 5 mars 2014  11:43:53
	 * @return the listDroit
	 */
	public List<Boolean> getListDroit() {
		return listDroit;
	}
	/**
	 * [AvenantDroitBean.listDroit] Setter 
	 * @author MAKERRI Sofiane on : 5 mars 2014  11:43:53
	 * @param listDroit the listDroit to set
	 */
	public void setListDroit(List<Boolean> listDroit) {
		this.listDroit = listDroit;
	}

	/**
	 * [AvenantDroitBean.listActions] Getter 
	 * @author MAKERRI Sofiane on : 23 mars 2014  12:02:15
	 * @return the listActions
	 */
	public List<RefPermissionDto> getListActions() {
		return listActions;
	}

	/**
	 * [AvenantDroitBean.listActions] Setter 
	 * @author MAKERRI Sofiane on : 23 mars 2014  12:02:15
	 * @param listActions the listActions to set
	 */
	public void setListActions(List<RefPermissionDto> listActions) {
		this.listActions = listActions;
	}
	
	
	
	

}
