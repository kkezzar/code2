/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.reporting.ReportDomaineModel.java] 
 * @author MAKERRI Sofiane on : 25 juin 2014  10:18:11
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.reporting;

import java.io.Serializable;

/**
 * @author MAKERRI Sofiane  on : 25 juin 2014  10:18:11
 */
public class ReportDomaineModel implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 25 juin 2014  10:18:28
	 */
	private static final long serialVersionUID = 1L;
	private String domaineFiliere;
	private String codeDomaineFiliere;
	private Integer nbrBachelier;
	private Integer nbrInscrit;
	private Integer nbrNonInscrit;

	/**
	 * [ReportDomaineModel.ReportDomaineModel()] Constructor
	 * @author MAKERRI Sofiane  on : 25 juin 2014  10:18:11	
	 */
	public ReportDomaineModel() {
		super();
	}

	/**
	 * [ReportDomaineModel.domaineFiliere] Getter 
	 * @author MAKERRI Sofiane on : 25 juin 2014  10:20:31
	 * @return the domaineFiliere
	 */
	public String getDomaineFiliere() {
		return domaineFiliere;
	}

	/**
	 * [ReportDomaineModel.domaineFiliere] Setter 
	 * @author MAKERRI Sofiane on : 25 juin 2014  10:20:31
	 * @param domaineFiliere the domaineFiliere to set
	 */
	public void setDomaineFiliere(String domaineFiliere) {
		this.domaineFiliere = domaineFiliere;
	}

	/**
	 * [ReportDomaineModel.nbrBachelier] Getter 
	 * @author MAKERRI Sofiane on : 25 juin 2014  10:20:31
	 * @return the nbrBachelier
	 */
	public Integer getNbrBachelier() {
		return nbrBachelier;
	}

	/**
	 * [ReportDomaineModel.nbrBachelier] Setter 
	 * @author MAKERRI Sofiane on : 25 juin 2014  10:20:31
	 * @param nbrBachelier the nbrBachelier to set
	 */
	public void setNbrBachelier(Integer nbrBachelier) {
		this.nbrBachelier = nbrBachelier;
	}

	/**
	 * [ReportDomaineModel.nbrInscrit] Getter 
	 * @author MAKERRI Sofiane on : 25 juin 2014  10:20:31
	 * @return the nbrInscrit
	 */
	public Integer getNbrInscrit() {
		return nbrInscrit;
	}

	/**
	 * [ReportDomaineModel.nbrInscrit] Setter 
	 * @author MAKERRI Sofiane on : 25 juin 2014  10:20:31
	 * @param nbrInscrit the nbrInscrit to set
	 */
	public void setNbrInscrit(Integer nbrInscrit) {
		this.nbrInscrit = nbrInscrit;
	}

	/**
	 * [ReportDomaineModel.nbrNonInscrit] Getter 
	 * @author MAKERRI Sofiane on : 25 juin 2014  10:20:31
	 * @return the nbrNonInscrit
	 */
	public Integer getNbrNonInscrit() {
		return nbrNonInscrit;
	}

	/**
	 * [ReportDomaineModel.nbrNonInscrit] Setter 
	 * @author MAKERRI Sofiane on : 25 juin 2014  10:20:31
	 * @param nbrNonInscrit the nbrNonInscrit to set
	 */
	public void setNbrNonInscrit(Integer nbrNonInscrit) {
		this.nbrNonInscrit = nbrNonInscrit;
	}

	/**
	 * [ReportDomaineModel.codeDomaineFiliere] Getter 
	 * @author MAKERRI Sofiane on : 25 juin 2014  12:59:26
	 * @return the codeDomaineFiliere
	 */
	public String getCodeDomaineFiliere() {
		return codeDomaineFiliere;
	}

	/**
	 * [ReportDomaineModel.codeDomaineFiliere] Setter 
	 * @author MAKERRI Sofiane on : 25 juin 2014  12:59:26
	 * @param codeDomaineFiliere the codeDomaineFiliere to set
	 */
	public void setCodeDomaineFiliere(String codeDomaineFiliere) {
		this.codeDomaineFiliere = codeDomaineFiliere;
	}
	
	

}
