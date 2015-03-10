/**
 * 
 */
package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;
/**
 * @author jbeldi
 *
 */
@XmlRootElement(name="RefIndividuDto")
public class RefIndividuDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String identifiant;
	private Integer id;
	private String qualite;
	//private Nomenclature nomenclatureBySituationServiceNational;
	private Integer serviceNatId;
	private String serviceNatLibelleLongFr;
	private String serviceNatLibelleLongAr;
	private String serviceNatLibelleCourtFr;
	private String serviceNatLibelleCourtAr;
	private String serviceNatCode;
	//private Nomenclature nomenclatureByGroupeSanguin;
	private Integer groupeSanguinId;
	private String groupeSanguinLibelleLongFr;
	private String groupeSanguinLibelleLongAr;
	private String groupeSanguinLibelleCourtFr;
	private String groupeSanguinLibelleCourtAr;
	private String groupeSanguinCode;
	//private Nomenclature nomenclatureBySituationFamiliale;
	private Integer situationFamilialeId;
	private String situationFamilialeLibelleLongFr;
	private String situationFamilialeLibelleLongAr;
	private String situationFamilialeLibelleCourtFr;
	private String situationFamilialeLibelleCourtAr;
	private String situationFamilialeCode;
	//private Nomenclature nomenclatureByCivilite;
	private Integer civiliteId;
	private String civiliteLibelleLongFr;
	private String civiliteLibelleLongAr;
	private String civiliteLibelleCourtFr;
	private String civiliteLibelleCourtAr;
	private String civiliteCode;
	//private Nomenclature nomenclatureByNationalite;
	private Integer nationaliteId;
	private String nationaliteLibelleLongFr;
	private String nationaliteLibelleLongAr;
	private String nationaliteLibelleCourtFr;
	private String nationaliteLibelleCourtAr;
	private String nationaliteCode;
	
	//private Nomenclature nomenclatureByTypeIndividu;
	private Integer typeIndividuId;
	private String  typeIndividuLibelleLongFr;
	private String  typeIndividuLibelleLongAr;
	private String  typeIndividuLibelleCourtFr;
	private String  typeIndividuLibelleCourtAr;
	private String  typeIndividuCode;
		
		
	private Date dateNaissance;
	private String nomArabe;
	private String nomJeuneFilleArabe;
	private String nomJeuneFilleLatin;
	private String nomLatin;
	private String nomMereArabe;
	private String nomMereLatin;
	private String prenomArabe;
	private String prenomLatin;
	private String prenomMereArabe;
	private String prenomMereLatin;
	private String prenomPereArabe;
	private String prenomPereLatin;
	private Boolean presume;
	
	/**
	 * Current Situation
	 * 
	 */
	private Date dateSituation;
	private String llSituationAr;
	private String llSituationFr;
	private String lieuNaissance;

	
	public RefIndividuDto() {
		super();
	}


	public RefIndividuDto(RefIndividu refIndividu) {
		this.identifiant = refIndividu.getIdentifiant();
		this.nomArabe = refIndividu.getNomArabe();
		this.nomLatin = refIndividu.getNomLatin();
		this.prenomArabe = refIndividu.getPrenomArabe();
		this.prenomLatin = refIndividu.getPrenomLatin();
	}
	public RefIndividuDto(String identifiant, 
			 Date dateNaissance,
			String nomArabe, String nomJeuneFilleArabe,
			String nomJeuneFilleLatin, String nomLatin, String nomMereArabe,
			String nomMereLatin, String prenomArabe, String prenomLatin,
			String prenomMereArabe, String prenomMereLatin,
			String prenomPereArabe, String prenomPereLatin, Boolean presume
			) {
		this.identifiant = identifiant;
		this.dateNaissance = dateNaissance;
		this.nomArabe = nomArabe;
		this.nomJeuneFilleArabe = nomJeuneFilleArabe;
		this.nomJeuneFilleLatin = nomJeuneFilleLatin;
		this.nomLatin = nomLatin;
		this.nomMereArabe = nomMereArabe;
		this.nomMereLatin = nomMereLatin;
		this.prenomArabe = prenomArabe;
		this.prenomLatin = prenomLatin;
		this.prenomMereArabe = prenomMereArabe;
		this.prenomMereLatin = prenomMereLatin;
		this.prenomPereArabe = prenomPereArabe;
		this.prenomPereLatin = prenomPereLatin;
		this.presume = presume;
		
	}
	

	/**
	 * [RefIndividuDto.id] Getter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  11:03:45
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * [RefIndividuDto.id] Setter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  11:03:45
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}


	public String getIdentifiant() {
		return this.identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}


	
	

	
	public Date getDateNaissance() {
		return this.dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getNomArabe() {
		return this.nomArabe;
	}

	public void setNomArabe(String nomArabe) {
		this.nomArabe = nomArabe;
	}

	public String getNomJeuneFilleArabe() {
		return this.nomJeuneFilleArabe;
	}

	public void setNomJeuneFilleArabe(String nomJeuneFilleArabe) {
		this.nomJeuneFilleArabe = nomJeuneFilleArabe;
	}

	public String getNomJeuneFilleLatin() {
		return this.nomJeuneFilleLatin;
	}

	public void setNomJeuneFilleLatin(String nomJeuneFilleLatin) {
		this.nomJeuneFilleLatin = nomJeuneFilleLatin;
	}

	public String getNomLatin() {
		return this.nomLatin;
	}

	public void setNomLatin(String nomLatin) {
		this.nomLatin = nomLatin;
	}

	public String getNomMereArabe() {
		return this.nomMereArabe;
	}

	public void setNomMereArabe(String nomMereArabe) {
		this.nomMereArabe = nomMereArabe;
	}

	public String getNomMereLatin() {
		return this.nomMereLatin;
	}

	public void setNomMereLatin(String nomMereLatin) {
		this.nomMereLatin = nomMereLatin;
	}

	public String getPrenomArabe() {
		return this.prenomArabe;
	}

	public void setPrenomArabe(String prenomArabe) {
		this.prenomArabe = prenomArabe;
	}

	public String getPrenomLatin() {
		return this.prenomLatin;
	}

	public void setPrenomLatin(String prenomLatin) {
		this.prenomLatin = prenomLatin;
	}

	public String getPrenomMereArabe() {
		return this.prenomMereArabe;
	}

	public void setPrenomMereArabe(String prenomMereArabe) {
		this.prenomMereArabe = prenomMereArabe;
	}

	public String getPrenomMereLatin() {
		return this.prenomMereLatin;
	}

	public void setPrenomMereLatin(String prenomMereLatin) {
		this.prenomMereLatin = prenomMereLatin;
	}

	public String getPrenomPereArabe() {
		return this.prenomPereArabe;
	}

	public void setPrenomPereArabe(String prenomPereArabe) {
		this.prenomPereArabe = prenomPereArabe;
	}

	public String getPrenomPereLatin() {
		return this.prenomPereLatin;
	}

	public void setPrenomPereLatin(String prenomPereLatin) {
		this.prenomPereLatin = prenomPereLatin;
	}

	public Boolean getPresume() {
		return this.presume;
	}

	public void setPresume(Boolean presume) {
		this.presume = presume;
	}

	
		/**
	 * @return the dateSituation
	 */
	public Date getDateSituation() {
		return dateSituation;
	}

	/**
	 * @param dateSituation the dateSituation to set
	 */
	public void setDateSituation(Date dateSituation) {
		this.dateSituation = dateSituation;
	}

	/**
	 * @return the llSituationAr
	 */
	public String getLlSituationAr() {
		return llSituationAr;
	}

	/**
	 * @param llSituationAr the llSituationAr to set
	 */
	public void setLlSituationAr(String llSituationAr) {
		this.llSituationAr = llSituationAr;
	}

	/**
	 * @return the llSituationFr
	 */
	public String getLlSituationFr() {
		return llSituationFr;
	}

	/**
	 * @param llSituationFr the llSituationFr to set
	 */
	public void setLlSituationFr(String llSituationFr) {
		this.llSituationFr = llSituationFr;
	}

	



	/**
	 * [RefIndividuDto.serviceNatId] Getter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @return the serviceNatId
	 */
	public Integer getServiceNatId() {
		return serviceNatId;
	}


	/**
	 * [RefIndividuDto.serviceNatId] Setter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @param serviceNatId the serviceNatId to set
	 */
	public void setServiceNatId(Integer serviceNatId) {
		this.serviceNatId = serviceNatId;
	}


	/**
	 * [RefIndividuDto.serviceNatLibelleLongFr] Getter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @return the serviceNatLibelleLongFr
	 */
	public String getServiceNatLibelleLongFr() {
		return serviceNatLibelleLongFr;
	}


	/**
	 * [RefIndividuDto.serviceNatLibelleLongFr] Setter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @param serviceNatLibelleLongFr the serviceNatLibelleLongFr to set
	 */
	public void setServiceNatLibelleLongFr(String serviceNatLibelleLongFr) {
		this.serviceNatLibelleLongFr = serviceNatLibelleLongFr;
	}


	/**
	 * [RefIndividuDto.serviceNatLibelleLongAr] Getter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @return the serviceNatLibelleLongAr
	 */
	public String getServiceNatLibelleLongAr() {
		return serviceNatLibelleLongAr;
	}


	/**
	 * [RefIndividuDto.serviceNatLibelleLongAr] Setter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @param serviceNatLibelleLongAr the serviceNatLibelleLongAr to set
	 */
	public void setServiceNatLibelleLongAr(String serviceNatLibelleLongAr) {
		this.serviceNatLibelleLongAr = serviceNatLibelleLongAr;
	}


	/**
	 * [RefIndividuDto.serviceNatLibelleCourtFr] Getter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @return the serviceNatLibelleCourtFr
	 */
	public String getServiceNatLibelleCourtFr() {
		return serviceNatLibelleCourtFr;
	}


	/**
	 * [RefIndividuDto.serviceNatLibelleCourtFr] Setter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @param serviceNatLibelleCourtFr the serviceNatLibelleCourtFr to set
	 */
	public void setServiceNatLibelleCourtFr(String serviceNatLibelleCourtFr) {
		this.serviceNatLibelleCourtFr = serviceNatLibelleCourtFr;
	}


	/**
	 * [RefIndividuDto.serviceNatLibelleCourtAr] Getter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @return the serviceNatLibelleCourtAr
	 */
	public String getServiceNatLibelleCourtAr() {
		return serviceNatLibelleCourtAr;
	}


	/**
	 * [RefIndividuDto.serviceNatLibelleCourtAr] Setter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @param serviceNatLibelleCourtAr the serviceNatLibelleCourtAr to set
	 */
	public void setServiceNatLibelleCourtAr(String serviceNatLibelleCourtAr) {
		this.serviceNatLibelleCourtAr = serviceNatLibelleCourtAr;
	}


	/**
	 * [RefIndividuDto.serviceNatCode] Getter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @return the serviceNatCode
	 */
	public String getServiceNatCode() {
		return serviceNatCode;
	}


	/**
	 * [RefIndividuDto.serviceNatCode] Setter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @param serviceNatCode the serviceNatCode to set
	 */
	public void setServiceNatCode(String serviceNatCode) {
		this.serviceNatCode = serviceNatCode;
	}


	/**
	 * [RefIndividuDto.groupeSanguinId] Getter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @return the groupeSanguinId
	 */
	public Integer getGroupeSanguinId() {
		return groupeSanguinId;
	}


	/**
	 * [RefIndividuDto.groupeSanguinId] Setter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @param groupeSanguinId the groupeSanguinId to set
	 */
	public void setGroupeSanguinId(Integer groupeSanguinId) {
		this.groupeSanguinId = groupeSanguinId;
	}


	/**
	 * [RefIndividuDto.groupeSanguinLibelleLongFr] Getter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @return the groupeSanguinLibelleLongFr
	 */
	public String getGroupeSanguinLibelleLongFr() {
		return groupeSanguinLibelleLongFr;
	}


	/**
	 * [RefIndividuDto.groupeSanguinLibelleLongFr] Setter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @param groupeSanguinLibelleLongFr the groupeSanguinLibelleLongFr to set
	 */
	public void setGroupeSanguinLibelleLongFr(String groupeSanguinLibelleLongFr) {
		this.groupeSanguinLibelleLongFr = groupeSanguinLibelleLongFr;
	}


	/**
	 * [RefIndividuDto.groupeSanguinLibelleLongAr] Getter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @return the groupeSanguinLibelleLongAr
	 */
	public String getGroupeSanguinLibelleLongAr() {
		return groupeSanguinLibelleLongAr;
	}


	/**
	 * [RefIndividuDto.groupeSanguinLibelleLongAr] Setter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @param groupeSanguinLibelleLongAr the groupeSanguinLibelleLongAr to set
	 */
	public void setGroupeSanguinLibelleLongAr(String groupeSanguinLibelleLongAr) {
		this.groupeSanguinLibelleLongAr = groupeSanguinLibelleLongAr;
	}


	/**
	 * [RefIndividuDto.groupeSanguinLibelleCourtFr] Getter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @return the groupeSanguinLibelleCourtFr
	 */
	public String getGroupeSanguinLibelleCourtFr() {
		return groupeSanguinLibelleCourtFr;
	}


	/**
	 * [RefIndividuDto.groupeSanguinLibelleCourtFr] Setter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @param groupeSanguinLibelleCourtFr the groupeSanguinLibelleCourtFr to set
	 */
	public void setGroupeSanguinLibelleCourtFr(String groupeSanguinLibelleCourtFr) {
		this.groupeSanguinLibelleCourtFr = groupeSanguinLibelleCourtFr;
	}


	/**
	 * [RefIndividuDto.groupeSanguinLibelleCourtAr] Getter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @return the groupeSanguinLibelleCourtAr
	 */
	public String getGroupeSanguinLibelleCourtAr() {
		return groupeSanguinLibelleCourtAr;
	}


	/**
	 * [RefIndividuDto.groupeSanguinLibelleCourtAr] Setter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @param groupeSanguinLibelleCourtAr the groupeSanguinLibelleCourtAr to set
	 */
	public void setGroupeSanguinLibelleCourtAr(String groupeSanguinLibelleCourtAr) {
		this.groupeSanguinLibelleCourtAr = groupeSanguinLibelleCourtAr;
	}


	/**
	 * [RefIndividuDto.groupeSanguinCode] Getter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @return the groupeSanguinCode
	 */
	public String getGroupeSanguinCode() {
		return groupeSanguinCode;
	}


	/**
	 * [RefIndividuDto.groupeSanguinCode] Setter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @param groupeSanguinCode the groupeSanguinCode to set
	 */
	public void setGroupeSanguinCode(String groupeSanguinCode) {
		this.groupeSanguinCode = groupeSanguinCode;
	}

    /**
	 * [RefIndividuDto.situationFamilialeId] Getter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @return the situationFamilialeId
	 */
	public Integer getSituationFamilialeId() {
		return situationFamilialeId;
	}


	/**
	 * [RefIndividuDto.situationFamilialeId] Setter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @param situationFamilialeId the situationFamilialeId to set
	 */
	public void setSituationFamilialeId(Integer situationFamilialeId) {
		this.situationFamilialeId = situationFamilialeId;
	}


	/**
	 * [RefIndividuDto.situationFamilialeLibelleLongFr] Getter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @return the situationFamilialeLibelleLongFr
	 */
	public String getSituationFamilialeLibelleLongFr() {
		return situationFamilialeLibelleLongFr;
	}


	/**
	 * [RefIndividuDto.situationFamilialeLibelleLongFr] Setter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @param situationFamilialeLibelleLongFr the situationFamilialeLibelleLongFr to set
	 */
	public void setSituationFamilialeLibelleLongFr(
			String situationFamilialeLibelleLongFr) {
		this.situationFamilialeLibelleLongFr = situationFamilialeLibelleLongFr;
	}


	/**
	 * [RefIndividuDto.situationFamilialeLibelleLongAr] Getter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @return the situationFamilialeLibelleLongAr
	 */
	public String getSituationFamilialeLibelleLongAr() {
		return situationFamilialeLibelleLongAr;
	}


	/**
	 * [RefIndividuDto.situationFamilialeLibelleLongAr] Setter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @param situationFamilialeLibelleLongAr the situationFamilialeLibelleLongAr to set
	 */
	public void setSituationFamilialeLibelleLongAr(
			String situationFamilialeLibelleLongAr) {
		this.situationFamilialeLibelleLongAr = situationFamilialeLibelleLongAr;
	}


	/**
	 * [RefIndividuDto.situationFamilialeLibelleCourtFr] Getter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @return the situationFamilialeLibelleCourtFr
	 */
	public String getSituationFamilialeLibelleCourtFr() {
		return situationFamilialeLibelleCourtFr;
	}


	/**
	 * [RefIndividuDto.situationFamilialeLibelleCourtFr] Setter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @param situationFamilialeLibelleCourtFr the situationFamilialeLibelleCourtFr to set
	 */
	public void setSituationFamilialeLibelleCourtFr(
			String situationFamilialeLibelleCourtFr) {
		this.situationFamilialeLibelleCourtFr = situationFamilialeLibelleCourtFr;
	}


	/**
	 * [RefIndividuDto.situationFamilialeLibelleCourtAr] Getter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @return the situationFamilialeLibelleCourtAr
	 */
	public String getSituationFamilialeLibelleCourtAr() {
		return situationFamilialeLibelleCourtAr;
	}


	/**
	 * [RefIndividuDto.situationFamilialeLibelleCourtAr] Setter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @param situationFamilialeLibelleCourtAr the situationFamilialeLibelleCourtAr to set
	 */
	public void setSituationFamilialeLibelleCourtAr(
			String situationFamilialeLibelleCourtAr) {
		this.situationFamilialeLibelleCourtAr = situationFamilialeLibelleCourtAr;
	}


	/**
	 * [RefIndividuDto.situationFamilialeCode] Getter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @return the situationFamilialeCode
	 */
	public String getSituationFamilialeCode() {
		return situationFamilialeCode;
	}


	/**
	 * [RefIndividuDto.situationFamilialeCode] Setter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @param situationFamilialeCode the situationFamilialeCode to set
	 */
	public void setSituationFamilialeCode(String situationFamilialeCode) {
		this.situationFamilialeCode = situationFamilialeCode;
	}


	/**
	 * [RefIndividuDto.civiliteId] Getter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @return the civiliteId
	 */
	public Integer getCiviliteId() {
		return civiliteId;
	}


	/**
	 * [RefIndividuDto.civiliteId] Setter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @param civiliteId the civiliteId to set
	 */
	public void setCiviliteId(Integer civiliteId) {
		this.civiliteId = civiliteId;
	}


	/**
	 * [RefIndividuDto.civiliteLibelleLongFr] Getter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @return the civiliteLibelleLongFr
	 */
	public String getCiviliteLibelleLongFr() {
		return civiliteLibelleLongFr;
	}


	/**
	 * [RefIndividuDto.civiliteLibelleLongFr] Setter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @param civiliteLibelleLongFr the civiliteLibelleLongFr to set
	 */
	public void setCiviliteLibelleLongFr(String civiliteLibelleLongFr) {
		this.civiliteLibelleLongFr = civiliteLibelleLongFr;
	}


	/**
	 * [RefIndividuDto.civiliteLibelleLongAr] Getter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @return the civiliteLibelleLongAr
	 */
	public String getCiviliteLibelleLongAr() {
		return civiliteLibelleLongAr;
	}


	/**
	 * [RefIndividuDto.civiliteLibelleLongAr] Setter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @param civiliteLibelleLongAr the civiliteLibelleLongAr to set
	 */
	public void setCiviliteLibelleLongAr(String civiliteLibelleLongAr) {
		this.civiliteLibelleLongAr = civiliteLibelleLongAr;
	}


	/**
	 * [RefIndividuDto.civiliteLibelleCourtFr] Getter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @return the civiliteLibelleCourtFr
	 */
	public String getCiviliteLibelleCourtFr() {
		return civiliteLibelleCourtFr;
	}


	/**
	 * [RefIndividuDto.civiliteLibelleCourtFr] Setter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @param civiliteLibelleCourtFr the civiliteLibelleCourtFr to set
	 */
	public void setCiviliteLibelleCourtFr(String civiliteLibelleCourtFr) {
		this.civiliteLibelleCourtFr = civiliteLibelleCourtFr;
	}


	/**
	 * [RefIndividuDto.civiliteLibelleCourtAr] Getter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @return the civiliteLibelleCourtAr
	 */
	public String getCiviliteLibelleCourtAr() {
		return civiliteLibelleCourtAr;
	}


	/**
	 * [RefIndividuDto.civiliteLibelleCourtAr] Setter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @param civiliteLibelleCourtAr the civiliteLibelleCourtAr to set
	 */
	public void setCiviliteLibelleCourtAr(String civiliteLibelleCourtAr) {
		this.civiliteLibelleCourtAr = civiliteLibelleCourtAr;
	}


	/**
	 * [RefIndividuDto.civiliteCode] Getter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @return the civiliteCode
	 */
	public String getCiviliteCode() {
		return civiliteCode;
	}


	/**
	 * [RefIndividuDto.civiliteCode] Setter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @param civiliteCode the civiliteCode to set
	 */
	public void setCiviliteCode(String civiliteCode) {
		this.civiliteCode = civiliteCode;
	}


	/**
	 * [RefIndividuDto.nationaliteId] Getter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @return the nationaliteId
	 */
	public Integer getNationaliteId() {
		return nationaliteId;
	}


	/**
	 * [RefIndividuDto.nationaliteId] Setter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @param nationaliteId the nationaliteId to set
	 */
	public void setNationaliteId(Integer nationaliteId) {
		this.nationaliteId = nationaliteId;
	}


	/**
	 * [RefIndividuDto.nationaliteLibelleLongFr] Getter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @return the nationaliteLibelleLongFr
	 */
	public String getNationaliteLibelleLongFr() {
		return nationaliteLibelleLongFr;
	}


	/**
	 * [RefIndividuDto.nationaliteLibelleLongFr] Setter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @param nationaliteLibelleLongFr the nationaliteLibelleLongFr to set
	 */
	public void setNationaliteLibelleLongFr(String nationaliteLibelleLongFr) {
		this.nationaliteLibelleLongFr = nationaliteLibelleLongFr;
	}


	/**
	 * [RefIndividuDto.nationaliteLibelleLongAr] Getter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @return the nationaliteLibelleLongAr
	 */
	public String getNationaliteLibelleLongAr() {
		return nationaliteLibelleLongAr;
	}


	/**
	 * [RefIndividuDto.nationaliteLibelleLongAr] Setter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @param nationaliteLibelleLongAr the nationaliteLibelleLongAr to set
	 */
	public void setNationaliteLibelleLongAr(String nationaliteLibelleLongAr) {
		this.nationaliteLibelleLongAr = nationaliteLibelleLongAr;
	}


	/**
	 * [RefIndividuDto.nationaliteLibelleCourtFr] Getter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @return the nationaliteLibelleCourtFr
	 */
	public String getNationaliteLibelleCourtFr() {
		return nationaliteLibelleCourtFr;
	}


	/**
	 * [RefIndividuDto.nationaliteLibelleCourtFr] Setter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @param nationaliteLibelleCourtFr the nationaliteLibelleCourtFr to set
	 */
	public void setNationaliteLibelleCourtFr(String nationaliteLibelleCourtFr) {
		this.nationaliteLibelleCourtFr = nationaliteLibelleCourtFr;
	}


	/**
	 * [RefIndividuDto.nationaliteLibelleCourtAr] Getter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @return the nationaliteLibelleCourtAr
	 */
	public String getNationaliteLibelleCourtAr() {
		return nationaliteLibelleCourtAr;
	}


	/**
	 * [RefIndividuDto.nationaliteLibelleCourtAr] Setter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @param nationaliteLibelleCourtAr the nationaliteLibelleCourtAr to set
	 */
	public void setNationaliteLibelleCourtAr(String nationaliteLibelleCourtAr) {
		this.nationaliteLibelleCourtAr = nationaliteLibelleCourtAr;
	}


	/**
	 * [RefIndividuDto.nationaliteCode] Getter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @return the nationaliteCode
	 */
	public String getNationaliteCode() {
		return nationaliteCode;
	}


	/**
	 * [RefIndividuDto.nationaliteCode] Setter 
	 * @author BELBRIK Oualid on : 22 janv. 2014  17:39:56
	 * @param nationaliteCode the nationaliteCode to set
	 */
	public void setNationaliteCode(String nationaliteCode) {
		this.nationaliteCode = nationaliteCode;
	}


	public Integer getTypeIndividuId() {
		return typeIndividuId;
	}


	public void setTypeIndividuId(Integer typeIndividuId) {
		this.typeIndividuId = typeIndividuId;
	}


	public String getTypeIndividuLibelleLongFr() {
		return typeIndividuLibelleLongFr;
	}


	public void setTypeIndividuLibelleLongFr(String typeIndividuLibelleLongFr) {
		this.typeIndividuLibelleLongFr = typeIndividuLibelleLongFr;
	}


	public String getTypeIndividuLibelleLongAr() {
		return typeIndividuLibelleLongAr;
	}


	public void setTypeIndividuLibelleLongAr(String typeIndividuLibelleLongAr) {
		this.typeIndividuLibelleLongAr = typeIndividuLibelleLongAr;
	}


	public String getTypeIndividuLibelleCourtFr() {
		return typeIndividuLibelleCourtFr;
	}


	public void setTypeIndividuLibelleCourtFr(String typeIndividuLibelleCourtFr) {
		this.typeIndividuLibelleCourtFr = typeIndividuLibelleCourtFr;
	}


	public String getTypeIndividuLibelleCourtAr() {
		return typeIndividuLibelleCourtAr;
	}


	public void setTypeIndividuLibelleCourtAr(String typeIndividuLibelleCourtAr) {
		this.typeIndividuLibelleCourtAr = typeIndividuLibelleCourtAr;
	}


	public String getTypeIndividuCode() {
		return typeIndividuCode;
	}


	public void setTypeIndividuCode(String typeIndividuCode) {
		this.typeIndividuCode = typeIndividuCode;
	}


	/**
	 * [RefIndividuDto.qualite] Getter 
	 * @author MAKERRI Sofiane on : 7 avr. 2014  08:24:10
	 * @return the qualite
	 */
	public String getQualite() {
		return qualite;
	}


	/**
	 * [RefIndividuDto.qualite] Setter 
	 * @author MAKERRI Sofiane on : 7 avr. 2014  08:24:10
	 * @param qualite the qualite to set
	 */
	public void setQualite(String qualite) {
		this.qualite = qualite;
	}


	/**
	 * [RefIndividuDto.lieuNaissance] Getter 
	 * @author BELDI Jamel on : 22 juin 2014  15:36:23
	 * @return the lieuNaissance
	 */
	public String getLieuNaissance() {
		return lieuNaissance;
	}


	/**
	 * [RefIndividuDto.lieuNaissance] Setter 
	 * @author BELDI Jamel on : 22 juin 2014  15:36:23
	 * @param lieuNaissance the lieuNaissance to set
	 */
	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}

	
	
}
