/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.configuration.ParametreEtabDroitBean.java] 
 * @author MAKERRI Sofiane on : 27 avr. 2014  14:22:43
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
 * @author MAKERRI Sofiane  on : 27 avr. 2014  14:22:43
 */
@ManagedBean(name = "parametreEtabDroitBean")
@SessionScoped
public class ParametreEtabDroitBean implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 27 avr. 2014  14:22:58
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
	 * [ParametreEtabDroitBean.ParametreEtabDroitBean()] Constructor
	 * @author MAKERRI Sofiane  on : 27 avr. 2014  14:22:43	
	 */
	public ParametreEtabDroitBean() {
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
	 * [ParametreEtabDroitBean.createAllow] Getter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  14:23:45
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}

	/**
	 * [ParametreEtabDroitBean.createAllow] Setter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  14:23:45
	 * @param createAllow the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}

	/**
	 * [ParametreEtabDroitBean.editAllow] Getter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  14:23:45
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}

	/**
	 * [ParametreEtabDroitBean.editAllow] Setter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  14:23:45
	 * @param editAllow the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}

	/**
	 * [ParametreEtabDroitBean.deleteAllow] Getter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  14:23:45
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}

	/**
	 * [ParametreEtabDroitBean.deleteAllow] Setter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  14:23:45
	 * @param deleteAllow the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}

	/**
	 * [ParametreEtabDroitBean.readAllow] Getter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  14:23:45
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}

	/**
	 * [ParametreEtabDroitBean.readAllow] Setter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  14:23:45
	 * @param readAllow the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}

	/**
	 * [ParametreEtabDroitBean.crudBean] Getter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  14:23:45
	 * @return the crudBean
	 */
	public CrudBean getCrudBean() {
		return crudBean;
	}

	/**
	 * [ParametreEtabDroitBean.crudBean] Setter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  14:23:45
	 * @param crudBean the crudBean to set
	 */
	public void setCrudBean(CrudBean crudBean) {
		this.crudBean = crudBean;
	}

	/**
	 * [ParametreEtabDroitBean.listDroit] Getter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  14:23:45
	 * @return the listDroit
	 */
	public List<Boolean> getListDroit() {
		return listDroit;
	}

	/**
	 * [ParametreEtabDroitBean.listDroit] Setter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  14:23:45
	 * @param listDroit the listDroit to set
	 */
	public void setListDroit(List<Boolean> listDroit) {
		this.listDroit = listDroit;
	}

	/**
	 * [ParametreEtabDroitBean.listActions] Getter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  14:23:45
	 * @return the listActions
	 */
	public List<RefPermissionDto> getListActions() {
		return listActions;
	}

	/**
	 * [ParametreEtabDroitBean.listActions] Setter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  14:23:45
	 * @param listActions the listActions to set
	 */
	public void setListActions(List<RefPermissionDto> listActions) {
		this.listActions = listActions;
	}
	
	
	

}
