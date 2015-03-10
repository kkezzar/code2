/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.cursus.BilanUeDto.java] 
 * @author MAKERRI Sofiane on : 27 oct. 2014  09:37:20
 */
package dz.gov.mesrs.sii.fve.business.model.dto.examen;

import java.io.Serializable;
import java.util.List;

/**
 * @author MAKERRI Sofiane  on : 27 oct. 2014  09:37:20
 */
public class BilanUeDto implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 27 oct. 2014  09:37:29
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private Long bilanSessionId;
	private Integer repartitionUeId;
	private String ueLibelleFr;
	private String ueCode;
	private String ueType;
	private double moyenne;
	private double coefficient;
	private double credit;
	private double creditObtenu;
	private double creditAcquis;
	private String ueNatureLlFr;
	private String ueNatureLlAr;
	private String ueNatureLcFr;
	private String ueNatureLcAr;
	private String ueNatureCode;
	private List<BilanMcDto> bilanMcs;
	private double ueNoteObtention;
	private boolean ueAcquis;
	private double  ueCredit;

	/**
	 * [BilanUeDto.BilanUeDto()] Constructor
	 * @author MAKERRI Sofiane  on : 27 oct. 2014  09:37:20	
	 */
	public BilanUeDto() {
		super();
	}

	/**
	 * [BilanUeDto.id] Getter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:38:44
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * [BilanUeDto.id] Setter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:38:44
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * [BilanUeDto.bilanSessionId] Getter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:38:44
	 * @return the bilanSessionId
	 */
	public Long getBilanSessionId() {
		return bilanSessionId;
	}

	/**
	 * [BilanUeDto.bilanSessionId] Setter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:38:44
	 * @param bilanSessionId the bilanSessionId to set
	 */
	public void setBilanSessionId(Long bilanSessionId) {
		this.bilanSessionId = bilanSessionId;
	}

	

	/**
	 * [BilanUeDto.repartitionUeId] Getter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  10:51:01
	 * @return the repartitionUeId
	 */
	public Integer getRepartitionUeId() {
		return repartitionUeId;
	}

	/**
	 * [BilanUeDto.repartitionUeId] Setter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  10:51:01
	 * @param repartitionUeId the repartitionUeId to set
	 */
	public void setRepartitionUeId(Integer repartitionUeId) {
		this.repartitionUeId = repartitionUeId;
	}

	/**
	 * [BilanUeDto.moyenne] Getter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:38:44
	 * @return the moyenne
	 */
	public double getMoyenne() {
		return moyenne;
	}

	/**
	 * [BilanUeDto.moyenne] Setter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:38:44
	 * @param moyenne the moyenne to set
	 */
	public void setMoyenne(double moyenne) {
		this.moyenne = moyenne;
	}

	/**
	 * [BilanUeDto.coefficient] Getter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:38:44
	 * @return the coefficient
	 */
	public double getCoefficient() {
		return coefficient;
	}

	/**
	 * [BilanUeDto.coefficient] Setter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:38:44
	 * @param coefficient the coefficient to set
	 */
	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}

	/**
	 * [BilanUeDto.credit] Getter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:38:44
	 * @return the credit
	 */
	public double getCredit() {
		return credit;
	}

	/**
	 * [BilanUeDto.credit] Setter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:38:44
	 * @param credit the credit to set
	 */
	public void setCredit(double credit) {
		this.credit = credit;
	}

	/**
	 * [BilanUeDto.bilanMc] Getter 
	 * @author MAKERRI Sofiane on : 28 oct. 2014  11:01:40
	 * @return the bilanMc
	 */
	public List<BilanMcDto> getBilanMcs() {
		return bilanMcs;
	}

	/**
	 * [BilanUeDto.bilanMc] Setter 
	 * @author MAKERRI Sofiane on : 28 oct. 2014  11:01:40
	 * @param bilanMc the bilanMc to set
	 */
	public void setBilanMcs(List<BilanMcDto> bilanMcs) {
		this.bilanMcs = bilanMcs;
	}

	/**
	 * [BilanUeDto.ueLibelleFr] Getter 
	 * @author MAKERRI Sofiane on : 28 oct. 2014  13:01:04
	 * @return the ueLibelleFr
	 */
	public String getUeLibelleFr() {
		return ueLibelleFr;
	}

	/**
	 * [BilanUeDto.ueLibelleFr] Setter 
	 * @author MAKERRI Sofiane on : 28 oct. 2014  13:01:04
	 * @param ueLibelleFr the ueLibelleFr to set
	 */
	public void setUeLibelleFr(String ueLibelleFr) {
		this.ueLibelleFr = ueLibelleFr;
	}

	/**
	 * [BilanUeDto.ueCode] Getter 
	 * @author MAKERRI Sofiane on : 28 oct. 2014  13:01:04
	 * @return the ueCode
	 */
	public String getUeCode() {
		return ueCode;
	}

	/**
	 * [BilanUeDto.ueCode] Setter 
	 * @author MAKERRI Sofiane on : 28 oct. 2014  13:01:04
	 * @param ueCode the ueCode to set
	 */
	public void setUeCode(String ueCode) {
		this.ueCode = ueCode;
	}

	/**
	 * [BilanUeDto.ueType] Getter 
	 * @author MAKERRI Sofiane on : 28 oct. 2014  13:01:04
	 * @return the ueType
	 */
	public String getUeType() {
		return ueType;
	}

	/**
	 * [BilanUeDto.ueType] Setter 
	 * @author MAKERRI Sofiane on : 28 oct. 2014  13:01:04
	 * @param ueType the ueType to set
	 */
	public void setUeType(String ueType) {
		this.ueType = ueType;
	}

	/**
	 * [BilanUeDto.ueNatureLlFr] Getter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  09:54:32
	 * @return the ueNatureLlFr
	 */
	public String getUeNatureLlFr() {
		return ueNatureLlFr;
	}

	/**
	 * [BilanUeDto.ueNatureLlFr] Setter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  09:54:32
	 * @param ueNatureLlFr the ueNatureLlFr to set
	 */
	public void setUeNatureLlFr(String ueNatureLlFr) {
		this.ueNatureLlFr = ueNatureLlFr;
	}

	/**
	 * [BilanUeDto.ueNatureLlAr] Getter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  09:54:32
	 * @return the ueNatureLlAr
	 */
	public String getUeNatureLlAr() {
		return ueNatureLlAr;
	}

	/**
	 * [BilanUeDto.ueNatureLlAr] Setter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  09:54:32
	 * @param ueNatureLlAr the ueNatureLlAr to set
	 */
	public void setUeNatureLlAr(String ueNatureLlAr) {
		this.ueNatureLlAr = ueNatureLlAr;
	}

	/**
	 * [BilanUeDto.ueNatureLcFr] Getter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  09:54:32
	 * @return the ueNatureLcFr
	 */
	public String getUeNatureLcFr() {
		return ueNatureLcFr;
	}

	/**
	 * [BilanUeDto.ueNatureLcFr] Setter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  09:54:32
	 * @param ueNatureLcFr the ueNatureLcFr to set
	 */
	public void setUeNatureLcFr(String ueNatureLcFr) {
		this.ueNatureLcFr = ueNatureLcFr;
	}

	/**
	 * [BilanUeDto.ueNatureLcAr] Getter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  09:54:32
	 * @return the ueNatureLcAr
	 */
	public String getUeNatureLcAr() {
		return ueNatureLcAr;
	}

	/**
	 * [BilanUeDto.ueNatureLcAr] Setter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  09:54:32
	 * @param ueNatureLcAr the ueNatureLcAr to set
	 */
	public void setUeNatureLcAr(String ueNatureLcAr) {
		this.ueNatureLcAr = ueNatureLcAr;
	}

	/**
	 * [BilanUeDto.ueNatureCode] Getter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  09:54:32
	 * @return the ueNatureCode
	 */
	public String getUeNatureCode() {
		return ueNatureCode;
	}

	/**
	 * [BilanUeDto.ueNatureCode] Setter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  09:54:32
	 * @param ueNatureCode the ueNatureCode to set
	 */
	public void setUeNatureCode(String ueNatureCode) {
		this.ueNatureCode = ueNatureCode;
	}

	/**
	 * [BilanUeDto.creditObtenu] Getter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  10:20:56
	 * @return the creditObtenu
	 */
	public double getCreditObtenu() {
		return creditObtenu;
	}

	/**
	 * [BilanUeDto.creditObtenu] Setter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  10:20:56
	 * @param creditObtenu the creditObtenu to set
	 */
	public void setCreditObtenu(double creditObtenu) {
		this.creditObtenu = creditObtenu;
	}

	/**
	 * [BilanUeDto.ueNoteObtention] Getter 
	 * @author MAKERRI Sofiane on : 17 déc. 2014  17:01:08
	 * @return the ueNoteObtention
	 */
	public double getUeNoteObtention() {
		return ueNoteObtention;
	}

	/**
	 * [BilanUeDto.ueNoteObtention] Setter 
	 * @author MAKERRI Sofiane on : 17 déc. 2014  17:01:08
	 * @param ueNoteObtention the ueNoteObtention to set
	 */
	public void setUeNoteObtention(double ueNoteObtention) {
		this.ueNoteObtention = ueNoteObtention;
	}

	/**
	 * [BilanUeDto.ueAcquis] Getter 
	 * @author MAKERRI Sofiane on : 21 déc. 2014  12:39:21
	 * @return the ueAcquis
	 */
	public boolean isUeAcquis() {
		//ueAcquis = (moyenne >=  ueNoteObtention ? true : false);
		return ueAcquis;
	}

	/**
	 * [BilanUeDto.ueAcquis] Setter 
	 * @author MAKERRI Sofiane on : 21 déc. 2014  12:39:21
	 * @param ueAcquis the ueAcquis to set
	 */
	public void setUeAcquis(boolean ueAcquis) {
		this.ueAcquis = ueAcquis;
	}



	/**
	 * [BilanUeDto.ueCredit] Getter 
	 * @author MAKERRI Sofiane on : 14 janv. 2015  11:52:33
	 * @return the ueCredit
	 */
	public double getUeCredit() {
		return ueCredit;
	}

	/**
	 * [BilanUeDto.ueCredit] Setter 
	 * @author MAKERRI Sofiane on : 14 janv. 2015  11:52:33
	 * @param ueCredit the ueCredit to set
	 */
	public void setUeCredit(double ueCredit) {
		this.ueCredit = ueCredit;
	}

	/**
	 * [BilanUeDto.creditAcquis] Getter 
	 * @author MAKERRI Sofiane on : 15 janv. 2015  15:57:17
	 * @return the creditAcquis
	 */
	public double getCreditAcquis() {
		return creditAcquis;
	}

	/**
	 * [BilanUeDto.creditAcquis] Setter 
	 * @author MAKERRI Sofiane on : 15 janv. 2015  15:57:17
	 * @param creditAcquis the creditAcquis to set
	 */
	public void setCreditAcquis(double creditAcquis) {
		this.creditAcquis = creditAcquis;
	}
	
	

}
