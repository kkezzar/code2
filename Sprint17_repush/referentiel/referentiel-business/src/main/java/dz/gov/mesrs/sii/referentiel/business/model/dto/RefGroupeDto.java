/**
 * [dz.gov.mesrs.sii.referentiel.business.model.dto.RefGroupeDto.java] 
 * @author MAKERRI Sofiane on : 6 janv. 2014  15:42:34
 */
package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author MAKERRI Sofiane on : 6 janv. 2014 15:42:34
 */
@XmlRootElement(name = "RefGroupeDto")
public class RefGroupeDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String identifiant;
	private String lcGroupe;
	private String lcGroupeArabe;
	private String llGroupe;
	private String llGroupeArabe;
	private Date dateCreation;
	/**
	 * Current Situation
	 * 
	 */
	private Date dateSituation;
	private String llSituationAr;
	private String llSituationFr;
	private Integer idEtablissement;
	private String idfEtablissement;
	private Integer idFonction;
	private Integer idCompte;

	// Type groupe
	private Integer idTypeGroupe;
	private String llTypeGroupe;

	/**
	 * [RefGroupeDto.RefGroupeDto()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2014 15:42:34
	 */
	public RefGroupeDto() {
      super();
	}

	/**
	 * [RefGroupeDto.RefGroupeDto()] Constructor
	 * @author MAKERRI Sofiane  on : 29 janv. 2015  13:18:08
	 * @param id
	 * @param identifiant
	 * @param lcGroupe
	 * @param lcGroupeArabe
	 * @param llGroupe
	 * @param llGroupeArabe	
	 */
	public RefGroupeDto(Integer id, String identifiant, String lcGroupe,
			String lcGroupeArabe, String llGroupe, String llGroupeArabe) {
          this.id = id;
          this.identifiant = identifiant;
          this.lcGroupe = lcGroupe;
          this.lcGroupeArabe = lcGroupeArabe;
          this.llGroupe = llGroupe;
          this.llGroupeArabe = llGroupeArabe;
	}
	
	
	/**
	 * [RefGroupeDto.RefGroupeDto()] Constructor
	 * @author MAKERRI Sofiane  on : 29 janv. 2015  13:33:55
	 * @param refAffectationDto	
	 */
	public RefGroupeDto(RefAffectationDto refAffectationDto) {
		if (refAffectationDto != null) {
			this.id = refAffectationDto.getIdGroupe();
			this.identifiant = refAffectationDto.getIdentifiantGroupe();
			this.lcGroupe = refAffectationDto.getLcGroupe();
			this.lcGroupeArabe = refAffectationDto.getLcGroupeArabe();
			this.llGroupe = refAffectationDto.getLlGroupe();
			this.llGroupeArabe = refAffectationDto.getLlGroupeArabe();
		}
	}

	/**
	 * [RefGroupeDto.id] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 mai 2014 11:56:33
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [RefGroupeDto.id] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 mai 2014 11:56:33
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * [RefGroupeDto.dateCreation] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 avr. 2014 09:29:46
	 * @return the dateCreation
	 */
	public Date getDateCreation() {
		return dateCreation;
	}

	/**
	 * [RefGroupeDto.dateCreation] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 avr. 2014 09:29:46
	 * @param dateCreation
	 *            the dateCreation to set
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * [RefGroupeDto.identifiant] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2014 15:42:57
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}

	/**
	 * [RefGroupeDto.identifiant] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2014 15:42:57
	 * @param identifiant
	 *            the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	/**
	 * [RefGroupeDto.lcGroupe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2014 15:42:57
	 * @return the lcGroupe
	 */
	public String getLcGroupe() {
		return lcGroupe;
	}

	/**
	 * [RefGroupeDto.lcGroupe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2014 15:42:57
	 * @param lcGroupe
	 *            the lcGroupe to set
	 */
	public void setLcGroupe(String lcGroupe) {
		this.lcGroupe = lcGroupe;
	}

	/**
	 * [RefGroupeDto.lcGroupeArabe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2014 15:42:57
	 * @return the lcGroupeArabe
	 */
	public String getLcGroupeArabe() {
		return lcGroupeArabe;
	}

	/**
	 * [RefGroupeDto.lcGroupeArabe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2014 15:42:57
	 * @param lcGroupeArabe
	 *            the lcGroupeArabe to set
	 */
	public void setLcGroupeArabe(String lcGroupeArabe) {
		this.lcGroupeArabe = lcGroupeArabe;
	}

	/**
	 * [RefGroupeDto.llGroupe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2014 15:42:57
	 * @return the llGroupe
	 */
	public String getLlGroupe() {
		return llGroupe;
	}

	/**
	 * [RefGroupeDto.llGroupe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2014 15:42:57
	 * @param llGroupe
	 *            the llGroupe to set
	 */
	public void setLlGroupe(String llGroupe) {
		this.llGroupe = llGroupe;
	}

	/**
	 * [RefGroupeDto.llGroupeArabe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2014 15:42:57
	 * @return the llGroupeArabe
	 */
	public String getLlGroupeArabe() {
		return llGroupeArabe;
	}

	/**
	 * [RefGroupeDto.llGroupeArabe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2014 15:42:57
	 * @param llGroupeArabe
	 *            the llGroupeArabe to set
	 */
	public void setLlGroupeArabe(String llGroupeArabe) {
		this.llGroupeArabe = llGroupeArabe;
	}

	/**
	 * [RefGroupeDto.dateSituation] Getter
	 * 
	 * @author BELBRIK Oualid on : 3 f�vr. 2014 17:27:17
	 * @return the dateSituation
	 */
	public Date getDateSituation() {
		return dateSituation;
	}

	/**
	 * [RefGroupeDto.dateSituation] Setter
	 * 
	 * @author BELBRIK Oualid on : 3 f�vr. 2014 17:27:17
	 * @param dateSituation
	 *            the dateSituation to set
	 */
	public void setDateSituation(Date dateSituation) {
		this.dateSituation = dateSituation;
	}

	/**
	 * [RefGroupeDto.llSituationAr] Getter
	 * 
	 * @author BELBRIK Oualid on : 3 f�vr. 2014 17:27:17
	 * @return the llSituationAr
	 */
	public String getLlSituationAr() {
		return llSituationAr;
	}

	/**
	 * [RefGroupeDto.llSituationAr] Setter
	 * 
	 * @author BELBRIK Oualid on : 3 f�vr. 2014 17:27:17
	 * @param llSituationAr
	 *            the llSituationAr to set
	 */
	public void setLlSituationAr(String llSituationAr) {
		this.llSituationAr = llSituationAr;
	}

	/**
	 * [RefGroupeDto.llSituationFr] Getter
	 * 
	 * @author BELBRIK Oualid on : 3 f�vr. 2014 17:27:17
	 * @return the llSituationFr
	 */
	public String getLlSituationFr() {
		return llSituationFr;
	}

	/**
	 * [RefGroupeDto.llSituationFr] Setter
	 * 
	 * @author BELBRIK Oualid on : 3 f�vr. 2014 17:27:17
	 * @param llSituationFr
	 *            the llSituationFr to set
	 */
	public void setLlSituationFr(String llSituationFr) {
		this.llSituationFr = llSituationFr;
	}

	/**
	 * [RefGroupeDto.idEtablissement] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 mai 2014 15:21:16
	 * @return the idEtablissement
	 */
	public Integer getIdEtablissement() {
		return idEtablissement;
	}

	/**
	 * [RefGroupeDto.idEtablissement] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 mai 2014 15:21:16
	 * @param idEtablissement
	 *            the idEtablissement to set
	 */
	public void setIdEtablissement(Integer idEtablissement) {
		this.idEtablissement = idEtablissement;
	}

	/**
	 * [RefGroupeDto.idfEtablissement] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 juin 2014 08:03:39
	 * @return the idfEtablissement
	 */
	public String getIdfEtablissement() {
		return idfEtablissement;
	}

	/**
	 * [RefGroupeDto.idfEtablissement] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 juin 2014 08:03:39
	 * @param idfEtablissement
	 *            the idfEtablissement to set
	 */
	public void setIdfEtablissement(String idfEtablissement) {
		this.idfEtablissement = idfEtablissement;
	}

	/**
	 * [RefGroupeDto.idFonction] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 11:31:33
	 * @return the idFonction
	 */
	public Integer getIdFonction() {
		return idFonction;
	}

	/**
	 * [RefGroupeDto.idFonction] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 11:31:33
	 * @param idFonction
	 *            the idFonction to set
	 */
	public void setIdFonction(Integer idFonction) {
		this.idFonction = idFonction;
	}

	/**
	 * [RefGroupeDto.idCompte] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 11:37:37
	 * @return the idCompte
	 */
	public Integer getIdCompte() {
		return idCompte;
	}

	/**
	 * [RefGroupeDto.idCompte] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 11:37:37
	 * @param idCompte
	 *            the idCompte to set
	 */
	public void setIdCompte(Integer idCompte) {
		this.idCompte = idCompte;
	}

	/**
	 * [RefGroupeDto.llTypeGroupe] Getter
	 * 
	 * @author rlaib on : 11 janv. 2015 11:58:38
	 * @return the llTypeGroupe
	 */
	public String getLlTypeGroupe() {
		return llTypeGroupe;
	}

	/**
	 * [RefGroupeDto.llTypeGroupe] Setter
	 * 
	 * @author rlaib on : 11 janv. 2015 11:58:38
	 * @param llTypeGroupe
	 *            the llTypeGroupe to set
	 */
	public void setLlTypeGroupe(String llTypeGroupe) {
		this.llTypeGroupe = llTypeGroupe;
	}

	/**
	 * [RefGroupeDto.idTypeGroupe] Getter
	 * 
	 * @author rlaib on : 11 janv. 2015 16:43:17
	 * @return the idTypeGroupe
	 */
	public Integer getIdTypeGroupe() {
		return idTypeGroupe;
	}

	/**
	 * [RefGroupeDto.idTypeGroupe] Setter
	 * 
	 * @author rlaib on : 11 janv. 2015 16:43:17
	 * @param idTypeGroupe
	 *            the idTypeGroupe to set
	 */
	public void setIdTypeGroupe(Integer idTypeGroupe) {
		this.idTypeGroupe = idTypeGroupe;
	}

}
