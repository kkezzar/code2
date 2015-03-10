/**
 * [dz.gov.mesrs.sii.referentiel.jsf.beans.document.DocumentCrudBean.java] 
 * @author MAKERRI Sofiane on : 24 mars 2014  16:37:31
 */
package dz.gov.mesrs.sii.referentiel.jsf.beans.document;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author MAKERRI Sofiane  on : 24 mars 2014  16:37:31
 */
@ManagedBean(name = "documentCrudBean")
@SessionScoped
public class DocumentCrudBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private boolean createAllow;
	private boolean editAllow;
	private boolean deleteAllow;
	private boolean readAllow;
	/**
	 * [DocumentCrudBean.DocumentCrudBean()] Constructor
	 * @author MAKERRI Sofiane  on : 24 mars 2014  16:37:31	
	 */
	public DocumentCrudBean() {
		super();
	}
	/**
	 * [DocumentCrudBean.createAllow] Getter 
	 * @author MAKERRI Sofiane on : 24 mars 2014  16:38:16
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}
	/**
	 * [DocumentCrudBean.createAllow] Setter 
	 * @author MAKERRI Sofiane on : 24 mars 2014  16:38:16
	 * @param createAllow the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}
	/**
	 * [DocumentCrudBean.editAllow] Getter 
	 * @author MAKERRI Sofiane on : 24 mars 2014  16:38:16
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}
	/**
	 * [DocumentCrudBean.editAllow] Setter 
	 * @author MAKERRI Sofiane on : 24 mars 2014  16:38:16
	 * @param editAllow the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}
	/**
	 * [DocumentCrudBean.deleteAllow] Getter 
	 * @author MAKERRI Sofiane on : 24 mars 2014  16:38:16
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}
	/**
	 * [DocumentCrudBean.deleteAllow] Setter 
	 * @author MAKERRI Sofiane on : 24 mars 2014  16:38:16
	 * @param deleteAllow the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}
	/**
	 * [DocumentCrudBean.readAllow] Getter 
	 * @author MAKERRI Sofiane on : 24 mars 2014  16:38:16
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}
	/**
	 * [DocumentCrudBean.readAllow] Setter 
	 * @author MAKERRI Sofiane on : 24 mars 2014  16:38:16
	 * @param readAllow the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}
	
	

}
