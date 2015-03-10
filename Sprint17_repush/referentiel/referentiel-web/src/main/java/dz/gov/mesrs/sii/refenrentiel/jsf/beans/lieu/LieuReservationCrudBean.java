/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.lieu.LieuReservationCrudBean.java] 
 * @author MAKERRI Sofiane on : 27 mars 2014  10:13:54
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.lieu;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author MAKERRI Sofiane  on : 27 mars 2014  10:13:54
 */
@ManagedBean(name = "lieuReservationCrudBean")
@SessionScoped
public class LieuReservationCrudBean implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 27 mars 2014  10:14:04
	 */
	private static final long serialVersionUID = 1L;
	private boolean createAllow;
	private boolean editAllow;
	private boolean deleteAllow;
	private boolean readAllow;

	/**
	 * [LieuReservationCrudBean.LieuReservationCrudBean()] Constructor
	 * @author MAKERRI Sofiane  on : 27 mars 2014  10:13:54	
	 */
	public LieuReservationCrudBean() {
		super();
	}

	/**
	 * [LieuReservationCrudBean.createAllow] Getter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  10:14:25
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}

	/**
	 * [LieuReservationCrudBean.createAllow] Setter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  10:14:25
	 * @param createAllow the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}

	/**
	 * [LieuReservationCrudBean.editAllow] Getter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  10:14:25
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}

	/**
	 * [LieuReservationCrudBean.editAllow] Setter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  10:14:25
	 * @param editAllow the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}

	/**
	 * [LieuReservationCrudBean.deleteAllow] Getter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  10:14:25
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}

	/**
	 * [LieuReservationCrudBean.deleteAllow] Setter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  10:14:25
	 * @param deleteAllow the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}

	/**
	 * [LieuReservationCrudBean.readAllow] Getter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  10:14:25
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}

	/**
	 * [LieuReservationCrudBean.readAllow] Setter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  10:14:25
	 * @param readAllow the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}
	
	

}
