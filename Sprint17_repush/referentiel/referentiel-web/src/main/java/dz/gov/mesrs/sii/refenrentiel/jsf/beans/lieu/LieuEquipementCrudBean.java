/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.lieu.LieuEquipementCrudBean.java] 
 * @author MAKERRI Sofiane on : 27 mars 2014  10:12:36
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.lieu;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author MAKERRI Sofiane  on : 27 mars 2014  10:12:36
 */
@ManagedBean(name = "lieuEquipementCrudBean")
@SessionScoped
public class LieuEquipementCrudBean implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 27 mars 2014  10:12:46
	 */
	private static final long serialVersionUID = 1L;
	private boolean createAllow;
	private boolean editAllow;
	private boolean deleteAllow;
	private boolean readAllow;

	/**
	 * [LieuEquipementCrudBean.LieuEquipementCrudBean()] Constructor
	 * @author MAKERRI Sofiane  on : 27 mars 2014  10:12:36	
	 */
	public LieuEquipementCrudBean() {
		super();
	}

	/**
	 * [LieuEquipementCrudBean.createAllow] Getter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  10:13:25
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}

	/**
	 * [LieuEquipementCrudBean.createAllow] Setter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  10:13:25
	 * @param createAllow the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}

	/**
	 * [LieuEquipementCrudBean.editAllow] Getter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  10:13:25
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}

	/**
	 * [LieuEquipementCrudBean.editAllow] Setter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  10:13:25
	 * @param editAllow the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}

	/**
	 * [LieuEquipementCrudBean.deleteAllow] Getter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  10:13:25
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}

	/**
	 * [LieuEquipementCrudBean.deleteAllow] Setter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  10:13:25
	 * @param deleteAllow the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}

	/**
	 * [LieuEquipementCrudBean.readAllow] Getter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  10:13:25
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}

	/**
	 * [LieuEquipementCrudBean.readAllow] Setter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  10:13:25
	 * @param readAllow the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}
	
	

}
