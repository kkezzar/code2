/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.affectation.AffectationCrudBean.java] 
 * @author MAKERRI Sofiane on : 20 mars 2014  15:24:38
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.affectation;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author MAKERRI Sofiane  on : 20 mars 2014  15:24:38
 */
@ManagedBean(name = "affecIndividuCrudBean")
@SessionScoped
public class AffecIndividuCrudBean implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 20 mars 2014  15:25:05
	 */
	private static final long serialVersionUID = 1L;
	private boolean createAllow;
	private boolean editAllow;
	private boolean deleteAllow;
	private boolean readAllow;

	/**
	 * [AffectationCrudBean.AffectationCrudBean()] Constructor
	 * @author MAKERRI Sofiane  on : 20 mars 2014  15:24:38	
	 */
	public AffecIndividuCrudBean() {
		super();
	}

	/**
	 * [AffectationCrudBean.createAllow] Getter 
	 * @author MAKERRI Sofiane on : 20 mars 2014  15:25:50
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}

	/**
	 * [AffectationCrudBean.createAllow] Setter 
	 * @author MAKERRI Sofiane on : 20 mars 2014  15:25:50
	 * @param createAllow the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}

	/**
	 * [AffectationCrudBean.editAllow] Getter 
	 * @author MAKERRI Sofiane on : 20 mars 2014  15:25:50
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}

	/**
	 * [AffectationCrudBean.editAllow] Setter 
	 * @author MAKERRI Sofiane on : 20 mars 2014  15:25:50
	 * @param editAllow the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}

	/**
	 * [AffectationCrudBean.deleteAllow] Getter 
	 * @author MAKERRI Sofiane on : 20 mars 2014  15:25:50
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}

	/**
	 * [AffectationCrudBean.deleteAllow] Setter 
	 * @author MAKERRI Sofiane on : 20 mars 2014  15:25:50
	 * @param deleteAllow the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}

	/**
	 * [AffectationCrudBean.readAllow] Getter 
	 * @author MAKERRI Sofiane on : 20 mars 2014  15:25:50
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}

	/**
	 * [AffectationCrudBean.readAllow] Setter 
	 * @author MAKERRI Sofiane on : 20 mars 2014  15:25:50
	 * @param readAllow the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}
	
	
	
	
	

}
