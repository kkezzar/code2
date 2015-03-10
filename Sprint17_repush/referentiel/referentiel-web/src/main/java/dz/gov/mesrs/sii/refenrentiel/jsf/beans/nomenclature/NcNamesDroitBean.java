/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.nomenclature.NcNamesDroitBean.java] 
 * @author MAKERRI Sofiane on : 4 mars 2014  17:52:41
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.nomenclature;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.CrudBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPermissionDto;

/**
 * @author MAKERRI Sofiane  on : 4 mars 2014  17:52:41
 */
@ManagedBean(name = "ncNamesDroitBean")
@SessionScoped
public class NcNamesDroitBean implements Serializable {

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
	 * [NcNamesDroitBean.NcNamesDroitBean()] Constructor
	 * @author MAKERRI Sofiane  on : 4 mars 2014  17:52:41	
	 */
	public NcNamesDroitBean() {
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
	 * [NcNamesDroitBean.createAllow] Getter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:53:24
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}
	/**
	 * [NcNamesDroitBean.createAllow] Setter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:53:24
	 * @param createAllow the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}
	/**
	 * [NcNamesDroitBean.editAllow] Getter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:53:24
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}
	/**
	 * [NcNamesDroitBean.editAllow] Setter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:53:24
	 * @param editAllow the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}
	/**
	 * [NcNamesDroitBean.deleteAllow] Getter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:53:24
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}
	/**
	 * [NcNamesDroitBean.deleteAllow] Setter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:53:24
	 * @param deleteAllow the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}
	/**
	 * [NcNamesDroitBean.readAllow] Getter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:53:24
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}
	/**
	 * [NcNamesDroitBean.readAllow] Setter 
	 * @author MAKERRI Sofiane on : 4 mars 2014  17:53:24
	 * @param readAllow the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}

	/**
	 * [NcNamesDroitBean.crudBean] Getter 
	 * @author MAKERRI Sofiane on : 5 mars 2014  11:51:04
	 * @return the crudBean
	 */
	public CrudBean getCrudBean() {
		return crudBean;
	}

	/**
	 * [NcNamesDroitBean.crudBean] Setter 
	 * @author MAKERRI Sofiane on : 5 mars 2014  11:51:04
	 * @param crudBean the crudBean to set
	 */
	public void setCrudBean(CrudBean crudBean) {
		this.crudBean = crudBean;
	}

	/**
	 * [NcNamesDroitBean.listDroit] Getter 
	 * @author MAKERRI Sofiane on : 5 mars 2014  11:51:04
	 * @return the listDroit
	 */
	public List<Boolean> getListDroit() {
		return listDroit;
	}

	/**
	 * [NcNamesDroitBean.listDroit] Setter 
	 * @author MAKERRI Sofiane on : 5 mars 2014  11:51:04
	 * @param listDroit the listDroit to set
	 */
	public void setListDroit(List<Boolean> listDroit) {
		this.listDroit = listDroit;
	}

	/**
	 * [NcNamesDroitBean.listActions] Getter 
	 * @author MAKERRI Sofiane on : 23 mars 2014  12:09:10
	 * @return the listActions
	 */
	public List<RefPermissionDto> getListActions() {
		return listActions;
	}

	/**
	 * [NcNamesDroitBean.listActions] Setter 
	 * @author MAKERRI Sofiane on : 23 mars 2014  12:09:10
	 * @param listActions the listActions to set
	 */
	public void setListActions(List<RefPermissionDto> listActions) {
		this.listActions = listActions;
	}
	
	
	
	

}
