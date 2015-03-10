/**
 * [dz.gov.mesrs.sii.referentiel.jsf.beans.coordonnee.CoordonneeCrudBean.java] 
 * @author MAKERRI Sofiane on : 20 mars 2014  12:43:34
 */
package dz.gov.mesrs.sii.referentiel.jsf.beans.coordonnee;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author MAKERRI Sofiane  on : 20 mars 2014  12:43:34
 */
@ManagedBean(name = "coordonneeCrudBean")
@SessionScoped
public class CoordonneeCrudBean implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 20 mars 2014  12:46:10
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * [CoordonneeCrudBean.CoordonneeCrudBean()] Constructor
	 * @author MAKERRI Sofiane  on : 20 mars 2014  12:43:34	
	 */
	private boolean createAllow;
	private boolean editAllow;
	private boolean deleteAllow;
	private boolean readAllow;
	
	public CoordonneeCrudBean() {
		super();
	}

	/**
	 * [CoordonneeCrudBean.createAllow] Getter 
	 * @author MAKERRI Sofiane on : 20 mars 2014  12:46:21
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}

	/**
	 * [CoordonneeCrudBean.createAllow] Setter 
	 * @author MAKERRI Sofiane on : 20 mars 2014  12:46:21
	 * @param createAllow the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}

	/**
	 * [CoordonneeCrudBean.editAllow] Getter 
	 * @author MAKERRI Sofiane on : 20 mars 2014  12:46:21
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}

	/**
	 * [CoordonneeCrudBean.editAllow] Setter 
	 * @author MAKERRI Sofiane on : 20 mars 2014  12:46:21
	 * @param editAllow the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}

	/**
	 * [CoordonneeCrudBean.deleteAllow] Getter 
	 * @author MAKERRI Sofiane on : 20 mars 2014  12:46:21
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}

	/**
	 * [CoordonneeCrudBean.deleteAllow] Setter 
	 * @author MAKERRI Sofiane on : 20 mars 2014  12:46:21
	 * @param deleteAllow the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}

	/**
	 * [CoordonneeCrudBean.readAllow] Getter 
	 * @author MAKERRI Sofiane on : 20 mars 2014  12:46:21
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}

	/**
	 * [CoordonneeCrudBean.readAllow] Setter 
	 * @author MAKERRI Sofiane on : 20 mars 2014  12:46:21
	 * @param readAllow the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}
	
	
	

}
