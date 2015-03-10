/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.etablissement.EtablissementDroitBean.java] 
 * @author MAKERRI Sofiane on : 10 avr. 2014  12:05:25
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.etablissement;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.CrudBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPermissionDto;

/**
 * @author MAKERRI Sofiane  on : 10 avr. 2014  12:05:25
 */
@ManagedBean(name = "etablissementDroitBean")
@SessionScoped
public class EtablissementDroitBean implements Serializable{

	
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
	 * [EtablissementDroitBean.EtablissementDroitBean()] Constructor
	 * @author MAKERRI Sofiane  on : 10 avr. 2014  12:05:25	
	 */
	public EtablissementDroitBean() {
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
	 * [EtablissementDroitBean.createAllow] Getter 
	 * @author MAKERRI Sofiane on : 10 avr. 2014  12:06:40
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}

	/**
	 * [EtablissementDroitBean.createAllow] Setter 
	 * @author MAKERRI Sofiane on : 10 avr. 2014  12:06:40
	 * @param createAllow the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}

	/**
	 * [EtablissementDroitBean.editAllow] Getter 
	 * @author MAKERRI Sofiane on : 10 avr. 2014  12:06:40
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}

	/**
	 * [EtablissementDroitBean.editAllow] Setter 
	 * @author MAKERRI Sofiane on : 10 avr. 2014  12:06:40
	 * @param editAllow the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}

	/**
	 * [EtablissementDroitBean.deleteAllow] Getter 
	 * @author MAKERRI Sofiane on : 10 avr. 2014  12:06:40
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}

	/**
	 * [EtablissementDroitBean.deleteAllow] Setter 
	 * @author MAKERRI Sofiane on : 10 avr. 2014  12:06:40
	 * @param deleteAllow the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}

	/**
	 * [EtablissementDroitBean.readAllow] Getter 
	 * @author MAKERRI Sofiane on : 10 avr. 2014  12:06:40
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}

	/**
	 * [EtablissementDroitBean.readAllow] Setter 
	 * @author MAKERRI Sofiane on : 10 avr. 2014  12:06:40
	 * @param readAllow the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}

	/**
	 * [EtablissementDroitBean.crudBean] Getter 
	 * @author MAKERRI Sofiane on : 10 avr. 2014  12:06:40
	 * @return the crudBean
	 */
	public CrudBean getCrudBean() {
		return crudBean;
	}

	/**
	 * [EtablissementDroitBean.crudBean] Setter 
	 * @author MAKERRI Sofiane on : 10 avr. 2014  12:06:40
	 * @param crudBean the crudBean to set
	 */
	public void setCrudBean(CrudBean crudBean) {
		this.crudBean = crudBean;
	}

	/**
	 * [EtablissementDroitBean.listDroit] Getter 
	 * @author MAKERRI Sofiane on : 10 avr. 2014  12:06:40
	 * @return the listDroit
	 */
	public List<Boolean> getListDroit() {
		return listDroit;
	}

	/**
	 * [EtablissementDroitBean.listDroit] Setter 
	 * @author MAKERRI Sofiane on : 10 avr. 2014  12:06:40
	 * @param listDroit the listDroit to set
	 */
	public void setListDroit(List<Boolean> listDroit) {
		this.listDroit = listDroit;
	}

	/**
	 * [EtablissementDroitBean.listActions] Getter 
	 * @author MAKERRI Sofiane on : 10 avr. 2014  12:06:40
	 * @return the listActions
	 */
	public List<RefPermissionDto> getListActions() {
		return listActions;
	}

	/**
	 * [EtablissementDroitBean.listActions] Setter 
	 * @author MAKERRI Sofiane on : 10 avr. 2014  12:06:40
	 * @param listActions the listActions to set
	 */
	public void setListActions(List<RefPermissionDto> listActions) {
		this.listActions = listActions;
	}
	
	
	

}
