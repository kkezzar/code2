/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.contrat.PartenaireCrudBean.java] 
 * @author MAKERRI Sofiane on : 22 mars 2014  11:00:12
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.contrat;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author MAKERRI Sofiane  on : 22 mars 2014  11:00:12
 */
@ManagedBean(name = "signatureCrudBean")
@SessionScoped
public class SignatureCrudBean implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 22 mars 2014  11:00:40
	 */
	private static final long serialVersionUID = 1L;
	private boolean createAllow;
	private boolean editAllow;
	private boolean deleteAllow;
	private boolean readAllow;

	/**
	 * [PartenaireCrudBean.PartenaireCrudBean()] Constructor
	 * @author MAKERRI Sofiane  on : 22 mars 2014  11:00:12	
	 */
	public SignatureCrudBean() {
		super();
	}

	/**
	 * [PartenaireCrudBean.createAllow] Getter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  11:01:06
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}

	/**
	 * [PartenaireCrudBean.createAllow] Setter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  11:01:06
	 * @param createAllow the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}

	/**
	 * [PartenaireCrudBean.editAllow] Getter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  11:01:06
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}

	/**
	 * [PartenaireCrudBean.editAllow] Setter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  11:01:06
	 * @param editAllow the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}

	/**
	 * [PartenaireCrudBean.deleteAllow] Getter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  11:01:06
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}

	/**
	 * [PartenaireCrudBean.deleteAllow] Setter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  11:01:06
	 * @param deleteAllow the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}

	/**
	 * [PartenaireCrudBean.readAllow] Getter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  11:01:06
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}

	/**
	 * [PartenaireCrudBean.readAllow] Setter 
	 * @author MAKERRI Sofiane on : 22 mars 2014  11:01:06
	 * @param readAllow the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}
	
	

}
