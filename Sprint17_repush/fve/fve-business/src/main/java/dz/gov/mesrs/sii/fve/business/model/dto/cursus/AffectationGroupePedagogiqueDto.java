/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.cursus.AffectationGroupePedagogiqueDto.java] 
 * @author MAKERRI Sofiane on : 22 juil. 2014  13:04:33
 */
package dz.gov.mesrs.sii.fve.business.model.dto.cursus;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanControleContinuDto;


/**
 * @author MAKERRI Sofiane  on : 22 juil. 2014  13:04:33
 */
public class AffectationGroupePedagogiqueDto implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 22 juil. 2014  13:04:41
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer dossierInscriptionId;
	private Integer dossierEtudiantId;
	private String nomEtudiant;
	private Integer individuId;
	private String individuIdentifiant;
	private String prenomEtudiant;
	private String numeroMatricule;
	private String numeroInscription;
	private Integer groupePedagogiqueId;
	private Integer refGroupeId;
	private Date dateAffectation;
	private Date dateNaissanceEtudiant;
	private String etudiantSexe;
	private double moyenneBac;
	private double lastMoyenne;
	private String etudiantCivilite;
	private String urlPhoto;
	private List<BilanControleContinuDto> bilanControleContinuDtos;
	
	
	

	/**
	 * [AffectationGroupePedagogiqueDto.urlPhoto] Getter 
	 * @author MAKERRI Sofiane on : 11 ao没t 2014  16:34:11
	 * @return the urlPhoto
	 */
	public String getUrlPhoto() {
		return urlPhoto;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.urlPhoto] Setter 
	 * @author MAKERRI Sofiane on : 11 ao没t 2014  16:34:11
	 * @param urlPhoto the urlPhoto to set
	 */
	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}

	

	/**
	 * [AffectationGroupePedagogiqueDto.AffectationGroupePedagogiqueDto()] Constructor
	 * @author MAKERRI Sofiane  on : 22 juil. 2014  13:04:33	
	 */
	public AffectationGroupePedagogiqueDto() {
		super();
	}

	/**
	 * [AffectationGroupePedagogiqueDto.id] Getter 
	 * @author MAKERRI Sofiane on : 11 ao没t 2014  08:48:48
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.id] Setter 
	 * @author MAKERRI Sofiane on : 11 ao没t 2014  08:48:48
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	

	/**
	 * [AffectationGroupePedagogiqueDto.dossierInscriptionId] Getter 
	 * @author MAKERRI Sofiane on : 11 ao没t 2014  08:58:04
	 * @return the dossierInscriptionId
	 */
	public Integer getDossierInscriptionId() {
		return dossierInscriptionId;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.dossierInscriptionId] Setter 
	 * @author MAKERRI Sofiane on : 11 ao没t 2014  08:58:04
	 * @param dossierInscriptionId the dossierInscriptionId to set
	 */
	public void setDossierInscriptionId(Integer dossierInscriptionId) {
		this.dossierInscriptionId = dossierInscriptionId;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.groupePedagogiqueId] Getter 
	 * @author MAKERRI Sofiane on : 11 ao没t 2014  08:58:04
	 * @return the groupePedagogiqueId
	 */
	public Integer getGroupePedagogiqueId() {
		return groupePedagogiqueId;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.groupePedagogiqueId] Setter 
	 * @author MAKERRI Sofiane on : 11 ao没t 2014  08:58:04
	 * @param groupePedagogiqueId the groupePedagogiqueId to set
	 */
	public void setGroupePedagogiqueId(Integer groupePedagogiqueId) {
		this.groupePedagogiqueId = groupePedagogiqueId;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.dateAffectation] Getter 
	 * @author MAKERRI Sofiane on : 11 ao没t 2014  08:48:48
	 * @return the dateAffectation
	 */
	public Date getDateAffectation() {
		return dateAffectation;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.dateAffectation] Setter 
	 * @author MAKERRI Sofiane on : 11 ao没t 2014  08:48:48
	 * @param dateAffectation the dateAffectation to set
	 */
	public void setDateAffectation(Date dateAffectation) {
		this.dateAffectation = dateAffectation;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.numeroMatricule] Getter 
	 * @author MAKERRI Sofiane on : 11 ao没t 2014  12:15:50
	 * @return the numeroMatricule
	 */
	public String getNumeroMatricule() {
		return numeroMatricule;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.numeroMatricule] Setter 
	 * @author MAKERRI Sofiane on : 11 ao没t 2014  12:15:50
	 * @param numeroMatricule the numeroMatricule to set
	 */
	public void setNumeroMatricule(String numeroMatricule) {
		this.numeroMatricule = numeroMatricule;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.nomEtudiant] Getter 
	 * @author MAKERRI Sofiane on : 7 oct. 2014  12:12:25
	 * @return the nomEtudiant
	 */
	public String getNomEtudiant() {
		return nomEtudiant;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.nomEtudiant] Setter 
	 * @author MAKERRI Sofiane on : 7 oct. 2014  12:12:25
	 * @param nomEtudiant the nomEtudiant to set
	 */
	public void setNomEtudiant(String nomEtudiant) {
		this.nomEtudiant = nomEtudiant;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.prenomEtudiant] Getter 
	 * @author MAKERRI Sofiane on : 7 oct. 2014  12:12:25
	 * @return the prenomEtudiant
	 */
	public String getPrenomEtudiant() {
		return prenomEtudiant;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.prenomEtudiant] Setter 
	 * @author MAKERRI Sofiane on : 7 oct. 2014  12:12:25
	 * @param prenomEtudiant the prenomEtudiant to set
	 */
	public void setPrenomEtudiant(String prenomEtudiant) {
		this.prenomEtudiant = prenomEtudiant;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.dateNaissanceEtudiant] Getter 
	 * @author MAKERRI Sofiane on : 7 oct. 2014  12:12:25
	 * @return the dateNaissanceEtudiant
	 */
	public Date getDateNaissanceEtudiant() {
		return dateNaissanceEtudiant;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.dateNaissanceEtudiant] Setter 
	 * @author MAKERRI Sofiane on : 7 oct. 2014  12:12:25
	 * @param dateNaissanceEtudiant the dateNaissanceEtudiant to set
	 */
	public void setDateNaissanceEtudiant(Date dateNaissanceEtudiant) {
		this.dateNaissanceEtudiant = dateNaissanceEtudiant;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.dossierEtudiantId] Getter 
	 * @author MAKERRI Sofiane on : 11 oct. 2014  10:57:43
	 * @return the dossierEtudiantId
	 */
	public Integer getDossierEtudiantId() {
		return dossierEtudiantId;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.dossierEtudiantId] Setter 
	 * @author MAKERRI Sofiane on : 11 oct. 2014  10:57:43
	 * @param dossierEtudiantId the dossierEtudiantId to set
	 */
	public void setDossierEtudiantId(Integer dossierEtudiantId) {
		this.dossierEtudiantId = dossierEtudiantId;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.individuId] Getter 
	 * @author MAKERRI Sofiane on : 11 oct. 2014  10:57:43
	 * @return the individuId
	 */
	public Integer getIndividuId() {
		return individuId;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.individuId] Setter 
	 * @author MAKERRI Sofiane on : 11 oct. 2014  10:57:43
	 * @param individuId the individuId to set
	 */
	public void setIndividuId(Integer individuId) {
		this.individuId = individuId;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.individuIdentifiant] Getter 
	 * @author MAKERRI Sofiane on : 12 oct. 2014  14:03:20
	 * @return the individuIdentifiant
	 */
	public String getIndividuIdentifiant() {
		return individuIdentifiant;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.individuIdentifiant] Setter 
	 * @author MAKERRI Sofiane on : 12 oct. 2014  14:03:20
	 * @param individuIdentifiant the individuIdentifiant to set
	 */
	public void setIndividuIdentifiant(String individuIdentifiant) {
		this.individuIdentifiant = individuIdentifiant;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.bilanControleContinuDtos] Getter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  08:35:22
	 * @return the bilanControleContinuDtos
	 */
	public List<BilanControleContinuDto> getBilanControleContinuDtos() {
		return bilanControleContinuDtos;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.bilanControleContinuDtos] Setter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  08:35:22
	 * @param bilanControleContinuDtos the bilanControleContinuDtos to set
	 */
	public void setBilanControleContinuDtos(
			List<BilanControleContinuDto> bilanControleContinuDtos) {
		this.bilanControleContinuDtos = bilanControleContinuDtos;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.refGroupeId] Getter 
	 * @author MAKERRI Sofiane on : 27 janv. 2015  08:24:30
	 * @return the refGroupeId
	 */
	public Integer getRefGroupeId() {
		return refGroupeId;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.refGroupeId] Setter 
	 * @author MAKERRI Sofiane on : 27 janv. 2015  08:24:30
	 * @param refGroupeId the refGroupeId to set
	 */
	public void setRefGroupeId(Integer refGroupeId) {
		this.refGroupeId = refGroupeId;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.etudiantSexe] Getter 
	 * @author MAKERRI Sofiane on : 28 janv. 2015  08:50:50
	 * @return the etudiantSexe
	 */
	public String getEtudiantSexe() {
		if(etudiantCivilite != null && etudiantCivilite.toLowerCase().equals("monsieur")) {
			etudiantSexe = "Masculin";
		} else {
			etudiantSexe = "F閙inin";
		}
		return etudiantSexe;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.etudiantSexe] Setter 
	 * @author MAKERRI Sofiane on : 28 janv. 2015  08:50:50
	 * @param etudiantSexe the etudiantSexe to set
	 */
	public void setEtudiantSexe(String etudiantSexe) {
		this.etudiantSexe = etudiantSexe;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.etudiantCivilite] Getter 
	 * @author MAKERRI Sofiane on : 28 janv. 2015  10:23:52
	 * @return the etudiantCivilite
	 */
	public String getEtudiantCivilite() {
		return etudiantCivilite;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.etudiantCivilite] Setter 
	 * @author MAKERRI Sofiane on : 28 janv. 2015  10:23:52
	 * @param etudiantCivilite the etudiantCivilite to set
	 */
	public void setEtudiantCivilite(String etudiantCivilite) {
		this.etudiantCivilite = etudiantCivilite;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.moyenneBac] Getter 
	 * @author MAKERRI Sofiane on : 28 janv. 2015  10:25:49
	 * @return the moyenneBac
	 */
	public double getMoyenneBac() {
		return moyenneBac;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.moyenneBac] Setter 
	 * @author MAKERRI Sofiane on : 28 janv. 2015  10:25:49
	 * @param moyenneBac the moyenneBac to set
	 */
	public void setMoyenneBac(double moyenneBac) {
		this.moyenneBac = moyenneBac;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.lastMoyenne] Getter 
	 * @author MAKERRI Sofiane on : 28 janv. 2015  11:56:39
	 * @return the lastMoyenne
	 */
	public double getLastMoyenne() {
		return lastMoyenne;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.lastMoyenne] Setter 
	 * @author MAKERRI Sofiane on : 28 janv. 2015  11:56:39
	 * @param lastMoyenne the lastMoyenne to set
	 */
	public void setLastMoyenne(double lastMoyenne) {
		this.lastMoyenne = lastMoyenne;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.numeroInscription] Getter 
	 * @author MAKERRI Sofiane on : 10 f関r. 2015  11:18:09
	 * @return the numeroInscription
	 */
	public String getNumeroInscription() {
		return numeroInscription;
	}

	/**
	 * [AffectationGroupePedagogiqueDto.numeroInscription] Setter 
	 * @author MAKERRI Sofiane on : 10 f関r. 2015  11:18:09
	 * @param numeroInscription the numeroInscription to set
	 */
	public void setNumeroInscription(String numeroInscription) {
		this.numeroInscription = numeroInscription;
	}
	
	
	
}
