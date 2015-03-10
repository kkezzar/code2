/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.cursus.SignatureDiplomeFinEtudeDto.java] 
 * @author MAKERRI Sofiane on : 18 févr. 2015  10:07:16
 */
package dz.gov.mesrs.sii.fve.business.model.dto.cursus;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MAKERRI Sofiane  on : 18 févr. 2015  10:07:16
 */

public class SignatureDiplomeFinEtudeDto implements Serializable{

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 18 févr. 2015  10:07:26
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date dateSignature;
	private String signataire;
	private Integer diplomeFinEtudeId;
	private boolean attestation;
	private Integer ncSignatureDiplomeId;
	private String ncSignatureDiplomeLibelleLongFr;
	private String ncSignatureDiplomeCode;
	private String reference;
	private String observation;
	private boolean added;
	
	public SignatureDiplomeFinEtudeDto() {
		super();
	}
	
	/**
	 * [SignatureDiplomeFinEtudeDto.SignatureDiplomeFinEtudeDto()] Constructor
	 * @author MAKERRI Sofiane  on : 25 févr. 2015  09:17:33
	 * @param signatureDiplomeFinEtudeDto	
	 */
	public SignatureDiplomeFinEtudeDto(SignatureDiplomeFinEtudeDto signatureDiplomeFinEtudeDto) {
		super();
		if(signatureDiplomeFinEtudeDto != null) {
			this.dateSignature = signatureDiplomeFinEtudeDto.getDateSignature();
			this.signataire = signatureDiplomeFinEtudeDto.getSignataire();
			this.ncSignatureDiplomeId = signatureDiplomeFinEtudeDto.getNcSignatureDiplomeId();
			this.reference = signatureDiplomeFinEtudeDto.getReference();
			this.observation = signatureDiplomeFinEtudeDto.getObservation();
		}
	}

	/**
	 * [SignatureDiplomeFinEtudeDto.id] Getter 
	 * @author MAKERRI Sofiane on : 18 févr. 2015  10:08:21
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * [SignatureDiplomeFinEtudeDto.id] Setter 
	 * @author MAKERRI Sofiane on : 18 févr. 2015  10:08:21
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * [SignatureDiplomeFinEtudeDto.dateSignature] Getter 
	 * @author MAKERRI Sofiane on : 18 févr. 2015  10:08:21
	 * @return the dateSignature
	 */
	public Date getDateSignature() {
		return dateSignature;
	}

	/**
	 * [SignatureDiplomeFinEtudeDto.dateSignature] Setter 
	 * @author MAKERRI Sofiane on : 18 févr. 2015  10:08:21
	 * @param dateSignature the dateSignature to set
	 */
	public void setDateSignature(Date dateSignature) {
		this.dateSignature = dateSignature;
	}


	/**
	 * [SignatureDiplomeFinEtudeDto.signataire] Getter 
	 * @author MAKERRI Sofiane on : 25 févr. 2015  12:09:07
	 * @return the signataire
	 */
	public String getSignataire() {
		return signataire;
	}

	/**
	 * [SignatureDiplomeFinEtudeDto.signataire] Setter 
	 * @author MAKERRI Sofiane on : 25 févr. 2015  12:09:07
	 * @param signataire the signataire to set
	 */
	public void setSignataire(String signataire) {
		this.signataire = signataire;
	}

	/**
	 * [SignatureDiplomeFinEtudeDto.reference] Getter 
	 * @author MAKERRI Sofiane on : 25 févr. 2015  08:09:02
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * [SignatureDiplomeFinEtudeDto.reference] Setter 
	 * @author MAKERRI Sofiane on : 25 févr. 2015  08:09:02
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * [SignatureDiplomeFinEtudeDto.observation] Getter 
	 * @author MAKERRI Sofiane on : 25 févr. 2015  08:09:02
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}

	/**
	 * [SignatureDiplomeFinEtudeDto.observation] Setter 
	 * @author MAKERRI Sofiane on : 25 févr. 2015  08:09:02
	 * @param observation the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}

	/**
	 * [SignatureDiplomeFinEtudeDto.diplomeFinEtudeId] Getter 
	 * @author MAKERRI Sofiane on : 18 févr. 2015  10:08:21
	 * @return the diplomeFinEtudeId
	 */
	public Integer getDiplomeFinEtudeId() {
		return diplomeFinEtudeId;
	}

