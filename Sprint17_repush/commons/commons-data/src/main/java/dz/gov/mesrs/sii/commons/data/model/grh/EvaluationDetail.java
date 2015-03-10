package dz.gov.mesrs.sii.commons.data.model.grh;

import java.io.Serializable;

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
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

@Entity
@Table(name = "evaluation_detail", schema = "grh")
public class EvaluationDetail implements Serializable, Identifiable<Long> {

	private static final long serialVersionUID = -7657721321460347290L;
	private Long id;
	private Nomenclature critere;
	private EvaluationEmploye evaluationEmploye;
	private String appreciation;
	private Nomenclature niveau;

	public EvaluationDetail() {
		super();
	}

	@Id
	@SequenceGenerator(name = "evaluation_detail_id_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "evaluation_detail_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "critere", nullable = false)
	public Nomenclature getCritere() {
		return critere;
	}

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "evaluation_employe", nullable = false)
	public EvaluationEmploye getEvaluationEmploye() {
		return evaluationEmploye;
	}
	
	@Column(name="appreciation")
	public String getAppreciation() {
		return appreciation;
	}
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "niveau", nullable = false)
	public Nomenclature getNiveau() {
		return niveau;
	}
	
	public void setAppreciation(String appreciation) {
		this.appreciation = appreciation;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCritere(Nomenclature critere) {
		this.critere = critere;
	}

	public void setEvaluationEmploye(EvaluationEmploye evaluationEmploye) {
		this.evaluationEmploye = evaluationEmploye;
	}
	
	public void setNiveau(Nomenclature niveau) {
		this.niveau = niveau;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((critere == null) ? 0 : critere.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EvaluationDetail other = (EvaluationDetail) obj;
		if (critere == null) {
			if (other.critere != null)
				return false;
		} else if (!critere.equals(other.critere))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EvaluationDetail [id=" + id + ", critere=" + critere + "]";
	}

	@Transient
	@Override
	public Long getIdenfiant() {
		return this.getId();
	}

	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

}
