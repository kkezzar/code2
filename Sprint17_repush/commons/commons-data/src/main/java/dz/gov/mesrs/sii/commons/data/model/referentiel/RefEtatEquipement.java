package dz.gov.mesrs.sii.commons.data.model.referentiel;

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

@Entity
@Table(name = "ref_etat_equipement", schema = "ppm")
public class RefEtatEquipement implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELBRIK Oualid on : 16 mars. 2014 11:21:13
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Date dateEtat;
	private String observation;
	private Nomenclature nomenclatureByEtatEquipement;
	private RefEquipement refEquipement;

	public RefEtatEquipement() {
	}

	public RefEtatEquipement(int id) {
		this.id = id;
	}

	public RefEtatEquipement(int id, Nomenclature nomenclatureByEtatEquipement,
			RefEquipement refEquipement, Date dateEtat,
			String observation,
			String nomEntite) {
		this.id = id;
		this.dateEtat = dateEtat;
		this.setRefEquipement(refEquipement);
		this.observation = observation;
		this.nomenclatureByEtatEquipement = nomenclatureByEtatEquipement;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@SequenceGenerator(name = "seq_etat_equipement", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_etat_equipement")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_etat_equipement", length = 29)
	public Date getDateEtat() {
		return this.dateEtat;
	}

	public void setDateEtat(Date dateEtat) {
		this.dateEtat = dateEtat;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "etat", nullable = false)
	public Nomenclature getNomenclatureByEtatEquipement() {
		return this.nomenclatureByEtatEquipement;
	}

	public void setNomenclatureByEtatEquipement(
			Nomenclature nomenclatureByEtatEquipement) {
		this.nomenclatureByEtatEquipement = nomenclatureByEtatEquipement;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "equipement")
	public RefEquipement getRefEquipement() {
		return refEquipement;
	}

	public void setRefEquipement(RefEquipement refEquipement) {
		this.refEquipement = refEquipement;
	}

}
