package dz.gov.mesrs.sii.commons.data.model.grh;

// Generated 21 oct. 2014 11:36:27 by Hibernate Tools 3.6.0

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

/**
 * Difficulte generated by hbm2java
 */
@Entity
@Table(name = "difficulte", schema = "grh")
public class Difficulte implements java.io.Serializable, Identifiable<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private DossierEmploye dossierEmploye;
	private Nomenclature nomenclature;
	private Date dateDebut;
	private Date dateFin;

	public Difficulte() {
	}

	public Difficulte(Integer id) {
		this.id = id;
	}

	public Difficulte(Integer id, DossierEmploye dossierEmploye, Nomenclature nomenclature, Date dateDebut, Date dateFin) {
		this.id = id;
		this.dossierEmploye = dossierEmploye;
		this.nomenclature = nomenclature;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	@JoinColumn(name = "type_difficulte")
	public Nomenclature getNomenclature() {
		return this.nomenclature;
	}

	public void setNomenclature(Nomenclature nomenclature) {
		this.nomenclature = nomenclature;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_debut", length = 13)
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
