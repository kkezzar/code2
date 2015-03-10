/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.SessionValues.java] 
 * @author MAKERRI Sofiane on : 14 avr. 2014  16:03:39
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.common;

/**
 * @author MAKERRI Sofiane  on : 14 avr. 2014  16:03:39
 */
public class SessionValues {

	/**
	 * [SessionValues.SessionValues()] Constructor
	 * @author MAKERRI Sofiane  on : 14 avr. 2014  16:03:39	
	 */
	private static Integer idEtablissement;
	private static String idfEtablissement;
	private static String lcLatinEtablissement;
	private static Integer idCompte;
	
	public SessionValues() {
		super();
	}

	/**
	 * [SessionValues.idfEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 14 avr. 2014  16:04:30
	 * @return the idfEtablissement
	 */
	public static String getIdfEtablissement() {
		return idfEtablissement;
	}

	/**
	 * [SessionValues.idfEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 14 avr. 2014  16:04:30
	 * @param idfEtablissement the idfEtablissement to set
	 */
	public static void setIdfEtablissement(String idfEtablissement) {
		SessionValues.idfEtablissement = idfEtablissement;
	}

	/**
	 * [SessionValues.lcLatinEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 15 avr. 2014  10:54:49
	 * @return the lcLatinEtablissement
	 */
	public static String getLcLatinEtablissement() {
		return lcLatinEtablissement;
	}

	/**
	 * [SessionValues.lcLatinEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 15 avr. 2014  10:54:49
	 * @param lcLatinEtablissement the lcLatinEtablissement to set
	 */
	public static void setLcLatinEtablissement(String lcLatinEtablissement) {
		SessionValues.lcLatinEtablissement = lcLatinEtablissement;
	}

	/**
	 * [SessionValues.idEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  15:12:44
	 * @return the idEtablissement
	 */
	public static Integer getIdEtablissement() {
		return idEtablissement;
	}

	/**
	 * [SessionValues.idEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  15:12:44
	 * @param idEtablissement the idEtablissement to set
	 */
	public static void setIdEtablissement(Integer idEtablissement) {
		SessionValues.idEtablissement = idEtablissement;
	}

	/**
	 * [SessionValues.idCompte] Getter 
	 * @author MAKERRI Sofiane on : 18 juin 2014  11:40:52
	 * @return the idCompte
	 */
	public static Integer getIdCompte() {
		return idCompte;
	}

	/**
	 * [SessionValues.idCompte] Setter 
	 * @author MAKERRI Sofiane on : 18 juin 2014  11:40:52
	 * @param idCompte the idCompte to set
	 */
	public static void setIdCompte(Integer idCompte) {
		SessionValues.idCompte = idCompte;
	}
	
	
	
	

}
