package dz.gov.mesrs.sii.commons.data.model.fve.scolarite;

// Generated 7 oct. 2014 17:24:06 by Hibernate Tools 3.6.0

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
import javax.persistence.UniqueConstraint;

import dz.gov.mesrs.sii.commons.data.model.referentiel.Jour;

/**
 * 
 * @author BELDI Jamel  on : 7 oct. 2014  17:48:33
 */
@Entity
@Table(name = "cellule_horaire", schema = "fve_scolarite", uniqueConstraints = @UniqueConstraint(columnNames = {
		"id_jour", "id_plage_horaire" }))
public class CelluleHoraire implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 7 oct. 2014  17:49:21
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private PlageHoraire plageHoraire;
	private Jour jour;
	private Set<AffectationLieuStructure> affectationLieuStructures = new HashSet<AffectationLieuStructure>(
			0);

	public CelluleHoraire() {
	}

	public CelluleHoraire(int id, PlageHoraire plageHoraire, Jour jour) {
		this.id = id;
		this.plageHoraire = plageHoraire;
		this.jour = jour;
	}

	public CelluleHoraire(int id, PlageHoraire plageHoraire, Jour jour,
			Set<AffectationLieuStructure> affectationLieuStructures) {
		this.id = id;
		this.plageHoraire = plageHoraire;
		this.jour = jour;
		this.affectationLieuStructures = affectationLieuStructures;
	}

	@Id
	@SequenceGenerator(name="cellule_horaire_id_seq_gen", sequenceName="fve_scolarite.cellule_horaire_id_seq")
	@GeneratedValue(generator="cellule_horaire_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_plage_horaire", nullable = false)
	public PlageHoraire getPlageHoraire() {
		return this.plageHoraire;
	}

	public void setPlageHoraire(PlageHoraire plageHoraire) {
		this.plageHoraire = plageHoraire;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_jour", nullable = false)
	public Jour getJour() {
		return this.jour;
	}

	public void setJour(Jour jour) {
		this.jour = jour;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "celluleHoraire")
	public Set<AffectationLieuStructure> getAffectationLieuStructures() {
		return this.affectationLieuStructures;
	}

	public void setAffectationLieuStructures(
			Set<AffectationLieuStructure> affectationLieuStructures) {
		this.affectationLieuStructures = affectationLieuStructures;
	}

}
