package dz.gov.mesrs.sii.commons.data.model.recherche;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

@Entity
@Table(name = "grille_evaluation_detail", schema = "recherche",
			 uniqueConstraints=@UniqueConstraint(columnNames={"id", "id_grille_evaluation", "id_nc_chapitre", "id_nc_critere"}))
public class GrilleEvaluationDetail implements java.io.Serializable , Identifiable<Long>  {

	private static final long serialVersionUID = 1L;
	private Long id;
	private GrilleEvaluation grilleEvaluation;
	private Nomenclature ncChapitre;
	private Nomenclature ncCritere;
	private String numero;
	private Nomenclature ncAppreciation;
	
	public GrilleEvaluationDetail() {
		
	}

	@Id
	@SequenceGenerator(name="grille_evaluation_detail_id_seq_gen", sequenceName="recherche.grille_evaluation_detail_id_seq")
	@GeneratedValue(generator="grille_evaluation_detail_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_grille_evaluation", nullable = false)
	public GrilleEvaluation getGrilleEvaluation() {
		return grilleEvaluation;
	}

	public void setGrilleEvaluation(GrilleEvaluation grilleEvaluation) {
		this.grilleEvaluation = grilleEvaluation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nc_chapitre", nullable = false)
	public Nomenclature getNcChapitre() {
		return ncChapitre;
	}

	public void setNcChapitre(Nomenclature ncChapitre) {
		this.ncChapitre = ncChapitre;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nc_critere", nullable = false)
	public Nomenclature getNcCritere() {
		return ncCritere;
	}

	public void setNcCritere(Nomenclature ncCritere) {
		this.ncCritere = ncCritere;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nc_appreciation", nullable = false)
	public Nomenclature getNcAppreciation() {
		return ncAppreciation;
	}

	public void setNcAppreciation(Nomenclature ncAppreciation) {
		this.ncAppreciation = ncAppreciation;
	}
	
	@Column(name = "numero", nullable = false, length=10)
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
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
