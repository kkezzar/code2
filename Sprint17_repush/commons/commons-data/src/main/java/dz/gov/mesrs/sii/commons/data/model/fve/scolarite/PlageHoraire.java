package dz.gov.mesrs.sii.commons.data.model.fve.scolarite;

// Generated 7 oct. 2014 17:24:06 by Hibernate Tools 3.6.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;

/**
 * 
 * @author BELDI Jamel  on : 7 oct. 2014  17:46:51
 */
@Entity
@Table(name = "plage_horaire", schema = "fve_scolarite")
public class PlageHoraire implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 7 oct. 2014  17:46:44
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private AnneeAcademique anneeAcademique;
	private RefEtablissement refEtablissement;
	private String code;
	private short numero;
	private String libelleFr;
	private String libelleAr;
	private Date heureDebut;
	private Date heureFin;
	private Set<CelluleHoraire> celluleHoraires = new HashSet<CelluleHoraire>(0);

	public PlageHoraire() {
	}

	public PlageHoraire(int id, String code, short numero, Date heureDebut,
			Date heureFin) {
		this.id = id;
		this.code = code;
		this.numero = numero;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
	}

	public PlageHoraire(int id, AnneeAcademique anneeAcademique,
			RefEtablissement refEtablissement, String code, short numero,
			String libelleFr, String libelleAr, Date heureDebut, Date heureFin,
			Set<CelluleHoraire> celluleHoraires) {
		this.id = id;
		this.anneeAcademique = anneeAcademique;
		this.refEtablissement = refEtablissement;
		this.code = code;
		this.numero = numero;
		this.libelleFr = libelleFr;
		this.libelleAr = libelleAr;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.celluleHoraires = celluleHoraires;
	}

	@Id
	@SequenceGenerator(name="plage_horaire_id_seq_gen", sequenceName="fve_scolarite.plage_horaire_id_seq")
	@GeneratedValue(generator="plage_horaire_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_annee_academique")
	public AnneeAcademique getAnneeAcademique() {
		return this.anneeAcademique;
	}

	public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
		this.anneeAcademique = anneeAcademique;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_etablissement")
	public RefEtablissement getRefEtablissement() {
		return this.refEtablissement;
	}

	public void setRefEtablissement(RefEtablissement refEtablissement) {
		this.refEtablissement = refEtablissement;
	}

	@Column(name = "code", nullable = false)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "numero", nullable = false)
	public short getNumero() {
		return this.numero;
	}

	public void setNumero(short numero) {
		this.numero = numero;
	}

	@Column(name = "libelle_fr")
	public String getLibelleFr() {
		return this.libelleFr;
	}

	public void setLibelleFr(String libelleFr) {
		this.libelleFr = libelleFr;
	}

	@Column(name = "libelle_ar")
	public String getLibelleAr() {
		return this.libelleAr;
	}

	public void setLibelleAr(String libelleAr) {
		this.libelleAr = libelleAr;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "heure_debut", nullable = false, length = 15)
	public Date getHeureDebut() {
		return this.heureDebut;
	}

	public void setHeureDebut(Date heureDebut) {
		this.heureDebut = heureDebut;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "heure_fin", nullable = false, length = 15)
	public Date getHeureFin() {
		return this.heureFin;
	}

	public void setHeureFin(Date heureFin) {
		this.heureFin = heureFin;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "plageHoraire")
	public Set<CelluleHoraire> getCelluleHoraires() {
		return this.celluleHoraires;
	}

	public void setCelluleHoraires(Set<CelluleHoraire> celluleHoraires) {
		this.celluleHoraires = celluleHoraires;
	}

}
