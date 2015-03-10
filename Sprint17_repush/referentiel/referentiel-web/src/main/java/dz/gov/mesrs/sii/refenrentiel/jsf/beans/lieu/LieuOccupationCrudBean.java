/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.equipement.LieuCrudBean.java] 
 * @author MAKERRI Sofiane on : 22 mars 2014  16:05:35
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.lieu;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author MAKERRI Sofiane  on : 22 mars 2014  16:05:35
 */
@ManagedBean(name = "lieuOccupationCrudBean")
@SessionScoped
public class LieuOccupationCrudBean implements Serializable {

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
	 * [LieuCrudBean.LieuCrudBean()] Constructor
	 * @author MAKERRI Sofiane  on : 22 mars 2014  16:05:35	
	 */
	public LieuOccupationCrudBean() {
		super();
	}

	/**
	 * [LieuCrudBean.createAllow] Getter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  16:06:19
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}

	/**
	 * [LieuCrudBean.createAllow] Setter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  16:06:19
	 * @param createAllow the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}

	/**
	 * [LieuCrudBean.editAllow] Getter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  16:06:19
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}

	/**
	 * [LieuCrudBean.editAllow] Setter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  16:06:19
	 * @param editAllow the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}

	/**
	 * [LieuCrudBean.deleteAllow] Getter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  16:06:19
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}

	/**
	 * [LieuCrudBean.deleteAllow] Setter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  16:06:19
	 * @param deleteAllow the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}

	/**
	 * [LieuCrudBean.readAllow] Getter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  16:06:19
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}

	/**
	 * [LieuCrudBean.readAllow] Setter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  16:06:19
	 * @param readAllow the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}
	
	

}
