package dz.gov.mesrs.sii.commons.data.model.fve.cursus;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OuvertureOffreFormation;

/**
 * @author BELDI Jamel on : 8 juin 2014 12:27:09
 */
@Entity
@Table(name = "dossier_transfert", schema = "cursus", uniqueConstraints = @UniqueConstraint(columnNames = {
		"id_annee_academique", "ref_code_etab_accueil", "id_inscription_administrative" }))
public class DossierTransfert implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 8 juin 2014 12:26:56
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private OuvertureOffreFormation ouvertureOffreFormation;
	private DossierInscriptionAdministrative dossierInscriptionAdministrative;
	private Dossier dossier;
	private AnneeAcademique anneeAcademique;
	private String refCodeMotif;
	private String objet;
	private Date dateDepot;
	private String refCodeEtabAccueil;

	private Boolean isEtabOrigineAccepte;
	private Boolean isEtabAccueilAccepte;
	private String refCodeMotifEtabOrigine;
	private String refCodeMotifEtabAccueil;
	private Date dateAvisEtabOrigine;
	private Date dateAvisEtabAccueil;
	private String refCodeTypeTransfert;

	private Boolean casExceptionnel;
	private String refCodeMotifCasExceptionnel;

	private String observationEtabAccueil;
	private String observationEtabOrigine;
	private String observationCommission;

	private String refCodeDomaineOrigine;
	private String refCodeFiliereOrigine;

	private String refCodeDomaineDemande;
	private String refCodeFiliereDemandee;

	private String refCodeEtabOrigine;

	private Boolean isAccordee;

	private Boolean nouveauBachelier;

	public DossierTransfert() {
	}

	public DossierTransfert(DossierInscriptionAdministrative dossierInscriptionAdministrative, Dossier dossier,
			AnneeAcademique anneeAcademique, String objet, Date dateDepot) {
		this.dossierInscriptionAdministrative = dossierInscriptionAdministrative;
		this.dossier = dossier;
		this.anneeAcademique = anneeAcademique;
		this.objet = objet;
		this.dateDepot = dateDepot;
	}

	public DossierTransfert(DossierInscriptionAdministrative dossierInscriptionAdministrative, Dossier dossier,
			AnneeAcademique anneeAcademique, String objet, Date dateDepot, String refCodeEtabAccueil,
			Boolean isEtabOrigineAccepte, Boolean isEtabAccueilAccepte, String refCodeMotifEtabOrigine,
			String refCodeMotifEtabAccueil, Date dateAvisEtabOrigine, Date dateAvisEtabAccueil) {

		this.dossierInscriptionAdministrative = dossierInscriptionAdministrative;
		this.dossier = dossier;
		this.anneeAcademique = anneeAcademique;
		this.objet = objet;
		this.dateDepot = dateDepot;
		this.refCodeEtabAccueil = refCodeEtabAccueil;
		this.isEtabOrigineAccepte = isEtabOrigineAccepte;
		this.isEtabAccueilAccepte = isEtabAccueilAccepte;
		this.refCodeMotifEtabOrigine = refCodeMotifEtabOrigine;
		this.refCodeMotifEtabAccueil = refCodeMotifEtabAccueil;
		this.dateAvisEtabOrigine = dateAvisEtabOrigine;
		this.dateAvisEtabAccueil = dateAvisEtabAccueil;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "id_ouverture_of")
	public OuvertureOffreFormation getOuvertureOffreFormation() {
		return this.ouvertureOffreFormation;
	}

	public void setOuvertureOffreFormation(OuvertureOffreFormation ouvertureOffreFormation) {
		this.ouvertureOffreFormation = ouvertureOffreFormation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_inscription_administrative", nullable = false)
	public DossierInscriptionAdministrative getDossierInscriptionAdministrative() {
		return this.dossierInscriptionAdministrative;
	}

	public void setDossierInscriptionAdministrative(DossierInscriptionAdministrative dossierInscriptionAdministrative) {
		this.dossierInscriptionAdministrative = dossierInscriptionAdministrative;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Dossier getDossier() {
		return this.dossier;
	}

	public void setDossier(Dossier dossier) {
		this.dossier = dossier;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_annee_academique", nullable = false)
	public AnneeAcademique getAnneeAcademique() {
		return this.anneeAcademique;
	}

	public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
		this.anneeAcademique = anneeAcademique;
	}

	@Column(name = "objet", nullable = false)
	public String getObjet() {
		return this.objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_depot", nullable = false, length = 35)
	public Date getDateDepot() {
		return this.dateDepot;
	}

	public void setDateDepot(Date dateDepot) {
		this.dateDepot = dateDepot;
	}

	@Column(name = "ref_code_etab_accueil")
	public String getRefCodeEtabAccueil() {
		return this.refCodeEtabAccueil;
	}

	public void setRefCodeEtabAccueil(String refCodeEtabAccueil) {
		this.refCodeEtabAccueil = refCodeEtabAccueil;
	}

	@Column(name = "ref_code_motif_etab_origine")
	public String getRefCodeMotifEtabOrigine() {
		return this.refCodeMotifEtabOrigine;
	}

	public void setRefCodeMotifEtabOrigine(String refCodeMotifEtabOrigine) {
		this.refCodeMotifEtabOrigine = refCodeMotifEtabOrigine;
	}

	@Column(name = "ref_code_motif_etab_accueil")
	public String getRefCodeMotifEtabAccueil() {
		return this.refCodeMotifEtabAccueil;
	}

	public void setRefCodeMotifEtabAccueil(String refCodeMotifEtabAccueil) {
		this.refCodeMotifEtabAccueil = refCodeMotifEtabAccueil;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_avis_etab_origine", length = 35)
	public Date getDateAvisEtabOrigine() {
		return this.dateAvisEtabOrigine;
	}

	public void setDateAvisEtabOrigine(Date dateAvisEtabOrigine) {
		this.dateAvisEtabOrigine = dateAvisEtabOrigine;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_avis_etab_accueil", length = 35)
	public Date getDateAvisEtabAccueil() {
		return this.dateAvisEtabAccueil;
	}

	public void setDateAvisEtabAccueil(Date dateAvisEtabAccueil) {
		this.dateAvisEtabAccueil = dateAvisEtabAccueil;
	}

	/**
	 * [DossierTransfert.refCodeTypeTransfert] Getter
	 * 
	 * @author BELDI Jamel on : 8 juin 2014 16:56:12
	 * @return the refCodeTypeTransfert
	 */
	@Column(name = "ref_code_type_transfert")
	public String getRefCodeTypeTransfert() {
		return refCodeTypeTransfert;
	}

	/**
	 * [DossierTransfert.refCodeTypeTransfert] Setter
	 * 
	 * @author BELDI Jamel on : 8 juin 2014 16:56:12
	 * @param refCodeTypeTransfert
	 *            the refCodeTypeTransfert to set
	 */
	public void setRefCodeTypeTransfert(String refCodeTypeTransfert) {
		this.refCodeTypeTransfert = refCodeTypeTransfert;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 16:06:14
	 * @return
	 */
	@Column(name = "cas_exceptionnel")
	public Boolean getCasExceptionnel() {
		return casExceptionnel;
	}

	public void setCasExceptionnel(Boolean casExceptionnel) {
		this.casExceptionnel = casExceptionnel;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 16:06:14
	 * @return
	 */
	@Column(name = "ref_code_motif_cas_exceptionnel")
	public String getRefCodeMotifCasExceptionnel() {
		return refCodeMotifCasExceptionnel;
	}

	public void setRefCodeMotifCasExceptionnel(String refCodeMotifCasExceptionnel) {
		this.refCodeMotifCasExceptionnel = refCodeMotifCasExceptionnel;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 16:06:14
	 * @return
	 */
	@Column(name = "observation_etab_accueil")
	public String getObservationEtabAccueil() {
		return observationEtabAccueil;
	}

	public void setObservationEtabAccueil(String observationEtabAccueil) {
		this.observationEtabAccueil = observationEtabAccueil;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 16:06:14
	 * @return
	 */
	@Column(name = "observation_etab_origine")
	public String getObservationEtabOrigine() {
		return observationEtabOrigine;
	}

	public void setObservationEtabOrigine(String observationEtabOrigine) {
		this.observationEtabOrigine = observationEtabOrigine;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 16:06:14
	 * @return
	 */
	@Column(name = "observation_commission")
	public String getObservationCommission() {
		return observationCommission;
	}

	public void setObservationCommission(String observationCommission) {
		this.observationCommission = observationCommission;
	}

	@Column(name = "ref_code_domaine_origine")
	public String getRefCodeDomaineOrigine() {
		return refCodeDomaineOrigine;
	}

	public void setRefCodeDomaineOrigine(String refCodeDomaineOrigine) {
		this.refCodeDomaineOrigine = refCodeDomaineOrigine;
	}

	@Column(name = "ref_code_domaine_demande")
	public String getRefCodeDomaineDemande() {
		return refCodeDomaineDemande;
	}

	public void setRefCodeDomaineDemande(String refCodeDomaineDemande) {
		this.refCodeDomaineDemande = refCodeDomaineDemande;
	}

	@Column(name = "ref_code_filiere_demandee")
	public String getRefCodeFiliereDemandee() {
		return refCodeFiliereDemandee;
	}

	public void setRefCodeFiliereDemandee(String refCodeFiliereDemandee) {
		this.refCodeFiliereDemandee = refCodeFiliereDemandee;
	}

	@Column(name = "ref_code_filiere_origine")
	public String getRefCodeFiliereOrigine() {
		return refCodeFiliereOrigine;
	}

	public void setRefCodeFiliereOrigine(String refCodeFiliereOrigine) {
		this.refCodeFiliereOrigine = refCodeFiliereOrigine;
	}

	@Column(name = "ref_code_etab_origine")
	public String getRefCodeEtabOrigine() {
		return refCodeEtabOrigine;
	}

	public void setRefCodeEtabOrigine(String refCodeEtabOrigine) {
		this.refCodeEtabOrigine = refCodeEtabOrigine;
	}

	@Column(name = "etab_origine_accepte")
	public Boolean getIsEtabOrigineAccepte() {
		return isEtabOrigineAccepte;
	}

	public void setIsEtabOrigineAccepte(Boolean isEtabOrigineAccepte) {
		this.isEtabOrigineAccepte = isEtabOrigineAccepte;
	}

	@Column(name = "etab_accueil_accepte")
	public Boolean getIsEtabAccueilAccepte() {
		return isEtabAccueilAccepte;
	}

	public void setIsEtabAccueilAccepte(Boolean isEtabAccueilAccepte) {
		this.isEtabAccueilAccepte = isEtabAccueilAccepte;
	}

	@Column(name = "demande_accordee")
	public Boolean getIsAccordee() {
		return isAccordee;
	}

	public void setIsAccordee(Boolean isAccordee) {
		this.isAccordee = isAccordee;
	}

	@Column(name = "nouveau_bachelier")
	public Boolean getNouveauBachelier() {
		return nouveauBachelier;
	}

	public void setNouveauBachelier(Boolean nouveauBachelier) {
		this.nouveauBachelier = nouveauBachelier;
	}

	@Column(name = "ref_code_motif")
	public String getRefCodeMotif() {
		return refCodeMotif;
	}

	public void setRefCodeMotif(String refCodeMotif) {
		this.refCodeMotif = refCodeMotif;
	}
}
