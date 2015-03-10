package dz.gov.mesrs.sii.refenrentiel.jsf.beans.filierelmd;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "filiereaffectationCrudBean")
@SessionScoped
public class FiliereAffectationCrudBean implements Serializable {

	/**
	 * serialVersionUID 
	 * @author BELBRIK Oualid  on : 20 aout 2014  16:05:58
	 */
	private static final long serialVersionUID = 1L;
	private boolean createAllow;
	private boolean editAllow;
	private boolean deleteAllow;
	private boolean readAllow;

	/**
	 * @author BELBRIK Oualid  on : 20 avril 2014  16:05:35	
	 */
	public FiliereAffectationCrudBean() {
		super();
	}

	/**
	 * @author BELBRIK Oualid on : 20 avril 2014  16:06:19
	 * @return the createAllow
	 */
	public boolean isCreateAllow() {
		return createAllow;
	}

	/** 
	 * @author BELBRIK Oualid on : 20 avril 2014  16:06:19
	 * @param createAllow the createAllow to set
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}

	/**
	 * @author BELBRIK Oualid on : 20 avril 2014  16:06:19
	 * @return the editAllow
	 */
	public boolean isEditAllow() {
		return editAllow;
	}

	/**
	 * @author BELBRIK Oualid on : 20 avril 2014  16:06:19
	 * @param editAllow the editAllow to set
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}

	/**
	 * @author BELBRIK Oualid on : 20 avril 2014  16:06:19
	 * @return the deleteAllow
	 */
	public boolean isDeleteAllow() {
		return deleteAllow;
	}

	/**
	 * @author BELBRIK Oualid on : 20 avril 2014  16:06:19
	 * @param deleteAllow the deleteAllow to set
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}

	/**
	 * @author BELBRIK Oualid on : 20 avril 2014  16:06:19
	 * @return the readAllow
	 */
	public boolean isReadAllow() {
		return readAllow;
	}

	/**
	 * @author BELBRIK Oualid on : 20 avril 2014  16:06:19
	 * @param readAllow the readAllow to set
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}
	
	

}
