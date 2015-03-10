package dz.gov.mesrs.sii.commons.data.model.fve.bac;

// Generated 22 mai 2014 12:17:42 by Hibernate Tools 3.6.0

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Importation generated by hbm2java
 */
@Entity
@Table(name = "generation", schema = "bac")
public class Generation implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 22 mai 2014  12:19:45
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String anneeAcademique;
	private String situation;
	private String refCodeEtablissement;
	private int nbBacheliers;
	private int nbDossiersEtudiants;
	private int nbDossiersInscriptions;
	private int nbIndividusGeneres;
	private int nbIndividusExistants;
	private Date dateDebutGeneration;
	private Date dateFinGeneration;
	public Generation() {
	}

	/**
	 * [Generation.id] Getter 
	 * @author rlaib on : 30 juin 2014  10:34:44
	 * @return the id
	 */
	@SequenceGenerator(name="generation_id_seq_gen", sequenceName="bac.generation_id_seq")
	@Id @GeneratedValue(generator="generation_id_seq_gen")
	@Column(name = "id")
	public int getId() {
		return id;
	}

	/**
	 * [Generation.id] Setter 
	 * @author rlaib on : 30 juin 2014  10:34:44
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * [Generation.anneeAcademique] Getter 
	 * @author rlaib on : 30 juin 2014  10:34:44
	 * @return the anneeAcademique
	 */
	@Column(name = "annee_academique", nullable = false, length = 10)
	public String getAnneeAcademique() {
		return anneeAcademique;
	}

	/**
	 * [Generation.anneeAcademique] Setter 
	 * @author rlaib on : 30 juin 2014  10:34:44
	 * @param anneeAcademique the anneeAcademique to set
	 */
	public void setAnneeAcademique(String anneeAcademique) {
		this.anneeAcademique = anneeAcademique;
	}

	/**
	 * [Generation.situation] Getter 
	 * @author rlaib on : 30 juin 2014  10:34:44
	 * @return the situation
	 */
	@Column(name = "situation", length = 10)
	public String getSituation() {
		return situation;
	}

	/**
	 * [Generation.situation] Setter 
	 * @author rlaib on : 30 juin 2014  10:34:44
	 * @param situation the situation to set
	 */
	public void setSituation(String situation) {
		this.situation = situation;
	}

	/**
	 * [Generation.refCodeEtablissement] Getter 
	 * @author rlaib on : 30 juin 2014  10:34:44
	 * @return the refCodeEtablissement
	 */
	@Column(name = "ref_code_etablissement", length = 10)
	public String getRefCodeEtablissement() {
		return refCodeEtablissement;
	}

	/**
	 * [Generation.refCodeEtablissement] Setter 
	 * @author rlaib on : 30 juin 2014  10:34:44
	 * @param refCodeEtablissement the refCodeEtablissement to set
	 */
	public void setRefCodeEtablissement(String refCodeEtablissement) {
		this.refCodeEtablissement = refCodeEtablissement;
	}

	/**
	 * [Generation.nbBacheliers] Getter 
	 * @author rlaib on : 30 juin 2014  12:11:40
	 * @return the nbBacheliers
	 */
	@Column(name = "nb_bacheliers")
	public int getNbBacheliers() {
		return nbBacheliers;
	}

	/**
	 * [Generation.nbBacheliers] Setter 
	 * @author rlaib on : 30 juin 2014  12:11:40
	 * @param nbBacheliers the nbBacheliers to set
	 */
	public void setNbBacheliers(int nbBacheliers) {
		this.nbBacheliers = nbBacheliers;
	}

	/**
	 * [Generation.nbDossiersEtudiants] Getter 
	 * @author rlaib on : 30 juin 2014  12:11:40
	 * @return the nbDossiersEtudiants
	 */
	@Column(name = "nb_dossiers_etudiants")
	public int getNbDossiersEtudiants() {
		return nbDossiersEtudiants;
	}

	/**
	 * [Generation.nbDossiersEtudiants] Setter 
	 * @author rlaib on : 30 juin 2014  12:11:40
	 * @param nbDossiersEtudiants the nbDossiersEtudiants to set
	 */
	public void setNbDossiersEtudiants(int nbDossiersEtudiants) {
		this.nbDossiersEtudiants = nbDossiersEtudiants;
	}

	/**
	 * [Generation.nbDossiersInscriptions] Getter 
	 * @author rlaib on : 30 juin 2014  12:11:40
	 * @return the nbDossiersInscriptions
	 */
	@Column(name = "nb_dossiers_inscriptions")
	public int getNbDossiersInscriptions() {
		return nbDossiersInscriptions;
	}

	/**
	 * [Generation.nbDossiersInscriptions] Setter 
	 * @author rlaib on : 30 juin 2014  12:11:40
	 * @param nbDossiersInscriptions the nbDossiersInscriptions to set
	 */
	public void setNbDossiersInscriptions(int nbDossiersInscriptions) {
		this.nbDossiersInscriptions = nbDossiersInscriptions;
	}

	/**
	 * [Generation.nbIndividusGeneres] Getter 
	 * @author rlaib on : 30 juin 2014  12:11:40
	 * @return the nbIndividusGeneres
	 */
	@Column(name = "nb_individus_generes")
	public int getNbIndividusGeneres() {
		return nbIndividusGeneres;
	}

	/**
	 * [Generation.nbIndividusGeneres] Setter 
	 * @author rlaib on : 30 juin 2014  12:11:40
	 * @param nbIndividusGeneres the nbIndividusGeneres to set
	 */
	public void setNbIndividusGeneres(int nbIndividusGeneres) {
		this.nbIndividusGeneres = nbIndividusGeneres;
	}

	/**
	 * [Generation.nbIndividusExistants] Getter 
	 * @author rlaib on : 30 juin 2014  12:11:40
	 * @return the nbIndividusExistants
	 */
	@Column(name = "nb_individus_existants")
	public int getNbIndividusExistants() {
		return nbIndividusExistants;
	}

	/**
	 * [Generation.nbIndividusExistants] Setter 
	 * @author rlaib on : 30 juin 2014  12:11:40
	 * @param nbIndividusExistants the nbIndividusExistants to set
	 */
	public void setNbIndividusExistants(int nbIndividusExistants) {
		this.nbIndividusExistants = nbIndividusExistants;
	}

	/**
	 * [Generation.dateDebutGeneration] Getter 
	 * @author rlaib on : 2 août 2014  10:30:00
	 * @return the dateDebutGeneration
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_debut_generation", nullable = false, length = 29)
	public Date getDateDebutGeneration() {
		return dateDebutGeneration;
	}

	/**
	 * [Generation.dateDebutGeneration] Setter 
	 * @author rlaib on : 2 août 2014  10:30:00
	 * @param dateDebutGeneration the dateDebutGeneration to set
	 */
	public void setDateDebutGeneration(Date dateDebutGeneration) {
		this.dateDebutGeneration = dateDebutGeneration;
	}

	/**
	 * [Generation.dateFinGeneration] Getter 
	 * @author rlaib on : 2 août 2014  10:30:00
	 * @return the dateFinGeneration
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_fin_generation", nullable = false, length = 29)
	public Date getDateFinGeneration() {
		return dateFinGeneration;
	}

	/**
	 * [Generation.dateFinGeneration] Setter 
	 * @author rlaib on : 2 août 2014  10:30:00
	 * @param dateFinGeneration the dateFinGeneration to set
	 */
	public void setDateFinGeneration(Date dateFinGeneration) {
		this.dateFinGeneration = dateFinGeneration;
	}

	


}
