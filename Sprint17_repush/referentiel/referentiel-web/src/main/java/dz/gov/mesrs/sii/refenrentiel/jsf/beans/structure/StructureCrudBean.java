/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.structure.StructureCrudBean.java] 
 * @author MAKERRI Sofiane on : 22 mars 2014  09:44:49
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.structure;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author MAKERRI Sofiane  on : 22 mars 2014  09:44:49
 */
@ManagedBean(name = "structureCrudBean")
@SessionScoped
public class StructureCrudBean implements Serializable  {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 22 mars 2014  09:45:23
	 */
	private static final long serialVersionUID = 1L;
	private boolean createAllow;
	private boolean editAllow;
	private boolean deleteAllow;
	private boolean readAllow;

	/**
	 * [StructureCrudBean.StructureCrudBean()] Constructor
	 * @author MAKERRI Sofiane  on : 22 mars 2014  09:44:49	
	 */
	public StructureCrudBean() {
		super();
	}

	/**
	 * [StructureCrudBean.createAllow] Getter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  09:46:05
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}

	/**
	 * [StructureCrudBean.createAllow] Setter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  09:46:05
	 * @param createAllow the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}

	/**
	 * [StructureCrudBean.editAllow] Getter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  09:46:05
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}

	/**
	 * [StructureCrudBean.editAllow] Setter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  09:46:05
	 * @param editAllow the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}

	/**
	 * [StructureCrudBean.deleteAllow] Getter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  09:46:05
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}

	/**
	 * [StructureCrudBean.deleteAllow] Setter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  09:46:05
	 * @param deleteAllow the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}

	/**
	 * [StructureCrudBean.readAllow] Getter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  09:46:05
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}

	/**
	 * [StructureCrudBean.readAllow] Setter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  09:46:05
	 * @param readAllow the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}
	
	
	

}
