/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.etablissement.EtabParameterCrudBean.java] 
 * @author MAKERRI Sofiane on : 28 avr. 2014  09:16:03
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.etablissement;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author MAKERRI Sofiane  on : 28 avr. 2014  09:16:03
 */
@ManagedBean(name = "etabParameterCrudBean")
@SessionScoped
public class EtabParameterCrudBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean createAllow;
	private boolean editAllow;
	private boolean deleteAllow;
	private boolean readAllow;

	/**
	 * [EtabParameterCrudBean.EtabParameterCrudBean()] Constructor
	 * @author MAKERRI Sofiane  on : 28 avr. 2014  09:16:03	
	 */
	public EtabParameterCrudBean() {
		super();
	}

	/**
	 * [EtabParameterCrudBean.createAllow] Getter 
	 * @author MAKERRI Sofiane on : 28 avr. 2014  09:16:50
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}

	/**
	 * [EtabParameterCrudBean.createAllow] Setter 
	 * @author MAKERRI Sofiane on : 28 avr. 2014  09:16:50
	 * @param createAllow the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}

	/**
	 * [EtabParameterCrudBean.editAllow] Getter 
	 * @author MAKERRI Sofiane on : 28 avr. 2014  09:16:50
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}

	/**
	 * [EtabParameterCrudBean.editAllow] Setter 
	 * @author MAKERRI Sofiane on : 28 avr. 2014  09:16:50
	 * @param editAllow the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}

	/**
	 * [EtabParameterCrudBean.deleteAllow] Getter 
	 * @author MAKERRI Sofiane on : 28 avr. 2014  09:16:50
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}

	/**
	 * [EtabParameterCrudBean.deleteAllow] Setter 
	 * @author MAKERRI Sofiane on : 28 avr. 2014  09:16:50
	 * @param deleteAllow the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}

	/**
	 * [EtabParameterCrudBean.readAllow] Getter 
	 * @author MAKERRI Sofiane on : 28 avr. 2014  09:16:50
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}

	/**
	 * [EtabParameterCrudBean.readAllow] Setter 
	 * @author MAKERRI Sofiane on : 28 avr. 2014  09:16:50
	 * @param readAllow the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}
	
	

}
