package dz.gov.mesrs.sii.commons.data.model.fve.offreformation;

// Generated 12 mai 2014 12:43:56 by Hibernate Tools 3.6.0

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

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;

/**
 * @author BELDI Jamel on : 12 mai 2014 14:12:54
 */
@Entity
@Table(name = "ouverture_offre_formation", schema = "lmd")
public class OuvertureOffreFormation implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 14:12:35
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private OffreFormation offreFormation;
	private AnneeAcademique anneeAcademique;
	private int effectifMin;
	private int effectifMax;
	private Date dateOuverture;
	private boolean estOuverte;
	private Date dateFermeture;
	private RefEtablissement refEtablissement;
//	private Set<GroupePedagogique> groupePedagogiques = new HashSet<GroupePedagogique>(
//			0);

	public OuvertureOffreFormation() {
	}
	public OuvertureOffreFormation(int id) {
		this.id = id;
	}
	public OuvertureOffreFormation(int id, OffreFormation offreFormation,
			AnneeAcademique anneeAcademique, 
			int effectifMin, int effectifMax, Date dateOuverture,
			boolean estOuverte) {
		this.id = id;
		this.offreFormation = offreFormation;
		this.anneeAcademique = anneeAcademique;
		this.effectifMin = effectifMin;
		this.effectifMax = effectifMax;
		this.dateOuverture = dateOuverture;
		this.estOuverte = estOuverte;
	}

	public OuvertureOffreFormation(int id, OffreFormation offreFormation,
			AnneeAcademique anneeAcademique, 
			int effectifMin, int effectifMax, Date dateOuverture,
			boolean estOuverte, Date dateFermeture) {
		this.id = id;
		this.offreFormation = offreFormation;
		this.anneeAcademique = anneeAcademique;
		this.effectifMin = effectifMin;
		this.effectifMax = effectifMax;
		this.dateOuverture = dateOuverture;
		this.estOuverte = estOuverte;
		this.dateFermeture = dateFermeture;
	}

	@SequenceGenerator(name = "ouverture_of_id_seq_gen", sequenceName = "lmd.ouverture_of_id_seq")
	@Id
	@GeneratedValue(generator = "ouverture_of_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_offre_formation", nullable = false)
	public OffreFormation getOffreFormation() {
		return this.offreFormation;
	}

	public void setOffreFormation(OffreFormation offreFormation) {
		this.offreFormation = offreFormation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_annee_academique", nullable = false)
	public AnneeAcademique getAnneeAcademique() {
		return this.anneeAcademique;
	}

	public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
		this.anneeAcademique = anneeAcademique;
	}

	@Column(name = "effectif_min", nullable = false)
	public int getEffectifMin() {
		return this.effectifMin;
	}

	public void setEffectifMin(int effectifMin) {
		this.effectifMin = effectifMin;
	}

	@Column(name = "effectif_max", nullable = false)
	public int getEffectifMax() {
		return this.effectifMax;
	}

	public void setEffectifMax(int effectifMax) {
		this.effectifMax = effectifMax;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_ouverture", nullable = false, length = 35)
	public Date getDateOuverture() {
		return this.dateOuverture;
	}

	public void setDateOuverture(Date dateOuverture) {
		this.dateOuverture = dateOuverture;
	}

	@Column(name = "est_ouverte", nullable = false)
	public boolean isEstOuverte() {
		return this.estOuverte;
	}

	public void setEstOuverte(boolean estOuverte) {
		this.estOuverte = estOuverte;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_fermeture", length = 35)
	public Date getDateFermeture() {
		return this.dateFermeture;
	}

	public void setDateFermeture(Date dateFermeture) {
		this.dateFermeture = dateFermeture;
	}
	/**
	 * [OuvertureOffreFormation.refEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 30 nov. 2014  12:22:50
	 * @return the refEtablissement
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_etablissement", nullable = false)
	public RefEtablissement getRefEtablissement() {
		return refEtablissement;
	}
	/**
	 * [OuvertureOffreFormation.refEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 30 nov. 2014  12:22:50
	 * @param refEtablissement the refEtablissement to set
	 */
	public void setRefEtablissement(RefEtablissement refEtablissement) {
		this.refEtablissement = refEtablissement;
	}

}
