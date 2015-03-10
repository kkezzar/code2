package dz.gov.mesrs.sii.commons.data.model.fve.cursus;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;

/**
 * Dossier generated by hbm2java
 */
@Entity
@Table(name = "dossier", schema = "cursus")
public class Dossier implements java.io.Serializable {

	/**
	 * serialVersionUID
	 *
	 * @author BELDI Jamel on : 21 mai 2014 09:18:18
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private SituationEntite situationEntite;
	private String typeDossier;
	private Set<PieceConstitutive> pieceConstitutives = new LinkedHashSet<PieceConstitutive>(0);
	private DossierInscriptionAdministrative dossierInscriptionAdministrative;
	private DossierEtudiant dossierEtudiant;
	private DossierTransfert dossierTransfert;
	private Date dateCreation;
	private CongeAcademique congeAcademique;

	public Dossier() {
	}

	public Dossier(int id) {
		this.id = id;
	}

	// @SequenceGenerator(name = "dossier_id_seq_gen", initialValue = 1)
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
	// "dossier_id_seq_gen")
	@Id
	@SequenceGenerator(name = "dossier_id_seq_gen", sequenceName = "cursus.dossier_id_seq")
	@GeneratedValue(generator = "dossier_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_situation")
	public SituationEntite getSituationEntite() {
		return this.situationEntite;
	}

	public void setSituationEntite(SituationEntite situationEntite) {
		this.situationEntite = situationEntite;
	}

	@Column(name = "type_dossier", length = 30)
	public String getTypeDossier() {
		return this.typeDossier;
	}

	public void setTypeDossier(String typeDossier) {
		this.typeDossier = typeDossier;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dossier", cascade = CascadeType.ALL)
	public Set<PieceConstitutive> getPieceConstitutives() {
		return this.pieceConstitutives;
	}

	public void setPieceConstitutives(Set<PieceConstitutive> pieceConstitutives) {
		this.pieceConstitutives = pieceConstitutives;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "dossier")
	public DossierInscriptionAdministrative getDossierInscriptionAdministrative() {
		return this.dossierInscriptionAdministrative;
	}

	public void setDossierInscriptionAdministrative(DossierInscriptionAdministrative dossierInscriptionAdministrative) {
		this.dossierInscriptionAdministrative = dossierInscriptionAdministrative;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "dossier")
	public DossierEtudiant getDossierEtudiant() {
		return this.dossierEtudiant;
	}

	public void setDossierEtudiant(DossierEtudiant dossierEtudiant) {
		this.dossierEtudiant = dossierEtudiant;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_creation", length = 35)
	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * [Dossier.dossierTransfert] Getter
	 *
	 * @author BELDI Jamel on : 10 juin 2014 16:36:49
	 * @return the dossierTransfert
	 */
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "dossier")
	public DossierTransfert getDossierTransfert() {
		return dossierTransfert;
	}

	/**
	 * [Dossier.dossierTransfert] Setter
	 *
	 * @author BELDI Jamel on : 10 juin 2014 16:36:49
	 * @param dossierTransfert
	 *            the dossierTransfert to set
	 */
	public void setDossierTransfert(DossierTransfert dossierTransfert) {
		this.dossierTransfert = dossierTransfert;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "dossier")
	public CongeAcademique getCongeAcademique() {
		return this.congeAcademique;
	}

	public void setCongeAcademique(CongeAcademique congeAcademique) {
		this.congeAcademique = congeAcademique;
	}

}