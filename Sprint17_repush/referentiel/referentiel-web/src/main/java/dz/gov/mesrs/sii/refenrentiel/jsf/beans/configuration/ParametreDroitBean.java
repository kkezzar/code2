/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.configuration.ConfigurationDroitBean.java] 
 * @author MAKERRI Sofiane on : 22 avr. 2014  12:54:16
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.configuration;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.CrudBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPermissionDto;

/**
 * @author MAKERRI Sofiane  on : 22 avr. 2014  12:54:16
 */
@ManagedBean(name = "parametreDroitBean")
@SessionScoped
public class ParametreDroitBean implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 22 avr. 2014  12:54:27
	 */
	private static final long serialVersionUID = 1L;
	private boolean createAllow;
	private boolean editAllow;
	private boolean deleteAllow;
	private boolean readAllow;
	@ManagedProperty(value = "#{crudBean}")
	private CrudBean crudBean;
	private List<Boolean> listDroit;
	private List<RefPermissionDto> listActions;

	/**
	 * [ConfigurationDroitBean.ConfigurationDroitBean()] Constructor
	 * @author MAKERRI Sofiane  on : 22 avr. 2014  12:54:16	
	 */
	public ParametreDroitBean() {
		super();
	}
	
	@PostConstruct
	public void init() {
		if (listDroit == null) {
			listDroit = crudBean.loadCrudDroit();
			listActions = crudBean.getListActions();
			if (listDroit == null) {
				setCreateAllow(false);
				setEditAllow(false);
				setDeleteAllow(false);
				setReadAllow(false);
			} else {
				setCreateAllow(listDroit.get(0));
				setEditAllow(listDroit.get(1));
				setDeleteAllow(listDroit.get(2));
				setReadAllow(listDroit.get(3));
			}
		}
	}

	/**
	 * [ConfigurationDroitBean.createAllow] Getter 
	 * @author MAKERRI Sofiane on : 22 avr. 2014  12:55:03
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}

	/**
	 * [ConfigurationDroitBean.createAllow] Setter 
	 * @author MAKERRI Sofiane on : 22 avr. 2014  12:55:03
	 * @param createAllow the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}

	/**
	 * [ConfigurationDroitBean.editAllow] Getter 
	 * @author MAKERRI Sofiane on : 22 avr. 2014  12:55:03
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}

	/**
	 * [ConfigurationDroitBean.editAllow] Setter 
	 * @author MAKERRI Sofiane on : 22 avr. 2014  12:55:03
	 * @param editAllow the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}

	/**
	 * [ConfigurationDroitBean.deleteAllow] Getter 
	 * @author MAKERRI Sofiane on : 22 avr. 2014  12:55:03
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}

	/**
	 * [ConfigurationDroitBean.deleteAllow] Setter 
	 * @author MAKERRI Sofiane on : 22 avr. 2014  12:55:03
	 * @param deleteAllow the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}

	/**
	 * [ConfigurationDroitBean.readAllow] Getter 
	 * @author MAKERRI Sofiane on : 22 avr. 2014  12:55:03
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}

	/**
	 * [ConfigurationDroitBean.readAllow] Setter 
	 * @author MAKERRI Sofiane on : 22 avr. 2014  12:55:03
	 * @param readAllow the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}

	/**
	 * [ConfigurationDroitBean.crudBean] Getter 
	 * @author MAKERRI Sofiane on : 22 avr. 2014  12:55:03
	 * @return the crudBean
	 */
	public CrudBean getCrudBean() {
		return crudBean;
	}

	/**
	 * [ConfigurationDroitBean.crudBean] Setter 
	 * @author MAKERRI Sofiane on : 22 avr. 2014  12:55:03
	 * @param crudBean the crudBean to set
	 */
	public void setCrudBean(CrudBean crudBean) {
		this.crudBean = crudBean;
	}

	/**
	 * [ConfigurationDroitBean.listDroit] Getter 
	 * @author MAKERRI Sofiane on : 22 avr. 2014  12:55:03
	 * @return the listDroit
	 */
	public List<Boolean> getListDroit() {
		return listDroit;
	}

	/**
	 * [ConfigurationDroitBean.listDroit] Setter 
	 * @author MAKERRI Sofiane on : 22 avr. 2014  12:55:03
	 * @param listDroit the listDroit to set
	 */
	public void setListDroit(List<Boolean> listDroit) {
		this.listDroit = listDroit;
	}

	/**
	 * [ConfigurationDroitBean.listActions] Getter 
	 * @author MAKERRI Sofiane on : 22 avr. 2014  12:55:03
	 * @return the listActions
	 */
	public List<RefPermissionDto> getListActions() {
		return listActions;
	}

	/**
	 * [ConfigurationDroitBean.listActions] Setter 
	 * @author MAKERRI Sofiane on : 22 avr. 2014  12:55:03
	 * @param listActions the listActions to set
	 */
	public void setListActions(List<RefPermissionDto> listActions) {
		this.listActions = listActions;
	}
	
	
	

}
