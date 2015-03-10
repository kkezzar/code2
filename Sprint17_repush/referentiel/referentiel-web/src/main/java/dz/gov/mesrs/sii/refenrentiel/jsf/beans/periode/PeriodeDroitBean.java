/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.periode.PeriodeDroitBean.java] 
 * @author MAKERRI Sofiane on : 25 mars 2014  16:31:51
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.periode;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.CrudBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPermissionDto;

/**
 * @author MAKERRI Sofiane  on : 25 mars 2014  16:31:51
 */
@ManagedBean(name = "periodeDroitBean")
@SessionScoped
public class PeriodeDroitBean implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 25 mars 2014  16:33:07
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
	 * [PeriodeDroitBean.PeriodeDroitBean()] Constructor
	 * @author MAKERRI Sofiane  on : 25 mars 2014  16:31:51	
	 */
	public PeriodeDroitBean() {
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
	 * [PeriodeDroitBean.createAllow] Getter 
	 * @author MAKERRI Sofiane on : 25 mars 2014  16:33:30
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}

	/**
	 * [PeriodeDroitBean.createAllow] Setter 
	 * @author MAKERRI Sofiane on : 25 mars 2014  16:33:30
	 * @param createAllow the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}

	/**
	 * [PeriodeDroitBean.editAllow] Getter 
	 * @author MAKERRI Sofiane on : 25 mars 2014  16:33:30
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}

	/**
	 * [PeriodeDroitBean.editAllow] Setter 
	 * @author MAKERRI Sofiane on : 25 mars 2014  16:33:30
	 * @param editAllow the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}

	/**
	 * [PeriodeDroitBean.deleteAllow] Getter 
	 * @author MAKERRI Sofiane on : 25 mars 2014  16:33:30
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}

	/**
	 * [PeriodeDroitBean.deleteAllow] Setter 
	 * @author MAKERRI Sofiane on : 25 mars 2014  16:33:30
	 * @param deleteAllow the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}

	/**
	 * [PeriodeDroitBean.readAllow] Getter 
	 * @author MAKERRI Sofiane on : 25 mars 2014  16:33:30
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}

	/**
	 * [PeriodeDroitBean.readAllow] Setter 
	 * @author MAKERRI Sofiane on : 25 mars 2014  16:33:30
	 * @param readAllow the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}

	/**
	 * [PeriodeDroitBean.crudBean] Getter 
	 * @author MAKERRI Sofiane on : 25 mars 2014  16:33:30
	 * @return the crudBean
	 */
	public CrudBean getCrudBean() {
		return crudBean;
	}

	/**
	 * [PeriodeDroitBean.crudBean] Setter 
	 * @author MAKERRI Sofiane on : 25 mars 2014  16:33:30
	 * @param crudBean the crudBean to set
	 */
	public void setCrudBean(CrudBean crudBean) {
		this.crudBean = crudBean;
	}

	/**
	 * [PeriodeDroitBean.listDroit] Getter 
	 * @author MAKERRI Sofiane on : 25 mars 2014  16:33:30
	 * @return the listDroit
	 */
	public List<Boolean> getListDroit() {
		return listDroit;
	}

	/**
	 * [PeriodeDroitBean.listDroit] Setter 
	 * @author MAKERRI Sofiane on : 25 mars 2014  16:33:30
	 * @param listDroit the listDroit to set
	 */
	public void setListDroit(List<Boolean> listDroit) {
		this.listDroit = listDroit;
	}

	/**
	 * [PeriodeDroitBean.listActions] Getter 
	 * @author MAKERRI Sofiane on : 25 mars 2014  16:33:30
	 * @return the listActions
	 */
	public List<RefPermissionDto> getListActions() {
		return listActions;
	}

	/**
	 * [PeriodeDroitBean.listActions] Setter 
	 * @author MAKERRI Sofiane on : 25 mars 2014  16:33:30
	 * @param listActions the listActions to set
	 */
	public void setListActions(List<RefPermissionDto> listActions) {
		this.listActions = listActions;
	}
	
	

}
