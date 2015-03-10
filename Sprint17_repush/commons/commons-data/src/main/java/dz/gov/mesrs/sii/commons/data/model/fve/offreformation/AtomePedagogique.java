package dz.gov.mesrs.sii.commons.data.model.fve.offreformation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

/**
 * Classe d'entité représentant une atome pédagogique
 * 
 * @author Kheireddine OMRANI
 * 
 */
@Entity
@Table(name = "ap", schema = "lmd")
public class AtomePedagogique implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private MatiereConstitutive matiereConstitutive;
	private String code;
	private Double volumeHoraire;
	private Double seuil;
	private Double pourcentage;
	private Integer groupesPrevus;
	private Nomenclature ncTypeAp;
	private Nomenclature ncModeEnseignementAp;

	// private Set<GroupePedagogiqueAp> groupePedagogiqueAps = new
	// HashSet<GroupePedagogiqueAp>(
	// 0);

	public AtomePedagogique() {
	}

	@SequenceGenerator(name = "ap_id_seq", sequenceName = "lmd.ap_id_seq")
	@Id
	@GeneratedValue(generator = "ap_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_matiere_constitutive")
	public MatiereConstitutive getMatiereConstitutive() {
		return this.matiereConstitutive;
	}

	public void setMatiereConstitutive(MatiereConstitutive matiereConstitutive) {
		this.matiereConstitutive = matiereConstitutive;
	}

	@Column(name = "code", nullable = false, length = 10, unique = true)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "volume_horaire", precision = 17, scale = 17, nullable = false)
	public Double getVolumeHoraire() {
		return this.volumeHoraire;
	}

	public void setVolumeHoraire(Double volumeHoraire) {
		this.volumeHoraire = volumeHoraire;
	}

	@Column(name = "pourcentage_evaluation", precision = 17, scale = 17)
	public Double getPourcentage() {
		return this.pourcentage;
	}

	public void setPourcentage(Double pourcentage) {
		this.pourcentage = pourcentage;
	}

	@Column(name = "seuil", precision = 17, scale = 17)
	public Double getSeuil() {
		return this.seuil;
	}

	public void setSeuil(Double seuil) {
		this.seuil = seuil;
	}

	@Column(name = "groupes_prevus")
	public Integer getGroupesPrevus() {
		return this.groupesPrevus;
	}

	public void setGroupesPrevus(Integer groupesPrevus) {
		this.groupesPrevus = groupesPrevus;
	}

	/**
	 * [AtomePedagogique.ncTypeAp] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 12:17:58
	 * @return the ncTypeAp
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nc_type_ap")
	public Nomenclature getNcTypeAp() {
		return ncTypeAp;
	}

	/**
	 * [AtomePedagogique.ncTypeAp] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 12:17:58
	 * @param ncTypeAp
	 *            the ncTypeAp to set
	 */
	public void setNcTypeAp(Nomenclature ncTypeAp) {
		this.ncTypeAp = ncTypeAp;
	}

	/**
	 * [AtomePedagogique.ncModeEnseignementAp] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 12:17:58
	 * @return the ncModeEnseignementAp
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nc_mode_enseignement")
	public Nomenclature getNcModeEnseignementAp() {
		return ncModeEnseignementAp;
	}

	/**
	 * [AtomePedagogique.ncModeEnseignementAp] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 12:17:58
	 * @param ncModeEnseignementAp
	 *            the ncModeEnseignementAp to set
	 */
	public void setNcModeEnseignementAp(Nomenclature ncModeEnseignementAp) {
		this.ncModeEnseignementAp = ncModeEnseignementAp;
	}

}
