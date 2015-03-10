/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.Specialitelmd.SpecialitelmdDroitBean.java] 
 * @author BELBRIK Oualid on : 4 avril 2014  17:42:12
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.specialitelmd;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.CrudBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPermissionDto;

/**
 * @author BELBRIK Oualid  on : 24 avril 2014  10:42:12
 */
@ManagedBean(name = "SpecialitelmdDroitBean")
@SessionScoped
public class SpecialitelmdDroitBean implements Serializable {
    
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
	 * [SpecialitelmdDroitBean.listActions] Getter 
	 * @author BELBRIK Oualid on : 21 avril 2014  12:04:01
	 * @return the listActions
	 */
	public List<RefPermissionDto> getListActions() {
		return listActions;
	}

	/**
	 * [SpecialitelmdDroitBean.listActions] Setter 
	 * @author BELBRIK Oualid on : 24 avril 2014  12:04:01
	 * @param listActions the listActions to set
	 */
	public void setListActions(List<RefPermissionDto> listActions) {
		this.listActions = listActions;
	}

	/**
	 * [SpecialitelmdDroitBean.SpecialitelmdDroitBean()] Constructor
	 * @author BELBRIK Oualid  on : 24 avril 2014  17:42:12	
	 */
	
	public SpecialitelmdDroitBean() {
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
	 * [SpecialitelmdDroitBean.createAllow] Getter 
	 * @author BELBRIK Oualid on : 24 avril 2014  17:42:59
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}

	/**
	 * [SpecialitelmdDroitBean.createAllow] Setter 
	 * @author BELBRIK Oualid on : 24 avril 2014  17:42:59
	 * @param createAllow the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}

	/**
	 * [SpecialitelmdDroitBean.editAllow] Getter 
	 * @author BELBRIK Oualid on : 24 avril 2014  17:42:59
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}

	/**
	 * [SpecialitelmdDroitBean.editAllow] Setter 
	 * @author BELBRIK Oualid on : 24 avril 2014  17:42:59
	 * @param editAllow the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}

	/**
	 * [SpecialitelmdDroitBean.deleteAllow] Getter 
	 * @author BELBRIK Oualid on : 24 avril 2014  17:42:59
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}

	/**
	 * [SpecialitelmdDroitBean.deleteAllow] Setter 
	 * @author BELBRIK Oualid on : 24 avril 2014  17:42:59
	 * @param deleteAllow the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}

	/**
	 * [SpecialitelmdDroitBean.readAllow] Getter 
	 * @author BELBRIK Oualid on : 24 avril 2014  17:42:59
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}

	/**
	 * [SpecialitelmdDroitBean.readAllow] Setter 
	 * @author BELBRIK Oualid on : 24 avril 2014  17:42:59
	 * @param readAllow the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}

	/**
	 * [SpecialitelmdDroitBean.crudBean] Getter 
	 * @author BELBRIK Oualid on : 24 avril 2014  11:46:07
	 * @return the crudBean
	 */
	public CrudBean getCrudBean() {
		return crudBean;
	}

	/**
	 * [SpecialitelmdDroitBean.crudBean] Setter 
	 * @author BELBRIK Oualid on : 24 avril 2014  11:46:07
	 * @param crudBean the crudBean to set
	 */
	public void setCrudBean(CrudBean crudBean) {
		this.crudBean = crudBean;
	}

	/**
	 * [SpecialitelmdDroitBean.listDroit] Getter 
	 * @author BELBRIK Oualid on : 24 avril 2014  11:46:07
	 * @return the listDroit
	 */
	public List<Boolean> getListDroit() {
		return listDroit;
	}

	/**
	 * [SpecialitelmdDroitBean.listDroit] Setter 
	 * @author BELBRIK Oualid on : 24 avril 2014  11:46:07
	 * @param listDroit the listDroit to set
	 */
	public void setListDroit(List<Boolean> listDroit) {
		this.listDroit = listDroit;
	}
	
	
	
	
	

}
