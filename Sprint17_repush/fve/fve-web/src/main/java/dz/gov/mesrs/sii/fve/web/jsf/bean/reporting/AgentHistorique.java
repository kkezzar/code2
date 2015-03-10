/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.reporting.AgentHistorique.java] 
 * @author MAKERRI Sofiane on : 22 juin 2014  11:08:57
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.reporting;

import java.io.Serializable;
import java.util.List;

/**
 * @author MAKERRI Sofiane on : 22 juin 2014 11:08:57
 */
public class AgentHistorique implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 11:09:02
	 */
	private static final long serialVersionUID = 1L;
	private String nomLtIndividu;
	private String prenomLtIndividu;
	private String roleLibelleLongFr;
	private String roleLibelleLongAr;
	private String roleLibelleCourtFr;
	private String roleLibelleCourtAr;
	private String nomCompte;
	private int actionNumber;
	private List<HistoriqueDetail> historiques;

	/**
	 * [AgentHistorique.AgentHistorique()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 11:08:57
	 */
	public AgentHistorique() {
		super();
	}

	/**
	 * [AgentHistorique.nomLtIndividu] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 11:11:15
	 * @return the nomLtIndividu
	 */
	public String getNomLtIndividu() {
		return nomLtIndividu;
	}

	/**
	 * [AgentHistorique.nomLtIndividu] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 11:11:15
	 * @param nomLtIndividu
	 *            the nomLtIndividu to set
	 */
	public void setNomLtIndividu(String nomLtIndividu) {
		this.nomLtIndividu = nomLtIndividu;
	}

	/**
	 * [AgentHistorique.prenomLtIndividu] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 11:11:15
	 * @return the prenomLtIndividu
	 */
	public String getPrenomLtIndividu() {
		return prenomLtIndividu;
	}

	/**
	 * [AgentHistorique.prenomLtIndividu] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 11:11:15
	 * @param prenomLtIndividu
	 *            the prenomLtIndividu to set
	 */
	public void setPrenomLtIndividu(String prenomLtIndividu) {
		this.prenomLtIndividu = prenomLtIndividu;
	}

	/**
	 * [AgentHistorique.roleLibelleLongFr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 11:11:15
	 * @return the roleLibelleLongFr
	 */
	public String getRoleLibelleLongFr() {
		return roleLibelleLongFr;
	}

	/**
	 * [AgentHistorique.roleLibelleLongFr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 11:11:15
	 * @param roleLibelleLongFr
	 *            the roleLibelleLongFr to set
	 */
	public void setRoleLibelleLongFr(String roleLibelleLongFr) {
		this.roleLibelleLongFr = roleLibelleLongFr;
	}

	/**
	 * [AgentHistorique.roleLibelleLongAr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 11:11:15
	 * @return the roleLibelleLongAr
	 */
	public String getRoleLibelleLongAr() {
		return roleLibelleLongAr;
	}

	/**
	 * [AgentHistorique.roleLibelleLongAr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 11:11:15
	 * @param roleLibelleLongAr
	 *            the roleLibelleLongAr to set
	 */
	public void setRoleLibelleLongAr(String roleLibelleLongAr) {
		this.roleLibelleLongAr = roleLibelleLongAr;
	}

	/**
	 * [AgentHistorique.roleLibelleCourtFr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 11:11:15
	 * @return the roleLibelleCourtFr
	 */
	public String getRoleLibelleCourtFr() {
		return roleLibelleCourtFr;
	}

	/**
	 * [AgentHistorique.roleLibelleCourtFr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 11:11:15
	 * @param roleLibelleCourtFr
	 *            the roleLibelleCourtFr to set
	 */
	public void setRoleLibelleCourtFr(String roleLibelleCourtFr) {
		this.roleLibelleCourtFr = roleLibelleCourtFr;
	}

	/**
	 * [AgentHistorique.roleLibelleCourtAr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 11:11:15
	 * @return the roleLibelleCourtAr
	 */
	public String getRoleLibelleCourtAr() {
		return roleLibelleCourtAr;
	}

	/**
	 * [AgentHistorique.roleLibelleCourtAr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 11:11:15
	 * @param roleLibelleCourtAr
	 *            the roleLibelleCourtAr to set
	 */
	public void setRoleLibelleCourtAr(String roleLibelleCourtAr) {
		this.roleLibelleCourtAr = roleLibelleCourtAr;
	}

	/**
	 * [AgentHistorique.historiques] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 11:12:13
	 * @return the historiques
	 */
	public List<HistoriqueDetail> getHistoriques() {
		return historiques;
	}

	/**
	 * [AgentHistorique.historiques] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 11:12:13
	 * @param historiques
	 *            the historiques to set
	 */
	public void setHistoriques(List<HistoriqueDetail> historiques) {
		this.historiques = historiques;
	}

	/**
	 * [AgentHistorique.nomCompte] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 11:46:39
	 * @return the nomCompte
	 */
	public String getNomCompte() {
		return nomCompte;
	}

	/**
	 * [AgentHistorique.nomCompte] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 11:46:39
	 * @param nomCompte
	 *            the nomCompte to set
	 */
	public void setNomCompte(String nomCompte) {
		this.nomCompte = nomCompte;
	}

	/**
	 * [AgentHistorique.actionNumber] Getter 
	 * @author MAKERRI Sofiane on : 23 juin 2014  17:06:43
	 * @return the actionNumber
	 */
	public int getActionNumber() {
		return actionNumber;
	}

	/**
	 * [AgentHistorique.actionNumber] Setter 
	 * @author MAKERRI Sofiane on : 23 juin 2014  17:06:43
	 * @param actionNumber the actionNumber to set
	 */
	public void setActionNumber(int actionNumber) {
		this.actionNumber = actionNumber;
	}
	
	

}
