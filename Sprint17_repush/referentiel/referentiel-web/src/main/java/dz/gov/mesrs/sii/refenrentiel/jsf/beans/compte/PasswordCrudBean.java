/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.compte.PasswordCrudBean.java] 
 * @author MAKERRI Sofiane on : 22 mars 2014  14:08:13
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.compte;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author MAKERRI Sofiane  on : 22 mars 2014  14:08:13
 */
@ManagedBean(name = "passwordCrudBean")
@SessionScoped
public class PasswordCrudBean implements Serializable {

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
	 * [PasswordCrudBean.PasswordCrudBean()] Constructor
	 * @author MAKERRI Sofiane  on : 22 mars 2014  14:08:13	
	 */
	public PasswordCrudBean() {
		super();
	}

	/**
	 * [PasswordCrudBean.createAllow] Getter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  14:09:14
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}

	/**
	 * [PasswordCrudBean.createAllow] Setter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  14:09:14
	 * @param createAllow the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}

	/**
	 * [PasswordCrudBean.editAllow] Getter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  14:09:14
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}

	/**
	 * [PasswordCrudBean.editAllow] Setter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  14:09:14
	 * @param editAllow the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}

	/**
	 * [PasswordCrudBean.deleteAllow] Getter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  14:09:14
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}

	/**
	 * [PasswordCrudBean.deleteAllow] Setter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  14:09:14
	 * @param deleteAllow the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}

	/**
	 * [PasswordCrudBean.readAllow] Getter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  14:09:14
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}

	/**
	 * [PasswordCrudBean.readAllow] Setter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  14:09:14
	 * @param readAllow the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}
	

}
