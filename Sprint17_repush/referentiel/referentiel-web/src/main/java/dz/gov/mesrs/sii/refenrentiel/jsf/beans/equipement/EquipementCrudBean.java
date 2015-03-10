/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.equipement.EquipementCrudBean.java] 
 * @author MAKERRI Sofiane on : 22 mars 2014  16:05:35
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.equipement;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author MAKERRI Sofiane  on : 22 mars 2014  16:05:35
 */
@ManagedBean(name = "equipementCrudBean")
@SessionScoped
public class EquipementCrudBean implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 22 mars 2014  16:05:58
	 */
	private static final long serialVersionUID = 1L;
	private boolean createAllow;
	private boolean editAllow;
	private boolean deleteAllow;
	private boolean readAllow;

	/**
	 * [EquipementCrudBean.EquipementCrudBean()] Constructor
	 * @author MAKERRI Sofiane  on : 22 mars 2014  16:05:35	
	 */
	public EquipementCrudBean() {
		super();
	}

	/**
	 * [EquipementCrudBean.createAllow] Getter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  16:06:19
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}

	/**
	 * [EquipementCrudBean.createAllow] Setter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  16:06:19
	 * @param createAllow the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}

	/**
	 * [EquipementCrudBean.editAllow] Getter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  16:06:19
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}

	/**
	 * [EquipementCrudBean.editAllow] Setter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  16:06:19
	 * @param editAllow the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}

	/**
	 * [EquipementCrudBean.deleteAllow] Getter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  16:06:19
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}

	/**
	 * [EquipementCrudBean.deleteAllow] Setter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  16:06:19
	 * @param deleteAllow the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}

	/**
	 * [EquipementCrudBean.readAllow] Getter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  16:06:19
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}

	/**
	 * [EquipementCrudBean.readAllow] Setter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  16:06:19
	 * @param readAllow the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}
	
	

}