	/**
	 * [SignatureDiplomeFinEtudeDto.diplomeFinEtudeId] Setter 
	 * @author MAKERRI Sofiane on : 18 févr. 2015  10:08:21
	 * @param diplomeFinEtudeId the diplomeFinEtudeId to set
	 */
	public void setDiplomeFinEtudeId(Integer diplomeFinEtudeId) {
		this.diplomeFinEtudeId = diplomeFinEtudeId;
	}

	/**
	 * [SignatureDiplomeFinEtudeDto.attestation] Getter 
	 * @author MAKERRI Sofiane on : 18 févr. 2015  10:08:21
	 * @return the attestation
	 */
	public boolean isAttestation() {
		return attestation;
	}

	/**
	 * [SignatureDiplomeFinEtudeDto.attestation] Setter 
	 * @author MAKERRI Sofiane on : 18 févr. 2015  10:08:21
	 * @param attestation the attestation to set
	 */
	public void setAttestation(boolean attestation) {
		this.attestation = attestation;
	}

	/**
	 * [SignatureDiplomeFinEtudeDto.ncSignatureDiplomeId] Getter 
	 * @author MAKERRI Sofiane on : 25 févr. 2015  08:00:23
	 * @return the ncSignatureDiplomeId
	 */
	public Integer getNcSignatureDiplomeId() {
		return ncSignatureDiplomeId;
	}

	/**
	 * [SignatureDiplomeFinEtudeDto.ncSignatureDiplomeId] Setter 
	 * @author MAKERRI Sofiane on : 25 févr. 2015  08:00:23
	 * @param ncSignatureDiplomeId the ncSignatureDiplomeId to set
	 */
	public void setNcSignatureDiplomeId(Integer ncSignatureDiplomeId) {
		this.ncSignatureDiplomeId = ncSignatureDiplomeId;
	}

	/**
	 * [SignatureDiplomeFinEtudeDto.ncSignatureDiplomeLibelleLongFr] Getter 
	 * @author MAKERRI Sofiane on : 25 févr. 2015  08:00:23
	 * @return the ncSignatureDiplomeLibelleLongFr
	 */
	public String getNcSignatureDiplomeLibelleLongFr() {
		return ncSignatureDiplomeLibelleLongFr;
	}

	/**
	 * [SignatureDiplomeFinEtudeDto.ncSignatureDiplomeLibelleLongFr] Setter 
	 * @author MAKERRI Sofiane on : 25 févr. 2015  08:00:23
	 * @param ncSignatureDiplomeLibelleLongFr the ncSignatureDiplomeLibelleLongFr to set
	 */
	public void setNcSignatureDiplomeLibelleLongFr(
			String ncSignatureDiplomeLibelleLongFr) {
		this.ncSignatureDiplomeLibelleLongFr = ncSignatureDiplomeLibelleLongFr;
	}

	/**
	 * [SignatureDiplomeFinEtudeDto.ncSignatureDiplomeCode] Getter 
	 * @author MAKERRI Sofiane on : 25 févr. 2015  08:00:23
	 * @return the ncSignatureDiplomeCode
	 */
	public String getNcSignatureDiplomeCode() {
		return ncSignatureDiplomeCode;
	}

	/**
	 * [SignatureDiplomeFinEtudeDto.ncSignatureDiplomeCode] Setter 
	 * @author MAKERRI Sofiane on : 25 févr. 2015  08:00:23
	 * @param ncSignatureDiplomeCode the ncSignatureDiplomeCode to set
	 */
	public void setNcSignatureDiplomeCode(String ncSignatureDiplomeCode) {
		this.ncSignatureDiplomeCode = ncSignatureDiplomeCode;
	}

	/**
	 * [SignatureDiplomeFinEtudeDto.added] Getter 
	 * @author MAKERRI Sofiane on : 25 févr. 2015  08:12:01
	 * @return the added
	 */
	public boolean isAdded() {
		return added;
	}

	/**
	 * [SignatureDiplomeFinEtudeDto.added] Setter 
	 * @author MAKERRI Sofiane on : 25 févr. 2015  08:12:01
	 * @param added the added to set
	 */
	public void setAdded(boolean added) {
		this.added = added;
	}
	
	

}
