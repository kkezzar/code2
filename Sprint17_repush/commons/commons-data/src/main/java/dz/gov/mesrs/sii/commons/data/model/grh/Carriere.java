package dz.gov.mesrs.sii.commons.data.model.grh;

// Generated 21 oct. 2014 11:36:27 by Hibernate Tools 3.6.0

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

/**
 * Carriere generated by hbm2java
 */
@Entity
@Table(name = "carriere", schema = "grh")
public class Carriere implements java.io.Serializable, Identifiable<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Nomenclature nomenclatureByCarriere;
	private Grade grade;
	private Date dateEffetGrade;
	private Corps corps;
	private Date dateEffetCorps;
	private DossierEmploye dossierEmploye;
	private CategorieProfessionnelle categorieProfessionnelle;
	private Date dateEffet;
	private Date dateChangement;
	private GrilleIndiciaire grilleIndiciaire;
	private Date dateEffetEchelon;
	private Integer indice;	
	private String motif;
	private Boolean confirm;
	private String objetFormation;
	private Date dateDebutFormation;
	private Date dateFinFormation;
	private Date dateExament;
	private Date dateObtentionDiplome;
	private Nomenclature nomenclatureBydiplome;
	private Nomenclature nomenclatureBytypePromotion;
	private Nomenclature nomenclatureBytitre;
	

	// private EmployePropose employePropose;

	public Carriere() {
	}

	public Carriere(Integer id) {
		this.id = id;
	}

	public Carriere(Integer id, Grade grade, Corps corps, DossierEmploye dossierEmploye, String motif,
			CategorieProfessionnelle categorieProfessionnelle, Date dateEffet, GrilleIndiciaire grilleIndiciaire,
			Integer indice, Nomenclature nomenclatureByCarriere, Date dateChangement, Date dateEffetGrade,
			Date dateEffetEchelon, Date dateEffetCorps,Boolean confirm,String objetFormation, Date dateDebutFormation,
			Date dateFinFormation, Date dateExament,Date dateObtentionDiplome, Nomenclature nomenclatureBydiplome,
	 Nomenclature nomenclatureBytypePromotion,	 Nomenclature nomenclatureBytitre) {
		this.id = id;
		this.grade = grade;
		this.corps = corps;
		this.dossierEmploye = dossierEmploye;
		this.categorieProfessionnelle = categorieProfessionnelle;
		this.dateEffet = dateEffet;
		this.grilleIndiciaire = grilleIndiciaire;
		this.indice = indice;
		this.nomenclatureByCarriere = nomenclatureByCarriere;
		this.dateChangement = dateChangement;
		this.motif = motif;
		this.dateEffetCorps = dateEffetCorps;
		this.dateEffetEchelon = dateEffetEchelon;
		this.dateEffetGrade = dateEffetGrade;
		this.confirm = confirm;
		this.objetFormation=objetFormation;
		this.dateDebutFormation=dateDebutFormation;
		this.dateFinFormation=dateFinFormation;
		this.dateExament=dateExament;
		this.dateObtentionDiplome=dateObtentionDiplome;
		this. nomenclatureBydiplome= nomenclatureBydiplome;
		this. nomenclatureBytypePromotion =  nomenclatureBytypePromotion;
		this. nomenclatureBytitre=  nomenclatureBytitre;
		// this.employePropose=employePropose;
	}

	@Id
	@SequenceGenerator(name = "grh_carriere_id_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grh_carriere_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "grade")
	public Grade getGrade() {
		return this.grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "corps")
	public Corps getCorps() {
		return this.corps;
	}

	public void setCorps(Corps corps) {
		this.corps = corps;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employe")
	public DossierEmploye getDossierEmploye() {
		return this.dossierEmploye;
	}

	public void setDossierEmploye(DossierEmploye dossierEmploye) {
		this.dossierEmploye = dossierEmploye;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categorie_profess")
	public CategorieProfessionnelle getCategorieProfessionnelle() {
		return this.categorieProfessionnelle;
	}

	public void setCategorieProfessionnelle(CategorieProfessionnelle categorieProfessionnelle) {
		this.categorieProfessionnelle = categorieProfessionnelle;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_effet", length = 13)
	public Date getDateEffet() {
		return dateEffet;
	}

	
	public void setDateEffet(Date dateEffet) {
		this.dateEffet = dateEffet;
	}

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "echelon")
	public GrilleIndiciaire getGrilleIndiciaire() {
		return grilleIndiciaire;
	}

	
	public void setGrilleIndiciaire(GrilleIndiciaire grilleIndiciaire) {
		this.grilleIndiciaire = grilleIndiciaire;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}

	@Column(name = "indice")
	public Integer getIndice() {
		return this.indice;
	}

	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nomenclature_carriere")
	public Nomenclature getNomenclatureByCarriere() {
		return nomenclatureByCarriere;
	}

	
	public void setNomenclatureByCarriere(Nomenclature nomenclatureByCarriere) {
		this.nomenclatureByCarriere = nomenclatureByCarriere;
	}

	

	@Temporal(TemporalType.DATE)
	@Column(name = "date_changement", length = 13)
	public Date getDateChangement() {
		return dateChangement;
	}

	
	public void setDateChangement(Date dateChangement) {
		this.dateChangement = dateChangement;
	}

	
	@Column(name = "motif")
	public String getMotif() {
		return motif;
	}

	
	public void setMotif(String motif) {
		this.motif = motif;
	}

	// @OneToOne(fetch = FetchType.LAZY, mappedBy = "carriere", cascade =
	// CascadeType.ALL)
	// public EmployePropose getEmployePropose() {
	// return employePropose;
	// }

	/**
	 * [Carriere.dateEffetGrade] Getter
	 * 
	 * @author obelbrik on : 21 dc. 2014 14:03:59
	 * @return the dateEffetGrade
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "date_effet_grade", length = 13)
	public Date getDateEffetGrade() {
		return dateEffetGrade;
	}

	/**
	 * [Carriere.dateEffetGrade] Setter
	 * 
	 * @author obelbrik on : 21 dc. 2014 14:03:59
	 * @param dateEffetGrade
	 *            the dateEffetGrade to set
	 */
	public void setDateEffetGrade(Date dateEffetGrade) {
		this.dateEffetGrade = dateEffetGrade;
	}

	/**
	 * [Carriere.dateEffetCorps] Getter
	 * 
	 * @author obelbrik on : 21 dc. 2014 14:03:59
	 * @return the dateEffetCorps
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "date_effet_corps", length = 13)
	public Date getDateEffetCorps() {
		return dateEffetCorps;
	}

	/**
	 * [Carriere.dateEffetCorps] Setter
	 * 
	 * @author obelbrik on : 21 dc. 2014 14:03:59
	 * @param dateEffetCorps
	 *            the dateEffetCorps to set
	 */
	public void setDateEffetCorps(Date dateEffetCorps) {
		this.dateEffetCorps = dateEffetCorps;
	}

	/**
	 * [Carriere.dateEffetEchelon] Getter
	 * 
	 * @author obelbrik on : 21 dc. 2014 14:03:59
	 * @return the dateEffetEchelon
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "date_effet_echelon", length = 13)
	public Date getDateEffetEchelon() {
		return dateEffetEchelon;
	}

	/**
	 * [Carriere.dateEffetEchelon] Setter
	 * 
	 * @author obelbrik on : 21 dc. 2014 14:03:59
	 * @param dateEffetEchelon
	 *            the dateEffetEchelon to set
	 */
	public void setDateEffetEchelon(Date dateEffetEchelon) {
		this.dateEffetEchelon = dateEffetEchelon;
	}

	/**
	 * [Carriere.employePropose] Setter
	 * 
	 * @author obelbrik on : 14 dc. 2014 10:18:50
	 * @param employePropose
	 *            the employePropose to set
	 */
	// public void setEmployePropose(EmployePropose employePropose) {
	// this.employePropose = employePropose;
	// }
	@Column(name = "confirm")
	public Boolean getConfirm() {
		return confirm;
	}

	public void setConfirm(Boolean confirm) {
		this.confirm = confirm;
	}
	@Column(name = "objet_formation")
	public String getObjetFormation() {
		return objetFormation;
	}

	public void setObjetFormation(String objetFormation) {
		this.objetFormation = objetFormation;
	}
    
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_debut_formation", length = 13)
	public Date getDateDebutFormation() {
		return dateDebutFormation;
	}

	public void setDateDebutFormation(Date dateDebutFormation) {
		this.dateDebutFormation = dateDebutFormation;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_fin_formation", length = 13)
	public Date getDateFinFormation() {
		return dateFinFormation;
	}

	public void setDateFinFormation(Date dateFinFormation) {
		this.dateFinFormation = dateFinFormation;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "date_exament", length = 13)
	public Date getDateExament() {
		return dateExament;
	}

	public void setDateExament(Date dateExament) {
		this.dateExament = dateExament;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "date_obtention_diplome", length = 13)
	public Date getDateObtentionDiplome() {
		return dateObtentionDiplome;
	}

	public void setDateObtentionDiplome(Date dateObtentionDiplome) {
		this.dateObtentionDiplome = dateObtentionDiplome;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_promotion")
	public Nomenclature getNomenclatureBytypePromotion() {
		return nomenclatureBytypePromotion;
	}

	public void setNomenclatureBytypePromotion(
			Nomenclature nomenclatureBytypePromotion) {
		this.nomenclatureBytypePromotion = nomenclatureBytypePromotion;
	}

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diplome")	
	public Nomenclature getNomenclatureBydiplome() {
		return nomenclatureBydiplome;
	}

	public void setNomenclatureBydiplome(Nomenclature nomenclatureBydiplome) {
		this.nomenclatureBydiplome = nomenclatureBydiplome;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "titre")	
	public Nomenclature getNomenclatureBytitre() {
		return nomenclatureBytitre;
	}

	public void setNomenclatureBytitre(Nomenclature nomenclatureBytitre) {
		this.nomenclatureBytitre = nomenclatureBytitre;
	}

	
	

	@Transient
	@Override
	public Integer getIdenfiant() {
		return this.getId();
	}

	
	

	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

}