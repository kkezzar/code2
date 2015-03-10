/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.equipement.ReservationEquipCrudBean.java] 
 * @author MAKERRI Sofiane on : 27 mars 2014  17:59:27
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.equipement;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author MAKERRI Sofiane  on : 27 mars 2014  17:59:27
 */
@ManagedBean(name = "reservationEquipCrudBean")
@SessionScoped
public class ReservationEquipCrudBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean createAllow;
	private boolean editAllow;
	private boolean deleteAllow;
	private boolean readAllow;
	/**
	 * [ReservationEquipCrudBean.ReservationEquipCrudBean()] Constructor
	 * @author MAKERRI Sofiane  on : 27 mars 2014  17:59:27	
	 */
	public ReservationEquipCrudBean() {
		super();
	}
	/**
	 * [ReservationEquipCrudBean.createAllow] Getter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  18:00:14
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}
	/**
	 * [ReservationEquipCrudBean.createAllow] Setter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  18:00:14
	 * @param createAllow the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}
	/**
	 * [ReservationEquipCrudBean.editAllow] Getter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  18:00:14
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}
	/**
	 * [ReservationEquipCrudBean.editAllow] Setter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  18:00:14
	 * @param editAllow the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}
	/**
	 * [ReservationEquipCrudBean.deleteAllow] Getter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  18:00:14
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}
	/**
	 * [ReservationEquipCrudBean.deleteAllow] Setter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  18:00:14
	 * @param deleteAllow the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}
	/**
	 * [ReservationEquipCrudBean.readAllow] Getter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  18:00:14
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}
	/**
	 * [ReservationEquipCrudBean.readAllow] Setter 
	 * @author MAKERRI Sofiane on : 27 mars 2014  18:00:14
	 * @param readAllow the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}
	
	

}
