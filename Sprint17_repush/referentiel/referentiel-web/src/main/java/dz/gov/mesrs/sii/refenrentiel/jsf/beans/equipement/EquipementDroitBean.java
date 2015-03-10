/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.equipement.equipementDroitBean.java] 
 * @author BELBRIK Oualid on : 6 mars 2014  17:50:04
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.equipement;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.CrudBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPermissionDto;

/**
 * @author BELBRIK Oualid  on : 6 mars 2014  17:50:04
 */
@ManagedBean(name = "equipementDroitBean")
@SessionScoped
public class EquipementDroitBean implements Serializable {

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
	 * [equipementDroitBean.equipementDroitBean()] Constructor
	 * @author BELBRIK Oualid  on : 6 mars 2014  17:50:04	
	 */
	public EquipementDroitBean() {
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
	 * [equipementDroitBean.createAllow] Getter 
	 * @author BELBRIK Oualid on : 6 mars 2014  17:51:47
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}
	/**
	 * [equipementDroitBean.createAllow] Setter 
	 * @author BELBRIK Oualid on : 6 mars 2014  17:51:47 
	 * @param createAllow the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}
	/**
	 * [equipementDroitBean.editAllow] Getter 
	 * @author BELBRIK Oualid on : 6 mars 2014  17:51:47
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}
	/**
	 * [equipementDroitBean.editAllow] Setter 
	 * @author BELBRIK Oualid on : 6 mars 2014  17:51:47
	 * @param editAllow the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}
	/**
	 * [equipementDroitBean.deleteAllow] Getter 
	 * @author BELBRIK Oualid on : 6 mars 2014  17:51:47
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}
	/**
	 * [equipementDroitBean.deleteAllow] Setter 
	 * @author BELBRIK Oualid on : 6 mars 2014  17:51:47
	 * @param deleteAllow the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}
	/**
	 * [equipementDroitBean.readAllow] Getter 
	 * @author BELBRIK Oualid on : 6 mars 2014  17:51:47
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}
	/**
	 * [equipementDroitBean.readAllow] Setter 
	 * @author BELBRIK Oualid on : 6 mars 2014  17:51:47
	 * @param readAllow the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}

	/**
	 * [equipementDroitBean.crudBean] Getter 
	 * @author BELBRIK Oualid on : 5 mars 2014  11:49:39
	 * @return the crudBean
	 */
	public CrudBean getCrudBean() {
		return crudBean;
	}

	/**
	 * [equipementDroitBean.crudBean] Setter 
	 * @author BELBRIK Oualid on : 5 mars 2014  11:49:39
	 * @param crudBean the crudBean to set
	 */
	public void setCrudBean(CrudBean crudBean) {
		this.crudBean = crudBean;
	}

	/**
	 * [equipementDroitBean.listDroit] Getter 
	 * @author BELBRIK Oualid on : 5 mars 2014  11:49:39
	 * @return the listDroit
	 */
	public List<Boolean> getListDroit() {
		return listDroit;
	}

	/**
	 * [equipementDroitBean.listDroit] Setter 
	 * @author BELBRIK Oualid on : 5 mars 2014  11:49:39
	 * @param listDroit the listDroit to set
	 */
	public void setListDroit(List<Boolean> listDroit) {
		this.listDroit = listDroit;
	}

	/**
	 * [EquipementDroitBean.listActions] Getter 
	 * @author MAKERRI Sofiane on : 23 mars 2014  12:03:28
	 * @return the listActions
	 */
	public List<RefPermissionDto> getListActions() {
		return listActions;
	}

	/**
	 * [EquipementDroitBean.listActions] Setter 
	 * @author MAKERRI Sofiane on : 23 mars 2014  12:03:28
	 * @param listActions the listActions to set
	 */
	public void setListActions(List<RefPermissionDto> listActions) {
		this.listActions = listActions;
	}
	
	
	
	

}
