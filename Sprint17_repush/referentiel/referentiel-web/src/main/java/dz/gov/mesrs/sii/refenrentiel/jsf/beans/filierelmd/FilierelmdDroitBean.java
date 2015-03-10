/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.Filierelmd.FilierelmdDroitBean.java] 
 * @author BELBRIK Oualid on : 4 avril 2014  17:42:12
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.filierelmd;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.CrudBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPermissionDto;

/**
 * @author BELBRIK Oualid  on : 21 avril 2014  17:42:12
 */
@ManagedBean(name = "FilierelmdDroitBean")
@SessionScoped
public class FilierelmdDroitBean implements Serializable {
    
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
	 * [FilierelmdDroitBean.listActions] Getter 
	 * @author BELBRIK Oualid on : 21 avril 2014  12:04:01
	 * @return the listActions
	 */
	public List<RefPermissionDto> getListActions() {
		return listActions;
	}

	/**
	 * [FilierelmdDroitBean.listActions] Setter 
	 * @author BELBRIK Oualid on : 14 avril 2014  12:04:01
	 * @param listActions the listActions to set
	 */
	public void setListActions(List<RefPermissionDto> listActions) {
		this.listActions = listActions;
	}

	/**
	 * [FilierelmdDroitBean.FilierelmdDroitBean()] Constructor
	 * @author BELBRIK Oualid  on : 14 avril 2014  17:42:12	
	 */
	
	public FilierelmdDroitBean() {
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
	 * [FilierelmdDroitBean.createAllow] Getter 
	 * @author BELBRIK Oualid on : 14 avril 2014  17:42:59
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}

	/**
	 * [FilierelmdDroitBean.createAllow] Setter 
	 * @author BELBRIK Oualid on : 14 avril 2014  17:42:59
	 * @param createAllow the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}

	/**
	 * [FilierelmdDroitBean.editAllow] Getter 
	 * @author BELBRIK Oualid on : 14 avril 2014  17:42:59
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}

	/**
	 * [FilierelmdDroitBean.editAllow] Setter 
	 * @author BELBRIK Oualid on : 14 avril 2014  17:42:59
	 * @param editAllow the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}

	/**
	 * [FilierelmdDroitBean.deleteAllow] Getter 
	 * @author BELBRIK Oualid on : 14 avril 2014  17:42:59
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}

	/**
	 * [FilierelmdDroitBean.deleteAllow] Setter 
	 * @author BELBRIK Oualid on : 14 avril 2014  17:42:59
	 * @param deleteAllow the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}

	/**
	 * [FilierelmdDroitBean.readAllow] Getter 
	 * @author BELBRIK Oualid on : 14 avril 2014  17:42:59
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}

	/**
	 * [FilierelmdDroitBean.readAllow] Setter 
	 * @author BELBRIK Oualid on : 14 avril 2014  17:42:59
	 * @param readAllow the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}

	/**
	 * [FilierelmdDroitBean.crudBean] Getter 
	 * @author BELBRIK Oualid on : 14 avril 2014  11:46:07
	 * @return the crudBean
	 */
	public CrudBean getCrudBean() {
		return crudBean;
	}

	/**
	 * [FilierelmdDroitBean.crudBean] Setter 
	 * @author BELBRIK Oualid on : 14 avril 2014  11:46:07
	 * @param crudBean the crudBean to set
	 */
	public void setCrudBean(CrudBean crudBean) {
		this.crudBean = crudBean;
	}

	/**
	 * [FilierelmdDroitBean.listDroit] Getter 
	 * @author BELBRIK Oualid on : 14 avril 2014  11:46:07
	 * @return the listDroit
	 */
	public List<Boolean> getListDroit() {
		return listDroit;
	}

	/**
	 * [FilierelmdDroitBean.listDroit] Setter 
	 * @author BELBRIK Oualid on : 14 avril 2014  11:46:07
	 * @param listDroit the listDroit to set
	 */
	public void setListDroit(List<Boolean> listDroit) {
		this.listDroit = listDroit;
	}
	
	
	
	
	

}
