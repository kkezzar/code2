package dz.gov.mesrs.sii.commons.data.model.grh;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;

@Entity
@Table(name = "poste_emploi", schema = "grh")
public class PosteEmploi implements Serializable, Identifiable<Long> {

	private static final long serialVersionUID = 5246884039057810851L;
	private Long id;
	private String code;
	private String objet;
	private String resultat;
	private Date dateCreation;
	private Boolean statut;
	private Nomenclature niveauQualification;
	private Nomenclature niveauCompetence;
	private List<ConnaissancePoste> connaissances;
	private List<HabiletePoste> habiletes;
	private RefEtablissement etablissement;

	public PosteEmploi() {
		super();
	}

	public PosteEmploi(Long id) {
		super();
		this.id = id;
	}

	@Id
	@SequenceGenerator(name = "grh.poste_emploi_id_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grh.poste_emploi_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	@Column(name = "code", nullable = false)
	public String getCode() {
		return code;
	}

	@Column(name = "resultat", nullable = false)
	public String getResultat() {
		return resultat;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_creation", length = 13, nullable = false)
	public Date getDateCreation() {
		return dateCreation;
	}

	@Column(name = "statut")
	public Boolean getStatut() {
		return statut;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "niveau_qualification", nullable = false)
	public Nomenclature getNiveauQualification() {
		return niveauQualification;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "niveau_competence", nullable = false)
	public Nomenclature getNiveauCompetence() {
		return niveauCompetence;
	}

	@OneToMany(mappedBy = "poste", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<ConnaissancePoste> getConnaissances() {
		return connaissances;
	}

	@OneToMany(mappedBy = "poste", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<HabiletePoste> getHabiletes() {
		return habiletes;
	}

	@ManyToOne
	@JoinColumn(name = "ref_etablissement", nullable = false)
	public RefEtablissement getEtablissement() {
		return etablissement;
	}

	@Column(name = "objet")
	public String getObjet() {
		return objet;
	}

	public void setEtablissement(RefEtablissement etablissement) {
		this.etablissement = etablissement;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public void setStatut(Boolean statut) {
		this.statut = statut;
	}

	public void setNiveauQualification(Nomenclature niveauQualification) {
		this.niveauQualification = niveauQualification;
	}

	public void setNiveauCompetence(Nomenclature niveauCompetence) {
		this.niveauCompetence = niveauCompetence;
	}

	public void setConnaissances(List<ConnaissancePoste> connaissances) {
		this.connaissances = connaissances;
	}

	public void setHabiletes(List<HabiletePoste> habiletes) {
		this.habiletes = habiletes;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	@Transient
	@Override
	public Long getIdenfiant() {
		return this.id;
	}

	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((dateCreation == null) ? 0 : dateCreation.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((niveauCompetence == null) ? 0 : niveauCompetence.hashCode());
		result = prime * result + ((niveauQualification == null) ? 0 : niveauQualification.hashCode());
		result = prime * result + ((resultat == null) ? 0 : resultat.hashCode());
		result = prime * result + ((statut == null) ? 0 : statut.hashCode());
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
		PosteEmploi other = (PosteEmploi) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (dateCreation == null) {
			if (other.dateCreation != null)
				return false;
		} else if (!dateCreation.equals(other.dateCreation))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (niveauCompetence == null) {
			if (other.niveauCompetence != null)
				return false;
		} else if (!niveauCompetence.equals(other.niveauCompetence))
			return false;
		if (niveauQualification == null) {
			if (other.niveauQualification != null)
				return false;
		} else if (!niveauQualification.equals(other.niveauQualification))
			return false;
		if (resultat == null) {
			if (other.resultat != null)
				return false;
		} else if (!resultat.equals(other.resultat))
			return false;
		if (statut == null) {
			if (other.statut != null)
				return false;
		} else if (!statut.equals(other.statut))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PosteEmploi [id=" + id + ", code=" + code + ", resultat=" + resultat + ", dateCreation=" + dateCreation
				+ ", statut=" + statut + ", niveauQualification=" + niveauQualification + ", niveauCompetence="
				+ niveauCompetence + "]";
	}

}
