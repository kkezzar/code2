/**
 * [dz.gov.mesrs.sii.commons.business.util.NoteMoyenneAp.java] 
 * @author MAKERRI Sofiane on : 14 oct. 2014  09:53:25
 */
package dz.gov.mesrs.sii.commons.data.util;

import java.io.Serializable;

/**
 * @author MAKERRI Sofiane on : 14 oct. 2014 09:53:25
 */
public class NoteMoyenneAp implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 09:53:37
	 */
	private static final long serialVersionUID = 1L;
	private Integer affGroupePedagogiqueId;
	private Integer apId;
	private String apCode;
	private Double noteSum;
	private Double coefficientSum;
	private Double moyenne;

	/**
	 * [NoteMoyenneAp.NoteMoyenneAp()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 09:53:25
	 */
	public NoteMoyenneAp() {
		super();
	}

	public NoteMoyenneAp(Integer affGroupePedagogiqueId, Integer apId,
			Double noteSum, Double coefficientSum) {
		super();
		this.affGroupePedagogiqueId = affGroupePedagogiqueId;
		this.apId = apId;
		this.noteSum = noteSum;
		this.coefficientSum = coefficientSum;
	}

	/**
	 * [NoteMoyenneAp.affGroupePedagogiqueId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 12:48:01
	 * @return the affGroupePedagogiqueId
	 */
	public Integer getAffGroupePedagogiqueId() {
		return affGroupePedagogiqueId;
	}

	/**
	 * [NoteMoyenneAp.affGroupePedagogiqueId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 12:48:01
	 * @param affGroupePedagogiqueId
	 *            the affGroupePedagogiqueId to set
	 */
	public void setAffGroupePedagogiqueId(Integer affGroupePedagogiqueId) {
		this.affGroupePedagogiqueId = affGroupePedagogiqueId;
	}

	/**
	 * [NoteMoyenneAp.apId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 09:55:16
	 * @return the apId
	 */
	public Integer getApId() {
		return apId;
	}

	/**
	 * [NoteMoyenneAp.apId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 09:55:16
	 * @param apId
	 *            the apId to set
	 */
	public void setApId(Integer apId) {
		this.apId = apId;
	}

	/**
	 * [NoteMoyenneAp.noteSum] Getter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 12:02:23
	 * @return the noteSum
	 */
	public Double getNoteSum() {
		return noteSum;
	}

	/**
	 * [NoteMoyenneAp.noteSum] Setter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 12:02:23
	 * @param noteSum
	 *            the noteSum to set
	 */
	public void setNoteSum(Double noteSum) {
		this.noteSum = noteSum;
	}

	/**
	 * [NoteMoyenneAp.coefficientSum] Getter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 12:02:23
	 * @return the coefficientSum
	 */
	public Double getCoefficientSum() {
		return coefficientSum;
	}

	/**
	 * [NoteMoyenneAp.coefficientSum] Setter
	 * 
	 * @author MAKERRI Sofiane on : 14 oct. 2014 12:02:23
	 * @param coefficientSum
	 *            the coefficientSum to set
	 */
	public void setCoefficientSum(Double coefficientSum) {
		this.coefficientSum = coefficientSum;
	}

	/**
	 * [NoteMoyenneAp.apCode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 09:54:44
	 * @return the apCode
	 */
	public String getApCode() {
		return apCode;
	}

	/**
	 * [NoteMoyenneAp.apCode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 09:54:45
	 * @param apCode
	 *            the apCode to set
	 */
	public void setApCode(String apCode) {
		this.apCode = apCode;
	}

	/**
	 * [NoteMoyenneAp.moyenne] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 09:54:45
	 * @return the moyenne
	 */
	public Double getMoyenne() {
		moyenne = 0.0;
		if (noteSum != null && coefficientSum != null && coefficientSum != 0) {
			moyenne = noteSum / coefficientSum;
			moyenne = Math.round(moyenne * Math.pow(10, 2)) / Math.pow(10, 2);
		}
		return moyenne;
	}

	/**
	 * [NoteMoyenneAp.moyenne] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 09:54:45
	 * @param moyenne
	 *            the moyenne to set
	 */
	public void setMoyenne(Double moyenne) {
		this.moyenne = moyenne;
	}

}
