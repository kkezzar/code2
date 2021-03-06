package dz.gov.mesrs.sii.commons.data.model.referentiel;

// Generated 20 mai 2014 15:30:43 by Hibernate Tools 3.6.0

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
import javax.persistence.UniqueConstraint;

/**
 * RefTypePieceConstitutive generated by hbm2java
 */
@Entity
@Table(name = "ref_type_piece_constitutive", schema = "ppm", uniqueConstraints = @UniqueConstraint(columnNames = {
		"type_dossier", "type_piece" }))
public class RefTypePieceConstitutive implements java.io.Serializable {

	private int id;
	private Nomenclature ncTypePiece;
	private Nomenclature ncTypeDossier;
	private Integer rang;
	private Date dateDebut;
	private Date dateFin;
	private Boolean obligatoire;
	private Boolean aFournir;
	private Integer nombre;

	public RefTypePieceConstitutive() {
	}

	public RefTypePieceConstitutive(int id, Date dateDebut) {
		this.id = id;
		this.dateDebut = dateDebut;
	}

	public RefTypePieceConstitutive(int id,
			Nomenclature ncTypePiece,
			Nomenclature ncTypeDossier, Integer rang,
			Date dateDebut, Date dateFin, Boolean obligatoire,
			Boolean aFournir, Integer nombre) {
		this.id = id;
		this.ncTypePiece = ncTypePiece;
		this.ncTypeDossier = ncTypeDossier;
		this.rang = rang;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.obligatoire = obligatoire;
		this.aFournir = aFournir;
		this.nombre = nombre;
	}
	
	@SequenceGenerator(name="ref_piece_constitutive_id_seq_gen", sequenceName="ppm.ref_piece_constitutive_id_seq")
	@Id @GeneratedValue(generator="ref_piece_constitutive_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_piece")
	public Nomenclature getNcTypePiece() {
		return this.ncTypePiece;
	}

	public void setNcTypePiece(Nomenclature ncTypePiece) {
		this.ncTypePiece = ncTypePiece;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_dossier")
	public Nomenclature getNcTypeDossier() {
		return this.ncTypeDossier;
	}

	public void setNcTypeDossier(
			Nomenclature ncTypeDossier) {
		this.ncTypeDossier = ncTypeDossier;
	}

	@Column(name = "rang")
	public Integer getRang() {
		return this.rang;
	}

	public void setRang(Integer rang) {
		this.rang = rang;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_debut", nullable = false, length = 13)
	public Date getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_fin", length = 13)
	public Date getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	@Column(name = "obligatoire")
	public Boolean getObligatoire() {
		return this.obligatoire;
	}

	public void setObligatoire(Boolean obligatoire) {
		this.obligatoire = obligatoire;
	}

	@Column(name = "a_fournir")
	public Boolean getAFournir() {
		return this.aFournir;
	}

	public void setAFournir(Boolean aFournir) {
		this.aFournir = aFournir;
	}

	@Column(name = "nombre")
	public Integer getNombre() {
		return this.nombre;
	}

	public void setNombre(Integer nombre) {
		this.nombre = nombre;
	}

}
