package dz.gov.mesrs.sii.commons.data.model.fve.cursus;

// Generated 21 mai 2014 09:51:36 by Hibernate Tools 3.6.0

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import dz.gov.mesrs.sii.commons.data.model.fve.bac.DossierBachelier;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;

/**
 * DossierEtudiant generated by hbm2java
 */
@Entity
@Table(name = "dossier_etudiant", schema = "cursus")
public class DossierEtudiant implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 21 mai 2014 09:53:00
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Dossier dossier;
	private DossierBachelier dossierBachelier;
	private String numeroMatricule;
	private String etablissementOrigineFr;
	private String etablissementOrigineAr;
	private String photo;
	private RefIndividu individu;
	private RefEtablissement refEtablissement;

	private Set<CongeAcademique> congeAcademiques = new HashSet<CongeAcademique>(
			0);
	private Set<SituationHandicap> situationHandicaps = new HashSet<SituationHandicap>(
			0);
	private List<DossierInscriptionAdministrative> dossierInscriptionAdministratives;
	
	private Set<TitreAcces> titreAcceses = new HashSet<TitreAcces>(0);
	private Set<Formation> formations = new HashSet<Formation>(0);
	private Set<SituationSportive> situationSportives = new HashSet<SituationSportive>(
			0);

	private Set<Diplome> diplomes = new HashSet<Diplome>(0);

	public DossierEtudiant() {
	}

	public DossierEtudiant(Dossier dossier) {
		this.dossier = dossier;
	}

	/*
	 * @GenericGenerator(name = "generator1", strategy = "foreign", parameters =
	 * 
	 * @Parameter(name = "property", value = "dossier"))
	 * 
	 * @Id
	 * 
	 * @GeneratedValue(generator = "generator1")
	 */
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	public Dossier getDossier() {
		return this.dossier;
	}

	public void setDossier(Dossier dossier) {
		this.dossier = dossier;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_dossier_bachelier")
	public DossierBachelier getDossierBachelier() {
		return this.dossierBachelier;
	}

	public void setDossierBachelier(DossierBachelier dossierBachelier) {
		this.dossierBachelier = dossierBachelier;
	}

	@Column(name = "numero_matricule", length = 25)
	public String getNumeroMatricule() {
		return this.numeroMatricule;
	}

	public void setNumeroMatricule(String numeroMatricule) {
		this.numeroMatricule = numeroMatricule;
	}


	@Column(name = "etablissement_origine_fr")
	public String getEtablissementOrigineFr() {
		return this.etablissementOrigineFr;
	}

	public void setEtablissementOrigineFr(String etablissementOrigineFr) {
		this.etablissementOrigineFr = etablissementOrigineFr;
	}

	@Column(name = "etablissement_origine_ar")
	public String getEtablissementOrigineAr() {
		return this.etablissementOrigineAr;
	}

	public void setEtablissementOrigineAr(String etablissementOrigineAr) {
		this.etablissementOrigineAr = etablissementOrigineAr;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dossierEtudiant")
	public Set<CongeAcademique> getCongeAcademiques() {
		return this.congeAcademiques;
	}

	public void setCongeAcademiques(Set<CongeAcademique> congeAcademiques) {
		this.congeAcademiques = congeAcademiques;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dossierEtudiant")
	public Set<SituationHandicap> getSituationHandicaps() {
		return this.situationHandicaps;
	}

	public void setSituationHandicaps(Set<SituationHandicap> situationHandicaps) {
		this.situationHandicaps = situationHandicaps;
	}
	
	

	/**
	 * [DossierEtudiant.dossierInscriptionAdministratives] Getter 
	 * @author MAKERRI Sofiane on : 25 déc. 2014  11:03:17
	 * @return the dossierInscriptionAdministratives
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dossierEtudiant", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("id DESC")
	public List<DossierInscriptionAdministrative> getDossierInscriptionAdministratives() {
		return dossierInscriptionAdministratives;
	}

	/**
	 * [DossierEtudiant.dossierInscriptionAdministratives] Setter 
	 * @author MAKERRI Sofiane on : 25 déc. 2014  11:03:17
	 * @param dossierInscriptionAdministratives the dossierInscriptionAdministratives to set
	 */
	public void setDossierInscriptionAdministratives(
			List<DossierInscriptionAdministrative> dossierInscriptionAdministratives) {
		this.dossierInscriptionAdministratives = dossierInscriptionAdministratives;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dossierEtudiant")
	public Set<TitreAcces> getTitreAcceses() {
		return this.titreAcceses;
	}

	public void setTitreAcceses(Set<TitreAcces> titreAcceses) {
		this.titreAcceses = titreAcceses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dossierEtudiant")
	public Set<Formation> getFormations() {
		return this.formations;
	}

	public void setFormations(Set<Formation> formations) {
		this.formations = formations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dossierEtudiant")
	public Set<SituationSportive> getSituationSportives() {
		return this.situationSportives;
	}

	public void setSituationSportives(Set<SituationSportive> situationSportives) {
		this.situationSportives = situationSportives;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dossierEtudiant")
	public Set<Diplome> getDiplomes() {
		return this.diplomes;
	}

	public void setDiplomes(Set<Diplome> diplomes) {
		this.diplomes = diplomes;
	}

	@Column(name = "photo")
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/**
	 * [DossierEtudiant.individu] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:52:56
	 * @return the individu
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_individu")
	public RefIndividu getIndividu() {
		return individu;
	}

	/**
	 * [DossierEtudiant.individu] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:52:56
	 * @param individu
	 *            the individu to set
	 */
	public void setIndividu(RefIndividu individu) {
		this.individu = individu;
	}

	/**
	 * [DossierEtudiant.refEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 30 d�c. 2014  15:09:45
	 * @return the refEtablissement
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_etablissement")
	public RefEtablissement getRefEtablissement() {
		return refEtablissement;
	}

	/**
	 * [DossierEtudiant.refEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 30 d�c. 2014  15:09:45
	 * @param refEtablissement the refEtablissement to set
	 */
	public void setRefEtablissement(RefEtablissement refEtablissement) {
		this.refEtablissement = refEtablissement;
	}
	
	

}