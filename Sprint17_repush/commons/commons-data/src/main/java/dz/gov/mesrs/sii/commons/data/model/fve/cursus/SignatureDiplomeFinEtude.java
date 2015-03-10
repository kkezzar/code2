/**
 * [dz.gov.mesrs.sii.commons.data.model.fve.cursus.SignatureDiplomeFinEtude.java] 
 * @author MAKERRI Sofiane on : 18 févr. 2015  09:51:31
 */
package dz.gov.mesrs.sii.commons.data.model.fve.cursus;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

/**
 * @author MAKERRI Sofiane  on : 18 févr. 2015  09:51:31
 */
@Entity
@Table(name = "signature_diplome_fin_etude", schema = "cursus")
public class SignatureDiplomeFinEtude implements java.io.Serializable{

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 18 févr. 2015  09:51:50
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private Date dateSignature;
	private String signataire;
	private String reference;
	private DiplomeFinEtude diplomeFinEtude;
	private Nomenclature ncSignatureDiplome;
	private boolean attestation;
	private String observation;
	
	public SignatureDiplomeFinEtude(){
		super();
	}

	@SequenceGenerator(name = "signature_diplome_fin_etude_id_seq", sequenceName = "cursus.signature_diplome_fin_etude_id_seq")
	@Id
	@GeneratedValue(generator = "signature_diplome_fin_etude_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * [SignatureDiplomeFinEtude.dateSignature] Getter 
	 * @author MAKERRI Sofiane on : 18 févr. 2015  09:55:43
	 * @return the dateSignature
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "date_signature", length = 13)
	public Date getDateSignature() {
		return dateSignature;
	}

	/**
	 * [SignatureDiplomeFinEtude.dateSignature] Setter 
	 * @author MAKERRI Sofiane on : 18 févr. 2015  09:55:43
	 * @param dateSignature the dateSignature to set
	 */
	public void setDateSignature(Date dateSignature) {
		this.dateSignature = dateSignature;
	}


	/**
	 * [SignatureDiplomeFinEtude.signataire] Getter 
	 * @author MAKERRI Sofiane on : 25 févr. 2015  12:05:38
	 * @return the signataire
	 */
	@Column(name = "signataire")
	public String getSignataire() {
		return signataire;
	}

	/**
	 * [SignatureDiplomeFinEtude.signataire] Setter 
	 * @author MAKERRI Sofiane on : 25 févr. 2015  12:05:38
	 * @param signataire the signataire to set
	 */
	public void setSignataire(String signataire) {
		this.signataire = signataire;
	}

	/**
	 * [SignatureDiplomeFinEtude.reference] Getter 
	 * @author MAKERRI Sofiane on : 25 févr. 2015  08:10:12
	 * @return the reference
	 */
	@Column(name = "reference")
	public String getReference() {
		return reference;
	}

	/**
	 * [SignatureDiplomeFinEtude.reference] Setter 
	 * @author MAKERRI Sofiane on : 25 févr. 2015  08:10:12
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * [SignatureDiplomeFinEtude.observation] Getter 
	 * @author MAKERRI Sofiane on : 25 févr. 2015  08:10:12
	 * @return the observation
	 */
	@Column(name = "observation")
	public String getObservation() {
		return observation;
	}

	/**
	 * [SignatureDiplomeFinEtude.observation] Setter 
	 * @author MAKERRI Sofiane on : 25 févr. 2015  08:10:12
	 * @param observation the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}

	/**
	 * [SignatureDiplomeFinEtude.diplomeFinEtude] Getter 
	 * @author MAKERRI Sofiane on : 18 févr. 2015  09:55:43
	 * @return the diplomeFinEtude
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_diplome_fin_etude", nullable = false)
	public DiplomeFinEtude getDiplomeFinEtude() {
		return diplomeFinEtude;
	}

	/**
	 * [SignatureDiplomeFinEtude.diplomeFinEtude] Setter 
	 * @author MAKERRI Sofiane on : 18 févr. 2015  09:55:43
	 * @param diplomeFinEtude the diplomeFinEtude to set
	 */
	public void setDiplomeFinEtude(DiplomeFinEtude diplomeFinEtude) {
		this.diplomeFinEtude = diplomeFinEtude;
	}

	/**
	 * [SignatureDiplomeFinEtude.attestation] Getter 
	 * @author MAKERRI Sofiane on : 18 févr. 2015  09:55:43
	 * @return the attestation
	 */
	@Column(name = "est_attestation")
	public boolean isAttestation() {
		return attestation;
	}

	/**
	 * [SignatureDiplomeFinEtude.attestation] Setter 
	 * @author MAKERRI Sofiane on : 18 févr. 2015  09:55:43
	 * @param attestation the attestation to set
	 */
	public void setAttestation(boolean attestation) {
		this.attestation = attestation;
	}

	/**
	 * [SignatureDiplomeFinEtude.ncSignatureDiplome] Getter 
	 * @author MAKERRI Sofiane on : 25 févr. 2015  07:58:33
	 * @return the ncSignatureDiplome
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nc_signature_diplome", nullable = false)
	public Nomenclature getNcSignatureDiplome() {
		return ncSignatureDiplome;
	}

	/**
	 * [SignatureDiplomeFinEtude.ncSignatureDiplome] Setter 
	 * @author MAKERRI Sofiane on : 25 févr. 2015  07:58:33
	 * @param ncSignatureDiplome the ncSignatureDiplome to set
	 */
	public void setNcSignatureDiplome(Nomenclature ncSignatureDiplome) {
		this.ncSignatureDiplome = ncSignatureDiplome;
	}
	
	

	
}
