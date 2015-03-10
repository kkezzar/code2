package dz.gov.mesrs.sii.commons.data.model.fve.scolarite;

// Generated 7 oct. 2014 17:24:06 by Hibernate Tools 3.6.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OuvertureOffreFormation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefLieu;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefStructure;

/**
 * 
 * @author BELDI Jamel  on : 7 oct. 2014  17:49:29
 */
@Entity
@Table(name = "affectation_lieu_structure", schema = "fve_scolarite")
public class AffectationLieuStructure implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 7 oct. 2014  17:49:48
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Nomenclature nomenclatureByIdNcPeriodicite;
	private Nomenclature nomenclatureByIdNcPeriode;
	private RefStructure refStructure;
	private RefLieu refLieu;
	private CelluleHoraire celluleHoraire;
	private AnneeAcademique anneeAcademique;
	private OuvertureOffreFormation ouvertureOffreFormation;

	public AffectationLieuStructure() {
	}

	public AffectationLieuStructure(int id,
			Nomenclature nomenclatureByIdNcPeriodicite,
			RefStructure refStructure, RefLieu refLieu,
			AnneeAcademique anneeAcademique) {
		this.id = id;
		this.nomenclatureByIdNcPeriodicite = nomenclatureByIdNcPeriodicite;
		this.refStructure = refStructure;
		this.refLieu = refLieu;
		this.anneeAcademique = anneeAcademique;
	}

	public AffectationLieuStructure(int id,
			Nomenclature nomenclatureByIdNcPeriodicite,
			Nomenclature nomenclatureByIdNcPeriode, RefStructure refStructure,
			RefLieu refLieu, CelluleHoraire celluleHoraire,
			AnneeAcademique anneeAcademique) {
		this.id = id;
		this.nomenclatureByIdNcPeriodicite = nomenclatureByIdNcPeriodicite;
		this.nomenclatureByIdNcPeriode = nomenclatureByIdNcPeriode;
		this.refStructure = refStructure;
		this.refLieu = refLieu;
		this.celluleHoraire = celluleHoraire;
		this.anneeAcademique = anneeAcademique;
	}

	@Id
	@SequenceGenerator(name="affectation_lieu_structure_id_seq_gen", sequenceName="fve_scolarite.affectation_lieu_structure_id_seq")
	@GeneratedValue(generator="affectation_lieu_structure_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_nc_periodicite", nullable = false)
	public Nomenclature getNomenclatureByIdNcPeriodicite() {
		return this.nomenclatureByIdNcPeriodicite;
	}

	public void setNomenclatureByIdNcPeriodicite(
			Nomenclature nomenclatureByIdNcPeriodicite) {
		this.nomenclatureByIdNcPeriodicite = nomenclatureByIdNcPeriodicite;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_nc_periode")
	public Nomenclature getNomenclatureByIdNcPeriode() {
		return this.nomenclatureByIdNcPeriode;
	}

	public void setNomenclatureByIdNcPeriode(
			Nomenclature nomenclatureByIdNcPeriode) {
		this.nomenclatureByIdNcPeriode = nomenclatureByIdNcPeriode;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_structure", nullable = false)
	public RefStructure getRefStructure() {
		return this.refStructure;
	}

	public void setRefStructure(RefStructure refStructure) {
		this.refStructure = refStructure;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_lieu", nullable = false)
	public RefLieu getRefLieu() {
		return this.refLieu;
	}

	public void setRefLieu(RefLieu refLieu) {
		this.refLieu = refLieu;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cellule_horaire")
	public CelluleHoraire getCelluleHoraire() {
		return this.celluleHoraire;
	}

	public void setCelluleHoraire(CelluleHoraire celluleHoraire) {
		this.celluleHoraire = celluleHoraire;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_annee_academique", nullable = false)
	public AnneeAcademique getAnneeAcademique() {
		return this.anneeAcademique;
	}

	public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
		this.anneeAcademique = anneeAcademique;
	}

	/**
	 * [AffectationLieuStructure.ouvertureOffreFormation] Getter 
	 * @author BELDI Jamel on : 13 oct. 2014  16:15:22
	 * @return the ouvertureOffreFormation
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_ouverture_of", nullable = true)
	public OuvertureOffreFormation getOuvertureOffreFormation() {
		return ouvertureOffreFormation;
	}

	/**
	 * [AffectationLieuStructure.ouvertureOffreFormation] Setter 
	 * @author BELDI Jamel on : 13 oct. 2014  16:15:22
	 * @param ouvertureOffreFormation the ouvertureOffreFormation to set
	 */
	public void setOuvertureOffreFormation(
			OuvertureOffreFormation ouvertureOffreFormation) {
		this.ouvertureOffreFormation = ouvertureOffreFormation;
	}

	
	
}
