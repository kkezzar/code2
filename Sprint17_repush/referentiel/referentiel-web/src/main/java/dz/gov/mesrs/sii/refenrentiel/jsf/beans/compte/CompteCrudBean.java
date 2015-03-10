/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.compte.CompteCrudBean.java] 
 * @author MAKERRI Sofiane on : 22 mars 2014  14:08:13
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.compte;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author MAKERRI Sofiane  on : 22 mars 2014  14:08:13
 */
@ManagedBean(name = "compteCrudBean")
@SessionScoped
public class CompteCrudBean implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 22 mars 2014  14:08:51
	 */
	private static final long serialVersionUID = 1L;
	private boolean createAllow;
	private boolean editAllow;
	private boolean deleteAllow;
	private boolean readAllow;

	/**
	 * [CompteCrudBean.CompteCrudBean()] Constructor
	 * @author MAKERRI Sofiane  on : 22 mars 2014  14:08:13	
	 */
	public CompteCrudBean() {
		super();
	}

	/**
	 * [CompteCrudBean.createAllow] Getter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  14:09:14
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}

	/**
	 * [CompteCrudBean.createAllow] Setter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  14:09:14
	 * @param createAllow the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}

	/**
	 * [CompteCrudBean.editAllow] Getter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  14:09:14
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}

	/**
	 * [CompteCrudBean.editAllow] Setter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  14:09:14
	 * @param editAllow the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}

	/**
	 * [CompteCrudBean.deleteAllow] Getter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  14:09:14
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}

	/**
	 * [CompteCrudBean.deleteAllow] Setter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  14:09:14
	 * @param deleteAllow the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}

	/**
	 * [CompteCrudBean.readAllow] Getter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  14:09:14
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}

	/**
	 * [CompteCrudBean.readAllow] Setter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  14:09:14
	 * @param readAllow the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}
	
	

}
