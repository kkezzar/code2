package dz.gov.mesrs.sii.commons.data.model.grh;


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

@Entity
@Table(name = "statut", schema = "grh")
public class Statut implements java.io.Serializable, Identifiable<Integer> {

	private static final long serialVersionUID = -7295829006858295303L;
	private Integer id;
	private String numero;
	private Integer annee;
	private Date dateStatut;
	private String intitule;
	private Date dateEffet;
	private Date datePublication;
	private Date dateFinValidation;
	private Nomenclature typeStatut;
	private Statut statutParent;
	private List<Corps> corps;

	public Statut(){
		super();
	}
	
	@Id
	@SequenceGenerator(name = "grh_statut_id_statut_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grh_statut_id_statut_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@Column(name = "numero", nullable = false)
	public String getNumero() {
		return numero;
	}
	
	@Column(name = "annee", nullable = false)
	public Integer getAnnee() {
		return annee;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "statut_parent")
	public Statut getStatutParent() {
		return this.statutParent;
	}


	@Temporal(TemporalType.DATE)
	@Column(name = "date_statut", length = 13 , nullable=false)
	public Date getDateStatut() {
		return dateStatut;
	}

	@Column(name = "intitule" , nullable=false)
	public String getIntitule() {
		return intitule;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_effet", length = 13, nullable = false)
	public Date getDateEffet() {
		return dateEffet;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_publication", length = 13 , nullable=false)
	public Date getDatePublication() {
		return datePublication;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_fin_validation", length = 13)
	public Date getDateFinValidation() {
		return dateFinValidation;
	}

	@ManyToOne
	@JoinColumn(name = "type_statut", nullable = false)
	public Nomenclature getTypeStatut() {
		return typeStatut;
	}

	@OneToMany(mappedBy = "statut", cascade = CascadeType.ALL)
	public List<Corps> getCorps() {
		return corps;
	}
	
	

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}


	public void setDateStatut(Date dateStatut) {
		this.dateStatut = dateStatut;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public void setDateEffet(Date dateEffet) {
		this.dateEffet = dateEffet;
	}

	public void setDatePublication(Date datePublication) {
		this.datePublication = datePublication;
	}

	public void setDateFinValidation(Date dateFinValidation) {
		this.dateFinValidation = dateFinValidation;
	}

	public void setTypeStatut(Nomenclature typeStatut) {
		this.typeStatut = typeStatut;
	}

	public void setStatutParent(Statut statutParent) {
		this.statutParent = statutParent;
	}

	public void setCorps(List<Corps> corps) {
		this.corps = corps;
	}

	public void setAnnee(Integer annee) {
		this.annee = annee;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((annee == null) ? 0 : annee.hashCode());
		result = prime * result + ((corps == null) ? 0 : corps.hashCode());
		result = prime * result + ((dateEffet == null) ? 0 : dateEffet.hashCode());
		result = prime * result + ((dateFinValidation == null) ? 0 : dateFinValidation.hashCode());
		result = prime * result + ((datePublication == null) ? 0 : datePublication.hashCode());
		result = prime * result + ((dateStatut == null) ? 0 : dateStatut.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((intitule == null) ? 0 : intitule.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((statutParent == null) ? 0 : statutParent.hashCode());
		result = prime * result + ((typeStatut == null) ? 0 : typeStatut.hashCode());
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
		Statut other = (Statut) obj;
		if (annee == null) {
			if (other.annee != null)
				return false;
		} else if (!annee.equals(other.annee))
			return false;
		if (corps == null) {
			if (other.corps != null)
				return false;
		} else if (!corps.equals(other.corps))
			return false;
		if (dateEffet == null) {
			if (other.dateEffet != null)
				return false;
		} else if (!dateEffet.equals(other.dateEffet))
			return false;
		if (dateFinValidation == null) {
			if (other.dateFinValidation != null)
				return false;
		} else if (!dateFinValidation.equals(other.dateFinValidation))
			return false;
		if (datePublication == null) {
			if (other.datePublication != null)
				return false;
		} else if (!datePublication.equals(other.datePublication))
			return false;
		if (dateStatut == null) {
			if (other.dateStatut != null)
				return false;
		} else if (!dateStatut.equals(other.dateStatut))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (intitule == null) {
			if (other.intitule != null)
				return false;
		} else if (!intitule.equals(other.intitule))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (statutParent == null) {
			if (other.statutParent != null)
				return false;
		} else if (!statutParent.equals(other.statutParent))
			return false;
		if (typeStatut == null) {
			if (other.typeStatut != null)
				return false;
		} else if (!typeStatut.equals(other.typeStatut))
			return false;
		return true;
	}
	
	

	@Override
	public String toString() {
		return "Statut [id=" + id + ", numeroStatut=" + numero + ", annee=" + annee + ", dateStatut="
				+ dateStatut + ", intitule=" + intitule + ", dateEffet=" + dateEffet + ", datePublication="
				+ datePublication + ", dateFinValidation=" + dateFinValidation + ", typeStatut=" + typeStatut + "]";
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
