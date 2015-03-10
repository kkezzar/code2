/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.contrat.ContratDroitBean.java] 
 * @author MAKERRI Sofiane on : 4 mars 2014  17:40:41
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author MAKERRI Sofiane  on : 4 mars 2014  17:40:41
 */
@ManagedBean(name = "sessionDroitBean")
@SessionScoped
public class SessionDroitBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean createAllow = true;
	private boolean editAllow = true;
	private boolean deleteAllow = true;
	private boolean readAllow = true;
	
	
	/**
	 * [ExamenNoteJuryDroitBean.ExamenNoteJuryDroitBean()] Constructor
	 * @author MAKERRI Sofiane  on : 29 sept. 2014  16:17:05	
	 */
	public SessionDroitBean() {
		super();
	}
	
	@PostConstruct
	public void init() {
	}

	
	/**
	 * [ExamenNoteJuryDroitBean.isCreateAllow] Method 
	 * @author MAKERRI Sofiane  on : 29 sept. 2014  16:17:13
	 * @return
	 */
	public boolean getCreateAllow() {
		return createAllow;
	}

	
	/**
	 * [ExamenNoteJuryDroitBean.setCreateAllow] Method 
	 * @author MAKERRI Sofiane  on : 29 sept. 2014  16:17:23
	 * @param createAllow
	 */
	public void setCreateAllow(boolean createAllow) {
		this.createAllow = createAllow;
	}

	
	/**
	 * [ExamenNoteJuryDroitBean.getEditAllow] Method 
	 * @author MAKERRI Sofiane  on : 29 sept. 2014  16:17:32
	 * @return
	 */
	public boolean getEditAllow() {
		return editAllow;
	}

	
	/**
	 * [ExamenNoteJuryDroitBean.setEditAllow] Method 
	 * @author MAKERRI Sofiane  on : 29 sept. 2014  16:17:37
	 * @param editAllow
	 */
	public void setEditAllow(boolean editAllow) {
		this.editAllow = editAllow;
	}

	
	/**
	 * [ExamenNoteJuryDroitBean.getDeleteAllow] Method 
	 * @author MAKERRI Sofiane  on : 29 sept. 2014  16:17:51
	 * @return
	 */
	public boolean getDeleteAllow() {
		return deleteAllow;
	}

	/**
	 * [ExamenNoteJuryDroitBean.setDeleteAllow] Method 
	 * @author MAKERRI Sofiane  on : 29 sept. 2014  16:17:57
	 * @param deleteAllow
	 */
	public void setDeleteAllow(boolean deleteAllow) {
		this.deleteAllow = deleteAllow;
	}

	
	/**
	 * [ExamenNoteJuryDroitBean.getReadAllow] Method 
	 * @author MAKERRI Sofiane  on : 29 sept. 2014  16:18:04
	 * @return
	 */
	public boolean getReadAllow() {
		return readAllow;
	}

	
	/**
	 * [ExamenNoteJuryDroitBean.setReadAllow] Method 
	 * @author MAKERRI Sofiane  on : 29 sept. 2014  16:18:22
	 * @param readAllow
	 */
	public void setReadAllow(boolean readAllow) {
		this.readAllow = readAllow;
	}

	
	
	

}
