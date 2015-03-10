/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.etatvalidation.SituationCrudBean.java] 
 * @author MAKERRI Sofiane on : 20 mars 2014  14:59:50
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.etatvalidation;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author MAKERRI Sofiane  on : 20 mars 2014  14:59:50
 */
@ManagedBean(name = "situationCrudBean")
@SessionScoped
public class SituationCrudBean  implements Serializable{

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 20 mars 2014  15:00:30
	 */
	private static final long serialVersionUID = 1L;
	private boolean createAllow;
	private boolean editAllow;
	private boolean deleteAllow;
	private boolean readAllow;

	/**
	 * [SituationCrudBean.SituationCrudBean()] Constructor
	 * @author MAKERRI Sofiane  on : 20 mars 2014  14:59:50	
	 */
	public SituationCrudBean() {
		super();
	}

	/**
	 * [SituationCrudBean.createAllow] Getter 
	 * @author MAKERRI Sofiane on : 20 mars 2014  15:00:59
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}

	/**
	 * [SituationCrudBean.createAllow] Setter 
	 * @author MAKERRI Sofiane on : 20 mars 2014  15:00:59
	 * @param createAllow the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}

	/**
	 * [SituationCrudBean.editAllow] Getter 
	 * @author MAKERRI Sofiane on : 20 mars 2014  15:00:59
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}

	/**
	 * [SituationCrudBean.editAllow] Setter 
	 * @author MAKERRI Sofiane on : 20 mars 2014  15:00:59
	 * @param editAllow the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}

	/**
	 * [SituationCrudBean.deleteAllow] Getter 
	 * @author MAKERRI Sofiane on : 20 mars 2014  15:00:59
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}

	/**
	 * [SituationCrudBean.deleteAllow] Setter 
	 * @author MAKERRI Sofiane on : 20 mars 2014  15:00:59
	 * @param deleteAllow the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}

	/**
	 * [SituationCrudBean.readAllow] Getter 
	 * @author MAKERRI Sofiane on : 20 mars 2014  15:00:59
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}

	/**
	 * [SituationCrudBean.readAllow] Setter 
	 * @author MAKERRI Sofiane on : 20 mars 2014  15:00:59
	 * @param readAllow the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}
	
	
	

}
