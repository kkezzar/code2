/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.equipement.EtatEquipCrudBean.java] 
 * @author MAKERRI Sofiane on : 27 mars 2014  17:50:20
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.equipement;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author MAKERRI Sofiane  on : 27 mars 2014  17:50:20
 */
@ManagedBean(name = "etatEquipCrudBean")
@SessionScoped
public class EtatEquipCrudBean implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 27 mars 2014  17:50:45
	 */
	private static final long serialVersionUID = 1L;
	private boolean createAllow;
	private boolean editAllow;
	private boolean deleteAllow;
	private boolean readAllow;

	/**
	 * [EtatEquipCrudBean.EtatEquipCrudBean()] Constructor
	 * @author MAKERRI Sofiane  on : 27 mars 2014  17:50:20	
	 */
	public EtatEquipCrudBean() {
		super();
	}

	/**
	 * [EtatEquipCrudBean.createAllow] Getter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  17:51:10
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}

	/**
	 * [EtatEquipCrudBean.createAllow] Setter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  17:51:10
	 * @param createAllow the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}

	/**
	 * [EtatEquipCrudBean.editAllow] Getter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  17:51:10
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}

	/**
	 * [EtatEquipCrudBean.editAllow] Setter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  17:51:10
	 * @param editAllow the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}

	/**
	 * [EtatEquipCrudBean.deleteAllow] Getter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  17:51:10
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}

	/**
	 * [EtatEquipCrudBean.deleteAllow] Setter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  17:51:10
	 * @param deleteAllow the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}

	/**
	 * [EtatEquipCrudBean.readAllow] Getter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  17:51:10
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}

	/**
	 * [EtatEquipCrudBean.readAllow] Setter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  17:51:10
	 * @param readAllow the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}
	
	

}
